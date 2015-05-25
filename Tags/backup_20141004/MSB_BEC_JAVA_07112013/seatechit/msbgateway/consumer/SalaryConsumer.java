package seatechit.msbgateway.consumer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.HOSTMessagLogInfo;
import seatechit.msbgateway.dbaccess.entity.SalaryBKDDSALW;
import seatechit.msbgateway.dbaccess.entity.SalaryBKDDSALWH;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.CoreBankUtils;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbMessage;

/**
 * Process message for Salary Payment
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6
 * 
 */
public class SalaryConsumer extends SIBSConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(SalaryConsumer.class);
	final String CLASS_NAME = this.getClass().getName();

	public SalaryConsumer() {
		super();
	}

	@Override
	public void submit(MbMessage inMessage, MbMessage outMessage) {
		try {
			this.inMessage = inMessage;
			this.outMessage = outMessage;

			this.baseXmlRequestDoc = ElementUtils.getXMLContent(inMessage);
			this.buildLogOfRequestMessage();

			String tran_code = xmlMessageLogInfo.getTran_code();
			String tran_service_code = xmlMessageLogInfo.getTran_service_code();
			buildTransactionInfo(consumerType, tran_code, tran_service_code);

			saveSalary();

			// 3)add more detail response time,date....
			this.buildLogOfResponseMessage();
			this.xmlMessageLogInfo.setExecution_date(hostMessageLogInfo.getHost_tran_date());
			this.xmlMessageLogInfo.setRef_tran_no(hostMessageLogInfo.getHost_tran_sn());
			this.buildXMLMessageResponseHeader();
			this.baseXmlResponseDoc = ElementUtils.getXMLContent(outMessage);
			this.buildLogMessageInfo();
			this.logMessageFacade.insertLogMessage(logMessage);
		} catch (Exception ex) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".submit()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void saveSalary() {
		try {

			String UploadTime = AppUtils.now("hhmmss");
			String hostTranDate = this.hostMessageLogInfo.getHost_tran_date().length() < 6 ? "0" + hostMessageLogInfo.getHost_tran_date() : hostMessageLogInfo.getHost_tran_date();
			String UploadDate = hostTranDate;
			String NextTransferDate = AppUtils.getJulian7FromDate((Date) (new SimpleDateFormat(Global.DEF_FORMAT_DATE_DDMMYY)).parse(hostTranDate));

			// List
			List<MbElement> listOfTransfer = (List<MbElement>) this.inMessage.evaluateXPath("/message/body/list[@name='records']/entity");
			ArrayList<SalaryBKDDSALW> arrSalaryBKDDSALW = new ArrayList<SalaryBKDDSALW>();
			ArrayList<SalaryBKDDSALWH> arrSalaryBKDDSALWH = new ArrayList<SalaryBKDDSALWH>();
			SalaryBKDDSALWH tempSalaryBKDDSALWH = new SalaryBKDDSALWH();
			BigDecimal totalAmt = BigDecimal.valueOf(0);
			BigDecimal totalFee = BigDecimal.valueOf(0);
			String rollout_branch_no = ElementUtils.getXMLElementInStringValue(inMessage, "/message/body/list[@name='records']/entity[@name='0']/field[@name='rollout_branch_no']");
			String seqNumber = sysParamFacade.getSalarySequence();
			String strNew_RefNumber = rollout_branch_no + "9" + AppUtils.padLeft(seqNumber, 6, '0');
			String rollout_acct_no = "";
			String rollout_acct_ccy = "";
			String gl_hold_amount = CachedParam.getSystemParam(Global.PARAM_GL_HOLD_AMOUNT);
			String gl_hold_vat = CachedParam.getSystemParam(Global.PARAM_GL_HOLD_VAT);
			String gl_hold_fee = CachedParam.getSystemParam(Global.PARAM_GL_HOLD_FEE);

			// Kienvt : voi khoan amount lay source_branch,gl la cua trung tam
			// vi the khi cung anh huong den khi addReff,approve,online posting
			String headOfficeBranch = CachedParam.getSystemParam(Global.PARAM_GL_SALARY_SRC_BRANCH);

			ErrorInfo tempErrorInfo = new ErrorInfo(ERR_SYSTEM_OK, ERR_SYSTEM_OK_MSG, MESSAGE_TYPE_ABCS);

			for (int i = 0; i < listOfTransfer.size(); i++) {

				SalaryBKDDSALW tempSalaryBKDDSALW = new SalaryBKDDSALW();

				String xmlInputPattern_2 = "/message/body/list[@name='records']/entity[@name='" + (i) + "']/field[@name='%s']";
				String tran_sn = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "tran_sn"));
				rollout_branch_no = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "rollout_branch_no"));
				String bnfc_branch_no = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "bnfc_branch_no"));
				rollout_acct_no = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "rollout_acct_no"));
				String rollout_acct_type = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "rollout_acct_type"));
				rollout_acct_ccy = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "rollout_acct_ccy"));
				String rollout_buy_rate = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "rollout_buy_rate"));

				String bnfc_acct_no = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "bnfc_acct_no"));
				String bnfc_acct_type = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "bnfc_acct_type"));
				String bnfc_acct_ccy = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "bnfc_acct_ccy"));
				String bnfc_sell_rate = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "bnfc_sell_rate"));

				String amt = CoreBankUtils.roundCBNumberic(ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "amt")));
				String serviceFee = CoreBankUtils.roundCBNumberic(ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "fee")));
				String remark = AppUtils.convertToVietnamesNoSign(ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "remark")));

				/*
				if (tran_sn.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_TRAN_SN_NOT_VALID_OR_MISSING);
					break;
				}

				if (rollout_branch_no.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_SRC_BRANCH_CODE_NOT_VALID_OR_MISSING);
					break;
				}
				if (rollout_acct_no.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_NO_NOT_VALID_OR_MISSING);
					break;
				}
				if (rollout_acct_ccy.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_SRC_CURRENCY_CODE_NOT_VALID_OR_MISSING);
					break;
				}
				if (rollout_buy_rate.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_SELL_RATE_NOT_VALID_OR_MISSING);
					break;
				}
				if (rollout_acct_type.length() == 0 || CoreBankUtils.isValidAccountType(rollout_acct_type) == false) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_SRC_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
					break;
				}

				if (!AppUtils.isValidNumber(amt)) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_TRANSACTION_AMOUNT_NOT_VALID_OR_MISSING);
					break;
				}

				if (bnfc_branch_no.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_DES_BRANCH_CODE_NOT_VALID_OR_MISSING);
					break;
				}
				if (bnfc_acct_no.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_NO_NOT_VALID_OR_MISSING);
					break;
				}
				if (bnfc_acct_type.length() == 0 || CoreBankUtils.isValidAccountType(bnfc_acct_type) == false) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_DES_ACCOUNT_TYPE_NOT_VALID_OR_MISSING);
					break;
				}
				if (bnfc_acct_ccy.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_DES_CURRENCY_CODE_NOT_VALID_OR_MISSING);
					break;
				}
				if (bnfc_sell_rate.length() == 0) {
					tempErrorInfo.setErrorCode(Global.ERR_UDF_BUY_RATE_NOT_VALID_OR_MISSING);
					break;
				}
				*/
				totalAmt = totalAmt.add(new BigDecimal(amt));
				totalFee = totalFee.add(new BigDecimal(serviceFee));

				tempSalaryBKDDSALW.setSDID(strNew_RefNumber);
				tempSalaryBKDDSALW.setSDAMT(amt);
				tempSalaryBKDDSALW.setSDBRN(headOfficeBranch);
				tempSalaryBKDDSALW.setSDRCID("I");
				tempSalaryBKDDSALW.setSDCTYP("VND");
				tempSalaryBKDDSALW.setSDSEQ(String.valueOf(i + 1));
				tempSalaryBKDDSALW.setSDACCT(bnfc_acct_no);
				tempSalaryBKDDSALW.setSDATYP(bnfc_acct_type);
				tempSalaryBKDDSALW.setSDCOST("0");
				tempSalaryBKDDSALW.setSDPROC("0");
				tempSalaryBKDDSALW.setSDSTAT("0");
				tempSalaryBKDDSALW.setSDAMT2("0");
				tempSalaryBKDDSALW.setSDAVL("0");
				tempSalaryBKDDSALW.setSDMUID("CNTT");
				tempSalaryBKDDSALW.setSDMWID("CNTT");
				tempSalaryBKDDSALW.setSDMTIM(UploadTime);
				tempSalaryBKDDSALW.setSDDAT6(UploadDate);
				tempSalaryBKDDSALW.setSDDAT7(NextTransferDate);
				tempSalaryBKDDSALW.setSDACUR("0");
				tempSalaryBKDDSALW.setSSRSV1("");
				tempSalaryBKDDSALW.setSSRSV2("");
				tempSalaryBKDDSALW.setREMRK1(AppUtils.subStringBySpecLength(remark, 49));
				tempSalaryBKDDSALW.setREMRK2("");
				arrSalaryBKDDSALW.add(tempSalaryBKDDSALW);
			}

			// Save salary record to AS400 first
			if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
				tempSalaryBKDDSALWH.setSMACCT(gl_hold_amount); // gl trung gian
				tempSalaryBKDDSALWH.setSMID(strNew_RefNumber);
				tempSalaryBKDDSALWH.setSMRCID("I");
				tempSalaryBKDDSALWH.setSMCOST("0");
				tempSalaryBKDDSALWH.setSMPROC("0");
				tempSalaryBKDDSALWH.setSMATYP("G");
				tempSalaryBKDDSALWH.setSMBRN(headOfficeBranch);
				tempSalaryBKDDSALWH.setSMCTYP("VND");
				tempSalaryBKDDSALWH.setSMSTAT("0");
				tempSalaryBKDDSALWH.setSMAMT(String.valueOf(totalAmt.longValue()));
				tempSalaryBKDDSALWH.setSMAVL("0");
				tempSalaryBKDDSALWH.setSMCNT("0");
				tempSalaryBKDDSALWH.setSMAMT2("0");
				tempSalaryBKDDSALWH.setSMCNT2("0");
				tempSalaryBKDDSALWH.setSSMUID("CNTT");
				tempSalaryBKDDSALWH.setSSMWID("CNTT");
				tempSalaryBKDDSALWH.setSSMTIM(UploadTime);
				tempSalaryBKDDSALWH.setSMDAT6(UploadDate);
				tempSalaryBKDDSALWH.setSMDAT7(NextTransferDate);
				tempSalaryBKDDSALWH.setSMTFR6(UploadDate);
				tempSalaryBKDDSALWH.setSMTFR7(NextTransferDate);
				tempSalaryBKDDSALWH.setSMLST6("0");
				tempSalaryBKDDSALWH.setSMLST7("0");
				tempSalaryBKDDSALWH.setSSRSV1("");
				tempSalaryBKDDSALWH.setSSRSV2("");
				tempSalaryBKDDSALWH.setSMRMK1("");
				tempSalaryBKDDSALWH.setSMRMK2("");
				arrSalaryBKDDSALWH.add(tempSalaryBKDDSALWH);
				String core_sn = sysParamFacade.getTransactionSequence();

				if (salaryFacade.saveSalary(arrSalaryBKDDSALW, arrSalaryBKDDSALWH)) {
					// Hold total amount
					tempErrorInfo = transferCAGL(hostMessageLogInfo.getHost_tran_date(), headOfficeBranch, rollout_acct_no, rollout_acct_ccy, gl_hold_amount, String.valueOf(totalAmt.longValue()));
					if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
						String vatFee = String.valueOf(totalFee.divide(new BigDecimal(Global.BASED_VAT_RATE), 0, RoundingMode.HALF_UP).longValue());
						String orgFee = String.valueOf(totalFee.subtract(new BigDecimal(vatFee)).longValue());

						// Kienvt : VAT va FEE thi chuyen ve chi nhanh
						// Hold fee
						if (Double.parseDouble(orgFee) > 0) {
							core_sn = sysParamFacade.getTransactionSequence();
							// Chy y VAT va fee thi van lay branch code cua chi nhanh
							tempErrorInfo = transferCAGL(hostMessageLogInfo.getHost_tran_date(), rollout_branch_no, rollout_acct_no, rollout_acct_ccy, gl_hold_fee, String.valueOf(orgFee));
						}

						// Hold VAT fee
						if (Double.parseDouble(vatFee) > 0) {
							core_sn = sysParamFacade.getTransactionSequence();
							// Chy y VAT va fee thi van lay branch code cua chi nhanh
							tempErrorInfo = transferCAGL(hostMessageLogInfo.getHost_tran_date(), rollout_branch_no, rollout_acct_no, rollout_acct_ccy, gl_hold_vat, String.valueOf(vatFee));
						}

						if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
							// Add refnumber
							tempErrorInfo = addRefNumber(strNew_RefNumber, headOfficeBranch, rollout_acct_ccy);
							if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
								// Lock row before approve
								tempErrorInfo = salaryLockApprove(core_sn, strNew_RefNumber, headOfficeBranch, rollout_acct_ccy);
								if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
									// Approve salary
									tempErrorInfo = salaryApprove(core_sn, strNew_RefNumber, headOfficeBranch, rollout_acct_ccy);
									if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
										// Lock row before posting
										tempErrorInfo = salaryLockApprove(core_sn, strNew_RefNumber, headOfficeBranch, rollout_acct_ccy);
										if (tempErrorInfo.getErrorCode() == ERR_SYSTEM_OK) {
											// Online posting
											tempErrorInfo = onlinePosting(core_sn, strNew_RefNumber, headOfficeBranch, rollout_acct_ccy);
										}
									}
								}
							}
						} else {
							tempErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
							tempErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
						}
					}
				} else {
					tempErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
					tempErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
				}
			}
			String xmlOutputPattern_2 = "/message/?body/?list[?@name[set-value('records')]]";
			outMessage.evaluateXPath(xmlOutputPattern_2);
			for (int i = 0; i < listOfTransfer.size(); i++) {
				String xmlInputPattern_2 = "/message/body/list[@name='records']/entity[@name='" + (i) + "']/field[@name='%s']";
				String tran_sn = ElementUtils.getXMLElementInStringValue(inMessage, String.format(xmlInputPattern_2, "tran_sn"));

				xmlOutputPattern_2 = "/message/body/list[@name='records']/?$entity[?@name[set-value('" + i + "')]]";
				outMessage.evaluateXPath(xmlOutputPattern_2);

				String xmlOuputPattern = "/message/body/list[@name='records']/entity[@name='" + i + "']/?$field[set-value('%s')][?@name[set-value('%s')]]";
				ElementUtils.setXMLElementValue(outMessage, xmlOuputPattern, tran_sn, "tran_sn");
				ElementUtils.setXMLElementValue(outMessage, xmlOuputPattern, hostMessageLogInfo.getHost_tran_sn(), "core_sn");
				ElementUtils.setXMLElementValue(outMessage, xmlOuputPattern, String.valueOf(tempErrorInfo.getErrorCode()), "result");
			}
			this.retErrorInfo = tempErrorInfo;
		} catch (Exception ex) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".saveSalary()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
		}
	}

	/**
	 * Transfer money (hold amount,fee) from CA account to GL account
	 * 
	 * @param core_sn
	 * @param effective_date
	 * @param rollout_branch_no
	 * @param rollout_acct_no
	 * @param rollout_acct_ccy
	 * @param gl_acct_no
	 * @param totalAmt
	 * @return
	 */
	private ErrorInfo transferCAGL(String effective_date, String rollout_branch_no, String rollout_acct_no, String rollout_acct_ccy, String gl_acct_no, String totalAmt) {
		ErrorInfo tempErrorInfo = new ErrorInfo();
		totalAmt = String.valueOf(Math.round(Double.parseDouble(totalAmt)));
		rebuildTransactionInfo(SIBS_081, rollout_branch_no);
		ABCS_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
		ABCS_TEMPLATE[18] = "BBHTLMONEYFNC";
		ABCS_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
		ABCS_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
		ABCS_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code();
		ABCS_TEMPLATE[34] = hostMessageLogInfo.getWs_ip();
		ABCS_TEMPLATE[42] = hostMessageLogInfo.getTeller_id();
		ABCS_TEMPLATE[43] = hostMessageLogInfo.getHost_tran_sn();
		ABCS_TEMPLATE[48] = hostMessageLogInfo.getHost_tran_code();
		ABCS_TEMPLATE[49] = hostMessageLogInfo.getHost_tran_date();
		ABCS_TEMPLATE[53] = hostMessageLogInfo.getApprover_id();
		ABCS_TEMPLATE[54] = rollout_branch_no;
		ABCS_TEMPLATE[64] = rollout_acct_no;
		ABCS_TEMPLATE[67] = totalAmt + "00";
		ABCS_TEMPLATE[68] = effective_date;
		ABCS_TEMPLATE[72] = gl_acct_no;
		ABCS_TEMPLATE[83] = String.valueOf(totalAmt + "00");
		ABCS_TEMPLATE[88] = "10000000" + "00";
		ABCS_TEMPLATE[89] = "10000000" + "00";
		ABCS_TEMPLATE[94] = "VND"; // base_ccy
		ABCS_TEMPLATE[106] = rollout_acct_ccy;
		ABCS_TEMPLATE[107] = rollout_acct_ccy;
		ABCS_TEMPLATE[250] = String.valueOf(totalAmt + "00");
		String hostSendMessage = AppUtils.convertArrayToString(ABCS_TEMPLATE);
		String hostReceiveMessage = "";
		this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		try {
			retMessage = as400Port.hostMessageSending(hostMessageLogInfo.getRef_channel(), hostSendMessage);
			if (retMessage != null) {
				if (retMessage.getErrCode() != null) {
					tempErrorInfo = CachedParam.mappingErrorCode(retMessage.getErrCode(), retMessage.getDescription(), Global.MESSAGE_TYPE_ABCS);
				} else {
					tempErrorInfo = CachedParam.mappingErrorCode(String.valueOf(Global.ERR_SYSTEM_OK), Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_ABCS);
				}
				hostReceiveMessage = AppUtils.convertArrayToString(retMessage.getArrString());
			} else {
				tempErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
				tempErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
			}

		} catch (RemoteException ex) {
			tempErrorInfo.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			tempErrorInfo.setErrorMessage(ex.toString());
		}
		this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		this.hostMessageLogInfo.setRef_cif_acct(rollout_acct_no);
		this.hostMessageLogInfo.setRef_amount(totalAmt);
		this.hostMessageLogInfo.setResp_code(String.valueOf(tempErrorInfo.getErrorCode()));
		this.hostMessageLogInfo.setResp_msg(tempErrorInfo.getErrorMessage());
		this.hostMessageLogInfo.setHostRequestMsg(hostSendMessage);
		this.hostMessageLogInfo.setHostResponseMsg(hostReceiveMessage);
		this.arrRetHOSTMessageLogInfo.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());
		return tempErrorInfo;
	}

	/**
	 * Add ref_number before approve salary payment
	 * 
	 * @param core_sn
	 * @param refNumber
	 * @param branch_code
	 * @param currency
	 * @return
	 */
	private ErrorInfo addRefNumber(String refNumber, String branch_code, String currency) {
		ErrorInfo tempErrorInfo = new ErrorInfo();
		rebuildTransactionInfo(SIBS_082, branch_code);
		// String hostTranCode = "28902";
		MBASE_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
		MBASE_TEMPLATE[18] = "BBMBSDDMNTFNC";
		MBASE_TEMPLATE[23] = "1";
		MBASE_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
		MBASE_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
		MBASE_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code();
		MBASE_TEMPLATE[32] = hostMessageLogInfo.getTeller_id();
		MBASE_TEMPLATE[40] = hostMessageLogInfo.getWs_name();
		MBASE_TEMPLATE[42] = branch_code;// hostMessageLogInfo.getBranch_code();
		MBASE_TEMPLATE[46] = hostMessageLogInfo.getHost_tran_code();
		MBASE_TEMPLATE[47] = "C";
		MBASE_TEMPLATE[49] = "1";
		MBASE_TEMPLATE[68] = branch_code;
		MBASE_TEMPLATE[69] = refNumber;
		MBASE_TEMPLATE[70] = currency;

		String hostSendMessage = AppUtils.convertArrayToString(MBASE_TEMPLATE);
		String hostReceiveMessage = "";
		this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		try {
			retMessage = as400Port.hostMessageSending(hostMessageLogInfo.getRef_channel(), hostSendMessage);
			if (retMessage != null) {
				if (retMessage.getErrCode() != null) {
					tempErrorInfo = CachedParam.mappingErrorCode(retMessage.getErrCode(), retMessage.getDescription(), Global.MESSAGE_TYPE_MBASE);
				} else {
					tempErrorInfo = CachedParam.mappingErrorCode(String.valueOf(Global.ERR_SYSTEM_OK), Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_MBASE);
				}
				hostReceiveMessage = AppUtils.convertArrayToString(this.retMessage.getArrString());
			} else {
				tempErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
				tempErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
			}

		} catch (RemoteException ex) {
			tempErrorInfo.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			tempErrorInfo.setErrorMessage(ex.toString());
		}
		this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

		this.hostMessageLogInfo.setRef_cif_acct(refNumber);
		this.hostMessageLogInfo.setResp_code(String.valueOf(tempErrorInfo.getErrorCode()));
		this.hostMessageLogInfo.setResp_msg(tempErrorInfo.getErrorMessage());
		this.hostMessageLogInfo.setHostRequestMsg(hostSendMessage);
		this.hostMessageLogInfo.setHostResponseMsg(hostReceiveMessage);
		this.arrRetHOSTMessageLogInfo.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());
		return tempErrorInfo;
	}

	/**
	 * Lock row before approve,online posting salary item
	 * 
	 * @param core_sn
	 * @param refNumber
	 * @param branch_code
	 * @param currency
	 * @return
	 */
	private ErrorInfo salaryLockApprove(String core_sn, String refNumber, String branch_code, String currency) {
		ErrorInfo tempErrorInfo = new ErrorInfo();
		rebuildTransactionInfo(SIBS_083, branch_code);
		// String hostTranCode = "26205";
		MBASE_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
		MBASE_TEMPLATE[18] = "BBMBSDDINQFNC";
		MBASE_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
		MBASE_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
		MBASE_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code();
		MBASE_TEMPLATE[32] = hostMessageLogInfo.getTeller_id();
		MBASE_TEMPLATE[40] = hostMessageLogInfo.getWs_name();
		MBASE_TEMPLATE[42] = hostMessageLogInfo.getBranch_code();
		MBASE_TEMPLATE[46] = hostMessageLogInfo.getHost_tran_code();

		MBASE_TEMPLATE[47] = "I";
		MBASE_TEMPLATE[48] = "R";
		MBASE_TEMPLATE[49] = "1";
		MBASE_TEMPLATE[50] = "N";
		MBASE_TEMPLATE[51] = "F";
		MBASE_TEMPLATE[57] = "C";

		MBASE_TEMPLATE[68] = branch_code;
		MBASE_TEMPLATE[69] = refNumber;
		MBASE_TEMPLATE[70] = currency;

		String hostSendMessage = AppUtils.convertArrayToString(MBASE_TEMPLATE);
		String hostReceiveMessage = "";

		this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		try {
			retMessage = as400Port.hostMessageSending(hostMessageLogInfo.getRef_channel(), hostSendMessage);
			if (retMessage != null) {
				if (retMessage.getErrCode() != null) {
					tempErrorInfo = CachedParam.mappingErrorCode(retMessage.getErrCode(), retMessage.getDescription(), Global.MESSAGE_TYPE_MBASE);
				} else {
					tempErrorInfo = CachedParam.mappingErrorCode(String.valueOf(Global.ERR_SYSTEM_OK), Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_MBASE);
				}
				hostReceiveMessage = AppUtils.convertArrayToString(this.retMessage.getArrString());
			} else {
				tempErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
				tempErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
			}

		} catch (RemoteException ex) {
			tempErrorInfo.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			tempErrorInfo.setErrorMessage(ex.toString());
		}
		this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		this.hostMessageLogInfo.setRef_cif_acct(refNumber);
		this.hostMessageLogInfo.setResp_code(String.valueOf(tempErrorInfo.getErrorCode()));
		this.hostMessageLogInfo.setResp_msg(tempErrorInfo.getErrorMessage());
		this.hostMessageLogInfo.setHostRequestMsg(hostSendMessage);
		this.hostMessageLogInfo.setHostResponseMsg(hostReceiveMessage);
		this.arrRetHOSTMessageLogInfo.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());
		return tempErrorInfo;
	}

	/**
	 * Approve for Salary payment,on this step money will transfer from GL account (temporary) to each bnfc_account.
	 * 
	 * @param core_sn
	 * @param refNumber
	 * @param branch_code
	 * @param currency
	 * @return
	 */
	private ErrorInfo salaryApprove(String core_sn, String refNumber, String branch_code, String currency) {
		ErrorInfo tempErrorInfo = new ErrorInfo();
		// String hostTranCode = "28903";
		rebuildTransactionInfo(SIBS_084, branch_code);
		MBASE_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
		MBASE_TEMPLATE[18] = "BBMBSDDMNTFNC";
		MBASE_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
		MBASE_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
		MBASE_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code();
		MBASE_TEMPLATE[32] = hostMessageLogInfo.getTeller_id();
		MBASE_TEMPLATE[40] = hostMessageLogInfo.getWs_name();
		MBASE_TEMPLATE[42] = hostMessageLogInfo.getBranch_code();
		MBASE_TEMPLATE[46] = hostMessageLogInfo.getHost_tran_code();
		MBASE_TEMPLATE[67] = "";
		MBASE_TEMPLATE[68] = hostMessageLogInfo.getBranch_code();
		MBASE_TEMPLATE[69] = refNumber;
		MBASE_TEMPLATE[70] = currency;

		String hostSendMessage = AppUtils.convertArrayToString(MBASE_TEMPLATE);
		String hostReceiveMessage = "";

		this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		try {
			retMessage = as400Port.hostMessageSending(hostMessageLogInfo.getRef_channel(), hostSendMessage);
			if (retMessage != null) {
				if (retMessage.getErrCode() != null) {
					tempErrorInfo = CachedParam.mappingErrorCode(retMessage.getErrCode(), retMessage.getDescription(), Global.MESSAGE_TYPE_MBASE);
				} else {
					tempErrorInfo = CachedParam.mappingErrorCode(String.valueOf(Global.ERR_SYSTEM_OK), Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_MBASE);
				}
				hostReceiveMessage = AppUtils.convertArrayToString(this.retMessage.getArrString());
			} else {
				tempErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
				tempErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
			}

		} catch (RemoteException ex) {
			tempErrorInfo.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			tempErrorInfo.setErrorMessage(ex.toString());
		}
		this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		this.hostMessageLogInfo.setRef_cif_acct(refNumber);
		this.hostMessageLogInfo.setResp_code(String.valueOf(tempErrorInfo.getErrorCode()));
		this.hostMessageLogInfo.setResp_msg(tempErrorInfo.getErrorMessage());
		this.hostMessageLogInfo.setHostRequestMsg(hostSendMessage);
		this.hostMessageLogInfo.setHostResponseMsg(hostReceiveMessage);
		this.arrRetHOSTMessageLogInfo.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());
		return tempErrorInfo;
	}

	/**
	 * Online posting to GL,after this step teller can check GL account to see balance change.
	 * 
	 * @param core_sn
	 * @param refNumber
	 * @param branch_code
	 * @param currency
	 * @return
	 */
	private ErrorInfo onlinePosting(String core_sn, String refNumber, String branch_code, String currency) {
		ErrorInfo tempErrorInfo = new ErrorInfo();
		// String hostTranCode = "28904";
		rebuildTransactionInfo(SIBS_085, branch_code);
		MBASE_TEMPLATE[2] = hostMessageLogInfo.getWs_ip();
		MBASE_TEMPLATE[18] = "BBMBSDDMNTFNC";
		MBASE_TEMPLATE[25] = hostMessageLogInfo.getTeller_id();
		MBASE_TEMPLATE[26] = hostMessageLogInfo.getWs_name();
		MBASE_TEMPLATE[27] = hostMessageLogInfo.getHost_tran_code();
		MBASE_TEMPLATE[32] = hostMessageLogInfo.getTeller_id();
		MBASE_TEMPLATE[40] = hostMessageLogInfo.getWs_name();
		MBASE_TEMPLATE[42] = hostMessageLogInfo.getBranch_code();
		MBASE_TEMPLATE[46] = hostMessageLogInfo.getHost_tran_code();
		MBASE_TEMPLATE[47] = "C";
		MBASE_TEMPLATE[69] = refNumber;
		MBASE_TEMPLATE[70] = currency;

		String hostSendMessage = AppUtils.convertArrayToString(MBASE_TEMPLATE);
		String hostReceiveMessage = "";

		this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		try {
			retMessage = as400Port.hostMessageSending(hostMessageLogInfo.getRef_channel(), hostSendMessage);
			if (retMessage != null) {
				if (retMessage.getErrCode() != null) {
					// if (retMessage.getErrCode().equals("MBL1404")) {
					// // fail to release record
					// tempErrorInfo.setErrorCode(ERR_SYSTEM_OK);
					// } else {
					// tempErrorInfo =
					// CachedParam.mappingErrorCode(retMessage.getErrCode(),
					// retMessage.getDescription(), Global.MESSAGE_TYPE_MBASE);
					// }
					tempErrorInfo = CachedParam.mappingErrorCode(retMessage.getErrCode(), retMessage.getDescription(), Global.MESSAGE_TYPE_MBASE);
				} else {
					tempErrorInfo = CachedParam.mappingErrorCode(String.valueOf(Global.ERR_SYSTEM_OK), Global.ERR_SYSTEM_OK_MSG, Global.MESSAGE_TYPE_MBASE);
				}
				hostReceiveMessage = AppUtils.convertArrayToString(this.retMessage.getArrString());
			} else {
				tempErrorInfo.setErrorCode(ERR_SYSTEM_UNKNOWN_ERROR);
				tempErrorInfo.setErrorMessage("ERR_SYSTEM_UNKNOWN_ERROR");
			}

		} catch (RemoteException ex) {
			tempErrorInfo.setErrorCode(Global.ERR_CM_EXCEPTION_WHEN_CALL_ONECONNECT);
			tempErrorInfo.setErrorMessage(ex.toString());
		}
		this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
		this.hostMessageLogInfo.setRef_cif_acct(refNumber);
		this.hostMessageLogInfo.setResp_code(String.valueOf(tempErrorInfo.getErrorCode()));
		this.hostMessageLogInfo.setResp_msg(tempErrorInfo.getErrorMessage());
		this.hostMessageLogInfo.setHostRequestMsg(hostSendMessage);
		this.hostMessageLogInfo.setHostResponseMsg(hostReceiveMessage);
		this.arrRetHOSTMessageLogInfo.add((HOSTMessagLogInfo) hostMessageLogInfo.clone());
		return tempErrorInfo;
	}

}
