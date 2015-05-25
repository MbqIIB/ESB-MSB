/**
 * PaymentDtl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.wuabmt;

public class PaymentDtl  implements java.io.Serializable {
    private long county_tax;

    private java.lang.String desCountryCode;

    private java.lang.String desCurrencyCode;

    private long desPayoutActualAmount;

    private long desPayoutAmount;

    private double exchange_rate;

    private long fee_charges;

    private long municipal_tax;

    private java.lang.String orgCountryCode;

    private java.lang.String orgCurrencyCode;

    private long origineAmount;

    private long origineGrossAmount;

    private long promotion;

    private java.lang.String promotionCode;

    private java.lang.String promotionDescription;

    private java.lang.String promotionMesssage;

    private java.lang.String promotionName;

    private long state_tax;

    public PaymentDtl() {
    }

    public PaymentDtl(
           long county_tax,
           java.lang.String desCountryCode,
           java.lang.String desCurrencyCode,
           long desPayoutActualAmount,
           long desPayoutAmount,
           double exchange_rate,
           long fee_charges,
           long municipal_tax,
           java.lang.String orgCountryCode,
           java.lang.String orgCurrencyCode,
           long origineAmount,
           long origineGrossAmount,
           long promotion,
           java.lang.String promotionCode,
           java.lang.String promotionDescription,
           java.lang.String promotionMesssage,
           java.lang.String promotionName,
           long state_tax) {
           this.county_tax = county_tax;
           this.desCountryCode = desCountryCode;
           this.desCurrencyCode = desCurrencyCode;
           this.desPayoutActualAmount = desPayoutActualAmount;
           this.desPayoutAmount = desPayoutAmount;
           this.exchange_rate = exchange_rate;
           this.fee_charges = fee_charges;
           this.municipal_tax = municipal_tax;
           this.orgCountryCode = orgCountryCode;
           this.orgCurrencyCode = orgCurrencyCode;
           this.origineAmount = origineAmount;
           this.origineGrossAmount = origineGrossAmount;
           this.promotion = promotion;
           this.promotionCode = promotionCode;
           this.promotionDescription = promotionDescription;
           this.promotionMesssage = promotionMesssage;
           this.promotionName = promotionName;
           this.state_tax = state_tax;
    }


    /**
     * Gets the county_tax value for this PaymentDtl.
     * 
     * @return county_tax
     */
    public long getCounty_tax() {
        return county_tax;
    }


    /**
     * Sets the county_tax value for this PaymentDtl.
     * 
     * @param county_tax
     */
    public void setCounty_tax(long county_tax) {
        this.county_tax = county_tax;
    }


    /**
     * Gets the desCountryCode value for this PaymentDtl.
     * 
     * @return desCountryCode
     */
    public java.lang.String getDesCountryCode() {
        return desCountryCode;
    }


    /**
     * Sets the desCountryCode value for this PaymentDtl.
     * 
     * @param desCountryCode
     */
    public void setDesCountryCode(java.lang.String desCountryCode) {
        this.desCountryCode = desCountryCode;
    }


    /**
     * Gets the desCurrencyCode value for this PaymentDtl.
     * 
     * @return desCurrencyCode
     */
    public java.lang.String getDesCurrencyCode() {
        return desCurrencyCode;
    }


    /**
     * Sets the desCurrencyCode value for this PaymentDtl.
     * 
     * @param desCurrencyCode
     */
    public void setDesCurrencyCode(java.lang.String desCurrencyCode) {
        this.desCurrencyCode = desCurrencyCode;
    }


    /**
     * Gets the desPayoutActualAmount value for this PaymentDtl.
     * 
     * @return desPayoutActualAmount
     */
    public long getDesPayoutActualAmount() {
        return desPayoutActualAmount;
    }


    /**
     * Sets the desPayoutActualAmount value for this PaymentDtl.
     * 
     * @param desPayoutActualAmount
     */
    public void setDesPayoutActualAmount(long desPayoutActualAmount) {
        this.desPayoutActualAmount = desPayoutActualAmount;
    }


    /**
     * Gets the desPayoutAmount value for this PaymentDtl.
     * 
     * @return desPayoutAmount
     */
    public long getDesPayoutAmount() {
        return desPayoutAmount;
    }


    /**
     * Sets the desPayoutAmount value for this PaymentDtl.
     * 
     * @param desPayoutAmount
     */
    public void setDesPayoutAmount(long desPayoutAmount) {
        this.desPayoutAmount = desPayoutAmount;
    }


    /**
     * Gets the exchange_rate value for this PaymentDtl.
     * 
     * @return exchange_rate
     */
    public double getExchange_rate() {
        return exchange_rate;
    }


    /**
     * Sets the exchange_rate value for this PaymentDtl.
     * 
     * @param exchange_rate
     */
    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }


    /**
     * Gets the fee_charges value for this PaymentDtl.
     * 
     * @return fee_charges
     */
    public long getFee_charges() {
        return fee_charges;
    }


    /**
     * Sets the fee_charges value for this PaymentDtl.
     * 
     * @param fee_charges
     */
    public void setFee_charges(long fee_charges) {
        this.fee_charges = fee_charges;
    }


    /**
     * Gets the municipal_tax value for this PaymentDtl.
     * 
     * @return municipal_tax
     */
    public long getMunicipal_tax() {
        return municipal_tax;
    }


    /**
     * Sets the municipal_tax value for this PaymentDtl.
     * 
     * @param municipal_tax
     */
    public void setMunicipal_tax(long municipal_tax) {
        this.municipal_tax = municipal_tax;
    }


    /**
     * Gets the orgCountryCode value for this PaymentDtl.
     * 
     * @return orgCountryCode
     */
    public java.lang.String getOrgCountryCode() {
        return orgCountryCode;
    }


    /**
     * Sets the orgCountryCode value for this PaymentDtl.
     * 
     * @param orgCountryCode
     */
    public void setOrgCountryCode(java.lang.String orgCountryCode) {
        this.orgCountryCode = orgCountryCode;
    }


    /**
     * Gets the orgCurrencyCode value for this PaymentDtl.
     * 
     * @return orgCurrencyCode
     */
    public java.lang.String getOrgCurrencyCode() {
        return orgCurrencyCode;
    }


    /**
     * Sets the orgCurrencyCode value for this PaymentDtl.
     * 
     * @param orgCurrencyCode
     */
    public void setOrgCurrencyCode(java.lang.String orgCurrencyCode) {
        this.orgCurrencyCode = orgCurrencyCode;
    }


    /**
     * Gets the origineAmount value for this PaymentDtl.
     * 
     * @return origineAmount
     */
    public long getOrigineAmount() {
        return origineAmount;
    }


    /**
     * Sets the origineAmount value for this PaymentDtl.
     * 
     * @param origineAmount
     */
    public void setOrigineAmount(long origineAmount) {
        this.origineAmount = origineAmount;
    }


    /**
     * Gets the origineGrossAmount value for this PaymentDtl.
     * 
     * @return origineGrossAmount
     */
    public long getOrigineGrossAmount() {
        return origineGrossAmount;
    }


    /**
     * Sets the origineGrossAmount value for this PaymentDtl.
     * 
     * @param origineGrossAmount
     */
    public void setOrigineGrossAmount(long origineGrossAmount) {
        this.origineGrossAmount = origineGrossAmount;
    }


    /**
     * Gets the promotion value for this PaymentDtl.
     * 
     * @return promotion
     */
    public long getPromotion() {
        return promotion;
    }


    /**
     * Sets the promotion value for this PaymentDtl.
     * 
     * @param promotion
     */
    public void setPromotion(long promotion) {
        this.promotion = promotion;
    }


    /**
     * Gets the promotionCode value for this PaymentDtl.
     * 
     * @return promotionCode
     */
    public java.lang.String getPromotionCode() {
        return promotionCode;
    }


    /**
     * Sets the promotionCode value for this PaymentDtl.
     * 
     * @param promotionCode
     */
    public void setPromotionCode(java.lang.String promotionCode) {
        this.promotionCode = promotionCode;
    }


    /**
     * Gets the promotionDescription value for this PaymentDtl.
     * 
     * @return promotionDescription
     */
    public java.lang.String getPromotionDescription() {
        return promotionDescription;
    }


    /**
     * Sets the promotionDescription value for this PaymentDtl.
     * 
     * @param promotionDescription
     */
    public void setPromotionDescription(java.lang.String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }


    /**
     * Gets the promotionMesssage value for this PaymentDtl.
     * 
     * @return promotionMesssage
     */
    public java.lang.String getPromotionMesssage() {
        return promotionMesssage;
    }


    /**
     * Sets the promotionMesssage value for this PaymentDtl.
     * 
     * @param promotionMesssage
     */
    public void setPromotionMesssage(java.lang.String promotionMesssage) {
        this.promotionMesssage = promotionMesssage;
    }


    /**
     * Gets the promotionName value for this PaymentDtl.
     * 
     * @return promotionName
     */
    public java.lang.String getPromotionName() {
        return promotionName;
    }


    /**
     * Sets the promotionName value for this PaymentDtl.
     * 
     * @param promotionName
     */
    public void setPromotionName(java.lang.String promotionName) {
        this.promotionName = promotionName;
    }


    /**
     * Gets the state_tax value for this PaymentDtl.
     * 
     * @return state_tax
     */
    public long getState_tax() {
        return state_tax;
    }


    /**
     * Sets the state_tax value for this PaymentDtl.
     * 
     * @param state_tax
     */
    public void setState_tax(long state_tax) {
        this.state_tax = state_tax;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaymentDtl)) return false;
        PaymentDtl other = (PaymentDtl) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.county_tax == other.getCounty_tax() &&
            ((this.desCountryCode==null && other.getDesCountryCode()==null) || 
             (this.desCountryCode!=null &&
              this.desCountryCode.equals(other.getDesCountryCode()))) &&
            ((this.desCurrencyCode==null && other.getDesCurrencyCode()==null) || 
             (this.desCurrencyCode!=null &&
              this.desCurrencyCode.equals(other.getDesCurrencyCode()))) &&
            this.desPayoutActualAmount == other.getDesPayoutActualAmount() &&
            this.desPayoutAmount == other.getDesPayoutAmount() &&
            this.exchange_rate == other.getExchange_rate() &&
            this.fee_charges == other.getFee_charges() &&
            this.municipal_tax == other.getMunicipal_tax() &&
            ((this.orgCountryCode==null && other.getOrgCountryCode()==null) || 
             (this.orgCountryCode!=null &&
              this.orgCountryCode.equals(other.getOrgCountryCode()))) &&
            ((this.orgCurrencyCode==null && other.getOrgCurrencyCode()==null) || 
             (this.orgCurrencyCode!=null &&
              this.orgCurrencyCode.equals(other.getOrgCurrencyCode()))) &&
            this.origineAmount == other.getOrigineAmount() &&
            this.origineGrossAmount == other.getOrigineGrossAmount() &&
            this.promotion == other.getPromotion() &&
            ((this.promotionCode==null && other.getPromotionCode()==null) || 
             (this.promotionCode!=null &&
              this.promotionCode.equals(other.getPromotionCode()))) &&
            ((this.promotionDescription==null && other.getPromotionDescription()==null) || 
             (this.promotionDescription!=null &&
              this.promotionDescription.equals(other.getPromotionDescription()))) &&
            ((this.promotionMesssage==null && other.getPromotionMesssage()==null) || 
             (this.promotionMesssage!=null &&
              this.promotionMesssage.equals(other.getPromotionMesssage()))) &&
            ((this.promotionName==null && other.getPromotionName()==null) || 
             (this.promotionName!=null &&
              this.promotionName.equals(other.getPromotionName()))) &&
            this.state_tax == other.getState_tax();
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
        _hashCode += new Long(getCounty_tax()).hashCode();
        if (getDesCountryCode() != null) {
            _hashCode += getDesCountryCode().hashCode();
        }
        if (getDesCurrencyCode() != null) {
            _hashCode += getDesCurrencyCode().hashCode();
        }
        _hashCode += new Long(getDesPayoutActualAmount()).hashCode();
        _hashCode += new Long(getDesPayoutAmount()).hashCode();
        _hashCode += new Double(getExchange_rate()).hashCode();
        _hashCode += new Long(getFee_charges()).hashCode();
        _hashCode += new Long(getMunicipal_tax()).hashCode();
        if (getOrgCountryCode() != null) {
            _hashCode += getOrgCountryCode().hashCode();
        }
        if (getOrgCurrencyCode() != null) {
            _hashCode += getOrgCurrencyCode().hashCode();
        }
        _hashCode += new Long(getOrigineAmount()).hashCode();
        _hashCode += new Long(getOrigineGrossAmount()).hashCode();
        _hashCode += new Long(getPromotion()).hashCode();
        if (getPromotionCode() != null) {
            _hashCode += getPromotionCode().hashCode();
        }
        if (getPromotionDescription() != null) {
            _hashCode += getPromotionDescription().hashCode();
        }
        if (getPromotionMesssage() != null) {
            _hashCode += getPromotionMesssage().hashCode();
        }
        if (getPromotionName() != null) {
            _hashCode += getPromotionName().hashCode();
        }
        _hashCode += new Long(getState_tax()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaymentDtl.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "paymentDtl"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("county_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "county_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desCountryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "desCountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desCurrencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "desCurrencyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desPayoutActualAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "desPayoutActualAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desPayoutAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "desPayoutAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exchange_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exchange_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fee_charges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fee_charges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("municipal_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "municipal_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgCountryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orgCountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgCurrencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orgCurrencyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origineAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "origineAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origineGrossAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "origineGrossAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("promotion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "promotion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("promotionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "promotionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("promotionDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "promotionDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("promotionMesssage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "promotionMesssage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("promotionName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "promotionName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
