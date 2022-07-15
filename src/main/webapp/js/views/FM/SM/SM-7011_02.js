/******************************************************************************************
 * 작성자 : sbj1000
 * Program Id : SM-7011_02
 * 작업일자 : 2016.05.11
 *
 ******************************************************************************************/

function onLoadPage() {
    SM7011_02.init.initComponent();
}

var SM7011_02 = {

    // 초기값 설정
    init : {
        initComponent : function() {
        	SM7011_02.calendar.initCalendar_1();
            SM7011_02.calendar.initCalendar_2();
            SM7011_02.combobox.initCombo_1();
            SM7011_02.control.selectNoticeInfo();
            
            SM7011_02.ui.viewChangeUI();
        }
    },
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
        	var flag = form.handle.getValue("SM7011_02_form_01", "flag");
            var cal_1 = calendar.getObject("SM7011_02_form_01", "START_DATE");
            var vDate01 = form.handle.getValue("SM7011_02_form_01", "START_DATE");
            
            if(flag == "insert") {
            	var vDate = calendar.util.toDate2String("-");
                calendar.init.setInitDate(vDate);
            } else {
            	var vDate = calendar.util.getDate2String(vDate01, "-");
                calendar.init.setInitDate(vDate);
            }
            calendar.init.setRequired(true);
            
            calendar.create(cal_1);
        },
        initCalendar_2 : function() {
        	var flag = form.handle.getValue("SM7011_02_form_01", "flag");
            var cal_1 = calendar.getObject("SM7011_02_form_01", "END_DATE");
            var vDate02 = form.handle.getValue("SM7011_02_form_01", "END_DATE");
            
            if(flag == "insert") {
            	var vDate = calendar.util.getDate2String("29991231", "-");
                calendar.init.setInitDate(vDate);
            } else {
            	var vDate = calendar.util.getDate2String(vDate02, "-");
                calendar.init.setInitDate(vDate);
            }
            calendar.init.setRequired(true);
            
            calendar.create(cal_1);
        }
    },
    // 콤보박스 그리드 생성
    combobox : {
        initCombo_1 : function() {
        	var obj = combo.getObject("SM7011_02_form_01", "BBS_TYPE");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"BBS_TYPE", MESSAGE_CODE:"SELET"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj);
        }
    },
    // 데이터 그리드 생성
    datagrid : { },
    // 이벤트 처리
    event : {
        selectAfterUI : function(data) {
            form.handle.loadData("SM7011_02_form_01", data);
            
            var fileName = form.getObject("SM7011_02_form_01", "SM7011_02_span_01");
            var vHtml = form.handle.getValue("SM7011_02_form_01","NOTICE_FILE_NAME");
            
            if(vHtml != ''){
            	form.util.setVisible("img_btn", true);
            }
            
            fileName.html(vHtml);
        },
        updateAfterUI : function(data) {
            if (form.handle.getValue("SM7011_02_form_01", "flag") == "insert") {
                form.handle.setValue("SM7011_02_form_01", "flag", "update");
                form.handle.setValue("SM7011_02_form_01", "NOTICE_NO", data.NOTICE_NO);
            }

            SM7011_01.control.selectNoticeList();
        },
        deleteAfterUI : function(data) {
            SM7011_01.control.selectNoticeList();
            
            var dl_1 = dialog.getObject("SM7011_02_dailog_01");
            dialog.handle.close(dl_1);
        }
    },
    control : {// 업무구현
        selectNoticeInfo : function() {
            var obj = form.getObject("SM7011_02_form_01");

            form.init.setURL("/fm/sm/sm7011_02/selectNoticeInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);

            form.handle.setValue("SM7011_02_form_01", "COMPANY_CD", SESSION.COMPANY_CD);
            form.handle.setValue("SM7011_02_form_01", "VENDOR_CD", SESSION.VENDOR_CD);

            form.submit(obj);
        },
        updateNoticeInfo : function() {
            if (!form.handle.isValidate("SM7011_02_form_01")) return;
            
            var fileUrl = form.handle.getValue("SM7011_02_form_01","NOTICE_FILE");
            var fileName = fileUrl.substring(fileUrl.lastIndexOf("\\")+1, fileUrl.length);
            
            form.handle.setValue("SM7011_02_form_01", "NOTICE_FILE_NAME", fileName);

            var confMsg = "";
            if(form.handle.getValue("SM7011_02_form_01", "flag") == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }

            $.messager.confirm(resource.getMessage("CNFIR"), confMsg, function(flag) {
                if(flag) {
                    var obj = form.getObject("SM7011_02_form_01");

                    form.handle.setValue("SM7011_02_form_01", "COMPANY_CD", SESSION.COMPANY_CD);

                    if(form.handle.getValue("SM7011_02_form_01", "flag") == "insert") {
                        form.init.setURL("/fm/sm/sm7011_02/insertNoticeInfo");
                    }
                    if(form.handle.getValue("SM7011_02_form_01", "flag") == "update") {
                        form.init.setURL("/fm/sm/sm7011_02/updateNoticeInfo");
                    }
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    form.submit(obj);
                }
            });
        },
        deleteNoticeInfo : function() {
            $.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7011_02_form_01");
                    
                    form.init.setURL("/fm/sm/sm7011_02/deleteNoticeInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
            
        }
    },
    dialog : { },
    // 화면 UI 변경
    ui : {
    	viewChangeUI : function() {
            var flag = form.handle.getValue("SM7011_02_form_01", "flag");
            
            if(flag == "view") {
            	form.handle.closeAll("SM7011_02_form_01");
            	
            	form.util.setVisible("SM7011_02_btn_01", false);
            	form.util.setVisible("SM7011_02_btn_03", true);
            	form.util.setVisible("SM7011_02_tr_02", false);
            	form.util.setVisible("SM7011_02_tr_03", true);
                form.util.setVisible("SM7011_02_tr_01", false);
            } else if(flag == "update") {
            	form.util.setVisible("SM7011_02_btn_02", true);
            	form.util.setVisible("SM7011_02_tr_03", true);
            }
        }
    },
    // 파일 input 엘리먼트 구현
    file : { },
    // 엑셀다운로드 구현
    excel : { }
};