/**
 * AS400_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.as400gw;

public interface AS400_PortType extends java.rmi.Remote {
	public seatechit.msbgateway.proxy.as400gw.Messages transferFromCASAToGL(java.lang.String channel, java.lang.String hostIP, java.lang.String hostName, java.lang.String teller,
			java.lang.String manager, java.lang.String sequence, java.lang.String transDate, java.lang.String branchCode, java.lang.String fromAccount, java.lang.String amount,
			java.lang.String fromCif, java.lang.String GLAccount, java.lang.String vatFee, java.lang.String serviceFee, java.lang.String remarks, java.lang.String fromName, java.lang.String fromId,
			java.lang.String toAccount, java.lang.String toName, java.lang.String toId, java.lang.String toAddress, java.lang.String toIdIssueDate, java.lang.String toIdIssuePlace,
			java.lang.String transCode) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages createFD(java.lang.String channel, java.lang.String hostDate, java.lang.String hostId, java.lang.String branchCode,
			java.lang.String currencyType, java.lang.String cifNumber, java.lang.String accountNumber, java.lang.String accountType, java.lang.String accountName, java.lang.String modeOfOperation)
			throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages lockUpdateMainternanceCA(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountNumber)
			throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages updateE_Contact(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cifNumber,
			java.lang.String sequenceNo, java.lang.String eContactType, java.lang.String newEcontact) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages debitAdvice(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String sequence,
			java.lang.String transDate, java.lang.String manager, java.lang.String accountNumber, java.lang.String effectiveDate, java.lang.String crAmount, java.lang.String glAccount,
			java.lang.String drAmount, java.lang.String buyRate, java.lang.String sellRate, java.lang.String debitCurrency, java.lang.String glCurrency, java.lang.String remark,
			java.lang.String transcode) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages hostMessageSending(java.lang.String channel, java.lang.String message) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages linkMasterCard(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountTag,
			java.lang.String accountType, java.lang.String usage, java.lang.String currency, java.lang.String branchTag, java.lang.String cifNumber, java.lang.String idNumber,
			java.lang.String idType, java.lang.String name, java.lang.String address, java.lang.String product, java.lang.String annualFee, java.lang.String vip, java.lang.String addressLine2,
			java.lang.String addressLine3, java.lang.String addressLine4) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages lockUpdateE_Contact(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cifNumber,
			java.lang.String sequenceNo) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages createFDReceipt(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String journalSeq,
			java.lang.String transDate, java.lang.String fdGroupAccount, java.lang.String depositAmt, java.lang.String effectiveDate, java.lang.String fdReceipt, java.lang.String rate,
			java.lang.String intPaymentToAcctno, java.lang.String printTranferToAcctno, java.lang.String currency, java.lang.String productCode, java.lang.String autoRenew,
			java.lang.String intPaymentMode, java.lang.String fdType, java.lang.String remark) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages transferFromCASAToCASA(java.lang.String channel, java.lang.String branchCode, java.lang.String creditAccount, java.lang.String creditAmount,
			java.lang.String creditCurrency, java.lang.String creditRate, java.lang.String debitAccount, java.lang.String debitAmount, java.lang.String debitCurrency, java.lang.String debitRate,
			java.lang.String manager, java.lang.String description, java.lang.String sequence, java.lang.String teller, java.lang.String transDate, java.lang.String transCode,
			java.lang.String vatFee, java.lang.String serviceFee) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages updateMainternanceCA(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountNumber,
			java.lang.String introducerCode, java.lang.String expense, java.lang.String accountName) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages getInformationByCIFForIBSRegistration(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode,
			java.lang.String cif_no) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages lockTransferAccountFromCifToAnother(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String sourceCif,
			java.lang.String accountToMove) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages createSA(java.lang.String channel, java.lang.String hostDate, java.lang.String hostId, java.lang.String branchCode,
			java.lang.String currencyType, java.lang.String cifNumber, java.lang.String accountNumber, java.lang.String accountType, java.lang.String accountName, java.lang.String modeOfOperation)
			throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages TFMessageSending(java.lang.String channel, java.lang.String message) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages miniStatement(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String recordNumber,
			java.lang.String accountNumber, java.lang.String accountType) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages accountInquiry(java.lang.String channel, java.lang.String teller, java.lang.String account, java.lang.String transDate)
			throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages joinCA(java.lang.String channel, java.lang.String strteller, java.lang.String strBankcode, java.lang.String transDate,
			java.lang.String strAccount, java.lang.String strCifnumber, java.lang.String strNameAcount, java.lang.String strcurrCode) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages joinSA(java.lang.String channel, java.lang.String strteller, java.lang.String strBankcode, java.lang.String transDate,
			java.lang.String strAccount, java.lang.String strCifnumber, java.lang.String strNameAcount, java.lang.String strcurrCode) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages searchCustomerByCif(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode,
			java.lang.String cifNumber) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages addE_Contact(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cifNumber,
			java.lang.String eContactType, java.lang.String newEcontact) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages createCA(java.lang.String channel, java.lang.String hostDate, java.lang.String hostId, java.lang.String branchCode,
			java.lang.String currencyType, java.lang.String cifNumber, java.lang.String accountNumber, java.lang.String accountType, java.lang.String accountName, java.lang.String modeOfOperation)
			throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages reversableOL2Transaction(java.lang.String channel, java.lang.String hostIP, java.lang.String hostName, java.lang.String teller,
			java.lang.String manager, java.lang.String sequence, java.lang.String oldSequence, java.lang.String transDate, java.lang.String branchCode, java.lang.String fromAccount,
			java.lang.String amount, java.lang.String amountMustPay, java.lang.String fromCif, java.lang.String GLAccount, java.lang.String vatFee, java.lang.String serviceFee,
			java.lang.String remarks, java.lang.String fromName, java.lang.String fromId, java.lang.String toAccount, java.lang.String toName, java.lang.String toId, java.lang.String toAddress,
			java.lang.String toIdIssueDate, java.lang.String toIdIssuePlace, java.lang.String strTransCode, java.lang.String refId) throws java.rmi.RemoteException;

	public java.lang.String getAccount(java.lang.String branchCode, java.lang.String currencyCode, java.lang.String groupCode) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages getInformationByIDForIBSRegistration(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode,
			java.lang.String cert_code) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages searchCustomerById(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode,
			java.lang.String idNumber) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages updateMainternanceNewCA(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountNumber,
			java.lang.String introducerCode, java.lang.String expense, java.lang.String accountName) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages transferAccountFromCifToAnother(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String sourceCif,
			java.lang.String accountToMove, java.lang.String destinationCif, java.lang.String destinationName) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages creditAdvice(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String sequence,
			java.lang.String transDate, java.lang.String manager, java.lang.String accountNumber, java.lang.String effectiveDate, java.lang.String crAmount, java.lang.String glAccount,
			java.lang.String drAmount, java.lang.String buyRate, java.lang.String sellRate, java.lang.String debitCurrency, java.lang.String glCurrency, java.lang.String remark,
			java.lang.String transcode) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages searchAccountByCif(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode,
			java.lang.String cifNumber) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages cardActivation(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cardNumber,
			java.lang.String cifNumber, java.lang.String idNo, java.lang.String idType) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages linkCard(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cardNumber, java.lang.String accountTag,
			java.lang.String accountType, java.lang.String usage, java.lang.String currency, java.lang.String branchTag, java.lang.String cifNumber, java.lang.String idNumber,
			java.lang.String idType, java.lang.String name, java.lang.String address, java.lang.String product, java.lang.String annualFee, java.lang.String vip, java.lang.String addressLine2,
			java.lang.String addressLine3, java.lang.String addressLine4) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages createCif(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String idNumber,
			java.lang.String idType, java.lang.String idDateIssued, java.lang.String idCountryIssued, java.lang.String idPlaceIssued, java.lang.String strSurname, java.lang.String afterSurname,
			java.lang.String addressLine1, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4, java.lang.String addressLine5, java.lang.String nationality,
			java.lang.String birthday, java.lang.String raceCode, java.lang.String maritalStatus, java.lang.String gender, java.lang.String occupationCode_2, java.lang.String placeOfBirth,
			java.lang.String typeElectronicAddress1, java.lang.String typeElectronicAddress2, java.lang.String typeElectronicAddress3, java.lang.String electronicAddress1,
			java.lang.String electronicAddress2, java.lang.String electronicAddress3, java.lang.String hostDate, java.lang.String birthday_Full, java.lang.String idDateIssued_Full,
			java.lang.String strOccupationCode_1, java.lang.String strProvince) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages lockUpdateMainternanceNewCA(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountNumber)
			throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages searchE_ContactByCif(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cifNumber)
			throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages reversableTransaction(java.lang.String channel, java.lang.String teller, java.lang.String manager, java.lang.String branchCode,
			java.lang.String strFromAccount, java.lang.String strToAccount, java.lang.String transDate, java.lang.String strDescription, java.lang.String strTranferAmount,
			java.lang.String strJournalSeq, java.lang.String orgJournalSeq, java.lang.String strTransCode) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages searchCustomerInformationByID(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode,
			java.lang.String cifNumber) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages ddMasterInquiry(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode,
			java.lang.String accountNumber, java.lang.String accountType) throws java.rmi.RemoteException;

	public seatechit.msbgateway.proxy.as400gw.Messages searchAccountMoreByCif(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode,
			java.lang.String cifNumber, java.lang.String lastAccountType, java.lang.String lastAccountNumber, java.lang.String relationShip) throws java.rmi.RemoteException;

	public java.lang.String getFDReceiptAccount(java.lang.String branchCode, java.lang.String currencyCode, java.lang.String groupCode) throws java.rmi.RemoteException;
}
