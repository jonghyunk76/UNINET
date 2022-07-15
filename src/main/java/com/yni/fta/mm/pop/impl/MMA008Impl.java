package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA008;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > [MX] Trace List 관리
 * 
 * @author YNI-maker
 *
 */
@Service("mmA008")
public class MMA008Impl extends YniAbstractService implements MMA008 {
	
	@Resource(name="mmA008Dao")
		private MMA008Dao mmA008Dao;

	public List selectMainList(Map map) throws Exception {
		return mmA008Dao.selectMainList(map);
	}
	
}
