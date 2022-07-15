package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA018;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 한국 확인서 원산지 등록
 * 
 * @author YNI-maker
 */
@Service("mmA018")
public class MMA018Impl extends YniAbstractService implements MMA018 {
	
	@Resource(name="mmA018Dao")
	private MMA018Dao mmA018Dao;

	/**
     * 한국 증명서 품목 HS Code 조회
     * 
     * @param map Map
     * @return String
     * @exception Exception
     */
    public String selectHsCode(Map map) throws Exception {
        Map rtMap = mmA018Dao.selectHsCode(map);
        String hsCode = null;
        
        if(rtMap != null) {
            hsCode = StringHelper.null2string(rtMap.get("HS_CODE"), "");
        }
        
        return hsCode;
    }
    
    /**
     * 한국 증명서 품목 결정기준 조회
     * 
     * @param map Map
     * @return List
     * @exception Exception
     */
    public List selectRuleContents(Map map) throws Exception {
        List rtList = mmA018Dao.selectRuleContents(map);
        
        return rtList;
    }
    
}
