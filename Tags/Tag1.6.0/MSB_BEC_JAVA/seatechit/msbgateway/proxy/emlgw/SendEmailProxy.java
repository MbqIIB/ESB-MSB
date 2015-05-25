package seatechit.msbgateway.proxy.emlgw;

public class SendEmailProxy implements seatechit.msbgateway.proxy.emlgw.SendEmail_PortType {
	private String _endpoint = null;
	private seatechit.msbgateway.proxy.emlgw.SendEmail_PortType sendEmail_PortType = null;

	public SendEmailProxy() {
		_initSendEmailProxy();
	}

	public SendEmailProxy(String endpoint) {
		_endpoint = endpoint;
		_initSendEmailProxy();
	}

	private void _initSendEmailProxy() {
		try {
			sendEmail_PortType = (new seatechit.msbgateway.proxy.emlgw.SendEmail_ServiceLocator()).getsendEmailHttpPort();
			if (sendEmail_PortType != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) sendEmail_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) sendEmail_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (sendEmail_PortType != null)
			((javax.xml.rpc.Stub) sendEmail_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public seatechit.msbgateway.proxy.emlgw.SendEmail_PortType getSendEmail_PortType() {
		if (sendEmail_PortType == null)
			_initSendEmailProxy();
		return sendEmail_PortType;
	}

	public java.lang.Object serviceMethod() throws java.rmi.RemoteException {
		if (sendEmail_PortType == null)
			_initSendEmailProxy();
		return sendEmail_PortType.serviceMethod();
	}

	public java.lang.String sendEmail(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4) throws java.rmi.RemoteException {
		if (sendEmail_PortType == null)
			_initSendEmailProxy();
		return sendEmail_PortType.sendEmail(in0, in1, in2, in3, in4);
	}

	public java.lang.Object run() throws java.rmi.RemoteException {
		if (sendEmail_PortType == null)
			_initSendEmailProxy();
		return sendEmail_PortType.run();
	}

}