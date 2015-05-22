/**
 * WuSender.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package seatechit.msbgateway.proxy.wuabmt;

public class WuSender  implements java.io.Serializable {
    private java.lang.String contactPhone;

    private java.lang.String sendAddress;

    private java.lang.String sendAddress2;

    private java.lang.String sendCity;

    private java.lang.String sendCountryIsoCode;

    private java.lang.String sendFirstName;

    private java.lang.String sendLastName;

    private java.lang.String sendPostalCose;

    private java.lang.String sendState;

    public WuSender() {
    }

    public WuSender(
           java.lang.String contactPhone,
           java.lang.String sendAddress,
           java.lang.String sendAddress2,
           java.lang.String sendCity,
           java.lang.String sendCountryIsoCode,
           java.lang.String sendFirstName,
           java.lang.String sendLastName,
           java.lang.String sendPostalCose,
           java.lang.String sendState) {
           this.contactPhone = contactPhone;
           this.sendAddress = sendAddress;
           this.sendAddress2 = sendAddress2;
           this.sendCity = sendCity;
           this.sendCountryIsoCode = sendCountryIsoCode;
           this.sendFirstName = sendFirstName;
           this.sendLastName = sendLastName;
           this.sendPostalCose = sendPostalCose;
           this.sendState = sendState;
    }


    /**
     * Gets the contactPhone value for this WuSender.
     * 
     * @return contactPhone
     */
    public java.lang.String getContactPhone() {
        return contactPhone;
    }


    /**
     * Sets the contactPhone value for this WuSender.
     * 
     * @param contactPhone
     */
    public void setContactPhone(java.lang.String contactPhone) {
        this.contactPhone = contactPhone;
    }


    /**
     * Gets the sendAddress value for this WuSender.
     * 
     * @return sendAddress
     */
    public java.lang.String getSendAddress() {
        return sendAddress;
    }


    /**
     * Sets the sendAddress value for this WuSender.
     * 
     * @param sendAddress
     */
    public void setSendAddress(java.lang.String sendAddress) {
        this.sendAddress = sendAddress;
    }


    /**
     * Gets the sendAddress2 value for this WuSender.
     * 
     * @return sendAddress2
     */
    public java.lang.String getSendAddress2() {
        return sendAddress2;
    }


    /**
     * Sets the sendAddress2 value for this WuSender.
     * 
     * @param sendAddress2
     */
    public void setSendAddress2(java.lang.String sendAddress2) {
        this.sendAddress2 = sendAddress2;
    }


    /**
     * Gets the sendCity value for this WuSender.
     * 
     * @return sendCity
     */
    public java.lang.String getSendCity() {
        return sendCity;
    }


    /**
     * Sets the sendCity value for this WuSender.
     * 
     * @param sendCity
     */
    public void setSendCity(java.lang.String sendCity) {
        this.sendCity = sendCity;
    }


    /**
     * Gets the sendCountryIsoCode value for this WuSender.
     * 
     * @return sendCountryIsoCode
     */
    public java.lang.String getSendCountryIsoCode() {
        return sendCountryIsoCode;
    }


    /**
     * Sets the sendCountryIsoCode value for this WuSender.
     * 
     * @param sendCountryIsoCode
     */
    public void setSendCountryIsoCode(java.lang.String sendCountryIsoCode) {
        this.sendCountryIsoCode = sendCountryIsoCode;
    }


    /**
     * Gets the sendFirstName value for this WuSender.
     * 
     * @return sendFirstName
     */
    public java.lang.String getSendFirstName() {
        return sendFirstName;
    }


    /**
     * Sets the sendFirstName value for this WuSender.
     * 
     * @param sendFirstName
     */
    public void setSendFirstName(java.lang.String sendFirstName) {
        this.sendFirstName = sendFirstName;
    }


    /**
     * Gets the sendLastName value for this WuSender.
     * 
     * @return sendLastName
     */
    public java.lang.String getSendLastName() {
        return sendLastName;
    }


    /**
     * Sets the sendLastName value for this WuSender.
     * 
     * @param sendLastName
     */
    public void setSendLastName(java.lang.String sendLastName) {
        this.sendLastName = sendLastName;
    }


    /**
     * Gets the sendPostalCose value for this WuSender.
     * 
     * @return sendPostalCose
     */
    public java.lang.String getSendPostalCose() {
        return sendPostalCose;
    }


    /**
     * Sets the sendPostalCose value for this WuSender.
     * 
     * @param sendPostalCose
     */
    public void setSendPostalCose(java.lang.String sendPostalCose) {
        this.sendPostalCose = sendPostalCose;
    }


    /**
     * Gets the sendState value for this WuSender.
     * 
     * @return sendState
     */
    public java.lang.String getSendState() {
        return sendState;
    }


    /**
     * Sets the sendState value for this WuSender.
     * 
     * @param sendState
     */
    public void setSendState(java.lang.String sendState) {
        this.sendState = sendState;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WuSender)) return false;
        WuSender other = (WuSender) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contactPhone==null && other.getContactPhone()==null) || 
             (this.contactPhone!=null &&
              this.contactPhone.equals(other.getContactPhone()))) &&
            ((this.sendAddress==null && other.getSendAddress()==null) || 
             (this.sendAddress!=null &&
              this.sendAddress.equals(other.getSendAddress()))) &&
            ((this.sendAddress2==null && other.getSendAddress2()==null) || 
             (this.sendAddress2!=null &&
              this.sendAddress2.equals(other.getSendAddress2()))) &&
            ((this.sendCity==null && other.getSendCity()==null) || 
             (this.sendCity!=null &&
              this.sendCity.equals(other.getSendCity()))) &&
            ((this.sendCountryIsoCode==null && other.getSendCountryIsoCode()==null) || 
             (this.sendCountryIsoCode!=null &&
              this.sendCountryIsoCode.equals(other.getSendCountryIsoCode()))) &&
            ((this.sendFirstName==null && other.getSendFirstName()==null) || 
             (this.sendFirstName!=null &&
              this.sendFirstName.equals(other.getSendFirstName()))) &&
            ((this.sendLastName==null && other.getSendLastName()==null) || 
             (this.sendLastName!=null &&
              this.sendLastName.equals(other.getSendLastName()))) &&
            ((this.sendPostalCose==null && other.getSendPostalCose()==null) || 
             (this.sendPostalCose!=null &&
              this.sendPostalCose.equals(other.getSendPostalCose()))) &&
            ((this.sendState==null && other.getSendState()==null) || 
             (this.sendState!=null &&
              this.sendState.equals(other.getSendState())));
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
        if (getContactPhone() != null) {
            _hashCode += getContactPhone().hashCode();
        }
        if (getSendAddress() != null) {
            _hashCode += getSendAddress().hashCode();
        }
        if (getSendAddress2() != null) {
            _hashCode += getSendAddress2().hashCode();
        }
        if (getSendCity() != null) {
            _hashCode += getSendCity().hashCode();
        }
        if (getSendCountryIsoCode() != null) {
            _hashCode += getSendCountryIsoCode().hashCode();
        }
        if (getSendFirstName() != null) {
            _hashCode += getSendFirstName().hashCode();
        }
        if (getSendLastName() != null) {
            _hashCode += getSendLastName().hashCode();
        }
        if (getSendPostalCose() != null) {
            _hashCode += getSendPostalCose().hashCode();
        }
        if (getSendState() != null) {
            _hashCode += getSendState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WuSender.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://epaymentgw.msb.com.vn/", "wuSender"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactPhone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contactPhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendAddress2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendAddress2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendCity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendCountryIsoCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendCountryIsoCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendFirstName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendFirstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendLastName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendLastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendPostalCose");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendPostalCose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendState"));
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
