package vn.com.msb.cnn.accounts;

public class AS400Proxy implements vn.com.msb.cnn.accounts.AS400_PortType {
  private String _endpoint = null;
  private vn.com.msb.cnn.accounts.AS400_PortType aS400_PortType = null;
  
  public AS400Proxy() {
    _initAS400Proxy();
  }
  
  public AS400Proxy(String endpoint) {
    _endpoint = endpoint;
    _initAS400Proxy();
  }
  
  private void _initAS400Proxy() {
    try {
      aS400_PortType = (new vn.com.msb.cnn.accounts.AS400_ServiceLocator()).getAS400HttpPort();
      if (aS400_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)aS400_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)aS400_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (aS400_PortType != null)
      ((javax.xml.rpc.Stub)aS400_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public vn.com.msb.cnn.accounts.AS400_PortType getAS400_PortType() {
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType;
  }
  
  public vn.com.msb.cnn.utils.Messages createHoldMessage(java.lang.String channel, java.lang.String teller, java.lang.String account, java.lang.String expiryDate, java.lang.String description, java.lang.String amount, java.lang.String code) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createHoldMessage(channel, teller, account, expiryDate, description, amount, code);
  }
  
  public vn.com.msb.cnn.utils.Messages createFD(java.lang.String channel, java.lang.String hostDate, java.lang.String hostId, java.lang.String branchCode, java.lang.String currencyType, java.lang.String cifNumber, java.lang.String accountNumber, java.lang.String accountType, java.lang.String accountName, java.lang.String modeOfOperation) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createFD(channel, hostDate, hostId, branchCode, currencyType, cifNumber, accountNumber, accountType, accountName, modeOfOperation);
  }
  
  public vn.com.msb.cnn.utils.Messages lockUpdateMainternanceCA(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.lockUpdateMainternanceCA(channel, teller, bankCode, accountNumber);
  }
  
  public vn.com.msb.cnn.utils.Messages debitAdvice(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String sequence, java.lang.String transDate, java.lang.String manager, java.lang.String accountNumber, java.lang.String effectiveDate, java.lang.String crAmount, java.lang.String glAccount, java.lang.String drAmount, java.lang.String buyRate, java.lang.String sellRate, java.lang.String debitCurrency, java.lang.String glCurrency, java.lang.String remark, java.lang.String transcode) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.debitAdvice(channel, teller, branchCode, sequence, transDate, manager, accountNumber, effectiveDate, crAmount, glAccount, drAmount, buyRate, sellRate, debitCurrency, glCurrency, remark, transcode);
  }
  
  public vn.com.msb.cnn.utils.Messages hostMessageSending(java.lang.String channel, java.lang.String message) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.hostMessageSending(channel, message);
  }
  
  public vn.com.msb.cnn.utils.Messages linkMasterCard(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountTag, java.lang.String accountType, java.lang.String usage, java.lang.String currency, java.lang.String branchTag, java.lang.String cifNumber, java.lang.String idNumber, java.lang.String idType, java.lang.String name, java.lang.String address, java.lang.String product, java.lang.String annualFee, java.lang.String vip, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.linkMasterCard(channel, teller, bankCode, accountTag, accountType, usage, currency, branchTag, cifNumber, idNumber, idType, name, address, product, annualFee, vip, addressLine2, addressLine3, addressLine4);
  }
  
  public vn.com.msb.cnn.utils.Messages lockUpdateE_Contact(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cifNumber, java.lang.String sequenceNo) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.lockUpdateE_Contact(channel, teller, bankCode, cifNumber, sequenceNo);
  }
  
  public vn.com.msb.cnn.utils.Messages changeCardService(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String cardNumber, java.lang.String serviceName, java.lang.String cifNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.changeCardService(channel, teller, branchCode, cardNumber, serviceName, cifNumber);
  }
  
  public vn.com.msb.cnn.utils.Messages createFDReceipt(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String journalSeq, java.lang.String transDate, java.lang.String fdGroupAccount, java.lang.String depositAmt, java.lang.String effectiveDate, java.lang.String fdReceipt, java.lang.String rate, java.lang.String intPaymentToAcctno, java.lang.String printTranferToAcctno, java.lang.String currency, java.lang.String productCode, java.lang.String autoRenew, java.lang.String intPaymentMode, java.lang.String fdType, java.lang.String remark) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createFDReceipt(channel, teller, branchCode, journalSeq, transDate, fdGroupAccount, depositAmt, effectiveDate, fdReceipt, rate, intPaymentToAcctno, printTranferToAcctno, currency, productCode, autoRenew, intPaymentMode, fdType, remark);
  }
  
  public vn.com.msb.cnn.utils.Messages transferFromCASAToCASA(java.lang.String channel, java.lang.String branchCode, java.lang.String creditAccount, java.lang.String creditAmount, java.lang.String creditCurrency, java.lang.String creditRate, java.lang.String debitAccount, java.lang.String debitAmount, java.lang.String debitCurrency, java.lang.String debitRate, java.lang.String manager, java.lang.String description, java.lang.String sequence, java.lang.String teller, java.lang.String transDate, java.lang.String transCode, java.lang.String vatFee, java.lang.String serviceFee) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.transferFromCASAToCASA(channel, branchCode, creditAccount, creditAmount, creditCurrency, creditRate, debitAccount, debitAmount, debitCurrency, debitRate, manager, description, sequence, teller, transDate, transCode, vatFee, serviceFee);
  }
  
  public vn.com.msb.cnn.utils.Messages lockTransferAccountFromCifToAnother(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String sourceCif, java.lang.String accountToMove) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.lockTransferAccountFromCifToAnother(channel, teller, bankCode, sourceCif, accountToMove);
  }
  
  public vn.com.msb.cnn.utils.Messages createSA(java.lang.String channel, java.lang.String hostDate, java.lang.String hostId, java.lang.String branchCode, java.lang.String currencyType, java.lang.String cifNumber, java.lang.String accountNumber, java.lang.String accountType, java.lang.String accountName, java.lang.String modeOfOperation) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createSA(channel, hostDate, hostId, branchCode, currencyType, cifNumber, accountNumber, accountType, accountName, modeOfOperation);
  }
  
  public vn.com.msb.cnn.utils.Messages TFMessageSending(java.lang.String channel, java.lang.String message) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.TFMessageSending(channel, message);
  }
  
  public vn.com.msb.cnn.utils.Messages joinSA(java.lang.String channel, java.lang.String strteller, java.lang.String strBankcode, java.lang.String transDate, java.lang.String strAccount, java.lang.String strCifnumber, java.lang.String strNameAcount, java.lang.String strcurrCode) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.joinSA(channel, strteller, strBankcode, transDate, strAccount, strCifnumber, strNameAcount, strcurrCode);
  }
  
  public vn.com.msb.cnn.utils.Messages joinCA(java.lang.String channel, java.lang.String strteller, java.lang.String strBankcode, java.lang.String transDate, java.lang.String strAccount, java.lang.String strCifnumber, java.lang.String strNameAcount, java.lang.String strcurrCode) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.joinCA(channel, strteller, strBankcode, transDate, strAccount, strCifnumber, strNameAcount, strcurrCode);
  }
  
  public vn.com.msb.cnn.utils.Messages accountInquiry(java.lang.String channel, java.lang.String teller, java.lang.String account, java.lang.String transDate) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.accountInquiry(channel, teller, account, transDate);
  }
  
  public vn.com.msb.cnn.utils.Messages createAddCifGroupMainternanceMessage(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String cifCorp, java.lang.String seq, java.lang.String cifEmp, java.lang.String relationalEmpLev, java.lang.String relationalType, java.lang.String relationalCorpLev) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createAddCifGroupMainternanceMessage(channel, teller, branchCode, cifCorp, seq, cifEmp, relationalEmpLev, relationalType, relationalCorpLev);
  }
  
  public vn.com.msb.cnn.utils.Messages markHotCard(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String cardNumber, java.lang.String typeLock, java.lang.String comment) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.markHotCard(channel, teller, branchCode, cardNumber, typeLock, comment);
  }
  
  public vn.com.msb.cnn.utils.Messages getInformationByIDForIBSRegistration(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String cert_code) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.getInformationByIDForIBSRegistration(channel, teller, hostName, bankCode, cert_code);
  }
  
  public vn.com.msb.cnn.utils.Messages searchCustomerById(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String idNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.searchCustomerById(channel, teller, hostName, bankCode, idNumber);
  }
  
  public vn.com.msb.cnn.utils.Messages creditAdvice(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String sequence, java.lang.String transDate, java.lang.String manager, java.lang.String accountNumber, java.lang.String effectiveDate, java.lang.String crAmount, java.lang.String glAccount, java.lang.String drAmount, java.lang.String buyRate, java.lang.String sellRate, java.lang.String debitCurrency, java.lang.String glCurrency, java.lang.String remark, java.lang.String transcode) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.creditAdvice(channel, teller, branchCode, sequence, transDate, manager, accountNumber, effectiveDate, crAmount, glAccount, drAmount, buyRate, sellRate, debitCurrency, glCurrency, remark, transcode);
  }
  
  public vn.com.msb.cnn.utils.Messages linkCard(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cardNumber, java.lang.String accountTag, java.lang.String accountType, java.lang.String usage, java.lang.String currency, java.lang.String branchTag, java.lang.String cifNumber, java.lang.String idNumber, java.lang.String idType, java.lang.String name, java.lang.String address, java.lang.String product, java.lang.String annualFee, java.lang.String vip, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.linkCard(channel, teller, bankCode, cardNumber, accountTag, accountType, usage, currency, branchTag, cifNumber, idNumber, idType, name, address, product, annualFee, vip, addressLine2, addressLine3, addressLine4);
  }
  
  public vn.com.msb.cnn.utils.Messages reActiveCard(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String cardNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.reActiveCard(channel, teller, branchCode, cardNumber);
  }
  
  public vn.com.msb.cnn.utils.Messages reversableTransaction(java.lang.String channel, java.lang.String teller, java.lang.String manager, java.lang.String branchCode, java.lang.String strFromAccount, java.lang.String strToAccount, java.lang.String transDate, java.lang.String strDescription, java.lang.String strTranferAmount, java.lang.String strJournalSeq, java.lang.String orgJournalSeq, java.lang.String strTransCode) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.reversableTransaction(channel, teller, manager, branchCode, strFromAccount, strToAccount, transDate, strDescription, strTranferAmount, strJournalSeq, orgJournalSeq, strTransCode);
  }
  
  public vn.com.msb.cnn.utils.Messages ddMasterInquiry(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String accountNumber, java.lang.String accountType) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.ddMasterInquiry(channel, teller, hostName, bankCode, accountNumber, accountType);
  }
  
  public vn.com.msb.cnn.utils.Messages searchAccountMoreByCif(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String cifNumber, java.lang.String lastAccountType, java.lang.String lastAccountNumber, java.lang.String relationShip) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.searchAccountMoreByCif(channel, teller, hostName, bankCode, cifNumber, lastAccountType, lastAccountNumber, relationShip);
  }
  
  public vn.com.msb.cnn.utils.Messages loanAccountInquiry(java.lang.String channel, java.lang.String teller, java.lang.String branchCode, java.lang.String hostName, java.lang.String accountNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.loanAccountInquiry(channel, teller, branchCode, hostName, accountNumber);
  }
  
  public int transferGLtoGL(java.lang.String channel, java.lang.String refID, java.lang.String debitGLBranch, java.lang.String debitGLAccount, java.lang.String creditGLBranch, java.lang.String creditGLAccount, java.lang.String amount, java.lang.String currency, java.lang.String comments, java.lang.String referenceText, java.lang.String teller, java.lang.String manager, java.lang.String hostDate, java.lang.String reconcileGL, java.lang.String transactionOffice, java.lang.String customerTypeCode, java.lang.String account, java.lang.String businessDeptCode, java.lang.String branchCode5) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.transferGLtoGL(channel, refID, debitGLBranch, debitGLAccount, creditGLBranch, creditGLAccount, amount, currency, comments, referenceText, teller, manager, hostDate, reconcileGL, transactionOffice, customerTypeCode, account, businessDeptCode, branchCode5);
  }
  
  public vn.com.msb.cnn.utils.Messages transferFromCASAToGL(java.lang.String channel, java.lang.String hostIP, java.lang.String hostName, java.lang.String teller, java.lang.String manager, java.lang.String sequence, java.lang.String transDate, java.lang.String branchCode, java.lang.String fromAccount, java.lang.String amount, java.lang.String fromCif, java.lang.String GLAccount, java.lang.String vatFee, java.lang.String serviceFee, java.lang.String remarks, java.lang.String fromName, java.lang.String fromId, java.lang.String toAccount, java.lang.String toName, java.lang.String toId, java.lang.String toAddress, java.lang.String toIdIssueDate, java.lang.String toIdIssuePlace, java.lang.String transCode) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.transferFromCASAToGL(channel, hostIP, hostName, teller, manager, sequence, transDate, branchCode, fromAccount, amount, fromCif, GLAccount, vatFee, serviceFee, remarks, fromName, fromId, toAccount, toName, toId, toAddress, toIdIssueDate, toIdIssuePlace, transCode);
  }
  
  public vn.com.msb.cnn.utils.Messages updateE_Contact(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cifNumber, java.lang.String sequenceNo, java.lang.String eContactType, java.lang.String newEcontact) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.updateE_Contact(channel, teller, bankCode, cifNumber, sequenceNo, eContactType, newEcontact);
  }
  
  public vn.com.msb.cnn.utils.Messages updateMainternanceCA(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountNumber, java.lang.String introducerCode, java.lang.String expense, java.lang.String accountName) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.updateMainternanceCA(channel, teller, bankCode, accountNumber, introducerCode, expense, accountName);
  }
  
  public vn.com.msb.cnn.utils.Messages getInformationByCIFForIBSRegistration(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String cif_no) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.getInformationByCIFForIBSRegistration(channel, teller, hostName, bankCode, cif_no);
  }
  
  public vn.com.msb.cnn.utils.Messages createUnHoldMessage(java.lang.String channel, java.lang.String teller, java.lang.String account, java.lang.String description, java.lang.String amount, java.lang.String code, java.lang.String sequence, java.lang.String actionCode, java.lang.String expireDate) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createUnHoldMessage(channel, teller, account, description, amount, code, sequence, actionCode, expireDate);
  }
  
  public vn.com.msb.cnn.utils.Messages transferFromWUToCA(java.lang.String channel, java.lang.String hostIP, java.lang.String hostName, java.lang.String teller, java.lang.String manager, java.lang.String sequence, java.lang.String transDate, java.lang.String branchCode, java.lang.String toAccount, java.lang.String crAmount, java.lang.String ttAmount, java.lang.String toCif, java.lang.String WUAccount, java.lang.String vatFee, java.lang.String serviceFee, java.lang.String buyRate, java.lang.String sendRate, java.lang.String ttSendRate, java.lang.String remarks, java.lang.String toName, java.lang.String toId, java.lang.String toAddress, java.lang.String toIdIssueDate, java.lang.String toIdIssuePlace, java.lang.String transCode, java.lang.String refWU, java.lang.String sendingCurrencyType, java.lang.String receivingCurrencyType) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.transferFromWUToCA(channel, hostIP, hostName, teller, manager, sequence, transDate, branchCode, toAccount, crAmount, ttAmount, toCif, WUAccount, vatFee, serviceFee, buyRate, sendRate, ttSendRate, remarks, toName, toId, toAddress, toIdIssueDate, toIdIssuePlace, transCode, refWU, sendingCurrencyType, receivingCurrencyType);
  }
  
  public vn.com.msb.cnn.utils.Messages miniStatement(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String recordNumber, java.lang.String accountNumber, java.lang.String accountType) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.miniStatement(channel, teller, bankCode, recordNumber, accountNumber, accountType);
  }
  
  public vn.com.msb.cnn.utils.Messages searchCustomerByCif(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String cifNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.searchCustomerByCif(channel, teller, hostName, bankCode, cifNumber);
  }
  
  public vn.com.msb.cnn.utils.Messages addE_Contact(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cifNumber, java.lang.String eContactType, java.lang.String newEcontact) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.addE_Contact(channel, teller, bankCode, cifNumber, eContactType, newEcontact);
  }
  
  public vn.com.msb.cnn.utils.Messages createCA(java.lang.String channel, java.lang.String hostDate, java.lang.String hostId, java.lang.String branchCode, java.lang.String currencyType, java.lang.String cifNumber, java.lang.String accountNumber, java.lang.String accountType, java.lang.String accountName, java.lang.String modeOfOperation) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createCA(channel, hostDate, hostId, branchCode, currencyType, cifNumber, accountNumber, accountType, accountName, modeOfOperation);
  }
  
  public vn.com.msb.cnn.utils.Messages reversableOL2Transaction(java.lang.String channel, java.lang.String hostIP, java.lang.String hostName, java.lang.String teller, java.lang.String manager, java.lang.String sequence, java.lang.String oldSequence, java.lang.String transDate, java.lang.String branchCode, java.lang.String fromAccount, java.lang.String amount, java.lang.String amountMustPay, java.lang.String fromCif, java.lang.String GLAccount, java.lang.String vatFee, java.lang.String serviceFee, java.lang.String remarks, java.lang.String fromName, java.lang.String fromId, java.lang.String toAccount, java.lang.String toName, java.lang.String toId, java.lang.String toAddress, java.lang.String toIdIssueDate, java.lang.String toIdIssuePlace, java.lang.String strTransCode, java.lang.String refId) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.reversableOL2Transaction(channel, hostIP, hostName, teller, manager, sequence, oldSequence, transDate, branchCode, fromAccount, amount, amountMustPay, fromCif, GLAccount, vatFee, serviceFee, remarks, fromName, fromId, toAccount, toName, toId, toAddress, toIdIssueDate, toIdIssuePlace, strTransCode, refId);
  }
  
  public java.lang.String getAccount(java.lang.String branchCode, java.lang.String currencyCode, java.lang.String groupCode) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.getAccount(branchCode, currencyCode, groupCode);
  }
  
  public vn.com.msb.cnn.utils.Messages updateMainternanceNewCA(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountNumber, java.lang.String introducerCode, java.lang.String expense, java.lang.String accountName) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.updateMainternanceNewCA(channel, teller, bankCode, accountNumber, introducerCode, expense, accountName);
  }
  
  public vn.com.msb.cnn.utils.Messages transferAccountFromCifToAnother(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String sourceCif, java.lang.String accountToMove, java.lang.String destinationCif, java.lang.String destinationName) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.transferAccountFromCifToAnother(channel, teller, bankCode, sourceCif, accountToMove, destinationCif, destinationName);
  }
  
  public vn.com.msb.cnn.utils.Messages cardActivation(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cardNumber, java.lang.String cifNumber, java.lang.String idNo, java.lang.String idType) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.cardActivation(channel, teller, bankCode, cardNumber, cifNumber, idNo, idType);
  }
  
  public vn.com.msb.cnn.utils.Messages searchAccountByCif(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String cifNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.searchAccountByCif(channel, teller, hostName, bankCode, cifNumber);
  }
  
  public vn.com.msb.cnn.utils.Messages createCif(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String idNumber, java.lang.String idType, java.lang.String idDateIssued, java.lang.String idCountryIssued, java.lang.String idPlaceIssued, java.lang.String strSurname, java.lang.String afterSurname, java.lang.String addressLine1, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4, java.lang.String addressLine5, java.lang.String nationality, java.lang.String birthday, java.lang.String raceCode, java.lang.String maritalStatus, java.lang.String gender, java.lang.String occupationCode_2, java.lang.String placeOfBirth, java.lang.String typeElectronicAddress1, java.lang.String typeElectronicAddress2, java.lang.String typeElectronicAddress3, java.lang.String electronicAddress1, java.lang.String electronicAddress2, java.lang.String electronicAddress3, java.lang.String hostDate, java.lang.String birthday_Full, java.lang.String idDateIssued_Full, java.lang.String strOccupationCode_1, java.lang.String strProvince) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createCif(channel, teller, hostName, bankCode, idNumber, idType, idDateIssued, idCountryIssued, idPlaceIssued, strSurname, afterSurname, addressLine1, addressLine2, addressLine3, addressLine4, addressLine5, nationality, birthday, raceCode, maritalStatus, gender, occupationCode_2, placeOfBirth, typeElectronicAddress1, typeElectronicAddress2, typeElectronicAddress3, electronicAddress1, electronicAddress2, electronicAddress3, hostDate, birthday_Full, idDateIssued_Full, strOccupationCode_1, strProvince);
  }
  
  public vn.com.msb.cnn.utils.Messages lockUpdateMainternanceNewCA(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String accountNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.lockUpdateMainternanceNewCA(channel, teller, bankCode, accountNumber);
  }
  
  public vn.com.msb.cnn.utils.Messages createLockForUnHoldMessage(java.lang.String channel, java.lang.String teller, java.lang.String account, java.lang.String sequence) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.createLockForUnHoldMessage(channel, teller, account, sequence);
  }
  
  public vn.com.msb.cnn.utils.Messages searchE_ContactByCif(java.lang.String channel, java.lang.String teller, java.lang.String bankCode, java.lang.String cifNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.searchE_ContactByCif(channel, teller, bankCode, cifNumber);
  }
  
  public vn.com.msb.cnn.utils.Messages searchCustomerInformationByID(java.lang.String channel, java.lang.String teller, java.lang.String hostName, java.lang.String bankCode, java.lang.String cifNumber) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.searchCustomerInformationByID(channel, teller, hostName, bankCode, cifNumber);
  }
  
  public java.lang.String getFDReceiptAccount(java.lang.String branchCode, java.lang.String currencyCode, java.lang.String groupCode) throws java.rmi.RemoteException{
    if (aS400_PortType == null)
      _initAS400Proxy();
    return aS400_PortType.getFDReceiptAccount(branchCode, currencyCode, groupCode);
  }
  
  
}