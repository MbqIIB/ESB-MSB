/**
 * AS400_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.as400gw;

public interface AS400_Service extends javax.xml.rpc.Service {
	public java.lang.String getAS400HttpPortAddress();

	public seatechit.msbgateway.proxy.as400gw.AS400_PortType getAS400HttpPort() throws javax.xml.rpc.ServiceException;

	public seatechit.msbgateway.proxy.as400gw.AS400_PortType getAS400HttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}