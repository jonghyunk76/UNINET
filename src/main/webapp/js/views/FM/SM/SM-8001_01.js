/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM-8001_01
 * 작업일자 : 2020.07.15
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM8001_01.config.applyFtaNation();
    SM8001_01.init.initComponent();
}

var SM8001_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {}
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
    	selectAccessToken : function(data) {
    		form.handle.setValue("SM8001_01_form_02", "ACCESS_TOKEN", data.ACCESS_TOKEN);
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
                
                $("#SM8001_01_ta_01").val("{"+results.join('\n,').toString()+"}");
    		} else if(data.resultCode == "1") {
    			$("#SM8001_01_ta_01").val(data.resultMsg);
    		} else if(data.resultCode == "2") {
    			$("#SM8001_01_ta_01").val(resource.getMessage("MSG_NO_SEARCH_RESULT"));
    		}
    	}
    },
    // 업무구현
    control : {
    	selectAccessToken : function() {
            var obj = form.getObject("SM8001_01_form_01");
            
            form.init.setURL("/fm/sm/SM8001_01/selectAccessToken");
            form.init.setCallBackFunction("selectAccessToken");
            form.init.setProgressFlag(true);
            
            form.submit(obj);
        },
        executeInterface : function() {
            var obj = form.getObject("SM8001_01_form_02");
            
            form.init.setURL("/fm/sm/SM8001_01/executeInterface");
            form.init.setCallBackFunction("executeInterface");
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
