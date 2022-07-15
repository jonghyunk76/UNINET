package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 엑셀 업로드(Excel Upload)
 * 
 * @author carlos
 *
 */
public interface MMA016 {
    
	/**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
	List selectExcelData(Map map) throws Exception;
	
	/**
     * Excel Upload 실행
     * 
     * @param map
     * @return 해더정보
     * @throws Exception
     */
	Map executeExcelUpload(Map map) throws Exception;
    
	/**
     * Excel 컬럼항목 및 유효성 체크 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectExcelColmunList(Map map) throws Exception;
    
    /**
     * Excel 컬럼별 추출 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectExcelValueList(Map map) throws Exception;
    
    /**
     * 대상 테이블의 컬럼리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectJcoColumnList(Map map) throws Exception;
    
    /**
     * 대상 테이블의 컬럼정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map selectJcoColumnInfo(Map map) throws Exception;
    
    /**
     * 이관시킬 대상 컬럼ID 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int updateTargetColumn(Map map) throws Exception;
    
    /**
     * Excel 원본 데이터 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int deleteSourceColumn(Map map) throws Exception;
    
    /**
     * Excel 소스 자동 맵핑
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map executeChangeSource(Map map) throws Exception;
    
    /**
     * 엑셀데이터 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int insertExcelData(Map map) throws Exception;
    
    /**
     * Excel 인터페이스 항목 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectExcelInterfaceList(Map map) throws Exception;
    
    /**
     * 검증 후 Excel 데이터의 통계
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map selectExcelDataStatus(Map map) throws Exception;
    
    /**
     * 검증 후 Excel 데이터의 해더정보 
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectExcelHeaderList(Map map) throws Exception;
    
    /**
     * 엑셀업로드 완료 실행
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map executeExcelBatch(Map map) throws Exception;
    
    /**
     * 실시간으로 제품BOM을 가져오기 위한 인터페이스 수행
     * 
     * @param map 요청 파라메터 정보
     * @param seq 상위 인터페이스 식별ID
     * @param reqType 작업을 요청 타입(AS:매출조정, DS:일배치 매출, MS:월배치 매출 등)-다양한 구분으로 활용가능
     * @return
     * @throws Exception
     */
    Map getRealProductBOM(Map map, String seq, String reqType) throws Exception;
    
}
