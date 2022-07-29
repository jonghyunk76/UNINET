/**
 * AttachEtyCSVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.mail.vo;

import javax.activation.DataHandler;

public class AttachEtyCSVO  implements java.io.Serializable {
//    private org.apache.axis.types.URI file;

	private DataHandler		file;
	
    private java.lang.Integer iSeqID;

    private java.lang.String fileName;

    private java.lang.Integer fileSize;

    private java.lang.String localFileName;

    private java.lang.String localPath;

    private java.lang.String fileServer;

    private java.lang.String msgKeyID;

    public AttachEtyCSVO() {
    }

    public AttachEtyCSVO(
//           org.apache.axis.types.URI file,
    		DataHandler file,
           java.lang.Integer iSeqID,
           java.lang.String fileName,
           java.lang.Integer fileSize,
           java.lang.String localFileName,
           java.lang.String localPath,
           java.lang.String fileServer,
           java.lang.String msgKeyID) {
           this.file = file;
           this.iSeqID = iSeqID;
           this.fileName = fileName;
           this.fileSize = fileSize;
           this.localFileName = localFileName;
           this.localPath = localPath;
           this.fileServer = fileServer;
           this.msgKeyID = msgKeyID;
    }


    /**
     * Gets the file value for this AttachEtyCSVO.
     * 
     * @return file
     */
//    public org.apache.axis.types.URI getFile() {
    public DataHandler getFile() {
        return file;
    }


    /**
     * Sets the file value for this AttachEtyCSVO.
     * 
     * @param file
     */
//    public void setFile(org.apache.axis.types.URI file) {
    public void setFile(DataHandler file) {
        this.file = file;
    }


    /**
     * Gets the iSeqID value for this AttachEtyCSVO.
     * 
     * @return iSeqID
     */
    public java.lang.Integer getISeqID() {
        return iSeqID;
    }


    /**
     * Sets the iSeqID value for this AttachEtyCSVO.
     * 
     * @param iSeqID
     */
    public void setISeqID(java.lang.Integer iSeqID) {
        this.iSeqID = iSeqID;
    }


    /**
     * Gets the fileName value for this AttachEtyCSVO.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this AttachEtyCSVO.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the fileSize value for this AttachEtyCSVO.
     * 
     * @return fileSize
     */
    public java.lang.Integer getFileSize() {
        return fileSize;
    }


    /**
     * Sets the fileSize value for this AttachEtyCSVO.
     * 
     * @param fileSize
     */
    public void setFileSize(java.lang.Integer fileSize) {
        this.fileSize = fileSize;
    }


    /**
     * Gets the localFileName value for this AttachEtyCSVO.
     * 
     * @return localFileName
     */
    public java.lang.String getLocalFileName() {
        return localFileName;
    }


    /**
     * Sets the localFileName value for this AttachEtyCSVO.
     * 
     * @param localFileName
     */
    public void setLocalFileName(java.lang.String localFileName) {
        this.localFileName = localFileName;
    }


    /**
     * Gets the localPath value for this AttachEtyCSVO.
     * 
     * @return localPath
     */
    public java.lang.String getLocalPath() {
        return localPath;
    }


    /**
     * Sets the localPath value for this AttachEtyCSVO.
     * 
     * @param localPath
     */
    public void setLocalPath(java.lang.String localPath) {
        this.localPath = localPath;
    }


    /**
     * Gets the fileServer value for this AttachEtyCSVO.
     * 
     * @return fileServer
     */
    public java.lang.String getFileServer() {
        return fileServer;
    }


    /**
     * Sets the fileServer value for this AttachEtyCSVO.
     * 
     * @param fileServer
     */
    public void setFileServer(java.lang.String fileServer) {
        this.fileServer = fileServer;
    }


    /**
     * Gets the msgKeyID value for this AttachEtyCSVO.
     * 
     * @return msgKeyID
     */
    public java.lang.String getMsgKeyID() {
        return msgKeyID;
    }


    /**
     * Sets the msgKeyID value for this AttachEtyCSVO.
     * 
     * @param msgKeyID
     */
    public void setMsgKeyID(java.lang.String msgKeyID) {
        this.msgKeyID = msgKeyID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AttachEtyCSVO)) return false;
        AttachEtyCSVO other = (AttachEtyCSVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.file==null && other.getFile()==null) || 
             (this.file!=null &&
              this.file.equals(other.getFile()))) &&
            ((this.iSeqID==null && other.getISeqID()==null) || 
             (this.iSeqID!=null &&
              this.iSeqID.equals(other.getISeqID()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.fileSize==null && other.getFileSize()==null) || 
             (this.fileSize!=null &&
              this.fileSize.equals(other.getFileSize()))) &&
            ((this.localFileName==null && other.getLocalFileName()==null) || 
             (this.localFileName!=null &&
              this.localFileName.equals(other.getLocalFileName()))) &&
            ((this.localPath==null && other.getLocalPath()==null) || 
             (this.localPath!=null &&
              this.localPath.equals(other.getLocalPath()))) &&
            ((this.fileServer==null && other.getFileServer()==null) || 
             (this.fileServer!=null &&
              this.fileServer.equals(other.getFileServer()))) &&
            ((this.msgKeyID==null && other.getMsgKeyID()==null) || 
             (this.msgKeyID!=null &&
              this.msgKeyID.equals(other.getMsgKeyID())));
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
        if (getFile() != null) {
            _hashCode += getFile().hashCode();
        }
        if (getISeqID() != null) {
            _hashCode += getISeqID().hashCode();
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getFileSize() != null) {
            _hashCode += getFileSize().hashCode();
        }
        if (getLocalFileName() != null) {
            _hashCode += getLocalFileName().hashCode();
        }
        if (getLocalPath() != null) {
            _hashCode += getLocalPath().hashCode();
        }
        if (getFileServer() != null) {
            _hashCode += getFileServer().hashCode();
        }
        if (getMsgKeyID() != null) {
            _hashCode += getMsgKeyID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AttachEtyCSVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://samsung/esb/mail/vo", "AttachEtyCSVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file");
        elemField.setXmlName(new javax.xml.namespace.QName("", "file"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ISeqID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iSeqID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localPath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileServer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgKeyID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msgKeyID"));
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
