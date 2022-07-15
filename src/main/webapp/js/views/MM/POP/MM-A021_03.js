/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A021_03
 * 작업일자 : 2016.09.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA021_03.config.applyFtaNation();
    MMA021_03.init.initComponent();
}

var MMA021_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA021_03.combobox.initCombo_1();
        	MMA021_03.combobox.initCombo_2();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	initCombo_1 : function() {
            var obj_1 = combo.getObject("MMA021_03_form_01", "EV_DOC_TYPE"); 

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"EV_DOC_TYPE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setCallFunction("onChangeEvDocType");
            combo.init.setRequired(true);
            
            combo.create(obj_1);
        },
        initCombo_2 : function() {
        	var params = form.handle.getElementsParam("MMA021_03_form_01");
            var obj_1 = combo.getObject("MMA021_03_form_01", "ITEM_CD"); 
            
            params.MESSAGE_CODE = "NOSEL";
            
            combo.init.setURL("/mm/cbox/selectReceiveCoItem");
            combo.init.setQueryParams(params);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            combo.init.setReadonly(false);
            
            combo.create(obj_1);
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
    	onChangeEvDocType : function(val) {
    		return;
    	},
    	saveEvDocFile : function() {
    		MMA017_01.control.selectEvdcFileList();
    		MMA021_03.dialog.closeDialog();
    	}
    },
    // 업무구현
    control : {
    	saveEvDocFile : function() {
    		var docType = combo.handle.getValue("MMA021_03_form_01", "EV_DOC_TYPE");
    		var itemCd = combo.handle.getValue("MMA021_03_form_01", "ITEM_CD");
    		
    		if(!(docType == "01" || docType == "02" || docType == "07" || docType == "09") && oUtil.isNull(itemCd)) {
    			alert(resource.getMessage("LTIT, MSG_CHOICE_BOMBO"));
    			return;
    		} else {
    			if(!form.handle.isValidate("MMA021_03_form_01")) return;
    		}
    		
    		$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {                
        		if(flag) {
		    		var obj = form.getObject("MMA021_03_form_01");
		    		
		    		form.init.setURL("/mm/pop/mmA017_01/insertEvCoFiles");
					form.init.setProgressFlag(true);
		            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY")); /*포괄만료일이 정상적으로 수정되었습니다. 원산지 정보를 수정하신 후 제출하시기 바랍니다.*/
		            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
					form.init.setCallBackFunction("saveEvDocFile");
					
					form.submit(obj);
        		}
    		});
    	},
    	excelSampleDownload : function() {
    		var dg_1 = grid.getObject("MMA021_03_grid_01");

            var obj = form.getObject("MMA021_03_form_02");
            
            form.init.setURL("/mm/pop/mmA016_01/excelSampleDownload");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
    	}
    },
    // 다이얼로그 구현
    dialog : {
    	closeDialog : function() {
    		var dl_1 = dialog.getObject("MMA021_03_dailog_01");
    		
        	dialog.handle.close(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {}, 
    // 파일 input 엘리먼트 구현
    file : {
    	setFiles : function(pfile) {
    		// 파일 크기 체크
    		var file = pfile.files;
            var tts = file[0].size;
            
            if(tts > parseInt(10000000)) {
                alert(resource.getMessage("MSG_FILE_SIZE_OVER") + "(" + resource.getMessage("MAX, SIZE") + " : 10MB)");
                form.handle.setValue("MMA021_03_form_01", "PRF_FILE", "");
                return;
            }
    	}
    }, 
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA021_03_grid_01");
            var fobj = form.getObject("MMA021_03_form_01");
            var title = resource.getMessage("VGB000_01, RECOD");
            
            form.init.setURL("/mm/pop/mmA021_03/selectRecieveCoDetail.fle");
            
            form.excelSubmit(dg_1, fobj, title, title);
        }
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		;
    	}
    }
};
