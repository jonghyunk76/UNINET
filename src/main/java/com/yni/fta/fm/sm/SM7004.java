package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 시스템관리 > 인터페이스 > 스케쥴관리
 * 
 * @author carlos
 *
 */
public interface SM7004 {

    /**
     * 스케쥴 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectScheduleList(Map map) throws Exception;
    
    /**
     * 스케쥴 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectScheduleDupCheck(Map map) throws Exception;
    
    /**
     * INTERFACE_SCHEDULE_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceScheduleId(Map map) throws Exception;
    
    /**
     * 스케쥴 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectScheduleInfo(Map map) throws Exception;
    
    public int insertScheduleInfo(Map map) throws Exception;

    public int updateScheduleInfo(Map map) throws Exception;
    
    public int deleteScheduleInfo(Map map) throws Exception;

    /**
     * 스케쥴 맵핑 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectScheduleMappingList(Map map) throws Exception;

    /**
     * INTERFACE_SCHEDULE_MAPPING_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceScheduleMappingId(Map map) throws Exception;
    
    /**
     * 스케쥴 맵핑 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectScheduleMappingInfo(Map map) throws Exception;
    
    public int insertScheduleMappingInfo(Map map) throws Exception;

    public int updateScheduleMappingInfo(Map map) throws Exception;
    
    public int deleteScheduleMappingInfo(Map map) throws Exception;
    
    public int deleteScheduleMappingAllInfo(Map map) throws Exception;
    
}
