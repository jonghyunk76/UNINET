/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MMA016_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA016_01.config.applyFtaNation();
    MMA016_01.init.initComponent();
}

var MMA016_01 = {
    
    init : {
    	tabIndex : 0,
        initComponent : function() {
        	MMA016_01.datagrid.initGrid_1();
        }
    },
    calendar : {},
    combobox : {}, // 콤보박스 그리드 생성
    tab : {},
    datagrid : {
    	header : [
    		["IF_NAME"              ,resource.getMessage("DTA,NAME")       /*배치명*/     ,150      ,"left"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
    		["FILE_NAME"            ,resource.getMessage("FILE,NAME")      /*샘플파일*/    ,30       ,"center"     ,"center"    ,true       ,false     ,null         ,null     ,{format:"function", pid:'MMA016_01', name:'fileDownloadIconUI'}    ,0        ,0]
		],
    	initGrid_1 : function() {
    		var params = form.handle.getElementsParam("MMA016_01_form_02");
            var dg_1 = grid.getObject("MMA016_01_grid_01");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/mmA016_02/selectExcelInterfaceList");
            grid.init.setFitColumns(true);
            grid.init.setShowHeader(false);
            grid.init.setRownumbers(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            grid.init.setCallBackFunction("selectExcelItemRow");
            
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header);
        }
    },
    event : {
    	selectExcelItemRow : function() {
    		var dg_1 = grid.getObject("MMA016_01_grid_01");
    		var datas = grid.handle.getAllRows(dg_1);
		    var leng = grid.handle.getRowCount(dg_1);
		    var ifCode = form.handle.getValue("MMA016_01_form_02", "IF_CD");
		    
		    if(oUtil.isNull(ifCode)) {
		    	ifCode = form.handle.getValue("MMA016_02_form_02", "IF_CD");
		    }
		    
		    for(var i = 0; i < leng; i++) {
		    	if(datas[i].IF_CD == ifCode) {
		    		form.handle.loadData("MMA016_01_form_01", datas[i]);
		    		form.handle.loadData("MMA016_01_form_02", datas[i]);
		    		grid.handle.selectRowIndex(dg_1, i);
		    		
		    		break;
		    	}
		    }
		    
		    if(!grid.handle.isSelected(dg_1) && leng > 0) {
		    	form.handle.loadData("MMA016_01_form_01", datas[0]);
		    	form.handle.loadData("MMA016_01_form_02", datas[0]);
		    	grid.handle.selectRowIndex(dg_1, 0);
            }
		    
		    var tabObj = tab.getObject("MMA016_01_tabs_01");
		    
		    tab.handle.reload(tabObj, "/mm/pop/mmA016_02");
    	},
    	onClick_MMA016_01_grid_01 : function(rowData) {
    		var currentIfcode = form.handle.getValue("MMA016_02_form_02", "IF_CD");
    		
    		form.handle.loadData("MMA016_01_form_01", rowData);
    		form.handle.loadData("MMA016_01_form_02", rowData);
    		form.handle.loadData("MMA016_01_form_03", rowData);
    		
    		var closeType = form.handle.getValue("MMA016_01_form_02", "SCHEDULE_CD");
    		
    		if(rowData.IF_CD == "RPT_MM_008" || rowData.IF_CD == "RPT_MM_009" || rowData.IF_CD == "RPT_SD_015" || rowData.IF_CD == "RPT_SD_016" || rowData.IF_CD == "RPT_SD_017") {
    			form.util.setVisible("MMA016_02_tr_01", false);
    		} else if(closeType == "MONTHLY_BATCH" || closeType == "DAILY_BATCH") {
    			form.util.setVisible("MMA016_02_tr_01", true);
    		}
    		
    		if(currentIfcode == rowData.IF_CD) return;
    		else {
    			var tabObj = tab.getObject("MMA016_01_tabs_01");
                
                tab.handle.close(tabObj, 2);
                tab.handle.close(tabObj, 1);
                
                MMA016_01.init.tabIndex = 0;
                
                combo.handle.loadData("MMA016_02_form_01", "SHEET_NAME", "[]");
    			MMA016_02.datagrid.initGrid_1([]);
    			
    			form.handle.loadData("MMA016_02_form_02", rowData);
            	form.handle.setValue("MMA016_02_form_01", "IF_NAME", rowData.IF_NAME);
            	
            	form.util.setVisible("MMA016_01_btn_01", true);
    			form.util.setVisible("MMA016_01_btn_02", true);
    			form.util.setVisible("MMA016_01_btn_03", true);
    			
    			form.util.removeClass("MMA016_01_btn_01", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_01", "btnDisableProc");
                form.util.removeClass("MMA016_01_btn_02", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_02", "btnDisableProc");
                form.util.removeClass("MMA016_01_btn_03", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_03", "btnDisableProc");
    		}
    	},
    	executeExcelBatchAfterUI : function(data) {
    		if(data.O_RETURN == "S") { // 정상적으로 batch가 완료된 경우
    			alert(resource.getMessage("MSG_SUCCESS_BODY"));
    			
    			form.util.setVisible("MMA016_01_btn_01", false);
    			form.util.setVisible("MMA016_01_btn_02", false);
    			form.util.setVisible("MMA016_01_btn_03", false);
    			
    			form.util.removeClass("MMA016_01_btn_01", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_01", "btnDisableProc");
                form.util.removeClass("MMA016_01_btn_02", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_02", "btnDisableProc");
    		} else {
    			alert(resource.getMessage("[,TXT_BATCH_ERROR,]") + "<br>" + data.O_RETURNMSG);
    			
        		form.util.removeClass("MMA016_01_btn_03", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_03", "btnDisableProc");
    		}
    		
    		var pid = form.handle.getValue("MMA016_01_form_01", "TARGET_PID");
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA016_01"]) == "function") {
                    pro_id["callByMMA016_01"](data);
                }
            }
    	},
    	executeChangeSource : function(data) {
    		var dg_2 = grid.getObject("MMA016_03_grid_02");
        	var tabObj = tab.getObject("MMA016_01_tabs_01");
            
            MMA016_03.control.selectExcelColmunList();
        	
        	// 3.완료 페이지 닫기
            tab.handle.close(tabObj, 2);
    	}
    },
    control : {
    	executeExcelBatch : function() {
    		if(form.util.hasClass("MMA016_01_btn_03", "btnDisableProc")) {
        		return;
        	}
    		
    		var type = form.handle.getValue("MMA016_01_form_02", "DATA_TYPE");
    		var warMsg;
    		
    		if(type == "C") {
    			warMsg = resource.getMessage("MSG_REREGISTERED");
    		} else {
    			var warMsg = resource.getMessage("MSG_CONFIRM_EXECUTE");
    		}
    		
    		$.messager.confirm(resource.getMessage("CNFIR"), warMsg, function(flag) {
                if (flag) {
                	var obj = form.getObject("MMA016_01_form_02");
                    
                    form.init.setURL("/mm/pop/mmA016_01/executeExcelBatch");
                    form.init.setCallBackFunction("executeExcelBatchAfterUI");
                    form.init.setValidationFlag(false);
                    form.init.setProgressFlag(true);
                    
                    form.submit(obj);
                }
    		});
    	},
    	cancelUpload : function() {
    		var dg_1 = dialog.getObject("MMA016_01_dailog_01");
    		
    		if(form.util.hasClass("MMA016_01_btn_01", "btnDisableProc") && form.util.hasClass("MMA016_01_btn_02", "btnDisableProc")) {
    			dialog.handle.close(dg_1);
    		} else {
	    		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_NOT_MODIFY_CLOSE"), function(flag) {
	                if (flag) {
			    		dialog.handle.close(dg_1);
	                }
	    		});
    		}
    	},
    	excelSampleDownload : function() {
    		var dg_1 = grid.getObject("MMA016_01_grid_01");

            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var obj = form.getObject("MMA016_01_form_01");
            
            form.init.setURL("/mm/pop/mmA016_01/excelSampleDownload");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
    	}
    },
    dialog : {},
    ui: {
        tabsBack : function(num) {
        	if(form.util.hasClass("MMA016_01_btn_01", "btnDisableProc")) {
        		return;
        	} else {
        		form.util.removeClass("MMA016_01_btn_02", "btnDisableProc");
                form.util.addClass("MMA016_01_btn_02", "btnEnableProc");
                
                form.util.removeClass("MMA016_01_btn_03", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_03", "btnDisableProc");
        	}
        	if(MMA016_01.init.tabIndex == 0) return;
        	
        	var tabObj = tab.getObject("MMA016_01_tabs_01");
        	var spanId = MMA016_01.init.tabIndex;
        	
        	form.util.removeClass("MMA016_01_span_0" + spanId, "excel_process_cmp");
        	form.util.addClass("MMA016_01_span_0" + spanId, "excel_process_sel");
        	
        	if(MMA016_01.init.tabIndex != 0) {
        		MMA016_01.init.tabIndex = MMA016_01.init.tabIndex - 1;
        	}
        	
        	tab.handle.select(tabObj, MMA016_01.init.tabIndex);
            
            spanId = spanId+1;
            
            form.util.removeClass("MMA016_01_span_0" + spanId, "excel_process_sel");
            form.util.addClass("MMA016_01_span_0" + spanId, "excel_process_cmp");
            
            if(MMA016_01.init.tabIndex == 0) {
            	form.util.removeClass("MMA016_01_btn_01", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_01", "btnDisableProc");
            }
        },
        tabsNext : function(num) {
        	if(form.util.hasClass("MMA016_01_btn_02", "btnDisableProc")) {
        		return;
        	} else {
        		form.util.removeClass("MMA016_01_btn_01", "btnDisableProc");
                form.util.addClass("MMA016_01_btn_01", "btnEnableProc");
        	}
        	if(MMA016_01.init.tabIndex == 2) return;
        	
        	var tabObj = tab.getObject("MMA016_01_tabs_01");
        	var url = "/mm/pop/mmA016_0" + (MMA016_01.init.tabIndex+3);
        	
        	MMA016_01.init.tabIndex = MMA016_01.init.tabIndex + 1;
        	
        	form.util.removeClass("MMA016_01_span_0" + MMA016_01.init.tabIndex, "excel_process_none");
        	form.util.removeClass("MMA016_01_span_0" + MMA016_01.init.tabIndex, "excel_process_sel");
        	form.util.addClass("MMA016_01_span_0" + MMA016_01.init.tabIndex, "excel_process_cmp");
        	
        	var tabId = "MMA016_01_tab_0" + MMA016_01.init.tabIndex;
            var tabTitle = "Step" + MMA016_01.init.tabIndex;
            var newTabInfo = {id:tabId, title:tabTitle, href:url};
            
            tab.handle.addTab(tabObj, newTabInfo, MMA016_01.init.tabIndex);
            
            var spanId = MMA016_01.init.tabIndex+1;
            
            form.util.removeClass("MMA016_01_span_0" + spanId, "excel_process_none");
        	form.util.removeClass("MMA016_01_span_0" + spanId, "excel_process_cmp");
            form.util.addClass("MMA016_01_span_0" + spanId, "excel_process_sel");
            
            if(MMA016_01.init.tabIndex == 2) {
            	form.util.removeClass("MMA016_01_btn_02", "btnEnableProc");
                form.util.addClass("MMA016_01_btn_02", "btnDisableProc");
                
                var callFunc = "changeCompleteButtom";
                try {
	                var funcPath = eval("window.MMA016_04.ui");
	            	
	            	if(!oUtil.isNull(funcPath)) {
	            		if(typeof(funcPath[callFunc]) == "function") {
	            			funcPath[callFunc]();
	            		}
	            	}
                } catch(e) {}
            }
        },
        fileDownloadIconUI : function(val, row, idx) {
        	var rst = val;
        	
    		if(!oUtil.isNull(val)) {
    			rst = "<a href=\"javascript:MMA016_01.control.excelSampleDownload();\" class=\"btnExportEmpty\"/></a>";
    		}
    		
    		return rst;
    	}
    },
    file : {},
    excel : {},
    config : {
    	applyFtaNation : function() {
    		var str = "";
    		var type = form.handle.getValue("MMA016_01_form_02", "DATA_TYPE");
    		
    		if(type == "C") {
    			str = "• " + resource.getMessage("TXT_DELTE_HSTORY_DATA") + "<br>• " + resource.getMessage("TXT_REGE_CLOSING_DATA");
    		} else {
    			str = "• " + resource.getMessage("TXT_MODI_REGI_DATA") + "<br>• " + resource.getMessage("TXT_TRANS_COMPLE");
    		}
    		
    		if($("#MMA016_01_div_01").children().length > 0) {
                $("#MMA016_01_div_01").html("");
            }
    		
    		$("#MMA016_01_div_01").append(str);
    	}
    }
};
