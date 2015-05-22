/**
 * InternalSMSRes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.smsgw;

public class InternalSMSRes implements java.io.Serializable {
	private java.lang.String responseCode;

	private java.lang.String seqId;

	private java.lang.String signatute;

	private java.lang.String transCode;

	public InternalSMSRes() {
	}

	public InternalSMSRes(java.lang.String responseCode, java.lang.String seqId, java.lang.String signatute, java.lang.String transCode) {
		this.responseCode = responseCode;
		this.seqId = seqId;
		this.signatute = signatute;
		this.transCode = transCode;
	}

	/**
	 * Gets the responseCode value for this InternalSMSRes.
	 * 
	 * @return responseCode
	 */
	public java.lang.String getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the responseCode value for this InternalSMSRes.
	 * 
	 * @param responseCode
	 */
	public void setResponseCode(java.lang.String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the seqId value for this InternalSMSRes.
	 * 
	 * @return seqId
	 */
	public java.lang.String getSeqId() {
		return seqId;
	}

	/**
	 * Sets the seqId value for this InternalSMSRes.
	 * 
	 * @param seqId
	 */
	public void setSeqId(java.lang.String seqId) {
		this.seqId = seqId;
	}

	/**
	 * Gets the signatute value for this InternalSMSRes.
	 * 
	 * @return signatute
	 */
	public java.lang.String getSignatute() {
		return signatute;
	}

	/**
	 * Sets the signatute value for this InternalSMSRes.
	 * 
	 * @param signatute
	 */
	public void setSignatute(java.lang.String signatute) {
		this.signatute = signatute;
	}

	/**
	 * Gets the transCode value for this InternalSMSRes.
	 * 
	 * @return transCode
	 */
	public java.lang.String getTransCode() {
		return transCode;
	}

	/**
	 * Sets the transCode value for this InternalSMSRes.
	 * 
	 * @param transCode
	 */
	public void setTransCode(java.lang.String transCode) {
		this.transCode = transCode;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof InternalSMSRes))
			return false;
		InternalSMSRes other = (InternalSMSRes) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.responseCode == null && other.getResponseCode() == null) || (this.responseCode != null && this.responseCode.equals(other.getResponseCode())))
				&& ((this.seqId == null && other.getSeqId() == null) || (this.seqId != null && this.seqId.equals(other.getSeqId())))
				&& ((this.signatute == null && other.getSignatute() == null) || (this.signatute != null && this.signatute.equals(other.getSignatute())))
				&& ((this.transCode == null && other.getTransCode() == null) || (this.transCode != null && this.transCode.equals(other.getTransCode())));
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
		if (getResponseCode() != null) {
			_hashCode += getResponseCode().hashCode();
		}
		if (getSeqId() != null) {
			_hashCode += getSeqId().hashCode();
		}
		if (getSignatute() != null) {
			_hashCode += getSignatute().hashCode();
		}
		if (getTransCode() != null) {
			_hashCode += getTransCode().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(InternalSMSRes.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://gateway.msb.com.vn/", "internalSMSRes"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("responseCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "responseCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("seqId");
		elemField.setXmlName(new javax.xml.namespace.QName("", "seqId"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("signatute");
		elemField.setXmlName(new javax.xml.namespace.QName("", "signatute"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("transCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "transCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
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
