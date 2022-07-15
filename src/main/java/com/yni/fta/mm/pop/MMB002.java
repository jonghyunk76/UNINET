package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 *공통 > 통관 공통팝업 인터페이스
 * 
 * @author YNI-maker
 *
 */
public interface MMB002 {

	/**
	 * 거래처 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectClientList(Map map) throws Exception;
	
	
	/**
	 * 표준 품명 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectStardProductNameList(Map map) throws Exception;

	/**
	 * 장치장 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectShedList(Map map) throws Exception;
	
	/**
	 * 브랜드 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectCommonBrandList(Map map) throws Exception;
	
	/**
	 * 브랜드 일괄 등록/수정/삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateCommonBrandInfo(Map map) throws Exception;
	
	/**
	 * 브랜드 일괄 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteCommonBrandInfo(Map map) throws Exception;
	
    /**
     * 세관, 항구, 공항... 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public List selectKcsCodeList(Map map) throws Exception; 
	
    /**
     * 과 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public List selectKwaList(Map map) throws Exception; 	
	
	/**
	 * 세관 과 연계 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertCsmhKwaMapping(Map map) throws Exception;

	/**
	 * 세관 과 연계 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteCsmhKwaMapping(Map map) throws Exception;	
	
    /**
     * 세관 과 제외 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public List selectCsmhKwaExcludeList(Map map) throws Exception;
	
    /**
     * 화물운송주선업자 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public List selectFreightTransportAgencyList(Map map) throws Exception;	
	
    /**
     * 관세사 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public List selectCustomsBrokerAgentList(Map map) throws Exception;
	
    /**
     * 해외거래처 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public List selectOverBcncList(Map map) throws Exception;	
	
    /**
     * 통관 첨부파일 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public List selectEntryFileList(Map map) throws Exception;
	
	/**
	 * 통관 첨부파일 상세
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectEntryFileInfo(Map map) throws Exception;		
	
    /**
     * 통관 첨부파일 리스트 조회(첨부 전체 다운로드)
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	public List selectEntryFileAllList(Map map) throws Exception;	
	
	/**
	 * 통관 첨부파일 마스터 등록/상세 등록,수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map mergeEntryFile(Map map) throws Exception;	
	
	/**
	 * 통관 첨부파일 상세 삭제(Info)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteEntryFileDetailInfo(Map map) throws Exception;	
	
	/**
     * 표준품명 세번코드 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List<?> selectStardHscodeList(Map map) throws Exception;
    
    /**
     * 표준품명코드 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List<?> selectStardProductcodeList(Map map) throws Exception;
    
    /**
     * 해외 원산지 발행기관 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List<?> selectOverseaIssueOrganList(Map map) throws Exception;
    
	/**
	 * 대행사 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectAgencyList(Map map) throws Exception;    
	
	/**
	 * 운수기관 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectTratInsttList(Map map) throws Exception;  	
	
	/**
	 * 우편번호별 관할세관 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectZipCmpcCsmhList(Map map) throws Exception;  	
    
	/**
	 * 법령부호별 서류명 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	List selectLwodPapeKndList(Map map) throws Exception;
	
	/**
	 * selectExpBcncSetupList
	 * 기본값일괄수정 수입 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectImpBcncSetupList(Map map) throws Exception;
	
	/**
	 * selectExpBcncSetupList
	 * 기본값일괄수정 수출 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectExpBcncSetupList(Map map) throws Exception;
	
	/**
	 * updateBcncSetupBatchModify
	 * 기본값일괄 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateBcncSetupBatchModify(Map map) throws Exception;		
	
}
