/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7003_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7003_02.config.applyFtaNation();
    SM7003_02.init.initComponent();
}

var SM7003_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            var flag = form.handle.getValue("SM7003_02_form_01", "flag");
            
            SM7003_02.calendar.initCalendar_1();
            SM7003_02.calendar.initCalendar_2();
            
            SM7003_02.combobox.initCombo_1();
            SM7003_02.combobox.initCombo_2();
            SM7003_02.combobox.initCombo_3();

            if (flag == "view") {
                SM7003_02.ui.viewChangeUI();
            } else if (flag == "update") {
                SM7003_02.ui.updateChangeUI();
            } else {
                SM7003_02.ui.insertChangeUI();
            }

            if (flag != "insert") {            	
                SM7003_02.control.selectUserInfo();
            }
                       
            if(SESSION.USER_ID == "fta" || form.handle.getValue("SM7003_02_form_01", "COMPANY_CD") == "KJ100") {
            	form.util.setVisible("SM7003_02_tr_01", true);
            	SM7003_02.combobox.initCombo_4();
            }
            
            // 통관 OPENAPI 인증키 설정
            if(TOP_SYS_ID == "CC") {
            	dialog.handle.resize("SM7003_02_dailog_01", 1124, 610);
            	form.handle.setValue("SM7003_02_form_01", "TOP_SYS_ID", TOP_SYS_ID);
            	form.util.setVisible("SM7003_02_CC_01", true);            	
            	SM7003_02.datagrid.initGrid_1();
            	SM7003_02.datagrid.initGrid_2();
            	
	            setTimeout(function() { 
	            	if (flag != "insert") {
						SM7003_02.control.selectUserEntrInfo();	
	            		SM7003_02.control.selectUserOpenApiKeyList();	// 인증키
	            		SM7003_02.control.selectUserEntrBcncList();		// 거래처
	            	}
            	}, 1000);	            	
            }           
        }
    }, 
    // 달력 그리드 생성
    calendar : {
    	initCalendar_1 : function() {
            var obj = calendar.getObject("SM7003_02_form_01", "START_DATE");
            var flag = form.handle.getValue("SM7003_02_form_01", "flag");
            var vDate01 = form.handle.getValue("SM7003_02_form_01", "START_DATE");
            
            if(flag == "insert") {
                vDate = calendar.util.getDate2String("20000101","-");
                calendar.init.setInitDate(vDate);
            }
            
            calendar.create(obj);
        },
        initCalendar_2 : function() {
            var obj = calendar.getObject("SM7003_02_form_01", "END_DATE");
            var flag = form.handle.getValue("SM7003_02_form_01", "flag");
            var vDate02 = form.handle.getValue("SM7003_02_form_01", "END_DATE");
            
            if(flag == "insert") {
                vDate = calendar.util.getDate2String("29991231", "-");
                calendar.init.setInitDate(vDate);
            }
            
            calendar.create(obj);
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {
        dataYn : [
        	{CODE:" ", NAME:resource.getMessage("NOSEL")},
        	{CODE:"Y", NAME:resource.getMessage("TXT_YES")},
        	{CODE:"N", NAME:resource.getMessage("TXT_NO")}
        ],
        initCombo_1 : function() {
            var obj_01 = combo.getObject("SM7003_02_form_01", "STATUS");
            
            combo.init.setData(this.dataYn);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_01);
        },
        initCombo_2 : function() {
            var obj_02 = combo.getObject("SM7003_02_form_01", "MANAGER_YN");
            
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"MANAGER_TYPE"});
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_02);
        },
        initCombo_3 : function() {
            var obj_02 = combo.getObject("SM7003_02_form_01", "DEFAULT_LANGUAGE");
            
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LENG_CD"});
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_02);
        },
        initCombo_4 : function() {
            var obj_01 = combo.getObject("SM7003_02_form_01", "MAIL_SENDER_YN");
            
            combo.init.setData(this.dataYn);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
        	var dg_1 = grid.getObject("SM7003_02_grid_01");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");

            // 셀 에디팅 설정
        	grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        },
    	initGrid_2 : function() {
        	var dg_1 = grid.getObject("SM7003_02_grid_02");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_2");

            // 셀 에디팅 설정
        	grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header2"}});
        }        
    },
    // 이벤트 처리
    event : {
        selectAfterUI : function(data) {
        	if(SESSION.USER_ID != data.USER_ID) {
        		data.MAIL_PASSWORD = "";
        	}
            form.handle.loadData("SM7003_02_form_01", data);
        },
        // 사용자 통관 데이터 맵핑
        selectUserEntrInfoUI : function(data) {
        	form.handle.setValue("SM7003_02_form_01", "USER_ENTR_SN", data.USER_ENTR_SN);
        	form.handle.setValue("SM7003_02_form_01", "APLC_CD", data.APLC_CD);
        	form.handle.setValue("SM7003_02_form_01", "APLC_NM", data.APLC_NM);
        },
        updateAfterUI : function(data) {
            
            var oMap = null;
            try {
                oMap = data.dataMap.map;    
            } catch (e) {
                oMap = {};
            }
            
            if (form.handle.getValue("SM7003_02_form_01", "flag") == "insert") {
                form.handle.setValue("SM7003_02_form_01", "flag", "update");
                
                form.handle.setValue("SM7003_02_form_01", "PASSWORD", "");
                form.handle.setValue("SM7003_02_form_01", "PASSWORD_CFM", "");
            }
            
            SM7003_02.ui.updateChangeUI();
            
            // 통관 OPENAPI 인증키 설정
            if(TOP_SYS_ID == "CC") {
            	SM7003_02.control.selectUserOpenApiKeyList();	// 인증키
            	SM7003_02.control.selectUserEntrInfo();			// 신고인
            	SM7003_02.control.selectUserEntrBcncList();		// 거래처
            }            
            
            if (form.handle.getValue("SM7003_02_form_01", "callPid") == "SM7003_01") {
                SM7003_01.control.selectUserList();
            } else if (form.handle.getValue("SM7003_02_form_01", "callPid") == "SM7016_01") {
                SM7016_01.control.selectUserList();
            } else {
                var dl_1 = dialog.getObject("SM7003_02_dailog_01");
                dialog.handle.close(dl_1);   
            }
        },
        deleteAfterUI : function(data) {
            if (form.handle.getValue("SM7003_02_form_01", "callPid") == "SM7003_01") {
                SM7003_01.control.selectUserList();
            } else if(form.handle.getValue("SM7003_02_form_01", "callPid") == "SM7016_01") {
                SM7016_01.control.selectUserList();
            }
            
            var dl_1 = dialog.getObject("SM7003_02_dailog_01");
            dialog.handle.close(dl_1);
        },
        deleteUserEntrBcnc : function() {
			SM7003_02.control.selectUserEntrBcncList();		// 거래처
		}
    },
    // 업무구현
    control : {
        selectUserInfo : function() {
            var obj = form.getObject("SM7003_02_form_01");
            
            form.init.setURL("/fm/sm/sm7003_02/selectUserInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        updateUserInfo : function() {
            
            if (!form.handle.isValidate("SM7003_02_form_01")) return;
            
            var vFlag = form.handle.getValue("SM7003_02_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            var confMsg = "";
            var dg_1;
            var rows;
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }

            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7003_02_form_01");
                    
                    if (form.handle.getValue("SM7003_02_form_01", "flag") == "insert") {
                        form.init.setURL("/fm/sm/sm7003_02/insertUserInfo");
                    }
                    
                    if (form.handle.getValue("SM7003_02_form_01", "flag") == "update") {
                        form.init.setURL("/fm/sm/sm7003_02/updateUserInfo");
                    	if(TOP_SYS_ID == "CC") {
							// 인증키
        	        		dg_1 = grid.getObject("SM7003_02_grid_01");
        	        		dg_1.datagrid('endCellEdit'); 
        	        		rows = grid.handle.getChangesData(dg_1);
        	        		form.handle.setValue("SM7003_02_form_01", "gridData", grid.util.convertJson2Rows(rows));
							// 통관 거래처
        	        		dg_2 = grid.getObject("SM7003_02_grid_02");
        	        		dg_2.datagrid('endCellEdit'); 
        	        		rows2 = grid.handle.getChangesData(dg_2);
        	        		form.handle.setValue("SM7003_02_form_01", "gridData1", grid.util.convertJson2Rows(rows2));        	        		
                    	}                        
                    }
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");

                    form.submit(obj);
                }
            });
        },
        deleteUserInfo : function() {
            var vFlag = form.handle.getValue("SM7003_02_form_01", "flag");
            var userId = form.handle.getValue("SM7003_02_form_01", "USER_ID");
            
            if(userId == "supplier" || userId == "fta") {
            	alert(resource.getMessage("MSG_NO_ROLE_DELETE"));
            	return;
            }
            
            if (!this.validate(vFlag)) {
                return;
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7003_02_form_01");
                    
                    form.init.setURL("/fm/sm/sm7003_02/deleteUserInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        validate : function(pType) {
            var vFlag = nullToString(form.handle.getValue("SM7003_02_form_01", "flag"));
            
            var vCompanyCd = nullToString(form.handle.getValue("SM7003_02_form_01", "COMPANY_CD"));
            var vUserId = nullToString(form.handle.getValue("SM7003_02_form_01", "USER_ID"));
            var vPasswordOrg = nullToString(form.handle.getValue("SM7003_02_form_01", "PASSWORD_ORG"));
            var vPassword = nullToString(form.handle.getValue("SM7003_02_form_01", "PASSWORD"));
            var vPasswordCfm = nullToString(form.handle.getValue("SM7003_02_form_01", "PASSWORD_CFM"));
            
            if (oUtil.isNull(vCompanyCd)) {
                alert(resource.getMessage("MSG_NOT_FOUND_COMPANY_CD"));
                return false;
            }
            
            if (oUtil.isNull(vUserId)) {
                alert(resource.getMessage("USER,ID, MSG_NOT_FOUND_01"));
                return false;
            }
            
            if (vFlag == "update") {
                if ((oUtil.isNull(vPassword) && !oUtil.isNull(vPasswordCfm)) || (!oUtil.isNull(vPassword) && oUtil.isNull(vPasswordCfm))) {
                    alert(resource.getMessage("MOD, MSG_CHK_PWD"));
                    return;
                }
            }
            
            if (!oUtil.isNull(vPassword) && !oUtil.isNull(vPasswordCfm) && vPassword != vPasswordCfm) {
                alert(resource.getMessage("MSG_CHK_PWD"));
                return;
            }

            return true;
        },
        // 사용자 OPENAPI 인증키 설정
        selectUserOpenApiKeyList : function() {
            //if (!form.handle.isValidate("SM7003_02_form_01")) return;		// FORM 개체 유효성 체크
    		var dg_1 = grid.getObject("SM7003_02_grid_01");
     		var params = form.handle.getElementsParam("SM7003_02_form_01");
    		
     		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7003_02/selectUserOpenApiKeyList", params);    		
    	},
    	// 사용자 통관 조회
    	selectUserEntrInfo : function() {
            var obj = form.getObject("SM7003_02_form_01");
            
            form.init.setURL("/fm/sm/sm7003_02/selectUserEntrInfo");
            form.init.setCallBackFunction("selectUserEntrInfoUI");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);     		
    	},
        // 사용자 통관 거래처
        selectUserEntrBcncList : function() {
            //if (!form.handle.isValidate("SM7003_02_form_01")) return;		// FORM 개체 유효성 체크
    		var dg_1 = grid.getObject("SM7003_02_grid_02");
     		var params = form.handle.getElementsParam("SM7003_02_form_01");
    		
     		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7003_02/selectUserEntrBcncList", params);    		
    	},
        // 통관 거래처 삭제
        deleteUserEntrBcnc : function() {
            var dg_1 = grid.getObject("SM7003_02_grid_02");
            //var userId = form.handle.setValue("SM7003_02_form_01", "USER_ID");

            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }

            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7003_02_form_01");                    
                    
                    var rowData = grid.handle.getSelectedRowData(dg_1);               
                    
                    if (!oUtil.isNull(rowData.USER_ID)) {
						userId = rowData.USER_ID;
					}
					
                    //form.handle.setValue("SM7003_02_form_01", "USER_ID", userId);
                    form.handle.setValue("SM7003_02_form_01", "USER_ENTR_BCNC_SN", rowData.USER_ENTR_BCNC_SN);
                    form.init.setURL("/fm/sm/sm7003_02/deleteUserEntrBcnc");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    // form.init.setCallBackFunction("deleteUserEntrBcnc");
                    
                    SM7003_02.ui.deleteRow('2');
                    
                    form.submit(obj);
                }
            });
        } 
    },
    // 다이얼로그 구현
    dialog : {
        openDialog : function(pType) {
            
        },
        openDialog_14 : function() {
            var params = new Object();
            
            params.TARGET_PID = "SM7003_02";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            
    		var dl_1 = dialog.getObject("MMB002_14_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("CC_[f]관세사 조회"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(900);
            dialog.init.setHeight(600);
            dialog.init.setURL("/mm/pop/MMB002_14");
            
            dialog.open(dl_1);
        },
        callByMMB002_14 : function(rowData) {
        	form.handle.setValue("SM7003_02_form_01", "APLC_CD", rowData.CBOA_SL);
        	form.handle.setValue("SM7003_02_form_01", "APLC_NM", rowData.CBOA_NM);
        	
        	var dl_1 = dialog.getObject("MMB002_14_dailog_01");
        	dialog.handle.close(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
        insertChangeUI : function() {
            var vDefaultLanguage = form.handle.getValue("SM7003_02_form_01", "DEFAULT_LANGUAGE");
            
            form.util.setVisible("SM7003_02_btn_02", false);
            if(form.handle.getValue("SM7003_02_form_01", "callPid") == 'SM7003_01') {
            	form.util.setVisible("SM7003_02_span_02", false);
            }
            
            form.handle.setValidate("SM7003_02_form_01", "PASSWORD", true);
            form.handle.setValidate("SM7003_02_form_01", "PASSWORD_CFM", true);
        },
        updateChangeUI : function() {
            var userId = form.handle.getValue("SM7003_02_form_01", "USER_ID");
            var vDefaultLanguage = form.handle.getValue("SM7003_02_form_01", "DEFAULT_LANGUAGE");
            
            form.util.setVisible("SM7003_02_btn_02", true);
            
            form.handle.setValidate("SM7003_02_form_01", "PASSWORD", false);
            form.handle.setValidate("SM7003_02_form_01", "PASSWORD_CFM", false);
            
            form.handle.readonly("SM7003_02_form_01", "USER_ID", true);
            form.handle.addClass("SM7003_02_form_01", "USER_ID", "input_readOnly");
            
            if((userId == "supplier" || userId == "fta") &&
            		form.handle.getValue("SM7003_02_form_01", "callPid") == 'SM7003_01') {
            	form.util.setVisible("SM7003_02_span_02", false);
            }
        },
        viewChangeUI : function() {
            var vDefaultLanguage = form.handle.getValue("SM7003_02_form_01", "DEFAULT_LANGUAGE");
            
            form.handle.readonly("SM7003_02_form_01", "PASSWORD", true);
            form.handle.addClass("SM7003_02_form_01", "PASSWORD", "input_readOnly");
            form.handle.readonly("SM7003_02_form_01", "PASSWORD_CFM", true);
            form.handle.addClass("SM7003_02_form_01", "PASSWORD_CFM", "input_readOnly");
            
            form.handle.removeClass("SM7003_02_form_01", "PASSWORD", "easyui-validatebox");
            form.handle.removeClass("SM7003_02_form_01", "PASSWORD_CFM", "easyui-validatebox");
            
            form.handle.removeClass("SM7003_02_form_01", "PASSWORD", "validatebox-text");
            form.handle.removeClass("SM7003_02_form_01", "PASSWORD_CFM", "validatebox-text");
            
            // 버튼감싸는영역
            form.util.setVisible("SM7003_02_span_01", false);
            form.util.setVisible("SM7003_02_span_02", false);
            
            // form안 모든 element에 대해서 label과 같은 형식으로 표시한다.
            form.handle.closeAll("SM7003_02_form_01");
        },
        insertRow : function(i) {
        	var dg_1 = grid.getObject("SM7003_02_grid_0"+i);
        	var rowCnt = grid.handle.getRowCount(dg_1);
        	var data = SM7003_02.util.getInitRowData(rowCnt+1);
        	
        	grid.handle.appendRow(dg_1, data);
        },
        deleteRow : function(i) {
        	var dg_1 = grid.getObject("SM7003_02_grid_0"+i);
        	
	        if(!grid.handle.isSelected(dg_1)) {
        		alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	var idx = grid.handle.getCurrentRowIndex(dg_1);
        	
        	grid.handle.removeRow(dg_1, idx);
        },
        moveRow : function(i, step) {
        	var dg_1 = grid.getObject("SM7003_02_grid_0"+i);
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
    util : {
    	getInitRowData : function(idx) {
    		var hcell = new Object();
    		
    		hcell.CELL_FIELD     = "COLUMN_"+idx; 
        	hcell.CELL_TITLE     = "CC_";
        	hcell.CELL_WIDTH     = "100";
        	hcell.CELL_ALIGN     = "center";
        	hcell.CELL_HALIGN    = "center";
        	hcell.CELL_SORTABLE  = "true";
        	hcell.CELL_HIDDEN    = "false";
        	hcell.CELL_EDITOR    = null;
        	hcell.CELL_CHECKBOX  = null;
        	hcell.CELL_FORMATTER = null;
        	hcell.CELL_ROWSPAN   = "0";
        	hcell.CELL_COLSPAN   = "0";
        	hcell.CELL_FROZEN    = null;
        	hcell.CELL_STYLER    = null;
        	hcell.CELL_SPAN_CNT  = null;
        	
        	return hcell;
    	}
    },   
    config : {
    	applyFtaNation : function() {
    		var mailIntgYn = form.handle.getValue("SM7003_02_form_01", "EMAIL_AUTH_INTG_YN");
    		
    		if(mailIntgYn == "Y") {
    			form.handle.readonly("SM7003_02_form_01", "MAIL_ID", true);
                form.handle.addClass("SM7003_02_form_01", "MAIL_ID", "input_readOnly");
                form.handle.readonly("SM7003_02_form_01", "MAIL_PASSWORD", true);
                form.handle.addClass("SM7003_02_form_01", "MAIL_PASSWORD", "input_readOnly");
                
                $("#SM7003_02_label_01").html("* 사내 정책에 따라 메일계정이 하나로 관리되고 있어, 메일 인증 ID와 비밀번호 입력이 불가합니다.");
    		}
    	}
    }
    
};
