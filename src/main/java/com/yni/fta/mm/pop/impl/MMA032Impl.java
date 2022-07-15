package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA032;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 협력사 조회
 * 
 * @author carlos
 *
 */
@Service("mmA032")
public class MMA032Impl extends YniAbstractService implements MMA032 {

    @Resource(name = "mmA032Dao")
    private MMA032Dao mmA032Dao;

    /**
     * 회사 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectCompanyList(Map map) throws Exception {
        return mmA032Dao.selectCompanyList(map);
    }

}
