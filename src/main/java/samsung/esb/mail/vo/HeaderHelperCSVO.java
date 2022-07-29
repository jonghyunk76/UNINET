/**
 * HeaderHelperCSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.mail.vo;

public class HeaderHelperCSVO  implements java.io.Serializable {
    private java.lang.String subject;

    private java.lang.String msgType;

    private java.lang.Boolean bHtmlContentCheck;

    private java.lang.Integer iFileCount;

    private java.lang.String senderMailAddr;

    private java.lang.String timeZone;

    private java.lang.Boolean isDst;

    private java.lang.String folderName;

    private java.lang.Boolean bExternal;

    private java.lang.String epTrCode;

    private java.lang.String epTrName;

    private java.lang.Boolean bMhtBody;

    private java.lang.Integer priority;

    private java.lang.String deliveryType;

    public HeaderHelperCSVO() {
    }

    public HeaderHelperCSVO(
           java.lang.String subject,
           java.lang.String msgType,
           java.lang.Boolean bHtmlContentCheck,
           java.lang.Integer iFileCount,
           java.lang.String senderMailAddr,
           java.lang.String timeZone,
           java.lang.Boolean isDst,
           java.lang.String folderName,
           java.lang.Boolean bExternal,
           java.lang.String epTrCode,
           java.lang.String epTrName,
           java.lang.Boolean bMhtBody,
           java.lang.Integer priority,
           java.lang.String deliveryType) {
           this.subject = subject;
           this.msgType = msgType;
           this.bHtmlContentCheck = bHtmlContentCheck;
           this.iFileCount = iFileCount;
           this.senderMailAddr = senderMailAddr;
           this.timeZone = timeZone;
           this.isDst = isDst;
           this.folderName = folderName;
           this.bExternal = bExternal;
           this.epTrCode = epTrCode;
           this.epTrName = epTrName;
           this.bMhtBody = bMhtBody;
           this.priority = priority;
           this.deliveryType = deliveryType;
    }


    /**
     * Gets the subject value for this HeaderHelperCSVO.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this HeaderHelperCSVO.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the msgType value for this HeaderHelperCSVO.
     * 
     * @return msgType
     */
    public java.lang.String getMsgType() {
        return msgType;
    }


    /**
     * Sets the msgType value for this HeaderHelperCSVO.
     * 
     * @param msgType
     */
    public void setMsgType(java.lang.String msgType) {
        this.msgType = msgType;
    }


    /**
     * Gets the bHtmlContentCheck value for this HeaderHelperCSVO.
     * 
     * @return bHtmlContentCheck
     */
    public java.lang.Boolean getBHtmlContentCheck() {
        return bHtmlContentCheck;
    }


    /**
     * Sets the bHtmlContentCheck value for this HeaderHelperCSVO.
     * 
     * @param bHtmlContentCheck
     */
    public void setBHtmlContentCheck(java.lang.Boolean bHtmlContentCheck) {
        this.bHtmlContentCheck = bHtmlContentCheck;
    }


    /**
     * Gets the iFileCount value for this HeaderHelperCSVO.
     * 
     * @return iFileCount
     */
    public java.lang.Integer getIFileCount() {
        return iFileCount;
    }


    /**
     * Sets the iFileCount value for this HeaderHelperCSVO.
     * 
     * @param iFileCount
     */
    public void setIFileCount(java.lang.Integer iFileCount) {
        this.iFileCount = iFileCount;
    }


    /**
     * Gets the senderMailAddr value for this HeaderHelperCSVO.
     * 
     * @return senderMailAddr
     */
    public java.lang.String getSenderMailAddr() {
        return senderMailAddr;
    }


    /**
     * Sets the senderMailAddr value for this HeaderHelperCSVO.
     * 
     * @param senderMailAddr
     */
    public void setSenderMailAddr(java.lang.String senderMailAddr) {
        this.senderMailAddr = senderMailAddr;
    }


    /**
     * Gets the timeZone value for this HeaderHelperCSVO.
     * 
     * @return timeZone
     */
    public java.lang.String getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this HeaderHelperCSVO.
     * 
     * @param timeZone
     */
    public void setTimeZone(java.lang.String timeZone) {
        this.timeZone = timeZone;
    }


    /**
     * Gets the isDst value for this HeaderHelperCSVO.
     * 
     * @return isDst
     */
    public java.lang.Boolean getIsDst() {
        return isDst;
    }


    /**
     * Sets the isDst value for this HeaderHelperCSVO.
     * 
     * @param isDst
     */
    public void setIsDst(java.lang.Boolean isDst) {
        this.isDst = isDst;
    }


    /**
     * Gets the folderName value for this HeaderHelperCSVO.
     * 
     * @return folderName
     */
    public java.lang.String getFolderName() {
        return folderName;
    }


    /**
     * Sets the folderName value for this HeaderHelperCSVO.
     * 
     * @param folderName
     */
    public void setFolderName(java.lang.String folderName) {
        this.folderName = folderName;
    }


    /**
     * Gets the bExternal value for this HeaderHelperCSVO.
     * 
     * @return bExternal
     */
    public java.lang.Boolean getBExternal() {
        return bExternal;
    }


    /**
     * Sets the bExternal value for this HeaderHelperCSVO.
     * 
     * @param bExternal
     */
    public void setBExternal(java.lang.Boolean bExternal) {
        this.bExternal = bExternal;
    }


    /**
     * Gets the epTrCode value for this HeaderHelperCSVO.
     * 
     * @return epTrCode
     */
    public java.lang.String getEpTrCode() {
        return epTrCode;
    }


    /**
     * Sets the epTrCode value for this HeaderHelperCSVO.
     * 
     * @param epTrCode
     */
    public void setEpTrCode(java.lang.String epTrCode) {
        this.epTrCode = epTrCode;
    }


    /**
     * Gets the epTrName value for this HeaderHelperCSVO.
     * 
     * @return epTrName
     */
    public java.lang.String getEpTrName() {
        return epTrName;
    }


    /**
     * Sets the epTrName value for this HeaderHelperCSVO.
     * 
     * @param epTrName
     */
    public void setEpTrName(java.lang.String epTrName) {
        this.epTrName = epTrName;
    }


    /**
     * Gets the bMhtBody value for this HeaderHelperCSVO.
     * 
     * @return bMhtBody
     */
    public java.lang.Boolean getBMhtBody() {
        return bMhtBody;
    }


    /**
     * Sets the bMhtBody value for this HeaderHelperCSVO.
     * 
     * @param bMhtBody
     */
    public void setBMhtBody(java.lang.Boolean bMhtBody) {
        this.bMhtBody = bMhtBody;
    }


    /**
     * Gets the priority value for this HeaderHelperCSVO.
     * 
     * @return priority
     */
    public java.lang.Integer getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this HeaderHelperCSVO.
     * 
     * @param priority
     */
    public void setPriority(java.lang.Integer priority) {
        this.priority = priority;
    }


    /**
     * Gets the deliveryType value for this HeaderHelperCSVO.
     * 
     * @return deliveryType
     */
    public java.lang.String getDeliveryType() {
        return deliveryType;
    }


    /**
     * Sets the deliveryType value for this HeaderHelperCSVO.
     * 
     * @param deliveryType
     */
    public void setDeliveryType(java.lang.String deliveryType) {
        this.deliveryType = deliveryType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HeaderHelperCSVO)) return false;
        HeaderHelperCSVO other = (HeaderHelperCSVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.msgType==null && other.getMsgType()==null) || 
             (this.msgType!=null &&
              this.msgType.equals(other.getMsgType()))) &&
            ((this.bHtmlContentCheck==null && other.getBHtmlContentCheck()==null) || 
             (this.bHtmlContentCheck!=null &&
              this.bHtmlContentCheck.equals(other.getBHtmlContentCheck()))) &&
            ((this.iFileCount==null && other.getIFileCount()==null) || 
             (this.iFileCount!=null &&
              this.iFileCount.equals(other.getIFileCount()))) &&
            ((this.senderMailAddr==null && other.getSenderMailAddr()==null) || 
             (this.senderMailAddr!=null &&
              this.senderMailAddr.equals(other.getSenderMailAddr()))) &&
            ((this.timeZone==null && other.getTimeZone()==null) || 
             (this.timeZone!=null &&
              this.timeZone.equals(other.getTimeZone()))) &&
            ((this.isDst==null && other.getIsDst()==null) || 
             (this.isDst!=null &&
              this.isDst.equals(other.getIsDst()))) &&
            ((this.folderName==null && other.getFolderName()==null) || 
             (this.folderName!=null &&
              this.folderName.equals(other.getFolderName()))) &&
            ((this.bExternal==null && other.getBExternal()==null) || 
             (this.bExternal!=null &&
              this.bExternal.equals(other.getBExternal()))) &&
            ((this.epTrCode==null && other.getEpTrCode()==null) || 
             (this.epTrCode!=null &&
              this.epTrCode.equals(other.getEpTrCode()))) &&
            ((this.epTrName==null && other.getEpTrName()==null) || 
             (this.epTrName!=null &&
              this.epTrName.equals(other.getEpTrName()))) &&
            ((this.bMhtBody==null && other.getBMhtBody()==null) || 
             (this.bMhtBody!=null &&
              this.bMhtBody.equals(other.getBMhtBody()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.deliveryType==null && other.getDeliveryType()==null) || 
             (this.deliveryType!=null &&
              this.deliveryType.equals(other.getDeliveryType())));
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
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getMsgType() != null) {
            _hashCode += getMsgType().hashCode();
        }
        if (getBHtmlContentCheck() != null) {
            _hashCode += getBHtmlContentCheck().hashCode();
        }
        if (getIFileCount() != null) {
            _hashCode += getIFileCount().hashCode();
        }
        if (getSenderMailAddr() != null) {
            _hashCode += getSenderMailAddr().hashCode();
        }
        if (getTimeZone() != null) {
            _hashCode += getTimeZone().hashCode();
        }
        if (getIsDst() != null) {
            _hashCode += getIsDst().hashCode();
        }
        if (getFolderName() != null) {
            _hashCode += getFolderName().hashCode();
        }
        if (getBExternal() != null) {
            _hashCode += getBExternal().hashCode();
        }
        if (getEpTrCode() != null) {
            _hashCode += getEpTrCode().hashCode();
        }
        if (getEpTrName() != null) {
            _hashCode += getEpTrName().hashCode();
        }
        if (getBMhtBody() != null) {
            _hashCode += getBMhtBody().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getDeliveryType() != null) {
            _hashCode += getDeliveryType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HeaderHelperCSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/mail/vo", "HeaderHelperCSVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msgType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BHtmlContentCheck");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bHtmlContentCheck"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IFileCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iFileCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderMailAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderMailAddr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeZone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeZone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isDst");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isDst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BExternal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bExternal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epTrCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "epTrCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epTrName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "epTrName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BMhtBody");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bMhtBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeliveryType"));
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
