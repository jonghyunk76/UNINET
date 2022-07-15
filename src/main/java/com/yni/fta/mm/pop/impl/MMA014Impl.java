package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA014;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > FTA 옵션등록
 * 
 * @author YNI-maker
 *
 */
@Service("mmA014")
public class MMA014Impl extends YniAbstractService implements MMA014 {
	
	@Resource(name="mmA014Dao")
		private MMA014Dao mmA014Dao;

	public List selectMainList(Map map) throws Exception {
		return mmA014Dao.selectMainList(map);
	}
	
}
