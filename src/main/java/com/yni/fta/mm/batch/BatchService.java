package com.yni.fta.mm.batch;

import java.util.List;
import java.util.Map;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;

public interface BatchService {
	
	/**
     * 인터페이스 마스터키를 조회하는 매소드
     * 
     * @return
     * @throws Exception
     */
    public String selectTransKey() throws Exception;

    /**
     * RFC인터페이스을 통해 ERP데이터를 구하는 매소드
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectRemoteObject(BatchVo batchVo) throws Exception;

    /**
     * 소스데이터 이관 마스터 정보 저장하는 매소드
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int insertTransInfoData(Map map) throws Exception;

    /**
     * ERP데이터에서 추출한 데이터 건수 업데이트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateJcoCountData(BatchVo batchVo) throws Exception;

    /**
     * ERP에서 획득한 데이터을 전송테이블에 저장하는 매소드
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertTransDtlData(BatchVo batchVo, List<Object> source) throws Exception;

    /**
     * ERP에서 획득한 데이터을 전송테이블에 저장한 결과를 업데이트하는 매소드
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    int updateTransInfoData(Map map) throws Exception;

    /**
     * 검증할 테이블및 컬럼, 속성 조회하는 매소드
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    List<Object> selectSchedulerItemList(Map map) throws Exception;
    
    /**
     * 항목 마스터의 상세정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    Map<String, Object> selectSchedulerItemDetail(Map map) throws Exception;
    
    /**
     * 이력 테이블에 있는 데이터를 이관 대상 테이블로 이관<BR> 
     * - 이력 테이블에 에러가 0개인 경우 데이터 이관
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int insertToTargetData(BatchVo batchVo, List<Object> target) throws Exception;
    
    /**
     * 소스 테이블에 있는 데이터를 이관 대상 테이블로 이관<BR> 
     * - 이력 테이블에 에러가 0개인 경우 데이터 이관
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int insertTargetFromSourceData(BatchVo batchVo, List<Object> target) throws Exception;
    
    /**
     * 처리 결과 리턴
     * 
     * @return
     */
    List<Object> getResultMessage();

    /**
     * 스케쥴 정보 조회
     * 
     * @param map
     * @return 스케쥴 정보
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    List<Object> selectInterfaceScheduleList(Map map) throws Exception;

    /**
     * 스케쥴-인터페이스 매핑 정보 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    List<Object> selectInterfaceMappingList(Map map) throws Exception;
    
    /**
     * 스케쥴 서비스 목록 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List<Object> selectServiceMappingList(Map map) throws Exception;
    
    /**
     * 서비스 Plan 목록 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List<Object> selectServicePlanList(Map map) throws Exception;
    
    /**
     * 서비스 이력키를 조회하는 매소드
     * 
     * @return
     * @throws Exception
     */
    String selectServiceTranID() throws Exception;
    
    /**
     * 서비스 이력 등록
     * 
     * @return
     * @throws Exception
     */
    int insertServiceHistory(Map map) throws Exception;
    
    /**
     * 서비스 이력정보 업데이트
     * 
     * @return
     * @throws Exception
     */
    int updateServiceHistoryStatus(Map map) throws Exception;
    
    /**
     * 서비스 이력을 이용한 재전송 정보 초기화
     * 
     * @return
     * @throws Exception
     */
    int updateServiceResetHistory(Map map) throws Exception;
    
    /**
     * 송신할 데이터 인터페이스 이력에서 수신 파라메터 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    String selectSendParameterInfo(Map map) throws Exception;
    
    /**
     * 송신할 데이터 인터페이스 이력에서 수신 데이터 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    List<Object> selectSendDataList(Map map) throws Exception;
    
    /**
     * 인터페이스 될 배치항목을 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    List<Object> selectInterfaceBatchItemList(Map map) throws Exception;
    
    /**
     * 배치실행결과를 리턴
     * 
     * @param map
     * @return 배치실행 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    List<Object> selectBatchResultInfo(Map map) throws Exception;

    /**
     * 인터페이스 중에 실패가 발생하면 이와 관련된 인터페이스 테이블의 모든 데이터를 삭제
     * 
     * @param target
     *            삭제할 테이블 정보
     * @return 삭제 결과
     * @throws Exception
     */
    int deleteInterfaceTable(List<Object> target) throws Exception;

    /**
     * 스케쥴 실행일자 업데이트
     * 
     * @param map
     *            파라메터 정보
     * @return 업데이터 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    int updateInterfaceScheduleDate(Map map) throws Exception;

    /**
     * 마감업무를 담당하는 프로시져 호출
     * 
     * @param map
     * @return 배치실행 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    Map executeProcedureProcess(Map map) throws Exception;

    /**
     * Procedure 수행 후 실패한 경우 전송마스터에 실패 메시지로 업데이트한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    int updateProcedureResult(Map map) throws Exception;

    /**
     * 마지막 완료된 시간을 기록한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    int updateFinishedTime(Map map) throws Exception;
    
    /**
     * 배치실행 결과를 리턴
     * 
     * @param map
     * @return 배치실행 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    List<Object> selectResultMessage(Map map) throws Exception;
    
    /**
     * 스케쥴 상세 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    List selectInterfaceSchedulerDetail(Map map) throws Exception;
    
    /**
     * 배치를 수행할 사업부를 조회한다.
     * 
     * @param map
     * @return 시스템 설정정보
     * @throws Exception
     */
    List<Object> selectDivisionList(Map map) throws Exception;
    
    /**
     * 서버 연결 체크
     * 
     * @param batchVo
     * @throws Exception
     */
    boolean selectConnect2Server(BatchVo batchVo) throws Exception;
    
    /**
     * TOMS Cloud 가입사 라이센스 인증
     * 
     * @param batchVo
     * @throws Exception
     */
    boolean selectCertifyHubCompany(BatchVo batchVo) throws Exception;
    
    /**
	 * HUB 가입 협력사 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectPoVendorForHubList(JobVo jobVo) throws Exception;
	
	/**
	 * 현대차 연계 조직별 ID구하기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectHubOrganizatList(Map map) throws Exception;
	
	/**
	 * 송신된 제출서중 처리되지 않은 목록 구하기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectHubNoComplateList(Map map) throws Exception;
	
	/**
	 * 인터페이스 소스테이블 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectSourceTable(Map map) throws Exception;
	
	/**
     * FTA PASS와 인터페이스할 대상정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List<Object> selectFtaPassDoList(Map map) throws Exception;
    
    /**
     * FTA PASS 요청 상태 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int updatePassReqStatus(Map map) throws Exception;
	
}
