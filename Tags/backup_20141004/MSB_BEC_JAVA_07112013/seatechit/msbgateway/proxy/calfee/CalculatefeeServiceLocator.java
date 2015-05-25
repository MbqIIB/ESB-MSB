/**
 * CalculatefeeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.calfee;

public class CalculatefeeServiceLocator extends org.apache.axis.client.Service implements seatechit.msbgateway.proxy.calfee.CalculatefeeService {
	private int serviceTimeout;

	public void setServiceTimeout(int serviceTimeout) {
		this.serviceTimeout = serviceTimeout;
	}
    public CalculatefeeServiceLocator() {
    }


    public CalculatefeeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CalculatefeeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for calculatefeePort
    private java.lang.String calculatefeePort_address = "http://10.0.2.51:80/calculateFee/calculatefee";

    public java.lang.String getcalculatefeePortAddress() {
        return calculatefeePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String calculatefeePortWSDDServiceName = "calculatefeePort";

    public java.lang.String getcalculatefeePortWSDDServiceName() {
        return calculatefeePortWSDDServiceName;
    }

    public void setcalculatefeePortWSDDServiceName(java.lang.String name) {
        calculatefeePortWSDDServiceName = name;
    }

    public seatechit.msbgateway.proxy.calfee.Calculatefee getcalculatefeePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(calculatefeePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getcalculatefeePort(endpoint);
    }

    public seatechit.msbgateway.proxy.calfee.Calculatefee getcalculatefeePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            seatechit.msbgateway.proxy.calfee.CalculatefeePortBindingStub _stub = new seatechit.msbgateway.proxy.calfee.CalculatefeePortBindingStub(portAddress, this);
            _stub.setPortName(getcalculatefeePortWSDDServiceName());
            _stub.setTimeout(serviceTimeout);
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setcalculatefeePortEndpointAddress(java.lang.String address) {
        calculatefeePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (seatechit.msbgateway.proxy.calfee.Calculatefee.class.isAssignableFrom(serviceEndpointInterface)) {
                seatechit.msbgateway.proxy.calfee.CalculatefeePortBindingStub _stub = new seatechit.msbgateway.proxy.calfee.CalculatefeePortBindingStub(new java.net.URL(calculatefeePort_address), this);
                _stub.setPortName(getcalculatefeePortWSDDServiceName());
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
        if ("calculatefeePort".equals(inputPortName)) {
            return getcalculatefeePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://calfee.msb.com.vn/", "calculatefeeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://calfee.msb.com.vn/", "calculatefeePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("calculatefeePort".equals(portName)) {
            setcalculatefeePortEndpointAddress(address);
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
