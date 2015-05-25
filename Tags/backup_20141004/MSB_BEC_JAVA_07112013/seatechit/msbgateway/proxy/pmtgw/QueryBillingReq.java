/**
 * QueryBillingReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.pmtgw;

public class QueryBillingReq implements java.io.Serializable {
	private java.lang.String msgType;

	private java.lang.String sequenceId;

	private java.lang.String customerCode;

	private java.lang.String processingCode;

	private java.lang.String requestTime;

	private java.lang.String payProviderCode;

	private java.lang.String sender;

	private java.lang.String description;

	private java.lang.String providerCode;

	public QueryBillingReq() {
	}

	public QueryBillingReq(java.lang.String msgType, java.lang.String sequenceId, java.lang.String customerCode, java.lang.String processingCode, java.lang.String requestTime,
			java.lang.String payProviderCode, java.lang.String sender, java.lang.String description, java.lang.String providerCode) {
		this.msgType = msgType;
		this.sequenceId = sequenceId;
		this.customerCode = customerCode;
		this.processingCode = processingCode;
		this.requestTime = requestTime;
		this.payProviderCode = payProviderCode;
		this.sender = sender;
		this.description = description;
		this.providerCode = providerCode;
	}

	@Override
	public String toString() {
		return "msgType:" + this.msgType + ",sequenceId :" + this.sequenceId + ",customerCode :" + this.customerCode + ",processingCode :" + this.processingCode + ",requestTime :" + this.requestTime
				+ ",payProviderCode :" + this.payProviderCode + ",sender :" + this.sender + ",description :" + this.description + ",providerCode :" + this.providerCode;
	}

	/**
	 * Gets the msgType value for this QueryBillingReq.
	 * 
	 * @return msgType
	 */
	public java.lang.String getMsgType() {
		return msgType;
	}

	/**
	 * Sets the msgType value for this QueryBillingReq.
	 * 
	 * @param msgType
	 */
	public void setMsgType(java.lang.String msgType) {
		this.msgType = msgType;
	}

	/**
	 * Gets the sequenceId value for this QueryBillingReq.
	 * 
	 * @return sequenceId
	 */
	public java.lang.String getSequenceId() {
		return sequenceId;
	}

	/**
	 * Sets the sequenceId value for this QueryBillingReq.
	 * 
	 * @param sequenceId
	 */
	public void setSequenceId(java.lang.String sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * Gets the customerCode value for this QueryBillingReq.
	 * 
	 * @return customerCode
	 */
	public java.lang.String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Sets the customerCode value for this QueryBillingReq.
	 * 
	 * @param customerCode
	 */
	public void setCustomerCode(java.lang.String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * Gets the processingCode value for this QueryBillingReq.
	 * 
	 * @return processingCode
	 */
	public java.lang.String getProcessingCode() {
		return processingCode;
	}

	/**
	 * Sets the processingCode value for this QueryBillingReq.
	 * 
	 * @param processingCode
	 */
	public void setProcessingCode(java.lang.String processingCode) {
		this.processingCode = processingCode;
	}

	/**
	 * Gets the requestTime value for this QueryBillingReq.
	 * 
	 * @return requestTime
	 */
	public java.lang.String getRequestTime() {
		return requestTime;
	}

	/**
	 * Sets the requestTime value for this QueryBillingReq.
	 * 
	 * @param requestTime
	 */
	public void setRequestTime(java.lang.String requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * Gets the payProviderCode value for this QueryBillingReq.
	 * 
	 * @return payProviderCode
	 */
	public java.lang.String getPayProviderCode() {
		return payProviderCode;
	}

	/**
	 * Sets the payProviderCode value for this QueryBillingReq.
	 * 
	 * @param payProviderCode
	 */
	public void setPayProviderCode(java.lang.String payProviderCode) {
		this.payProviderCode = payProviderCode;
	}

	/**
	 * Gets the sender value for this QueryBillingReq.
	 * 
	 * @return sender
	 */
	public java.lang.String getSender() {
		return sender;
	}

	/**
	 * Sets the sender value for this QueryBillingReq.
	 * 
	 * @param sender
	 */
	public void setSender(java.lang.String sender) {
		this.sender = sender;
	}

	/**
	 * Gets the description value for this QueryBillingReq.
	 * 
	 * @return description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * Sets the description value for this QueryBillingReq.
	 * 
	 * @param description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets the providerCode value for this QueryBillingReq.
	 * 
	 * @return providerCode
	 */
	public java.lang.String getProviderCode() {
		return providerCode;
	}

	/**
	 * Sets the providerCode value for this QueryBillingReq.
	 * 
	 * @param providerCode
	 */
	public void setProviderCode(java.lang.String providerCode) {
		this.providerCode = providerCode;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof QueryBillingReq))
			return false;
		QueryBillingReq other = (QueryBillingReq) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.msgType == null && other.getMsgType() == null) || (this.msgType != null && this.msgType.equals(other.getMsgType())))
				&& ((this.sequenceId == null && other.getSequenceId() == null) || (this.sequenceId != null && this.sequenceId.equals(other.getSequenceId())))
				&& ((this.customerCode == null && other.getCustomerCode() == null) || (this.customerCode != null && this.customerCode.equals(other.getCustomerCode())))
				&& ((this.processingCode == null && other.getProcessingCode() == null) || (this.processingCode != null && this.processingCode.equals(other.getProcessingCode())))
				&& ((this.requestTime == null && other.getRequestTime() == null) || (this.requestTime != null && this.requestTime.equals(other.getRequestTime())))
				&& ((this.payProviderCode == null && other.getPayProviderCode() == null) || (this.payProviderCode != null && this.payProviderCode.equals(other.getPayProviderCode())))
				&& ((this.sender == null && other.getSender() == null) || (this.sender != null && this.sender.equals(other.getSender())))
				&& ((this.description == null && other.getDescription() == null) || (this.description != null && this.description.equals(other.getDescription())))
				&& ((this.providerCode == null && other.getProviderCode() == null) || (this.providerCode != null && this.providerCode.equals(other.getProviderCode())));
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
		if (getSequenceId() != null) {
			_hashCode += getSequenceId().hashCode();
		}
		if (getCustomerCode() != null) {
			_hashCode += getCustomerCode().hashCode();
		}
		if (getProcessingCode() != null) {
			_hashCode += getProcessingCode().hashCode();
		}
		if (getRequestTime() != null) {
			_hashCode += getRequestTime().hashCode();
		}
		if (getPayProviderCode() != null) {
			_hashCode += getPayProviderCode().hashCode();
		}
		if (getSender() != null) {
			_hashCode += getSender().hashCode();
		}
		if (getDescription() != null) {
			_hashCode += getDescription().hashCode();
		}
		if (getProviderCode() != null) {
			_hashCode += getProviderCode().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(QueryBillingReq.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", ">queryBilling>req"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("msgType");
		elemField.setXmlName(new javax.xml.namespace.QName("", "msgType"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sequenceId");
		elemField.setXmlName(new javax.xml.namespace.QName("", "sequenceId"));
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
		elemField.setFieldName("requestTime");
		elemField.setXmlName(new javax.xml.namespace.QName("", "requestTime"));
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
		elemField.setFieldName("sender");
		elemField.setXmlName(new javax.xml.namespace.QName("", "sender"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("description");
		elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("providerCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "providerCode"));
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
