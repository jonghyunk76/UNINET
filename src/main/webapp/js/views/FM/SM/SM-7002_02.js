/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7002_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7002_02.config.applyFtaNation();
    SM7002_02.init.initComponent();
}

var SM7002_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {            
            var flag = form.handle.getValue("SM7002_02_form_01", "flag");

            SM7002_02.combobox.initCombo_1();
            SM7002_02.combobox.initCombo_2();
            SM7002_02.combobox.initCombo_3();
            SM7002_02.combobox.initCombo_4();
            
            if (flag == "view") {
                SM7002_02.ui.viewChangeUI();
            } else if (flag == "update") {
                SM7002_02.ui.updateChangeUI();
            } else {
                SM7002_02.ui.insertChangeUI();
            }
            
            if (flag != "insert") {
                SM7002_02.control.selectDivisionInfo();
            }
        }
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {
        initCombo_1 : function() {
            var obj_01 = combo.getObject("SM7002_02_form_01", "CO_CERTIFIED_EXPORTER_YN");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN_TYPE", MESSAGE_CODE:"NOSEL"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_01);
        },
        initCombo_2 : function() {
            var obj_02 = combo.getObject("SM7002_02_form_01", "DIVISION_TYPE");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"DIVISION_TYPE", MESSAGE_CODE:"NOSEL"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_02);
        },
        initCombo_3 : function() {
            var obj_03 = combo.getObject("SM7002_02_form_01", "STATUS");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"ENABLED_FLAG"});
            combo.init.setRequired(true);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj_03);
        },
        initCombo_4 : function() {
            var obj_03 = combo.getObject("SM7002_02_form_01", "CUSTOMS_CD");
            
            combo.init.setURL("/mm/cbox/selectKcsStandardCode");
            combo.init.setQueryParams({CATEGORY_CD:"CT", NATION_CD:'KR', MESSAGE_CODE:"NOSEL"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj_03);
        }
    },
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {
        selectAfterUI : function(data) {
            form.handle.loadData("SM7002_02_form_01", data);
        },
        updateAfterUI : function(data) {
            if (form.handle.getValue("SM7002_02_form_01", "flag") == "insert") {
                form.handle.setValue("SM7002_02_form_01", "flag", "update");
            }
            
            SM7002_02.ui.updateChangeUI();
            SM7002_01.control.selectDivisionList();
        },
        deleteAfterUI : function(data) {
            SM7002_01.control.selectDivisionList();
            
            var dl_1 = dialog.getObject("SM7002_02_dailog_01");
            dialog.handle.close(dl_1);
        }
    },
    control : {
        selectDivisionInfo : function() {
            var obj = form.getObject("SM7002_02_form_01");
            
            form.init.setURL("/fm/sm/sm7002_02/selectDivisionInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        updateDivisionInfo : function() {
            if (!form.handle.isValidate("SM7002_02_form_01")) return;
            
            var vFlag = form.handle.getValue("SM7002_02_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            var confMsg = "";
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                
                if (flag) {
                    var obj = form.getObject("SM7002_02_form_01");
                    
                    if (form.handle.getValue("SM7002_02_form_01", "flag") == "insert") {
                        form.init.setURL("/fm/sm/sm7002_02/insertDivisionInfo");
                    }
                    if (form.handle.getValue("SM7002_02_form_01", "flag") == "update") {
                        form.init.setURL("/fm/sm/sm7002_02/updateDivisionInfo");
                    }
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        deleteDivisionInfo : function() {
            var vFlag = form.handle.getValue("SM7002_02_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7002_02_form_01");
                    
                    form.init.setURL("/fm/sm/sm7002_02/deleteDivisionInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        validate : function(pType) {
            var vCompanyCd = form.handle.getValue("SM7002_02_form_01", "COMPANY_CD");
            var vDivisionCd = form.handle.getValue("SM7002_02_form_01", "DIVISION_CD");
            
            if (oUtil.isNull(vCompanyCd)) {
                alert(resource.getMessage("MSG_NOT_FOUND_COMPANY_CD"));
                return false;
            }
            
            if (oUtil.isNull(vDivisionCd)) {
                alert(resource.getMessage("DIVIS,CODE, MSG_NOT_FOUND_01"));
                return false;
            }
            
            return true;
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
        insertChangeUI : function() {
            var vDefaultLanguage = form.handle.getValue("SM7002_02_form_01", "DEFAULT_LANGUAGE");
            
            form.util.setVisible("SM7002_02_btn_02", false);
        },
        updateChangeUI : function() {
            var vDefaultLanguage = form.handle.getValue("SM7002_02_form_01", "DEFAULT_LANGUAGE");
            
            form.handle.readonly("SM7002_02_form_01", "DIVISION_CD", true);
            form.handle.addClass("SM7002_02_form_01", "DIVISION_CD", "input_readOnly");
            
            form.util.setVisible("SM7002_02_btn_02", true);
        },
        viewChangeUI : function() {
            var vDefaultLanguage = form.handle.getValue("SM7002_02_form_01", "DEFAULT_LANGUAGE");
            
            dialog.handle.resize("SM7002_02_dailog_01", 1024, 253);
            
            form.util.setVisible("SM7002_02_div_01", false);
            form.handle.closeAll("SM7002_02_form_01");
        }
    },
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현
    excel : {},
    config : {
    	applyFtaNation : function() {
    		var orgYn = form.handle.getValue("SM7002_02_form_01", "ORGAN_ISSUE_YN");
    		
			if(orgYn == "Y") {
				form.util.setVisible("SM7002_02_div_02", true);
			}
		}
    }
};
