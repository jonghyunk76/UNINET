package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 가격신고서 DAO 클래스
 * 
 * @author YNI-maker
 *
 */
@Repository("MMB003Dao")
public class MMB003Dao extends YniAbstractDAO {

	/**
	 * 가격신고서 초기 작성시 보여줄 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectEmptyImpPrnoInfo(Map map) throws Exception {
		return (Map) this.selectByPk("MMB003.selectEmptyImpPrnoInfo", map);
	}
	
	/**
	 * 가격신고서 등록된 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectRegistImpPrnoInfo(Map map) throws Exception {
		return (Map) this.selectByPk("MMB003.selectRegistImpPrnoInfo", map);
	}
	
	/**
	 * 가격신고서 정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String insertImpPrnoInfo(Map map) throws Exception {
		return (String) this.insert("MMB003.insertImpPrnoInfo", map);
	}
	
	/**
	 * 가격신고서 정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImpPrnoInfo(Map map) throws Exception {
		return this.update("MMB003.updateImpPrnoInfo", map);
	}
	
	/**
	 * 가격신고서 정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteImpPrnoInfo(Map map) throws Exception {
		return this.delete("MMB003.deleteImpPrnoInfo", map);
	}
	
}
