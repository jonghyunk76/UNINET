package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

/**
 * 시스템관리 > 인터페이스 > 스케쥴 관리
 * 
 * @author carlos
 *
 */
@Repository("sm7004Dao")
public class SM7004Dao extends YniAbstractDAO {

    /**
     * 스케쥴 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectScheduleList(Map map) throws Exception {
        return this.listWithRowPaging("SM7004.selectScheduleList", map);
    }
    
    /**
     * 스케쥴 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectScheduleDupCheck(Map map) throws Exception {
        return (Map) this.selectByPk("SM7004.selectScheduleDupCheck", map);
    }
    
    /**
     * INTERFACE_SCHEDULE_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceScheduleId(Map map) throws Exception {
        return (Map) this.selectByPk("SM7004.selectLastInterfaceScheduleId", map);
    }
    
    /**
     * 스케쥴 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectScheduleInfo(Map map) throws Exception {
        return (Map) this.selectByPk("SM7004.selectScheduleInfo", map);
    }
    
    /**
     * 스케쥴 신규등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertScheduleInfo(Map map) throws Exception {
        return this.update("SM7004.insertScheduleInfo", map);
    }

    /**
     * 스케쥴 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateScheduleInfo(Map map) throws Exception {
        return this.update("SM7004.updateScheduleInfo", map);
    }
    
    /**
     * 스케쥴 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteScheduleInfo(Map map) throws Exception {
        return this.delete("SM7004.deleteScheduleInfo", map);
    }
    
    /**
     * 스케쥴 맵핑 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectScheduleMappingList(Map map) throws Exception {
    	String sysid = StringHelper.null2void(map.get("SYSTEM_ID"));
    	
    	if(sysid.equals("RS")) {
    		return this.listWithRowPaging("SM7004.selectScheduleServiceMappingList", map);
    	} else {
    		return this.listWithRowPaging("SM7004.selectScheduleMappingList", map);
    	}
    }
    
    /**
     * INTERFACE_SCHEDULE_MAPPING_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceScheduleMappingId(Map map) throws Exception {
        return (Map) this.selectByPk("SM7004.selectLastInterfaceScheduleMappingId", map);
    }
    
    /**
     * 스케쥴 맵핑 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectScheduleMappingInfo(Map map) throws Exception {
        return (Map) this.selectByPk("SM7004.selectScheduleMappingInfo", map);
    }
    
    /**
     * 스케쥴 맵핑 신규등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertScheduleMappingInfo(Map map) throws Exception {
        //int rtnCnt = this.update("SM7004.insertScheduleMappingInfo", map);
        Object rData = this.insert("SM7004.insertScheduleMappingInfo", map);
        return 1;
    }

    /**
     * 스케쥴 맵핑 신규등록(일괄배치)
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int batchScheduleMappingInfo(List list) throws Exception {
        return this.executeBatch("SM7004.insertScheduleMappingInfo", list);
    }
    
    /**
     * 스케쥴 맵핑 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateScheduleMappingInfo(Map map) throws Exception {
        log.debug("updateScheduleMappingInfo = " +  map.toString());
        return this.update("SM7004.updateScheduleMappingInfo", map);
    }
    
    /**
     * 스케쥴 맵핑 전체 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteScheduleMappingInfo(Map map) throws Exception {
        log.debug("deleteScheduleMappingInfo = " +  map.toString());
        return this.delete("SM7004.deleteScheduleMappingInfo", map);
    }
    
    
    /**
     * 스케쥴 매핑 전체삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteScheduleMappingAllInfo(Map map) throws Exception {
        log.debug("deleteScheduleMappingAllInfo = " +  map.toString());
        return this.delete("SM7004.deleteScheduleMappingAllInfo", map);
    }
    
    
}
