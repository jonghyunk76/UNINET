package com.yni.fta.common.batch;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import kr.yni.frame.batch.logger.BatchLogger;

public abstract class BatchFactory {

    /**
     * 인터페이스 방식에 따라 인스턴스를 생성하고 배치를 관리하는 매소드
     * 
     * @param job 배치수행 정보
     * @return
     */
    public final BatchProcess cerate(BatchVo batchVo, BatchLogger logger) throws Exception {
        BatchProcess processor = this.createProcess(batchVo, logger);

        return processor;
    }

    public final BatchProcess cerate(JobVo jobVo, BatchVo batchVo) throws Exception {
        BatchProcess processor = this.createProcess(jobVo, batchVo);

        return processor;
    }

    // 인터페이스 완료 후 실업무 테이블로 이관하기 위한 프로시져 호출
    protected abstract BatchProcess createProcess(BatchVo batchVo, BatchLogger logger) throws Exception;

    // 외부 시스템간의 인터페이스를 처리하기 위한 배치 프로세스 실행
    protected abstract BatchProcess createProcess(JobVo jobVo, BatchVo batchVo) throws Exception;

}
