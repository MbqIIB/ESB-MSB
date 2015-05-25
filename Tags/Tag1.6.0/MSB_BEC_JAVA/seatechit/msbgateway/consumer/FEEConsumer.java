package seatechit.msbgateway.consumer;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.proxy.calfee.Calculatefee;
import seatechit.msbgateway.proxy.calfee.CalculatefeeServiceLocator;
import seatechit.msbgateway.proxy.calfee.FeeRequest;
import seatechit.msbgateway.proxy.calfee.FeeRes;
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
public class FEEConsumer extends BaseConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1835655754886130241L;
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	private CalculatefeeServiceLocator feeService;
	private Calculatefee feePort;

	public FEEConsumer() {
		super();
		try {
			this.consumerType = Global.CONSUMER_CODE_FEE;
			ServiceInfo objService = CachedParam.getServiceInfoByServiceType(consumerType);
			this.feeService = new CalculatefeeServiceLocator();
			this.feeService.setcalculatefeePortEndpointAddress(objService.getService_url());
			this.feeService.setServiceTimeout(objService.getService_timeout() * 1000);
			this.feePort = feeService.getcalculatefeePort();
		} catch (ServiceException e) {

		}

	}

	@Override
	public void submit(final MbMessage inMessage, MbMessage outMessage) {
		try {
			this.retErrorInfo.setErrorType(Global.MESSAGE_TYPE_FEE);
			this.inMessage = inMessage;
			this.outMessage = outMessage;

			this.baseXmlRequestDoc = ElementUtils.getXMLContent(inMessage);
			this.buildLogOfRequestMessage();

			String tran_code = xmlMessageLogInfo.getTran_code();
			String tran_service_code = xmlMessageLogInfo.getTran_service_code();
			buildTransactionInfo(consumerType, tran_code, tran_service_code);
			this.hostMessageLogInfo.setTran_sn(xmlMessageLogInfo.getSender_tran_sn());

			if (tran_code.equalsIgnoreCase(MSG_FEE_CAL_FEE_OBJ)) {
				calFeeObj(inMessage, outMessage);
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
	private void calFeeObj(final MbMessage inMessage, MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String amount = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "amount"));
			String bankType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "bankType"));
			String branchCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "branchCode"));
			String businessType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "businessType"));
			String channel = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "channel"));
			String currencyCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "currencyCode"));
			String customerType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "customerType"));
			String feeCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "feeCode"));
			String fromAcc = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "fromAcc"));
			String fromOrgBranchCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "fromOrgBranchCode"));
			String micNumber = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "micNumber"));
			String productType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "productType"));
			String officeCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "officeCode"));
			String toAcc = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "toAcc"));
			String toBranchCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "toBranchCode"));
			String toOrgBranchCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "toOrgBranchCode"));
			String transID = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "transID"));
			String transType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "transType"));

			rebuildTransactionInfo(Global.SIBS_050, Global.APP_DEFAULT_BRANCH_CODE);
			FeeRequest objRequest = new FeeRequest();
			objRequest.setAmount(amount);
			objRequest.setBankType(bankType);
			objRequest.setBranchCode(branchCode);
			objRequest.setBusinessType(businessType);
			objRequest.setChannel(channel);
			objRequest.setCurrencyCode(currencyCode);
			objRequest.setOfficeCode(officeCode);
			objRequest.setCustomerType(customerType);
			objRequest.setFeeCode(feeCode);
			objRequest.setFromAcc(fromAcc);
			objRequest.setFromOrgBranchCode(fromOrgBranchCode);
			objRequest.setMicNumber(micNumber);
			objRequest.setProductType(productType);
			objRequest.setToAcc(toAcc);
			objRequest.setToBranchCode(toBranchCode);
			objRequest.setToOrgBranchCode(toOrgBranchCode);
			objRequest.setTransID(transID);
			objRequest.setTransType(transType);

			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			FeeRes objResponse = null;
			try {
				objResponse = feePort.calFeeObj(objRequest);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_FEE);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (objResponse != null) {
				retErrorInfo = CachedParam.mappingErrorCode(String.valueOf(Integer.parseInt(objResponse.getResponseCode())), "", retErrorInfo.getErrorType());
				String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "amount", objResponse.getAmount()));
				outMessage.evaluateXPath(String.format(xpathOutput, "currencyCode", objResponse.getCurrencyCode()));
				outMessage.evaluateXPath(String.format(xpathOutput, "description", objResponse.getDescription()));
				outMessage.evaluateXPath(String.format(xpathOutput, "feeAmount", objResponse.getFeeAmount()));
				outMessage.evaluateXPath(String.format(xpathOutput, "feeCode", objResponse.getFeeCode()));
				outMessage.evaluateXPath(String.format(xpathOutput, "responseCode", objResponse.getResponseCode()));

			}
			this.hostMessageLogInfo.setRef_cif_acct("");
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg(objRequest.toString());
			this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? "" : objResponse.toString());
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".calFeeObj()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

}
