/**
 * ProcessRevisionWSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.vo;

public class ProcessRevisionWSVO  implements java.io.Serializable {
    private java.lang.String misID;

    private java.lang.String procinstID;

    private java.lang.String revision;

    private java.lang.String status;

    public ProcessRevisionWSVO() {
    }

    public ProcessRevisionWSVO(
           java.lang.String misID,
           java.lang.String procinstID,
           java.lang.String revision,
           java.lang.String status) {
           this.misID = misID;
           this.procinstID = procinstID;
           this.revision = revision;
           this.status = status;
    }


    /**
     * Gets the misID value for this ProcessRevisionWSVO.
     * 
     * @return misID
     */
    public java.lang.String getMisID() {
        return misID;
    }


    /**
     * Sets the misID value for this ProcessRevisionWSVO.
     * 
     * @param misID
     */
    public void setMisID(java.lang.String misID) {
        this.misID = misID;
    }


    /**
     * Gets the procinstID value for this ProcessRevisionWSVO.
     * 
     * @return procinstID
     */
    public java.lang.String getProcinstID() {
        return procinstID;
    }


    /**
     * Sets the procinstID value for this ProcessRevisionWSVO.
     * 
     * @param procinstID
     */
    public void setProcinstID(java.lang.String procinstID) {
        this.procinstID = procinstID;
    }


    /**
     * Gets the revision value for this ProcessRevisionWSVO.
     * 
     * @return revision
     */
    public java.lang.String getRevision() {
        return revision;
    }


    /**
     * Sets the revision value for this ProcessRevisionWSVO.
     * 
     * @param revision
     */
    public void setRevision(java.lang.String revision) {
        this.revision = revision;
    }


    /**
     * Gets the status value for this ProcessRevisionWSVO.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ProcessRevisionWSVO.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcessRevisionWSVO)) return false;
        ProcessRevisionWSVO other = (ProcessRevisionWSVO) obj;
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
            ((this.procinstID==null && other.getProcinstID()==null) || 
             (this.procinstID!=null &&
              this.procinstID.equals(other.getProcinstID()))) &&
            ((this.revision==null && other.getRevision()==null) || 
             (this.revision!=null &&
              this.revision.equals(other.getRevision()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getProcinstID() != null) {
            _hashCode += getProcinstID().hashCode();
        }
        if (getRevision() != null) {
            _hashCode += getRevision().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcessRevisionWSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessRevisionWSVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("misID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MisID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procinstID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProcinstID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revision");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Revision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Status"));
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
