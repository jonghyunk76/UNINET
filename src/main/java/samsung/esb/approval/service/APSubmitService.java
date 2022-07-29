/**
 * APSubmitService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.service;

public interface APSubmitService extends java.rmi.Remote {
    public java.lang.String submitApproval(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.StartProcessWSVO startProcessWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
}
