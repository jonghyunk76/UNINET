/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A030_01_01
 * 작업일자 : 2017.09.05
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA030_01.config.applyFtaNation();
    MMA030_01.init.initComponent();
}

var MMA030_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	;
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
    	selectNewJoinCompanyNoAfter :function(data) {
    		//alert(data.COMPANY_JOIN_NO + " / " + data.SERVICE_STATUS);
    		if(!oUtil.isNull(data.COMPANY_JOIN_NO)) {
    			alert("<h3>이미 가입된 사업자등록번호입니다.</h3><br><br>가입정보를 확인하시려면 우측의 <b>가입신청 확인</b>에서 가능합니다.");
    		} else {
    			form.handle.reset("MMA030_01_form_03");
    			
    			data.BUSINESS_NO = form.handle.getValue("MMA030_01_form_01", "BUSINESS_NO");
    			data.COMPANY_JOIN_NO = "";
    			
    			form.util.setVisible("MMA030_01_div_04", true);
    			form.util.setVisible("MMA030_01_div_05", false);
    			form.handle.loadData("MMA030_01_form_03", data);
    			
    			var obj = tab.getObject("MMA030_01_tabs_01");
    			
    			tab.handle.select(obj, 1);
    			
    			form.util.removeClass("MMA030_01_divMove01", "active");
    			form.util.addClass("MMA030_01_divMove02", "active");
    			form.util.removeClass("MMA030_01_divMove03", "active");
    		}
    	},
    	selectOldJoinCompanyNoAfter :function(data) {
    		if(oUtil.isNull(data.COMPANY_JOIN_NO)) {
    			alert("<h3>입력하신 정보에 대한 가입정보를 찾을 수 없습니다.</h3><br><br>사업자등록번호와 가입시 입력한 신청자의 ID 또는 이메일을 확인하신 후 다시 시도해 주시기 바랍니다.<br>가입전이라면 신규 가입신청이 필요합니다.");
    		} else {
    			form.handle.setValue("MMA030_01_form_03", "COMPANY_JOIN_NO", data.COMPANY_JOIN_NO);
    			form.util.setVisible("MMA030_01_div_04", false);
    			form.util.setVisible("MMA030_01_div_05", true);
    			
    			MMA030_01.control.selectJoinCompanyInfo();
    			
    			var obj = tab.getObject("MMA030_01_tabs_01");
    			
    			tab.handle.select(obj, 1);
    			
    			form.util.removeClass("MMA030_01_divMove01", "active");
    			form.util.addClass("MMA030_01_divMove02", "active");
    			form.util.removeClass("MMA030_01_divMove03", "active");
    		}
    	},
    	selectJoinCompanyInfoAfter : function(data) {
    		form.handle.loadData("MMA030_01_form_03", data);
    	},
    	insertJoinCompanyInfoAfter : function() {
    		form.util.setVisible("MMA030_01_span_01", true);
			form.util.setVisible("MMA030_01_span_02", false);
			
    		var obj = tab.getObject("MMA030_01_tabs_01");
    		
			tab.handle.select(obj, 2);
			
			form.util.removeClass("MMA030_01_divMove01", "active");
			form.util.removeClass("MMA030_01_divMove02", "active");
			form.util.addClass("MMA030_01_divMove03", "active");
    	},
    	updateJoinCompanyInfoAfter : function() {
    		form.util.setVisible("MMA030_01_span_01", false);
			form.util.setVisible("MMA030_01_span_02", true);
			
    		var obj = tab.getObject("MMA030_01_tabs_01");
    		
			tab.handle.select(obj, 2);
			
			form.util.removeClass("MMA030_01_divMove01", "active");
			form.util.removeClass("MMA030_01_divMove02", "active");
			form.util.addClass("MMA030_01_divMove03", "active");
    	}
    },
    // 업무구현
    control : {
    	selectNewJoinCompanyNo : function() {
    		if(!form.util.isChecked("MMA030_01_input_01")) {
    			alert("이용약관을 확인 후 동의하셔야 가입이 가능합니다.");
    			return;
    		}
    		
    		var obj = form.getObject("MMA030_01_form_01");
    		
    		form.init.setURL("/mm/noses/mmA030_01/selectJoinCompanyNo");
    		form.init.setProgressFlag(false);
            form.init.setCallBackFunction("selectNewJoinCompanyNoAfter");
            
            form.submit(obj);
    	},
    	selectOldJoinCompanyNo : function() {
    		var obj = form.getObject("MMA030_01_form_02");
    		
    		form.init.setURL("/mm/noses/mmA030_01/selectJoinCompanyNo");
    		form.init.setProgressFlag(false);
            form.init.setCallBackFunction("selectOldJoinCompanyNoAfter");
            
            form.submit(obj);
    	},
    	selectJoinCompanyInfo : function() {
    		var obj = form.getObject("MMA030_01_form_03");
    		
    		form.init.setURL("/mm/noses/mmA030_01/selectJoinCompanyInfo");
    		form.init.setProgressFlag(false);
    		form.init.setValidationFlag(false);
            form.init.setCallBackFunction("selectJoinCompanyInfoAfter");
            
            form.submit(obj);
    	},
    	insertJoinCompanyInfo : function() {
    		var userId = form.handle.getValue("MMA030_01_form_03", "USER_ID");
    		
    		if(userId.toUpperCase() == "FTA" || userId.toUpperCase() == "SUPPLIER") {
    			alert(userId + "는 예약된 ID로 사용할 수 없습니다.");
    			
    			return;
    		}
    		
    		var obj = form.getObject("MMA030_01_form_03");
    		
    		form.init.setURL("/mm/noses/mmA030_01/insertJoinCompanyInfo");
    		form.init.setProgressFlag(true);
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("insertJoinCompanyInfoAfter");
            
            form.submit(obj);
    	},
    	updateJoinCompanyInfo : function() {
    		var userId = form.handle.getValue("MMA030_01_form_03", "USER_ID");
    		
    		if(userId.toUpperCase() == "FTA" || userId.toUpperCase() == "SUPPLIER") {
    			alert(userId + "는 예약된 ID로 사용할 수 없습니다.");
    			
    			return;
    		}
    		
    		var obj = form.getObject("MMA030_01_form_03");
    		
    		form.init.setURL("/mm/noses/mmA030_01/updateJoinCompanyInfo");
    		form.init.setProgressFlag(true);
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("updateJoinCompanyInfoAfter");
            
            form.submit(obj);
    	}
    },
    // 다이얼로그 구현
    dialog : {
    	close : function() {
    		var obj = dialog.getObject("MMA030_01_dailog_01");
			
    		dialog.handle.close(obj);
    	}
    },
    // 화면 UI 변경
    ui : {
    	firstMove : function() {
    		var obj = tab.getObject("MMA030_01_tabs_01");
			
			tab.handle.select(obj, 0);
			
			form.util.addClass("MMA030_01_divMove01", "active");
			form.util.removeClass("MMA030_01_divMove02", "active");
			form.util.removeClass("MMA030_01_divMove03", "active");
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
