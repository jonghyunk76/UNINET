package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA304;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 베트남 원산지 서류 발급
 * 
 * @author YNI-maker
 *
 */
@Service("mmA304")
public class MMA304Impl extends YniAbstractService implements MMA304 {
	
	@Resource(name="mmA304Dao")
	private MMA304Dao mmA304Dao;
	
	/**
	 * 원산지 증명서 마스터 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectInvCoMasterInfo(Map map) throws Exception {
		return mmA304Dao.selectInvCoMasterInfo(map);
	}
	
	/**
	 * 원산지 신고품목 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvPackingList(Map map) throws Exception {
		return mmA304Dao.selectInvPackingList(map);
	}
	
}
