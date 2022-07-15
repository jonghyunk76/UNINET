package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 서명권자 조회
 * 
 * @author carlos
 *
 */
public interface MMA010 {

	/**
	 * 서명권자 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectSignatureList(Map map) throws Exception;
}
