package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 멕시코 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
public interface MMA019 {
    
	/**
     * 확인서 기본정보 및 생산자 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    Map selectBasicInfoCaseInsert(Map map) throws Exception;

    /**
     * 품목별 원산지 리스트 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    List executeItemListCaseInsert(Map map) throws Exception;
    
    /**
     * 확인서 기본정보 및 생산자,서명권자 수정정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    Map selectBasicInfoCaseUpdate(Map map) throws Exception;
    
    /**
     * 품목별 원산지 내역 수정정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    List selectItemListCaseUpdate(Map map) throws Exception;
    
    /**
     * 확인서 기본정보 및 생산자 갱신정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    Map selectBasicInfoCaseRenew(Map map) throws Exception;
    
    /**
     * 품목별 원산지 리스트 갱신정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    List executeItemListCaseRenew(Map map) throws Exception;
    
    /**
     * 품목별 원산지 리스트 미결정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    List executeItemListCasePeding(Map map) throws Exception;
    
    /**
     * 증빙파일 다운로드
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     * */
    Map selectProofFile(Map map) throws Exception;
    
    /**
     * 멕시코 확인서 기본정보 신규 등록<br>
     *  1.포괄기간 중복여부 체크
     *  2.원산지 증명번호 채번<br>
     *    - 사용자가 직접 입력할 경우 기등록 증명번호인지 체크함<br>
     *  3.확인서 기본정보 및 생산자,서명권자 등록<br>
     *  4.품목 원산지 정보 전체 삭제<br>
     *  5.품목 원산지 정보 저장<br>
     *  6.원산지 정보 증빙파일 저장<br>
     *  7.협력사 자재의 원산지 확인서 등록 상태 변경(N:신규, U:기등록)<br>
     *  8.원산지 확인서 제출상태 수정<br>
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    int insertRcvCoInfo(Map map) throws Exception;
    
    /**
     * 증빙파일을 등록한다.
     * @param map
     * @return
     * @throws Exception
     */
    int insertRcvCoFileInfo(Map map) throws Exception;
    
    /**
     * 원산지 수정을 위해 기존 포괄만료기간을 업데이트함
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     * */
    int updateModifyEndDate(Map map) throws Exception;
    
}
