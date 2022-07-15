package com.yni.fta.fm.sm;

import java.util.Map;

/**
 * Python 인터페이스 클래스
 * 
 * @author YNI-Framework
 *
 */
public interface SM8003 {
    
    /**
     * Access Token 구하기
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	Map executePythonFile(Map map) throws Exception;
	
}
