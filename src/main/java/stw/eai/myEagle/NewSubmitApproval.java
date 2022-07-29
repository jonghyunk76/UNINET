package stw.eai.myEagle;

import hanwha.neo.branch.ss.approval.axisws.ApprovalDocument;
import hanwha.neo.branch.ss.approval.axisws.ApprovalServiceProxy;
import hanwha.neo.branch.ss.approval.axisws.ReceiverInfo;
import hanwha.neo.branch.ss.approval.axisws.SignerInfo;
import hanwha.neo.branch.ss.approval.axisws.WsApAttachFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;


public class NewSubmitApproval implements NewClient {

	//static Logger logger = Logger.getLogger(NewSendMail.class);
	
	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 BufferedWriter w = null;
		 FileWriter f = null;
		try{
			 f = new FileWriter("D:\\APPOVAL.txt");
			  w = new BufferedWriter(f);
			  w.write("======================= NewSubmitApproval =========================================");
			  w.newLine();
		}catch(IOException e){
			
		}
	
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
        //String endPoint = "http://www.ci.eagleoffice.co.kr/api/services/ApprovalService"; // 전자결재 외부망
        //String endPoint = "http://ci.eagleoffice.co.kr/api/services/ApprovalService"; //전자결재 내부망
        //String endPoint = "http://hanwha.eagleoffice.co.kr/api/services/ApprovalService"; //운영
        //String orgEndPoint = "http://www.ci.eagleoffice.co.kr/api/ss/org"; // 전자결재 외부망
 	      //String orgEndPoint = "http://ci.eagleoffice.co.kr/api/ss/org"; //전자결재 내부망
        //String orgEndPoint = "http://hanwha.eagleoffice.co.kr/api/ss/org"; //운영
		//2022.02.07 Port 7500 Add
        String endPoint = "http://ep.circle.hanwha.com:7500/api/axis/services/ApprovalService"; //운영 2019.06.10(민수)
        
		SiebelPropertySet	siebelMessage		= null;
		SiebelPropertySet	psSubmitApproval	= null;
		SiebelPropertySet	psSubmitApprovalResp= null;
		SiebelPropertySet	psStartProcessWSVO	= null;

		ArrayList<SiebelPropertySet>	psRouteVOs	= null;
		ArrayList<SiebelPropertySet>	psAttchVOs	= null;
		
		ApprovalServiceProxy proxy = null;
		SignerInfoCreate singerInfoCreate = null;
		ApprovalDocument apprDoc = null;
		SignerInfo[] signerInfos = null;
		ReceiverInfo[] receiverInfos = null;
		WsApAttachFile[] attachfiles = null;
		
		String error = "";
		outputs.setProperty("Error", "");
		outputs.setProperty("Result", "");
		
		//BufferedWriter w = null;
		
		try {
			
			singerInfoCreate = new SignerInfoCreate();

			// Inputs
			siebelMessage		= Common.getChild	(inputs, 			"SiebelMessage");
			psSubmitApproval	= Common.getChild	(siebelMessage, 	"submitApproval");
			psStartProcessWSVO	= Common.getChild	(psSubmitApproval,	"startProcessWSVO");
			psRouteVOs			= Common.getChilds	(psStartProcessWSVO,"RouteVOs");
			psAttchVOs			= Common.getChilds	(psStartProcessWSVO,"AttachmentVOs");
			
			
			    w.newLine();
		        w.write("=========== =psRouteVOs ========================");
			    w.write(psRouteVOs.toString());
			    w.newLine();

		/*
		 * ***************************************************************************
		 *  전자결재문서 생성 
		 * ****************************************************************************	
		 */

			// SignerInfo[] ,ReceiverInfo[] 생성
			singerInfoCreate.createSignerInfosReceiverInfos(psRouteVOs);
			signerInfos = singerInfoCreate.getSignerInfos();  //SignerInfo[]
			receiverInfos = singerInfoCreate.getReceiverInfos(); //ReceiverInfo[]
			attachfiles = singerInfoCreate.getAttachFiles(psAttchVOs); // 첨부파일
	
			// ApprovalDocument Document 생성
			apprDoc = singerInfoCreate.getApprovalDocument(psStartProcessWSVO,signerInfos[0].getCompanyId());
			apprDoc.setSignerInfos(signerInfos);
			
			if(null != receiverInfos){
				apprDoc.setReceiverInfos(receiverInfos);
			}
			
			if(null != attachfiles){
				apprDoc.setAttachfiles(attachfiles);
			}
		
	/*
	 * *******************************************************
	 *  결재 상신 웹서비스 호출 		
	 *  ******************************************************
	 */

			String result = "";			
			proxy = new ApprovalServiceProxy(endPoint);

			result = proxy.submitApprovalUseUserId(apprDoc);
			
			result = result.toLowerCase();

		/*
		 * *******************************************************
		 *  결재 상신 웹서비스 호출 결과 바인딩		
		 *  ******************************************************
		 */						

			outputs.setProperty("Error", error);
			outputs.setProperty("Result", result);
			
			siebelMessage			= new SiebelPropertySet();
			psSubmitApprovalResp	= new SiebelPropertySet();

			siebelMessage.setType("SiebelMessage");

			psSubmitApprovalResp.setType("submitApprovalResponse");
			psSubmitApprovalResp.setProperty("result",result);
			siebelMessage.addChild(psSubmitApprovalResp);

			outputs.addChild(siebelMessage);
			

		}  catch (ESBFaultVO e) {
			
			try{
				StackTraceElement[] ex = e.getStackTrace();
				  w.write("============ ESBFaultVO EXCEPTION ========================== ");
				  w.newLine();
				for( int k = 0; k < ex.length; k++){
					
					
					StackTraceElement exxx = ex[k];
					  w.write(exxx.toString());
					  w.newLine();
					  
				}
			}catch(IOException exx){
				
			}
			
			
			error += "ESBFaultVO : [" + e.getFaultCode1() + "] "+ e.getFaultMessage();
			outputs.setProperty("Error", error);
		
			throw new SiebelBusinessServiceException(e, e.getFaultCode1(), e.getFaultMessage());
		} catch (RemoteException e) {
			
			try{
				StackTraceElement[] ex = e.getStackTrace();
				  w.write("============ RemoteException EXCEPTION ========================== ");
				  w.newLine();
				for( int k = 0; k < ex.length; k++){
					
					
					StackTraceElement exxx = ex[k];
					  w.write(exxx.toString());
					  w.newLine();
					  
				}
			}catch(IOException exx){
				
			}
			
			
			error += "RemoteException \n" + e.getMessage()  ;
			outputs.setProperty("Error", error);
			
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
	
		} catch	(Exception e){
			
			try{
				StackTraceElement[] ex = e.getStackTrace();
				  w.write("============ Exception EXCEPTION ========================== ");
				  w.newLine();
				for( int k = 0; k < ex.length; k++){
					
					
					StackTraceElement exxx = ex[k];
					  w.write(exxx.toString());
					  w.newLine();
					  
				}
			}catch(IOException exx){
				
			}
		
			error += "Exception \n" + e.getMessage();
			outputs.setProperty("Error", error);
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
			
		}finally{
			
			siebelMessage		= null;
			psSubmitApproval	= null;
			psStartProcessWSVO	= null;
            psRouteVOs			= null;
			psAttchVOs			= null;
			
			singerInfoCreate = null;
			signerInfos = null;
			receiverInfos = null;
			attachfiles = null;
			apprDoc = null;
			proxy = null;	
			
			try{
				w.close();
			}catch(IOException exx){
				
			}
			
			
			
		}
	}
	
}
