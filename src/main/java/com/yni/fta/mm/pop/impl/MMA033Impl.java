package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA033;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 고객사 품목 조회
 * 
 * @author jonghyunkim
 *
 */
@Service("mmA033")
public class MMA033Impl extends YniAbstractService implements MMA033 {

    @Resource(name = "mmA033Dao")
    private MMA033Dao mmA033Dao;

    /**
     * 고객사 품목 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectCustomerItemList(Map map) throws Exception {
        return mmA033Dao.selectCustomerItemList(map);
    }

}
