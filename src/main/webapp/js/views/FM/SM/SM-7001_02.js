/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM-7001_02
 * 작업일자 : 2017.02.03
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7001_02.config.applyFtaNation();
    SM7001_02.init.initComponent();
}

var SM7001_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	var flag = form.handle.getValue("SM7001_02_form_01", "flag");
        	
        	if(flag == "update") {
        		SM7001_02.ui.updateChangeUI();
        	}
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
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {
    	updateAfterUI : function(data) {
            if (form.handle.getValue("SM7001_02_form_01", "flag") == "insert") {
                form.handle.setValue("SM7001_02_form_01", "flag", "update");
            }
            
            SM7001_02.ui.updateChangeUI();
            SM7001_01.control.selectHubCertCustomerList();
        },
        deleteAfterUI : function(data) {
        	SM7001_01.control.selectHubCertCustomerList();
        	
            var dl_1 = dialog.getObject("SM7001_02_dailog_01");
            
            dialog.handle.close(dl_1);
        },
        selectCertIDAfterUI : function(data) {
        	if(data.DUPLICATE_CNT > 0) {
        		alert(resource.getMessage("MSG_EXISTS_CERT_ID"));
        	} else {
        		SM7001_02.control.updateHubCertCustomerInfo();
        	}
        }
    },
    // 업무구현
    control : {
    	updateHubCertCustomerInfo : function() {
            if (!form.handle.isValidate("SM7001_02_form_01")) return;
            
            var vFlag = form.handle.getValue("SM7001_02_form_01", "flag");
            var confMsg = "";
            
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7001_02_form_01");
                    
                    form.init.setURL("/fm/sm/sm7001_02/updateHubCertCustomerInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        selectHubCertID : function() {
        	var vFlag = form.handle.getValue("SM7001_02_form_01", "flag");
        	
        	if (vFlag == "insert") {
	        	var obj = form.getObject("SM7001_02_form_01");
	            
	            form.init.setURL("/fm/sm/SM7001_01/selectHubCertID");
	            form.init.setCallBackFunction("selectCertIDAfterUI");
	            
	            form.submit(obj);
        	} else {
        		SM7001_02.control.updateHubCertCustomerInfo();
        	}
        },
        deleteHubCertCustomerInfo : function() {
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7001_02_form_01");
                    
                    form.init.setURL("/fm/sm/sm7001_02/deleteHubCertCustomerInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setValidationFlag(false);
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        }
        
        
    },
    // 다이얼로그 구현
    dialog : {
		openDialog_1 : function() {
			var params = form.handle.getElementsParam("SM7001_02_form_01");
			var dl_1 = dialog.getObject("MMA007_01_dailog_01");
			
			dialog.init.setTitle(resource.getMessage("MMA007_01"));
	        dialog.init.setURL("/mm/pop/mmA007_01");
	        dialog.init.setResizable(false);
	        dialog.init.setQueryParams(params);
	        
	        dialog.open(dl_1);
	    },
	    callByMMA007_01 : function(rowData) {
	    	if(!oUtil.isNull(rowData.HUB_CERT_ID)) {
	    		rowData.flag = "update";
	    	} else {
	    		rowData.flag = "insert";
	    	}
	    	
	    	form.handle.loadData("SM7001_02_form_01", rowData);
	    	
	    	dialog.handle.close(dialog.getObject("MMA007_01_dailog_01"));
	    }
    },
    // 화면 UI 변경
    ui : {
    	updateChangeUI : function() {
    		form.util.setVisible("SM7001_02_span_01", false);
    		form.handle.removeClass("SM7001_02_form_01", "CUSTOMER_CD", "search_readOnly");
    		form.handle.addClass("SM7001_02_form_01", "CUSTOMER_CD", "input_readOnly");
    	}
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
};
