package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA006;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 협력사 조회
 * 
 * @author carlos
 *
 */
@Service("mmA006")
public class MMA006Impl extends YniAbstractService implements MMA006 {

    @Resource(name = "mmA006Dao")
    private MMA006Dao mmA006Dao;

    /**
     * 협력사 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectMainList(Map map) throws Exception {
        return mmA006Dao.selectMainList(map);
    }

}
