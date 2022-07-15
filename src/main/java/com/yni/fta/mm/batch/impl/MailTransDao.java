package com.yni.fta.mm.batch.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 자동메일발송 처리 클랳스
 * 
 * @author YNI-Maker
 *
 */
@Repository("mailTransDao")
public class MailTransDao extends YniAbstractDAO {
	
	public MailTransDao() { }
	
	/**
     * 메일을 발송할 대상정보 리스트 조회
     * 
     * @return
     * @throws Exception
     */
    public List selectMailTargetList(Map map) throws Exception {
        return this.listWithRowPaging("mmMail.selectMailTargetList", map);
    }
    
    /**
     * 메일 스케쥴 마스터 리스트 조회
     * 
     * @return
     * @throws Exception
     */
    public List selectMailScheduleList(Map map) throws Exception {
        return this.list("mmMail.selectMailScheduleList", map);
    }
    
    /**
     * 메일 스케쥴 마스터 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertMailScheduleInfo(Map map) throws Exception {
    	return this.update("mmMail.insertMailScheduleInfo", map);
    }
    
    /**
     * 메일 스케쥴 마스터 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateMailScheduleInfo(Map map) throws Exception {
    	return this.update("mmMail.updateMailScheduleInfo", map);
    }
    
    /**
     * 메일 스케쥴 예약 리스트 조회
     * 
     * @return
     * @throws Exception
     */
    public List selectMailSendHistoryList(Map map) throws Exception {
        return this.list("mmMail.selectMailSendHistoryList", map);
    }
    
    /**
     * 메일 발송이력 등록 및 예약
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertMailSendHistoryInfo(Map map) throws Exception {
    	return this.update("mmMail.insertMailSendHistoryInfo", map);
    }
    
    /**
     * 메일 발송이력 수정 및 예약
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateMailSendHistoryInfo(Map map) throws Exception {
    	return this.update("mmMail.updateMailSendHistoryInfo", map);
    }
    
    /**
     * 메일 발송 취소
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateCancelMailReservat(Map map) throws Exception {
    	return this.update("mmMail.updateCancelMailReservat", map);
    }
    
    /**
     * 메일 발송예약 취소
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteReservateInfo(Map map) throws Exception {
    	return this.update("mmMail.deleteReservateInfo", map);
    }
    
}
