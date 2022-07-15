package com.yni.fta.common.batch.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.Consistent;

import kr.yni.frame.Constants;
import kr.yni.frame.batch.logger.BatchLogger;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.util.StringHelper;

/**
 * 외부 시스템간 인터페이스를 위한 파라메터를 저장하는 ValueObject
 * 
 * @author jonghyun3.kim
 *
 */
public class JobVo {
    private static Log log = LogFactory.getLog(JobVo.class);

    private DataMap jobMap = new DataMap();

    // 수행된 배치 항목을 가지는 List
    private List<BatchVo> batchList = new ArrayList<BatchVo>();

    private BatchLogger batchLogger; // 배치내용을 파일로 기록하기 위한 로그 클래스
    
    public JobVo() {
    	this.setCreateBy(Constants.APPLICATION_BATCH_ID);
        this.setUpdateBy(Constants.APPLICATION_BATCH_ID);
    }
    
    public String getCompanyCode() {
        return StringHelper.null2void(jobMap.get(Consistent.IF_JOB_COMPANY_CD));
    }

    public void setCompanyCode(String ifJobCompanyCode) {
        this.jobMap.put(Consistent.IF_JOB_COMPANY_CD, ifJobCompanyCode);
    }

    public String getScheduleCode() {
        return StringHelper.null2void(jobMap.get(Consistent.IF_JOB_SCHEDULE_CD));
    }

    public void setScheduleCode(String ifJobScheduleCode) {
        this.jobMap.put(Consistent.IF_JOB_SCHEDULE_CD, ifJobScheduleCode);
    }

    public String getCreateBy() {
        return StringHelper.null2void(jobMap.get(Consistent.IF_JOB_CREATE_BY));
    }

    public void setCreateBy(String ifJobCreateBy) {
        this.jobMap.put(Consistent.IF_JOB_CREATE_BY, ifJobCreateBy);
    }

    public String getUpdateBy() {
        return StringHelper.null2void(jobMap.get(Consistent.IF_JOB_UPDATE_BY));
    }

    public void setUpdateBy(String ifJobUpdateBy) {
        this.jobMap.put(Consistent.IF_JOB_UPDATE_BY, ifJobUpdateBy);
    }

    public String getPyyyymm() {
        return StringHelper.null2void(jobMap.get(Consistent.IF_JOB_YYYYMM));
    }

    public void setPyyyymm(String yyyymm) {
        this.jobMap.put(Consistent.IF_JOB_YYYYMM, yyyymm);
    }

    public String getAliasScheduleCode() {
        return StringHelper.null2void(jobMap.get(Consistent.IF_JOB_ALIAS_SCHEDULE_CD));
    }

    public void setAliasScheduleCode(String ifJobAliasScheduleCode) {
        this.jobMap.put(Consistent.IF_JOB_ALIAS_SCHEDULE_CD, ifJobAliasScheduleCode);
    }

    public String getStatus() {
        return StringHelper.null2void(jobMap.get(Consistent.IF_JOB_STATUS));
    }

    public void setStatus(String ifJobStatus) {
        this.jobMap.put(Consistent.IF_JOB_STATUS, ifJobStatus);
    }

    public BatchLogger getBatchLogger() {
        return batchLogger;
    }

    public void setBatchLogger(BatchLogger batchLogger) {
        this.batchLogger = batchLogger;
    }

    @SuppressWarnings("rawtypes")
    public Map getMap() {
        return ((DataMap) this.jobMap).getMap();
    }

    public String toString() {
        return "Job Info [" + this.jobMap.getMap() + "]";
    }

    public void setBatchList(int idx, BatchVo vo) {
        this.batchList.add(idx, vo);
        if (log.isDebugEnabled())
            log.debug("batch item count = " + this.batchList.size());
    }

    public List<BatchVo> getBatchList() {
        return this.batchList;
    }

    public int getBatchVoSize() {
        return this.batchList.size();
    }
}
