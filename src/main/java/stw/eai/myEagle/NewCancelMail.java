package stw.eai.myEagle;

import hanwha.neo.branch.ss.mail.service.MailServiceProxy;
import hanwha.neo.branch.ss.mail.vo.WsResource;

import java.rmi.RemoteException;

import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class NewCancelMail implements NewClient {

	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
			
        //String endPoint = "http://www.ci.eagleoffice.co.kr/api/services/MailService"; // 전자결재 외부망
	    //String endPoint = "http://ci.eagleoffice.co.kr/api/services/MailService"; //전자결재 내부망
	    //String endPoint = "http://hanwha.eagleoffice.co.kr/api/services/MailService"; //운영
		//String endPoint = "http://circle.tw.hanwha.com/api/axis/services/MailService"; //Circle 운영
		//2022.02.07 Port 7500 Add
		String endPoint = "http://ep.circle.hanwha.com:7500/api/axis/services/MailService";   //2021.11.14부터 발송(Sec Level = 2)
		MailServiceProxy proxy = null;
		SignerInfoCreate singerInfoCreate = null;
		String[] receiverForCancel= null;
		WsResource senderInfo = null;
		String	mailKey			= null;
	
		SiebelPropertySet	psSiebelMessage	= null;
		SiebelPropertySet	psCancelMail	= null;
		SiebelPropertySet	psAddrForCancel	= null;
		SiebelPropertySet	resource		= null;
		
		String				error			= "";
		
		try {
		
			// get Inputs
			psSiebelMessage	= Common.getChild(inputs			,"SiebelMessage");
			psCancelMail	= Common.getChild(psSiebelMessage	,"cancelMISMailByRecipient");
			psAddrForCancel	= Common.getChild(psCancelMail		,"addressForCancel");
			resource		= Common.getChild(psCancelMail		,"resourceCSVO");
			
			
			// ====== mailKey ======
			mailKey		= psCancelMail.getProperty("mtrKey");
			
			/*
			 * ***************************************************************************
			 *  메일  취소 생성 
			 * ****************************************************************************	
			 */
			
			singerInfoCreate  = new SignerInfoCreate();
			
			receiverForCancel = singerInfoCreate.getMailReceiverForCancel(psAddrForCancel);
			
			senderInfo = singerInfoCreate.getMailWsResource(resource);
				
			/*
			 * ***************************************************************************
			 *  메일 취소 웹서비스 호출
			 * ****************************************************************************	
			 */
	
			proxy = new MailServiceProxy(endPoint);			
			String result = proxy.cancelMISMailByRecipient(mailKey, receiverForCancel, senderInfo);

	
			/*
			 * *******************************************************
			 *  웹서비스 호출 결과 바인딩		
			 *  ******************************************************
			 */						


			psSiebelMessage	= new SiebelPropertySet();
			psCancelMail	= new SiebelPropertySet();
			
			psSiebelMessage.setType("SiebelMessage");
			
			psCancelMail.setType("cancelMISMailByRecipientResponse");
			psCancelMail.setProperty("result", result);
			psSiebelMessage.addChild(psCancelMail);
			
			outputs.addChild(psSiebelMessage);
			outputs.setProperty("Error", "");
				
		} catch (ESBFaultVO e) {
			
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
		} finally {
			
			proxy = null;
			senderInfo = null;
			receiverForCancel = null;
			singerInfoCreate = null;
			mailKey = null;
			
			psSiebelMessage	= null;
			psCancelMail	= null;
			psAddrForCancel	= null;
			resource		= null;
		}
	}
}
