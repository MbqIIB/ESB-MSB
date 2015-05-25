/**
 * PayBillingReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.pmtgw;

public class PayBillingReq implements java.io.Serializable {
	private java.lang.String msgType;

	private java.lang.String sequenceId;

	private java.lang.String customerCode;

	private java.lang.String processingCode;

	private java.lang.String requestTime;

	private double amount;

	private double amountDiscounted;

	private java.lang.String settlementDate;

	private java.lang.String payProviderCode;

	private java.lang.String sender;

	private java.lang.String accountNumber;

	private java.lang.String cifNumber;

	private java.lang.String description;

	private java.lang.String providerCode;

	private java.lang.String branch;

	private java.lang.String fromid;

	private java.lang.String fromName;

	private java.lang.String billCode;

	private java.lang.String ccycd;

	public PayBillingReq() {
	}

	public PayBillingReq(java.lang.String msgType, java.lang.String sequenceId, java.lang.String customerCode, java.lang.String processingCode, java.lang.String requestTime, double amount,
			double amountDiscounted, java.lang.String settlementDate, java.lang.String payProviderCode, java.lang.String sender, java.lang.String accountNumber, java.lang.String cifNumber,
			java.lang.String description, java.lang.String providerCode, java.lang.String branch, java.lang.String fromid, java.lang.String fromName, java.lang.String billCode, java.lang.String ccycd) {
		this.msgType = msgType;
		this.sequenceId = sequenceId;
		this.customerCode = customerCode;
		this.processingCode = processingCode;
		this.requestTime = requestTime;
		this.amount = amount;
		this.amountDiscounted = amountDiscounted;
		this.settlementDate = settlementDate;
		this.payProviderCode = payProviderCode;
		this.sender = sender;
		this.accountNumber = accountNumber;
		this.cifNumber = cifNumber;
		this.description = description;
		this.providerCode = providerCode;
		this.branch = branch;
		this.fromid = fromid;
		this.fromName = fromName;
		this.billCode = billCode;
		this.ccycd = ccycd;
	}

	@Override
	public String toString() {
		return "msgType:" + this.msgType + ",sequenceId:" + this.sequenceId + ",customerCode:" + this.customerCode + ",processingCode:" + this.processingCode + ",requestTime:" + this.requestTime
				+ ",amount:" + this.amount + ",amountDiscounted:" + this.amountDiscounted + ",settlementDate:" + this.settlementDate + ",payProviderCode:" + this.payProviderCode + ",sender:"
				+ this.sender + ",accountNumber:" + this.accountNumber + ",cifNumber:" + this.cifNumber + ",description:" + this.description + ",providerCode:" + this.providerCode + ",branch:"
				+ this.branch + ",fromid:" + this.fromid + ",fromName:" + this.fromName + ",billCode:" + this.billCode + ",ccycd:" + this.ccycd;
	}

	/**
	 * Gets the msgType value for this PayBillingReq.
	 * 
	 * @return msgType
	 */
	public java.lang.String getMsgType() {
		return msgType;
	}

	/**
	 * Sets the msgType value for this PayBillingReq.
	 * 
	 * @param msgType
	 */
	public void setMsgType(java.lang.String msgType) {
		this.msgType = msgType;
	}

	/**
	 * Gets the sequenceId value for this PayBillingReq.
	 * 
	 * @return sequenceId
	 */
	public java.lang.String getSequenceId() {
		return sequenceId;
	}

	/**
	 * Sets the sequenceId value for this PayBillingReq.
	 * 
	 * @param sequenceId
	 */
	public void setSequenceId(java.lang.String sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * Gets the customerCode value for this PayBillingReq.
	 * 
	 * @return customerCode
	 */
	public java.lang.String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Sets the customerCode value for this PayBillingReq.
	 * 
	 * @param customerCode
	 */
	public void setCustomerCode(java.lang.String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * Gets the processingCode value for this PayBillingReq.
	 * 
	 * @return processingCode
	 */
	public java.lang.String getProcessingCode() {
		return processingCode;
	}

	/**
	 * Sets the processingCode value for this PayBillingReq.
	 * 
	 * @param processingCode
	 */
	public void setProcessingCode(java.lang.String processingCode) {
		this.processingCode = processingCode;
	}

	/**
	 * Gets the requestTime value for this PayBillingReq.
	 * 
	 * @return requestTime
	 */
	public java.lang.String getRequestTime() {
		return requestTime;
	}

	/**
	 * Sets the requestTime value for this PayBillingReq.
	 * 
	 * @param requestTime
	 */
	public void setRequestTime(java.lang.String requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * Gets the amount value for this PayBillingReq.
	 * 
	 * @return amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount value for this PayBillingReq.
	 * 
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the amountDiscounted value for this PayBillingReq.
	 * 
	 * @return amountDiscounted
	 */
	public double getAmountDiscounted() {
		return amountDiscounted;
	}

	/**
	 * Sets the amountDiscounted value for this PayBillingReq.
	 * 
	 * @param amountDiscounted
	 */
	public void setAmountDiscounted(double amountDiscounted) {
		this.amountDiscounted = amountDiscounted;
	}

	/**
	 * Gets the settlementDate value for this PayBillingReq.
	 * 
	 * @return settlementDate
	 */
	public java.lang.String getSettlementDate() {
		return settlementDate;
	}

	/**
	 * Sets the settlementDate value for this PayBillingReq.
	 * 
	 * @param settlementDate
	 */
	public void setSettlementDate(java.lang.String settlementDate) {
		this.settlementDate = settlementDate;
	}

	/**
	 * Gets the payProviderCode value for this PayBillingReq.
	 * 
	 * @return payProviderCode
	 */
	public java.lang.String getPayProviderCode() {
		return payProviderCode;
	}

	/**
	 * Sets the payProviderCode value for this PayBillingReq.
	 * 
	 * @param payProviderCode
	 */
	public void setPayProviderCode(java.lang.String payProviderCode) {
		this.payProviderCode = payProviderCode;
	}

	/**
	 * Gets the sender value for this PayBillingReq.
	 * 
	 * @return sender
	 */
	public java.lang.String getSender() {
		return sender;
	}

	/**
	 * Sets the sender value for this PayBillingReq.
	 * 
	 * @param sender
	 */
	public void setSender(java.lang.String sender) {
		this.sender = sender;
	}

	/**
	 * Gets the accountNumber value for this PayBillingReq.
	 * 
	 * @return accountNumber
	 */
	public java.lang.String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the accountNumber value for this PayBillingReq.
	 * 
	 * @param accountNumber
	 */
	public void setAccountNumber(java.lang.String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the cifNumber value for this PayBillingReq.
	 * 
	 * @return cifNumber
	 */
	public java.lang.String getCifNumber() {
		return cifNumber;
	}

	/**
	 * Sets the cifNumber value for this PayBillingReq.
	 * 
	 * @param cifNumber
	 */
	public void setCifNumber(java.lang.String cifNumber) {
		this.cifNumber = cifNumber;
	}

	/**
	 * Gets the description value for this PayBillingReq.
	 * 
	 * @return description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * Sets the description value for this PayBillingReq.
	 * 
	 * @param description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets the providerCode value for this PayBillingReq.
	 * 
	 * @return providerCode
	 */
	public java.lang.String getProviderCode() {
		return providerCode;
	}

	/**
	 * Sets the providerCode value for this PayBillingReq.
	 * 
	 * @param providerCode
	 */
	public void setProviderCode(java.lang.String providerCode) {
		this.providerCode = providerCode;
	}

	/**
	 * Gets the branch value for this PayBillingReq.
	 * 
	 * @return branch
	 */
	public java.lang.String getBranch() {
		return branch;
	}

	/**
	 * Sets the branch value for this PayBillingReq.
	 * 
	 * @param branch
	 */
	public void setBranch(java.lang.String branch) {
		this.branch = branch;
	}

	/**
	 * Gets the fromid value for this PayBillingReq.
	 * 
	 * @return fromid
	 */
	public java.lang.String getFromid() {
		return fromid;
	}

	/**
	 * Sets the fromid value for this PayBillingReq.
	 * 
	 * @param fromid
	 */
	public void setFromid(java.lang.String fromid) {
		this.fromid = fromid;
	}

	/**
	 * Gets the fromName value for this PayBillingReq.
	 * 
	 * @return fromName
	 */
	public java.lang.String getFromName() {
		return fromName;
	}

	/**
	 * Sets the fromName value for this PayBillingReq.
	 * 
	 * @param fromName
	 */
	public void setFromName(java.lang.String fromName) {
		this.fromName = fromName;
	}

	/**
	 * Gets the billCode value for this PayBillingReq.
	 * 
	 * @return billCode
	 */
	public java.lang.String getBillCode() {
		return billCode;
	}

	/**
	 * Sets the billCode value for this PayBillingReq.
	 * 
	 * @param billCode
	 */
	public void setBillCode(java.lang.String billCode) {
		this.billCode = billCode;
	}

	/**
	 * Gets the ccycd value for this PayBillingReq.
	 * 
	 * @return ccycd
	 */
	public java.lang.String getCcycd() {
		return ccycd;
	}

	/**
	 * Sets the ccycd value for this PayBillingReq.
	 * 
	 * @param ccycd
	 */
	public void setCcycd(java.lang.String ccycd) {
		this.ccycd = ccycd;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof PayBillingReq))
			return false;
		PayBillingReq other = (PayBillingReq) obj;
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
				&& ((this.requestTime == null && other.getRequestTime() == null) || (this.requestTime != null && this.requestTime.equals(other.getRequestTime()))) && this.amount == other.getAmount()
				&& this.amountDiscounted == other.getAmountDiscounted()
				&& ((this.settlementDate == null && other.getSettlementDate() == null) || (this.settlementDate != null && this.settlementDate.equals(other.getSettlementDate())))
				&& ((this.payProviderCode == null && other.getPayProviderCode() == null) || (this.payProviderCode != null && this.payProviderCode.equals(other.getPayProviderCode())))
				&& ((this.sender == null && other.getSender() == null) || (this.sender != null && this.sender.equals(other.getSender())))
				&& ((this.accountNumber == null && other.getAccountNumber() == null) || (this.accountNumber != null && this.accountNumber.equals(other.getAccountNumber())))
				&& ((this.cifNumber == null && other.getCifNumber() == null) || (this.cifNumber != null && this.cifNumber.equals(other.getCifNumber())))
				&& ((this.description == null && other.getDescription() == null) || (this.description != null && this.description.equals(other.getDescription())))
				&& ((this.providerCode == null && other.getProviderCode() == null) || (this.providerCode != null && this.providerCode.equals(other.getProviderCode())))
				&& ((this.branch == null && other.getBranch() == null) || (this.branch != null && this.branch.equals(other.getBranch())))
				&& ((this.fromid == null && other.getFromid() == null) || (this.fromid != null && this.fromid.equals(other.getFromid())))
				&& ((this.fromName == null && other.getFromName() == null) || (this.fromName != null && this.fromName.equals(other.getFromName())))
				&& ((this.billCode == null && other.getBillCode() == null) || (this.billCode != null && this.billCode.equals(other.getBillCode())))
				&& ((this.ccycd == null && other.getCcycd() == null) || (this.ccycd != null && this.ccycd.equals(other.getCcycd())));
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
		_hashCode += new Double(getAmount()).hashCode();
		_hashCode += new Double(getAmountDiscounted()).hashCode();
		if (getSettlementDate() != null) {
			_hashCode += getSettlementDate().hashCode();
		}
		if (getPayProviderCode() != null) {
			_hashCode += getPayProviderCode().hashCode();
		}
		if (getSender() != null) {
			_hashCode += getSender().hashCode();
		}
		if (getAccountNumber() != null) {
			_hashCode += getAccountNumber().hashCode();
		}
		if (getCifNumber() != null) {
			_hashCode += getCifNumber().hashCode();
		}
		if (getDescription() != null) {
			_hashCode += getDescription().hashCode();
		}
		if (getProviderCode() != null) {
			_hashCode += getProviderCode().hashCode();
		}
		if (getBranch() != null) {
			_hashCode += getBranch().hashCode();
		}
		if (getFromid() != null) {
			_hashCode += getFromid().hashCode();
		}
		if (getFromName() != null) {
			_hashCode += getFromName().hashCode();
		}
		if (getBillCode() != null) {
			_hashCode += getBillCode().hashCode();
		}
		if (getCcycd() != null) {
			_hashCode += getCcycd().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(PayBillingReq.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", ">payBilling>req"));
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
		elemField.setFieldName("amount");
		elemField.setXmlName(new javax.xml.namespace.QName("", "amount"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("amountDiscounted");
		elemField.setXmlName(new javax.xml.namespace.QName("", "amountDiscounted"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("settlementDate");
		elemField.setXmlName(new javax.xml.namespace.QName("", "settlementDate"));
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
		elemField.setFieldName("accountNumber");
		elemField.setXmlName(new javax.xml.namespace.QName("", "accountNumber"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cifNumber");
		elemField.setXmlName(new javax.xml.namespace.QName("", "cifNumber"));
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
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("branch");
		elemField.setXmlName(new javax.xml.namespace.QName("", "branch"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fromid");
		elemField.setXmlName(new javax.xml.namespace.QName("", "fromid"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fromName");
		elemField.setXmlName(new javax.xml.namespace.QName("", "fromName"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("billCode");
		elemField.setXmlName(new javax.xml.namespace.QName("", "billCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("ccycd");
		elemField.setXmlName(new javax.xml.namespace.QName("", "ccycd"));
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
