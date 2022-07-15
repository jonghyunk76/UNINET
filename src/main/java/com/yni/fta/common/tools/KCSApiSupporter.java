package com.yni.fta.common.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import kr.yni.frame.Constants;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;

public class KCSApiSupporter {
	
	private static Log log = LogFactory.getLog(KCSApiSupporter.class);
	
	/**
	 * 원산지 증명서 신청서 XML파일 생성
	 *  
	 * @param docMap 증명서 신청정보 : 신청서, 원산지 증명서, 인보이스 정보
	 * @param salesList 판매물품 리스트 : 물품판매정보 및 소명서 정보
	 * @param itemList 원재료 리스트 : 자재 명세서 정보
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public Map createCustomsRequestXML(Map docMap, List salesList, List itemList) throws Exception {
		String sendType = StringHelper.null2string(docMap.get("REQ_SEND_TYPE_CODE"), "09"); // 문서구분(09:원본, 18:재발급, 35:정정발급)
		
		log.debug("createCustomsRequestXML Start.....................");
		
		Map xmlMap = new HashMap();
		
		Date dt = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	    
	    Date dt1 = new Date();
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	    String ftaCd = StringHelper.null2void(docMap.get("FTA_CD")); //fta 협정코드 
	    String kcsNamespace = null;
	    String wcoNamespace = null;
	    String schemaLocationNamespace = null;
	    
	    //Name 스페이스 정의
	    if(ftaCd.equals("PKRAP")) {
	    	kcsNamespace = "urn:kr:gov:kcs:data:standard:KCS_DeclarationOfFTA_504SchemaModule:1:0";
	    	wcoNamespace = "urn:kr:gov:kcs:data:standard:KCS_DeclarationOfFTA_504SchemaModule:1:0";
	    	schemaLocationNamespace = "urn:kr:gov:kcs:data:standard:KCS_DeclarationOfFTA_504SchemaModule:1:0 ../../schema4G/kcs/data/standard/KCS_DeclarationOfFTA_504SchemaModule_1.0_standard.xsd";
	    } else {
	    	kcsNamespace = "urn:kr:gov:kcs:data:standard:KCS_DeclarationOfFTA_036SchemaModule:1:0";
	    	wcoNamespace = "urn:kr:gov:kcs:data:standard:KCS_DeclarationOfFTA_036SchemaModule:1:0";
	    	schemaLocationNamespace = "urn:kr:gov:kcs:data:standard:KCS_DeclarationOfFTA_036SchemaModule:1:0 ../../schema4G/kcs/data/standard/KCS_DeclarationOfFTA_036SchemaModule_1.0_standard.xsd";
	    }
	    
	    Namespace kcs = Namespace.getNamespace("kcs", kcsNamespace);
  		Namespace wco = Namespace.getNamespace("wco", wcoNamespace);
  		Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

  		// root element 만들기
		Element Declaration = new Element("Declaration", wco);
		
		// Name 스페이스 설정
		Declaration.setAttribute("schemaLocation", schemaLocationNamespace, xsi);
		Declaration.addNamespaceDeclaration(kcs);
		Declaration.addNamespaceDeclaration(xsi);
		
		//각 elements에 입력할 항목의 값을 구한다.
		String docTypeCd = ftaCd.equals("PKRAP") ? "GOVCBR504" : "FTAGOVCBR036"; // 문서형태구분(고정값) APTA : "GOVCBR504", APTA외 : "GOVCBR036"
		String docTypeName = ftaCd.equals("PKRAP") ? "일반특혜원산지증명서" : "FTA원산지증명서"; //APTA : "FTA원산지증명서", APTA외 : "일반특혜원산지증명서"
		String fob_declare_amount = StringHelper.null2void(docMap.get("FOB_DECLARE_AMOUNT"));
		String packing_qty_coment = StringHelper.null2void(docMap.get("PACKING_QTY_COMENT"));
		String rel_issue_date = StringHelper.null2void(docMap.get("REL_ISSUE_DATE"));
		String rel_origin_nation_cd = StringHelper.null2void(docMap.get("REL_ORIGIN_NATION_CD"));
		String rel_issue_type = StringHelper.null2void(docMap.get("REL_ISSUE_TYPE"));
		String vehicle_type = StringHelper.null2void(docMap.get("VEHICLE_TYPE"));
		String vehicle_name = StringHelper.null2void(docMap.get("VEHICLE_NAME"));
		String co_certified_exporter_no = StringHelper.null2void(docMap.get("CO_CERTIFIED_EXPORTER_NO"));
		String signature_name_eng = StringHelper.null2void(docMap.get("SIGNATURE_NAME_ENG"));
		String printTypeCode = "N";
		String final_destination_name = StringHelper.null2void(docMap.get("FINAL_DESTINATION_NAME"));
		String producer_address = StringHelper.null2void(docMap.get("PRODUCER_ADDRESS"));
		String exporter_business_no = StringHelper.null2void(docMap.get("EXPORTER_BUSINESS_NO"));
		String exporter_president_name = StringHelper.null2void(docMap.get("EXPORTER_PRESIDENT_NAME"));
		String exporter_phone_no = StringHelper.null2void(docMap.get("EXPORTER_PHONE_NO"));
		String exporter_fax_no = StringHelper.null2void(docMap.get("EXPORTER_FAX_NO"));
		String exporter_president_email = StringHelper.null2void(docMap.get("EXPORTER_EMAIL"));
		String invoice_company_name = StringHelper.null2void(docMap.get("INVOICE_COMPANY_NAME"));
		String oed_invoice_no = StringHelper.null2void(docMap.get("OED_INVOICE_NO"));
		String oed_invoice_issue_date = StringHelper.null2void(docMap.get("OED_INVOICE_ISSUE_DATE"));
		String oed_export_yn = StringHelper.null2string(docMap.get("OED_EXPORT_YN"), "N");
		String oed_company_name = StringHelper.null2void(docMap.get("OED_COMPANY_NAME"));
		String export_other_yn = StringHelper.null2void(docMap.get("EXPORT_OTHER_YN")); // 제3국 송장출력여부(값이 없는 경우 출격하지 않게 수정함-2022.02.18)
		String oed_nation_cd = StringHelper.null2void(docMap.get("OED_NATION_CD"));
		String oed_address = StringHelper.null2void(docMap.get("OED_ADDRESS"));
		String itiner_nation_cd = StringHelper.null2void(docMap.get("ITINER_NATION_CD"));
		String itiner_nation_name = StringHelper.null2void(docMap.get("ITINER_NATION_NAME"));
		String importer_business_no = StringHelper.null2void(docMap.get("IMPORTER_BUSINESS_NO"));
		String importer_nation_cd = StringHelper.null2void(docMap.get("IMPORTER_NATION_CD"));
		String importer_address = StringHelper.null2void(docMap.get("IMPORTER_ADDRESS"));
		String importer_president_name = StringHelper.null2void(docMap.get("IMPORTER_PRESIDENT_NAME"));
		String import_phone_no = StringHelper.null2void(docMap.get("IMPORTER_PHONE_NO"));
		String importer_fax_no = StringHelper.null2void(docMap.get("IMPORTER_FAX_NO"));
		String importer_email = StringHelper.null2void(docMap.get("IMPORTER_EMAIL"));
		String producer_name = StringHelper.null2void(docMap.get("PRODUCER_NAME"));
		String producer_business_no = StringHelper.null2void(docMap.get("PRODUCER_BUSINESS_NO"));
		String producer_nation_cd = StringHelper.null2void(docMap.get("PRODUCER_NATION_CD"));
		String producer_president_name = StringHelper.null2void(docMap.get("PRODUCER_PRESIDENT_NAME"));
		String producer_phone_no = StringHelper.null2void(docMap.get("PRODUCER_PHONE_NO"));
		String producer_fax_no = StringHelper.null2void(docMap.get("PRODUCER_FAX_NO"));
		String producer_email = StringHelper.null2void(docMap.get("PRODUCER_EMAIL"));
		String loading_mark = StringHelper.null2void(docMap.get("LOADING_MARK"));
		String packing_qty = StringHelper.null2void(docMap.get("PACKING_QTY"));
		String discharge_port = StringHelper.null2void(docMap.get("DISCHARGE_PORT"));
		String final_des_port_name = StringHelper.null2void(docMap.get("FINAL_DEST_PORT_NAME"));
		String req_president_name = StringHelper.null2void(docMap.get("REQ_PRESIDENT_NAME"));
		String export_declare_date = StringHelper.null2void(docMap.get("EXPORT_DECLARE_DATE"));
		String export_declare_seq = StringHelper.null2void(docMap.get("EXPORT_DECLARE_SEQ"));
		String kcs_user_id = StringHelper.null2void(docMap.get("KCS_USER_ID"));
		String bl_no = StringHelper.null2void(docMap.get("BL_NO"));
		String relExpNo = StringHelper.null2void(docMap.get("REL_CERTIFIED_EXPORTER_NO"));
		String relNationName = StringHelper.null2void(docMap.get("REL_RCEP_ORIGIN_NATION_NAME"));
		String relDocNo = StringHelper.null2void(docMap.get("REL_CO_DOC_NO"));
		
		// RCEP 추가(2022-01-29)
		// 원산지간이대상여부 = salesList
		// RCEP원산지국가명 = docMap
		// 송장번호 = docMap
		// 송장발행일 = docMap
		// 최초수출국RCEP원산지국가명(단일) = docMap
		// 최초수출국인증수출자인증번호(단일) = docMap

		
		// 신청서 마스터 정보
		Declaration.addContent(new Element("DeclarationOfficeID", wco).setText(StringHelper.null2void(docMap.get("CUSTOMS_CD")))); // 신청기관코드
		Declaration.addContent(new Element("FunctionCode", wco).setText(sendType)); // 문서전송구분(09:원본, 18:재발급, 35:정정발급)
		if(!sendType.equals("09")) Declaration.addContent(new Element("FunctionalReferenceID", wco).setText(StringHelper.null2void(docMap.get("OLD_CO_DOC_REP")))); // 이전접수번호
		Declaration.addContent(new Element("ID", wco).setText(StringHelper.null2void(docMap.get("CO_SUBMIT_NO")))); // 제출번호
		if(!fob_declare_amount.isEmpty()) Declaration.addContent(new Element("InvoiceAmount", wco).setAttribute("currencyID", StringHelper.null2void(docMap.get("CURRENCY"))).setText(fob_declare_amount)); // 통화/금액
		Declaration.addContent(new Element("IssueDateTime", wco).setText(StringHelper.null2void(docMap.get("REQ_DATE")))); // 신청일자
		Declaration.addContent(new Element("IssuingPartyName", wco).setText(StringHelper.null2void(docMap.get("ISSUE_ORGAN_TYPE")))); // 발급기관구분
		Declaration.addContent(new Element("StatusCode", wco).setText(StringHelper.null2void(docMap.get("ORGAN_FTA_NAME")))); // 관세청 협정코드
		Declaration.addContent(new Element("TotalGrossMassMeasure", wco).setAttribute("kcsUnitCode", StringHelper.null2void(docMap.get("WEIGHT_UNIT"))).setText(StringHelper.null2void(docMap.get("TOTAL_WEIGHT")))); // 총중량/단위
		Declaration.addContent(new Element("TotalPackageQuantity", wco).setAttribute("kcsUnitCode", StringHelper.null2void(docMap.get("QTY_UNIT"))).setText(StringHelper.null2void(docMap.get("TOTAL_QTY")))); // 총수량 / 단위
		Declaration.addContent(new Element("TypeCode", wco).setText(StringHelper.null2string(docMap.get("DOC_TYPE_CD"), docTypeCd))); // 문서형태구분(고정값) APTA : "GOVCBR504", APTA외 : "GOVCBR036"
		Declaration.addContent(new Element("VersionID", wco).setText(StringHelper.null2string(docMap.get("REQ_SEQ"), "0"))); // 변경차수
		Declaration.addContent(new Element("TransactionNatureCode", wco).setText(StringHelper.null2void(docMap.get("REQ_REASON_CODE")))); // 신청사유 코드
		Declaration.addContent(new Element("Name", wco).setText(StringHelper.null2string(docMap.get("DOC_TYPE_NAME"), docTypeCd))); // 문서명(고정값) APTA : "FTA원산지증명서", APTA외 : "일반특혜원산지증명서"
		Declaration.addContent(new Element("SubTypeCode", kcs).setText(StringHelper.null2void(docMap.get("REQ_CODE")))); // 신청유형(선적후발급여부)
		if(!packing_qty_coment.isEmpty()) Declaration.addContent(new Element("Note", kcs).setText(packing_qty_coment)); // 총포장갯수 및 총수량 영문명
		if(!sendType.equals("09")) Declaration.addContent(new Element("Reason", kcs).setText(StringHelper.null2void(docMap.get("RE_REQ_REASON")))); // 재발급/정정발급 사유란

		/////////// 연결원산지 원산지 증명서 정보 ///////////
		if(true) {
			Element addo = new Element("AdditionalDocument", wco);
			
			// 연결 원산지 정보(2022-01-29) - 신고정보에서 찾도록 변경할 것
			if(ftaCd.equals("PKRRC")) { // rcep인 경우에만 적용
				addo.addContent(new Element("ID", wco).setText(relDocNo)); // 연결원산지증명서번호
				addo.addContent(new Element("IssueDateTime", wco).setText(rel_issue_date)); // 연결원산지증명서발행일자
				addo.addContent(new Element("IssueLocation", wco).setText(rel_origin_nation_cd)); // 연결원산지 국가코드
				if(!relExpNo.isEmpty()) addo.addContent(new Element("IssuingPartyID", wco).setText(relExpNo)); // 최초수출국인증수출자인증번호(Declaration/AdditionalDocument/IssuingPartyID)(2022-01-29)
			}
			
			addo.addContent(new Element("CriteriaConformanceCode", kcs).setText(StringHelper.null2string(docMap.get("REL_EXCEPT_CD"), "98"))); // 원산지증명서특례(01 제3국 송장, 02 전시, 03 연결 원산지 증명, 04 개성공단 공업지구, 98 해당없음)
			if(!rel_issue_type.isEmpty()) addo.addContent(new Element("SubmissionIndicatorCode", kcs).setText(rel_issue_type)); // 인터텟
			
			// 연결 원산지 정보(2022-01-29) - 신고정보에서 찾도록 변경할 것
			if(ftaCd.equals("PKRRC")) { // rcep인 경우에만 적용
				addo.addContent(new Element("ReceiptOriginCountryName", kcs).setText(relNationName)); // 최초수출국RCEP원산지국가명(Declaration/AdditionalDocument/ReceiptOriginCountryName)(2022-01-29)
			}
			Declaration.addContent(addo);
		} // end /Declaration/AdditionalDocument
		
		/////////// 추가정보 ///////////
		if(false) {
			Element adin = new Element("AdditionalInformation", wco);
			
			if(ftaCd.contentEquals("PKRPE")) adin.addContent(new Element("Content", wco).setText("")); // 한-페루 비고(한-페루 협정인 경우에만 적용)
			adin.addContent(new Element("StatementTypeCode", wco).setText("")); // 날짜출력형식
			adin.addContent(new Element("StatementDescription", wco).setText("")); // 전시회정보
			adin.addContent(new Element("DirectCarriageCriterionCode", kcs).setText("")); // 직접운송여부
			adin.addContent(new Element("Notice", kcs).setText("")); // 메모
			adin.addContent(new Element("OrderCode", kcs).setText("")); // 수하인지시여부
			adin.addContent(new Element("OrderDescription", kcs).setText("")); // 수하인지시내용
			if(ftaCd.equals("PKRAP")) adin.addContent(new Element("LimitDateTime", wco).setText("")); // 소급발급요청일자. APTA일 경우에만 적용 
			
			Declaration.addContent(adin);
		} // end /Declaration/AdditionalInformation
		
		if(true) { // 운송수단
			Element btpm = new Element("BorderTransportMeans", wco);
			
			if(!vehicle_type.isEmpty()) btpm.addContent(new Element("ID", wco).setText(vehicle_type)); // 운송수단
			if(!vehicle_name.isEmpty()) btpm.addContent(new Element("Name", wco).setText(vehicle_name)); // 선박/항공기명
			
			Declaration.addContent(btpm);
		} // end /Declaration/BorderTransportMeans
		
		if(true) { // 수출자 신고인증
			Element cert = new Element("Certifier", wco);
			
			if(!ftaCd.equals("PKRAP") && !co_certified_exporter_no.isEmpty()) cert.addContent(new Element("ID", wco).setText(co_certified_exporter_no)); // 인증수출자번호, APTA 신청서 양식에는 없음.
			if(!signature_name_eng.isEmpty()) cert.addContent(new Element("Name", wco).setText(signature_name_eng)); // 서명자명
			if(!printTypeCode.isEmpty()) cert.addContent(new Element("PrintTypeCode", kcs).setText(printTypeCode)); // 이름출력여부
			Element certAddo = new Element("AdditionalDocument", wco);
			certAddo.addContent(new Element("ID", wco).setText(StringHelper.null2void(docMap.get("KCS_SIGNATURE_ID")))); // 서명등록번호(UNIPASS)
			Element certAd = new Element("Address", wco);
			if(!final_destination_name.isEmpty()) certAd.addContent(new Element("CountryCode", wco).setText(final_destination_name)); // 도착국가
			if(!producer_address.isEmpty()) certAd.addContent(new Element("Line", wco).setText(producer_address)); // 생산자 소재지
			
			cert.addContent(certAddo);
			cert.addContent(certAd);
			
			Declaration.addContent(cert);
		} // end /Declaration/Certifier
		
        ///////////////////////////////////////// 판매풀품 신고 및 원산지 정보 작성 ////////////////////////////////////////////		
		if(true) {
			if(salesList != null && salesList.size() > 0) {
				for(int i = 0; i < salesList.size(); i++) {
					Element cons = new Element("Consignment", wco); // XML Path : Consignment
					
					Map salesMap = (Map) salesList.get(i); 
					String numSeq = (i < 10) ? "0"+(i+1) : Integer.toString(i+1);
					String prodCode = StringHelper.null2void(salesMap.get("PRODUCT_ITEM_CD"));
					String custProdCode = StringHelper.null2void(salesMap.get("CUSTOMER_ITEM_CD"));
					
					// 각 elements에 입력할 항목의 값을 구한다.
					String m_etc_cd = StringHelper.null2void(salesMap.get("M_ETC_CD"));
					String m_ctc_rule = StringHelper.null2void(salesMap.get("M_CTC_RULE"));
					String m_rvc_rule = StringHelper.null2void(salesMap.get("M_RVC_RULE"));
					String m_rvc_rate = StringHelper.null2void(salesMap.get("M_RVC_RATE"));
					String product_line_cd_up = StringHelper.null2void(salesMap.get("PRODUCT_LINE_CD_UP"));
					String net_weight = StringHelper.null2void(salesMap.get("NET_WEIGHT"));
					String origin_nation_cd = StringHelper.null2void(salesMap.get("ORIGIN_NATION_CD"));
					String origin_rate = StringHelper.null2void(salesMap.get("ORIGIN_RATE"));
					String hs_code_desc = StringHelper.null2void(salesMap.get("HS_CODE_DESC"));
					String s_packing_qty = StringHelper.null2void(salesMap.get("PACKING_QTY"));
					String packing_unit = StringHelper.null2void(salesMap.get("PACKING_UNIT"));
					String export_req_qty = StringHelper.null2void(salesMap.get("EXPORT_REQ_QTY"));
					String export_item_name = StringHelper.null2void(salesMap.get("EXPORT_ITEM_NAME"));
					String declare_amount = StringHelper.null2void(salesMap.get("DECLARE_AMOUNT"));
					String declare_unit_price = StringHelper.null2void(salesMap.get("DECLARE_UNIT_PRICE"));
					String origin_simple_report = StringHelper.null2void(salesMap.get("ORG_SIMPLE_REPORT_YN"));
					String vendor_name = null;
					String presdent_name = null;
					String business_no = null;
					String phone_no = null;
					String fax_no = null;
					String president_email = null;
					
					if(true) {
						
						
						Element consItem = new Element("ConsignmentItem", wco); // XML Path : Consignment/ConsignmentItem
						consItem.addContent(new Element("SequenceNumeric", wco).setText(Integer.toString(i+1))); // 품목순번
						
		       ///////////////////////////////////////// Packing 목록(PPL) ////////////////////////////////////////////
						if(!ftaCd.equals("PKRAP")) {
							Element consItemAdin = new Element("AdditionalInformation", wco); // XML Path : Consignment/ConsignmentItem/AdditionalInformation
						
							if(!m_etc_cd.isEmpty()) consItemAdin.addContent(new Element("StatementCode", wco).setText(m_etc_cd)); // // 기타(Y/N) - 008(OP, 인도협정,개성공업지구내 생산), 009(Other), 010(Rule6, 개성공업지구내 생산), 014(WO, 완전생산기준-표준), 015(WOR, 완전생산기준-기재용), 016(WO-AK, 아세안, 역내완전생산기준), 024(SP)
							if(!m_ctc_rule.isEmpty()) consItemAdin.addContent(new Element("ChangeInTariffHeadingCode", kcs).setText(m_ctc_rule)); // 세번변경기준001(CTC) - 018(CC) ,002(CTH) ,019(CTSH)
							if(!m_rvc_rule.isEmpty()) consItemAdin.addContent(new Element("ValueAddedCriterionCode", kcs).setText(m_rvc_rule)); // 부가가치 기준 - 011(RVC), 013(VAR), 020(BD), 021(BU) ,022(MC), 023(NC)
							if(!m_rvc_rate.isEmpty()) consItemAdin.addContent(new Element("ValueAddedPercentageNumeric", kcs).setText(m_rvc_rate)); // 부가가치기준비율
							
							consItem.addContent(consItemAdin);
						} // end /Declaration/Consignment/ConsignmentItem/AdditionalInformation
						
				/////////////////////////////////////// 소명서 정보 ////////////////////////////////////////////
						if(true) { // 원산지인정요건 및 가격총액
							Element consItemCmm = new Element("Commodity", wco); // XML Path : Consignment/ConsignmentItem/Commodity
							
							if(true) { // 원산지 인정요건 상세정보
								Element consItemCmmAdin = new Element("AdditionalInformation", wco); // XML Path : Consignment/ConsignmentItem/Commodity/AdditionalInformation
								
								//부가가치 비율 필수값이며 0이 되면 사전오류 검증에서 오류 발생으로 0이면 1로 변경.
								String rvcRate = Integer.toString(StringHelper.null2zero(salesMap.get("RVC_RATE")));
								rvcRate = rvcRate.contentEquals("0") ? "1" : rvcRate ;
								
								consItemCmmAdin.addContent(new Element("Content", wco).setText(StringHelper.null2void(salesMap.get("ETC_YN")))); // 기타(Y/N)
								consItemCmmAdin.addContent(new Element("DomesticRawMaterialAmount", kcs).setText(StringHelper.null2void(salesMap.get("ORIGIN_AMT")))); // 원산지 재료가격 총액
								consItemCmmAdin.addContent(new Element("ImportRawMaterialAmount", kcs).setText(StringHelper.null2void(salesMap.get("NON_ORIGIN_AMT")))); // 비원산지 재료가격 총액
								consItemCmmAdin.addContent(new Element("WhollyObtainedCriterionCode", kcs).setText(StringHelper.null2void(salesMap.get("WO_YN")))); // 완전생산기준 여부(Y/N)
								consItemCmmAdin.addContent(new Element("ChangeInTariffHeadingCode", kcs).setText(StringHelper.null2void(salesMap.get("CTC_YN")))); // 세번변경기준 여부(Y/N)
								consItemCmmAdin.addContent(new Element("ValueAddedCriterionCode", kcs).setText(StringHelper.null2void(salesMap.get("RVC_YN")))); // 부가가치기준 여부(Y/N)
								consItemCmmAdin.addContent(new Element("ValueAddedPercentageNumeric", kcs).setText(rvcRate)); // 부가가치 비율(%)
								consItemCmmAdin.addContent(new Element("SimultaneousSatisfactionCriterionCode", kcs).setText(StringHelper.null2void(salesMap.get("CTC_RVC_YN")))); // 세번변경기준과 부가가치기준 동시적용 여부(Y/N)
								consItemCmmAdin.addContent(new Element("MinimalCriterionCode", kcs).setText(StringHelper.null2void(salesMap.get("MIN_YN")))); // 최소기준 여부(Y/N)
								consItemCmmAdin.addContent(new Element("AccumulationCriterionCode", kcs).setText(StringHelper.null2void(salesMap.get("ACO_YN")))); // 누적기준 여부(Y/N)
								consItemCmmAdin.addContent(new Element("OffshoreProductionCriterionCode", kcs).setText(StringHelper.null2void(salesMap.get("SP_YN")))); // 역외가공기준(개성공단 생산품) 여부(Y/N)
								consItemCmmAdin.addContent(new Element("DirectCarriageCriterionCode", kcs).setText(StringHelper.null2void(salesMap.get("DIRECT_MOVE_YN")))); // 직접운송 여부(Y/N)
								consItemCmmAdin.addContent(new Element("RuleOfOrignCriterionCode", kcs).setText(StringHelper.null2void(salesMap.get("COMPANY_CO_YN")))); // 원산지 결정 충족 여부(Y/N)
								
								consItemCmm.addContent(consItemCmmAdin);
							} // end /Declaration/Consignment/ConsignmentItem/Commodity/AdditionalInformation
							
							// 원산지 소명서 - 원재료 명세서
							if(itemList != null && itemList.size() > 0) {
								int nm = 1;
								
								for(int r = 0; r < itemList.size(); r++) {
									Map rowMap = (Map) itemList.get(r);
									
									// 각 elements에 입력할 항목의 값을 구한다.
									String req_qty = StringHelper.null2void(rowMap.get("REQ_QTY"));
									String item_cd = StringHelper.null2void(rowMap.get("ITEM_CD"));
									String fta_unit_amt = StringHelper.null2void(rowMap.get("FTA_UNIT_AMT"));
									String co_doc_no = StringHelper.null2void(rowMap.get("CO_DOC_NO"));
									String m_origin_nation_cd =  StringHelper.null2void(rowMap.get("ORIGIN_NATION_CD"));
									String address = StringHelper.null2void(rowMap.get("ADDRESS"));
									String itemCode = StringHelper.null2void(rowMap.get("PRODUCT_ITEM_CD"));
									String custItemCode = StringHelper.null2void(rowMap.get("CUSTOMER_ITEM_CD"));
									vendor_name = StringHelper.null2void(rowMap.get("VENDOR_NAME"));
									presdent_name = StringHelper.null2void(rowMap.get("PRESIDENT_NAME"));
									business_no = StringHelper.null2void(rowMap.get("BUSINESS_NO"));
									phone_no = StringHelper.null2void(rowMap.get("PHONE_NO"));
									fax_no = StringHelper.null2void(rowMap.get("FAX_NO"));
									president_email = StringHelper.null2void(rowMap.get("PRESIDENT_EMAIL"));
									
									if(StringHelper.null2string(custProdCode, prodCode).equals(StringHelper.null2string(custItemCode, itemCode))) {
										Element consItemCmmDet = new Element("DetailedCommodity", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity
										// 원재료 명세서
										consItemCmmDet.addContent(new Element("SequenceNumeric", wco).setText(Integer.toString(nm))); // 일련번호
										if(!req_qty.isEmpty()) consItemCmmDet.addContent(new Element("CountQuantity", wco).setAttribute("kcsUnitCode", StringHelper.null2void(rowMap.get("QTY_UNIT"))).setText(req_qty)); // 수량(단위)
										if(!item_cd.isEmpty()) consItemCmmDet.addContent(new Element("ID", wco).setText(item_cd)); // 품목코드
										consItemCmmDet.addContent(new Element("Name", wco).setText(StringHelper.null2void(rowMap.get("ITEM_NAME")))); // 품목명
										if(!fta_unit_amt.isEmpty()) consItemCmmDet.addContent(new Element("ValueAmount", wco).setAttribute("currencyID", StringHelper.null2void(rowMap.get("CURRENCY"))).setText(fta_unit_amt)); // 통화/금액
										
										// 원재료 증빙파일
										if(false) {
											Element consItemCmmDetAddo = new Element("AdditionalDocument", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity/AdditionalDocument
											if(!co_doc_no.isEmpty()) consItemCmmDetAddo.addContent(new Element("ID", wco).setText(co_doc_no)); // 협력사 원산지증빙번호
											if(!m_origin_nation_cd.isEmpty()) consItemCmmDetAddo.addContent(new Element("TypeCode", wco).setText(m_origin_nation_cd)); // 협력사 원산지증빙구분(01:원산지 확인서)
											consItemCmmDet.addContent(consItemCmmDetAddo);
										}
										
										// 원재료 HS코드
										Element consItemCmmDetClss = new Element("Classification", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity/Classification
										consItemCmmDetClss.addContent(new Element("ID", wco).setText(StringHelper.null2void(rowMap.get("HS_CODE")))); // HS10단위부호
										consItemCmmDet.addContent(consItemCmmDetClss);
										
										// 원재료 공급처(생산자)
										Element consItemCmmDetManu = new Element("Manufacturer", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity/Manufacturer
										if(!vendor_name.isEmpty()) consItemCmmDetManu.addContent(new Element("Name", wco).setText(vendor_name)); // 협력사명
										if(!address.isEmpty()) {
											Element consItemCmmDetManuAddr = new Element("Address", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity/Manufacturer/Address
											consItemCmmDetManuAddr.addContent(new Element("Line", wco).setText(address)); // 주소
											consItemCmmDetManu.addContent(consItemCmmDetManuAddr);
										}
										
										if(!phone_no.isEmpty()) {
											Element consItemCmmDetManuComm1 = new Element("Communication", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity/Manufacturer/Communication
											consItemCmmDetManuComm1.addContent(new Element("TypeID", wco).setText("TE")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
											consItemCmmDetManuComm1.addContent(new Element("ID", wco).setText(phone_no)); // 전화번호
											consItemCmmDetManu.addContent(consItemCmmDetManuComm1);
										}
										if(!fax_no.isEmpty()) {
											Element consItemCmmDetManuComm2 = new Element("Communication", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity/Manufacturer/Communication
											consItemCmmDetManuComm2.addContent(new Element("TypeID", wco).setText("FX")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
											consItemCmmDetManuComm2.addContent(new Element("ID", wco).setText(fax_no)); // 팩스번호
											consItemCmmDetManu.addContent(consItemCmmDetManuComm2);
										}
										if(!president_email.isEmpty()) {
											Element consItemCmmDetManuComm3 = new Element("Communication", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity/Manufacturer/Communication
											consItemCmmDetManuComm3.addContent(new Element("TypeID", wco).setText("EM")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
											consItemCmmDetManuComm3.addContent(new Element("ID", wco).setText(president_email)); // 이메일
											consItemCmmDetManu.addContent(consItemCmmDetManuComm3);
										}
										
										consItemCmmDet.addContent(consItemCmmDetManu);
										
										// 원재료 원산지
										Element consItemCmmDetOrig = new Element("Origin", wco); // XML Path : Consignment/ConsignmentItem/Commodity/DetailedCommodity/Origin
										consItemCmmDetOrig.addContent(new Element("CountryCode", wco).setText(StringHelper.null2void(rowMap.get("ORIGIN_NATION_CD")))); // 원산지국가코드(KR인 경우에만 표시)
										consItemCmmDet.addContent(consItemCmmDetOrig);
										
										consItemCmm.addContent(consItemCmmDet);
										
										nm++;
									}
								} // end for()
							} // end /Declaration/Consignment/ConsignmentItem/Commodity/DetailedCommodity
							
							if(true) { // 원산지 소명서 - 물품상세
								Element consItemCmmPre = new Element("PreviousDocument", wco); // XML Path : Consignment/ConsignmentItem/Commodity/PreviousDocument
								
								consItemCmmPre.addContent(new Element("TypeCode", wco).setText(StringHelper.null2void(salesMap.get("PRICE_TERM_CD")))); // 제품가격조건(15:EXW... 이외는 확인이 코드 확인이 필요함)
								consItemCmmPre.addContent(new Element("AmountAmount", wco).setAttribute("currencyID", StringHelper.null2void(salesMap.get("CURRENCY"))).setText(StringHelper.null2void(salesMap.get("AMOUNT")))); // 통화/판매금액
								
								Element consItemCmmPreAdin = new Element("AdditionalInformation", wco); // XML Path : Consignment/ConsignmentItem/Commodity/PreviousDocument/AdditionalInformation
								
								if(!product_line_cd_up.isEmpty()) consItemCmmPreAdin.addContent(new Element("Content", wco).setText(product_line_cd_up)); // 생산공정
								consItemCmmPreAdin.addContent(new Element("StatementCode", wco).setText(StringHelper.null2void(salesMap.get("ORGAN_FTA_NAME")))); // 협정코드(관세청 기준)
								
								Element consItemCmmPreAdinOrig = new Element("Origin", wco); // XML Path : Consignment/ConsignmentItem/Commodity/PreviousDocument/AdditionalInformation/Origin
								consItemCmmPreAdinOrig.addContent(new Element("Description", kcs).setText(StringHelper.null2void(salesMap.get("RULE_CONTENTS_TYP_NAME")))); // 원산지 결정기준(세번변경기준, 부가가치기준, 조합기준)
								
								consItemCmmPreAdin.addContent(consItemCmmPreAdinOrig);
								consItemCmmPre.addContent(consItemCmmPreAdin);
								
								consItemCmm.addContent(consItemCmmPre);
							} // end /Declaration/Consignment/ConsignmentItem/Commodity/PreviousDocument
							
							if(ftaCd.equals("PKRAP")) { // APTA일 경우만
								Element consItemCmmPro = new Element("Producer", wco); // XML Path : Consignment/ConsignmentItem/Commodity/Producer
								
								consItemCmmPro.addContent(new Element("ID", wco).setText(business_no)); // 사업자등록번호
								consItemCmmPro.addContent(new Element("Name", wco).setText(vendor_name)); // 상호
								
								Element consItemCmmProCon = new Element("Contact", wco); // XML Path : Consignment/ConsignmentItem/Commodity/Producer/Contact
								
								consItemCmmProCon.addContent(new Element("RepresentativeName", kcs).setText(presdent_name)); // 대표자
								consItemCmmPro.addContent(consItemCmmProCon);
								
								if(!phone_no.isEmpty()) {
									Element consItemCmmProComm1 = new Element("Communication", wco); // XML Path : Consignment/ConsignmentItem/Commodity/Producer/Communication
									consItemCmmProComm1.addContent(new Element("TypeID", wco).setText("TE")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
									consItemCmmProComm1.addContent(new Element("ID", wco).setText(phone_no)); // 전화번호
									consItemCmmPro.addContent(consItemCmmProComm1);
								}
								if(!fax_no.isEmpty()) {
									Element consItemCmmProComm2 = new Element("Communication", wco); // XML Path : Consignment/ConsignmentItem/Commodity/Producer/Communication
									consItemCmmProComm2.addContent(new Element("TypeID", wco).setText("FX")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
									consItemCmmProComm2.addContent(new Element("ID", wco).setText(fax_no)); // 팩스번호
									consItemCmmPro.addContent(consItemCmmProComm2);
								}
								if(!president_email.isEmpty()) {
									Element consItemCmmProComm3 = new Element("Communication", wco); // XML Path : Consignment/ConsignmentItem/Commodity/Producer/Communication
									consItemCmmProComm3.addContent(new Element("TypeID", wco).setText("EM")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
									consItemCmmProComm3.addContent(new Element("ID", wco).setText(president_email)); // 이메일
									consItemCmmPro.addContent(consItemCmmProComm3);
								}
								
								consItemCmm.addContent(consItemCmmPro);
							} // end /Declaration/Consignment/ConsignmentItem/Commodity/Producer
							
							consItem.addContent(consItemCmm);
						} // end /Declaration/Consignment/ConsignmentItem/Commodity
						
						if(true) { // 제품 총중량 및 순중량
							Element consItemGood = new Element("GoodsMeasure", wco); // XML Path : Consignment/ConsignmentItem/GoodsMeasure
							
							consItemGood.addContent(new Element("GrossMassMeasure", wco).setAttribute("kcsUnitCode", StringHelper.null2void(salesMap.get("WEIGHT_UNIT"))).setText(StringHelper.null2void(salesMap.get("GROSS_WEIGHT")))); // 단위/총중량
							if(!net_weight.isEmpty()) consItemGood.addContent(new Element("NetNetWeightMeasure", wco).setAttribute("kcsUnitCode", StringHelper.null2void(salesMap.get("WEIGHT_UNIT"))).setText(net_weight)); // 단위/순중량
							
							consItem.addContent(consItemGood);
						} // end /Declaration/Consignment/ConsignmentItem/GoodsMeasure
						
						String consProducerName = StringHelper.null2void(salesMap.get("CONS_PRODUCER_NAME"));
						
						if(ftaCd.equals("PKRRC") && !consProducerName.isEmpty()) { // 제품별 생산자(영문) - (2022-01-29) -- RCEP인 경우에만 적용
							Element manu = new Element("Manufacturer", wco); // XML Path : Consignment/ConsignmentItem/Manufacturer
							
							manu.addContent(new Element("ID", wco).setText(StringHelper.null2void(salesMap.get("CONS_PRODUCER_BUSINESS_NO")))); // 생산자 사업자등록번호
							manu.addContent(new Element("Name", wco).setText(consProducerName)); // 생산자명
							
							String consRcepNation = StringHelper.null2void(salesMap.get("CONS_RCEP_NATION_NAME"));
							
							manu.addContent(new Element("ReceiptOriginCountryName", kcs).setText(consRcepNation)); // RCEP원산지국가명(2022-01-29)
							
							Element manuAddr = new Element("Address", wco); // XML Path : Manufacturer/Address
							if(!producer_nation_cd.isEmpty()) manuAddr.addContent(new Element("CountryCode", wco).setText(StringHelper.null2void(salesMap.get("CONS_PRODUCER_NATION_CD")))); // 생산자국가코드
							if(!producer_address.isEmpty()) manuAddr.addContent(new Element("Line", wco).setText(StringHelper.null2void(salesMap.get("CONS_PRODUCER_ADDRESS")))); // 생산자주소
							manu.addContent(manuAddr);
							
							Element manuCont = new Element("Contact", wco); // XML Path : Manufacturer/Contact
							if(!producer_president_name.isEmpty()) manuCont.addContent(new Element("RepresentativeName", kcs).setText(StringHelper.null2void(salesMap.get("CONS_PRODUCER_PRESIDENT_NAME")))); // 생산자대표자명
							manu.addContent(manuCont);
							
							if(!producer_phone_no.isEmpty()) {
								Element manuComm1 = new Element("Communication", wco); // XML Path : Manufacturer/Communication
								manuComm1.addContent(new Element("TypeID", wco).setText("TE")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
								manuComm1.addContent(new Element("ID", wco).setText(StringHelper.null2void(salesMap.get("CONS_PRODUCER_PHONE_NO")))); // 전화번호
								manu.addContent(manuComm1);
							}
							if(!producer_fax_no.isEmpty()) {
								Element manuComm2 = new Element("Communication", wco); // XML Path : Manufacturer/Communication
								manuComm2.addContent(new Element("TypeID", wco).setText("FX")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
								manuComm2.addContent(new Element("ID", wco).setText(StringHelper.null2void(salesMap.get("CONS_PRODUCER_FAX_NO")))); // 팩스번호
								manu.addContent(manuComm2);
							}
							if(!producer_email.isEmpty()) {
								Element manuComm3 = new Element("Communication", wco); // XML Path : Manufacturer/Communication
								manuComm3.addContent(new Element("TypeID", wco).setText("EM")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
								manuComm3.addContent(new Element("ID", wco).setText(StringHelper.null2void(salesMap.get("CONS_PRODUCER_EMAIL")))); // 이메일
								manu.addContent(manuComm3);
							}
							
							String consInvoiceNo = StringHelper.null2void(salesMap.get("CONS_INVOICE_NO"));
							String consInvoiceDate = StringHelper.null2void(salesMap.get("CONS_INVOICE_DATE"));
							
							if(!consInvoiceNo.isEmpty()) {
								Element manuInvoice = new Element("Invoice", wco); // XML Path : Manufacturer/Invoice
								manuInvoice.addContent(new Element("ID", wco).setText(consInvoiceNo)); // 송장번호
								if(!consInvoiceDate.isEmpty()) manuInvoice.addContent(new Element("IssueDateTime", wco).setText(consInvoiceDate)); // 송장발행일
								manu.addContent(manuInvoice);
							}
							
							consItem.addContent(manu);
						} // end /Declaration/Consignment/ConsignmentItem/Origin
						
						if(true) { // 원산지 및 결정기준
							Element consItemGood = new Element("Origin", wco); // XML Path : Consignment/ConsignmentItem/Origin
							
							if(!origin_nation_cd.isEmpty()) consItemGood.addContent(new Element("CountryCode", wco).setText(origin_nation_cd)); // 원산지국가코드(역내산인 경우에만 표시)
							if(!origin_simple_report.isEmpty()) consItemGood.addContent(new Element("CriteriaCode", kcs).setText(origin_simple_report)); // 원산지간이대상여부: Declaration/Consignment/ConsignmentItem/Origin/CriteriaCode (2022-01-29)
							consItemGood.addContent(new Element("Description", kcs).setText(StringHelper.null2void(salesMap.get("RULE_CONTENTS_N")))); // 원산지결정기준(증명서 표기코드 기입)
							
							consItem.addContent(consItemGood);
						} // end /Declaration/Consignment/ConsignmentItem/Origin
						
						if(true) { // 물품판매정보
							Element consItemOrig = new Element("OriginCommodity", wco); // XML Path : Consignment/ConsignmentItem/OriginCommodity
							
							consItemOrig.addContent(new Element("CargoDescription", kcs).setText(StringHelper.null2void(salesMap.get("ITEM_SPEC")))); // 물품상세설명(300자 이내)
							consItemOrig.addContent(new Element("CountQuantity", kcs).setAttribute("kcsUnitCode", StringHelper.null2void(salesMap.get("UNIT"))).setText(StringHelper.null2void(salesMap.get("SALES_QTY")))); // 수량(단위)
							consItemOrig.addContent(new Element("ID", kcs).setText(StringHelper.null2string(custProdCode, prodCode))); // 고객사 제품코드 우선적용(2022-01-30)
							consItemOrig.addContent(new Element("Name", kcs).setText(StringHelper.null2void(salesMap.get("PRODUCT_ITEM_NAME")))); // 제품명
							consItemOrig.addContent(new Element("ValueAmount", kcs).setAttribute("currencyID", StringHelper.null2void(salesMap.get("EXPORT_CURRENCY"))).setText(StringHelper.null2void(salesMap.get("FOB_UNIT_PRICE")))); // 통화/원산지 판정에 적용된 FOB금액 적용
							if(!origin_rate.isEmpty()) consItemOrig.addContent(new Element("ExportRelativeImportanceNumeric", kcs).setText(origin_rate)); // 국내부가가치비율
							
							Element consItemOrigClss = new Element("Classification", wco); // XML Path : Consignment/ConsignmentItem/OriginCommodity/Classification
							consItemOrigClss.addContent(new Element("ID", wco).setText(StringHelper.null2void(salesMap.get("HS_CODE")))); // HS코드
							if(!hs_code_desc.isEmpty()) consItemOrigClss.addContent(new Element("AdditionalStatement", kcs).setText(hs_code_desc)); // HS코드 비교
							consItemOrig.addContent(consItemOrigClss);
							
							consItem.addContent(consItemOrig);
						} // end /Declaration/Consignment/ConsignmentItem/OriginCommodity
						
						if(false) { // 포장정보(매출정보)
							Element consItemPack = new Element("Packaging", wco); // XML Path : Consignment/ConsignmentItem/Packaging
							
							if(!s_packing_qty.isEmpty()) consItemPack.addContent(new Element("QuantityQuantity", wco).setText(s_packing_qty)); // 포장수
							if(!packing_unit.isEmpty()) consItemPack.addContent(new Element("TypeCode", wco).setText(packing_unit)); // 포장단위
							
							consItem.addContent(consItemPack);
						} // end /Declaration/Consignment/ConsignmentItem/Packaging
						
						cons.addContent(consItem);
					} // end /Declaration/Consignment/ConsignmentItem
					
					if(true) { // 수출 신고정보(매출 정보)
						Element consComm = new Element("Commodity", wco); // XML Path : Consignment/Commodity
						
						if(!export_req_qty.isEmpty()) consComm.addContent(new Element("CountQuantity", wco).setAttribute("kcsUnitCode", StringHelper.null2void(salesMap.get("EXPORT_REQ_UNIT"))).setText(export_req_qty)); // 수출신고 수량(중량)을 기재
						if(!export_item_name.isEmpty()) consComm.addContent(new Element("Description", wco).setText(export_item_name)); // 모델규격명(증명서 발급정보)
						if(!declare_amount.isEmpty()) consComm.addContent(new Element("ValueAmount", wco).setText(declare_amount)); // 신고금액
						if(!declare_unit_price.isEmpty()) consComm.addContent(new Element("UnitPriceAmount", kcs).setText(declare_unit_price)); // 신고단가
						
						Element consCommAdd = new Element("AdditionalDocument", wco); // XML Path : Consignment/Commodity/AdditionalDocument
						consCommAdd.addContent(new Element("QuantityQuantity", wco).setText(StringHelper.null2void(salesMap.get("CO_SALES_QTY")))); // 규격의 원산지증명발급 신청수량
						consCommAdd.addContent(new Element("ID", wco).setText(StringHelper.null2void(salesMap.get("EXPORT_DECLARE_NO")))); // 수출신고번호(57번 항목에 기재된 수출신고번호에 있는 값을 기재해야 한다.)
						consCommAdd.addContent(new Element("SequenceNumeric", wco).setText(StringHelper.null2void(salesMap.get("EXPORT_DECLARE_SEQ")))); // 수출신고서 상의 란번호(수출신고번호가 기타번호인 경우 '000'을 기재)
						consCommAdd.addContent(new Element("LineNumeric", wco).setText(StringHelper.null2string(salesMap.get("EXPORT_FORM_SEQ"), numSeq))); // 수출신고서 상의 규격일련번호(수출신고번호가 기타번호인경우 '01'부터 순차적으로 기재)
						consComm.addContent(consCommAdd);
						
						cons.addContent(consComm);
					} // end /Declaration/Consignment/Commodity
					
					Declaration.addContent(cons);
				} // end for() // end /Declaration/Consignment
			} // end if()
		} // end if()
		
		if(true) { // 수출자 정보(영문으로 작성할 것)
			Element expo = new Element("Exporter", wco); // XML Path : Exporter
			
			// 수출자사업자등록번호(KTX)/수출자개인통관고유부호(380)(SchemaAgencyID로 구분(380:개인통관고유부호, KTX: 사업자등록번호))
			// 수출자개인통관고유부호 형식 = 개인(P) + 발급연도(2) + 일련번호 (9) + 오류검증부호(1), 예시 : P812120005707
			if(!exporter_business_no.isEmpty()) expo.addContent(new Element("ID", wco).setAttribute("schemeAgencyID", StringHelper.null2string(docMap.get("BUSINESS_TYPE"), "KTX")).setText(exporter_business_no));
			expo.addContent(new Element("Name", wco).setText(StringHelper.null2void(docMap.get("EXPORTER_NAME")))); // 수출자명
			
			Element expoAddr = new Element("Address", wco); // XML Path : Exporter/Address
			expoAddr.addContent(new Element("CountryCode", wco).setText(StringHelper.null2void(docMap.get("EXPORTER_NATION_CD")))); // 수출자국가코드
			expoAddr.addContent(new Element("Line", wco).setText(StringHelper.null2void(docMap.get("EXPORTER_ADDRESS")))); // 수출자주소
			expo.addContent(expoAddr);
			
			Element expoCont = new Element("Contact", wco); // XML Path : Exporter/Contact
			if(!exporter_president_name.isEmpty()) expoCont.addContent(new Element("RepresentativeName", kcs).setText(exporter_president_name)); // 수출자대표자명
			expo.addContent(expoCont);
			
			if(!exporter_phone_no.isEmpty()) {
				Element expoComm1 = new Element("Communication", wco); // XML Path : Exporter/Communication
				expoComm1.addContent(new Element("TypeID", wco).setText("TE")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
				expoComm1.addContent(new Element("ID", wco).setText(exporter_phone_no)); // 전화번호
				expo.addContent(expoComm1);
			}
			if(!exporter_fax_no.isEmpty()) {
				Element expoComm2 = new Element("Communication", wco); // XML Path : Exporter/Communication
				expoComm2.addContent(new Element("TypeID", wco).setText("FX")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
				expoComm2.addContent(new Element("ID", wco).setText(exporter_fax_no)); // 팩스번호
				expo.addContent(expoComm2);
			}
			if(!exporter_president_email.isEmpty()) {
				Element expoComm3 = new Element("Communication", wco); // XML Path : Exporter/Communication
				expoComm3.addContent(new Element("TypeID", wco).setText("EM")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
				expoComm3.addContent(new Element("ID", wco).setText(exporter_president_email)); // 이메일
				expo.addContent(expoComm3);
			}
			
			Declaration.addContent(expo);
		} // end /Declaration/Exporter
		
		if(true) { // 최종 목적지 및 제3국 송장 정보 
			Element good = new Element("GoodsShipment", wco); // XML Path : GoodsShipment
			// 인보이스상 최종 목적국가
			
			good.addContent(new Element("ExportationCountryCode", wco).setText(StringHelper.null2void(docMap.get("FINAL_DESTINATION")))); // 목적국 국가코드
			if(!final_destination_name.isEmpty()) good.addContent(new Element("ExportationCountryName", kcs).setText(final_destination_name)); // 목적국 국가명
			
			if(!oed_invoice_no.isEmpty()) { // 제3국 송장정보(추후 지원이 필요할경우에만 사용할 계획임. 20180907, 김종현), 제3국 송장번호가 있을 경우에만 적용(2022-02-24)
				Element goodAdd = new Element("AdditionalDocument", wco); // XML Path : GoodsShipment/AdditionalDocument
				goodAdd.addContent(new Element("ID", wco).setText(oed_invoice_no)); // 송장번호
				if(!oed_invoice_issue_date.isEmpty()) goodAdd.addContent(new Element("IssueDateTime", wco).setText(oed_invoice_issue_date)); // 송장발행일자
				if(!invoice_company_name.isEmpty()) goodAdd.addContent(new Element("IssuingPartyName", wco).setText(invoice_company_name)); // 송장발행회사명
				if(!oed_export_yn.isEmpty()) goodAdd.addContent(new Element("ItemOfBusiness", kcs).setText(oed_export_yn)); // 제3자무역
				
				if(!oed_company_name.isEmpty()) {
					Element goodAddAuth = new Element("Authenticator", wco); // XML Path : GoodsShipment/AdditionalDocument/Authenticator
					goodAddAuth.addContent(new Element("Name", wco).setText(oed_company_name)); // 운영인 법적이름
					if(!export_other_yn.isEmpty()) goodAddAuth.addContent(new Element("PrintTypeCode", kcs).setText(export_other_yn)); // 송장번호출력여부
					
					if(!oed_nation_cd.isEmpty()) {
						Element goodAddAuthAddr = new Element("Address", wco); // XML Path : GoodsShipment/AdditionalDocument/Authenticator/Address
						goodAddAuthAddr.addContent(new Element("CountryCode", wco).setText(oed_nation_cd)); // 송장 발행 국가코드
						if(!oed_address.isEmpty()) goodAddAuthAddr.addContent(new Element("Line", wco).setText(oed_address)); // 송장 발행회사 주소
						goodAddAuth.addContent(goodAddAuthAddr);
					}
					
					goodAdd.addContent(goodAddAuth);
				}
				
				good.addContent(goodAdd);
			}
			
			Element goodInvo = new Element("Invoice", wco); // XML Path : GoodsShipment/Invoice
			goodInvo.addContent(new Element("ID", wco).setText(StringHelper.null2void(docMap.get("INVOICE_NO")))); // 송장번호
			goodInvo.addContent(new Element("IssueDateTime", wco).setText(StringHelper.null2void(docMap.get("INVOICE_ISSUE_DATE")))); // 송장발행일자
			goodInvo.addContent(new Element("SequenceNumeric", kcs).setText(StringHelper.null2string(docMap.get("INVOICE_SEQ"), "1"))); // 송장일련번호
			good.addContent(goodInvo);
			
			Declaration.addContent(good);
		} // end /Declaration/GoodsShipment
		
		if(false) { // 경유국 정보
			Element itin = new Element("Itinerary", wco); // XML Path : Itinerary
			
			if(!itiner_nation_cd.isEmpty()) itin.addContent(new Element("ID", wco).setText(itiner_nation_cd)); // 경유국가코드
			if(!itiner_nation_name.isEmpty()) itin.addContent(new Element("Name", wco).setText(itiner_nation_name)); // 경유국가명
			
			Declaration.addContent(itin);
		} // end /Declaration/Itinerary
		
		if(true) { // 수입자 정보(영문으로 작성할 것)
			Element impo = new Element("Importer", wco); // XML Path : Importer
			
			if(!importer_business_no.isEmpty()) impo.addContent(new Element("ID", wco).setText(importer_business_no)); // 해외수입자 거래처부호(코드)
			impo.addContent(new Element("Name", wco).setText(StringHelper.null2void(docMap.get("IMPORTER_NAME")))); // 수입자명
			
			Element impoAddr = new Element("Address", wco); // XML Path : Importer/Address
			if(!importer_nation_cd.isEmpty()) impoAddr.addContent(new Element("CountryCode", wco).setText(importer_nation_cd)); // 수입자국가코드
			if(!importer_address.isEmpty()) impoAddr.addContent(new Element("Line", wco).setText(importer_address)); // 수입자주소
			impo.addContent(impoAddr);
			
			Element impoCont = new Element("Contact", wco); // XML Path : Importer/Contact
			if(!importer_president_name.isEmpty()) impoCont.addContent(new Element("RepresentativeName", kcs).setText(importer_president_name)); // 수입자대표자명
			impo.addContent(impoCont);
			
			if(!import_phone_no.isEmpty()) {
				Element impoComm1 = new Element("Communication", wco); // XML Path : Importer/Communication
				impoComm1.addContent(new Element("TypeID", wco).setText("TE")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
				impoComm1.addContent(new Element("ID", wco).setText(import_phone_no)); // 전화번호
				impo.addContent(impoComm1);
			}
			if(!importer_fax_no.isEmpty()) {
				Element impoComm2 = new Element("Communication", wco); // XML Path : Importer/Communication
				impoComm2.addContent(new Element("TypeID", wco).setText("FX")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
				impoComm2.addContent(new Element("ID", wco).setText(importer_fax_no)); // 팩스번호
				impo.addContent(impoComm2);
			}
			if(!importer_email.isEmpty()) {
				Element impoComm3 = new Element("Communication", wco); // XML Path : Importer/Communication
				impoComm3.addContent(new Element("TypeID", wco).setText("EM")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
				impoComm3.addContent(new Element("ID", wco).setText(importer_email)); // 이메일
				impo.addContent(impoComm3);
			}
			
			Declaration.addContent(impo);
		} // end /Declaration/Importer
		
		if(!ftaCd.equals("PKRAP")) { // 생산자 정보(영문으로 작성할 것)
			Element manu = new Element("Manufacturer", wco); // XML Path : Manufacturer 
			
			// RCEP인 경우 란정보 생산자 정보를 입력하는 경우 Name에는 SEE BOX 8이라고 입력하고 사업자등록번호는 0000000000으로 기재함(2022-02-17)
			if(ftaCd.equals("PKRRC")) {
				manu.addContent(new Element("ID", wco).setText("0000000000")); // 생산자 사업자등록번호
				manu.addContent(new Element("Name", wco).setText("SEE BOX 8")); // 생산자명
			} else {
				manu.addContent(new Element("ID", wco).setText(producer_business_no)); // 생산자 사업자등록번호
				manu.addContent(new Element("Name", wco).setText(producer_name)); // 생산자명
				
				Element manuAddr = new Element("Address", wco); // XML Path : Manufacturer/Address
				if(!producer_nation_cd.isEmpty()) manuAddr.addContent(new Element("CountryCode", wco).setText(producer_nation_cd)); // 생산자국가코드
				if(!producer_address.isEmpty()) manuAddr.addContent(new Element("Line", wco).setText(producer_address)); // 생산자주소
				manu.addContent(manuAddr);
				
				Element manuCont = new Element("Contact", wco); // XML Path : Manufacturer/Contact
				if(!producer_president_name.isEmpty()) manuCont.addContent(new Element("RepresentativeName", kcs).setText(producer_president_name)); // 생산자대표자명
				manu.addContent(manuCont);
				
				if(!producer_phone_no.isEmpty()) {
					Element manuComm1 = new Element("Communication", wco); // XML Path : Manufacturer/Communication
					manuComm1.addContent(new Element("TypeID", wco).setText("TE")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
					manuComm1.addContent(new Element("ID", wco).setText(producer_phone_no)); // 전화번호
					manu.addContent(manuComm1);
				}
				if(!producer_fax_no.isEmpty()) {
					Element manuComm2 = new Element("Communication", wco); // XML Path : Manufacturer/Communication
					manuComm2.addContent(new Element("TypeID", wco).setText("FX")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
					manuComm2.addContent(new Element("ID", wco).setText(producer_fax_no)); // 팩스번호
					manu.addContent(manuComm2);
				}
				if(!producer_email.isEmpty()) {
					Element manuComm3 = new Element("Communication", wco); // XML Path : Manufacturer/Communication
					manuComm3.addContent(new Element("TypeID", wco).setText("EM")); // 연락처 구분(TE:전화번호, EM:이메일, FX:팩스)
					manuComm3.addContent(new Element("ID", wco).setText(producer_email)); // 이메일
					manu.addContent(manuComm3);
				}
			}
			
			Declaration.addContent(manu);
		} // end /Declaration/Manufacturer
		
		if(true) { // 선적지 정보
			Element itin = new Element("LoadingLocation", wco); // XML Path : LoadingLocation
			
			itin.addContent(new Element("DepartureDateTime", wco).setText(StringHelper.null2void(docMap.get("DEPARTURE_DATE")))); // 출발일자
			itin.addContent(new Element("ID", wco).setText(StringHelper.null2void(docMap.get("LOADING_PORT")))); // 선적항구
			itin.addContent(new Element("LoadingDateTime", wco).setText(StringHelper.null2void(docMap.get("SHIPPING_DATE")))); // 선적일자
			itin.addContent(new Element("Name", wco).setText(StringHelper.null2void(docMap.get("LOADING_NATION_NAME")))); // 선적국가명
			itin.addContent(new Element("CountrySubEntityName", kcs).setText(StringHelper.null2void(docMap.get("LOADING_CITY_NAME")))); // 선적도시명
			
			Declaration.addContent(itin);
		} // end /Declaration/LoadingLocation
		
		String packing_unit = StringHelper.null2void(docMap.get("PACKING_UNIT"));
		
		if(!packing_qty.isEmpty() && !packing_unit.isEmpty()) { // 포장 정보
			Element pack = new Element("Packaging", wco); // XML Path : Packaging
			
			pack.addContent(new Element("QuantityQuantity", wco).setAttribute("kcsUnitCode", packing_unit).setText(packing_qty)); // 총포장수(단위)
			if(!loading_mark.isEmpty()) pack.addContent(new Element("MarksNumbersID", wco).setText(loading_mark)); // 선적마크
			
			Declaration.addContent(pack);
		} // end /Declaration/Packaging
		
		if(true) { // 관련수출신고내역
			Element prev = new Element("PreviousDocument", wco); // XML Path : PreviousDocument
			
			prev.addContent(new Element("ID", wco).setText(StringHelper.null2void(docMap.get("EXPORT_DECLARE_NO")))); // 수출신고번호 체계 = 신고인부호(5) +연도(2) +일련번호(6)+체크디지트(1)
			if(!export_declare_date.isEmpty()) prev.addContent(new Element("IssueDateTime", wco).setText(export_declare_date)); // 수출신고 수리일자
			if(!export_declare_seq.isEmpty()) prev.addContent(new Element("LineNumeric", wco).setText(export_declare_seq)); // 수출신고총란번호
			prev.addContent(new Element("TypeCode", wco).setText(StringHelper.null2void(docMap.get("EXPORT_DECLARE_TYPE")))); // 수출신고 구분 - 01:수출신고, 02:까르네, 03:휴대반출, 04:간이수출, 99:기타번호(기타)
			
			Declaration.addContent(prev);
		} // end /Declaration/PreviousDocument
		
		if(true) { // 신청인 정보
			Element subm = new Element("Submitter", wco); // XML Path : Submitter
			
			subm.addContent(new Element("ID", wco).setAttribute("schemeAgencyID", StringHelper.null2string(docMap.get("BUSINESS_TYPE"), "KTX")).setText(StringHelper.null2void(docMap.get("REQ_BUSINESS_NO")))); // 신청업체사업자등록번호/신청인부호(SchemaAgencyID로 구분(380:신청인부호, KTX: 사업자등록번호))
			subm.addContent(new Element("Name", wco).setText(StringHelper.null2void(docMap.get("REQ_COMPANY_NAME")))); // 신청업체상호
			subm.addContent(new Element("RoleCode", wco).setText(StringHelper.null2void(docMap.get("REQ_USER_TYPE")))); // 신청인구분(01:관세사, 02:수출자, 04:생산자, 99:기타) - UI에서 직접 지정
			if(!kcs_user_id.isEmpty()) subm.addContent(new Element("PortalID", kcs).setText(kcs_user_id)); // 신청 담당자 아이디(관세청 로그인 아이디) - 서명권자 등록시 추가
			
			Element submCont = new Element("Contact", wco); // XML Path : Submitter/Contact
			submCont.addContent(new Element("Name", wco).setText(StringHelper.null2void(docMap.get("REQ_USER_NAME")))); // 신청인명
			if(!req_president_name.isEmpty()) submCont.addContent(new Element("RepresentativeName", kcs).setText(req_president_name)); // 신청인대표자명
			subm.addContent(submCont);
			
			Declaration.addContent(subm);
		} // end /Declaration/Submitter
		
		if(true) { // 선하증권번호
			Element tran = new Element("TransportContractDocument", wco); // XML Path : TransportContractDocument
			
			if(!bl_no.isEmpty()) tran.addContent(new Element("ID", wco).setText(bl_no)); // 선하증권/항공운송장번호
			
			Declaration.addContent(tran);
		} // end /Declaration/TransportContractDocument
		
		if(true) { // 목적국공항/항구 정보
			Element tran = new Element("TransitDestination", wco); // XML Path : TransitDestination
			
			tran.addContent(new Element("ID", wco).setText(StringHelper.null2void(docMap.get("FINAL_DEST_PORT")))); // 목적국공항/항구 코드
			if(!final_des_port_name.isEmpty()) tran.addContent(new Element("Name", wco).setText(final_des_port_name)); // 목적국공항/항구명
			
			Declaration.addContent(tran);
		} // end /Declaration/TransitDestination
		
		if(ftaCd.equals("PKRPE")) { // 하역항(한-페루 FTA 일경우 사용)
			Element tran = new Element("UnloadingLocation", wco); // XML Path : UnloadingLocation
			
			if(!discharge_port.isEmpty()) tran.addContent(new Element("ID", wco).setText(discharge_port)); // 하역항구 코드(한-페루 FTA 일경우에만 사용하고, 이외는 선택사항임)
			
			Declaration.addContent(tran);
		} // end /Declaration/UnloadingLocation
		
		Document myDoc = new Document(Declaration);
		
		Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
		String outputFolder = FileUtil.getFullPath("temp.dir");
		XMLOutputter outputter = new XMLOutputter();
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        
        StringBuffer fileName = new StringBuffer();
        
        try {
            outputter.setFormat(Format.getPrettyFormat());
            
            fileName.append(StringHelper.null2void(docMap.get("INVOICE_NO")).replace("-", ""));
            fileName.append("-");
            fileName.append(StringHelper.null2void(docMap.get("CO_DOC_NO")).replace("-", ""));
            fileName.append("-");
            fileName.append(sdf.format(dt1));
            fileName.append(".xml");
            
            fos = new FileOutputStream(outputFolder + fileName.toString());
            writer = new OutputStreamWriter(fos, Constants.APPLICATION_FILE_ENCODING);
            
            outputter.output(myDoc, writer);
            
            File fFile = new File(outputFolder + fileName.toString());
            
            xmlMap.put("XML_PATH", outputFolder);
            xmlMap.put("XML_NAME", fileName.toString());
            xmlMap.put("XML_ORIGIN_NAME", fileName.toString());
            xmlMap.put("XML_ORIGIN_FILE", FileUtil.getBytesFromFile(fFile));
            xmlMap.put("XML_FILE_TYPE", "B");
            
            if(log.isDebugEnabled()) {
                log.debug("Generate XML file info(path = "+ outputFolder + fileName.toString() + ")");
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(writer != null) writer.close();
            if(fos != null) fos.close();
            
            try {
	            // 생성되었던 파일을 삭제한다.
	            FileUtil.deleteTo("temp.dir", fileName.toString());
            } catch(Exception e) {
                throw e;
            }
        }
        
        return xmlMap;
	}
	
}
