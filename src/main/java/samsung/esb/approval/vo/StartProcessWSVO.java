/**
 * StartProcessWSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.vo;

import samsung.esb.mail.vo.AttachEtyCSVO;

public class StartProcessWSVO  implements java.io.Serializable {
    private java.lang.String body;

    private java.lang.String bodyType;

    private java.lang.String createDate;

    private java.lang.String drmOptionInfo;

    private java.lang.String localeInfo;

    private java.lang.String misID;

    private java.lang.String systemID;

    private java.lang.String notiMail;

    private java.lang.String priority;

    private java.lang.String TCode;

    private java.lang.String TName;

    private java.lang.String security;

    private java.lang.String timeZone;

    private java.lang.String title;

    private samsung.esb.approval.vo.RouteWSVO[] routeVOs;

    private AttachmentWSVO[] attachmentVOs;

    private java.lang.Boolean sendDMS;

    public StartProcessWSVO() {
    }

    public StartProcessWSVO(
           java.lang.String body,
           java.lang.String bodyType,
           java.lang.String createDate,
           java.lang.String drmOptionInfo,
           java.lang.String localeInfo,
           java.lang.String misID,
           java.lang.String systemID,
           java.lang.String notiMail,
           java.lang.String priority,
           java.lang.String TCode,
           java.lang.String TName,
           java.lang.String security,
           java.lang.String timeZone,
           java.lang.String title,
           samsung.esb.approval.vo.RouteWSVO[] routeVOs,
           AttachmentWSVO[] attachmentVOs,
           java.lang.Boolean sendDMS) {
           this.body = body;
           this.bodyType = bodyType;
           this.createDate = createDate;
           this.drmOptionInfo = drmOptionInfo;
           this.localeInfo = localeInfo;
           this.misID = misID;
           this.systemID = systemID;
           this.notiMail = notiMail;
           this.priority = priority;
           this.TCode = TCode;
           this.TName = TName;
           this.security = security;
           this.timeZone = timeZone;
           this.title = title;
           this.routeVOs = routeVOs;
           this.attachmentVOs = attachmentVOs;
           this.sendDMS = sendDMS;
    }


    /**
     * Gets the body value for this StartProcessWSVO.
     * 
     * @return body
     */
    public java.lang.String getBody() {
        return body;
    }


    /**
     * Sets the body value for this StartProcessWSVO.
     * 
     * @param body
     */
    public void setBody(java.lang.String body) {
        this.body = body;
    }


    /**
     * Gets the bodyType value for this StartProcessWSVO.
     * 
     * @return bodyType
     */
    public java.lang.String getBodyType() {
        return bodyType;
    }


    /**
     * Sets the bodyType value for this StartProcessWSVO.
     * 
     * @param bodyType
     */
    public void setBodyType(java.lang.String bodyType) {
        this.bodyType = bodyType;
    }


    /**
     * Gets the createDate value for this StartProcessWSVO.
     * 
     * @return createDate
     */
    public java.lang.String getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this StartProcessWSVO.
     * 
     * @param createDate
     */
    public void setCreateDate(java.lang.String createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the drmOptionInfo value for this StartProcessWSVO.
     * 
     * @return drmOptionInfo
     */
    public java.lang.String getDrmOptionInfo() {
        return drmOptionInfo;
    }


    /**
     * Sets the drmOptionInfo value for this StartProcessWSVO.
     * 
     * @param drmOptionInfo
     */
    public void setDrmOptionInfo(java.lang.String drmOptionInfo) {
        this.drmOptionInfo = drmOptionInfo;
    }


    /**
     * Gets the localeInfo value for this StartProcessWSVO.
     * 
     * @return localeInfo
     */
    public java.lang.String getLocaleInfo() {
        return localeInfo;
    }


    /**
     * Sets the localeInfo value for this StartProcessWSVO.
     * 
     * @param localeInfo
     */
    public void setLocaleInfo(java.lang.String localeInfo) {
        this.localeInfo = localeInfo;
    }


    /**
     * Gets the misID value for this StartProcessWSVO.
     * 
     * @return misID
     */
    public java.lang.String getMisID() {
        return misID;
    }


    /**
     * Sets the misID value for this StartProcessWSVO.
     * 
     * @param misID
     */
    public void setMisID(java.lang.String misID) {
        this.misID = misID;
    }


    /**
     * Gets the systemID value for this StartProcessWSVO.
     * 
     * @return systemID
     */
    public java.lang.String getSystemID() {
        return systemID;
    }


    /**
     * Sets the systemID value for this StartProcessWSVO.
     * 
     * @param systemID
     */
    public void setSystemID(java.lang.String systemID) {
        this.systemID = systemID;
    }


    /**
     * Gets the notiMail value for this StartProcessWSVO.
     * 
     * @return notiMail
     */
    public java.lang.String getNotiMail() {
        return notiMail;
    }


    /**
     * Sets the notiMail value for this StartProcessWSVO.
     * 
     * @param notiMail
     */
    public void setNotiMail(java.lang.String notiMail) {
        this.notiMail = notiMail;
    }


    /**
     * Gets the priority value for this StartProcessWSVO.
     * 
     * @return priority
     */
    public java.lang.String getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this StartProcessWSVO.
     * 
     * @param priority
     */
    public void setPriority(java.lang.String priority) {
        this.priority = priority;
    }


    /**
     * Gets the TCode value for this StartProcessWSVO.
     * 
     * @return TCode
     */
    public java.lang.String getTCode() {
        return TCode;
    }


    /**
     * Sets the TCode value for this StartProcessWSVO.
     * 
     * @param TCode
     */
    public void setTCode(java.lang.String TCode) {
        this.TCode = TCode;
    }


    /**
     * Gets the TName value for this StartProcessWSVO.
     * 
     * @return TName
     */
    public java.lang.String getTName() {
        return TName;
    }


    /**
     * Sets the TName value for this StartProcessWSVO.
     * 
     * @param TName
     */
    public void setTName(java.lang.String TName) {
        this.TName = TName;
    }


    /**
     * Gets the security value for this StartProcessWSVO.
     * 
     * @return security
     */
    public java.lang.String getSecurity() {
        return security;
    }


    /**
     * Sets the security value for this StartProcessWSVO.
     * 
     * @param security
     */
    public void setSecurity(java.lang.String security) {
        this.security = security;
    }


    /**
     * Gets the timeZone value for this StartProcessWSVO.
     * 
     * @return timeZone
     */
    public java.lang.String getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this StartProcessWSVO.
     * 
     * @param timeZone
     */
    public void setTimeZone(java.lang.String timeZone) {
        this.timeZone = timeZone;
    }


    /**
     * Gets the title value for this StartProcessWSVO.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this StartProcessWSVO.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the routeVOs value for this StartProcessWSVO.
     * 
     * @return routeVOs
     */
    public samsung.esb.approval.vo.RouteWSVO[] getRouteVOs() {
        return routeVOs;
    }


    /**
     * Sets the routeVOs value for this StartProcessWSVO.
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
     * Gets the attachmentVOs value for this StartProcessWSVO.
     * 
     * @return attachmentVOs
     */
    public AttachmentWSVO[] getAttachmentVOs() {
        return attachmentVOs;
    }


    /**
     * Sets the attachmentVOs value for this StartProcessWSVO.
     * 
     * @param attachmentVOs2
     */
    public void setAttachmentVOs(AttachmentWSVO[] attachmentVOs2) {
        this.attachmentVOs = attachmentVOs2;
    }

    public AttachmentWSVO getAttachmentVOs(int i) {
        return this.attachmentVOs[i];
    }

    public void setAttachmentVOs(int i, AttachmentWSVO _value) {
        this.attachmentVOs[i] = _value;
    }


    /**
     * Gets the sendDMS value for this StartProcessWSVO.
     * 
     * @return sendDMS
     */
    public java.lang.Boolean getSendDMS() {
        return sendDMS;
    }


    /**
     * Sets the sendDMS value for this StartProcessWSVO.
     * 
     * @param sendDMS
     */
    public void setSendDMS(java.lang.Boolean sendDMS) {
        this.sendDMS = sendDMS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StartProcessWSVO)) return false;
        StartProcessWSVO other = (StartProcessWSVO) obj;
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
            ((this.drmOptionInfo==null && other.getDrmOptionInfo()==null) || 
             (this.drmOptionInfo!=null &&
              this.drmOptionInfo.equals(other.getDrmOptionInfo()))) &&
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
            ((this.TCode==null && other.getTCode()==null) || 
             (this.TCode!=null &&
              this.TCode.equals(other.getTCode()))) &&
            ((this.TName==null && other.getTName()==null) || 
             (this.TName!=null &&
              this.TName.equals(other.getTName()))) &&
            ((this.security==null && other.getSecurity()==null) || 
             (this.security!=null &&
              this.security.equals(other.getSecurity()))) &&
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
              java.util.Arrays.equals(this.attachmentVOs, other.getAttachmentVOs()))) &&
            ((this.sendDMS==null && other.getSendDMS()==null) || 
             (this.sendDMS!=null &&
              this.sendDMS.equals(other.getSendDMS())));
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
        if (getDrmOptionInfo() != null) {
            _hashCode += getDrmOptionInfo().hashCode();
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
        if (getTCode() != null) {
            _hashCode += getTCode().hashCode();
        }
        if (getTName() != null) {
            _hashCode += getTName().hashCode();
        }
        if (getSecurity() != null) {
            _hashCode += getSecurity().hashCode();
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
        if (getSendDMS() != null) {
            _hashCode += getSendDMS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StartProcessWSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "StartProcessWSVO"));
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
        elemField.setFieldName("drmOptionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DrmOptionInfo"));
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
        elemField.setFieldName("TCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TName"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendDMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SendDMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
