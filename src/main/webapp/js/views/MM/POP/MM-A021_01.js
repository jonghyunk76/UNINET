/******************************************************************************************
 * 작성자 : RHN 
 * Program Id : MMA021_01
 * 작업일자 : 2016.04.12
 ******************************************************************************************/

function onLoadPage() {
	MMA021_01.init.initComponent();
}

var MMA021_01 = {
    init : {
    	initComponent : function() {
    		MMA021_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {}, // 달력 그리드 생성
	combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
			["SEQ",                   resource.getMessage("RECOD,MSSNM")            , 80, "center", "center", true, false, null, null, null, 0, 0],
            ["CO_DOC_NO",             resource.getMessage("TXT_SEARCH_COO_CERTIFY_NO")/*확인서 번호*/    , 180, "center", "center", false, false, null, null, {format:"function", pid:"MMA021_01", name:"showVendorCoDetail"}, 0, 0],
			["VENDOR_CD",             resource.getMessage("PATNE, CODE")/*협력사코드*/  , 100, "center", "center", false, false, null, null, null, 0, 0],
			["VENDOR_NAME",           resource.getMessage("PATNE, NAME")/*협력사명*/    , 150, "left",   "center", false, false, null, null, null, 0, 0],
			["MODIFY_REQUEST_DATE",   resource.getMessage("REQ,DATE")/*요청일자*/, 100, "center", "center", false, false, null, null, {format:'date'}, 0, 0],
			["MODIFY_REQUEST_TIME",   resource.getMessage("REQ,HOUR")/*요청일자*/, 100, "center", "center", false, false, null, null, null, 0, 0],
			["UPDATE_BY",             resource.getMessage("USER, ID")/*발행일자*/   , 100, "center", "center", false, false, null, null, null, 0, 0],
			["ISSUE_DATE",            resource.getMessage("TXT_ISSUE_DATE")/*발행일자*/   , 100, "center", "center", false, false, null, null, {format:'date'}, 0, 0],
			["MRS_NM",                resource.getMessage("TXT_RESLT_STAT")/*처리상태*/   , 100, "center", "center", false, false, null, null, null, 0, 0],
            ["MODIFY_REQ",            resource.getMessage("REVSN,MSSNM")/*등록번호*/            , 80, "center", "center", true, false, null, null, null, 0, 0],
			["MODIFY_REQUEST_REASON", resource.getMessage("TXT_REQUEST_REASN")/*요청사유*/, 200, "left",   "center", false, false, null, null, null, 0, 0]
		],
		initGrid_1 : function() {
			var params = form.handle.getElementsParam("MMA021_01_form_01");
			var dg_1 = grid.getObject("MMA021_01_grid_01");
			
            grid.init.setPage(true);
            grid.init.setShowConfigPage(true);
            grid.init.setPageLayout(["resizeColumn"]);
            grid.init.setFitColumns(false);
            grid.init.setURL("/mm/pop/mmA021_01/selectMainList");
            grid.init.setEmptyMessage(false);
            grid.init.setQueryParams(params);
            grid.init.setCallBackFunction("changeUIButtomEnvent");
            grid.init.setCallBackFunctionForNoData("changeUIButtomEnvent");
            
            grid.event.setOnClickRow(dg_1);

			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
    	onClick_MMA021_01_grid_01 : function(rowData) {
			form.handle.setValue("MMA021_01_form_02", "MODIFY_REQUEST_REASON_VIEW", rowData.MODIFY_REQUEST_REASON);
		},
		changeUIButtomEnvent : function() {
			var dg_1 = grid.getObject("MMA021_01_grid_01");
    		var rows = grid.handle.getAllRows(dg_1);
    		
    		if(rows.length == 0) {
    			form.util.setVisible("MMA021_01_btn_01", true);
    		} else {
    			if(rows[0].MODIFY_REQUEST_STATUS == "R") {
    				form.util.setVisible("MMA021_01_btn_01", false);
    				if(form.handle.getValue("MMA021_01_form_01", "REGISTED_BY") != "1") form.util.setVisible("MMA021_01_btn_02", true);
    				form.util.setVisible("MMA021_01_btn_03", true);
    			} else {
    				form.util.setVisible("MMA021_01_btn_01", true);
    				form.util.setVisible("MMA021_01_btn_02", false);
    				form.util.setVisible("MMA021_01_btn_03", false);
    			}
    		}
		},
    	callBack_saveReqReason : function() {
    		this.changeUIButtomEnvent();
    		
    		MMA021_01.control.selectMainList();
    		
    		var pid = form.handle.getValue("MMA021_01_form_01", "TARGET_PID");
    		var pro_id = eval("window." + pid + ".control");
            if (!oUtil.isNull(pro_id)) {
                if (typeof(pro_id["selectMainList"]) == "function") {
                    pro_id["selectMainList"]();
                }
            }
    	}
    },
    control : {// 업무구현
    	selectMainList : function() {
    		var dg_1 = grid.getObject("MMA021_01_grid_01");
    		var params = form.handle.getElementsParam("MMA021_01_form_01");
    		
    		params.MODIFY_REQUEST_STATUS = "";
    		params.MODIFY_REQUEST_REASON = "";
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA021_01/selectMainList", params);
    	},
    	saveReqReason : function() {
    		if(!form.handle.isValidate("MMA021_01_form_01")) return;
    		
    		var dg_1 = grid.getObject("MMA021_01_grid_01");
    		var rows = grid.handle.getAllRows(dg_1);
    		
    		if(rows.length > 0) {
    			if(rows[0].MODIFY_REQUEST_STATUS == "R") {
    				alert(resource.getMessage("MSG_REWRITING_REQUESTING"));
    				return;
    			}
    		}
    		
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {                
                if(flag) {
                	var obj = form.getObject("MMA021_01_form_01");
                	
                    form.handle.setValue("MMA021_01_form_01", "MODIFY_REQUEST_STATUS", "R");
                    
                    form.init.setURL("/mm/pop/mmA021_01/updateReqReason");
                    form.init.setProgressFlag(false);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY")); /*요청하신 작업이 성공적으로 처리되었습니다.*/
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
                    form.init.setCallBackFunction("callBack_saveReqReason");
                    
                    form.submit(obj);
                }
            });
    	},
    	cancelReqReason : function() {
    		var dg_1 = grid.getObject("MMA021_01_grid_01");
    		var rows = grid.handle.getAllRows(dg_1);
    		
    		if(rows.length > 0) {
    			if(rows[0].MODIFY_REQUEST_STATUS != "R") {
    				alert(resource.getMessage("MSG_DELETE_REQ"));
    				return;
    			}
    		} else {
    			alert(resource.getMessage("MSG_DELETE_REQ"));
				return;
    		}
    		
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {                
                if(flag) {
                	var obj = form.getObject("MMA021_01_form_01");
                	
                    form.handle.setValue("MMA021_01_form_01", "MODIFY_REQUEST_STATUS", "N");
                    
                    form.init.setURL("/mm/pop/mmA021_01/updateReqReason");
                    form.init.setProgressFlag(false);
                    form.init.setValidationFlag(false);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY")); /*요청하신 작업이 성공적으로 처리되었습니다.*/
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
                    form.init.setCallBackFunction("callBack_saveReqReason");
                    
                    form.submit(obj);
                }
            });
    	}
    },
    dialog : {
    	openDialog : function() {
    		var dg_1 = grid.getObject("MMA021_01_grid_01");
    		var rows = grid.handle.getAllRows(dg_1);
        	var params = form.handle.getElementsParam("MMA021_01_form_01");
            
            params.TARGET_PID = "MMA021_01";
            params.flag = "update";
            if(rows.length > 0) params.MODIFY_REQ = rows[0].MAX_MODIFY_REQ;
            	
            // 팝업 셋팅(각 국가별 FTA 확인서 등록 페이지로 이동)
            if(SESSION.FTA_NATION == "MX") {
	            dl_1 = dialog.getObject("MMA019_01_dailog_01");
	            
	            dialog.init.setTitle(resource.getMessage("MMA019_01"));
	    		dialog.init.setURL("/mm/pop/mmA019_01");
	    		dialog.init.setQueryParams(params);
            } else if(SESSION.FTA_NATION == "KR" || SESSION.FTA_NATION == "VN") {
	            dl_1 = dialog.getObject("MMA017_01_dailog_01");
	            
	    		dialog.init.setTitle(resource.getMessage("MMA017_01"));
	    		dialog.init.setURL("/mm/pop/mmA017_01");
	    		dialog.init.setQueryParams(params);
            }
            
    		dialog.open(dl_1);
        },
        callByMMA019_01 : function(datas) {
        	DI3010_01.control.selectReceiveCoList();
        },
        callByMMA017_01 : function(datas) {
        	DI3010_01.control.selectReceiveCoList();
	 	},
    	closeDialog :function() {
    		var dl_1 = dialog.getObject("MMA021_01_dailog_01");
        	dialog.handle.close(dl_1);
    	},
    	openDialog_1 : function() {
    		var dg_1 = grid.getObject("MMA021_01_grid_01");
        	var params = new Object();
        	
        	params = grid.handle.getSelectedRowData(dg_1);
        	
            var dl_1 = dialog.getObject("MMA021_02_dialog_01");
            
            dialog.init.setWidth(950);
            dialog.init.setHeight(700);
    		dialog.init.setURL("/mm/pop/mmA021_02");
    		dialog.init.setQueryParams(params);
    		
    		dialog.open(dl_1);
        }
    },
    ui : {
    	showVendorCoDetail : function(value, row, index) {
	   		if(oUtil.isNull(value)) {
	   			return "";
			} else if(!oUtil.isNull(row.MODIFY_REQ)) {
				return "<a href=\"javascript:MMA021_01.dialog.openDialog_1()\">" +
                       "<img src=\"/images/icon/external_link.png\" boder=\"0\"/> "+value+"</a>";
			} else {
				return value;
			}
    	}
    },
    file : {},
    excel : {}
};
