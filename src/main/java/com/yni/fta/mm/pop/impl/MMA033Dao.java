package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 고객사 품목 조회
 * 
 * @author carlos
 *
 */
@Repository("mmA033Dao")
public class MMA033Dao extends YniAbstractDAO {

    /**
     * 고객사 품목 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectCustomerItemList(Map map) throws Exception {
        return this.listWithRowPaging("MMA033.selectCustomerItemList", map);
    }
}
