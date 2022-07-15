package com.yni.fta.mm.smp.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.smp.SMP1001;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Service("smp1001")
public class SMP1001Impl extends YniAbstractService implements SMP1001 {
	
	@Resource(name="smp1001Dao")
	private SMP1001Dao smp1001Dao;
	
	public List selectMainList(Map map) throws Exception {
		return smp1001Dao.selectMainList(map);
	}
	
}
