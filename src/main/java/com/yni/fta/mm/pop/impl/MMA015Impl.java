package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA015;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > HS Code 조회
 * 
 * @author YNI-maker
 *
 */
@Service("mmA015")
public class MMA015Impl extends YniAbstractService implements MMA015 {
	
	@Resource(name="mmA015Dao")
	private MMA015Dao mmA015Dao;

	public List selectMainList(Map map) throws Exception {
		return mmA015Dao.selectMainList(map);
	}
	
	public List selectKcsStandardCodeList(Map map) throws Exception {
		return mmA015Dao.selectKcsStandardCodeList(map);
	}
	
}
