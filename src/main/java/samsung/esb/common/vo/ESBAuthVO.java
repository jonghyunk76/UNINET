/**
 * ESBAuthVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.common.vo;

public class ESBAuthVO  implements java.io.Serializable {
    private java.lang.String cID;

    private java.lang.String cPW;

    public ESBAuthVO() {
    }

    public ESBAuthVO(
           java.lang.String cID,
           java.lang.String cPW) {
           this.cID = cID;
           this.cPW = cPW;
    }


    /**
     * Gets the cID value for this ESBAuthVO.
     * 
     * @return cID
     */
    public java.lang.String getCID() {
        return cID;
    }


    /**
     * Sets the cID value for this ESBAuthVO.
     * 
     * @param cID
     */
    public void setCID(java.lang.String cID) {
        this.cID = cID;
    }


    /**
     * Gets the cPW value for this ESBAuthVO.
     * 
     * @return cPW
     */
    public java.lang.String getCPW() {
        return cPW;
    }


    /**
     * Sets the cPW value for this ESBAuthVO.
     * 
     * @param cPW
     */
    public void setCPW(java.lang.String cPW) {
        this.cPW = cPW;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESBAuthVO)) return false;
        ESBAuthVO other = (ESBAuthVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cID==null && other.getCID()==null) || 
             (this.cID!=null &&
              this.cID.equals(other.getCID()))) &&
            ((this.cPW==null && other.getCPW()==null) || 
             (this.cPW!=null &&
              this.cPW.equals(other.getCPW())));
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
        if (getCID() != null) {
            _hashCode += getCID().hashCode();
        }
        if (getCPW() != null) {
            _hashCode += getCPW().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESBAuthVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CPW");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cPW"));
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
