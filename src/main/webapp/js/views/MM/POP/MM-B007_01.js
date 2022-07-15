/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B007_01
 * 작업일자 : 2020.05.15
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB007_01.config.applyFtaNation();
    MMB007_01.init.initComponent();
}

var MMB007_01_modify = false;
var MMB007_01_changes = false;

var MMB007_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB007_01.datagrid.initGrid_1();
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
    // 에디트 컴포넌트 생성
    editor : {
        gridCombo_5 : function(obj) {
            var opts = new Object;
            
            opts.queryParams = {COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_ADDI_DDC_SE", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."};
            opts.data = combo.util.getCacheData(opts.queryParams);
            if(oUtil.isNull(opts.data)) opts.url = "/mm/cbox/selectStandardCode";
            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            opts.panelWidth = "120";
            combo.util.putCacheData(opts, opts.queryParams);
            
            return opts;
        },
        gridSearchbox_1 : function(obj) {
    		MMB007_01.dialog.openDialog_1(obj);
    	},
        gridSearchbox_2 : function(obj) {
    		MMB007_01.dialog.openDialog_2("I");
    	},
    	gridSearchbox_3 : function(obj) {
    		MMB007_01.dialog.openDialog_2("D");
    	},
        gridSearchbox_4 : function(obj) {
            MMB007_01.dialog.openDialog_3("COTDOO");
        },
        gridSearchbox_5 : function(obj) {
            MMB007_01.dialog.openDialog_3("ORPL_AT");
        },
        gridSearchbox_6 : function(obj) {
            MMB007_01.dialog.openDialog_3("ORPL_MTH");
        },
        gridSearchbox_7 : function(obj) {
            MMB007_01.dialog.openDialog_3("ORPL_EXEPT");
        }
    },
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
    		var params = form.handle.getElementsParam("MMB007_01_form_01");
    		var dg_1 = grid.getObject("MMB007_01_grid_01");
            
    		grid.init.setURL("/mm/pop/MMB007_01/selectImportLneInputList");
    		grid.init.setQueryParams(params);
    		grid.init.setPage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            grid.init.setCheckOnBox(true);
            grid.init.setKeepCheck(true);
            // 에디팅 모드 적용
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(true); // 에디팅 컬럼이 아닌 경우, 흐른 필름을 위에 표시할지 여부
            grid.init.setEmptyMessage(false);
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
        updateImportLneInputInfo : function() {
        	MMB007_01_modify = true;
        	MMB007_01_changes = false;
        	
        	form.handle.setValue("MMB007_01_form_01", "gridData", "");
        	
        	MMB007_01.control.selectImportLneInputList();
        	
        	dialog.handle.close(dialog.getObject('MMB007_01_dailog_01'));
        },
        updateImportLneDealNetwght : function() {
        	MMB007_01_modify = true;
        	MMB007_01_changes = false;
        	
        	form.handle.setValue("MMB007_01_form_01", "gridData", "");
        	
        	MMB007_01.control.selectImportLneInputList();
        },
        updateImportLneDealName : function() {
        	MMB007_01_modify = true;
        	MMB007_01_changes = false;
        	
        	form.handle.setValue("MMB007_01_form_01", "gridData", "");
        	
        	MMB007_01.control.selectImportLneInputList();
        }
    },
    // 업무구현
    control : {
    	selectImportLneInputList : function() {
    		var dg_1 = grid.getObject("MMB007_01_grid_01");
    		var params = form.handle.getElementsParam("MMB007_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB007_01/selectImportLneInputList", params);
    	},
    	updateImportLneInputInfo : function() {
    		var dg_1 = grid.getObject("MMB007_01_grid_01");
        	dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
        	
        	var allRows = grid.handle.getAllRows(dg_1, "1");
        	var rowCnt = grid.handle.getRowCount(dg_1);
        	var warr = "";
            
    		// 표준품명코드가 필수인 경우 체크
        	for(var i = 0; i < rowCnt; i++) {        		
        		if(allRows[i]["STD_PRDNM_YN"] == "Y" && oUtil.isNull(allRows[i]["STD_PRDNM_CD"])) {
        			alert(resource.getMessage("CC_표준품명코드가 필수(√)로 지정된 열은 반드시 입력해야 합니다."));
        			
        			grid.handle.selectRowIndex(dg_1, i);
        			return;
        		}
                
                warr += MMB007_01.util.lenChecker(allRows[i], i+1);
        	}
        	
            if(!oUtil.isNull(warr)) {
                $.messager.confirm(resource.getMessage("CNFIR"), "데이터 유효성 오류가 발생했습니다. 무시하시고 진행할 경우에는 [OK]를 클릭하세요.<br><br><textarea style=\"width:100%;height:120px;\">"+warr+"</textarea>", function(flag) {
                    if(flag) {
                        MMB007_01.control.saveImportLneInputInfo(allRows);
                    }
                }); 
            } else {
                MMB007_01.control.saveImportLneInputInfo(allRows);
            }
        },
        saveImportLneInputInfo : function(allRows) {
        	$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {
                if (flag) {
                	var rowParam = grid.util.convertJson2Rows(allRows);
                    form.handle.setValue("MMB007_01_form_01", "gridData", rowParam);
                	
                    var obj = form.getObject("MMB007_01_form_01");
                    
                    form.init.setURL("/mm/pop/MMB007_01/updateImportLneInputInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateImportLneInputInfo");
                    
                    form.submit(obj);
                }
            });
        },
        updateImportLneDealName : function() {
        	$.messager.confirm(CNFIR, resource.getMessage("CC_거래품목을 변경하시겠습니까?"), function(flag) {
                if (flag) {
                	var dg_1 = grid.getObject("MMB007_01_grid_01");
                	dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
                	
                	var chgs = grid.handle.getAllRows(dg_1); // 에디팅으로 인한 변경된 데이터만 추출하는 것에서 전체로 변경했음(2021-04-18)
                	var rowParam = grid.util.convertJson2Rows(chgs);
                	
                    form.handle.setValue("MMB007_01_form_01", "gridData", rowParam);
                	
                    var obj = form.getObject("MMB007_01_form_01");
                    
                    form.init.setURL("/mm/pop/MMB007_01/updateImportLneDealName");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateImportLneDealName");
                    
                    form.submit(obj);
                }
            });
        },
        updateImportLneDealNetwght : function(flags) {
        	var totNetwght = form.handle.getValue("MMB007_01_form_01", "TOT_NETWGHT");
        	
        	if(oUtil.isNull(totNetwght)) {
        		alert(resource.getMessage("CC_신고할 순중량의 총량을 입력해 주세요."));
        		return;
        	}
        	
        	var dg_1 = grid.getObject("MMB007_01_grid_01");
        	dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
        	
        	var rowCnt = grid.handle.getRowCount(dg_1);
        	var allRows = grid.handle.getAllRows(dg_1);
        	var rowParam = grid.util.convertJson2Rows(allRows);
        	
        	// 란기준 안분
        	if(flags == "L") {
        		form.handle.setValue("MMB007_01_form_01", "AVG_NETWGHT", totNetwght/rowCnt);
        	} else if(flags == "Q") {
        		var totQy = 0;
        		
        		// 전체 수량 구하기
        		for(var i = 0; i < rowCnt; i++) {
        			totQy += allRows[i]["TOT_QY"];
        		}
        		
        		if(oUtil.isNull(totQy) || totQy <= 0) {
        			alert(resource.getMessage("CC_입력된 수량이 없습니다."));
        			return;
        		}
        		
        		form.handle.setValue("MMB007_01_form_01", "DCLRT_QY", totQy);
        	} else if(flags == "A") {
        		var totAmt = 0;
        		
        		// 전체 수량 구하기
        		for(var i = 0; i < rowCnt; i++) {
        			totAmt += allRows[i]["SANC_AMT"];
        		}
        		
        		if(oUtil.isNull(totAmt) || totAmt <= 0) {
        			alert(resource.getMessage("CC_입력된 결재금액이 없습니다."));
        			return;
        		}
        		
        		form.handle.setValue("MMB007_01_form_01", "DCLRT_AMT", totAmt);
        	}
        	
        	var msg = resource.getMessage("CC_순중량 안분을 실행하시겠습니까?");
        	
        	for(var i = 0; i < rowCnt; i++) {        		
        		if(allRows[i]["NETWGHT"] > 0) {
        			msg = "기 입력된 순중량이 존해하며 실행 후에는 재 계산된 값으로 변경됩니다.<br><br>그래도 " + msg;
        			break;
        		}
        	}
        	
        	$.messager.confirm(CNFIR, msg, function(flag) {
                if (flag) {
                    
                    form.handle.setValue("MMB007_01_form_01", "gridData", rowParam);
                    form.handle.setValue("MMB007_01_form_01", "PRPDVS_STDR", flags);
                	
                    var obj = form.getObject("MMB007_01_form_01");
                    
                    form.init.setURL("/mm/pop/MMB007_01/updateImportLneDealNetwght");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateImportLneDealNetwght");
                    
                    form.submit(obj);
                }
            });
        },
        updateSelectText : function(colmn) {
            var dg_1 = grid.getObject("MMB007_01_grid_01");
            dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("CC_복사할 값을 선택해 주세요."));
                return;
            }
            
            var fieldId = "";
            
            if(colmn == 1) fieldId = "DEAL_PRDNM"; // 거래품명
            else if(colmn == 2) fieldId = "COTDOO"; // 원산지 표시결정
            else if(colmn == 3) fieldId = "ORPL_AT"; // 유무
            else if(colmn == 4) fieldId = "ORPL_MTH"; // 방법
            else if(colmn == 5) fieldId = "ORPL_EXEPT"; // 면제
            
            var gdata = grid.handle.getSelectedRowData(dg_1, fieldId);
            if(oUtil.isNull(gdata)) {
                alert(resource.getMessage("CC_복사할 값이 비어 있습니다. 확인 후 다시 시도해 주세요."));
                return;
            }
            
            if(!grid.handle.isChecked(dg_1)) {
                alert(resource.getMessage("CC_선택된 값을 입력할 행을 체크해 주세요."));
                return;
            }
            
            $.messager.confirm(resource.getMessage("CNFIR"), gdata+resource.getMessage("CC_(으)로 체크된 행에 값을 동일하게 입력합니다. 입력 후 저장을 수행해야 적용됩니다.<br><br>입력하시겠습니까?"), function(flag) {
                if(flag) {
                    var rows = grid.handle.getAllRows(dg_1);
                    var crows = grid.handle.getCheckedRowsData(dg_1); 
                    var cnt = 0;
                    
                    for(var i = 0; i < crows.length; i++) {
                        crows[i][fieldId] = gdata;
                        cnt++;
                    }
                    
                    if(cnt == 0) {
                        alert(resource.getMessage("CC_이미 모든 행에 값이 입력되어 있습니다. 확인 후 다시 시도해 주세요."));
                    } else {
                        grid.handle.setLoadData(dg_1, rows);
                    }
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {
    	editObj : null,
    	openDialog_1 : function(obj) {
            this.editObj = obj;
    		
            var dg_1 = grid.getObject("MMB007_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var params = new Object();
            
            params.TARGET_PID = "MMB007_01";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.HS_CD = rowData.HS_CD;
            params.STT_DE = rowData.STT_DE;
            if(!oUtil.isNull(rowData.STT_DE) && rowData.STT_DE.length > 4) {                
                params.YYYY = rowData.STT_DE.substring(0, 4);
            } else {
                params.YYYY = rowData.STT_DE;
            }
            
    		var dl_1 = dialog.getObject("MMB002_10_dailog_01");
			
    		dialog.init.setTitle(resource.getMessage("CC_[f]표준품명 조회"));
			dialog.init.setQueryParams(params);
            dialog.init.setWidth(1123);
            dialog.init.setHeight(600);
            dialog.init.setResizable(true);
            dialog.init.setURL("/mm/pop/MMB002_10");
            
            dialog.open(dl_1);
        },
        callByMMB002_10 : function(rowData) {
    		// 그리드 값 변경
    		var dg_1 = grid.getObject("MMB007_01_grid_01");
            var selData = grid.handle.getSelectedRowData(dg_1);
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            
            if(rowData.ONLY_STD_CODE_YN == "N") selData.PRDNM = rowData.STD_PRDNM_ENG;
            selData.STD_PRDNM_CD = rowData.STD_PRDNM_CD;
            
            grid.handle.setChangeRowValue(dg_1, idx, selData);
            
            MMB007_01_changes = true;
            
        	var dl_1 = dialog.getObject("MMB002_10_dailog_01");
        	dialog.handle.close(dl_1);
        },
        closeThisDialog : function() {
            var dg_1 = grid.getObject("MMB007_01_grid_01");
            var rows = grid.handle.getAllRows(dg_1);
            var warr = "";
                    
            // 신규등록하는지 체크함
            for(var i = 0; i < rows.length; i++) {
                warr += MMB007_01.util.lenChecker(rows[i], i+1);
            }
            
            if(!oUtil.isNull(warr)) {
                $.messager.confirm(resource.getMessage("CNFIR"), "데이터 유효성 오류가 발생했습니다. 무시하시고 진행할 경우에는 [OK]를 클릭하세요.<br><br><textarea style=\"width:100%;height:120px;\">"+warr+"</textarea>", function(flag) {
                    if(flag) {
                        MMB007_01.dialog.dialogClose();
                    }
                }); 
            } else {
                MMB007_01.dialog.dialogClose();
            }
        },
        dialogClose : function() {
        	var dg_1 = grid.getObject("MMB007_01_grid_01");
        	dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
        	
        	var chgCnt = grid.handle.getChangesCount(dg_1);
        	
        	if(chgCnt > 0 || MMB007_01_changes) {
        		$.messager.confirm(CNFIR, resource.getMessage("CC_란입력 목록에 변경된 데이터가 있습니다.<br>저장하지 않고 진행할 경우 OK버튼을 클릭하세요.<br><br>창을 닫으시겠습니다?"), function(flag) {
                    if (flag) {
                    	if(MMB007_01_modify) IC1004_01.control.selectImportOnetapMstInfo();
                    	
                    	dialog.handle.close(dialog.getObject('MMB007_01_dailog_01'));
                    }
                });
        	} else {
        		if(MMB007_01_modify) IC1004_01.control.selectImportOnetapMstInfo();
        		
        		dialog.handle.close(dialog.getObject('MMB007_01_dailog_01'));
        	}
        },
        openDialog_2 : function(stype) {
            var dg_1 = grid.getObject("MMB007_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var params = new Object();
            
            params.TARGET_PID = "MMB007_01";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.HS_CD = rowData.HS_CD;
            params.BCNC_CD = rowData.BCNC_CD;
            params.CC_TYPE = form.handle.getValue("MMB007_01_form_01", "CC_TYPE");
            params.SEARCH_TYPE = stype;
            
            MMB007_01_changes = true;
            
    		var dl_1 = dialog.getObject("MMB007_02_dailog_01");
    		if(stype == "D") dialog.init.setTitle(resource.getMessage("CC_[f]업체 신고이력 조회 - 거래품명"));
    		else dialog.init.setTitle(resource.getMessage("CC_[f]업체 신고이력 조회 - 품명"));
    		dialog.init.setQueryParams(params);
            dialog.init.setWidth(550);
            dialog.init.setHeight(500);
            dialog.init.setURL("/mm/pop/MMB007_02");
            
            dialog.open(dl_1);
        },
        callByMMB007_01 : function(data) {
            var dg_1 = grid.getObject("MMB007_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            
            var stype = form.handle.getValue("MMB007_02_form_01", "SEARCH_TYPE");
            
            if(stype == "D") rowData.DEAL_PRDNM = data.PRDNM;
            else rowData.PRDNM = data.PRDNM;
            
            grid.handle.setChangeRowValue(dg_1, idx, rowData);
        	
        	dialog.handle.close(dialog.getObject('MMB007_02_dailog_01'));
        },
        openDialog_3 : function(field) {
            var params = new Object();
            
            params.TARGET_PID = "MMB007_01";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.FIELD_ID = field;
            
            var dl_1 = dialog.getObject("MMB002_04_dailog_01");
            
            if(field == "COTDOO") {                
                params.CATEGORY_CD = "C_ORCY_DTRM_BASE_CD";
                dialog.init.setTitle(resource.getMessage("CC_[f]원산지 결정기준 조회"));
            } else if(field == "ORPL_AT") {                
                params.CATEGORY_CD = "C_ORCY_INDC_EON_TPCD";
                dialog.init.setTitle(resource.getMessage("CC_[f]원산지 표시유무 조회"));
            } else if(field == "ORPL_MTH") {                
                params.CATEGORY_CD = "C_ORCY_INDC_MCD";
                dialog.init.setTitle(resource.getMessage("CC_[f]원산지 표시방법 조회"));
            } else if(field == "ORPL_EXEPT") {                
                params.CATEGORY_CD = "C_ORCY_INDC_EXMP_RCD";
                dialog.init.setTitle(resource.getMessage("CC_[f]원산지 표시면제사유 조회"));
            }
            
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(500);
            dialog.init.setHeight(500);
            dialog.init.setURL("/mm/pop/MMB002_04");
            
            dialog.open(dl_1);
        },
        callByMMB002_04 : function(data) {
            var dg_1 = grid.getObject("MMB007_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            
            if(data.FIELD_ID == "COTDOO") {
                rowData.COTDOO = data.CODE;
            } else if(data.FIELD_ID == "ORPL_AT") {
                rowData.ORPL_AT = data.CODE;
            } else if(data.FIELD_ID == "ORPL_MTH") {
                rowData.ORPL_MTH = data.CODE;
            } else if(data.FIELD_ID == "ORPL_EXEPT") {
                rowData.ORPL_EXEPT = data.CODE;
            } 
            
            grid.handle.setChangeRowValue(dg_1, idx, rowData);
            
            var dg_1 = dialog.getObject("MMB002_04_dailog_01");
            dialog.handle.close(dg_1);
        }
    },
    // 화면 UI 변경
    ui : {
    	drawStdPrdmn : function(value, row, index) {
    		if(row.STD_PRDNM_YN == "Y") {
    			return "√";
    		} else {
    			return "";
    		}
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    util : {
        lenChecker : function(data, rownum) {
            var warr = "";
            
            // 유효성 경고메시지 체크
            if(!oUtil.isNull(data.PRDNM)) if(data.PRDNM.length > 50) warr += rownum+"번째열의 품명이 "+data.PRDNM.length+"자로 최대 문자수(50자)를 초과했습니다.\n";
            if(!oUtil.isNull(data.DEAL_PRDNM)) if(data.DEAL_PRDNM.length > 50) warr += rownum+"번째열의 거래품명이 "+data.DEAL_PRDNM.length+"자로 최대 문자수(50자)를 초과했습니다.\n";
            
            return warr;
        } 
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
