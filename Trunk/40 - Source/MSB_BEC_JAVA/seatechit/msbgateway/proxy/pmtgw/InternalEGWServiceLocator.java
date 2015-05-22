/**
 * InternalEGWServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.pmtgw;

public class InternalEGWServiceLocator extends org.apache.axis.client.Service implements seatechit.msbgateway.proxy.pmtgw.InternalEGWService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int serviceTimeout;

	public void setServiceTimeout(int serviceTimeout) {
		this.serviceTimeout = serviceTimeout;
	}

	public int getServiceTimeout() {
		return serviceTimeout;
	}

	public InternalEGWServiceLocator() {
	}

	public InternalEGWServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public InternalEGWServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for InternalEGWPort
	private java.lang.String InternalEGWPort_address = "http://10.0.2.51:80/ePaymentGateway/InternalEGW";

	public java.lang.String getInternalEGWPortAddress() {
		return InternalEGWPort_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String InternalEGWPortWSDDServiceName = "InternalEGWPort";

	public java.lang.String getInternalEGWPortWSDDServiceName() {
		return InternalEGWPortWSDDServiceName;
	}

	public void setInternalEGWPortWSDDServiceName(java.lang.String name) {
		InternalEGWPortWSDDServiceName = name;
	}

	public seatechit.msbgateway.proxy.pmtgw.InternalEGW getInternalEGWPort() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(InternalEGWPort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getInternalEGWPort(endpoint);
	}

	public seatechit.msbgateway.proxy.pmtgw.InternalEGW getInternalEGWPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			seatechit.msbgateway.proxy.pmtgw.InternalEGWPortBindingStub _stub = new seatechit.msbgateway.proxy.pmtgw.InternalEGWPortBindingStub(portAddress, this);
			_stub.setTimeout(getServiceTimeout());
			_stub.setPortName(getInternalEGWPortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setInternalEGWPortEndpointAddress(java.lang.String address) {
		InternalEGWPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (seatechit.msbgateway.proxy.pmtgw.InternalEGW.class.isAssignableFrom(serviceEndpointInterface)) {
				seatechit.msbgateway.proxy.pmtgw.InternalEGWPortBindingStub _stub = new seatechit.msbgateway.proxy.pmtgw.InternalEGWPortBindingStub(new java.net.URL(InternalEGWPort_address), this);
				_stub.setPortName(getInternalEGWPortWSDDServiceName());
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
		if ("InternalEGWPort".equals(inputPortName)) {
			return getInternalEGWPort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "InternalEGWService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "InternalEGWPort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("InternalEGWPort".equals(portName)) {
			setInternalEGWPortEndpointAddress(address);
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
