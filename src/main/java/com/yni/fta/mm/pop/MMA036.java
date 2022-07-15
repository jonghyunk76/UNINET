package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 수입당사국 세율 조회
 * 
 * @author YNI-Maker
 *
 */
public interface MMA036 {

    /**
     * 수입당사국 세율 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectImporterTariffList(Map map) throws Exception;
}
