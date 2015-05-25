/**
 * InternalGWServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.smsgw;

public class InternalGWServiceLocator extends org.apache.axis.client.Service implements seatechit.msbgateway.proxy.smsgw.InternalGWService {
	private String serviceUrl;
	private int serviceTimeout;

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceTimeout(int serviceTimeout) {
		this.serviceTimeout = serviceTimeout;
	}

	public int getServiceTimeout() {
		return serviceTimeout;
	}

	public InternalGWServiceLocator() {
	}

	public InternalGWServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public InternalGWServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	private java.lang.String InternalGWPort_address = "http://10.0.2.51/smsgwprocess/InternalGW?wsdl";

	public java.lang.String getInternalGWPortAddress() {
		return InternalGWPort_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String InternalGWPortWSDDServiceName = "InternalGWPort";

	public java.lang.String getInternalGWPortWSDDServiceName() {
		return InternalGWPortWSDDServiceName;
	}

	public void setInternalGWPortWSDDServiceName(java.lang.String name) {
		InternalGWPortWSDDServiceName = name;
	}

	public seatechit.msbgateway.proxy.smsgw.InternalGW getInternalGWPort() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(InternalGWPort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getInternalGWPort(endpoint);
	}

	public seatechit.msbgateway.proxy.smsgw.InternalGW getInternalGWPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			seatechit.msbgateway.proxy.smsgw.InternalGWPortBindingStub _stub = new seatechit.msbgateway.proxy.smsgw.InternalGWPortBindingStub(portAddress, this);
			// _stub.setTimeout(Integer.parseInt(CachedParam.getSystemParam(Global.SMS_GATEWAY_TIMEOUT)));
			_stub.setPortName(getInternalGWPortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setInternalGWPortEndpointAddress(java.lang.String address) {
		InternalGWPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (seatechit.msbgateway.proxy.smsgw.InternalGW.class.isAssignableFrom(serviceEndpointInterface)) {
				seatechit.msbgateway.proxy.smsgw.InternalGWPortBindingStub _stub = new seatechit.msbgateway.proxy.smsgw.InternalGWPortBindingStub(new java.net.URL(InternalGWPort_address), this);
				// _stub.setTimeout(1);
				_stub.setPortName(getInternalGWPortWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("InternalGWPort".equals(inputPortName)) {
			return getInternalGWPort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://gateway.msb.com.vn/", "InternalGWService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://gateway.msb.com.vn/", "InternalGWPort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("InternalGWPort".equals(portName)) {
			setInternalGWPortEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
