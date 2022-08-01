package com.yni.fta.common.batch.vo;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.Consistent;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.util.StringHelper;

/**
 * 배치 수행이력를 저장하는 ValueObject
 * 
 * @author jonghyun3.kim
 *
 */
public class BatchVo {

    private static Log log = LogFactory.getLog(BatchVo.class);

    private DataMap batchMap = new DataMap();

    // 배치 시 하나의 파라메터를 포함할 수 있음.
    private ParameterVo parameterVo;

    // 인터페이스코드
    public String getInterfaceCode() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_INTERFACE_CD));
    }

    public void setInterfaceCode(String interfacecode) {
        this.batchMap.put(Consistent.IF_BATCH_INTERFACE_CD, interfacecode);
    }

    // 우선 실행해야 할 인터페이스 코드
    public String getParentCode() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_PARENT_CD));
    }

    public void setParentCode(String parentcode) {
        this.batchMap.put(Consistent.IF_BATCH_PARENT_CD, parentcode);
    }

    // 인터페이스 처리 방식(T:외부 테이블에서 데이터를 읽는 방식, P:외부 데이터 인터페이스 없이 프로시져를 호출, C:URL 호출, E:클라이언트별 엑셀양식에 맞는 테이블을 생성한 후 프로시져를 호출하는 방식)
    public String getItemType() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_ITEM_TYPE));
    }

    public void setItemType(String type) {
        this.batchMap.put(Consistent.IF_BATCH_ITEM_TYPE, type);
    }

    // 인터페이스 필수 실행여부
    public String getRequired() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_REQUIRED));
    }

    public void setRequired(String required) {
        this.batchMap.put(Consistent.IF_BATCH_REQUIRED, required);
    }

    // 인터페이스 방식
    public String getInterfaceMethod() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_INTERFACE_METHOD));
    }

    public void setInterfaceMethod(String interfacemethod) {
        this.batchMap.put(Consistent.IF_BATCH_INTERFACE_METHOD, interfacemethod);
    }

    // XML - JCO ID
    public String getJcoId() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_JCO_ID));
    }

    public void setJcoId(String jcoid) {
        this.batchMap.put(Consistent.IF_BATCH_JCO_ID, jcoid);
    }

    // 수행할 function 명
    public String getFunctionName() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_FUNCTION_NAME));
    }

    public void setFunctionName(String functionName) {
        this.batchMap.put(Consistent.IF_BATCH_FUNCTION_NAME, functionName);
    }

    // 수행할 parameter 명
    public String getParameterName() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_PARAMETER_NAME));
    }

    public void setParameterName(String functionName) {
        this.batchMap.put(Consistent.IF_BATCH_PARAMETER_NAME, functionName);
    }

    // 파라메터(호출 시 적용될 파라메터 정보, Map 타입)
    public String getInterfaceParam() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_INTERFACE_PARAM));
    }

    public void setInterfaceParam(String param) {
        this.batchMap.put(Consistent.IF_BATCH_INTERFACE_PARAM, param);
    }
    
    // 배치 실행 후 직접 데이터 리턴
    public Object getReturnData() {
        return batchMap.get(Consistent.IF_BATCH_RETURN_DATA);
    }

    public void setReturnData(Object datas) {
        this.batchMap.put(Consistent.IF_BATCH_RETURN_DATA, datas);
    }
    
    // 배치 실행 후 직접 데이터 리턴
    public Object getImportData() {
        return batchMap.get("IMPORT_DATA");
    }

    public void setImportData(Object datas) {
        this.batchMap.put("IMPORT_DATA", datas);
    }
    
    // 배치 실행 후 직접 데이터 리턴
    public Object getExportData() {
        return batchMap.get("EXPORT_DATA");
    }

    public void setExportData(Object datas) {
        this.batchMap.put("EXPORT_DATA", datas);
    }
    
    // 전송ID
    public String getTransId() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_TRANS_ID));
    }

    public void setTransId(String transid) {
        this.batchMap.put(Consistent.IF_BATCH_TRANS_ID, transid);
    }
    
    // 상위 전송이력 번호
    public String getParentTransId() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_PARENT_HISTORY_ID));
    }

    public void setParentTransId(String transid) {
        this.batchMap.put(Consistent.IF_PARENT_HISTORY_ID, transid);
    }
    
    // 배치상태
    public String getBatchStatus() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_BATCH_STATUS));
    }

    public void setBatchStatus(String batchStatus) {
        this.batchMap.put(Consistent.IF_BATCH_BATCH_STATUS, batchStatus);
    }

    // 데이터 수신상태
    public String getTransStatus() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_TRANS_STATUS));
    }

    public void setTransStatus(String transStatus) {
        this.batchMap.put(Consistent.IF_BATCH_TRANS_STATUS, transStatus);
    }
    
    // 송수신 유형
    public String getSubmitStatus() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_SUBMIT_STATUS));
    }

    public void setSubmitStatus(String submitStatus) {
        this.batchMap.put(Consistent.IF_BATCH_SUBMIT_STATUS, submitStatus);
    }
    
    // row 수
    public String getTotalRows() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_TOTAL_ROWS));
    }

    public void setTotalRows(String totalrows) {
        this.batchMap.put(Consistent.IF_BATCH_TOTAL_ROWS, totalrows);
    }

    // 배치상태에 대한 결과(table 또는 structure 타입의 데이터를 저장)
    public Object getExportObject(String name) {
        return this.batchMap.get(name);
    }

    public void setExportObject(String name, Object obj) {
        this.batchMap.put(name, obj);
    }

    // 에러메시지
    public String getErrorMessage() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_ERROR_MESSAGE));
    }

    public void setErrorMessage(String errormessage) {
        this.batchMap.put(Consistent.IF_BATCH_ERROR_MESSAGE, errormessage);
    }

    // row off set
    public String getOffset() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_OFFSET));
    }

    public void setOffset(String offset) {
        this.batchMap.put(Consistent.IF_BATCH_OFFSET, offset);
    }

    // 1회 조회할 최대 row 수
    public String getMaxRows() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_MAX_ROWS));
    }

    public void setMaxRows(String rows) {
        this.batchMap.put(Consistent.IF_BATCH_MAX_ROWS, rows);
    }

    // 소스 테이블명
    public String getSourceTable() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_SOURCE_TABLE));
    }

    public void setSourceTable(String sourceTable) {
        this.batchMap.put(Consistent.IF_BATCH_SOURCE_TABLE, sourceTable);
    }

    // 대상 테이블 명
    public String getTargetTable() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_TARGET_TABLE));
    }

    public void setTargetTable(String targetTable) {
        this.batchMap.put(Consistent.IF_BATCH_TARGET_TABLE, targetTable);
    }

    // 언어
    public String getLanguage() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_LANGUAGE));
    }

    public void setLanguage(String lang) {
        this.batchMap.put(Consistent.IF_BATCH_LANGUAGE, lang);
    }
    
    // 유효성 체크
    public String getValidCheckYn() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_VALID_CHECK_YN));
    }

    public void setValidCheckYn(String flag) {
        this.batchMap.put(Consistent.IF_BATCH_VALID_CHECK_YN, flag);
    }
    
    // 실행 프로그램 및 URL 정보
    public String getFilePath() {
        return StringHelper.null2void(batchMap.get(Consistent.IF_BATCH_FILE_PATH));
    }

    public void setFilePath(String lang) {
        this.batchMap.put(Consistent.IF_BATCH_FILE_PATH, lang);
    }
    
    // 파라메터 map 정보
    public ParameterVo getParameter() {
        return this.parameterVo;
    }

    public void setParameter(ParameterVo paramVo) {
        this.parameterVo = paramVo;

        if (log.isDebugEnabled()) {
            log.debug("batch excute parameter : " + paramVo.toString());
        }
        
        if(this.getInterfaceMethod().equals("H")) { // 현대차 허브 파라메터(파일명만 파라메터 값으로 등록함)
        	String fileName = StringHelper.null2void(paramVo.getMap().get("XML_FILE_NAME"));
        	
        	this.setInterfaceParam(fileName);
        } else {
        	this.setInterfaceParam(paramVo.toString());
        }
    }

    /**
     * 스케쥴 실행의 기본 정보를 batch VO에 등록
     * 
     * @param map JobVo 객체
     */
    @SuppressWarnings("rawtypes")
    public void setJobInfo(Map map) {
        this.batchMap.put(Consistent.IF_JOB_COMPANY_CD, map.get(Consistent.IF_JOB_COMPANY_CD));
        this.batchMap.put(Consistent.IF_JOB_SCHEDULE_CD, map.get(Consistent.IF_JOB_SCHEDULE_CD));
        this.batchMap.put(Consistent.IF_JOB_CREATE_BY, map.get(Consistent.IF_JOB_CREATE_BY));
        this.batchMap.put(Consistent.IF_JOB_UPDATE_BY, map.get(Consistent.IF_JOB_UPDATE_BY));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setPutAll(Map map) {
        this.batchMap.putAll(map);
    }

    @SuppressWarnings("rawtypes")
    public Map getMap() {
        return ((DataMap) this.batchMap).getMap();
    }

    public String toString() {
        return "Batch Info [" + this.batchMap.getMap() + "]";
    }

    public void clear() {
        this.batchMap.clear();
    }

}
