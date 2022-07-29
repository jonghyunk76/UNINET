package stw.eai.mySingle;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import mySingle.service.MLCancelDeliveryServiceLocator;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;
import samsung.esb.mail.service.MLCancelDeliveryService;
import samsung.esb.mail.vo.ResourceCSVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class CancelMail implements Client {

	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		MLCancelDeliveryServiceLocator	serviceLocator	= null;
		MLCancelDeliveryService			clientStub		= null;
		
		ESBAuthVO			esbAuthVO		= null;
		String				mtrKey			= null;
		String[]			addressForCancel= null;
		ResourceCSVO		resourceCSVO	= null;
		
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
			
			// ====== esbAuthVO ======
			esbAuthVO	= new ESBAuthVO(Common.CONN_ID_ML,Common.CONN_PW_ML);
			
			// ====== mtrKey ======
			mtrKey		= psCancelMail.getProperty("mtrKey");
			
			// ====== addressForCancel ======
			int idx	= psAddrForCancel.getChildCount();
			addressForCancel	= new String[idx];
			for(int i = 0 ; i < idx ; i ++) {
				addressForCancel[i] = psAddrForCancel.getChild(i).getValue();
			}
			
			// ====== resourceCSVO ======
			resourceCSVO	= new ResourceCSVO();
			resourceCSVO.setEmail	(resource.getProperty(	"email"));
			resourceCSVO.setEncoding(resource.getProperty(	"encoding"));
			resourceCSVO.setLocale	(resource.getProperty(	"locale"));
			resourceCSVO.setPassword(resource.getProperty(	"password"));
			
			serviceLocator	= new MLCancelDeliveryServiceLocator();
			clientStub		= serviceLocator.getMLCancelDeliveryService_InboundPort();
			String result	= clientStub.cancelMISMailByRecipient(esbAuthVO, mtrKey, addressForCancel, resourceCSVO);
			System.out.println(result);
			
			// Response Message
			psSiebelMessage	= new SiebelPropertySet();
			psCancelMail	= new SiebelPropertySet();
			
			psSiebelMessage.setType("SiebelMessage");
			
			psCancelMail.setType("cancelMISMailByRecipientResponse");
			psCancelMail.setProperty("result", result);
			psSiebelMessage.addChild(psCancelMail);
			
			outputs.addChild(psSiebelMessage);
			outputs.setProperty("Error", "");
//			System.out.println(outputs.toString());
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
			clientStub		= null;
			
			esbAuthVO		= null;
			addressForCancel= null;
			resourceCSVO	= null;
			
			psSiebelMessage	= null;
			psCancelMail	= null;
			psAddrForCancel	= null;
			resource		= null;
		}
	}
}
