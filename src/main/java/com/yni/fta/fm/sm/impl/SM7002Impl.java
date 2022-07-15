package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.fm.sm.SM7002;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.StringHelper;

/**
 * 시스템관리 > 사업부등록
 * 
 * @author carlos
 *
 */
@Service("sm7002")
public class SM7002Impl extends YniAbstractService implements SM7002 {
	
    @Resource(name = "sm7002Dao")
    private SM7002Dao sm7002Dao;

    public List selectDivisionList(Map map) throws Exception {
        return sm7002Dao.selectDivisionList(map);
    }
    
    public Map selectDivisionDupCheck(Map map) throws Exception {
        return sm7002Dao.selectDivisionDupCheck(map);
    }
    
    public Map selectDivisionInfo(Map map) throws Exception {
        return sm7002Dao.selectDivisionInfo(map);
    }
    
    public int insertDivisionInfo(Map map) throws Exception {
        
        Map dupInfo = sm7002Dao.selectDivisionDupCheck(map);
        
        if (dupInfo != null) {
            int nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            if (nDupCnt > 0) {
                // 이미 등록된 사업부정보입니다
                throw new RuntimeException(getMessage("MSG_EXISTS_DIVISION_INFO"));
            }
        }

        return sm7002Dao.insertDivisionInfo(map);
    }

    public int updateDivisionInfo(Map map) throws Exception {
        return sm7002Dao.updateDivisionInfo(map);
    }
    
    public int deleteDivisionInfo(Map map) throws Exception {
        return sm7002Dao.deleteDivisionInfo(map);
    }
    
}
