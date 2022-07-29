package stw.eai.myEagle;

import hanwha.neo.branch.ss.approval.axisws.ApprovalServiceProxy;
import hanwha.neo.branch.ss.approval.axisws.CancelApprovalDocument;

import java.rmi.RemoteException;

import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class NewCancelApproval implements NewClient {

	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {

        //String endPoint = "http://www.ci.eagleoffice.co.kr/api/services/ApprovalService"; // 전자결재 외부망
        //String endPoint = "http://ci.eagleoffice.co.kr/api/services/ApprovalService"; //전자결재 내부망
		//2022.02.07 Port 7500 Add
        String endPoint = "http://ep.circle.hanwha.com:7500/api/axis/services/ApprovalService"; //전자결재 내부망 2019.06.10
        //String endPoint = "http://hanwha.eagleoffice.co.kr/api/services/ApprovalService"; //운영
		SiebelPropertySet	psCancelAP			= null;
		SiebelPropertySet	psCancelProc		= null;
		SiebelPropertySet	psSiebelMessage		= null;
		SiebelPropertySet	psCancelAPResp		= null;

		String error	= "";
		String result	= "";
		ApprovalServiceProxy proxy = null;
		SignerInfoCreate singerInfoCreate = null;
		CancelApprovalDocument apprDoc =  null;

		outputs.setProperty("Error", "");
		try {

			
			// get Inputs
			psSiebelMessage	= Common.getChild(inputs			,"SiebelMessage");
			psCancelAP		= Common.getChild(psSiebelMessage	,"cancelApproval");
			psCancelProc	= Common.getChild(psCancelAP		,"cancelProcessWSVO");

			/*
			 * ***************************************************************************
			 *  전자결재문서 생성 
			 * ****************************************************************************	
			 */
	
			singerInfoCreate = new SignerInfoCreate();
			apprDoc = singerInfoCreate.getCancelApprovalDocument(psCancelProc);
			
			/*
			 * *******************************************************
			 *  결재 상신 취소 서비스 호출 		
			 *  ******************************************************
			 */
	
					
			proxy = new ApprovalServiceProxy(endPoint);
			result = proxy.cancelApproval(apprDoc);

			/*
			 * *******************************************************
			 *  결재 상신 웹서비스 호출 결과 바인딩		
			 *  ******************************************************
			 */						
	
		
			psSiebelMessage	= new SiebelPropertySet();
			psCancelAPResp	= new SiebelPropertySet();

			psSiebelMessage.setType("SiebelMessage");

			psCancelAPResp.setType("cancelApprovalResponse");
			psCancelAPResp.setProperty("result", result);
			psSiebelMessage.addChild(psCancelAPResp);

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
			
			psCancelAP			= null;
			psCancelProc		= null;
			psSiebelMessage		= null;
			psCancelAPResp		= null;
			
			proxy = null;
			singerInfoCreate = null;
			apprDoc = null;
			
		}
	}

}
