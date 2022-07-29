/**
 * APStatusInquiryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package samsung.esb.approval.service;

public interface APStatusInquiryService extends java.rmi.Remote {
    public samsung.esb.approval.vo.ProcessStatusWSVO getStatusByMisId(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO misKeyWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public samsung.esb.approval.vo.ProcessBriefStatusWSVO[] getStatusByBulkMisId(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO[] misKeyWSVOs) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public samsung.esb.approval.vo.ProcessListWSVO[] getListByUserKey(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.UserKeyWSVO userKeyWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public samsung.esb.approval.vo.ProcessRevisionWSVO[] getProcessIdByMisId(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO[] misKeyWSVOs) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public samsung.esb.approval.vo.ProcessRevisionWSVO[] getRevisionByMisId(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO[] misKeyWSVOs) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public samsung.esb.approval.vo.MisKeyWSVO[] getCallbackFailKey(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.ProcessCallbackFailWSVO processCallbackFailWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public samsung.esb.approval.vo.RouteDelegateWSVO getOriginUserID(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO misKeyWSVO, samsung.esb.approval.vo.RouteDelegateWSVO routeDelegateWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
}
