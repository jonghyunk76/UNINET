package com.yni.rs.batch.DHL_ROUTE_1.receive;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.siebel.data.SiebelPropertySet;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.parameter.YniAbstractBatch;
import com.yni.rs.batch.ImportPackage;

import kr.yni.frame.util.StringHelper;
import stw.eai.dhl.siebel.STWDhlClient;

/**
 * 수신할 파라메터 정보 생성 클래스
 * 
 * @author ador2
 *
 */
public class Import extends YniAbstractBatch implements ImportPackage {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 요청 파라메터 값 생성
	 * - JCO XML에 지정된 import 파라메터와 맵핑되는 정보를 생성해야 함
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Object getParameter(Map map) throws Exception {
		log.debug("Receive Import : " + map);
		
		Map rst = new HashMap();
		
		String RequestType = StringHelper.null2void(map.get("RequestType"));
		String Address1 = StringHelper.null2void(map.get("Address1"));
		String Address2 = StringHelper.null2void(map.get("Address2"));
		String CountryCode = StringHelper.null2void(map.get("CountryCode"));
		String CountryName = StringHelper.null2void(map.get("CountryName"));
		String PostalCode = StringHelper.null2void(map.get("PostalCode"));
		String City = StringHelper.null2void(map.get("City"));
		String OriginCountryCode = StringHelper.null2void(map.get("OriginCountryCode"));
		
		SiebelPropertySet psInput = new SiebelPropertySet();
		
		psInput.setProperty("Server", "Local");
		
		SiebelPropertySet psSiebelMessage = new SiebelPropertySet();
		psSiebelMessage.setType("SiebelMessage");
		
		SiebelPropertySet psRouting = new SiebelPropertySet();
		psRouting.setType("RouteRequest");
		
		psRouting.setProperty("RequestType", RequestType);
		psRouting.setProperty("Address1", Address1);
		psRouting.setProperty("Address2", Address2);
		psRouting.setProperty("CountryCode", CountryCode); // NL
		psRouting.setProperty("CountryName", CountryName);
		psRouting.setProperty("PostalCode", PostalCode);
		psRouting.setProperty("City", City);
		psRouting.setProperty("OriginCountryCode", OriginCountryCode);
		
		psSiebelMessage.addChild(psRouting);
		psInput.addChild(psSiebelMessage);
		
		return psInput; 
	}

	@Override
	public void executeBatch(Object batchVo) throws Exception {
		log.debug("Send Batch : " + batchVo);
	}
	
}
