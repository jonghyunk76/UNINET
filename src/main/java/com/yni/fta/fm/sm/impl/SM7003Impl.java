package com.yni.fta.fm.sm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.StringUtil;
import com.yni.fta.fm.sm.SM7003;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 시스템관리 > 사용자 등록
 * 
 * @author carlos
 *
 */
@Service("sm7003")
public class SM7003Impl extends YniAbstractService implements SM7003 {

    @Resource(name = "sm7003Dao")
    private SM7003Dao sm7003Dao;

    public List selectUserList(Map map) throws Exception {
        return sm7003Dao.selectUserList(map);
    }

    public Map selectUserDupCheck(Map map) throws Exception {
        return sm7003Dao.selectUserDupCheck(map);
    }

    public Map selectUserPwdCheck(Map map) throws Exception {
        return sm7003Dao.selectUserPwdCheck(map);
    }

    public Map selectUserInfo(Map map) throws Exception {
        return sm7003Dao.selectUserInfo(map);
    }
    
    /**
     * 사용자 인증 키 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectUserOpenApiKeyList(Map map) throws Exception {    
    	return sm7003Dao.selectUserOpenApiKeyList(map);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int insertUserInfo(Map map) throws Exception {

        Map dupInfo = sm7003Dao.selectUserDupCheck(map);

        if (dupInfo != null) {
            int nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            if (nDupCnt > 0) {
                // 이미 등록된 사용자입니다
                throw new RuntimeException(getMessage("MSG_EXISTS_USER_INFO"));
            }
        }

        String pwd = StringHelper.null2void(map.get("PASSWORD"));
        String pwdEnc = StringUtil.getSHA128(pwd);
        
        // 패스워드 함호화
        map.put("PASSWORD", pwdEnc);

        return sm7003Dao.insertUserInfo(map);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int updateUserInfo(Map map) throws Exception {
    	int rCnt = 0;
        // CASE 1.패스워드 들고 다니면서 변경되었을때만 변경하는 로직
        // CASE 2.패스워드가 넘어온 경우만 변경하는 로직
        // 현재는 2번
        
        // CASE 1.
        //String pwd = StringHelper.null2void(map.get("PASSWORD"));
        ////String pwdOrg = StringHelper.null2void(map.get("PASSWORD_ORG"));
        //map.put("PWD_ENC", pwd);
        //Map pwdInfo = sm7003Dao.selectUserPwdCheck(map);
        //String pwdValid = StringHelper.null2void(pwdInfo.get("PWD_VALID"));
        //if (!pwdValid.equals("T")) {
        //    // DB에 저장된 패스워드와 다르면 암호화 하여 저장
        //    String pwdEnc = StringUtil.getSHA128(pwd);
        //    map.put("PASSWORD", pwdEnc);
        //} else {
        //    map.remove("PASSWORD");
        //}
        
        // CASE 2.
        String pwd = StringHelper.null2void(map.get("PASSWORD"));
        //String pwdOrg = StringHelper.null2void(map.get("PASSWORD_ORG"));
        if (!pwd.equals("")) {
            // 패스워드가 있는경우
            String pwdEnc = StringUtil.getSHA128(pwd);
            //String pwdOrgEnc = StringUtil.getSHA128(pwdOrg);
            //map.put("#PWD_ENC", pwdOrgEnc);
            //Map pwdInfo = sm7003Dao.selectUserPwdCheck(map);
            //if (pwdInfo != null) {
            //    String pwdValid = StringHelper.null2void(pwdInfo.get("PWD_VALID"));
            //    if (!pwdValid.equals("T")) {
            //        // 기존 패스워드를 확인하세요.
            //        throw new RuntimeException(getMessage("BSIC, MSG_CHK_PWD"));
            //    }
            //}
            // 패스워드 함호화
            map.put("PASSWORD", pwdEnc);    
        } else {
            map.remove("PASSWORD");
        }
        
        rCnt = sm7003Dao.updateUserInfo(map);
        
        // 통관 사용자 OPENAPI 인증키 MERGE
        String topSysId = StringHelper.null2void(map.get("TOP_SYS_ID"));
        if (topSysId.equals("CC")) {
        	this.updateUserEntr(map);
        	this.updateUserOpenApiKey(map);
        	this.updateUserEntrBcnc(map);
        }
        
        return rCnt;
    }

    public int deleteUserInfo(Map map) throws Exception {
        return sm7003Dao.deleteUserInfo(map);
    }

    /**
     * 사용자 통관 조회
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserEntrInfo(Map map) throws Exception {
        return sm7003Dao.selectUserEntrInfo(map);
    }
    
    /**
     * 사용자 OPENAPI 인증키 MERGE
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserOpenApiKey(Map map) throws Exception {
		List gridData = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	
        if (gridData == null || gridData.size() <= 0) {
            // 처리할 데이터가 없습니다.
            // throw new RuntimeException(this.getMessage("MSG_NOT_EXIST_DATA", null, lang));
        }
        
		return sm7003Dao.updateUserOpenApiKey(gridData);    	
    }      
    
    /**
     * 사용자 통관 업데이트
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserEntr(Map map) throws Exception {
    	return sm7003Dao.updateUserEntr(map);
    }
    
    /**
     * selectUserEntrBcncList
     * 사용자 통관 거래처 List (USER_ENTR_BCNC)
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectUserEntrBcncList(Map map) throws Exception {    
    	return sm7003Dao.selectUserEntrBcncList(map);
    }
    
    /**
     * updateUserEntrBcnc
     * 사용자 통관 거래처 MERGE(USER_ENTR_BCNC)
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserEntrBcnc(Map map) throws Exception {
		List gridData = JsonUtil.getList(StringHelper.null2void(map.get("gridData1")));
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	
    	String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
    	String userId = StringHelper.null2void(map.get("USER_ID"));
    	
    	if (gridData == null || gridData.size() <= 0) {
            // 처리할 데이터가 없습니다.
            // throw new RuntimeException(this.getMessage("MSG_NOT_EXIST_DATA", null, lang));
        }
        
		for (int i = 0; i < gridData.size(); i++) {
			Map dataMap = (Map) gridData.get(i);
			dataMap.put("COMPANY_CD", companyCd);
			dataMap.put("USER_ID", userId);
		}
		
		return sm7003Dao.updateUserEntrBcnc(gridData);    	
    }    
    
    /**
     * deleteUserEntrBcnc
     * 사용자 통관 거래처 삭제 (USER_ENTR_BCNC)
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteUserEntrBcnc(Map map) throws Exception {
        return sm7003Dao.deleteUserEntrBcnc(map);
    }    
    
    
}
