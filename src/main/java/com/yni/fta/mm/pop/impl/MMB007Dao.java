package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 란입력 DAO 클래스
 * 
 * @author YNI-maker
 *
 */
@Repository("MMB007Dao")
public class MMB007Dao extends YniAbstractDAO {
	
	/**
	 * 수입 란입력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImportLneInputList(Map map) throws Exception {
		return this.list("MMB007.selectImportLneInputList", map);
	}
	
	/**
	 * 수입 란입력 협력사 품목등록 이력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImportPrdnmHistList(Map map) throws Exception {
		return this.list("MMB007.selectImportPrdnmHistList", map);
	}
	
	/**
	 * 수입 란입력 정보 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportLneInputInfo(List list, Map map) throws Exception {
		return this.executeBatch("MMB007.updateImportLneInputInfo", list, map);
	}
	
	/**
	 * 수입 란입력 > 거래품명 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportLneDealName(Map map) throws Exception {
		return this.update("MMB007.updateImportLneDealName", map);
	}
	
	/**
	 * 수입 란입력 > 순중량 안분 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportLneDealNetwght(Map map) throws Exception {
		return this.update("MMB007.updateImportLneDealNetwght", map);
	}
	
	/**
	 * 수출 란입력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectExportLneInputList(Map map) throws Exception {
		return this.list("MMB007.selectExportLneInputList", map);
	}
	
	/**
	 * 수출 란입력 협력사 품목등록 이력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectExportPrdnmHistList(Map map) throws Exception {
		return this.list("MMB007.selectExportPrdnmHistList", map);
	}
	
	/**
	 * 수출 란입력 정보 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateExportLneInputInfo(List list, Map map) throws Exception {
		return this.executeBatch("MMB007.updateExportLneInputInfo", list, map);
	}
	
	/**
	 * 수출 란입력 > 거래품명 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateExportLneDealName(Map map) throws Exception {
		return this.update("MMB007.updateExportLneDealName", map);
	}
	
	/**
	 * 수출 란입력 > 순중량 안분 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateExportLneDealNetwght(Map map) throws Exception {
		return this.update("MMB007.updateExportLneDealNetwght", map);
	}
	
}
