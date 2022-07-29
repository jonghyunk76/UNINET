package stw.eai.myEagle;

import hanwha.neo.branch.ss.mail.service.MailServiceProxy;
import hanwha.neo.branch.ss.mail.vo.WsMailStatus;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import mySingle.service.MLDeliveryStatusInquiryServiceLocator;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;
import samsung.esb.mail.service.MLDeliveryStatusInquiryService;
import samsung.esb.mail.vo.StatusCountESBVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class NewGetMailStatusCounts implements NewClient {

	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
	
		//String endPoint = "http://www.ci.eagleoffice.co.kr/api/services/MailService"; // 전자결재 외부망
		//String endPoint = "http://ci.eagleoffice.co.kr/api/services/MailService"; //전자결재 내부망
		//String endPoint = "http://hanwha.eagleoffice.co.kr/api/services/MailService"; //이글오피스운영
		//String endPoint = "http://circledev.tw.hanwha.com/api/axis/services/MailService"; //Circle 개발
		//2022.02.07 Port 7500 Add
		String endPoint = "http://ep.circle.hanwha.com:7500/api/axis/services/MailService"; //민수Circle 운영
		
		MailServiceProxy proxy = null;
		SignerInfoCreate singerInfoCreate = null;
		String[] mailKeys= null;
		WsMailStatus[] wsMailStatuss = null;
		WsMailStatus wsMailStatus = null;
		
		SiebelPropertySet	psMtrKeys		= null;
		SiebelPropertySet	psSiebelMessage	= null;
		SiebelPropertySet	psGetMailStatus	= null;
		SiebelPropertySet	psStatusCnt		= null;	
		String				error			= "";

		try {
			
			// get Inputs
			psSiebelMessage	= Common.getChild(inputs			,"SiebelMessage");
			psGetMailStatus	= Common.getChild(psSiebelMessage	,"getMailStatusCounts");
			psMtrKeys		= Common.getChild(psGetMailStatus	,"mtrKeys");

			singerInfoCreate  = new SignerInfoCreate();
			
			/*
			 * ***************************************************************************
			 *  mailkeys 가져오기
			 * ****************************************************************************	
			 */

			mailKeys = singerInfoCreate.getMailKeys(psMtrKeys);
			/*
			 * ***************************************************************************
			 *  메일 상태 조회 웹서비스 호출
			 * ****************************************************************************	
			 */

			proxy = new MailServiceProxy(endPoint);			
			wsMailStatuss = proxy.getMailStatusCounts(mailKeys);

			/*
			 * *******************************************************
			 *  웹서비스 호출 결과 바인딩		
			 *  ******************************************************
			 */						

			psSiebelMessage	= new SiebelPropertySet();
			psGetMailStatus	= new SiebelPropertySet();
			psStatusCnt		= new SiebelPropertySet();

			psSiebelMessage	.setType("SiebelMessage");

			psGetMailStatus	.setType("getMailStatusCountsResponse");
			psSiebelMessage.addChild(psGetMailStatus);

			psStatusCnt		.setType("statusCountVO");
			psGetMailStatus.addChild(psStatusCnt);

			singerInfoCreate.setMailStatusCounts(wsMailStatuss,psStatusCnt);
		
			outputs.addChild(psSiebelMessage);
			outputs.setProperty("Error", error);

		}  catch (ESBFaultVO e) {
			
			error += "ESBFaultVO : [" + e.getFaultCode1() + "] "+ e.getFaultMessage();
			outputs.setProperty("Error", error);
			
			throw new SiebelBusinessServiceException(e, e.getFaultCode1(), e.getFaultMessage());
		} catch (RemoteException e) {
			
			error += "RemoteException \n" + e.getMessage();
			outputs.setProperty("Error", error);
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		} catch	(Exception e){
			
			error += "Exception \n" + e.getMessage();
			outputs.setProperty("Error", error);
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		}finally{
			proxy = null;
			singerInfoCreate = null;
			mailKeys= null;
			wsMailStatus = null;
			wsMailStatuss = null;
		
			psMtrKeys		= null;
			psSiebelMessage	= null;
			psGetMailStatus	= null;
			psStatusCnt		= null;
		}
	}

}
