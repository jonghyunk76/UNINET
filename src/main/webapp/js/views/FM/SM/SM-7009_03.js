/******************************************************************************************
 * 작성자 : sbj1000
 * Program Id : SM-7009_03
 * 작업일자 : 2016.05.17
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7009_03.init.initComponent();
}

var SM7009_03 = {
	    
		// 초기값 설정
	    init : {
	    	initComponent : function() {
	    		
	    		var flag = form.handle.getValue("SM7009_03_form_01", "flag");

	            // 초기화면에 보여줄 데이터 조회
	            if (flag == "update") {
	            	SM7009_03.control.selectMenuLanguageInfo();
	            	SM7009_03.ui.updateChangeUI();
	            }
	    		
	    		if(flag == "insert"){
	    			SM7009_03.combobox.initCombo_1();
	    			SM7009_03.ui.insertChangeUI();
	    		}
	    	}
	    },
	    // 달력 그리드 생성
	    calendar : { }, 
	    // 콤보박스 그리드 생성
	    combobox : {
	    	
	    	cdata1 : [{CODE:"KR", NAME:"한글"},{CODE:"EN", NAME:"English"},{CODE:"ES", NAME:"español"}],
	    	
	    	initCombo_1 : function() {	    		
	    		var obj_01 = combo.getObject("SM7009_03_form_01", "LANGUAGE_CAT");
	    		
		        combo.init.setData(this.cdata1);
		        combo.init.setValueField("CODE");
		        combo.init.setNameField("NAME");
		        combo.create(obj_01);
	        }
	    },
	    // 데이터 그리드 생성
	    datagrid : { },
	    // 이벤트 처리
	    event : {
	    	// 성공적으로 요청처리가 완료될 경우 호출되는 함수
	    	updateAfterUI : function(data) {
	    		
	    		var oMap = null;
	            try {
	                oMap = data.dataMap.map;    
	            } catch (e) {
	                oMap = {};
	            }
	            
	            SM7009_01.control.selectMenuLanguageList(oMap);
	            form.handle.readonly("SM7009_03_form_01", "MENU_ID", true);
	    		form.handle.addClass("SM7009_03_form_01", "MENU_ID", "input_readOnly");
	        },
	        selectAfterUI : function(data) {
	            form.handle.loadData("SM7009_03_form_01", data);
	        }
	    },
	    control : {// 업무구현
	    	
	    	selectMenuLanguageInfo : function() {
	            var obj = form.getObject("SM7009_03_form_01");
	            
	            form.init.setURL("/fm/sm/sm7009_03/selectMenuLanguageInfo");
	            form.init.setCallBackFunction("selectAfterUI");
	            form.init.setProgressFlag(false);
	            // 성공과 실패에 대한 메시지 안 뜨게 함(추후엔 공통에서 처리할 것임)
	            form.init.setSucceseMessage("");
	            form.init.setFailMessage("");
	            
	            form.submit(obj);
	        },
	        
	    	// insert/update 수행
	    	updateMenuLanguageInfo : function() {
	    		
	    		if (!form.handle.isValidate("SM7009_03_form_01")) return;
	    		
	    		var confMsg = "";
	    		if(form.handle.getValue("SM7009_03_form_01", "flag") == "insert") {
	    			confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
	    		} else {
	    			confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
	    		}
	    		
	    		$.messager.confirm(CNFIR, confMsg, function(flag) {
                    if(flag) {
                    	var obj = form.getObject("SM7009_03_form_01");
                        
        	    		if(form.handle.getValue("SM7009_03_form_01", "flag") == "insert") {
        	    			form.init.setURL("/fm/sm/sm7009_03/insertMenuLanguageInfo");
        	    		}
        	    		if(form.handle.getValue("SM7009_03_form_01", "flag") == "update") {
        	    			form.init.setURL("/fm/sm/sm7009_03/updateMenuLanguageInfo");
        	    		}
        	    		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
        	    		form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
        	    		form.init.setCallBackFunction("updateAfterUI");
        	    		form.submit(obj);
                    }
                });
	    	}
	    },
	    dialog : { },
	    // 화면 UI 변경
	    ui : {
	    	updateChangeUI : function(){
	    		form.handle.readonly("SM7009_03_form_01", "MENU_ID", true);
	    		form.handle.addClass("SM7009_03_form_01", "MENU_ID", "input_readOnly");
	    		form.handle.readonly("SM7009_03_form_01", "LANGUAGE_CAT", true);
	    		form.handle.addClass("SM7009_03_form_01", "LANGUAGE_CAT", "input_readOnly");
	    		form.handle.readonly("SM7009_03_form_01", "LANG_ID", true);
	    		form.handle.addClass("SM7009_03_form_01", "LANG_ID", "input_readOnly");
	    	},
	    	insertChangeUI : function(){
	    		form.handle.readonly("SM7009_03_form_01", "MENU_ID", true);
	    		form.handle.addClass("SM7009_03_form_01", "MENU_ID", "input_readOnly");
	    	}
	    },
	    // 파일 input 엘리먼트 구현
	    file : { },
	    // 엑셀다운로드 구현
	    excel : { }
};