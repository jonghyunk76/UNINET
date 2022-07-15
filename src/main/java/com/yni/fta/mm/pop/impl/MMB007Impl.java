package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMB007;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 란입력 구현 클래스
 * 
 * @author YNI-maker
 *
 */
@Service("MMB007")
public class MMB007Impl extends YniAbstractService implements MMB007 {
	
	@Resource(name="MMB007Dao")
	private MMB007Dao MMB007Dao;
	
	/**
	 * 수입 란입력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImportLneInputList(Map map) throws Exception {
		return MMB007Dao.selectImportLneInputList(map);
	}
	
	/**
	 * 수입 란입력 협력사 품목등록 이력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImportPrdnmHistList(Map map) throws Exception {
		return MMB007Dao.selectImportPrdnmHistList(map);
	}
	
	/**
	 * 수입 란입력 정보 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportLneInputInfo(Map map) throws Exception {
		List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
    	
		return MMB007Dao.updateImportLneInputInfo(gridList, map);
	}
	
	/**
	 * 수입 란입력 > 거래품명 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportLneDealName(Map map) throws Exception {
		List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		
		MMB007Dao.updateImportLneInputInfo(gridList, map); // 변경된 정보 업데이트
		
		return MMB007Dao.updateImportLneDealName(map);
	}
	
	/**
	 * 수입 란입력 > 순중량 안분 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportLneDealNetwght(Map map) throws Exception {
		List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		
		MMB007Dao.updateImportLneInputInfo(gridList, map); // 변경된 정보 업데이트
		
		return MMB007Dao.updateImportLneDealNetwght(map);
	}
	
	/**
	 * 수출 란입력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectExportLneInputList(Map map) throws Exception {
		return MMB007Dao.selectExportLneInputList(map);
	}
	
	/**
	 * 수출 란입력 협력사 품목등록 이력 정보 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectExportPrdnmHistList(Map map) throws Exception {
		return MMB007Dao.selectExportPrdnmHistList(map);
	}
	
	/**
	 * 수출 란입력 정보 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateExportLneInputInfo(Map map) throws Exception {
		List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
    	
		return MMB007Dao.updateExportLneInputInfo(gridList, map);
	}
	
	/**
	 * 수출 란입력 > 거래품명 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateExportLneDealName(Map map) throws Exception {
		List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		
		MMB007Dao.updateExportLneInputInfo(gridList, map); // 변경된 정보 업데이트
		
		return MMB007Dao.updateExportLneDealName(map);
	}
	
	/**
	 * 수출 란입력 > 순중량 안분 일괄 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateExportLneDealNetwght(Map map) throws Exception {
		List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		
		MMB007Dao.updateExportLneInputInfo(gridList, map); // 변경된 정보 업데이트
		
		return MMB007Dao.updateExportLneDealNetwght(map);
	}
	
}
