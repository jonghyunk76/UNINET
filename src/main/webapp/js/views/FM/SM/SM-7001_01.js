/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7001_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7001_01.config.applyFtaNation();
    SM7001_01.init.initComponent();
}

var SM7001_01 = {
    
    // 초기값 설정
    init : {
        tabNumber : "1",
        initComponent : function() {
        	SM7001_01.calendar.initCalendar_1();
        	SM7001_01.calendar.initCalendar_2();
        	
            SM7001_01.datagrid.initGrid_1();
            SM7001_01.datagrid.initGrid_2();
            SM7001_01.datagrid.initGrid_3();
            SM7001_01.datagrid.initGrid_4();
            SM7001_01.datagrid.initGrid_5();
            
            SM7001_01.control.selectCompanyInfo();
        }
    }, 
    // 달력 그리드 생성
    calendar : {
    	initCalendar_1 : function() {
        	var obj = calendar.getObject("SM7001_01_form_01", "EXP_CERTIFY_APPLY_DATE");
            
            calendar.init.setInitDate("none");
            calendar.init.setRequired(false);
            
            calendar.create(obj);
        },
        initCalendar_2 : function() {
        	var obj = calendar.getObject("SM7001_01_form_01", "EXP_CERTIFY_END_DATE");
            
            calendar.init.setInitDate("none");
            calendar.init.setRequired(false);
            
            calendar.create(obj);
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {
    	data_1 : [
    		{CODE:" ", NAME:resource.getMessage("TXT_VENDOR_ITEM_NAME")},
	        {CODE:"1", NAME:resource.getMessage("PATNE, PURSI, LTIT")},
	        {CODE:"2", NAME:resource.getMessage("CRTCT, LTIT")}
        ],
        data_2 : [
    		{CODE:" ", NAME:resource.getMessage("ALL, CSTMR")},
	        {CODE:"1", NAME:resource.getMessage("CRTCT, CSTMR")}
        ],
        data_3 : [
    		{CODE:"A", NAME:resource.getMessage("BKREP, TYPE")},
	        {CODE:"C", NAME:resource.getMessage("BASIS, TYPE")},
	        {CODE:"T", NAME:resource.getMessage("CC_추척형")} // 개별법 적용 시 사용되는 방법(2021-06-08)
        ],
        data_4 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"Y", NAME:resource.getMessage("TXT_YES")},
	        {CODE:"N", NAME:resource.getMessage("TXT_NO")}
        ],
        data_5 : [
        	{CODE:"Y", NAME:resource.getMessage("TXT_YES")},
	        {CODE:"N", NAME:resource.getMessage("TXT_NO")}
        ],
        data_6 : [
        	{CODE:"D", NAME:resource.getMessage("BYDIV")},
	        {CODE:"C", NAME:resource.getMessage("BYCPY")}
        ],
        data_7 : [
	        {CODE:"2", NAME:resource.getMessage("BKREP, PERFO")},
	        {CODE:"1", NAME:resource.getMessage("PRODU,WHSQT")}
        ],
        data_8 : [
	        {CODE:"C", NAME:resource.getMessage("TXT_BASIC_COMPANY")},
	        {CODE:"D", NAME:resource.getMessage("BYDIV")}
	        //{CODE:"P", NAME:resource.getMessage("BYMTE, CNFIT")}
        ],
        data_9 : [
	        {CODE:"Y", NAME:resource.getMessage("TXT_NO")},
	        {CODE:"N", NAME:resource.getMessage("TXT_YES")}
        ],
        data_10 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"S", NAME:resource.getMessage("SUM()")},
        	{CODE:"X", NAME:resource.getMessage("MAX()")},
	        {CODE:"N", NAME:resource.getMessage("MIN()")},
	        {CODE:"A", NAME:resource.getMessage("AVG()")}
        ],
        data_11 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"1", NAME:resource.getMessage("PATNE,CODE")},
        	{CODE:"2", NAME:resource.getMessage("TXT_CO_REG_NUM")},
	        {CODE:"99", NAME:resource.getMessage("NOAPY,(,NON,GREAT,)")}
        ],
        data_12 : [ // 일배치 매출처리 구분(E:직수출만 처리, D:내수만 처리, DE:내수,수출 모두 처리)

        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"E", NAME:resource.getMessage("EXP, FNISH")},
        	{CODE:"D", NAME:resource.getMessage("DOMDE, FNISH")},
	        {CODE:"DE", NAME:resource.getMessage("DOMDE,+,EXP, FNISH")}
        ],
        data_13 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"N", NAME:resource.getMessage("NON,SENT")},
        	{CODE:"S", NAME:resource.getMessage("SENT,(,APROV,MAIL,)")},
	        {CODE:"Y", NAME:resource.getMessage("SENT,(,APROV,+,REQ,MAIL,)")}
        ],
        data_14 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"1", NAME:resource.getMessage("RELATE, TXT_BASIC_COMPANY")},
        	{CODE:"2", NAME:resource.getMessage("IDVDL, TXT_BASIC_COMPANY")},
        ],
        data_15 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
	        {CODE:"C", NAME:resource.getMessage("BYCPY")},
	        {CODE:"D", NAME:resource.getMessage("BYDIV")}
        ],
        data_16 : [
        	{CODE:"1", NAME:resource.getMessage("PRODU")+"BOM->"+resource.getMessage("STNDD")+"BOM"},
	        {CODE:"2", NAME:resource.getMessage("STNDD")+"BOM->"+resource.getMessage("PRODU")+"BOM"}
        ],
        data_17 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"D", NAME:resource.getMessage("MNUEP_D_CNVSN_RATE")},
        	{CODE:"M", NAME:resource.getMessage("MMNUEP_X_CNVSN_RATE")},
        	{CODE:"N", NAME:resource.getMessage("NOAPY")}
        ],
        data_18 : [
        	{CODE:"A", NAME:resource.getMessage("ALL,INDEX")},
        	{CODE:"Y", NAME:resource.getMessage("ORGGD, INDEX")},
        	{CODE:"N", NAME:resource.getMessage("NOORD, INDEX")}
        ],
        data_19 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"A", NAME:resource.getMessage("SS_ALL, SALES")},
        	{CODE:"Y", NAME:resource.getMessage("SS_EXP, SALES")},
        	{CODE:"N", NAME:resource.getMessage("SS_DOMDE, SALES")}
        ],
        data_20 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"1", NAME:"C/O "+resource.getMessage("CERIS")},
        	{CODE:"2", NAME:resource.getMessage("SS_DTSTA,+,CERIS")}
        ],
        data_21 : [
        	{CODE:"1", NAME:resource.getMessage("CC_이분법")},
        	{CODE:"2", NAME:resource.getMessage("CC_비율법(금액기준)")}
        ],
        data_22 : [
        	{CODE:"A", NAME:resource.getMessage("CC_Aging기법(재고회전)")},
        	{CODE:"I", NAME:resource.getMessage("CC_개별법(인보이스별)")}
        ],
        data_23 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"RNG", NAME:resource.getMessage("CC_기간내")},
        	{CODE:"CUR", NAME:resource.getMessage("CC_최근")}
        ],
        data_24 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"AVG", NAME:resource.getMessage("CC_평균")},
        	{CODE:"MIN", NAME:resource.getMessage("CC_최소")},
        	{CODE:"MAX", NAME:resource.getMessage("CC_최대")}
        ],
        data_25 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"B", NAME:resource.getMessage("CC_실적 BOM")},
        	{CODE:"R", NAME:resource.getMessage("CC_생산실적")}
        ],
        data_26 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"I", NAME:resource.getMessage("INVIC_MSSNM")},
        	{CODE:"D", NAME:resource.getMessage("CSTHU")+" "+resource.getMessage("TXT_DECLARE_NO")}
        ],
        data_27 : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"1", NAME:resource.getMessage("CC_원산지 판정완료 후 가능")},
        	{CODE:"2", NAME:resource.getMessage("CC_증명서 발급 후 가능")},
        	{CODE:"3", NAME:resource.getMessage("CC_모두 역내산으로 전송")}
        ],
        data_28 : [// PE판정대상(D:내수만, E:수출만, A:내수+수출)
            {CODE:" ", NAME:resource.getMessage("NOSEL")}, 
            {CODE:"D", NAME:resource.getMessage("DOMDE_FNISH")},
            {CODE:"E", NAME:resource.getMessage("EXP_FNISH")},
            {CODE:"A", NAME:resource.getMessage("DOMDE__43_EXP_FNISH")}
        ],
        //원산지 인증 수출자 여부
        initCombo_1 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_01", "CO_CERTIFIED_EXPORTER_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        //가공공정기준
        initCombo_2 : function() {
            var obj_02 = combo.getObject("SM7001_01_form_02", "SP_YN");
            
            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_02);
        },
        //완전 생산 기준
        initCombo_3 : function() {
            var obj_03 = combo.getObject("SM7001_01_form_02", "WO_YN");
            
            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_03);
        },
        //BOM 타입
        initCombo_4 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "BOM_TYPE");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"BOM_TYPE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_04);
        },
        //운영 구분
        initCombo_5 : function() {
            var obj_05 = combo.getObject("SM7001_01_form_02", "RUN_TYPE");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"RUN_TYPE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_05);
        },
        //인도조건 마감
        initCombo_6 : function() {
            var obj_06 = combo.getObject("SM7001_01_form_02", "TERM_TYPE");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"TERM_TYPE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_06);
        },
        //로컬 인도조건
        initCombo_7 : function() {
            var obj_07 = combo.getObject("SM7001_01_form_02", "DOMESTIC_TERM");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"PRICE_TERM"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_07);
        },
        //수출 인도조건
        initCombo_8 : function() {
            var obj_08 = combo.getObject("SM7001_01_form_02", "EXPORT_TERM");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"PRICE_TERM"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_08);
        },
        //관리 통화 단위
        initCombo_9 : function() {
            var obj_09 = combo.getObject("SM7001_01_form_02", "LOCAL_CURRENCY");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CURRENCY"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            combo.init.setEditable(true);
            
            combo.create(obj_09);
        },
        //환산 통화 단위
        initCombo_10 : function() {
            var obj_10 = combo.getObject("SM7001_01_form_02", "EXCHANGE_CURRENCY");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CURRENCY"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            combo.init.setEditable(true);
            
            combo.create(obj_10);
        },
        //환산 통화 타입
        initCombo_11 : function() {
            var obj_11 = combo.getObject("SM7001_01_form_02", "EXCHANGE_CURRENCY_TYPE");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"APPLY_CURRENCY"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_11);
        },
        // 판매유형의 맵핑 데이터 연결 구분
        initCombo_12 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "ITEM_TYPE_DATA");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"ITEM_TYPE_DATA", MESSAGE_CODE:"NOSEL"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        // 협력사 확인서 수취대상
        initCombo_13 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "VENDOR_CO_TARGET");
            
            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        }, //
        initCombo_14 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "BOM_MM_DELETE");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_15 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "BOM_ASSY_DEL_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_16 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "CUSTOMER_CO_TARGET");
            
            combo.init.setData(this.data_2);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_17 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "EVALU_TYPE");
            
            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_18 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "CO_CLOSING_UNIT");
            
            combo.init.setData(this.data_6);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_19 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "PROD_YYYYMM");
            
            combo.init.setData(this.data_7);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_20 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "IM_ITEM_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_21 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "CERT_EXPORTER_SCOPE");
            
            combo.init.setData(this.data_8);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_22 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "VENODR_FTA_FIX_YN");
            
            combo.init.setData(this.data_9);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_23 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "ORGAN_ISSUE_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            combo.init.setReadonly(true);
            
            combo.create(obj_04);
        },
        initCombo_24 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "RESIDUAL_CO_YN");
            
            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_25 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "RVC_DETERMIN_YN");
            
            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_26 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "BOM_REQ_SUM_YN");
            
            combo.init.setData(this.data_10);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_27 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "GEN_ORIGIN_REQ_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_28 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "NON_ORGIN_RESN_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_29 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "NON_ORGIN_RESN_REQ_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_30 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "VENDOR_ID_TYPE");
            
            combo.init.setData(this.data_11);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_31 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "VENDOR_PW_TYPE");
            
            combo.init.setData(this.data_11);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_32 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "VENDOR_INFO_REQUIRED_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_33 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "DAILY_SAELS_DE");
            
            combo.init.setData(this.data_12);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_34 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "CUSTOMER_PRICE_APLY_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_35 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "SALES_NO_PRICE_INC_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_36 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "SUB_MAIL_SEND_YN");
            
            combo.init.setData(this.data_13);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_37 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "EVALUATION_SCOPE");
            
            combo.init.setData(this.data_14);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_38 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "SALES_IN_ZERO_QTY_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_39 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "VAATZ_HUB_UNIT");
            
            combo.init.setData(this.data_15);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_40 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "BOM_APPLY_ORDER");
            
            combo.init.setData(this.data_16);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_41 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "AMOUNT_DETERMIN_YN");
            
            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_42 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "USAGE_DETERMIN_YN");
            
            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_43 : function() {
            var obj_04 = combo.getObject("SM7001_01_form_02", "WEIGHT_DETERMIN_YN");
            
            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_04);
        },
        initCombo_44 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "BOM_PRICE_ADJUST_TYPE");
            
            combo.init.setData(this.data_17);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_45 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "BFO_DOC_JO_YN");
            
            combo.init.setData(this.data_18);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_46 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "PKRIN_EXP_TARGET_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_47 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "SEND_ORGIN_TARGET");
            
            combo.init.setData(this.data_19);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_48 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "SEND_CO_TIMING");
            
            combo.init.setData(this.data_20);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_49 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "PASS_MAIL_SEND_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_50 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "ORIGIN_DECSN_TYPE");
            
            combo.init.setData(this.data_21);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_51 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "RVC_REQUIRED_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_52 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "ORIGIN_TRACE_MTH");
            
            combo.init.setData(this.data_22);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_53 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "INV_IN_PRICE_TYP");
            
            combo.init.setData(this.data_23);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_54 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "INV_IN_PRICE_MTH");
            
            combo.init.setData(this.data_24);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_55 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "EMAIL_AUTH_INTG_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_56 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "BOM_DIRC_CTRL_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        //기준환율통화
        initCombo_57 : function() {
            var obj_09 = combo.getObject("SM7001_01_form_02", "STD_EXCRATE_CURRENCY");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CURRENCY"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            combo.init.setEditable(true);
            
            combo.create(obj_09);
        },
        initCombo_58 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "INV_BOM_ISSUE_YN");
            
            combo.init.setData(this.data_25);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_59 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "INV_INQTY_ADJUST_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_60 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "INV_PO_DECLARE_TYPE");
            
            combo.init.setData(this.data_26);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_61 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "OVER_COPER_CO_TRANS");
            
            combo.init.setData(this.data_27);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_62 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "INV_EXT_ISSUE_APY_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_63 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "INV_SD_DECLARE_TYPE");
            
            combo.init.setData(this.data_26);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_64 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "INV_IMP_ADJUST_TYPE");
            
            combo.init.setData(this.data_26);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_65 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "PE_RUN_TARGET");
            
            combo.init.setData(this.data_28);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_66 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "CR_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_67 : function() {
            var obj_01 = combo.getObject("SM7001_01_form_02", "REMOTE_SERVICE_YN");
            
            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header_1 : [    // Field ID               | Field Name                                     | Width     |  Align      | hAlign   | Sort Y/N   | hidden  | edit type   | chbox    | style | rowspan | colspan
            ["CUSTOMER_CD"              ,resource.getMessage("TXT_CUSTOMER_CODE_HD")        ,60     ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["CUSTOMER_NAME"            ,resource.getMessage("TXT_CUSTOMER_NAME_HD")        ,80     ,"left"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["HUB_CERT_ID"              ,resource.getMessage("CNFIT, ID")                   ,120    ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["ADDRESS"                  ,resource.getMessage("ADDRS")                       ,200    ,"left"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["PHONE_NO"                 ,resource.getMessage("TELNM")                       ,150    ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0]
        ],
        header_2 : [
            ["CUSTOMER_CD"              ,resource.getMessage("CNFDO, CERIS, CSTMR")         ,100    ,"center"    ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,3],
            ["SALES_CUSTOMER_CD"        ,resource.getMessage("SALES, CSTMR")                ,100    ,"center"    ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,3]
        ],
        header_2_1 : [
            ["CUSTOMER_CD"              ,resource.getMessage("TXT_CUSTOMER_CODE_HD")        ,100    ,"center"    ,"center"    ,true       ,false     ,null         ,null     ,null],
            ["CUSTOMER_NAME"            ,resource.getMessage("TXT_CUSTOMER_NAME_HD")        ,200    ,"left"      ,"center"    ,true       ,false     ,null         ,null     ,null],
            ["HUB_CERT_ID"              ,resource.getMessage("CNFIT, ID")                   ,100    ,"center"    ,"center"    ,true       ,false     ,null         ,null     ,null],
            ["SALES_CUSTOMER_CD"        ,resource.getMessage("TXT_CUSTOMER_CODE_HD")        ,100    ,"center"    ,"center"    ,true       ,false     ,null         ,null     ,null],
            ["SALES_CUSTOMER_NAME"      ,resource.getMessage("TXT_CUSTOMER_NAME_HD")        ,200    ,"left"      ,"center"    ,true       ,false     ,null         ,null     ,null],
            ["DIVISION_NAME"            ,resource.getMessage("TXT_SALES_DIVISION")          ,200    ,"left"      ,"center"    ,true       ,false     ,null         ,null     ,null]
        ],
        header_3 : [
            ["SIGNATURE_NAME"           ,resource.getMessage("TXT_SIGN_USER_NM")            ,250    ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["POSITION"                 ,resource.getMessage("POSIT")                       ,170    ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["PHONE_NO"                 ,resource.getMessage("TELNM")                       ,250    ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["EMAIL"                    ,resource.getMessage("TXT_EMAIL")                   ,400    ,"left"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["START_DATE"               ,resource.getMessage("TXT_FROM_DATE")               ,100    ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,{format:"date"}],
            ["END_DATE"                 ,resource.getMessage("TXT_TO_DATE")                 ,100    ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,{format:"date"}]
        ],
        header_4 : [
            ["COMPANY_CD"        , resource.getMessage("CONPY,CODE")          , 100, "center", "center", true, false, null, null, {format:"function", pid:"SM7001_01", name:'viewPretCompany'}, 0, 0],
            ["COMPANY_NAME"      , resource.getMessage("CONPY,NAME")          , 120, "left"  , "center", true, false, null, null, null, 0, 0],
            ["BUSINESS_NO"       , resource.getMessage("TXT_BUSINESSNO")      , 120, "center", "center", true, true , null, null, null, 0, 0],
            ["PRESIDENT_NAME"    , resource.getMessage("TXT_PRESIDENT_NAME")  , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["ADDRESS1"          , resource.getMessage("ADDRS")               , 260, "left"  , "center", true, false, null, null, null, 0, 0],
            ["OFFICER_NAME"      , resource.getMessage("THPIC")               , 120, "center", "center", true, false, null, null, null, 0, 0],
            ["OFFICER_PHONE_NO"  , resource.getMessage("THPIC, TELNM")        , 120, "center", "center", true, false, null, null, null, 0, 0],
            ["OFFICER_EMAIL"     , resource.getMessage("THPIC, EMAIL")        , 120, "left"  , "center", true, false, null, null, null, 0, 0]
        ],
        header_5 : [
            ["DIVISION_NAME"            ,resource.getMessage("DIVIS")         , 150   , "left"      ,"center"    ,true       ,false     ,null         ,null     ,null, 0, 0],
            ["HUB_CERT_ID"              ,resource.getMessage("CNFIT, ID")     , 100   , "center"    ,"center"    ,true       ,false     ,{type:'text'}         ,null     ,null]
        ],
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("SM7001_01_form_03");
            var dg_1 = grid.getObject("SM7001_01_grid_01");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/fm/sm/SM7001_01/selectHubCertCustomerList");
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
            var dg_2 = grid.getObject("SM7001_01_grid_02");
            
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(true);
            grid.init.setMergeData(["CUSTOMER_CD", "CUSTOMER_NAME", "HUB_CERT_ID"], "CUSTOMER_CD");
            grid.init.setFitColumns(true);
            
            grid.event.setOnDblClickRow(dg_2);
            
            Theme.defaultGrid(dg_2, this.header_2, this.header_2_1);
        },
        initGrid_3 : function() {
            var dg_3 = grid.getObject("SM7001_01_grid_03");
            
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            
            grid.event.setOnDblClickRow(dg_3);
            
            Theme.defaultGrid(dg_3, this.header_3);
        },
        initGrid_4 : function() {
        	var params = form.handle.getElementsParam("SM7001_01_form_01");
            var dg_4 = grid.getObject("SM7001_01_grid_04");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/fm/sm/sm7001_01/selectCompnayGroupList");
            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            
            Theme.defaultGrid(dg_4, this.header_4);
        },
        initGrid_5 : function() {
        	var params = new Object();
        	params.COMPANY_CD = SESSION.COMPANY_CD;
            var dg_5 = grid.getObject("SM7001_01_grid_05");
            
            grid.init.setURL("/fm/sm/sm7002_01/selectDivisionList");
            grid.init.setQueryParams(params);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            grid.init.setRownumbers(false);
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowFooter(true);
            
            grid.event.setOnClickCell(dg_5);
            grid.event.setOnCellEdit(dg_5);
            
            Theme.defaultGrid(dg_5, this.header_5);
        }
    },
    // 이벤트 처리
    event : {
        selectAfterUI : function(data) {
            form.handle.loadData("SM7001_01_form_01", data);
            form.handle.setValue("SM7001_01_form_03", "HUB_CERT_ID", data.HUB_CERT_ID);
            form.handle.setValue("SM7001_01_form_03", "TOMS_HUB_ID", data.TOMS_HUB_ID);
            
            if(oUtil.isNull(data.CO_CERTIFIED_EXPORTER_NO)) {
            	form.handle.readonly("SM7001_01_form_01", "CO_CERTIFIED_EXPORTER_NO", true);
            	form.handle.addClass("SM7001_01_form_01", "CO_CERTIFIED_EXPORTER_NO", "input_readOnly");
            	form.handle.readonly("SM7001_01_form_01", "CO_CERTIFIED_EXPORTER_YN", true);
            }
            
            SM7001_01.combobox.initCombo_1();
            SM7001_01.control.selectStempCIImg("1");
            SM7001_01.control.selectStempCIImg("2");
        },
        selectSysconfigAfterUI : function(data) {
            form.handle.loadData("SM7001_01_form_02", data);
            
            if(data.CRTCT_GOOD_YN == "Y") {form.handle.setChecked("SM7001_01_form_02", "CRTCT_GOOD_YN", true);}
            if(data.CRTCT_IMP_YN == "Y") {form.handle.setChecked("SM7001_01_form_02", "CRTCT_IMP_YN", true);}
            if(data.CRTCT_SUBITEM_YN == "Y") {form.handle.setChecked("SM7001_01_form_02", "CRTCT_SUBITEM_YN", true);}
            if(data.CRTCT_EXPORT_YN == "Y") {form.handle.setChecked("SM7001_01_form_02", "CRTCT_EXPORT_YN", true);}
            if(data.CRTCT_HS_YN == "Y") {form.handle.setChecked("SM7001_01_form_02", "CRTCT_HS_YN", true);}
            
            SM7001_01.combobox.initCombo_2();
            SM7001_01.combobox.initCombo_3();
            SM7001_01.combobox.initCombo_4();
            SM7001_01.combobox.initCombo_5();
            SM7001_01.combobox.initCombo_6();
            SM7001_01.combobox.initCombo_7();
            SM7001_01.combobox.initCombo_8();
            SM7001_01.combobox.initCombo_9();
            SM7001_01.combobox.initCombo_10();
            SM7001_01.combobox.initCombo_11();
            SM7001_01.combobox.initCombo_12();
            SM7001_01.combobox.initCombo_13();
            SM7001_01.combobox.initCombo_14();
            SM7001_01.combobox.initCombo_15();
            SM7001_01.combobox.initCombo_16();
            SM7001_01.combobox.initCombo_17();
            SM7001_01.combobox.initCombo_18();
            SM7001_01.combobox.initCombo_19();
            SM7001_01.combobox.initCombo_20();
            SM7001_01.combobox.initCombo_21();
            SM7001_01.combobox.initCombo_22();
            SM7001_01.combobox.initCombo_23();
            SM7001_01.combobox.initCombo_24();
            SM7001_01.combobox.initCombo_25();
            SM7001_01.combobox.initCombo_26();
            SM7001_01.combobox.initCombo_27();
            SM7001_01.combobox.initCombo_28();
            SM7001_01.combobox.initCombo_29();
            SM7001_01.combobox.initCombo_30();
            SM7001_01.combobox.initCombo_31();
            SM7001_01.combobox.initCombo_32();
            SM7001_01.combobox.initCombo_33();
            SM7001_01.combobox.initCombo_34();
            SM7001_01.combobox.initCombo_35();
            SM7001_01.combobox.initCombo_36();
            SM7001_01.combobox.initCombo_37();
            SM7001_01.combobox.initCombo_38();
            SM7001_01.combobox.initCombo_39();
            SM7001_01.combobox.initCombo_40();
            SM7001_01.combobox.initCombo_41();
            SM7001_01.combobox.initCombo_42();
            SM7001_01.combobox.initCombo_43();
            SM7001_01.combobox.initCombo_44();
            SM7001_01.combobox.initCombo_45();
            SM7001_01.combobox.initCombo_46();
            SM7001_01.combobox.initCombo_47();
            SM7001_01.combobox.initCombo_48();
            SM7001_01.combobox.initCombo_49();
            SM7001_01.combobox.initCombo_50();
            SM7001_01.combobox.initCombo_51();
            SM7001_01.combobox.initCombo_52();
            SM7001_01.combobox.initCombo_53();
            SM7001_01.combobox.initCombo_54();
            SM7001_01.combobox.initCombo_55();
            SM7001_01.combobox.initCombo_56();
            SM7001_01.combobox.initCombo_57();
            SM7001_01.combobox.initCombo_58();
            SM7001_01.combobox.initCombo_59();
            SM7001_01.combobox.initCombo_60();
            SM7001_01.combobox.initCombo_61();
            SM7001_01.combobox.initCombo_62();
            SM7001_01.combobox.initCombo_63();
            SM7001_01.combobox.initCombo_64();
            SM7001_01.combobox.initCombo_65();
            SM7001_01.combobox.initCombo_66();
            SM7001_01.combobox.initCombo_67();
        },
        updateAfterUI : function(data) {
            if (form.handle.getValue("SM7001_01_form_01", "flag") == "insert") {
                form.handle.setValue("SM7001_01_form_01", "flag", "update");
            }
            SM7001_01.ui.updateChangeUI();
            SM7001_01.control.selectStempCIImg("1");
            SM7001_01.control.selectStempCIImg("2");
        },
        updateSysconfigOptionInfoAfterUI : function(data) {
            if (form.handle.getValue("SM7001_01_form_02", "flag") == "insert") {
            form.handle.setValue("SM7001_01_form_02", "flag", "update");
            }
            
            SM7001_01.ui.updateChangeUI();
        },
        updateCompanyCertIDAfterUI : function(data) {
            if (form.handle.getValue("SM7001_01_form_03", "flag") == "insert") {
                form.handle.setValue("SM7001_01_form_03", "flag", "update");
            }
            
            SM7001_01.ui.updateChangeUI();
        },
        deleteAfterUI : function(data) {
            var dl_1 = dialog.getObject("SM7001_01_dailog_01");
            
            dialog.handle.close(dl_1);
        },
        onDblClick_SM7001_01_grid_01 : function(rowData) {
            SM7001_01.dialog.openDialog_1('update');
        },
        onDblClick_SM7001_01_grid_02 : function(rowData) {
            SM7001_01.dialog.openDialog_2('update');
        },
        onDblClick_SM7001_01_grid_03 : function(rowData) {
            SM7001_01.dialog.openDialog_3('update');
        },
        updateCompnayGroupCd : function() {
        	SM7001_01.control.selectCompnayGroupList();
        }
    },
    // 업무구현
    control : {
        selectCompanyInfo : function() {
            var obj = form.getObject("SM7001_01_form_01");
            
            form.init.setURL("/fm/sm/sm7001_01/selectCompanyInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        updateCompanyInfo : function() {
            if (!form.handle.isValidate("SM7001_01_form_01")) return;
            
            var vFlag = form.handle.getValue("SM7001_01_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            var confMsg = "";
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7001_01_form_01");
                    
                    if (form.handle.getValue("SM7001_01_form_01", "flag") == "insert") {
                        form.init.setURL("/fm/sm/sm7001_01/insertCompanyInfo");
                    }
                    if (form.handle.getValue("SM7001_01_form_01", "flag") == "update") {
                        form.init.setURL("/fm/sm/sm7001_01/updateCompanyInfo");
                    }
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        deleteCompanyInfo : function() {
            var vFlag = form.handle.getValue("SM7001_01_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7001_01_form_01");
                    
                    form.init.setURL("/fm/sm/sm7001_01/deleteCompanyInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        selectCompnayGroupList : function() {
            var params = form.handle.getElementsParam("SM7001_01_form_01");
            var dg_1 = grid.getObject("SM7001_01_grid_04");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7001_01/selectCompnayGroupList", params);
        },
        validate : function(pType) {
            var vCompanyCd = form.handle.getValue("SM7001_01_form_01", "COMPANY_CD");
            
            if (oUtil.isNull(vCompanyCd)) {
                alert(resource.getMessage("MSG_NOT_FOUND_COMPANY_CD"));
                return false;
            }
            
            return true;
        },
        selectSysconfigOptionInfo : function() {
            var obj = form.getObject("SM7001_01_form_02");
            
            form.init.setURL("/fm/sm/sm7001_01/selectSysconfigOptionInfo");
            form.init.setCallBackFunction("selectSysconfigAfterUI");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        updateSysconfigOptionInfo : function() {
            if (!form.handle.isValidate("SM7001_01_form_02")) return;
            
            var confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7001_01_form_02");
                    
                    if(form.handle.isChecked("SM7001_01_form_02", "CRTCT_GOOD_YN")) {
	                    form.handle.setValue("SM7001_01_form_02", "CRTCT_GOOD_YN", "Y");
                    } else {
                    	form.handle.setValue("SM7001_01_form_02", "CRTCT_GOOD_YN", "N");
                    }
                    
                    if(form.handle.isChecked("SM7001_01_form_02", "CRTCT_IMP_YN")) {
	                    form.handle.setValue("SM7001_01_form_02", "CRTCT_IMP_YN", "Y");
            		} else {
            			form.handle.setValue("SM7001_01_form_02", "CRTCT_IMP_YN", "N");
            		}
                    
                    if(form.handle.isChecked("SM7001_01_form_02", "CRTCT_HS_YN")) {
	                    form.handle.setValue("SM7001_01_form_02", "CRTCT_HS_YN", "Y");
            		} else {
            			form.handle.setValue("SM7001_01_form_02", "CRTCT_HS_YN", "N");
            		}
                    
                    if(form.handle.isChecked("SM7001_01_form_02", "CRTCT_SUBITEM_YN")) {
	                    form.handle.setValue("SM7001_01_form_02", "CRTCT_SUBITEM_YN", "Y");
            		} else {
            			form.handle.setValue("SM7001_01_form_02", "CRTCT_SUBITEM_YN", "N");
            		}
                    
                    if(form.handle.isChecked("SM7001_01_form_02", "CRTCT_EXPORT_YN")) {
	                    form.handle.setValue("SM7001_01_form_02", "CRTCT_EXPORT_YN", "Y");
            		} else {
            			form.handle.setValue("SM7001_01_form_02", "CRTCT_EXPORT_YN", "N");
            		}
                    
                    form.init.setURL("/fm/sm/sm7001_01/updateSysconfigOptionInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateSysconfigOptionInfoAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        updateCompanyCertIDInfo : function() {
            if (!form.handle.isValidate("SM7001_01_form_03")) return;
            
            var dg_5 = grid.getObject("SM7001_01_grid_05");
            var confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            
            dg_5.datagrid('endCellEdit');
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                	var rows = grid.handle.getAllRows(dg_5);
                	var obj = form.getObject("SM7001_01_form_03");
                    
                    form.handle.setValue("SM7001_01_form_03", "gridData", grid.util.convertJson2Rows(rows));
                    
                    form.init.setURL("/fm/sm/sm7001_01/updateCompanyCertIDInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateCompanyCertIDAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        selectHubCertCustomerList : function() {
            var dg_1 = grid.getObject("SM7001_01_grid_01");
            var params = form.handle.getElementsParam("SM7001_01_form_03");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/SM7001_01/selectHubCertCustomerList", params);
        },
        selectHubSalesCustomerList : function() {
            var dg_2 = grid.getObject("SM7001_01_grid_02");
            var params = form.handle.getElementsParam("SM7001_01_form_03");
            
            grid.handle.sendRedirect(dg_2, "/fm/sm/SM7001_01/selectHubSalesCustomerList", params);
        },
        selectHubSignatureList : function() {
            var dg_3 = grid.getObject("SM7001_01_grid_03");
            var params = form.handle.getElementsParam("SM7001_01_form_03");
            
            grid.handle.sendRedirect(dg_3, "/fm/sm/SM7001_01/selectHubSignatureList", params);
        },
        deleteCompnayGroupCd : function() {
        	var dg_1 = grid.getObject("SM7001_01_grid_04");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	form.handle.setValue("SM7001_01_form_04", "COMPANY_CD", rowData.COMPANY_CD);
    		form.handle.setValue("SM7001_01_form_04", "PARENT_COMPANY_CD", "");
    		
    		this.updateCompnayGroupCd();
        },
        updateCompnayGroupCd : function() {
            var obj = form.getObject("SM7001_01_form_04");
            
            form.init.setURL("/fm/sm/sm7001_01/updateCompnayGroupCd");
            form.init.setCallBackFunction("updateCompnayGroupCd");
            
            form.submit(obj);
        },
        selectStempCIImg : function(data) {
        	var obj = form.getObject("SM7001_01_form_01", "SM7001_01_img_0"+data);
        	var src = "/fm/sm/SM7001_01/selectStempCIImg/"+data+"?" +Date.now(); // data = 1:Logo, 2:Stamp
        	
        	if(!oUtil.isNull(obj)) obj.removeAttr("src");
        	
        	obj.removeAttr("src").attr("src", src);
        }
    },
    // 다이얼로그 구현
    dialog : {
        openDialog_1 : function(flag) {
            var dg_1 = grid.getObject("SM7001_01_grid_01");
            var params = new Object();
            
            if (flag == "update" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var dl_1 = dialog.getObject("SM7001_02_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("SM7001_02"));
            dialog.init.setWidth(1024);
            dialog.init.setHeight(260);
            dialog.init.setURL("/fm/sm/sm7001_02");
            dialog.init.setResizable(false);
            // 파라메터 지정
            if(flag == "insert") {
                params.COMPANY_CD = form.handle.getValue("SM7001_01_form_03", "COMPANY_CD");
            } else if(flag == "update") {
                params = grid.handle.getSelectedRowData(dg_1);
            }
            params.flag = flag;
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        },
        openDialog_2 : function(flag) {
            var dg_1 = grid.getObject("SM7001_01_grid_02");
            var params = new Object();
            
            if (flag == "update" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var dl_2 = dialog.getObject("SM7001_03_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("SM7001_03"));
            dialog.init.setWidth(1024);
            dialog.init.setHeight(270);
            dialog.init.setURL("/fm/sm/sm7001_03");
            dialog.init.setResizable(false);
            if(flag == "insert") {
                params.COMPANY_CD = form.handle.getValue("SM7001_01_form_03", "COMPANY_CD");
            } else if(flag == "update") {
                params = grid.handle.getSelectedRowData(dg_1);
            }
            params.flag = flag;
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_2);
        },
        openDialog_3 : function(flag) {
            var dg_3 = grid.getObject("SM7001_01_grid_03");
            var params = new Object();
            
            if (flag == "update" && !grid.handle.isSelected(dg_3)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var dl_3 = dialog.getObject("SM7001_04_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("SM7001_04"));
            dialog.init.setWidth(1024);
            dialog.init.setHeight(240);
            dialog.init.setURL("/fm/sm/sm7001_04");
            dialog.init.setResizable(false);
            if(flag == "insert") {
                params.COMPANY_CD = form.handle.getValue("SM7001_01_form_03", "COMPANY_CD");
                params.USER_ID = form.handle.getValue("SM7001_01_form_03", "USER_ID");
            } else if(flag == "update") {
                params = grid.handle.getSelectedRowData(dg_3);
                params.USER_ID = form.handle.getValue("SM7001_01_form_03", "USER_ID");
            }
            params.flag = flag;
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_3);
        },
        openDialog_4 : function() {
      		var params = new Object();
    		var dg_1 = dialog.getObject("MMA032_01_dailog_01");
      		
    		params.TARGET_PID = "SM7001_01";
      		
      		dialog.init.setWidth(900);
      		dialog.init.setHeight(500);
      		dialog.init.setURL("/mm/pop/mmA032_01");
      		dialog.init.setQueryParams(params);
      		      		
    		dialog.open(dg_1);
    	},
    	callByMMA032_01 : function(datas) {
    		// 회사 그룹에 추가
    		form.handle.setValue("SM7001_01_form_04", "COMPANY_CD", datas.COMPANY_CD);
    		form.handle.setValue("SM7001_01_form_04", "PARENT_COMPANY_CD", SESSION.COMPANY_CD);
    		
    		SM7001_01.control.updateCompnayGroupCd();
    		
    		var dg_1 = dialog.getObject("MMA032_01_dailog_01");
    		dialog.handle.close(dg_1);
    	}
    },
    // 화면 UI 변경
    ui : {
        insertChangeUI : function() {
            ;
        },
        updateChangeUI : function() {
            ;
        },
        viewChangeUI : function() {
            form.util.setVisible("SM7001_01_div_01", false);
            form.handle.closeAll("SM7001_01_form_01");
            
        },
        divMove : function(num) {
        	var obj = tab.getObject("SM7001_01_tabs_02");
			
			tab.handle.select(obj, (num-1));
			
			if(num == 1) {
				form.util.addClass("SM7001_01_divMove01", "on");
				form.util.removeClass("SM7001_01_divMove02", "on");
				form.util.removeClass("SM7001_01_divMove03", "on");
			} else if(num == 2) {
				form.util.removeClass("SM7001_01_divMove01", "on");
				form.util.addClass("SM7001_01_divMove02", "on");
				form.util.removeClass("SM7001_01_divMove03", "on");
				
				SM7001_01.control.selectHubSalesCustomerList();
			} else if(num == 3) {
				form.util.removeClass("SM7001_01_divMove01", "on");
				form.util.removeClass("SM7001_01_divMove02", "on");
				form.util.addClass("SM7001_01_divMove03", "on");
				
				SM7001_01.control.selectHubSignatureList();
			}
            
            SM7001_01.init.tabNumber = num;
        },
        divTabMove : function(num) {
        	var obj = tab.getObject("SM7001_01_tabs_01");
			
			tab.handle.select(obj, (num-1));
			
			if(num == 1) {
				form.util.addClass("SM7001_01_tabMove01", "on");
				form.util.removeClass("SM7001_01_tabMove02", "on");
				form.util.removeClass("SM7001_01_tabMove03", "on");
			} else if(num == 2) {
				form.util.addClass("SM7001_01_tabMove02", "on");
				form.util.removeClass("SM7001_01_tabMove01", "on");
				form.util.removeClass("SM7001_01_tabMove03", "on");
				
				SM7001_01.control.selectSysconfigOptionInfo();
			} else if(num == 3) {
				form.util.addClass("SM7001_01_tabMove03", "on");
				form.util.removeClass("SM7001_01_tabMove01", "on");
				form.util.removeClass("SM7001_01_tabMove02", "on");
			}
        },
        viewPretCompany : function(val, row, idx) {
        	if(!oUtil.isNull(row.PARENT_COMPANY_YN)) {
    			if(row.PARENT_COMPANY_YN == "Y") {
    				return "<b>" + val + "("+resource.getMessage("RPSNT_CONPY")+")</b>";
    			} else {
    				return val;
    			}
    		}
    		
    		return val;
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		var comCode = form.handle.getValue("SM7001_01_form_01","COMPANY_CD");
    		var parentCode = form.handle.getValue("SM7001_01_form_01","PARENT_COMPANY_CD");
    		var authGroup = form.handle.getValue("SM7001_01_form_01","AUTH_GROUP");
    		
    		if(SESSION.USER_ID == "fta") {
    			if(comCode == parentCode) {
    				form.util.setVisible("SM7001_01_div_10", true);
        		}
    			form.util.setVisible("SM7001_01_div_11", true);
    			form.util.setVisible("SM7001_01_div_12", true);
    			form.util.setVisible("SM7001_01_tabMove02", true);
    			form.util.setVisible("SM7001_01_tabMove03", true);
    		} else if(authGroup == "master") {
    			form.util.setVisible("SM7001_01_div_11", true);
    			form.util.setVisible("SM7001_01_tabMove02", true);
    			form.util.setVisible("SM7001_01_tabMove03", true);
    			
    			form.handle.readonly("SM7001_01_form_02", "CLOUD_SERVER_URL", true);
                form.handle.addClass("SM7001_01_form_02", "CLOUD_SERVER_URL", "input_readOnly");
    		}
    		
    	}
    }
};
