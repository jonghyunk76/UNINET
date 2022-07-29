/**
 * OrganizationService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package OutldapFindOrgLib.OrganizationService;

public interface OrganizationService extends java.rmi.Remote {
    public model.outldap.samsung.net.Organization[] findByDepartmentName(java.lang.String cid, java.lang.String compCode, java.lang.String departmentName, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Organization[] findByDepartmentNumber(java.lang.String cid, java.lang.String compCode, java.lang.String departmentnumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Organization[] findByEpOrganizationNumber(java.lang.String cid, java.lang.String eporganizationnumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Organization[] findByEpHighDeptNumber(java.lang.String cid, java.lang.String compCode, java.lang.String ephighdeptnumber, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Title[] findTitleByCompCode(java.lang.String cid, java.lang.String compcode, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
    public model.outldap.samsung.net.Organization[] findByEpOrganizationNumberWithPaging(java.lang.String cid, java.lang.String eporganizationnumber, java.lang.String page, java.lang.String cpw) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO;
}
