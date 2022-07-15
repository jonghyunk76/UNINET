package com.yni.fta.mm.menu;

import java.util.List;
import java.util.Map;

public interface LeftMenu {
	
	/**
	 * 메인화면의 메뉴 리스트 조회
	 * 
	 * @param selectedLanguage 
	 * @param user 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Items<MenuItem> getMenuItems(String selectedLanguage, String user, String company) throws Exception; 
	
	/**
	 * 메뉴 기준으로 실적 데이터를 인터페이스하는 방식 조회
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Map getMenuInterfaceInfo(Map params) throws Exception;
	
	/**
	 * 메뉴에 대한 사용권한 조회
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Map selectMenuAuthInfo(Map params) throws Exception;
	
}
