package stw.eai.myEagle;

import hanwha.neo.branch.ss.approval.axisws.ApprovalDocumentStatusOnly;
import hanwha.neo.branch.ss.approval.axisws.ApprovalServiceProxy;
import hanwha.neo.branch.ss.approval.axisws.MisKey;

import java.rmi.RemoteException;

import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class NewGetRevisionByMisId implements NewClient {
	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		
        //String endPoint = "http://www.ci.eagleoffice.co.kr/api/services/ApprovalService"; // 전자결재 외부망
	    //String endPoint = "http://ci.eagleoffice.co.kr/api/services/ApprovalService"; //전자결재 내부망
	    //String endPoint = "http://hanwha.eagleoffice.co.kr/api/services/ApprovalService"; //운영
		//2022.02.07 Port 7500 Add
	    String endPoint = "http://ep.circle.hanwha.com:7500/api/axis/services/ApprovalService"; //운영 2019.06.10
	
		ApprovalServiceProxy proxy = null;
		SignerInfoCreate signerInfoCreate = null;
		MisKey[] misKeys = null;
		ApprovalDocumentStatusOnly[] approvalDocumnetStausOnlys = null;
	
		SiebelPropertySet	psSiebelMessage	= null;
		SiebelPropertySet	psGetRevision	= null;
		SiebelPropertySet	psMisKeyWSVOs	= null;
		SiebelPropertySet	psGetRevResp	= null;
		SiebelPropertySet	psProcRevs		= null;
		//SiebelPropertySet	psProcRev		= null;
		
		String			error			= "";
		try{
			
			psSiebelMessage	= Common.getChild(inputs			,"SiebelMessage");
			psGetRevision	= Common.getChild(psSiebelMessage	,"getRevisionByMisId");
			psMisKeyWSVOs	= Common.getChild(psGetRevision		,"misKeyWSVOs");
		
			signerInfoCreate = new SignerInfoCreate();
			
			/*
			 * ***************************************************************************
			 *  MISKEY 배열 생성 
			 * ****************************************************************************	
			 */
	
		
			misKeys = signerInfoCreate.getMisKeys(psMisKeyWSVOs);
			
			/*
			 * *******************************************************
			 *   서비스 호출 		
			 *  ******************************************************
			 */
		
					
			proxy = new ApprovalServiceProxy(endPoint);
			approvalDocumnetStausOnlys = proxy.getProcessIdByBulkMisId(misKeys);

			/*
			 * *******************************************************
			 *  서비스 호출 결과 바인딩		
			 *  ******************************************************
			 */						
			
			// Response
			psSiebelMessage	= new SiebelPropertySet();
			psGetRevResp	= new SiebelPropertySet();
			psProcRevs		= new SiebelPropertySet();
			//psProcRev		= new SiebelPropertySet();
			
			psSiebelMessage	.setType("SiebelMessage");
			
			psGetRevResp	.setType("getRevisionByMisIdResponse");
			psSiebelMessage	.addChild(psGetRevResp);
			
			psProcRevs		.setType("ProcessRevisionWSVOs");
			psGetRevResp	.addChild(psProcRevs);
	
			signerInfoCreate.setRevisionByMisIdResult(approvalDocumnetStausOnlys, psProcRevs);
			
			outputs.addChild(psSiebelMessage);
			outputs.setProperty("Error", "");
			
		
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
		} finally {
			signerInfoCreate	= null;
			proxy	= null;
			misKeys		= null;
			approvalDocumnetStausOnlys = null;
			
			psSiebelMessage	= null;
			psGetRevision	= null;
			psMisKeyWSVOs	= null;
			psGetRevResp	= null;
			psProcRevs		= null;
			//psProcRev		= null;
		}
	}
}
