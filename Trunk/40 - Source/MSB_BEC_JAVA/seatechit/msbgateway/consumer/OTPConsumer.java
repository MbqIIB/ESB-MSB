package seatechit.msbgateway.consumer;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.proxy.otpgen.OtpCalculator;
import seatechit.msbgateway.proxy.otpgen.OtpCalculatorServiceLocator;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbMessage;

/**
 * 
 * 
 * @author trungkien
 * @version 1.0
 */
public class OTPConsumer extends BaseConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1835655754886130241L;
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	private OtpCalculatorServiceLocator otpService;
	private OtpCalculator otpPort;

	public OTPConsumer() {
		super();
		try {
			this.consumerType = Global.CONSUMER_CODE_OTP;
			ServiceInfo objService = CachedParam.getServiceInfoByServiceType(consumerType);
			this.otpService = new OtpCalculatorServiceLocator();
			this.otpService.setOtpCalculatorPortEndpointAddress(objService.getService_url());
			this.otpService.setServiceTimeout(objService.getService_timeout() * 1000);
			this.otpPort = otpService.getOtpCalculatorPort();
		} catch (ServiceException e) {

		}

	}

	@Override
	public void submit(final MbMessage inMessage, MbMessage outMessage) {
		try {
			this.retErrorInfo.setErrorType(Global.MESSAGE_TYPE_OTP);
			this.inMessage = inMessage;
			this.outMessage = outMessage;

			this.baseXmlRequestDoc = ElementUtils.getXMLContent(inMessage);
			this.buildLogOfRequestMessage();

			String tran_code = xmlMessageLogInfo.getTran_code();
			String tran_service_code = xmlMessageLogInfo.getTran_service_code();
			buildTransactionInfo(consumerType, tran_code, tran_service_code);
			this.hostMessageLogInfo.setTran_sn(xmlMessageLogInfo.getSender_tran_sn());

			if (tran_code.equalsIgnoreCase(MSG_OTP_CHECK_OTP)) {
				checkOtp(inMessage, outMessage);
			} else if (tran_code.equalsIgnoreCase(MSG_OTP_GET_OTP)) {
				getOtp(inMessage, outMessage);
			} else {
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

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void checkOtp(final MbMessage inMessage, MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String transactionID = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "transactionID"));
			String otpTransactionId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "otpTransactionId"));
			String iValueTime = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "iValueTime"));
			String sOtp = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "sOtp"));

			rebuildTransactionInfo(Global.SIBS_034, Global.APP_DEFAULT_BRANCH_CODE);

			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			int retOtp = 0;
			try {
				retOtp = otpPort.checkOtp(transactionID, Long.parseLong(otpTransactionId), Integer.parseInt(iValueTime), sOtp);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_OTP);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			if (retOtp == ERR_SYSTEM_OK) {
				retErrorInfo = CachedParam.mappingErrorCode(String.valueOf(ERR_SYSTEM_OK), "", retErrorInfo.getErrorType());
			} else {
				retErrorInfo = CachedParam.mappingErrorCode(String.valueOf(retOtp), "", retErrorInfo.getErrorType());
			}
			String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
			outMessage.evaluateXPath(String.format(xpathOutput, "return", retOtp));

			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg("");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".checkOtp()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

	/**
	 * 
	 * @param outMessage
	 * @param orgXMLDesc
	 */

	private void getOtp(final MbMessage inMessage, MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String transactionID = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "transactionID"));

			rebuildTransactionInfo(Global.SIBS_034, Global.APP_DEFAULT_BRANCH_CODE);

			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			String OtpValue = null;
			try {
				OtpValue = otpPort.getOtp(transactionID);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_OTP);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			retErrorInfo = CachedParam.mappingErrorCode(String.valueOf(ERR_SYSTEM_OK), "", retErrorInfo.getErrorType());

			String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
			outMessage.evaluateXPath(String.format(xpathOutput, "return", OtpValue));

			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg("");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".getOtp()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}
}
