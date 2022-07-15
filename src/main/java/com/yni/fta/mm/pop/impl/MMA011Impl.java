package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA011;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Service("mmA011")
public class MMA011Impl extends YniAbstractService implements MMA011 {
	
	@Resource(name="mmA011Dao")
		private MMA011Dao mmA011Dao;
	
	public List selectMainList(Map map) throws Exception {
		return mmA011Dao.selectMainList(map);
	}
	
}
