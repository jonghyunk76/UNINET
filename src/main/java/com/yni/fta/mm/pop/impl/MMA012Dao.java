package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > NALADISA 리스트
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA012Dao")
public class MMA012Dao extends YniAbstractDAO {

    /**
     * NALADISA 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectMainList(Map map) throws Exception {
        return this.listWithRowPaging("MMA012.selectMainList", map);
    }
}
