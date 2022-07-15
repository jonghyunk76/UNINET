package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 한국 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
public interface MMA004 {

    /**
     * 기발급 원산지 증명서 정보 조회(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    Map selectCOIssueInfo(Map map) throws Exception;

    /**
     * 증명서 발급 사유 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    Map selectIssueReasonInfo(Map map) throws Exception;
    
    /**
     * 수출자 및 생산자 조회(신규등록)
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    Map selectExportNProducerInfo(Map map) throws Exception;

    /**
     * 기발급 증명서 원산지 리스트(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    List selectCOOriginList(Map map) throws Exception;

    /**
     * 원산지 판정내역 조회(신규등록)
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    List selectDeterminateList(Map map) throws Exception;

    /**
     * 서명권자 정보 조회(신규등록)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    Map selectSignatureInfo(Map map) throws Exception;

    /**
     * 수출 원산지 학인서 인보이스 정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    Map selectInvoiceInfo(Map map) throws Exception;

    /**
     * 수출 원산지 학인서 인보이스 정보 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    int updateInvoiceInfo(Map map) throws Exception;

    /**
     * 발급할 보고서 리스트 조회
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    List selectCoDocList(Map map) throws Exception;
    
    /**
     * 발급일자 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    int updateCoIssueDate(Map map) throws Exception;
    
}
