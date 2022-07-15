/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM-7001_03
 * 작업일자 : 2017.02.03
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7001_03.config.applyFtaNation();
	SM7001_03.init.initComponent();
}

var SM7001_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	var flag = form.handle.getValue("SM7001_03_form_02", "flag");
        	
        	SM7001_03.combobox.initCombo_1();
        	SM7001_03.combobox.initCombo_2();
        	
        	if(flag == "update"){
        		SM7001_03.ui.updateChangeUI();
        	}
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	initCombo_1 : function() {
            var obj_01 = combo.getObject("SM7001_03_form_01", "CUSTOMER_CD");
            
            combo.init.setURL("/mm/cbox/selectHubCertCustomer");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            combo.init.setCallFunction("changeHubCertCustomer");
            
            combo.create(obj_01);
        },
        initCombo_2 : function() {
            var obj_02 = combo.getObject("SM7001_03_form_02", "DIVISION_CD");
            
            combo.init.setURL("/mm/cbox/selectDivision");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, DIVISION_TYPE:"S"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj_02);
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {
    	updateAfterUI : function(data) {
            if (form.handle.getValue("SM7001_03_form_02", "flag") == "insert") {
                form.handle.setValue("SM7001_03_form_02", "flag", "update");
            }
            
            SM7001_03.ui.updateChangeUI();
            SM7001_01.control.selectHubSalesCustomerList();
        },
        changeHubCertCustomer : function(data) {
        	var flag = form.handle.getValue("SM7001_03_form_02", "flag");
        	
        	if(oUtil.isNull(data) || flag == "update") return;
        	
        	SM7001_03.control.selectHubCertCustomerDetail();
        },
        selectCertCustomerAfterUI : function(rowData) {
        	form.handle.loadData("SM7001_03_form_01", rowData);
        },
        deleteAfterUI : function(data) {
        	SM7001_01.control.selectHubSalesCustomerList();
        	
        	var dl_1 = dialog.getObject("SM7001_03_dailog_01");
        	
        	dialog.handle.close(dl_1);
        }
    },
    // 업무구현
    control : {
    	selectHubCertCustomerDetail : function() {
            var obj = form.getObject("SM7001_03_form_01");
            
            form.init.setURL("/fm/sm/SM7001_01/selectHubCertCustomerDtail");
            form.init.setCallBackFunction("selectCertCustomerAfterUI");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
    	updateHubSalesCustomerInfo : function() {
    		if (!form.handle.isValidate("SM7001_03_form_01") && !form.handle.isValidate("SM7001_03_form_02")) return;
            
            var vFlag = form.handle.getValue("SM7001_03_form_02", "flag");
            var confMsg = "";
            
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7001_03_form_02");
                    
                    form.handle.setValue("SM7001_03_form_02", "CUSTOMER_CD", combo.handle.getValue("SM7001_03_form_01", "CUSTOMER_CD"));
                    
                    form.init.setURL("/fm/sm/sm7001_03/updateHubSalesCustomerInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        deleteHubSalesCustomerInfo : function() {
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7001_03_form_02");
                    
                    form.handle.setValue("SM7001_03_form_02", "CUSTOMER_CD", combo.handle.getValue("SM7001_03_form_01", "CUSTOMER_CD"));
                    
                    form.init.setURL("/fm/sm/sm7001_03/deleteHubSalesCustomerInfo");
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
    		var params = form.handle.getElementsParam("SM7001_03_form_02");
    		var dl_1 = dialog.getObject("MMA007_01_dailog_01");
    		
    		dialog.init.setTitle(resource.getMessage("MMA007_01"));
    		dialog.init.setURL("/mm/pop/mmA007_01");
    		dialog.init.setResizable(false);
    		dialog.init.setQueryParams(params);
    		
    		dialog.open(dl_1);
    	},
    	callByMMA007_01 : function(rowData){
    		form.handle.setValue("SM7001_03_form_02", "SALES_CUSTOMER_CD", rowData.CUSTOMER_CD);
    		form.handle.setValue("SM7001_03_form_02", "SALES_CUSTOMER_NAME", rowData.CUSTOMER_NAME);
    		
    		dialog.handle.close(dialog.getObject("MMA007_01_dailog_01"));
    	}
    },
    // 화면 UI 변경
    ui : {
    	updateChangeUI : function() {
        	form.util.setVisible("SM7001_03_span_01", false);
        	
        	form.handle.removeClass("SM7001_03_form_02", "SALES_CUSTOMER_CD", "search_readOnly");
    		form.handle.addClass("SM7001_03_form_02", "SALES_CUSTOMER_CD", "input_readOnly");
    		
    		combo.handle.readonly("SM7001_03_form_01", "CUSTOMER_CD", true);
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
