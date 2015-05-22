package seatechit.msbgateway.consumer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.AccountInfo;
import seatechit.msbgateway.dbaccess.entity.AftInfo;
import seatechit.msbgateway.dbaccess.entity.CurrencyInfo;
import seatechit.msbgateway.dbaccess.entity.CustomerInfo;
import seatechit.msbgateway.dbaccess.entity.FDInfo;
import seatechit.msbgateway.dbaccess.entity.HOSTMessagLogInfo;
import seatechit.msbgateway.dbaccess.entity.HostTranCodeInfo;
import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.dbaccess.facade.AccountInfoFacade;
import seatechit.msbgateway.dbaccess.facade.AftInfoFacade;
import seatechit.msbgateway.dbaccess.facade.CustomerInfoFacade;
import seatechit.msbgateway.dbaccess.facade.FDInfoFacade;
import seatechit.msbgateway.proxy.as400gw.AS400_PortType;
import seatechit.msbgateway.proxy.as400gw.AS400_ServiceLocator;
import seatechit.msbgateway.proxy.as400gw.Messages;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.CoreBankUtils;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbMessage;

/**
 * Main class process message send/receive to/from Silverlake corebank system
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6
 * 
 */

public class SIBSConsumer extends BaseConsumer {
	private static final long serialVersionUID = 5143523007654403527L;
	final Logger logger = Logger.getLogger(SIBSConsumer.class);
	final String CLASS_NAME = this.getClass().getName();
	private AS400_ServiceLocator as400Service;
	protected AS400_PortType as400Port;
	protected Messages retMessage = null;
	protected String[] ABCS_TEMPLATE;
	protected HostTranCodeInfo hostTranCodeInfo = null;

	protected void initABCSMessageTemplate() {
		ABCS_TEMPLATE = new String[306];
		ABCS_TEMPLATE[0] = "4092";// Socket Message Length
		ABCS_TEMPLATE[1] = "*LINX";// Header Type
		ABCS_TEMPLATE[6] = "213";// Header Length
		ABCS_TEMPLATE[7] = "42";// Message Length
		ABCS_TEMPLATE[8] = "0200";// Version Number
		ABCS_TEMPLATE[10] = "ABCS";// Data Format ID
		ABCS_TEMPLATE[11] = "*LINX";// Source ID
		ABCS_TEMPLATE[31] = "3843";// APPLICATION DATA LENGTH
		ABCS_TEMPLATE[32] = "*MOSA";
		ABCS_TEMPLATE[39] = "T9999";
		ABCS_TEMPLATE[44] = "N";
		ABCS_TEMPLATE[51] = "VD";// CONTROL UNIT
		ABCS_TEMPLATE[52] = "vt";// WORK STN
		ABCS_TEMPLATE[55] = "0";// REBIT REQUEST NO
		ABCS_TEMPLATE[56] = "1";// AM/PM MODE
		ABCS_TEMPLATE[57] = "N";
		ABCS_TEMPLATE[58] = "N";
		ABCS_TEMPLATE[59] = "N";
		ABCS_TEMPLATE[60] = "N";
		ABCS_TEMPLATE[61] = "N";
		ABCS_TEMPLATE[62] = "N";
		ABCS_TEMPLATE[63] = "N";
	}

	protected String[] MBASE_TEMPLATE;

	protected void initMBASEMessageTemplate() {
		MBASE_TEMPLATE = new String[422];
		MBASE_TEMPLATE[0] = "680";
		MBASE_TEMPLATE[1] = "*LINX";
		MBASE_TEMPLATE[6] = "213";
		MBASE_TEMPLATE[8] = "0200";
		MBASE_TEMPLATE[9] = "*DSP";
		MBASE_TEMPLATE[10] = "MBSD";
		MBASE_TEMPLATE[11] = "*LINX";
		MBASE_TEMPLATE[15] = "01";
		MBASE_TEMPLATE[23] = "10";
		MBASE_TEMPLATE[24] = "10";
		MBASE_TEMPLATE[28] = "N";
		MBASE_TEMPLATE[33] = "1";
		MBASE_TEMPLATE[35] = "*END";
		MBASE_TEMPLATE[37] = "BTS";
		MBASE_TEMPLATE[38] = "RBS";
		MBASE_TEMPLATE[39] = "";
		MBASE_TEMPLATE[41] = "27";
		MBASE_TEMPLATE[47] = "I";
		MBASE_TEMPLATE[48] = "R";
		MBASE_TEMPLATE[49] = "10";
		MBASE_TEMPLATE[50] = "N";
		MBASE_TEMPLATE[51] = "F";
	}

	public SIBSConsumer() {
		super();
		try {
			initABCSMessageTemplate();
			initMBASEMessageTemplate();
			this.consumerType = Global.CONSUMER_CODE_SIBS;
			ServiceInfo objService = CachedParam
					.getServiceInfoByServiceType(consumerType);
			as400Service = new AS400_ServiceLocator();
			as400Service
					.setServiceTimeout(objService.getService_timeout() * 1000);
			as400Service.setAS400HttpPortEndpointAddress(objService
					.getService_url());
			as400Port = as400Service.getAS400HttpPort();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void submit(final MbMessage inMessage, MbMessage outMessage) {
		try {
			retErrorInfo.setErrorType(Global.MESSAGE_TYPE_OTHER);
			this.inMessage = inMessage;
			this.outMessage = outMessage;

			this.baseXmlRequestDoc = ElementUtils.getXMLContent(inMessage);
			this.buildLogOfRequestMessage();

			String tran_code = xmlMessageLogInfo.getTran_code();
			String tran_service_code = xmlMessageLogInfo.getTran_service_code();
			buildTransactionInfo(consumerType, tran_code, tran_service_code);

			// COMMON
			if (tran_code.equals(Global.MSG_CM_INQUIRY_CIF_INFOR)) {
				getCustomerInfoOffline();
			} else if (tran_code.equals(Global.MSG_CM_INQUIRY_CIF_ACCOUNTS)) {
				getCustomerAccounts();
			} else if (tran_code.equals(Global.MSG_CM_INQUIRY_REL_CIF_ACCOUNTS)) {
				getRelCustomerAccounts();
			} else if (tran_code.equals(Global.MSG_TR_TRANSFER_MONEY)) {
				fundTransfer();
			} else if (tran_code.equals(Global.MSG_OS_CREATE_FD_ACCOUNT)) {
				createFDAccount();
			} else if (tran_code.equals(Global.MSG_OS_CREATE_FD_RECEIPT)) {
				createFDReceipt();
			} else if (tran_code.equals(Global.MSG_OS_TRANSFER_FD_RECEIPT)) {
				transferToFDReceipt();
			} else if (tran_code.equals(Global.MSG_OS_SETTLEMENT_FD_ACCOUNT)) {
				settlementFDAccount();
			} else if (tran_code.equals(Global.MSG_LN_PAYMENT_LN_TRANSACTION)) {
				loanPayment();
			} else if (tran_code.equals(Global.MSG_CS_ACTIVATED_ATMCARD)) {
				activeATMCard();
			} else if (tran_code
					.equals(Global.MSG_TR_CREATE_CASHFLOW_TRANSACTION)) {
				createCashFlowOrder();
			} else if (tran_code
					.equals(Global.MSG_TR_CANCEL_CASHFLOW_TRANSACTION)) {
				cancelCashFlowOrder();
			} else if (tran_code
					.equals(Global.MSG_FX_CREATE_EXCHANGEMONEY_TRANSACTION)) {
				exchangeMoney();
			} else if (tran_code
					.equals(Global.MSG_CM_INQUIRY_MINISTATEMENT_BY_ACCOUNT)) {
				inquiryMiniStatement();
			} else if (tran_code.equals(Global.MSG_SETTING_AFT)) {
				settingAFT();
			} else if (tran_code.equals(Global.MSG_DESTROY_AFT)) {
				destroyAFT();
			} else if (tran_code.equals(Global.MSG_QUERY_AFT)) {
				queryAFT();
			} else if (tran_code.equals(Global.MSG_QUERY_FD_RECEIPT)) {
				queryFDReceipt();
			} else {
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
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".submit()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());

		}
	}

	/**
	 * (CM034)Query CIF inquiryMiniStatement
	 * 
	 * @version 1.0
	 */
	private void inquiryMiniStatement() {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String acct_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "acct_no"));
			String acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "acct_type"));
			int startPos = 67;
			int recordLength = 11;

			rebuildTransactionInfo(SIBS_012, Global.APP_DEFAULT_BRANCH_CODE);
			MBASE_TEMPLATE[49] = "10";// numOfRecord
			MBASE_TEMPLATE[68] = acct_no;
			MBASE_TEMPLATE[69] = acct_type;

			MBASE_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
			MBASE_TEMPLATE[18] = "BBMBSDDINQFNC";// scenarioNumber
			MBASE_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
			MBASE_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
			MBASE_TEMPLATE[32] = hostMessageLogInfo.getApprover_id();
			MBASE_TEMPLATE[40] = hostMessageLogInfo.getWs_name();
			MBASE_TEMPLATE[42] = hostMessageLogInfo.getBranch_code();
			MBASE_TEMPLATE[27] = "25520";
			MBASE_TEMPLATE[46] = "25520";

			String hostRequestMessage = AppUtils
					.convertArrayToString(MBASE_TEMPLATE);
			String hostResponseMessage = "";
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				this.retMessage = as400Port
						.hostMessageSending(
								hostMessageLogInfo.getRef_channel(),
								hostRequestMessage);
			} catch (RemoteException e) {
				retErrorInfo
						.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
				retErrorInfo.setErrorMessage(e.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (retMessage != null) {
				hostResponseMessage = AppUtils.convertArrayToString(retMessage
						.getArrString());
				if (retMessage.getErrCode() == null) {
					ArrayList<String[]> retArrResponseRow = AppUtils
							.getListOfResponseRow(retMessage.getArrString(),
									startPos, recordLength);
					String xpathOutput = "/message/?body/?list[?@name[set-value('records')]]";
					// 1.Tao the list
					ElementUtils.setXMLElementValue(outMessage, xpathOutput);
					for (int i = 0; i < retArrResponseRow.size(); i++) {
						xpathOutput = "/message/body/list[@name='records']/?$entity[?@name[set-value('"
								+ i + "')]]";
						// 2.Tao tung the entity
						// outMessage.evaluateXPath(xpathOutput);
						ElementUtils
								.setXMLElementValue(outMessage, xpathOutput);
						// 3.Tao tung the field
						xpathOutput = "/message/body/list[@name='records']/entity[@name='"
								+ i
								+ "']/?$field[set-value('%s')][?@name[set-value('%s')]]";
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, CoreBankUtils
										.convertCB2EBDate(retArrResponseRow
												.get(i)[2]), "tran_date");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, retArrResponseRow.get(i)[8],
								"tran_code");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, retArrResponseRow.get(i)[9],
								"amount");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, retArrResponseRow.get(i)[10],
								"tran_type");
					}
				} else {
					this.retErrorInfo.setErrorCode(Global.ERR_MBASE_START);
					this.retErrorInfo.setErrorMessage(retMessage
							.getDescription());
					this.retErrorInfo.setErrorType(MESSAGE_TYPE_MBASE);
				}
			}
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + hostRequestMessage
					+ "]");
			this.hostMessageLogInfo.setHostResponseMsg("["
					+ hostResponseMessage + "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".inquiryMiniStatement()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (CM021)Query CIF information(use cif number or id card+mobile to get cif
	 * information)
	 * 
	 * @version 1.0
	 */
	private void getCustomerInfoOffline() {
		try {
			String strRequestMsg = "";
			String strResponseMsg = "";

			String xpathInput = "/message/body/field[@name='%s']";
			String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "cif_no"));
			String cert_code = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "cert_code"));
			String cert_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "cert_type"));
			CustomerInfo retCustomerInfo = null;
			CustomerInfoFacade custFacade = new CustomerInfoFacade();
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (cif_no.length() > 0) {
				rebuildTransactionInfo(Global.SIBS_001,
						Global.APP_DEFAULT_BRANCH_CODE);
				retCustomerInfo = custFacade.getCustomerInfoByCIFOrID(cif_no,
						cert_code, cert_type);
			} else {
				if (cert_code.length() == 0) {
					this.retErrorInfo
							.setErrorCode(Global.ERR_UDF_CERT_CODE_NOT_VALID_OR_MISSING);
					this.retErrorInfo
							.setErrorMessage("ERR_UDF_CERT_CODE_NOT_VALID_OR_MISSING");
					return;
				}

				if (cert_type.length() == 0) {
					this.retErrorInfo
							.setErrorCode(Global.ERR_UDF_CERT_TYPE_NOT_VALID_OR_MISSING);
					this.retErrorInfo
							.setErrorMessage("ERR_UDF_CERT_TYPE_NOT_VALID_OR_MISSING");
					return;
				}
				rebuildTransactionInfo(Global.SIBS_002,
						Global.APP_DEFAULT_BRANCH_CODE);
				retCustomerInfo = custFacade.getCustomerInfoByCIFOrID(cif_no,
						cert_code, cert_type);
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			if (retCustomerInfo != null) {
				strResponseMsg = retCustomerInfo.toString();
				String xpathOutput = "/message/?body/?$field[set-value('%s')][?@name[set-value('%s')]]";
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getCif_no(), "cif_no");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getCif_acct_name(), "cif_acct_name");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getStatus(), "status");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getBank_no(), "bank_no");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getCountry(), "country");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getGender(), "gender");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getCert_code(), "cert_code");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getCert_type(), "cert_type");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						CoreBankUtils.convertCB2EBDateYYYYMMdd(retCustomerInfo
								.getBirth_date()), "birth_date");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getBirth_place(), "birth_place");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getIndividual(), "individual");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getTelephone(), "telephone");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getAddr(), "addr");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getMobile(), "mobile");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getEmail(), "email");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						retCustomerInfo.getContact_person(), "contact_person");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						CoreBankUtils.correctBranchCode(retCustomerInfo
								.getBranch_no()), "branch_no");
			} else {
				this.retErrorInfo.setErrorCode(Global.ERR_MBASE_START);
				this.retErrorInfo.setErrorMessage("No record found");

			}

			this.hostMessageLogInfo.setRef_cif_acct(cif_no);
			this.hostMessageLogInfo.setResp_code(String
					.valueOf(this.retErrorInfo.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(this.retErrorInfo
					.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[channel:"
					+ hostMessageLogInfo.getRef_channel() + "," + "branch:"
					+ hostMessageLogInfo.getBatch_no() + "," + "cif_no:"
					+ cif_no + ",cert_code:" + cert_code + "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + strResponseMsg
					+ "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".getCustomerInfo()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * Get customer's accounts.
	 */
	private void getCustomerAccounts() {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "cif_no"));
			String acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "acct_type"));
			String acct_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "acct_no"));
			String acct_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "acct_ccy"));
			String listOfCif[] = { cif_no };
			// TODO 26/06/2012
			String online = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "is_online"));
			if (cif_no.length() > 0) {
				getListOfCustomerAccountOffline(listOfCif, online, 1);
			} else {
				getAccountInfoOffline(acct_no, acct_type, acct_ccy, online);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".getCustomerAccount()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());
		}
	}

	/**
	 * Get customer's accounts.
	 */
	private void getRelCustomerAccounts() {
		try {
			String xmlInputSummary = "/message/body/entity[@name='summary']/field[@name='%s']";
			String online = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputSummary, "is_online"));

			List<MbElement> listOfTransfer = ElementUtils.getListOfXMLElement(
					inMessage, "/message/body/list[@name='records']/entity");
			String xpathInput = "";
			String cif_no = "";
			String acct_type = "";
			String acct_no = "";
			String acct_ccy = "";
			int iTotalRecord = listOfTransfer.size();
			String[] listOfCif = new String[iTotalRecord];
			for (int i = 0; i < iTotalRecord; i++) {
				xpathInput = "/message/body/list[@name='records']/entity[@name='"
						+ (i) + "']/field[@name='%s']";
				cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
						String.format(xpathInput, "cif_no"));
				acct_type = ElementUtils.getXMLElementInStringValue(inMessage,
						String.format(xpathInput, "acct_type"));
				acct_no = ElementUtils.getXMLElementInStringValue(inMessage,
						String.format(xpathInput, "acct_no"));
				acct_ccy = ElementUtils.getXMLElementInStringValue(inMessage,
						String.format(xpathInput, "acct_ccy"));
				listOfCif[i] = cif_no;
			}
			if (iTotalRecord == 1) {
				if (cif_no.length() > 0) {
					getListOfCustomerAccountOffline(listOfCif, online,
							iTotalRecord);
				} else {
					getAccountInfoOffline(acct_no, acct_type, acct_ccy, online);
				}
			} else if (iTotalRecord > 1) {
				getListOfCustomerAccountOffline(listOfCif, online, iTotalRecord);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".getCustomerAccount()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());
		}
	}

	/**
	 * Get customer account info
	 * 
	 * @param acct_no
	 * @param acct_type
	 * @param acct_ccy
	 */
	private void getAccountInfoOffline(String acct_no, String acct_type,
			String acct_ccy, String online) {
		try {
			String strRequestMsg = "";
			String strResponseMsg = "";
			AccountInfo tempAccount = new AccountInfo();
			AccountInfoFacade acctFacade = new AccountInfoFacade();
			if (acct_type.equals(Global.COREBBANK_CA_ACCOUNT_TYPE)) {
				rebuildTransactionInfo(Global.SIBS_003,
						Global.APP_DEFAULT_BRANCH_CODE);
			} else if (acct_type.equals(Global.COREBBANK_SA_ACCOUNT_TYPE)) {
				rebuildTransactionInfo(Global.SIBS_004,
						Global.APP_DEFAULT_BRANCH_CODE);
			} else if (acct_type
					.equals(Global.COREBBANK_FD_RECEIPT_ACCOUNT_TYPE)) {
				rebuildTransactionInfo(Global.SIBS_005,
						Global.APP_DEFAULT_BRANCH_CODE);
			} else if (acct_type.equals(Global.COREBBANK_FD_GROUP_ACCOUNT_TYPE)) {
				rebuildTransactionInfo(Global.SIBS_006,
						Global.APP_DEFAULT_BRANCH_CODE);
			} else if (acct_type.equals(Global.COREBBANK_LN_ACCOUNT_TYPE)) {
				rebuildTransactionInfo(Global.SIBS_007,
						Global.APP_DEFAULT_BRANCH_CODE);
			}
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				tempAccount = acctFacade.getAccountInfoByAccountNo(acct_no,
						acct_type, online);
			} catch (Exception ex) {
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (tempAccount == null) {
				this.retErrorInfo.setErrorCode(Global.ERR_MBASE_START);
				this.retErrorInfo.setErrorMessage("No record found");
			}

			if (retErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
				// 1.Tao node list,entity
				String xpathOutput = "/message/?body/?list[?@name[set-value('records')]]/?entity[?@name[set-value('0')]]";// /?$field[set-value('%s')][?@name[set-value('%s')]]";
				ElementUtils.setXMLElementValue(outMessage, xpathOutput);
				// 2.Tao cac field
				xpathOutput = "/message/body/list[@name[set-value('records')]]/entity[@name[set-value('0')]]/?$field[set-value('%s')][?@name[set-value('%s')]]";
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCif_no(), "cif_no");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						CoreBankUtils.correctAccountNumber(acct_no), "acct_no");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getAcct_ccy(), "acct_ccy");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getAcct_type(), "acct_type");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getSub_acct_type(), "sub_acct_type");
				ElementUtils
						.setXMLElementValue(outMessage, xpathOutput,
								AppUtils.XMLReplaceSpecChar(tempAccount
										.getAcct_name()), "acct_name");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						CoreBankUtils.correctAccountNumber(tempAccount
								.getP_acct_no()), "p_acct_no");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						CoreBankUtils.correctBranchCode(tempAccount
								.getBranch_no()), "branch_no");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getSub_branch_no(), "sub_branch_no");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCb_sign(), "cb_sign");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCert_type(), "cert_type");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCert_code(), "cert_code");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCx_sign(), "cx_sign");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getEstablish_date(), "establish_date");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getAvailable_date(), "available_date");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getRemark(), "remark");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCurrent_balance(), "current_balance");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getFreezed_balance(), "freezed_balance");
				ElementUtils
						.setXMLElementValue(outMessage, xpathOutput,
								tempAccount.getAvailable_balance(),
								"available_balance");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCreate_by(), "create_by");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCreate_time(), "create_time");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getUpdate_by(), "update_by");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getUpdate_time(), "update_time");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getLedger_balance(), "ledger_balance");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getHold_amt(), "hold_amt");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getOverdraft_limit(), "overdraft_limit");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getInterest_rate(), "interest_rate");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getDate_acct_opened(), "date_acct_opened");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getPassbook_no(), "passbook_no");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getAccured_interest(), "accured_interest");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getTerm(), "term");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getTerm_code(), "term_code");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getOri_balance(), "ori_balance");
				ElementUtils
						.setXMLElementValue(outMessage, xpathOutput,
								tempAccount.getPrincipal_balance(),
								"principal_balance");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getPenalty_amt(), "penalty_amt");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getCurrent_cash_value(),
						"current_cash_value");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getEarmarked_amt(), "earmarked_amt");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getIssued_date(), "issued_date");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getMaturity_date(), "maturity_date");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getTimes_nenewed_count(),
						"times_nenewed_count");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getPurpose_code(), "purpose_code");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getLoan_ori_amt(), "loan_ori_amt");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getOs_balance(), "os_balance");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getOs_principal(), "os_principal");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getLoan_term(), "loan_term");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getPrincipal_frequent(),
						"principal_frequent");
				ElementUtils
						.setXMLElementValue(outMessage, xpathOutput,
								tempAccount.getInterest_frequent(),
								"interest_frequent");
				ElementUtils
						.setXMLElementValue(outMessage, xpathOutput,
								tempAccount.getFull_release_date(),
								"full_release_date");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getOverdue_indicator_desc(),
						"overdue_indicator_desc");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getAcct_status(), "acct_status");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getLoan_term_code(), "loan_term_code");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getBilled_late_charge(),
						"billed_late_charge");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getBilled_principal(), "billed_principal");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getBilled_interest(), "billed_interest");
				ElementUtils.setXMLElementValue(outMessage, xpathOutput,
						tempAccount.getPayment_amt(), "payment_amt");
				ElementUtils
						.setXMLElementValue(outMessage, xpathOutput,
								tempAccount.getFinal_payment_amt(),
								"final_payment_amt");
			}
			this.hostMessageLogInfo.setRef_cif_acct(acct_no);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo
					.setHostRequestMsg("[" + strRequestMsg + "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + strResponseMsg
					+ "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".getAccount()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * Query customer's account from corebank using JDBC.
	 * 
	 */
	private void getListOfCustomerAccountOffline(String[] cif_no,
			String online, int numOfCif) {
		try {
			AccountInfoFacade acctFacade = new AccountInfoFacade();
			String hostRequestMessage = "";
			String hostResponseMessage = "";
			ArrayList<AccountInfo> arrAccountInfo = null;
			rebuildTransactionInfo(SIBS_009, Global.APP_DEFAULT_BRANCH_CODE);
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				if (numOfCif == 1) {
					if (cif_no[0].length() > 0) {
						arrAccountInfo = acctFacade.getAccountInfoByCif(
								cif_no[0], online);
					} else {

					}
				} else if (numOfCif > 1) {
					arrAccountInfo = acctFacade.getRelAccountInfoByCif(cif_no,
							numOfCif, online);
				}
			} catch (Exception ex) {
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			if (arrAccountInfo == null || arrAccountInfo.size() == 0) {
				this.retErrorInfo.setErrorCode(Global.ERR_MBASE_START);
				this.retErrorInfo.setErrorMessage("No record found");
			} else {

				// 1.Tao node list,entity
				String xpathOutput = "/message/?body/?list[?@name[set-value('records')]]";
				ElementUtils.setXMLElementValue(outMessage, xpathOutput);

				for (int i = 0; i < arrAccountInfo.size(); i++) {
					AccountInfo tempAccount = arrAccountInfo.get(i);
					xpathOutput = "/message/body/list[@name='records']/?$entity[?@name[set-value('"
							+ i + "')]]";
					ElementUtils.setXMLElementValue(outMessage, xpathOutput);
					xpathOutput = "/message/body/list[@name='records']/entity[@name='%s']/?$field[set-value('%s')][?@name[set-value('%s')]]";

					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getCif_no(),
							"cif_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.correctAccountNumber(tempAccount
											.getAccount_no()), "acct_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getAcct_ccy(),
							"acct_ccy");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getAcct_type(),
							"acct_type");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getSub_acct_type(),
							"sub_acct_type");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), AppUtils
									.XMLReplaceSpecChar(tempAccount
											.getAcct_name()), "acct_name");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.correctAccountNumber(tempAccount
											.getP_acct_no()), "p_acct_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.correctBranchCode(tempAccount
											.getBranch_no()), "branch_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getSub_branch_no(),
							"sub_branch_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getCb_sign(),
							"cb_sign");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getCert_type(),
							"cert_type");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getCert_code(),
							"cert_code");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getCx_sign(),
							"cx_sign");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getEstablish_date(),
							"establish_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getAvailable_date(),
							"available_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getRemark(),
							"remark");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput,
									String.valueOf(i),
									tempAccount.getCurrent_balance(),
									"current_balance");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput,
									String.valueOf(i),
									tempAccount.getFreezed_balance(),
									"freezed_balance");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getAvailable_balance(),
							"available_balance");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getCreate_by(),
							"create_by");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getCreate_time(),
							"create_time");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getUpdate_by(),
							"update_by");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getUpdate_time(),
							"update_time");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getLedger_balance(),
							"ledger_balance");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getHold_amt(),
							"hold_amt");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput,
									String.valueOf(i),
									tempAccount.getOverdraft_limit(),
									"overdraft_limit");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getInterest_rate(),
							"interest_rate");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getDate_acct_opened(),
							"date_acct_opened");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getPassbook_no(),
							"passbook_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getAccured_interest(),
							"accured_interest");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getTerm(), "term");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getTerm_code(),
							"term_code");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getOri_balance(),
							"ori_balance");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getPrincipal_balance(),
							"principal_balance");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getPenalty_amt(),
							"penalty_amt");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getCurrent_cash_value(),
							"current_cash_value");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getEarmarked_amt(),
							"earmarked_amt");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getIssued_date(),
							"issued_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getMaturity_date(),
							"maturity_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getTimes_nenewed_count(),
							"times_nenewed_count");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getPurpose_code(),
							"purpose_code");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getLoan_ori_amt(),
							"loan_ori_amt");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getOs_balance(),
							"os_balance");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getOs_principal(),
							"os_principal");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getLoan_term(),
							"loan_term");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getPrincipal_frequent(),
							"principal_frequent");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getInterest_frequent(),
							"interest_frequent");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getFull_release_date(),
							"full_release_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getOverdue_indicator_desc(),
							"overdue_indicator_desc");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getAcct_status(),
							"acct_status");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getLoan_term_code(),
							"loan_term_code");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getBilled_late_charge(),
							"billed_late_charge");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getBilled_principal(),
							"billed_principal");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput,
									String.valueOf(i),
									tempAccount.getBilled_interest(),
									"billed_interest");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAccount.getPayment_amt(),
							"payment_amt");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAccount.getFinal_payment_amt(),
							"final_payment_amt");
				}
			}
			this.hostMessageLogInfo.setRef_cif_acct(cif_no[0]);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + hostRequestMessage
					+ "]");
			this.hostMessageLogInfo.setHostResponseMsg("["
					+ hostResponseMessage + "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer()
					.append("Error source:")
					.append(CLASS_NAME + ".getListOfCustomerAccountOffline()\n")
					.append("Error message:").append(ex.getStackTrace() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());
		}
	}

	/**
	 * (TR004)Create cash flow transaction
	 */
	private void createCashFlowOrder() {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String major_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "major_acct_no"));
			String major_acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "major_acct_type"));
			String sub_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "sub_acct_no"));
			String sub_acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "sub_acct_type"));
			String ceiling_amt = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "ceiling_amt")));
			String floor_amt = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "floor_amt")));
			String balance_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "balance_type"));
			String fee = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "fee")));

			if (major_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (major_acct_type.length() == 0
					|| CoreBankUtils.isValidAccountType(major_acct_type) == false) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			if (sub_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (sub_acct_type.length() == 0
					|| CoreBankUtils.isValidAccountType(sub_acct_type) == false) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			if (!CoreBankUtils.isValidTransactionAmount(ceiling_amt)) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_CEILLING_AMT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_CEILLING_AMT_NOT_VALID_OR_MISSING");
				return;
			}
			if (!CoreBankUtils.isValidTransactionAmount(floor_amt)) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_FLOOR_AMT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_FLOOR_AMT_NOT_VALID_OR_MISSING");
				return;
			}
			if (balance_type.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_BALANCE_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_BALANCE_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			rebuildTransactionInfo(SIBS_010, Global.APP_DEFAULT_BRANCH_CODE);
			MBASE_TEMPLATE[47] = "A";// Add
			MBASE_TEMPLATE[48] = "R";//
			MBASE_TEMPLATE[49] = "1";// numOfRecord
			MBASE_TEMPLATE[50] = "N";//
			MBASE_TEMPLATE[51] = "F";//

			MBASE_TEMPLATE[68] = major_acct_no;
			MBASE_TEMPLATE[69] = major_acct_type;
			MBASE_TEMPLATE[70] = sub_acct_no;
			MBASE_TEMPLATE[71] = sub_acct_type;
			MBASE_TEMPLATE[72] = CoreBankUtils.convertEB2CBNumeric(ceiling_amt);
			MBASE_TEMPLATE[73] = CoreBankUtils.convertEB2CBNumeric(floor_amt);
			MBASE_TEMPLATE[74] = balance_type;
			MBASE_TEMPLATE[75] = "Y";// Auto reset
			MBASE_TEMPLATE[76] = CoreBankUtils.convertEB2CBNumeric(fee);

			MBASE_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
			MBASE_TEMPLATE[18] = "BBMBSDDMNTFNC";// scenarioNumber
			MBASE_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
			MBASE_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
			MBASE_TEMPLATE[32] = hostMessageLogInfo.getApprover_id();
			MBASE_TEMPLATE[40] = hostMessageLogInfo.getWs_name();
			MBASE_TEMPLATE[42] = hostMessageLogInfo.getBranch_code();
			MBASE_TEMPLATE[27] = this.hostMessageLogInfo.getHost_tran_code();// "28131";
			MBASE_TEMPLATE[46] = this.hostMessageLogInfo.getHost_tran_code();// "28131";

			String hostRequestMessage = AppUtils
					.convertArrayToString(MBASE_TEMPLATE);
			String hostResponseMessage = "";
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				retMessage = as400Port
						.hostMessageSending(
								hostMessageLogInfo.getRef_channel(),
								hostRequestMessage);

				if (retMessage != null) {
					if (retMessage.getErrCode() == null) {
						String xpathOutput = "/message/?body/?$field[set-value('%s')][?@name[set-value('%s')]]";
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, CoreBankUtils
										.correctAccountNumber(major_acct_no),
								"major_acct_no");
						ElementUtils
								.setXMLElementValue(outMessage, xpathOutput,
										major_acct_type, "major_acct_type");
						ElementUtils
								.setXMLElementValue(
										outMessage,
										xpathOutput,
										CoreBankUtils
												.correctAccountNumber(sub_acct_no),
										"sub_acct_no");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, sub_acct_type, "sub_acct_type");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, ceiling_amt, "ceiling_amt");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, floor_amt, "floor_amt");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, balance_type, "balance_type");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, fee, "fee");
					} else {
						if (retMessage.getErrCode().equals("DDM0118")) {
							this.retErrorInfo
									.setErrorCode(Global.ERR_UDF_CASHFLOW_ACCOUNT_PRIMARY_HIGHERLEVEL);
							this.retErrorInfo.setErrorMessage(retMessage
									.getDescription());
							this.retErrorInfo.setErrorType(MESSAGE_TYPE_MBASE);
						} else if (retMessage.getErrCode().equals("DDM0116")) {
							this.retErrorInfo
									.setErrorCode(Global.ERR_UDF_CASHFLOW_ACCOUNT_ALREADY_SECONDARY);
							this.retErrorInfo.setErrorMessage(retMessage
									.getDescription());
							this.retErrorInfo.setErrorType(MESSAGE_TYPE_MBASE);
						} else {
							this.retErrorInfo
									.setErrorCode(Global.ERR_MBASE_START);
							this.retErrorInfo.setErrorMessage(retMessage
									.getDescription());
							this.retErrorInfo.setErrorType(MESSAGE_TYPE_MBASE);
						}
					}
					if (retMessage.getArrString() != null) {
						hostResponseMessage = AppUtils
								.convertArrayToString(retMessage.getArrString());
					}
				} else {
					this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
					this.retErrorInfo
							.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
					this.retErrorInfo.setErrorType(MESSAGE_TYPE_MBASE);
				}

			} catch (RemoteException ex) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
				this.retErrorInfo.setErrorMessage(ex.toString());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			this.hostMessageLogInfo.setRef_cif_acct(major_acct_no);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + hostRequestMessage
					+ "]");
			this.hostMessageLogInfo.setHostResponseMsg("["
					+ hostResponseMessage + "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".createCashFlowOrder()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (TR006)Cancel cash flow transaction
	 * 
	 */
	private void cancelCashFlowOrder() {
		try {
			// Thieu branch_code???
			String xpathInput = "/message/body/field[@name='%s']";
			String major_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "major_acct_no"));
			String major_acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "major_acct_type"));
			String sub_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "sub_acct_no"));
			String sub_acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "sub_acct_type"));
			String ceiling_amt = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "ceiling_amt")));
			String floor_amt = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "floor_amt")));
			String balance_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "balance_type"));
			String fee = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "fee"));

			if (major_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (major_acct_type.length() == 0
					|| CoreBankUtils.isValidAccountType(major_acct_type) == false) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			if (sub_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (sub_acct_type.length() == 0
					|| CoreBankUtils.isValidAccountType(sub_acct_type) == false) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			rebuildTransactionInfo(SIBS_011, Global.APP_DEFAULT_BRANCH_CODE);
			MBASE_TEMPLATE[47] = "D";// delete
			MBASE_TEMPLATE[48] = "R";//
			MBASE_TEMPLATE[49] = "1";// numOfRecord
			MBASE_TEMPLATE[50] = "N";//
			MBASE_TEMPLATE[51] = "F";//

			MBASE_TEMPLATE[68] = major_acct_no;
			MBASE_TEMPLATE[69] = major_acct_type;
			MBASE_TEMPLATE[70] = sub_acct_no;
			MBASE_TEMPLATE[71] = sub_acct_type;

			MBASE_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
			MBASE_TEMPLATE[18] = "BBMBSDDMNTFNC";// scenarioNumber
			MBASE_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
			MBASE_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
			MBASE_TEMPLATE[32] = hostMessageLogInfo.getApprover_id();
			MBASE_TEMPLATE[40] = hostMessageLogInfo.getWs_name();
			MBASE_TEMPLATE[42] = hostMessageLogInfo.getBranch_code();
			MBASE_TEMPLATE[27] = "28131";
			MBASE_TEMPLATE[46] = "28131";

			String hostRequestMessage = AppUtils
					.convertArrayToString(MBASE_TEMPLATE);
			String hostResponseMessage = "";
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				retMessage = as400Port
						.hostMessageSending(
								hostMessageLogInfo.getRef_channel(),
								hostRequestMessage);

				if (retMessage != null) {
					if (retMessage.getErrCode() == null) {
						String xpathOutput = "/message/?body/?$field[set-value('%s')][?@name[set-value('%s')]]";
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, CoreBankUtils
										.correctAccountNumber(major_acct_no),
								"major_acct_no");
						ElementUtils
								.setXMLElementValue(outMessage, xpathOutput,
										major_acct_type, "major_acct_type");
						ElementUtils
								.setXMLElementValue(
										outMessage,
										xpathOutput,
										CoreBankUtils
												.correctAccountNumber(sub_acct_no),
										"sub_acct_no");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, sub_acct_type, "sub_acct_type");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, ceiling_amt, "ceiling_amt");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, floor_amt, "floor_amt");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, balance_type, "balance_type");
						ElementUtils.setXMLElementValue(outMessage,
								xpathOutput, fee, "fee");
					} else {
						if (retMessage.getErrCode().equals("MB28131")) {
							this.retErrorInfo
									.setErrorCode(Global.ERR_UDF_CASHFLOW_RECORD_NOTFOUND);
							this.retErrorInfo.setErrorMessage(retMessage
									.getDescription());
							this.retErrorInfo.setErrorType(MESSAGE_TYPE_MBASE);
						} else {
							this.retErrorInfo
									.setErrorCode(Global.ERR_MBASE_START);
							this.retErrorInfo.setErrorMessage(retMessage
									.getDescription());
							this.retErrorInfo.setErrorType(MESSAGE_TYPE_MBASE);
						}
					}
					hostResponseMessage = AppUtils
							.convertArrayToString(retMessage.getArrString());
				} else {
					this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
					this.retErrorInfo
							.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
					this.retErrorInfo.setErrorType(MESSAGE_TYPE_MBASE);
				}
			} catch (RemoteException ex) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
				this.retErrorInfo.setErrorMessage(ex.toString());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.hostMessageLogInfo.setRef_cif_acct(major_acct_no);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + hostRequestMessage
					+ "]");
			this.hostMessageLogInfo.setHostResponseMsg("["
					+ hostResponseMessage + "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".cancelCashFlowOrder()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo
					.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (OS002)Create FD account
	 * 
	 * Call : webservice with groupcode (05) Branch code: subbranchcode Currency
	 * : 01-VND,37 USD
	 */
	private void createFDAccount() {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "cif_no"));
			String fd_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "fd_branch_no"));
			String fd_acct_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "fd_acct_ccy"));

			if (cif_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_CIF_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_CIF_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (fd_acct_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (fd_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}

			fd_branch_no = CoreBankUtils.correctBranchCode(fd_branch_no);
			String sub_fd_branch_no = "";// sub branch for create account
			if (fd_branch_no.length() < 5)
				sub_fd_branch_no = fd_branch_no + "00";

			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			// Kienvt : Hien tai moi chi fix cho VND = 05
			String fd_acct_no = as400Port.getAccount(sub_fd_branch_no, "01",
					"05");
			String strRequestMsg = "";
			String strResponseMsg = "";
			if (fd_acct_no != null && fd_acct_no.length() > 0) {
				try {
					rebuildTransactionInfo(SIBS_013, fd_branch_no);
					this.retMessage = as400Port.createFD(
							hostMessageLogInfo.getRef_channel(),
							hostMessageLogInfo.getHost_tran_date(),
							hostMessageLogInfo.getTeller_id(), fd_branch_no,
							fd_acct_ccy, cif_no, fd_acct_no, "T", "", "Y");
				} catch (RemoteException e) {
					retErrorInfo
							.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
					retErrorInfo.setErrorMessage(e.getMessage());
				}
				if (retMessage != null) {
					if (retMessage.getErrCode() == null) {
						String xmlOuputPattern = "/message/?body/?$field[set-value('%s')][?@name[set-value('%s')]]";
						outMessage.evaluateXPath(String.format(xmlOuputPattern,
								CoreBankUtils.correctAccountNumber(fd_acct_no),
								"fd_acct_no"));
						outMessage.evaluateXPath(String.format(xmlOuputPattern,
								fd_acct_ccy, "fd_acct_ccy"));
						outMessage
								.evaluateXPath(String.format(
										xmlOuputPattern,
										AppUtils.now(Global.DEF_FORMAT_CREATE_DATE_TIME),
										"create_time"));
					}
					strResponseMsg = AppUtils.convertArrayToString(retMessage
							.getArrString());
				} else {
					retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
					retErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
				}
			}
			this.hostMessageLogInfo.setRef_cif_acct(fd_acct_no);
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[channel:"
					+ hostMessageLogInfo.getRef_channel() + "," + "tran_date:"
					+ hostMessageLogInfo.getHost_tran_date() + ","
					+ "tellerid:" + hostMessageLogInfo.getTeller_id() + ","
					+ "branch_no:" + fd_branch_no + "," + "fd_acct_ccy:"
					+ fd_acct_ccy + "," + "cif_no:" + cif_no + ","
					+ "fd_acct_no:" + fd_acct_no + ",:" + "T" + "," + "," + "Y"
					+ "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + strResponseMsg
					+ "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".createFDAccount()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo
					.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (OS003)Create FD receipt Call : webservice with groupcode (1)
	 */
	private void createFDReceipt() {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String fd_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "fd_branch_no"));
			String fd_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "fd_acct_no"));
			String fd_acct_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "fd_acct_ccy"));
			String fd_product_code = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "fd_product_code"));
			String interest_receive_acct = ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "interest_receive_acct"));
			String deposit_amt = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "amt")));
			String remark = AppUtils.convertToVietnamesNoSign(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "remark")));

			// 201411 QuanLd Them truong du lieu cho viec tao TK FD cho phep
			// renew voi truong hop chuyen lai sang TK khac

			String isRolloutInterest = "";
			try {
				isRolloutInterest = AppUtils
						.convertToVietnamesNoSign(ElementUtils
								.getXMLElementInStringValue(inMessage, String
										.format(xpathInput,
												"is_rollout_interest")));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// / het update
			if (fd_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (fd_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (fd_acct_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (fd_product_code.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_FDPRODUCT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_FDPRODUCT_NOT_VALID_OR_MISSING");
				return;
			}
			if (!CoreBankUtils.isValidTransactionAmount(deposit_amt)) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_TRANSACTION_AMOUT_NOT_VALID_OR_MISSING");
				return;
			}

			fd_branch_no = CoreBankUtils.correctBranchCode(fd_branch_no);
			deposit_amt = CoreBankUtils.convertEB2CBNumeric(deposit_amt);

			String sub_fd_branch_no = "";// sub branch for create account
			if (fd_branch_no.length() < 5)
				sub_fd_branch_no = fd_branch_no + "00";

			CurrencyInfo currencyInfo = CachedParam
					.findCurrencyByCode(fd_acct_ccy);
			String currRefCode = currencyInfo.getRefCode();
			String fd_group = currencyInfo.getFd_groupcode();

			// Production groupcode="2" =>khong cap so
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			String receipt_no = as400Port.getFDReceiptAccount(sub_fd_branch_no,
					currRefCode, fd_group);
			String strRequestMsg = "";
			String strResponseMsg = "";
			if (receipt_no != null) {
				this.hostMessageLogInfo.setSend_time(AppUtils
						.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
				try {
					rebuildTransactionInfo(SIBS_014, fd_branch_no);
					String autoRenew = "Y";
					if (interest_receive_acct.length() > 0) {
						autoRenew = "N";
					}
					// 20141113: QuanLD: hot fix: Neu gia tri truong nay='Z' se
					// de autoRenew=Y

					if ("Z".equals(isRolloutInterest)) {
						autoRenew = "Z";
					}
					// ------------------------------------------
					strRequestMsg = buildCreateFDReceipt(
							hostMessageLogInfo.getWs_ip(),
							hostMessageLogInfo.getHost_tran_date(),
							fd_branch_no, hostMessageLogInfo.getTeller_id(),
							hostMessageLogInfo.getApprover_id(),
							hostMessageLogInfo.getHost_tran_sn(),
							hostMessageLogInfo.getHost_tran_code(), autoRenew,
							fd_acct_no, receipt_no, fd_acct_ccy,
							fd_product_code, deposit_amt, remark,
							interest_receive_acct, interest_receive_acct);
					retMessage = as400Port.hostMessageSending(
							hostMessageLogInfo.getRef_channel(), strRequestMsg);

					if (retMessage != null) {
						strResponseMsg = AppUtils
								.convertArrayToString(retMessage.getArrString());
						if (retMessage.getErrCode() == null) {
							String xpathOutput = "/message/?body/?$field[set-value('%s')][?@name[set-value('%s')]]";
							outMessage.evaluateXPath(String.format(xpathOutput,
									CoreBankUtils
											.correctAccountNumber(fd_acct_no),
									"fd_acct_no"));
							outMessage.evaluateXPath(String.format(xpathOutput,
									CoreBankUtils
											.correctAccountNumber(receipt_no),
									"receipt_no"));
							outMessage
									.evaluateXPath(String.format(
											xpathOutput,
											AppUtils.now(Global.DEF_FORMAT_CREATE_DATE_TIME),
											"create_time"));
						} else {
							retErrorInfo = CachedParam.mappingErrorCode(
									retMessage.getErrCode(),
									retMessage.getDescription(),
									MESSAGE_TYPE_ABCS);
						}
					} else {
						retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
						retErrorInfo
								.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
					}
				} catch (RemoteException e) {
					retErrorInfo
							.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
					retErrorInfo.setErrorMessage(e.getMessage());
				}
				this.hostMessageLogInfo.setReceive_time(AppUtils
						.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			}
			this.hostMessageLogInfo.setRef_cif_acct(receipt_no);
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg(strRequestMsg);
			this.hostMessageLogInfo.setHostResponseMsg("[" + strResponseMsg
					+ "]");
			this.arrRetHOSTMessageLogInfo
					.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".createFDReceipt()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo
					.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (CS002)Active ATM card
	 */
	private void activeATMCard() {
		try {
			String xpathInput = "/message/body/field[@name='%s']";
			String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "cif_no"));
			String card_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xpathInput, "card_no"));
			String cert_code = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "cert_code"));
			String cert_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "cert_type"));

			if (cif_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_CIF_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_CIF_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (card_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_CARD_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_CARD_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (cert_code.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_CERT_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_CERT_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (cert_type.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_CERT_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_CERT_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			String strRequestMsg = "";
			String strResponseMsg = "";
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				rebuildTransactionInfo(SIBS_015, Global.APP_DEFAULT_BRANCH_CODE);
				this.retMessage = as400Port.cardActivation(
						hostMessageLogInfo.getRef_channel(),
						hostMessageLogInfo.getTeller_id(),
						hostMessageLogInfo.getBranch_code(), card_no, cif_no,
						cert_code, cert_type);

				if (retMessage != null) {
					strResponseMsg = AppUtils.convertArrayToString(retMessage
							.getArrString());
					if (retMessage.getErrCode() == null) {
						this.retErrorInfo.setErrorCode(ERR_SYSTEM_OK);
						this.retErrorInfo.setErrorMessage(retMessage
								.getDescription());
					} else {
						if (retMessage.getErrCode().equals("102")) {
							this.retErrorInfo
									.setErrorCode(Global.ERR_UDF_ACTIVE_ATM_CARD_NOTFOUND);
							this.retErrorInfo.setErrorMessage(retMessage
									.getDescription());
						} else if (retMessage.getErrCode().equals("120")) {
							this.retErrorInfo
									.setErrorCode(Global.ERR_UDF_ACTIVE_ATM_CARD_NOTREACTIVE);
							this.retErrorInfo.setErrorMessage(retMessage
									.getDescription());
						} else {
							this.retErrorInfo
									.setErrorCode(Global.ERR_MBASE_START);
							this.retErrorInfo.setErrorMessage(retMessage
									.getDescription());
						}
					}
				} else {
					this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
					this.retErrorInfo
							.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
				}
			} catch (RemoteException e) {
				retErrorInfo
						.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
				retErrorInfo.setErrorMessage(e.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.hostMessageLogInfo.setRef_cif_acct(card_no);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[cif_no:" + cif_no
					+ ", card_no:" + card_no + ", cert_code:" + cert_code
					+ ", cert_type:" + cert_type + "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + strResponseMsg
					+ "]");
			this.arrRetHOSTMessageLogInfo
					.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".activeATMCard()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo
					.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (LN002)Loan payment online
	 */
	private void loanPayment() {
		try {

			String xpathInput = "/message/body/field[@name='%s']";
			String is_payment_adv = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "is_payment_adv"));
			String is_payoff_tran = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "is_payoff_tran"));
			String rollout_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_branch_no"));
			String rollout_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_acct_no"));
			String rollout_acct_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_acct_ccy"));
			String rollout_buy_rate = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_buy_rate"));
			String loan_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "loan_branch_no"));
			String loan_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "loan_acct_no"));
			String loan_acct_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "loan_acct_ccy"));
			String loan_sell_rate = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "loan_sell_rate"));
			String cr_amt = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "cr_amt")));

			if (rollout_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_acct_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_buy_rate.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SELL_RATE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SELL_RATE_AMOUT_NOT_VALID_OR_MISSING");
				return;
			}

			if (loan_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (loan_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}

			if (loan_acct_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (loan_sell_rate.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING");
				return;
			}
			if (!CoreBankUtils.isValidTransactionAmount(cr_amt)) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_TRANSACTION_AMOUT_NOT_VALID_OR_MISSING");
				return;
			}

			if (is_payment_adv.equals("Y")) {// advance 4245
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				rebuildTransactionInfo(SIBS_023, rollout_branch_no);
			}
			if (is_payoff_tran.equals("Y")) {// payoff 4920
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				ABCS_TEMPLATE[0] = "";
				rebuildTransactionInfo(SIBS_024, rollout_branch_no);
			}

			String hostRequestMessage = "";
			String hostResponseMessage = "";

			hostRequestMessage = AppUtils.convertArrayToString(ABCS_TEMPLATE);

			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.retMessage = as400Port.hostMessageSending(
					hostMessageLogInfo.getRef_channel(), hostRequestMessage);
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			if (this.retMessage != null) {
				if (this.retMessage.getErrCode() == null) {
				} else {
				}
				hostResponseMessage = AppUtils.convertArrayToString(retMessage
						.getArrString());
			}
			this.hostMessageLogInfo.setRef_cif_acct(loan_acct_no);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg(hostRequestMessage);
			this.hostMessageLogInfo.setHostResponseMsg(hostResponseMessage);
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".loanPayment()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (TR003)Transfer money
	 */
	private void fundTransfer() {
		try {
			// CSP200: Salary Payment Transaction CTR203: retail batch transfer:
			// RTR004: corporate batch transfer
			String xmlInputPattern = "/message/body/entity[@name='summary']/field[@name='%s']";
			String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "cif_no"));
			String cif_acct_name = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "cif_acct_name"));
			String cert_code = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "cert_code"));
			String is_batches = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "is_batches"));

			int iTotalRecord = 0;
			int iSuccesfulRecord = 0;
			ErrorInfo lastRetErrorInfo = new ErrorInfo(ERR_SYSTEM_OK,
					ERR_SYSTEM_OK_MSG, MESSAGE_TYPE_ABCS);
			List<MbElement> listOfTransfer = ElementUtils.getListOfXMLElement(
					inMessage, "/message/body/list[@name='records']/entity");
			String xmlInputPattern_2 = "/message/?body/?list[?@name[set-value('records')]]";
			outMessage.evaluateXPath(xmlInputPattern_2);
			String GLAccount = "";
			for (int i = 0; i < listOfTransfer.size(); i++) {
				// 2.Tao tung the entity
				xmlInputPattern_2 = "/message/body/list[@name='records']/?$entity[?@name[set-value('"
						+ i + "')]]";
				outMessage.evaluateXPath(xmlInputPattern_2);

				// 3.Process detail
				ErrorInfo tempErrorInfo = new ErrorInfo(ERR_SYSTEM_OK,
						ERR_SYSTEM_OK_MSG, MESSAGE_TYPE_ABCS);
				xmlInputPattern_2 = "/message/body/list[@name='records']/entity[@name='"
						+ (i) + "']/field[@name='%s']";

				String tran_sn = ElementUtils.getXMLElementInStringValue(
						inMessage, String.format(xmlInputPattern_2, "tran_sn"));
				String is_outward_transfer = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "is_outward_transfer"));
				String rollout_branch_no = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "rollout_branch_no"));
				String bnfc_branch_no = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "bnfc_branch_no"));
				String rollout_acct_no = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "rollout_acct_no"));
				String rollout_acct_name = AppUtils
						.convertToVietnamesNoSign(ElementUtils
								.getXMLElementInStringValue(inMessage, String
										.format(xmlInputPattern_2,
												"rollout_acct_name")));
				String rollout_acct_type = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "rollout_acct_type"));
				String rollout_acct_ccy = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "rollout_acct_ccy"));
				String rollout_buy_rate = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "rollout_buy_rate"));

				String bnfc_acct_no = ElementUtils.getXMLElementInStringValue(
						inMessage,
						String.format(xmlInputPattern_2, "bnfc_acct_no"));
				String bnfc_acct_name = AppUtils
						.convertToVietnamesNoSign(ElementUtils.getXMLElementInStringValue(
								inMessage,
								String.format(xmlInputPattern_2, "bnfc_name")));
				String bnfc_acct_type = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "bnfc_acct_type"));
				String bnfc_acct_ccy = ElementUtils.getXMLElementInStringValue(
						inMessage,
						String.format(xmlInputPattern_2, "bnfc_acct_ccy"));
				String bnfc_sell_rate = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "bnfc_sell_rate"));

				String bnfc_bank_addr = AppUtils
						.convertToVietnamesNoSign(ElementUtils
								.getXMLElementInStringValue(inMessage, String
										.format(xmlInputPattern_2,
												"bnfc_bank_addr")));
				String bnfc_branch_id = ElementUtils
						.getXMLElementInStringValue(inMessage, String.format(
								xmlInputPattern_2, "bnfc_branch_id"));
				String bnfc_branch_name = AppUtils
						.convertToVietnamesNoSign(ElementUtils
								.getXMLElementInStringValue(inMessage, String
										.format(xmlInputPattern_2,
												"bnfc_branch_name")));
				String amt = CoreBankUtils.roundCBNumberic(ElementUtils
						.getXMLElementInStringValue(inMessage,
								String.format(xmlInputPattern_2, "amt")));
				String remark = AppUtils.convertToVietnamesNoSign(ElementUtils
						.getXMLElementInStringValue(inMessage,
								String.format(xmlInputPattern_2, "remark")));
				String orgFee = CoreBankUtils.roundCBNumberic(ElementUtils
						.getXMLElementInStringValue(inMessage,
								String.format(xmlInputPattern_2, "fee")));
				if (is_outward_transfer.length() == 0) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_TRANSFER_TYPE_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_TRANSFER_TYPE_NOT_VALID_OR_MISSING");

				}
				if (is_batches.length() == 0) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_IS_BATCH_TRAN_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_IS_BATCH_TRAN_NOT_VALID_OR_MISSING");

				}
				if (tran_sn.length() == 0) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_TRAN_SN_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_TRAN_SN_NOT_VALID_OR_MISSING");

				}

				if (rollout_branch_no.length() == 0) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");

				}
				if (rollout_acct_no.length() == 0) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING");

				}
				if (rollout_acct_ccy.length() == 0) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING");

				}

				if (rollout_buy_rate.length() == 0) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_SELL_RATE_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_SELL_RATE_AMOUT_NOT_VALID_OR_MISSING");

				}
				if (rollout_acct_type.length() == 0
						|| CoreBankUtils.isValidAccountType(rollout_acct_type) == false) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");

				}

				if (!CoreBankUtils.isValidTransactionAmount(amt)) {
					tempErrorInfo
							.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
					tempErrorInfo
							.setErrorMessage("ERR_UDF_TRANSACTION_AMOUT_NOT_VALID_OR_MISSING");

				}

				String hostRequestMessage = "";
				String hostResponseMessage = "";
				String refTranCode = "";

				bnfc_sell_rate = rollout_buy_rate;
				bnfc_acct_ccy = rollout_acct_ccy;
				if (bnfc_acct_type == null || bnfc_acct_type.length() == 0) {
					bnfc_acct_type = rollout_acct_type;
				}
				if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
					if (is_outward_transfer.equals("N")) {// internal-bank
						if (bnfc_branch_no.length() == 0) {
							bnfc_branch_no = bnfc_acct_no.substring(0, 3);
						}
						if (bnfc_branch_no.length() == 0) {
							tempErrorInfo
									.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
							tempErrorInfo
									.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
						}
						if (bnfc_acct_no.length() == 0) {
							tempErrorInfo
									.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING);
							tempErrorInfo
									.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");

						}

						if (bnfc_acct_type.length() == 0
								|| CoreBankUtils
										.isValidAccountType(bnfc_acct_type) == false) {
							tempErrorInfo
									.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
							tempErrorInfo
									.setErrorMessage("ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");

						}
						if (bnfc_acct_ccy.length() == 0) {
							tempErrorInfo
									.setErrorCode(Global.ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING);
							tempErrorInfo
									.setErrorMessage("ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING");

						}
						if (bnfc_sell_rate.length() == 0) {
							tempErrorInfo
									.setErrorCode(Global.ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING);
							tempErrorInfo
									.setErrorMessage("ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING");

						}

						if (rollout_acct_type
								.equals(Global.COREBBANK_CA_ACCOUNT_TYPE)) {

							if (bnfc_acct_type
									.equals(Global.COREBBANK_CA_ACCOUNT_TYPE)) {
								if (bnfc_branch_no.equals(rollout_branch_no)) {
									refTranCode = SIBS_018;// CA->CA same
									// branch
								} else {
									refTranCode = SIBS_019;// CA->CA diff
								}
							} else if (bnfc_acct_type
									.equals(Global.COREBBANK_SA_ACCOUNT_TYPE)) {
								if (bnfc_branch_no.equals(rollout_branch_no)) {
									refTranCode = SIBS_020;// CA->SA same
									// branch
								} else {
									refTranCode = SIBS_021;// CA->SA diff
								}
							}
							// ngocnv

							else if (bnfc_acct_type
									.equals(Global.COREBBANK_FD_RECEIPT_ACCOUNT_TYPE)) {
								refTranCode = SIBS_3603;// CA->FD diff
							}

						} else if (rollout_acct_type
								.equals(Global.COREBBANK_SA_ACCOUNT_TYPE)) {
							if (bnfc_acct_type
									.equals(Global.COREBBANK_CA_ACCOUNT_TYPE)) {
								if (bnfc_branch_no.equals(rollout_branch_no)) {
									refTranCode = SIBS_037;// SA->CA same
									// branch
								} else {
									refTranCode = SIBS_038;// SA->CA diff
								}
							} else if (bnfc_acct_type
									.equals(Global.COREBBANK_SA_ACCOUNT_TYPE)) {
								if (bnfc_branch_no.equals(rollout_branch_no)) {
									refTranCode = SIBS_039;// SA->SA same
									// branch
								} else {
									refTranCode = SIBS_040;// SA->SA diff
								}
							}
						}
						// Chi lam neu du lieu da duoc valid het
						if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
							String vatFee = String.valueOf(new BigDecimal(
									orgFee).divide(new BigDecimal(
									Global.BASED_VAT_RATE), 0,
									RoundingMode.HALF_UP));
							String serviceFee = String.valueOf(new BigDecimal(
									orgFee).subtract(new BigDecimal(vatFee))
									.longValue());

							this.hostMessageLogInfo.setSend_time(AppUtils
									.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
							rebuildTransactionInfo(refTranCode,
									rollout_branch_no);
							try {
								if (refTranCode.equals(SIBS_018)
										|| refTranCode.equals(SIBS_019)) {// CA->CA
									this.retMessage = as400Port
											.transferFromCASAToCASA(
													hostMessageLogInfo
															.getRef_channel(),
													rollout_branch_no,
													bnfc_acct_no,
													amt,
													bnfc_acct_ccy,
													bnfc_sell_rate,
													rollout_acct_no,
													amt,
													rollout_acct_ccy,
													rollout_buy_rate,
													hostMessageLogInfo
															.getApprover_id(),
													remark,
													hostMessageLogInfo
															.getHost_tran_sn(),
													hostMessageLogInfo
															.getTeller_id(),
													hostMessageLogInfo
															.getHost_tran_date(),
													hostMessageLogInfo
															.getHost_tran_code(),
													vatFee, serviceFee);
								} else if (refTranCode.equals(SIBS_020)
										|| refTranCode.equals(SIBS_021)) {// CA->SA
									amt = CoreBankUtils
											.convertEB2CBNumeric(amt);
									serviceFee = CoreBankUtils
											.convertEB2CBNumeric(serviceFee);
									vatFee = CoreBankUtils
											.convertEB2CBNumeric(vatFee);
									String totalFee = CoreBankUtils
											.convertEB2CBNumeric(orgFee);
									String sendMSG = buildCASAMessage(
											hostMessageLogInfo.getWs_ip(),
											hostMessageLogInfo
													.getHost_tran_date(),
											rollout_branch_no,
											hostMessageLogInfo.getTeller_id(),
											hostMessageLogInfo.getApprover_id(),
											hostMessageLogInfo
													.getHost_tran_sn(),
											rollout_acct_no, bnfc_acct_no, amt,
											serviceFee, vatFee, totalFee,
											remark, hostMessageLogInfo
													.getHost_tran_code());
									this.retMessage = as400Port
											.hostMessageSending(
													hostMessageLogInfo
															.getRef_channel(),
													sendMSG);
								} else if (refTranCode.equals(SIBS_037)
										|| refTranCode.equals(SIBS_038)) {// SA->CA
									amt = CoreBankUtils
											.convertEB2CBNumeric(amt);
									serviceFee = CoreBankUtils
											.convertEB2CBNumeric(serviceFee);
									vatFee = CoreBankUtils
											.convertEB2CBNumeric(vatFee);
									String totalFee = CoreBankUtils
											.convertEB2CBNumeric(orgFee);
									String sendMSG = buildSACAMessage(
											hostMessageLogInfo.getWs_ip(),
											hostMessageLogInfo
													.getHost_tran_date(),
											rollout_branch_no,
											hostMessageLogInfo.getTeller_id(),
											hostMessageLogInfo.getApprover_id(),
											hostMessageLogInfo
													.getHost_tran_sn(),
											rollout_acct_no, bnfc_acct_no, amt,
											serviceFee, vatFee, totalFee,
											remark, hostMessageLogInfo
													.getHost_tran_code());
									this.retMessage = as400Port
											.hostMessageSending(
													hostMessageLogInfo
															.getRef_channel(),
													sendMSG);
								} else if (refTranCode.equals(SIBS_039)
										|| refTranCode.equals(SIBS_040)) {// SA->SA
									amt = CoreBankUtils
											.convertEB2CBNumeric(amt);
									serviceFee = CoreBankUtils
											.convertEB2CBNumeric(serviceFee);
									vatFee = CoreBankUtils
											.convertEB2CBNumeric(vatFee);
									String totalFee = CoreBankUtils
											.convertEB2CBNumeric(orgFee);
									String sendMSG = buildSASAMessage(
											hostMessageLogInfo.getWs_ip(),
											hostMessageLogInfo
													.getHost_tran_date(),
											rollout_branch_no,
											hostMessageLogInfo.getTeller_id(),
											hostMessageLogInfo.getApprover_id(),
											hostMessageLogInfo
													.getHost_tran_sn(),
											rollout_acct_no, bnfc_acct_no, amt,
											serviceFee, vatFee, totalFee,
											remark, hostMessageLogInfo
													.getHost_tran_code());
									this.retMessage = as400Port
											.hostMessageSending(
													hostMessageLogInfo
															.getRef_channel(),
													sendMSG);
								}// ngocnv

								else if (refTranCode.equals(SIBS_3603)) {// CA->FD

									amt = CoreBankUtils
											.convertEB2CBNumeric(amt);
									serviceFee = CoreBankUtils
											.convertEB2CBNumeric(serviceFee);
									vatFee = CoreBankUtils
											.convertEB2CBNumeric(vatFee);
									String totalFee = CoreBankUtils
											.convertEB2CBNumeric(orgFee);

									String sendMSG = buildTransferCAtoFDMessage(
											hostMessageLogInfo.getWs_ip(),
											hostMessageLogInfo.getTeller_id(),
											hostMessageLogInfo
													.getHost_tran_sn(),
											hostMessageLogInfo
													.getHost_tran_code(),
											rollout_acct_no, bnfc_acct_no, amt,
											totalFee, remark,
											hostMessageLogInfo
													.getHost_tran_date(),
											rollout_branch_no, vatFee);

									this.retMessage = as400Port
											.hostMessageSending(
													hostMessageLogInfo
															.getRef_channel(),
													sendMSG);

									// check errCode
									if (retMessage != null
											&& "79".equals(retMessage
													.getErrCode())) {

										String reSendMSG = reBuildTransferCAtoFDMessage(
												hostMessageLogInfo.getWs_ip(),
												hostMessageLogInfo
														.getTeller_id(),
												hostMessageLogInfo
														.getHost_tran_sn(),
												hostMessageLogInfo
														.getHost_tran_code(),
												rollout_acct_no, bnfc_acct_no,
												amt, totalFee, remark,
												hostMessageLogInfo
														.getHost_tran_date(),
												rollout_branch_no, vatFee);

										this.retMessage = as400Port
												.hostMessageSending(
														hostMessageLogInfo
																.getRef_channel(),
														reSendMSG);
									}

								}
								if (retMessage != null) {
									if (retMessage.getErrCode() != null) {
										tempErrorInfo = CachedParam
												.mappingErrorCode(
														retMessage.getErrCode(),
														retMessage
																.getDescription(),
														Global.MESSAGE_TYPE_ABCS);
									} else {
										tempErrorInfo = CachedParam
												.mappingErrorCode(
														String.valueOf(Global.ERR_SYSTEM_OK),
														Global.ERR_SYSTEM_OK_MSG,
														Global.MESSAGE_TYPE_ABCS);
									}
									if (this.retMessage.getArrString() != null) {
										hostResponseMessage = AppUtils
												.convertArrayToString(this.retMessage
														.getArrString());
									}
								} else {
									tempErrorInfo
											.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
									tempErrorInfo
											.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
								}
							} catch (RemoteException e) {
								tempErrorInfo
										.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
								tempErrorInfo.setErrorMessage(e.getMessage());
							}
							hostRequestMessage = "[channel:"
									+ hostMessageLogInfo.getRef_channel() + ","
									+ "branchCode:" + rollout_branch_no + ","
									+ "creditAccount:" + bnfc_acct_no + ","
									+ "creditAmount:" + amt + ","
									+ "creditCurrency:" + bnfc_acct_ccy + ","
									+ "creditRate:" + bnfc_sell_rate + ","
									+ "debitAccount:" + rollout_acct_no + ","
									+ "debitAmount:" + amt + ","
									+ "debitCurrency:" + rollout_acct_ccy + ","
									+ "debitRate:" + rollout_buy_rate + ","
									+ "manager:"
									+ hostMessageLogInfo.getApprover_id() + ","
									+ "description:" + remark + ","
									+ "sequence:"
									+ hostMessageLogInfo.getHost_tran_sn()
									+ "," + "teller:"
									+ hostMessageLogInfo.getTeller_id() + ","
									+ "transDate:"
									+ hostMessageLogInfo.getHost_tran_date()
									+ "," + "transCode:"
									+ hostMessageLogInfo.getHost_tran_code()
									+ "," + "vatFee:" + vatFee + ","
									+ "serviceFee:" + serviceFee + "]";
							this.hostMessageLogInfo.setReceive_time(AppUtils
									.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
						}
					} else {// inter-bank
						if (cif_no.length() == 0) {
							tempErrorInfo
									.setErrorCode(Global.ERR_UDF_TRANSFER_CIF_NO_NOT_VALID_OR_MISSING);
							tempErrorInfo
									.setErrorMessage("ERR_UDF_TRANSFER_CIF_NO_NOT_VALID_OR_MISSING");
						}

						if (cert_code.length() == 0) {
							tempErrorInfo
									.setErrorCode(Global.ERR_UDF_CERT_CODE_NOT_VALID_OR_MISSING);
							tempErrorInfo
									.setErrorMessage("ERR_UDF_CERT_CODE_NOT_VALID_OR_MISSING");
						}

						// // TODO :Remove temporary
						// if (rollout_acct_name.length() == 0) {
						// tempErrorInfo.setErrorCode(Global.ERR_UDF_TRANSFER_CIF_NAME_NOT_VALID_OR_MISSING);
						// tempErrorInfo.setErrorMessage("ERR_UDF_TRANSFER_CIF_NAME_NOT_VALID_OR_MISSING");
						// }

						// if (bnfc_branch_id.length() == 0) {
						// tempErrorInfo.setErrorCode(Global.ERR_UDF_TRANSFER_BANK_ID_NOT_VALID_OR_MISSING);
						// tempErrorInfo.setErrorMessage("ERR_UDF_TRANSFER_BANK_ID_NOT_VALID_OR_MISSING");
						// }

						if (GLAccount.length() == 0) {
							GLAccount = CachedParam
									.getSystemParam(Global.PARAM_GL_INTER_BANK_TRANSFER);
						}

						// TODO : Kient tinh VAT fee
						String vatFee = String.valueOf(new BigDecimal(orgFee)
								.divide(new BigDecimal(Global.BASED_VAT_RATE),
										0, RoundingMode.HALF_UP));
						String serviceFee = String.valueOf(new BigDecimal(
								orgFee).subtract(new BigDecimal(vatFee))
								.longValue());
						// ---end---

						String toAccount = bnfc_acct_no;
						String toName = bnfc_acct_name;// "";
						String toId = "";
						String toAddress = bnfc_bank_addr;
						String toIdIssueDate = "";
						String toIdIssuePlace = "";

						if (bnfc_acct_name.length() > 70
								|| bnfc_acct_name.length() < 0) {
							tempErrorInfo
									.setErrorCode(Global.ERR_UDF_TRANSFER_RECEIVE_NAME_NOT_VALID_OR_MISSING);
							tempErrorInfo
									.setErrorMessage("ERR_UDF_TRANSFER_RECEIVE_NAME_NOT_VALID_OR_MISSING");

						}
						// end--20110711

						if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
							this.hostMessageLogInfo.setSend_time(AppUtils
									.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
							rebuildTransactionInfo(SIBS_022, rollout_branch_no);
							String rm_ref_no = "";
							try {
								amt = CoreBankUtils.convertEB2CBNumeric(amt);
								serviceFee = CoreBankUtils
										.convertEB2CBNumeric(serviceFee);
								vatFee = CoreBankUtils
										.convertEB2CBNumeric(vatFee);
								String totalFee = CoreBankUtils
										.convertEB2CBNumeric(orgFee);

								String seqNumber = sysParamFacade
										.getRMSequence();
								String reversalDate = CoreBankUtils
										.convertCBDateReversal(hostMessageLogInfo
												.getHost_tran_date());
								rm_ref_no = rollout_branch_no + "2"
										+ reversalDate
										+ AppUtils.padLeft(seqNumber, 4, '0');

								String sendMessage = buildOL2Message(
										hostMessageLogInfo.getWs_ip(),
										hostMessageLogInfo.getHost_tran_date(),
										rollout_branch_no,
										hostMessageLogInfo.getTeller_id(),
										hostMessageLogInfo.getApprover_id(),
										hostMessageLogInfo.getHost_tran_sn(),
										rm_ref_no, GLAccount, cif_no,
										rollout_acct_name, cert_code,
										rollout_acct_no, toAccount, toName,
										toId, toAddress, toIdIssueDate,
										toIdIssuePlace, amt, serviceFee,
										vatFee, totalFee, remark,
										bnfc_branch_id, bnfc_branch_name,
										hostMessageLogInfo.getHost_tran_code());

								this.retMessage = as400Port.hostMessageSending(
										hostMessageLogInfo.getRef_channel(),
										sendMessage);

								if (retMessage != null) {
									if (retMessage.getErrCode() != null) {
										tempErrorInfo = CachedParam
												.mappingErrorCode(
														retMessage.getErrCode(),
														retMessage
																.getDescription(),
														Global.MESSAGE_TYPE_ABCS);
									} else {
										tempErrorInfo = CachedParam
												.mappingErrorCode(
														String.valueOf(Global.ERR_SYSTEM_OK),
														Global.ERR_SYSTEM_OK_MSG,
														Global.MESSAGE_TYPE_ABCS);
									}
									if (this.retMessage.getArrString() != null) {
										hostResponseMessage = AppUtils
												.convertArrayToString(this.retMessage
														.getArrString());
									}
								} else {
									tempErrorInfo
											.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
									tempErrorInfo
											.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
								}

							} catch (RemoteException e) {
								tempErrorInfo
										.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
								tempErrorInfo.setErrorMessage(e.getMessage());
							}
							hostRequestMessage = "[channel:"
									+ hostMessageLogInfo.getRef_channel() + ","
									+ "branchCode:" + rollout_branch_no + ","
									+ "teller:"
									+ hostMessageLogInfo.getTeller_id() + ","
									+ "approver:"
									+ hostMessageLogInfo.getApprover_id() + ","
									+ "core_sn:"
									+ hostMessageLogInfo.getHost_tran_sn()
									+ "," + "rm_ref_no:" + rm_ref_no + ","
									+ "tran_date:"
									+ hostMessageLogInfo.getHost_tran_date()
									+ "," + "rollout_acct_no:"
									+ rollout_acct_no + "," + "amt:" + amt
									+ "," + "cif_no:" + cif_no + ","
									+ "glAccount:" + GLAccount + ","
									+ "vatFee:" + vatFee + "," + "serviceFee:"
									+ serviceFee + "," + "remarks:" + remark
									+ "," + "cif_acct_name:" + cif_acct_name
									+ "," + "cert_code:" + cert_code + ","
									+ "toAccount:" + toAccount + ","
									+ "toName:" + toName + "," + "toId:" + toId
									+ "," + "toAddress:" + toAddress + ","
									+ "toIdIssueDate:" + toIdIssueDate + ","
									+ "toIdIssuePlace:" + toIdIssuePlace + ","
									+ "transCode:"
									+ hostMessageLogInfo.getHost_tran_code()
									+ "]";
							this.hostMessageLogInfo.setReceive_time(AppUtils
									.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
						}
					}
				} else {// error
					this.retErrorInfo
							.setErrorCode(tempErrorInfo.getErrorCode());
					this.retErrorInfo.setErrorMessage(tempErrorInfo
							.getErrorMessage());
				}

				// 3.Tao tung the field
				String xmlOuputPattern = "/message/body/list[@name='records']/entity[@name='"
						+ i
						+ "']/?$field[set-value('%s')][?@name[set-value('%s')]]";
				ElementUtils.setXMLElementValue(outMessage, xmlOuputPattern,
						tran_sn, "tran_sn");
				ElementUtils.setXMLElementValue(outMessage, xmlOuputPattern,
						hostMessageLogInfo.getHost_tran_sn(), "core_sn");
				ElementUtils.setXMLElementValue(outMessage, xmlOuputPattern,
						String.valueOf(tempErrorInfo.getErrorCode()), "result");

				// Tang so luong record thanh cong
				if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
					iSuccesfulRecord += 1;
				} else {
					// ghi nhan record cuoi cung bi loi
					lastRetErrorInfo.setErrorCode(tempErrorInfo.getErrorCode());
					lastRetErrorInfo.setErrorMessage(tempErrorInfo
							.getErrorMessage());
				}
				this.hostMessageLogInfo.setRef_cif_acct(rollout_acct_no);
				this.hostMessageLogInfo.setRef_amount(amt);
				this.hostMessageLogInfo.setTran_sn(tran_sn);// Update field for
															// batch transfer
				this.hostMessageLogInfo.setResp_code(String
						.valueOf(tempErrorInfo.getErrorCode()));
				this.hostMessageLogInfo.setResp_msg(tempErrorInfo
						.getErrorMessage());
				this.hostMessageLogInfo.setHostRequestMsg("["
						+ hostRequestMessage + "]");
				this.hostMessageLogInfo.setHostResponseMsg("["
						+ hostResponseMessage + "]");
				this.arrRetHOSTMessageLogInfo
						.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());

				iTotalRecord += 1;
			}// end-for

			if (iSuccesfulRecord == 0) {// all-record-fail
				this.retErrorInfo.setErrorCode(lastRetErrorInfo.getErrorCode());
				this.retErrorInfo.setErrorMessage(lastRetErrorInfo
						.getErrorMessage());
			} else if ((0 < iSuccesfulRecord)
					&& (iSuccesfulRecord < iTotalRecord)) {// apart-done
				this.retErrorInfo
						.setErrorCode(ERR_CM_APART_OF_TRAN_SUCESSFULLY);
				this.retErrorInfo
						.setErrorMessage("ERR_CM_APART_OF_TRAN_SUCESSFULLY");
			} else if ((0 < iSuccesfulRecord)
					&& (iSuccesfulRecord == iTotalRecord)) {// all-record-done
				this.retErrorInfo.setErrorCode(ERR_SYSTEM_OK);
				this.retErrorInfo.setErrorMessage("ERR_SYSTEM_OK");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".fundTransfer()\n")
					.append("Error message:").append(ex.getMessage() + "\n"));
			this.retErrorInfo
					.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (OS006)Settlement for FD account
	 */
	private void settlementFDAccount() {
		try {

			String xpathInput = "/message/body/field[@name='%s']";
			String receipt_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "receipt_branch_no"));
			String receipt_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "receipt_no"));
			String receipt_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "receipt_ccy"));
			String receipt_sell_rate = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "receipt_sell_rate"));
			String rollin_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollin_branch_no"));
			String rollin_acc_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollin_acc_no"));
			String rollin_acc_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollin_acc_type"));
			String rollin_acc_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollin_acc_ccy"));
			String rollin_buy_rate = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollin_buy_rate"));
			// Kienvt lay amt tu message gui vao core
			String amt = "0";// CoreBankUtils.roundCBNumberic(ElementUtils.getXMLElementInStringValue(inMessage,
			// String.format(xpathInput, "amt")));
			String fee = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "fee")));
			String remark = AppUtils.convertToVietnamesNoSign(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "remark")));

			if (receipt_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (receipt_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_FDRECEIPT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_FDRECEIPT_NOT_VALID_OR_MISSING");
				return;
			} else {

				// TODO: check or not
				receipt_no = CoreBankUtils.correctAccountNumber(receipt_no);
				// Check khong co so thi moi duoc tat toan
				// if (!receipt_no.substring(3,
				// 4).equals(FD_ACCOUNT_NO_PASSBOOK)) {
				// this.retErrorInfo.setErrorCode(Global.ERR_UDF_FDRECEIPT_NOT_VALID_OR_MISSING);
				// this.retErrorInfo.setErrorMessage("ERR_UDF_FDRECEIPT_NOT_VALID_OR_MISSING");
				// return;
				// }
			}
			if (receipt_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (receipt_sell_rate.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SELL_RATE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SELL_RATE_AMOUT_NOT_VALID_OR_MISSING");
				return;
			}

			if (rollin_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollin_acc_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}

			if (rollin_acc_type.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollin_acc_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollin_buy_rate.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING");
				return;
			}
			// if (!CoreBankUtils.isValidTransactionAmount(amt)) {
			// this.retErrorInfo.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
			// this.retErrorInfo.setErrorMessage("ERR_UDF_TRANSACTION_AMOUT_NOT_VALID_OR_MISSING");
			// return;
			// }

			// 1.Lay thong tin ve fdreceipt
			int startPos = 41;
			int recordLength = 230;
			rebuildTransactionInfo(Global.SIBS_036,
					Global.APP_DEFAULT_BRANCH_CODE);
			ABCS_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
			ABCS_TEMPLATE[18] = "BBHTLMONEYFNC";
			ABCS_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
			ABCS_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
			ABCS_TEMPLATE[34] = hostMessageLogInfo.getWs_ip();
			ABCS_TEMPLATE[42] = hostMessageLogInfo.getTeller_id();
			ABCS_TEMPLATE[43] = hostMessageLogInfo.getHost_tran_sn();
			ABCS_TEMPLATE[49] = hostMessageLogInfo.getHost_tran_date();
			ABCS_TEMPLATE[53] = hostMessageLogInfo.getApprover_id();
			ABCS_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code(); // hostTranCode;
			ABCS_TEMPLATE[48] = hostMessageLogInfo.getHost_tran_code(); // hostTranCode;
			ABCS_TEMPLATE[69] = receipt_no;
			ABCS_TEMPLATE[94] = receipt_ccy;

			String hostSendMessage = AppUtils
					.convertArrayToString(ABCS_TEMPLATE);
			String hostReceiveMessage = "";
			String serial = "";
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				retMessage = as400Port.hostMessageSending(
						hostMessageLogInfo.getRef_channel(), hostSendMessage);
			} catch (RemoteException ex) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
				this.retErrorInfo.setErrorMessage(ex.toString());
			}
			ArrayList<String[]> retArrOfResult = null;
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			if (retMessage != null) {
				if (retMessage.getErrCode() == null) {
					retArrOfResult = AppUtils.getListOfResponseRow(
							retMessage.getArrString(), startPos, recordLength);
					// Kienvt : check length
					if (retArrOfResult.get(0).length > 200) {
						serial = (String) retArrOfResult.get(0)[171];
						amt = (String) retArrOfResult.get(0)[173];
						if (amt != null && amt.length() > 0) {
							amt = amt.substring(0, amt.indexOf("."));
						}
						if (amt == null || amt.length() == 0) {
							amt = "0";
						}
					}
				} else {
					this.retErrorInfo = CachedParam.mappingErrorCode(
							retMessage.getErrCode(),
							retMessage.getDescription(),
							Global.MESSAGE_TYPE_ABCS);
				}
				if (retMessage.getArrString() != null)
					hostReceiveMessage = AppUtils
							.convertArrayToString(retMessage.getArrString());
			} else {
				// TODO: kienvt bo xung them
				retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
				retErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
			}

			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + hostSendMessage
					+ "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + hostReceiveMessage
					+ "]");
			this.arrRetHOSTMessageLogInfo
					.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());

			if (retErrorInfo.getErrorCode() == 0) { // Check amt
				if (!CoreBankUtils.isValidTransactionAmount(amt)) {
					this.retErrorInfo
							.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
					this.retErrorInfo
							.setErrorMessage("ERR_UDF_TRANSACTION_AMOUT_NOT_VALID_OR_MISSING");
					return;
				}

				// 2.Settlement
				String strRequestMsg = "";
				String strResponseMsg = "";

				rebuildTransactionInfo(SIBS_016, receipt_branch_no);
				ABCS_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
				ABCS_TEMPLATE[18] = "BBHTLMONEYFNC";
				ABCS_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
				ABCS_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
				ABCS_TEMPLATE[34] = hostMessageLogInfo.getWs_ip();
				ABCS_TEMPLATE[42] = hostMessageLogInfo.getTeller_id();
				ABCS_TEMPLATE[43] = hostMessageLogInfo.getHost_tran_sn();
				ABCS_TEMPLATE[49] = hostMessageLogInfo.getHost_tran_date();
				ABCS_TEMPLATE[53] = hostMessageLogInfo.getApprover_id();
				ABCS_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code(); // hostTranCode;
				ABCS_TEMPLATE[48] = hostMessageLogInfo.getHost_tran_code(); // hostTranCode;

				// "00000000000";
				// Kienvt : them vao tinh fee
				double dblInputFee = Double.parseDouble(fee);
				double dblVATCharge = dblInputFee
						/ Double.parseDouble(Global.BASED_VAT_RATE);
				fee = String.valueOf(Math.round(Double.parseDouble(fee)
						- dblVATCharge));
				// --end-----

				ABCS_TEMPLATE[54] = rollin_branch_no;
				ABCS_TEMPLATE[66] = CoreBankUtils.convertEB2CBNumeric(amt);
				ABCS_TEMPLATE[69] = receipt_no;
				ABCS_TEMPLATE[72] = rollin_acc_no;

				// TODO Kienvt: khi co fee nho cat sau dau phay
				ABCS_TEMPLATE[81] = CoreBankUtils.convertEB2CBNumeric(String
						.valueOf(dblInputFee + dblVATCharge));
				ABCS_TEMPLATE[88] = CoreBankUtils
						.convertEB2CBNumeric(rollin_buy_rate);
				ABCS_TEMPLATE[89] = CoreBankUtils
						.convertEB2CBNumeric(rollin_buy_rate);
				ABCS_TEMPLATE[90] = CoreBankUtils
						.convertEB2CBNumeric(receipt_sell_rate);
				ABCS_TEMPLATE[91] = CoreBankUtils
						.convertEB2CBNumeric(receipt_sell_rate);

				ABCS_TEMPLATE[83] = CoreBankUtils.convertEB2CBNumeric(amt);
				ABCS_TEMPLATE[84] = serial;// "00000000000";// serial

				ABCS_TEMPLATE[94] = Global.BASED_CCYCD;
				ABCS_TEMPLATE[106] = receipt_ccy;
				ABCS_TEMPLATE[107] = receipt_ccy;
				ABCS_TEMPLATE[108] = rollin_acc_ccy;
				ABCS_TEMPLATE[109] = rollin_acc_ccy;

				ABCS_TEMPLATE[236] = rollin_acc_no;
				ABCS_TEMPLATE[238] = CoreBankUtils.convertEB2CBNumeric(String
						.valueOf(dblInputFee + dblVATCharge));

				ABCS_TEMPLATE[241] = CoreBankUtils.convertEB2CBNumeric(fee);
				ABCS_TEMPLATE[242] = CoreBankUtils.convertEB2CBNumeric(String
						.valueOf(dblVATCharge));
				ABCS_TEMPLATE[250] = CoreBankUtils.convertEB2CBNumeric(amt);
				ABCS_TEMPLATE[281] = remark;

				strRequestMsg = AppUtils.convertArrayToString(ABCS_TEMPLATE);

				this.hostMessageLogInfo.setSend_time(AppUtils
						.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
				try {
					// TODO: check FD account settemtnet
					retMessage = as400Port.hostMessageSending(
							hostMessageLogInfo.getRef_channel(), strRequestMsg);

					if (retMessage != null) {
						if (retMessage.getErrCode() != null) {
							this.retErrorInfo = CachedParam.mappingErrorCode(
									retMessage.getErrCode(),
									retMessage.getDescription(),
									Global.MESSAGE_TYPE_ABCS);
						} else {
							this.retErrorInfo = CachedParam.mappingErrorCode(
									String.valueOf(Global.ERR_SYSTEM_OK),
									Global.ERR_SYSTEM_OK_MSG,
									Global.MESSAGE_TYPE_ABCS);
						}
						strResponseMsg = AppUtils
								.convertArrayToString(this.retMessage
										.getArrString());
					} else {
						// TODO: kienvt bo xung them
						retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
						retErrorInfo
								.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
					}

				} catch (Exception ex) {
					this.retErrorInfo
							.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
					this.retErrorInfo.setErrorMessage(ex.toString());
				}
				this.hostMessageLogInfo.setReceive_time(AppUtils
						.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
				this.hostMessageLogInfo.setRef_cif_acct(rollin_acc_no);
				this.hostMessageLogInfo.setRef_amount(amt);
				this.hostMessageLogInfo.setResp_code(String
						.valueOf(retErrorInfo.getErrorCode()));
				this.hostMessageLogInfo.setResp_msg(retErrorInfo
						.getErrorMessage());
				this.hostMessageLogInfo.setHostRequestMsg("[" + strRequestMsg
						+ "]");
				this.hostMessageLogInfo.setHostResponseMsg("[" + strResponseMsg
						+ "]");
				this.arrRetHOSTMessageLogInfo
						.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".settlementFDAccount()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (OS004)Transfer money to FD receipt(Only transfer from CA account)
	 * 
	 * Reference: SIBS_033
	 * 
	 * @version 1.0
	 * @author Vu Trung Kien
	 */
	private void transferToFDReceipt() {
		try {

			String xpathInput = "/message/body/field[@name='%s']";
			String receipt_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "receipt_branch_no"));
			String receipt_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "receipt_no"));
			String receipt_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "receipt_ccy"));
			String receipt_sell_rate = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "receipt_sell_rate"));
			String rollout_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_branch_no"));
			String rollout_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_acct_no"));
			String rollout_acct_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_acct_ccy"));
			String rollout_acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_acct_type"));
			String rollout_buy_rate = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xpathInput, "rollout_buy_rate"));
			String amt = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "amt")));
			String fee = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "fee")));
			String remark = AppUtils.convertToVietnamesNoSign(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xpathInput, "remark")));

			if (receipt_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (receipt_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_FDRECEIPT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_FDRECEIPT_NOT_VALID_OR_MISSING");
				return;
			}
			if (receipt_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (receipt_sell_rate.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SELL_RATE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SELL_RATE_AMOUT_NOT_VALID_OR_MISSING");
				return;
			}

			if (rollout_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}

			if (rollout_acct_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_TRANSACTION_AMOUT_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_acct_type.length() == 0
					|| CoreBankUtils.isValidAccountType(rollout_acct_type) == false) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING");
				return;
			}
			if (rollout_buy_rate.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING");
				return;
			}
			if (!CoreBankUtils.isValidTransactionAmount(amt)) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_TRANSACTION_AMOUT_NOT_VALID_OR_MISSING");
				return;
			}
			rebuildTransactionInfo(SIBS_017, receipt_branch_no);

			ABCS_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
			ABCS_TEMPLATE[18] = "BBHTLMONEYFNC";
			ABCS_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
			ABCS_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
			ABCS_TEMPLATE[34] = hostMessageLogInfo.getWs_ip();
			ABCS_TEMPLATE[42] = hostMessageLogInfo.getTeller_id();
			ABCS_TEMPLATE[43] = hostMessageLogInfo.getHost_tran_sn();
			ABCS_TEMPLATE[49] = hostMessageLogInfo.getHost_tran_date();
			ABCS_TEMPLATE[53] = hostMessageLogInfo.getApprover_id();
			ABCS_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code();// hostTranCode;
			ABCS_TEMPLATE[48] = hostMessageLogInfo.getHost_tran_code();

			// dump value
			ABCS_TEMPLATE[113] = "3";
			ABCS_TEMPLATE[127] = "1";
			ABCS_TEMPLATE[129] = "4";
			ABCS_TEMPLATE[130] = "2";
			ABCS_TEMPLATE[143] = "4";
			ABCS_TEMPLATE[144] = "4";
			ABCS_TEMPLATE[145] = "4";
			ABCS_TEMPLATE[151] = "1";
			ABCS_TEMPLATE[256] = "4";
			ABCS_TEMPLATE[257] = "4";
			ABCS_TEMPLATE[258] = "4";
			ABCS_TEMPLATE[265] = "2";
			ABCS_TEMPLATE[296] = "1";
			ABCS_TEMPLATE[297] = "1";

			double dblInputFee = Double.parseDouble(fee);
			double dblVATCharge = dblInputFee
					/ Double.parseDouble(Global.BASED_VAT_RATE);
			fee = String.valueOf(Math.round(Double.parseDouble(fee)
					- dblVATCharge));
			// --end-----

			ABCS_TEMPLATE[54] = receipt_branch_no;
			ABCS_TEMPLATE[66] = CoreBankUtils.convertEB2CBNumeric(amt);
			ABCS_TEMPLATE[69] = receipt_no;
			ABCS_TEMPLATE[72] = rollout_acct_no;
			ABCS_TEMPLATE[83] = CoreBankUtils.convertEB2CBNumeric(amt);
			ABCS_TEMPLATE[88] = rollout_buy_rate;
			ABCS_TEMPLATE[89] = receipt_sell_rate;
			ABCS_TEMPLATE[90] = rollout_buy_rate;
			ABCS_TEMPLATE[91] = rollout_buy_rate;
			ABCS_TEMPLATE[94] = Global.BASED_CCYCD;
			ABCS_TEMPLATE[106] = receipt_ccy;
			ABCS_TEMPLATE[107] = receipt_ccy;
			ABCS_TEMPLATE[108] = rollout_acct_ccy;
			ABCS_TEMPLATE[109] = rollout_acct_ccy;
			ABCS_TEMPLATE[241] = CoreBankUtils.convertEB2CBNumeric(fee);
			ABCS_TEMPLATE[242] = CoreBankUtils.convertEB2CBNumeric(String
					.valueOf(dblVATCharge));
			ABCS_TEMPLATE[250] = CoreBankUtils.convertEB2CBNumeric(amt);
			ABCS_TEMPLATE[281] = remark;

			String hostSendMessage = AppUtils
					.convertArrayToString(ABCS_TEMPLATE);
			String hostReceiveMessage = "";

			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				retMessage = as400Port.hostMessageSending(
						hostMessageLogInfo.getRef_channel(), hostSendMessage);

				if (retMessage != null) {
					if (retMessage.getErrCode() != null) {
						this.retErrorInfo = CachedParam.mappingErrorCode(
								retMessage.getErrCode(),
								retMessage.getDescription(),
								Global.MESSAGE_TYPE_ABCS);
					} else {
						this.retErrorInfo = CachedParam.mappingErrorCode(
								String.valueOf(Global.ERR_SYSTEM_OK),
								Global.ERR_SYSTEM_OK_MSG,
								Global.MESSAGE_TYPE_ABCS);
					}
					hostReceiveMessage = AppUtils
							.convertArrayToString(this.retMessage
									.getArrString());
				} else {
					// TODO: kienvt bo xung them
					retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
					retErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
				}

			} catch (RemoteException ex) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
				this.retErrorInfo.setErrorMessage(ex.toString());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.hostMessageLogInfo.setRef_cif_acct(receipt_no);
			this.hostMessageLogInfo.setRef_amount(amt);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + hostSendMessage
					+ "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + hostReceiveMessage
					+ "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".transferToFDReceipt()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo
					.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * (FX002)Exchange money
	 * 
	 * @version 1.0
	 * @author Vu Trung Kien
	 */
	private void exchangeMoney() {
		try {

			String xmlInputPattern = "/message/body/field[@name='%s']";
			String sell_branch_no = ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xmlInputPattern, "sell_branch_no"));
			String sell_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "sell_acct_no"));
			String sell_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "sell_ccy"));
			String receipt_branch_no = ElementUtils.getXMLElementInStringValue(
					inMessage,
					String.format(xmlInputPattern, "receipt_branch_no"));
			String receipt_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage,
					String.format(xmlInputPattern, "receipt_acct_no"));
			String receipt_ccy = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "receipt_ccy"));
			String amt = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xmlInputPattern, "amt")));
			String fx_rate = CoreBankUtils.roundCBNumberic(ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xmlInputPattern, "fx_rate")));

			if (sell_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (sell_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (sell_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (fx_rate.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_SELL_RATE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_SELL_RATE_NOT_VALID_OR_MISSING");
				return;
			}
			if (receipt_branch_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING");
				return;
			}
			if (receipt_acct_no.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING");
				return;
			}
			if (receipt_ccy.length() == 0) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING");
				return;
			}

			if (!CoreBankUtils.isValidTransactionAmount(amt)) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING");
				return;
			}
			if (!CoreBankUtils.isValidTransactionAmount(fx_rate)) {
				this.retErrorInfo
						.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
				this.retErrorInfo
						.setErrorMessage("ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING");
				return;
			}
			amt = String.valueOf(AppUtils.isValidNumber(amt) == true ? amt
					: "0");
			fx_rate = String
					.valueOf(AppUtils.isValidNumber(fx_rate) == true ? fx_rate
							: "0");
			// String hostTranCode = "";
			if (sell_branch_no.equals(receipt_branch_no)) {
				rebuildTransactionInfo(SIBS_025, receipt_branch_no);
			} else {
				rebuildTransactionInfo(SIBS_026, receipt_branch_no);
			}
			String totalAMT = String.valueOf(java.lang.Math.round(Double
					.valueOf(amt)
					* java.lang.Math.round(Double.valueOf(fx_rate)))) + "00";
			ABCS_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
			ABCS_TEMPLATE[18] = "BBHTLMONEYFNC";
			ABCS_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
			ABCS_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
			ABCS_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code();
			ABCS_TEMPLATE[34] = hostMessageLogInfo.getWs_ip();
			ABCS_TEMPLATE[42] = hostMessageLogInfo.getTeller_id();
			ABCS_TEMPLATE[43] = hostMessageLogInfo.getHost_tran_sn();
			ABCS_TEMPLATE[48] = hostMessageLogInfo.getHost_tran_code();//
			ABCS_TEMPLATE[49] = hostMessageLogInfo.getHost_tran_date();
			ABCS_TEMPLATE[53] = hostMessageLogInfo.getApprover_id();
			ABCS_TEMPLATE[54] = sell_branch_no;
			ABCS_TEMPLATE[64] = sell_acct_no;
			ABCS_TEMPLATE[66] = totalAMT;
			ABCS_TEMPLATE[72] = receipt_acct_no;
			ABCS_TEMPLATE[83] = CoreBankUtils.convertEB2CBNumeric(amt);
			ABCS_TEMPLATE[88] = CoreBankUtils.convertEB2CBNumeric(fx_rate);

			ABCS_TEMPLATE[80] = "000";
			ABCS_TEMPLATE[81] = "000";
			ABCS_TEMPLATE[82] = "000";
			ABCS_TEMPLATE[113] = "3";
			ABCS_TEMPLATE[127] = "1";
			ABCS_TEMPLATE[128] = "4";
			ABCS_TEMPLATE[129] = "4";
			ABCS_TEMPLATE[130] = "2";
			ABCS_TEMPLATE[143] = "4";
			ABCS_TEMPLATE[144] = "4";
			ABCS_TEMPLATE[145] = "4";
			ABCS_TEMPLATE[148] = "5";
			ABCS_TEMPLATE[151] = "1";
			ABCS_TEMPLATE[239] = "000";
			ABCS_TEMPLATE[241] = "000";
			ABCS_TEMPLATE[242] = "000";
			ABCS_TEMPLATE[243] = "000";
			ABCS_TEMPLATE[254] = "5";
			ABCS_TEMPLATE[256] = "4";
			ABCS_TEMPLATE[257] = "4";
			ABCS_TEMPLATE[258] = "4";

			ABCS_TEMPLATE[89] = BASED_CCYCD_RATE;
			ABCS_TEMPLATE[94] = BASED_CCYCD;
			ABCS_TEMPLATE[106] = sell_ccy;
			ABCS_TEMPLATE[107] = receipt_ccy;
			ABCS_TEMPLATE[108] = receipt_ccy;
			ABCS_TEMPLATE[109] = receipt_ccy;
			ABCS_TEMPLATE[285] = totalAMT;
			ABCS_TEMPLATE[286] = "000";
			ABCS_TEMPLATE[287] = "000";
			ABCS_TEMPLATE[295] = "3";
			ABCS_TEMPLATE[296] = "1";
			ABCS_TEMPLATE[297] = "1";

			String hostSendMessage = AppUtils
					.convertArrayToString(ABCS_TEMPLATE);
			String hostReceiveMessage = "";
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.retMessage = as400Port.hostMessageSending(
					hostMessageLogInfo.getRef_channel(), hostSendMessage);
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (retMessage != null) {
				hostReceiveMessage = AppUtils.convertArrayToString(retMessage
						.getArrString());
				if (retMessage.getErrCode() == null) {
				} else {
					this.retErrorInfo.setErrorCode(Global.ERR_MBASE_START);
					this.retErrorInfo.setErrorMessage(retMessage
							.getDescription());
				}
			}
			this.hostMessageLogInfo.setRef_cif_acct(receipt_acct_no);
			this.hostMessageLogInfo.setRef_amount(amt);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + hostSendMessage
					+ "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + hostReceiveMessage
					+ "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".exchangeMoney()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * 
	 * @param hostIP
	 * @param transactionDate
	 * @param branchCode
	 * @param tellerId
	 * @param approverId
	 * @param refId
	 * @param tranCode
	 * @param autoreNew
	 * @param fd_account_no
	 * @param fd_receipt_no
	 * @param fd_receipt_ccy
	 * @param product_code
	 * @param amount
	 * @param remark
	 * @param principal_account
	 * @param interest_account
	 * @return
	 */
	private String buildCreateFDReceipt(String hostIP, String transactionDate,
			String branchCode, String tellerId, String approverId,
			String refId, String tranCode, String autoreNew,
			String fd_account_no, String fd_receipt_no, String fd_receipt_ccy,
			String product_code, String amount, String remark,
			String principal_account, String interest_account) {
		String message = "";
		if (autoreNew.equals("Y")) {

			message = "*LINX|"
					+ hostIP
					+ "||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||"
					+ tellerId
					+ "|"
					+ hostIP
					+ "|"
					+ tranCode
					+ "||||3843|*MOSA||"
					+ hostIP
					+ "|||||T9999|||"
					+ tellerId
					+ "|"
					+ refId
					+ "|N||||"
					+ tranCode
					+ "|"
					+ transactionDate
					+ "||VD|IB||"
					+ branchCode
					+ "|0|1|N|N|N|N|N|N|N|"
					+ fd_account_no
					+ "|||"
					+ amount
					+ "|"
					+ transactionDate
					+ "|"
					+ fd_receipt_no
					+ "||||||||||0000000000|||||||||||||||"
					+ fd_receipt_ccy
					+ "||"
					+ product_code
					+ "||||Y|A|||||||||||||1||||||||||||||||1|1||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
					+ remark + "|||||||||||||||||||||||";
		} else {
			if (autoreNew.equals("N")) {
				message = "*LINX|"
						+ hostIP
						+ "||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||"
						+ tellerId
						+ "|"
						+ hostIP
						+ "|"
						+ tranCode
						+ "||||3843|*MOSA||"
						+ hostIP
						+ "|||||T9999|||"
						+ tellerId
						+ "|"
						+ refId
						+ "|N||||"
						+ tranCode
						+ "|"
						+ transactionDate
						+ "||VD|IB||"
						+ branchCode
						+ "|0|1|N|N|N|N|N|N|N|"
						+ fd_account_no
						+ "|||"
						+ amount
						+ "|"
						+ transactionDate
						+ "|"
						+ fd_receipt_no
						+ "||||||||||0000000000||||"
						+ principal_account
						+ "|"
						+ interest_account
						+ "||||||||||"
						+ fd_receipt_ccy
						+ "||"
						+ product_code
						+ "||||N|D|D|D|D||||||||||1||||||||||||||||1|1||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
						+ remark + "|||||||||||||||||||||||";
			}
			else
			{
				if (autoreNew.equals("Z")) {
					message = "*LINX|"
						+ hostIP
						+ "||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||"
						+ tellerId
						+ "|"
						+ hostIP
						+ "|"
						+ tranCode
						+ "||||3843|*MOSA||"
						+ hostIP
						+ "|||||T9999|||"
						+ tellerId
						+ "|"
						+ refId
						+ "|N||||"
						+ tranCode
						+ "|"
						+ transactionDate
						+ "||VD|IB||"
						+ branchCode
						+ "|0|1|N|N|N|N|N|N|N|"
						+ fd_account_no
						+ "|||"
						+ amount
						+ "|"
						+ transactionDate
						+ "|"
						+ fd_receipt_no
						+ "||||||||||0000000000|||||"+interest_account+ "||||||||||"
						+ fd_receipt_ccy
						+ "||"
						+ product_code
						+ "||||Y|D||D|||||||||||1||||||||||||||||1|1||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
						+ remark + "|||||||||||||||||||||||";
				}
			}
		}

		return message;
	}

	/**
	 * Message for inter-bank transfer,using OL2.
	 * 
	 * @param hostIP
	 * @param transactionDate
	 * @param branchCode
	 * @param tellerId
	 * @param approverId
	 * @param refId
	 * @param rm_ref_no
	 * @param glAccount
	 * @param fromCif
	 * @param fromName
	 * @param fromId
	 * @param fromAccount
	 * @param toAccount
	 * @param toName
	 * @param toId
	 * @param toAddress
	 * @param toIdIssueDate
	 * @param toIdIssuePlace
	 * @param amount
	 * @param serviceFee
	 * @param vatFee
	 * @param totalFee
	 * @param remarks
	 * @param bank_id
	 * @param bank_name
	 * @param tranCode
	 * @return
	 */

	private String buildOL2Message(String hostIP, String transactionDate,
			String branchCode, String tellerId, String approverId,
			String refId, String rm_ref_no, String glAccount, String fromCif,
			String fromName, String fromId, String fromAccount,
			String toAccount, String toName, String toId, String toAddress,
			String toIdIssueDate, String toIdIssuePlace, String amount,
			String serviceFee, String vatFee, String totalFee, String remarks,
			String bank_id, String bank_name, String tranCode) {

		// Kiem tra length cat bot cac ky tu thua cua cac truong co trong phan
		// remark
		fromName = AppUtils.convertToVietnamesNoSign(AppUtils
				.subStringBySpecLength(fromName, 41));
		toId = AppUtils.subStringBySpecLength(toId, 196);
		glAccount = AppUtils.subStringBySpecLength(glAccount, 19);
		toName = AppUtils.convertToVietnamesNoSign(AppUtils
				.subStringBySpecLength(toName, 70));
		bank_id = AppUtils.subStringBySpecLength(bank_id, 43);
		bank_name = AppUtils.convertToVietnamesNoSign(AppUtils
				.subStringBySpecLength(bank_name, 40));
		toAccount = AppUtils.subStringBySpecLength(toAccount, 20);
		toAddress = AppUtils.convertToVietnamesNoSign(AppUtils
				.subStringBySpecLength(toAddress, 113));
		toIdIssueDate = AppUtils.subStringBySpecLength(toIdIssueDate, 144);
		remarks = "(RmNo: " + rm_ref_no + ") " + remarks;
		remarks = AppUtils.convertToVietnamesNoSign(AppUtils
				.subStringBySpecLength(remarks, 180));

		String message = "*LINX|"
				+ hostIP
				+ "||||213|42|0200||ABCS|*LINX||"
				+ "|||||BBHTLMONEYFNC|||||||"
				+ tellerId
				+ "|al|"
				+ tranCode
				+ "||||3843|*MOSA|"
				+ "|"
				+ hostIP
				+ "|||||T9999|||"
				+ tellerId
				+ "|"
				+ refId
				+ "|N||||"
				+ tranCode
				+ "|"
				+ transactionDate
				+ "||VD|al|"
				+ approverId
				+ "|"
				+ branchCode
				+ "|0|1|N|N|N|N|N|N|N|"
				+ rm_ref_no
				+ "||"
				+ amount
				+ "|||||"
				+ AppUtils.padLeft(glAccount, 19, '0')
				+ "|"
				+ fromAccount
				+ "|||"
				+ "||||||"
				+ totalFee
				+ "||"
				+ amount
				+ "||||||10000000|10000000||10000000||VND|"
				+ fromCif
				+ "|||||"
				+ "||||||VND|VND|VND|VND|||||||||||||||||||||||||||||||||||||||||||||||||||"
				+ "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||VND||||||||||||||"
				+ fromAccount + "||" + totalFee + "|||" + serviceFee + "|"
				+ vatFee + "||||||||" + amount
				+ "||||||||||||||||||||||||||||||" + "|" + remarks
				+ "||||||||||||||||||||||";

		String toName1 = "";
		String toName2 = "";
		String[] arrName = toName.split(" ");
		for (int i = 0; i < arrName.length; i++) {
			if (toName1.length() + (arrName[i].trim() + " ").length() < 40) {
				toName1 += arrName[i].trim() + " ";
			} else
				break;
		}
		toName1 = AppUtils.convertToVietnamesNoSign(AppUtils
				.subStringBySpecLength(toName1, 40));
		int remainLength = toName.length() - toName1.length();
		if (remainLength > 0) {
			// Neu so ky tu con lai > 30 ky tu
			// thi chap nhan truong hop ghep ten bi lech
			if (remainLength > 30) {
				toName1 = toName.substring(0, 40);
				toName2 = toName.substring(toName1.length(), toName.length());
			} else {
				toName2 = toName.substring(toName1.length(), toName.length());
			}

		}
		toName2 = AppUtils.convertToVietnamesNoSign(AppUtils
				.subStringBySpecLength(toName2, 30));

		message += "RM        "
				+ "C                            "
				+ "OL2                                                                                                                 "
				+ AppUtils.padRight("", 60, ' ')
				+ "SMSBK       "
				+ "VND                                                  "
				+ AppUtils.padRight("C" + fromName.toUpperCase(), 41, ' ')
				+ AppUtils.padRight(toId, 196, ' ')
				+ "T"
				+ AppUtils.padLeft(glAccount, 19, '0')
				+ AppUtils.padRight(toAccount.toUpperCase(), 20, ' ')
				+ AppUtils.padRight(toName1.toUpperCase(), 40, ' ')
				+ AppUtils.padRight(bank_id, 43, ' ') // bnfc_bank_id
				+ AppUtils.padRight(bank_name, 40, ' ')
				+ AppUtils.padRight(toAddress.toUpperCase(), 113, ' ')
				+ "0000000000000000000      "
				+ AppUtils.padLeft(String.valueOf(amount), 54, '0') // amt
				+ "            " + toIdIssueDate
				+ AppUtils.padRight("", 87, ' ')
				+ AppUtils.padRight(toName2.toUpperCase(), 30, ' ')
				+ AppUtils.padRight("", 27, ' ') + "|";

		return message;
	}

	/**
	 * Build message transfer CA->SA.
	 * 
	 * @param hostIP
	 * @param transactionDate
	 * @param branchCode
	 * @param tellerId
	 * @param approverId
	 * @param refId
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 * @param serviceFee
	 * @param vatFee
	 * @param totalFee
	 * @param remarks
	 * @param tranCode
	 * @return
	 */
	private String buildCASAMessage(String hostIP, String transactionDate,
			String branchCode, String tellerId, String approverId,
			String refId, String fromAccount, String toAccount, String amount,
			String serviceFee, String vatFee, String totalFee, String remarks,
			String tranCode) {

		String message = "*LINX|"
				+ hostIP
				+ "||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||"
				+ tellerId
				+ "|IB|"
				+ tranCode
				+ "||||3843|*MOSA||"
				+ hostIP
				+ "|||||T9999|||"
				+ tellerId
				+ "|"
				+ refId
				+ "|N||||"
				+ tranCode
				+ "|"
				+ transactionDate
				+ "||VD|IB|"
				+ approverId
				+ "|"
				+ branchCode
				+ "|0|1|N|N|N|N|N|N|N|"
				+ fromAccount
				+ "|"
				+ amount
				+ "|||||||"
				+ toAccount
				+ "||||||||000|"
				+ totalFee
				+ "|000|"
				+ amount
				+ "|||||10000000|10000000|||||VND||||||||||||VND|VND|VND|VND|||3|||||||||||||||1|4|4|2|||||||||||||4|4|||||||1|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
				+ fromAccount + "||" + totalFee + "|||" + serviceFee + "|"
				+ vatFee + "||||||||" + amount
				+ "|||5|||4|4||||||||1||||||||||||||||" + remarks + "||||"
				+ amount + "|000|000||||||||3|1|1|||||||";

		return message;
	}

	/**
	 * Build message transfer from SA->CA.
	 * 
	 * @param hostIP
	 * @param transactionDate
	 * @param branchCode
	 * @param tellerId
	 * @param approverId
	 * @param refId
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 * @param serviceFee
	 * @param vatFee
	 * @param totalFee
	 * @param remarks
	 * @param tranCode
	 * @return
	 */
	private String buildSACAMessage(String hostIP, String transactionDate,
			String branchCode, String tellerId, String approverId,
			String refId, String fromAccount, String toAccount, String amount,
			String serviceFee, String vatFee, String totalFee, String remarks,
			String tranCode) {

		String message = "*LINX|"
				+ hostIP
				+ "||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||"
				+ tellerId
				+ "|ok|"
				+ tranCode
				+ "||||3843|*MOSA||"
				+ hostIP
				+ "|||||T9999|||"
				+ tellerId
				+ "|"
				+ refId
				+ "|N||||"
				+ tranCode
				+ "|"
				+ transactionDate
				+ "||VD|ok|"
				+ approverId
				+ "|"
				+ branchCode
				+ "|0|1|N|N|N|N|N|N|N|"
				+ fromAccount
				+ "||"
				+ amount
				+ "||||||"
				+ toAccount
				+ "||||||||000|"
				+ totalFee
				+ "|000|"
				+ amount
				+ "|||||10000000|10000000|10000000|10000000|||VND||||||||||||VND|VND|VND|VND||||3||||||||||||||1|4|4|2|||||||||||||4|4|4||||||1|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
				+ fromAccount + "|" + totalFee + "||||" + serviceFee + "|"
				+ vatFee + "|000|||||||||5||||4|4|4|||||||||||||||||||||||"
				+ remarks + "||||" + amount + "|000|000||||||||3|1|1|||||||";

		return message;
	}

	/**
	 * Build message transfer from SA->SA.
	 * 
	 * @param hostIP
	 * @param transactionDate
	 * @param branchCode
	 * @param tellerId
	 * @param approverId
	 * @param refId
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 * @param serviceFee
	 * @param vatFee
	 * @param totalFee
	 * @param remarks
	 * @param tranCode
	 * @return
	 */
	private String buildSASAMessage(String hostIP, String transactionDate,
			String branchCode, String tellerId, String approverId,
			String refId, String fromAccount, String toAccount, String amount,
			String serviceFee, String vatFee, String totalFee, String remarks,
			String tranCode) {

		String message = "*LINX|"
				+ hostIP
				+ "||||213|42|0200||ABCS|*LINX|||||||BBHTLMONEYFNC|||||||"
				+ tellerId
				+ "|ok|"
				+ tranCode
				+ "||||3843|*MOSA||"
				+ hostIP
				+ "|||||T9999|||"
				+ tellerId
				+ "|"
				+ refId
				+ "|N||||"
				+ tranCode
				+ "|"
				+ transactionDate
				+ "||VD|ok|"
				+ approverId
				+ "|"
				+ branchCode
				+ "|0|1|N|N|N|N|N|N|N|"
				+ fromAccount
				+ "|"
				+ amount
				+ "|||||||"
				+ toAccount
				+ "||||||||000|"
				+ totalFee
				+ "|000|"
				+ amount
				+ "|||||10000000|10000000|10000000|10000000|||VND||||||||||||VND|VND|VND|VND|||3|||||||||||||||1|4|4|2|||||||||||||4|4|4||||||1|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
				+ fromAccount + "|" + totalFee + "||||" + serviceFee + "|"
				+ vatFee + "|000|||||||||5||||4|4|4|||||||||||||||||||||||"
				+ remarks + "||||" + amount + "|000|000||||||||3|1|1|||||||";

		return message;
	}

	// ==========================
	/**
	 * Ngocnv (AFT001) dat lich AFT
	 */
	private void settingAFT() {
		try {
			// CSP200: Salary Payment Transaction CTR203: retail batch transfer:
			// RTR004: corporate batch transfer
			String xmlInputPattern = "/message/body/field[@name='%s']";
			String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "cif_no"));
			String rollout_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage,
					String.format(xmlInputPattern, "rollout_acct_no"));
			String rollout_acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage,
					String.format(xmlInputPattern, "rollout_acct_type"));
			String bnfc_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "bnfc_acct_no"));
			String bnfc_acct_type = ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xmlInputPattern, "bnfc_acct_type"));
			String freq = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "freq"));
			String freq_code = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "freq_code"));
			String started_date = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "started_date"));
			String expired_date = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "expired_date"));
			String aft_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "aft_type"));
			String aft_prod = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "aft_prod"));
			String amount = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "amount"));
			String fee = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "fee"));
			String remark = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "remark"));

			String strRequestMsg = "";
			String strResponseMsg = "";

			rebuildTransactionInfo(SIBS_27121, null);
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				amount = CoreBankUtils.convertEB2CBNumeric(amount);
				fee = CoreBankUtils.convertEB2CBNumeric(fee);

				strRequestMsg = buildSettingAFTMessage(
						hostMessageLogInfo.getWs_ip(),
						hostMessageLogInfo.getTeller_id(),
						hostMessageLogInfo.getHost_tran_code(),
						rollout_acct_no, rollout_acct_type, bnfc_acct_no,
						bnfc_acct_type, aft_type, started_date, expired_date,
						amount, freq, freq_code, fee, aft_prod);

				retMessage = as400Port.hostMessageSending(
						hostMessageLogInfo.getRef_channel(), strRequestMsg);

				if (retMessage != null) {
					strResponseMsg = AppUtils.convertArrayToString(retMessage
							.getArrString());
					if (retMessage.getErrCode() != null) {
						this.retErrorInfo = CachedParam.mappingErrorCode(
								retMessage.getErrCode(),
								retMessage.getDescription(),
								Global.MESSAGE_TYPE_ABCS);

					} else {

						this.retErrorInfo = CachedParam.mappingErrorCode(
								String.valueOf(Global.ERR_SYSTEM_OK),
								Global.ERR_SYSTEM_OK_MSG,
								Global.MESSAGE_TYPE_ABCS);
					}
				} else {

					retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
					retErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
				}
			} catch (RemoteException e) {

				retErrorInfo
						.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
				retErrorInfo.setErrorMessage(e.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg(strRequestMsg);
			this.hostMessageLogInfo.setHostResponseMsg("[" + strResponseMsg
					+ "]");
			this.arrRetHOSTMessageLogInfo
					.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());

		} catch (Exception ex) {

			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".createFDReceipt()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo
					.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * Ngocnv (AFT003) huy dat lich AFT
	 */
	private void destroyAFT() {
		try {
			// CSP200: Salary Payment Transaction CTR203: retail batch transfer:
			// RTR004: corporate batch transfer
			String xmlInputPattern = "/message/body/field[@name='%s']";
			String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "cif_no"));
			String rollout_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage,
					String.format(xmlInputPattern, "rollout_acct_no"));
			String rollout_acct_type = ElementUtils.getXMLElementInStringValue(
					inMessage,
					String.format(xmlInputPattern, "rollout_acct_type"));
			String bnfc_acct_no = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "bnfc_acct_no"));
			String bnfc_acct_type = ElementUtils
					.getXMLElementInStringValue(inMessage,
							String.format(xmlInputPattern, "bnfc_acct_type"));
			String freq = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "freq"));
			String freq_code = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "freq_code"));
			String started_date = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "started_date"));
			String expired_date = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "expired_date"));
			String aft_type = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "aft_type"));
			String aft_prod = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "aft_prod"));
			String amount = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "amount"));
			String fee = ElementUtils.getXMLElementInStringValue(inMessage,
					String.format(xmlInputPattern, "aft_fee"));
			String sequence = ElementUtils.getXMLElementInStringValue(
					inMessage, String.format(xmlInputPattern, "sequence"));
			rebuildTransactionInfo(SIBS_26121, null);
			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

			String strRequestMsg = "";
			String strResponseMsg = "";
			try {
				amount = CoreBankUtils.convertEB2CBNumeric(amount);
				fee = CoreBankUtils.convertEB2CBNumeric(fee);
				strRequestMsg = buildLockAFTMessage(
						hostMessageLogInfo.getWs_ip(),
						hostMessageLogInfo.getTeller_id(),
						hostMessageLogInfo.getHost_tran_code(),
						rollout_acct_no, rollout_acct_type, sequence);

				retMessage = as400Port.hostMessageSending(
						hostMessageLogInfo.getRef_channel(), strRequestMsg);

				if (retMessage != null) {
					strResponseMsg = AppUtils.convertArrayToString(retMessage
							.getArrString());
					if (retMessage.getErrCode() != null) {
						this.retErrorInfo = CachedParam.mappingErrorCode(
								retMessage.getErrCode(),
								retMessage.getDescription(),
								Global.MESSAGE_TYPE_ABCS);

					} else {
						rebuildTransactionInfo(SIBS_29121, null);
						this.hostMessageLogInfo.setSend_time(AppUtils
								.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
						strRequestMsg = buildDestroyAFTMessage(
								hostMessageLogInfo.getWs_ip(),
								hostMessageLogInfo.getTeller_id(),
								hostMessageLogInfo.getHost_tran_code(),
								rollout_acct_no, rollout_acct_type, sequence,
								bnfc_acct_no, bnfc_acct_type, aft_type,
								started_date, expired_date, amount, freq,
								freq_code, fee, aft_prod);
						retMessage = as400Port.hostMessageSending(
								hostMessageLogInfo.getRef_channel(),
								strRequestMsg);
						if (retMessage != null) {
							strResponseMsg = AppUtils
									.convertArrayToString(retMessage
											.getArrString());
							if (retMessage.getErrCode() != null) {

								this.retErrorInfo = CachedParam
										.mappingErrorCode(
												retMessage.getErrCode(),
												retMessage.getDescription(),
												Global.MESSAGE_TYPE_ABCS);

							} else {
								this.retErrorInfo = CachedParam
										.mappingErrorCode(String
												.valueOf(Global.ERR_SYSTEM_OK),
												Global.ERR_SYSTEM_OK_MSG,
												Global.MESSAGE_TYPE_ABCS);
							}
						} else {

							retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
							retErrorInfo
									.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
						}

					}
				} else {

					retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
					retErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
				}
			} catch (RemoteException e) {
				retErrorInfo
						.setErrorCode(ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
				retErrorInfo.setErrorMessage(e.getMessage());
			}
			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg(strRequestMsg);
			this.hostMessageLogInfo.setHostResponseMsg("[" + strResponseMsg
					+ "]");
			this.arrRetHOSTMessageLogInfo
					.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:")
					.append(CLASS_NAME + ".createFDReceipt()\n")
					.append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo
					.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * Ngocnv (AFT003) huy dat lich AFT
	 */
	private void queryAFT() {

		String xmlInputPattern = "/message/body/field[@name='%s']";
		String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
				String.format(xmlInputPattern, "cif_no"));
		String rollout_acct_no = ElementUtils.getXMLElementInStringValue(
				inMessage, String.format(xmlInputPattern, "rollout_acct_no"));
		String rollout_acct_type = ElementUtils.getXMLElementInStringValue(
				inMessage, String.format(xmlInputPattern, "rollout_acct_type"));
		String bnfc_acct_no = ElementUtils.getXMLElementInStringValue(
				inMessage, String.format(xmlInputPattern, "bnfc_acct_no"));
		String bnfc_acct_type = ElementUtils.getXMLElementInStringValue(
				inMessage, String.format(xmlInputPattern, "bnfc_acct_type"));

		String aft_prod = ElementUtils.getXMLElementInStringValue(inMessage,
				String.format(xmlInputPattern, "aft_prod"));

		getQueryAFT(cif_no, rollout_acct_no, rollout_acct_type, bnfc_acct_no,
				bnfc_acct_type, aft_prod);

	}

	/**
	 * Ngocnv (AFT003) huy dat lich AFT
	 */
	private void queryFDReceipt() {

		String xmlInputPattern = "/message/body/field[@name='%s']";
		String cif_no = ElementUtils.getXMLElementInStringValue(inMessage,
				String.format(xmlInputPattern, "cif_no"));
		String group_acct = ElementUtils.getXMLElementInStringValue(inMessage,
				String.format(xmlInputPattern, "group_acct"));
		String receipt_no = ElementUtils.getXMLElementInStringValue(inMessage,
				String.format(xmlInputPattern, "receipt_no"));

		getQueryFDReceipt(cif_no, group_acct, receipt_no);

	}

	/**
	 * added by Ngocnv AFT001 Build message Setting AFT
	 * 
	 * @param hostIP
	 * @param tellerId
	 * @param tranCode
	 * @param accountNo
	 * @param typeAccountNo
	 * @param accountCo
	 * @param typeAccountCo
	 * @param codeAFT
	 * @param startDate
	 * @param endDate
	 * @param moneyTransfer
	 * @param TS
	 * @param typeTS
	 * @param moneyFee
	 * @param AFT
	 * @return
	 */
	private String buildSettingAFTMessage(String hostIP, String tellerId,
			String tranCode, String accountNo, String typeAccountNo,
			String accountCo, String typeAccountCo, String codeAFT,
			String startDate, String endDate, String moneyTransfer, String TS,
			String typeTS, String moneyFee, String AFT) {
		String message = "*LINX|"
				+ hostIP
				+ "||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDMNTFNC|||||1|10|"
				+ tellerId
				+ "|ok|"
				+ tranCode
				+ "|N||||"
				+ tellerId
				+ "|1||*END||BTS|RBS||ok|27|110||||"
				+ tranCode
				+ "|A|R|1|N|F|||||||||||||||||"
				+ accountNo
				+ "|"
				+ typeAccountNo
				+ "||"
				+ accountCo
				+ "|"
				+ typeAccountCo
				+ "|"
				+ codeAFT
				+ "|"
				+ startDate
				+ "|000000|"
				+ endDate
				+ "||"
				+ moneyTransfer
				+ "|"
				+ TS
				+ "|"
				+ typeTS
				+ "|||||"
				+ moneyFee
				+ "||000|000|||||"
				+ AFT
				+ "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";

		return message;
	}

	/**
	 * added by Ngocnv AFT003 Build message destroy AFT
	 * 
	 * @param hostIP
	 * @param tellerId
	 * @param tranCode
	 * @param accountNo
	 * @param typeAccountNo
	 * @param seq
	 * @param accountCo
	 * @param typeAccountCo
	 * @param codeAFT
	 * @param startDate
	 * @param endDate
	 * @param moneyTransfer
	 * @param TS
	 * @param typeTS
	 * @param moneyFee
	 * @param AFT
	 * @return
	 */
	private String buildDestroyAFTMessage(String hostIP, String tellerId,
			String tranCode, String accountNo, String typeAccountNo,
			String seq, String accountCo, String typeAccountCo, String codeAFT,
			String startDate, String endDate, String moneyTransfer, String TS,
			String typeTS, String moneyFee, String AFT) {
		String message = "*LINX|"
				+ hostIP
				+ "||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDMNTFNC|||||1|10|"
				+ tellerId
				+ "|ok|"
				+ tranCode
				+ "|N||||"
				+ tellerId
				+ "|1||*END||BTS|RBS||ok|27|110||||"
				+ tranCode
				+ "|D|R|1|N|F||||||C|||||||||||"
				+ accountNo
				+ "|"
				+ typeAccountNo
				+ "|"
				+ seq
				+ "|"
				+ accountCo
				+ "|"
				+ typeAccountCo
				+ "|"
				+ codeAFT
				+ "|"
				+ startDate
				+ "|"
				+ startDate
				+ "|"
				+ endDate
				+ "||"
				+ moneyTransfer
				+ "|"
				+ TS
				+ "|"
				+ typeTS
				+ "|000|000|000||"
				+ moneyFee
				+ "||00|00||0000000000000000000|||"
				+ AFT
				+ "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";

		return message;
	}

	/**
	 * added by Ngocnv AFT003 Build message destroy AFT
	 * 
	 * @param hostIP
	 * @param tellerId
	 * @param tranCode
	 * @param accountNo
	 * @param typeAccountNo
	 * @param seq
	 * @param accountCo
	 * @param typeAccountCo
	 * @param codeAFT
	 * @param startDate
	 * @param endDate
	 * @param moneyTransfer
	 * @param TS
	 * @param typeTS
	 * @param moneyFee
	 * @param AFT
	 * @return
	 */
	private String buildLockAFTMessage(String hostIP, String tellerId,
			String tranCode, String accountNo, String typeAccountNo, String seq) {
		String message = "*LINX|"
				+ hostIP
				+ "||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDINQFNC|||||1|10|"
				+ tellerId
				+ "|OK|"
				+ tranCode
				+ "|N||||"
				+ tellerId
				+ "|1||*END||BTS|RBS||ok|27|110||||"
				+ tranCode
				+ "|I|R|1|N|F||||||C|||||||||||"
				+ accountNo
				+ "|"
				+ typeAccountNo
				+ "|"
				+ seq
				+ "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";

		return message;
	}

	/**
	 * added by Ngocnv Build message transferCAtoFD
	 * 
	 * @param hostIP
	 * @param tellerId
	 * @param tranCode
	 * @param CA
	 * @param FD
	 * @param amount
	 * @param remark
	 * @param date
	 * @return
	 */
	private String buildTransferCAtoFDMessage(String hostIP, String tellerId,
			String refId, String tranCode, String CA, String FD, String amount,
			String fee, String remark, String date, String branch, String vatFee) {
		String message = "*LINX|"
				+ hostIP
				+ "| | | |213|42|0200| |ABCS|*LINX| | | | | | |BBHTLMONEYFNC| | | | | | |"
				+ tellerId
				+ "|PC|"
				+ tranCode
				+ "| | | |3843|*MOSA| |"
				+ hostIP
				+ "| | | | |T9999| ||"
				+ tellerId
				+ "|"
				+ refId
				+ "|N||||"
				+ tranCode
				+ "|"
				+ date
				+ "| |VD|PC|"
				+ tellerId
				+ "|"
				+ branch
				+ "|0|1|N|N|N|N|N|N|N|||"
				+ amount
				+ "|||"
				+ FD
				+ "|||"
				+ CA
				+ "||||||||000|"
				+ fee
				+ "|000|"
				+ amount
				+ "|||||10000000|10000000|10000000|10000000|||VND||||||||||||VND|VND|VND|VND||||3||||||||||||||1|4|4|2|||||||||||||4|4|4||||||1|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
				+ CA + "||" + fee + "|||" + fee + "|" + vatFee + "|000|||||||"
				+ amount + "|||5|||4|4|4|||||||2||||||||||||||||" + remark
				+ "|||||000|000|||||||||1|1|||||||";

		return message;
	}

	@SuppressWarnings("unused")
	private String reBuildTransferCAtoFDMessage(String hostIP, String tellerId,
			String refId, String tranCode, String CA, String FD, String amount,
			String fee, String remark, String date, String branch, String vatFee) {
		String message = "*LINX|"
				+ hostIP
				+ "| | | |213|42|0200| |ABCS|*LINX| | | | | | |BBHTLMONEYFNC| | | | | | |"
				+ tellerId
				+ "|PC|"
				+ tranCode
				+ "| | | |3843|*MOSA| |"
				+ hostIP
				+ "| | | | |T9999| ||"
				+ tellerId
				+ "|"
				+ refId
				+ "|N||||"
				+ tranCode
				+ "|"
				+ date
				+ "| |VD|PC|"
				+ tellerId
				+ "|"
				+ branch
				+ "|0|1|N|N|N|N|Y|N|N|||"
				+ amount
				+ "|||"
				+ FD
				+ "|||"
				+ CA
				+ "||||||||000|"
				+ fee
				+ "|000|"
				+ amount
				+ "|||||10000000|10000000|10000000|10000000|||VND||||||||||||VND|VND|VND|VND||||3||||||||||||||1|4|4|2|||||||||||||4|4|4||||||1|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
				+ CA + "||" + fee + "|||" + fee + "|" + vatFee + "|000|||||||"
				+ amount + "|||5|||4|4|4|||||||2||||||||||||||||" + remark
				+ "|||||000|000|||||||||1|1|||||||";

		return message;
	}

	private void getQueryAFT(String cif_no, String rollout_acct_no,
			String rollout_acct_type, String bnfc_acct_no,
			String bnfc_acct_type, String aft_prod) {
		try {
			ArrayList<AftInfo> arrAftInfo = null;
			AftInfoFacade aftFacade = new AftInfoFacade();

			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				arrAftInfo = aftFacade.getAftInfos(cif_no, rollout_acct_no,
						bnfc_acct_no, rollout_acct_type, bnfc_acct_type,
						aft_prod);
			} catch (Exception ex) {
			}

			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (arrAftInfo == null || arrAftInfo.size() == 0) {

				String xpathOutput = "/message/?body";
				ElementUtils.setXMLElementValue(outMessage, xpathOutput);
				this.retErrorInfo = CachedParam.mappingErrorCode(
						String.valueOf(Global.ERR_SYSTEM_OK),
						Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_ABCS);

			} else {

				// 1.Tao node list,entity
				String xpathOutput = "/message/?body/?list[?@name[set-value('records')]]";
				ElementUtils.setXMLElementValue(outMessage, xpathOutput);

				for (int i = 0; i < arrAftInfo.size(); i++) {
					AftInfo tempAft = arrAftInfo.get(i);
					xpathOutput = "/message/body/list[@name='records']/?$entity[?@name[set-value('"
							+ i + "')]]";
					ElementUtils.setXMLElementValue(outMessage, xpathOutput);
					xpathOutput = "/message/body/list[@name='records']/entity[@name='%s']/?$field[set-value('%s')][?@name[set-value('%s')]]";

					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getStatus(), "status");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.correctAccountNumber(tempAft
											.getRollout_Acct_No()),
							"rollout_acct_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getRollout_acct_ccy(),
							"rollout_acct_ccy");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getRollout_acct_type(),
							"rollout_acct_type");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput, String
									.valueOf(i), CoreBankUtils
									.correctAccountNumber(tempAft
											.getBnfc_Acct_No()), "bnfc_acct_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getBnfc_acct_ccy(),
							"bnfc_acct_ccy");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getBnfc_acct_type(),
							"bnfc_acct_type");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput, String
									.valueOf(i), CoreBankUtils
									.convertDdMMyyToYyyyMMyy(tempAft
											.getStarted_Date()), "started_date");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput, String
									.valueOf(i), CoreBankUtils
									.convertDdMMyyToYyyyMMyy(tempAft
											.getExpired_Date()), "expired_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.convertDdMMyyToYyyyMMyy(tempAft
											.getNext_Transfer_Date()),
							"next_transfer_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.convertDdMMyyToYyyyMMyy(tempAft
											.getLast_Transfer_Date()),
							"last_transfer_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.correctBranchCode(tempAft.getAmount()),
							"amount");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getAft_Fee(), "aft_fee");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getFreq_No(), "freq_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getFreq_Code(),
							"freq_code");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempAft.getDebit_Seq(),
							"debit_seq");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput, String
									.valueOf(i), CoreBankUtils
									.convertDdMMyyToYyyyMMyy(tempAft
											.getEntered_date()), "entered_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempAft.getBnfc_acct_prod_type(),
							"bnfc_acct_prod_type");

				}
				this.retErrorInfo = CachedParam.mappingErrorCode(
						String.valueOf(Global.ERR_SYSTEM_OK),
						Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_ABCS);
			}
			this.hostMessageLogInfo.setRef_cif_acct(cif_no);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + "" + "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + "" + "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {

			ex.printStackTrace();
			logger.error(new StringBuffer()
					.append("Error source:")
					.append(CLASS_NAME + ".getListOfCustomerAccountOffline()\n")
					.append("Error message:").append(ex.getStackTrace() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());
		}
	}

	private void getQueryFDReceipt(String cif_no, String group_acct,
			String receipt_no) {
		try {
			ArrayList<FDInfo> arrFDInfo = null;
			FDInfoFacade fdFacade = new FDInfoFacade();

			this.hostMessageLogInfo.setSend_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			try {
				arrFDInfo = fdFacade.getFDInfos(cif_no, group_acct, receipt_no);
			} catch (Exception ex) {
			}

			this.hostMessageLogInfo.setReceive_time(AppUtils
					.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
			if (arrFDInfo == null || arrFDInfo.size() == 0) {

				String xpathOutput = "/message/?body/?list[?@name[set-value('records')]]";
				ElementUtils.setXMLElementValue(outMessage, xpathOutput);
				this.retErrorInfo = CachedParam.mappingErrorCode(
						String.valueOf(Global.ERR_SYSTEM_OK),
						Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_ABCS);
			} else {

				// 1.Tao node list,entity
				String xpathOutput = "/message/?body/?list[?@name[set-value('records')]]";
				ElementUtils.setXMLElementValue(outMessage, xpathOutput);

				for (int i = 0; i < arrFDInfo.size(); i++) {
					FDInfo tempFD = arrFDInfo.get(i);
					xpathOutput = "/message/body/list[@name='records']/?$entity[?@name[set-value('"
							+ i + "')]]";
					ElementUtils.setXMLElementValue(outMessage, xpathOutput);
					xpathOutput = "/message/body/list[@name='records']/entity[@name='%s']/?$field[set-value('%s')][?@name[set-value('%s')]]";

					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempFD.getGroup_Acct(),
							"group_acct");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.correctAccountNumber(tempFD
											.getReceipt_No()), "receipt_no");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i),
							tempFD.getReceipt_Product_Code(),
							"receipt_product_code");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempFD.getInterest_Rate(),
							"interest_rate");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), AppUtils
									.XMLReplaceSpecChar(tempFD
											.getOriginal_Balance()),
							"original_balance");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.convertDdMMyyToYyyyMMyy(tempFD
											.getIssue_Date()), "issue_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), CoreBankUtils
									.convertDdMMyyToYyyyMMyy(tempFD
											.getMaturity_Date()),
							"maturity_date");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempFD.getAccurated_Interest(),
							"accurated_interest");
					ElementUtils
							.setXMLElementValue(outMessage, xpathOutput,
									String.valueOf(i), tempFD.getCur_Type(),
									"cur_type");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempFD.getCd_Term(), "cd_term");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempFD.getAvailable_Balance(),
							"available_balance");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempFD.getHold(), "hold");
					ElementUtils.setXMLElementValue(outMessage, xpathOutput,
							String.valueOf(i), tempFD.getStatus(), "status");

				}
				this.retErrorInfo = CachedParam.mappingErrorCode(
						String.valueOf(Global.ERR_SYSTEM_OK),
						Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_ABCS);
			}
			this.hostMessageLogInfo.setRef_cif_acct(cif_no);
			this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo
					.getErrorCode()));
			this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
			this.hostMessageLogInfo.setHostRequestMsg("[" + "" + "]");
			this.hostMessageLogInfo.setHostResponseMsg("[" + "" + "]");
			this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);
		} catch (Exception ex) {
			logger.error("query AFT: ", ex);
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.getMessage());
		}
	}

	// private void settlementFDAccountTEMP() {
	// try {
	//
	// // 1.Lay thong tin ve fdreceipt
	// int startPos = 41;
	// int recordLength = 230;
	// rebuildTransactionInfo(Global.SIBS_036, Global.APP_DEFAULT_BRANCH_CODE);
	// ABCS_TEMPLATE[2] = "192.168.1.1";
	// ABCS_TEMPLATE[18] = "BBHTLMONEYFNC";
	// ABCS_TEMPLATE[25] = "EBANKING01";
	// ABCS_TEMPLATE[26] = "ebank";
	// ABCS_TEMPLATE[34] = "192.168.1.1";
	// ABCS_TEMPLATE[42] = "EBANKING01";
	// ABCS_TEMPLATE[43] = "11111";
	// ABCS_TEMPLATE[49] = "021112";
	// ABCS_TEMPLATE[53] = "EBANKING02";
	// ABCS_TEMPLATE[27] = "3080";
	// ABCS_TEMPLATE[48] = "3080";
	// ABCS_TEMPLATE[69] = "03120185053026";
	// ABCS_TEMPLATE[94] = "VND";
	// //for (int i = 0; i < 10; i++) {
	// String hostSendMessage = AppUtils.convertArrayToString(ABCS_TEMPLATE);
	// String hostReceiveMessage = "";
	// String serial = "";
	// this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
	// // try {
	// // ServiceInfo objService =
	// CachedParam.getServiceInfoByServiceType(consumerType);
	// // as400Service = new AS400_ServiceLocator();
	// // as400Service.setServiceTimeout(objService.getService_timeout() *
	// 1000);
	// //
	// as400Service.setAS400HttpPortEndpointAddress("http://10.0.2.215:7080/OneConnectServices/services/AS400");
	// // as400Port = as400Service.getAS400HttpPort();
	// // retMessage = as400Port.hostMessageSending("CC001", hostSendMessage);
	// // } catch (RemoteException ex) {
	// //
	// this.retErrorInfo.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
	// // this.retErrorInfo.setErrorMessage(ex.toString());
	// // }
	// ArrayList<String[]> retArrOfResult = null;
	// this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
	// String amt = "";
	// String []
	// arrRet="*SOKI|10.1.1.1|00002|005505||0213|001283|0005|*DSP|ABCS|*LINX|BBHTLRTE|00|/0000000|00000000000|00|SVDSPPV51 DSPV5505IQ|BBHTLMONEYFNC||8079891304121107143547||0000|0010|0010|EBANKING01|kienvt|3080|0|||1066|*HAND|DQTMPRTE|10.1.1.1|38656|DSPO7020Q|000000000|110714|T9999|13102|*EOM|939|I2|3520185019855|T|VU DUY TUAN|VU DUY TUAN|471709|FS010I|35|35|711|00000|00000||3|0|I|11005010903893|.00|2500000.00|2517222.00|.00|N||.00|.00000|.00|.00|.00|555.55556|17222.00|.00|.00|.00|.00|17222.00|.00|.00|.00|.00|.00|2500000.00|2500000.00|.00|.00|.00000|11666.66655|2500000.00|16666.66650|555.55555|.00|.00|.00000|.00|.00|.00|2517222.00|2500000.00|.00|.00|27500000.00|6111.11105|11|120413|0000000|0000000|120413|120513|0000000|0000000|0000000|0000000|0000000|120313|000|2013071|0000000|0000000|2013102|2013102|0000000|0000000|0000000|0000000|0000000|2013071|1|M|1|M|Y|A|S|2|N||0000000000000000000||0000000000000000000|.075000000|.000000000||.020000000|.130000000|000||30161|N|.00|00000|.00|00000|.00|00000|.00|00000|N|.00|.00|.00|.00|.00|.00|.00|.00|.00|.00|.00|0|0000000000000000000||.000000000|.00|.00|.00|.00|.00||0|.000000000|.00|.00|.00|.00|.00|.080000000|N|N|N|N|||N||N|Y||||01||Y|Y|||N|N|N|VND|0|Y|00000000000|R|2517222.00||0000000000000000000|N||N||00000|53|I6|17222.00|N|N|2517222.00|.00||.00|".split("\\|");
	// //if (retMessage != null) {
	// //if (retMessage.getErrCode() == null) {
	// retArrOfResult = AppUtils.getListOfResponseRow(arrRet, startPos,
	// recordLength);
	// // Kienvt : check length
	// if (retArrOfResult.get(0).length > 200) {
	// serial = (String) retArrOfResult.get(0)[171];
	// amt = (String) retArrOfResult.get(0)[173];
	// if (amt != null && amt.length() > 0) {
	// amt = amt.substring(0, amt.indexOf("."));
	// }
	// if (amt == null || amt.length() == 0) {
	// amt = "0";
	// }
	// System.out.println((String) retArrOfResult.get(0)[61]);
	// System.out.println((String) retArrOfResult.get(0)[173]);
	// System.out.println((String) retArrOfResult.get(0)[227]);
	// }
	// // } else {
	// // this.retErrorInfo =
	// CachedParam.mappingErrorCode(retMessage.getErrCode(),
	// retMessage.getDescription(), Global.MESSAGE_TYPE_ABCS);
	// // }
	// //if (retMessage.getArrString() != null)
	// // hostResponseMessage =
	// AppUtils.convertArrayToString(retMessage.getArrString());
	// // } else {
	// // // TODO: kienvt bo xung them
	// // retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
	// // retErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
	// // }
	//
	// this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
	// this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
	// this.hostMessageLogInfo.setHostRequestMsg("[" + hostSendMessage + "]");
	// this.hostMessageLogInfo.setHostResponseMsg("[" + hostReceiveMessage +
	// "]");
	// this.arrRetHOSTMessageLogInfo.add((HOSTMessagLogInfo)
	// hostMessageLogInfo.clone());
	//
	// System.out.println("=====================================");
	// //}
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME
	// +
	// ".settlementFDAccount()\n").append("Error message:").append(ex.toString()
	// + "\n"));
	// this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
	// this.retErrorInfo.setErrorMessage(ex.toString());
	// }
	// }
	//
	// public static void main(String args[]) {
	// SIBSConsumer temp = new SIBSConsumer();
	// temp.settlementFDAccountTEMP();
	// }
}
