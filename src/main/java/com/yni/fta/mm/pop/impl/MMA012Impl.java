package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA012;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > NALADISA 리스트
 * 
 * @author YNI-maker
 *
 */
@Service("mmA012")
public class MMA012Impl extends YniAbstractService implements MMA012 {
    
    @Resource(name="mmA012Dao")
    private MMA012Dao mmA012Dao;

    public List selectMainList(Map map) throws Exception {
        return mmA012Dao.selectMainList(map);
    }
    
}
