/**
 * PickupValidateRes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.wuabmt;

public class PickupValidateRes  implements java.io.Serializable {
	private seatechit.msbgateway.proxy.wuabmt.WuBankAccount bankAcc;

    private java.lang.String description;

    private java.lang.String errCode;

    private java.lang.String message_context;

    private java.lang.String message_text;

    private java.lang.String moneyTransactionID;

    private java.lang.String moneyTransferDate;

    private java.lang.String mtcn;

    private java.lang.String others;

    private seatechit.msbgateway.proxy.wuabmt.PaymentDtl paymentDtl;

    private java.lang.String pickupReferenceNumber;

    private seatechit.msbgateway.proxy.wuabmt.WuReceiver receiver;

    private java.lang.String referenceNo;

    private seatechit.msbgateway.proxy.wuabmt.WuSender sender;

    private java.lang.String transactionDigest;

    private java.lang.String transactionID;

    public PickupValidateRes() {
    }

    public PickupValidateRes(
           seatechit.msbgateway.proxy.wuabmt.WuBankAccount bankAcc,
           java.lang.String description,
           java.lang.String errCode,
           java.lang.String message_context,
           java.lang.String message_text,
           java.lang.String moneyTransactionID,
           java.lang.String moneyTransferDate,
           java.lang.String mtcn,
           java.lang.String others,
           seatechit.msbgateway.proxy.wuabmt.PaymentDtl paymentDtl,
           java.lang.String pickupReferenceNumber,
           seatechit.msbgateway.proxy.wuabmt.WuReceiver receiver,
           java.lang.String referenceNo,
           seatechit.msbgateway.proxy.wuabmt.WuSender sender,
           java.lang.String transactionDigest,
           java.lang.String transactionID) {
           this.bankAcc = bankAcc;
           this.description = description;
           this.errCode = errCode;
           this.message_context = message_context;
           this.message_text = message_text;
           this.moneyTransactionID = moneyTransactionID;
           this.moneyTransferDate = moneyTransferDate;
           this.mtcn = mtcn;
           this.others = others;
           this.paymentDtl = paymentDtl;
           this.pickupReferenceNumber = pickupReferenceNumber;
           this.receiver = receiver;
           this.referenceNo = referenceNo;
           this.sender = sender;
           this.transactionDigest = transactionDigest;
           this.transactionID = transactionID;
    }


    /**
     * Gets the bankAcc value for this PickupValidateRes.
     * 
     * @return bankAcc
     */
    public seatechit.msbgateway.proxy.wuabmt.WuBankAccount getBankAcc() {
        return bankAcc;
    }


    /**
     * Sets the bankAcc value for this PickupValidateRes.
     * 
     * @param bankAcc
     */
    public void setBankAcc(seatechit.msbgateway.proxy.wuabmt.WuBankAccount bankAcc) {
        this.bankAcc = bankAcc;
    }


    /**
     * Gets the description value for this PickupValidateRes.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PickupValidateRes.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the errCode value for this PickupValidateRes.
     * 
     * @return errCode
     */
    public java.lang.String getErrCode() {
        return errCode;
    }


    /**
     * Sets the errCode value for this PickupValidateRes.
     * 
     * @param errCode
     */
    public void setErrCode(java.lang.String errCode) {
        this.errCode = errCode;
    }


    /**
     * Gets the message_context value for this PickupValidateRes.
     * 
     * @return message_context
     */
    public java.lang.String getMessage_context() {
        return message_context;
    }


    /**
     * Sets the message_context value for this PickupValidateRes.
     * 
     * @param message_context
     */
    public void setMessage_context(java.lang.String message_context) {
        this.message_context = message_context;
    }


    /**
     * Gets the message_text value for this PickupValidateRes.
     * 
     * @return message_text
     */
    public java.lang.String getMessage_text() {
        return message_text;
    }


    /**
     * Sets the message_text value for this PickupValidateRes.
     * 
     * @param message_text
     */
    public void setMessage_text(java.lang.String message_text) {
        this.message_text = message_text;
    }


    /**
     * Gets the moneyTransactionID value for this PickupValidateRes.
     * 
     * @return moneyTransactionID
     */
    public java.lang.String getMoneyTransactionID() {
        return moneyTransactionID;
    }


    /**
     * Sets the moneyTransactionID value for this PickupValidateRes.
     * 
     * @param moneyTransactionID
     */
    public void setMoneyTransactionID(java.lang.String moneyTransactionID) {
        this.moneyTransactionID = moneyTransactionID;
    }


    /**
     * Gets the moneyTransferDate value for this PickupValidateRes.
     * 
     * @return moneyTransferDate
     */
    public java.lang.String getMoneyTransferDate() {
        return moneyTransferDate;
    }


    /**
     * Sets the moneyTransferDate value for this PickupValidateRes.
     * 
     * @param moneyTransferDate
     */
    public void setMoneyTransferDate(java.lang.String moneyTransferDate) {
        this.moneyTransferDate = moneyTransferDate;
    }


    /**
     * Gets the mtcn value for this PickupValidateRes.
     * 
     * @return mtcn
     */
    public java.lang.String getMtcn() {
        return mtcn;
    }


    /**
     * Sets the mtcn value for this PickupValidateRes.
     * 
     * @param mtcn
     */
    public void setMtcn(java.lang.String mtcn) {
        this.mtcn = mtcn;
    }


    /**
     * Gets the others value for this PickupValidateRes.
     * 
     * @return others
     */
    public java.lang.String getOthers() {
        return others;
    }


    /**
     * Sets the others value for this PickupValidateRes.
     * 
     * @param others
     */
    public void setOthers(java.lang.String others) {
        this.others = others;
    }


    /**
     * Gets the paymentDtl value for this PickupValidateRes.
     * 
     * @return paymentDtl
     */
    public seatechit.msbgateway.proxy.wuabmt.PaymentDtl getPaymentDtl() {
        return paymentDtl;
    }


    /**
     * Sets the paymentDtl value for this PickupValidateRes.
     * 
     * @param paymentDtl
     */
    public void setPaymentDtl(seatechit.msbgateway.proxy.wuabmt.PaymentDtl paymentDtl) {
        this.paymentDtl = paymentDtl;
    }


    /**
     * Gets the pickupReferenceNumber value for this PickupValidateRes.
     * 
     * @return pickupReferenceNumber
     */
    public java.lang.String getPickupReferenceNumber() {
        return pickupReferenceNumber;
    }


    /**
     * Sets the pickupReferenceNumber value for this PickupValidateRes.
     * 
     * @param pickupReferenceNumber
     */
    public void setPickupReferenceNumber(java.lang.String pickupReferenceNumber) {
        this.pickupReferenceNumber = pickupReferenceNumber;
    }


    /**
     * Gets the receiver value for this PickupValidateRes.
     * 
     * @return receiver
     */
    public seatechit.msbgateway.proxy.wuabmt.WuReceiver getReceiver() {
        return receiver;
    }


    /**
     * Sets the receiver value for this PickupValidateRes.
     * 
     * @param receiver
     */
    public void setReceiver(seatechit.msbgateway.proxy.wuabmt.WuReceiver receiver) {
        this.receiver = receiver;
    }


    /**
     * Gets the referenceNo value for this PickupValidateRes.
     * 
     * @return referenceNo
     */
    public java.lang.String getReferenceNo() {
        return referenceNo;
    }


    /**
     * Sets the referenceNo value for this PickupValidateRes.
     * 
     * @param referenceNo
     */
    public void setReferenceNo(java.lang.String referenceNo) {
        this.referenceNo = referenceNo;
    }


    /**
     * Gets the sender value for this PickupValidateRes.
     * 
     * @return sender
     */
    public seatechit.msbgateway.proxy.wuabmt.WuSender getSender() {
        return sender;
    }


    /**
     * Sets the sender value for this PickupValidateRes.
     * 
     * @param sender
     */
    public void setSender(seatechit.msbgateway.proxy.wuabmt.WuSender sender) {
        this.sender = sender;
    }


    /**
     * Gets the transactionDigest value for this PickupValidateRes.
     * 
     * @return transactionDigest
     */
    public java.lang.String getTransactionDigest() {
        return transactionDigest;
    }


    /**
     * Sets the transactionDigest value for this PickupValidateRes.
     * 
     * @param transactionDigest
     */
    public void setTransactionDigest(java.lang.String transactionDigest) {
        this.transactionDigest = transactionDigest;
    }


    /**
     * Gets the transactionID value for this PickupValidateRes.
     * 
     * @return transactionID
     */
    public java.lang.String getTransactionID() {
        return transactionID;
    }


    /**
     * Sets the transactionID value for this PickupValidateRes.
     * 
     * @param transactionID
     */
    public void setTransactionID(java.lang.String transactionID) {
        this.transactionID = transactionID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PickupValidateRes)) return false;
        PickupValidateRes other = (PickupValidateRes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bankAcc==null && other.getBankAcc()==null) || 
             (this.bankAcc!=null &&
              this.bankAcc.equals(other.getBankAcc()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.errCode==null && other.getErrCode()==null) || 
             (this.errCode!=null &&
              this.errCode.equals(other.getErrCode()))) &&
            ((this.message_context==null && other.getMessage_context()==null) || 
             (this.message_context!=null &&
              this.message_context.equals(other.getMessage_context()))) &&
            ((this.message_text==null && other.getMessage_text()==null) || 
             (this.message_text!=null &&
              this.message_text.equals(other.getMessage_text()))) &&
            ((this.moneyTransactionID==null && other.getMoneyTransactionID()==null) || 
             (this.moneyTransactionID!=null &&
              this.moneyTransactionID.equals(other.getMoneyTransactionID()))) &&
            ((this.moneyTransferDate==null && other.getMoneyTransferDate()==null) || 
             (this.moneyTransferDate!=null &&
              this.moneyTransferDate.equals(other.getMoneyTransferDate()))) &&
            ((this.mtcn==null && other.getMtcn()==null) || 
             (this.mtcn!=null &&
              this.mtcn.equals(other.getMtcn()))) &&
            ((this.others==null && other.getOthers()==null) || 
             (this.others!=null &&
              this.others.equals(other.getOthers()))) &&
            ((this.paymentDtl==null && other.getPaymentDtl()==null) || 
             (this.paymentDtl!=null &&
              this.paymentDtl.equals(other.getPaymentDtl()))) &&
            ((this.pickupReferenceNumber==null && other.getPickupReferenceNumber()==null) || 
             (this.pickupReferenceNumber!=null &&
              this.pickupReferenceNumber.equals(other.getPickupReferenceNumber()))) &&
            ((this.receiver==null && other.getReceiver()==null) || 
             (this.receiver!=null &&
              this.receiver.equals(other.getReceiver()))) &&
            ((this.referenceNo==null && other.getReferenceNo()==null) || 
             (this.referenceNo!=null &&
              this.referenceNo.equals(other.getReferenceNo()))) &&
            ((this.sender==null && other.getSender()==null) || 
             (this.sender!=null &&
              this.sender.equals(other.getSender()))) &&
            ((this.transactionDigest==null && other.getTransactionDigest()==null) || 
             (this.transactionDigest!=null &&
              this.transactionDigest.equals(other.getTransactionDigest()))) &&
            ((this.transactionID==null && other.getTransactionID()==null) || 
             (this.transactionID!=null &&
              this.transactionID.equals(other.getTransactionID())));
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
        if (getBankAcc() != null) {
            _hashCode += getBankAcc().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getErrCode() != null) {
            _hashCode += getErrCode().hashCode();
        }
        if (getMessage_context() != null) {
            _hashCode += getMessage_context().hashCode();
        }
        if (getMessage_text() != null) {
            _hashCode += getMessage_text().hashCode();
        }
        if (getMoneyTransactionID() != null) {
            _hashCode += getMoneyTransactionID().hashCode();
        }
        if (getMoneyTransferDate() != null) {
            _hashCode += getMoneyTransferDate().hashCode();
        }
        if (getMtcn() != null) {
            _hashCode += getMtcn().hashCode();
        }
        if (getOthers() != null) {
            _hashCode += getOthers().hashCode();
        }
        if (getPaymentDtl() != null) {
            _hashCode += getPaymentDtl().hashCode();
        }
        if (getPickupReferenceNumber() != null) {
            _hashCode += getPickupReferenceNumber().hashCode();
        }
        if (getReceiver() != null) {
            _hashCode += getReceiver().hashCode();
        }
        if (getReferenceNo() != null) {
            _hashCode += getReferenceNo().hashCode();
        }
        if (getSender() != null) {
            _hashCode += getSender().hashCode();
        }
        if (getTransactionDigest() != null) {
            _hashCode += getTransactionDigest().hashCode();
        }
        if (getTransactionID() != null) {
            _hashCode += getTransactionID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PickupValidateRes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "pickupValidateRes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAcc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bankAcc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "wuBankAccount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message_context");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message_context"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message_text");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message_text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moneyTransactionID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "moneyTransactionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moneyTransferDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "moneyTransferDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtcn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mtcn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("others");
        elemField.setXmlName(new javax.xml.namespace.QName("", "others"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentDtl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentDtl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "paymentDtl"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pickupReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pickupReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "wuReceiver"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "referenceNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "wuSender"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionDigest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionDigest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionID"));
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
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
