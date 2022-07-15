/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B006_03
 * 작업일자 : 2020.05.14
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB006_03.config.applyFtaNation();
    MMB006_03.init.initComponent();
}

var MMB006_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	var sflag = form.handle.getValue("MMB006_03_form_01", "flag");
        	var setno = form.handle.getValue("MMB006_03_form_01", "FORM_SETUP_SN");
        	var nm = form.handle.getValue("MMB006_01_form_01", "TAB_NM");
        	
        	if(oUtil.isNull(setno)) {
        		form.util.disabledImage("MMB006_03_href_01", false);
        		form.handle.setValue("MMB006_03_form_01", "flag", "insert");
        	} else {
        		if(oUtil.isNull(sflag) || sflag != "view") {
        			form.util.disabledImage("MMB006_03_href_01", true);
        			form.handle.setValue("MMB006_03_form_01", "flag", "update");
        		}
        	}
        	
        	form.handle.setValue("MMB006_03_form_04", "TAB_NM", nm);
        	
        	if(nm == "2") {
        		$("#MMB006_03_span_01").html(resource.getMessage("CC_3.부가자료 리스트"));
        	}
        	
        	var ts_1 = tab.getObject("MMB006_01_tabs_01");
        	var data = [];
        	
        	// 인보이스 탭에서 엑셀데이터가 있다면, copy함
			try {
				var dg_1 = grid.getObject("MMB006_02_grid_02");
				data = grid.handle.getAllRows(dg_1);
			} catch(e) {
				data = [];
			}
			
        	MMB006_03.datagrid.initGrid_1();
        	MMB006_03.datagrid.initGrid_2(data);
        	MMB006_03.datagrid.initGrid_3([],[]);
        	MMB006_03.datagrid.initGrid_4();
        	
        	MMB006_03.control.selectInvUpMasterInfo();
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
        },
        gridCombo_2 : function(obj) {
            var opts = new Object;
            
            opts.data = [{CODE:"Y", NAME:resource.getMessage("TXT_YES")},
            	         {CODE:"N", NAME:resource.getMessage("TXT_NO")}
            	         ];
            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            
            return opts;
        },
        gridCombo_3 : function(obj) {
            var opts = new Object;
            
            opts.data = [
            	{CODE:" ", NAME:resource.getMessage("NOSEL")},
            	{CODE:"R", NAME:resource.getMessage("CC_반올림")},
            	{CODE:"C", NAME:resource.getMessage("CC_올림")},
            	{CODE:"F", NAME:resource.getMessage("CC_내림")},
            	{CODE:"T", NAME:resource.getMessage("CC_버림")}
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
    		var dg_1 = grid.getObject("MMB006_03_grid_01");
            
    		grid.init.setPage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setFitColumns(false);
            grid.init.setRecallFunction("initGrid_1");
            grid.init.setEmptyMessage(false);
            grid.init.setCallBackFunction("reloadGridEvent_1");
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
        initGrid_2 : function(data) {
        	var dg_2 = grid.getObject("MMB006_03_grid_02");
            
        	grid.init.setData(data);
            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_2");
            // 셀 에디팅 설정
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_2);
            grid.event.setOnDblClickCell(dg_2);
            
            Theme.defaultGrid(dg_2, {params:{HEADER_ID:"header2"}});
        },
        initGrid_3 : function(header, data) {
        	var dg_1 = grid.getObject("MMB006_03_grid_03");
            
        	grid.init.setData(data);
        	grid.init.setPage(true);
            grid.init.setPageLayout(["resizeColumn"]);
            grid.init.setEmptyMessage(true);
            // 셀 에디팅 설정
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(true);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            
            // 이벤트 설정
            grid.event.setOnClickRow(dg_1);
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, header);
        },
        initGrid_4 : function() {
    		var dg_1 = grid.getObject("MMB006_03_grid_04");
    		
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
    	reloadGridEvent_1 : function() {
    		var dg_1 = grid.getObject("MMB006_03_grid_01");
    		var rowcount = grid.handle.getRowCount(dg_1);
    		
    		if(rowcount > 0) {
    			var rows = grid.handle.getAllRows(dg_1);
    			var rdata = rows[0];
    			
    			if(rdata.QY_SSUM_AT == "Y") form.handle.setChecked("MMB006_03_form_01", "QY_SSUM_AT", true);
    			if(rdata.AMT_SSUM_AT == "Y") form.handle.setChecked("MMB006_03_form_01", "AMT_SSUM_AT", true);
    			if(rdata.NETWGHT_SSUM_AT == "Y") form.handle.setChecked("MMB006_03_form_01", "NETWGHT_SSUM_AT", true);
    			if(rdata.TOT_WT_SSUM_AT == "Y") form.handle.setChecked("MMB006_03_form_01", "TOT_WT_SSUM_AT", true);
    			if(rdata.PKNG_CO_SSUM_AT == "Y") form.handle.setChecked("MMB006_03_form_01", "PKNG_CO_SSUM_AT", true);
    			if(rdata.ADAMT_SSUM_AT == "Y") form.handle.setChecked("MMB006_03_form_01", "ADAMT_SSUM_AT", true);
    			if(rdata.DDC_AMT_SSUM_AT == "Y") form.handle.setChecked("MMB006_03_form_01", "DDC_AMT_SSUM_AT", true);
    		}
    	},
    	onDblClick_MMB006_03_grid_04 : function(rowData) {
    		form.handle.setValue("MMB006_03_form_01", "FORM_SETUP_SN", rowData.FORM_SETUP_SN);
    		form.handle.setValue("MMB006_03_form_01", "FORM_SETUP_NM", rowData.FORM_SETUP_NM);
    		form.handle.setValue("MMB006_03_form_01", "flag", "update");
    		form.handle.setChecked("MMB006_03_form_01", "ALL_SSUM_AT", false);
    		form.util.disabledImage("MMB006_03_href_01", true);
    		
    		MMB006_03.event.allCheck();
    		MMB006_03.control.selectInvUpMasterInfo();
    	},
    	insertInvUpSetupInfo : function(data) {
    		form.handle.setValue("MMB006_03_form_01", "flag", "update");
    		form.handle.setValue("MMB006_03_form_01", "FORM_SETUP_SN", data.FORM_SETUP_SN);
    		form.util.disabledImage("MMB006_03_href_01", true);
    		
    		MMB006_03.ui.setUpdateUI();
    	},
    	deleteInvUpSetupInfo : function() {
    		MMB006_03.ui.setInitUI();
    	},
    	insertPlUploadHist : function() {
    		var pid = form.handle.getValue("MMB006_03_form_01", "TARGET_PID");
    		
    		if(pid == "IC1003_01") {
    			IC1003_01.control.selectUploadHistList();
    		} else if(pid == "EC2003_01") {
    			EC2003_01.control.selectUploadHistList();
    		}
            
            MMB006_03.control.cud_flag = 1;
    	},
    	onDblClickCell_MMB006_03_grid_02 : function(rowIndex, field, value) {
    		var cellPoint = field;
    		
    		if(!oUtil.isNull(cellPoint)) {
    			var dg_1 = grid.getObject("MMB006_03_grid_01");
    			var datas = grid.handle.getSelectedRowData(dg_1);
    			var idx = grid.handle.getCurrentRowIndex(dg_1);
    			
    			datas.CELL_LC = cellPoint;
    			datas.CELL_SETUP_2 = "Y";
    			datas.CELL_SETUP_2_NM = resource.getMessage("TXT_YES");
    			grid.handle.setChangeRowValue(dg_1, idx, datas);
    		}
    	},
    	executeExcelUploadFile : function(datas) {
    		var dg_2 = grid.getObject("MMB006_03_grid_02");
    		
    		grid.handle.setLoadData(dg_2, datas)
    	},
    	allCheck : function() {
			var allchk = form.handle.isChecked("MMB006_03_form_01", "ALL_SSUM_AT");
			
			form.handle.setChecked("MMB006_03_form_01", "QY_SSUM_AT", allchk);
    		form.handle.setChecked("MMB006_03_form_01", "AMT_SSUM_AT", allchk);
    		form.handle.setChecked("MMB006_03_form_01", "NETWGHT_SSUM_AT", allchk);
    		form.handle.setChecked("MMB006_03_form_01", "TOT_WT_SSUM_AT", allchk);
    		form.handle.setChecked("MMB006_03_form_01", "PKNG_CO_SSUM_AT", allchk);
    		
    		if(form.handle.getValue("MMB006_03_form_01", "IMP_EXP_SE_CD") == "I") {
	    		form.handle.setChecked("MMB006_03_form_01", "ADAMT_SSUM_AT", allchk);
	    		form.handle.setChecked("MMB006_03_form_01", "DDC_AMT_SSUM_AT", allchk);
    		}
		}
    },
    // 업무구현
    control : {
    	cud_flag : 0,
        loadState : 0, // 0:최초, 1:사용
        selectInvUpMasterInfo : function() {
    		var flag = form.handle.getValue("MMB006_03_form_01", "flag");
    		var url;
    		
    		if(oUtil.isNull(flag) || flag == "insert") {
	    		url = "/mm/pop/MMB006_01/selectBaseInvUpList";
    		} else {
    			if (!form.handle.isValidate("MMB006_03_form_01")) {
	    			return;
	            }
    			
    			url = "/mm/pop/MMB006_01/selectInvUpMasterInfo";    			
    		}
    		
            var dg_1 = grid.getObject("MMB006_03_grid_01");
            var params = form.handle.getElementsParam("MMB006_03_form_01");
            
            grid.handle.sendRedirect(dg_1, url, params);
    	},
    	insertInvUpSetupInfo : function() {
    		if (!form.handle.isValidate("MMB006_03_form_01")) return;
    		
    		var iflag = form.handle.getValue("MMB006_03_form_01", "flag");
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {
        		if(flag) {
        			var dg_1 = grid.getObject("MMB006_03_grid_01");
        			dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
        			
        			var rows = grid.handle.getAllRows(dg_1);
        			form.handle.setValue("MMB006_03_form_01", "gridData", grid.util.convertJson2Rows(rows, "N"));
        			
        			var obj = form.getObject("MMB006_03_form_01");
        			
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
    		if (!form.handle.isValidate("MMB006_03_form_01")) return;
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
        		if(flag) {
        			var obj = form.getObject("MMB006_03_form_01");
        			
        			form.init.setURL("/mm/pop/MMB006_01/deleteInvUpSetupInfo");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
        			form.init.setCallBackFunction("deleteInvUpSetupInfo");
        			
        			form.submit(obj);
        		}
        	});
    	},
    	initPageControl : function() {
    		$.messager.confirm(CNFIR, resource.getMessage("TXT_INIT_PROCESS"), function(flag) {
				if(flag) {
					MMB006_03.ui.setInitUI();
				}
			});
    	},
    	insertPlUploadHist : function() {
    		if (!form.handle.isValidate("MMB006_03_form_04")) return;
    		
            if(this.cud_flag > 0) {
                alert(resource.getMessage("CC_이미 저장된 데이터입니다.(데이터 추출 후에 다시 저장이 가능합니다.)"));
                return;
            }
            
    		var dg_1 = grid.getObject("MMB006_03_grid_03");
    		
    		if(grid.handle.getRowCount(dg_1) == 0) {
    			alert(resource.getMessage("MSG_NO_INSERT"));
    			return;
    		}
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {
        		if(flag) {
        			dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
        			
        			var rows = grid.handle.getAllRows(dg_1);        			
        			form.handle.setValue("MMB006_03_form_04", "gridData", grid.util.convertJson2Rows(rows, "N"));
        			
        			var obj = form.getObject("MMB006_03_form_04");
        			
        			form.init.setURL("/mm/pop/MMB006_01/insertPlUploadHist");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
        			form.init.setCallBackFunction("insertPlUploadHist");
        			
        			form.submit(obj);
        		}
        	});
    	},
    	// 1.클립보드 붙여넣기 이벤트 발생
    	pasteColumn : function() {
    		var obj = form.getObject("MMB006_03_form_00", "PASTE_TEXT1");
    		
    		if(isIE()) {
				alert(resource.getMessage("TXT_SORRY_EXPLORER_10"));
			} else {
	    		obj.focus();
	    		obj.trigger("paste"); // 붙여넣기 이벤트 생성
			}
    	},
    	allClearColumn : function() {
    		var dg_2 = grid.getObject("MMB006_03_grid_02");
    		grid.handle.clearAll(dg_2);
    		
    		var dg_3 = grid.getObject("MMB006_03_grid_03");
    		grid.handle.clearAll(dg_3);
    	},
    	// 데이터추출
    	filterInvoiceData : function() {
    		var dg_3 = grid.getObject("MMB006_03_grid_03");
    		var dg_1 = grid.getObject("MMB006_03_grid_01");
    		var dg_2 = grid.getObject("MMB006_03_grid_02");
    		
    		var crownum = grid.handle.getAllRows(dg_1);
    		var erownum = grid.handle.getRowCount(dg_2);
    		
    		if(crownum < 1) {
    			return alert(resource.getMessage("CC_엑셀 맵핑정보가 등록되지 않았습니다. 정보 등록 후 다시 시도해 주십시오."));
    		} else if(erownum < 1) {
    			return alert(resource.getMessage("CC_추출할 엑셀 데이터가 없습니다. 데이터 등록 후 다시 시도해 주십시오."));
    		}
    		
            dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
            
            // 스타일 초기화
            var edata = grid.handle.getAllRows(dg_2); // 엑셀 원본 데이터
            
            if(MMB006_03.control.loadState != 0) {
                grid.handle.clearAll(dg_2);
                grid.handle.setLoadData(dg_2, edata);                
            }
            
    		$.messager.confirm(CNFIR, resource.getMessage("CC_작업중 데이터가 있는 경우, 모두 삭제한 후 다시 데이터를 생성합니다.<br>그래도 진행하시겠습니까?"), function(flag) {                
        		if(flag) {
                    MMB006_03.control.loadState = 1;
                    
        			grid.handle.clearAll(dg_3);
            		
                    var edata = grid.handle.getAllRows(dg_2); // 엑셀 원본 데이터
                    var startRow = form.handle.getValue("MMB006_03_form_01", "BEGIN_ROW");
            		var columns = grid.handle.getAllRows(dg_1);
            		var heList = new Array();
            		var toList = "[";
                    var quotes = '\\"';
                    var quote = "'";
            		var total = columns.length;
                    
            		if(oUtil.isNull(startRow) || parseInt(startRow) < 0) {
            			alert(resource.getMessage("CC_데이터를 읽기 시작할 행번호를 정확하게 입력해 주시요."));
            			return;
            		}
            		
            	    for(var j = parseInt(startRow)-1; j < erownum; j++) { // 엑셀 원본데이터
	            		var toMap = "{";
	            		var hlen = heList.length;
	            		var chker = true;
	            		var rate = ((i+1)/total)*100;
                        
                        WINDOW.sleep(100);
                        
	            		for(var i = 0; i < columns.length; i++) { // 맵핑설정값
	            			var cid = new Array(); // 열리스트
	            			var val = "";
	            			var midCell = "";
	            			var cellLc = columns[i].CELL_LC;
	            			var mstr = cellLc;
	            			
//	            			if(oUtil.isNull(cellLc)) continue;
	            			
	            			if(cellLc.toUpperCase().startsWith("FUNC:")) { // 함수가 설정된 경우, 해당 셀 위치를 구한다.(단, 조합식은 안됨)
	            				cellLc = cellLc.replace("func:", "");
	            				var mid = cellLc.split("$");
	            				var chkMid = true;
	            				mstr = "";
	            				
	            				for(var m = 0; m < mid.length; m++) {
	            					if(mid[m].indexOf(".") >= 0) {
	            						var cname = mid[m].split(".");
	            						if(oUtil.isNull(edata[j][cname[0]])) {
	            							chkMid = false;
	            						} else {	            							
	            							cellLc = cellLc.replace("$"+cname[0], '"'+edata[j][cname[0]]+'"');
	            						}
	            						continue;
	            					}
	            					
	            					if(mid[m].indexOf("==") >= 0) {
	            						var cname = mid[m].split("==");
	            						if(oUtil.isNull(edata[j][cname[0]])) {
	            							chkMid = false;
	            						} else {	            							
	            							cellLc = cellLc.replace("$"+cname[0], '"'+edata[j][cname[0]]+'"');
	            						}
	            						continue;
	            					}
	            				}
//	            				console.log("caluration = " + cellLc + ", mid[0]="+mid[0] +", mid[1]="+mid[1]);
	            				if(chkMid == true) val = eval(cellLc);
	            				else val = resource.getMessage("No data");
	            			} else {
		            			cid = oUtil.getListFilterAlphabet(mstr, "+"); // 열리스트
	            			    
		            			// 지정된 셀끼리 문자열 합치기
		            			for(var k = 0; k < cid.length; k++) {
	        	    				try {    					
	        	    					var str = edata[j][cid[k]];
	        	    					
	        	    					if(!cellLc.startsWith("=")) {
		        	    					if(oUtil.isNull(str) || str == "undefined") str = "";
		        	    					val = val + " " + str;
	        	    					} else {
	        	    						val = "";
	        	    					}
	        	    					
//	        	    					if(cid.length > 1) console.log("cell target = " + cellLc + ", value = " + str + " > " + val);
	        	    				} catch(e) {}
		            			}
	            			}
                            
                            // cell에 값이 있는지 없는지 색상으로 표시하는 기능 추가(2022-04-26)
                            if(columns[i].CELL_SETUP_2 == "Y") {
                                if(oUtil.isNull(val)) {
                                    grid.handle.setCellStyler(dg_2, cellLc, j, "background-color: darkgray;", columns[i].CELL_NM);
                                } else {
                                    grid.handle.setCellStyler(dg_2, cellLc, j, "background-color: #c7d21e;", columns[i].CELL_NM);
                                }
                            }
                            
	            			var dataType = columns[i].CELL_SETUP_1;
	            			
	            			if(!oUtil.isNull(val)) {
                                val = val.replace(/\\/g, "\\\\"); // 백슬레시 치환
	            				val = val.replace(/\"/g, quotes); // 쌍따움표 치환
		            			val = val.replace(/\'/g, quote); // 따움표 치환
		            			val = val.replace(/(\n|\r\n)/g, ' '); // 줄바끔 치환
		            			
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
	            			
	            			// 값이 없는 경우에는 기본값으로 셋팅
	            			if(!oUtil.isNull(columns[i].CELL_SETUP_3) && (oUtil.isNull(val) || val == 'null')) {
	            				val = columns[i].CELL_SETUP_3;
	            			}
	            			
	            			if (oUtil.isNull(val) || val == 'null') {
	    	                    toMap += '"' + columns[i].CELL_ID + '":"",';
	    	                    
	    	                    // 필수값이 모두 입력되지 않은 항목은 지우기 위해 기록해 놓음
	    	                    if(!cellLc.startsWith("=") && columns[i].CELL_SETUP_2 == "Y") {
	    	                    	chker = false;
	    	                    }
	    	                } else {
	    	                	val = val.toString().trim();
	    	                    toMap += '"' + columns[i].CELL_ID + '":"' + val + '",';
	    	                }
	            			
	            			if(hlen == 0) { // 해더정보는 데이터 첫 행이 있을 때만 생성할 것
		            			var colMap = MMB006_03.util.getInitRowData(columns[i].CELL_ID, columns[i].CELL_NM);
		            			
		            			heList.push(colMap);
	            			}
	            		}
	            		
	            		// 필수값이 모두 입력되지 않은 항목은 지우기 위해 기록해 놓음
	                    if(!chker) {
	                    	toMap += '"USE_YN":"N"';
	                    } else {
	                    	toMap += '"USE_YN":"Y"';
	                    }
	                    
	    	            toMap += ',"ROW_INDEX":"'+j+'"},';
	    	            toList += toMap;
            	    }
            	    
            	    if(toList.length > 1) {
        	        	toList = toList.substring(0, toList.length - 1);
        	        }
        	        toList += "]";
        	        
        	        try {
	        	        var rdate = eval(toList);
	        	        var odata = new Array()
	        	        // 소수점 처리 항목이 있는지 체크
	            		var prcsList = new Array();
	        	        
	        	        // 화면에 표시할 정보만 추출해서 List에 담는다.
	            	    for(var j = 0; j < rdate.length; j++) {
	            	    	if(rdate[j].USE_YN != "N") {
	            	    		odata.push(rdate[j])
	            	    	}
	            	    }
	            	    
	        	        // 계산식(+, -, /, *) 적용
	            		for(var i = 0; i < columns.length; i++) { // 추출항목
	            			var formura = "";
	            			var cellId = "";
	            			var rdata = null;
	            			var dtype = columns[i].CELL_SETUP_1; // 데이터 타입
	            			var defData = columns[i].CELL_SETUP_3; // 기본값
		        			var pnum = columns[i].CELL_SETUP_4; // 표시할 소수점 자리수
		        			
	            			if(dtype == "N" && !oUtil.isNull(pnum)) {
	            				prcsList.push(columns[i]);
            				}
	            			
	            			// 1. 계산식을 가지고 있는 컬럼항목을 찾는다.
		        			if(columns[i].CELL_LC.startsWith("=")) {
		        				formura = columns[i].CELL_LC;
		        				cellId = columns[i].CELL_ID;
		        			} else {
		        				continue;
		        			}
		        			
		        			// 2. 계산식 항목인 경우, 추출된 데이터 항목과 대입해서 계산식 생성 후 수식을 생성한다.
		        			// 수식에 대입되는 조건은 모두 숫자인 경우이다.
		        			if(!oUtil.isNull(formura)) {
		        				formura = formura.toUpperCase();
		        				
		        				for(var j = 0; j < odata.length; j++) {
		        					var pformura = replaceAll(formura, "=", "");
		        					var cidList = oUtil.getListFilterAlphabet(pformura, "+,-,*,/"); // 열리스트
		        					rdata = odata[j];
		        					
		        					for(var k = cidList.length-1; k >= 0; k--) { // 추출항목
		        						//  계산식 생성
	        							pformura = replaceAll(pformura, cidList[k], replaceAll(edata[rdata.ROW_INDEX][cidList[k]], ",", ""));
//	        							console.log("value = " + edata[rdata.ROW_INDEX][cidList[k]]);
		        					}
		        					
			        				if(!oUtil.isNull(rdata)) {
//			        				    console.log("pformura =>> " + pformura +", length = " + cidList.length);
			        					try {
			        						rdata[cellId] = parseFloat(eval(pformura).toFixed(5));
			        					} catch(e) {
			        						if(!oUtil.isNull(defData)) {
			        							rdata[cellId] = defData;
			        						} else {
			        							rdata[cellId] = "";
			        						}
			        					}
			        				}
		        				}
		        			}
                            
                            WINDOW.sleep(100);
	            		}
	            		
	            		// 합산할지 여부 체크
	            		var sumCheck = false;
	            		var qychk = form.handle.isChecked("MMB006_03_form_01", "QY_SSUM_AT");
	            		var amtchk = form.handle.isChecked("MMB006_03_form_01", "AMT_SSUM_AT");
	            		var netchk = form.handle.isChecked("MMB006_03_form_01", "NETWGHT_SSUM_AT");
	            		var wtchk = form.handle.isChecked("MMB006_03_form_01", "TOT_WT_SSUM_AT");
	            		var pkgchk = form.handle.isChecked("MMB006_03_form_01", "PKNG_CO_SSUM_AT");
	            		var adamtchk = form.handle.isChecked("MMB006_03_form_01", "ADAMT_SSUM_AT");
	            		var dcamtchk = form.handle.isChecked("MMB006_03_form_01", "DDC_AMT_SSUM_AT");
	            		
	        			for(var r = 0; r < crownum.length; r++) {
	        				if(crownum[r]["CELL_ID"] == "ITEM_CD") {
	        					if(qychk || amtchk || netchk || wtchk || pkgchk) {
		        					if(!oUtil.isNull(crownum[r]["CELL_LC"])) {
		        						sumCheck = true;
		        					} else {
		        						alert(resource.getMessage("CC_합산을 위해선 자재코드에 대한 엑셀맵핑을 설정해야 합니다."));
		        						return;
		        					}
	        					}
	        					
	        					break;
	        				}
                            
                            WINDOW.sleep(100);
	        			}
	        			
	        			var result = new Array();
	        			
	            		if(sumCheck) { // 품목코드로 그룹지어서 수량,금액,순중량,포장개수,총중량에 값이 있는 경우에 sum한다.
	            			odata.reduce(function(res, value) {
	            			  if (!res[value.ITEM_CD]) {
	            			      res[value.ITEM_CD] = value;
	            			      
	            			      result.push(res[value.ITEM_CD]);
	            			  } else {
		            			  if(qychk && !oUtil.isNull(value.QY)) res[value.ITEM_CD].QY = Number.parseInt(res[value.ITEM_CD].QY) + Number.parseInt(value.QY);
		            			  if(amtchk && !oUtil.isNull(value.AMT)) res[value.ITEM_CD].AMT = Number.parseInt(res[value.ITEM_CD].AMT) + Number.parseInt(value.AMT);
		            			  if(netchk && !oUtil.isNull(value.NETWGHT)) res[value.ITEM_CD].NETWGHT = Number.parseInt(res[value.ITEM_CD].NETWGHT) + Number.parseInt(value.NETWGHT);
		            			  if(wtchk && !oUtil.isNull(value.TOT_WT)) res[value.ITEM_CD].TOT_WT = Number.parseInt(res[value.ITEM_CD].TOT_WT) + Number.parseInt(value.TOT_WT);
		            			  if(pkgchk && !oUtil.isNull(value.PKNG_CO)) res[value.ITEM_CD].PKNG_CO = Number.parseInt(res[value.ITEM_CD].PKNG_CO) + Number.parseInt(value.PKNG_CO);
		            			  if(adamtchk && !oUtil.isNull(value.ADAMT)) res[value.ITEM_CD].ADAMT = Number.parseInt(res[value.ITEM_CD].ADAMT) + Number.parseInt(value.ADAMT);
		            			  if(dcamtchk && !oUtil.isNull(value.DDC_AMT)) res[value.ITEM_CD].DDC_AMT = Number.parseInt(res[value.ITEM_CD].DDC_AMT) + Number.parseInt(value.DDC_AMT);
	            			  }
	            			  
	            			  return res;
	            			}, [{}]);
	            		} else {
	            			result = odata;
	            		}
	            		
	            		// 상위 추출결과 중 숫자에 대한 소수점 추가 처리
	            		if(prcsList.length > 0) {
	            			for(var j = 0; j < result.length; j++) {
	            				for(var i = 0; i < prcsList.length; i++) {
	            					var pnum = prcsList[i].CELL_SETUP_4; // 표시할 소수점 자리수
	            					var ftype = prcsList[i].CELL_SETUP_5; // 소수점 처리방식
	            					var cellId = prcsList[i].CELL_ID;
	            					var val = result[j][cellId];
	            					
	            					if(!oUtil.isNull(val)) result[j][cellId] = oUtil.getFilterFloat(val.toString(), ftype, pnum);
	            				}
	            			}
	            		}
	            		
	            		MMB006_03.datagrid.initGrid_3(heList, result);
        	        } catch(e) {
        	        	if(SESSION.USER_ID == "fta") console.log(e);
        	        }
        		}
    		});
            
            this.cud_flag = 0;
    	},
    	executeExcelUploadFile : function() {
    		if (!form.handle.isValidate("MMB006_03_form_02")) return;
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("CC_파일 업로드 시 작성중인 내용은 초기화됩니다.<br>그래도 진행하시겠습니까?"), function(flag) {
        		if(flag) {
        			var sheetNo = form.handle.getValue("MMB006_03_form_01", "SHEET_NO");
        			
        			if(oUtil.isNull(sheetNo)) sheetNo = 1;
        			
        			form.handle.setValue("MMB006_03_form_02", "SHEET_NO", sheetNo);
        			
        			var obj = form.getObject("MMB006_03_form_02");
        			
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
            
            params.TARGET_PID = "MMB006_03";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            
    		var dl_1 = dialog.getObject("MMB002_03_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("CC_[f]거래처 조회"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(850);
            dialog.init.setHeight(600);
            dialog.init.setURL("/mm/pop/MMB002_03");
            
            dialog.open(dl_1);
        },
        callByMMB002_03 : function(rowData) {
        	form.handle.setValue("MMB006_03_form_01", "BCNC_CD", rowData.BCNC_CD);
        	form.handle.setValue("MMB006_03_form_01", "BCNC_NM", rowData.BCNC_MTLTY);
        	
        	form.handle.isValidate("MMB006_03_form_01");
        	
        	var dl_1 = dialog.getObject("MMB002_03_dailog_01");
        	dialog.handle.close(dl_1);
        },
        openDialog_2 : function() {
            var params = new Object();
            
            params.TARGET_PID = "MMB006_03";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.IMP_EXP_SE_CD = form.handle.getValue("MMB006_03_form_01", "IMP_EXP_SE_CD");
            params.DATA_SE =  form.handle.getValue("MMB006_03_form_01", "DATA_SE");
            params.CATEGORY_CD = form.handle.getValue("MMB006_03_form_01", "CATEGORY_CD");
            params.ORG_BCNC_CD = form.handle.getValue("MMB006_03_form_01", "BCNC_CD");
            
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
    		form.handle.setValue("MMB006_03_form_01", "FORM_SETUP_SN", "");
    		form.handle.setValue("MMB006_03_form_01", "FORM_SETUP_NM", "");    		
    		form.handle.setValue("MMB006_03_form_01", "SHEET_NO", "1");
    		form.handle.setValue("MMB006_03_form_01", "BEGIN_ROW", "1");
    		form.handle.setValue("MMB006_03_form_01", "flag", "insert");
    		form.util.disabledImage("MMB006_03_href_01", false);
    		
    		form.handle.setChecked("MMB006_03_form_01", "ALL_SSUM_AT", false);
    		MMB006_03.event.allCheck();
    		
    		MMB006_03.control.selectInvUpMasterInfo();
    	},
    	// 수정 화면 디자인
    	setUpdateUI : function() {
    		form.handle.addClass("MMB006_03_form_01", "FORM_SETUP_NM", "search_readOnly");
    		form.handle.readonly("MMB006_03_form_01", "FORM_SETUP_NM", true);
    		form.util.disabledImage("MMB006_03_href_01", true);
    		
    		MMB006_03.control.selectInvUpMasterInfo();
    	},
    	// 3.클립보드 붙여넣기 데이터 그리드 출력
    	drowSetData : function() {
    		var pval = form.handle.getValue("MMB006_03_form_00", "PASTE_TEXT1");
    		
    		try {
    			var dg_1 = grid.getObject("MMB006_03_grid_02");
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
    	},
    	insertRow : function() {
        	var dg_1 = grid.getObject("MMB006_03_grid_01");
        	
        	grid.handle.appendRow(dg_1);
        },
        deleteRow : function(i) {
        	var dg_1 = grid.getObject("MMB006_03_grid_01");
        	
        	if(!grid.handle.isSelected(dg_1)) {
        		alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	var idx = grid.handle.getCurrentRowIndex(dg_1);
        	
        	grid.handle.removeRow(dg_1, idx);
        },
        moveRow : function(step) {
        	var dg_1 = grid.getObject("MMB006_03_grid_01");
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
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		var iese = form.handle.getValue("MMB006_03_form_01", "IMP_EXP_SE_CD");
    		
    		if(iese == "I") {
    			form.handle.setValue("MMB006_03_form_01", "CATEGORY_CD", "PAKING_COLUMN_LIST"); // 수입 기본항목 구분
    		} else if(iese == "E") {
    			form.handle.setValue("MMB006_03_form_01", "CATEGORY_CD", "PPL_COLUMN_LIST"); // 수출 기본항목 구분
    			form.handle.disabled("MMB006_03_form_01", "ADAMT_SSUM_AT", true);
    			form.handle.disabled("MMB006_03_form_01", "DDC_AMT_SSUM_AT", true);
    		}
    		
    		var obj1 = form.getObject("MMB006_03_form_00", "PASTE_TEXT1");
    		
    		// 2.클립보드 붙여넣기 이벤트처리
    		obj1.bind('paste', function(e) {
    			navigator.clipboard.readText().then(clipText => form.handle.setValue("MMB006_03_form_00", "PASTE_TEXT1", clipText));
    			
//    			navigator.clipboard.read().then(text => {
//    				form.handle.setValue("MMB006_03_form_00", "PASTE_TEXT1", text);
//    		    });
			});
    		
    		obj1.bind('propertychange change keyup paste input', function() {
    			setTimeout(function() {
        			MMB006_03.ui.drowSetData();
        		}, 500);
			});
    		
    		$('#MMB006_03_href_01').tooltip({
                content: function(){
                    return $('#MMB006_03_contents_01');
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
    		
    		$("#MMB006_03_href_01").bind('click.menubutton', function(e) {
    			var dg_4 = grid.getObject("MMB006_03_grid_04");
        		grid.handle.clearAll(dg_4);
        		
	    		setTimeout( function() {
	            	var dg_1 = grid.getObject("MMB006_03_grid_04");
	                var params = form.handle.getElementsParam("MMB006_03_form_03");
	                
	                grid.handle.sendRedirect(dg_1, "/mm/pop/MMB006_01/selectInvUpSetupList", params);
	            }, 500);
    		});
    	}
    },
    util : {
    	getInitRowData : function(field, title) {
    		var hcell = [
    			 field.toString() 
    			,title.toString()
    			,100    
    			,"left"     
    			,"center"   
    			,false       
    			,false        
    			,{type:'text'}   
    			 ,null     
    			 ,null    
    			 ,0    
    			 ,0    
    			 ,null];
    		
    		return hcell;
    	}
    }
    
};
