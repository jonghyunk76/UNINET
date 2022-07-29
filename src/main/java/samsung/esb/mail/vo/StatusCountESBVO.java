/**
 * StatusCountESBVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.mail.vo;

public class StatusCountESBVO  implements java.io.Serializable {
    private java.lang.Boolean isScheduled;

    private java.lang.Integer totalCount;

    private java.lang.Integer deliveryCount;

    private java.lang.Integer unSeenCount;

    private java.lang.Integer seenCount;

    private java.lang.Integer etcCount;

    private java.lang.Integer cancelCount;

    private java.lang.String mtrKey;

    public StatusCountESBVO() {
    }

    public StatusCountESBVO(
           java.lang.Boolean isScheduled,
           java.lang.Integer totalCount,
           java.lang.Integer deliveryCount,
           java.lang.Integer unSeenCount,
           java.lang.Integer seenCount,
           java.lang.Integer etcCount,
           java.lang.Integer cancelCount,
           java.lang.String mtrKey) {
           this.isScheduled = isScheduled;
           this.totalCount = totalCount;
           this.deliveryCount = deliveryCount;
           this.unSeenCount = unSeenCount;
           this.seenCount = seenCount;
           this.etcCount = etcCount;
           this.cancelCount = cancelCount;
           this.mtrKey = mtrKey;
    }


    /**
     * Gets the isScheduled value for this StatusCountESBVO.
     * 
     * @return isScheduled
     */
    public java.lang.Boolean getIsScheduled() {
        return isScheduled;
    }


    /**
     * Sets the isScheduled value for this StatusCountESBVO.
     * 
     * @param isScheduled
     */
    public void setIsScheduled(java.lang.Boolean isScheduled) {
        this.isScheduled = isScheduled;
    }


    /**
     * Gets the totalCount value for this StatusCountESBVO.
     * 
     * @return totalCount
     */
    public java.lang.Integer getTotalCount() {
        return totalCount;
    }


    /**
     * Sets the totalCount value for this StatusCountESBVO.
     * 
     * @param totalCount
     */
    public void setTotalCount(java.lang.Integer totalCount) {
        this.totalCount = totalCount;
    }


    /**
     * Gets the deliveryCount value for this StatusCountESBVO.
     * 
     * @return deliveryCount
     */
    public java.lang.Integer getDeliveryCount() {
        return deliveryCount;
    }


    /**
     * Sets the deliveryCount value for this StatusCountESBVO.
     * 
     * @param deliveryCount
     */
    public void setDeliveryCount(java.lang.Integer deliveryCount) {
        this.deliveryCount = deliveryCount;
    }


    /**
     * Gets the unSeenCount value for this StatusCountESBVO.
     * 
     * @return unSeenCount
     */
    public java.lang.Integer getUnSeenCount() {
        return unSeenCount;
    }


    /**
     * Sets the unSeenCount value for this StatusCountESBVO.
     * 
     * @param unSeenCount
     */
    public void setUnSeenCount(java.lang.Integer unSeenCount) {
        this.unSeenCount = unSeenCount;
    }


    /**
     * Gets the seenCount value for this StatusCountESBVO.
     * 
     * @return seenCount
     */
    public java.lang.Integer getSeenCount() {
        return seenCount;
    }


    /**
     * Sets the seenCount value for this StatusCountESBVO.
     * 
     * @param seenCount
     */
    public void setSeenCount(java.lang.Integer seenCount) {
        this.seenCount = seenCount;
    }


    /**
     * Gets the etcCount value for this StatusCountESBVO.
     * 
     * @return etcCount
     */
    public java.lang.Integer getEtcCount() {
        return etcCount;
    }


    /**
     * Sets the etcCount value for this StatusCountESBVO.
     * 
     * @param etcCount
     */
    public void setEtcCount(java.lang.Integer etcCount) {
        this.etcCount = etcCount;
    }


    /**
     * Gets the cancelCount value for this StatusCountESBVO.
     * 
     * @return cancelCount
     */
    public java.lang.Integer getCancelCount() {
        return cancelCount;
    }


    /**
     * Sets the cancelCount value for this StatusCountESBVO.
     * 
     * @param cancelCount
     */
    public void setCancelCount(java.lang.Integer cancelCount) {
        this.cancelCount = cancelCount;
    }


    /**
     * Gets the mtrKey value for this StatusCountESBVO.
     * 
     * @return mtrKey
     */
    public java.lang.String getMtrKey() {
        return mtrKey;
    }


    /**
     * Sets the mtrKey value for this StatusCountESBVO.
     * 
     * @param mtrKey
     */
    public void setMtrKey(java.lang.String mtrKey) {
        this.mtrKey = mtrKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusCountESBVO)) return false;
        StatusCountESBVO other = (StatusCountESBVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.isScheduled==null && other.getIsScheduled()==null) || 
             (this.isScheduled!=null &&
              this.isScheduled.equals(other.getIsScheduled()))) &&
            ((this.totalCount==null && other.getTotalCount()==null) || 
             (this.totalCount!=null &&
              this.totalCount.equals(other.getTotalCount()))) &&
            ((this.deliveryCount==null && other.getDeliveryCount()==null) || 
             (this.deliveryCount!=null &&
              this.deliveryCount.equals(other.getDeliveryCount()))) &&
            ((this.unSeenCount==null && other.getUnSeenCount()==null) || 
             (this.unSeenCount!=null &&
              this.unSeenCount.equals(other.getUnSeenCount()))) &&
            ((this.seenCount==null && other.getSeenCount()==null) || 
             (this.seenCount!=null &&
              this.seenCount.equals(other.getSeenCount()))) &&
            ((this.etcCount==null && other.getEtcCount()==null) || 
             (this.etcCount!=null &&
              this.etcCount.equals(other.getEtcCount()))) &&
            ((this.cancelCount==null && other.getCancelCount()==null) || 
             (this.cancelCount!=null &&
              this.cancelCount.equals(other.getCancelCount()))) &&
            ((this.mtrKey==null && other.getMtrKey()==null) || 
             (this.mtrKey!=null &&
              this.mtrKey.equals(other.getMtrKey())));
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
        if (getIsScheduled() != null) {
            _hashCode += getIsScheduled().hashCode();
        }
        if (getTotalCount() != null) {
            _hashCode += getTotalCount().hashCode();
        }
        if (getDeliveryCount() != null) {
            _hashCode += getDeliveryCount().hashCode();
        }
        if (getUnSeenCount() != null) {
            _hashCode += getUnSeenCount().hashCode();
        }
        if (getSeenCount() != null) {
            _hashCode += getSeenCount().hashCode();
        }
        if (getEtcCount() != null) {
            _hashCode += getEtcCount().hashCode();
        }
        if (getCancelCount() != null) {
            _hashCode += getCancelCount().hashCode();
        }
        if (getMtrKey() != null) {
            _hashCode += getMtrKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusCountESBVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/mail/vo", "StatusCountESBVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isScheduled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isScheduled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totalCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unSeenCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unSeenCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seenCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seenCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("etcCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "etcCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cancelCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtrKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mtrKey"));
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
