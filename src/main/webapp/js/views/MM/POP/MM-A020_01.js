/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A020_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA020_01.init.initComponent();
}

var MMA020_01 = {
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA020_01.combobox.initCombo_1();
        	MMA020_01.combobox.initCombo_2();
        	MMA020_01.combobox.initCombo_3();
        	MMA020_01.combobox.initCombo_4();
        	MMA020_01.combobox.initCombo_5();
        	MMA020_01.combobox.initCombo_6();

        	var flag = form.handle.getValue("MMA020_01_form_01", "flag");
        	
            if(flag == "insert") {
            	MMA020_01.ui.insertChangeUI();
            } else if(flag == "update") {
            	MMA020_01.ui.updateChangeUI();
            }
        }
    }, 
    calendar : {}, // 달력 그리드 생성
    // 콤보박스 그리드 생성
    combobox : {
    	CURRENCY : [{CODE:"USD", NAME:"US Dollar"},{CODE:"MXN", NAME:"Mexican Peso"}],
    	//충족여부
    	initCombo_1 : function() {
            var objCoDocType = combo.getObject("MMA020_01_form_01", "FTA_CO_YN");
            var vCompanyCd = form.handle.getValue("MMA020_01_form_01", "COMPANY_CD"); 

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, CATEGORY_CD:"YN_TYPE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(objCoDocType);
        },
        //FTA협정
        initCombo_2 : function() {
        	var objFtaCd = combo.getObject("MMA020_01_form_01", "FTA_CD");
        	
        	combo.init.setURL("/mm/cbox/selectFta");
        	combo.init.setQueryParams({FTA_GROUP_CD:form.handle.getValue("MMA020_01_form_01", "FTA_GROUP_CD")});
        	combo.init.setValueField("CODE");
        	combo.init.setNameField("NAME");
        	combo.init.setRequired(true);
        	combo.init.setCallFunction("comboFtaCdOnChange");
        	
        	combo.create(objFtaCd);
        },
        //FTA그룹코드
        initCombo_3 : function() {
        	var obj = combo.getObject("MMA020_01_form_01", "FTA_GROUP_CD");
        	var vCompanyCd  = form.handle.getValue("MMA020_01_form_01", "COMPANY_CD"); 
        	
        	combo.init.setURL("/mm/cbox/selectStandardCode");
        	combo.init.setQueryParams({COMPANY_CD:vCompanyCd, CATEGORY_CD:"FTA_GROUP_CD"});
        	combo.init.setValueField("CODE");
        	combo.init.setNameField("NAME");
        	combo.init.setRequired(true);
        	
        	combo.create(obj);
        },
        //결정기준
        initCombo_4 : function() {
        	var param = {HS_CODE:form.handle.getValue("MMA020_01_form_01", "HS_CODE"),
				         NALADISA_CODE : form.handle.getValue("MMA020_01_form_01", "NALADISA_CODE"),
                         FTA_CODE:form.handle.getValue("MMA020_01_form_01", "FTA_CD")};
        	var objRuleContents = combo.getObject("MMA020_01_form_01", "RULE_CONTENTS");
        	
        	combo.init.setQueryParams(param);
        	combo.init.setURL("/mm/pop/MMA020_01/selectRuleContents");
			combo.init.setValueField("RULE_CONTENTS");
			combo.init.setNameField("RULE_CONTENTS");
			combo.init.setRequired(true);
    		
        	combo.create(objRuleContents);
        },
        initCombo_5 : function() {
        	var obj = combo.getObject("MMA020_01_form_01", "CURRENCY");
        	
        	combo.init.setData(this.CURRENCY);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			combo.init.setRequired(true);
    		
        	combo.create(obj);
        },
        initCombo_6 : function() {
        	var obj = combo.getObject("MMA020_01_form_01", "SALES_CURRENCY");
        	
        	combo.init.setData(this.CURRENCY);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			combo.init.setRequired(true);
    		
        	combo.create(obj);
        }
    },
    // 데이터 그리드 생성
    datagrid : { },
    // 이벤트 처리
    event : {
        comboFtaCdOnChange : function(data) {
        	if(data.FTA_CD == "PMXBR" || data.FTA_CD == "PMXUY" || data.FTA_CD == "PMXAR" ||
        			data.FTA_CD == "PMXAR_P" || data.FTA_CD == "PMXBR_P") {
        		form.handle.setValue("MMA020_01_form_01", "FTA_ASSOCIATION_CD", "MER");
        	} else {
        		form.handle.setValue("MMA020_01_form_01", "FTA_ASSOCIATION_CD", "");
        	}
        }
    },
    // 업무구현
    control : {
    	//결정기준 조회
    	selectRuleContents : function() {
    		var param = {HS_CODE:form.handle.getValue("MMA020_01_form_01", "HS_CODE"),
					     NALADISA_CODE : form.handle.getValue("MMA020_01_form_01", "NALADISA_CODE"),
                         FTA_CODE:combo.handle.getValue("MMA020_01_form_01", "FTA_CD")};

    		combo.handle.reload("MMA020_01_form_01", "RULE_CONTENTS", "/mm/pop/MMA020_01/selectRuleContents", param);
    	},
    	saveDetailInfo : function() {
            if(!form.handle.isValidate("MMA020_01_form_01")) return;
            
            var pid = form.handle.getValue("MMA020_01_form_01", "TARGET_PID");
            
            if(!oUtil.isNull(pid)) {  
                var pro_id = eval("window." + pid + ".dialog");
                
                if(typeof(pro_id["callByMMA020_01"]) == "function") {
                	var obj = form.handle.getElementsParam("MMA020_01_form_01");
            		pro_id["callByMMA020_01"](obj);
                }
            }
    	}
    },
    // 다이얼로그 구현
    dialog : {
        openDialog1 : function() {
        	var dl_1 = dialog.getObject("MMA005_01_dailog_01");
    		
        	var vTargetPid = form.handle.getValue("MMA020_01_form_01", "TARGET_PID");
        	form.handle.getValue("MMA020_01_form_01", "BF_TARGET_PID", vTargetPid);
        	
        	// 팝업으로 넘겨줄 파라메터 생성
            var params = form.handle.getElementsParam("MMA020_01_form_01");
            params.TARGET_PID = "MMA020_01";
            params.ITEM_NAME = "";
    		
    		dialog.init.setURL("/mm/pop/mmA005_01");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("MMA005_01"));
    		dialog.open(dl_1);
        },
        callByMMA005_01 : function(datas) {
            var dg_1 = dialog.getObject("MMA005_01_dailog_01");
            
            form.handle.setValue("MMA020_01_form_01", "ITEM_CD", datas.ITEM_CD);
            form.handle.setValue("MMA020_01_form_01", "ITEM_NAME", datas.ITEM_NAME);
            form.handle.setValue("MMA020_01_form_01", "HS_CODE", datas.HS_CODE);
            form.handle.setValue("MMA020_01_form_01", "NALADISA_CODE", datas.NALADISA_CODE);
            
            var vBfTargetPid = form.handle.getValue("MMA020_01_form_01", "BF_TARGET_PID");
        	form.handle.getValue("MMA020_01_form_01", "TARGET_PID", vBfTargetPid);
            
            // 창을 닫는다.
            dialog.handle.close(dg_1);
        }
    },
    // 화면 UI 변경
    ui : {
    	commonUI : function() {
        	//수정불가
        	combo.handle.readonly("MMA020_01_form_01", "FTA_GROUP_CD", true);
    	},
        insertChangeUI : function() {
        	this.commonUI();
        },
        updateChangeUI : function() {
        	this.commonUI();
        	
        	//validation체크 제외
        	form.handle.setValidate("MMA020_01_form_01", "ITEM_CD", false);
        	
        	//읽기전용으로 변경
            form.handle.readonly("MMA020_01_form_01", "ITEM_NAME", true);
            form.handle.addClass("MMA020_01_form_01", "ITEM_NAME", "input_readOnly");
            combo.handle.readonly("MMA020_01_form_01", "FTA_CD", true);
            
            //버튼 숨기기
            form.util.setVisible("MMA020_01_form_01_ITEM", false);
        }
    }
};
