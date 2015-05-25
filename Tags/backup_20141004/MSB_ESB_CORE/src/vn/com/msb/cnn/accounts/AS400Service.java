package vn.com.msb.cnn.accounts;

import java.net.Socket;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import vn.com.msb.as400.dsp.ATMMessageFactory;
import vn.com.msb.as400.dsp.DSPField;
import vn.com.msb.as400.dsp.DSPMessageConstant;
import vn.com.msb.as400.dsp.DSPPackager;
import vn.com.msb.as400.dsp.MessageFactory;
import vn.com.msb.as400.dsp.TreasuryMessageFactory;
import vn.com.msb.cnn.processor.CCProcessor;
import vn.com.msb.cnn.processor.HostProcessor;
import vn.com.msb.cnn.processor.IBSProcessor;
import vn.com.msb.cnn.processor.MtradingProcessor;
import vn.com.msb.cnn.utils.AccountUtils;
import vn.com.msb.cnn.utils.HostConstants;
import vn.com.msb.cnn.utils.Messages;

public class AS400Service {
	private final String PARAM_CB_STATUS = "cb_status";
	private final String PARAM_CB_PORT_NUMBER = "port_number";

	private ArrayList<HostParameter> paramList;

	public void setParamList(ArrayList<HostParameter> paramList) {
		this.paramList = paramList;
	}

	public ArrayList<HostParameter> getParamList() {
		return paramList;
	}

	private HostParameter findHostParamByName(String name) {
		HostParameter retHostParameter = null;
		for (int i = 0; i < paramList.size(); i++) {
			if (paramList.get(i).getName().equalsIgnoreCase(name)) {
				retHostParameter = paramList.get(i);
			}
		}
		return retHostParameter;
	}

	private HostParameter findHostParamByNameAndValue(String name, String value) {
		HostParameter retHostParameter = null;
		for (int i = 0; i < paramList.size(); i++) {
			if (paramList.get(i).getName().equalsIgnoreCase(name) && paramList.get(i).getValue().equalsIgnoreCase(value)) {
				retHostParameter = paramList.get(i);
			}
		}
		return retHostParameter;
	}

	private HostParameter getCoreBankPort(HostParameter cbStatus, String name, String channel) {
		// Check them dieu kien ve on/off
		HostParameter cbPort = findHostParamByNameAndValue(name, channel);
		// Voi tham so ve port neu online thi lay PARAM1,offline thi lay PARAM2
		if (cbPort != null) {
			if (cbStatus.getValue().equalsIgnoreCase("off")) {
				cbPort.setParam1(cbPort.getParam2());
			}
		} else {
			// Neu khong tim thay theo channel trong message thi tim theo
			// channel default
			return findHostParamByNameAndValue(name, "DEF_PORT");
		}
		return cbPort;
	}

	private String getCoreBankIP(HostParameter cbStatus) {
		// Voi tham so ve IP neu online thi lay PARAM1,offline thi lay PARAM2
		if (cbStatus.getValue().equalsIgnoreCase("on")) {
			return cbStatus.getParam1();
		} else {
			return cbStatus.getParam2();
		}
	}

	public Messages transferFromCASAToGL(String channel, String hostIP, String hostName, String teller, String manager, String sequence, String transDate, String branchCode,
			String fromAccount, String amount, String fromCif, String GLAccount, String vatFee, String serviceFee, String remarks, String fromName, String fromId,
			String toAccount, String toName, String toId, String toAddress, String toIdIssueDate, String toIdIssuePlace, String transCode, String rm_number) {
		String refId = rm_number;
		String[] messageArray = MessageFactory.createOL2Message(hostIP, hostName, teller, manager, sequence, transDate, branchCode, fromAccount, amount, fromCif, GLAccount,
				vatFee, serviceFee, remarks, fromName, fromId, toAccount, toName, toId, toAddress, toIdIssueDate, toIdIssuePlace, transCode, refId);

		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		Messages messages = HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));

		// return refId to solve reversable transaction
		if (messages != null && (messages.getErrCode() == null || messages.getErrCode().equalsIgnoreCase("0") || messages.getErrCode().equalsIgnoreCase("/0000000"))) {
			messages.setDescription(refId);
		}
		return messages;
	}

	// Sua lai thi viet tren tcpoutflow
	public String getAccount(String act_seq, String branchCode, String currencyCode, String groupCode) {
		return AccountUtils.constructAccountNumber((int) Integer.parseInt(act_seq), branchCode.substring(0, 3), currencyCode, groupCode);
	}

	// Sua lai thi viet tren tcpoutflow
	public String getFDReceiptAccount(String act_seq, String branchCode, String currencyCode, String groupCode) {
		return AccountUtils.constructFDAccountNumber((int) Integer.parseInt(act_seq), branchCode.substring(0, 3), currencyCode, groupCode);
	}

	public Messages createCA(String channel, String hostDate, String hostId, String branchCode, String currencyType, String cifNumber, String accountNumber, String accountType,
			String accountName, String modeOfOperation) {
		String[] messageArray = MessageFactory.createCAMessage(hostId, accountNumber, hostDate, branchCode, currencyType, cifNumber, accountType, accountName, modeOfOperation);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages createSA(String channel, String hostDate, String hostId, String branchCode, String currencyType, String cifNumber, String accountNumber, String accountType,
			String accountName, String modeOfOperation) {
		String[] messageArray = MessageFactory.createSAMessage("", hostId, branchCode, hostDate, accountNumber, cifNumber, accountType, accountName, currencyType, modeOfOperation);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages createFD(String channel, String hostDate, String hostId, String branchCode, String currencyType, String cifNumber, String accountNumber, String accountType,
			String accountName, String modeOfOperation) {
		String[] messageArray = MessageFactory.createFDMessage("", hostId, branchCode, hostDate, accountNumber, cifNumber, accountName, currencyType, modeOfOperation);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages transferFromCASAToCASA(String channel, String branchCode, String creditAccount, String creditAmount, String creditCurrency, String creditRate,
			String debitAccount, String debitAmount, String debitCurrency, String debitRate, String manager, String description, String sequence, String teller, String transDate,
			String transCode, String vatFee, String serviceFee) {
		String[] messageArray = MessageFactory.createBranchTranferMessage(branchCode, creditAccount, creditAmount, creditCurrency, creditRate, debitAccount, debitAmount,
				debitCurrency, debitRate, manager, description, sequence, teller, transDate, transCode, vatFee, serviceFee);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages joinCA(String channel, String strteller, String strBankcode, String transDate, String strAccount, String strCifnumber, String strNameAcount, String strcurrCode) {
		String[] messageArray = MessageFactory.createJoinAccountCAMessage("", strteller, strBankcode, transDate, strAccount, strCifnumber, strNameAcount, strcurrCode);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages joinSA(String channel, String strteller, String strBankcode, String transDate, String strAccount, String strCifnumber, String strNameAcount, String strcurrCode) {
		String[] messageArray = MessageFactory.createJoinAccountSAMessage("*LINX", strteller, strBankcode, transDate, strAccount, strCifnumber, strNameAcount, strcurrCode);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages searchCustomerByCif(String channel, String teller, String hostName, String branchCode, String cifNumber) {
		String[] messageArray = MessageFactory.searchCustomerByCifMessage(teller, hostName, branchCode, cifNumber);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages searchCustomerById(String channel, String teller, String hostName, String branchCode, String idNumber) {
		String[] messageArray = MessageFactory.searchCustomerByIDMessage(teller, hostName, branchCode, idNumber);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages searchE_ContactByCif(String channel, String strteller, String strBankcode, String strsearch_key) {
		String[] messageArray = MessageFactory.searchMobileByCifMessage("", strteller, strBankcode, strsearch_key);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages searchAccountByCif(String channel, String teller, String hostName, String branchCode, String cifNumber) {
		String[] messageArray = MessageFactory.searchAccountByCifMessage(teller, hostName, branchCode, cifNumber);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages searchAccountMoreByCif(String channel, String teller, String hostName, String branchCode, String cifNumber, String lastAccountType, String lastAccountNumber,
			String relationShip) {
		String[] messageArray = MessageFactory.searchAccountMoreByCifMessage(teller, hostName, branchCode, cifNumber, lastAccountType, lastAccountNumber, relationShip);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages searchCustomerInformationByID(String channel, String teller, String hostName, String branchCode, String cifNumber) {
		String[] messageArray = MessageFactory.searchCustomerInformationByCifMessage(teller, hostName, branchCode, cifNumber);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages lockTransferAccountFromCifToAnother(String channel, String teller, String branchCode, String sourceCif, String accountToMove) {
		String[] messageArray = MessageFactory.createLockTranferCifMessage(teller, branchCode, sourceCif, accountToMove);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages transferAccountFromCifToAnother(String channel, String teller, String branchCode, String sourceCif, String accountToMove, String destinationCif,
			String destinationName) {
		String[] messageArray = MessageFactory.createTranferFromCifToAnotherMessage(teller, branchCode, sourceCif, accountToMove, destinationCif, destinationName);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages lockUpdateE_Contact(String channel, String teller, String branchCode, String strCifNumber, String sequenceNo) {
		String[] messageArray = MessageFactory.createLockPhoneMessage("", teller, branchCode, strCifNumber, sequenceNo);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages updateE_Contact(String channel, String teller, String branchCode, String strCifNumber, String sequenceNo, String strEcontactType, String strNewEcontact) {
		String[] messageArray = MessageFactory.createUpdateEcontactMessage("", teller, branchCode, strCifNumber, sequenceNo, strEcontactType, strNewEcontact);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages addE_Contact(String channel, String teller, String branchCode, String strCifNumber, String strEcontactType, String strEcontact) {
		String[] messageArray = MessageFactory.createAddCifEContactMessage("", teller, branchCode, strCifNumber, strEcontactType, strEcontact);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages createCif(String channel, String teller, String hostName, String branchCode, String strIdNo, String strIdType, String strIdDateIssued,
			String strIdCountryIssued, String strIdPlaceIssued, String strSurname, String strAfterSurname, String strAddressLine1, String strAddressLine2, String strAddressLine3,
			String strAddressLine4_codeCheckCust, String strAddressLine5, String strNationality, String strBirthday, String strRaceCode, String strMaritalStatus, String strGender,
			String _2strOccupationCode, String strPlaceOfBirth, String strTypeElectronicAddress1, String strTypeElectronicAddress2, String strTypeElectronicAddress3,
			String strElectronicAddress1, String strElectronicAddress2, String strElectronicAddress3, String hostDate, String strBirthday_Full, String strIdDateIssued_Full,
			String _1strOccupationCode, String strProvince) {

		String[] messageArray = MessageFactory.createCifMessage(teller, hostName, branchCode, strIdNo, strIdType, strIdDateIssued, strIdCountryIssued, strIdPlaceIssued,
				strSurname, strAfterSurname, strAddressLine1, strAddressLine2, strAddressLine3, strAddressLine4_codeCheckCust, strAddressLine5, strNationality, strBirthday,
				strRaceCode, strMaritalStatus, strGender, _2strOccupationCode, strPlaceOfBirth, strTypeElectronicAddress1, strTypeElectronicAddress2, strTypeElectronicAddress3,
				strElectronicAddress1, strElectronicAddress2, strElectronicAddress3, hostDate, strBirthday_Full, strIdDateIssued_Full, _1strOccupationCode, strProvince);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages miniStatement(String channel, String teller, String branchCode, String recordNumber, String accountNumber, String accountType) {
		String[] messageArray = MessageFactory.createMiniStatementMessage(teller, branchCode, recordNumber, accountNumber, accountType);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages ddMasterInquiry(String channel, String teller, String hostName, String branchCode, String accountNumber, String accountType) {
		String[] messageArray = MessageFactory.searchCustomerByAccountMessage(teller, hostName, branchCode, accountNumber, accountType);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages linkCard(String channel, String teller, String branchCode, String strCardNumber, String strAccountTag, String strAccountType, String strUsage,
			String strCurrency, String strBranchTag, String strCIFNo, String strIDNumber, String strIDType, String strName, String strAddress, String strProduct,
			String strAnnualFee, String strVIP, String strAddressLine2, String strAddressLine3, String strAddressLine4) {
		String[] messageArray = MessageFactory.createLinkCardMessage(teller, branchCode, strCardNumber, strAccountTag, strAccountType, strUsage, strCurrency, strBranchTag,
				strCIFNo, strIDNumber, strIDType, strName, strAddress, strProduct, strAnnualFee, strVIP, strAddressLine2, strAddressLine3, strAddressLine4);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));

	}

	public Messages linkMasterCard(String channel, String teller, String branchCode, String strAccountTag, String strAccountType, String strUsage, String strCurrency,
			String strBranchTag, String strCIFNo, String strIDNumber, String strIDType, String strName, String strAddress, String strProduct, String strAnnualFee, String strVIP,
			String strAddressLine2, String strAddressLine3, String strAddressLine4) {
		String[] messageArray = ATMMessageFactory.createLinkMasterCardMessage(teller, branchCode, strAccountTag, strAccountType, strUsage, strCurrency, strBranchTag, strCIFNo,
				strIDNumber, strIDType, strName, strAddress, strProduct, strAnnualFee, strVIP, strAddressLine2, strAddressLine3, strAddressLine4);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages cardActivation(String channel, String teller, String branchCode, String cardNumber, String cifNumber, String idNo, String idType) {
		String[] messageArray = ATMMessageFactory.createCardActivationMessage(teller, branchCode, cardNumber, cifNumber, idNo, idType);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages lockUpdateMainternanceNewCA(String channel, String teller, String branchCode, String accountNo) {
		String[] messageArray = MessageFactory.createLockUpdatingIntroducerCodeInNewCAMessage(teller, branchCode, accountNo);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages updateMainternanceNewCA(String channel, String teller, String branchCode, String accountNo, String introducerCode, String expense, String accountName) {
		String[] messageArray = MessageFactory.createUpdatingIntroducerCodeInNewCAMessage(teller, branchCode, accountNo, introducerCode, expense, accountName);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages lockUpdateMainternanceCA(String channel, String teller, String branchCode, String accountNo) {
		String[] messageArray = MessageFactory.createLockUpdatingIntroducerCodeInCAMessage(teller, branchCode, accountNo);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages updateMainternanceCA(String channel, String teller, String branchCode, String accountNo, String introducerCode, String expense, String accountName) {
		String[] messageArray = MessageFactory.createUpdatingIntroducerCodeInCAMessage(teller, branchCode, accountNo, introducerCode, expense, accountName);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages HostMessageSending(String channel, String message) {
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), message.split(HostConstants.separator), channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages TFMessageSending(String channel, String message) {
		HostParameter hostParam = findHostParamByNameAndValue("port_number", channel.toUpperCase());
		// return
		// HostProcessor.sendTFMessage(Integer.parseInt(hostParam.getParam1()),
		// message.split(HostConstants.separator), channel.toUpperCase());
		return null;
	}

	public Messages getInformationByIDForIBSRegistration(String channel, String teller, String hostName, String bankCode, String cert_code) {
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return IBSProcessor.getCustomerInfoFullByCertCode(hostParam, channel, teller, hostName, bankCode, cert_code, getCoreBankIP(cbStatus));
	}

	public Messages getInformationByCIFForIBSRegistration(String channel, String teller, String hostName, String bankCode, String cif_no) {
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return IBSProcessor.getCustomerInfoFullByCifNo(hostParam, channel, teller, hostName, bankCode, cif_no, getCoreBankIP(cbStatus));
	}

	public Messages reversableTransaction(String channel, String teller, String manager, String branchCode, String strFromAccount, String strToAccount, String transDate,
			String strDescription, String strTranferAmount, String strJournalSeq, String orgJournalSeq, String strTransCode) {
		String[] messageArray = MessageFactory.createBranchReversableTranferMessage("*LINX", teller, manager, branchCode, strFromAccount, strToAccount, transDate, strDescription,
				strTranferAmount, strJournalSeq, orgJournalSeq, strTransCode);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages reversableOL2Transaction(String channel, String hostIP, String hostName, String teller, String manager, String sequence, String oldSequence, String transDate,
			String branchCode, String fromAccount, String amount, String amountMustPay, String fromCif, String GLAccount, String vatFee, String serviceFee, String remarks,
			String fromName, String fromId, String toAccount, String toName, String toId, String toAddress, String toIdIssueDate, String toIdIssuePlace, String strTransCode,
			String refId) {
		String[] messageArray = MessageFactory.createOL2ReservationMessage(hostIP, hostName, teller, manager, sequence, oldSequence, transDate, branchCode, fromAccount, amount,
				amountMustPay, fromCif, GLAccount, vatFee, serviceFee, remarks, fromName, fromId, toAccount, toName, toId, toAddress, toIdIssueDate, toIdIssuePlace, strTransCode,
				refId);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages createFDReceipt(String channel, String teller, String branchCode, String journalSeq, String transDate, String fdGroupAccount, String depositAmt,
			String effectiveDate, String fdReceipt, String rate, String intPaymentToAcctno, String printTranferToAcctno, String currency, String productCode, String autoRenew,
			String intPaymentMode, String fdType, String remark) {
		String[] messageArray = MessageFactory.createFDReceiptMessage(teller, branchCode, journalSeq, transDate, fdGroupAccount, depositAmt, effectiveDate, fdReceipt, rate,
				intPaymentToAcctno, printTranferToAcctno, currency, productCode, autoRenew, intPaymentMode, fdType, remark);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages loanAccountInquiry(String channel, String teller, String branchCode, String hostName, String accountNumber) {
		String[] messageArray = MessageFactory.searchLoanAccountMessage(teller, hostName, branchCode, accountNumber);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages accountInquiry(String channel, String teller, String account, String transDate) {
		String[] messageArray = MessageFactory.createCAInQuiryMessage("*LINX", teller, account, transDate);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages debitAdvice(String channel, String teller, String branchCode, String sequence, String transDate, String manager, String accountNumber, String effectiveDate,
			String crAmount, String glAccount, String drAmount, String buyRate, String sellRate, String debitCurrency, String glCurrency, String remark, String transcode) {
		String[] messageArray = TreasuryMessageFactory.debitAdvice(teller, branchCode, transDate, sequence, manager, accountNumber, crAmount, effectiveDate, glAccount, drAmount,
				buyRate, sellRate, debitCurrency, glCurrency, remark, transcode);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages creditAdvice(String channel, String teller, String branchCode, String sequence, String transDate, String manager, String accountNumber, String effectiveDate,
			String crAmount, String glAccount, String drAmount, String buyRate, String sellRate, String debitCurrency, String glCurrency, String remark, String transcode) {
		String[] messageArray = TreasuryMessageFactory.creditAdvice(teller, branchCode, transDate, sequence, manager, accountNumber, crAmount, effectiveDate, glAccount, drAmount,
				buyRate, sellRate, debitCurrency, glCurrency, remark, transcode);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public int transferGLtoGL(String channel, String refID, String debitGLBranch, String debitGLAccount, String creditGLBranch, String creditGLAccount, String amount,
			String currency, String comments, String teller, String manager, String hostDate, String reconcileGL, String transactionOffice, String customerTypeCode,
			String account, String businessDeptCode, String branchCode5) {
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return MtradingProcessor.tranferGLtoGL(hostParam, channel, refID, debitGLBranch, debitGLAccount, creditGLBranch, creditGLAccount, amount, currency, comments, teller,
				manager, hostDate, reconcileGL, transactionOffice, customerTypeCode, account, businessDeptCode, branchCode5, getCoreBankIP(cbStatus));
	}

	public Messages changeCardService(String channel, String teller, String branchCode, String cardNumber, String serviceName, String cifNumber) {
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return CCProcessor.cardChangeServiceName(hostParam, channel, teller, cardNumber, cardNumber, serviceName, cifNumber, getCoreBankIP(cbStatus));
	}

	public Messages markHotCard(String channel, String teller, String branchCode, String cardNumber, String typeLock, String comment) {
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return CCProcessor.markHotCard(hostParam, channel, teller, cardNumber, cardNumber, typeLock, comment, getCoreBankIP(cbStatus));
	}

	public Messages reActiveCard(String channel, String teller, String branchCode, String cardNumber) {
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return CCProcessor.reActiveCard(hostParam, channel, teller, cardNumber, cardNumber, getCoreBankIP(cbStatus));
	}

	public Messages createHoldMessage(String channel, String teller, String account, String expiryDate, String description, String amount, String code) {
		String[] messageArray = MessageFactory.createHoldMessage("*LINX", teller, account, expiryDate, description, amount, code);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages createLockForUnHoldMessage(String channel, String teller, String account, String sequence) {
		String[] messageArray = MessageFactory.createLockMessage("*LINX", teller, account, sequence);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages createUnHoldMessage(String channel, String teller, String account, String description, String amount, String code, String sequence, String actionCode,
			String expireDate) {
		String[] messageArray = MessageFactory.createUnHoldMessage("*LINX", teller, account, description, amount, code, sequence, actionCode, expireDate);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages createAddCifGroupMainternanceMessage(String channel, String teller, String branchCode, String cifCorp, String seq, String cifEmp, String relationalEmpLev,
			String relationalType, String relationalCorpLev) {
		String[] messageArray = MessageFactory.createCifGroupManagementAdditionMessage(teller, branchCode, cifCorp, seq, cifEmp, relationalEmpLev, relationalType,
				relationalCorpLev);
		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages chargeCollection(String channel, String teller, String branchCode, String transDate, String sequence, String glAccount, String totalNetCharges,
			String baseCurrency, String glCurrency, String customerCurrency, String accountNumber, String drTotalNetCharge, String totalCharge, String vat, String remark,
			String transcode) {
		String[] messageArray = TreasuryMessageFactory.chargeCollection(teller, branchCode, transDate, sequence, glAccount, totalNetCharges, baseCurrency, glCurrency,
				customerCurrency, accountNumber, drTotalNetCharge, totalCharge, vat, remark, transcode);

		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));
	}

	public Messages transferFromWUToCA(String channel, String hostIP, String hostName, String teller, String manager, String sequence, String transDate, String branchCode,
			String toAccount, String crAmount, String ttAmount, String toCif, String GLAccount, String vatFee, String serviceFee, String buyRate, String sendRate,
			String ttSendRate, String remarks, String toName, String toId, String toAddress, String toIdIssueDate, String toIdIssuePlace, String transCode, String refWU,
			String sendingCurrencyType, String receivingCurrencyType, String refId) {
		// String refId = HostUtils.buildRefId();
		// HostUtils._increaseSequence()

		String[] messageArray = MessageFactory.createIO5Message(hostIP, hostName, teller, manager, sequence, transDate, branchCode, toAccount, toName, toId, toAddress, crAmount,
				ttAmount, toCif, GLAccount, vatFee, serviceFee, buyRate, sendRate, ttSendRate, remarks, transCode, refId, refWU, sendingCurrencyType, receivingCurrencyType,
				toIdIssueDate, toIdIssuePlace);

		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, channel.toUpperCase());
		Messages messages = HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, channel.toUpperCase(), getCoreBankIP(cbStatus));

		// return refId to solve reversable transaction
		if (messages != null && (messages.getErrCode().equalsIgnoreCase("0") || messages.getErrCode().equalsIgnoreCase("/0000000"))) {
			messages.setDescription(refId);
		}
		return messages;
	}

	public String[] createDDInquiryMessage(Socket sk, String teller, String hostName, String branchCode, String accountNumber, String accountType) throws Exception {
		String[] strReturn = new String[3];

		String[] strValue = MessageFactory.searchCustomerByAccountMessage(teller, hostName, branchCode, accountNumber, accountType);
		byte[] btHold = DSPPackager.PACKAGER_MBASE_26161I.pack(strValue);
		// Put data to Host
		sk.getOutputStream().write(btHold);

		// Get response from Host
		byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk.getInputStream());

		// Check the message status
		DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(btResponse, DSPPackager.PACKAGER_MBASE_26161I);

		DSPField fld[] = mbsdPackager.getFieldDefinitionList();
		String strMsgStatus = fld[DSPMessageConstant.IDX_MESSAGE_STATUS].unpack(btResponse);

		System.out.println(mbsdPackager.unpackMultiRecords(btResponse, DSPMessageConstant.MBSD_NAME, "|"));//

		if (!strMsgStatus.equals(DSPMessageConstant.STATUS_SUCCESS)) {

			throw new Exception(strMsgStatus);
		}

		return strReturn;
	}

	public Messages changeCIFInformationMessage(String obj) {

		JSONObject cifObj = JSONObject.fromObject(obj);

		String[] messageArray = MessageFactory.changeCIFInformationMessage(cifObj.getString("teller"), "localhost", cifObj.getString("branchCode"), cifObj.getString("cifNumber"),
				cifObj.getString("salution"), cifObj.getString("shortName"), cifObj.getString("fullName1"), cifObj.getString("fullName2"), cifObj.getString("motherName"),
				cifObj.getString("inquiryIdCode"), cifObj.getString("insiderCode"), cifObj.getString("vipCustomerCode"), cifObj.getString("codonglonMSB"),
				cifObj.getString("thamsochuadung1"), cifObj.getString("doanhnghiepvuavanho"), cifObj.getString("tochuctaichinhvanganhang"), cifObj.getString("cacbenlienquan"),
				cifObj.getString("doanhnghieplon"), cifObj.getString("khachhangmuabaohiem"), cifObj.getString("thamsochuadung2"), cifObj.getString("baohiem"),
				cifObj.getString("buuchinhvienthong"), cifObj.getString("hangkhong"), cifObj.getString("nuoitrongthuysan"), cifObj.getString("congnghiepchebien"),
				cifObj.getString("nganhdichvu"), cifObj.getString("nganhhanghai"), cifObj.getString("nganhxaydung"), cifObj.getString("addressSequence"),
				cifObj.getString("residentCode"), cifObj.getString("nationality"), cifObj.getString("raceCode"), cifObj.getString("religionCode"),
				cifObj.getString("languageIdentifier"), cifObj.getString("nameFormat"), cifObj.getString("param1"), cifObj.getString("param2"),
				cifObj.getString("customerFirstNameStart"), cifObj.getString("customerFirstNameLength"), cifObj.getString("param3"), cifObj.getString("param4"),
				cifObj.getString("param5"), cifObj.getString("param6"), cifObj.getString("param7"), cifObj.getString("param8"), cifObj.getString("param9"),
				cifObj.getString("param10"), cifObj.getString("dateCIFCreated"), cifObj.getString("dateCIFReview"), cifObj.getString("uid"), cifObj.getString("idType"),
				cifObj.getString("dateOfBirthShort"), cifObj.getString("placeOfBirth"), cifObj.getString("maritalStatus"), cifObj.getString("maritalDateShorted"),
				cifObj.getString("decreaseCustomerFlag"), cifObj.getString("decreaseCustomerFlagDate"), cifObj.getString("gender"), cifObj.getString("cfClass"),
				cifObj.getString("individual"), cifObj.getString("occupationCode"), cifObj.getString("subClass"), cifObj.getString("customerRatingCode"),
				cifObj.getString("combineCycle"), cifObj.getString("federalWithholdingDate"), cifObj.getString("tellerCreated"), cifObj.getString("hostNameCreated"),
				cifObj.getString("timeCreated"), cifObj.getString("customerStatus"), cifObj.getString("customerStatusDate"), cifObj.getString("foreignAddress"),
				cifObj.getString("addressLine1"), cifObj.getString("addressLine2"), cifObj.getString("addressLine3"), cifObj.getString("provincePostalCode"),
				cifObj.getString("homePhoneNo"), cifObj.getString("officePhoneNo"), cifObj.getString("handPhoneNo"), cifObj.getString("issuesDateShort"),
				cifObj.getString("issuesCountry"), cifObj.getString("issuesPlace"), cifObj.getString("tax"), cifObj.getString("addressLine4"), cifObj.getString("addressLine5"),
				cifObj.getString("dateOfBirthFull"), cifObj.getString("issuesDateFull"), cifObj.getString("businessType1"), cifObj.getString("businessType2"),
				cifObj.getString("businessType3"), cifObj.getString("cfsubClass"), cifObj.getString("businessCode"));

		HostParameter cbStatus = findHostParamByName(PARAM_CB_STATUS);
		HostParameter hostParam = getCoreBankPort(cbStatus, PARAM_CB_PORT_NUMBER, cifObj.getString("channel").toUpperCase());
		return HostProcessor.sendMessage(Integer.parseInt(hostParam.getParam1()), messageArray, cifObj.getString("channel").toUpperCase(), getCoreBankIP(cbStatus));
	}

	// private void testJson() {
	// JSONObject objTest1 = new JSONObject();
	// objTest1.put("teller", "TT1");
	//
	// JSONObject objTest2 = JSONObject.fromObject(objTest1.toString());
	// System.out.println(objTest2.getString("teller"));
	//
	// }
	//
	// public static void main(String arg[]) {
	// AS400Service service = new AS400Service();
	// Messages msg = service.createCifMainternanceMessage("");
	// // String teller = "TT110001";
	// // String hostName = "local";
	// // String branchCode = "110";
	// // String accountNumber = "11001010000105";
	// // String accountType = "D";
	//
	// // Messages retMessage = service.ddMasterInquiry("IBS123", "TT110001",
	// // "local", "110", "11001010000105", "D");
	// //
	// // String retArray[] = retMessage.getArrString();
	// // String retMsg = "";
	// // for (int i = 0; i < retArray.length; i++) {
	// // retMsg += retArray[i];
	// // }
	// // System.out.println(retMsg);
	//
	// }

}
