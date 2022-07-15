/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM1002_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM1002_02.init.initComponent();
}

var SM1002_02 = {
	    
		// 초기값 설정
	    init : {
	    	initComponent : function() {
	    		var flag = form.handle.getValue("SM1002_02_form_01", "flag");
	    		
	    		if(flag == "insert") {
	    			form.util.setVisible("SM1002_02_btn_02", false);
	    		}
	    		
	    		// 초기화면에 보여줄 데이터 조회
	    		if(flag != "insert") {
	    			SM1002_02.control.selectMainDetail();
	    		}
	    		
	    		if(flag == "update") {
	    			SM1002_02.ui.updateChangeUI();
	    		}
	    	}
	    },
	    // 달력 그리드 생성
	    calendar : {}, 
	    // 콤보박스 그리드 생성
	    combobox : {},
	    // 데이터 그리드 생성
	    datagrid : { },
	    // 이벤트 처리
	    event : {
	    	// 상세 조회가 성공적으로 완료될 경우 호출되는 함수
	    	selectAfterUI : function(data) {
	    		// selectMessgeDetail으로 부터 조회된 데이터를 form에 자동 입력함(input id와 name비교)
	    		form.handle.loadData("SM1002_02_form_01", data);
	    	},
	    	// 성공적으로 요청처리가 완료될 경우 호출되는 함수
	    	updateAfterUI : function(data) {
	    		SM1002_01.control.selectDetailList();
    			// update 상태로 변경한다.
    			form.handle.setValue("SM1002_02_form_01", "flag", "update");
    			// 업데이트 상태로 UI를 변경한다.
    			SM1002_02.ui.updateChangeUI();
	    	},
	    	deleteAfterUI : function(data) {
	    		SM1002_01.control.selectDetailList();
	    		
	    		var dl_1 = dialog.getObject("SM1002_02_dailog_01");
	    		dialog.handle.close(dl_1);
	    	}
	    },
	    control : {// 업무구현
	    	selectMainDetail : function() {
	    		var obj = form.getObject("SM1002_02_form_01");
	    		
	    		form.init.setURL("/mm/smp/smp1002/selectSysCodeDetail");
	    		form.init.setCallBackFunction("selectAfterUI");
	    		form.init.setProgressFlag(false);
	    		form.init.setValidationFlag(false);
	    		
	    		form.submit(obj);
	    	},
	    	// insert/update 수행
	    	updateMainInfo : function() {
	    		if(!form.handle.isValidate("SM1002_02_form_01")) return;
	    		
	    		var cudFlag = form.handle.getValue("SM1002_02_form_01", "flag");
	    		var confMsg = "";
	    		if(cudFlag == "insert") {
	    			confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
	    		} else {
	    			confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
	    		}
	    		
	    		$.messager.confirm(CNFIR, confMsg, function(flag) {
                    if(flag) {
                    	var obj = form.getObject("SM1002_02_form_01");
        	    		
        	    		if(cudFlag == "insert") {
        	    			form.init.setURL("/mm/smp/smp1002/insertSysCodeInfo");
        	    		}
        	    		if(cudFlag == "update") {
        	    			form.init.setURL("/mm/smp/smp1002/updateSysCodeInfo");
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
	    		$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                    if(flag) {
                    	var obj = form.getObject("SM1002_02_form_01");
        	    		
        	    		form.init.setURL("/mm/smp/smp1002/deleteSysCodeInfo");
        	    		form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
        	    		form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
        	    		form.init.setCallBackFunction("deleteAfterUI");
        	    		
        	    		form.submit(obj);
                    }
                });
	    	}
	    },
	    dialog : {
	    	
	    },
	    // 화면 UI 변경
	    ui : {
	    	updateChangeUI : function() {
    			// key가 되는 input은 모두 막는다.
    			form.handle.readonly("SM1002_02_form_01", "CODE", true);
    			form.handle.addClass("SM1002_02_form_01", "CODE", "input_readOnly");
	    	}
	    },
	    // 파일 input 엘리먼트 구현
	    file : { },
	    // 엑셀다운로드 구현
	    excel : { }
	    
	};