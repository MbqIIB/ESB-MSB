/**
 * InReceiverMoneyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.wuabmt;

public class InReceiverMoneyServiceLocator extends org.apache.axis.client.Service implements seatechit.msbgateway.proxy.wuabmt.InReceiverMoneyService {

	private static final long serialVersionUID = 1L;
	private int serviceTimeout;

	public void setServiceTimeout(int serviceTimeout) {
		this.serviceTimeout = serviceTimeout;
	}

	public int getServiceTimeout() {
		return serviceTimeout;
	}
    public InReceiverMoneyServiceLocator() {
    }

    public InReceiverMoneyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InReceiverMoneyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InReceiverMoneyPort
    private java.lang.String InReceiverMoneyPort_address = "http://10.0.2.51:80/ePaymentGateway/InReceiverMoney";

    public java.lang.String getInReceiverMoneyPortAddress() {
        return InReceiverMoneyPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InReceiverMoneyPortWSDDServiceName = "InReceiverMoneyPort";

    public java.lang.String getInReceiverMoneyPortWSDDServiceName() {
        return InReceiverMoneyPortWSDDServiceName;
    }

    public void setInReceiverMoneyPortWSDDServiceName(java.lang.String name) {
        InReceiverMoneyPortWSDDServiceName = name;
    }

    public seatechit.msbgateway.proxy.wuabmt.InReceiverMoney getInReceiverMoneyPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InReceiverMoneyPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInReceiverMoneyPort(endpoint);
    }

    public seatechit.msbgateway.proxy.wuabmt.InReceiverMoney getInReceiverMoneyPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            seatechit.msbgateway.proxy.wuabmt.InReceiverMoneyPortBindingStub _stub = new seatechit.msbgateway.proxy.wuabmt.InReceiverMoneyPortBindingStub(portAddress, this);
            _stub.setPortName(getInReceiverMoneyPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInReceiverMoneyPortEndpointAddress(java.lang.String address) {
        InReceiverMoneyPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (seatechit.msbgateway.proxy.wuabmt.InReceiverMoney.class.isAssignableFrom(serviceEndpointInterface)) {
                seatechit.msbgateway.proxy.wuabmt.InReceiverMoneyPortBindingStub _stub = new seatechit.msbgateway.proxy.wuabmt.InReceiverMoneyPortBindingStub(new java.net.URL(InReceiverMoneyPort_address), this);
                _stub.setPortName(getInReceiverMoneyPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("InReceiverMoneyPort".equals(inputPortName)) {
            return getInReceiverMoneyPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "InReceiverMoneyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "InReceiverMoneyPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InReceiverMoneyPort".equals(portName)) {
            setInReceiverMoneyPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
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
