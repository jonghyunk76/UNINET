/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM-7018_01
 * 작업일자 : 2018.05.02
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7018_01.config.applyFtaNation();
	SM7018_01.init.initComponent();
}

var SM7018_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	SM7018_01.calendar.initCalendar_1();
            SM7018_01.calendar.initCalendar_2();
            
            SM7018_01.combobox.initCombo_1();
            SM7018_01.combobox.initCombo_2();
            SM7018_01.combobox.initCombo_3();
            
            SM7018_01.datagrid.initGrid_1();
            SM7018_01.datagrid.initGrid_2();
        }
    }, 
    // 달력 그리드 생성
    calendar : {
    	initCalendar_1 : function() {
            var obj_01 = calendar.getObject("SM7018_01_form_01", "SEARCH_FROM_DATE");
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
            var obj_01 = calendar.getObject("SM7018_01_form_01", "SEARCH_TO_DATE");
            
            calendar.create(obj_01);
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {
    	data_1 : [
    		{CODE:"USER_NAME", NAME:resource.getMessage("USER,NAME")},
    		{CODE:"USER_ID", NAME:resource.getMessage("USER, ID")},
    		{CODE:"COOPER_NAME", NAME:resource.getMessage("CONPY, NAME")},
    		{CODE:"COOPER_CD", NAME:resource.getMessage("CONPY, CODE")},
    		{CODE:"MENU_NAME", NAME:resource.getMessage("MENU, NAME")},
			{CODE:"MENU_ID", NAME:resource.getMessage("MENU, ID")},
			{CODE:"CLIENT_IP", NAME:resource.getMessage("USER, IP")}
		],
		data_2 : [
			{CODE:"internal", NAME:"Internal"},
			{CODE:"external", NAME:"External"}
		],
		initCombo_1 : function() {
			var obj = combo.getObject("SM7018_01_form_01", "schKeyField");
			
			combo.init.setData(this.data_1);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_2 : function() {
			var obj = combo.getObject("SM7018_01_form_01", "schKeyLike");
			
			combo.init.setURL("/mm/cbox/selectStandardCode");
			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_3 : function() {
    		var obj_1 = combo.getObject("SM7018_01_form_01", "SITE_TYPE");
            
    		combo.init.setData(this.data_2);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
            
            combo.create(obj_1);
    	}
    },
    // 데이터 그리드 생성
    datagrid : {
        header1_1 : [
        	["USER_ID"         , resource.getMessage("USER, ID")        , 100, "center", "center", true, false, null, null, null, 2, 0],
        	["USER_NAME"       , resource.getMessage("USER,NAME")       , 120, "left"  , "center", true, false, null, null, null, 2, 0],
        	["COOPER_CD"       , resource.getMessage("CONPY, CODE")     , 120, "center", "center", true, false, null, null, null, 2, 0],
        	["COOPER_NAME"     , resource.getMessage("CONPY,NAME")      , 150, "left"  , "center", true, false, null, null, null, 2, 0],
        	["REQUEST_INFO"    , resource.getMessage("REQ,INFMT")       , 100, "center", "center", true, false, null, null, null, 0, 8],
        	["RESPONSE_INFO"   , resource.getMessage("RESPS,INFMT")     , 100, "center", "center", true, false, null, null, null, 0, 2]
        ],
        header1_2 : [
        	["SITE_TYPE"       , resource.getMessage("SITE")            , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["CLIENT_IP"       , resource.getMessage("USER, IP")        , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["REQUEST_DATE"    , resource.getMessage("REQ,DATE")        , 90,  "center", "center", true, false, null, null, {format:"date"}, 0, 0],
        	["REQUEST_TIME"    , resource.getMessage("REQ,HOUR")        , 90,  "center", "center", true, false, null, null, null, 0, 0],
        	["MENU_ID"         , resource.getMessage("MENU, ID")        , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["MENU_NAME"       , resource.getMessage("MENU,NAME")       , 160, "left"  , "center", true, false, null, null, null, 0, 0],
        	["REQUEST_URL"     , " URL"                                 , 180, "left"  , "center", true, false, null, null, null, 0, 0],
        	["PARAMETER"       , resource.getMessage("PARAM")           , 250, "left"  , "center", true, false, null, null, null, 0, 0],
        	["RESPONSE_TIME"   , resource.getMessage("RESPS,HOUR")      , 90,  "center", "center", true, false, null, null, null, 0, 0],
        	["RESPONSE_MSG"    , resource.getMessage("COMENT")          , 200, "left"  , "center", true, false, null, null, null, 0, 0]
        ],
        header2_1 : [
        	["USER_ID"         , resource.getMessage("USER, ID")         , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["USER_NAME"       , resource.getMessage("USER,NAME")        , 120, "left"  , "center", true, false, null, null, null, 0, 0],
        	["LOGIN_COUNT"     , resource.getMessage("CNNTE,NUMB")       , 90,  "right" , "center", true, false, null, null, {format:"int"}, 0, 0],
        	["TRANS_COUNT"     , resource.getMessage("TXT_READ_COUNT")   , 100, "right" , "center", true, false, null, null, {format:"int"}, 0, 0],
        	["LAST_TIME"       , resource.getMessage("LATST, CNNTE,HOUR"), 140, "center", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("SM7018_01_grid_01");
            
            grid.init.setPage(true);
            
            Theme.defaultGrid(dg_1, this.header1_1, this.header1_2);
        },
        initGrid_2 : function() {
            var dg_1 = grid.getObject("SM7018_01_grid_02");
            
            grid.init.setPage(true);
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header2_1);
        }
    },
    // 이벤트 처리
    event : {
    	onClick_SM7018_01_grid_02 : function(rowData) {
    		SM7018_01.control.selectUseLogList(rowData.USER_ID);
    	}
    },
    // 업무구현
    control : {
    	selectUseLogList : function(userId) {
            if (!form.handle.isValidate("SM7018_01_form_01")) return;
    	    
    		var dg_1 = grid.getObject("SM7018_01_grid_01");
    		var params = form.handle.getElementsParam("SM7018_01_form_01");
    		
    		if(!oUtil.isNull(userId)) {
    			params.USER_ID = userId;
    		} else {
    			delete params.USER_ID;
    		}
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7018_01/selectUseLogList", params);
    	},
    	selectUseLogStatusList : function() {
            if (!form.handle.isValidate("SM7018_01_form_01")) return;
    	    
            SM7018_01.control.selectUseLogList();
            
    		var dg_1 = grid.getObject("SM7018_01_grid_02");
    		var params = form.handle.getElementsParam("SM7018_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7018_01/selectUseLogStatusList", params);
    	}
    },
    // 다이얼로그 구현
    dialog : {},
    ui : {},
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("SM7018_01_grid_01");
            var fobj = form.getObject("SM7018_01_form_01");
            
            form.init.setURL("/fm/sm/sm7018_01/selectUseLogList.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("USER,USE,RECOD"), resource.getMessage("LIST"));
        },
        excelDownload_2 : function() {
            var dg_1 = grid.getObject("SM7018_01_grid_02");
            var fobj = form.getObject("SM7018_01_form_01");
            
            form.init.setURL("/fm/sm/sm7018_01/selectUseLogStatusList.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("BYUSR,-,USE,PSTCN"), resource.getMessage("LIST"));
        }
    },
    config : {
      	applyFtaNation : function() {
      		if(form.handle.getValue("SM7018_01_form_01", "USER_ID") == "fta") {
      			form.handle.setValue("SM7018_01_form_01", "PARENT_COMPANY_CD", "");
      		}
      	}
     }
	
};
