package seatechit.msbgateway.proxy.otpgen;

public class OtpCalculatorProxy implements seatechit.msbgateway.proxy.otpgen.OtpCalculator {
  private String _endpoint = null;
  private seatechit.msbgateway.proxy.otpgen.OtpCalculator otpCalculator = null;
  
  public OtpCalculatorProxy() {
    _initOtpCalculatorProxy();
  }
  
  public OtpCalculatorProxy(String endpoint) {
    _endpoint = endpoint;
    _initOtpCalculatorProxy();
  }
  
  private void _initOtpCalculatorProxy() {
    try {
      otpCalculator = (new seatechit.msbgateway.proxy.otpgen.OtpCalculatorServiceLocator()).getOtpCalculatorPort();
      if (otpCalculator != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)otpCalculator)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)otpCalculator)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (otpCalculator != null)
      ((javax.xml.rpc.Stub)otpCalculator)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public seatechit.msbgateway.proxy.otpgen.OtpCalculator getOtpCalculator() {
    if (otpCalculator == null)
      _initOtpCalculatorProxy();
    return otpCalculator;
  }
  
  public int checkOtp(java.lang.String transactionID, long otpTransactionId, int iValueTime, java.lang.String sOtp) throws java.rmi.RemoteException, seatechit.msbgateway.proxy.otpgen.PreexistingEntityException, seatechit.msbgateway.proxy.otpgen.Exception{
    if (otpCalculator == null)
      _initOtpCalculatorProxy();
    return otpCalculator.checkOtp(transactionID, otpTransactionId, iValueTime, sOtp);
  }
  
  public java.lang.String getOtp(java.lang.String transactionID) throws java.rmi.RemoteException, seatechit.msbgateway.proxy.otpgen.PreexistingEntityException, seatechit.msbgateway.proxy.otpgen.Exception{
    if (otpCalculator == null)
      _initOtpCalculatorProxy();
    return otpCalculator.getOtp(transactionID);
  }
  
  
}