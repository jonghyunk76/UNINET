/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7004_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7004_02.config.applyFtaNation();
    SM7004_02.init.initComponent();
}

var SM7004_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            var flag = form.handle.getValue("SM7004_02_form_01", "flag");

            // 초기화면에 보여줄 데이터 조회
            if (flag != "insert") {
                SM7004_02.control.selectScheduleInfo();
            }
            
            SM7004_02.calendar.initCalendar_1();
            SM7004_02.calendar.initCalendar_2();
            SM7004_02.combobox.initCombo_1();
            SM7004_02.datagrid.initGrid_1();
            
            // ui변경
            if (flag == "view") {
                SM7004_02.ui.viewChangeUI();
            } else if (flag == "update") {
                SM7004_02.ui.updateChangeUI();
            } else {
                SM7004_02.ui.insertChangeUI();
            }
        }
    }, 
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
            var obj_01 = calendar.getObject("SM7004_02_form_01", "APPLY_FROM_DATE");
            
            calendar.init.setRequired(true);
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	var obj_02 = calendar.getObject("SM7004_02_form_01", "APPLY_TO_DATE");
            
        	calendar.init.setInitDate(calendar.util.getDate2String("29991231", "-"));
            calendar.init.setRequired(true);
            
            calendar.create(obj_02);
            
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {
        initCombo_1 : function() {
            var vCompanyCd = form.handle.getValue("SM7004_02_form_01", "COMPANY_CD");
            var objSystemBatchYn = combo.getObject("SM7004_02_form_01", "SYSTEM_BATCH_YN");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, COMPANY_CD:vCompanyCd, CATEGORY_CD:"YN_TYPE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSystemBatchYn);
        }
    },
    editor : {
        gridSearchbox_1 : function(obj) {
            SM7004_02.dialog.openDialog_1();
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : null,
        initGrid_1 : function() {
            if(TOP_SYS_ID == "RS") {
                this.header1 = [
                                ["IF_CD"           , resource.getMessage("CC_서비스ID")        /*서비스 코드*/      , 120, "center"  , "center", true, false, null, null, null, 0, 0],
                                ["IF_NAME"         , resource.getMessage("CC_서비스명")        /*서비스 명*/        , 200, "left"  , "center", true, false, {type:"searchbox",options:{pid:'SM7004_02',searcher:'gridSearchbox_1'}}, null, null, 0, 0]
                           ];
            } else {
                this.header1 = [
                                ["IF_CD"         , resource.getMessage("INF, CODE")        /*인터페이스 코드*/      , 120, "center"  , "center", true, false, null, null, null, 0, 0],
                                ["IF_NAME"         , resource.getMessage("INF, NAME")        /*인터페이스 명*/      , 200, "left"  , "center", true, false, null, null, null, 0, 0],
                                ["IF_PARENT_NAME"  , resource.getMessage("PARNT, INF, NAME") /*상위인터페이스 명*/    , 200, "left", "center", true, false, null, null, null, 0, 0],
                                ["IF_METHOD_NAME"  , resource.getMessage("INF, METHD")       /*인터페이스 방법*/     , 150, "center"  , "center", true, false, null, null, null, 0, 0],
                                ["REQUIRED_YN_NAME", resource.getMessage("REQUD,ORNOT")      /*필수여부*/         , 120, "center", "center", true, false, null, null, null, 0, 0],
                                ["VALID_CHECK_YN_NAME" , resource.getMessage("EFTIV,CHECK")  /*유효성 체크*/       , 100, "center", "center", true, false, null, null, null, 0, 0],
                                ["SCHEDULE_SEQ"    , resource.getMessage("EXCUS,SEQ")        /*실행순서*/         , 100, "center", "center", true, false, null, null, null, 0, 0]
                           ];
            }
                           
            var dg_1 = grid.getObject("SM7004_02_grid_01");
            
            if(TOP_SYS_ID == "RS") {
                grid.init.setPage(false);
                grid.init.setEmptyMessage(false);
                grid.init.setFitColumns(false);
                // 에디팅 모드 적용
                grid.init.setEditMode(true, "cell");
                grid.init.autoAddRow(false);
                grid.init.setSingleSelect(true);
                grid.init.setShowEditorFilm(false);
                // 이벤트 설정
                grid.event.setOnClickCell(dg_1);
                grid.event.setOnCellEdit(dg_1); 
            } else {
                grid.init.setPage(true);
                grid.init.setFitColumns(true);
                grid.init.setEmptyMessage(false);
                // 이벤트 설정
                grid.event.setOnDblClickRow(dg_1);
            }
            
            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
        // 스케쥴정보 조회가 성공적으로 완료될 경우 호출되는 함수
        selectAfterUI : function(data) {
            form.handle.loadData("SM7004_02_form_01", data);

            SM7004_02.control.selectScheduleMappingList();
        },
        // 성공적으로 요청처리가 완료될 경우 호출되는 함수
        updateAfterUI : function(data) {
            var oMap = null;
            
            try {
                oMap = data.dataMap.map;    
            } catch (e) {
                oMap = {};
            }
            
            // flag가 "update"인 경우, 초기화면 로딩 시 입력란을 컨트롤 함
            if (form.handle.getValue("SM7004_02_form_01", "flag") == "insert") {
                // update 상태로 변경한다.
                form.handle.setValue("SM7004_02_form_01", "flag", "update");
                
                form.handle.setValue("SM7004_02_form_01", "INTERFACE_SCHEDULE_ID", oMap.INTERFACE_SCHEDULE_ID);

            }
            
            // 업데이트 상태로 UI를 변경한다.
            SM7004_02.ui.updateChangeUI();
            
            // 부모조회
            SM7004_01.control.selectScheduleList();
        },
        deleteAfterUI : function(data) {
            SM7004_01.control.selectScheduleList();
            
            var dl_1 = dialog.getObject("SM7004_02_dailog_01");
            dialog.handle.close(dl_1);
        },
        onClick_SM7004_02_grid_01 : function(rowData) {
            //console.log("call onClick_SM7004_02_grid_01");
        },
        onDblClick_SM7004_02_grid_01 : function(rowData) {
            //console.log("call onDblClick_SM7004_02_grid_01");
            SM7004_02.dialog.openDialog('update');
        },
        callBackNoData : function(data) {
            //console.log("call callBackNoData");
        },
        // 콤보박스 변경 이벤트 처리
        onChangeCompany : function(data) {
            //console.log("call onChangeCompany");
        }
    },
    // 업무구현
    control : {
        selectScheduleInfo : function() {
            var obj = form.getObject("SM7004_02_form_01");
            
            form.init.setURL("/fm/sm/sm7004_02/selectScheduleInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        // insert/update 수행
        updateScheduleInfo : function() {
            if (!form.handle.isValidate("SM7004_02_form_01")) return;
            var confMsg = "";
            
            var vFlag = form.handle.getValue("SM7004_02_form_01", "flag");

            if (!this.validate(vFlag)) {
                return
            }
            
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                
                if (flag) {
                    if(TOP_SYS_ID == "RS") {
                        var dg_1 = grid.getObject("SM7004_02_grid_01");
                        var dg_data = grid.handle.getAllRows(dg_1);
                        
                        form.handle.setValue("SM7004_02_form_01", "gridData", grid.util.convertJson2Rows(dg_data));
                    } else {
                        form.handle.setValue("SM7004_02_form_01", "gridData", "");
                    }
                    
                    var obj = form.getObject("SM7004_02_form_01");
                    
                    if (form.handle.getValue("SM7004_02_form_01", "flag") == "insert") {
                        form.init.setURL("/fm/sm/sm7004_02/insertScheduleInfo");
                    }
                    if (form.handle.getValue("SM7004_02_form_01", "flag") == "update") {
                        form.init.setURL("/fm/sm/sm7004_02/updateScheduleInfo");
                    }
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        // delete 수행
        deleteScheduleInfo : function() {
            
            if (!this.validate("delete")) {
                return
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7004_02_form_01");
                    
                    form.init.setURL("/fm/sm/sm7004_02/deleteScheduleInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        selectScheduleMappingList : function() {
            // 검색창의 validation 체크결과를 비교한다.
            if (!form.handle.isValidate("SM7004_02_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("SM7004_02_grid_01");
            var params = form.handle.getElementsParam("SM7004_02_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7004_02/selectScheduleMappingList", params);
        },
        validate : function(pType) {
            
            var vCompanyCd = form.handle.getValue("SM7004_02_form_01", "COMPANY_CD");
            var vScheduleCode = form.handle.getValue("SM7004_02_form_01", "SCHEDULE_CD");
            if (oUtil.isNull(vCompanyCd)) {
                // 회사코드를 찾을 수 없습니다.
                alert(resource.getMessage("MSG_NOT_FOUND_COMPANY_CD"));
                return false;
            }
            
            if (pType != "insert") {
                if (oUtil.isNull(vScheduleCode)) {
                    // 스케줄코드를 찾을 수 없습니다.
                    alert(resource.getMessage("MSG_NOT_FOUND_SCHEDULE_CODE"));
                    return false;
                }
            }
            
            return true;
        }
    },
    // 다이얼로그 구현
    dialog : {
        openDialog : function(flag) {
            if (!form.handle.isValidate("SM7004_02_form_01")) return;
            
            var dg_1 = grid.getObject("SM7004_02_grid_01");
            var dl_1 = dialog.getObject("SM7004_03_dailog_01");
            
            var vScheduleCode = form.handle.getValue("SM7004_02_form_01", "SCHEDULE_CD");
            var vScheduleName = form.handle.getValue("SM7004_02_form_01", "SCHEDULE_NAME");
            
            if (flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("SM7004_02_form_01");
            var params2 = {};
            
            params1.flag = flag;
            if (flag != "insert") {
                params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);
            // 추가파라미터 설정
            params.SCHEDULE_CD = params.SCHEDULE_CD || vScheduleCode;
            params.SCHEDULE_NAME = params.SCHEDULE_NAME || vScheduleName;   
            
            // 팝업 셋팅
            // title:배치 항목등록
            dialog.init.setTitle(resource.getMessage("SM7004_03"));
            dialog.init.setWidth(600);
            dialog.init.setHeight(284);
            dialog.init.setURL("/fm/sm/sm7004_03");
            dialog.init.setResizable(true);
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        },
        openDialog_1 : function() {
            var params = form.handle.getElementsParam("SM7004_02_form_01");
            
            params.TARGET_PID = "SM7004_02";
            
            var dl_1 = dialog.getObject("STR002_03_dailog_01");
            
            dialog.init.setQueryParams(params);
            dialog.init.setURL("/rs/st/stR002_03");
            dialog.init.setWidth(410);
            dialog.init.setHeight(500);
            
            dialog.open(dl_1);
        },
        callBySTR002_03 : function(rowData) {
            var dg_1 = grid.getObject("SM7004_02_grid_01");
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            var selData = grid.handle.getSelectedRowData(dg_1);
            
            selData.IF_CD = rowData.SERVICE_ID;
            selData.IF_NAME = rowData.SERVICE_NAME;
            
            grid.handle.setChangeRowValue(dg_1, idx, selData); 
            
            var dl_1 = dialog.getObject("STR002_03_dailog_01");
            dialog.handle.close(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
        insertChangeUI : function() {
            // 메인 삭제버튼감춤
            form.util.setVisible("SM7004_02_btn_02", false);
            
            // 그리드관련 버튼 비활성화
            form.util.setVisible("SM7004_02_btn_03", false);
            form.util.setVisible("SM7004_02_btn_04", false);
        },
        updateChangeUI : function() {
            // 메인 삭제버튼보임
            form.util.setVisible("SM7004_02_btn_02", true);
            
            // key가 되는 input은 모두 막는다.
            form.handle.readonly("SM7004_02_form_01", "SCHEDULE_CD", true);
            form.handle.addClass("SM7004_02_form_01", "SCHEDULE_CD", "input_readOnly");
            
            // 그리드관련 버튼 활성화
            form.util.setVisible("SM7004_02_btn_03", true);
            form.util.setVisible("SM7004_02_btn_04", true);
        },
        viewChangeUI : function() {
            // 버튼감싸는영역 비활성화
            form.util.setVisible("SM7004_02_div_01", false);
            
            // 그리드관련 버튼 비활성화
            form.util.setVisible("SM7004_02_btn_03", false);
            form.util.setVisible("SM7004_02_btn_04", false);
            
            // form안 모든 element에 대해서 label과 같은 형식으로 표시한다.
            form.handle.closeAll("SM7004_02_form_01");
        },
        insertRow : function(i) {
            var dg_1 = grid.getObject("SM7004_02_grid_0"+i);
            var rowCnt = grid.handle.getRowCount(dg_1);
            var data = SM7004_02.util.getInitRowData(rowCnt+1);
            
            grid.handle.appendRow(dg_1, data);
        },
        deleteRow : function(i) {
            var dg_1 = grid.getObject("SM7004_02_grid_0"+i);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            
            grid.handle.removeRow(dg_1, idx);
        },
        moveRow : function(i, step) {
            var dg_1 = grid.getObject("SM7004_02_grid_0"+i);
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
    file : {
        
    },
    // 엑셀다운로드 구현
    excel : {
        
    },
    config : {
        applyFtaNation : function() {
            form.handle.setValue("SM7004_02_form_01", "SYSTEM_ID", TOP_SYS_ID);
            
            if(TOP_SYS_ID == "RS") {
                form.util.setVisible("SM7004_02_btn_05", true);
                form.util.setVisible("SM7004_02_btn_06", true);
                form.util.setVisible("SM7004_02_btn_07", true);
                form.util.setVisible("SM7004_02_btn_08", true);
            } else {
                form.util.setVisible("SM7004_02_btn_03", true);
                form.util.setVisible("SM7004_02_btn_04", true);
            }
        }
    },
    util : {
        getInitRowData : function(idx) {
            var hcell = new Object();
            
            hcell.SCHEDULE_SEQ = idx; 
            hcell.IF_CD = null;
            hcell.IF_NAME = null;
            hcell.IF_PARENT_CD = null;
            hcell.IF_METHOD = "R"; // 중계서버로 고정
            hcell.REQUIRED_YN = null;
            hcell.VALID_CHECK_YN_NAME  = null;
            
            return hcell;
        }
    }
    
};
