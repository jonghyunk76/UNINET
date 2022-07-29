/**
/* 프로젝트 : 미주 유상 결재모듈 개발
/* 제    목 : PayPal Authorization 연동 모듈
/* 작 성 자 : dkkim@coreplus.co.kr
/* 작 성 일 : 2016.09.12
/* 수정이력 : 2016.09.12 , dkkim@coreplus.co.kr , 최초작성
*/

package stw.eai.hwt.paypal.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import stw.eai.common.Common;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
//import com.paypal.api.payments.util.GenerateAccessToken;
//import com.paypal.core.rest.APIContext;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class HTWPayPalDoHold {
	
	private static Log log = LogFactory.getLog(HTWPayPalDoHold.class);
	
	SiebelPropertySet	psDoHold;
	SiebelPropertySet	psSiebelMessage;
	
	/**
	 * Authorization 정보를 등록한다.
	 * @param inputs
	 * @param outputs  
	 * @return void
	 * @throws SiebelBusinessServiceException
	 */
	protected void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {

		this.psSiebelMessage	= Common.getChild(inputs			, "SiebelMessage"); 
		this.psDoHold			= Common.getChild(psSiebelMessage	, "PaypalDoCapture");
		
		APIContext apiContext	= null;		
		Authorization			authorization;
		SiebelPropertySet		psResponse;

		try {

			// 1. Get Token
			//String accessToken = GenerateAccessToken.getAccessToken();
			HashMap<String,String> paypalConfigMap = HTWPayPalConfig.getPayPalConfig();			
			String mode 		= (String) paypalConfigMap.get("mode");
			String clientID 	= (String) paypalConfigMap.get("clientID");
			String clientSecret = (String) paypalConfigMap.get("clientSecret");
			
			// 2. get APIContext
			//apiContext = new APIContext(accessToken);
			apiContext = new APIContext( clientID, clientSecret, mode);

			// 3. Call Authorization
			authorization = getAuthorization(apiContext);

			// 4. Return SiebelMessage
			this.psSiebelMessage = new SiebelPropertySet();
			this.psSiebelMessage.setType("SiebelMessage");

			psResponse	= new SiebelPropertySet();
			psResponse.setType("PaypalCaptureResponse");
			
			// 출력 공통항목.
			psResponse.setProperty("id"					, "");
			psResponse.setProperty("hold_id"			, authorization.getId());
			psResponse.setProperty("capture_id"			, "");
			
			// SandBox에서는 데이타 값이 들어오나 Live에서는 데이타 값이 들어오지 않는다.
			if ( authorization.getCreateTime() != null )	{ psResponse.setProperty("create_time"		, authorization.getCreateTime() ); 	} else { psResponse.setProperty("create_time"		, "" ); }			
			if ( authorization.getUpdateTime() != null )	{ psResponse.setProperty("update_time"		, authorization.getUpdateTime()); 	} else { psResponse.setProperty("update_time"		, "" ); }			
			if ( authorization.getState() != null )			{ psResponse.setProperty("state"			, authorization.getState()); 		} else { psResponse.setProperty("state"				, "" ); }			
			if ( authorization.getParentPayment() != null )	{ psResponse.setProperty("parent_payment"	, authorization.getParentPayment());} else { psResponse.setProperty("parent_payment"	, "" ); }
			
			this.psSiebelMessage.addChild(psResponse);
			
			outputs.addChild(this.psSiebelMessage);
			
		} catch (PayPalRESTException e0) {
			
			log.debug("1111111111111");
			outputs.setProperty("Error", HTWPayPalUtil.makeUserErrorMsg(e0) );
			throw new SiebelBusinessServiceException(e0, "doHold:doPost:","doHold:doPost: "+HTWPayPalUtil.makeUserErrorMsg(e0));			
		} catch (Exception e1) {
			
			log.debug("222222222");
			e1.printStackTrace();
			outputs.setProperty("Error", HTWPayPalUtil.makeCommonErrorMsg(e1) );
			throw new SiebelBusinessServiceException(e1, "doHold:doPost:","doHold:doPost: "+HTWPayPalUtil.makeCommonErrorMsg(e1));
		} finally {
			this.psSiebelMessage	= null;
			this.psDoHold			= null;
			apiContext				= null;
			authorization			= null;
			psResponse				= null;
		}
	}
	
	/**
	 * Authorization 정보를 등록한다.
	 * @param apiContext
	 * @return Authorization
	 * @throws Exception
	 */
	private Authorization getAuthorization(APIContext apiContext) throws PayPalRESTException {
				
		SiebelPropertySet psPayment 				= Common.getChild(this.psDoHold, 	"Payment");
		SiebelPropertySet psPayer					= Common.getChild(psPayment, 		"payer");
		
		ArrayList<SiebelPropertySet> psFunding		= Common.getChilds(psPayer, 		"funding_instrument");
		SiebelPropertySet psCreditCard				= null;
		SiebelPropertySet psBillAddr				= null;
		
		ArrayList<SiebelPropertySet> psTransaction	= Common.getChilds(psPayment, 		"transaction");
		SiebelPropertySet psAmount					= null;
		SiebelPropertySet psDetail					= null;
		
		Payment					payment;
		Payer					payer;
		List<FundingInstrument>	fundingInstruments;
		List<Transaction>		transactions;
		Payment					responsePayment;

		try{			
			// ###Payment
			// A Payment Resource; create one using
			// the above types and intent as 'authorize'
			payment = new Payment();
			payment.setIntent(psPayment.getProperty("intent"));			

			// ###Payer
			// A resource representing a Payer that funds a payment
			// Use the List of `FundingInstrument` and the Payment Method
			// as 'credit_card'
			payer = new Payer();
			payer.setPaymentMethod(psPayer.getProperty("payment_method"));
			payment.setPayer(payer);

			// The Payment creation API requires a list of
			// FundingInstrument; add the created `FundingInstrument`
			// to a List
			fundingInstruments = new ArrayList<FundingInstrument>();
			payer.setFundingInstruments(fundingInstruments);

			int cnt = psFunding.size();
			for(int i = 0 ; i < cnt ; i++ ){
				// ###FundingInstrument
				// A resource representing a Payeer's funding instrument.
				// Use a Payer ID (A unique identifier of the payer generated
				// and provided by the facilitator. This is required when
				// creating or using a tokenized funding instrument)
				// and the `CreditCardDetails`
				FundingInstrument fundingInstrument = new FundingInstrument();
				fundingInstruments.add(fundingInstrument);

				// ###CreditCard
				// A resource representing a credit card that can be
				// used to fund a payment.
				psCreditCard = Common.getChild(psFunding.get(i), "credit_card");
				if(psCreditCard != null){
					CreditCard creditCard = new CreditCard();
					creditCard.setCvv2			(psCreditCard.getProperty("cvv2"));
					creditCard.setExpireMonth	(Integer.parseInt(psCreditCard.getProperty("expire_month")));
					creditCard.setExpireYear	(Integer.parseInt(psCreditCard.getProperty("expire_year")));
					creditCard.setFirstName		(psCreditCard.getProperty("first_name"));
					creditCard.setLastName		(psCreditCard.getProperty("last_name"));
					creditCard.setNumber		(psCreditCard.getProperty("number"));
					creditCard.setType			(psCreditCard.getProperty("type"));
					fundingInstrument.setCreditCard(creditCard);
					
					// ###Address
					// Base Address object used as shipping or billing
					// address in a payment. [Optional]
					psBillAddr	= Common.getChild(psCreditCard, "billing_address");
					if(psBillAddr != null){
						Address billingAddress = new Address();
						billingAddress.setCity			(Common.NVL(psBillAddr.getProperty("city")));
						billingAddress.setCountryCode	(Common.NVL(psBillAddr.getProperty("country_code")));
						billingAddress.setLine1			(Common.NVL(psBillAddr.getProperty("line1")));
						billingAddress.setLine2			(Common.NVL(psBillAddr.getProperty("line2")));
						billingAddress.setPhone			(Common.NVL(psBillAddr.getProperty("phone")));
						billingAddress.setPostalCode	(Common.NVL(psBillAddr.getProperty("postal_code")));
						billingAddress.setState			(Common.NVL(psBillAddr.getProperty("state")));
						creditCard.setBillingAddress(billingAddress);
					}
				}
			}

			// The Payment creation API requires a list of
			// Transaction; add the created `Transaction`
			// to a List
			transactions = new ArrayList<Transaction>();
			payment.setTransactions(transactions);

			cnt	= psTransaction.size();
			for(int i = 0 ; i < cnt ; i++ )
			{
				// ###Transaction
				// A transaction defines the contract of a
				// payment - what is the payment for and who
				// is fulfilling it. Transaction is created with
				// a `Payee` and `Amount` types
				Transaction transaction = new Transaction();
				transaction.setDescription(psTransaction.get(i).getProperty("description"));

				// ###Amount
				// Let's you specify a payment amount.
				psAmount	= Common.getChild(psTransaction.get(i), "amount");
				if(psAmount != null){
					Amount amount = new Amount();
					amount.setCurrency	(psAmount.getProperty("currency"));
					amount.setTotal		(HTWPayPalUtil.convCurrencyAmount(psAmount.getProperty("total")));
					transaction.setAmount(amount);

					// ###Details
					// Let's you specify details of a payment amount.
					psDetail	= Common.getChild(psAmount, "details");
					if(psDetail != null){
						Details details = new Details();
						details.setShipping	(HTWPayPalUtil.convCurrencyAmount(psDetail.getProperty("shipping")));
						details.setSubtotal	(HTWPayPalUtil.convCurrencyAmount(psDetail.getProperty("subtotal")));
						details.setTax		(HTWPayPalUtil.convCurrencyAmount(psDetail.getProperty("tax")));
						amount.setDetails(details);
					}
				}
				transactions.add(transaction);
			}

			log.debug("=================Request Hold==================");
			log.debug(payment);
			log.debug("===================================================");

			responsePayment = payment.create(apiContext);
			
			Authorization objAuthorization = responsePayment.getTransactions().get(0).getRelatedResources().get(0).getAuthorization();
			
			String holdId = objAuthorization.getId();
			
			log.debug("=================Response Hold==================");
			log.debug("= Hold ID : " + holdId );
			log.debug("===================================================");
			log.debug(responsePayment);
			log.debug("===================================================");
			
			return objAuthorization;
			
		} catch (PayPalRESTException e0) {
			e0.printStackTrace();
			throw e0;
		} catch (Exception e1) {	
			e1.printStackTrace();
			throw e1;			
		} finally {
			
			psPayment			= null;
			psPayer				= null;
			psFunding			= null;
			psCreditCard		= null;
			psBillAddr			= null;
			psTransaction		= null;
			psAmount			= null;
			psDetail			= null;			
			payment				= null;
			payer				= null;
			fundingInstruments	= null;
			transactions		= null;
			responsePayment		= null;
			
		}
	}
}