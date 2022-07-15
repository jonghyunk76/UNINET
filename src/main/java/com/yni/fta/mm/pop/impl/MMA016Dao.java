package com.yni.fta.mm.pop.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yni.fta.common.postgresql.PostgresqlDao;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 엑셀 업로드(Excel Upload)
 * 
 * @author carlos
 *
 */
@Repository("mmA016Dao")
public class MMA016Dao extends YniAbstractDAO {

    /**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelData(Map map) throws Exception {
        return this.listWithRowPaging("MMA016.selectExcelData", map);
    }

    /**
     * Excel Upload & Save
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public int insertExcelData(List mapList) throws Exception {
        return this.executeBatch("MMA016.insertExcelData", mapList);
    }
    
    /**
     * Excel Delete
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public int deleteExcelData(Map map) throws Exception {
        return this.delete("MMA016.deleteExcelData", map);
    }
    
    /**
     * Excel 컬럼항목 및 유효성 체크 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelColmunList(Map map) throws Exception {
        return this.list("MMA016.selectExcelColmunList", map);
    }
    
    /**
     * Excel 컬럼별 추출 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelValueList(Map map) throws Exception {
        return this.listWithRowPaging("MMA016.selectExcelValueList", map);
    }
    
    /**
     * 이관시킬 대상 컬럼ID 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateTargetColumn(Map map) throws Exception {
    	return this.update("MMA016.updateTargetColumn", map);
    }
    
    /**
     * Excel 원본 데이터 목록 구하기
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelOriginDataList(Map map) throws Exception {
        return this.list("MMA016.selectExcelOriginDataList", map);
    }
    
    /**
     * Excel 원본 데이터 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteSourceColumn(Map map) throws Exception {
        return this.delete("MMA016.deleteSourceColumn", map);
    }
    
    /**
     * Excel 소스 자동 맵핑
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map executeChangeSource(Map map) throws Exception {
    	String db = StringHelper.null2void(this.properties.get("application.db.type"));
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	
		if (db.equals("POSTGRESQL")) {
        	PostgresqlDao postgre = new PostgresqlDao();
        	map = postgre.executeChangeSource(map);
		} else {	
			this.executeProcedure("MMA016.executeChangeSource", map);
		}

        returnMap.put("O_LINES", StringHelper.null2void(map.get("O_LINES")));  		// 처리한 레코드 수
        returnMap.put("O_RETURN", StringHelper.null2void(map.get("O_RETURN"))); 	// 처리결과(S(성공),E(에러),N(데이터 없음))
        
        log.debug("executeChangeSource result = " + StringHelper.null2void(map.get("O_RETURN")) + ", message = " + StringHelper.null2void(map.get("O_RETURNMSG")));
        
        if(StringHelper.null2void(map.get("O_RETURN")).equals("S")) {
        	returnMap.put("O_RETURNMSG", ""); 	// 처리결과 메시지
        } else {
        	returnMap.put("O_RETURNMSG", StringHelper.null2void(map.get("O_RETURNMSG"))); 	// 처리결과 메시지
        }
        
        return returnMap;
    }
    
    /**
     * Excel 인터페이스 항목 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelInterfaceList(Map map) throws Exception {
        return this.list("MMA016.selectExcelInterfaceList", map);
    }
    
    /**
     * 검증 후 Excel 데이터의 통계
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectExcelDataStatus(Map map) throws Exception {
        return (Map) this.selectByPk("MMA016.selectExcelDataStatus", map);
    }
    
    /**
     * 검증 후 Excel 데이터의 해더정보 
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelHeaderList(Map map) throws Exception {
        return this.list("MMA016.selectExcelHeaderList", map);
    }
    
    /**
     * 품목정보의 자산구분을 판매유형으로 적용하기 위한 목록 조회. 단, 회사 설정에서 판매유형을 품목정보(I)으로 설정해야 조회됨
     * 
     * @param mapList
     * @return
     * @throws Exception
     */
    public List selectItemTypeList(Map map) throws Exception {
        return this.list("MMA016.selectItemTypeList", map);
    }
    
}
