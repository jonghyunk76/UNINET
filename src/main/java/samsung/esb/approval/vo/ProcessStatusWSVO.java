/**
 * ProcessStatusWSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.vo;

public class ProcessStatusWSVO  implements java.io.Serializable {
    private java.lang.String body;

    private java.lang.String bodyType;

    private java.lang.String createDate;

    private java.lang.String localeInfo;

    private java.lang.String misID;

    private java.lang.String systemID;

    private java.lang.String notiMail;

    private java.lang.String priority;

    private java.lang.String security;

    private java.lang.String status;

    private java.lang.String timeZone;

    private java.lang.String title;

    private samsung.esb.approval.vo.RouteWSVO[] routeVOs;

    private samsung.esb.approval.vo.AttachmentWSVO[] attachmentVOs;

    public ProcessStatusWSVO() {
    }

    public ProcessStatusWSVO(
           java.lang.String body,
           java.lang.String bodyType,
           java.lang.String createDate,
           java.lang.String localeInfo,
           java.lang.String misID,
           java.lang.String systemID,
           java.lang.String notiMail,
           java.lang.String priority,
           java.lang.String security,
           java.lang.String status,
           java.lang.String timeZone,
           java.lang.String title,
           samsung.esb.approval.vo.RouteWSVO[] routeVOs,
           samsung.esb.approval.vo.AttachmentWSVO[] attachmentVOs) {
           this.body = body;
           this.bodyType = bodyType;
           this.createDate = createDate;
           this.localeInfo = localeInfo;
           this.misID = misID;
           this.systemID = systemID;
           this.notiMail = notiMail;
           this.priority = priority;
           this.security = security;
           this.status = status;
           this.timeZone = timeZone;
           this.title = title;
           this.routeVOs = routeVOs;
           this.attachmentVOs = attachmentVOs;
    }


    /**
     * Gets the body value for this ProcessStatusWSVO.
     * 
     * @return body
     */
    public java.lang.String getBody() {
        return body;
    }


    /**
     * Sets the body value for this ProcessStatusWSVO.
     * 
     * @param body
     */
    public void setBody(java.lang.String body) {
        this.body = body;
    }


    /**
     * Gets the bodyType value for this ProcessStatusWSVO.
     * 
     * @return bodyType
     */
    public java.lang.String getBodyType() {
        return bodyType;
    }


    /**
     * Sets the bodyType value for this ProcessStatusWSVO.
     * 
     * @param bodyType
     */
    public void setBodyType(java.lang.String bodyType) {
        this.bodyType = bodyType;
    }


    /**
     * Gets the createDate value for this ProcessStatusWSVO.
     * 
     * @return createDate
     */
    public java.lang.String getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this ProcessStatusWSVO.
     * 
     * @param createDate
     */
    public void setCreateDate(java.lang.String createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the localeInfo value for this ProcessStatusWSVO.
     * 
     * @return localeInfo
     */
    public java.lang.String getLocaleInfo() {
        return localeInfo;
    }


    /**
     * Sets the localeInfo value for this ProcessStatusWSVO.
     * 
     * @param localeInfo
     */
    public void setLocaleInfo(java.lang.String localeInfo) {
        this.localeInfo = localeInfo;
    }


    /**
     * Gets the misID value for this ProcessStatusWSVO.
     * 
     * @return misID
     */
    public java.lang.String getMisID() {
        return misID;
    }


    /**
     * Sets the misID value for this ProcessStatusWSVO.
     * 
     * @param misID
     */
    public void setMisID(java.lang.String misID) {
        this.misID = misID;
    }


    /**
     * Gets the systemID value for this ProcessStatusWSVO.
     * 
     * @return systemID
     */
    public java.lang.String getSystemID() {
        return systemID;
    }


    /**
     * Sets the systemID value for this ProcessStatusWSVO.
     * 
     * @param systemID
     */
    public void setSystemID(java.lang.String systemID) {
        this.systemID = systemID;
    }


    /**
     * Gets the notiMail value for this ProcessStatusWSVO.
     * 
     * @return notiMail
     */
    public java.lang.String getNotiMail() {
        return notiMail;
    }


    /**
     * Sets the notiMail value for this ProcessStatusWSVO.
     * 
     * @param notiMail
     */
    public void setNotiMail(java.lang.String notiMail) {
        this.notiMail = notiMail;
    }


    /**
     * Gets the priority value for this ProcessStatusWSVO.
     * 
     * @return priority
     */
    public java.lang.String getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this ProcessStatusWSVO.
     * 
     * @param priority
     */
    public void setPriority(java.lang.String priority) {
        this.priority = priority;
    }


    /**
     * Gets the security value for this ProcessStatusWSVO.
     * 
     * @return security
     */
    public java.lang.String getSecurity() {
        return security;
    }


    /**
     * Sets the security value for this ProcessStatusWSVO.
     * 
     * @param security
     */
    public void setSecurity(java.lang.String security) {
        this.security = security;
    }


    /**
     * Gets the status value for this ProcessStatusWSVO.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ProcessStatusWSVO.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the timeZone value for this ProcessStatusWSVO.
     * 
     * @return timeZone
     */
    public java.lang.String getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this ProcessStatusWSVO.
     * 
     * @param timeZone
     */
    public void setTimeZone(java.lang.String timeZone) {
        this.timeZone = timeZone;
    }


    /**
     * Gets the title value for this ProcessStatusWSVO.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this ProcessStatusWSVO.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the routeVOs value for this ProcessStatusWSVO.
     * 
     * @return routeVOs
     */
    public samsung.esb.approval.vo.RouteWSVO[] getRouteVOs() {
        return routeVOs;
    }


    /**
     * Sets the routeVOs value for this ProcessStatusWSVO.
     * 
     * @param routeVOs
     */
    public void setRouteVOs(samsung.esb.approval.vo.RouteWSVO[] routeVOs) {
        this.routeVOs = routeVOs;
    }

    public samsung.esb.approval.vo.RouteWSVO getRouteVOs(int i) {
        return this.routeVOs[i];
    }

    public void setRouteVOs(int i, samsung.esb.approval.vo.RouteWSVO _value) {
        this.routeVOs[i] = _value;
    }


    /**
     * Gets the attachmentVOs value for this ProcessStatusWSVO.
     * 
     * @return attachmentVOs
     */
    public samsung.esb.approval.vo.AttachmentWSVO[] getAttachmentVOs() {
        return attachmentVOs;
    }


    /**
     * Sets the attachmentVOs value for this ProcessStatusWSVO.
     * 
     * @param attachmentVOs
     */
    public void setAttachmentVOs(samsung.esb.approval.vo.AttachmentWSVO[] attachmentVOs) {
        this.attachmentVOs = attachmentVOs;
    }

    public samsung.esb.approval.vo.AttachmentWSVO getAttachmentVOs(int i) {
        return this.attachmentVOs[i];
    }

    public void setAttachmentVOs(int i, samsung.esb.approval.vo.AttachmentWSVO _value) {
        this.attachmentVOs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcessStatusWSVO)) return false;
        ProcessStatusWSVO other = (ProcessStatusWSVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.body==null && other.getBody()==null) || 
             (this.body!=null &&
              this.body.equals(other.getBody()))) &&
            ((this.bodyType==null && other.getBodyType()==null) || 
             (this.bodyType!=null &&
              this.bodyType.equals(other.getBodyType()))) &&
            ((this.createDate==null && other.getCreateDate()==null) || 
             (this.createDate!=null &&
              this.createDate.equals(other.getCreateDate()))) &&
            ((this.localeInfo==null && other.getLocaleInfo()==null) || 
             (this.localeInfo!=null &&
              this.localeInfo.equals(other.getLocaleInfo()))) &&
            ((this.misID==null && other.getMisID()==null) || 
             (this.misID!=null &&
              this.misID.equals(other.getMisID()))) &&
            ((this.systemID==null && other.getSystemID()==null) || 
             (this.systemID!=null &&
              this.systemID.equals(other.getSystemID()))) &&
            ((this.notiMail==null && other.getNotiMail()==null) || 
             (this.notiMail!=null &&
              this.notiMail.equals(other.getNotiMail()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.security==null && other.getSecurity()==null) || 
             (this.security!=null &&
              this.security.equals(other.getSecurity()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.timeZone==null && other.getTimeZone()==null) || 
             (this.timeZone!=null &&
              this.timeZone.equals(other.getTimeZone()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.routeVOs==null && other.getRouteVOs()==null) || 
             (this.routeVOs!=null &&
              java.util.Arrays.equals(this.routeVOs, other.getRouteVOs()))) &&
            ((this.attachmentVOs==null && other.getAttachmentVOs()==null) || 
             (this.attachmentVOs!=null &&
              java.util.Arrays.equals(this.attachmentVOs, other.getAttachmentVOs())));
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
        if (getBody() != null) {
            _hashCode += getBody().hashCode();
        }
        if (getBodyType() != null) {
            _hashCode += getBodyType().hashCode();
        }
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        if (getLocaleInfo() != null) {
            _hashCode += getLocaleInfo().hashCode();
        }
        if (getMisID() != null) {
            _hashCode += getMisID().hashCode();
        }
        if (getSystemID() != null) {
            _hashCode += getSystemID().hashCode();
        }
        if (getNotiMail() != null) {
            _hashCode += getNotiMail().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getSecurity() != null) {
            _hashCode += getSecurity().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getTimeZone() != null) {
            _hashCode += getTimeZone().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getRouteVOs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRouteVOs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRouteVOs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAttachmentVOs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttachmentVOs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttachmentVOs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcessStatusWSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessStatusWSVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("body");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Body"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bodyType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BodyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreateDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localeInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LocaleInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("notiMail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NotiMail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("security");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Security"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeZone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TimeZone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeVOs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RouteVOs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "RouteWSVO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentVOs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AttachmentVOs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "AttachmentWSVO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
