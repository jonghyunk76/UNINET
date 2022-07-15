package com.yni.fta.common.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import com.yni.fta.common.batch.vo.JobVo;

import kr.yni.frame.batch.logger.BatchLogger;
import kr.yni.frame.pool.BatchPoolManager;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.StringHelper;

public class QuartzSupporter {
	
	private static Log log = LogFactory.getLog(QuartzSupporter.class);
	
	/**
	 * 스케쥴 시간을 조회한 후 리턴한다.
	 * 
	 * @param companyCode 회사(법인) 코드
	 * @param scheduleCode 스케쥴 코드
	 * @return 스케쥴 시간
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getNextFireTime(String companyCode, String scheduleCode, String batchYn) throws Exception {
		Map map = new HashMap();
		
        map.put("COMPANY_CD", companyCode);
        map.put("SCHEDULE_CD", scheduleCode);
        map.put("SYSTEM_BATCH_YN", batchYn);
        
		return getNextFireTime(map);
	}
	
	/**
	 * 스케쥴 시간을 조회한 후 리턴한다.
	 * 
	 * @param map 회사코드와 스케쥴 코드가 입력된 map
	 * @return 스케쥴 시간
	 * @throws Exception
	 */
	public static String getNextFireTime(Map map) throws Exception {
		String nextTime = null;
		
		Scheduler scheduler = BatchPoolManager.getDefaultScheduler(map);
		
		if(scheduler == null || scheduler.isShutdown() || StringHelper.null2string(map.get("SYSTEM_BATCH_YN"), "N").equals("N")) {
			nextTime = "--/--/-- --:--:--";
		} else {
			nextTime = BatchPoolManager.getNextFireTime(scheduler, map);
		}
		
		if(nextTime.isEmpty()) {
			nextTime = "00/00/00 00:00:00";
		}
		
		if(log.isDebugEnabled()) {
			log.debug("next fire time of the job = " + nextTime + " / " + StringHelper.null2string(map.get("SYSTEM_BATCH_YN"), "N"));
		}
		
        return nextTime;
	}
	
	/**
	 * 스케쥴를 정지시킵니다.
	 * 
	 * @param map 회사코드와 스케쥴 코드가 입력된 map
	 * @return 정지여부
	 * @throws Exception
	 */
	public static boolean stopScheduler(Map map) throws Exception {
		boolean rst = true;
		
		Scheduler scheduler = BatchPoolManager.getDefaultScheduler(map);
		
		rst = BatchPoolManager.deleteJob(scheduler, map);
		
		if(log.isDebugEnabled()) {
			log.debug("Job is delete.(result = " + rst + ")");
		}
		
        return rst;
	}
	
	/**
	 * 스케쥴 시간을 실행하거나 조정한다.
	 * 
	 * @param map 배치 실행여부/상태/시간이 입력된 map
	 * @return 오류 발생 시 오류 메시지 리턴
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String setNextFireTime(Map map) throws Exception {
		String message = null;
		
		String status = StringHelper.null2void(map.get("STATUS"));
		String jobGroup = StringHelper.null2void(map.get("COMPANY_CD"));
		String jobName = StringHelper.null2void(map.get("SCHEDULE_CD"));
		
		// 배치실행중에는 설정을 변경할 수 없음
		if("1".equals(status)) {
			message = MessageResource.getMessageInstance().getMessage("MSG_BATCH_THREAD_RUN");
		} else {
			Scheduler scheduler = BatchPoolManager.getDefaultScheduler(map);
			
			String batch_yn = StringHelper.null2void(map.get("SYSTEM_BATCH_YN"));
			
			if ("Y".equals(batch_yn)) {
				map.remove("YEAR");
				
				if (log.isInfoEnabled()) {
					log.info("Scheduler = " + scheduler + " | map data = " + map);
				}

				if(scheduler == null || scheduler.isShutdown()) {
					scheduler = BatchPoolManager.startBatch(map);
				} else {
					BatchPoolManager.rescheduleJob(scheduler, map);
				}
			} else {
				BatchPoolManager.deleteJob(scheduler, map);
			}
		}
		
		return message;
	}
	
	/**
     * Scheduler List를 조회한다.
     * 
     * @param map
     * @author carlos
     * @throws Exception
     */
    public static List<Map<String, Object>> getQuartzJobList() throws Exception {
    	List<Map<String, Object>> quartzJobList = new ArrayList<Map<String, Object>>();
        Scheduler scheduler = BatchPoolManager.getDefaultScheduler();
        
        for (String groupName : scheduler.getJobGroupNames()) {
            for (String jobName : scheduler.getJobNames(groupName)) {
                Map<String, Object> jobMap = new LinkedHashMap<String, Object>();
                Trigger[] triggers = scheduler.getTriggersOfJob(jobName, groupName);
                Date nextFireTime = triggers[0].getNextFireTime();
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                
                jobMap.put("jobName", jobName);
                jobMap.put("groupName", groupName);
                jobMap.put("nextFireTime", nextFireTime);
                jobMap.put("nextTime", sdf.format(nextFireTime));
                
                log.debug("[jobName] : " + jobName + " [groupName] : " + groupName + " - " + nextFireTime);

                quartzJobList.add(jobMap);
            }
        }
        
        return quartzJobList;
    }
    
    /**
     * 
     * @author carlos
     * @param map
     * @throws SchedulerException
     */
    public void triggerJob(Map map) throws SchedulerException {
        Scheduler scheduler = BatchPoolManager.getDefaultScheduler();
        String jobName = StringHelper.null2string(map.get("jobName"), "");
        String jobGroup = StringHelper.null2string(map.get("jobGroup"), "");
        
        scheduler.triggerJob(jobName, jobGroup);
    }
    
    /**
     * 설정된 시간에 대한 다음 일자를 작성합니다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public static Map getNextTimeToMap(Map map) throws Exception {
        String pyyyy = StringHelper.null2void(map.get("YEAR")); // 매년반복이 아닌 경우 적용할 년도
        String fromMM = StringHelper.null2void(map.get("SEND_FROM_MON")); // 예약시작월
        String fromDD = StringHelper.null2void(map.get("SEND_FROM_DAY")); // 예약시작일
        String toMM = StringHelper.null2void(map.get("SEND_TO_MON")); // 예약종료월
        String toDD = StringHelper.null2void(map.get("SEND_TO_DAY")); // 예약종료일
        
        String pweek = StringHelper.null2void(map.get("WEEK"));  // 요일(0:일, 1:월, 2:화, 3:수, 4:목, 5:금, 6:토)
        String phour = StringHelper.null2void(map.get("HOUR"));  // 시간
        String repeat = StringHelper.null2string(map.get("REPEAT_YN"), "N"); // 매년 반복여부 Y/N
        
        fromMM = (Integer.parseInt(fromMM) < 10) ? "0"+Integer.parseInt(fromMM) : fromMM;
        fromDD = (Integer.parseInt(fromDD) < 10) ? "0"+Integer.parseInt(fromDD) : fromDD;
        toMM = (Integer.parseInt(toMM) < 10) ? "0"+Integer.parseInt(toMM) : toMM;
        toDD = (Integer.parseInt(toDD) < 10) ? "0"+Integer.parseInt(toDD) : toDD;
        phour = (Integer.parseInt(phour) < 10) ? "0"+Integer.parseInt(phour) : phour;
        
        // 현재 일자구하기
        Calendar today = new GregorianCalendar();
        
        String ttyyyy = DateHelper.toString(today.getTime(), "yyyy", Locale.KOREA);
        String ttmm = DateHelper.toString(today.getTime(), "MM", Locale.KOREA);
        String ttdd = DateHelper.toString(today.getTime(), "dd", Locale.KOREA);
        
        // 반복을 체크한 경우에는 현재년도를 기준으로 함
        if(repeat.equals("Y")) {
        	pyyyy = ttyyyy;
        }
        
        // 마지막일자 조정
        int fromLastDay = DateHelper.getDayCountForMonth(Integer.parseInt(pyyyy), Integer.parseInt(fromMM));
        int toLastDay = DateHelper.getDayCountForMonth(Integer.parseInt(pyyyy), Integer.parseInt(toMM));
        
        if(Integer.parseInt(fromDD) > fromLastDay) fromDD = Integer.toString(fromLastDay);
        if(Integer.parseInt(toDD) > toLastDay) toDD = Integer.toString(toLastDay);
        
        int dayDue = DateHelper.getDayCount((pyyyy+""+fromMM+""+fromDD), (ttyyyy+""+ttmm+""+ttdd));
        
        log.debug("From date = " + (pyyyy+""+fromMM+""+fromDD) + "/ Current date = " + (ttyyyy+""+ttmm+""+ttdd) + " / interval = " + dayDue);
        
        // 시작일자가 현재일자보다 큰 경우에는 시작월일을 적용한다.
        if(dayDue < 0) {
        	today = new GregorianCalendar(Integer.parseInt(pyyyy),
                    Integer.parseInt(fromMM) - 1,
                    Integer.parseInt(fromDD));
        }
        
        String tyyyy = DateHelper.toString(today.getTime(), "yyyy", Locale.KOREA);
        String tmm = DateHelper.toString(today.getTime(), "MM", Locale.KOREA);
        String tdd = DateHelper.toString(today.getTime(), "dd", Locale.KOREA);
        String thour = DateHelper.toString(today.getTime(), "HH", Locale.KOREA);
        String tweek = Integer.toString(today.get(Calendar.DAY_OF_WEEK) - 1); // 일~토=0~6
        
        log.debug("change date = " + (tyyyy+""+tmm+""+tdd) + " / week = " + tweek);
        
        String[] weeks = pweek.split(",");
        int plusDay = -1;
        List<Integer> weeksAry = new ArrayList<Integer>();
        int weeksVal = Integer.parseInt(tweek);
        int firstWeek = Integer.parseInt(weeks[0]); // 선택된 요일중 최초요일
        
        // 요일을 시작요일을 기준으로 재정렬해 놓는다.(0->1...>6>0>1...)
        for(int a = 0; a < 7; a++) {
        	weeksAry.add(weeksVal);
        	
        	log.debug("Week value("+a+") = " + weeksVal);
        	
        	if(weeksVal == 6) {
        		weeksVal = 0;
        	} else {
        		weeksVal++;
        	}        	
        }
        
        // 선택된 요일중에 같은 요일이 있다면 현재일로 적용하기 위해 더할 날짜를 0으로 설정한다.
        for(int i = 0; i < weeks.length; i++) {
        	int weekInt = Integer.parseInt(weeks[i]);
        	
        	if(weekInt == weeksVal) {
        		plusDay = 0;
        	}
        	
        	if(firstWeek > weekInt) {
        		firstWeek = weekInt;
        	}
        }
        
        log.debug("1.st plusDay = " + plusDay + " / week = " + firstWeek);
        
        // 지정된 요일과 재정렬한 요일을 비교해서 더할 날짜를 구한다.
        if(plusDay == -1) {
        	plusDay = 0;
        	
        	for(int k = 0; k < weeksAry.size(); k++) {
            	if(weeksAry.get(k) == firstWeek) {
            		break;
            	}
            	
            	plusDay++;
            }
        }
        
        log.debug("2.st plusDay = " + plusDay + " / week = " + firstWeek);
        
        Calendar theDay = new GregorianCalendar(Integer.parseInt(tyyyy),
                Integer.parseInt(tmm) - 1,
                Integer.parseInt(tdd));
        
        theDay.add(Calendar.DATE, plusDay); // 일자 더하기
        
        String theYear = DateHelper.toString(theDay.getTime(), "yyyy", Locale.KOREA);
        String theMM = DateHelper.toString(theDay.getTime(), "MM", Locale.KOREA);
        String theDD = DateHelper.toString(theDay.getTime(), "dd", Locale.KOREA);
        String theHour = phour;
        
        log.debug("befor date = " + (tyyyy+""+tmm+""+tdd) + " / The date = " + (theYear+""+theMM+""+theDD) + ", Hour = " + theHour + " / Plus day = " + plusDay);
        
        // 한해의 7일이후에는 지정된 년도를 기준으로 변경한다.
        if(Integer.parseInt(theMM+theDD) >= 1226) {
        	theYear = pyyyy;
        }
        
        int theDue = DateHelper.getDayCount((theYear+""+theMM+""+theDD), (pyyyy+""+toMM+""+toDD));
        
        log.debug("The date = " + (theYear+""+theMM+""+theDD) + "/ To date" + (pyyyy+""+toMM+""+toDD) + " / interval = " + theDue);
        
        Map rstMap = new HashMap();
        
        // 종료일자가 계산된 일자보다 이전인 경우에는 null를 리턴한다.(다음해로 넘길수 없음)
        if(theDue < 0) {
        	return rstMap;
        }
    	
        rstMap.put("RESERVATION_DATE", theYear+theMM+theDD); // 일자(년월일)
        rstMap.put("RESERVATION_TIME", theHour); // 시간
        rstMap.put("STATUS", "1"); // 자동메일 상태(1:대기, 2:완료, 3:실패, 4:취소)
        
        return rstMap;
    }
    
    public static void setStandardFireTime(List schList, JobVo jobVo) throws Exception {
    	String companyCode = jobVo.getCompanyCode();
    	String scheduleCode = jobVo.getScheduleCode();
    	BatchLogger logger = jobVo.getBatchLogger();
    	
    	// 회사코드와 스케쥴코드가 일치하고, 표준 스케쥴이 있다면 시간을 조정한다.
		for(int i = 0; i < schList.size(); i++) {
			Map schMap = (Map) schList.get(i);
			
			String comCd = StringHelper.null2void(schMap.get("COMPANY_CD"));
			String schCd = StringHelper.null2void(schMap.get("SCHEDULE_CD"));
			
			if(comCd.equals(companyCode) && schCd.equals(scheduleCode)) {
				String comMon = StringHelper.null2void(schMap.get("COM_MONTH"));
				String comDay = StringHelper.null2void(schMap.get("COM_DAY"));
				String comHour = StringHelper.null2void(schMap.get("COM_HOUR"));
				String comMin = StringHelper.null2void(schMap.get("COM_MINUTES"));
				
				String Mon = StringHelper.null2void(schMap.get("MONTH"));
				String Day = StringHelper.null2void(schMap.get("DAY"));
				String Hour = StringHelper.null2void(schMap.get("HOUR"));
				String Min = StringHelper.null2void(schMap.get("MINUTES"));
				
				logger.logMessage("Standard schedule info = " + comMon + "-" + comDay + " " + comHour + ":" + comMin);
				
				if(!comHour.isEmpty()) { // 표준시간이 없다면 셋팅하지 않음
    				if(!(comMon + comDay + comHour + comMin).equals((Mon + Day + Hour + Min))) {
        				if(!comMon.isEmpty() && !comDay.isEmpty() && !comHour.isEmpty() && !comMin.isEmpty()) {
        					// 스케쥴시간을 표준시간으로 변경한다.
        					schMap.put("MONTH", comMon);
        					schMap.put("DAY", comDay);
        					schMap.put("HOUR", comHour);
        					schMap.put("MINUTES", comMin);
        					schMap.put("ADD_MINUTE_NUM", "1"); // 1분 후 스케쥴이 되도록 추가
        					
        					Scheduler scheduler = BatchPoolManager.getDefaultScheduler(schMap);
        					BatchPoolManager.rescheduleJob(scheduler, schMap);
        					
        					logger.logMessage("Change standard schedule time = " + QuartzSupporter.getNextFireTime(schMap));
        				}
    				}
				}
				
				break;
			}
		}
    }
    
}
