package test;

import hanwha.neo.branch.ss.org.service.NeoOrgWsProxy;
import hanwha.neo.branch.ss.org.vo.OrgUserVO;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import stw.eai.myEagle.NewSendMail;


public class NeoSloWsClientService {
	private static Log logger = LogFactory.getLog(NewSendMail.class);
	

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args)  {
		
		
		String endPoint = "http://www.ci.eagleoffice.co.kr/api/ss/org"; // 전자결재 외부망
		try{
		NeoOrgWsProxy proxy = new NeoOrgWsProxy();
		proxy.setEndpoint(endPoint);
		
		OrgUserVO orgUserVO = proxy.searchDefaultUserByUserId("wc5919.lee");
		
		logger.debug("userKey : "+orgUserVO.getUserKey());
		//logger.log("userKey : "+orgUserVO.getUserKey());
		
		}catch(Exception e){
			e.printStackTrace();
		}
				
	
		
	}

}
