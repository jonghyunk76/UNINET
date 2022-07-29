/**
 * MLSendService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.mail.service;

public interface MLSendService extends java.rmi.Remote {
    public java.lang.String sendMISMail(samsung.esb.common.vo.ESBAuthVO esbAuthVO, java.lang.String bodyStr, samsung.esb.mail.vo.HeaderHelperCSVO headerHelperCSVO, samsung.esb.mail.vo.RecipientEtyCSVO[] recipientEtyCSVO, samsung.esb.mail.vo.AttachEtyCSVO[] attachEtyCSVO, samsung.esb.mail.vo.ResourceCSVO resourceCSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
}
