package stw.eai.myEagle;

import hanwha.neo.branch.ss.approval.axisws.ApprovalDocumentStatus;
import hanwha.neo.branch.ss.approval.axisws.ApprovalServiceProxy;
import hanwha.neo.branch.ss.approval.axisws.MisKey;
import hanwha.neo.branch.ss.approval.axisws.ReceiverInfo;
import hanwha.neo.branch.ss.approval.axisws.SignerInfo;
import hanwha.neo.branch.ss.approval.axisws.WsApAttachFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;
import stw.eai.mySingle.STWMySingleClient;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class NewGetStatusByMisId implements NewClient
{

    public NewGetStatusByMisId()
    {
    }

    public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException{

 		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	
		 BufferedWriter w = null;
		 FileWriter f = null;
		try{
			 f = new FileWriter("D:\\StatusApproval.txt");
			  w = new BufferedWriter(f);
			  w.write("======================= NewGetStatusByMisId =========================================");
			  w.newLine();
		}catch(IOException e){
			
		}
	
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	
    	  //String endPoint = "http://www.ci.eagleoffice.co.kr/api/services/ApprovalService"; // 전자결재 외부망
  	      //String endPoint = "http://ci.eagleoffice.co.kr/api/services/ApprovalService"; //전자결재 내부망
  	      //String endPoint = "http://hanwha.eagleoffice.co.kr/api/services/ApprovalService"; //운영   
		  //2022.02.07 Port 7500 Add
  	      String endPoint = "http://ep.circle.hanwha.com:7500/api/axis/services/ApprovalService"; //운영 2019.06.10
  	      
    	
    	  SignerInfoCreate signerInfoCreate = new SignerInfoCreate();
          MisKey misKey = null;
          ApprovalServiceProxy proxy = null;
          ApprovalDocumentStatus approvalDocumnetStaus = null;
          SignerInfo[] signerInfos = null;
          ReceiverInfo[] receiverInfos = null;
          WsApAttachFile[] wsApAttachFiles = null;
          int signerInfolength =0;
          int receiverInfoLength =0;
          int wsApAttachFileLength = 0;
     
	       SiebelPropertySet psSiebelMessage = null;
	       SiebelPropertySet psGetStatReq = null;
	       SiebelPropertySet psMisKeyWSVO = null;
	       SiebelPropertySet psGetStatResp = null;
	        
	       SiebelPropertySet psProcWSVO = null;
	       SiebelPropertySet psRouteVOs = null;
	       SiebelPropertySet psAttVOs = null;
	       String error ="";
	       //20160321
		   String receiver = "receiver";
		   String referer = "referer";
		   String tempBeforeActivity = "";
 
        try{
 
            psSiebelMessage = Common.getChild(inputs, "SiebelMessage");
            psGetStatReq = Common.getChild(psSiebelMessage, "getStatusByMisId");
            psMisKeyWSVO = Common.getChild(psGetStatReq, "misKeyWSVO");
  
        	/*
			 * ***************************************************************************
			 *  MISKEY 배열 생성 
			 * ****************************************************************************	
			 */
	
		
			 misKey = new MisKey();
			 misKey.setMisDocId(psMisKeyWSVO.getProperty("MisID"));
			 //misKey.setSystemId("D11AP0043");
			 misKey.setSystemId(psMisKeyWSVO.getProperty("SystemID"));
			
			/*
			 * *******************************************************
			 *   서비스 호출 		
			 *  ******************************************************
			 */
	
					
			proxy = new ApprovalServiceProxy(endPoint);
			approvalDocumnetStaus = proxy.getStatusByMisId(misKey);

			/*
			 * *******************************************************
			 *  결재 상신 웹서비스 호출 결과 바인딩		
			 *  ******************************************************
			 */						
	
            psSiebelMessage = new SiebelPropertySet();
            psGetStatResp = new SiebelPropertySet();
            psProcWSVO = new SiebelPropertySet();
            
            psSiebelMessage.setType("SiebelMessage");
            psGetStatResp.setType("getStatusByMisIdResponse");
            psSiebelMessage.addChild(psGetStatResp);
            psProcWSVO.setType("processStatusWSVO");
            
            psGetStatResp.addChild(psProcWSVO);
         
            //psProcWSVO.setProperty("Body", approvalDocumnetStaus.getBodyContent());
            if(null != approvalDocumnetStaus.getBodyContent())
            {
            	psProcWSVO.setProperty("Body", approvalDocumnetStaus.getBodyContent());
            }
            else
            {
            	psProcWSVO.setProperty("Body", "<html></html>");
            }
            psProcWSVO.setProperty("BodyType", "2");
            psProcWSVO.setProperty("CreateDate", approvalDocumnetStaus.getStdDate());
            psProcWSVO.setProperty("LocaleInfo", "ko_KR.euc-kr");
            psProcWSVO.setProperty("MisID", approvalDocumnetStaus.getMisDocId());
            
            psProcWSVO.setProperty("NotiMail", "0");
            psProcWSVO.setProperty("Priority", "0");
            psProcWSVO.setProperty("Security", "0");
            
            psProcWSVO.setProperty("Status", String.valueOf(approvalDocumnetStaus.getStatus()) );
            psProcWSVO.setProperty("SystemID", approvalDocumnetStaus.getSystemId());
            psProcWSVO.setProperty("TimeZone", "GMT");
            psProcWSVO.setProperty("Title", approvalDocumnetStaus.getTitle());
            
            signerInfos = approvalDocumnetStaus.getSignerInfos();
            receiverInfos = approvalDocumnetStaus.getReceiverInfos();
            wsApAttachFiles = approvalDocumnetStaus.getAttachfiles();
           
        
            /*
			 *  SignerInfo 가져와서 바인딩
			 */						
            if( null != signerInfos && signerInfos.length > 0 ){
            	
            	signerInfolength = signerInfos.length;
            	SignerInfo signerInfo = null;
                for(int i = 0; i < signerInfolength; i++){
                	signerInfo = signerInfos[i];
                    
                    psRouteVOs = new SiebelPropertySet();
                    
                    psRouteVOs.setType("RouteVOs");
                    psRouteVOs.setProperty("ActionType", String.valueOf(signerInfo.getStatus()));
                    psRouteVOs.setProperty("Activity", String.valueOf(signerInfo.getAssignType()));
                    psRouteVOs.setProperty("AlarmType", "0");
                    psRouteVOs.setProperty("Approved", signerInfoCreate.getStr(signerInfo.getEndDate()));
                    
                    if( signerInfo.isArbitrary()){
                    	 psRouteVOs.setProperty("Arbitrary", "0");
                    }else{
                    	 psRouteVOs.setProperty("Arbitrary", "-1");
                    }
                    
                    psRouteVOs.setProperty("Arrived", "");
                    psRouteVOs.setProperty("BodyModify", "0");
                    psRouteVOs.setProperty("CompCode", signerInfoCreate.getStr(signerInfo.getCompanyId()));
                    psRouteVOs.setProperty("CompName", signerInfoCreate.getStr(signerInfo.getCompanyName()));
                    
                    if( signerInfo.isDelegeted()){
                    	   psRouteVOs.setProperty("Delegated", "1");
                    }else{
                    	
                    	psRouteVOs.setProperty("Delegated", "0");
                    }
                    
                    psRouteVOs.setProperty("DeptCode", signerInfoCreate.getStr(signerInfo.getDeptId() ) );
                    psRouteVOs.setProperty("DeptName", signerInfoCreate.getStr(signerInfo.getDeptName() ) );
                    psRouteVOs.setProperty("Duration", "0");
                    psRouteVOs.setProperty("Duty", signerInfoCreate.getStr(signerInfo.getFunctionName() ) );
                    psRouteVOs.setProperty("DutyCode", signerInfoCreate.getStr(signerInfo.getFunctionId() ) );
                    psRouteVOs.setProperty("GroupCode", "");
                    psRouteVOs.setProperty("GroupName", "");
                    psRouteVOs.setProperty("MailAddress", signerInfoCreate.getStr(signerInfo.getEmailAddr() ) );
                    psRouteVOs.setProperty("Opinion", signerInfoCreate.getStr(signerInfo.getComment() ) );
                    psRouteVOs.setProperty("Reserved", "");
                    psRouteVOs.setProperty("RouteModify","0" );
                    psRouteVOs.setProperty("Sequence", String.valueOf( signerInfo.getSequence() ) );
                    psRouteVOs.setProperty("SocialID", "00000000");
                    psRouteVOs.setProperty("UserID", signerInfoCreate.getStr(signerInfo.getUserId() ) );
                    psRouteVOs.setProperty("UserName", signerInfoCreate.getStr(signerInfo.getUserName() ) );
                                   
                    psProcWSVO.addChild(psRouteVOs);
                }
            }
        	
   
            /*
			 *  ReceiverInfo 가져와서 바인딩
			 */					
            if( null != receiverInfos && receiverInfos.length > 0 ){
            	
            	   receiverInfoLength = receiverInfos.length;
                   int reSequence = signerInfolength;
                   ReceiverInfo receiverInfo = null;
                   for(int j = 0; j < receiverInfoLength; j++){
                       receiverInfo = receiverInfos[j];
                       
                       psRouteVOs = new SiebelPropertySet();
                       
                       psRouteVOs.setType("RouteVOs");
                       psRouteVOs.setProperty("ActionType", "0");
                       //wclee20160321 변경 참조(5) 수신(6) 추가 
                       tempBeforeActivity = signerInfoCreate.getStr(receiverInfo.getRoleType());
                       if(receiver.equals(tempBeforeActivity)) //수신(6)
                       {
                    	   psRouteVOs.setProperty("Activity", "6");
                       }
                       else if(referer.equals(tempBeforeActivity)) //참조(5)
                       {
                    	   psRouteVOs.setProperty("Activity", "5");
                       }
                       else
                       {
                    	   psRouteVOs.setProperty("Activity", "9");
                       }
                       //psRouteVOs.setProperty("Activity", "9");
                       
                       psRouteVOs.setProperty("AlarmType", "0");
                       psRouteVOs.setProperty("Approved", "");
                       psRouteVOs.setProperty("Arbitrary", "0");
                       
                       psRouteVOs.setProperty("Arrived", "");
                       psRouteVOs.setProperty("BodyModify", "0");
                       psRouteVOs.setProperty("CompCode", signerInfoCreate.getStr(receiverInfo.getCompanyId()));
                       psRouteVOs.setProperty("CompName", signerInfoCreate.getStr(receiverInfo.getCompanyName()));
          	           psRouteVOs.setProperty("Delegated", "0");
                     
                       psRouteVOs.setProperty("DeptCode", signerInfoCreate.getStr(receiverInfo.getDeptId()));
                       psRouteVOs.setProperty("DeptName", signerInfoCreate.getStr(receiverInfo.getDeptName()));
                       psRouteVOs.setProperty("Duration", "0");
                       psRouteVOs.setProperty("Duty", signerInfoCreate.getStr(receiverInfo.getFunctionName()));
                       psRouteVOs.setProperty("DutyCode", signerInfoCreate.getStr(receiverInfo.getFunctionId()));
                       psRouteVOs.setProperty("GroupCode", "");
                       psRouteVOs.setProperty("GroupName", "");
                       psRouteVOs.setProperty("MailAddress", signerInfoCreate.getStr(receiverInfo.getReceiverId())+"@hanwha.com");
                       psRouteVOs.setProperty("Opinion", "");
                       psRouteVOs.setProperty("Reserved", "");
                       psRouteVOs.setProperty("RouteModify","0" );
                       psRouteVOs.setProperty("Sequence",  String.valueOf(reSequence +j ) );
                       psRouteVOs.setProperty("SocialID", "00000000");
                       psRouteVOs.setProperty("UserID", signerInfoCreate.getStr(receiverInfo.getReceiverId()));
                       psRouteVOs.setProperty("UserName", signerInfoCreate.getStr(receiverInfo.getReceiverName()));
                       
                       if(!receiverInfo.isDeptType())
                       {
                    	   psProcWSVO.addChild(psRouteVOs);
                       }
                      
                   }
            }
   
            /*
             *  WsApAttachFile 가져와서 바인딩
         	*/	
            if(wsApAttachFiles != null && wsApAttachFiles.length > 0 ){ 
            	
	                wsApAttachFileLength = wsApAttachFiles.length;
	     
	                for(int jj = 0; jj < wsApAttachFileLength; jj++){
	                	WsApAttachFile wsApAttachFile = wsApAttachFiles[jj];
	                    
	                    psAttVOs = new SiebelPropertySet();
	                    
	                    psAttVOs.setType("AttachmentVOs");
	                    psAttVOs.setProperty("FileName", wsApAttachFile.getFileName());
	                    psAttVOs.setProperty("FileSize", wsApAttachFile.getFileSize());
	                    //psAttVOs.setProperty("Sequence", String.valueOf( wsApAttachFile.getSeqID() ));
	                    psAttVOs.setProperty("Sequence", String.valueOf( jj+1 ));
	                    psAttVOs.setProperty("StoreLocation", "");
	                    
	                    psProcWSVO.addChild(psAttVOs);
	                }

            }
            outputs.addChild(psSiebelMessage);
            outputs.setProperty("Error", "");
            

        }
        catch(ESBFaultVO e)
        {
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
            error = (new StringBuilder(String.valueOf(error))).append("ESBFaultVO : [").append(e.getFaultCode1()).append("] ").append(e.getFaultMessage()).toString();
            outputs.setProperty("Error", error);
          
            throw new SiebelBusinessServiceException(e, e.getFaultCode1(), e.getFaultMessage());
        }
        catch(RemoteException e)
        {
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
            error = (new StringBuilder(String.valueOf(error))).append("RemoteException \n").append(e.getMessage()).toString();
            outputs.setProperty("Error", error);
           		
            throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
        }
        catch(Exception e)
        {
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
            error = (new StringBuilder(String.valueOf(error))).append("Exception \n").append(e.getMessage()).toString();
            outputs.setProperty("Error", error);
         
            throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
        }finally{

	        psSiebelMessage = null;
	        psGetStatReq = null;
	        psMisKeyWSVO = null;
	        psGetStatResp = null;
	        psProcWSVO = null;
	        psRouteVOs = null;
	        psAttVOs = null;
	        
	        signerInfoCreate = null;
	        misKey = null;
	        proxy = null;
	        approvalDocumnetStaus = null;
	        signerInfos = null;
	        receiverInfos = null;
	        wsApAttachFiles = null;	
        
        }
       
    }
}
