package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.fm.sm.SM7010;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 시스템 관리 > 사용자 권한관리
 * 
 * @author YNI-maker
 *
 */
@Service("sm7010")
public class SM7010Impl extends YniAbstractService implements SM7010 {
	
	@Resource(name="sm7010Dao")
	private SM7010Dao sm7010Dao;

	/**
	 * 권한그룹 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectAuthGroupList(Map map) throws Exception {
		return sm7010Dao.selectAuthGroupList(map);
	}

	/**
	 * 권한그룹 상세내역 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectAuthGroupInfo(Map map) throws Exception {
		return sm7010Dao.selectAuthGroupInfo(map);
	}

	/**
	 * 권한그룹 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertAuthGroupInfo(Map map) throws Exception {
		String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
		
	    Map rtMap = sm7010Dao.selectAuthGroupDupCheck(map);
        
        if(rtMap != null) {
            int nDupCnt = StringHelper.null2zero(rtMap.get("DUPLICATE"));
            if(nDupCnt > 0) {
                throw new Exception(getMessage("MSG_IS_DUPLICATED", null, lang)); /*중복된 값을 가지고 있습니다. 확인하시고 다른 값을 넣으세요.*/
            }
        }
        
		return sm7010Dao.insertAuthGroupInfo(map);
	}

	/**
	 * 권한그룹 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateAuthGroupInfo(Map map) throws Exception {
		return sm7010Dao.updateAuthGroupInfo(map);
	}
	
	/**
	 * 권한그룹 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthGroupInfo(Map map) throws Exception {
		return sm7010Dao.deleteAuthGroupInfo(map);
	}
	
	/**
	 * 권한그룹 사용자 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectAuthUserList(Map map) throws Exception {
		return sm7010Dao.selectAuthUserList(map);
	}

	/**
	 * 권한그룹 사용자 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertAuthUserInfo(Map map) throws Exception {
		String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
		
	    Map rtMap = sm7010Dao.selectAuthUserDupCheck(map);
        
        if(rtMap != null) {
            int nDupCnt = StringHelper.null2zero(rtMap.get("DUPLICATE"));
            if(nDupCnt > 0) {
                throw new Exception(getMessage("MSG_IS_DUPLICATED", null, lang)); /*중복된 값을 가지고 있습니다. 확인하시고 다른 값을 넣으세요.*/
            }
        }
        
		return sm7010Dao.insertAuthUserInfo(map);
	}

	/**
	 * 권한그룹 사용자 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthUserInfo(Map map) throws Exception {
		return sm7010Dao.deleteAuthUserInfo(map);
	}
	
	/**
	 * 권한그룹 미등록 메뉴 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMenuList(Map map) throws Exception {
		return sm7010Dao.selectMenuList(map);
	}
	
	/**
	 * 권한그룹 메뉴 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectAuthMenuList(Map map) throws Exception {
		return sm7010Dao.selectAuthMenuList(map);
	}
	
	/**
	 * 권한그룹 메뉴 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertAuthMenuInfo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
			
			pmap.put("COMPANY_CD", map.get("COMPANY_CD"));
			pmap.put("AUTH_GROUP_ID", map.get("AUTH_GROUP_ID"));
			pmap.put("SEL_AUTH", "Y");
			pmap.put("REG_AUTH", "Y");
			pmap.put("UPD_AUTH", "Y");
			pmap.put("DEL_AUTH", "Y");
			pmap.put("EXC_AUTH", "Y");
			pmap.put("FLE_AUTH", "Y");
		}
		
		return sm7010Dao.insertAuthMenuInfo(list, map);
	}

	/**
	 * 권한그룹 메뉴 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthMenuInfo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
			
			pmap.put("COMPANY_CD", map.get("COMPANY_CD"));
			pmap.put("AUTH_GROUP_ID", map.get("AUTH_GROUP_ID"));			
		}
		
		return sm7010Dao.deleteAuthMenuInfo(list, map);
	}
	
	/**
	 * 부여된 메뉴 목록 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateAuthMenuInfo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		
		return sm7010Dao.updateAuthMenuInfo(list, map);
	}	
}
