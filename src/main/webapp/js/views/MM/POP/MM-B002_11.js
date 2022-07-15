/******************************************************************************************
 * 작성자 : Atom
 * Program Id : MM-B002_11
 * 작업일자 : 2020.07.08
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMB002_11.init.initComponent();
}

var MMB002_11 = {
    
    // 초기값 설정
    init : {    	 
        initComponent : function() {      	
            MMB002_11.combobox.initCombo_1();
            MMB002_11.control.selectOpenAPIInfo();
            // MMB002_11.datagrid.initGrid_1();
            MMB002_11.datagrid.initGrid_2();
            MMB002_11.datagrid.initGrid_3();
        }
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {
        dataSearchType : 
        	[
        	{CODE : "OAPI_IEM_CD", NAME : resource.getMessage("CC_오픈API_항목_코드")}, 
        	{CODE : "OAPI_IEM_NM", NAME : resource.getMessage("CC_오픈API_항목_명")}
        	],
        initCombo_1 : function() {
            var objSearchType = combo.getObject("MMB002_11_form_01", "schKeyField");
            
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchType);
            
            var objSearchKeyLike = combo.getObject("MMB002_11_form_01", "schKeyLike");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchKeyLike);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB002_11_grid_01");
    		var params = form.handle.getElementsParam("MMB002_11_form_01");
            
    		grid.init.setURL("/fm/sm/sm7023_01/selectOpenAPIInfo");
    		grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_1"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            grid.init.setCallBackFunction("onClick_AutoLine");			// 그리드 자동 클릭
            
            grid.event.setOnClickRow(dg_1);	// 그리드 ONE 클릭 시 이벤트
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}}); // DB에서 그리드 해더 정보 획득		                                                
        },
    	initGrid_2 : function() {
    		var dg_1 = grid.getObject("MMB002_11_grid_02");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_2"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            
            grid.event.setOnClickRow(dg_1);	// 그리드 ONE 클릭 시 이벤트
            
            // 셀 에디팅 설정
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(true);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            
            // 이벤트 설정
            grid.event.setOnClickRow(dg_1);
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header2"}});                   
        },
    	initGrid_3 : function() {
    		var dg_1 = grid.getObject("MMB002_11_grid_03");
    		var params = form.handle.getElementsParam("MMB002_11_form_03");

            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setShowConfigPage(false); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_3"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            console.log("initGrid_3 : " + params.OAPI_IEM_REC_LIST_SN);
            if (!oUtil.isNull(params.OAPI_IEM_REC_LIST_SN)) {
            	Theme.defaultGrid(dg_1, {url:"/fm/sm/sm7024_01/selectOpenAPIReceiveHeaderInfo", params:params}); // DB에서 그리드 해더 정보 획득	
            } else {
            	Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header3"}});     // DB에서 그리드 해더 정보 획득	
            }	
        },
    	initGrid_4 : function() {
    		var dg_1 = grid.getObject("MMB002_11_grid_04");
    		var params = form.handle.getElementsParam("MMB002_11_form_04");

            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setShowConfigPage(false); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_4"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            console.log("initGrid_4 : " + params.OAPI_IEM_REC_LIST_SN);
            if (!oUtil.isNull(params.OAPI_IEM_REC_LIST_SN)) {
            	Theme.defaultGrid(dg_1, {url:"/fm/sm/sm7024_01/selectOpenAPIReceiveHeaderInfo", params:params}); // DB에서 그리드 해더 정보 획득	
            } else {
            	Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header3"}});     // DB에서 그리드 해더 정보 획득	
            }	
        },
    	initGrid_5 : function() {
    		var dg_1 = grid.getObject("MMB002_11_grid_05");
    		var params = form.handle.getElementsParam("MMB002_11_form_05");

            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setShowConfigPage(false); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_5"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            console.log("initGrid_5 : " + params.OAPI_IEM_REC_LIST_SN);
            if (!oUtil.isNull(params.OAPI_IEM_REC_LIST_SN)) {
            	Theme.defaultGrid(dg_1, {url:"/fm/sm/sm7024_01/selectOpenAPIReceiveHeaderInfo", params:params}); // DB에서 그리드 해더 정보 획득	
            } else {
            	Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header3"}});     // DB에서 그리드 해더 정보 획득	
            }	
        }       
    },
    // 이벤트 처리
    event : {
    	// OpenAPI 항목 Call
    	onClick_MMB002_11_grid_01 : function() {
    		var rowData = {};
    		var oapiIemRecListSnArray = form.handle.getValue("MMB002_11_form_01", "OAPI_IEM_REC_LIST_SN_ARRAY");
    		var oapiIemRecListSnArrayComma = oapiIemRecListSnArray.split(",");
    		var trgTableYn = form.handle.getValue("MMB002_11_form_01", "TRG_TABLE_YN");    		
			
    		rowData.COMPANY_CD = form.handle.getValue("MMB002_11_form_01", "COMPANY_CD");
    		rowData.OAPI_IEM_CD = form.handle.getValue("MMB002_11_form_01", "OAPI_IEM_CD");
    		rowData.P_PARA_KEY_01 = form.handle.getValue("MMB002_11_form_01", "P_PARA_KEY_01");
    		rowData.P_PARA_VAL_01 = form.handle.getValue("MMB002_11_form_01", "P_PARA_VAL_01");
    		rowData.P_PARA_KEY_02 = form.handle.getValue("MMB002_11_form_01", "P_PARA_KEY_02");
    		rowData.P_PARA_VAL_02 = form.handle.getValue("MMB002_11_form_01", "P_PARA_VAL_02");
    		rowData.P_PARA_KEY_03 = form.handle.getValue("MMB002_11_form_01", "P_PARA_KEY_03");
    		rowData.P_PARA_VAL_03 = form.handle.getValue("MMB002_11_form_01", "P_PARA_VAL_03");
    		rowData.P_PARA_KEY_04 = form.handle.getValue("MMB002_11_form_01", "P_PARA_KEY_04");
    		rowData.P_PARA_VAL_04 = form.handle.getValue("MMB002_11_form_01", "P_PARA_VAL_04");
    		rowData.P_PARA_KEY_05 = form.handle.getValue("MMB002_11_form_01", "P_PARA_KEY_05");
    		rowData.P_PARA_VAL_05 = form.handle.getValue("MMB002_11_form_01", "P_PARA_VAL_05");
    		
    		console.log("oapiIemRecListSnArrayComma : " + oapiIemRecListSnArrayComma);
    		
		    if (oapiIemRecListSnArrayComma.length <= 1 || oapiIemRecListSnArrayComma.indexOf(',') == -1) {
		    	form.handle.setValue("MMB002_11_form_03", "OAPI_IEM_REC_LIST_SN", oapiIemRecListSnArrayComma[0].trim());
		    	MMB002_11.ui.divMove('1');
		    	MMB002_11.datagrid.initGrid_3();		    	
		    } else if (oapiIemRecListSnArrayComma.length == 2) {
		    	form.handle.setValue("MMB002_11_form_04", "OAPI_IEM_REC_LIST_SN", oapiIemRecListSnArrayComma[0].trim());
		    	form.handle.setValue("MMB002_11_form_05", "OAPI_IEM_REC_LIST_SN", oapiIemRecListSnArrayComma[1].trim());
		    	MMB002_11.ui.divMove('2');
		    	MMB002_11.datagrid.initGrid_4();
		    	MMB002_11.datagrid.initGrid_5();
		    }		       

		    // 가져오기 체크박스 보이기 숨기기
		    MMB002_11.ui.checkChangeUI(trgTableYn);
	    	
    		MMB002_11.control.selectOpenAPIParaList(rowData);
    	},    	
    	selectAfterUI : function(data) {
    		var dg_3 = grid.getObject("MMB002_11_grid_03");
    		var dg_4 = grid.getObject("MMB002_11_grid_04");
    		var dg_5 = grid.getObject("MMB002_11_grid_05");
    		
            var oList01 = null;
     		var oList02 = null;
            try {
                oList01 = data.dataList01;
                oList02 = data.dataList02;
            } catch (e) {
                oList01 = {};
                oList02 = {};
            }
            
            // Get Tab index
    		var obj = tab.getObject("MMB002_11_tabs_01");
		    var tabIndex = tab.handle.getTabIndex(obj);
		 
		    if (tabIndex == 0) {
		    	dg_3.datagrid("loadData", oList01);
		    } else if (tabIndex == 1) {
	            dg_4.datagrid("loadData", oList01);
	            dg_5.datagrid("loadData", oList02);
		    }
    	},
    	selectAfter01UI : function(data) {
            form.handle.loadData("MMB002_11_form_01", data);
            MMB002_11.event.onClick_MMB002_11_grid_01();
        }
    },
    // 업무구현
    control : {
    	// 오픈API 항목
    	selectOpenAPIInfo : function() {
            var obj = form.getObject("MMB002_11_form_01");	// Get Form 개체
            
            form.init.setURL("/fm/sm/sm7023_02/selectOpenAPIInfo");
            form.init.setCallBackFunction("selectAfter01UI");            
            form.init.setProgressFlag(false);	// progress bar 사용 여부(default=true)
            form.init.setValidationFlag(false);	// validation check여부(default=true)
            
            form.submit(obj);
            
        },
        // 오픈API 항목 송신 파라미터
        selectOpenAPIParaList : function(rowData) {
    		var dg_2 = grid.getObject("MMB002_11_grid_02");
    		var params;
    		if(!oUtil.isNull(rowData)) {
    			params = rowData;
    		} else {
    			var dg_1 = grid.getObject("MMB002_11_grid_01");
    			params = grid.handle.getSelectedRowData(dg_1);
    		}    		
    		
    		grid.handle.sendRedirect(dg_2, "/fm/sm/sm7023_02/selectOpenAPIParaList", params);
        },
        // OpenAPI 수신 리스트
        selectOpenAPICallSelectList : function() {

     		var dg_1 = grid.getObject("MMB002_11_grid_02");
     		var dg_2 = grid.getObject("MMB002_11_grid_03");
     		var rows = grid.handle.getAllRows(dg_1);
     		var params = form.handle.getElementsParam("MMB002_11_form_01");
     		
			if(form.handle.isChecked("MMB002_11_form_02", "TARGET_INSERT_AT")) {
				form.handle.setValue("MMB002_11_form_01", "TARGET_INSERT_AT", "Y");
      		} else {
      			form.handle.setValue("MMB002_11_form_01", "TARGET_INSERT_AT", "N");
      		}
     		
            dg_1.datagrid('endCellEdit');
            
     		for(var i = 0; i < rows.length; i++) {   
     			if (i == 0) {
     				form.handle.setValue("MMB002_11_form_01", "OAPI_IEM_CD", rows[i].OAPI_IEM_CD);
     			}
     			
	 			if (i >= 9) {
	     			form.handle.setValue("MMB002_11_form_01", "P_PARA_KEY_"+[i+1], rows[i].PARA_NM);
	     			form.handle.setValue("MMB002_11_form_01", "P_PARA_VAL_"+[i+1], rows[i].PARA_VAL);        				
	 			} else {
	     			form.handle.setValue("MMB002_11_form_01", "P_PARA_KEY_0"+[i+1], rows[i].PARA_NM);
	     			form.handle.setValue("MMB002_11_form_01", "P_PARA_VAL_0"+[i+1], rows[i].PARA_VAL);   
	 			}				
     		}

		    // 그리드 초기화
     		var dg_3 = grid.getObject("MMB002_11_grid_03");
     		var dg_4 = grid.getObject("MMB002_11_grid_04");
     		var dg_5 = grid.getObject("MMB002_11_grid_05");
     		try {grid.handle.clearAll(dg_3);} catch (e) {}
     		try {grid.handle.clearAll(dg_4);} catch (e) {}
     		try {grid.handle.clearAll(dg_5);} catch (e) {}
		    
     		var obj = form.getObject("MMB002_11_form_01");
     		form.init.setURL("/fm/sm/sm7024_01/selectOpenAPICallSelectList");     		
     		form.init.setCallBackFunction("selectAfterUI");
     		form.submit(obj);     		     	          
        }
    },
    // 다이얼로그 구현
    dialog : {
    },
    file : {
        
    },
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMB002_11_grid_01");
            var fobj = form.getObject("MMB002_11_form_01");
            
            form.init.setURL("/fm/sm/MMB002_11/selectInterfaceItemMstList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("INF,ITEMS, TXT_LIST"), resource.getMessage("INF,ITEMS, TXT_LIST"));
        }
    },
    ui : {
    	divMove : function(idx) {
    		var obj = tab.getObject("MMB002_11_tabs_01");
			
			tab.handle.select(obj, idx);
        },
        // 가져오기 체크박스 보이기 숨기기
    	checkChangeUI : function(targetTableYn) {
    		var formName = "MMB002_11_form_02";
    		if (targetTableYn == "Y") {
    			form.handle.disabled(formName, "TARGET_INSERT_AT", false);
			} else {
    			form.handle.setChecked(formName, "TARGET_INSERT_AT", false);
	    		form.handle.disabled(formName, "TARGET_INSERT_AT", true);	    		
			}
        }        
    }
    
};
