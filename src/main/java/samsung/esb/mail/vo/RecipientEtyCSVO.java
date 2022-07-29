/**
 * RecipientEtyCSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.mail.vo;

public class RecipientEtyCSVO  implements java.io.Serializable {
    private java.lang.Integer iSeqID;

    private java.lang.String recType;

    private java.lang.String recAddr;

    public RecipientEtyCSVO() {
    }

    public RecipientEtyCSVO(
           java.lang.Integer iSeqID,
           java.lang.String recType,
           java.lang.String recAddr) {
           this.iSeqID = iSeqID;
           this.recType = recType;
           this.recAddr = recAddr;
    }


    /**
     * Gets the iSeqID value for this RecipientEtyCSVO.
     * 
     * @return iSeqID
     */
    public java.lang.Integer getISeqID() {
        return iSeqID;
    }


    /**
     * Sets the iSeqID value for this RecipientEtyCSVO.
     * 
     * @param iSeqID
     */
    public void setISeqID(java.lang.Integer iSeqID) {
        this.iSeqID = iSeqID;
    }


    /**
     * Gets the recType value for this RecipientEtyCSVO.
     * 
     * @return recType
     */
    public java.lang.String getRecType() {
        return recType;
    }


    /**
     * Sets the recType value for this RecipientEtyCSVO.
     * 
     * @param recType
     */
    public void setRecType(java.lang.String recType) {
        this.recType = recType;
    }


    /**
     * Gets the recAddr value for this RecipientEtyCSVO.
     * 
     * @return recAddr
     */
    public java.lang.String getRecAddr() {
        return recAddr;
    }


    /**
     * Sets the recAddr value for this RecipientEtyCSVO.
     * 
     * @param recAddr
     */
    public void setRecAddr(java.lang.String recAddr) {
        this.recAddr = recAddr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecipientEtyCSVO)) return false;
        RecipientEtyCSVO other = (RecipientEtyCSVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.iSeqID==null && other.getISeqID()==null) || 
             (this.iSeqID!=null &&
              this.iSeqID.equals(other.getISeqID()))) &&
            ((this.recType==null && other.getRecType()==null) || 
             (this.recType!=null &&
              this.recType.equals(other.getRecType()))) &&
            ((this.recAddr==null && other.getRecAddr()==null) || 
             (this.recAddr!=null &&
              this.recAddr.equals(other.getRecAddr())));
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
        if (getISeqID() != null) {
            _hashCode += getISeqID().hashCode();
        }
        if (getRecType() != null) {
            _hashCode += getRecType().hashCode();
        }
        if (getRecAddr() != null) {
            _hashCode += getRecAddr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecipientEtyCSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/mail/vo", "RecipientEtyCSVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ISeqID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iSeqID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recAddr"));
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
