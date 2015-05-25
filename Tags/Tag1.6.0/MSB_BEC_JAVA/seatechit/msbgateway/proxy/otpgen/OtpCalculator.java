/**
 * OtpCalculator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.otpgen;

public interface OtpCalculator extends java.rmi.Remote {
    public int checkOtp(java.lang.String transactionID, long otpTransactionId, int iValueTime, java.lang.String sOtp) throws java.rmi.RemoteException, seatechit.msbgateway.proxy.otpgen.PreexistingEntityException, seatechit.msbgateway.proxy.otpgen.Exception;
    public java.lang.String getOtp(java.lang.String transactionID) throws java.rmi.RemoteException, seatechit.msbgateway.proxy.otpgen.PreexistingEntityException, seatechit.msbgateway.proxy.otpgen.Exception;
}
