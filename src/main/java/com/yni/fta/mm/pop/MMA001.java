package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
public interface MMA001 {
	
	/**
	 * 마감정보 현황 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectClosingInfo(Map map) throws Exception;
	
	/**
	 * ERP 인터페이스 현황 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectDataInterfaceCount(Map map) throws Exception;
	
	/**
	 * 협력사 CO 수취 현황 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectVendorCoCount(Map map) throws Exception;
	
	/**
	 * 거래 현황 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectInOutCount(Map map) throws Exception;
	
	/**
	 * 원산지 판정 현황 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectDeterminateCount(Map map) throws Exception;
	
	/**
	 * 월별 원산지 판정 현황 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMonthlyDeterminateCount(Map map) throws Exception;
	
	/**
	 * 증명서 발급 현황 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectIssueReportCount(Map map) throws Exception;
	
	/**
	 * 관리할 마감월 지정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateWorkDate(Map map) throws Exception;
	
	/**
	 * 협력사 원산지 제출 상태
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectSupplierIssueStatus(Map map) throws Exception;
	
	/**
	 * FTA 관세 절감액 추이
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectExportTariffInfo(Map map) throws Exception;
	
	/**
	 * 환경설정 값 구하기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectSystemConfig(Map map) throws Exception;
	
	/**
	 * 메뉴정보 구하기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectSystemMenuInfo(Map map) throws Exception;
	
	/**
	 * 월별 원산지 판정결과 재집계
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateDwMonthFcrStatus(Map map) throws Exception;
	
}
