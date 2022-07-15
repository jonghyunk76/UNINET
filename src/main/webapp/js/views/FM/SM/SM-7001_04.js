/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM-7001_04
 * 작업일자 : 2017.02.03
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7001_04.init.initComponent();
}

var SM7001_04 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            var flag = form.handle.getValue("SM7001_04_form_01", "flag");
        	
            SM7001_04.calendar.initCalendar_1();
            SM7001_04.calendar.initCalendar_2();
            
            SM7001_04.combobox.initCombo_1();
            
        	if(flag == "update") {
        		var signNo = form.handle.getValue("SM7001_04_form_01", "SIGNATURE_SEQ");
        		
        		SM7001_04.ui.updateChangeUI();
        		SM7001_04.control.selectSignatureFile(signNo);
        	}
        }
    }, 
    // 달력 생성
    calendar : {
    	initCalendar_1 : function() {
    		var obj_01 = calendar.getObject("SM7001_04_form_01", "START_DATE");
    		
    		calendar.init.setRequired(true);
    		
    		calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	var date = calendar.util.getDate2String("29991231");
        	var obj_01 = calendar.getObject("SM7001_04_form_01", "END_DATE");
            
            calendar.init.setRequired(true);
            calendar.init.setInitDate(date);
            
            calendar.create(obj_01);
        }
    }, 
    // 콤보박스 생성
    combobox : {
  		initCombo_1 : function() {
  			var obj = combo.getObject("SM7001_04_form_01", "DIVISION_CD");
  			
  			combo.init.setURL("/mm/cbox/selectDivision");
  			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, MESSAGE_CODE:"BLANK"});
  			combo.init.setValueField("CODE");
  			combo.init.setNameField("NAME");
  			combo.init.setRequired(true);
  			combo.init.setReadonly(true);
  			
  			combo.create(obj);
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
    	updateHubSignatureInfoAfterUI : function(data) {
    		SM7001_01.control.selectHubSignatureList();
    		SM7001_04.ui.updateChangeUI();
    	},
    	deleteHubSignatureInfoAfterUI : function(data) {
    		SM7001_01.control.selectHubSignatureList();
    		
            var dl_1 = dialog.getObject("SM7001_04_dailog_01");
            
            dialog.handle.close(dl_1);
        }
    },
    // 업무구현
    control : {
    	selectSignatureFile : function(data) {
    		if(oUtil.isNull(data)) return;
    		
        	var obj = form.getObject("SM7001_04_form_01", "SM7001_04_img_01");
        	var src = "/mm/pop/mmA203_01/selectSignatureFile/"+data;
        	
        	obj.removeAttr("src").attr("src", src); 
        	
        	form.handle.setValidate("SM7001_04_form_01", "SIGNATURE_IMAGE", false);
        },
        updateHubSignatureInfo : function() {
            if (!form.handle.isValidate("SM7001_04_form_01")) return;
            
            var confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
	            if (flag) {
	                var obj = form.getObject("SM7001_04_form_01");
	                
	                form.init.setURL("/fm/sm/sm7001_04/updateHubSignatureInfo");
	                form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
	                form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
	                form.init.setCallBackFunction("updateHubSignatureInfoAfterUI");
	                
	                form.submit(obj);
	            }
	        });
        },
        deleteHubSignatureInfo : function() {
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7001_04_form_01");
                    
                    form.init.setURL("/fm/sm/sm7001_04/deleteHubSignatureInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setValidationFlag(false);
                    form.init.setCallBackFunction("deleteHubSignatureInfoAfterUI");
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {
    	openDialog_1 : function() {
    		var params = form.handle.getElementsParam("SM7001_04_form_01");
    		var dl_1 = dialog.getObject("MMA010_01_dailog_01");
    		
    		dialog.init.setTitle(resource.getMessage("MMA010_01"));
    		dialog.init.setURL("/mm/pop/mmA010_01");
    		dialog.init.setResizable(false);
    		dialog.init.setQueryParams(params);
    		
    		dialog.open(dl_1);
    	},
    	callByMMA010_01 : function(rowData) {
	    	if(oUtil.isNull(rowData)) return;
	    	
    		form.handle.loadData("SM7001_04_form_01", rowData);
    		form.handle.setValue("SM7001_04_form_01", "SIGNATURE_SEQ", rowData.SIGNATURE_SEQ);
    		
	    	SM7001_04.control.selectSignatureFile(rowData.SIGNATURE_SEQ);
	    	
	    	dialog.handle.close(dialog.getObject("MMA010_01_dailog_01"));
	    }
    },
    // 화면 UI 변경
    ui : {
    	updateChangeUI : function() {
    		form.util.setVisible("SM7001_04_span_01", false);
    		form.handle.removeClass("SM7001_04_form_01", "SIGNATURE_NAME", "search_readOnly");
    		form.handle.addClass("SM7001_04_form_01", "SIGNATURE_NAME", "input_readOnly");
    	}
    }, 
    // 파일 input 엘리먼트 구현
    file : {
        setFiles : function(pfile) {
            var file = pfile.files;
            
            if(file.length > 0) {
                if(file[0].size > 2000) {
                    alert(resource.getMessage("CC_2000byte를 초과한 파일은 등록할 수 없습니다.")+"(파일크기: "+file[0].size+"byte)");
                    form.handle.setValue("SM7001_04_form_01", "SIGNATURE_IMAGE", "");
                    return false;
                }
            }
        }
    }, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
};
