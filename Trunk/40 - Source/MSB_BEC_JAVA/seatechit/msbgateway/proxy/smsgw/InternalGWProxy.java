package seatechit.msbgateway.proxy.smsgw;

public class InternalGWProxy implements seatechit.msbgateway.proxy.smsgw.InternalGW {
	private String _endpoint = null;
	private seatechit.msbgateway.proxy.smsgw.InternalGW internalGW = null;

	public InternalGWProxy() {
		_initInternalGWProxy();
	}

	public InternalGWProxy(String endpoint) {
		_endpoint = endpoint;
		_initInternalGWProxy();
	}

	private void _initInternalGWProxy() {
		try {
			internalGW = (new seatechit.msbgateway.proxy.smsgw.InternalGWServiceLocator()).getInternalGWPort();
			if (internalGW != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) internalGW)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) internalGW)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (internalGW != null)
			((javax.xml.rpc.Stub) internalGW)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public seatechit.msbgateway.proxy.smsgw.InternalGW getInternalGW() {
		if (internalGW == null)
			_initInternalGWProxy();
		return internalGW;
	}

	public seatechit.msbgateway.proxy.smsgw.InternalSMSRes sendSMSObj(seatechit.msbgateway.proxy.smsgw.InternalSMSReq arg0) throws java.rmi.RemoteException, seatechit.msbgateway.proxy.smsgw.Exception {
		if (internalGW == null)
			_initInternalGWProxy();
		return internalGW.sendSMSObj(arg0);
	}

	public seatechit.msbgateway.proxy.smsgw.InternalSMSRes sendSMS(java.lang.String sequence_id, java.lang.String supply_id, java.lang.String[] mobile_number, java.lang.String content,
			java.lang.String datetime, int priority, java.lang.String app_sender) throws java.rmi.RemoteException, seatechit.msbgateway.proxy.smsgw.Exception {
		if (internalGW == null)
			_initInternalGWProxy();
		return internalGW.sendSMS(sequence_id, supply_id, mobile_number, content, datetime, priority, app_sender);
	}

}