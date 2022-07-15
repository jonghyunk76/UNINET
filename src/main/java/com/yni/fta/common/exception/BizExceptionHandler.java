package com.yni.fta.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

/**
 * Exception 발생 시 핸들링하기 위한 클래스
 * 
 * @author YNI-maker
 *
 */
public class BizExceptionHandler implements ExceptionHandler {

    protected Log log = LogFactory.getLog(this.getClass());
    
    /**
     * 호출 시 자동으로 실행되는 매소드
     * 
     * @param exception
     * @param packageName
     */
    public void occur(Exception exception, String packageName) {
        log.debug(" COSCMExcepHndlr run...............");
    }

}
