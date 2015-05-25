/**
 * PayBillingResponseReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.pmtgw;

public class PayBillingResponseReturn implements java.io.Serializable {
	private java.lang.String msgType;

	private java.lang.String customerCode;

	private java.lang.String processingCode;

	private double amount;

	private java.lang.String sequenceId;

	private java.lang.String responseCode;

	private java.lang.String paymentId;

	private java.lang.String settlementInfo;

	private java.lang.String payProviderCode;

	private java.lang.String processSts;

	private java.lang.String responseDescription;

	public PayBillingResponseReturn() {
	}

	public PayBillingResponseReturn(java.lang.String msgType, java.lang.String customerCode, java.lang.String processingCode, double amount, java.lang.String sequenceId,
			java.lang.String responseCode, java.lang.String paymentId, java.lang.String settlementInfo, java.lang.String payProviderCode, java.lang.String processSts,
			java.lang.String responseDescription) {
		this.msgType = msgType;
		this.customerCode = customerCode;
		this.processingCode = processingCode;
		this.amount = amount;
		this.sequenceId = sequenceId;
		this.responseCode = responseCode;
		this.paymentId = paymentId;
		this.settlementInfo = settlementInfo;
		this.payProviderCode = payProviderCode;
		this.processSts = processSts;
		this.responseDescription = responseDescription;
	}

	/**
	 * Gets the msgType value for this PayBillingResponseReturn.
	 * 
	 * @return msgType
	 */
	public java.lang.String getMsgType() {
		return msgType;
	}

	/**
	 * Sets the msgType value for this PayBillingResponseReturn.
	 * 
	 * @param msgType
	 */
	public void setMsgType(java.lang.String msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "msgType:" + this.msgType + ",customerCode:" + this.customerCode + ",processingCode:" + this.processingCode + ",amount:" + this.amount + ",sequenceId:" + this.sequenceId
				+ ",responseCode:" + this.responseCode + ",paymentId:" + this.paymentId + ",settlementInfo:" + this.settlementInfo + ",payProviderCode:" + this.payProviderCode + ",processSts:"
				+ this.processSts + ",responseDescription:" + this.responseDescription;
	}

	/**
	 * Gets the customerCode value for this PayBillingResponseReturn.
	 * 
	 * @return customerCode
	 */
	public java.lang.String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Sets the customerCode value for this PayBillingResponseReturn.
	 * 
	 * @param customerCode
	 */
	public void setCustomerCode(java.lang.String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * Gets the processingCode value for this PayBillingResponseReturn.
	 * 
	 * @return processingCode
	 */
	public java.lang.String getProcessingCode() {
		return processingCode;
	}

	/**
	 * Sets the processingCode value for this PayBillingResponseReturn.
	 * 
	 * @param processingCode
	 */
	public void setProcessingCode(java.lang.String processingCode) {
		this.processingCode = processingCode;
	}

	/**
	 * Gets the amount value for this PayBillingResponseReturn.
	 * 
	 * @return amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount value for this PayBillingResponseReturn.
	 * 
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the sequenceId value for this PayBillingResponseReturn.
	 * 
	 * @return sequenceId
	 */
	public java.lang.String getSequenceId() {
		return sequenceId;
	}

	/**
	 * Sets the sequenceId value for this PayBillingResponseReturn.
	 * 
	 * @param sequenceId
	 */
	public void setSequenceId(java.lang.String sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * Gets the responseCode value for this PayBillingResponseReturn.
	 * 
	 * @return responseCode
	 */
	public java.lang.String getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the responseCode value for this PayBillingResponseReturn.
	 * 
	 * @param responseCode
	 */
	public void setResponseCode(java.lang.String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the paymentId value for this PayBillingResponseReturn.
	 * 
	 * @return paymentId
	 */
	public java.lang.String getPaymentId() {
		return paymentId;
	}

	/**
	 * Sets the paymentId value for this PayBillingResponseReturn.
	 * 
	 * @param paymentId
	 */
	public void setPaymentId(java.lang.String paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * Gets the settlementInfo value for this PayBillingResponseReturn.
	 * 
	 * @return settlementInfo
	 */
	public java.lang.String getSettlementInfo() {
		return settlementInfo;
	}

	/**
	 * Sets the settlementInfo value for this PayBillingResponseReturn.
	 * 
	 * @param settlementInfo
	 */
	public void setSettlementInfo(java.lang.String settlementInfo) {
		this.settlementInfo = settlementInfo;
	}

	/**
	 * Gets the payProviderCode value for this PayBillingResponseReturn.
	 * 
	 * @return payProviderCode
	 */
	public java.lang.String getPayProviderCode() {
		return payProviderCode;
	}

	/**
	 * Sets the payProviderCode value for this PayBillingResponseReturn.
	 * 
	 * @param payProviderCode
	 */
	public void setPayProviderCode(java.lang.String payProviderCode) {
		this.payProviderCode = payProviderCode;
	}

	/**
	 * Gets the processSts value for this PayBillingResponseReturn.
	 * 
	 * @return processSts
	 */
	public java.lang.String getProcessSts() {
		return processSts;
	}

	/**
	 * Sets the processSts value for this PayBillingResponseReturn.
	 * 
	 * @param processSts
	 */
	public void setProcessSts(java.lang.String processSts) {
		this.processSts = processSts;
	}

	/**
	 * Gets the responseDescription value for this PayBillingResponseReturn.
	 * 
	 * @return responseDescription
	 */
	public java.lang.String getResponseDescription() {
		return responseDescription;
	}

	/**
	 * Sets the responseDescription value for this PayBillingResponseReturn.
	 * 
	 * @param responseDescription
	 */
	public void setResponseDescription(java.lang.String responseDescription) {
		this.responseDescription = responseDescription;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof PayBillingResponseReturn))
			return false;
		PayBillingResponseReturn other = (PayBillingResponseReturn) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true
				&& ((this.msgType == null && other.getMsgType() == null) || (this.msgType != null && this.msgType.equals(other.getMsgType())))
				&& ((this.customerCode == null && other.getCustomerCode() == null) || (this.customerCode != null && this.customerCode.equals(other.getCustomerCode())))
				&& ((this.processingCode == null && other.getProcessingCode() == null) || (this.processingCode != null && this.processingCode.equals(other.getProcessingCode())))
				&& this.amount == other.getAmount()
				&& ((this.sequenceId == null && other.getSequenceId() == null) || (this.sequenceId != null && this.sequenceId.equals(other.getSequenceId())))
				&& ((this.responseCode == null && other.getResponseCode() == null) || (this.responseCode != null && this.responseCode.equals(other.getResponseCode())))
				&& ((this.paymentId == null && other.getPaymentId() == null) || (this.paymentId != null && this.paymentId.equals(other.getPaymentId())))
				&& ((this.settlementInfo == null && other.getSettlementInfo() == null) || (this.settlementInfo != null && this.settlementInfo.equals(other.getSettlementInfo())))
				&& ((this.payProviderCode == null && other.getPayProviderCode() == null) || (this.payProviderCode != null && this.payProviderCode.equals(other.getPayProviderCode())))
				&& ((this.processSts == null && other.getProcessSts() == null) || (this.processSts != null && this.processSts.equals(other.getProcessSts())))
				&& ((this.responseDescription == null && other.getResponseDescription() == null) || (this.responseDescription != null && this.responseDescription
						.equals(other.getResponseDescription())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (getMsgType() != null) {
			_hashCode += getMsgType().hashCode();
		}
		if (getCustomerCode() != null) {
			_hashCode += getCustomerCode().hashCode();
		}
		if (getProcessingCode() != null) {
			_hashCode += getProcessingCode().hashCode();
		}
		_hashCode += new Double(getAmount()).hashCode();
		if (getSequenceId() != null) {
			_hashCode += getSequenceId().hashCode();
		}
		if (getResponseCode() != null) {
			_hashCode += getResponseCode().hashCode();
		}
		if (getPaymentId() != null) {
			_hashCode += getPaymentId().hashCode();
		}
		if (getSettlementInfo() != null) {
			_hashCode += getSettlementInfo().hashCode();
		}
		if (getPayProviderCode() != null) {
			_hashCode += getPayProviderCode().hashCode();
		}
		if (getProcessSts() != null) {
			_hashCode += getProcessSts().hashCode();
		}
		if (getResponseDescription() != null) {
			_hashCode += getResponseDescription().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(PayBillingResponseReturn.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", ">payBillingResponse>return"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("msgType");
		elemField.setXmlName(new javax.xml.namespace.QName("", "msgType"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("customerCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "customerCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("processingCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "processingCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("amount");
		elemField.setXmlName(new javax.xml.namespace.QName("", "amount"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sequenceId");
		elemField.setXmlName(new javax.xml.namespace.QName("", "sequenceId"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("responseCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "responseCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("paymentId");
		elemField.setXmlName(new javax.xml.namespace.QName("", "paymentId"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("settlementInfo");
		elemField.setXmlName(new javax.xml.namespace.QName("", "settlementInfo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("payProviderCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "payProviderCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("processSts");
		elemField.setXmlName(new javax.xml.namespace.QName("", "processSts"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("responseDescription");
		elemField.setXmlName(new javax.xml.namespace.QName("", "responseDescription"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
