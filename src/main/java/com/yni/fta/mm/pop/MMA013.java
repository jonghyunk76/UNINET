package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > FTA 협정조회
 * 
 * @author carlos
 *
 */
public interface MMA013 {

    /**
     * FTA 협정 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectMainList(Map map) throws Exception;
}
