package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA010;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 서명권자 조회
 * 
 * @author carlos
 *
 */
@Service("mmA010")
public class MMA010Impl extends YniAbstractService implements MMA010 {

    @Resource(name = "mmA010Dao")
    private MMA010Dao mmA010Dao;

    /**
     * 서명권자 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectSignatureList(Map map) throws Exception {
        return mmA010Dao.selectSignatureList(map);
    }

}
