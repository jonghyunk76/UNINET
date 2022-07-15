/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : ST-R001_02
 * 작업일자 : 2022.06.17
 * 
 ******************************************************************************************/

function onLoadPage() {
	STR001_02.config.applyFtaNation();
    STR001_02.init.initComponent();
}

var STR001_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	var vFlag = form.handle.getValue("STR001_02_form_01", "flag");
        	
            STR001_02.combobox.initCombo_1();
            STR001_02.combobox.initCombo_2();
            STR001_02.combobox.initCombo_3();
            
        	if (vFlag == "update") {
            	STR001_02.ui.updateChangeUI();
            } else {
            	STR001_02.ui.insertChangeUI();
            }
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
        initCombo_1 : function() {
            var obj_01 = combo.getObject("STR001_02_form_01", "ATTRIBUTE01");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"RS_SERVER_TYPE", MESSAGE_CODE:"SELET"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_01);
        },
        initCombo_2 : function() {
            var vFlag = form.handle.getValue("STR001_02_form_01", "flag");
            var obj_01 = combo.getObject("STR001_02_form_01", "USE_YN");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN", MESSAGE_CODE:"SELET"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            if(vFlag == "insert") combo.init.setValue("Y");
            else combo.init.setValue("");
            
            combo.create(obj_01);
        },
        initCombo_3 : function() {
            var obj = combo.getObject("STR001_02_form_01", "ATTRIBUTE06"); // 전송구분

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"RS_CNN_PROTOCAL", MESSAGE_CODE:"SELET"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {}
    },
    // 이벤트 처리
    event : {
    	insertServerMst : function(data) {
            form.handle.setValue("STR001_02_form_01","flag", "update");
             
            STR001_02.ui.updateChangeUI();
    		STR001_01.control.selectMainList();
    	},
    	deleteAfterUI : function(data) {
    		STR001_01.control.selectMainList();
    		
    		var dl_1 = dialog.getObject("STR001_02_dailog_01");
            dialog.handle.close(dl_1);
    	}
    },
    // 업무구현
    control : {
    	insertServerListMst : function(flag){
    		var iflag = form.handle.getValue("STR001_02_form_01","flag"); 
    		var msg = "";
            
            if(iflag == "insert") {
                msg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                msg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
			$.messager.confirm(CNFIR, msg, function(flag) {
    			if (flag) {
                    if(form.handle.isChecked("STR001_02_form_01", "SECURE_CONNECT_YN")) {
                        form.handle.setValue("STR001_02_form_01", "ATTRIBUTE02", "Y");
                    } else {
                        form.handle.setValue("STR001_02_form_01", "ATTRIBUTE02", "N");
                    }
                    
    				var obj = form.getObject("STR001_02_form_01");
    				
    	            if(iflag == "insert") form.init.setURL("/rs/st/stR001_02/insertServerMst");
                    else form.init.setURL("/rs/st/stR001_02/updateServerMst");
    	            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
    	            form.init.setCallBackFunction("insertServerMst");
    	            
    	            form.submit(obj);
                }
            });
    	},
    	deleteServerListMst : function(flag){
    		$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if(flag) {
                    var obj = form.getObject("STR001_02_form_01");

                    form.init.setURL("/rs/st/stR001_02/deleteServerMst");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");

                    form.submit(obj);
                }
            });
    	}
    },
    // 다이얼로그 구현
    dialog : {
        
    },
    // 화면 UI 변경
    ui : {
    	insertChangeUI : function() {
    		form.util.setVisible("STR001_02_btn_02", false);
    	},
    	updateChangeUI : function() {
    		form.handle.readonly("STR001_02_form_01", "SERVER_ID", true);
    		form.handle.addClass("STR001_02_form_01", "SERVER_ID", "input_readOnly");
            
    		form.util.setVisible("STR001_02_btn_02", true);
    	}
    }, 
    // 검색박스 구현
    searchbox : {},
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
            if(form.handle.getValue("STR001_02_form_01", "ATTRIBUTE02") == "Y") {
                form.handle.setChecked("STR001_02_form_01", "SECURE_CONNECT_YN", true);
            } else {
                form.handle.setChecked("STR001_02_form_01", "SECURE_CONNECT_YN", false);
            }
        }
    }
    
};
