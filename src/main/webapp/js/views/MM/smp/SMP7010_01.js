/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7001_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7099_01.init.initComponent();
}

var SM7099_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {}
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {},
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {},
    // 업무구현
    control : {},
    // 다이얼로그 구현
    dialog : {
    	openDialog_1 : function() {
    		var dl_1 = dialog.getObject("MMA012_01_dailog_01");
    		
    		var params = {};
            params.TARGET_PID = "SM7099_01";
            params.COMPANY_CD = SESSION.COMPANY_CD
            params.CODE       = form.handle.getValue("SM7099_01_form_01", "NALADISA_CODE");   
    		
    		dialog.init.setWidth(600);
    		dialog.init.setHeight(400);
    		dialog.init.setURL("/mm/pop/mmA012_01");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("MMA012_01"));
    		dialog.open(dl_1);
    	},
    	callByMMA012_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA012_01_dailog_01");
    		
    		form.handle.setValue("SM7099_01_form_01", "NALADISA_CODE", datas.NALADISA_CODE);
    		form.handle.setValue("SM7099_01_form_01", "NALADISA_CODE_NAME", datas.NALADISA_CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	},
    	openDialog_2 : function() {
    		var dl_1 = dialog.getObject("MMA015_01_dailog_01");
    		
    		var params = {};
    		params.TARGET_PID  = "SM7099_01";
    		params.COMPANY_CD  = SESSION.COMPANY_CD
    		params.CATEGORY_CD = "APPLY_YN";
    		params.CODE        = form.handle.getValue("SM7099_01_form_01", "BASIC_CODE");   
    		
    		dialog.init.setWidth(600);
    		dialog.init.setHeight(400);
    		dialog.init.setURL("/mm/pop/mmA015_01");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("MMA015_01"));
    		dialog.open(dl_1);
    	},
    	callByMMA015_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA015_01_dailog_01");
    		
    		for (key in datas) {
    			console.log("key:"+key+",value:"+datas[key]);
    		}
    		
    		form.handle.setValue("SM7099_01_form_01", "BASIC_CODE", datas.CODE);
    		form.handle.setValue("SM7099_01_form_01", "BASIC_CODE_NAME", datas.CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	},
    	openDialog_3 : function() {
    		var dl_1 = dialog.getObject("MMA021_01_dailog_01");
    		
    		var params = {};
    		params.TARGET_PID  = "SM7099_01";
    		params.COMPANY_CD  = SESSION.COMPANY_CD;
    		params.USER_ID     = SESSION.USER_ID;
    		params.DIVISION_CD = "P1";
            params.VENDOR_CD   = "V1590";
            params.CO_DOC_NO   = "V1590-20160503-000001";
    		
    		dialog.init.setURL("/mm/pop/mmA021_01");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("MMA021_01"));
    		dialog.open(dl_1);
    	},
    	callByMMA021_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA021_01_dailog_01");
    		
    		for (key in datas) {
    			console.log("key:"+key+",value:"+datas[key]);
    		}
    		
    		form.handle.setValue("SM7099_01_form_01", "BASIC_CODE", datas.CODE);
    		form.handle.setValue("SM7099_01_form_01", "BASIC_CODE_NAME", datas.CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	},
    	//매출원장관리 상세
    	openDialog_4 : function() {
    		var dl_1 = dialog.getObject("DI3102_02_dailog_01");
    		
    		var params = {};
    		params.TARGET_PID  = "SM7099_01";
    		params.COMPANY_CD  = SESSION.COMPANY_CD;
    		params.USER_ID     = SESSION.USER_ID;
    		params.EXPORT_FLAG = "E";
    		
    		dialog.init.setURL("/fm/di/di3102_02");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("DI3102_02"));
    		dialog.open(dl_1);
    	},
    	callByDI3102_02 : function(datas) {
    		var dl_1 = dialog.getObject("DI3102_02_dailog_01");
    		
    		for (key in datas) {
    			console.log("key:"+key+",value:"+datas[key]);
    		}
    		
    		form.handle.setValue("SM7099_01_form_01", "CODE4", datas.CODE);
    		form.handle.setValue("SM7099_01_form_01", "NAME4", datas.CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	},
    	//제품군 선택
    	openDialog_5 : function() {
    		var dl_1 = dialog.getObject("MMA024_01_dailog_01");
    		
    		var params = {};
    		params.TARGET_PID  = "SM7099_01";
    		params.COMPANY_CD  = SESSION.COMPANY_CD;
    		
    		dialog.init.setURL("/mm/pop/mmA024_01");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("MMA024_01"));
    		dialog.open(dl_1);
    	},
    	callByMMA024_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA024_01_dailog_01");
    		
    		for (key in datas) {
    			console.log("key:"+key+",value:"+datas[key]);
    		}
    		
    		form.handle.setValue("SM7099_01_form_01", "CODE5", datas.PRODUCT_LINE_CD);
    		form.handle.setValue("SM7099_01_form_01", "NAME5", datas.CONTENT1);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	},
    	//NALADISA 코드
    	openDialog_6 : function() {
    		var dl_1 = dialog.getObject("MMA012_01_dailog_01");
    		
    		var params = {};
    		params.TARGET_PID  = "SM7099_01";
    		params.COMPANY_CD  = SESSION.COMPANY_CD;
    		
    		dialog.init.setURL("/mm/pop/mmA012_01");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("MMA012_01"));
    		dialog.open(dl_1);
    	},
    	callByMMA012_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA012_01_dailog_01");
    		
    		for (key in datas) {
    			console.log("key:"+key+",value:"+datas[key]);
    		}
    		
    		form.handle.setValue("SM7099_01_form_01", "CODE6", datas.NALADISA_CODE);
    		form.handle.setValue("SM7099_01_form_01", "NAME6", datas.NALADISA_CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	},
    	//한국 확인서 엑셀 업로드
    	openDialog_7 : function() {
    		var dl_1 = dialog.getObject("MMA022_01_dailog_01");
    		
    		var params = {};
    		params.TARGET_PID  = "SM7099_01";
    		params.COMPANY_CD  = SESSION.COMPANY_CD;
    		
    		dialog.init.setURL("/mm/pop/mmA022_01");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("MMA022_01"));
    		dialog.open(dl_1);
    	},
    	callByMMA22_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA022_01_dailog_01");
    		
    		for (key in datas) {
    			console.log("key:"+key+",value:"+datas[key]);
    		}
    		
    		form.handle.setValue("SM7099_01_form_01", "CODE7", datas.NALADISA_CODE);
    		form.handle.setValue("SM7099_01_form_01", "NAME7", datas.NALADISA_CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	},
    	//한국 증명서 등록
    	openDialog_8 : function() {
    		var dl_1 = dialog.getObject("MMA017_01_dailog_01");
    		
    		var params = {};
    		params.TARGET_PID  = "SM7099_01";
    		params.flag        = "insert"; //등록                
            params.COMPANY_CD  = "010101";       
            params.DIVISION_CD = "P1";       
            params.VENDOR_CD   = "V1590";       
            params.USER_ID     = SESSION.USER_ID;        
    		
    		dialog.init.setURL("/mm/pop/mmA017_01");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("MMA017_01"));
    		dialog.open(dl_1);
    	},
    	callByMMA17_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA017_01_dailog_01");
    		
    		for (key in datas) {
    			console.log("key:"+key+",value:"+datas[key]);
    		}
    		
    		form.handle.setValue("SM7099_01_form_01", "CODE8", datas.NALADISA_CODE);
    		form.handle.setValue("SM7099_01_form_01", "NAME8", datas.NALADISA_CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	}
    },
    ui : {}, // 화면 UI 변경
    file : {}, // 파일 input 엘리먼트 구현
    excel : {} // 엑셀다운로드 구현
};
