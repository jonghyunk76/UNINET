package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMB003;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 가격신고서 구현 클래스
 * 
 * @author YNI-maker
 *
 */
@Service("MMB003")
public class MMB003Impl extends YniAbstractService implements MMB003 {
	
	@Resource(name="MMB003Dao")
	private MMB003Dao MMB003Dao;
	
	/**
	 * 가격신고서 초기 작성시 보여줄 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectEmptyImpPrnoInfo(Map map) throws Exception {
		return MMB003Dao.selectEmptyImpPrnoInfo(map);
	}
	
	/**
	 * 가격신고서 등록된 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectRegistImpPrnoInfo(Map map) throws Exception {
		return MMB003Dao.selectRegistImpPrnoInfo(map);
	}
	
	/**
	 * 가격신고서 정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String insertImpPrnoInfo(Map map) throws Exception {
		return MMB003Dao.insertImpPrnoInfo(map);
	}
	
	/**
	 * 가격신고서 정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImpPrnoInfo(Map map) throws Exception {
		return MMB003Dao.updateImpPrnoInfo(map);
	}
	
	/**
	 * 가격신고서 정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteImpPrnoInfo(Map map) throws Exception {
		return MMB003Dao.deleteImpPrnoInfo(map);
	}
	
}
