package com.yni.fta.common.batch.period;

import com.yni.fta.common.batch.BatchFactory;
import com.yni.fta.common.batch.BatchProcess;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import kr.yni.frame.batch.logger.BatchLogger;
import kr.yni.frame.batch.logger.impl.BatchLoggerImpl;
import kr.yni.frame.util.StringHelper;

public class ProcessFactory extends BatchFactory {

    /**
     * 
     * @param jobVo 스케쥴 정보
     * @return 실제 배치작업을 수행하는 비지니스 로직을 포함한 클래스
     */
    public BatchProcess createProcess(BatchVo batchVo, BatchLogger logger) throws Exception {
    	if(logger == null) logger = new BatchLoggerImpl();
    	
        return new ProcedureProcess(batchVo, logger);
    }

    /**
     * 인터페이스를 처리하는 클래스 생성
     * 
     * @param jobVo 스케쥴 정보
     * @param batchVo 배치정보
     * @return 실제 배치작업을 수행하는 비지니스 로직을 포함한 클래스
     */
    public BatchProcess createProcess(JobVo jobVo, BatchVo batchVo) throws Exception {
        BatchProcess process = null;

        if(StringHelper.null2void(batchVo.getItemType()).equals("T")) { // 테이블 타입의 데이터 인터페이스용
            process = new DataTransProcess(jobVo, batchVo);
        } else if(StringHelper.null2void(batchVo.getItemType()).equals("P")) { // 배치 타입의 프로시져 수행
            process = new ProcedureProcess(jobVo, batchVo);
        } else if(StringHelper.null2void(batchVo.getItemType()).equals("C")) { // HTTP 프로토콜 타입의 인터페이스용
            process = new DataTransProcess(jobVo, batchVo);
        } else if(StringHelper.null2void(batchVo.getItemType()).equals("S")) { // 서비스 수행
            process = new ServiceProcess(jobVo, batchVo);
        }

        return process;
    }

}
