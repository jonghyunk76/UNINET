package stw.eai.myEagle;

import hanwha.neo.branch.ss.common.vo.WsAttachFile;
import hanwha.neo.branch.ss.mail.service.MailServiceProxy;
import hanwha.neo.branch.ss.mail.vo.WsMailInfo;
import hanwha.neo.branch.ss.mail.vo.WsRecipient;

import java.rmi.RemoteException;


import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class NewSendMail implements NewClient {
	

	@Override
	public void request(SiebelPropertySet inputs,SiebelPropertySet outputs) throws SiebelBusinessServiceException{
		
		//String endPoint = "http://www.ci.eagleoffice.co.kr/api/services/MailService"; // 전자결재 외부망
	  //String endPoint = "http://ci.eagleoffice.co.kr/api/services/MailService"; //전자결재 내부망
		
		//String endPoint_tw = "http://circle.tw.hanwha.com/api/axis/services/MailService"; //Circle 운영
		//String endPoint = "http://circledev.tw.hanwha.com/api/axis/services/MailService"; //Circle 개발
		//2022.02.07 Port 7500 Add
		String endPoint = "http://ep.circle.hanwha.com:7500/api/axis/services/MailService";   //2021.11.14부터 발송(Sec Level = 2)
	    String endPoint_eg = "http://hanwha.eagleoffice.co.kr:7500/api/services/MailService"; //2021.11.14부터 발송(Sec Level = 1,3,5)
	    
		MailServiceProxy proxy = null;
		SignerInfoCreate singerInfoCreate = null;
		WsMailInfo mailInfo = null;
		WsRecipient[] wsRecipients = null;
		WsAttachFile[] wsAttachFiles = null;
	
		SiebelPropertySet sendMISMail	= null;
		SiebelPropertySet headerHelper	= null;
		SiebelPropertySet attachEty		= null;
		SiebelPropertySet recipientEty	= null;

		SiebelPropertySet siebelMessage	= null;
		SiebelPropertySet sendMailResp	= null;
	
		String error = "";
		
		outputs.setProperty("Error", "");
		
		
		try{

			
			siebelMessage	= Common.getChild(inputs, "SiebelMessage");
			sendMISMail		= Common.getChild(siebelMessage, "sendMISMail");

			headerHelper	= Common.getChild(sendMISMail,	"HeaderHelperCSVO");
			attachEty		= Common.getChild(sendMISMail,	"AttachEtyCSVO");
			recipientEty	= Common.getChild(sendMISMail,	"RecipientEtyCSVO");
			
			/*
			 * ***************************************************************************
			 *  전송 메일  생성 
			 * ****************************************************************************	
			 */
	

           singerInfoCreate  = new SignerInfoCreate();

			// 메일 기본 정보
			mailInfo = singerInfoCreate.getMailInfo(headerHelper, attachEty.getChildCount());
			// 메일 수신자 정보
			wsRecipients = singerInfoCreate.getMailReceiverInfos(recipientEty);
			// 메일 첨부파일
			wsAttachFiles = singerInfoCreate.getMailAttachFiles(attachEty);


			/*
			 * ***************************************************************************
			 *  메일 전송 웹서비스 호출
			 * ****************************************************************************	
			 */
		
			//proxy = new MailServiceProxy(endPoint);
			//20211007 일반사용자 추가(epTrCode => 1:방산,2:민수,3:일반,5:공통(방산))
			//20211114이후부터 민수(2)를 제외하고 모두 이글오피스로 전송하도록 수정
			String checkenpPoint = headerHelper.getProperty("epTrCode");
			if(checkenpPoint.equals("2"))
			{//민수사용자
				proxy = new MailServiceProxy(endPoint);
			}
			else
			{//이외의 사용자
				proxy = new MailServiceProxy(endPoint_eg);
			}
			
			String result = proxy.sendMISMail(sendMISMail.getProperty("BodyStr"), mailInfo, wsRecipients, wsAttachFiles);
			
			/*
			 * *******************************************************
			 *  웹서비스 호출 결과 바인딩		
			 *  ******************************************************
			 */						

			siebelMessage	= new SiebelPropertySet();
			sendMailResp	= new SiebelPropertySet();
		
			siebelMessage	.setType("SiebelMessage");
			sendMailResp	.setType("sendMISMailResponse");
			sendMailResp	.setProperty("result", result);
			siebelMessage	.addChild(sendMailResp);
			
			outputs.addChild(siebelMessage);
					
		}catch (ESBFaultVO e) {
			
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
			mailInfo = null;
			wsRecipients = null;
			wsAttachFiles = null;
			singerInfoCreate = null;
			
			sendMISMail		= null;
			headerHelper	= null;
			attachEty		= null;
			recipientEty	= null;
     		siebelMessage	= null;
			sendMailResp	= null;
		}
	}
}