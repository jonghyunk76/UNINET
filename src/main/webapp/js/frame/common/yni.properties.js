var DBMS_TYPE = "postgresql"; 			// 데이터 베이스 타입(oracle, mssql, postgresql)
var PROGRAM_ID_NUMBER = 9; 			// Program ID 자릿수
var DEFAULT_LANGUAGE = "KR";		// 화면에 보여질 메시지의 기본언어
var NATION_DATE_VIEW = "KR"; 		// 화면에 보여질 날짜 표현 국가(US:mmddyyyy, KR:yyyymmdd, MX:ddmmyyyy, VN:ddmmyyyy)
var NATION_DATE_DB = "KR"; 			// DB에 저장될 날짜 표현 국가(US:mmddyyyy, KR:yyyymmdd, MX:ddmmyyyy)
var SEPERATOR_OF_DATE = "-"; 		// 일자 구분 문자
var TOP_SYS_ID = "RS";				// 로그인화면 시스템 ID(FTA:IF, 통관:CC,  중계서버:RS)
var TAB_MAX_SIZE = 15;				// 화면에 표시할 최대 탭갯수
var CURRENT_REQUEST_URL;            // 최근 요청경로
var MAX_MULTI_COUNT = 100;          // 멀티검색 최대 갯수
// 다국어 적용 시 변경할 문자영을 지정(Json타입으로 지정할 것)
// 주의1> 서버측 app_resource.properties에도 동일하게 추가할 것
// 주의2> 다국어 추가시 해당 언어별로 추가할 것
var CONVERSION_WORD_KR = null;
var CONVERSION_WORD_EN = null;
var CONVERSION_WORD_ES = null;
var CONVERSION_WORD_VN = null;
// 품목의 추가코드를 보여줄지 여부(한일튜브, 파트넘버, ECN코드)
var PART_NUM_VIEW_YN = "N";

//////////////////////////////////////////////////////////////////////////////
//전역변수
//////////////////////////////////////////////////////////////////////////////

//데이터그리드의 절대적 컬럼 넓이 설정 
var F_H_20091115020000 = {
		 AMOUNT : {width:120, align:'right', format:{format:"amount"}}
		,APPLY_DATE : {width:100, format:{format:"date"}, align:"center"}
		,APPLY_FROM_DATE : {width:95, align:'center', format:{format:'date'}}
		,APPLY_TO_DATE : {width:105, align:'center', format:{format:'date'}}
		,BUFFER_DE_MINIMIS_RATE : {width:130, align:'right', format:{format:'rate'}}
		,BUFFER_RVC_RATE : {width:110, align:'right', format:{format:'rate'}}
		,C_TAX : {width:130, align:'right', format:{format:"amount"}}
		,CFR_RATE : {width:100, align:'right', format:{format:'rate'}}
		,CFS_FEE : {width:130, align:'right', format:{format:"amount"}}
		,CIF_RATE : {width:100, align:'right', format:{format:'rate'}}
		,CIP_RATE : {width:100, align:'right', format:{format:'rate'}}
		,CO_DOC_NO : {width:160}
		,CL_CO_DOC_NO : {width:160}
		,CO_DOC_TYPE : {width:80, align:"center"}
		,CO_DOC_TYPE_NAME : {width:100, align:"center"}
		,CO_ISSUE_TYPE : {width:80, align:"center"}
		,CO_ISSUE_TYPE_NAME : {width:110, align:"center"}
		,CPT_RATE : {width:100, align:'right', format:{format:'rate'}}
		//,CREATE_DATE : {width:130, align:'center', format:{format:"date"}}
		,CUSTOMER_CD : {width:100}
		,CUSTOMER_NAME : {width:150}
		,D_FEE : {width:130, align:'right', format:{format:"amount"}}
		,DATA_LENGTH : {width:150, align:'right', format:{format:'int'}}
		,DDP_RATE : {width:100, align:'right', format:{format:'rate'}}
		,DDU_RATE : {width:100, align:'right', format:{format:'rate'}}
		,DELETE_YN : {width:100, align:"center"}
		,EFFECTIVE_DATE : {width:100, format:{format:"date"}, align:"center"}
		,END_AMT : {width:150, align:'right', format:{format:'amount'}}
		,END_DATE : {width:100, align:'center', format:{format:"date"}}
		,END_QTY : {width:100, align:'right', format:{format:'quantity'}}
		,ERROR_REASON : {width:300}
		,ETC_COST1 : {width:130, align:'right', format:{format:"amount"}}
		,ETC_COST2 : {width:130, align:'right', format:{format:"amount"}}
		,ETC_COST3 : {width:130, align:'right', format:{format:"amount"}}
		,ETC_COST4 : {width:130, align:'right', format:{format:"amount"}}
		,ETC_COST5 : {width:130, align:'right', format:{format:"amount"}}
		,EXCHANGE_RATE : {width:120, align:'right', format:{format:'exchange'}}
		,EXCHANGE_RATE_ACCUM : {width:120, align:'right', format:{format:'exchange'}}
		,EXCHANGE_RATE_END : {width:120, align:'right', format:{format:'exchange'}}
		,EXPENSE_RATE : {width:120, align:'right', format:{format:"rate"}}
		,EXPORT_FLAG : {width:70}
		,EXPORT_FLAG_NAME : {width:100}
		,EXT_AIR_FREIGHT_CHARGE : {width:130, align:'right', format:{format:"amount"}}
		,EXT_FREIGHT_CHARGE : {width:130, align:'right', format:{format:"amount"}}
		,EXTRA_IN_AMT : {width:150, align:'right', format:{format:'amount'}}
		,EXTRA_IN_QTY : {width:100, align:'right', format:{format:'quantity'}}
		,EXTRA_ISSUE_AMT : {width:150, align:'right', format:{format:'amount'}}
		,EXTRA_ISSUE_QTY : {width:100, align:'right', format:{format:'quantity'}}
		,EXW_RATE : {width:100, align:'right', format:{format:'rate'}}
		,EXWORK_UNIT_PRICE : {width:90, align:'right', format:{format:"amount"}}
		,FOB_RATE : {width:100, align:'right', format:{format:'rate'}}
		,FOB_UNIT_PRICE : {width:90, align:'right', format:{format:"amount"}}
		,FROM_DATE : {width:100, align:'center', format:{format:'date'}}
		,FTA_CD : {width:110, align:"center"}
		,FTA_CO_YN : {width:120}
		,FTA_CODE : {width:110, align:"center"}
		,FTA_NAME : {width:130, align:"left"}
		,FTA_NAME_ENG : {width:120}
		,FTA_UNIT_AMT : {width:100}
		,FTA_UNIT_PRICE : {width:80}
		,HS_CODE : {width:100, align:"center"}
		,IN_AMT : {width:150, align:'right', format:{format:'amount'}}
		,IN_QTY : {width:100, align:'right', format:{format:'quantity'}}
		,IN_UNIT_PRICE : {width:90, align:'right', format:{format:"amount"}}
		,INIT_AMT : {width:150, align:'right', format:{format:'amount'}}
		,INIT_QTY : {width:100, align:'right', format:{format:'quantity'}}
		,INSURANCE : {width:130, align:'right', format:{format:"amount"}}
		,INV_ROT_DAY : {width:150, align:'right', format:{format:'int'}}
		,INVOICE_DATE : {width:100, align:'center', format:{format:"date"}}
		,INVOICE_NO : {width:130}
		,ISSUE_AMT : {width:150, align:'right', format:{format:'amount'}}
		,ISSUE_DATE : {width:100, align:'center', format:{format:'date'}}
		,ISSUE_QTY : {width:100, align:'right', format:{format:"quantity"}}
		,ISSUE_UNIT_PRICE : {width:90, align:'right', format:{format:"amount"}}
		,ITEM_CD : {width:130, align:"center"}
		,ITEM_NAME : {width:180, align:"left"}
		,ITEM_NAME_ENG : {width:180, align:"left"}
		,ITEM_TYPE_NAME : {width:100}
		,LABOR_RATE : {width:150, align:'right', format:{format:"rate"}}
		,LAST_PURCHASE_DATE : {width:180, align:'center', format:{format:"date"}}
		,MATERIAL_COST : {width:120, align:'right', format:{format:'amount'}}
		,MATERIAL_RATE : {width:140, align:'right', format:{format:"rate"}}
		,MODIFY_REQUEST_DATE : {width:100, align:'center', format:{format:'date'}}
		,NET_COST : {width:120, align:'right', format:{format:'amount'}}
		,NON_ORIGIN_COST : {width:105, align:'right', format:{format:"amount"}}
		,NONORIGIN_UNIT_AMT : {width:100}
		,ORIGIN_COST : {width:80, align:'right', format:{format:"amount"}}
		,ORIGIN_UNIT_AMT : {width:100}
		,OYB_REQ_DATE : {width:100, align:'center', format:{format:'date'}}
		,PRODUCT_ITEM_CD : {width:130}
		,PRODUCT_ITEM_NAME : {width:180}
		,REQ_QTY : {width:100, align:'right', format:{format:'quantity'}}
		,RULE_CONTENTS : {width:110}
		,RVC_RATE : {width:95, align:"right"}
		,SALES_ADMIN_RATE : {width:140, align:'right', format:{format:"rate"}}
		,SALES_AMT : {width:100, align:'right', format:{format:'amount'}}
		,SALES_QTY : {width:100, align:'right', format:{format:'quantity'}}
		,SALES_UNIT_PRICE : {width:90}
		,SHIPPING_DATE : {width:100, align:'center', format:{format:"date"}}
		,SORT_NO : {width:60, align:'right', format:{format:"int"}}
		,STANDARD_COST_AMT : {width:90, align:'right', format:{format:"amount"}}
		,START_DATE : {width:100, align:'center', format:{format:"date"}}
		,STATUS_NAME : {width:80}
		,TO_DATE : {width:100, align:'center', format:{format:'date'}}
		,TOT_MATERIAL_COST : {width:100, align:'right', format:{format:"amount"}}
		,TRACE_TYPE : {width:100, align:"center"}
		,TRANS_FEE : {width:150, align:'right', format:{format:"amount"}}
		,TRANS_RATE : {width:150, align:'right', format:{format:"rate"}}
		,TXN_AMOUNT : {width:100, align:'right', format:{format:"amount"}}
		,TXN_DATE : {width:100, align:'center', format:{format:"date"}}
		,TXN_QTY : {width:100, align:'right', format:{format:"quantity"}}
		,UNIT : {width:60}
		,UNIT_COST : {width:100, align:'right', format:{format:'amount'}}
		,UNIT_PRICE : {width:100, align:'right', format:{format:"amount"}}
		,WFG_FEE : {width:130, align:'right', format:{format:"amount"}}
		,YYYYMM : {width:90, align:'center', format:{format:"month"}}
		,REFERENCE_ID : {width:150}
};

var F_D_76080120160605 = {
	 SI2011_02_dailog_01 : {width:500, height:280, rewidth:500, reheight:290}
     // 공통팝업 크기 설정
    ,MMA002_01_01_dailog_01 : {width:600, height:350}
    ,MMA015_01_01_dailog_01 : {width:600, height:350}
    ,MMA002_01_dailog_01 : {width:600, height:400}
    ,MMA004_01_dailog_01 : {width:1030, height:220}
    
    ,MMA005_01_dailog_01 : {width:800, height:450}
    ,MMA006_01_dailog_01 : {width:800, height:450}
    ,MMA007_01_dailog_01 : {width:800, height:450}
    ,MMA008_01_dailog_01 : {width:800, height:450}
    ,MMA009_01_dailog_01 : {width:800, height:450}
    ,MMA010_01_dailog_01 : {width:800, height:450}
    ,MMA011_01_dailog_01 : {width:800, height:450}
    ,MMA012_01_dailog_01 : {width:800, height:450}
    ,MMA013_01_dailog_01 : {width:800, height:450}
    ,MMA015_01_dailog_01 : {width:800, height:450}
    ,MMA016_01_dailog_01 : {width:1100, height:700}
    ,MMA024_01_dailog_01 : {width:800, height:450}
    ,MMA025_01_dailog_01 : {width:800, height:450}
    
    ,MMA017_01_dailog_01 : {width:1350, height:900}
    ,MMA018_01_dailog_01 : {width:800, height:160}
    ,MMA019_01_dailog_01 : {width:1050, height:900}
    ,MMA020_01_dailog_01 : {width:800, height:220}
    ,MMA021_01_dailog_01 : {width:1000, height:700}
    ,MMA022_01_dailog_01 : {width:1000, height:700}
    ,MMA023_01_dailog_01 : {width:1000, height:500}
    
    ,MMA026_01_dailog_01 : {width:400, height:300}
    ,MMA103_01_dialog_01 : {width:1030, height:610}
    ,MMA203_01_dailog_01 : {width:1030, height:900}
    
    ,FR1002_02_dailog_01 : {width:800, height:132}
};
