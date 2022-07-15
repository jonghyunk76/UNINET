package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > FTA 협정 조회
 * 
 * @author carlos
 *
 */
@Repository("mmA013Dao")
public class MMA013Dao extends YniAbstractDAO {

    /**
     * FTA 협정 조회
     * 
     * @param mapE
     * @return
     * @throws Exception
     */
    public List selectMainList(Map map) throws Exception {
        return this.listWithRowPaging("MMA013.selectMainList", map);
    }
}
