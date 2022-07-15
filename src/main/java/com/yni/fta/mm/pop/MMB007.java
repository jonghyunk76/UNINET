package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

import kr.yni.frame.Constants;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 *공통 > 란입력 인터페이스
 * 
 * @author YNI-maker
 *
 */
public interface MMB007 {

	/**
	 * 수입 란입력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectImportLneInputList(Map map) throws Exception;
	
	/**
	 * 수입 란입력 협력사 품목등록 이력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectImportPrdnmHistList(Map map) throws Exception;
	
	/**
	 * 수입 란입력 정보 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateImportLneInputInfo(Map map) throws Exception;
	
	/**
	 * 수입 란입력 > 거래품명 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateImportLneDealName(Map map) throws Exception;
	
	/**
	 * 수입 란입력 > 순중량 안분 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateImportLneDealNetwght(Map map) throws Exception;
	
	/**
	 * 수출 란입력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectExportLneInputList(Map map) throws Exception;
	
	/**
	 * 수출 란입력 협력사 품목등록 이력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectExportPrdnmHistList(Map map) throws Exception;
	
	/**
	 * 수출 란입력 정보 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateExportLneInputInfo(Map map) throws Exception;
	
	/**
	 * 수출 란입력 > 거래품명 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateExportLneDealName(Map map) throws Exception;
	
	/**
	 * 수출 란입력 > 순중량 안분 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateExportLneDealNetwght(Map map) throws Exception;
}
