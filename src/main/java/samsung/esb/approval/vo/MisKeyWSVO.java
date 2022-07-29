/**
 * MisKeyWSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.vo;

public class MisKeyWSVO  implements java.io.Serializable {
    private java.lang.String misID;

    private java.lang.String systemID;

    public MisKeyWSVO() {
    }

    public MisKeyWSVO(
           java.lang.String misID,
           java.lang.String systemID) {
           this.misID = misID;
           this.systemID = systemID;
    }


    /**
     * Gets the misID value for this MisKeyWSVO.
     * 
     * @return misID
     */
    public java.lang.String getMisID() {
        return misID;
    }


    /**
     * Sets the misID value for this MisKeyWSVO.
     * 
     * @param misID
     */
    public void setMisID(java.lang.String misID) {
        this.misID = misID;
    }


    /**
     * Gets the systemID value for this MisKeyWSVO.
     * 
     * @return systemID
     */
    public java.lang.String getSystemID() {
        return systemID;
    }


    /**
     * Sets the systemID value for this MisKeyWSVO.
     * 
     * @param systemID
     */
    public void setSystemID(java.lang.String systemID) {
        this.systemID = systemID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MisKeyWSVO)) return false;
        MisKeyWSVO other = (MisKeyWSVO) obj;
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
        if (getMisID() != null) {
            _hashCode += getMisID().hashCode();
        }
        if (getSystemID() != null) {
            _hashCode += getSystemID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MisKeyWSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "MisKeyWSVO"));
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
