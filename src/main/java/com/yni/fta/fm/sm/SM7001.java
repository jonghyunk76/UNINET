package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 시스템관리 > 회사정보설정   
 * 
 * @author carlos
 *
 */
public interface SM7001 {
	
    /**
     * 회사정보 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectCompanyList(Map map) throws Exception;
    
    /**
     * 회사정보 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map selectCompanyDupCheck(Map map) throws Exception;
    
    /**
     * 회사 CI 및 직인 파일 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map selectStempCIImg(Map map) throws Exception;
    
    /**
     * 회사정보 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map selectCompanyInfo(Map map) throws Exception;
    
    /**
     * 회사 등록
     * @param map
     * @return
     * @throws Exception
     */
    int insertCompanyInfo(Map map) throws Exception;

    /**
     * 회사정보 수정
     * @param map
     * @return
     * @throws Exception
     */
    int updateCompanyInfo(Map map) throws Exception;
    
    /**
     * 회사정보 삭제
     * @param map
     * @return
     * @throws Exception
     */
    int deleteCompanyInfo(Map map) throws Exception;  
    
    /**
     * 회사정보 그룹정보 수정
     * @param map
     * @return
     * @throws Exception
     */
    int updateCompnayGroupCd(Map map) throws Exception;
    
    /**
     * 회사정보 그룹 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectCompnayGroupList(Map map) throws Exception;
    
    /**
     * FTA 시스템 옵션 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectSysconfigOptionInfo(Map map) throws Exception;
    
    /**
     * FTA 시스템 옵션 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateSysconfigOptionInfo(Map map) throws Exception;

    /**
     * FTA 인증ID 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int updateCompanyCertIDInfo(Map map) throws Exception;
	
	/**
     * C/O 발급 고객사 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
	List selectHubCertCustomerList(Map map) throws Exception;
	
	/**
     * 거래 고객사 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
	List selectHubSalesCustomerList(Map map) throws Exception;
	
	/**
     * 서명권자 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
	List selectHubSignatureList(Map map) throws Exception;
    
    /**
     * C/O 발급 고객사 관리
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateHubCertCustomerInfo(Map map) throws Exception;
    
    /**
     * C/O 발급 고객사 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public int deleteHubCertCustomerInfo(Map map) throws Exception;
	
	/**
     * 거래 고객사 관리
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int updateHubSalesCustomerInfo(Map map) throws Exception;
	
	/**
     * 거래 고객사 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int deleteHubSalesCustomerInfo(Map map) throws Exception;

	/**
     * 서명권자 관리
     * 
     * @param map
     * @return
     * @throws Exception
     */	
	public int updateHubSignatureInfo(Map map) throws Exception;

	/**
     * 서명권자 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */	
	public int deleteHubSignatureInfo(Map map) throws Exception;

	/**
     * 인증 ID 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */	
	public Map selectHubCertID(Map map) throws Exception;
    
}
