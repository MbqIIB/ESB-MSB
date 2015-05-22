package seatechit.msbgateway.consumer;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.proxy.caldiscount.Calculatediscount;
import seatechit.msbgateway.proxy.caldiscount.CalculatediscountServiceLocator;
import seatechit.msbgateway.proxy.caldiscount.DiscountReqProvider;
import seatechit.msbgateway.proxy.caldiscount.DiscountRes;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbMessage;

/**
 * Process billing inquiry/payment message
 * 
 * @author trungkien
 * @version 1.0
 */
public class CalDiscountConsumer extends BaseConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1835655754886130241L;
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	private CalculatediscountServiceLocator otpService;
	private Calculatediscount otpPort;

	public CalDiscountConsumer() {
		super();
		try {
			this.consumerType = Global.CONSUMER_CODE_DISCNT;
			ServiceInfo objService = CachedParam
					.getServiceInfoByServiceType(consumerType);
			this.otpService = new CalculatediscountServiceLocator();
			this.otpService.setcalculatediscountPortEndpointAddress(objService
					.getService_url());
			this.otpService
					.setServiceTimeout(objService.getService_timeout() * 1000);
			this.otpPort = otpService.getcalculatediscountPort();
		} catch (ServiceException e) {

		}

	}

	@Override
	public void submit(final MbMessage inMessage, MbMessage outMessage) {
		try {

			this.retErrorInfo.setErrorType(Global.MESSAGE_TYPE_BLGW);
			this.inMessage = inMessage;
			this.outMessage = outMessage;

			this.baseXmlRequestDoc = ElementUtils.getXMLContent(inMessage);
			this.buildLogOfRequestMessage();

			String tran_code = xmlMessageLogInfo.getTran_code();
			String tran_service_code = xmlMessageLogInfo.getTran_service_code();
			buildTransactionInfo(consumerType, tran_code, tran_service_code);
			this.hostMessageLogInfo.setTran_sn(xmlMessageLogInfo
					.getSender_tran_sn());

			if (tran_code.equalsIgnoreCase(MSG_DSC_CAL_PROVIDER_SIMPLE)) {
				calDistcountProvider(inMessage, outMessage);
			} else if (tran_code.equalsIgnoreCase(MSG_DSC_CAL_PROVIDER_OBJECT)) {
				calDistcountProviderObj(inMessage, outMessage);
			}

			// 3)add more detail response time,date....
			this.buildLogOfResponseMessage();
			this.xmlMessageLogInfo.setExecution_date(hostMessageLogInfo
					.getHost_tran_date());
			this.xmlMessageLogInfo.setRef_tran_no(hostMessageLogInfo
					.getHost_tran_sn());
			this.buildXMLMessageResponseHeader();
			this.baseXmlResponseDoc = ElementUtils.getXMLContent(outMessage);
			this.buildLogMessageInfo();
			this.logMessageFacade.insertLogMessage(logMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".submit()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}

	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void calDistcountProvider(final MbMessage inMessage,
			MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String discountCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "discountCode"));
			String transID = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "transID"));
			String serviceCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "serviceCode"));
			String branchCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "branchCode"));
			String amount = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "amount"));
			String currencyCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "currencyCode"));
			String customerType = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "customerType"));
			String businessType = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "businessType"));
			String officeCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "officeCode"));
			String micNumber = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "micNumber"));
			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "channel"));
			String bankType = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "bankType"));

			rebuildTransactionInfo(Global.SIBS_034,
					Global.APP_DEFAULT_BRANCH_CODE);
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			DiscountRes objResponse = null;
			try {
				objResponse = otpPort.calDistcountProvider(discountCode,
						transID, serviceCode, branchCode, amount, currencyCode,
						customerType, businessType, officeCode, micNumber,
						channel, bankType);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_DISCNT);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (objResponse != null) {
				retErrorInfo = CachedParam.mappingErrorCode(
						String.valueOf(Integer.parseInt(objResponse
								.getResponseCode())), objResponse
								.getDescription(), retErrorInfo.getErrorType());
				String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "amount",
						objResponse.getAmount()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"currencyCode", objResponse.getCurrencyCode()));
				outMessage.evaluateXPath(String.format(
						xpathOutput,
						"description",
						objResponse.getDescription() == null ? "" : objResponse
								.getDescription()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"discountAmount", objResponse.getDiscountAmount()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"discoutCode", objResponse.getDiscoutCode()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"responseCode", objResponse.getResponseCode()));
			}
			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? ""
					: objResponse.toString());
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".billingInquiry()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void calDistcountProviderObj(final MbMessage inMessage,
			MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String amount = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "amount"));
			String banktype = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "banktype"));
			String branchCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "branchCode"));
			String businessType = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "businessType"));
			String channel = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "channel"));
			String currencyCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "currencyCode"));
			String customerType = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "customerType"));
			String discountCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "discountCode"));
			String micNumber = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "micNumber"));
			String officeCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "officeCode"));
			String serviceCode = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "serviceCode"));
			String transID = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "transID"));

			rebuildTransactionInfo(Global.SIBS_034,
					Global.APP_DEFAULT_BRANCH_CODE);
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			DiscountRes objResponse = null;
			DiscountReqProvider discountParamsProvider = new DiscountReqProvider();
			discountParamsProvider.setAmount(amount);
			discountParamsProvider.setBanktype(banktype);
			discountParamsProvider.setBranchCode(branchCode);
			discountParamsProvider.setBusinessType(businessType);
			discountParamsProvider.setChannel(channel);
			discountParamsProvider.setCurrencyCode(currencyCode);
			discountParamsProvider.setCustomerType(customerType);
			discountParamsProvider.setDiscountCode(discountCode);
			discountParamsProvider.setMicNumber(micNumber);
			discountParamsProvider.setOfficeCode(officeCode);
			discountParamsProvider.setServiceCode(serviceCode);
			discountParamsProvider.setTransID(transID);
			try {
				objResponse = otpPort
						.calDistcountProviderObj(discountParamsProvider);

			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_DISCNT);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (objResponse != null) {
				retErrorInfo = CachedParam.mappingErrorCode(
						String.valueOf(Integer.parseInt(objResponse
								.getResponseCode())), objResponse
								.getDescription(), retErrorInfo.getErrorType());
				String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "amount",
						objResponse.getAmount()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"currencyCode", objResponse.getCurrencyCode()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"description", objResponse.getDescription()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"discountAmount", objResponse.getDiscountAmount()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"discoutCode", objResponse.getDiscoutCode()));
				outMessage.evaluateXPath(String.format(xpathOutput,
						"responseCode", objResponse.getResponseCode()));
			}
			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("");
			this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? ""
					: objResponse.toString());
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".calDistcountProviderObj()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

}
