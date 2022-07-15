/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM1009_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM1009_02.init.initComponent();
}

var SM1009_02 = {
	    
		// 초기값 설정
	    init : {
	    	initComponent : function() {
	    		var flag = form.handle.getValue("SM1009_02_form_01", "flag");
	    		
	    		SM1009_02.calendar.initCalendar_1();
	    		SM1009_02.calendar.initCalendar_2();
	    		SM1009_02.combobox.initCombo_1();
	    		SM1009_02.combobox.initCombo_2();
	    		
	    		// 초기화면에 보여줄 데이터 조회
	    		if(flag != "insert") {
	    			SM1009_02.control.selectMainDetail();
	    		}
	    		
	    		// ui변경
	    		if(flag == "view") {
	    			SM1009_02.ui.viewChangeUI();
	    		} else if(flag == "update") {
	    			SM1009_02.ui.updateChangeUI();
	    		} else {
	    			SM1009_02.ui.insertChangeUI();
	    		}
	    	}
	    },
	    // 달력 그리드 생성
	    calendar : {
	    	initCalendar_1 : function() {
	    		var flag = form.handle.getValue("SM1009_02_form_01", "flag");
				var obj_01 = calendar.getObject("SM1009_02_form_01", "APPLY_START_DATE");
				
				if(flag == "insert") {
					calendar.init.setInitDate(calendar.util.getFirstDay2String("201601", "-"));
				}
				
				calendar.create(obj_01);
			},
			initCalendar_2 : function() {
				var vDate = calendar.util.getDate2String("29991231", "-");
				var obj_01 = calendar.getObject("SM1009_02_form_01", "APPLY_END_DATE");
				
                calendar.init.setInitDate(vDate);
                
				calendar.create(obj_01);
			}
	    }, 
	    // 콤보박스 그리드 생성
	    combobox : {
	    	data_1 : [{CODE:"Y", NAME:resource.getMessage("USE")}, {CODE:"N", NAME:resource.getMessage("UNUSE")}],
	    	initCombo_1 : function() {
	    		var obj = combo.getObject("SM1009_02_form_01", "USE_YN");
				
				combo.init.setURL("/mm/cbox/selectStandardCode");
				combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
				combo.init.setValueField("CODE");
				combo.init.setNameField("NAME");
				
				combo.create(obj);
			},
			initCombo_2 : function() {
				var obj = combo.getObject("SM1009_02_form_01", "NATION_CODE");
				
				combo.init.setURL("/mm/cbox/selectStandardCode");
				combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LENG_CD"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
				combo.init.setValueField("CODE");
				combo.init.setNameField("NAME");
				
				combo.create(obj);
			}
	    },
	    // 데이터 그리드 생성
	    datagrid : { },
	    // 이벤트 처리
	    event : {
	    	// 상세 조회가 성공적으로 완료될 경우 호출되는 함수
	    	selectAfterUI : function(data) {
	    		// selectMainDetail으로 부터 조회된 데이터를 form에 자동 입력함(input id와 name비교)
	    		form.handle.loadData("SM1009_02_form_01", data);
	    	},
	    	// 성공적으로 요청처리가 완료될 경우 호출되는 함수
	    	updateAfterUI : function(data) {
	    		SM1009_01.control.selectMainList();
    			// update 상태로 변경한다.
    			form.handle.setValue("SM1009_02_form_01", "flag", "update");
    			// 업데이트 상태로 UI를 변경한다.
    			SM1009_02.ui.updateChangeUI();
	    	},
	    	deleteAfterUI : function(data) {
	    		SM1009_01.control.selectMainList();
	    		
	    		var dl_1 = dialog.getObject("SM1009_02_dailog_01");
	    		dialog.handle.close(dl_1);
	    	}
	    },
	    control : {// 업무구현
	    	selectMainDetail : function() {
	    		var obj = form.getObject("SM1009_02_form_01");
	    		
	    		form.init.setURL("/mm/smp/smp1009/selectMessgeDetail");
	    		form.init.setCallBackFunction("selectAfterUI");
	    		form.init.setProgressFlag(false);
	    		form.init.setValidationFlag(false);
	    		
	    		form.submit(obj);
	    	},
	    	// insert/update 수행
	    	updateMainInfo : function() {
	    		if(!form.handle.isValidate("SM1009_02_form_01")) return;
	    		
	    		var cudFlag = form.handle.getValue("SM1009_02_form_01", "flag");
	    		var confMsg = "";
	    		if(cudFlag == "insert") {
	    			confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
	    		} else {
	    			confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
	    		}
	    		
	    		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage(confMsg), function(flag) {
                    if(flag) {
                    	var obj = form.getObject("SM1009_02_form_01");
        	    		
        	    		if(cudFlag == "insert") {
        	    			form.init.setURL("/mm/smp/smp1009/insertMessageInfo");
        	    		}
        	    		if(cudFlag == "update") {
        	    			form.init.setURL("/mm/smp/smp1009/updateMessageInfo");
        	    		}
        	    		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
        	    		form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
        	    		form.init.setCallBackFunction("updateAfterUI");
        	    		
        	    		form.submit(obj);
                    }
                });
	    	},
	    	// delete 수행
	    	deleteMainInfo : function() {
	    		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                    if(flag) {
                    	var obj = form.getObject("SM1009_02_form_01");
        	    		
        	    		form.init.setURL("/mm/smp/smp1009/deleteMessgeDetail");
        	    		form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
        	    		form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
        	    		form.init.setCallBackFunction("deleteAfterUI");
        	    		
        	    		form.submit(obj);
                    }
                });
	    	}
	    },
	    dialog : {
	    	openDialog_1 : function() {
	    		var dl_1 = dialog.getObject("MMA008_01_dailog_01");
	    		
	    		dialog.init.setWidth(600);
	    		dialog.init.setHeight(400);
	    		dialog.init.setURL("/mm/pop/mmA008_01");
	    		dialog.init.setQueryParams({TARGET_PID:"SM1009_02", COMPANY_CD:SESSION.COMPANY_CD, CODE:form.handle.getValue("SM1009_02_form_01", "NATION_CODE")});
	    		dialog.init.setTitle(resource.getMessage("MMA008_01"));
	    		dialog.open(dl_1);
	    	},
	    	callByMMA008_01 : function(datas) {
	    		var dl_1 = dialog.getObject("MMA008_01_dailog_01");
	    		
	    		form.handle.setValue("SM1009_02_form_01", "NATION_CODE", datas.CODE);
	    		form.handle.setValue("SM1009_02_form_01", "NATION_NAME", datas.CODE_NAME);
	    		
	    		// 창을 닫는다.
	    		dialog.handle.close(dl_1);
	    	}
	    },
	    // 화면 UI 변경
	    ui : {
	    	insertChangeUI : function() {
	    		form.util.setVisible("SM1009_02_btn_02", false);
	    	},
	    	updateChangeUI : function() {
	    		form.util.setVisible("SM1009_02_btn_03", false);
    			// key가 되는 input은 모두 막는다.
    			combo.handle.readonly("SM1009_02_form_01", "NATION_CODE", true);
    			form.handle.readonly("SM1009_02_form_01", "MESSAGE_CODE", true);
    			form.handle.addClass("SM1009_02_form_01", "MESSAGE_CODE", "input_readOnly");
	    	},
	    	viewChangeUI : function() {
	    		dialog.handle.resize("SM1009_02_dailog_01", 600, 182);
    			
	    		form.util.setVisible("SM1009_02_div_01", false);
    			form.util.setVisible("SM1009_02_btn_03", false);
    			// form안 모든 element에 대해서 label과 같은 형식으로 표시한다.
    			form.handle.closeAll("SM1009_02_form_01");
	    	}
	    },
	    // 파일 input 엘리먼트 구현
	    file : { },
	    // 엑셀다운로드 구현
	    excel : { }
	    
	};