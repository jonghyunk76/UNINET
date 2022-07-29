/**
 * RouteDelegateWSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.vo;

public class RouteDelegateWSVO  implements java.io.Serializable {
    private java.lang.String originID;

    private java.lang.String proxyID;

    private java.lang.String sequence;

    public RouteDelegateWSVO() {
    }

    public RouteDelegateWSVO(
           java.lang.String originID,
           java.lang.String proxyID,
           java.lang.String sequence) {
           this.originID = originID;
           this.proxyID = proxyID;
           this.sequence = sequence;
    }


    /**
     * Gets the originID value for this RouteDelegateWSVO.
     * 
     * @return originID
     */
    public java.lang.String getOriginID() {
        return originID;
    }


    /**
     * Sets the originID value for this RouteDelegateWSVO.
     * 
     * @param originID
     */
    public void setOriginID(java.lang.String originID) {
        this.originID = originID;
    }


    /**
     * Gets the proxyID value for this RouteDelegateWSVO.
     * 
     * @return proxyID
     */
    public java.lang.String getProxyID() {
        return proxyID;
    }


    /**
     * Sets the proxyID value for this RouteDelegateWSVO.
     * 
     * @param proxyID
     */
    public void setProxyID(java.lang.String proxyID) {
        this.proxyID = proxyID;
    }


    /**
     * Gets the sequence value for this RouteDelegateWSVO.
     * 
     * @return sequence
     */
    public java.lang.String getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this RouteDelegateWSVO.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RouteDelegateWSVO)) return false;
        RouteDelegateWSVO other = (RouteDelegateWSVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.originID==null && other.getOriginID()==null) || 
             (this.originID!=null &&
              this.originID.equals(other.getOriginID()))) &&
            ((this.proxyID==null && other.getProxyID()==null) || 
             (this.proxyID!=null &&
              this.proxyID.equals(other.getProxyID()))) &&
            ((this.sequence==null && other.getSequence()==null) || 
             (this.sequence!=null &&
              this.sequence.equals(other.getSequence())));
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
        if (getOriginID() != null) {
            _hashCode += getOriginID().hashCode();
        }
        if (getProxyID() != null) {
            _hashCode += getProxyID().hashCode();
        }
        if (getSequence() != null) {
            _hashCode += getSequence().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RouteDelegateWSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "RouteDelegateWSVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OriginID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proxyID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProxyID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequence");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Sequence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
