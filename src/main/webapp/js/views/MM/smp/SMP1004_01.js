/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM1004_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM1004_01.init.initComponent();
}

var SM1004_01 = {
    
	// 초기값 설정
    init : {
    	initComponent : function() {
    		SM1004_01.datagrid.initGrid_1();
    		SM1004_01.datagrid.initGrid_2();
    		SM1004_01.datagrid.initGrid_3();
    		SM1004_01.datagrid.initGrid_4();
    		SM1004_01.combobox.initCombo_1();
    	}
    },
    // 달력 그리드 생성
    calendar : { }, 
    // 콤보박스 그리드 생성
    combobox : {
    	data_1 : [{CODE:"Y", NAME:resource.getMessage("USE")}, {CODE:"N", NAME:resource.getMessage("UNUSE")}],
    	initCombo_1 : function() {
    		var obj = combo.getObject("SM1004_01_form_01", "USE_YN");
    		
    		combo.init.setData(this.data_1);
    		combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			combo.init.setRequired(true);
			
			combo.create(obj);
    	}
    },
    datagrid : { // 데이터 그리드 생성
        header1 : [
                  ["COMPANY_CD", resource.getMessage("CONPY, CODE"), 100, "center", "center", true, false, true, null, null, 0, 0],
                  ["AUTH_GROUP_ID", resource.getMessage("GROUP, CODE"), 100, "center", "center", true, false, null, null, null, 0, 0],
                  ["AUTH_GROUP_NAME", resource.getMessage("GROUP, NAME"), 120, "left", "center", true, false, null, null, null, 0, 0],
                  ["AUTH_GROUP_NAME_ENG", resource.getMessage("GROUP, ENGLS"), 120, "left", "center", true, false, null, null, null, 0, 0],
                  ["USE_YN", resource.getMessage("USE, ORNOT"), 80, "center", "center", true, false, null, null, null, 0, 0],
                  ["COMMENTS", resource.getMessage("COMTS"), 230, "left", "left", true, false, null, null, null, 0, 0]
                  ],
        header2 : [
                  ["USER_ID", resource.getMessage("USER, ID"), 100, "center", "center", true, false, null, null, null, 0, 0],
                  ["EMP_NO", resource.getMessage("EMPNO"), 100, "center", "center", true, false, null, null, null, 0, 0],
                  ["NAME", resource.getMessage("NAME"), 120, "left", "center", true, false, null, null, null, 0, 0],
                  ["NAME_ENG", resource.getMessage("NAME, ENGLS"), 120, "left", "center", true, false, null, null, null, 0, 0],
                  ["POSITION", resource.getMessage("POSIT"), 100, "center", "center", true, false, null, null, null, 0, 0]
                  ],
        header3 : [
                  ["CHECK", "CHECK", 100, "center", "center", true, false, null, true, null, 0, 0, true],
                  ["MENU_ID", resource.getMessage("MENU, ID"), 100, "center", "center", true, false, null, null, null, 0, 0],
                  ["MENU_NM", resource.getMessage("MENU, NAME"), 200, "left", "center", true, false, null, null, null, 0, 0],
                  ["MENU_EPLN", resource.getMessage("DSCPT"), 250, "left", "center", true, false, null, null, null, 0, 0]
                  ],
        header4 : [
                  ["CHECK", "CHECK", 100, "center", "center", true, false, null, true, null, 0, 0, true],
                  ["MENU_ID", resource.getMessage("MENU, ID"), 100, "center", "center", true, false, null, null, null, 0, 0],
                  ["MENU_NM", resource.getMessage("MENU, NAME"), 200, "left", "center", true, false, null, null, null, 0, 0],
                  ["SEL_AUTH", resource.getMessage("SRCH"), 60, "center", "center", true, false, null, null, null, 0, 0],
                  ["REG_AUTH", resource.getMessage("SAVE"), 60, "center", "center", true, false, null, null, null, 0, 0],
                  ["UPD_AUTH", resource.getMessage("MOD"), 60, "center", "center", true, false, null, null, null, 0, 0],
                  ["DEL_AUTH", resource.getMessage("DEL"), 60, "center", "center", true, false, null, null, null, 0, 0],
                  ["EXC_AUTH", resource.getMessage("EXCUS"), 60, "center", "center", true, false, null, null, null, 0, 0],
                  ["FLE_AUTH", resource.getMessage("FILE"), 60, "center", "center", true, false, null, null, null, 0, 0]
                  ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("SM1004_01_grid_01");
            
            grid.init.setTitle(resource.getMessage("AUTHO, GROUP"));
            grid.init.setURL("/fm/sm/sm7010/selectAuthGroupList");
            grid.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD});
            grid.init.setEmptyMessage(false);
            // 이벤트 설정
            grid.event.setOnClickRow(dg_1);
            
            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header1);
        },
        initGrid_2 : function() {
            var dg_1 = grid.getObject("SM1004_01_grid_02");
            
            grid.init.setEmptyMessage(false);

            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header2);
        },
        initGrid_3 : function() {
            var dg_1 = grid.getObject("SM1004_01_grid_03");
            
            grid.init.setTitle(resource.getMessage("MENU, LIST"));
            grid.init.setEmptyMessage(false);
            
            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header3);
        },
        initGrid_4 : function() {
            var dg_1 = grid.getObject("SM1004_01_grid_04");
            
            grid.init.setTitle(resource.getMessage("MSG_MENU_INVESTED_LIST"));
            grid.init.setEmptyMessage(false);

            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header4);
        }
    },
    event : { // 이벤트 처리
        onClick_SM1004_01_grid_01 : function(rowData) {
        	SM1004_01.ui.updateChangeUI();
        	
        	form.handle.setValue("SM1004_01_form_01", "flag", "update");
        	form.handle.loadData("SM1004_01_form_01", rowData);
        	
        	SM1004_01.control.selectAuthUserList(rowData);
        	SM1004_01.control.selectMenuList(rowData);
        	SM1004_01.control.selectAuthMenuList(rowData);
        },
        // 지정된 form를 reset하여 등록가능한 상태로 변경하는 함수
		resetUI : function() {
			SM1004_01.ui.insertChangeUI();
			
			form.handle.setValue("SM1004_01_form_01", "flag", "insert");
			form.handle.reset("SM1004_01_form_01");
    	},
    	// 성공적으로 요청처리가 완료될 경우 호출되는 함수
    	updateAfterUI : function(data) {
			// update 상태로 변경한다.
			form.handle.setValue("SM1004_01_form_01", "flag", "update");
			// 업데이트 상태로 UI를 변경한다.
			SM1004_01.ui.updateChangeUI();
    		
    		SM1004_01.control.selectAuthGroupList();
    	},
    	userAfterUI : function() {
    		var params = form.handle.getElementsParam("SM1004_01_form_02");
    		
    		SM1004_01.control.selectAuthUserList(params);
    	},
    	menuAfterUI : function() {
    		var params = form.handle.getElementsParam("SM1004_01_form_03");
    		
    		SM1004_01.control.selectMenuList(params);
        	SM1004_01.control.selectAuthMenuList(params);
    	}
    },
    control : {// 업무구현
    	selectAuthGroupList : function() {
    		var dg_1 = grid.getObject("SM1004_01_grid_01");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7010/selectAuthGroupList");
    	},
    	selectAuthUserList : function(rowData) {
    		var dg_1 = grid.getObject("SM1004_01_grid_02");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7010/selectAuthUserList", rowData);
    	},
    	selectMenuList : function(rowData) {
    		var dg_1 = grid.getObject("SM1004_01_grid_03");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7010/selectMenuList", rowData);
    	},
    	selectAuthMenuList : function(rowData) {
    		var dg_1 = grid.getObject("SM1004_01_grid_04");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7010/selectAuthMenuList", rowData);
    	},
    	updateAuthGroupInfo : function() {
    		if(!form.handle.isValidate("SM1004_01_form_01")) return;
    		
    		var flag = form.handle.getValue("SM1004_01_form_01", "flag");
    		var confMsg = "";
    		
    		if(flag == "insert") {
    			confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
    		} else {
    			confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
    		}
    		
    		$.messager.confirm(CNFIR, confMsg, function(cnf) {
                if(cnf) {
                	var obj = form.getObject("SM1004_01_form_01");
    	    		
    	    		if(flag == "insert") {
    	    			form.init.setURL("/fm/sm/sm7010/insertAuthGroupInfo");
    	    		}
    	    		if(flag == "update") {
    	    			form.init.setURL("/fm/sm/sm7010/updateAuthGroupInfo");
    	    		}
    	    		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
    	    		form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
    	    		form.init.setCallBackFunction("updateAfterUI");
    	    		
    	    		form.submit(obj);
                }
            });
    	},
    	insertAuthUserInfo : function(data) {
    		var dg_1 = grid.getObject("SM1004_01_grid_01");
    		var row1 = grid.handle.getSelectedRowData(dg_1);
    		
    		form.handle.setValue("SM1004_01_form_02", "COMPANY_CD", row1.COMPANY_CD);
    		form.handle.setValue("SM1004_01_form_02", "AUTH_GROUP_ID", row1.AUTH_GROUP_ID);
    		form.handle.setValue("SM1004_01_form_02", "USER_ID", data.USER_ID);
    		
    		var obj = form.getObject("SM1004_01_form_02");
    		
    		form.init.setURL("/fm/sm/sm7010/insertAuthUserInfo");
    		form.init.setCallBackFunction("userAfterUI");
    		
    		form.submit(obj);
    	},
    	deleteAuthUserInfo : function() {
    		var dg_1 = grid.getObject("SM1004_01_grid_01");
    		if(!grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_SELECT_GRID_FIRST") + "<br>(Grid : " + resource.getMessage("AUTHO, GROUP") + ")");
    			return;
    		}
    		var dg_2 = grid.getObject("SM1004_01_grid_02");
    		if(!grid.handle.isSelected(dg_2)) {
    			alert(resource.getMessage("MSG_SELECT_GRID_FIRST") + "<br>(Grid : " + resource.getMessage("USER, AUTHO") + ")");
    			return;
    		}
    		
    		var row1 = grid.handle.getSelectedRowData(dg_1);
    		var row2 = grid.handle.getSelectedRowData(dg_2);
    		
    		form.handle.setValue("SM1004_01_form_02", "COMPANY_CD", row1.COMPANY_CD);
    		form.handle.setValue("SM1004_01_form_02", "AUTH_GROUP_ID", row1.AUTH_GROUP_ID);
    		form.handle.setValue("SM1004_01_form_02", "USER_ID", row2.USER_ID);
    		
    		var obj = form.getObject("SM1004_01_form_02");
    		
    		form.init.setURL("/fm/sm/sm7010/deleteAuthUserInfo");
    		form.init.setCallBackFunction("userAfterUI");
    		
    		form.submit(obj);
    	},
    	insertAuthMenuInfo : function() {
    		var dg_3 = grid.getObject("SM1004_01_grid_03");
    		if(!grid.handle.isChecked(dg_3)) {
    			alert(resource.getMessage("MSG_NO_CHECK") + "<br>(Grid : " + resource.getMessage("MENU, LIST") + ")");
    			return;
    		}
    		
    		var row3 = grid.handle.getCheckedRowsData(dg_3);
    		var dg_1 = grid.getObject("SM1004_01_grid_01");
    		var row1 = grid.handle.getSelectedRowData(dg_1);
    		
    		form.handle.setValue("SM1004_01_form_03", "COMPANY_CD", row1.COMPANY_CD);
    		form.handle.setValue("SM1004_01_form_03", "AUTH_GROUP_ID", row1.AUTH_GROUP_ID);
    		form.handle.setValue("SM1004_01_form_03", "gridData", grid.util.convertJson2Rows(row3));
    		
    		var obj = form.getObject("SM1004_01_form_03");
    		
    		form.init.setURL("/fm/sm/sm7010/insertAuthMenuInfo");
    		form.init.setCallBackFunction("menuAfterUI");
    		
    		form.submit(obj);
    	},
    	deleteAuthMenuInfo : function() {
    		var dg_4 = grid.getObject("SM1004_01_grid_04");
    		if(!grid.handle.isChecked(dg_4)) {
    			alert(resource.getMessage("MSG_NO_CHECK") + "<br>(Grid : " + resource.getMessage("MSG_MENU_INVESTED_LIST") + ")");
    			return;
    		}
    		
    		var row4 = grid.handle.getCheckedRowsData(dg_4);
    		var dg_1 = grid.getObject("SM1004_01_grid_01");
    		var row1 = grid.handle.getSelectedRowData(dg_1);
    		
    		form.handle.setValue("SM1004_01_form_03", "COMPANY_CD", row1.COMPANY_CD);
    		form.handle.setValue("SM1004_01_form_03", "AUTH_GROUP_ID", row1.AUTH_GROUP_ID);
    		form.handle.setValue("SM1004_01_form_03", "gridData", grid.util.convertJson2Rows(row4));
    		
    		var obj = form.getObject("SM1004_01_form_03");
    		
    		form.init.setURL("/fm/sm/sm7010/deleteAuthMenuInfo");
    		form.init.setCallBackFunction("menuAfterUI");
    		
    		form.submit(obj);
    	}
    },
    dialog : {
    	openDialog_1 : function() {
    		var dg_1 = grid.getObject("SM1004_01_grid_01");
    		
    		if(!grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_SELECT_GRID_FIRST") + "<br>(Grid : " + resource.getMessage("AUTHO, GROUP") + ")");
    			return;
    		}
    		
    		var dl_1 = dialog.getObject("MMA009_01_dailog_01");
    		
    		dialog.init.setWidth(600);
    		dialog.init.setHeight(400);
    		dialog.init.setURL("/mm/pop/mmA009_01");
    		dialog.init.setQueryParams({TARGET_PID:"SM1004_01", COMPANY_CD:SESSION.COMPANY_CD});
    		dialog.init.setTitle(resource.getMessage("MMA009_01"));
    		dialog.open(dl_1);
    	},
    	callByMMA009_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA009_01_dailog_01");
    		
    		SM1004_01.control.insertAuthUserInfo(datas);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	}
    },
    // flag의 상태에 따라 UI를 변경한다.
    ui : {
    	insertChangeUI : function() {
    		form.handle.readonly("SM1004_01_form_01", "AUTH_GROUP_ID", false);
			form.handle.removeClass("SM1004_01_form_01", "AUTH_GROUP_ID", "input_readOnly");
    	},
    	updateChangeUI : function() {
    		form.handle.readonly("SM1004_01_form_01", "AUTH_GROUP_ID", true);
			form.handle.addClass("SM1004_01_form_01", "AUTH_GROUP_ID", "input_readOnly");
    	}
    },
    // 파일 input 엘리먼트 구현
    file : { },
    // 엑셀다운로드 구현
    excel : { }
    
};
