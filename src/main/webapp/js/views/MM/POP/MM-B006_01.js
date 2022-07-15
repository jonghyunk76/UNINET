/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B006_01
 * 작업일자 : 2020.05.14
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB006_01.config.applyFtaNation();
    MMB006_01.init.initComponent();
}

var MMB006_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	var nm = form.handle.getValue("MMB006_01_form_01", "TAB_NM");
        	var lcno = form.handle.getValue("MMB006_01_form_01", "INVE_NO");
        	
        	if(nm == "1") {
        		form.util.setVisible("MMB006_01_divMove01", true);
        		form.util.setVisible("MMB006_01_divMove02", true);
        		if(oUtil.isNull(lcno)) {
        			form.util.disabled("MMB006_01_divMove02", true);
        		}
        	} else if(nm == "2") {
        		form.util.setVisible("MMB006_01_divMove01", false);
        		form.util.setVisible("MMB006_01_divMove02", true);
        		
        		$("#MMB006_01_span_01").html(resource.getMessage("CC_부가자료"));
        	} else {
        		form.util.setVisible("MMB006_01_divMove01", true);
        		form.util.setVisible("MMB006_01_divMove02", true);
        		
        		nm = "1";
        	}
        	
        	setTimeout( function() { 
        		MMB006_01.ui.divMove(nm);
        	}, 500);
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
    datagrid : {
    	initGrid_1 : function() {}
    },
    // 이벤트 처리
    event : {},
    // 업무구현
    control : {},
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	go_page :function(url, method, id, title, fid) {
    		var params = new Object();
    		var closeYn = false;
    		
    		try {
	    		var params = form.handle.getElementsParam(fid);
	    		
	    		params.menuIfMethod = method;
	    		params.MENU_AUTH_YN = "Y";
	    		params.MENU_ID = id;
	    		
	    		var tabObj = tab.getObject("MMB006_01_tabs_01");
	    		var newTabInfo = {id:id, title:title, href:url, queryParams:params, closable:closeYn};
	    		
	    		tab.handle.addTab(tabObj, newTabInfo, title);
    		} catch(e) {
    			if(SESSION.USER_ID == "fta") console.log("go_page : " + e);
    		}
    	},
    	divMove : function(num) {
    		var fid;
    		var url;
    		var pid;
    		
    		if(num == "1") { // 인보이스 업로드
    			pid = "MMB006_02";
    			title = resource.getMessage("CC_인보이스");
    			fid = "MMB006_01_form_01";
    			url = "/mm/pop/MMB006_02";
    			form.handle.setValue("MMB006_01_form_01", "DATA_SE", "I");
    		} else if(num == "2") {  // 패킹리스트 업로드
    			pid = "MMB006_03";
    			title = resource.getMessage("CC_부가자료");
    			fid = "MMB006_01_form_01";
    			url = "/mm/pop/MMB006_03";
    			form.handle.setValue("MMB006_01_form_01", "DATA_SE", "P");
    		}
    		
    		for(var i = 1; i < 3; i++) {
                if(num == i) {
                    form.util.addClass("MMB006_01_divMove0"+i, "on");
                } else {
                    form.util.removeClass("MMB006_01_divMove0"+i, "on");
                }
            }
    		
    		this.go_page(url, null, pid, title, fid);
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
