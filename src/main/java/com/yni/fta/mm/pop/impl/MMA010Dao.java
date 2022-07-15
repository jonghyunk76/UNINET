package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 서명권자 조회
 * 
 * @author carlos
 *
 */
@Repository("mmA010Dao")
public class MMA010Dao extends YniAbstractDAO {

    /**
     * 서명권자 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectSignatureList(Map map) throws Exception {
        return this.listWithRowPaging("MMA010.selectSignatureList", map);
    }
}
