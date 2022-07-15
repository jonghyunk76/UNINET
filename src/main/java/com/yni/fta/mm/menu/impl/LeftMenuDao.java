package com.yni.fta.mm.menu.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yni.fta.mm.menu.ItemModel;
import com.yni.fta.mm.menu.Items;
import com.yni.fta.mm.menu.MenuItem;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 메뉴 불러오기
 * @author jake
 *
 */
@Repository("leftMenuDao")
public class LeftMenuDao extends YniAbstractDAO {
	
	/**
	 * 메인화면의 메뉴 리스트 조회
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Items<MenuItem> getMenuItems(HashMap params) throws Exception {
		List<MenuItem> list = (List<MenuItem>) this.list("leftMenu.getMenuList",params);
		MenuItem[] mi = Arrays.copyOf(list.toArray(), list.size(), MenuItem[].class);
		
		return new ItemModel<MenuItem>(mi);
	}
	
	/**
	 * 메뉴 기준으로 실적 데이터를 인터페이스하는 방식 조회
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map getMenuInterfaceInfo(Map params) throws Exception {
	    return (Map) this.selectByPk("leftMenu.getMenuInterfaceInfo", params);
	}
	
	/**
	 * 메뉴에 대한 사용권한 조회
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map selectMenuAuthInfo(Map params) throws Exception {
	    return (Map) this.selectByPk("leftMenu.selectMenuAuthInfo", params);
	}
	
}
