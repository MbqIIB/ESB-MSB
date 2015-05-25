package seatechit.msbgateway.consumer;

import org.apache.log4j.Logger;

import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;

/**
 * Process RSA
 * 
 * @author trungkien
 * @version 1.0
 */
public class RSAConsumer extends BaseConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1835655754886130241L;
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();
	private seatechit.esb.rsa.assign.RSAAdmin rsaAdminSrv = new seatechit.esb.rsa.assign.RSAAdmin();
	private seatechit.esb.rsa.verify.AuthUser rsaAuthenSrv = new seatechit.esb.rsa.verify.AuthUser();

	public RSAConsumer() {
		super();
		this.consumerType = Global.CONSUMER_CODE_RSA;
	}

	@Override
	public void submit(final MbMessage inMessage, MbMessage outMessage) {
		try {
			this.retErrorInfo.setErrorType(Global.MESSAGE_TYPE_RSA);
			this.inMessage = inMessage;
			this.outMessage = outMessage;

			this.baseXmlRequestDoc = ElementUtils.getXMLContent(inMessage);
			this.buildLogOfRequestMessage();

			String tran_code = xmlMessageLogInfo.getTran_code();
			String tran_service_code = xmlMessageLogInfo.getTran_service_code();
			buildTransactionInfo(consumerType, tran_code, tran_service_code);
			this.hostMessageLogInfo.setTran_sn(xmlMessageLogInfo.getSender_tran_sn());

			if (tran_code.equals("RSA001")) {
				Assign(inMessage, outMessage);
			} else if (tran_code.equals("RSA002")) {
				checkOTP(inMessage, outMessage);
			} else if (tran_code.equals("RSA003")) {
				CreateUser(inMessage, outMessage);
			} else if (tran_code.equals("RSA004")) {
				deleteUser(inMessage, outMessage);
			} else if (tran_code.equals("RSA005")) {
				Unassign(inMessage, outMessage);
			}

			// 3)add more detail response time,date....
			this.buildLogOfResponseMessage();
			this.xmlMessageLogInfo.setExecution_date(hostMessageLogInfo.getHost_tran_date());
			this.xmlMessageLogInfo.setRef_tran_no(hostMessageLogInfo.getHost_tran_sn());
			this.buildXMLMessageResponseHeader();
			this.baseXmlResponseDoc = ElementUtils.getXMLContent(outMessage);
			this.buildLogMessageInfo();
			this.logMessageFacade.insertLogMessage(logMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".submit()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}

	}

	private void Assign(final MbMessage inMessage, MbMessage outMessage) {
		try {
			int iReturn = 0;
			String xpathInput = "/message/body/field[@name='%s']";
			String userId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "userId"));
			String sn = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "sn"));
			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			iReturn = rsaAdminSrv.assignTokenToUser(userId, sn, true);
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
			outMessage.evaluateXPath(String.format(xpathOutput, "returnCode", iReturn));
			outMessage.evaluateXPath(String.format(xpathOutput, "desc", ""));
			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg("");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (MbException ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".Assign()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

	private void Unassign(final MbMessage inMessage, MbMessage outMessage) {
		try {
			int iReturn = 0;
			String xpathInput = "/message/body/field[@name='%s']";
			String sn = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "sn"));
			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			iReturn = rsaAdminSrv.unassignToken(sn);
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
			outMessage.evaluateXPath(String.format(xpathOutput, "returnCode", iReturn));
			outMessage.evaluateXPath(String.format(xpathOutput, "desc", ""));
			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg("");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (MbException ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".Unassign()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

	private void CreateUser(final MbMessage inMessage, MbMessage outMessage) {
		try {
			int iReturn = 0;
			String xpathInput = "/message/body/field[@name='%s']";
			String tknSN = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "tknSN"));
			String userId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "userId"));
			String pwd = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "pwd"));
			String fname = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "fname"));
			String lname = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "lname"));
			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			iReturn = rsaAdminSrv.createUser(tknSN, userId, pwd, fname, lname);
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
			outMessage.evaluateXPath(String.format(xpathOutput, "returnCode", iReturn));
			outMessage.evaluateXPath(String.format(xpathOutput, "desc", ""));
			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg("");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (MbException ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".CreateUser()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

	private void deleteUser(final MbMessage inMessage, MbMessage outMessage) {
		try {
			int iReturn = 0;
			String xpathInput = "/message/body/field[@name='%s']";
			String userId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "userId"));
			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			iReturn = rsaAdminSrv.deleteUser(userId);
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
			outMessage.evaluateXPath(String.format(xpathOutput, "returnCode", iReturn));
			outMessage.evaluateXPath(String.format(xpathOutput, "desc", ""));
			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg("");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (MbException ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".deleteUser()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

	private void checkOTP(final MbMessage inMessage, MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String userId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "userId"));
			String otp = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "otp"));
			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			int iReturn = rsaAuthenSrv.auth(userId, otp);
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
			outMessage.evaluateXPath(String.format(xpathOutput, "returnCode", iReturn));
			outMessage.evaluateXPath(String.format(xpathOutput, "desc", ""));
			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg("");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (MbException ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".checkOTP()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}
}
