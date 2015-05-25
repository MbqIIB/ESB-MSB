package seatechit.msbgateway.consumer;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.proxy.pmtgw.InternalEGW;
import seatechit.msbgateway.proxy.pmtgw.InternalEGWServiceLocator;
import seatechit.msbgateway.proxy.pmtgw.PayBillingReq;
import seatechit.msbgateway.proxy.pmtgw.PayBillingResponseReturn;
import seatechit.msbgateway.proxy.pmtgw.QueryBillingReq;
import seatechit.msbgateway.proxy.pmtgw.QueryBillingResponseReturn;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.CoreBankUtils;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbMessage;

/**
 * Process billing inquiry/payment message
 * 
 * @author trungkien
 * @version 1.0
 */
public class SMLConsumer extends BaseConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1835655754886130241L;
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	private InternalEGWServiceLocator billingService;
	private InternalEGW billingPort;

	public SMLConsumer() {
		super();
		try {
			this.consumerType = Global.CONSUMER_CODE_SMLINK;
			ServiceInfo objService = CachedParam.getServiceInfoByServiceType(consumerType);
			this.billingService = new InternalEGWServiceLocator();
			this.billingService.setInternalEGWPortEndpointAddress(objService.getService_url());
			this.billingService.setServiceTimeout(objService.getService_timeout() * 1000);
			this.billingPort = billingService.getInternalEGWPort();
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
			this.hostMessageLogInfo.setTran_sn(xmlMessageLogInfo.getSender_tran_sn());

			if (tran_code.equalsIgnoreCase(Global.MSG_MP_INQUIRY_BILLPAYMENT_INFOR)) {
				billingInquiry(inMessage, outMessage);
			} else if (tran_code.equalsIgnoreCase(Global.MSG_MP_CREATE_BILLPAYMENT_TRANSACTION)) {
				billingPayment(inMessage, outMessage);
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
	private void billingInquiry(final MbMessage inMessage, MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String service_code = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "service_code"));
			String customer_code = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "customer_code"));
			String service_name = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "service_name"));
			String service_type = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "service_type"));
			String mobile = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "mobile"));
			String contract_no = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "contract_no"));

			if (customer_code.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING");
				return;

			}
			if (service_code.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			rebuildTransactionInfo(Global.SIBS_034, Global.APP_DEFAULT_BRANCH_CODE);
			String send_time = AppUtils.now("yyMMddHHmmss");
			QueryBillingReq objRequest = new QueryBillingReq();
			objRequest.setMsgType(MSG_TYPE_BILLING_INQUIRY);
			objRequest.setSequenceId(this.hostMessageLogInfo.getHost_tran_sn());
			objRequest.setRequestTime(send_time);
			objRequest.setSender(xmlMessageLogInfo.getSender_id());// Global.APP_SENDER);
			objRequest.setCustomerCode(customer_code);
			objRequest.setPayProviderCode(service_code);
			objRequest.setProviderCode("");
			objRequest.setDescription("");
			objRequest.setProcessingCode("");

			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			QueryBillingResponseReturn objResponse = null;
			try {
				objResponse = billingPort.queryBilling(objRequest);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_PAYMENTGW);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (objResponse != null) {
				retErrorInfo = CachedParam.mappingErrorCode(String.valueOf(Integer.parseInt(objResponse.getResponseCode())), objResponse.getResponseDescription(), retErrorInfo.getErrorType());
				String xpathOutput = "/message/?body/?entity[?@name[set-value('summary')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "service_name", service_name));
				outMessage.evaluateXPath(String.format(xpathOutput, "service_code", service_code));
				outMessage.evaluateXPath(String.format(xpathOutput, "service_type", service_type));
				outMessage.evaluateXPath(String.format(xpathOutput, "mobile", mobile));
				outMessage.evaluateXPath(String.format(xpathOutput, "contract_no", contract_no));
				outMessage.evaluateXPath(String.format(xpathOutput, "customer_code", customer_code));
				outMessage.evaluateXPath(String.format(xpathOutput, "amt", String.valueOf(objResponse.getEnquiryAmount())));
				outMessage.evaluateXPath(String.format(xpathOutput, "bill_term_month", ""));
				outMessage.evaluateXPath(String.format(xpathOutput, "bill_term_year", ""));
				processDescription(outMessage, objResponse.getEnquiryInfo());
			}
			this.hostMessageLogInfo.setRef_cif_acct(customer_code);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg(objRequest.toString());
			this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? "" : objResponse.toString());
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".billingInquiry()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());

		}
	}

	/**
	 *
	 * @param outMessage
	 * @param orgXMLDesc
	 */
	private void processDescription(MbMessage outMessage, final String orgXMLDesc) {
		try {
			if (orgXMLDesc != null && orgXMLDesc.length() > 0) {
				MbMessage mbTempMessage = ElementUtils.createMbMessageFromString(orgXMLDesc);
				if (mbTempMessage != null) {
					String xpath = "/orders";
					List<MbElement> listOfOrder = ElementUtils.getListOfXMLElement(mbTempMessage, xpath);
					// 1.Tao the list
					String xmlInputPattern_2 = "/message/?body/?list[?@name[set-value('records')]]";
					outMessage.evaluateXPath(xmlInputPattern_2);
					for (int i = 0; i < listOfOrder.size(); i++) {
						String xmlInputPattern_1 = "/orders/order-item[@id='" + i + "']";
						// 2.Tao tung the entity
						xmlInputPattern_2 = "/message/body/list[@name='records']/?$entity[?@name[set-value('" + i + "')]]";
						outMessage.evaluateXPath(xmlInputPattern_2);

						// 3.Tao tung the field
						xmlInputPattern_2 = "/message/body/list[@name='records']/entity[@name='" + i + "']/?$field[set-value('%s')][?@name[set-value('%s')]]";
						String bill_no = ElementUtils.getXMLElementInStringValue(mbTempMessage, xmlInputPattern_1.concat("/bill_no"));
						String amount = ElementUtils.getXMLElementInStringValue(mbTempMessage, xmlInputPattern_1.concat("/amount"));
						String description = ElementUtils.getXMLElementInStringValue(mbTempMessage, xmlInputPattern_1.concat("/description"));

						outMessage.evaluateXPath(String.format(xmlInputPattern_2, bill_no, "bill_no"));
						outMessage.evaluateXPath(String.format(xmlInputPattern_2, amount, "amount"));
						outMessage.evaluateXPath(String.format(xmlInputPattern_2, description, "description"));
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param inMessage
	 * @param outMessage
	 */
	private void billingPayment(final MbMessage inMessage, MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String cif_no = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "cif_no"));
			String cif_name = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "cif_name"));
			String cert_code = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "cert_code"));
			String service_code = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "service_code"));
			String rollout_branch_no = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "rollout_branch_no"));
			String rollout_acct_no = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "rollout_acct_no"));
			String rollout_acct_ccy = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "rollout_acct_ccy"));
			String rollout_acct_type = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "rollout_acct_type"));
			String amt = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "amt"));
			String discounted_amt = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "discounted_amt"));
			String remark = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "remark"));
			String paid_bill_code = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "paid_bill_code"));
			String customer_code = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "customer_code"));

			if (cif_no.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_CIF_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_CIF_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (customer_code.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (service_code.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (paid_bill_code.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_PAID_BILL_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_PAID_BILL_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_branch_no.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_acct_no.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_acct_type.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_acct_ccy.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (!CoreBankUtils.isValidTransactionAmount(amt)) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_TRANSACTION_AMOUT_NOT_VALID_OR_MISSING");
				return;
			}
			if (!CoreBankUtils.isValidTransactionAmount(discounted_amt)) {
				discounted_amt = amt;
			}

			if (remark.length() > 0) {
				String[] parseArr = remark.split("\\|");
				if (parseArr.length > 4) {
					cif_name = parseArr[2];
					cert_code = parseArr[3];
				}
			}

			double payedAmt = Double.parseDouble(amt);
			double payedDiscountedAmt = Double.parseDouble(discounted_amt);

			rebuildTransactionInfo(Global.SIBS_035, Global.APP_DEFAULT_BRANCH_CODE);
			String send_time = AppUtils.now("yyMMddHHmmss");
			PayBillingReq objRequest = new PayBillingReq();

			objRequest.setMsgType(MSG_TYPE_BILLING_PAYMENT);
			objRequest.setRequestTime(send_time);
			objRequest.setSender(xmlMessageLogInfo.getSender_id());// Global.APP_SENDER);
			objRequest.setSequenceId(hostMessageLogInfo.getHost_tran_sn());

			objRequest.setCifNumber(cif_no);
			objRequest.setCustomerCode(customer_code);
			objRequest.setProviderCode("");
			objRequest.setProcessingCode("");
			objRequest.setSettlementDate(hostMessageLogInfo.getHost_tran_date());
			objRequest.setFromid(cert_code);
			objRequest.setFromName(cif_name);
			objRequest.setPayProviderCode(service_code);
			objRequest.setAccountNumber(rollout_acct_no);
			objRequest.setBranch(rollout_branch_no);
			objRequest.setCcycd(rollout_acct_ccy);
			objRequest.setBillCode(paid_bill_code);
			objRequest.setAmount(payedAmt);
			objRequest.setAmountDiscounted(payedDiscountedAmt);

			String remarkTemp = "Dich vu thanh toan: TenKH:" + cif_name + ", So tien: " + discounted_amt + ", So HDTT:" + customer_code;
			objRequest.setDescription(remarkTemp);

			// Response
			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			PayBillingResponseReturn objResponse = null;
			try {
				objResponse = billingPort.payBilling(objRequest);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_PAYMENTGW);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			if (objResponse != null) {
				retErrorInfo = CachedParam.mappingErrorCode(String.valueOf(Integer.parseInt(objResponse.getResponseCode())), objResponse.getResponseDescription(), retErrorInfo.getErrorType());
				//this.refTranCode = SIBS_035;
				this.hostMessageLogInfo.setRef_cif_acct(rollout_acct_no);
				this.hostMessageLogInfo.setRef_amount(amt);
				this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
				this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
				this.hostMessageLogInfo.setHostRequestMsg(objRequest.toString());
				this.hostMessageLogInfo.setHostResponseMsg(objResponse.toString());
				this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".billingPayment()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());
		}
	}
}
