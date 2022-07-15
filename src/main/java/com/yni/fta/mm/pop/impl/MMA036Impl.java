package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA036;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 수입당사국 세율 조회
 * 
 * @author YNI-Maker
 *
 */
@Service("mmA036")
public class MMA036Impl extends YniAbstractService implements MMA036 {

    @Resource(name = "mmA036Dao")
    private MMA036Dao mmA036Dao;

    /**
     * 수입당사국 세율 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectImporterTariffList(Map map) throws Exception {
        return mmA036Dao.selectImporterTariffList(map);
    }

}
