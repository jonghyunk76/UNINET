package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA009;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > [MX] 품목분류 관리
 * 
 * @author YNI-maker
 *
 */
@Service("mmA009")
public class MMA009Impl extends YniAbstractService implements MMA009 {
	
	@Resource(name="mmA009Dao")
		private MMA009Dao mmA009Dao;

	public List selectMainList(Map map) throws Exception {
		return mmA009Dao.selectMainList(map);
	}
	
}
