package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 멕시코 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
@Repository("mmA019Dao")
@SuppressWarnings("rawtypes")
public class MMA019Dao extends YniAbstractDAO {
	
	/**
     * 동일계정의 임시정보 삭제
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public int deleteExcelReqItemSample(Map map) throws Exception {
        return this.delete("MMA019.deleteExcelReqItemSample", map);
    }
    
    /**
     * 동일계정의 임시정보 등록
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public int insertExcelReqItemSample(List list) throws Exception {
        return this.executeBatch("MMA019.insertExcelReqItemSample", list);
    }
    
    /**
     * 확인서 기본정보 및 생산자 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
	public Map selectBasicInfoCaseInsert(Map map) throws Exception {
        return (Map) this.selectByPk("MMA019.selectBasicInfoCaseInsert", map);
    }
    
    /**
     * 품목별 원산지 리스트 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectItemListCaseInsert(Map map) throws Exception {
        return list("MMA019.selectItemListCaseInsert", map);
    }  
    
    /**
     * 품목별 원산지 리스트 조회 및 엑셀form 다운로드(코일 확인서 등록용)
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectCoItemMappCaseInsert(Map map) throws Exception {
        return list("MMA019.selectCoItemMappCaseInsert", map);
    }
    
    /**
     * 확인서 기본정보 및 생산자,서명권자 수정정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectBasicInfoCaseUpdate(Map map) throws Exception {
        return (Map) this.selectByPk("MMA019.selectBasicInfoCaseUpdate", map);
    }  
    
    /**
     * 품목별 원산지 내역 수정정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectItemListCaseUpdate(Map map) throws Exception {
        return list("MMA019.selectItemListCaseUpdate", map);
    }
    
    /**
     * 확인서 기본정보 및 생산자 갱신정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectBasicInfoCaseRenew(Map map) throws Exception {
        return (Map) this.selectByPk("MMA019.selectBasicInfoCaseRenew", map);
    }
    
    /**
     * 품목별 원산지 리스트 갱신정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectItemListCaseRenew(Map map) throws Exception {
        return list("MMA019.selectItemListCaseRenew", map);
    }
    
    /**
     * 품목별 원산지 리스트 미결정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectItemListCasePeding(Map map) throws Exception {
        return list("MMA019.selectItemListCasePeding", map);
    }
    
    /**
     * 증빙파일 다운로드
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectProofFile(Map map) throws Exception {
        return (Map) this.selectByPk("MMA019.selectProofFile", map);
    }
    
    /**
     * 동일계정의 임시정보 삭제
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public int deleteExcelDOSample(Map map) throws Exception {
        return this.delete("MMA019.deleteExcelDOSample", map);
    }
    
    /**
     * 동일계정의 임시정보 등록
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public int insertExcelDOSample(List list) throws Exception {
        return this.executeBatch("MMA019.insertExcelDOSample", list);
    }
    /**
     * 확인서 포괄기간 중복등록 수 체크
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectCoDateCheck(Map map) throws Exception {
        return (Map) this.selectByPk("MMA019.selectCoDateCheck", map);
    }
    
    /**
     * 확인서 증명번호 중복 여부 체크
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectcInterfaceDupCheck(Map map) throws Exception {
        return (Map) this.selectByPk("MMA019.selectcInterfaceDupCheck", map);
    }
    
    /**
     * 확인서 증명번호 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectMaxCoDocNo(Map map) throws Exception {
        return (Map) this.selectByPk("MMA019.selectMaxCoDocNo", map);
    }
    
    /**
     * 확인서 기본정보 및 생산자,서명권자 등록
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int insertBasicInfo(Map map) throws Exception {
        return this.update("MMA019.insertBasicInfo", map);
    }
    
    /**
     * 확인서 기본정보 및 생산자,서명권자 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateBasicInfo(Map map) throws Exception {
        return this.update("MMA019.updateBasicInfo", map);
    }
    
    /**
     * 품목별 원산지 정보 삭제 전체<br>
     * - 원산지 수정 시 기등록된 정보를 삭제 후 재 등록하는 로직으로 구현됨 
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int deleteItemListAll(Map map) throws Exception {
        return this.update("MMA019.deleteItemListAll", map);
    }
    
    /**
     * 자재목록 원산지 정보 등록
     * 
     * @param mapList List
     * @return int
     * @throws Exception
     */
    public int insertItemList(List mapList) throws Exception {
        return this.executeBatch("MMA019.insertItemList", mapList);
    }
    
    /**
     * 자재목록 원산지 정보 등록
     * 
     * @param mapList List
     * @return int
     * @throws Exception
     */
    public int insertItemList(Map map) throws Exception {
        return this.update("MMA019.insertItemList", map);
    }
    
    /**
     * 증빙파일 삭제<br>
     * - 증빙파일 수정 시 삭제 후 재 등록됨
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int deleteProofFileInfo(Map map) throws Exception {
        return this.update("MMA019.deleteProofFileInfo", map);
    }
    
    /**
     * 원산지 확인서 증빙파일 등록
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int insertProofFileInfoCaseInsert(Map map) throws Exception {
        return this.update("MMA019.insertProofFileInfoCaseInsert", map);
    }
    
    /**
     * 증빙파일 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateProofFileInfo(Map map) throws Exception {
        return this.update("MMA019.updateProofFileInfo", map);
    }
    
    /**
     * 원산지 확인서 제출상태 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateDocSubmit(Map map) throws Exception {
        return update("MMA019.updateDocSubmit", map);
    }
    
    /**
     * 원산지 수정을 위해 기존 포괄만료기간을 업데이트함
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateModifyEndDate(Map map) throws Exception {
        return update("MMA019.updateModifyEndDate", map);
    }
    
    /**
     * 협력사 자재의 원산지 확인서 등록 상태 변경
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateVendorItemStatus(Map map) throws Exception {
        return update("MMA019.updateVendorItemStatus", map);
    }
    
    /**
     * 수정요청 제출 시 확인서와 싱크를 맞춤
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateVendorItemResync(Map map) throws Exception {
        return update("MMA019.updateVendorItemResync", map);
    }
    
    /**
     * 협력사 자재의 수정요청상태를 완료로 변경
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateDocRequestStatus(Map map) throws Exception {
        return update("MMA019.updateDocRequestStatus", map);
    }
    
    /**
     * 원산지 확인서 수정 이력 등록
     * 
     * @param mapList List
     * @return int
     * @throws Exception
     */
    public int insertItemListHistory(Map map) throws Exception {
        return this.update("MMA019.insertItemListHistory", map);
    }
    
    /**
     * 원산지 확인서 수정 이력 상세내역 등록
     * 
     * @param mapList List
     * @return int
     * @throws Exception
     */
    public int insertItemListHistoryDetail(Map map) throws Exception {
        return this.update("MMA019.insertItemListHistoryDetail", map);
    }
    
    /**
     * 원산지 확인서 수정 이력 파일정보 등록
     * 
     * @param mapList List
     * @return int
     * @throws Exception
     */
    public int insertItemListHistoryFile(Map map) throws Exception {
        return this.update("MMA019.insertItemListHistoryFile", map);
    }
    
    /**
     * 철판코일 확인서 등록 시 맵핑정보에 등록한 확인서 증빙번호 업데이트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateCoItemMapping(Map map) throws Exception {
        return this.update("MMA017.updateCoItemMapping", map);
    }
    
} 
