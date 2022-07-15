package com.yni.fta.mm.pop.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 멕시코 확인서 form 조회 및 업로드 실행
 * 
 * @author carlos
 */
@Repository("mmA023Dao")
public class MMA023Dao extends YniAbstractDAO {
    
    /**
     * 엑셀 임시테이블 데이터 건수 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectExcelSampleCnt(Map map) throws Exception {
        return (Map) this.selectByPk("MMA023.selectExcelSampleCnt", map);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelSampleList(Map map) throws Exception {
        return this.listWithRowPaging("MMA023.selectExcelSampleList", map);
    }
    
    /**
     * Excel Upload & Save
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public int saveExcelSample(List mapList) throws Exception {
        return this.executeBatch("MMA023.saveExcelSample", mapList);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteExcelSample(Map map) throws Exception {
        return this.delete("MMA023.deleteExcelSample", map);
    }
    
    /**
     * upload 데이터 오류 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map executeItemErrorCheck(Map map) throws Exception {
        
        Map<String, Object> returnMap = new HashMap<String, Object>();

        this.executeProcedure("MMA023.executeItemErrorCheck", map);
        
        returnMap.put("RETURN_CD", StringHelper.null2void(map.get("O_RETURN")));
        returnMap.put("RETURN_MSG", StringHelper.null2void(map.get("O_RETURNMSG")));
        returnMap.put("RETURN_CNT", StringHelper.null2void(map.get("O_LINES")));

        return returnMap; 
    }
    
}
