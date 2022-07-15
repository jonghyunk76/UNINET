/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : ST-R003_01
 * 작업일자 : 2022.07.11
 * 
 ******************************************************************************************/

function onLoadPage() {
	STR003_01.config.applyFtaNation();
    STR003_01.init.initComponent();
}

var STR003_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	STR003_01.calendar.initCalendar_1();
        	STR003_01.calendar.initCalendar_2();
        	
        	STR003_01.combobox.initCombo_1();
        	STR003_01.combobox.initCombo_2();
        	STR003_01.combobox.initCombo_3();
        	
        	STR003_01.datagrid.initGrid_1();
        	STR003_01.datagrid.initGrid_2();
        }
    }, 
    // 달력 생성
    calendar : {
    	initCalendar_1 : function() {
            var obj_01 = calendar.getObject("STR003_01_form_01", "SEH_FORM_DATE");
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
            var obj_01 = calendar.getObject("STR003_01_form_01", "SEH_TO_DATE");
            
            calendar.create(obj_01);
        }
    }, 
    // 콤보박스 생성
    combobox : {
		initCombo_1 : function() {
            var obj_01 = combo.getObject("STR003_01_form_01", "TRANS_STATUS");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"TRANS_STATUS", MESSAGE_CODE:"SELET"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj_01);
        },
        data_1 : [
    		{CODE:"COMPANY_CD", NAME:resource.getMessage("TXT_COMPANY_CODE")},
    		{CODE:"SERVICE_HISTORY_ID", NAME:resource.getMessage("CC_서비스ID")},
    		{CODE:"INTERFACE_HISTORY_ID", NAME:resource.getMessage("CC_인터페이스ID")}
		],
        initCombo_2 : function() {
			var obj = combo.getObject("STR003_01_form_01", "schKeyField");
			
			combo.init.setData(this.data_1);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
        initCombo_3 : function() {
			var obj = combo.getObject("STR003_01_form_01", "schKeyLike");
			
			combo.init.setURL("/mm/cbox/selectStandardCode");
			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
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
            var dg_1 = grid.getObject("STR003_01_grid_01");
            
            grid.init.setPage(true);
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            // 이벤트 설정
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        },
	    initGrid_2 : function() {
	    	var dg_2 = grid.getObject("STR003_01_grid_02");
	    	
	    	grid.init.setPage(true);
	    	grid.init.setFilter(true); // 그리드  해더에 필터 적용
	    	grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
	    	grid.init.setRecallFunction("initGrid_1"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
	    	// 이벤트 설정
            grid.event.setOnDblClickRow(dg_2);
            
	    	Theme.defaultGrid(dg_2, {params:{HEADER_ID:"header1"}});
	    }
    },
    // 이벤트 처리
    event : {
    	onClick_STR003_01_grid_01 : function(rowData) {
            form.handle.loadData("STR003_01_form_02", rowData);
            form.handle.loadData("STR003_01_form_03", rowData);
            
    		STR003_01.control.selectInterfaeHistoryList(rowData);
    	},
        onDblClick_STR003_01_grid_02 : function(rowData) {
            STR003_01.dialog.openDialog_1();
        },
        executeRelayReBatch : function(datas) {
            var dg_1 = grid.getObject("STR003_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            STR003_01.control.selectInterfaeHistoryList(rowData);
        }
    },
    // 업무구현
    control : {
    	selectServiceHistoryList : function() {
            var dg_2 = grid.getObject("STR003_01_grid_02");
            
            grid.handle.clearAll(dg_2);
            
    		var dg_1 = grid.getObject("STR003_01_grid_01");
    		var params = form.handle.getElementsParam("STR003_01_form_01");
    		
            
            
    		grid.handle.sendRedirect(dg_1, "/rs/st/stR003_01/selectServiceHistoryList", params);
    	},
    	selectInterfaeHistoryList : function(rowData) {
    		var dg_2 = grid.getObject("STR003_01_grid_02");
    		var params = form.handle.getElementsParam("STR003_01_form_02");
    		
    		grid.handle.sendRedirect(dg_2, "/rs/st/stR003_01/selectInterfaeHistoryList", params);
    	},
        executeRelayReBatch : function() {
            var dg_1 = grid.getObject("STR003_01_grid_01");
            
            if(!grid.handle.isSelected(dg_1)){ //선택안됐다면
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            $.messager.confirm(CNFIR, "서비스를 다시 실행하시겠습니까?", function(flag) {
                if (flag) {
                    var obj = form.getObject("STR003_01_form_03");
                    
                    form.init.setURL("/fm/sm/SM8001_02/executeRelayBatch");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("executeRelayReBatch");
                    form.init.setProgressFlag(true);
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {
        openDialog_1 : function() {
            var dg_1 = grid.getObject("STR003_01_grid_02");
            
            if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var params = grid.handle.getSelectedRowData(dg_1);
            
            params.IF_CODE = params.IF_CD;
            params.TARGET_PID = "STR003_01";
            
            var dl_1 = dialog.getObject("MMA027_01_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("MMA027_01"));
            dialog.init.setQueryParams(params);
            dialog.init.setURL("/mm/pop/mmA027_01");
            dialog.init.setWidth(1024);
            dialog.init.setHeight(768);
            dialog.init.setResizable(false);
            
            dialog.open(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {}, 
    // 검색박스 구현
    searchbox : {},
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("STR003_01_grid_01");
            var fobj = form.getObject("STR003_01_form_01");
            
            form.init.setURL("/rs/st/stR003_01/selectServiceHistoryList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("CC_서비스이력"), resource.getMessage("CC_서비스이력"));
        },
	    excelDownload_2 : function() {
	    	var dg_2 = grid.getObject("STR003_01_grid_02");
	    	var fobj_2 = form.getObject("STR003_01_form_02");
	    	
	    	form.init.setURL("/rs/st/stR003_01/selectInterfaeHistoryList.fle");
	    	
	    	// parameter description : grid객체, form객체, 파일명, 시트명
	    	form.excelSubmit(dg_2, fobj_2, resource.getMessage("CC_인터페이스이력"), resource.getMessage("CC_인터페이스이력"));
	    }
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
