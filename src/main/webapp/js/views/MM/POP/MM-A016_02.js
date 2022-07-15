/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MMA016_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA016_02.config.applyFtaNation();
    MMA016_02.init.initComponent();
}

var MMA016_02 = {
    
    init : {
    	runningYn : false,
        initComponent : function() {
        	MMA016_02.calendar.initCalendar_1();
        	
        	MMA016_02.combobox.initCombo_1();
        	MMA016_02.combobox.initCombo_2();
        	
        	MMA016_02.datagrid.initGrid_1([]);
        	
        	var data = form.handle.getElementsParam("MMA016_01_form_01");
        	form.handle.loadData("MMA016_02_form_02", data);
        	form.handle.setValue("MMA016_02_form_01", "IF_NAME", data.IF_NAME);
        	
        	// 파일박스 이벤트 설정
        	var obj = form.getObject("MMA016_02_form_02", "uploadExcelFile");
    		obj.change(function() {
    			combo.handle.loadData("MMA016_02_form_01", "SHEET_NAME", "[]");
    			MMA016_02.datagrid.initGrid_1([]);
            });
    		
    		var closeType = form.handle.getValue("MMA016_01_form_02", "SCHEDULE_CD");
    		var ifCd = form.handle.getValue("MMA016_02_form_02", "IF_CD");
    		
    		if(ifCd == "RPT_MM_008" || ifCd == "RPT_MM_009" || ifCd == "RPT_SD_015" || ifCd == "RPT_SD_016" || ifCd == "RPT_SD_017") {
    			form.util.setVisible("MMA016_02_tr_01", false);
    		} else if(closeType == "MONTHLY_BATCH" || closeType == "DAILY_BATCH") {
    			form.util.setVisible("MMA016_02_tr_01", true);
    		}
        }
    },
    calendar : {
    	initCalendar_1 : function() {
    		var iniDate = form.handle.getValue("MMA016_01_form_02", "YYYYMM");
    		var obj_01 = calendar.getObject("MMA016_02_form_02", "YYYYMM_CAL");
            
    		calendar.init.setDateType("month");
            if(oUtil.isNull(iniDate)) {
            	calendar.init.setInitDate(calendar.util.getFirstDay2String(SESSION.WORK_YYYY_MM, "-"));
            } else {
            	calendar.init.setInitDate(calendar.util.getFirstDay2String(iniDate, "-"));
            }

            calendar.create(obj_01);
        }
    },
    combobox : {
    	data_1 : [{CODE:"value", NAME:resource.getMessage("DTA,NAME")},
		          {CODE:"cell", NAME:resource.getMessage("CELL,MSSNM")}
		],
    	initCombo_1 : function() {
            var obj_1 = combo.getObject("MMA016_02_form_01", "SHEET_NAME");
            
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setReadonly(false);
            combo.init.setCallFunction("onChangeReupload1");
            
            combo.create(obj_1);
        },
        initCombo_2 : function() {
            var obj_1 = combo.getObject("MMA016_02_form_01", "VIEW_TYPE");
            
            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setCallFunction("onChangeReupload2");
            
            combo.create(obj_1);
        }
    },
    datagrid : {
        initGrid_1 : function(header) {
        	var params = form.handle.getElementsParam("MMA016_02_form_02");
        	if(header.length == 0) {
        		params = [];
        	}
            var dg_1 = grid.getObject("MMA016_02_grid_01");
            
            grid.init.setPage(true);
            grid.init.setURL("/mm/pop/mmA016_02/selectExcelData");
            grid.init.setQueryParams(params);
            grid.init.setRownumbers(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            Theme.defaultGrid(dg_1, header);
        }
    },
    event : {
    	executeExcelUploadAfterUI : function(data) {
    		try {
	    		if(!oUtil.isNull(data.Header)) {
		    		var header = Theme.util.makeHeaderForDataMap(data.Header);
		    		
		    		MMA016_02.datagrid.initGrid_1(header);
	    		}
	    		
	    		if(!oUtil.isNull(data.Sheet)) {
	    			var sheet = eval(data.Sheet);
	    			
	    			combo.handle.loadData("MMA016_02_form_01", "SHEET_NAME", sheet);
	    			
	    			if(!oUtil.isNull(data.SHEET_NAME)) {
	    				combo.handle.setValue("MMA016_02_form_01", "SHEET_NAME", data.SHEET_NAME);
	    			}
	    		}
    		} catch(e) {
    			alert(resource.getMessage("MSG_FAILED_BODY"));
    		}
    		
    		var closeDate = calendar.handle.getDate("MMA016_02_form_02", "YYYYMM_CAL");
        	form.handle.setValue("MMA016_01_form_02", "YYYYMM", closeDate);
        	
    		form.util.setVisible("MMA016_02_div_01", true);
    		combo.handle.readonly("MMA016_02_form_01", "SHEET_NAME", false);
    		
    		form.util.removeClass("MMA016_01_btn_02", "btnDisableProc");
            form.util.addClass("MMA016_01_btn_02", "btnEnableProc");
            
            // 2.데이터 검증과 3.완료 페이지 닫기
            var tabObj = tab.getObject("MMA016_01_tabs_01");
            
            tab.handle.close(tabObj, 2);
            tab.handle.close(tabObj, 1);
            
            MMA016_02.init.runningYn = false;
    	},
    	onChangeReupload1 : function(data) {
    		var uploadFile = form.handle.getValue("MMA016_02_form_02", "uploadExcelFile");
            
            if (oUtil.isNull(uploadFile) || uploadFile.length <= 0) {
                return;
            }
            
    		if(!oUtil.isNull(data) && !MMA016_02.init.runningYn) {
    			MMA016_02.control.executeExcelUpload();
    		}
    	},
    	onChangeReupload2 : function(data) {
    		var uploadFile = form.handle.getValue("MMA016_02_form_02", "uploadExcelFile");
            
            if (oUtil.isNull(uploadFile) || uploadFile.length <= 0) {
                return;
            }
            
    		if(!oUtil.isNull(data)&& !MMA016_02.init.runningYn) {
    			MMA016_02.control.executeExcelUpload();
    		}
    	}
    },
    control : {
    	executeExcelUpload : function() {
    		var ifCode = form.handle.getValue("MMA016_02_form_02", "IF_CD");
    		
    		if(oUtil.isNull(ifCode)) {
    			alert(resource.getMessage("DTA,MSG_CHOICE_BOMBO"));
    			return;
    		}
    		
    		var uploadFile = form.handle.getValue("MMA016_02_form_02", "uploadExcelFile");
            
            if(oUtil.isNull(uploadFile) || uploadFile.length <= 0) {
                alert(resource.getMessage("MSG_REQUIRED_ATTACH_EXCEL_FILE"));
                return;
            }
            
            var skip = form.handle.getValue("MMA016_02_form_01", "SKIP_COL_NO");
    		var sheet = combo.handle.getValue("MMA016_02_form_01", "SHEET_NAME");
    		var view = combo.handle.getValue("MMA016_02_form_01", "VIEW_TYPE");
    		
    		form.handle.setValue("MMA016_02_form_02", "SKIP_COL_NO", skip);
    		form.handle.setValue("MMA016_02_form_02", "SHEET_NAME", sheet);
    		form.handle.setValue("MMA016_02_form_02", "VIEW_TYPE", view);
    		
    		MMA016_02.init.runningYn = true;
    		
    		var obj = form.getObject("MMA016_02_form_02");
            
            form.init.setURL("/mm/pop/mmA016_02/executeExcelUpload");
            form.init.setCallBackFunction("executeExcelUploadAfterUI");
            
            form.submit(obj);
    	}
    },
    dialog : {},
    ui : {},
    file : {},
    excel : {},
    config : {
        applyFtaNation : function() {
        	var closeType = form.handle.getValue("MMA016_01_form_02", "SCHEDULE_CD");
        	
        	if(closeType == "MONTHLY_BATCH" || closeType == "DAILY_BATCH") {
        		form.util.setVisible("MMA016_02_tr_01", true);
        	} else {
        		form.util.setVisible("MMA016_02_tr_01", false);
        	}
        }
    }
    
};
