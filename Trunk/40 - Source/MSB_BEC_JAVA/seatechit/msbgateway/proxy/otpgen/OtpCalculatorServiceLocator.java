/**
 * OtpCalculatorServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.otpgen;

public class OtpCalculatorServiceLocator extends org.apache.axis.client.Service implements seatechit.msbgateway.proxy.otpgen.OtpCalculatorService {
	private int serviceTimeout;

	public void setServiceTimeout(int serviceTimeout) {
		this.serviceTimeout = serviceTimeout;
	}

	public int getServiceTimeout() {
		return serviceTimeout;
	}
    public OtpCalculatorServiceLocator() {
    }


    public OtpCalculatorServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OtpCalculatorServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OtpCalculatorPort
    private java.lang.String OtpCalculatorPort_address = "http://10.0.2.51:80/OTPGenerate/OtpCalculator";

    public java.lang.String getOtpCalculatorPortAddress() {
        return OtpCalculatorPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OtpCalculatorPortWSDDServiceName = "OtpCalculatorPort";

    public java.lang.String getOtpCalculatorPortWSDDServiceName() {
        return OtpCalculatorPortWSDDServiceName;
    }

    public void setOtpCalculatorPortWSDDServiceName(java.lang.String name) {
        OtpCalculatorPortWSDDServiceName = name;
    }

    public seatechit.msbgateway.proxy.otpgen.OtpCalculator getOtpCalculatorPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OtpCalculatorPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOtpCalculatorPort(endpoint);
    }

    public seatechit.msbgateway.proxy.otpgen.OtpCalculator getOtpCalculatorPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            seatechit.msbgateway.proxy.otpgen.OtpCalculatorPortBindingStub _stub = new seatechit.msbgateway.proxy.otpgen.OtpCalculatorPortBindingStub(portAddress, this);
            _stub.setPortName(getOtpCalculatorPortWSDDServiceName());
            _stub.setTimeout(getServiceTimeout());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOtpCalculatorPortEndpointAddress(java.lang.String address) {
        OtpCalculatorPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (seatechit.msbgateway.proxy.otpgen.OtpCalculator.class.isAssignableFrom(serviceEndpointInterface)) {
                seatechit.msbgateway.proxy.otpgen.OtpCalculatorPortBindingStub _stub = new seatechit.msbgateway.proxy.otpgen.OtpCalculatorPortBindingStub(new java.net.URL(OtpCalculatorPort_address), this);
                _stub.setPortName(getOtpCalculatorPortWSDDServiceName());
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
        if ("OtpCalculatorPort".equals(inputPortName)) {
            return getOtpCalculatorPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://otpgenerate.msb.com.vn/", "OtpCalculatorService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://otpgenerate.msb.com.vn/", "OtpCalculatorPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OtpCalculatorPort".equals(portName)) {
            setOtpCalculatorPortEndpointAddress(address);
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
