package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 협력사 조회
 * 
 * @author carlos
 *
 */
public interface MMA032 {

    /**
     * 회사 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectCompanyList(Map map) throws Exception;
}
