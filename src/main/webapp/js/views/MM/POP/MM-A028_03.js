/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A028_03
 * 작업일자 : 2019.07.02
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA028_03.config.applyFtaNation();
    MMA028_03.init.initComponent();
}

var MMA028_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA028_03.control.selectSurveyTempletList();
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
    	selectSurveyTempletList : function(datas) {
    		if($("#MMA028_03_div_01").length > 0) {
    			$("#MMA028_03_div_01").empty();
    		}
    		
    		var surveyStr = createSurvey("MMA028_03_div_01", datas.survey, datas.code, null, null, datas.survey[0].SURVEY_TYPE);
    		
        	$("#MMA028_03_div_01").html(surveyStr);
        	
        	form.setFormValue("MMA028_03_form_01", "str", surveyStr);
    	}
    },
    // 업무구현
    control : {
    	selectSurveyTempletList : function() {
    		var obj = form.getObject("MMA028_03_form_01");
        	
    		form.init.setURL("/fm/sm/sm7020_01/selectSurveyTempletList"); // 공통으로 소스이관해서 구현할 것
    		form.init.setCallBackFunction("selectSurveyTempletList");
    		form.init.setProgressFlag(true);
    		form.init.setValidationFlag(true);
    		
            form.submit(obj);
    	}
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {}, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
};
