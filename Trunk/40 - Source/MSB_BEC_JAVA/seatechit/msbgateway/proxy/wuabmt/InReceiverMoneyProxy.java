package seatechit.msbgateway.proxy.wuabmt;

public class InReceiverMoneyProxy implements seatechit.msbgateway.proxy.wuabmt.InReceiverMoney {
  private String _endpoint = null;
  private seatechit.msbgateway.proxy.wuabmt.InReceiverMoney inReceiverMoney = null;
  
  public InReceiverMoneyProxy() {
    _initInReceiverMoneyProxy();
  }
  
  public InReceiverMoneyProxy(String endpoint) {
    _endpoint = endpoint;
    _initInReceiverMoneyProxy();
  }
  
  private void _initInReceiverMoneyProxy() {
    try {
      inReceiverMoney = (new seatechit.msbgateway.proxy.wuabmt.InReceiverMoneyServiceLocator()).getInReceiverMoneyPort();
      if (inReceiverMoney != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)inReceiverMoney)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)inReceiverMoney)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (inReceiverMoney != null)
      ((javax.xml.rpc.Stub)inReceiverMoney)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public seatechit.msbgateway.proxy.wuabmt.InReceiverMoney getInReceiverMoney() {
    if (inReceiverMoney == null)
      _initInReceiverMoneyProxy();
    return inReceiverMoney;
  }
  
  public seatechit.msbgateway.proxy.wuabmt.TransactionInquiryRespone transationInquiry(seatechit.msbgateway.proxy.wuabmt.TransactionInquiryRequest request) throws java.rmi.RemoteException{
    if (inReceiverMoney == null)
      _initInReceiverMoneyProxy();
    return inReceiverMoney.transationInquiry(request);
  }
  
  public seatechit.msbgateway.proxy.wuabmt.PickupValidateRes pickupValidate(seatechit.msbgateway.proxy.wuabmt.PickupValidateReq request) throws java.rmi.RemoteException{
    if (inReceiverMoney == null)
      _initInReceiverMoneyProxy();
    return inReceiverMoney.pickupValidate(request);
  }
  
  public seatechit.msbgateway.proxy.wuabmt.PickupReciveMoneyRes pickupReciveMoney(seatechit.msbgateway.proxy.wuabmt.PickupReciveMoneyReq request) throws java.rmi.RemoteException{
    if (inReceiverMoney == null)
      _initInReceiverMoneyProxy();
    return inReceiverMoney.pickupReciveMoney(request);
  }
  
  
}