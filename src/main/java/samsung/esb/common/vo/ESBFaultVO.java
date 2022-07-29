/**
 * ESBFaultVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.common.vo;

public class ESBFaultVO  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private java.lang.String faultActor1;

    private java.lang.String faultCode1;

    private java.lang.String faultString1;

    private java.lang.String faultMessage;

    public ESBFaultVO() {
    }

    public ESBFaultVO(
           java.lang.String faultActor1,
           java.lang.String faultCode1,
           java.lang.String faultString1,
           java.lang.String faultMessage) {
        this.faultActor1 = faultActor1;
        this.faultCode1 = faultCode1;
        this.faultString1 = faultString1;
        this.faultMessage = faultMessage;
    }


    /**
     * Gets the faultActor1 value for this ESBFaultVO.
     * 
     * @return faultActor1
     */
    public java.lang.String getFaultActor1() {
        return faultActor1;
    }


    /**
     * Sets the faultActor1 value for this ESBFaultVO.
     * 
     * @param faultActor1
     */
    public void setFaultActor1(java.lang.String faultActor1) {
        this.faultActor1 = faultActor1;
    }


    /**
     * Gets the faultCode1 value for this ESBFaultVO.
     * 
     * @return faultCode1
     */
    public java.lang.String getFaultCode1() {
        return faultCode1;
    }


    /**
     * Sets the faultCode1 value for this ESBFaultVO.
     * 
     * @param faultCode1
     */
    public void setFaultCode1(java.lang.String faultCode1) {
        this.faultCode1 = faultCode1;
    }


    /**
     * Gets the faultString1 value for this ESBFaultVO.
     * 
     * @return faultString1
     */
    public java.lang.String getFaultString1() {
        return faultString1;
    }


    /**
     * Sets the faultString1 value for this ESBFaultVO.
     * 
     * @param faultString1
     */
    public void setFaultString1(java.lang.String faultString1) {
        this.faultString1 = faultString1;
    }


    /**
     * Gets the faultMessage value for this ESBFaultVO.
     * 
     * @return faultMessage
     */
    public java.lang.String getFaultMessage() {
        return faultMessage;
    }


    /**
     * Sets the faultMessage value for this ESBFaultVO.
     * 
     * @param faultMessage
     */
    public void setFaultMessage(java.lang.String faultMessage) {
        this.faultMessage = faultMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESBFaultVO)) return false;
        ESBFaultVO other = (ESBFaultVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.faultActor1==null && other.getFaultActor1()==null) || 
             (this.faultActor1!=null &&
              this.faultActor1.equals(other.getFaultActor1()))) &&
            ((this.faultCode1==null && other.getFaultCode1()==null) || 
             (this.faultCode1!=null &&
              this.faultCode1.equals(other.getFaultCode1()))) &&
            ((this.faultString1==null && other.getFaultString1()==null) || 
             (this.faultString1!=null &&
              this.faultString1.equals(other.getFaultString1()))) &&
            ((this.faultMessage==null && other.getFaultMessage()==null) || 
             (this.faultMessage!=null &&
              this.faultMessage.equals(other.getFaultMessage())));
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
        if (getFaultActor1() != null) {
            _hashCode += getFaultActor1().hashCode();
        }
        if (getFaultCode1() != null) {
            _hashCode += getFaultCode1().hashCode();
        }
        if (getFaultString1() != null) {
            _hashCode += getFaultString1().hashCode();
        }
        if (getFaultMessage() != null) {
            _hashCode += getFaultMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESBFaultVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faultActor1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "faultActor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faultCode1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "faultCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faultString1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "faultString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faultMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "faultMessage"));
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


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
