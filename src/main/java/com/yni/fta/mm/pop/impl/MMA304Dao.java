package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 베트남 원산지 서류 발급
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA304Dao")
public class MMA304Dao extends YniAbstractDAO {

	/**
	 * 원산지 증명서 마스터 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectInvCoMasterInfo(Map map) throws Exception {
		return (Map) this.selectByPk("MMA304.selectInvCoMasterInfo", map);
	}
	
	/**
	 * 원산지 신고품목 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvPackingList(Map map) throws Exception {
		return this.listWithRowPaging("MMA304.selectInvPackingList", map);
	}
	
}
