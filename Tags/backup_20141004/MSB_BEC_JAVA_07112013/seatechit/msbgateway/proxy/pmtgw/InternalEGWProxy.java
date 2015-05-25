package seatechit.msbgateway.proxy.pmtgw;

public class InternalEGWProxy implements seatechit.msbgateway.proxy.pmtgw.InternalEGW {
	private String _endpoint = null;
	private seatechit.msbgateway.proxy.pmtgw.InternalEGW internalEGW = null;

	public InternalEGWProxy() {
		_initInternalEGWProxy();
	}

	public InternalEGWProxy(String endpoint) {
		_endpoint = endpoint;
		_initInternalEGWProxy();
	}

	private void _initInternalEGWProxy() {
		try {
			internalEGW = (new seatechit.msbgateway.proxy.pmtgw.InternalEGWServiceLocator()).getInternalEGWPort();
			if (internalEGW != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) internalEGW)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) internalEGW)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (internalEGW != null)
			((javax.xml.rpc.Stub) internalEGW)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public seatechit.msbgateway.proxy.pmtgw.InternalEGW getInternalEGW() {
		if (internalEGW == null)
			_initInternalEGWProxy();
		return internalEGW;
	}

	public seatechit.msbgateway.proxy.pmtgw.QueryBillingResponseReturn queryBilling(seatechit.msbgateway.proxy.pmtgw.QueryBillingReq req) throws java.rmi.RemoteException {
		if (internalEGW == null)
			_initInternalEGWProxy();
		return internalEGW.queryBilling(req);
	}

	public seatechit.msbgateway.proxy.pmtgw.PayBillingResponseReturn payBilling(seatechit.msbgateway.proxy.pmtgw.PayBillingReq req) throws java.rmi.RemoteException {
		if (internalEGW == null)
			_initInternalEGWProxy();
		return internalEGW.payBilling(req);
	}

}