/**
 * EmpService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mySingle.service;

public interface EmpService extends javax.xml.rpc.Service {
    public java.lang.String getEmpService_InboundPortAddress();

    public OutldapFindEmpLib.UserService.UserService getEmpService_InboundPort() throws javax.xml.rpc.ServiceException;

    public OutldapFindEmpLib.UserService.UserService getEmpService_InboundPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
