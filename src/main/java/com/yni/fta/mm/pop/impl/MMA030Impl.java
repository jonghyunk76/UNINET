package com.yni.fta.mm.pop.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA030;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 가입신청서 작성
 * 
 * @author YNI-maker
 *
 */
@Service("mma030")
public class MMA030Impl extends YniAbstractService implements MMA030 {
	
	@Resource(name="mma030Dao")
	private MMA030Dao mma030Dao;
	
	/**
	 * 신규가입정보 등록여부 체크
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectJoinCompanyNo(Map map) throws Exception {
		return mma030Dao.selectJoinCompanyNo(map);
	}
	
	/**
	 * 신규가입정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectJoinCompanyInfo(Map map) throws Exception {
		return mma030Dao.selectJoinCompanyInfo(map);
	}
	
	/**
	 * 신규가입정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertJoinCompanyInfo(Map map) throws Exception {
		return mma030Dao.insertJoinCompanyInfo(map);
	}
	
	/**
	 * 신규가입정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateJoinCompanyInfo(Map map) throws Exception {
		return mma030Dao.updateJoinCompanyInfo(map);
	}
	
}
