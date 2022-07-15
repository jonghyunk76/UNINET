/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_24
 * 작업일자 : 2020.12.09
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_24.config.applyFtaNation();
    MMB002_24.init.initComponent();
}

var MMB002_24 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_24.control.selectOpenAPISetup('0');
        	MMB002_24.control.selectOpenAPISetup('1');
        	
        	MMB002_24.combobox.initCombo_1();
        	MMB002_24.combobox.initCombo_2();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : { 
    	data_1 : [
    		{CODE:"I", NAME:resource.getMessage("CC_수입")},
        	{CODE:"E", NAME:resource.getMessage("CC_수출")}
       	],    	
        initCombo_1 : function() {
        	var obj = combo.getObject("MMB002_24_form_01", "INSCQUANITT");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"C_INSC_QUAN_ITT_CD"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        },
        initCombo_2 : function() {
        	var obj = combo.getObject("MMB002_24_form_04", "IMEXTPCD");

        	combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB002_24_grid_01");
    		var params = form.handle.getElementsParam("MMB002_24_form_02");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setFit(true);
            grid.init.setShowConfigPage(false);
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {url:"/fm/sm/sm7024_01/selectOpenAPIReceiveHeaderInfo", params:params});
        },
        initGrid_2 : function() {
    		var dg_1 = grid.getObject("MMB002_24_grid_02");
    		var params = form.handle.getElementsParam("MMB002_24_form_03");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(false);
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {url:"/fm/sm/sm7024_01/selectOpenAPIReceiveHeaderInfo", params:params});
        },
        initGrid_3 : function() {
    		var dg_1 = grid.getObject("MMB002_24_grid_03");
    		var params = form.handle.getElementsParam("MMB002_24_form_05");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(false);
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {url:"/fm/sm/sm7024_01/selectOpenAPIReceiveHeaderInfo", params:params});
        },
        initGrid_4 : function() {
    		var dg_1 = grid.getObject("MMB002_24_grid_04");
    		var params = form.handle.getElementsParam("MMB002_24_form_06");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(false);
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {url:"/fm/sm/sm7024_01/selectOpenAPIReceiveHeaderInfo", params:params});
        }        
    },
    // 이벤트 처리
    event : {
    	// OpenAPI Setup
    	selectOpenAPISetupAfterUI : function(data) {
    		var oapiIemCd = data.rows[0].OAPI_IEM_CD;
		    var formNm1;
		    var formNm2;
		    
		    if (oapiIemCd == "API004") {
	    		formNm1 = "MMB002_24_form_02";
	    		formNm2 = "MMB002_24_form_03";
		    } else if (oapiIemCd == "API003") {
	    		formNm1 = "MMB002_24_form_05";
	    		formNm2 = "MMB002_24_form_06";
		    }
		    
    		var oapiIemRecListSnArrayComma = data.rows[0].OAPI_IEM_REC_LIST_SN_ARRAY.split(",");	// 오픈API 항목 수신 목록이 여러건 경우 콤마로 일련번호를 구분 함.			
    		
    		form.handle.setValue(formNm1, "OAPI_IEM_REC_LIST_SN", oapiIemRecListSnArrayComma[0]);
    		form.handle.setValue(formNm2, "OAPI_IEM_REC_LIST_SN", oapiIemRecListSnArrayComma[1]);		

		    if (oapiIemCd == "API004") {
	            MMB002_24.datagrid.initGrid_1();
	            MMB002_24.datagrid.initGrid_2(); 
		    } else if (oapiIemCd == "API003") {
	            MMB002_24.datagrid.initGrid_3();
	            MMB002_24.datagrid.initGrid_4(); 
		    }    		           
    	}, 
    	selectAfterUI : function(data) {
    		var obj = tab.getObject("MMB002_24_tabs_01");
		    var num = tab.handle.getTabIndex(obj);
            var oList01 = null;
     		var oList02 = null;		    
		    var dg_1;
		    var dg_2;
		    
		    if(num == 0) {
	    		dg_1 = grid.getObject("MMB002_24_grid_01");
	    		dg_2 = grid.getObject("MMB002_24_grid_02");		    	
		    } else if (num == 1) {
	    		dg_1 = grid.getObject("MMB002_24_grid_03");
	    		dg_2 = grid.getObject("MMB002_24_grid_04");		
		    }

            try {
                oList01 = data.dataList01;
                oList02 = data.dataList02;
            } catch (e) {
                oList01 = {};
                oList02 = {};
            }
            
            dg_1.datagrid("loadData", oList01);
            dg_2.datagrid("loadData", oList02);            
    	},    	
        onDblClick_MMB002_24_grid_01 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_24_form_01", "TARGET_PID");
        	var field = form.handle.getValue("MMB002_24_form_01", "FIELD_ID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_24"]) == "function") {
                	rowData.FIELD_ID = field;
                    pro_id["callByMMB002_24"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
        // OpenAPI GRID 정보
        selectOpenAPISetup : function(tab) {
        	var formNm;
        	if (tab == 0) {
        		formNm = "MMB002_24_form_01";
        	} else if (tab == 1) {
        		formNm = "MMB002_24_form_04";
        	}
        	
     		var obj = form.getObject(formNm);
     		form.init.setURL("/fm/sm/sm7023_01/selectOpenAPIList");
     		form.init.setCallBackFunction("selectOpenAPISetupAfterUI");
     		form.submit(obj);     		     	          
        }, 
        // OpenAPI 수신 리스트
        selectOpenAPICallSelectList : function(OAPI_IEM_CD) {
        	var formNm;

        	if (OAPI_IEM_CD == "API004") {
        		formNm = "MMB002_24_form_01";
        		if(!form.handle.isValidate(formNm)) return;
        		
            	var inscQuanItt = combo.handle.getValue(formNm, "INSCQUANITT");
            	var ntfcNo = form.handle.getValue(formNm, "NTFCNO");
            	var dgcnt = form.handle.getValue(formNm, "DGCNT");

            	form.handle.setValue(formNm, "OAPI_IEM_CD", OAPI_IEM_CD);
     			form.handle.setValue(formNm, "P_PARA_KEY_01", "inscQuanItt");
     			form.handle.setValue(formNm, "P_PARA_VAL_01", inscQuanItt);
     			form.handle.setValue(formNm, "P_PARA_KEY_02", "ntfcNo");
     			form.handle.setValue(formNm, "P_PARA_VAL_02", ntfcNo);
     			form.handle.setValue(formNm, "P_PARA_KEY_03", "dgcnt");
     			form.handle.setValue(formNm, "P_PARA_VAL_03", dgcnt);			

        		grid.handle.clearAll(grid.getObject("MMB002_24_grid_01"));
        		grid.handle.clearAll(grid.getObject("MMB002_24_grid_02"));
        	} else if (OAPI_IEM_CD == "API003") {
        		formNm = "MMB002_24_form_04";
        		if(!form.handle.isValidate(formNm)) return;
        		
            	var imexTpcd = combo.handle.getValue(formNm, "IMEXTPCD");
            	var reqApreNo = form.handle.getValue(formNm, "REQAPRENO");

            	form.handle.setValue(formNm, "OAPI_IEM_CD", OAPI_IEM_CD);
     			form.handle.setValue(formNm, "P_PARA_KEY_01", "reqApreNo");
     			form.handle.setValue(formNm, "P_PARA_VAL_01", reqApreNo);
     			form.handle.setValue(formNm, "P_PARA_KEY_02", "imexTpcd");
     			form.handle.setValue(formNm, "P_PARA_VAL_02", imexTpcd);

        		grid.handle.clearAll(grid.getObject("MMB002_24_grid_03"));
        		grid.handle.clearAll(grid.getObject("MMB002_24_grid_04"));
        	} else {
        		return;
        	}
    		
     		var obj = form.getObject(formNm);
     		form.init.setURL("/fm/sm/sm7024_01/selectOpenAPICallSelectList");     		
     		form.init.setCallBackFunction("selectAfterUI");
     		form.submit(obj);     		     	          
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	divMove : function(idx) {
    		var obj = tab.getObject("MMB002_24_tabs_01");
			var num = tab.handle.getTabIndex(obj);
			
			for(var i = 1; i < 3; i++) {
    			if(idx == i) {
    				form.util.addClass("MMB002_24_divMove0"+i, "on");
    			} else {
    				form.util.removeClass("MMB002_24_divMove0"+i, "on");
    			}
    		}
			
			tab.handle.select(obj, idx - 1);
        }    	
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
