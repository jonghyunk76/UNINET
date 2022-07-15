/******************************************************************************************
 * 작성자 : 
 * Program Id : SM1002_01
 * _01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM1002_01.config.applyFtaNation(); // FTA 협정별 UI변경
	SM1002_01.init.initComponent();
}

var SM1002_01 = {
	
	// 초기값 설정
    init : {
    	initComponent : function() {
    		SM1002_01.combobox.initCombo_1();
    		SM1002_01.combobox.initCombo_2();
    		SM1002_01.combobox.initCombo_3();
    		SM1002_01.combobox.initCombo_4();
    		
    		SM1002_01.datagrid.initGrid_1();
    		SM1002_01.datagrid.initGrid_2();
    		SM1002_01.datagrid.initGrid_3();
    		
    		form.handle.setValue("SM1002_01_form_02", "flag", "insert");
    	}
    }, 
    // 달력 그리드 생성
	calendar : {}, 
	// 콤보박스 그리드 생성
	combobox : {
		data_1 : [
			{CODE:"CATEGORY_NAME", NAME:resource.getMessage("CATEG, NAME")},
			{CODE:"CATEGORY_CD", NAME:resource.getMessage("CATEG, CODE")}, 
			{CODE:"CATEGORY_NAME_ENG", NAME:resource.getMessage("CATEG, ENGLS")}
        ],
        data_2 : [
        	{CODE:"", NAME:resource.getMessage("SELET")},
			{CODE:"N", NAME:resource.getMessage("TXT_NO")},
			{CODE:"Y", NAME:resource.getMessage("TXT_YES")}
        ],        
    	initCombo_1 : function() {
			var obj = combo.getObject("SM1002_01_form_01", "schKeyField");
			
			combo.init.setData(this.data_1);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_2 : function() {
			var obj = combo.getObject("SM1002_01_form_01", "schKeyLike");
			
			combo.init.setURL("/mm/cbox/selectStandardCode");
			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_3 : function() {
			var obj = combo.getObject("SM1002_01_form_03", "LANGUAGE_CAT");
			
			combo.init.setURL("/mm/cbox/selectStandardCode");
			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LENG_CD"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			combo.init.setRequired(true);
			
			combo.create(obj);
		},
		initCombo_4 : function() {
			var obj = combo.getObject("SM1002_01_form_02", "COMPANY_YN");
			
			combo.init.setData(this.data_2);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			combo.init.setRequired(true);
			if(SESSION.USER_ID == "fta") {
				combo.init.setReadonly(false);
			} else {
				combo.init.setReadonly(true);
			}
			
			combo.create(obj);
		}
	},
	// 데이터 그리드 생성
    datagrid : { 
		header_1 : [
			["CATEGORY_CD"        	,resource.getMessage("CATEG, CODE")       ,130    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["CATEGORY_NAME"   		,resource.getMessage("CATEG, NAME")       ,200    ,"left"      ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
			["CATEGORY_NAME_ENG"   	,resource.getMessage("CATEG, NAME")       ,200    ,"left"      ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
			["COMPANY_YN"        	,resource.getMessage("USER, MGT")         ,100    ,"center"      ,"center"    ,true       ,true    ,null         ,null     ,null    ,0        ,0]
        ],
        header_2 : [
        	["CODE"        			,resource.getMessage("CODE")        	  ,80     ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["CODE_NAME"   			,resource.getMessage("CODE, NAME")        ,160    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["CODE_NAME_ENG"   		,resource.getMessage("CODE, NAME")        ,160    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["SORT_NO"   			,resource.getMessage("SORT_SEQ")      	  ,100    ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,{format:"int"}    ,0        ,0],
        	["TXT_VALUE1"   		,resource.getMessage("SET")+"("+resource.getMessage("STR")+")1"          	,110    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["TXT_VALUE2"   		,resource.getMessage("SET")+"("+resource.getMessage("STR")+")2"             ,110     ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["TXT_VALUE3"   		,resource.getMessage("SET")+"("+resource.getMessage("STR")+")3"             ,110     ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["TXT_VALUE4"   		,resource.getMessage("SET")+"("+resource.getMessage("STR")+")4"             ,110     ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["TXT_VALUE5"   		,resource.getMessage("SET")+"("+resource.getMessage("STR")+")5"             ,110     ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["NUM_VALUE1"   		,resource.getMessage("SET")+"("+resource.getMessage("NUM")+")1"             ,110     ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["NUM_VALUE2"   		,resource.getMessage("SET")+"("+resource.getMessage("NUM")+")2"             ,110     ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["NUM_VALUE3"   		,resource.getMessage("SET")+"("+resource.getMessage("NUM")+")3"             ,110     ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["NUM_VALUE4"   		,resource.getMessage("SET")+"("+resource.getMessage("NUM")+")4"             ,110     ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["NUM_VALUE5"   		,resource.getMessage("SET")+"("+resource.getMessage("NUM")+")5"             ,110     ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0]
        ],
        header_3 : [
        	["LANGUAGE_CAT_NAME"    ,resource.getMessage("LANG")        	  ,110     ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["WORD"   			    ,resource.getMessage("MSMGT")             ,250    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["MEANNING"   			,resource.getMessage("DSCPT")             ,200    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0]
        ],
		initGrid_1 : function() {
			var dg_1 = grid.getObject("SM1002_01_grid_01");
    		var params = form.handle.getElementsParam("SM1002_01_form_01");
    		
			grid.init.setQueryParams(params);
			grid.init.setURL("/mm/smp/smp1002/selectSysCategoryList");
			grid.init.setPage(true);
            grid.init.setFilter(true);
			grid.init.setFitColumns(false);
			// 이벤트 설정
			grid.event.setOnClickRow(dg_1);
			
			Theme.defaultGrid(dg_1, this.header_1);
		},
		initGrid_2 : function() {
			var dg_1 = grid.getObject("SM1002_01_grid_02");
			
			grid.init.setPage(false);
			grid.init.setFitColumns(false);
            grid.init.setFilter(true);
			grid.init.setEmptyMessage(false);
			// 이벤트 설정
			grid.event.setOnDblClickRow(dg_1);
			grid.event.setOnClickRow(dg_1);
			
			Theme.defaultGrid(dg_1, this.header_2);
		},
		initGrid_3 : function() {
			var dg_1 = grid.getObject("SM1002_01_grid_03");
			
			grid.init.setPage(false);
			grid.init.setFitColumns(true);
			grid.init.setEmptyMessage(false);
			// 이벤트 설정
			grid.event.setOnClickRow(dg_1);
			
			Theme.defaultGrid(dg_1, this.header_3);
		}
    },
    // 이벤트 처리
    event : { 
		onClick_SM1002_01_grid_01 : function(rowData) {
			form.handle.loadData("SM1002_01_form_02", rowData);
			
			SM1002_01.ui.updateChangeUI();
			form.handle.setValue("SM1002_01_form_02", "flag", "update");
			
			SM1002_01.control.selectDetailList(rowData);
			
			var dg_1 = grid.getObject("SM1002_01_grid_03");
    		grid.handle.clearAll(dg_1);
		},
		onClick_SM1002_01_grid_02 : function(rowData) {
			form.handle.loadData("SM1002_01_form_03", rowData);
			
			SM1002_01.control.selectLangDetailList();
			
			this.resetLangUI();
		},
		onDblClick_SM1002_01_grid_02 : function(rowData) {
			SM1002_01.dialog.openDialog_1('update');
		},
		updateAfterUI : function(data) {
    		if(form.handle.getValue("SM1002_01_form_02", "flag") == "insert") {
    			form.handle.setValue("SM1002_01_form_02", "flag", "update");
    			
    			SM1002_01.ui.updateChangeUI();
    		}
    		
    		SM1002_01.control.selectMainList();
    	},
		resetUI : function() {
			SM1002_01.ui.insertChangeUI();
			
			form.handle.setValue("SM1002_01_form_02", "flag", "insert");
    		form.handle.reset("SM1002_01_form_02");
    		
    		var dg_1 = grid.getObject("SM1002_01_grid_02");
    		grid.handle.clearAll(dg_1);
    		dg_1 = grid.getObject("SM1002_01_grid_03");
    		grid.handle.clearAll(dg_1);
    	},
    	onClick_SM1002_01_grid_03 : function(rowData) {
    		form.handle.setValue("SM1002_01_form_03", "flag", "update");
			form.handle.loadData("SM1002_01_form_03", rowData);
		},
    	codeLangAfterUI : function() {
    		SM1002_01.control.selectLangDetailList();
    	},
    	resetLangUI : function() {
    		combo.handle.setValue("SM1002_01_form_03", "WORD", "");
    		form.handle.setValue("SM1002_01_form_03", "WORD", "");
			form.handle.setValue("SM1002_01_form_03", "MEANNING", "");
			form.handle.setValue("SM1002_01_form_03", "flag", "insert");
    	}
    },
    // 업무구현
    control : {
    	selectMainList : function() {
    		if(!form.handle.isValidate("SM1002_01_form_01")) return;
    		
    		var dg_1 = grid.getObject("SM1002_01_grid_01");
    		var params = form.handle.getElementsParam("SM1002_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/smp/smp1002/selectSysCategoryList", params);
    	},
    	selectDetailList : function(rowData) {
    		var dg_2 = grid.getObject("SM1002_01_grid_02");
    		
    		var params;
    		if(!oUtil.isNull(rowData)) {
    			params = rowData;
    		} else {
    			var dg_1 = grid.getObject("SM1002_01_grid_01");
    			params = grid.handle.getSelectedRowData(dg_1);
    		}
    		params.COMPANY_CD = SESSION.COMPANY_CD;
    		
    		grid.handle.sendRedirect(dg_2, "/mm/smp/smp1002/selectSysCodeList", params);
    	},
    	selectLangDetailList : function(rowData) {
    		var dg_3 = grid.getObject("SM1002_01_grid_03");
    		
    		var params;
    		if(!oUtil.isNull(rowData)) {
    			params = rowData;
    		} else {
    			var dg_1 = grid.getObject("SM1002_01_grid_02");
    			params = grid.handle.getSelectedRowData(dg_1);
    		}
    		
    		grid.handle.sendRedirect(dg_3, "/mm/smp/smp1002/selectSysCodeLangList", params);
    	},
    	updateCategoryInfo : function() {
    		if(!form.handle.isValidate("SM1002_01_form_02")) return;
    		
    		var flag = form.handle.getValue("SM1002_01_form_02", "flag");
    		var confMsg = "";
    		
    		if(flag == "insert") {
    			confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
    		} else {
    			confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
    		}
    		
    		$.messager.confirm(CNFIR, confMsg, function(cnf) {
                if(cnf) {
                	var obj = form.getObject("SM1002_01_form_02");
    	    		
    	    		if(flag == "insert") {
    	    			form.init.setURL("/mm/smp/smp1002/insertSysCategoryInfo");
    	    		}
    	    		if(flag == "update") {
    	    			form.init.setURL("/mm/smp/smp1002/updateSysCategoryInfo");
    	    		}
    	    		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
    	    		form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
    	    		form.init.setCallBackFunction("updateAfterUI");
    	    		
    	    		form.submit(obj);
                }
            });
    	},
    	// delete 수행
    	deleteCategoryInfo : function() {
    		$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if(flag) {
                	var obj = form.getObject("SM1002_01_form_02");
    	    		
    	    		form.init.setURL("/mm/smp/smp1002/deleteSysCategoryInfo");
    	    		form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
    	    		form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
    	    		form.init.setCallBackFunction("resetUI");
    	    		
    	    		form.submit(obj);
                }
            });
    	},
    	updateLanguageInfo : function() {
    		var dg_1 = grid.getObject("SM1002_01_grid_02");
    		
    		if(!grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_NO_SELECT")+" ["+resource.getMessage("CODE, LIST")+"]");
    			return;
    		}
    		
    		if(!form.handle.isValidate("SM1002_01_form_03")) return;
    		
    		// 기등록된 언어가 있는지 찾는다.
    		var dg_3 = grid.getObject("SM1002_01_grid_03");
    		var gridRowCnt = grid.handle.getRowCount(dg_3);
        	var rowData = grid.handle.getAllRows(dg_3);
        	var langCd = combo.handle.getValue("SM1002_01_form_03", "LANGUAGE_CAT");
        	var flag = form.handle.getValue("SM1002_01_form_03", "flag");
    		var confMsg = "";
    		
    		if(flag == "insert") {
	        	for(var i = 0; i < gridRowCnt; i++) {
	        		if(rowData[i]["LANGUAGE_CAT"] == langCd) {
	        			alert(resource.getMessage("MSG_IS_DUPLICATED"));
	        			return;
	        		}
	        	}
    		}
    		
    		if(flag == "insert") {
    			confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
    		} else {
    			confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
    		}
    		
    		$.messager.confirm(CNFIR, confMsg, function(cnf) {
                if(cnf) {
                	var obj = form.getObject("SM1002_01_form_03");
    	    		
    	    		if(flag == "insert") {
    	    			form.init.setURL("/mm/smp/smp1002/insertSysCodeLangInfo");
    	    		}
    	    		if(flag == "update") {
    	    			form.init.setURL("/mm/smp/smp1002/updateSysCodeLangInfo");
    	    		}
    	    		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
    	    		form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
    	    		form.init.setCallBackFunction("codeLangAfterUI");
    	    		
    	    		form.submit(obj);
                }
            });
    	},
    	deleteLanguageInfo : function() {
    		var dg_1 = grid.getObject("SM1002_01_grid_03");
    		
    		if(!grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_NO_SELECT")+" ["+resource.getMessage("LANG, LIST")+"]");
    			return;
    		}
    		
    		$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if(flag) {
                	var obj = form.getObject("SM1002_01_form_03");
    	    		
    	    		form.init.setURL("/mm/smp/smp1002/deleteSysCodeLangInfo");
    	    		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
    	    		form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
    	    		form.init.setCallBackFunction("codeLangAfterUI");
    	    		
    	    		form.submit(obj);
                }
            });
    	}
    },
    // 다이얼로그 구현
    dialog : {
    	openDialog_1 : function(flag) {
    		var dg_1 = grid.getObject("SM1002_01_grid_01");
    		var dg_2 = grid.getObject("SM1002_01_grid_02");
    		var dl_1 = dialog.getObject("SM1002_02_dailog_01");
    		
    		if(!grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_CATEGORY_SELECT"));
    			return;
    		}
    		
    		if(!form.handle.isValidate("SM1002_01_form_02")) return;
    		
    		if(flag != "insert" && !grid.handle.isSelected(dg_2)) {
    			alert(resource.getMessage("MSG_NO_SELECT"));
    			return;
    		}
    		
    		// 팝업으로 넘겨줄 파라메터 생성
    		var params = {};
    		var params1 = {};
    		var params2 = {};
    		
    		params1.flag = flag;
    		params1.CATEGORY_CD = form.handle.getValue("SM1002_01_form_02", "CATEGORY_CD");
    		params1.CATEGORY_NAME = form.handle.getValue("SM1002_01_form_02", "CATEGORY_NAME");
    		
    		if(flag != "insert") params2 = grid.handle.getSelectedRowData(dg_2);
    		else params1.COMPANY_CD = SESSION.COMPANY_CD;
    			
    		$.extend(params, params1, params2);

    		// 팝업 셋팅
    		dialog.init.setWidth(600);
    		dialog.init.setHeight(350);
    		dialog.init.setURL("/mm/smp/smp1002/smp1002_02");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("SMP1002_02"));
    		dialog.open(dl_1);
    	}
    },
    ui : {
    	insertChangeUI : function() {
    		if(SESSION.USER_ID == "fta") {
	    		form.handle.readonly("SM1002_01_form_02", "CATEGORY_CD", false);
				form.handle.removeClass("SM1002_01_form_02", "CATEGORY_CD", "input_readOnly");
				
				form.handle.readonly("SM1002_01_form_02", "CATEGORY_NAME", false);
	            form.handle.removeClass("SM1002_01_form_02", "CATEGORY_NAME", "input_readOnly");
	            form.handle.readonly("SM1002_01_form_02", "CATEGORY_NAME_ENG", false);
	            form.handle.removeClass("SM1002_01_form_02", "CATEGORY_NAME_ENG", "input_readOnly");
    		}
    	},
    	updateChangeUI : function() {
    		form.handle.readonly("SM1002_01_form_02", "CATEGORY_CD", true);
			form.handle.addClass("SM1002_01_form_02", "CATEGORY_CD", "input_readOnly");
			
			if(SESSION.USER_ID == "fta") {
				form.handle.readonly("SM1002_01_form_02", "CATEGORY_NAME", false);
	            form.handle.removeClass("SM1002_01_form_02", "CATEGORY_NAME", "input_readOnly");
	            form.handle.readonly("SM1002_01_form_02", "CATEGORY_NAME_ENG", false);
	            form.handle.removeClass("SM1002_01_form_02", "CATEGORY_NAME_ENG", "input_readOnly");
			}
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현    
    excel : {},
    config : {
    	applyFtaNation : function() {
    		var columns1 = SM1002_01.datagrid.header_1;
    		var columns2 = SM1002_01.datagrid.header_2;
    		
			if(SESSION.DEFAULT_LANGUAGE == "KR") {
				grid.util.changeHeaderHidden(columns1 , "CATEGORY_NAME", false);
				grid.util.changeHeaderHidden(columns1 , "CATEGORY_NAME_ENG", true);
			} else {
				grid.util.changeHeaderHidden(columns1 , "CATEGORY_NAME", true);
				grid.util.changeHeaderHidden(columns1 , "CATEGORY_NAME_ENG", false);
			}
			
			if(SESSION.USER_ID == "fta") {
				form.util.setVisible("SM1002_01_btn_01", true);
				form.util.setVisible("SM1002_01_btn_02", true);
				form.util.setVisible("SM1002_01_btn_03", true);
				
				grid.util.changeHeaderHidden(columns1 , "COMPANY_YN", false);
			}
		}
    }
    
};
