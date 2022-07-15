package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 수입당사국 세율 조회
 * 
 * @author YNI-Maker
 *
 */
@Repository("mmA036Dao")
public class MMA036Dao extends YniAbstractDAO {

    /**
     * 수입당사국 세율 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectImporterTariffList(Map map) throws Exception {
        return this.listWithRowPaging("MMA036.selectImporterTariffList", map);
    }
}
