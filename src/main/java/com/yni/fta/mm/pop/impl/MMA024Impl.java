package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA024;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 제품군 조회
 * 
 * @author YNI-maker
 *
 */
@Service("mmA024")
public class MMA024Impl extends YniAbstractService implements MMA024 {
	
	@Resource(name="mmA024Dao")
	private MMA024Dao mmA024Dao;
	
	public List selectMainList(Map map) throws Exception {
		return mmA024Dao.selectMainList(map);
	}
	
}
