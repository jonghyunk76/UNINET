package stw.eai.myEagle;

import hanwha.neo.branch.ss.approval.axisws.ApprovalDocument;
import hanwha.neo.branch.ss.approval.axisws.ApprovalDocumentStatusOnly;
import hanwha.neo.branch.ss.approval.axisws.CancelApprovalDocument;
import hanwha.neo.branch.ss.approval.axisws.MisKey;
import hanwha.neo.branch.ss.approval.axisws.ReceiverInfo;
import hanwha.neo.branch.ss.approval.axisws.SignerInfo;
import hanwha.neo.branch.ss.approval.axisws.WsApAttachFile;
import hanwha.neo.branch.ss.common.vo.WsAttachFile;
import hanwha.neo.branch.ss.common.vo.WsException;
import hanwha.neo.branch.ss.mail.vo.WsMailInfo;
import hanwha.neo.branch.ss.mail.vo.WsMailStatus;
import hanwha.neo.branch.ss.mail.vo.WsRecipient;
import hanwha.neo.branch.ss.mail.vo.WsResource;
//import hanwha.neo.branch.ss.org.service.NeoOrgWsProxy;
//import hanwha.neo.branch.ss.org.vo.OrgUserVO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import com.siebel.data.SiebelPropertySet;

public class SignerInfoCreate {

	private SignerInfo[] signerInfos = null;
	private ReceiverInfo[] receiverInfos = null;
	//private NeoOrgWsProxy proxy =null;
	
	public SignerInfoCreate(){
	}
	
	public SignerInfoCreate(String orgEndPoint){
		
		//this.proxy = new NeoOrgWsProxy();
		//proxy.setEndpoint(orgEndPoint);
			
	}
	
	public SignerInfo[] getSignerInfos() {
		return signerInfos;
	}

	public void setSignerInfos(SignerInfo[] signerInfos) {
		this.signerInfos = signerInfos;
	}

	public ReceiverInfo[] getReceiverInfos() {
		return receiverInfos;
	}

	public void setReceiverInfos(ReceiverInfo[] receiverInfos) {
		this.receiverInfos = receiverInfos;
	}

	public SignerInfo createSignerInfo( SiebelPropertySet psRouteVO) throws WsException ,RemoteException,IOException {
		
		SignerInfo signerInfo = new SignerInfo();
		int activity = Integer.parseInt( psRouteVO.getProperty("Activity"));
		int sequence = Integer.parseInt(psRouteVO.getProperty("Sequence") );
		
		boolean delegated = false;
		boolean arbitrary = false;
		String mail = psRouteVO.getProperty("MailAddress");
		String[] splitMail = mail.split("@");
		//OrgUserVO orgUserVO = proxy.searchDefaultUserByUserId(splitMail[0]);
		//String userKey = orgUserVO.getUserKey();
		//옵션값, 0:대리결재아님, 1:대리결재됨
		if("1".equals( psRouteVO.getProperty("Delegated") ) ){
			delegated = true;   // 대리결재자 여부
		}
		
		if("0".equals( psRouteVO.getProperty("Arbitrary") ) ){
			arbitrary = true;   // 전결권한 
		}
		
		if( activity == 7 ){
			
			activity = 1;  // 병렬결재
			
		}else if( activity == 4){
			
			activity = 2; // 병령합의
			
		}

		signerInfo.setAssignType( activity );  // 참여자 형태 ( 0,1,2,9)
		signerInfo.setSequence( sequence ); 
		signerInfo.setDelegeted( delegated );   // 대리결재자 여부
		signerInfo.setNotRemovable( false );   // 결재선상 삭제 불가능 여부
		signerInfo.setNotEditable( false);   // 결재선상 참여자 형태 수정 불가능 여부
		signerInfo.setArbitrary(arbitrary);   // 전결권한 
		//signerInfo.setUserkey(userKey);   // 사용자 키
		signerInfo.setUserkey("");   // 사용자 키
		//signerInfo.setUserId(splitMail[0]);   // 사용자id
		signerInfo.setUserId(psRouteVO.getProperty("UserID"));   // 사용자id 20160805 추가
		signerInfo.setUserName(psRouteVO.getProperty("UserName"));
		signerInfo.setCompanyId(psRouteVO.getProperty("CompCode"));
		//signerInfo.setCompanyId("501");//20151012결제수정 가능 처리 
		signerInfo.setCompanyName(psRouteVO.getProperty("CompName"));
		signerInfo.setDeptId(psRouteVO.getProperty("DeptCode"));
		signerInfo.setDeptName(psRouteVO.getProperty("DeptName"));
		signerInfo.setFunctionName(psRouteVO.getProperty("Duty"));
		signerInfo.setFunctionId(psRouteVO.getProperty("DutyCode"));
		signerInfo.setEmailAddr(psRouteVO.getProperty("MailAddress"));
		signerInfo.setComment(psRouteVO.getProperty("Opinion"));
	
		return signerInfo;
	}
/*	
    public NeoOrgWsProxy getProxy() {
		return proxy;
	}

	public void setProxy(NeoOrgWsProxy proxy) {
		this.proxy = proxy;
	}
*/
	public ReceiverInfo createReceiverInfo(SiebelPropertySet psRouteVO, String roleType) throws WsException ,RemoteException,IOException{

		int activity = Integer.parseInt( psRouteVO.getProperty("Activity"));	
		String mail = psRouteVO.getProperty("MailAddress");
		String[] splitMail = mail.split("@");
		//OrgUserVO orgUserVO = proxy.searchDefaultUserByUserId(splitMail[0]);
		//String userKey = orgUserVO.getUserKey();
		
		ReceiverInfo receiverInfo = new ReceiverInfo();
		
		receiverInfo.setRoleType(roleType);
		receiverInfo.setDeptType(false);
		receiverInfo.setIncludeChild(false);
		receiverInfo.setForwardable(true);
		receiverInfo.setReceiverKey("");
		//receiverInfo.setReceiverId(splitMail[0]);
		receiverInfo.setReceiverId(psRouteVO.getProperty("UserID"));// 사용자id 20160805 추가
		receiverInfo.setReceiverName(psRouteVO.getProperty("UserName"));
		receiverInfo.setCompanyId(psRouteVO.getProperty("CompCode"));
		//receiverInfo.setCompanyId("501");//20151012결제수정 가능 처리 
		receiverInfo.setCompanyName(psRouteVO.getProperty("CompName"));
		receiverInfo.setDeptId(psRouteVO.getProperty("DeptCode"));
		receiverInfo.setDeptName(psRouteVO.getProperty("DeptName"));
		receiverInfo.setFunctionName(psRouteVO.getProperty("Duty"));
		receiverInfo.setFunctionId(psRouteVO.getProperty("DutyCode"));
		receiverInfo.setEmailAddr(psRouteVO.getProperty("MailAddress"));
	
		return receiverInfo;
	}
    
    
    public ApprovalDocument getApprovalDocument(SiebelPropertySet psStartProcessWSVO,String companyId) {
    	
		ApprovalDocument apprDoc = new ApprovalDocument();
		
		apprDoc.setBodyContent(psStartProcessWSVO.getProperty("Body"));
		apprDoc.setBodyContentType( Integer.parseInt( psStartProcessWSVO.getProperty("BodyType") ) );   // 본문 종류 ( 1: html ,2 : mht , 기본 1 )
		
		if("D15AP0043".equals(psStartProcessWSVO.getProperty("SystemID"))){			
			apprDoc.setPreservationTermCode("5");   // 문서보존 년한 1,3,5,10 파워(D15AP0043)
		}
		else
		{
			
			apprDoc.setPreservationTermCode("3");    // 문서보존 년한 1,3,5,10 테크윈(D17AP0043) 정밀기계(D16AP0043)
		}
		
		apprDoc.setInitDeptOpenYn("Y");          // 상신 부서 문서 공개 여부 
		apprDoc.setDeptOpenYn("Y");  // 협조 부서 문서공개 여부
		apprDoc.setChildIncYn("Y");   // 하위부서 포함 여부
		apprDoc.setMisDocId( psStartProcessWSVO.getProperty("MisID") );  // 연계결재문서 고유KEY
		apprDoc.setSystemId( psStartProcessWSVO.getProperty("SystemID") );  
		//apprDoc.setDocumentLevelCode("3");
		apprDoc.setDocumentLevelCode("2"); //WCLEE@20151026 대외비로 변경요청 
	    apprDoc.setTitle( psStartProcessWSVO.getProperty("Title") );
	    apprDoc.setCompanyId(companyId);   // 삼섬테크윈 코드값 추후 값 결정
		apprDoc.setFlowCase("customized");
		apprDoc.setWfCode("");
		apprDoc.setWfDataType("");
		apprDoc.setWfData("");
		apprDoc.setFormCode("MIS_CRM");
		
		return apprDoc;
	}
    
 public ApprovalDocument getApprovalDocument(SiebelPropertySet psStartProcessWSVO
		 ,SignerInfo[] localSignerInfos,ReceiverInfo[] localReceiverInfos,WsApAttachFile[] localAttachfiles){
    	
		ApprovalDocument apprDoc = new ApprovalDocument();
		
		apprDoc.setBodyContent(psStartProcessWSVO.getProperty("Body"));
		apprDoc.setBodyContentType( Integer.parseInt( psStartProcessWSVO.getProperty("BodyType") ) );   // 본문 종류 ( 1: html ,2 : mht , 기본 1 )
		
		if("D15AP0043".equals(psStartProcessWSVO.getProperty("SystemID"))){			
			apprDoc.setPreservationTermCode("5");   // 문서보존 년한 1,3,5,10 파워(D15AP0043)
		}
		else
		{
			
			apprDoc.setPreservationTermCode("3");    // 문서보존 년한 1,3,5,10 테크윈(D17AP0043) 정밀기계(D16AP0043)
		}
		
		apprDoc.setInitDeptOpenYn("Y");          // 상신 부서 문서 공개 여부 
		apprDoc.setDeptOpenYn("Y");  // 협조 부서 문서공개 여부
		apprDoc.setChildIncYn("Y");   // 하위부서 포함 여부
		apprDoc.setMisDocId( psStartProcessWSVO.getProperty("MisID") );  // 연계결재문서 고유KEY
		apprDoc.setSystemId( psStartProcessWSVO.getProperty("SystemID") );  
		//apprDoc.setDocumentLevelCode("3");
		apprDoc.setDocumentLevelCode("2"); //WCLEE@20151026 대외비로 변경요청 
	    apprDoc.setTitle( psStartProcessWSVO.getProperty("Title") );
	    //apprDoc.setCompanyId("D11");   // 삼섬테크윈 코드값 추후 값 결정
	    apprDoc.setCompanyId("501");   // 20151012 결제내용 수정 위한 값 - 501 변경
	    apprDoc.setSignerInfos(localSignerInfos);
		apprDoc.setReceiverInfos(localReceiverInfos);
		apprDoc.setAttachfiles(localAttachfiles);
		apprDoc.setFlowCase("customized");
		apprDoc.setWfCode("");
		apprDoc.setWfDataType("");
		apprDoc.setWfData("");
		apprDoc.setFormCode("MIS_CRM");
		
		return apprDoc;
	}
    
    
    
    
    public void createSignerInfosReceiverInfos(ArrayList<SiebelPropertySet> psRouteVOs) throws IOException{

    	ArrayList<SignerInfo> signerInfoArr =new ArrayList<SignerInfo>();
		//SignerInfo[] signerInfos =null;
		SignerInfo signerInfo = null;
		
		ArrayList<ReceiverInfo> receiverInfoArr =new ArrayList<ReceiverInfo>();
		//ReceiverInfo[] receiverInfos =null;
		ReceiverInfo receiverInfo = null;

		String activity = "";
		String receiver = "receiver";
		String referer = "referer";
		int sequence =0;
		int voLength = psRouteVOs.size();
		
		for(int i = 0 ; i < voLength ; i++){
			
			activity = psRouteVOs.get(i).getProperty("Activity");
			sequence = Integer.parseInt( psRouteVOs.get(i).getProperty("Sequence") );
			
		
			// 0 : 기안 , 1: 결재 , 2: 합의  ,7 : 병렬결재 , 4:병렬합의
			if(  "0".equals(activity) || "1".equals(activity) || "2".equals(activity) || "7".equals(activity) || "4".equals(activity)){
				
				signerInfo = this.createSignerInfo(psRouteVOs.get(i));
				signerInfoArr.add(signerInfo);
				
			}else if( "9".equals(activity) ){
				// 통보(기안직후) , 통보 ( 결재 진행 중) , 통보(최종 승인 후)
				
				    String tempActivity = "";
				    int tempSequence = 0;
				    String tempBeforeActivity9 = "";
				    //String tempAfterActivity9 = "";
				    //String tempBeforeActivity1 = "";
				    String tempAfterActivity1 = "";
				    
				    if( sequence == 1){
				    	 // 현재 문서 앞에 기안자 밖에 없을 경우 현재 문서는 통보(기안직후)
		    			 tempBeforeActivity9 = referer;
		    			 
		    		}else {
		    			
		    			 for( int j = 1 ; j < voLength ; j++){
						    	
						    	tempActivity = psRouteVOs.get(j).getProperty("Activity");
						    	tempSequence = Integer.parseInt(psRouteVOs.get(j).getProperty("Sequence"));
						    	
                                if( "1".equals(tempActivity) || "2".equals(tempActivity)){
						    		
						    		if(sequence < tempSequence ){
							    		 // 현재 문서 후
							    		 tempAfterActivity1 = "1";
							    		
							    	}	
						    	}
						    	
						    }
		    		}
				    
				    
				    if(referer.equals(tempBeforeActivity9)){
				    	// 현재 문서가 sequence 1 이고 activity 가 9 인 경우는 통보( 기안 직후)  ReceiverInfo roleTyep = referer
				    	receiverInfo = this.createReceiverInfo(psRouteVOs.get(i),referer);
						
				    	receiverInfoArr.add(receiverInfo);
				    	
				    }else{
				    	
				    	if( "1".equals(tempAfterActivity1) ){
	                    	
	                    	signerInfo = this.createSignerInfo(psRouteVOs.get(i));
							
							signerInfoArr.add(signerInfo);
				    		 
	                    	
				    		 
				    	 }else{
				    		 
				    		 receiverInfo = this.createReceiverInfo(psRouteVOs.get(i),receiver);
								
	   					     receiverInfoArr.add(receiverInfo);
				    		   
				    	 }
				    }
	
			}
			else if( "5".equals(activity) ){
				//20160804 추가 Referer
				receiverInfo = this.createReceiverInfo(psRouteVOs.get(i),referer);
		    	receiverInfoArr.add(receiverInfo);
				
			}
			else if( "6".equals(activity) ){
				//20160804 추가  Receiver
				 receiverInfo = this.createReceiverInfo(psRouteVOs.get(i),receiver);
				 receiverInfoArr.add(receiverInfo);
			}
			
		}
		
		
		//SignerInfo[] localSignerInfos  = (SignerInfo[])signerInfoArr.toArray();
		//ReceiverInfo[] localReceiverInfos  = (ReceiverInfo[])receiverInfoArr.toArray();
		
		SignerInfo[] localSignerInfos  = new SignerInfo[signerInfoArr.size()];
				
			for( int k = 0; k < signerInfoArr.size(); k++){
				
				localSignerInfos[k] = (SignerInfo)signerInfoArr.get(k);
			}
		
		this.setSignerInfos(localSignerInfos);
			
		ReceiverInfo[] localReceiverInfos  = null;
		if(receiverInfoArr.size() != 0){
			
			localReceiverInfos  =  new ReceiverInfo[receiverInfoArr.size()];
			for( int y = 0; y < receiverInfoArr.size(); y++){
				
				localReceiverInfos[y] = (ReceiverInfo)receiverInfoArr.get(y);
			}
			
			this.setReceiverInfos(localReceiverInfos);	
		}
	
	}
    
    
    public WsApAttachFile[] getAttachFiles( ArrayList<SiebelPropertySet> psAttchVOs){
    	
    	File				file				= null;
		FileDataSource		fds					= null;
		DataHandler			dh					= null;
    	WsApAttachFile[] attachfiles = new WsApAttachFile[psAttchVOs.size()];
    	WsApAttachFile attachfile = null;
    	
		for(int i = 0 ; i < attachfiles.length ; i++){
			
			attachfile = new WsApAttachFile();

			file	= new File(psAttchVOs.get(i).getProperty("file"));
			fds		= new FileDataSource(file);
			dh		= new DataHandler(fds);
			
			attachfile.setSeqID(i);
			attachfile.setFileName(psAttchVOs.get(i).getProperty("FileName"));
			attachfile.setFileSize(String.valueOf(file.length()));
			attachfile.setFileInfo(dh); 
			//attachfile.setFileId("");
			attachfiles[i] = attachfile;
		}
		
		return attachfiles;
	}
    
    
 public CancelApprovalDocument getCancelApprovalDocument(SiebelPropertySet psCancelProc) {
    	
	    CancelApprovalDocument apprDoc = new CancelApprovalDocument();
		
		apprDoc.setMisDocId( psCancelProc.getProperty("MisID"));  // 연계결재문서 고유KEY
		apprDoc.setSystemId( psCancelProc.getProperty("SystemID") );  
		apprDoc.setComment( " Cancel Approval [" + psCancelProc.getProperty("CancelDate") +"] " );
		
		return apprDoc;
	}
 
 
 
 public void setRevisionByMisIdResult(ApprovalDocumentStatusOnly[] approvalDocumnetStausOnlys,SiebelPropertySet psProcRevs) {
	  
	  int length = approvalDocumnetStausOnlys.length;
	  ApprovalDocumentStatusOnly approvalDocumnetStausOnly = null;
	  SiebelPropertySet psProcRev= null;
	  
		for(int i = 0 ; i < length ; i ++){
		
			approvalDocumnetStausOnly =  approvalDocumnetStausOnlys[i];
			
			psProcRev		= new SiebelPropertySet();
			
			psProcRev.setType("ProcessRevisionWSVO");
			psProcRev.setProperty("Revision"	, approvalDocumnetStausOnly.getRevision());
			psProcRev.setProperty("Status"		, String.valueOf(approvalDocumnetStausOnly.getStatus()) );
			psProcRev.setProperty("MisID"		, approvalDocumnetStausOnly.getMisDocId());
			psProcRev.setProperty("ProcinstID"	, approvalDocumnetStausOnly.getInstanceId());
			psProcRevs.addChild(psProcRev);
		}
		
	}	
 
 
 
 public MisKey[] getMisKeys(SiebelPropertySet psMisKeyWSVOs) throws WsException, RemoteException{
	 
	    int cnt		= psMisKeyWSVOs.getChildCount();
	    MisKey[] misKeys = new MisKey[cnt];
	    MisKey misKey = null;
	    for(int i = 0 ; i < cnt ; i++ ) {
	    	misKey	= new MisKey();
			misKey.setMisDocId(psMisKeyWSVOs.getChild(i).getProperty("MisID"));
			misKey.setSystemId(psMisKeyWSVOs.getChild(i).getProperty("SystemID"));
			misKeys[i]	= misKey;
		}
		
	
		return misKeys;
	}
 
 
 
 public WsMailInfo getMailInfo( SiebelPropertySet headerHelper,int attchFileCount)  {
	 String msgType = headerHelper.getProperty("msgType");
	 boolean important = false;
		 
	 if("confidential".equals(msgType)){
		 important = true;
	 }
		 
	 WsMailInfo mailInfo = new WsMailInfo();
	 
	 mailInfo.setSubject(headerHelper.getProperty("subject"));
	 mailInfo.setHtmlContent(headerHelper.getProperty("bHtmlContentCheck").equals("Y"));
	 mailInfo.setAttachCount(attchFileCount);
	 mailInfo.setSenderEmail(headerHelper.getProperty("senderMailAddr"));
	 mailInfo.setImportant(important);
	
	return  mailInfo;
}
	
	
	public WsRecipient[] getMailReceiverInfos( SiebelPropertySet recipientEty) {
		
		int length = recipientEty.getChildCount();
		WsRecipient[] wsRecipients	= new WsRecipient[length];
		WsRecipient  wsRecipient = null;
		String recvType = null;
		
		for(int i = 0 ; i < length ; i++){
	
			recvType = recipientEty.getChild(i).getProperty( "recType");
			
			if( "t".equals(recvType) ){
				
				recvType = "TO";
				
			}else if( "c".equals(recvType)){
				
				recvType = "CC";
				
			}else if( "b".equals(recvType)){
				
				recvType = "BCC";
			}
			
			wsRecipient	= new WsRecipient();
			wsRecipient.setSeqID(i);
			wsRecipient.setRecvType( recvType );
			wsRecipient.setRecvEmail(recipientEty.getChild(i).getProperty( "recAddr"));
			wsRecipient.setDept(false);
			
			wsRecipients[i] = wsRecipient;
		}
		
		
		return wsRecipients;
	}
	
	public WsAttachFile[] getMailAttachFiles(SiebelPropertySet attachEty) {
		
		File				file				= null;
		FileDataSource		fds					= null;
		DataHandler			dh					= null;

		int length = attachEty.getChildCount();
		WsAttachFile[] wsAttachFiles	= new WsAttachFile[length];
		WsAttachFile wsAttachFile = null;
		for(int i = 0 ; i < length ; i++){
			
			file	= new File(attachEty.getChild(i).getProperty( "file"));
			fds		= new FileDataSource(file);
			dh		= new DataHandler(fds);

			wsAttachFile	= new WsAttachFile();
			wsAttachFile.setSeqID(i);
			wsAttachFile.setFileName(attachEty.getChild(i).getProperty( "fileName"));
			wsAttachFile.setFileSize(String.valueOf(file.length()));
			wsAttachFile.setFileInfo(dh);
			//wsAttachFile.setFileId(fileId);
			wsAttachFiles[i] = wsAttachFile;
		}
		
		return wsAttachFiles;
	}
	
  public WsResource getMailWsResource(SiebelPropertySet resource) {

	  WsResource wsResource		= new WsResource();
	
	  wsResource.setSenderEmail(resource.getProperty(	"email"));
	  wsResource.setSenderPw(resource.getProperty("password"));

	  return wsResource;
  }


  public String[] getMailReceiverForCancel(SiebelPropertySet	psAddrForCancel) {

		int length	= psAddrForCancel.getChildCount();
		String[] receiverForCancel	= new String[length];
		
		for(int i = 0 ; i < length ; i ++) {
			
			receiverForCancel[i] = psAddrForCancel.getChild(i).getValue();
			
		}
		
		return receiverForCancel;
	}


  public String[] getMailKeys(SiebelPropertySet	psMtrKeys) {

	int length	= psMtrKeys.getChildCount();
	String[] mailKeys	= new String[length];
	
	for(int i = 0 ; i < length ; i++){
		
		mailKeys[i] = psMtrKeys.getChild(i).getValue();
	}
	
	return mailKeys;
  }
  
  public void setMailStatusCounts(WsMailStatus[] wsMailStatuss,SiebelPropertySet psStatusCnt) {
	  
	  int length = wsMailStatuss.length;
	  WsMailStatus wsMailStatus = null;
	  SiebelPropertySet psStatusCntVO = null;
	  
		for(int i = 0 ; i < length ; i++){
			
			wsMailStatus = wsMailStatuss[i];
			
			psStatusCntVO = new SiebelPropertySet();    
			
		    psStatusCntVO.setType("statusCountESBVOs");
			psStatusCntVO.setProperty("mtrKey", wsMailStatus.getMailKey());
			psStatusCntVO.setProperty("unSeenCount", "");
			psStatusCntVO.setProperty("isScheduled","Y");
			psStatusCntVO.setProperty("totalCount", "");
			psStatusCntVO.setProperty("deliveryCount", "");
			psStatusCntVO.setProperty("etcCount", "");
			psStatusCntVO.setProperty("seenCount", "");
			psStatusCntVO.setProperty("cancelCount", "");
			
			psStatusCnt.addChild(psStatusCntVO);
		}
	}
  
  
  public String getStr(  String str ) throws IOException {
	  
	    if( null == str){
	    	str = "";
	    }
		
		return str;
}	
  
  
  
public void writeSiebel( BufferedWriter w ,String title,SiebelPropertySet	psRouteVOs) throws IOException {
		
	        w.newLine();
	        w.write("============"+title +"========================\n");
		    w.write(psRouteVOs.toString());
	}	  

public void writeSiebelArray( BufferedWriter w ,String title,	ArrayList<SiebelPropertySet>	psRouteVOs) throws IOException {
	
	int voLength = psRouteVOs.size();
	SiebelPropertySet test = null;
	
	for(int i = 0 ; i < voLength ; i++){
		
		test =psRouteVOs.get(i);
		String activity =test.getProperty("Activity");
		int sequence = Integer.parseInt( test.getProperty("Sequence") );
		w.newLine();
	    w.write("============"+title +"========================\n");
		w.write("============= [" + i + "] ============\n");
		w.write(test.toString());
		w.newLine();
	}
}	

public void writeString(  BufferedWriter w  ,String str) throws IOException {
	
		w.newLine();
		w.write(str);
}	

public BufferedWriter createFile(	String fileName) throws IOException {
	  FileWriter f = new FileWriter(fileName);
	  BufferedWriter w = new BufferedWriter(f);
	  return w;
}	

public void close(	BufferedWriter w) throws IOException {
	
	w.close();
	
	}	
}
