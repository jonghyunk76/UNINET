/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : DE-0000_01
 * 작업일자 : 2016.09.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA029_01.config.applyFtaNation();
    MMA029_01.init.initComponent();
}

var MMA029_01 = {
    
    // 초기값 설정
    init : {
    	init_help : '<div style="font-size:14pt;padding:10px 20px 20px 40px;font-weight:bold;">TOMS 도움말 개요</div>'+
    	            '<div style="font-size:12pt;padding:0px 40px 0px 40px;">'+
    	            '[메인화면] 상단의 [Help > 내용]에는 TOMS에서 제공하는 메뉴정보 모음이 포함되어 있습니다.'+
    	            '도움말 항목을 보려면 <b>조회탭</b>에서 검색 키워드를 통해 확인하거나 <b>내용탭</b>에서 목록을 클릭합니다.'+
    	            '<b>내용탭</b>내 목록은 메뉴구조에 따른 계층구조로 표시됩니다.<br><br>'+
    	            '사용자를 위한 빠른 도움말 기능을 제공합니다.<br><br>'+
    	            '메뉴를 통해 이동하신 페이지 또는 팝업창에서 <b>F1키</b>를 누르거나 상단 [Help] 또는 팝업창 상단의 [물음표] 아이콘을 클릭하면 해당 페이지의 도움말이 표시됩니다.'+
    	            '</div>',
    	initComponent : function() {
        	MMA029_01.control.selectHelpMenuList();
        	$("#MMA029_01_div_01").html(this.init_help);
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
    // 트리
    tree : {
    	initTree_1 : function(rows) {
    		$("#MMA029_01_ul_01").tree({
    			data : rows,
    			lines: false,
    			onClick: function(node) {
                    if (!oUtil.isNull(node)) {
                        if (!oUtil.isNull(node.attributes)) {
                            if (!oUtil.isNull(node.attributes.url)) {
                                var movePath = node.attributes.url;
                                $('#TOMS_HELP').attr('src', movePath);
                            }
                        }
                    }
                }
    		});
    	}
    },
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {
    	fncLoadData : function(data) {
    		MMA029_01.tree.initTree_1(data);
    	},
    	selectKeyContentsAfterUI : function(jdata) {
    		if(oUtil.isNull(jdata)) return;
    		else {
    			var datas = eval(jdata);
    			var key = form.handle.getValue("MMA029_01_form_02", "schKeyWord");
    			
    			MMA029_01.ui.showKeyContents(datas, key);
    		}
    	}
    },
    // 업무구현
    control : {
    	selectHelpMenuList : function() {
    		var obj = form.getObject("MMA029_01_form_01");
    		
    		form.init.setURL("/mm/pop/mmA029_01/selectHelpMenuList");
    		form.init.setProgressFlag(false);
            form.init.setCallBackFunction("fncLoadData");
            
            form.submit(obj);
        },
        selectKeyContents : function() {
        	if(oUtil.isNull(form.handle.getValue("MMA029_01_form_02", "schKeyWord"))) {
        	    $("#MMA029_01_div_01").html(MMA029_01.init.init_help);
        	} else {
	        	var obj = form.getObject("MMA029_01_form_02");
	        	
	    		form.init.setURL("/mm/pop/mmA029_01/selectKeyContents");
	    		form.init.setProgressFlag(false);
	            form.init.setCallBackFunction("selectKeyContentsAfterUI");
	            
	            form.submit(obj);
        	}
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	divMove : function(num) {
    		for(var i = 1; i <= 3; i++) {
    			if(num == i) {
    				form.util.addClass("MMA029_01_divMove0"+i, "on");
    				form.util.setVisible("MMA029_01_div_0"+i, true);
    			} else {
    				form.util.removeClass("MMA029_01_divMove0"+i, "on");
    				form.util.setVisible("MMA029_01_div_0"+i, false);
    			}
    		}
    	},
    	showKeyContents : function(datas, key) {
    		if(oUtil.isNull(datas)) return;
    		
    		var contents = new StringBuffer();
    		
    		for(var i = 0 ; i < datas.length; i++) {
    			var data = datas[i];
    			
	    		contents.append("<div style=\"padding:3px 12px 6px 12px;font-weight:bold;font-size:14pt;color:#5583a5;\">");
	    		contents.append("<a href=\"/html/help/"+data.PID+".html\" target=\"_blank\">" + data.PROGRAM_NAME + "</a>");
	    		contents.append("</div>");
	    		
	    		contents.append("<div style=\"padding:0 12px 6px 24px;\">");
	    		contents.append(replaceAll(data.COMMENTS.toUpperCase(), key.toUpperCase(), "<b>"+key+"</b>"));
	    		contents.append("</div>");
	    		
	    		contents.append("<div style=\"padding:0 12px 20px 24px;color:green;\">");
	    		contents.append(resource.getMessage("RETMU") + " : " + data.PARENT_MENU_NAME + " > " + data.MENU_NAME);
	    		contents.append("</div>");
    		}
    		
    		$("#MMA029_01_div_01").html(contents.toString());
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
