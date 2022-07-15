package com.yni.fta.common.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.yni.fta.common.beans.target.InterfaceTarget;
import kr.yni.frame.Constants;
import kr.yni.frame.batch.logger.BatchLogger;
import kr.yni.frame.batch.logger.impl.BatchLoggerImpl;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.context.ApplicationContextFactory;
import kr.yni.frame.pool.BatchPoolManager;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.StringHelper;

/**
 * 배치 스케쥴 실행 클래스
 * 
 * @author jonghyun3.kim
 *
 */
public class YNIFrameBatchJobBean extends QuartzJobBean {

    private static Log log = LogFactory.getLog(YNIFrameBatchJobBean.class);

    private InterfaceTarget target;

    // 0 or null = 사용안함, 1 = 사용
    private static String useToScheduler = "0";
    // 배치 허용 서버(,으로 구분해서 여러 서버를 등록할 수 있음)
    private static String allowServer;

    // 배치내용을 파일로 기록하기 위한 로그 클래스
    BatchLogger logger = null;

    /**
     * class initializer
     */
    static {
        configure();
    }

    /**
     * properties파일에 설정된 값을 구한다.
     */
    private static void configure() {
        try {
            Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();

            useToScheduler = StringHelper.null2void(configurator.getString("batch.use.scheduler"));
            allowServer = StringHelper.null2void(configurator.getString("batch.server.name"));
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("exception: \n" + ex);
            }
        }
    }

    public YNIFrameBatchJobBean() {
        logger = new BatchLoggerImpl();
    }

    /**
     * 스케쥴 배치 실행
     * 
     * @param context Quartz Context
     * @throws JobExecutionException
     */
    @SuppressWarnings("unchecked")
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        if (log.isInfoEnabled()) {
            log.info("started executeInternal(Job:" + context.getJobDetail().getName() + ", Server Name : " + Constants.SERVER_HOST_ADDRESS + ")");
        }

        // 스케쥴 구동 가능한 서버인지 체크한다.
        // - 이중화된 서버에서는 동일 스케쥴이 타 서버에 구동되어 동시에 여러 서버로 실행되는 문제가 있어 이를 적용함
        List<String> serverList = StringHelper.split(allowServer, ",");
        boolean allow = false;

        if (!allowServer.isEmpty() && serverList.size() > 0) {
        	for (int i = 0; i < serverList.size(); i++) {
        		String hostServer = StringHelper.null2void(serverList.get(i)).trim();
        		
                if (hostServer.equals(Constants.SERVER_HOST_ADDRESS)) {
                    allow = true;
                    break;
                }
            }
        } else {
            allow = true;
        }

        if (log.isInfoEnabled()) {
            log.info("Scheduler Condition Check...(Use Y/N = " + useToScheduler + ", allow the Server[" + allowServer + "] = " + allow + ")");
        }

        try {
            // 스케쥴 설정값이 1번이 경우에만 스케쥴이 구동하고 이외에는 모든 요청이 무시된다.
            if ("1".equals(useToScheduler) && allow) {
                ApplicationContext ctx = ApplicationContextFactory.getAppContext();
                target = (InterfaceTarget) ctx.getBean("batchTargetService");

                Map<String, Object> map = new HashMap<String, Object>();
                Map<String, String> paramMap = null;
                List<Object> resultList = target.selectInterfaceScheduleList(map);
                
                String sheduleInfo = null;
                if(resultList != null && resultList.size() > 0) {
                	Map sheduleMap = (Map) resultList.get(0);
                	sheduleInfo = sheduleMap.toString();
                }
                
                if(log.isInfoEnabled()) log.info("Shedule size = " + resultList.size() + ", Shedule info. = " + sheduleInfo);
                
                for(int i = 0; i < resultList.size(); i++) {
                    paramMap = (Map<String, String>) resultList.get(i);

                    Scheduler scheduler = BatchPoolManager.startBatch(paramMap);

                    String triggerGroup = paramMap.get("COMPANY_CD") + "_TRIGGER";
                    String triggerName = paramMap.get("SCHEDULE_CD") + "Trigger";

                    // 서버가 이중화되어 있을 경우 중복 실행되는 문제가 발생하는 것을 막는다.
                    // 동시간대에 다른 동일 스케쥴이 구동중인지 확인하는 방식임(일자,시,분으로 체크)
                    if (scheduler.isStarted()) {
                        String runningTime = paramMap.get("CURRENT_BATCH_DATE");
                        // Calendar cd = new GregorianCalendar(Locale.KOREA);
                        String currentTime = DateHelper.getCurrentYearAsString() + DateHelper.getCurrentMonthAsString() + DateHelper.getCurrentDayAsString()
                                + DateHelper.getCurrentHourAsString() + DateHelper.getCurrentMinuteAsString();

                        if (log.isDebugEnabled())
                            log.debug("runningTime = " + runningTime + ", currentTime=" + currentTime);

                        if (runningTime.equals(currentTime)) {
                            boolean kill = scheduler.deleteJob(paramMap.get("SCHEDULE_CD"), paramMap.get("COMPANY_CD"));
                        	
                            if (kill) {
                                if (log.isDebugEnabled())
                                    log.debug("interrupt... killed then batch(by same scheduler)");
                            } else {
                                if (log.isErrorEnabled())
                                    log.error("interrupt error... not kill then batch(same scheduler)");
                            }
                        } else {
                            paramMap.put("CREATE_BY", "scheduler");

                            target.updateScheduleBatchDate(paramMap);
                        }
                    }
                    
                    String message = "Schedule-Job debug(" + "company code = " + paramMap.get("COMPANY_CD") + " | schedule name = "
                            + paramMap.get("SCHEDULE_CD") + " | trigger info = " + scheduler.getTrigger(triggerName, triggerGroup) + ")";
                    
                    if (log.isDebugEnabled()) {
                        log.debug(message);
                    }
                }

                // scheduler info debug
                Scheduler scheduler = BatchPoolManager.getDefaultScheduler();
                String[] triggerGroupNames = scheduler.getTriggerGroupNames();

                for (int g = 0; g < triggerGroupNames.length; g++) {
                    String[] triggerNames = scheduler.getTriggerNames(triggerGroupNames[g]);

                    for (int n = 0; n < triggerNames.length; n++) {
                        String message = "Schedule-Job info(trigger status = " + scheduler.getTriggerState(triggerNames[n], triggerGroupNames[g])
                                + " | schedule address = " + scheduler + " | trigger info = " + scheduler.getTrigger(triggerNames[n], triggerGroupNames[g])
                                + ")";

                        if (log.isInfoEnabled()) {
                            log.info(message);
                        }

                        logger.logMessage(message);
                    }
                }
            } else {
                Scheduler scheduler = context.getScheduler();
                scheduler.shutdown();

                if (log.isInfoEnabled()) {
                    log.info("stop the scheduler... bey!!!");
                }
            }
        } catch (Exception exp) {
            try {
                logger.logMessage(exp.getMessage());
                logger.logEnd(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            exp.printStackTrace();
        }
    }
}
