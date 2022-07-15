package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA020;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 멕시코 확인서 원산지 등록
 * 
 * @author YNI-maker
 */
@Service("mmA020")
public class MMA020Impl extends YniAbstractService implements MMA020 {
	
	@Resource(name="mmA020Dao")
	private MMA020Dao mmA020Dao;
	
	/**
     * 멕시코 증명서 품목 HS Code 조회
     * 
     * @param map Map
     * @return String
     * @exception Exception
     */
    public String selectHsCode(Map map) throws Exception {
        Map rtMap = mmA020Dao.selectHsCode(map);
        String hsCode = null;
        
        if(rtMap != null) {
            hsCode = StringHelper.null2string(rtMap.get("HS_CODE"), "");
        }
        
        return hsCode;
    }
    
    /**
     * 멕시코 증명서 품목 Naladisa Code 조회
     * 
     * @param map Map
     * @return String
     * @exception Exception
     */
    public String selectNaladisaCd(Map map) throws Exception {
        Map rtMap = mmA020Dao.selectNaladisaCd(map);
        String hsCode = null;
        
        if(rtMap != null) {
            hsCode = StringHelper.null2string(rtMap.get("HS_CODE"), "");
        }
        
        return hsCode;
    }
    
    /**
     * 멕시코 증명서 품목 결정기준 조회
     * 
     * @param map Map
     * @return List
     * @exception Exception
     */
    public List selectRuleContents(Map map) throws Exception {
        List rtList = mmA020Dao.selectRuleContents(map);
        
        return rtList;
    }
    
}
