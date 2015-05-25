/**
 * InternalGW.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.smsgw;

public interface InternalGW extends java.rmi.Remote {
	public seatechit.msbgateway.proxy.smsgw.InternalSMSRes sendSMSObj(seatechit.msbgateway.proxy.smsgw.InternalSMSReq arg0) throws java.rmi.RemoteException, seatechit.msbgateway.proxy.smsgw.Exception;

	public seatechit.msbgateway.proxy.smsgw.InternalSMSRes sendSMS(java.lang.String sequence_id, java.lang.String supply_id, java.lang.String[] mobile_number, java.lang.String content,
			java.lang.String datetime, int priority, java.lang.String app_sender) throws java.rmi.RemoteException, seatechit.msbgateway.proxy.smsgw.Exception;
}
