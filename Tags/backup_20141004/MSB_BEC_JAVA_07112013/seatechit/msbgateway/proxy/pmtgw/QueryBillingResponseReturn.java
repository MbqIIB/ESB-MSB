/**
 * QueryBillingResponseReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.pmtgw;

public class QueryBillingResponseReturn implements java.io.Serializable {
	private java.lang.String msgType;

	private java.lang.String sequenceId;

	private java.lang.String customerCode;

	private java.lang.String processingCode;

	private java.lang.String payProviderCode;

	private double enquiryAmount;

	private java.lang.String enquiryInfo;

	private java.lang.String responseCode;

	private java.lang.String responseDescription;

	public QueryBillingResponseReturn() {
	}

	public QueryBillingResponseReturn(java.lang.String msgType, java.lang.String sequenceId, java.lang.String customerCode, java.lang.String processingCode, java.lang.String payProviderCode,
			double enquiryAmount, java.lang.String enquiryInfo, java.lang.String responseCode, java.lang.String responseDescription) {
		this.msgType = msgType;
		this.sequenceId = sequenceId;
		this.customerCode = customerCode;
		this.processingCode = processingCode;
		this.payProviderCode = payProviderCode;
		this.enquiryAmount = enquiryAmount;
		this.enquiryInfo = enquiryInfo;
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	@Override
	public String toString() {
		return "msgType:" + this.msgType + ",sequenceId:" + this.sequenceId + ",customerCode:" + this.customerCode + ",processingCode:" + this.processingCode + ",payProviderCode:"
				+ this.payProviderCode + ",enquiryAmount:" + this.enquiryAmount + ",enquiryInfo:" + this.enquiryInfo + ",responseCode:" + this.responseCode + ",responseDescription:"
				+ this.responseDescription;
	}

	/**
	 * Gets the msgType value for this QueryBillingResponseReturn.
	 * 
	 * @return msgType
	 */
	public java.lang.String getMsgType() {
		return msgType;
	}

	/**
	 * Sets the msgType value for this QueryBillingResponseReturn.
	 * 
	 * @param msgType
	 */
	public void setMsgType(java.lang.String msgType) {
		this.msgType = msgType;
	}

	/**
	 * Gets the sequenceId value for this QueryBillingResponseReturn.
	 * 
	 * @return sequenceId
	 */
	public java.lang.String getSequenceId() {
		return sequenceId;
	}

	/**
	 * Sets the sequenceId value for this QueryBillingResponseReturn.
	 * 
	 * @param sequenceId
	 */
	public void setSequenceId(java.lang.String sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * Gets the customerCode value for this QueryBillingResponseReturn.
	 * 
	 * @return customerCode
	 */
	public java.lang.String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Sets the customerCode value for this QueryBillingResponseReturn.
	 * 
	 * @param customerCode
	 */
	public void setCustomerCode(java.lang.String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * Gets the processingCode value for this QueryBillingResponseReturn.
	 * 
	 * @return processingCode
	 */
	public java.lang.String getProcessingCode() {
		return processingCode;
	}

	/**
	 * Sets the processingCode value for this QueryBillingResponseReturn.
	 * 
	 * @param processingCode
	 */
	public void setProcessingCode(java.lang.String processingCode) {
		this.processingCode = processingCode;
	}

	/**
	 * Gets the payProviderCode value for this QueryBillingResponseReturn.
	 * 
	 * @return payProviderCode
	 */
	public java.lang.String getPayProviderCode() {
		return payProviderCode;
	}

	/**
	 * Sets the payProviderCode value for this QueryBillingResponseReturn.
	 * 
	 * @param payProviderCode
	 */
	public void setPayProviderCode(java.lang.String payProviderCode) {
		this.payProviderCode = payProviderCode;
	}

	/**
	 * Gets the enquiryAmount value for this QueryBillingResponseReturn.
	 * 
	 * @return enquiryAmount
	 */
	public double getEnquiryAmount() {
		return enquiryAmount;
	}

	/**
	 * Sets the enquiryAmount value for this QueryBillingResponseReturn.
	 * 
	 * @param enquiryAmount
	 */
	public void setEnquiryAmount(double enquiryAmount) {
		this.enquiryAmount = enquiryAmount;
	}

	/**
	 * Gets the enquiryInfo value for this QueryBillingResponseReturn.
	 * 
	 * @return enquiryInfo
	 */
	public java.lang.String getEnquiryInfo() {
		return enquiryInfo;
	}

	/**
	 * Sets the enquiryInfo value for this QueryBillingResponseReturn.
	 * 
	 * @param enquiryInfo
	 */
	public void setEnquiryInfo(java.lang.String enquiryInfo) {
		this.enquiryInfo = enquiryInfo;
	}

	/**
	 * Gets the responseCode value for this QueryBillingResponseReturn.
	 * 
	 * @return responseCode
	 */
	public java.lang.String getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the responseCode value for this QueryBillingResponseReturn.
	 * 
	 * @param responseCode
	 */
	public void setResponseCode(java.lang.String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the responseDescription value for this QueryBillingResponseReturn.
	 * 
	 * @return responseDescription
	 */
	public java.lang.String getResponseDescription() {
		return responseDescription;
	}

	/**
	 * Sets the responseDescription value for this QueryBillingResponseReturn.
	 * 
	 * @param responseDescription
	 */
	public void setResponseDescription(java.lang.String responseDescription) {
		this.responseDescription = responseDescription;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof QueryBillingResponseReturn))
			return false;
		QueryBillingResponseReturn other = (QueryBillingResponseReturn) obj;
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
				&& ((this.sequenceId == null && other.getSequenceId() == null) || (this.sequenceId != null && this.sequenceId.equals(other.getSequenceId())))
				&& ((this.customerCode == null && other.getCustomerCode() == null) || (this.customerCode != null && this.customerCode.equals(other.getCustomerCode())))
				&& ((this.processingCode == null && other.getProcessingCode() == null) || (this.processingCode != null && this.processingCode.equals(other.getProcessingCode())))
				&& ((this.payProviderCode == null && other.getPayProviderCode() == null) || (this.payProviderCode != null && this.payProviderCode.equals(other.getPayProviderCode())))
				&& this.enquiryAmount == other.getEnquiryAmount()
				&& ((this.enquiryInfo == null && other.getEnquiryInfo() == null) || (this.enquiryInfo != null && this.enquiryInfo.equals(other.getEnquiryInfo())))
				&& ((this.responseCode == null && other.getResponseCode() == null) || (this.responseCode != null && this.responseCode.equals(other.getResponseCode())))
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
		if (getSequenceId() != null) {
			_hashCode += getSequenceId().hashCode();
		}
		if (getCustomerCode() != null) {
			_hashCode += getCustomerCode().hashCode();
		}
		if (getProcessingCode() != null) {
			_hashCode += getProcessingCode().hashCode();
		}
		if (getPayProviderCode() != null) {
			_hashCode += getPayProviderCode().hashCode();
		}
		_hashCode += new Double(getEnquiryAmount()).hashCode();
		if (getEnquiryInfo() != null) {
			_hashCode += getEnquiryInfo().hashCode();
		}
		if (getResponseCode() != null) {
			_hashCode += getResponseCode().hashCode();
		}
		if (getResponseDescription() != null) {
			_hashCode += getResponseDescription().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(QueryBillingResponseReturn.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", ">queryBillingResponse>return"));
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
		elemField.setFieldName("payProviderCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "payProviderCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("enquiryAmount");
		elemField.setXmlName(new javax.xml.namespace.QName("", "enquiryAmount"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("enquiryInfo");
		elemField.setXmlName(new javax.xml.namespace.QName("", "enquiryInfo"));
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
