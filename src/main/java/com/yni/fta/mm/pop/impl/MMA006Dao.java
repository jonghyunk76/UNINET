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
@Repository("mmA006Dao")
public class MMA006Dao extends YniAbstractDAO {

    /**
     * 협력사 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectMainList(Map map) throws Exception {
        return this.listWithRowPaging("MMA006.selectMainList", map);
    }
}
