package seatechit.msbgateway.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import seatechit.msbgateway.consumer.BaseConsumer;
import seatechit.msbgateway.consumer.CalDiscountConsumer;
import seatechit.msbgateway.consumer.EmailConsumer;
import seatechit.msbgateway.consumer.ErrorInfo;
import seatechit.msbgateway.consumer.FEEConsumer;
import seatechit.msbgateway.consumer.OTPConsumer;
import seatechit.msbgateway.consumer.RSAConsumer;
import seatechit.msbgateway.consumer.SIBSConsumer;
import seatechit.msbgateway.consumer.SMLConsumer;
import seatechit.msbgateway.consumer.SMSConsumer;
import seatechit.msbgateway.consumer.SalaryConsumer;
import seatechit.msbgateway.consumer.WUABMTConsumer;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedDBParam;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;

public class MessageProcessor extends MbJavaComputeNode {
	final String CLASS_NAME = this.getClass().getName();
	final Logger logger = Logger.getLogger(MessageProcessor.class);
	final String app_version_test = "1.0";
	final String app_version_real = "2.0";

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		int errorCode = Global.ERR_SYSTEM_OK;

		// Dua tham so ket noi db len cache
		CachedDBParam.getInstance();

		// Dua cac tham so len cache
		CachedParam.getInstance(false);
		DOMConfigurator.configure(CachedParam
				.getSystemParam(Global.APP_LOGPATH));

		MbOutputTerminal out = getOutputTerminal("out");
		MbMessage inMessage = inAssembly.getMessage();
		MbMessage outMessage = new MbMessage(inMessage);

		MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly,
				outMessage);

		// Ghi input message ra log file
		if (logger.isDebugEnabled()) {
			logger.info("INPUT MESSAGE>>>>>>>>>>>>>>>>"
					+ ElementUtils.getXMLContent(inMessage));
			logger.info(getMQLogInfo(inMessage, ""));
		}
		// Dat correl_id cua out message = message_id cua in message
		settingRelationID(inMessage, outMessage);

		// Kiem tra hop le phan header cua message
		if (errorCode == Global.ERR_SYSTEM_OK)
			errorCode = checkRequestDocument(inMessage);

		if (errorCode != Global.ERR_SYSTEM_OK) {
			ErrorInfo tempErrorInfo = new ErrorInfo(errorCode, "", "");
			removeBodyElement(outMessage);
			buildInvalidRequestDocument(outMessage, tempErrorInfo);
		} else {
			removeBodyElement(outMessage);
			// Xac dinh Consumer cu the cho message va forward process den
			BaseConsumer objBaseConsumer = getRealConsumer(inMessage);
			objBaseConsumer.submit(inMessage, outMessage);
		}
		// Ghi out message ra log file
		if (logger.isDebugEnabled()) {
			logger.info("OUTPUT MESSAGE>>>>>>>>>>>>>>>>"
					+ ElementUtils.getXMLContent(outMessage));
			logger.info(getMQLogInfo(outMessage,
					AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME)));
		}
		out.propagate(outAssembly);
	}

	/**
	 * Lay cac thong tin cua input_msg (thoi gian put msg) de ghi log
	 */
	private String getMQLogInfo(MbMessage inMessage, String time) {
		String retLogMessage = "";
		try {
			MbElement pTimeE = inMessage.getRootElement().getFirstChild()
					.getNextSibling().getFirstElementByPath("PutTime");
			MbElement correlId = inMessage.getRootElement().getFirstChild()
					.getNextSibling().getFirstElementByPath("CorrelId");
			MbElement msgId = inMessage.getRootElement().getFirstChild()
					.getNextSibling().getFirstElementByPath("MsgId");
			retLogMessage = "MQ Message info: >>>>>>>>>>>>>>>>>>>>\n";
			retLogMessage += "[PutTime:]"
					+ (time.length() == 0 ? pTimeE.getValueAsString() : time)
					+ "\n";
			retLogMessage += "[MessageId:]" + msgId.getValueAsString() + "\n";
			retLogMessage += "[CorrelId:]" + correlId.getValueAsString() + "\n";
			retLogMessage += "=====================================\n";
		} catch (MbException e) {
			e.printStackTrace();
		}
		return retLogMessage;
	}

	/**
	 * Xac dinh Consumer phu hop cho tung message
	 */
	private BaseConsumer getRealConsumer(MbMessage inMessage) {
		String tran_code = ElementUtils.getXMLElementInStringValue(inMessage,
				Global.XML_HDR_FLD_TRAN_CODE);
		String tran_service_code = ElementUtils.getXMLElementInStringValue(
				inMessage, Global.XML_HDR_FLD_TRAN_SERVICE_CODE);
		if (tran_code.equalsIgnoreCase(Global.MSG_CM_SEND_EMAIL)) {
			return new EmailConsumer();
		} else if (tran_code.equalsIgnoreCase(Global.MSG_CM_SEND_SMS)) {
			return new SMSConsumer();
		} else if (tran_code
				.equalsIgnoreCase(Global.MSG_MP_INQUIRY_BILLPAYMENT_INFOR)
				|| tran_code
						.equalsIgnoreCase(Global.MSG_MP_CREATE_BILLPAYMENT_TRANSACTION)) {
			return new SMLConsumer();
		} else if (tran_code
				.equalsIgnoreCase(Global.MSG_WU_TRANSACTION_INQUIRY)
				|| tran_code.equalsIgnoreCase(Global.MSG_WU_PICKUP_VALIDATE)
				|| tran_code.equalsIgnoreCase(Global.MSG_WU_PICKUP_RECEIVE)) {
			return new WUABMTConsumer();
		} else if (tran_code.equalsIgnoreCase(Global.MSG_TR_TRANSFER_MONEY)
				&& (tran_service_code.equalsIgnoreCase("CPT204") || tran_service_code
						.equalsIgnoreCase("CSP200"))) {
			return new SalaryConsumer();
			// Kienvt-them cac consumer moi
		} else if (tran_code.equalsIgnoreCase(Global.MSG_FEE_CAL_FEE_OBJ)) {
			return new FEEConsumer();
		} else if (tran_code
				.equalsIgnoreCase(Global.MSG_DSC_CAL_PROVIDER_SIMPLE)
				|| tran_code
						.equalsIgnoreCase(Global.MSG_DSC_CAL_PROVIDER_OBJECT)) {
			return new CalDiscountConsumer();
		} else if (tran_code.equalsIgnoreCase(Global.MSG_OTP_CHECK_OTP)
				|| tran_code.equalsIgnoreCase(Global.MSG_OTP_GET_OTP)) {
			return new OTPConsumer();
		} else if (tran_code.equalsIgnoreCase(Global.MSG_RSA_Assign)
				|| tran_code.equalsIgnoreCase(Global.MSG_RSA_checkOTP)
				|| tran_code.equalsIgnoreCase(Global.MSG_RSA_CreateUser)
				|| tran_code.equalsIgnoreCase(Global.MSG_RSA_deleteUser)
				|| tran_code.equalsIgnoreCase(Global.MSG_RSA_Unassign)) {
			return new RSAConsumer();
			// --end
		} else {
			return new SIBSConsumer();
		}
	}

	/**
	 * Sau khi copy noi dung cua input_msg sang output_msg thi bo phan body cua
	 * output_msg
	 */
	@SuppressWarnings("unchecked")
	private void removeBodyElement(MbMessage outMessage) throws MbException {
		try {
			List<MbElement> lstOfElement = ((List<MbElement>) outMessage
					.evaluateXPath("/message/body"));
			if (lstOfElement.size() > 0) {
				lstOfElement.get(0).detach();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Kiem tra hop le input_msg
	 */
	private int checkRequestDocument(final MbMessage inMessage) {
		if (ElementUtils.getXMLContent(inMessage).length() > 0) {
			int errCode = Global.ERR_SYSTEM_OK;
			if (ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_HDR_FLD_VERSION).length() == 0)
				errCode = Global.ERR_CM_REQ_DOC_VERSION_MISSING;
			if (ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_HDR_FLD_MESSAGE_TYPE).length() == 0)
				errCode = Global.ERR_CM_REQ_DOC_MESSAGE_TYPE_MISSING;
			if (ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_HDR_FLD_MESSAGE_SN).length() == 0)
				errCode = Global.ERR_CM_REQ_DOC_MESSAGE_SN_MISSING;
			if (ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_HDR_FLD_SENDER_TRAN_SN).length() == 0
					|| ElementUtils.getXMLElementInStringValue(inMessage,
							Global.XML_HDR_FLD_SENDER_TRAN_SN).length() > 20)
				errCode = Global.ERR_CM_REQ_DOC_SENDER_TRAN_SN_MISSING;
			if (isValidAppSender(ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_HDR_FLD_SENDER_ID)) == false)
				errCode = Global.ERR_CM_REQ_DOC_SENDER_ID_MISSING;
			if (ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_HDR_FLD_SEND_TIME).length() == 0)
				errCode = Global.ERR_CM_REQ_DOC_SEND_TIME_MISSING;
			if (!isValidTranCode(ElementUtils.getXMLElementInStringValue(
					inMessage, Global.XML_HDR_FLD_TRAN_CODE)))
				errCode = Global.ERR_CM_REQ_DOC_TRAN_CODE_MISSING;
			if (ElementUtils.getXMLElementInStringValue(inMessage,
					Global.XML_HDR_FLD_RECEIVER_ID).length() == 0)
				errCode = Global.ERR_CM_REQ_DOC_RECEIVER_ID_MISSING;
			return errCode;
		} else {
			return 0;//Global.ERR_CM_DOC_FORMAT_INCORRECT;
		}
	}

	/**
	 * Kiem tra truong sender_id trong input_msg co hop le khong
	 */
	private boolean isValidAppSender(String appSender) {
		ArrayList<String> arrAppSender = new ArrayList<String>();
		arrAppSender.add(Global.APP_SENDER_SMS);
		arrAppSender.add(Global.APP_SENDER_MOBILE_APP);
		arrAppSender.add(Global.APP_SENDER_MOBILE_WAP);
		arrAppSender.add(Global.APP_SENDER_IBS);
		return arrAppSender.contains(appSender == null ? "" : appSender);
	}

	/**
	 * Kiem tra truong tranCode cua input_msg co hop le khong
	 * 
	 */
	private boolean isValidTranCode(String tranCode) {
		ArrayList<String> arrTranCode = new ArrayList<String>();
		arrTranCode.add(Global.MSG_CM_SEND_SMS);
		arrTranCode.add(Global.MSG_CM_SEND_EMAIL);
		arrTranCode.add(Global.MSG_CM_INQUIRY_CIF_INFOR);
		arrTranCode.add(Global.MSG_CM_INQUIRY_CIF_ACCOUNTS);
		arrTranCode.add(Global.MSG_CM_INQUIRY_REL_CIF_ACCOUNTS);
		arrTranCode.add(Global.MSG_CM_INQUIRY_MINISTATEMENT_BY_ACCOUNT);
		arrTranCode.add(Global.MSG_OS_CREATE_FD_ACCOUNT);
		arrTranCode.add(Global.MSG_OS_CREATE_FD_RECEIPT);
		arrTranCode.add(Global.MSG_OS_TRANSFER_FD_RECEIPT);
		arrTranCode.add(Global.MSG_OS_SETTLEMENT_FD_ACCOUNT);
		arrTranCode.add(Global.MSG_MP_CREATE_BILLPAYMENT_TRANSACTION);
		arrTranCode.add(Global.MSG_MP_INQUIRY_BILLPAYMENT_INFOR);
		arrTranCode.add(Global.MSG_MP_INQUIRY_CREDITCARD_BILL_INFOR);
		arrTranCode.add(Global.MSG_MP_PAYMENT_CREDITCARD_BILL);
		arrTranCode.add(Global.MSG_CS_ACTIVATED_ATMCARD);
		arrTranCode.add(Global.MSG_CS_APPLICATION_CREDITCARD);
		arrTranCode.add(Global.MSG_TR_TRANSFER_MONEY);
		arrTranCode.add(Global.MSG_TR_CREATE_CASHFLOW_TRANSACTION);
		arrTranCode.add(Global.MSG_TR_CANCEL_SCHEDULE_TRANSFER);
		arrTranCode.add(Global.MSG_TR_CANCEL_CASHFLOW_TRANSACTION);
		arrTranCode.add(Global.MSG_LN_PAYMENT_LN_TRANSACTION);
		arrTranCode.add(Global.MSG_FX_CREATE_EXCHANGEMONEY_TRANSACTION);
		arrTranCode.add(Global.MSG_TF_SEND_REPORT_TO_PAYMENTCENTER);
		arrTranCode.add(Global.MSG_SETTING_AFT);
		arrTranCode.add(Global.MSG_DESTROY_AFT);
		arrTranCode.add(Global.MSG_QUERY_AFT);
		arrTranCode.add(Global.MSG_WU_TRANSACTION_INQUIRY);
		arrTranCode.add(Global.MSG_WU_PICKUP_VALIDATE);
		arrTranCode.add(Global.MSG_WU_PICKUP_RECEIVE);
		arrTranCode.add(Global.MSG_QUERY_FD_RECEIPT);

		arrTranCode.add(Global.MSG_FEE_CAL_FEE_OBJ);
		arrTranCode.add(Global.MSG_DSC_CAL_PROVIDER_SIMPLE);
		arrTranCode.add(Global.MSG_DSC_CAL_PROVIDER_OBJECT);
		arrTranCode.add(Global.MSG_OTP_CHECK_OTP);
		arrTranCode.add(Global.MSG_OTP_GET_OTP);

		arrTranCode.add(Global.MSG_RSA_Assign);
		arrTranCode.add(Global.MSG_RSA_checkOTP);
		arrTranCode.add(Global.MSG_RSA_CreateUser);
		arrTranCode.add(Global.MSG_RSA_deleteUser);
		arrTranCode.add(Global.MSG_RSA_Unassign);

		return arrTranCode.contains(tranCode == null ? "" : tranCode.trim());
	}

	/**
	 * Trong truong hop message khong hop le tra ve message thong bao khong hop
	 * le
	 */
	private void buildInvalidRequestDocument(MbMessage outMessage,
			ErrorInfo inErrorInfo) {
		String xpath = "/message/head/field[@name='%s']";
		try {
			ElementUtils.getXMLElement(outMessage,
					String.format(xpath, "resp_code")).setValue(
					inErrorInfo.getErrorCode());
			ElementUtils.getXMLElement(outMessage,
					String.format(xpath, "resp_msg")).setValue(
					inErrorInfo.getErrorMessage());
		} catch (MbException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dat lai correlId cua output_msg = messageId cua input_msg
	 */
	@SuppressWarnings("unchecked")
	private void settingRelationID(final MbMessage inMessage,
			MbMessage outMessage) throws MbException {
		try {
			MbElement mbInElement = inMessage.getRootElement();
			mbInElement = (MbElement) ((List) mbInElement
					.evaluateXPath("MQMD/MsgId")).get(0);
			MbElement mbOutElement = outMessage.getRootElement();
			mbOutElement = (MbElement) ((List) mbOutElement
					.evaluateXPath("MQMD/CorrelId")).get(0);
			mbOutElement.setValue((byte[]) mbInElement.getValue());
		} catch (Exception ex) {
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".settingRelationID()\n")
					.append("Error message:").append(ex.toString() + "\n"));
		}
	}
}
