/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B008_01
 * 작업일자 : 2020.05.21
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB008_01.config.applyFtaNation();
    MMB008_01.init.initComponent();
}

var MMB008_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB008_01.combobox.initCombo_1();
        	MMB008_01.datagrid.initGrid_1();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	data_1 : [
    		{CODE:"F", NAME:resource.getMessage("CC_앞")},
    		{CODE:"B", NAME:resource.getMessage("CC_뒤")}
    	],
    	initCombo_1 : function() {
	        var obj = combo.getObject("MMB008_01_form_01", "MTRIL_PRCSS_DRC");
	
	        combo.init.setData(this.data_1);
	        combo.init.setValueField("CODE");
	        combo.init.setNameField("NAME");
	        combo.init.setEditable(false);
	
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
    		var params = form.handle.getElementsParam("MMB008_01_form_02");
    		var dg_1 = grid.getObject("MMB008_01_grid_01");
            
    		grid.init.setURL("/mm/pop/MMB008_01/selectStandardEntrUploList");
    		grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setSelectCurrentRow(true);
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            grid.init.setRecallFunction("initGrid_1");
            // 에디팅 모드 적용
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);  
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
    	callbackSaveExcelSample : function() {
    		MMB008_01.control.selectStandardEntrUploList();
    		
    		if(form.handle.getValue("MMB008_01_form_01", "TARGET_PID") == "IC1004_01") {
    			IC1004_01.control.selectImportOnetapList();
    		}
    	},
    	deleteStandardEntrUplo : function() {
    		MMB008_01.control.selectStandardEntrUploList();
    		
    		if(form.handle.getValue("MMB008_01_form_01", "TARGET_PID") == "IC1004_01") {
    			IC1004_01.control.selectImportOnetapList();
    		}
    	},
        updateStandardEntrUploadNo : function() {
            MMB008_01.control.selectStandardEntrUploList();
        }
    },
    // 업무구현
    control : {
    	selectStandardEntrUploList : function() {
    		var dg_1 = grid.getObject("MMB008_01_grid_01");
    		var params = form.handle.getElementsParam("MMB008_01_form_02");

    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB008_01/selectStandardEntrUploList", params);	
    	},
    	saveExcelSample : function() {
            // from 객체 획득
            var obj = form.getObject("MMB008_01_form_01");
            
            var vUploadExcelFile = form.handle.getValue("MMB008_01_form_01", "uploadExcelFile");
            
            if(oUtil.isNull(vUploadExcelFile) || vUploadExcelFile.length <= 0) {
                alert(resource.getMessage("MSG_REQUIRED_ATTACH_EXCEL_FILE")); /*엑셀파일을 첨부해 주십시오.*/
                return;
            }
            
            if(!confirm(resource.getMessage("MSG_CONFIRM_REG_EXCEL_FILE"))) { /*엑셀파일 등록하시겠습니까?*/
                return;
            }
            
            form.init.setProgressFlag(true);
            form.init.setURL("/mm/pop/MMB008_01/saveExcelSample");
            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("callbackSaveExcelSample");
            
            form.submit(obj);
        },
        updatePlUploadHist : function() {
    		var dg_1 = grid.getObject("MMB008_01_grid_01");
    		dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
    		
    		var rows = grid.handle.getAllRows(dg_1);
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {
        		if(flag) {
        			form.handle.setValue("MMB008_01_form_03", "gridData", grid.util.convertJson2Rows(rows));
        			
        			var obj = form.getObject("MMB008_01_form_03");
        			
        			form.init.setURL("/mm/pop/MMB008_01/updateStandardEntrUplo");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
        			form.init.setCallBackFunction("deleteStandardEntrUplo");
        			
        			form.submit(obj);
        		}
        	});
    	},
        deleteStandardEntrUplo : function() {
        	var dg_1 = grid.getObject("MMB008_01_grid_01");
    		
    		if(!grid.handle.isChecked(dg_1)) {
    			alert(resource.getMessage("MSG_NO_CHECK"));
    			return;
    		}
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
        		if(flag) {
        			var rows = grid.handle.getCheckedRowsData(dg_1);
		        	
		        	form.handle.setValue("MMB008_01_form_03", "gridData", grid.util.convertJson2Rows(rows));
		        	
        			var obj = form.getObject("MMB008_01_form_03");
        			
        			form.init.setURL("/mm/pop/MMB008_01/deleteStandardEntrUplo");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
        			form.init.setCallBackFunction("deleteStandardEntrUplo");
        			
        			form.submit(obj);
        		}
        	});
        },
        updateStandardEntrUploadNo : function() {
            $.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("CC_현재 작업내용을 초기화하고 새롭게 연결합니다.<br><br>계속 진행하시겠습니까?"), function(flag) {
                if(flag) {
                    var obj = form.getObject("MMB008_01_form_02");
                    
                    form.init.setURL("/mm/pop/MMB008_01/updateStandardEntrUploadNo");
                    form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("updateStandardEntrUploadNo");
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	insertRow : function() {
            var dg_1 = grid.getObject("MMB008_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var idx = grid.handle.getRowIndex(dg_1, rowData);
            
            rowData = clone(rowData);
            rowData.QY = "";
            rowData.SN = "";
            rowData.REG_STA = "신규";
            
        	grid.handle.appendRow(dg_1, rowData, idx+1);
        },
        deleteRow : function() {
        	var dg_1 = grid.getObject("MMB008_01_grid_01");
        	var idx = grid.handle.getCurrentRowIndex(dg_1);
        	var rowData = grid.handle.getSelectedRowData(dg_1);
        	
        	if(!oUtil.isNull(rowData.SN)) {
        		alert(resource.getMessage("CC_이미 등록된 행은 처리할 수 없습니다."));
        		return;
        	}
        	
        	grid.handle.removeRow(dg_1, idx);
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		if(form.handle.getValue("MMB008_01_form_01", "flag") == "view") {
    			form.util.setVisible("MMB008_01_btn_01", false);
    			form.handle.closeAll("MMB008_01_form_01", true);
    			form.util.setVisible("MMB008_01_btn_02", false);
    			form.util.setVisible("MMB008_01_btn_03", false);
    			form.util.setVisible("MMB008_01_btn_04", false);
                form.util.setVisible("MMB008_01_btn_05", false);
    			
    			$("#MMB008_01_span_01").html(" * 신고분할 후에는 조회만 가능합니다.");
    		}
    	}
    }
    
};
