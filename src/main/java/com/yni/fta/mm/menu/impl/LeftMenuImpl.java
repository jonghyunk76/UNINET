package com.yni.fta.mm.menu.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yni.fta.mm.menu.Items;
import com.yni.fta.mm.menu.LeftMenu;
import com.yni.fta.mm.menu.MenuItem;

import kr.yni.frame.service.YniAbstractService;

/**
 * 사용자 인증을 위한 기능 구현
 * 
 * @author YNI-maker
 *
 */
@Service("leftMenu")
public class LeftMenuImpl extends YniAbstractService implements LeftMenu {
	
	private Log logger = LogFactory.getLog(LeftMenuImpl.class); 
	
	@Resource(name="leftMenuDao")
	private LeftMenuDao leftMenuDao;

	public Items<MenuItem> getMenuItems(String selectedLanguage,String user, String company) throws Exception {
		HashMap<String,Object> params = new HashMap<String, Object>();
		
		params.put("selectedLanguage", selectedLanguage.toUpperCase());
		params.put("user", user);
		params.put("company", company);
		
		return leftMenuDao.getMenuItems(params);
	}
	
	public Map getMenuInterfaceInfo(Map params) throws Exception {
		return leftMenuDao.getMenuInterfaceInfo(params);
	}
	
	public Map selectMenuAuthInfo(Map params) throws Exception {
		return leftMenuDao.selectMenuAuthInfo(params);
	}
	
}
