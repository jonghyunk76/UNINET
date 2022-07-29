/**
 * Title.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package model.outldap.samsung.net;

public class Title  implements java.io.Serializable {
    private java.lang.String epentitle;

    private java.lang.String eporganizationnumber;

    private java.lang.String epsortorder;

    private java.lang.String eptitlenumber;

    private java.lang.String title;

    public Title() {
    }

    public Title(
           java.lang.String epentitle,
           java.lang.String eporganizationnumber,
           java.lang.String epsortorder,
           java.lang.String eptitlenumber,
           java.lang.String title) {
           this.epentitle = epentitle;
           this.eporganizationnumber = eporganizationnumber;
           this.epsortorder = epsortorder;
           this.eptitlenumber = eptitlenumber;
           this.title = title;
    }


    /**
     * Gets the epentitle value for this Title.
     * 
     * @return epentitle
     */
    public java.lang.String getEpentitle() {
        return epentitle;
    }


    /**
     * Sets the epentitle value for this Title.
     * 
     * @param epentitle
     */
    public void setEpentitle(java.lang.String epentitle) {
        this.epentitle = epentitle;
    }


    /**
     * Gets the eporganizationnumber value for this Title.
     * 
     * @return eporganizationnumber
     */
    public java.lang.String getEporganizationnumber() {
        return eporganizationnumber;
    }


    /**
     * Sets the eporganizationnumber value for this Title.
     * 
     * @param eporganizationnumber
     */
    public void setEporganizationnumber(java.lang.String eporganizationnumber) {
        this.eporganizationnumber = eporganizationnumber;
    }


    /**
     * Gets the epsortorder value for this Title.
     * 
     * @return epsortorder
     */
    public java.lang.String getEpsortorder() {
        return epsortorder;
    }


    /**
     * Sets the epsortorder value for this Title.
     * 
     * @param epsortorder
     */
    public void setEpsortorder(java.lang.String epsortorder) {
        this.epsortorder = epsortorder;
    }


    /**
     * Gets the eptitlenumber value for this Title.
     * 
     * @return eptitlenumber
     */
    public java.lang.String getEptitlenumber() {
        return eptitlenumber;
    }


    /**
     * Sets the eptitlenumber value for this Title.
     * 
     * @param eptitlenumber
     */
    public void setEptitlenumber(java.lang.String eptitlenumber) {
        this.eptitlenumber = eptitlenumber;
    }


    /**
     * Gets the title value for this Title.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this Title.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Title)) return false;
        Title other = (Title) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.epentitle==null && other.getEpentitle()==null) || 
             (this.epentitle!=null &&
              this.epentitle.equals(other.getEpentitle()))) &&
            ((this.eporganizationnumber==null && other.getEporganizationnumber()==null) || 
             (this.eporganizationnumber!=null &&
              this.eporganizationnumber.equals(other.getEporganizationnumber()))) &&
            ((this.epsortorder==null && other.getEpsortorder()==null) || 
             (this.epsortorder!=null &&
              this.epsortorder.equals(other.getEpsortorder()))) &&
            ((this.eptitlenumber==null && other.getEptitlenumber()==null) || 
             (this.eptitlenumber!=null &&
              this.eptitlenumber.equals(other.getEptitlenumber()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle())));
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
        if (getEpentitle() != null) {
            _hashCode += getEpentitle().hashCode();
        }
        if (getEporganizationnumber() != null) {
            _hashCode += getEporganizationnumber().hashCode();
        }
        if (getEpsortorder() != null) {
            _hashCode += getEpsortorder().hashCode();
        }
        if (getEptitlenumber() != null) {
            _hashCode += getEptitlenumber().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Title.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Title"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epentitle");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epentitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eporganizationnumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Eporganizationnumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsortorder");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsortorder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eptitlenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Eptitlenumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Title"));
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
