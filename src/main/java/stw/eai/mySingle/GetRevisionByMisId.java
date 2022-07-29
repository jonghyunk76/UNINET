package stw.eai.mySingle;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import mySingle.service.APStatusInquiryServiceLocator;
import samsung.esb.approval.service.APStatusInquiryService;
import samsung.esb.approval.vo.MisKeyWSVO;
import samsung.esb.approval.vo.ProcessRevisionWSVO;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class GetRevisionByMisId implements Client {
	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		APStatusInquiryServiceLocator	serviceLocator	= null;
		APStatusInquiryService			clientStub	= null;
		
		ESBAuthVO				esbAuthVO		= null;
		MisKeyWSVO[]			misKeyWSVOs		= null;
		MisKeyWSVO				misKeyWSVO		= null;
		ProcessRevisionWSVO[]	procRevWSVOs	= null;
		
		SiebelPropertySet	psSiebelMessage	= null;
		SiebelPropertySet	psGetRevision	= null;
		SiebelPropertySet	psMisKeyWSVOs	= null;
		SiebelPropertySet	psGetRevResp	= null;
		SiebelPropertySet	psProcRevs		= null;
		SiebelPropertySet	psProcRev		= null;
		
		String			error			= "";
		try{
			System.out.println(inputs.toString());
			
			psSiebelMessage	= Common.getChild(inputs			,"SiebelMessage");
			psGetRevision	= Common.getChild(psSiebelMessage	,"getRevisionByMisId");
			psMisKeyWSVOs	= Common.getChild(psGetRevision		,"misKeyWSVOs");
			
			// ====== esbAuthVO ======
			esbAuthVO	= new ESBAuthVO(Common.CONN_ID_AP,Common.CONN_PW_AP);
			
			// ====== misKeyWSVOs ======
			int cnt		= psMisKeyWSVOs.getChildCount();
			misKeyWSVOs	= new MisKeyWSVO[cnt];
			for(int i = 0 ; i < cnt ; i++ ) {
				misKeyWSVO	= new MisKeyWSVO();
				misKeyWSVO.setMisID		(psMisKeyWSVOs.getChild(i).getProperty("MisID"));
				misKeyWSVO.setSystemID	(psMisKeyWSVOs.getChild(i).getProperty("MisID"));
				misKeyWSVOs[i]	= misKeyWSVO;
			}
			
			serviceLocator	= new APStatusInquiryServiceLocator();
			clientStub		= serviceLocator.getAPStatusInquiryService_InboundPort();
			procRevWSVOs	= clientStub.getRevisionByMisId(esbAuthVO, misKeyWSVOs);
			
			// Response
			psSiebelMessage	= new SiebelPropertySet();
			psGetRevResp	= new SiebelPropertySet();
			psProcRevs		= new SiebelPropertySet();
			psProcRev		= new SiebelPropertySet();
			
			psSiebelMessage	.setType("SiebelMessage");
			
			psGetRevResp	.setType("getRevisionByMisIdResponse");
			psSiebelMessage	.addChild(psGetRevResp);
			
			psProcRevs		.setType("ProcessRevisionWSVOs");
			psGetRevResp	.addChild(psProcRevs);
			
			cnt		= procRevWSVOs.length;
			for(int i = 0 ; i < cnt ; i ++){
				psProcRev		= new SiebelPropertySet();
				psProcRev.setType("ProcessRevisionWSVO");
				psProcRev.setProperty("Revision"	, procRevWSVOs[i].getRevision());
				psProcRev.setProperty("Status"		, procRevWSVOs[i].getStatus());
				psProcRev.setProperty("MisID"		, procRevWSVOs[i].getMisID());
				psProcRev.setProperty("ProcinstID"	, procRevWSVOs[i].getProcinstID());
				psProcRevs.addChild(psProcRev);
			}
			
			outputs.addChild(psSiebelMessage);
			outputs.setProperty("Error", "");
			
			System.out.println(outputs.toString());
		} catch (ServiceException e) {
			error += "ServiceException \n" + e.getMessage();
			outputs.setProperty("Error", error);
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		} catch (ESBFaultVO e) {
			e.printStackTrace();
			error += "ESBFaultVO : [" + e.getFaultCode1() + "] "+ e.getFaultMessage();
			outputs.setProperty("Error", error);
			System.out.println(error);
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
			serviceLocator	= null;
			clientStub	= null;
			
			esbAuthVO		= null;
			misKeyWSVOs		= null;
			misKeyWSVO		= null;
			procRevWSVOs	= null;
			
			psSiebelMessage	= null;
			psGetRevision	= null;
			psMisKeyWSVOs	= null;
			psGetRevResp	= null;
			psProcRevs		= null;
			psProcRev		= null;
		}
	}
}
