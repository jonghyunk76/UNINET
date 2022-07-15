package com.yni.fta.common.batch.period;

import com.yni.fta.common.batch.BatchProcess;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;

/**
 * 타 시스템을 통한 데이터 인터페이스를 처리하는 클래스
 * 
 * @author jonghyun3.kim
 *
 */
public class DataTransProcess extends BatchProcess {

    public DataTransProcess() {
    }

    public DataTransProcess(JobVo job, BatchVo batch) {
        super(job.getBatchLogger());

        this.setJobVo(job);
        this.setBatchVo(batch);
    }

    protected boolean applyBatch() throws Exception {
        return super.startBatch();
    }

}
