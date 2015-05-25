package seatechit.msbgateway.proxy.calfee;

public class CalculatefeeProxy implements seatechit.msbgateway.proxy.calfee.Calculatefee {
  private String _endpoint = null;
  private seatechit.msbgateway.proxy.calfee.Calculatefee calculatefee = null;
  
  public CalculatefeeProxy() {
    _initCalculatefeeProxy();
  }
  
  public CalculatefeeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCalculatefeeProxy();
  }
  
  private void _initCalculatefeeProxy() {
    try {
      calculatefee = (new seatechit.msbgateway.proxy.calfee.CalculatefeeServiceLocator()).getcalculatefeePort();
      if (calculatefee != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)calculatefee)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)calculatefee)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (calculatefee != null)
      ((javax.xml.rpc.Stub)calculatefee)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public seatechit.msbgateway.proxy.calfee.Calculatefee getCalculatefee() {
    if (calculatefee == null)
      _initCalculatefeeProxy();
    return calculatefee;
  }
  
  public seatechit.msbgateway.proxy.calfee.FeeRes calFee(java.lang.String feeCode, java.lang.String transID, java.lang.String branchCode, java.lang.String toBranchCode, java.lang.String transType, java.lang.String fromAcc, java.lang.String toAcc, java.lang.String fromOrgBranchCode, java.lang.String toOrgBranchCode, java.lang.String amount, java.lang.String currencyCode, java.lang.String customerType, java.lang.String productType, java.lang.String businessType, java.lang.String officeCode, java.lang.String micNumber, java.lang.String channel, java.lang.String bankType) throws java.rmi.RemoteException{
    if (calculatefee == null)
      _initCalculatefeeProxy();
    return calculatefee.calFee(feeCode, transID, branchCode, toBranchCode, transType, fromAcc, toAcc, fromOrgBranchCode, toOrgBranchCode, amount, currencyCode, customerType, productType, businessType, officeCode, micNumber, channel, bankType);
  }
  
  public seatechit.msbgateway.proxy.calfee.FeeRes calFeeObj(seatechit.msbgateway.proxy.calfee.FeeRequest feeParams) throws java.rmi.RemoteException{
    if (calculatefee == null)
      _initCalculatefeeProxy();
    return calculatefee.calFeeObj(feeParams);
  }
  
  
}