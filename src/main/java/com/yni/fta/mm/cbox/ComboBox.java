package com.yni.fta.mm.cbox;

import java.util.List;
import java.util.Map;

/**
 * 콤보박스 데이터를 획득을 위한 기능 선언
 * 
 * @author YNI-maker
 *
 */
public interface ComboBox {

	List selectCompany(Map map) throws Exception;
	
	List selectFTAMgmtSysCompany(Map map) throws Exception;
	
	List selectSupplierSysCompany(Map map) throws Exception;
	
	List selectDivision(Map map) throws Exception;
	
	List selectStandardCode(Map map) throws Exception;
	
	List selectProductLine(Map map) throws Exception;
	
	List selectFta(Map map) throws Exception;
	
	List selectSignature(Map map) throws Exception;
	
	List selectCustomerSalesType(Map map) throws Exception;
	
	List selectNaladisaVersion(Map map) throws Exception;
	
	List selectTraceType(Map map) throws Exception;
	
	List selectSystemType(Map map) throws Exception;
	
	List selectHighMenu(Map map) throws Exception;
	
	List selectInvestigate(Map map) throws Exception;
	
	List selectInvestigateCoNo(Map map) throws Exception;
	
	List selectInvestigateProduct(Map map) throws Exception;
	
    List selectHubCertCustomer(Map map) throws Exception;
    
    List selectHubSignature(Map map) throws Exception;
    
    List selectFtaNation(Map map) throws Exception;
    
    /**
	 * FTA 국가목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectFtaNationByCode(Map map) throws Exception;
	
    List selectReceiveCoItem(Map map) throws Exception;
    
    List selectKcsStandardCode(Map map) throws Exception;
    
    List selectForeignCustomer(Map map) throws Exception;
    
    List selectForeignVendor(Map map) throws Exception;
	
    List selectGridColumnSetList(Map map) throws Exception;
    
    List selectDatagridInfo(Map map) throws Exception;
    
    int deleteUserDatagridInfo(Map map) throws Exception;
    
    int insertUserDatagridInfo(Map map) throws Exception;
    
    /**
	 * 그리드 목록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectDatagridTableList(Map map) throws Exception;
	
	/**
	 * 그리드 필드 목록(실제 데이터를 표시하는 컬럼만 조회)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectDatagridColumnList(Map map) throws Exception;
	
	/**
	 * 통관 차수 콤보 데이터
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectComboSttOdr(Map map) throws Exception;
	
    /**
     * 사용자 조회
     */
    List selectFtaUserList(Map map) throws Exception;
    
    /**
     * 사이트 담당자 조회
     */
    List selectSiteUserList(Map map) throws Exception;
	
}
