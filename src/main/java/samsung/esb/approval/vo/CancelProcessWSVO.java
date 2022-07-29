/**
 * CancelProcessWSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.vo;

public class CancelProcessWSVO  implements java.io.Serializable {
    private java.lang.String misID;

    private java.lang.String systemID;

    private java.lang.String opinion;

    private java.lang.String cancelDate;

    private java.lang.String timeZone;

    public CancelProcessWSVO() {
    }

    public CancelProcessWSVO(
           java.lang.String misID,
           java.lang.String systemID,
           java.lang.String opinion,
           java.lang.String cancelDate,
           java.lang.String timeZone) {
           this.misID = misID;
           this.systemID = systemID;
           this.opinion = opinion;
           this.cancelDate = cancelDate;
           this.timeZone = timeZone;
    }


    /**
     * Gets the misID value for this CancelProcessWSVO.
     * 
     * @return misID
     */
    public java.lang.String getMisID() {
        return misID;
    }


    /**
     * Sets the misID value for this CancelProcessWSVO.
     * 
     * @param misID
     */
    public void setMisID(java.lang.String misID) {
        this.misID = misID;
    }


    /**
     * Gets the systemID value for this CancelProcessWSVO.
     * 
     * @return systemID
     */
    public java.lang.String getSystemID() {
        return systemID;
    }


    /**
     * Sets the systemID value for this CancelProcessWSVO.
     * 
     * @param systemID
     */
    public void setSystemID(java.lang.String systemID) {
        this.systemID = systemID;
    }


    /**
     * Gets the opinion value for this CancelProcessWSVO.
     * 
     * @return opinion
     */
    public java.lang.String getOpinion() {
        return opinion;
    }


    /**
     * Sets the opinion value for this CancelProcessWSVO.
     * 
     * @param opinion
     */
    public void setOpinion(java.lang.String opinion) {
        this.opinion = opinion;
    }


    /**
     * Gets the cancelDate value for this CancelProcessWSVO.
     * 
     * @return cancelDate
     */
    public java.lang.String getCancelDate() {
        return cancelDate;
    }


    /**
     * Sets the cancelDate value for this CancelProcessWSVO.
     * 
     * @param cancelDate
     */
    public void setCancelDate(java.lang.String cancelDate) {
        this.cancelDate = cancelDate;
    }


    /**
     * Gets the timeZone value for this CancelProcessWSVO.
     * 
     * @return timeZone
     */
    public java.lang.String getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this CancelProcessWSVO.
     * 
     * @param timeZone
     */
    public void setTimeZone(java.lang.String timeZone) {
        this.timeZone = timeZone;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelProcessWSVO)) return false;
        CancelProcessWSVO other = (CancelProcessWSVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.misID==null && other.getMisID()==null) || 
             (this.misID!=null &&
              this.misID.equals(other.getMisID()))) &&
            ((this.systemID==null && other.getSystemID()==null) || 
             (this.systemID!=null &&
              this.systemID.equals(other.getSystemID()))) &&
            ((this.opinion==null && other.getOpinion()==null) || 
             (this.opinion!=null &&
              this.opinion.equals(other.getOpinion()))) &&
            ((this.cancelDate==null && other.getCancelDate()==null) || 
             (this.cancelDate!=null &&
              this.cancelDate.equals(other.getCancelDate()))) &&
            ((this.timeZone==null && other.getTimeZone()==null) || 
             (this.timeZone!=null &&
              this.timeZone.equals(other.getTimeZone())));
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
        if (getMisID() != null) {
            _hashCode += getMisID().hashCode();
        }
        if (getSystemID() != null) {
            _hashCode += getSystemID().hashCode();
        }
        if (getOpinion() != null) {
            _hashCode += getOpinion().hashCode();
        }
        if (getCancelDate() != null) {
            _hashCode += getCancelDate().hashCode();
        }
        if (getTimeZone() != null) {
            _hashCode += getTimeZone().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelProcessWSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "CancelProcessWSVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("misID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MisID"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opinion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Opinion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CancelDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeZone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TimeZone"));
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
