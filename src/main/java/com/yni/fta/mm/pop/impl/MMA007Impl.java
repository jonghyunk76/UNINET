package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA007;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > [MX] NALADISA Code 조회
 * 
 * @author YNI-maker
 *
 */
@Service("mmA007")
public class MMA007Impl extends YniAbstractService implements MMA007 {
	
	@Resource(name="mmA007Dao")
		private MMA007Dao mmA007Dao;

	public List selectMainList(Map map) throws Exception {
		return mmA007Dao.selectMainList(map);
	}
	
}
