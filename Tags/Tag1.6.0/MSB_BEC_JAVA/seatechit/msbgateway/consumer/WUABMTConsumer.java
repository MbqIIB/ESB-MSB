package seatechit.msbgateway.consumer;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.proxy.wuabmt.InReceiverMoney;
import seatechit.msbgateway.proxy.wuabmt.InReceiverMoneyServiceLocator;
import seatechit.msbgateway.proxy.wuabmt.PaymentDtl;
import seatechit.msbgateway.proxy.wuabmt.PickupReciveMoneyReq;
import seatechit.msbgateway.proxy.wuabmt.PickupReciveMoneyRes;
import seatechit.msbgateway.proxy.wuabmt.PickupValidateReq;
import seatechit.msbgateway.proxy.wuabmt.PickupValidateRes;
import seatechit.msbgateway.proxy.wuabmt.TransactionInquiryRequest;
import seatechit.msbgateway.proxy.wuabmt.TransactionInquiryRespone;
import seatechit.msbgateway.proxy.wuabmt.WuBankAccount;
import seatechit.msbgateway.proxy.wuabmt.WuReceiver;
import seatechit.msbgateway.proxy.wuabmt.WuSender;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbMessage;

/**
 * Process WU ABMT
 * 
 * @author Bui Viet So
 * @version 1.0
 */
public class WUABMTConsumer extends BaseConsumer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1143523007654403527L;
	final Logger logger = Logger.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getName();

	private InReceiverMoneyServiceLocator wuabmtService;
	private InReceiverMoney wuabmtPort;

	public WUABMTConsumer() {
		super();
		try {
			this.consumerType = Global.CONSUMER_CODE_WUABMT;
			ServiceInfo objService = CachedParam.getServiceInfoByServiceType(consumerType);
			this.wuabmtService = new InReceiverMoneyServiceLocator();
			this.wuabmtService.setInReceiverMoneyPortEndpointAddress(objService.getService_url());
			this.wuabmtService.setServiceTimeout(objService.getService_timeout() * 1000);
			this.wuabmtPort = wuabmtService.getInReceiverMoneyPort();
		} catch (ServiceException e) {
		}
	}

	@Override
	public void submit(final MbMessage inMessage, MbMessage outMessage) {
		try {
			this.retErrorInfo.setErrorType(Global.MESSAGE_TYPE_WUABMT);
			this.inMessage = inMessage;
			this.outMessage = outMessage;

			this.baseXmlRequestDoc = ElementUtils.getXMLContent(inMessage);
			this.buildLogOfRequestMessage();

			String tran_code = xmlMessageLogInfo.getTran_code();
			String tran_service_code = xmlMessageLogInfo.getTran_service_code();
			buildTransactionInfo(consumerType, tran_code, tran_service_code);
			this.hostMessageLogInfo.setTran_sn(xmlMessageLogInfo.getSender_tran_sn());

			if (tran_code.equalsIgnoreCase(Global.MSG_WU_TRANSACTION_INQUIRY)) {
				tracactionInquiry(inMessage, outMessage);
			} else if (tran_code.equalsIgnoreCase(Global.MSG_WU_PICKUP_VALIDATE)) {
				pickupValidate(inMessage, outMessage);
			} else if (tran_code.equalsIgnoreCase(Global.MSG_WU_PICKUP_RECEIVE)) {
				pickupReceive(inMessage, outMessage);
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
	private void tracactionInquiry(final MbMessage inMessage, MbMessage outMessage) {
		try {
			//String xpathInput = "/message/body/entity/field[@name='%s']";
			String xpathInput = "/message/body/field[@name='%s']";
			String deviceId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "device_id"));
			String deviceType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "device_type"));
			String firstName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "receiver_first_name"));
			String lastName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "receiver_last_name"));
			String countryCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "country_iso_code"));
			//xpathInput = "/message/body/field[@name='%s']";
			String mtcn = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "mtcn"));
			String serviceCode = "WURECIVE";
			String channel = "IB";
			String transactionDate = AppUtils.now("yyyyMMddHH24MMss");
			
			if (mtcn.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING");
				return;

			}
			if (firstName.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (lastName.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_SERVICE_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			rebuildTransactionInfo(Global.SIBS_034, Global.APP_DEFAULT_BRANCH_CODE);
			TransactionInquiryRequest objRequest = new TransactionInquiryRequest();			
			
			objRequest.setChannel(channel);
			objRequest.setCountryCode(countryCode);
			objRequest.setFirstName(firstName);
			objRequest.setLastName(lastName);
			objRequest.setMstcn(mtcn);
			objRequest.setServiceCode(serviceCode);
			objRequest.setTransactionDate(transactionDate);
			objRequest.setTransactionID(this.hostMessageLogInfo.getHost_tran_sn());

			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			TransactionInquiryRespone objResponse = null;
			try {
				objResponse = wuabmtPort.transationInquiry(objRequest);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_WU_EXCEPTION_WHEN_CALL_GW);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (objResponse != null && objResponse.getErrCode() != null) {
				retErrorInfo = CachedParam.mappingErrorCode(objResponse.getErrCode(), objResponse.getDescription(), retErrorInfo.getErrorType());
				String xpathOutput = "/message/?body/?entity[?@name[set-value('device')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
				//String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				String transactionStatus = objResponse.getErrCode();
				outMessage.evaluateXPath(String.format(xpathOutput, "device_id", deviceId));
				outMessage.evaluateXPath(String.format(xpathOutput, "device_type", deviceType));
				xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "transaction_id", objResponse.getTransactionID()));
				outMessage.evaluateXPath(String.format(xpathOutput, "transfer_date", transactionDate));
				outMessage.evaluateXPath(String.format(xpathOutput, "mtcn", objResponse.getMstcn()));
				outMessage.evaluateXPath(String.format(xpathOutput, "transaction_status", transactionStatus));
				outMessage.evaluateXPath(String.format(xpathOutput, "transaction_message", objResponse.getDescription()));
				this.retErrorInfo.setErrorCode(Global.ERR_WU_CONNECT_SUCCESS);
				this.retErrorInfo.setErrorMessage("Transaction Inquiry Successful");
			}
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			//this.hostMessageLogInfo.setHostRequestMsg(objRequest.toString());
			//this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? "" : objResponse.toString());
			this.hostMessageLogInfo.setHostRequestMsg(getTransactionInquiryLog(objRequest, null));
			this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? "" : getTransactionInquiryLog(null, objResponse));
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
	 * @param inMessage
	 * @param outMessage
	 */
	private void pickupValidate(final MbMessage inMessage, MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String xpathInput1 = "/message/body/field[@name='%s']";
			String xpathInput2 = "/message/body/field[@name='%s']";
			//String xpathInput1 = "/message/body/entity/field[@name='%s']";
			//String xpathInput2 = "/message/body/entity/entity/field[@name='%s']";
			WuSender sender = new WuSender();
			WuReceiver receiver = new WuReceiver();
			WuBankAccount bankAccount = new WuBankAccount();
			PaymentDtl paymentDetail = new PaymentDtl();
			PickupValidateReq objRequest = new PickupValidateReq();
			
			//Payment Detail
			String desCurrencyCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_destination_currency"));
			String desCountryCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_destination_country"));
			paymentDetail.setDesCountryCode(desCountryCode);
			paymentDetail.setDesCurrencyCode(desCurrencyCode);
			objRequest.setPaymentDtl(paymentDetail);
			//Sender
			String senderFirstName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_first_name"));
			String senderLastName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_last_name"));
			String senderCountryIsoCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_country_iso_code"));			
			sender.setSendFirstName(senderFirstName);
			sender.setSendLastName(senderLastName);
			sender.setSendCountryIsoCode(senderCountryIsoCode);			
			objRequest.setSender(sender);
			//Receiver
			String receiverFirstName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "receiver_first_name"));
			String receiverLastName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "receiver_last_name"));
			receiver.setReceiveFirstName(receiverFirstName);
			receiver.setReceiveLastName(receiverLastName);

			String receiverIdType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_type"));
			String receiverIdData = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_data"));
			String receiverIdIssuePlace = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_issue_place"));
			String receiverIdIssueDate = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_issue_date"));
			String receiverIdExpirationDate = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_expiration_date"));
			receiver.setReceiveIDType(receiverIdType);
			receiver.setIdentity(receiverIdData);
			receiver.setIdPlace(receiverIdIssuePlace);
			receiver.setIdDate(receiverIdIssueDate);
			receiver.setExpiradate(receiverIdExpirationDate);
			
			//String receiverCpcCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_cpc_code"));
			String receiverCountryCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_country_code"));
			//String receiverCurrencyCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_currency_code"));
			//receiver.setCpcCountry(receiverCpcCode);
			receiver.setCountryCode(receiverCountryCode);
			//receiver.setCpcCurency(receiverCurrencyCode);
			String dateOfBirth = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_date_of_birth"));
			receiver.setDateOfBirth(dateOfBirth);
			
			String receiverAddrLine1 = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_line1"));
			String receiverAddrLine2 = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_line2"));
			String receiverAddrCity = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_city"));
			String receiverAddrPostalCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_postal_code"));
			String receiverAddrCountry = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_country"));
			//String receiverAddrStreet = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_street"));
			receiver.setAddress1(receiverAddrLine1);
			receiver.setAddress2(receiverAddrLine2);
			receiver.setCity(receiverAddrCity);
			receiver.setPostalCode(receiverAddrPostalCode);
			receiver.setAdressCountry(receiverAddrCountry);
			//receiver.setStreet(receiverAddrStreet);
			
			String receiverContactPhone = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "receiver_contact_phone"));
			String receiverNationality = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "receiver_nationality"));
			receiver.setContactPhone(receiverContactPhone);
			receiver.setNational(receiverNationality);
			objRequest.setReceiver(receiver);
			//Bank account
			String receiverCifNumber = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_cif_number"));
			String receiverAccountName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_account_name"));
			String receiverAccountNumber = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_account_number"));
			String receiverAccountType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_account_type"));
			String pinVerificationTime = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "pin_verification_time"));
			bankAccount.setCifNum(receiverCifNumber);
			bankAccount.setAccountName(receiverAccountName);
			bankAccount.setAccountNumber(receiverAccountNumber);
			bankAccount.setAccountType(receiverAccountType);
			bankAccount.setPinVerificationTime(pinVerificationTime);
			bankAccount.setRoutingNumber(receiverAccountNumber);
			objRequest.setBankAcc(bankAccount);
			
			String deviceId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "device_id"));
			String deviceType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "device_type"));
			String mtcn = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "mtcn"));
			String externalReferenceNo = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "external_reference_no"));			
			String serviceCode = "WURECIVE";
			String channel = "IB";
			String transactionDate = AppUtils.now("yyyyMMddHH24MMss");
			
			if (mtcn.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING");
				return;

			}
			rebuildTransactionInfo(Global.SIBS_035, Global.APP_DEFAULT_BRANCH_CODE);			
		
			objRequest.setMtcn(mtcn);
			objRequest.setTransactionID(this.hostMessageLogInfo.getHost_tran_sn());
			objRequest.setRefNo(externalReferenceNo);
			objRequest.setTransactionDate(transactionDate);			
			objRequest.setChannel(channel);
			objRequest.setServiceCode(serviceCode);

			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			PickupValidateRes objResponse = null;
			try {
				objResponse = wuabmtPort.pickupValidate(objRequest);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_WU_EXCEPTION_WHEN_CALL_GW);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			boolean isRtnNull = false;
			if (objResponse != null) {
				retErrorInfo = CachedParam.mappingErrorCode(objResponse.getErrCode(), objResponse.getDescription(), retErrorInfo.getErrorType());
				//String xpathOutput = "";
				String xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				//xpathOutput = "/message/?body/?entity[?@name[set-value('device')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "device_id", deviceId));
				outMessage.evaluateXPath(String.format(xpathOutput, "device_type", deviceType));
				//xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "external_reference_no", externalReferenceNo));
				if (objResponse.getSender() != null) {
					//xpathOutput = "/message/?body/?entity[?@name[set-value('sender')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_first_name", (objResponse.getSender()).getSendFirstName()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_last_name", (objResponse.getSender()).getSendLastName()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_line1", (objResponse.getSender()).getSendAddress()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_city", (objResponse.getSender()).getSendCity()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_state", (objResponse.getSender()).getSendState()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_postal_code", (objResponse.getSender()).getSendPostalCose()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_country_iso_code", (objResponse.getSender()).getSendCountryIsoCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_contact_phone", (objResponse.getSender()).getContactPhone()));
				} else {
					isRtnNull = true;
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_first_name", senderFirstName));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_last_name", senderLastName));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_line1", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_city", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_state", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_postal_code", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_country_iso_code", senderCountryIsoCode));
				}
				if (objResponse.getReceiver() != null) {
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_first_name", (objResponse.getReceiver()).getReceiveFirstName()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_last_name", (objResponse.getReceiver()).getReceiveLastName()));
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('id_documents')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_type", (objResponse.getReceiver()).getReceiveIDType()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_data", (objResponse.getReceiver()).getIdentity()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_issue_date", (objResponse.getReceiver()).getIdDate()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_issue_place", (objResponse.getReceiver()).getIdPlace()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_expiration_date", (objResponse.getReceiver()).getExpiradate()));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_cpc_code", (objResponse.getReceiver()).getCpcCountry()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_country_code", (objResponse.getReceiver()).getCountryCode()));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_currency_code", (objResponse.getReceiver()).getCpcCurency()));
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('current_address')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_line1", (objResponse.getReceiver()).getAddress1()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_line2", (objResponse.getReceiver()).getAddress2()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_city", (objResponse.getReceiver()).getCity()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_postal_code", (objResponse.getReceiver()).getPostalCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_country", (objResponse.getReceiver()).getAdressCountry()));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_street", (objResponse.getReceiver()).getStreet()));
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_contact_phone", (objResponse.getReceiver()).getContactPhone()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_nationality", (objResponse.getReceiver()).getNational()));
				} else {
					isRtnNull = true;
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_first_name", receiverFirstName));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_last_name", receiverLastName));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('id_documents')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_type", receiverIdType));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_data", receiverIdData));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_issue_date", receiverIdIssueDate));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_issue_place", receiverIdIssuePlace));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_expiration_date", receiverIdExpirationDate));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_cpc_code", receiverCpcCode));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_country_code", receiverCountryCode));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_currency_code", receiverCurrencyCode));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('current_address')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_line1", receiverAddrLine1));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_line2", receiverAddrLine2));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_city", receiverAddrCity));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_postal_code", receiverAddrPostalCode));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_country", receiverAddrCountry));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_street", receiverAddrStreet));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_contact_phone", receiverContactPhone));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_nationality", receiverNationality));
				}
				if (objResponse.getBankAcc() != null) {
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('bank_account')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_account_number", objResponse.getBankAcc().getAccountNumber()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_account_type", objResponse.getBankAcc().getAccountType()));
					outMessage.evaluateXPath(String.format(xpathOutput, "pin_verification_time", objResponse.getBankAcc().getPinVerificationTime()));
				} else {
					isRtnNull = true;
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('bank_account')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_account_number", receiverAccountNumber));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_account_type", receiverAccountType));
					outMessage.evaluateXPath(String.format(xpathOutput, "pin_verification_time", pinVerificationTime));
				}
				if (objResponse.getPaymentDtl() != null) {
					//xpathOutput = "/message/?body/?entity[?@name[set-value('payment_detail')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_principal_amount", objResponse.getPaymentDtl().getOrigineAmount()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_gross_amount", objResponse.getPaymentDtl().getOrigineGrossAmount()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_origination_currency", objResponse.getPaymentDtl().getOrgCurrencyCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_origination_country", objResponse.getPaymentDtl().getOrgCountryCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_expected_amount", objResponse.getPaymentDtl().getDesPayoutAmount()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_destination_currency", objResponse.getPaymentDtl().getDesCurrencyCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_destination_country", objResponse.getPaymentDtl().getDesCountryCode()));
					
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_exchange_rate", objResponse.getPaymentDtl().getExchange_rate()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_fee_charges", objResponse.getPaymentDtl().getFee_charges()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_municipal_tax", objResponse.getPaymentDtl().getMunicipal_tax()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_state_tax", objResponse.getPaymentDtl().getState_tax()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_county_tax", objResponse.getPaymentDtl().getCounty_tax()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion", objResponse.getPaymentDtl().getPromotion()));					
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion_name", objResponse.getPaymentDtl().getPromotionName()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion_code", objResponse.getPaymentDtl().getPromotionCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion_description", objResponse.getPaymentDtl().getPromotionDescription()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion_message", objResponse.getPaymentDtl().getPromotionMesssage()));
				}  else {
					isRtnNull = true;
					//xpathOutput = "/message/?body/?entity[?@name[set-value('payment_detail')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_principal_amount", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_gross_amount", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_origination_currency", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_origination_country", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_expected_amount", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_destination_currency", desCurrencyCode));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_destination_country", desCountryCode));
					
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_exchange_rate", "0.0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_fee_charges", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_municipal_tax", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_state_tax", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_county_tax", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion_name", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion_code", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion_description", ""));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_promotion_message", ""));
				}
				outMessage.evaluateXPath(String.format(xpathOutput, "transaction_message_text", objResponse.getMessage_text()));
				outMessage.evaluateXPath(String.format(xpathOutput, "transaction_message_context", objResponse.getMessage_context()));
				//xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "transaction_id", objResponse.getMoneyTransactionID()));
				outMessage.evaluateXPath(String.format(xpathOutput, "money_transfer_date", objResponse.getMoneyTransferDate()));
				outMessage.evaluateXPath(String.format(xpathOutput, "mtcn", objResponse.getMtcn()));
				outMessage.evaluateXPath(String.format(xpathOutput, "pickup_reference_number", objResponse.getPickupReferenceNumber()));
				outMessage.evaluateXPath(String.format(xpathOutput, "transaction_digest", objResponse.getTransactionDigest()));
				//xpathOutput = "/message/?body/?entity[?@name[set-value('status')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "status_code", objResponse.getErrCode()));
				outMessage.evaluateXPath(String.format(xpathOutput, "status_message", objResponse.getDescription()));
				outMessage.evaluateXPath(String.format(xpathOutput, "status_reference_no", objResponse.getReferenceNo()));
				if (isRtnNull) {
					this.retErrorInfo.setErrorCode(Global.ERR_WU_ONE_OF_RSP_NULL);
					this.retErrorInfo.setErrorMessage("Pickup Validate missing response Data");
				} else {
					this.retErrorInfo.setErrorCode(Global.ERR_WU_CONNECT_SUCCESS);
					this.retErrorInfo.setErrorMessage("Pickup Validate Successful");
				}
			}
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			//this.hostMessageLogInfo.setHostRequestMsg(objRequest.toString());
			//this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? "" : objResponse.toString());
			this.hostMessageLogInfo.setHostRequestMsg(getPickupValidateLog(objRequest, null));
			this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? "" : getPickupValidateLog(null, objResponse));
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
	 * @param inMessage
	 * @param outMessage
	 */
	private void pickupReceive(final MbMessage inMessage, MbMessage outMessage) {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String xpathInput1 = "/message/body/field[@name='%s']";
			String xpathInput2 = "/message/body/field[@name='%s']";
			//String xpathInput1 = "/message/body/entity/field[@name='%s']";
			//String xpathInput2 = "/message/body/entity/entity/field[@name='%s']";
			WuSender sender = new WuSender();
			WuReceiver receiver = new WuReceiver();
			WuBankAccount bankAccount = new WuBankAccount();
			PaymentDtl paymentDetail = new PaymentDtl();
			PickupReciveMoneyReq objRequest = new PickupReciveMoneyReq();
			
			//Payment Detail
			
			String pDesCurrencyCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_destination_currency"));
			String pDesCountryCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_destination_country"));
			String pPrincipalAmount = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_principal_amount"));
			String pGrossAmount = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_gross_amount"));
			String pOriginationCurrency = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_origination_currency"));
			String pOriginationCountry = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_origination_country"));
			String pExpectedAmount = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_expected_amount"));
			paymentDetail.setDesCountryCode(pDesCountryCode);
			paymentDetail.setDesCurrencyCode(pDesCurrencyCode);
			paymentDetail.setDesPayoutAmount(Long.parseLong(pExpectedAmount));
			paymentDetail.setOrgCountryCode(pOriginationCountry);
			paymentDetail.setOrgCurrencyCode(pOriginationCurrency);
			paymentDetail.setOrigineAmount(Long.parseLong(pPrincipalAmount));
			paymentDetail.setOrigineGrossAmount(Long.parseLong(pGrossAmount));
			
			String pExchangeRate = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_exchange_rate"));
			String pFee = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_fee_charges"));
			String pMunicipalTax = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_municipal_tax"));
			String pStateTax = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_state_tax"));
			String pCountyTax = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_county_tax"));
			String pPromotion = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_promotion"));
			String pPromotionName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_promotion_name"));
			String pPromotionCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_promotion_code"));
			String pPromotionDescription = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_promotion_description"));
			String pPromotionMessage = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "payment_promotion_message"));
			paymentDetail.setExchange_rate(Double.parseDouble(pExchangeRate));
			paymentDetail.setFee_charges(Long.parseLong(pFee));
			paymentDetail.setMunicipal_tax(Long.parseLong(pMunicipalTax));
			paymentDetail.setState_tax(Long.parseLong(pStateTax));
			paymentDetail.setCounty_tax(Long.parseLong(pCountyTax));
			paymentDetail.setPromotion(Long.parseLong(pPromotion));
			paymentDetail.setPromotionName(pPromotionName);
			paymentDetail.setPromotionCode(pPromotionCode);
			paymentDetail.setPromotionDescription(pPromotionDescription);
			paymentDetail.setPromotionMesssage(pPromotionMessage);
			objRequest.setPaymentDtl(paymentDetail);
			//Sender
			String senderFirstName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_first_name"));
			String senderLastName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_last_name"));
			String senderCountryIsoCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_country_iso_code"));
			String senderAddrLine1 = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_addr_line1"));
			String senderAddrCity = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_addr_city"));
			String senderAddrState  = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_addr_state"));
			String senderAddrPostalCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_addr_postal_code"));
			sender.setSendFirstName(senderFirstName);
			sender.setSendLastName(senderLastName);
			sender.setSendCountryIsoCode(senderCountryIsoCode);
			sender.setSendAddress(senderAddrLine1);
			sender.setSendCity(senderAddrCity);
			sender.setSendState(senderAddrState);
			sender.setSendPostalCose(senderAddrPostalCode);
			objRequest.setSender(sender);
			String senderMessage = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "sender_message"));
			objRequest.setComment(senderMessage);
			//Receiver
			String receiverFirstName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "receiver_first_name"));
			String receiverLastName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "receiver_last_name"));
			receiver.setReceiveFirstName(receiverFirstName);
			receiver.setReceiveLastName(receiverLastName);

			String receiverIdType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_type"));
			String receiverIdData = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_data"));
			String receiverIdIssuePlace = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_issue_place"));
			String receiverIdIssueDate = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_issue_date"));
			String receiverIdExpirationDate = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_id_expiration_date"));
			receiver.setReceiveIDType(receiverIdType);
			receiver.setIdentity(receiverIdData);
			receiver.setIdPlace(receiverIdIssuePlace);
			receiver.setIdDate(receiverIdIssueDate);
			receiver.setExpiradate(receiverIdExpirationDate);
			
			//String receiverCpcCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_cpc_code"));
			String receiverCountryCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_country_code"));
			//String receiverCurrencyCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_currency_code"));
			//receiver.setCpcCountry(receiverCpcCode);
			receiver.setCountryCode(receiverCountryCode);
			//receiver.setCpcCurency(receiverCurrencyCode);
			String dateOfBirth = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_date_of_birth"));
			receiver.setDateOfBirth(dateOfBirth);
			
			String receiverAddrLine1 = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_line1"));
			String receiverAddrLine2 = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_line2"));
			String receiverAddrCity = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_city"));
			String receiverAddrPostalCode = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_postal_code"));
			String receiverAddrCountry = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_country"));
			//String receiverAddrStreet = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_addr_street"));
			receiver.setAddress1(receiverAddrLine1);
			receiver.setAddress2(receiverAddrLine2);
			receiver.setCity(receiverAddrCity);
			receiver.setPostalCode(receiverAddrPostalCode);
			receiver.setAdressCountry(receiverAddrCountry);
			//receiver.setStreet(receiverAddrStreet);
			
			String receiverContactPhone = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "receiver_contact_phone"));
			String receiverNationality = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "receiver_nationality"));
			receiver.setContactPhone(receiverContactPhone);
			receiver.setNational(receiverNationality);
			objRequest.setReceiver(receiver);
			//Bank account
			String receiverCifNumber = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_cif_number"));
			String receiverAccountName = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_account_name"));
			String receiverAccountNumber = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_account_number"));
			String receiverAccountType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "receiver_account_type"));
			String pinVerificationTime = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput2, "pin_verification_time"));
			bankAccount.setCifNum(receiverCifNumber);
			bankAccount.setAccountName(receiverAccountName);
			bankAccount.setAccountNumber(receiverAccountNumber);
			bankAccount.setAccountType(receiverAccountType);
			bankAccount.setPinVerificationTime(pinVerificationTime);
			bankAccount.setRoutingNumber(receiverAccountNumber);
			objRequest.setBankAcc(bankAccount);
			
			String deviceId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "device_id"));
			String deviceType = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput1, "device_type"));
			String mtcn = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "mtcn"));
			String externalReferenceNo = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "external_reference_no"));			
			String moneyTransferDate = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "money_transfer_date"));
			String transactionDigest = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "transaction_digest"));
			String moneyTransactionId = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "transaction_id"));
			String pickupReferenceNumber = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xpathInput, "pickup_reference_number"));
			String serviceCode = "WURECIVE";
			String channel = "IB";
			String transactionDate = AppUtils.now("yyyyMMddHH24MMss");
			
			if (mtcn.length() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo.setErrorMessage("ERR_UDF_CUSTOMER_CODE_NOT_VALID_OR_MISSING");
				return;

			}
			rebuildTransactionInfo(Global.SIBS_035, Global.APP_DEFAULT_BRANCH_CODE);			

			objRequest.setMtcn(mtcn);
			objRequest.setTransactionID(this.hostMessageLogInfo.getHost_tran_sn());
			objRequest.setReferenceNo(externalReferenceNo);
			objRequest.setTransactionDate(transactionDate);			
			objRequest.setChannel(channel);
			objRequest.setServiceCode(serviceCode);
			objRequest.setMoneyTransferDate(moneyTransferDate);
			objRequest.setTransactionDigest(transactionDigest);
			objRequest.setMoneyTransactionId(moneyTransactionId);
			objRequest.setPickupReferenceNumber(pickupReferenceNumber);

			this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			PickupReciveMoneyRes objResponse = null;
			try {
				objResponse = wuabmtPort.pickupReciveMoney(objRequest);
			} catch (RemoteException re) {
				retErrorInfo.setErrorCode(ERR_WU_EXCEPTION_WHEN_CALL_GW);
				retErrorInfo.setErrorMessage(re.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			String xpathOutput = xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
			boolean isRtnNull = false;
			//if (objResponse != null && !("0".equals(objResponse.getErrCode()))) {
			if (objResponse != null) {
				retErrorInfo = CachedParam.mappingErrorCode(objResponse.getErrCode(), objResponse.getDescription(), retErrorInfo.getErrorType());
				//xpathOutput = "/message/?body/?entity[?@name[set-value('device')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "device_id", deviceId));
				outMessage.evaluateXPath(String.format(xpathOutput, "device_type", deviceType));
				
				//xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "external_reference_no", objResponse.getReferenceNo()));
				
				//xpathOutput = "/message/?body/?entity[?@name[set-value('sender')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
				if (objResponse.getSender() != null) {
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_first_name", (objResponse.getSender()).getSendFirstName()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_last_name", (objResponse.getSender()).getSendLastName()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_line1", (objResponse.getSender()).getSendAddress()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_city", (objResponse.getSender()).getSendCity()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_state", (objResponse.getSender()).getSendState()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_postal_code", (objResponse.getSender()).getSendPostalCose()));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_country_iso_code", (objResponse.getSender()).getSendCountryIsoCode()));
				} else {
					isRtnNull = true;
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_first_name", senderFirstName));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_last_name", senderLastName));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_line1", senderAddrLine1));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_city", senderAddrCity));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_state", senderAddrState));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_addr_postal_code", senderAddrPostalCode));
					outMessage.evaluateXPath(String.format(xpathOutput, "sender_country_iso_code", senderCountryIsoCode));
				}
				if (objResponse.getReceiver() != null) {
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_first_name", (objResponse.getReceiver()).getReceiveFirstName()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_last_name", (objResponse.getReceiver()).getReceiveLastName()));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('id_documents')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_type", (objResponse.getReceiver()).getReceiveIDType()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_data", (objResponse.getReceiver()).getIdentity()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_issue_date", (objResponse.getReceiver()).getIdDate()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_issue_place", (objResponse.getReceiver()).getIdPlace()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_expiration_date", (objResponse.getReceiver()).getExpiradate()));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_cpc_code", (objResponse.getReceiver()).getCpcCountry()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_country_code", (objResponse.getReceiver()).getCountryCode()));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_currency_code", (objResponse.getReceiver()).getCpcCurency()));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('current_address')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_line1", (objResponse.getReceiver()).getAddress1()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_line2", (objResponse.getReceiver()).getAddress2()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_city", (objResponse.getReceiver()).getCity()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_postal_code", (objResponse.getReceiver()).getPostalCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_country", (objResponse.getReceiver()).getAdressCountry()));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_street", (objResponse.getReceiver()).getStreet()));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_contact_phone", (objResponse.getReceiver()).getContactPhone()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_nationality", (objResponse.getReceiver()).getNational()));				
				} else {
					isRtnNull = true;
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_first_name", receiverFirstName));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_last_name", receiverLastName));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('id_documents')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_type", receiverIdType));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_data", receiverIdData));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_issue_date", receiverIdIssueDate));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_issue_place", receiverIdIssuePlace));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_id_expiration_date", receiverIdExpirationDate));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_cpc_code", receiverCpcCode));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_country_code", receiverCountryCode));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_currency_code", receiverCurrencyCode));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('current_address')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_line1", receiverAddrLine1));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_line2", receiverAddrLine2));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_city", receiverAddrCity));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_postal_code", receiverAddrPostalCode));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_country", receiverAddrCountry));
					//outMessage.evaluateXPath(String.format(xpathOutput, "receiver_addr_street", receiverAddrStreet));
					
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_contact_phone", receiverContactPhone));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_nationality", receiverNationality));
				}
				if (objResponse.getBankAcc() != null) {
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('bank_account')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_account_number", objResponse.getBankAcc().getAccountNumber()));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_account_type", objResponse.getBankAcc().getAccountType()));
					outMessage.evaluateXPath(String.format(xpathOutput, "pin_verification_time", objResponse.getBankAcc().getPinVerificationTime()));
				} else {
					isRtnNull = true;
					//xpathOutput = "/message/?body/?entity[?@name[set-value('receiver')]]/?entity[?@name[set-value('bank_account')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_account_number", receiverAccountNumber));
					outMessage.evaluateXPath(String.format(xpathOutput, "receiver_account_type", receiverAccountType));
					outMessage.evaluateXPath(String.format(xpathOutput, "pin_verification_time", pinVerificationTime));
				}
				if (objResponse.getPaymentDtl() != null) {
					//xpathOutput = "/message/?body/?entity[?@name[set-value('payment_detail')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_principal_amount", objResponse.getPaymentDtl().getOrigineAmount()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_gross_amount", objResponse.getPaymentDtl().getOrigineGrossAmount()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_origination_currency", objResponse.getPaymentDtl().getOrgCurrencyCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_origination_country", objResponse.getPaymentDtl().getOrgCountryCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_expected_amount", objResponse.getPaymentDtl().getDesPayoutAmount()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_actual_amount", objResponse.getPaymentDtl().getDesPayoutAmount()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_destination_currency", objResponse.getPaymentDtl().getDesCurrencyCode()));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_destination_country", objResponse.getPaymentDtl().getDesCountryCode()));
				} else {
					isRtnNull = true;
					//xpathOutput = "/message/?body/?entity[?@name[set-value('payment_detail')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_principal_amount", pPrincipalAmount));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_gross_amount", pGrossAmount));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_origination_currency", pOriginationCurrency));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_origination_country", pOriginationCountry));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_expected_amount", pExpectedAmount));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_actual_amount", "0"));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_destination_currency", pDesCurrencyCode));
					outMessage.evaluateXPath(String.format(xpathOutput, "payment_destination_country", pDesCountryCode));
				}
				//xpathOutput = "/message/?body/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "transaction_id", objResponse.getTransactionID()));
				outMessage.evaluateXPath(String.format(xpathOutput, "money_transfer_date", objResponse.getTransactionDate()));
				outMessage.evaluateXPath(String.format(xpathOutput, "mtcn", objResponse.getMtcn()));
				
				//xpathOutput = "/message/?body/?entity[?@name[set-value('status')]]/?$field[?@name[set-value('%s')]][set-value('%s')]";
				outMessage.evaluateXPath(String.format(xpathOutput, "status_code", objResponse.getErrCode()));
				outMessage.evaluateXPath(String.format(xpathOutput, "status_message", objResponse.getDescription()));
				outMessage.evaluateXPath(String.format(xpathOutput, "status_reference_no", objResponse.getReferenceNo()));
				if (isRtnNull) {
					this.retErrorInfo.setErrorCode(Global.ERR_WU_ONE_OF_RSP_NULL);
					this.retErrorInfo.setErrorMessage("Pickup Receive missing response Data");
				} else {
					this.retErrorInfo.setErrorCode(Global.ERR_WU_CONNECT_SUCCESS);
					this.retErrorInfo.setErrorMessage("Pickup Receive Successful");
				}
			}
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			//this.hostMessageLogInfo.setHostRequestMsg(objRequest.toString());
			//this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? "" : objResponse.toString());
			this.hostMessageLogInfo.setHostRequestMsg(getPickupReceiveLog(objRequest, null));
			this.hostMessageLogInfo.setHostResponseMsg(objResponse == null ? "" : getPickupReceiveLog(null, objResponse));
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".billingInquiry()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());
		}
	}
	private String getTransactionInquiryLog(TransactionInquiryRequest req, TransactionInquiryRespone rsp) {
		String log = "";
		if (req != null) {
			log += "MTCN: " + req.getMstcn();
			log += ",Receiver: " +req.getLastName() + "/" + req.getFirstName();
		} else if (rsp != null){
			log += "Status: " +rsp.getErrCode();
			log += ",Description: " + rsp.getDescription();
		}
		return log;
	}
	private String getPickupValidateLog(PickupValidateReq req, PickupValidateRes rsp) {
		String log = "";
		if (req != null) {
			log += "MTCN: " + req.getMtcn();
			log += ",Receiver: " + req.getReceiver().getReceiveLastName() + "/" + req.getReceiver().getReceiveFirstName();
			log += ",Sender: " + req.getSender().getSendLastName() + "/" + req.getSender().getSendFirstName();
			log += ",Expected: " + req.getPaymentDtl().getDesPayoutAmount() + req.getPaymentDtl().getDesCurrencyCode();
			log += ",Account: " + req.getBankAcc().getAccountNumber();
		} else if (rsp != null){
			log += "Status: " + rsp.getErrCode();
			log += ",Description: " + rsp.getDescription();
			if (rsp.getErrCode().equals("0")) {
				log += ",PickupReferenceNumber: " + rsp.getPickupReferenceNumber();
				log += ",TransactionDigest: " + rsp.getTransactionDigest();
				log += ",ReferenceNo: " + rsp.getTransactionID();
			}
		}
		return log;
	}
	private String getPickupReceiveLog(PickupReciveMoneyReq req, PickupReciveMoneyRes rsp) {
		String log = "";
		if (req != null) {
			log += "MTCN: " + req.getMtcn();
			log += ",Receiver: " + req.getReceiver().getReceiveLastName() + "/" + req.getReceiver().getReceiveFirstName();
			log += ",Sender: " + req.getSender().getSendLastName() + "/" + req.getSender().getSendFirstName();
			log += ",Expected: " + req.getPaymentDtl().getDesPayoutAmount() + req.getPaymentDtl().getDesCurrencyCode();
			log += ",Account: " + req.getBankAcc().getAccountNumber();
			log += ",PickupReferenceNumber: " + req.getPickupReferenceNumber();
			log += ",TransactionDigest: " + req.getTransactionDigest();
			log += ",ReferenceNo: " + req.getTransactionID();
		} else if (rsp != null){
			log += "Status: " +rsp.getErrCode();
			log += ",Description: " + rsp.getDescription();
			if (rsp.getErrCode().equals("0")) {
				log += ",ActualAmount: " + req.getPaymentDtl().getDesPayoutActualAmount() + req.getPaymentDtl().getDesCurrencyCode();
				log += ",OrigineAmount: " + req.getPaymentDtl().getOrigineAmount() + req.getPaymentDtl().getOrgCurrencyCode();
			}
		}
		return log;
	}
}
