/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7004_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7004_01.init.initComponent();
}

var SM7004_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            SM7004_01.combobox.initCombo_1();
            SM7004_01.combobox.initCombo_2();
            
            SM7004_01.datagrid.initGrid_1();
            SM7004_01.datagrid.initGrid_2();
        }
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {
        dataSearchType : [{CODE : "SCHEDULE_CD", NAME : resource.getMessage("SCHE, CODE")}, {CODE : "SCHEDULE_NAME", NAME : resource.getMessage("SCHE, NAME")}],
        initCombo_1 : function() {
            var objSearchType = combo.getObject("SM7004_01_form_01", "schKeyField");
            
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchType);
        },
        initCombo_2 : function() {
            var objSearchKeyLike = combo.getObject("SM7004_01_form_01", "schKeyLike");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchKeyLike);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
		header1 : [
			["SCHEDULE_CD"         , resource.getMessage("SCHE, CODE")  /*스케줄 코드*/, 120, "center", "center", true, false, null, null, null, 2, 0],
			["SCHEDULE_NAME"       , resource.getMessage("SCHE, NAME")  /*스케줄 명*/  , 120, "center", "center", true, false, null, null, null, 2, 0],
			["EXECUTION_PROGRAM"   , resource.getMessage("PROGM,NAME")  /*프로그램명*/ , 150, "left"  , "center", true, false, null, null, null, 2, 0],
			["APPLY_FROM_DATE"     , resource.getMessage("APYDT,DATE")  /*적용일자*/   , 95, "center", "center", true, false, null, null, {format:'date'}, 2, 0],
			["APPLY_TO_DATE"       , resource.getMessage("EXPIT,DATE")  /*만료일자*/   , 105, "center", "center", true, false, null, null, {format:'date'}, 2, 0],
			["SYSTEM_BATCH_YN_NAME", resource.getMessage("EXCUS,ORNOT") /*실행여부*/   , 100, "center", "center", true, false, null, null, null, 2, 0],
			["EXCUS,CYCLE"         , resource.getMessage("MSG_BATCH_NON_START, CYCLE") /*실행전 주기*/       , 120, "center", "center", true, false, null, null, null, 0, 4],
			["STATUS_NAME"         , resource.getMessage("STAUS")       /*상태*/      , 100, "center", "center", true, false, null, null, null, 2, 0],
			["STNDD_CYCLE"         , resource.getMessage("STNDD,CYCLE")       /*표준주기*/       , 120, "center", "center", true, false, null, null, null, 0, 4],
			["NEXT_TIME"           , resource.getMessage("NEXT,TXT_BATCH,HOUR")       ,  160, "center", "center", true, false, null, null, null, 2, 0]
		],
        header2 : [
        	["MONTH"  , resource.getMessage("MONTH") /*월*/  , 50, "center", "center", true, false, null, null, null, 0, 0],
        	["DAY"    , resource.getMessage("DAY")   /*일*/  , 80, "center", "center", true, false, null, null, null, 0, 0],
        	["HOUR"   , resource.getMessage("HOUR")  /*시간*/, 80, "center", "center", true, false, null, null, null, 0, 0],
        	["MINUTES", resource.getMessage("MINUT") /*분*/  , 50, "center", "center", true, false, null, null, null, 0, 0],
        	["COM_MONTH"  , resource.getMessage("MONTH") /*월*/  , 50, "center", "center", true, false, null, null, null, 0, 0],
        	["COM_DAY"    , resource.getMessage("DAY")   /*일*/  , 80, "center", "center", true, false, null, null, null, 0, 0],
        	["COM_HOUR"   , resource.getMessage("HOUR")  /*시간*/, 80, "center", "center", true, false, null, null, null, 0, 0],
        	["COM_MINUTES", resource.getMessage("MINUT") /*분*/  , 50, "center", "center", true, false, null, null, null, 0, 0]
        ],
        header3 : [
        	["groupName"     , resource.getMessage("GROUP, NAME")      , 90,  "center", "center", true, false, null, null, null, 0, 0],
        	["jobName"       , resource.getMessage("TXT_BATCH, NAME")  , 120, "center", "center", true, false, null, null, null, 0, 0],
        	["nextTime"      , resource.getMessage("TXT_BATCH,HOUR")   , 120, "center", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
			var dg_1 = grid.getObject("SM7004_01_grid_01");
			
			grid.init.setPage(true);
			grid.init.setEmptyMessage(false);
            grid.event.setOnDblClickRow(dg_1);
            
			Theme.defaultGrid(dg_1, this.header1, this.header2);
		},
		initGrid_2 : function() {
			var dg_1 = grid.getObject("SM7004_01_grid_02");
			
			grid.init.setPage(false);
			grid.init.setEmptyMessage(false);
            grid.event.setOnDblClickRow(dg_1);
            
			Theme.defaultGrid(dg_1, this.header3);
		}
    },
    // 이벤트 처리
    event : {
        onDblClick_SM7004_01_grid_01 : function(rowData) {
            SM7004_01.dialog.openDialog('update');
        },
        initAfterUI : function() {
        	SM7004_01.control.selectScheduleList();
        }
    },
    // 업무구현
    control : {
    	selectScheduleList : function() {
            if (!form.handle.isValidate("SM7004_01_form_01")) {
                return;
            }
    	    
    		var dg_1 = grid.getObject("SM7004_01_grid_01");
    		var params = form.handle.getElementsParam("SM7004_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7004_01/selectScheduleList", params);
    		
    		this.selectQuartzJobList();
    	},
    	initScheduleStatus : function() {
    		var dg_1 = grid.getObject("SM7004_01_grid_01");
    		
    		if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
    		
    		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_EXECUTE"), function(flag) {
                if (flag) {
                	var rowData = grid.handle.getSelectedRowData(dg_1);
            		var obj = form.getObject("SM7004_01_form_02");
        	        
            		form.handle.setValue("SM7004_01_form_02", "COMPANY_CD", SESSION.COMPANY_CD);
            		form.handle.setValue("SM7004_01_form_02", "SCHEDULE_CD", rowData.SCHEDULE_CD);
            		form.handle.setValue("SM7004_01_form_02", "INTERFACE_SCHEDULE_ID", rowData.INTERFACE_SCHEDULE_ID);
            		
            		form.init.setURL("/fm/sm/sm7004_02/updateScheduleInfo");
                    form.init.setCallBackFunction("initAfterUI");
                    
                    form.submit(obj);
                }
    		});
    	},
    	selectQuartzJobList : function() {
    		var dg_2 = grid.getObject("SM7004_01_grid_02");
    		var params = form.handle.getElementsParam("SM7004_01_form_01");
    		
    		grid.handle.sendRedirect(dg_2, "/fm/sm/sm7004_01/selectQuartzJobList", params);
    	},
    	executeStopScheduler : function() {
    		var dg_2 = grid.getObject("SM7004_01_grid_02");
    		
    		if (!grid.handle.isSelected(dg_2)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
    		
    		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_STOP"), function(flag) {
                if (flag) {
                	var rowData = grid.handle.getSelectedRowData(dg_2);
            		var obj = form.getObject("SM7004_01_form_02");
        	        
            		form.handle.setValue("SM7004_01_form_02", "COMPANY_CD", rowData.groupName);
            		form.handle.setValue("SM7004_01_form_02", "SCHEDULE_CD", rowData.jobName);
            		
            		form.init.setURL("/fm/sm/sm7004_01/executeStopScheduler");
                    form.init.setCallBackFunction("initAfterUI");
                    
                    form.submit(obj);
                }
    		});
    	}
    },
    // 다이얼로그 구현
    dialog : {
        openDialog : function(flag) {
            var dg_1 = grid.getObject("SM7004_01_grid_01");
            var dl_1 = dialog.getObject("SM7004_02_dailog_01");
            
            if (flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("SM7004_01_form_01");
            var params2 = {};
            
            params1.flag = params1.flag || flag;
            
            if (flag != "insert") {
                params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);
            
            // 팝업 셋팅
            // title:스케쥴 상세 조회 및 관리
            dialog.init.setTitle(resource.getMessage("SM7004_02"));
            dialog.init.setWidth(1024);
            dialog.init.setHeight(800);
            dialog.init.setURL("/fm/sm/sm7004_02");
            dialog.init.setResizable(true);
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        },
        openDialog_1 : function() {
        	var dg_1 = grid.getObject("SM7004_01_grid_01");
            var dl_4 = dialog.getObject("SM7004_04_dailog_01");
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = new Object();
            var params1 = form.handle.getElementsParam("SM7004_01_form_01");
            var params2 = grid.handle.getSelectedRowData(dg_1);
            
            $.extend(params, params1, params2);
            
            // 팝업 셋팅
            // title:스케쥴 상세 조회 및 관리
            dialog.init.setTitle(resource.getMessage("SM7004_04"));
            dialog.init.setWidth(1200);
            dialog.init.setHeight(800);
            dialog.init.setURL("/fm/sm/sm7004_04");
            dialog.init.setResizable(true);
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_4);
        }
    },
    // 파일 input 엘리먼트 구현
    file : {
    	
    },
    // 엑셀다운로드 구현
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("SM7004_01_grid_01");
            var fobj = form.getObject("SM7004_01_form_01");
            
            form.init.setURL("/fm/sm/sm7004_01/selectScheduleList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("SCHE, TXT_LIST"), resource.getMessage("SCHE, TXT_LIST"));
        }
    }
	
};
