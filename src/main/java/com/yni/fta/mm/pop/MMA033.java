package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 고객사 품목 조회
 * 
 * @author jonghyunkim
 *
 */
public interface MMA033 {

    /**
     * 고객사 품목 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectCustomerItemList(Map map) throws Exception;
}
