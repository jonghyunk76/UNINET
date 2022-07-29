/**
 * MLCancelDeliveryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.mail.service;

public interface MLCancelDeliveryService extends java.rmi.Remote {
    public java.lang.String cancelMISMailByRecipient(samsung.esb.common.vo.ESBAuthVO esbAuthVO, java.lang.String mtrKey, java.lang.String[] addressForCancel, samsung.esb.mail.vo.ResourceCSVO resourceCSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
}
