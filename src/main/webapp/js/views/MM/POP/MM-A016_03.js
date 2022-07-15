/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MMA016_03
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA016_03.config.applyFtaNation();
    MMA016_03.init.initComponent();
}

var MMA016_03 = {
    
    init : {
        initComponent : function() {
        	var data = form.handle.getElementsParam("MMA016_01_form_01");
        	form.handle.loadData("MMA016_03_form_01", data);
        	
        	MMA016_03.combobox.initCombo_1();
        	
        	MMA016_03.datagrid.initGrid_1();
        	MMA016_03.datagrid.initGrid_2();
        }
    },
    calendar : {},
    combobox : {
    	initCombo_1 : function() {
    		var params = form.handle.getElementsParam("MMA016_03_form_01");
            var obj = combo.getObject("MMA016_03_form_01", "COLUMN_ID");
            
            combo.init.setURL("/mm/pop/mmA016_02/selectJcoColumnList");
            combo.init.setQueryParams({IF_CD:form.handle.getValue("MMA016_03_form_01", "IF_CD")});
            combo.init.setValueField("COLUMN_TRANS");
            combo.init.setNameField("COLUMN_DESC");
            combo.init.setCallFunction("changeColumnId");
            
            combo.create(obj);
        }
    }, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
        header_1 : [
        	["COLUMN_NAME",            resource.getMessage("SOURC, TXT_COLUMN"),    150, "left",   "center", true, false, null, null, {format:"function", pid:'MMA016_03', name:'updateCellColumnUi'}, 0, 0],
        	["TARGET_COLUMN_NAME",     resource.getMessage("TARGT, TXT_COLUMN"),    150, "left",   "center", true, false, null, null, null, 0, 0]
        ],
        header_2 : [
        	["CELL_NAME",     resource.getMessage("CELL,MSSNM"),            50,  "center", "center", true, false, null, null, null, 0, 0],
        	["COLUMN_VALUE", resource.getMessage("SOURC, DTA"), 150, "left",   "center", true, false, null, null, null, 0, 0],
        	["ERROR_NAME", resource.getMessage("VFCTN,RSULT"), 70, "center",   "center", true, false, null, null, null, 0, 0],
        	["ERROR_MSG", resource.getMessage("ERROR, MSMGT"), 100, "left",   "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA016_03_form_01");
            var dg_1 = grid.getObject("MMA016_03_grid_01");
            
            grid.init.setURL("/mm/pop/mmA016_02/selectExcelColmunList");
            grid.init.setQueryParams(params);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            grid.init.setCallBackFunction("selectExcelColmunRow");
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
            var dg_1 = grid.getObject("MMA016_03_grid_02");
            
            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            Theme.defaultGrid(dg_1, this.header_2);
        }
    },
    event : {
    	onClick_MMA016_03_grid_01 : function(rowData) {
    		jQuery("#MMA016_03_div_01").empty();
    		
    		$("#MMA016_03_div_01").append(rowData.ERROR_MSG_T);
    		
    		if(rowData.TARGET_COLUMN_NAME == "-") {
    			combo.handle.setValue("MMA016_03_form_01", "COLUMN_ID", "");
    		} else {
    			combo.handle.setValue("MMA016_03_form_01", "COLUMN_ID", rowData.COLUMN_ID);
    		}
    		form.handle.setValue("MMA016_03_form_01", "CELL_ID", rowData.CELL_ID);
    		
    		MMA016_03.control.selectExcelValueList(rowData);
        },
        selectExcelColmunRow : function(rowDatas) {
        	var dg_1 = grid.getObject("MMA016_03_grid_01");
        	var curIdx = grid.handle.getCurrentRowIndex();
        	
        	if(oUtil.isNull(curIdx)) {
        		return;
        	} else {
	        	grid.handle.selectRowIndex(dg_1, curIdx);
	        	this.onClick_MMA016_03_grid_01(grid.handle.getCurrentRowData(dg_1));
        	}
        },
        changeColumnId : function(data) {
        	MMA016_03.control.selectJcoColumnInfo();
        },
        selectJcoColumnInfoAfterUI : function(datas) {
        	form.handle.loadData("MMA016_03_form_01", datas);
        },
        updateTargetColumnAfterUI : function() {
        	var dg_2 = grid.getObject("MMA016_03_grid_02");
        	var tabObj = tab.getObject("MMA016_01_tabs_01");
            
            MMA016_03.control.selectExcelColmunList();
        	
        	// 3.완료 페이지 닫기
            tab.handle.close(tabObj, 2);
        }
    },
    control : {
    	selectExcelColmunList : function() {
    		var params = form.handle.getElementsParam("MMA016_03_form_01");
            var dg_1 = grid.getObject("MMA016_03_grid_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA016_02/selectExcelColmunList", params);
        },
        selectExcelValueList : function(rowData) {
            var dg_1 = grid.getObject("MMA016_03_grid_01");
            var dg_2 = grid.getObject("MMA016_03_grid_02");
            
            if(oUtil.isNull(rowData)) {
            	rowData = grid.handle.getSelectedRowData(dg_1);
            }
            
            if(form.handle.isChecked("MMA016_03_form_02", "errorCheck")) {
            	rowData.ERROR_CODE = "E";
      		} else {
      			rowData.ERROR_CODE = "S";
      		}
            
            grid.handle.sendRedirect(dg_2, "/mm/pop/mmA016_02/selectExcelValueList", rowData);
        },
        selectJcoColumnInfo : function(rowData) {
            var obj = form.getObject("MMA016_03_form_01");
            
            form.init.setURL("/mm/pop/mmA016_02/selectJcoColumnInfo");
            form.init.setCallBackFunction("selectJcoColumnInfoAfterUI");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        updateTargetColumn : function() {
        	var dg_1 = grid.getObject("MMA016_03_grid_01");
        	
		    if (!grid.handle.isSelected(dg_1)) {
		        alert(resource.getMessage("MSG_NO_SELECT"));
		        return;
		    }
		    
		    var colId = combo.handle.getValue("MMA016_03_form_01", "COLUMN_ID");
		    
		    if(!oUtil.isNull(colId)) {
			    var datas = grid.handle.getAllRows(dg_1);
			    var leng = grid.handle.getRowCount(dg_1);
			    
			    for(var i = 0; i < leng; i++) {
			    	if(datas[i].COLUMN_ID == colId) {
			    		alert(resource.getMessage(TXT_SELECT_COL_DUP_CHECKE, [i+1]));
			    		return;
			    	}
			    }
		    }
		    
		    $.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_UPDATE"), function(flag) {
                if (flag) {
                	form.handle.setValue("MMA016_03_form_01", "YYYYMM", form.handle.getValue("MMA016_02_form_02", "YYYYMM"));
                	
                	var obj = form.getObject("MMA016_03_form_01");
                    
                    form.init.setURL("/mm/pop/mmA016_02/updateTargetColumn");
                    form.init.setSucceseMessage(resource.getMessage("MSG_MODIFY_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
                    form.init.setCallBackFunction("updateTargetColumnAfterUI");
                    form.init.setValidationFlag(false);
                    
                    form.submit(obj);
                }
            });
        },
        deleteSourceColumn : function() {
        	var dg_1 = grid.getObject("MMA016_03_grid_01");
        	
		    if (!grid.handle.isSelected(dg_1)) {
		        alert(resource.getMessage("MSG_DELETE_SELECT"));
		        return;
		    }
		    
		    var rowData = grid.handle.getSelectedRowData(dg_1);
		    
		    if(!oUtil.isNull(rowData.COLUMN_ID)) {
		    	alert(resource.getMessage("TXT_TARGET_NO_DELETE"));
		        return;
		    }
		    
		    $.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                	var obj = form.getObject("MMA016_03_form_01");
                    
                    form.init.setURL("/mm/pop/mmA016_02/deleteSourceColumn");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
                    form.init.setCallBackFunction("updateTargetColumnAfterUI");
                    form.init.setValidationFlag(false);
                    
                    form.submit(obj);
                }
            });
        },
        executeChangeSource : function() {
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                	var dg_1 = grid.getObject("MMA016_01_grid_01");
                	
                	var rows = grid.handle.getSelectedRowData(dg_1);
                	
                	form.handle.setValue("MMA016_01_form_03", "YYYYMM", form.handle.getValue("MMA016_02_form_02", "YYYYMM"));
                	form.handle.setValue("MMA016_01_form_03", "IF_CD", rows.IF_CD);
                	
                	var obj = form.getObject("MMA016_01_form_03");
                    
                    form.init.setURL("/mm/pop/mmA016_02/executeChangeSource");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
                    form.init.setCallBackFunction("executeChangeSource");
                    form.init.setValidationFlag(false);
                    
                    form.submit(obj);
                }
            });
        }
    },
    dialog : {},
    ui : {
    	updateCellColumnUi : function(val, row, idx) {
    		if(!oUtil.isNull(row.ERROR_YN) && row.ERROR_YN == "Y") {
    			if(row.TARGET_COLUMN_NAME == "-") {
    				return "<img src=\"/images/icon/emp_warning.png\"/> " + val;
    			} else {
    				return "<img src=\"/images/icon/in_warning.png\"/> " + val;
    			}
    		}
    		
    		return val;
    	}
    },
    file : {},
    excel : {},
    config : {
        applyFtaNation : function() {}
    }
    
};
