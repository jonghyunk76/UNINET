/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A004_03
 * 작업일자 : 2016.09.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA004_03.config.applyFtaNation();
    MMA004_03.init.initComponent();
}

var MMA004_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	var flag = form.handle.getValue("MMA004_03_form_01", "flag");
        	
        	if(flag == "view") {
        		MMA004_03.ui.viewChangeUI();
        		MMA004_03.control.selectIssueReasonInfo();
        	}
        	
        	if(form.handle.getValue("MMA004_03_form_01", "CO_SHARE_YN") == "Y") {
    			form.handle.setChecked("MMA004_03_form_01", "CO_SHARE_YN", true);
    		} else {
    			form.handle.setChecked("MMA004_03_form_01", "CO_SHARE_YN", false);
    		}
        	
        	MMA004_03.calendar.initCalendar_1();
        }
    }, 
    // 달력 그리드 생성
    calendar : {
    	initCalendar_1 : function() {
    		var flag = form.handle.getValue("MMA004_03_form_01", "flag");
    		var objDate = calendar.getObject("MMA004_03_form_01", "REQUEST_DATE");
        	
    		if(flag == "update") {
	    		val_01 = calendar.util.getDate2String(form.handle.getValue("MMA004_03_form_01", "REQUEST_DATE"), "-");
	            calendar.init.setInitDate(val_01);
    		}
    		calendar.init.setRequired(true);
        	
            calendar.create(objDate);
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {},
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {
    	btnSaveClick : function() {
    		if(!form.handle.isValidate("MMA004_03_form_01")) return;
    		
    		var rowData = form.handle.getElementsParam("MMA004_03_form_01");
 			var pid = form.handle.getValue("MMA004_03_form_01", "TARGET_PID");
 			
             if (!oUtil.isNull(pid)) {
             	var pro_id = eval("window." + pid + ".dialog");
             	
                 if (typeof(pro_id["callByMMA004_03"]) == "function") {
                     pro_id["callByMMA004_03"](rowData);
                 }
             }
 		},
 		fncLoadData : function(datas) {
 			form.handle.loadData("MMA004_03_form_01", datas);
 		}
    },
    // 업무구현
    control : {
    	selectIssueReasonInfo : function() {
    		var obj = form.getObject("MMA004_03_form_01");
    		
    		form.init.setURL("/mm/pop/mmA004_01/selectIssueReasonInfo");
    		form.init.setProgressFlag(false);
            form.init.setCallBackFunction("fncLoadData");
            
            form.submit(obj);
        }
    },
    // 다이얼로그 구현
    dialog : {
    	closeDialog : function() {
    		var dl_1 = dialog.getObject("MMA004_03_dailog_01");
        	dialog.handle.close(dl_1);
    	}
    },
    // 화면 UI 변경
    ui : {
    	viewChangeUI : function() {
    		form.handle.disabled("MMA004_03_form_01", "CO_SHARE_YN", true);
    		form.util.setVisible("MMA004_03_span_01", false);
    		form.handle.closeAll("MMA004_03_form_01");
    	},
    	changeCoShareCheck : function() {
    		var pid = form.handle.getValue("MMA004_03_form_01", "TARGET_PID");
        	
    		if(form.handle.isChecked("MMA004_03_form_01", "CO_SHARE_YN")) {
    			form.handle.setValue("MMA004_03_form_01", "CO_SHARE_YN", "Y");
    			
    			if(pid == "MMA203_01") {
    				form.handle.setValue("MMA203_01_form_01", "CO_SHARE_YN", "Y");
    				form.handle.setChecked("MMA203_01_form_01", "CO_SHARE_YN", true);
            	}
    		} else {
    			form.handle.setValue("MMA004_03_form_01", "CO_SHARE_YN", "N");
    			
    			if(pid == "MMA203_01") {
    				form.handle.setValue("MMA203_01_form_01", "CO_SHARE_YN", "N");
    				form.handle.setChecked("MMA203_01_form_01", "CO_SHARE_YN", false);
            	}
    		}
    	}
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    config : {
    	applyFtaNation : function() {
    		var pid = form.handle.getValue("MMA004_03_form_01", "TARGET_PID");
    		var flag = form.handle.getValue("MMA004_03_form_01", "flag");
    		
    		if(flag == "insert") {
				if(SESSION.APPLICATION_SERVICE_TYPE == "CL" && pid == "MMA203_01") {
	                form.util.setVisible("MMA004_03_tr_01", true);
	            }
    		}
		}
    }
};
