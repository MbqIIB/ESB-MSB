/**
 * InternalSMSReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.smsgw;

public class InternalSMSReq implements java.io.Serializable {
	private java.lang.String sequence_id;

	private java.lang.String supply_id;

	private java.lang.String[] mobile_number;

	private java.lang.String content;

	private java.lang.String datetime;

	private int priority;

	private java.lang.String sender;

	public InternalSMSReq() {
	}

	public InternalSMSReq(java.lang.String sequence_id, java.lang.String supply_id, java.lang.String[] mobile_number, java.lang.String content, java.lang.String datetime, int priority,
			java.lang.String sender) {
		this.sequence_id = sequence_id;
		this.supply_id = supply_id;
		this.mobile_number = mobile_number;
		this.content = content;
		this.datetime = datetime;
		this.priority = priority;
		this.sender = sender;
	}

	/**
	 * Gets the sequence_id value for this InternalSMSReq.
	 * 
	 * @return sequence_id
	 */
	public java.lang.String getSequence_id() {
		return sequence_id;
	}

	/**
	 * Sets the sequence_id value for this InternalSMSReq.
	 * 
	 * @param sequence_id
	 */
	public void setSequence_id(java.lang.String sequence_id) {
		this.sequence_id = sequence_id;
	}

	/**
	 * Gets the supply_id value for this InternalSMSReq.
	 * 
	 * @return supply_id
	 */
	public java.lang.String getSupply_id() {
		return supply_id;
	}

	/**
	 * Sets the supply_id value for this InternalSMSReq.
	 * 
	 * @param supply_id
	 */
	public void setSupply_id(java.lang.String supply_id) {
		this.supply_id = supply_id;
	}

	/**
	 * Gets the mobile_number value for this InternalSMSReq.
	 * 
	 * @return mobile_number
	 */
	public java.lang.String[] getMobile_number() {
		return mobile_number;
	}

	/**
	 * Sets the mobile_number value for this InternalSMSReq.
	 * 
	 * @param mobile_number
	 */
	public void setMobile_number(java.lang.String[] mobile_number) {
		this.mobile_number = mobile_number;
	}

	public java.lang.String getMobile_number(int i) {
		return this.mobile_number[i];
	}

	public void setMobile_number(int i, java.lang.String _value) {
		this.mobile_number[i] = _value;
	}

	/**
	 * Gets the content value for this InternalSMSReq.
	 * 
	 * @return content
	 */
	public java.lang.String getContent() {
		return content;
	}

	/**
	 * Sets the content value for this InternalSMSReq.
	 * 
	 * @param content
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 * Gets the datetime value for this InternalSMSReq.
	 * 
	 * @return datetime
	 */
	public java.lang.String getDatetime() {
		return datetime;
	}

	/**
	 * Sets the datetime value for this InternalSMSReq.
	 * 
	 * @param datetime
	 */
	public void setDatetime(java.lang.String datetime) {
		this.datetime = datetime;
	}

	/**
	 * Gets the priority value for this InternalSMSReq.
	 * 
	 * @return priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the priority value for this InternalSMSReq.
	 * 
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Gets the sender value for this InternalSMSReq.
	 * 
	 * @return sender
	 */
	public java.lang.String getSender() {
		return sender;
	}

	/**
	 * Sets the sender value for this InternalSMSReq.
	 * 
	 * @param sender
	 */
	public void setSender(java.lang.String sender) {
		this.sender = sender;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof InternalSMSReq))
			return false;
		InternalSMSReq other = (InternalSMSReq) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.sequence_id == null && other.getSequence_id() == null) || (this.sequence_id != null && this.sequence_id.equals(other.getSequence_id())))
				&& ((this.supply_id == null && other.getSupply_id() == null) || (this.supply_id != null && this.supply_id.equals(other.getSupply_id())))
				&& ((this.mobile_number == null && other.getMobile_number() == null) || (this.mobile_number != null && java.util.Arrays.equals(this.mobile_number, other.getMobile_number())))
				&& ((this.content == null && other.getContent() == null) || (this.content != null && this.content.equals(other.getContent())))
				&& ((this.datetime == null && other.getDatetime() == null) || (this.datetime != null && this.datetime.equals(other.getDatetime()))) && this.priority == other.getPriority()
				&& ((this.sender == null && other.getSender() == null) || (this.sender != null && this.sender.equals(other.getSender())));
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
		if (getSequence_id() != null) {
			_hashCode += getSequence_id().hashCode();
		}
		if (getSupply_id() != null) {
			_hashCode += getSupply_id().hashCode();
		}
		if (getMobile_number() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getMobile_number()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getMobile_number(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		if (getContent() != null) {
			_hashCode += getContent().hashCode();
		}
		if (getDatetime() != null) {
			_hashCode += getDatetime().hashCode();
		}
		_hashCode += getPriority();
		if (getSender() != null) {
			_hashCode += getSender().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(InternalSMSReq.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://gateway.msb.com.vn/", "InternalSMSReq"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sequence_id");
		elemField.setXmlName(new javax.xml.namespace.QName("", "sequence_id"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("supply_id");
		elemField.setXmlName(new javax.xml.namespace.QName("", "supply_id"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mobile_number");
		elemField.setXmlName(new javax.xml.namespace.QName("", "mobile_number"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("content");
		elemField.setXmlName(new javax.xml.namespace.QName("", "content"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("datetime");
		elemField.setXmlName(new javax.xml.namespace.QName("", "datetime"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("priority");
		elemField.setXmlName(new javax.xml.namespace.QName("", "priority"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sender");
		elemField.setXmlName(new javax.xml.namespace.QName("", "sender"));
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
