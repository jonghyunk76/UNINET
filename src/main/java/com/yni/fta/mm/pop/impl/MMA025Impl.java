package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA025;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > FTA협정선택 화면
 * 
 * @author YNI-maker
 *
 */
@Service("mmA025")
public class MMA025Impl extends YniAbstractService implements MMA025 {
	
	@Resource(name="mmA025Dao")
	private MMA025Dao mmA025Dao;
	
	public List selectMainList(Map map) throws Exception {
		return mmA025Dao.selectMainList(map);
	}
	
}
