package com.yni.fta.common.batch.period;

import com.yni.fta.common.batch.BatchProcess;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;

public class ServiceProcess extends BatchProcess {
	
	public ServiceProcess(JobVo job, BatchVo batch) {
		super(job.getBatchLogger());

        this.setJobVo(job);
        this.setBatchVo(batch);
	}
	
	@Override
	protected boolean applyBatch() throws Exception {
		return super.startService();
	}
	
}
