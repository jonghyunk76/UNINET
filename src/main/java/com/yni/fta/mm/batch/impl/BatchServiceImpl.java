package com.yni.fta.mm.batch.impl;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import kr.yni.frame.context.ApplicationContextFactory;
import kr.yni.frame.service.YniAbstractService;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import com.yni.fta.common.beans.target.InterfaceTarget;
import com.yni.fta.mm.batch.BatchService;

@Service("batchService")
public class BatchServiceImpl extends YniAbstractService implements BatchService {

    private InterfaceTarget batchTarget;

    public BatchServiceImpl() {
        ApplicationContext ctx = ApplicationContextFactory.getAppContext();
        batchTarget = (InterfaceTarget) ctx.getBean("batchTargetService");
    }

    @SuppressWarnings("rawtypes")
    public int updateScheduleBatchDate(Map map) throws Exception {
        return batchTarget.updateScheduleBatchDate(map);
    }

    public String selectTransKey() throws Exception {
        return batchTarget.selectTransKey();
    }

    public List<Object> selectRemoteObject(BatchVo batchVo) throws Exception {
        return batchTarget.selectRemoteObject(batchVo);
    }

    @SuppressWarnings("rawtypes")
    public int insertTransInfoData(Map map) throws Exception {
        return batchTarget.insertTransInfoData(map);
    }

    @SuppressWarnings("rawtypes")
    public int insertTransInfoData(Map map, List<Object> source) throws Exception {
        return batchTarget.insertTransInfoData(map);
    }

    public int updateJcoCountData(BatchVo batchVo) throws Exception {
        return batchTarget.updateJcoCountData(batchVo);
    }

    public int insertTransDtlData(BatchVo batchVo, List<Object> source) throws Exception {
        return batchTarget.insertTransDtlData(batchVo, source);
    }

    @SuppressWarnings("rawtypes")
    public int updateTransInfoData(Map map) throws Exception {
        return batchTarget.updateTransInfoData(map);
    }

    @SuppressWarnings("rawtypes")
    public List<Object> selectSchedulerItemList(Map map) throws Exception {
        return batchTarget.selectSchedulerItemList(map);
    }
    
    @SuppressWarnings("rawtypes")
    public Map<String, Object> selectSchedulerItemDetail(Map map) throws Exception {
        return batchTarget.selectSchedulerItemDetail(map);
    }

    public int insertToTargetData(BatchVo batchVo, List<Object> target) throws Exception {
        return batchTarget.insertToTargetData(batchVo, target);
    }

    public int insertTargetFromSourceData(BatchVo batchVo, List<Object> target) throws Exception {
        return batchTarget.insertTargetFromSourceData(batchVo, target);
    }
    
    public List<Object> getResultMessage() {
        return batchTarget.getResultMessage();
    }

    @SuppressWarnings("rawtypes")
    public List<Object> selectInterfaceScheduleList(Map map) throws Exception {
        return batchTarget.selectInterfaceScheduleList(map);
    }

    @SuppressWarnings("rawtypes")
    public List<Object> selectInterfaceMappingList(Map map) throws Exception {
        return batchTarget.selectInterfaceMappingList(map);
    }
    
    @SuppressWarnings("rawtypes")
    public List<Object> selectServiceMappingList(Map map) throws Exception {
        return batchTarget.selectServiceMappingList(map);
    }
    
    @SuppressWarnings("rawtypes")
    public List<Object> selectServicePlanList(Map map) throws Exception {
        return batchTarget.selectServicePlanList(map);
    }
    
    public String selectServiceTranID() throws Exception {
        return batchTarget.selectServiceTranID();
    }
    
    @SuppressWarnings("rawtypes")
	public int insertServiceHistory(Map map) throws Exception {
        return batchTarget.insertServiceHistory(map);
    }
    
    @SuppressWarnings("rawtypes")
	public int updateServiceHistoryStatus(Map map) throws Exception {
        return batchTarget.updateServiceHistoryStatus(map);
    }
    
    @SuppressWarnings("rawtypes")
	public int updateServiceResetHistory(Map map) throws Exception {
        return batchTarget.updateServiceResetHistory(map);
    }
    
    @SuppressWarnings("rawtypes")
	public List<Object> selectSendDataList(Map map) throws Exception {
        return batchTarget.selectSendDataList(map);
    }
    
    @SuppressWarnings("rawtypes")
	public List<Object> selectInterfaceBatchItemList(Map map) throws Exception {
        return batchTarget.selectInterfaceBatchItemList(map);
    }
    
    @SuppressWarnings("rawtypes")
    public List<Object> selectBatchResultInfo(Map map) throws Exception {
        return batchTarget.selectBatchResultInfo(map);
    }

    public int deleteInterfaceTable(List<Object> target) throws Exception {
        return batchTarget.deleteInterfaceTable(target);
    }

    @SuppressWarnings("rawtypes")
    public int updateInterfaceScheduleDate(Map map) throws Exception {
        return batchTarget.updateInterfaceScheduleDate(map);
    }

    @SuppressWarnings("rawtypes")
    public Map executeProcedureProcess(Map map) throws Exception {
        return batchTarget.executeProcedureProcess(map);
    }

    @SuppressWarnings("rawtypes")
    public int updateProcedureResult(Map map) throws Exception {
        return batchTarget.updateProcedureResult(map);
    }

    @SuppressWarnings("rawtypes")
    public int updateFinishedTime(Map map) throws Exception {
        return batchTarget.updateFinishedTime(map);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List selectResultMessage(Map map) throws Exception {
        return batchTarget.selectResultMessage(map);
    }

    @SuppressWarnings("rawtypes")
    public List selectInterfaceSchedulerDetail(Map map) throws Exception {
        return batchTarget.selectInterfaceSchedulerDetail(map);
    }
    
    @SuppressWarnings("rawtypes")
    public List selectDivisionList(Map map) throws Exception {
        return batchTarget.selectDivisionList(map);
    }
    
    public boolean selectConnect2Server(BatchVo batchVo) throws Exception {
        return batchTarget.selectConnect2Server(batchVo);
    }

	public boolean selectCertifyHubCompany(BatchVo batchVo) throws Exception {
		return batchTarget.selectCertifyHubCompany(batchVo);
	}

	public List selectPoVendorForHubList(JobVo jobVo) throws Exception {
		return batchTarget.selectPoVendorForHubList(jobVo);
	}

	public List selectHubOrganizatList(Map map) throws Exception {
		return batchTarget.selectHubOrganizatList(map);
	}

	public List selectHubNoComplateList(Map map) throws Exception {
		return batchTarget.selectHubNoComplateList(map);
	}

	@Override
	public List selectSourceTable(Map map) throws Exception {
		return batchTarget.selectSourceTable(map);
	}

	@Override
	public List<Object> selectFtaPassDoList(Map map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePassReqStatus(Map map) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
