package seatechit.msbgateway.proxy.caldiscount;

public class CalculatediscountProxy implements seatechit.msbgateway.proxy.caldiscount.Calculatediscount {
  private String _endpoint = null;
  private seatechit.msbgateway.proxy.caldiscount.Calculatediscount calculatediscount = null;
  
  public CalculatediscountProxy() {
    _initCalculatediscountProxy();
  }
  
  public CalculatediscountProxy(String endpoint) {
    _endpoint = endpoint;
    _initCalculatediscountProxy();
  }
  
  private void _initCalculatediscountProxy() {
    try {
      calculatediscount = (new seatechit.msbgateway.proxy.caldiscount.CalculatediscountServiceLocator()).getcalculatediscountPort();
      if (calculatediscount != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)calculatediscount)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)calculatediscount)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (calculatediscount != null)
      ((javax.xml.rpc.Stub)calculatediscount)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public seatechit.msbgateway.proxy.caldiscount.Calculatediscount getCalculatediscount() {
    if (calculatediscount == null)
      _initCalculatediscountProxy();
    return calculatediscount;
  }
  
  public seatechit.msbgateway.proxy.caldiscount.DiscountRes calDistcountProvider(java.lang.String discountCode, java.lang.String transID, java.lang.String serviceCode, java.lang.String branchCode, java.lang.String amount, java.lang.String currencyCode, java.lang.String customerType, java.lang.String businessType, java.lang.String officeCode, java.lang.String micNumber, java.lang.String channel, java.lang.String bankType) throws java.rmi.RemoteException{
    if (calculatediscount == null)
      _initCalculatediscountProxy();
    return calculatediscount.calDistcountProvider(discountCode, transID, serviceCode, branchCode, amount, currencyCode, customerType, businessType, officeCode, micNumber, channel, bankType);
  }
  
  public seatechit.msbgateway.proxy.caldiscount.DiscountRes calDistcountTrans(java.lang.String discountCode, java.lang.String transID, java.lang.String serviceCode, java.lang.String branchCode, java.lang.String toBranchCode, java.lang.String transType, java.lang.String fromAcc, java.lang.String toAcc, java.lang.String fromOrgBranchCode, java.lang.String toOrgBranchCode, java.lang.String amount, java.lang.String currencyCode, java.lang.String customerType, java.lang.String businessType, java.lang.String officeCode, java.lang.String productType, java.lang.String micNumber, java.lang.String channel, java.lang.String bankType) throws java.rmi.RemoteException{
    if (calculatediscount == null)
      _initCalculatediscountProxy();
    return calculatediscount.calDistcountTrans(discountCode, transID, serviceCode, branchCode, toBranchCode, transType, fromAcc, toAcc, fromOrgBranchCode, toOrgBranchCode, amount, currencyCode, customerType, businessType, officeCode, productType, micNumber, channel, bankType);
  }
  
  public seatechit.msbgateway.proxy.caldiscount.DiscountRes calDistcountProviderObj(seatechit.msbgateway.proxy.caldiscount.DiscountReqProvider discountParamsProvider) throws java.rmi.RemoteException{
    if (calculatediscount == null)
      _initCalculatediscountProxy();
    return calculatediscount.calDistcountProviderObj(discountParamsProvider);
  }
  
  public seatechit.msbgateway.proxy.caldiscount.DiscountRes calDistcountTransObj(seatechit.msbgateway.proxy.caldiscount.DiscountRequest discountParams) throws java.rmi.RemoteException{
    if (calculatediscount == null)
      _initCalculatediscountProxy();
    return calculatediscount.calDistcountTransObj(discountParams);
  }
  
  
}