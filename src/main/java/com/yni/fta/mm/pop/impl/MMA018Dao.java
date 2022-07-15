package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > 한국 확인서 원산지 등록
 * 
 * @author YNI-maker
 */
@Repository("mmA018Dao")
public class MMA018Dao extends YniAbstractDAO {

	/**
     * 한국 증명서 품목 HS Code 조회
     * 
     * @param map Map
     * @return Map
     * @exception Exception
     */
    public Map selectHsCode(Map map) throws Exception {
        return (Map) this.selectByPk("MMA020.selectHSCode", map);
    }
    
    /**
     * 한국 증명서 품목 결정기준 조회
     * 
     * @param map Map
     * @return List
     * @exception Exception
     */
    public List selectRuleContents(Map map) throws Exception {
        return list("MMA020.selectRuleContents", map);
    }

}
