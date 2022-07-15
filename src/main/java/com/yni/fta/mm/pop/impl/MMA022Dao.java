package com.yni.fta.mm.pop.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yni.fta.common.postgresql.PostgresqlDao;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 한국 확인서 form 조회 및 업로드 실행
 * 
 * @author carlos
 */
@Repository("mmA022Dao")
public class MMA022Dao extends YniAbstractDAO {
    
	/**
     * 엑셀 임시테이블 데이터 건수 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectExcelSampleCnt(Map map) throws Exception {
        return (Map) this.selectByPk("MMA022.selectExcelSampleCnt", map);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelSampleList(Map map) throws Exception {
        return this.listWithRowPaging("MMA022.selectExcelSampleList", map);
    }
    
    /**
     * Excel Upload & Save
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public int saveExcelSample(List mapList) throws Exception {
        return this.executeBatch("MMA022.saveExcelSample", mapList);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteExcelSample(Map map) throws Exception {
        return this.delete("MMA022.deleteExcelSample", map);
    }

    /**
     * upload 데이터 오류 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map executeItemErrorCheck(Map map) throws Exception {
    	String db = StringHelper.null2void(this.properties.get("application.db.type"));
        Map<String, Object> returnMap = new HashMap<String, Object>();

		if (db.equals("POSTGRESQL")) {
        	PostgresqlDao postgre = new PostgresqlDao();
        	map = postgre.executeItemErrorCheck(map);
		} else {	
			this.executeProcedure("MMA022.executeItemErrorCheck", map);
		}
        
        returnMap.put("RETURN_CD", StringHelper.null2void(map.get("O_RETURN")));
        returnMap.put("RETURN_MSG", StringHelper.null2void(map.get("O_RETURNMSG")));
        returnMap.put("RETURN_CNT", StringHelper.null2void(map.get("O_LINES")));

        return returnMap; 
    }
    
    /**
     * 엑셀 임시테이블 데이터 건수 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectVientnamExcelSampleCnt(Map map) throws Exception {
        return (Map) this.selectByPk("MMA022.selectVientnamExcelSampleCnt", map);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectVientnamExcelSampleList(Map map) throws Exception {
        return this.listWithRowPaging("MMA022.selectVientnamExcelSampleList", map);
    }
    
    /**
     * Excel Upload & Save
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public int saveVientnamExcelSample(List mapList) throws Exception {
        return this.executeBatch("MMA022.saveVientnamExcelSample", mapList);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteVientnamExcelSample(Map map) throws Exception {
        return this.delete("MMA022.deleteVientnamExcelSample", map);
    }
    
    /**
     * upload 데이터 오류 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map executeVientnamItemErrorCheck(Map map) throws Exception {
    	String db = StringHelper.null2void(this.properties.get("application.db.type"));
        Map<String, Object> returnMap = new HashMap<String, Object>();

		if (db.equals("POSTGRESQL")) {
        	PostgresqlDao postgre = new PostgresqlDao();
        	map = postgre.executeVientnamItemErrorCheck(map);
		} else {	
			this.executeProcedure("MMA022.executeVientnamItemErrorCheck", map);
		}
        
        returnMap.put("RETURN_CD", StringHelper.null2void(map.get("O_RETURN")));
        returnMap.put("RETURN_MSG", StringHelper.null2void(map.get("O_RETURNMSG")));
        returnMap.put("RETURN_CNT", StringHelper.null2void(map.get("O_LINES")));

        return returnMap; 
    }
    
    /**
     * 	베트남 원산지 증명서 마스터 정보 등록
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public int insertVientnamImpCoMaster(Map map) throws Exception {
        return this.update("MMA022.insertVientnamImpCoMaster", map);
    }
    
    /**
     * 베트남 원산지 증명서 상세 정보 등록
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public int insertVientnamImpCoDetail(Map map) throws Exception {
        return this.update("MMA022.insertVientnamImpCoDetail", map);
    }
    
    /**
     * 베트남 원산지 증명서 업로드내역 삭제
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public int deleteVientnamExcelSampleRow(Map map) throws Exception {
        return this.update("MMA022.deleteVientnamExcelSampleRow", map);
    }
    
}
