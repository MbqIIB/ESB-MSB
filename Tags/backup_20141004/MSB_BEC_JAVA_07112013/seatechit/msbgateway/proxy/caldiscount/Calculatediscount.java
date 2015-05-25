/**
 * Calculatediscount.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.caldiscount;

public interface Calculatediscount extends java.rmi.Remote {
    public seatechit.msbgateway.proxy.caldiscount.DiscountRes calDistcountProvider(java.lang.String discountCode, java.lang.String transID, java.lang.String serviceCode, java.lang.String branchCode, java.lang.String amount, java.lang.String currencyCode, java.lang.String customerType, java.lang.String businessType, java.lang.String officeCode, java.lang.String micNumber, java.lang.String channel, java.lang.String bankType) throws java.rmi.RemoteException;
    public seatechit.msbgateway.proxy.caldiscount.DiscountRes calDistcountTrans(java.lang.String discountCode, java.lang.String transID, java.lang.String serviceCode, java.lang.String branchCode, java.lang.String toBranchCode, java.lang.String transType, java.lang.String fromAcc, java.lang.String toAcc, java.lang.String fromOrgBranchCode, java.lang.String toOrgBranchCode, java.lang.String amount, java.lang.String currencyCode, java.lang.String customerType, java.lang.String businessType, java.lang.String officeCode, java.lang.String productType, java.lang.String micNumber, java.lang.String channel, java.lang.String bankType) throws java.rmi.RemoteException;
    public seatechit.msbgateway.proxy.caldiscount.DiscountRes calDistcountProviderObj(seatechit.msbgateway.proxy.caldiscount.DiscountReqProvider discountParamsProvider) throws java.rmi.RemoteException;
    public seatechit.msbgateway.proxy.caldiscount.DiscountRes calDistcountTransObj(seatechit.msbgateway.proxy.caldiscount.DiscountRequest discountParams) throws java.rmi.RemoteException;
}
