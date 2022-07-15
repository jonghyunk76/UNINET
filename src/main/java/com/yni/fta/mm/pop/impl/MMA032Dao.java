package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 협력사 조회
 * 
 * @author carlos
 *
 */
@Repository("mmA032Dao")
public class MMA032Dao extends YniAbstractDAO {

    /**
     * 회사 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectCompanyList(Map map) throws Exception {
        return this.listWithRowPaging("MMA032.selectCompanyList", map);
    }
}
