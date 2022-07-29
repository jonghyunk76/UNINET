/**
 * UserKeyWSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.vo;

public class UserKeyWSVO  implements java.io.Serializable {
    private java.lang.String comparatorType;

    private java.lang.String mailAddress;

    private java.lang.String socialID;

    private java.lang.String userID;

    private java.lang.String systemID;

    public UserKeyWSVO() {
    }

    public UserKeyWSVO(
           java.lang.String comparatorType,
           java.lang.String mailAddress,
           java.lang.String socialID,
           java.lang.String userID,
           java.lang.String systemID) {
           this.comparatorType = comparatorType;
           this.mailAddress = mailAddress;
           this.socialID = socialID;
           this.userID = userID;
           this.systemID = systemID;
    }


    /**
     * Gets the comparatorType value for this UserKeyWSVO.
     * 
     * @return comparatorType
     */
    public java.lang.String getComparatorType() {
        return comparatorType;
    }


    /**
     * Sets the comparatorType value for this UserKeyWSVO.
     * 
     * @param comparatorType
     */
    public void setComparatorType(java.lang.String comparatorType) {
        this.comparatorType = comparatorType;
    }


    /**
     * Gets the mailAddress value for this UserKeyWSVO.
     * 
     * @return mailAddress
     */
    public java.lang.String getMailAddress() {
        return mailAddress;
    }


    /**
     * Sets the mailAddress value for this UserKeyWSVO.
     * 
     * @param mailAddress
     */
    public void setMailAddress(java.lang.String mailAddress) {
        this.mailAddress = mailAddress;
    }


    /**
     * Gets the socialID value for this UserKeyWSVO.
     * 
     * @return socialID
     */
    public java.lang.String getSocialID() {
        return socialID;
    }


    /**
     * Sets the socialID value for this UserKeyWSVO.
     * 
     * @param socialID
     */
    public void setSocialID(java.lang.String socialID) {
        this.socialID = socialID;
    }


    /**
     * Gets the userID value for this UserKeyWSVO.
     * 
     * @return userID
     */
    public java.lang.String getUserID() {
        return userID;
    }


    /**
     * Sets the userID value for this UserKeyWSVO.
     * 
     * @param userID
     */
    public void setUserID(java.lang.String userID) {
        this.userID = userID;
    }


    /**
     * Gets the systemID value for this UserKeyWSVO.
     * 
     * @return systemID
     */
    public java.lang.String getSystemID() {
        return systemID;
    }


    /**
     * Sets the systemID value for this UserKeyWSVO.
     * 
     * @param systemID
     */
    public void setSystemID(java.lang.String systemID) {
        this.systemID = systemID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserKeyWSVO)) return false;
        UserKeyWSVO other = (UserKeyWSVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.comparatorType==null && other.getComparatorType()==null) || 
             (this.comparatorType!=null &&
              this.comparatorType.equals(other.getComparatorType()))) &&
            ((this.mailAddress==null && other.getMailAddress()==null) || 
             (this.mailAddress!=null &&
              this.mailAddress.equals(other.getMailAddress()))) &&
            ((this.socialID==null && other.getSocialID()==null) || 
             (this.socialID!=null &&
              this.socialID.equals(other.getSocialID()))) &&
            ((this.userID==null && other.getUserID()==null) || 
             (this.userID!=null &&
              this.userID.equals(other.getUserID()))) &&
            ((this.systemID==null && other.getSystemID()==null) || 
             (this.systemID!=null &&
              this.systemID.equals(other.getSystemID())));
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
        if (getComparatorType() != null) {
            _hashCode += getComparatorType().hashCode();
        }
        if (getMailAddress() != null) {
            _hashCode += getMailAddress().hashCode();
        }
        if (getSocialID() != null) {
            _hashCode += getSocialID().hashCode();
        }
        if (getUserID() != null) {
            _hashCode += getUserID().hashCode();
        }
        if (getSystemID() != null) {
            _hashCode += getSystemID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserKeyWSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "UserKeyWSVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comparatorType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ComparatorType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("socialID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SocialID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UserID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SystemID"));
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
