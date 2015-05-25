/**
 * PickupReciveMoneyRes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.wuabmt;

public class PickupReciveMoneyRes  implements java.io.Serializable {
    private seatechit.msbgateway.proxy.wuabmt.WuBankAccount bankAcc;

    private java.lang.String channel;

    private java.lang.String comment;

    private java.lang.String description;

    private java.lang.String errCode;

    private java.lang.String mtcn;

    private seatechit.msbgateway.proxy.wuabmt.PaymentDtl paymentDtl;

    private seatechit.msbgateway.proxy.wuabmt.WuReceiver receiver;

    private java.lang.String referenceNo;

    private seatechit.msbgateway.proxy.wuabmt.WuSender sender;

    private java.lang.String serviceCode;

    private java.lang.String transactionDate;

    private java.lang.String transactionDigest;

    private java.lang.String transactionID;

    public PickupReciveMoneyRes() {
    }

    public PickupReciveMoneyRes(
           seatechit.msbgateway.proxy.wuabmt.WuBankAccount bankAcc,
           java.lang.String channel,
           java.lang.String comment,
           java.lang.String description,
           java.lang.String errCode,
           java.lang.String mtcn,
           seatechit.msbgateway.proxy.wuabmt.PaymentDtl paymentDtl,
           seatechit.msbgateway.proxy.wuabmt.WuReceiver receiver,
           java.lang.String referenceNo,
           seatechit.msbgateway.proxy.wuabmt.WuSender sender,
           java.lang.String serviceCode,
           java.lang.String transactionDate,
           java.lang.String transactionDigest,
           java.lang.String transactionID) {
           this.bankAcc = bankAcc;
           this.channel = channel;
           this.comment = comment;
           this.description = description;
           this.errCode = errCode;
           this.mtcn = mtcn;
           this.paymentDtl = paymentDtl;
           this.receiver = receiver;
           this.referenceNo = referenceNo;
           this.sender = sender;
           this.serviceCode = serviceCode;
           this.transactionDate = transactionDate;
           this.transactionDigest = transactionDigest;
           this.transactionID = transactionID;
    }


    /**
     * Gets the bankAcc value for this PickupReciveMoneyRes.
     * 
     * @return bankAcc
     */
    public seatechit.msbgateway.proxy.wuabmt.WuBankAccount getBankAcc() {
        return bankAcc;
    }


    /**
     * Sets the bankAcc value for this PickupReciveMoneyRes.
     * 
     * @param bankAcc
     */
    public void setBankAcc(seatechit.msbgateway.proxy.wuabmt.WuBankAccount bankAcc) {
        this.bankAcc = bankAcc;
    }


    /**
     * Gets the channel value for this PickupReciveMoneyRes.
     * 
     * @return channel
     */
    public java.lang.String getChannel() {
        return channel;
    }


    /**
     * Sets the channel value for this PickupReciveMoneyRes.
     * 
     * @param channel
     */
    public void setChannel(java.lang.String channel) {
        this.channel = channel;
    }


    /**
     * Gets the comment value for this PickupReciveMoneyRes.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this PickupReciveMoneyRes.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the description value for this PickupReciveMoneyRes.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PickupReciveMoneyRes.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the errCode value for this PickupReciveMoneyRes.
     * 
     * @return errCode
     */
    public java.lang.String getErrCode() {
        return errCode;
    }


    /**
     * Sets the errCode value for this PickupReciveMoneyRes.
     * 
     * @param errCode
     */
    public void setErrCode(java.lang.String errCode) {
        this.errCode = errCode;
    }


    /**
     * Gets the mtcn value for this PickupReciveMoneyRes.
     * 
     * @return mtcn
     */
    public java.lang.String getMtcn() {
        return mtcn;
    }


    /**
     * Sets the mtcn value for this PickupReciveMoneyRes.
     * 
     * @param mtcn
     */
    public void setMtcn(java.lang.String mtcn) {
        this.mtcn = mtcn;
    }


    /**
     * Gets the paymentDtl value for this PickupReciveMoneyRes.
     * 
     * @return paymentDtl
     */
    public seatechit.msbgateway.proxy.wuabmt.PaymentDtl getPaymentDtl() {
        return paymentDtl;
    }


    /**
     * Sets the paymentDtl value for this PickupReciveMoneyRes.
     * 
     * @param paymentDtl
     */
    public void setPaymentDtl(seatechit.msbgateway.proxy.wuabmt.PaymentDtl paymentDtl) {
        this.paymentDtl = paymentDtl;
    }


    /**
     * Gets the receiver value for this PickupReciveMoneyRes.
     * 
     * @return receiver
     */
    public seatechit.msbgateway.proxy.wuabmt.WuReceiver getReceiver() {
        return receiver;
    }


    /**
     * Sets the receiver value for this PickupReciveMoneyRes.
     * 
     * @param receiver
     */
    public void setReceiver(seatechit.msbgateway.proxy.wuabmt.WuReceiver receiver) {
        this.receiver = receiver;
    }


    /**
     * Gets the referenceNo value for this PickupReciveMoneyRes.
     * 
     * @return referenceNo
     */
    public java.lang.String getReferenceNo() {
        return referenceNo;
    }


    /**
     * Sets the referenceNo value for this PickupReciveMoneyRes.
     * 
     * @param referenceNo
     */
    public void setReferenceNo(java.lang.String referenceNo) {
        this.referenceNo = referenceNo;
    }


    /**
     * Gets the sender value for this PickupReciveMoneyRes.
     * 
     * @return sender
     */
    public seatechit.msbgateway.proxy.wuabmt.WuSender getSender() {
        return sender;
    }


    /**
     * Sets the sender value for this PickupReciveMoneyRes.
     * 
     * @param sender
     */
    public void setSender(seatechit.msbgateway.proxy.wuabmt.WuSender sender) {
        this.sender = sender;
    }


    /**
     * Gets the serviceCode value for this PickupReciveMoneyRes.
     * 
     * @return serviceCode
     */
    public java.lang.String getServiceCode() {
        return serviceCode;
    }


    /**
     * Sets the serviceCode value for this PickupReciveMoneyRes.
     * 
     * @param serviceCode
     */
    public void setServiceCode(java.lang.String serviceCode) {
        this.serviceCode = serviceCode;
    }


    /**
     * Gets the transactionDate value for this PickupReciveMoneyRes.
     * 
     * @return transactionDate
     */
    public java.lang.String getTransactionDate() {
        return transactionDate;
    }


    /**
     * Sets the transactionDate value for this PickupReciveMoneyRes.
     * 
     * @param transactionDate
     */
    public void setTransactionDate(java.lang.String transactionDate) {
        this.transactionDate = transactionDate;
    }


    /**
     * Gets the transactionDigest value for this PickupReciveMoneyRes.
     * 
     * @return transactionDigest
     */
    public java.lang.String getTransactionDigest() {
        return transactionDigest;
    }


    /**
     * Sets the transactionDigest value for this PickupReciveMoneyRes.
     * 
     * @param transactionDigest
     */
    public void setTransactionDigest(java.lang.String transactionDigest) {
        this.transactionDigest = transactionDigest;
    }


    /**
     * Gets the transactionID value for this PickupReciveMoneyRes.
     * 
     * @return transactionID
     */
    public java.lang.String getTransactionID() {
        return transactionID;
    }


    /**
     * Sets the transactionID value for this PickupReciveMoneyRes.
     * 
     * @param transactionID
     */
    public void setTransactionID(java.lang.String transactionID) {
        this.transactionID = transactionID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PickupReciveMoneyRes)) return false;
        PickupReciveMoneyRes other = (PickupReciveMoneyRes) obj;
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
            ((this.channel==null && other.getChannel()==null) || 
             (this.channel!=null &&
              this.channel.equals(other.getChannel()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.errCode==null && other.getErrCode()==null) || 
             (this.errCode!=null &&
              this.errCode.equals(other.getErrCode()))) &&
            ((this.mtcn==null && other.getMtcn()==null) || 
             (this.mtcn!=null &&
              this.mtcn.equals(other.getMtcn()))) &&
            ((this.paymentDtl==null && other.getPaymentDtl()==null) || 
             (this.paymentDtl!=null &&
              this.paymentDtl.equals(other.getPaymentDtl()))) &&
            ((this.receiver==null && other.getReceiver()==null) || 
             (this.receiver!=null &&
              this.receiver.equals(other.getReceiver()))) &&
            ((this.referenceNo==null && other.getReferenceNo()==null) || 
             (this.referenceNo!=null &&
              this.referenceNo.equals(other.getReferenceNo()))) &&
            ((this.sender==null && other.getSender()==null) || 
             (this.sender!=null &&
              this.sender.equals(other.getSender()))) &&
            ((this.serviceCode==null && other.getServiceCode()==null) || 
             (this.serviceCode!=null &&
              this.serviceCode.equals(other.getServiceCode()))) &&
            ((this.transactionDate==null && other.getTransactionDate()==null) || 
             (this.transactionDate!=null &&
              this.transactionDate.equals(other.getTransactionDate()))) &&
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
        if (getChannel() != null) {
            _hashCode += getChannel().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getErrCode() != null) {
            _hashCode += getErrCode().hashCode();
        }
        if (getMtcn() != null) {
            _hashCode += getMtcn().hashCode();
        }
        if (getPaymentDtl() != null) {
            _hashCode += getPaymentDtl().hashCode();
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
        if (getServiceCode() != null) {
            _hashCode += getServiceCode().hashCode();
        }
        if (getTransactionDate() != null) {
            _hashCode += getTransactionDate().hashCode();
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
        new org.apache.axis.description.TypeDesc(PickupReciveMoneyRes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "pickupReciveMoneyRes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAcc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bankAcc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "wuBankAccount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("channel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "channel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("mtcn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mtcn"));
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
        elemField.setFieldName("serviceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
