package com.yni.fta.mm.smp;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
public interface SMP1002 {
	
	/**
	 * 시스템 카테고리 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectSysCategoryList(Map map) throws Exception;
	
	/**
	 * 시스템 카테고리 상세 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectSysCategoryDetail(Map map) throws Exception;
	
	/**
	 * 시스템 카테고리 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertSysCategoryInfo(Map map) throws Exception;

	/**
	 * 시스템 카테고리 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateSysCategoryInfo(Map map) throws Exception;
	
	/**
	 * 시스템 카테고리 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteSysCategoryInfo(Map map) throws Exception;
	
	/**
	 * 시스템 코드 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectSysCodeList(Map map) throws Exception;
	
	/**
	 * 시스템 코드 상세 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectSysCodeDetail(Map map) throws Exception;
	
	/**
	 * 시스템 코드 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertSysCodeInfo(Map map) throws Exception;

	/**
	 * 시스템 코드 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateSysCodeInfo(Map map) throws Exception;
	
	/**
	 * 시스템 코드 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteSysCodeInfo(Map map) throws Exception;
	
	/**
	 * 시스템 코드 다국어 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectSysCodeLangList(Map map) throws Exception;
	
	/**
	 * 시스템 코드 다국어 상세 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectSysCodeLangDetail(Map map) throws Exception;
	
	/**
	 * 시스템 코드 다국어 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertSysCodeLangInfo(Map map) throws Exception;

	/**
	 * 시스템 코드 다국어 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateSysCodeLangInfo(Map map) throws Exception;
	
	/**
	 * 시스템 코드 다국어 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteSysCodeLangInfo(Map map) throws Exception;
	
}
