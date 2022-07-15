/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7004_03
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7004_03.init.initComponent();
}

var SM7004_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            var flag = form.handle.getValue("SM7004_03_form_01", "flag");

            // 초기화면에 보여줄 데이터 조회
            if (flag != "insert") {
                SM7004_03.control.selectScheduleMappingInfo();
            }
            
            SM7004_03.calendar.initCalendar_1();
            SM7004_03.combobox.initCombo_1();
            SM7004_03.combobox.initCombo_2();
            SM7004_03.combobox.initCombo_3();
            SM7004_03.combobox.initCombo_4();
            SM7004_03.combobox.initCombo_5();
            
            SM7004_03.datagrid.initGrid_1();
            
            if (flag == "view") {
                SM7004_03.ui.viewChangeUI();
            } else if (flag == "update") {
                SM7004_03.ui.updateChangeUI();
            } else {
                SM7004_03.ui.insertChangeUI();
            }
        }
    }, 
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {
        initCombo_1 : function() {
            var vCompanyCd = form.handle.getValue("SM7004_03_form_01", "COMPANY_CD");
            var objIfCd = combo.getObject("SM7004_03_form_01", "IF_CD");

            combo.init.setURL("/fm/sm/sm7005_01/selectInterfaceItemMstCombo");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setHeight(250);
            combo.init.setCallFunction("onChangeIfCode");
            
            combo.create(objIfCd);
        },
        initCombo_2 : function() {
        	var vCompanyCd = form.handle.getValue("SM7004_03_form_01", "COMPANY_CD");
            var objIfParentCode = combo.getObject("SM7004_03_form_01", "IF_PARENT_CD");
            
            combo.init.setURL("/fm/sm/sm7005_01/selectInterfaceItemMstCombo");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, FIRST_ROW:"UNCHOOSE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setHeight(250);
            combo.init.setCallFunction("onChangeIfParentCode");
            
            combo.create(objIfParentCode);
        },
        initCombo_3 : function() {
        	var vCompanyCd = form.handle.getValue("SM7004_03_form_01", "COMPANY_CD");
            var objIfMethod = combo.getObject("SM7004_03_form_01", "IF_METHOD");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, CATEGORY_CD:"IF_METHOD"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setCallFunction("onChangeIfMethod");
            
            combo.create(objIfMethod);
        },
        initCombo_4 : function() {
        	var vCompanyCd = form.handle.getValue("SM7004_03_form_01", "COMPANY_CD");
            var objRequiredYn = combo.getObject("SM7004_03_form_01", "REQUIRED_YN");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, CATEGORY_CD:"YN_TYPE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objRequiredYn);
        },
        initCombo_5 : function() {
        	var vCompanyCd = form.handle.getValue("SM7004_03_form_01", "COMPANY_CD");
            var objRequiredYn = combo.getObject("SM7004_03_form_01", "VALID_CHECK_YN");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, CATEGORY_CD:"YN"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objRequiredYn);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [] ,
        initGrid_1 : function() {
        	if(SESSION.USER_ID == "fta") console.log("call datagrid.initGrid_1");
        }
    },
    // 이벤트 처리
    event : {
        // 상세 조회가 성공적으로 완료될 경우 호출되는 함수
        selectAfterUI : function(data) {
        	data.SCHEDULE_SEQ_NEW = data.SCHEDULE_SEQ;
            // selectMessgeDetail으로 부터 조회된 데이터를 form에 자동 입력함(input id와 name비교)
            form.handle.loadData("SM7004_03_form_01", data);
            
            if(oUtil.isNull(data.IF_PARENT_CD)) {
            	combo.handle.setValue("SM7004_03_form_01", "IF_PARENT_CD", " ");
            }
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
            if (form.handle.getValue("SM7004_03_form_01", "flag") == "insert") {
                // update 상태로 변경한다.
                form.handle.setValue("SM7004_03_form_01", "flag", "update");
                
                // 등록시 생성된 KEY값 설정
                form.handle.setValue("SM7004_03_form_01", "INTERFACE_SCHEDULE_MAPPING_ID", oMap.INTERFACE_SCHEDULE_MAPPING_ID);
                form.handle.setValue("SM7004_03_form_01", "SCHEDULE_SEQ", oMap.SCHEDULE_SEQ);
            }
            
            // 업데이트 상태로 UI를 변경한다.
            SM7004_03.ui.updateChangeUI();
            
            // 부모조회
            SM7004_02.control.selectScheduleMappingList();
        },
        deleteAfterUI : function(data) {
            SM7004_02.control.selectScheduleMappingList();
            
            var dl_1 = dialog.getObject("SM7004_03_dailog_01");
            dialog.handle.close(dl_1);
        }
    },
    // 업무구현
    control : {
        selectScheduleMappingInfo : function() {
            var obj = form.getObject("SM7004_03_form_01");
            
            form.init.setURL("/fm/sm/sm7004_03/selectScheduleMappingInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        // insert/update 수행
        updateScheduleMappingInfo : function() {
            
            if (!form.handle.isValidate("SM7004_03_form_01")) return;
            
            var vFlag = form.handle.getValue("SM7004_03_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return;
            }
            
            var confMsg = "";
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                
                if (flag) {
                    var obj = form.getObject("SM7004_03_form_01");
                    
                    if (form.handle.getValue("SM7004_03_form_01", "flag") == "insert") {
                        form.init.setURL("/fm/sm/sm7004_03/insertScheduleMappingInfo");
                    }
                    if (form.handle.getValue("SM7004_03_form_01", "flag") == "update") {
                        form.init.setURL("/fm/sm/sm7004_03/updateScheduleMappingInfo");
                    }
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        // delete 수행
        deleteScheduleMappingInfo : function() {
            
            var vFlag = form.handle.getValue("SM7004_03_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7004_03_form_01");
                    
                    form.init.setURL("/fm/sm/sm7004_03/deleteScheduleMappingInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        validate : function(pType) {
            
            var vCompanyCd = form.handle.getValue("SM7004_03_form_01", "COMPANY_CD");
            var vScheduleCode = form.handle.getValue("SM7004_03_form_01", "SCHEDULE_CD");
            var vScheduleSeq = form.handle.getValue("SM7004_03_form_01", "SCHEDULE_SEQ");
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
                if (oUtil.isNull(vScheduleSeq)) {
                    // 스케줄순번을 찾을 수 없습니다.
                    alert(resource.getMessage("MSG_NOT_FOUND_SCHEDULE_SEQ"));
                    return false;
                }
            }
            
            return true;
        }
        
    },
    // 다이얼로그 구현
    dialog : {
        openDialog : function(flag) {
        	if(SESSION.USER_ID == "fta") console.log("call dialog.openDialog");
        }
    },
    // 화면 UI 변경
    ui : {
        insertChangeUI : function() {
            // 삭제버튼감춤
            form.util.setVisible("SM7004_03_btn_02", false);
            
            // key가 되는 input은 모두 막는다.
            form.handle.readonly("SM7004_03_form_01", "SCHEDULE_NAME", true);
            form.handle.addClass("SM7004_03_form_01", "SCHEDULE_NAME", "input_readOnly");
        },
        updateChangeUI : function() {
            // 삭제버튼보임
            form.util.setVisible("SM7004_03_btn_02", true);
            
            // key가 되는 input은 모두 막는다.
            form.handle.readonly("SM7004_03_form_01", "SCHEDULE_NAME", true);
            form.handle.addClass("SM7004_03_form_01", "SCHEDULE_NAME", "input_readOnly");
        },
        viewChangeUI : function() {
            
            //dialog.handle.resize("SM7004_03_dailog_01", 1024, 768);
            
            // 버튼감싸는영역
            form.util.setVisible("SM7004_03_div_01", false);
            
            // form안 모든 element에 대해서 label과 같은 형식으로 표시한다.
            form.handle.closeAll("SM7004_03_form_01");
        }
    },
    // 파일 input 엘리먼트 구현
    file : {
        
    },
    // 엑셀다운로드 구현
    excel : {
        
    }
    
};
