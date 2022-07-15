package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 가입신청서 작성
 * 
 * @author YNI-maker
 *
 */
@Repository("mma030Dao")
public class MMA030Dao extends YniAbstractDAO {

	/**
	 * 신규가입정보 등록여부 체크
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectJoinCompanyNo(Map map) throws Exception {
		return (Map) this.selectByPk("MMA030.selectJoinCompanyNo", map);
	}
	
	/**
	 * 신규가입정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectJoinCompanyInfo(Map map) throws Exception {
		return (Map) this.selectByPk("MMA030.selectJoinCompanyInfo", map);
	}
	
	/**
	 * 신규가입정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertJoinCompanyInfo(Map map) throws Exception {
		return this.update("MMA030.insertJoinCompanyInfo", map);
	}
	
	/**
	 * 신규가입정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateJoinCompanyInfo(Map map) throws Exception {
		return this.update("MMA030.updateJoinCompanyInfo", map);
	}
	
}
