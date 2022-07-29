package stw.eai.mySingle;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import mySingle.service.MLDeliveryStatusInquiryServiceLocator;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;
import samsung.esb.mail.service.MLDeliveryStatusInquiryService;
import samsung.esb.mail.vo.StatusCountESBVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class GetMailStatusCounts implements Client {

	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		MLDeliveryStatusInquiryServiceLocator	serviceLocator	= null;
		MLDeliveryStatusInquiryService			clientStub		= null;

		ESBAuthVO			esbAuthVO		= null;
		StatusCountESBVO[]	statusCounts	= null;

		SiebelPropertySet	psMtrKeys		= null;
		SiebelPropertySet	psSiebelMessage	= null;
		SiebelPropertySet	psGetMailStatus	= null;
		SiebelPropertySet	psStatusCnt		= null;
		SiebelPropertySet	psStatusCntVO	= null;

		String[] 			mtrKeys			= null;
		String				error			= "";

		try {
			// get Inputs
			psSiebelMessage	= Common.getChild(inputs			,"SiebelMessage");
			psGetMailStatus	= Common.getChild(psSiebelMessage	,"getMailStatusCounts");
			psMtrKeys		= Common.getChild(psGetMailStatus	,"mtrKeys");

			// ====== esbAuthVO ======
			esbAuthVO	= new ESBAuthVO(Common.CONN_ID_ML,Common.CONN_PW_ML);

			// ====== mtrKeys ======
			int count = psMtrKeys.getChildCount();
			mtrKeys	= new String[count];
			for(int i = 0 ; i < count ; i++)
			{
				mtrKeys[i] = psMtrKeys.getChild(i).getValue();
			}

			serviceLocator	= new MLDeliveryStatusInquiryServiceLocator();

			clientStub	= serviceLocator.getMLDeliveryStatusInquiryService_InboundPort();
			statusCounts = clientStub.getMailStatusCounts(esbAuthVO, mtrKeys);


			System.out.println(error);

			psSiebelMessage	= new SiebelPropertySet();
			psGetMailStatus	= new SiebelPropertySet();
			psStatusCnt		= new SiebelPropertySet();


			psSiebelMessage	.setType("SiebelMessage");

			psGetMailStatus	.setType("getMailStatusCountsResponse");
			psSiebelMessage.addChild(psGetMailStatus);

			psStatusCnt		.setType("statusCountVO");
			psGetMailStatus.addChild(psStatusCnt);

			count	= statusCounts.length;
			for(int i = 0 ; i < count ; i++)
			{
				StatusCountESBVO	statusCount = statusCounts[i];

				psStatusCntVO		= new SiebelPropertySet();
				psStatusCntVO		.setType("statusCountESBVOs");
				psStatusCntVO		.setProperty("mtrKey"		, 		statusCount.getMtrKey());
				psStatusCntVO		.setProperty("unSeenCount"	, ""+	statusCount.getUnSeenCount());
				psStatusCntVO		.setProperty("isScheduled"	, 		statusCount.getIsScheduled()?"Y":"N");
				psStatusCntVO		.setProperty("totalCount"	, ""+	statusCount.getTotalCount());
				psStatusCntVO		.setProperty("deliveryCount", ""+	statusCount.getDeliveryCount());
				psStatusCntVO		.setProperty("etcCount"		, ""+	statusCount.getEtcCount());
				psStatusCntVO		.setProperty("seenCount"	, ""+	statusCount.getSeenCount());
				psStatusCntVO		.setProperty("cancelCount"	, ""+	statusCount.getCancelCount());
				psStatusCnt			.addChild(psStatusCntVO);

				statusCount	= null;
			}

			System.out.println(psSiebelMessage.toString());

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
		}
		finally
		{
			serviceLocator	= null;
			clientStub		= null;

			esbAuthVO		= null;
			statusCounts	= null;

			psMtrKeys		= null;
			psSiebelMessage	= null;
			psGetMailStatus	= null;
			psStatusCnt		= null;
			psStatusCntVO	= null;
		}
	}

}
