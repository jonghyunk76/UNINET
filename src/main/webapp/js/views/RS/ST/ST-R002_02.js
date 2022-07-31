/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : ST-R002_02
 * 작업일자 : 2022.06.17
 * 
 ******************************************************************************************/

function onLoadPage() {
	STR002_02.config.applyFtaNation();
    STR002_02.init.initComponent();
}

var STR002_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	STR002_02.datagrid.initGrid_1();
        	STR002_02.combobox.initCombo_1();
        	STR002_02.combobox.initCombo_2();
        	
        	var vFlag = form.handle.getValue("STR002_02_form_01", "flag");
        	
        	if (vFlag == "update") {
        		STR002_02.ui.updateChangeUI();
            } else {
            	STR002_02.ui.insertChangeUI();
            }
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
        data_2 : [
        	{CODE:"Y", NAME:resource.getMessage("CC_USE")},
        	{CODE:"N", NAME:resource.getMessage("CC_UNUSE")}
       	],
	    initCombo_1 : function() {
	        var obj_1 = combo.getObject("STR002_02_form_01", "SERVICE_TYPE");
	        combo.init.setRequired(true);
	        
	        combo.init.setURL("/mm/cbox/selectStandardCode");
	        combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"RS_IF_CNN_TYPE"});
	        combo.init.setValueField("CODE");
	        combo.init.setNameField("NAME");
	        
	        combo.create(obj_1);
	    },
    	initCombo_2 : function() {
    		var obj_2 = combo.getObject("STR002_02_form_01", "USE_YN");
    		
    		combo.init.setRequired(false);
    		combo.init.setData(this.data_2);
    		combo.init.setValueField("CODE");
    		combo.init.setNameField("NAME");
            
    		combo.create(obj_2);
    	}
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    editor : {
        gridSearchbox_1 : function(obj) {
            STR002_02.dialog.openDialog_1();
        },
        gridSearchbox_2 : function(obj) {
            STR002_02.dialog.openDialog_2('S');
        },
        gridCombo_1 : function(obj) {
            var opts = new Object;
            
            opts.url = "/mm/cbox/selectStandardCode";
            opts.queryParams = {COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"RS_RESEND_MTH", MESSAGE_CODE:"NOSEL"};
            opts.data = combo.util.getCacheData(opts.queryParams);
            if(oUtil.isNull(opts.data)) opts.url = "/mm/cbox/selectStandardCode";

            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            combo.util.putCacheData(opts, opts.queryParams);
            
            return opts;
        },
        gridCombo_2 : function(obj) {
            var opts = new Object;
            
            opts.url = "/mm/cbox/selectStandardCode";
            opts.queryParams = {COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"RS_CNN_SERVER_TYPE", MESSAGE_CODE:"NOSEL"};
            opts.data = combo.util.getCacheData(opts.queryParams);
            if(oUtil.isNull(opts.data)) opts.url = "/mm/cbox/selectStandardCode";

            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            combo.util.putCacheData(opts, opts.queryParams);
            
            return opts;
        },
        gridCombo_3 : function(obj) {
            var opts = new Object;
            
            opts.url = "/mm/cbox/selectStandardCode";
            opts.queryParams = {COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"RS_DATA_FORMAT", MESSAGE_CODE:"NOSEL"};
            opts.data = combo.util.getCacheData(opts.queryParams);
            if(oUtil.isNull(opts.data)) opts.url = "/mm/cbox/selectStandardCode";

            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            combo.util.putCacheData(opts, opts.queryParams);
            
            return opts;
        }
    },
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
            var params = form.handle.getElementsParam("STR002_02_form_01");
    		var dg_1 = grid.getObject("STR002_02_grid_01");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/rs/st/stR002_02/selectServicePlanList");
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            // 에디팅 모드 적용
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1); 
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}}, {params:{HEADER_ID:"header2"}}); // DB에서 그리드 해더 정보 획득
    	}
    },
    // 이벤트 처리
    event : {
    	updateAfterUI : function() {
    		STR002_01.control.selectMainList();
    	},
    	deleteAfterUI : function() {
    		STR002_01.control.selectMainList();
    		
    		var dl_1 = dialog.getObject("STR002_02_dailog_01");
    		dialog.handle.close(dl_1);
    	}
    },
    // 업무구현
    control : {
    	insertServiceMst : function() {
            if (!form.handle.isValidate("STR002_02_form_01")) return;
            
    		var iflag = form.handle.getValue("STR002_02_form_01","flag"); 
    		var msg = "";
            var dg_1 = grid.getObject("STR002_02_grid_01");
            
            grid.edit.endCellEdit(dg_1);
            
            if(iflag == "insert") msg = resource.getMessage("MSG_CONFIRM_SAVE");
            else msg = resource.getMessage("MSG_CONFIRM_UPDATE");
            
			$.messager.confirm(CNFIR, msg, function(flag) {
    			if (flag) {
                    var dg_data = grid.handle.getAllRows(dg_1);
                    
                    form.handle.setValue("STR002_02_form_01", "gridData", grid.util.convertJson2Rows(dg_data));
                    
    				var obj = form.getObject("STR002_02_form_01");
                    
    	            if(iflag == "insert") form.init.setURL("/rs/st/stR002_02/insertServiceMst");
                    else form.init.setURL("/rs/st/stR002_02/updateServiceMst");
    	            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
    	            form.init.setCallBackFunction("updateAfterUI");
    	            
    	            form.submit(obj);
                }
            });
    	},
    	deleteServiceMst : function() {
    		$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if(flag) {
                    var obj = form.getObject("STR002_02_form_01");

                    form.init.setURL("/rs/st/stR002_02/deleteServiceMst");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");

                    form.submit(obj);
                }
            });
    	}
    },
    // 다이얼로그 구현
    dialog : {
        openDialog_1 : function() {
            var params = form.handle.getElementsParam("STR002_02_form_01");
            
            params.TARGET_PID = "STR002_02";
            
            var dl_1 = dialog.getObject("SM7005_06_dailog_01");
            
            dialog.init.setQueryParams(params);
            dialog.init.setURL("/fm/sm/sm7005_06");
            dialog.init.setWidth(410);
            dialog.init.setHeight(500);
            
            dialog.open(dl_1);
        },
        callBySM7005_06 : function(rowData) {
            var dg_1 = grid.getObject("STR002_02_grid_01");
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            var selData = grid.handle.getSelectedRowData(dg_1);
            
            selData.IF_CD = rowData.IF_CD;
            selData.IF_NAME = rowData.IF_NAME;
            
            grid.handle.setChangeRowValue(dg_1, idx, selData); 
            
            var dl_1 = dialog.getObject("SM7005_06_dailog_01");
            dialog.handle.close(dl_1);
        },
        openDialog_2 : function(iFlag) {
            var params = form.handle.getElementsParam("STR002_02_form_01");
            
            params.TARGET_PID = "STR002_02";
            params.FIELD_ID = iFlag;
            
            var dl_1 = dialog.getObject("STR001_03_dailog_01");
            
            dialog.init.setQueryParams(params);
            dialog.init.setURL("/rs/st/stR001_03");
            dialog.init.setWidth(410);
            dialog.init.setHeight(500);
            
            dialog.open(dl_1);
        },
        callBySTR001_03 : function(rowData) {
            var dg_1 = grid.getObject("STR002_02_grid_01");
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            var selData = grid.handle.getSelectedRowData(dg_1);
            
            selData.SERVER_ID = rowData.SERVER_ID;
            selData.SERVER_NAME = rowData.SERVER_NAME;
            
            grid.handle.setChangeRowValue(dg_1, idx, selData); 
            
            var dl_1 = dialog.getObject("STR001_03_dailog_01");
            dialog.handle.close(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
    	insertChangeUI : function() {
    		form.util.setVisible("STR002_02_btn_02", false);
    	},
    	updateChangeUI  : function() {
    		form.handle.readonly("STR002_02_form_01", "SERVICE_ID", true);
    		form.handle.addClass("STR002_02_form_01", "SERVICE_ID", "input_readOnly");
    		form.util.setVisible("STR002_02_btn_02", true);
    	},
    	insertRow : function() {
            var dg_1 = grid.getObject("STR002_02_grid_01");
            var rowCnt = grid.handle.getRowCount(dg_1);
        	var data = STR002_02.util.getInitRowData(rowCnt+1);
        	
        	grid.handle.appendRow(dg_1, data);
        },
        deleteRow : function() {
        	var dg_1 = grid.getObject("STR002_02_grid_01");
        	
        	if(!grid.handle.isSelected(dg_1)) {
        		alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	var idx = grid.handle.getCurrentRowIndex(dg_1);
        	
        	grid.handle.removeRow(dg_1, idx);
        },
        moveRow : function(i, step) {
            var dg_1 = grid.getObject("STR002_02_grid_0"+i);
            var rows = grid.handle.getAllRows(dg_1);
            var row = grid.handle.getSelectedRowData(dg_1);
            
            if (row){
                var index = grid.handle.getCurrentRowIndex(dg_1);
                var chgIdx;
                
                if(step == 1 || step == -1) {
                    if(index == 0 && step == -1) return;
                    if(index == (rows.length-1) && step == 1) return;
                    
                    chgIdx = index+step;
                } else if(step == 0) {
                    chgIdx = 0;
                } else if(step == 9) {
                    chgIdx = rows.length-1;
                }
                
                rows.splice(index, 1); // 현재열에서 삭제
                rows.splice(chgIdx, 0, row); // 현재열에서 위아래있는 값을 저장
                
                dg_1.datagrid('loadData',rows);
                dg_1.datagrid('scrollTo',chgIdx);
                dg_1.datagrid('selectRow',chgIdx);
            }
        }
    }, 
    // 검색박스 구현
    searchbox : {},
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    },
    util : {
        getInitRowData : function(idx) {
            var dg_1 = grid.getObject("STR002_02_grid_01");
            var rowCnt = grid.handle.getRowCount(dg_1);
            var hcell = new Object();
            
            hcell.PLAN_NO            = rowCnt+1;
            hcell.IF_CD              = null;
            hcell.INTERFACE_MTH      = null;
            hcell.INTERFACE_MTH_NAME = null;
            hcell.SERVER_ID          = null;
            hcell.SERVER_NAME        = null;
            hcell.PROCESS_TYPE       = null;
            hcell.PROCESS_TYPE_NAME  = null;
            hcell.DATA_PATH          = null;
            hcell.TRAN_ROW_NUM       = null;
            hcell.PLAN_DESC          = null;
            hcell.ATTRIBUTE01        = "NONE";
            hcell.ATTRIBUTE02        = "NONE";
            hcell.ATTRIBUTE03        = "NONE";
            hcell.ATTRIBUTE04        = "NONE";
            hcell.ATTRIBUTE05        = null;
            hcell.ATTRIBUTE06        = null;
            hcell.ATTRIBUTE07        = null;
            hcell.ATTRIBUTE08        = null;
            hcell.ATTRIBUTE09        = null;
            hcell.ATTRIBUTE10        = null;
            hcell.PLAN_DESC          = null;
            hcell.REQ_FORMAT_NAME    = "None";
            hcell.IMP_FORMAT_NAME    = "None";
            hcell.EXP_FORMAT_NAME    = "None";
            hcell.RES_FORMAT_NAME    = "None";
            return hcell;
        }
    }
    
};
