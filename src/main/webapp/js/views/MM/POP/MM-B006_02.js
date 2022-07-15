/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B006_02
 * 작업일자 : 2020.05.14
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB006_02.config.applyFtaNation();
    MMB006_02.init.initComponent();
}

var MMB006_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	var sflag = form.handle.getValue("MMB006_02_form_01", "flag");
        	var setno = form.handle.getValue("MMB006_02_form_01", "FORM_SETUP_SN");
        	
        	if(oUtil.isNull(setno)) {
        		form.util.disabledImage("MMB006_02_href_01", false);
        		form.handle.setValue("MMB006_02_form_01", "flag", "insert");
        	} else {
        		if(oUtil.isNull(sflag) || sflag != "view") {
        			form.util.disabledImage("MMB006_02_href_01", true);
        			form.handle.setValue("MMB006_02_form_01", "flag", "update");
        		}
        	}
        	
        	MMB006_02.datagrid.initGrid_1();
        	MMB006_02.datagrid.initGrid_2();
        	MMB006_02.datagrid.initGrid_3();
        	MMB006_02.datagrid.initGrid_4();
        	
        	MMB006_02.control.selectInvUpMasterInfo();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {},
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    editor : {
        gridCombo_1 : function(obj) {
            var opts = new Object;
            
            opts.data = [{CODE:"", NAME:resource.getMessage("CC_원본 데이터")},
            	         {CODE:"S", NAME:resource.getMessage("CC_문자열")},
            	         {CODE:"N", NAME:resource.getMessage("CC_숫자")},
            	         {CODE:"D", NAME:resource.getMessage("CC_날짜")},
            	         {CODE:"C", NAME:resource.getMessage("CC_통화코드")},
            	         {CODE:"A", NAME:resource.getMessage("CC_국가코드")},
            	         {CODE:"I", NAME:resource.getMessage("CC_인도조건")},
            	         {CODE:"T", NAME:resource.getMessage("CC_숫자만")}
            	         ];
            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            
            return opts;
        }
    },
    // 데이터 그리드 생성
    datagrid : {
    	header4 : [
            ["FORM_SETUP_NM", resource.getMessage("CC_설정값") , 241, "left"  , "center", true, false, null, null, null, 0, 0]
        ],
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB006_02_grid_01");
            
            grid.init.setPage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setFitColumns(false);
            grid.init.setRecallFunction("initGrid_1");
            grid.init.setEmptyMessage(false);
            // 셀 에디팅 설정
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            
            // 이벤트 설정
            grid.event.setOnClickRow(dg_1);
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        },
        initGrid_2 : function() {
    		var dg_1 = grid.getObject("MMB006_02_grid_02");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_2");
            // 에디팅 모드 적용
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            grid.init.setEmptyMessage(false);
            grid.init.setCellEnterCallBackFunction("enterGridEvent_1");
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnDblClickCell(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header2"}});
        },
        initGrid_3 : function() {
    		var dg_1 = grid.getObject("MMB006_02_grid_03");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setFitColumns(true);
            grid.init.setRecallFunction("initGrid_3");
            // 셀 에디팅 설정
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            
            // 이벤트 설정
            grid.event.setOnClickRow(dg_1);
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header3"}});
        },
        initGrid_4 : function() {
    		var dg_1 = grid.getObject("MMB006_02_grid_04");
    		
    		grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setRownumbers(false);
            grid.init.setShowHeader(false);
            grid.init.setFitColumns(false);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header4);
        }
    },
    // 이벤트 처리
    event : {
    	onDblClick_MMB006_02_grid_04 : function(rowData) {
    		form.handle.setValue("MMB006_02_form_01", "FORM_SETUP_SN", rowData.FORM_SETUP_SN);
    		form.handle.setValue("MMB006_02_form_01", "FORM_SETUP_NM", rowData.FORM_SETUP_NM);
    		form.handle.setValue("MMB006_02_form_01", "flag", "update");
    		form.util.disabledImage("MMB006_02_href_01", true);
    		
    		MMB006_02.control.selectInvUpMasterInfo();
    	},
    	insertInvUpSetupInfo : function(data) {
    		form.handle.setValue("MMB006_02_form_01", "flag", "update");
    		form.handle.setValue("MMB006_02_form_01", "FORM_SETUP_SN", data.FORM_SETUP_SN);
    		form.util.disabledImage("MMB006_02_href_01", true);
    		
    		MMB006_02.ui.setUpdateUI();
    	},
    	deleteInvUpSetupInfo : function() {
    		MMB006_02.ui.setInitUI();
    	},
    	insertInvUploadHist : function() {
    		var pid = form.handle.getValue("MMB006_02_form_01", "TARGET_PID");

    		form.util.disabled("MMB006_01_divMove02", false);
    		
    		if(pid == "IC1003_01") {
    			IC1003_01.control.selectUploadHistList();
    		} else if(pid == "EC2003_01") {
    			EC2003_01.control.selectUploadHistList();
    		}
    	},
    	onDblClickCell_MMB006_02_grid_02 : function(rowIndex, field, value) {
    		var cellPoint = field+(rowIndex+1);
    		
    		if(!oUtil.isNull(cellPoint)) {
    			var dg_1 = grid.getObject("MMB006_02_grid_01");
    			var datas = grid.handle.getSelectedRowData(dg_1);
    			var idx = grid.handle.getCurrentRowIndex(dg_1);
    			
    			datas.CELL_LC = cellPoint;
    			
    			grid.handle.setChangeRowValue(dg_1, idx, datas);
    		}
    	},
    	executeExcelUploadFile : function(datas) {
    		var dg_2 = grid.getObject("MMB006_02_grid_02");
    		
    		grid.handle.setLoadData(dg_2, datas)
    	}
    },
    // 업무구현
    control : {
    	loadState : 0, // 0:최초, 1:사용
        selectInvUpSetupList : function() {
            var dg_1 = grid.getObject("MMB006_02_grid_01");
    		var params = form.handle.getElementsParam("MMB006_02_form_03");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/MMB006_01/selectInvUpSetupList", params);
    	},
    	selectInvUpMasterInfo : function() {
    		var flag = form.handle.getValue("MMB006_02_form_01", "flag");
    		var url;
    		
    		if(oUtil.isNull(flag) || flag == "insert") {
	    		url = "/mm/pop/MMB006_01/selectBaseInvUpList";
    		} else {
    			if (!form.handle.isValidate("MMB006_02_form_01")) {
	    			return;
	            }
    			
    			url = "/mm/pop/MMB006_01/selectInvUpMasterInfo";    			
    		}
    		
            var dg_1 = grid.getObject("MMB006_02_grid_01");
            var params = form.handle.getElementsParam("MMB006_02_form_01");
            
            grid.handle.sendRedirect(dg_1, url, params);
    	},
    	insertInvUpSetupInfo : function() {
    		if (!form.handle.isValidate("MMB006_02_form_01")) return;
    		
    		var iflag = form.handle.getValue("MMB006_02_form_01", "flag");
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {
        		if(flag) {
        			var dg_1 = grid.getObject("MMB006_02_grid_01");
        			dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
        			
        			var rows = grid.handle.getAllRows(dg_1);        			
        			form.handle.setValue("MMB006_02_form_01", "gridData", grid.util.convertJson2Rows(rows, "N"));
        			
        			var obj = form.getObject("MMB006_02_form_01");
        			
        			if(oUtil.isNull(iflag) || iflag == "insert") {
        				form.init.setURL("/mm/pop/MMB006_01/insertInvUpSetupInfo");
        			} else {
        				form.init.setURL("/mm/pop/MMB006_01/updateInvUpSetupInfo");
        			}
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
        			form.init.setCallBackFunction("insertInvUpSetupInfo");
        			
        			form.submit(obj);
        		}
        	});
    	},
    	deleteInvUpSetupInfo : function() {
    		if (!form.handle.isValidate("MMB006_02_form_01")) return;
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
        		if(flag) {
        			var obj = form.getObject("MMB006_02_form_01");
        			
        			form.init.setURL("/mm/pop/MMB006_01/deleteInvUpSetupInfo");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
        			form.init.setCallBackFunction("deleteInvUpSetupInfo");
        			
        			form.submit(obj);
        		}
        	});
    	},
    	insertInvUploadHist : function() {
    		if (!form.handle.isValidate("MMB006_02_form_04")) return;
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("CC_동일한 인보이스 번호가 있는 경우, 기존 데이터를 삭제하고 다시 등록합니다.<br><br>저장하시겠습니까?"), function(flag) {
        		if(flag) {
        			var dg_1 = grid.getObject("MMB006_02_grid_03");
        			
        			if(grid.handle.getRowCount(dg_1) > 0) {
        				dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
        			}
        			form.handle.setValue("MMB006_01_form_01", "INVE_NO", form.handle.getValue("MMB006_02_form_04", "INVE_NO"));
        			
        			var rows = grid.handle.getAllRows(dg_1);
        			form.handle.setValue("MMB006_02_form_04", "gridData", grid.util.convertJson2Rows(rows, "N"));
        			
        			var obj = form.getObject("MMB006_02_form_04");
        			
        			form.init.setURL("/mm/pop/MMB006_01/insertInvUploadHist");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
        			form.init.setCallBackFunction("insertInvUploadHist");
        			
        			form.submit(obj);
        		}
        	});
    	},
    	initPageControl : function() {
    		$.messager.confirm(CNFIR, resource.getMessage("TXT_INIT_PROCESS"), function(flag) {
				if(flag) {
					MMB006_02.ui.setInitUI();
				}
			});
    	},
    	// 1.클립보드 붙여넣기 이벤트 발생
    	pasteColumn : function() {
    		var obj = form.getObject("MMB006_02_form_00", "PASTE_TEXT1");
    		
    		if(isIE()) {
				alert(resource.getMessage("TXT_SORRY_EXPLORER_10"));
			} else {
	    		obj.focus();
	    		obj.trigger("paste"); // 붙여넣기 이벤트 생성
			}
    	},
    	allClearColumn : function() {
    		var dg_2 = grid.getObject("MMB006_02_grid_02");
    		grid.handle.clearAll(dg_2);
    		
    		var dg_3 = grid.getObject("MMB006_02_grid_03");
    		grid.handle.clearAll(dg_3);
    	},
    	// 데이터추출
    	filterInvoiceData : function() {
    		var dg_3 = grid.getObject("MMB006_02_grid_03");
    		var dg_1 = grid.getObject("MMB006_02_grid_01");
    		var dg_2 = grid.getObject("MMB006_02_grid_02");
    		
    		var crownum = grid.handle.getAllRows(dg_1);
    		var erownum = grid.handle.getRowCount(dg_2);
            
    		if(crownum < 1) {
    			return alert(resource.getMessage("CC_엑셀 맵핑정보가 등록되지 않았습니다. 정보 등록 후 다시 시도해 주십시오."));
    		}
            
            dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
            
            // 데이터 초기화
            var edata = grid.handle.getAllRows(dg_2); // 엑셀 원본 데이터
            
            if(MMB006_02.control.loadState != 0) {
                grid.handle.clearAll(dg_2);
                grid.handle.setLoadData(dg_2, edata);
            }
            
    		$.messager.confirm(CNFIR, resource.getMessage("CC_작업중 데이터가 있는 경우, 삭제한 후 다시 데이터를 생성합니다.<br>계속 진행하시겠습니까?"), function(flag) {                
        		if(flag) {
                    MMB006_02.control.loadState = 1;
                    
        			grid.handle.clearAll(dg_3);
            		
            		var columns = grid.handle.getAllRows(dg_1); // 엑셀 맵핑 컬럼 리스트
            		var total = columns.length;
                    
            		for(var i = 0; i < columns.length; i++) { // 추출항목
            			var data = new Object();
            			var cid = oUtil.getListFilterAlphabet(columns[i].CELL_LC, "+"); // 열리스트(A~AZ)
            			var rnum = oUtil.getListFilterNumber(columns[i].CELL_LC, "+"); // 행리스트(1~9999)
            			var val = "";
            			var rate = ((i+1)/total)*100;
                        
                        WINDOW.sleep(10);
                        
            			// 데이터 추출
            			for(var k = 0; k < cid.length; k++) {
        	    			if(rnum[k] > 0 && erownum >= rnum[k]) {
        	    				try {
        	    					var str = edata[rnum[k]-1][cid[k]];
                                    
                                    // cell에 값이 있는지 없는지 색상으로 표시하는 기능 추가(2022-04-26)
                                    if(oUtil.isNull(str)) {
                                        grid.handle.setCellStyler(dg_2, cid[k], rnum[k]-1, "background-color: darkgray;", columns[i].CELL_NM);
                                    } else {
                                        grid.handle.setCellStyler(dg_2, cid[k], rnum[k]-1, "background-color: #c7d21e;", columns[i].CELL_NM);
                                    }
                                    
        	    					if(!oUtil.isNull(str) && !str.startsWith("=")) {
	    	    						if(oUtil.isNull(str) || str == "undefined") str = "";
	        	    					val = val + str;
        	    					} else {
        	    						val = "";
        	    					}
        	    				} catch(e) { if(SESSION.USER_ID == "fta") console.log(e); }
        	    			}
            			}
            			
            			// 데이터 타입에 맞게 데이터를 추출함
            			// 데이터 타입 = 선택안함:전체, 문자열:S, 숫자:N, 통화:C, 국가:A, 인도조건:I, 날짜:D
            			var dataType = columns[i].CELL_SETUP_1;
            			
            			if(!oUtil.isNull(val)) {
            				try {
		            			if(dataType == "S") val = oUtil.getFilterChar(val);
		            			else if(dataType == "N") val = oUtil.getFilterFloat(val);
		            			else if(dataType == "I") val = oUtil.getFilterIncoterms(val);
		            			else if(dataType == "C") val = oUtil.getFilterCurrency(val);
		            			else if(dataType == "A") val = oUtil.getFilterNation(val);
		            			else if(dataType == "D") val = oUtil.getFilterDate(val);
		            			else if(dataType == "T") val = oUtil.getListFilterNumber(val);
	            			} catch(e) {
	            				val = "";
	            			}
            			}
            			
	            		// 데이터 추출결과를 추가함
            			if(columns[i].CELL_ID == "INVE_NO") {
            				if(!oUtil.isNull(val)) form.handle.setValue("MMB006_02_form_04", "INVE_NO", val);
            			} 
            			if(columns[i].CELL_ID == "CNR_NO") {
            				if(!oUtil.isNull(val)) form.handle.setValue("MMB006_02_form_04", "CNR_NO", val);
            			}
            			
            			if (oUtil.isNull(val) || val == 'null') {
            				data.CELL_VAL = "";
            			} else {
            				data.CELL_VAL = val.toString().trim();
            			}
            			data.CELL_NM = columns[i].CELL_NM;
            			data.CELL_ID = columns[i].CELL_ID;
            			data.CELL_LC = columns[i].CELL_LC;
            			
            			grid.handle.appendRow(dg_3, data);
            		}
            		
            		var rrownum = grid.handle.getAllRows(dg_3);
            		
            		// 계산식(+, -, /, *) 적용
            		for(var i = 0; i < columns.length; i++) { // 추출항목
            			var formura = "";
            			var rdata = null;
            			var idx;
            			
                        var rate = ((i+1)/total)*100;
                        
                        WINDOW.sleep(10);
                        
            			// 1. 계산식을 가지고 있는 컬럼항목을 찾는다.
	        			if(columns[i].CELL_LC.startsWith("=")) {
	        				formura = columns[i].CELL_LC;
	        			}
	        			
	        			// 2. 계산식 항목인 경우, 추출된 데이터 항목과 대입해서 계산식 생성 후 수식을 생성한다.
	        			// 수식에 대입되는 조건은 모두 숫자인 경우이다.
	        			if(!oUtil.isNull(formura)) {
	        				formura = replaceAll(formura, "=", "");
	        				
	        				for(var j = 0; j < rrownum.length; j++) {
	        					if(!rrownum[j].CELL_LC.startsWith("=")) {
	        						formura = replaceAll(formura, rrownum[j].CELL_LC, rrownum[j].CELL_VAL);
	        					}
	        					if(columns[i].CELL_ID == rrownum[j].CELL_ID) {
	        						rdata = rrownum[j];
	        						idx = j;
	        					}
	        				}
	        					        				
	        				if(!oUtil.isNull(rdata)) {
	        					try {
	        						rdata.CELL_VAL = parseFloat(eval(formura).toFixed(5));
	        					} catch(e) {
	        						rdata.CELL_VAL = "";
	        					}
		        				grid.handle.setChangeRowValue(dg_3, idx, rdata);
	        				}
	        			}
            		}
        		}
    		});
    	},
    	executeExcelUploadFile : function() {
    		if (!form.handle.isValidate("MMB006_02_form_02")) return;
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("CC_파일 업로드 시 작성중인 내용은 초기화됩니다.<br>그래도 진행하시겠습니까?"), function(flag) {
        		if(flag) {
        			var sheetNo = form.handle.getValue("MMB006_02_form_01", "SHEET_NO");
        			
        			if(oUtil.isNull(sheetNo)) sheetNo = 1;
        			
        			form.handle.setValue("MMB006_02_form_02", "SHEET_NO", sheetNo);
        			
        			var obj = form.getObject("MMB006_02_form_02");
        			
        			form.init.setURL("/mm/pop/MMB006_01/executeExcelUploadFile");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
        			form.init.setCallBackFunction("executeExcelUploadFile");
        			
        			form.submit(obj);
        		}
        	});
    	}
    },
    // 다이얼로그 구현
    dialog : {
        openDialog_1 : function() {
            var params = new Object();
            
            params.TARGET_PID = "MMB006_02";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.IMP_EXP_SE_CD = form.handle.getValue("MMB006_01_form_01", "IMP_EXP_SE_CD");
            params.DATA_SE =  form.handle.getValue("MMB006_01_form_01", "DATA_SE");
            params.CATEGORY_CD = form.handle.getValue("MMB006_02_form_01", "CATEGORY_CD");
            params.ORG_BCNC_CD = form.handle.getValue("MMB006_02_form_01", "BCNC_CD");
            
            var dl_1 = dialog.getObject("MMB006_04_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("CC_[f]업체 설정값"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(1100);
            dialog.init.setHeight(600);
            dialog.init.setResizable(true);
            dialog.init.setModal(false);
            dialog.init.setURL("/mm/pop/MMB006_04");
            
            dialog.open(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
    	// 초기화 화면 디자인
    	setInitUI : function() {
    		form.handle.setValue("MMB006_02_form_01", "FORM_SETUP_SN", "");
    		form.handle.setValue("MMB006_02_form_01", "FORM_SETUP_NM", "");    		
    		form.handle.setValue("MMB006_02_form_01", "SHEET_NO", "1");
    		form.handle.setValue("MMB006_02_form_01", "BEGIN_ROW", "1");
    		form.handle.setValue("MMB006_02_form_01", "flag", "insert");
    		
    		
    		form.util.disabledImage("MMB006_02_href_01", false);
    		
    		MMB006_02.control.selectInvUpMasterInfo();
    	},
    	// 수정 화면 디자인
    	setUpdateUI : function() {
    		form.util.disabledImage("MMB006_02_href_01", true);
    		
    		MMB006_02.control.selectInvUpMasterInfo();
    	},
    	// 3.클립보드 붙여넣기 데이터 그리드 출력
    	drowSetData : function() {
    		var pval = form.handle.getValue("MMB006_02_form_00", "PASTE_TEXT1");
    		
    		try {
    			var dg_1 = grid.getObject("MMB006_02_grid_02");
    			var datas;
    			
    			if(pval.startsWith("[") && pval.endsWith["]"]) { // json타입으로 인식
    				datas = eval(pval);
    			} else { // 스페이스로 구분된 텍스트로 인식
    				var csvarray = [];
    				var rows = pval.split('\n');
    				
    			    for(var i = 0; i < rows.length; i++){
    			        csvarray.push(rows[i].split('\t'));
    			    }
    			    
    			    datas = csvarray;
    			}
    			
    			grid.handle.clearAll(dg_1);
    			
    			// 이스케이프된 문자를 복원시킨다.
                if(datas.length > 0) {
                	var hdata = grid.util.getExcelHeaderData(datas);
                	
                	dg_1.datagrid('loadData', hdata);
                }
    		} catch(e) {
    			alert(resource.getMessage("NOT_READ_DATA"));
    		} 
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		var iese = form.handle.getValue("MMB006_02_form_01", "IMP_EXP_SE_CD");
    		
    		if(iese == "I") {
    			form.handle.setValue("MMB006_02_form_01", "CATEGORY_CD", "INVOICE_COLUMN_LIST"); // 수입 기본항목 구분
    		} else if(iese == "E") {
    			form.handle.setValue("MMB006_02_form_01", "CATEGORY_CD", "INVOICE_COLUMN_LIST"); // 수출 기본항목 구분
    		}
    		
    		var obj1 = form.getObject("MMB006_02_form_00", "PASTE_TEXT1");
    		
    		// 2.클립보드 붙여넣기 이벤트처리
    		obj1.bind('paste', function(e) {
    			navigator.clipboard.readText().then(clipText => form.handle.setValue("MMB006_02_form_00", "PASTE_TEXT1", clipText));

//    			navigator.clipboard.readText().then(text => { // 미지원
//    				form.handle.setValue("MMB006_02_form_00", "PASTE_TEXT1", text);
//    		    });
			});
    		
    		obj1.bind('propertychange change keyup paste input', function() {
    			setTimeout(function() {
        			MMB006_02.ui.drowSetData();
        		}, 500);
			});
    		
    		$('#MMB006_02_href_01').tooltip({
                content: function(){
                    return $('#MMB006_02_contents_01');
                },
                showEvent: 'click',
                position: 'bottom,right',
                onUpdate: function(content) {},
                onShow: function() {
                    var t = $(this);
                    
                    t.tooltip('tip').unbind().bind('mouseenter', function() {
                        t.tooltip('show');
                    }).bind('mouseleave', function(){
                        t.tooltip('hide');
                    });
                }
            });
    		
    		$("#MMB006_02_href_01").bind('click.menubutton', function(e) {
    			var dg_4 = grid.getObject("MMB006_02_grid_04");
        		grid.handle.clearAll(dg_4);
        		
	    		setTimeout( function() {
	            	var dg_1 = grid.getObject("MMB006_02_grid_04");
	                var params = form.handle.getElementsParam("MMB006_02_form_03");
	                
	                grid.handle.sendRedirect(dg_1, "/mm/pop/MMB006_01/selectInvUpSetupList", params);
	            }, 500);
    		});
    	}
    }
    
};
