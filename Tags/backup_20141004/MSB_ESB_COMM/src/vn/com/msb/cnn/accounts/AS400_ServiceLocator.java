/**
 * AS400_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.msb.cnn.accounts;

public class AS400_ServiceLocator extends org.apache.axis.client.Service implements vn.com.msb.cnn.accounts.AS400_Service {

    public AS400_ServiceLocator() {
    }


    public AS400_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AS400_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AS400HttpPort
    private java.lang.String AS400HttpPort_address = "http://10.0.2.215:7080/OneConnectServices/services/AS400";

    public java.lang.String getAS400HttpPortAddress() {
        return AS400HttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AS400HttpPortWSDDServiceName = "AS400HttpPort";

    public java.lang.String getAS400HttpPortWSDDServiceName() {
        return AS400HttpPortWSDDServiceName;
    }

    public void setAS400HttpPortWSDDServiceName(java.lang.String name) {
        AS400HttpPortWSDDServiceName = name;
    }

    public vn.com.msb.cnn.accounts.AS400_PortType getAS400HttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AS400HttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAS400HttpPort(endpoint);
    }

    public vn.com.msb.cnn.accounts.AS400_PortType getAS400HttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            vn.com.msb.cnn.accounts.AS400HttpBindingStub _stub = new vn.com.msb.cnn.accounts.AS400HttpBindingStub(portAddress, this);
            _stub.setPortName(getAS400HttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAS400HttpPortEndpointAddress(java.lang.String address) {
        AS400HttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (vn.com.msb.cnn.accounts.AS400_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                vn.com.msb.cnn.accounts.AS400HttpBindingStub _stub = new vn.com.msb.cnn.accounts.AS400HttpBindingStub(new java.net.URL(AS400HttpPort_address), this);
                _stub.setPortName(getAS400HttpPortWSDDServiceName());
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
        if ("AS400HttpPort".equals(inputPortName)) {
            return getAS400HttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://accounts.cnn.msb.com.vn", "AS400");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://accounts.cnn.msb.com.vn", "AS400HttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AS400HttpPort".equals(portName)) {
            setAS400HttpPortEndpointAddress(address);
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
