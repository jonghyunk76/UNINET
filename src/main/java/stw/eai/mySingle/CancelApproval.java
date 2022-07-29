package stw.eai.mySingle;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import mySingle.service.APWithdrawServiceLocator;
import samsung.esb.approval.service.APWithdrawService;
import samsung.esb.approval.vo.CancelProcessWSVO;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class CancelApproval implements Client {

	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		APWithdrawServiceLocator	serviceLocator	= null;
		APWithdrawService			clientStub		= null;

		SiebelPropertySet	psCancelAP			= null;
		SiebelPropertySet	psCancelProc		= null;
		SiebelPropertySet	psSiebelMessage		= null;
		SiebelPropertySet	psCancelAPResp		= null;

		ESBAuthVO			esbAuthVO			= null;
		CancelProcessWSVO	cancelProcessWSVO	= null;

		String error	= "";
		String result	= "";

		outputs.setProperty("Error", "");
		try {

			// get Inputs
			psSiebelMessage	= Common.getChild(inputs			,"SiebelMessage");
			psCancelAP		= Common.getChild(psSiebelMessage	,"cancelApproval");
			psCancelProc	= Common.getChild(psCancelAP		,"cancelProcessWSVO");

			// ====== esbAuthVO ======
			esbAuthVO	= new ESBAuthVO(Common.CONN_ID_AP,Common.CONN_PW_AP);

			// ====== cancelProcessWSVO ======
			cancelProcessWSVO	= new CancelProcessWSVO();
			cancelProcessWSVO.setMisID		(psCancelProc.getProperty("MisID"));
			cancelProcessWSVO.setTimeZone	(psCancelProc.getProperty("TimeZone"));
			cancelProcessWSVO.setOpinion	(psCancelProc.getProperty("Opinion"));
			cancelProcessWSVO.setSystemID	(psCancelProc.getProperty("SystemID"));
			cancelProcessWSVO.setCancelDate	(psCancelProc.getProperty("CancelDate"));

			serviceLocator	= new APWithdrawServiceLocator();
			clientStub	= serviceLocator.getAPWithdrawService_InboundPort();
			result		= clientStub.cancelApproval(esbAuthVO, cancelProcessWSVO);

			System.out.println(error);
			System.out.println(result);

			psSiebelMessage	= new SiebelPropertySet();
			psCancelAPResp	= new SiebelPropertySet();

			psSiebelMessage.setType("SiebelMessage");

			psCancelAPResp.setType("cancelApprovalResponse");
			psCancelAPResp.setProperty("result", result);
			psSiebelMessage.addChild(psCancelAPResp);

			outputs.addChild(psSiebelMessage);
			outputs.setProperty("Error", error);
		} catch (ServiceException e) {
			error += "ServiceException \n" + e.getMessage();
			outputs.setProperty("Error", error);
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		} catch (ESBFaultVO e) {
			e.printStackTrace();
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
		}
		finally
		{
			serviceLocator		= null;
			clientStub			= null;

			psCancelAP			= null;
			psCancelProc		= null;
			psSiebelMessage		= null;
			psCancelAPResp		= null;

			esbAuthVO			= null;
			cancelProcessWSVO	= null;
		}
	}

}
