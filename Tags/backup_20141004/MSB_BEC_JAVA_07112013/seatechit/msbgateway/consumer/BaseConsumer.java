package seatechit.msbgateway.consumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import seatechit.msbgateway.dbaccess.entity.HOSTMessagLogInfo;
import seatechit.msbgateway.dbaccess.entity.HostTranCodeInfo;
import seatechit.msbgateway.dbaccess.entity.LogMessage;
import seatechit.msbgateway.dbaccess.entity.XMLMessageLogInfo;
import seatechit.msbgateway.dbaccess.facade.LogMessageFacade;
import seatechit.msbgateway.dbaccess.facade.SalaryFacade;
import seatechit.msbgateway.dbaccess.facade.SysParamFacade;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;

/**
 * 
 * Base class of XML message processing.
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6 *
 */
public abstract class BaseConsumer extends Global {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private final String CLASS_NAME = this.getClass().getName();

	protected String consumerType = null;
	protected XMLMessageLogInfo xmlMessageLogInfo = null;
	protected HOSTMessagLogInfo hostMessageLogInfo = null;
	protected SysParamFacade sysParamFacade = null;
	protected SalaryFacade salaryFacade = null;
	protected LogMessageFacade logMessageFacade = null;
	protected LogMessage logMessage = null;
	protected ErrorInfo retErrorInfo = null;
	protected ArrayList<HOSTMessagLogInfo> arrRetHOSTMessageLogInfo = null;
	protected MbMessage inMessage = null;
	protected MbMessage outMessage = null;
	protected String baseXmlRequestDoc = null;
	protected String baseXmlResponseDoc = null;
	//protected String refTranCode = "";
	//protected String hostRequestMessage = "";
	//protected String hostResponseMessage = "";

	public abstract void submit(MbMessage inMessage, MbMessage outMessage);

	public BaseConsumer() {
		DOMConfigurator.configure("log4j.xml");
		hostMessageLogInfo = new HOSTMessagLogInfo();
		salaryFacade = new SalaryFacade();
		logMessageFacade = new LogMessageFacade();
		sysParamFacade = new SysParamFacade();
		xmlMessageLogInfo = new XMLMessageLogInfo();
		arrRetHOSTMessageLogInfo = new ArrayList<HOSTMessagLogInfo>();
		logMessage = new LogMessage();
		baseXmlRequestDoc = "";
		baseXmlResponseDoc = "";
		retErrorInfo = new ErrorInfo(ERR_SYSTEM_OK, "System Ok", Global.MESSAGE_TYPE_OTHER);
	}

	/**
	 * Remark: Voi giao dich khong phai dang batch thi tran_sn=sender_tran_sn
	 * 
	 * @param consumterType
	 * @param tran_code
	 * @param tran_service_code
	 */
	protected void buildTransactionInfo(String consumterType, String tran_code, String tran_service_code) {
		try {
			hostMessageLogInfo.setNumOfRetry("1");
			hostMessageLogInfo.setBatch_no("1");
			hostMessageLogInfo.setWs_ip(Global.APP_DEFAULT_CLIENT_ADDR);
			hostMessageLogInfo.setWs_name(Global.APP_DEFAULT_CLIENT_NAME);
			hostMessageLogInfo.setBranch_code(Global.APP_DEFAULT_BRANCH_CODE);
			hostMessageLogInfo.setTeller_id(CachedParam.getRandomBankTeller());
			hostMessageLogInfo.setApprover_id(CachedParam.getRandomBankApprove());
			hostMessageLogInfo.setTran_sn(xmlMessageLogInfo.getSender_tran_sn());
			hostMessageLogInfo.setRef_service(consumterType);
			hostMessageLogInfo.setRef_channel(CachedParam.getRandomChannel());
			// hostMessageLogInfo.setHost_tran_date(AppUtils.convertYYYYMMDD_DDMMYY(xmlMessageLogInfo.getSend_date()));
			hostMessageLogInfo.setHost_tran_date(sysParamFacade.getParamByName(Global.APP_TRAN_DATE).getParamValue());
			String host_real_date = hostMessageLogInfo.getHost_tran_date().length() < 6 ? "0" + hostMessageLogInfo.getHost_tran_date() : hostMessageLogInfo.getHost_tran_date();
			hostMessageLogInfo.setHost_real_date(AppUtils.getJulian7FromDate((Date) (new SimpleDateFormat("ddMMyy")).parse(host_real_date)));
		} catch (ParseException e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".buildTransactionInfo()\n").append("Error message:").append(e.toString() + "\n"));
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".buildTransactionInfo()\n").append("Error message:").append(e.toString() + "\n"));
		}
	}

	/**
	 * Tu ref_trancode cu the build cac thong tin cua giao dich vao core(sequence,trancode..)
	 * 
	 * @param refTranCode
	 * @param branch_code
	 */
	protected void rebuildTransactionInfo(String refTranCode, String branch_code) {
		try {
			HostTranCodeInfo hostTranInfo = CachedParam.getHostTranCodeByRefTranCode(refTranCode);
			hostMessageLogInfo.setBranch_code(branch_code);
			hostMessageLogInfo.setRef_tran_code(refTranCode);
			hostMessageLogInfo.setRef_channel(CachedParam.getRandomChannel());
			hostMessageLogInfo.setNeedSynchronized(hostTranInfo.getNeedSynchronized());
			hostMessageLogInfo.setHost_tran_code(hostTranInfo.getReal_host_tran_code());
			hostMessageLogInfo.setHost_tran_sn(sysParamFacade.getTransactionSequence());
		} catch (ParseException e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".rebuildTransactionInfo()\n").append("Error message:").append(e.toString() + "\n"));
		} catch (Exception e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".rebuildTransactionInfo()\n").append("Error message:").append(e.toString() + "\n"));
		}
	}

	/**
	 * 
	 * 
	 */
	protected void buildLogOfRequestMessage() {
		xmlMessageLogInfo = new XMLMessageLogInfo();
		xmlMessageLogInfo.setMessage_sn(ElementUtils.getXMLElementInStringValue(inMessage, XML_HDR_FLD_MESSAGE_SN));
		xmlMessageLogInfo.setTran_code(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_TRAN_CODE));
		xmlMessageLogInfo.setSender_id(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_SENDER_ID));
		xmlMessageLogInfo.setSender_tran_sn(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_SENDER_TRAN_SN));
		xmlMessageLogInfo.setSend_date(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_SEND_DATE));// AppUtils.now(Global.DEF_FORMAT_DATE_YYYYMMDD)));
		xmlMessageLogInfo.setSend_time(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_SEND_TIME));
		xmlMessageLogInfo.setReceive_date(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_RESP_DATE));
		xmlMessageLogInfo.setTran_service_code(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_TRAN_SERVICE_CODE));
		xmlMessageLogInfo.setReceive_time(AppUtils.now(DEF_FORMAT_LOGMSG_DATE_TIME));
		xmlMessageLogInfo.setReceiver_id(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_RECEIVER_ID));
		xmlMessageLogInfo.setReceiver_tran_sn(ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_SENDER_TRAN_SN));
	}

	/**
	 * Set more
	 */
	protected void buildLogOfResponseMessage() {
		xmlMessageLogInfo.setResp_date(AppUtils.now(Global.DEF_FORMAT_DATE_YYYYMMDD));
		xmlMessageLogInfo.setResp_time(AppUtils.now(DEF_FORMAT_LOGMSG_DATE_TIME));
		xmlMessageLogInfo.setResp_code(String.valueOf(this.retErrorInfo.getErrorCode()));
		xmlMessageLogInfo.setResp_msg(this.retErrorInfo.getErrorMessage());
		xmlMessageLogInfo.setServices(this.consumerType);
	}

	/**
	 * 
	 * 
	 */
	protected void buildLogMessageInfo() {
		logMessage.setMessageInfo(xmlMessageLogInfo);
		logMessage.setArrHOSTMessagLogInfo(arrRetHOSTMessageLogInfo);
		logMessage.setTradeMessageRequest(baseXmlRequestDoc);
		logMessage.setTradeMessageResponse(baseXmlResponseDoc);
	}

	/**
	 * Build header of Response message
	 * 
	 */
	protected void buildXMLMessageResponseHeader() {
		try {
			String xpath = "/message/head/field[@name='%s']";
			ElementUtils.getXMLElement(outMessage, String.format(xpath, "message_type")).setValue(String.valueOf(Global.EBANK_MESSAGE_TYPE_RESPONSE));
			ElementUtils.getXMLElement(outMessage, String.format(xpath, "receiver_id")).setValue(xmlMessageLogInfo.getReceiver_id());
			ElementUtils.getXMLElement(outMessage, String.format(xpath, "receiver_tran_sn")).setValue(xmlMessageLogInfo.getSender_tran_sn());
			ElementUtils.getXMLElement(outMessage, String.format(xpath, "resp_date")).setValue(xmlMessageLogInfo.getResp_date());
			ElementUtils.getXMLElement(outMessage, String.format(xpath, "resp_time")).setValue(xmlMessageLogInfo.getResp_time());
			ElementUtils.getXMLElement(outMessage, String.format(xpath, "resp_code")).setValue(xmlMessageLogInfo.getResp_code());
			ElementUtils.getXMLElement(outMessage, String.format(xpath, "resp_msg")).setValue(xmlMessageLogInfo.getResp_msg() == null ? "" : xmlMessageLogInfo.getResp_msg());
		} catch (MbException e) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".buildXMLMessageResponseHeader()\n").append("Error message:").append(e.toString() + "\n"));
		}
	}

	/**
	 * Build log message to write to log file.
	 * 
	 */
	protected String getLogTradeMessage() {
		String retLogMsg = "";
		String tranCode = xmlMessageLogInfo.getTran_code();
		String message_sn = xmlMessageLogInfo.getMessage_sn();

		retLogMsg = ">>>>>>>>>>>>>>>>>>>>>>>>\n";
		retLogMsg = retLogMsg + "Message Code: [" + tranCode + "]\n";
		retLogMsg = retLogMsg + "Message SN: [" + message_sn + "]\n";
		retLogMsg = retLogMsg + "Resp code: [" + xmlMessageLogInfo.getResp_code() + "]\n";
		retLogMsg = retLogMsg + "Resp message: [" + xmlMessageLogInfo.getResp_msg() + "]\n";
		retLogMsg = retLogMsg + "Processing time: [" + (Double.parseDouble(xmlMessageLogInfo.getResp_time()) - Double.parseDouble(xmlMessageLogInfo.getReceive_time())) + "]\n";
		retLogMsg = retLogMsg + "------------------------------\n";
		retLogMsg = retLogMsg + "Xml Request: [" + baseXmlRequestDoc + "]\n";
		retLogMsg = retLogMsg + "------------------------------\n";
		for (int i = 0; i < arrRetHOSTMessageLogInfo.size(); i++) {
			retLogMsg = retLogMsg + ">>>>Host Request num#" + i + ":\n";
			retLogMsg = retLogMsg + "Start Of Record	[";
			retLogMsg = retLogMsg + arrRetHOSTMessageLogInfo.get(i).getHostRequestMsg();
			retLogMsg = retLogMsg + "] End Of Record\n";
			retLogMsg = retLogMsg + "<<<<Host Response num#" + i + ":\n";
			retLogMsg = retLogMsg + "Start Of Record	[";
			retLogMsg = retLogMsg + arrRetHOSTMessageLogInfo.get(i).getHostResponseMsg();
			retLogMsg = retLogMsg + "] End Of Record\n";
			retLogMsg = retLogMsg + "------------------------------\n";
		}
		retLogMsg = retLogMsg + "Xml Response: [" + baseXmlResponseDoc + "]\n";
		return retLogMsg;
	}
}
