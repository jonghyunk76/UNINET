/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM-8001_02
 * 작업일자 : 2020.07.15
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM8001_02.config.applyFtaNation();
    SM8001_02.init.initComponent();
}

var SM8001_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
			SM8001_02.combobox.initCombo_1();
			SM8001_02.combobox.initCombo_2();
			SM8001_02.combobox.initCombo_3();
		}
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
		data_1 : [
        	{CODE:"paypalDoHold", NAME:resource.getMessage("CC_보류")},
			{CODE:"paypalDoHoldCapture", NAME:resource.getMessage("CC_보류문서")},
			{CODE:"paypalDoHoldCancel", NAME:resource.getMessage("CC_결재취소")},
			{CODE:"paypalDoCapture", NAME:resource.getMessage("CC_결재문서")},
			{CODE:"paypalDoRefund", NAME:resource.getMessage("CC_환불")},
        ],
		data_2 : [
        	{CODE:"RouteRequest", NAME:resource.getMessage("CC_Route요청")},
			{CODE:"Shipper", NAME:resource.getMessage("CC_Shipper")}
        ],
        initCombo_1 : function() {
            var obj = combo.getObject("SM8001_02_form_01", "METHOD_NAME");
            
            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj);
        },
		initCombo_2 : function() {
            var obj = combo.getObject("SM8001_02_form_03", "METHOD_NAME");
            
            combo.init.setData(this.data_2);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj);
        },
		initCombo_3 : function() {
			var obj = combo.getObject("SM8001_02_form_02", "SERVICE_ID");
			
			combo.init.setURL("/rs/st/stR002_03/selectServiceMstComboList");
			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
			combo.init.setValueField("SERVICE_ID");
			combo.init.setNameField("SERVICE_NAME");
			
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
    	executeRelayBatch : function(data) {
            //form.handle.setValue("SM8001_02_form_02", "ACCESS_TOKEN", data.ACCESS_TOKEN);
    	},
		executePaypalTest : function(data) {
			
		},
    	executeInterface : function(data) {
    		if(data.resultCode == "0") {
    			var results = [];
                
                for (var property in data.result) {
                    var value = data.result[property];
                    if(value) {                        
                        results.push(property.toString() + ': ' + value);
                    }
                }
                
                $("#SM8001_02_ta_01").val("{"+results.join('\n,').toString()+"}");
    		} else if(data.resultCode == "1") {
    			$("#SM8001_02_ta_01").val(data.resultMsg);
    		} else if(data.resultCode == "2") {
    			$("#SM8001_02_ta_01").val(resource.getMessage("MSG_NO_SEARCH_RESULT"));
    		}
    	}
    },
    // 업무구현
    control : {
    	executeRelayBatch : function() {
            var url = form.handle.getValue("SM8001_02_form_02", "URL");
            var obj = form.getObject("SM8001_02_form_02");
            
            form.init.setURL(url);
            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("executeRelayBatch");
            form.init.setProgressFlag(true);
            
            form.submit(obj);
        },
		executePaypalTest : function() {
			var url = form.handle.getValue("SM8001_02_form_01", "URL");
            var obj = form.getObject("SM8001_02_form_01");
            
            form.init.setURL(url);
            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("executePaypalTest");
            form.init.setProgressFlag(true);
            
            form.submit(obj);
		},
		executeDhlTest : function() {
			var url = form.handle.getValue("SM8001_02_form_03", "URL");
            var obj = form.getObject("SM8001_02_form_03");
            
            form.init.setURL(url);
            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("executePaypalTest");
            form.init.setProgressFlag(true);
            
            form.submit(obj);
		}
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {}, 
    // 검색박스 구현
    searchbox : {},
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
