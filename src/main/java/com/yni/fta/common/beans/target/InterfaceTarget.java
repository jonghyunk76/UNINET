package com.yni.fta.common.beans.target;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import com.yni.fta.mm.batch.BatchService;
import com.yni.fta.mm.batch.impl.BatchDao;
import com.yni.fta.mm.batch.impl.CloudTransDao;
import com.yni.fta.mm.batch.impl.RFCTransDAO;
import com.yni.fta.mm.batch.impl.ExcelTransDao;
import com.yni.fta.mm.batch.impl.HubTransDAO;
import com.yni.fta.mm.batch.impl.POTransDao;
import com.yni.fta.mm.batch.impl.PassTransDao;
import com.yni.fta.mm.batch.impl.RPCTransDao;
import com.yni.fta.mm.batch.impl.SystemTransDao;

import kr.yni.frame.batch.logger.BatchLogger;
import kr.yni.frame.batch.logger.impl.BatchLoggerImpl;
import kr.yni.frame.util.StringHelper;

public class InterfaceTarget implements BatchService {

    private static Log log = LogFactory.getLog(InterfaceTarget.class);

    // 내부 비지니스 로직를 처리하는 DAO
    private BatchDao batchDao;

    // Remote Function Controller(SAP RFC용)
    private RFCTransDAO rfcTransDAO;
    
    // Remote Function Controller(SAP PO용)
    private POTransDao poTransDao;

    // Remote Procedure Controller(MS-SQL, Oracle용)
    private RPCTransDao rpcTransDao;

    // Remote Procedure Controller(Excel 용)
    private ExcelTransDao excelTransDao;
    
    // XML 파싱(현대차 허브용)
 	private HubTransDAO hubTransDAO;
 	
    // TOMS Cloud HUB 연계
  	private CloudTransDao cloudTransDao;
 	
  	// FTA PASS HUB 연계
   	private PassTransDao passTransDao;
   	
   	private SystemTransDao systemTransDao;
   	
    // 배치내용을 파일로 기록하기 위한 로그 클래스
    BatchLogger logger;

    public InterfaceTarget() {
        logger = new BatchLoggerImpl();
    }

    public void setBatchDao(BatchDao dao) {
        if (this.batchDao == null) {
            this.batchDao = dao;
        }
        
        log.debug("Creating the batchDao....");
    }

    public void setRpcTransDao(RPCTransDao rpcTransDao) {
        if (this.rpcTransDao == null) {
            this.rpcTransDao = rpcTransDao;
        }
        
        log.debug("Creating the rpcTransDao....");
    }

    public void setPoTransDao(POTransDao poTransDao) {
        if (this.poTransDao == null) {
            this.poTransDao = poTransDao;
        }
        
        log.debug("Creating the poTransDao....");
    }
    
    public void setRfcTransDAO(RFCTransDAO rfcTransDAO) {
    	if(this.rfcTransDAO == null) {
    		this.rfcTransDAO = rfcTransDAO;
    	}
    	
    	log.debug("Creating RFCTransDAO....");
    }

    public void setExcelTransDao(ExcelTransDao excelTransDao) {
        if (this.excelTransDao == null) {
            this.excelTransDao = excelTransDao;
        }
        
        log.debug("Creating the excelTransDao....");
    }
    
    public void setHubTransDAO(HubTransDAO hubTransDAO) {
        if (this.hubTransDAO == null) {
            this.hubTransDAO = hubTransDAO;
        }
        
        log.debug("Creating the hubTransDAO....");
    }
    
    public void setCloudTransDao(CloudTransDao cloudTransDao) {
        if (this.cloudTransDao == null) {
            this.cloudTransDao = cloudTransDao;
        }
        
        log.debug("Creating the cloudTransDao....");
    }
    
    public void setPassTransDao(PassTransDao passTransDao) {
        if (this.passTransDao == null) {
            this.passTransDao = passTransDao;
        }
        
        log.debug("Creating the passTransDao....");
    }
    
    public void setSystemTransDao(SystemTransDao systemTransDao) {
        if (this.systemTransDao == null) {
            this.systemTransDao = systemTransDao;
        }
        
        log.debug("Creating the systemTransDao....");
    }
    
    /**
     * 사용자 정보를 확인하고 정보를 수집하는 매소드
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public List<Map> checkMemberInfo(Map<String, Object> map) throws Exception {
        return null;// mainDAO.selectMember(map);
    }
    
    /**
     * ERP데이터를 구하고, ERP데이터의 유효성 검증결과을 포함한 ERP데이터를 생성하는 매소드
     * 
     * @param map
     * @return List ERP데이터의 유효성 검증결과을 포함한 ERP데이터
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectRemoteObject(BatchVo batchVo) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용

        List list = new ArrayList();
        List erpData = null;

        try {
            list.add(batchVo.getMap());

            logger.logStart("InterfaceTarget", list);

            String method = StringHelper.null2void(batchVo.getInterfaceMethod());
            if (log.isDebugEnabled())
                log.debug("Interface Mathod = " + method);
        
        	if ("S".equals(method)) { // SAP(RFC)
                erpData = rfcTransDAO.callRemoteFunction(batchVo);
            } else if("A".equals(method)) { // A:SAP(PO)-http,webservice 통신
        		erpData = poTransDao.callRemoteFunction(batchVo);
        	} else if ("O".equals(method)) { // Oracle
                erpData = rpcTransDao.callRemoteProcedure(batchVo);
            } else if ("M".equals(method)) { // MS-SQL or PostgreSql
                erpData = rpcTransDao.callRemoteProcedure(batchVo);
            } else if ("E".equals(method)) { // Excel
                erpData = excelTransDao.callLocalExcelParsing(batchVo);
            } else if("H".equals(method)) {	// XML 송수신(현대차 HUB용)
        		erpData = hubTransDAO.callHubXMLParsing(batchVo);
        	} else if("C".equals(method)) { // TOMS HUB
        		erpData = cloudTransDao.callRemoteFunction(batchVo);
        	} else if("F".equals(method)) {	// XML 송수신(FTA PASS용)
        		erpData = passTransDao.callHubXMLParsing(batchVo);
        	} else if("P".equals(method)) {	// Python 연계
        		erpData = systemTransDao.callRemotePython(batchVo);
        	}
            
            if (erpData == null || erpData.size() < 1) {
                logger.logMessage("RFC Data : null or empty");
            } else {
            	if (log.isDebugEnabled()) log.debug("call remote function's result : " + erpData.size());
            }

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished selectFunctionList().(execute time=" + (ftime - stime) + " msec.)");
        } catch (Exception exp) {
            logger.logMessage(exp.toString());
            logger.logEnd(false);
            throw exp;
        }

        return erpData;
    }

    /**
     * 배치시작시간 업데이트
     * 
     * @param map 파라메터 데이터
     * @return 등록결과
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateScheduleBatchDate(Map map) throws Exception {
        int rstCnt = 0;

        try {
            rstCnt = batchDao.updateScheduleBatchDate(map);
        } catch (Exception exp) {
            throw exp;
        }

        return rstCnt;
    }

    /**
     * 인터페이스의 마스터Key 조회
     * 
     * @return 트랜잭션ID
     * @throws Exception
     */
    public String selectTransKey() throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        String key = null;

        try {
            key = batchDao.selectTransKey();

            logger.setTransactionID(key);
            long ftime = System.currentTimeMillis();
            logger.logMessage("finished selectTransKey().(execute time=" + (ftime - stime) + " msec. | transaction key=" + key + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return key;
    }

    /**
     * 소스데이터 이관 마스터 정보 셋팅
     * 
     * @param map 파라메터 데이터
     * @return 등록결과
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int insertTransInfoData(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;

        try {
            rstCnt = batchDao.insertTransInfoData(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished insertTransInfoData().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return rstCnt;
    }

    /**
     * ERP데이터에서 추출한 데이터 건수 업데이트
     * 
     * @param map 파라메터 데이터
     * @param source 저장할 소스 데이터
     * @param id 트랜잭션ID
     * @return 등록결과
     * @throws Exception
     */
    public int updateJcoCountData(BatchVo batchVo) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용

        int rstCnt = 0;

        try {
            rstCnt = batchDao.updateJcoCountData(batchVo.getMap());

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished updateJcoCountData().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return rstCnt;
    }
    
    /**
     * ERP데이터에서 추출한 데이터 건수 업데이트
     * 
     * @param source 저장할 소스 데이터
     * @param id 트랜잭션ID
     * @return 등록결과
     * @throws Exception
     */
    public int insertTransDtlData(List<Object> source) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용

        int rstCnt = 0;

        try {
            rstCnt = batchDao.insertTransDtlData(source);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished updateJcoCountData().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return rstCnt;
    }
    
    /**
     * ERP에서 획득한 데이터을 전송테이블에 이관
     * 
     * @param map 파라메터 데이터
     * @param source 저장할 소스 데이터
     * @param id 트랜잭션ID
     * @return 등록결과(0인 경우 정상처리임)
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int insertTransDtlData(BatchVo batchVo, List<Object> source) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = -1;

        try {
        	String validCheck = StringHelper.null2void(batchVo.getValidCheckYn());
        	boolean valid = true;
        	if(validCheck.equals("N")) valid = false;
        	
        	if(valid) {
	            Map<String, Object> colMap;
	            List<Object> colList = new ArrayList<Object>();
	            
	            // 입력번호를 달아 등록한다.
	            for (int i = 0; i < source.size(); i++) {
	                colMap = new LinkedHashMap<String, Object>();
	                
	                colMap.put(Consistent.IF_BATCH_TRANS_DATA_ID, batchVo.getInterfaceCode());
	                colMap.put(Consistent.IF_BATCH_TRANS_ID, batchVo.getTransId());
	                colMap.put(Consistent.IF_BATCH_TRANS_SEQ, i);
	                colMap.put(Consistent.IF_JOB_CREATE_BY, batchVo.getMap().get(Consistent.IF_JOB_CREATE_BY));
	
	                Map<String, Object> data = (LinkedHashMap<String, Object>) source.get(i);
	                Iterator iterator = data.entrySet().iterator();
	
	                while (iterator.hasNext()) {
	                    Map.Entry entry = (Map.Entry) iterator.next();
	                    colMap.put(entry.getKey().toString().toUpperCase(), entry.getValue());
	                }
	                
	                colList.add(colMap);
	            }
	
	            rstCnt = batchDao.insertTransDtlData(colList); // 성공시 0을 리턴한다.
        	} else { // 유효성 체크를 하지 않는 경우에는 DB대DB로 테이블간 바로 이관시킨다.(2019-12-17)
        		Map colMap = new LinkedHashMap<String, Object>();
        		Map targetInfo = (Map) source.get(0);
                
                colMap.put(Consistent.IF_BATCH_SOURCE_TABLE, targetInfo.get("SOURCE_TABLE"));
                colMap.put(Consistent.IF_JOB_COMPANY_CD, batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
        		colMap.put(Consistent.IF_BATCH_TRANS_DATA_ID, batchVo.getInterfaceCode());
                colMap.put(Consistent.IF_BATCH_TRANS_ID, batchVo.getTransId());
                colMap.put(Consistent.IF_JOB_CREATE_BY, batchVo.getMap().get(Consistent.IF_JOB_CREATE_BY));
                colMap.put("itemList", source);
                
        		rstCnt = batchDao.insertDirectToTargetData(colMap); // 성공시 0을 리턴한다.
        	}
        	
            long ftime = System.currentTimeMillis();
            logger.logMessage("finished insertTransDtlData().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return rstCnt;
    }

    /**
     * 이관결과 업데이트
     * 
     * @param map 파라메터 정보
     * @return 업데이터 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int updateTransInfoData(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;
        try {
            rstCnt = batchDao.updateTransInfoData(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished updateTransInfoData().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return rstCnt;
    }

    /**
     * 대상 테이블의 컬럼, 속성 조회하는 매소드
     * 
     * @param map 파라메터 데이터
     * @return 인터페이스 테이블 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectSchedulerItemList(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectSchedulerItemList(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("target info : " + rstList.get(0));
            logger.logMessage("finished selectSchedulerItemList().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.size() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }
    
    /**
     * 대상 테이블의 컬럼, 속성 조회하는 매소드
     * 
     * @param map 파라메터 데이터
     * @return 인터페이스 테이블 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map<String, Object> selectSchedulerItemDetail(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        Map rstList = null;

        try {
            rstList = batchDao.selectSchedulerItemDetail(map);

            long ftime = System.currentTimeMillis();
            if(rstList.size() > 0) {
            	logger.logMessage("target info : " + rstList);
            }
            logger.logMessage("finished selectSchedulerItemList().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.size() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }

    /**
     * 이력 테이블에 있는 데이터를 이관 대상 테이블로 이관<BR> 
     * - 이력 테이블에 에러가 0개인 경우 데이터 이관
     * 
     * @param map 파라메터 정보
     * @param target 인터페이스 테이블 정보
     * @return 등록결과
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int insertToTargetData(BatchVo batchVo, List<Object> target) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;

        try {
            if (target.size() > 0) {
                LinkedHashMap inMap = new LinkedHashMap();
                Map targetInfo = (Map) target.get(0);
                
                // 대상 테이블 내역을 삭제한다.
                int delcnt = batchDao.deleteInterfaceTable(target);
                
                logger.logMessage("delete interface table[parameter = "+targetInfo.toString()+", delete count = " + delcnt + "rows.]");
                
                inMap.put(Consistent.IF_JOB_COMPANY_CD, batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
                inMap.put(Consistent.IF_BATCH_SOURCE_TABLE, targetInfo.get("SOURCE_TABLE"));
                inMap.put(Consistent.IF_BATCH_TARGET_TABLE, targetInfo.get("TARGET_TABLE"));
                inMap.put(Consistent.IF_BATCH_TRANS_ID, batchVo.getTransId());
                inMap.put(Consistent.IF_JOB_CREATE_BY, batchVo.getMap().get(Consistent.IF_JOB_CREATE_BY));
                inMap.put("itemList", target);

                logger.logMessage("insertToTargetData parameter : " + inMap);
                
                // 소스 테이블 내역을 대상 테이블에 등록한다.
                rstCnt = batchDao.insertToTargetData(inMap);
            } else {
                if (log.isErrorEnabled()) {
                    log.error("not found schedule Info.");
                }
            }

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished insertToTargetData().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        logger.logEnd(true);

        return rstCnt;
    }
    
    /**
     * 소스 테이블에 있는 데이터를 이관 대상 테이블로 이관<BR> 
     * - 이력 테이블에 에러가 0개인 경우 데이터 이관
     * 
     * @param map 파라메터 정보
     * @param target 인터페이스 테이블 정보
     * @return 등록결과
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int insertTargetFromSourceData(BatchVo batchVo, List<Object> target) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;

        try {
            if (target.size() > 0) {
                LinkedHashMap inMap = new LinkedHashMap();

                Map targetInfo = (Map) target.get(0);

                inMap.put(Consistent.IF_BATCH_SOURCE_TABLE, targetInfo.get("SOURCE_TABLE"));
                inMap.put(Consistent.IF_BATCH_TARGET_TABLE, targetInfo.get("TARGET_TABLE"));
                inMap.put(Consistent.IF_BATCH_TRANS_ID, batchVo.getTransId());
                inMap.put(Consistent.IF_JOB_CREATE_BY, batchVo.getMap().get(Consistent.IF_JOB_CREATE_BY));
                inMap.put(Consistent.IF_JOB_COMPANY_CD, batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
                inMap.put("itemList", target);

                logger.logMessage("insertTargetFromSourceData parameter : " + inMap);
                
                // 소스 테이블 내역을 대상 테이블에 등록한다.
                rstCnt = batchDao.insertTargetFromSourceData(inMap);
            } else {
                if (log.isErrorEnabled()) {
                    log.error("not found schedule Info.");
                }
            }

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished insertTargetFromSourceData().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        logger.logEnd(true);

        return rstCnt;
    }
    
    /**
     * 스케쥴 정보 조회
     * 
     * @param map
     * @return 스케쥴 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectInterfaceScheduleList(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectInterfaceScheduleList(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("schedule info", rstList);
            logger.logMessage("(check)interface schedule info.(execute time=" + (ftime - stime) + " msec. | result=" + rstList + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }
        return rstList;
    }

    /**
     * 스케쥴-인터페이스 매핑 정보 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectInterfaceMappingList(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectInterfaceMappingList(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("schedule-interface mapping info : ", rstList);
            logger.logMessage("finished selectInterfaceMappingList().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.size() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }
    
    /**
     * 스케쥴 서비스 목록 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectServiceMappingList(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectServiceMappingList(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("schedule-interface mapping info : ", rstList);
            logger.logMessage("finished selectServiceMappingList().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.size() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }

    /**
     * 서비스 Plan 목록 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectServicePlanList(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectServicePlanList(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("schedule-interface mapping info : ", rstList);
            logger.logMessage("finished selectServicePlanList().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.size() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }
    
    /**
     * 서비스 이력 Key 조회
     * 
     * @return 트랜잭션ID
     * @throws Exception
     */
    public String selectServiceTranID() throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        String key = null;

        try {
            key = batchDao.selectServiceTranID();

            logger.setTransactionID(key);
            long ftime = System.currentTimeMillis();
            logger.logMessage("finished selectServiceTranID().(execute time=" + (ftime - stime) + " msec. | service key=" + key + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            
            throw exp;
        }

        return key;
    }
    
    /**
     * 서비스 이력 등록
     * 
     * @param map 파라메터 정보
     * @return 업데이터 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int insertServiceHistory(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;
        try {
            rstCnt = batchDao.insertServiceHistory(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished insertServiceHistory().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return rstCnt;
    }
    
    /**
     * 서비스 이력정보 업데이트
     * 
     * @param map 파라메터 정보
     * @return 업데이터 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int updateServiceHistoryStatus(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;
        try {
            rstCnt = batchDao.updateServiceHistoryStatus(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished updateServiceHistoryStatus().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return rstCnt;
    }
    
    /**
     * 서비스 이력을 이용한 재전송 정보 초기화
     * 
     * @param map 파라메터 정보
     * @return 업데이터 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int updateServiceResetHistory(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;
        try {
            rstCnt = batchDao.updateServiceResetHistory(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished updateServiceResetHistory().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);
            throw exp;
        }

        return rstCnt;
    }
    
    /**
     * 송신할 데이터 인터페이스 이력에서 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectSendDataList(Map map) throws Exception {
        List rstList = null;

        try {
            rstList = batchDao.selectSendDataList(map);
        } catch (Exception exp) {
            throw exp;
        }

        return rstList;
    }
    
    /**
     * 인터페이스 될 배치항목을 조회
     * 
     * @param map
     * @return 인터페이스 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectInterfaceBatchItemList(Map map) throws Exception {
        List rstList = null;

        try {
            rstList = batchDao.selectInterfaceBatchItemList(map);
        } catch (Exception exp) {
            throw exp;
        }

        return rstList;
    }
    
    /**
     * 배치실행결과를 리턴
     * 
     * @param map
     * @return 배치실행 결과
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectBatchResultInfo(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectBatchResultInfo(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("batch result info : ", rstList);
            logger.logMessage("finished selectBatchResultInfo().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.size() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }

    /**
     * 인터페이스 중에 실패가 발생하면 이와 관련된 인터페이스 테이블의 모든 데이터를 삭제
     * 
     * @param target 삭제할 테이블 정보
     * @return 삭제 결과
     * @throws Exception
     */
    public int deleteInterfaceTable(List<Object> target) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용

        int rstCnt = 0;

        try {
            rstCnt = batchDao.deleteInterfaceTable(target);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished deleteInterfaceTable().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstCnt;
    }

    /**
     * 스케쥴 실행일자 업데이트
     * 
     * @param map 파라메터 정보
     * @return 업데이터 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int updateInterfaceScheduleDate(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용

        int rstCnt = 0;

        try {
            rstCnt = batchDao.updateInterfaceScheduleDate(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished updateInterfaceScheduleDate().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstCnt;
    }

    /**
     * 마감업무를 담당하는 프로시져 호출
     * 
     * @param map
     * @return 배치실행 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map executeProcedureProcess(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용

        Map rstObj = null;

        try {
            rstObj = batchDao.executeProcedureProcess(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished executeMasterProcess().(execute time=" + (ftime - stime) + " msec. | result =" + rstObj + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstObj;
    }

    /**
     * 스케쥴 실행일자 업데이트
     * 
     * @param map 파라메터 정보
     * @return 업데이터 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int updateProcedureResult(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;

        try {
            rstCnt = batchDao.updateProcedureResult(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished updateProcedureResult().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstCnt;
    }

    /**
     * 마지막 완료된 시간을 기록한다.
     * 
     * @param map 파라메터 정보
     * @return 업데이터 결과
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int updateFinishedTime(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        int rstCnt = 0;

        try {
            rstCnt = batchDao.updateFinishedTime(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished updateFinishedTime().(execute time=" + (ftime - stime) + " msec. | result count=" + rstCnt + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstCnt;
    }

    /**
     * 배치실행결과를 리턴
     * 
     * @param map
     * @return 배치실행 결과
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectResultMessage(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectResultMessage(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("batch result info : ", rstList);
            logger.logMessage("finished selectResultMessage().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.size() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }

    /**
     * 배치 실행할 기준년월을 조회한다.
     * 
     * @param map
     * @return 시스템 설정정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectBatchYYYYMM(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectBatchYYYYMM(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage("finished selectBatchYYYYMM().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.toString() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }

    /**
     * 스케쥴 상세 정보를 조회한다.
     * 
     * @param map
     * @return 시스템 설정정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectInterfaceSchedulerDetail(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectInterfaceSchedulerDetail(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage(
                    "finished selectInterfaceSchedulerDetail().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.toString() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }
    
    /**
     * 배치를 수행할 사업부를 조회한다.
     * 
     * @param map
     * @return 시스템 설정정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> selectDivisionList(Map map) throws Exception {
        long stime = System.currentTimeMillis(); // 수행 시간 계산용
        List rstList = null;

        try {
            rstList = batchDao.selectDivisionList(map);

            long ftime = System.currentTimeMillis();
            logger.logMessage(
                    "finished selectDivisionList().(execute time=" + (ftime - stime) + " msec. | result count=" + rstList.toString() + ")");
        } catch (Exception exp) {
            logger.logMessage(exp.getMessage());
            logger.logEnd(false);

            throw exp;
        }

        return rstList;
    }
    
    public List<Object> getResultMessage() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    /**
     * 서버 연결 체크
     * 
     * @param batchVo
     * @throws Exception
     */
    public boolean selectConnect2Server(BatchVo batchVo) throws Exception {
    	return cloudTransDao.selectConnect2Server(batchVo);
    }
    
    /**
     * TOMS Cloud 가입사 라이센스 인증
     * 
     * @param batchVo
     * @throws Exception
     */
    public boolean selectCertifyHubCompany(BatchVo batchVo) throws Exception {
    	return cloudTransDao.selectCertifyHubCompany(batchVo);
    }
    
    /**
     * HUB 가입 협력사 리스트 조회
     * 
     * @param batchVo
     * @throws Exception
     */
    public List selectPoVendorForHubList(JobVo jobVo) throws Exception {
    	return cloudTransDao.selectPoVendorForHubList(jobVo);
    }

    /**
     * 현대차 연계 조직별 ID구하기
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public List selectHubOrganizatList(Map map) throws Exception {
		return batchDao.selectHubOrganizatList(map);
	}

	/**
     * 송신된 제출서중 처리되지 않은 목록 구하기
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public List selectHubNoComplateList(Map map) throws Exception {
		return batchDao.selectHubNoComplateList(map);
	}
    
	/**
	 * 자동메일발송 예약리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMailSendBatchList(Map map) throws Exception {
		return batchDao.selectMailSendBatchList(map);
	}
	
	/**
	 * 협력사 원산지 요청 품목 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectOrderInfoDtlList(Map map) throws Exception {
		return batchDao.selectOrderInfoDtlList(map);
	}
	
	/**
	 * 메일발송 이력 마스터 정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String insertMailMst(Map map) throws Exception {
		return batchDao.insertMailMst(map);
	}
	
	/**
	 * 메일발송 시 첨부할 파일을 가져온다.
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMailSendFileList(Map map) throws Exception {
		return batchDao.selectMailSendFileList(map);
	}
	
	/**
	 * 메일발송이력 협력사 정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMailVendor(Map map) throws Exception {
		return batchDao.insertMailVendor(map);
	}
	
	/**
	 * 메일발송 품목리스트 등록
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMailVendorItem(List list, Map map) throws Exception {
		return batchDao.insertMailVendorItem(list, map);
	}
	
	/**
	 * 문자발송 정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSendSMS(Map map) throws Exception {
		return batchDao.insertSendSMS(map);
	}
	
	/**
	 * 메일에러 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMail(Map map) throws Exception {
		return batchDao.updateMail(map);
	}
	
	/**
	 * 자동메일발송 처리이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMailSendHistoryInfo(Map map) throws Exception {
		return batchDao.updateMailSendHistoryInfo(map);
	}
	
	/**
	 * 자동메일발송 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectMailNextScheduleInfo(Map map) throws Exception {
		return batchDao.selectMailNextScheduleInfo(map);
	}
	
	/**
	 * 다음 자동메일발송 이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMailSendHistoryInfo(Map map) throws Exception {
		return batchDao.insertMailSendHistoryInfo(map);
	}
	
	/**
	 * 인터페이스 소스테이블 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSourceTable(Map map) throws Exception {
		return batchDao.selectSourceTable(map);
	}
	
	/**
     * FTA PASS와 인터페이스할 대상정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectFtaPassDoList(Map map) throws Exception {
    	return batchDao.selectFtaPassDoList(map);
    }
    
    /**
     * FTA PASS 요청 상태 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updatePassReqStatus(Map map) throws Exception {
    	return batchDao.updatePassReqStatus(map);
    }
    
}
