package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA013;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > FTA 협정
 * 
 * @author carlos
 *
 */
@Service("mmA013")
public class MMA013Impl extends YniAbstractService implements MMA013 {

    @Resource(name = "mmA013Dao")
    private MMA013Dao mmA013Dao;

    /**
     * FTA 협정 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectMainList(Map map) throws Exception {
        return mmA013Dao.selectMainList(map);
    }

}
