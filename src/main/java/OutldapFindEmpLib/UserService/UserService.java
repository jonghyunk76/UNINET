/**
 * UserService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package OutldapFindEmpLib.UserService;

public interface UserService extends java.rmi.Remote {
    public model.outldap.samsung.net.Employee[] findByFullName(java.lang.String cid, java.lang.String fullname, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompDepartmentNumber(java.lang.String cid, java.lang.String compCode, java.lang.String departmentnumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompEmployeeNumber(java.lang.String cid, java.lang.String compCode, java.lang.String employeenumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompUid(java.lang.String cid, java.lang.String compCode, java.lang.String uid, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompName(java.lang.String cid, java.lang.String compCode, java.lang.String name, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByDepartmentNumber(java.lang.String cid, java.lang.String departmentnumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByEmployeeNumber(java.lang.String cid, java.lang.String employeenumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByEpId(java.lang.String cid, java.lang.String epid, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompEpId(java.lang.String cid, java.lang.String compCode, java.lang.String epid, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByRegisterNumber(java.lang.String cid, java.lang.String epregisternumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByUid(java.lang.String cid, java.lang.String uid, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompRegisterNumber(java.lang.String cid, java.lang.String compCode, java.lang.String epregisternumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompMail(java.lang.String cid, java.lang.String compCode, java.lang.String mail, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompFullName(java.lang.String cid, java.lang.String compCode, java.lang.String fullname, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByName(java.lang.String cid, java.lang.String name, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByMail(java.lang.String cid, java.lang.String mail, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByRegisterNumberList(java.lang.String cid, java.lang.String[] epregisternumberList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByEpIdList(java.lang.String cid, java.lang.String[] keyList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByMailList(java.lang.String cid, java.lang.String[] mailList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByUidList(java.lang.String cid, java.lang.String[] uidList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompRegisterNumberList(java.lang.String cid, java.lang.String compCode, java.lang.String[] epregisternumberList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompEpIdList(java.lang.String cid, java.lang.String compCode, java.lang.String[] epidList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompUidList(java.lang.String cid, java.lang.String compCode, java.lang.String[] uidList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompMailList(java.lang.String cid, java.lang.String compCode, java.lang.String[] mailList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompEptitlenumber(java.lang.String cid, java.lang.String compCode, java.lang.String eptitlenumber, java.lang.String page, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByCompDepartmentNumberWithPaging(java.lang.String cid, java.lang.String compCode, java.lang.String departmentnumber, java.lang.String page, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByOriginCompEmployeeNumberList(java.lang.String cid, java.lang.String compCode, java.lang.String[] empNumberList, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Employee[] findByOriginCompEmployeeNumber(java.lang.String cid, java.lang.String compCode, java.lang.String empNumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public boolean isDuplicatedEmployeeNumber(java.lang.String cid, java.lang.String compCode, java.lang.String empNumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
}
