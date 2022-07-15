/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : DE-0000_01
 * 작업일자 : 2016.09.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	DE0000_01.config.applyFtaNation();
    DE0000_01.init.initComponent();
}

var DE0000_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	DE0000_01.calendar.initCalendar_1();
        	DE0000_01.calendar.initCalendar_2();
            
        	DE0000_01.datagrid.initGrid_1();
        	DE0000_01.datagrid.initGrid_2();
        }
    }, 
    // 달력 생성
    calendar : {
    	initCalendar_1 : function() {
        	var yyyymm = (oUtil.isNull(SESSION.WORK_YYYY_MM))?calendarMethod.util.toMonth2String():(SESSION.WORK_YYYY_MM);
        	var toyymm = calendarMethod.util.toMonth2String();
        	var initDate;
        	
        	if(oUtil.isNull(yyyymm) || yyyymm == toyymm) {
        		initDate = toyymm.substring(0, 4)+"01";
        	} else {
        		initDate = yyyymm;
        	}
        	
            var obj_01 = calendar.getObject("DE0000_01_form_01", "SALES_FROM_DATE_CAL");
            // 달력타입을 월로 지정
            calendar.init.setDateType("month");
            calendar.init.setInitDate(calendar.util.getFirstDay2String(initDate, "-"));
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	var yyyymm = (oUtil.isNull(SESSION.WORK_YYYY_MM))?calendarMethod.util.toMonth2String():(SESSION.WORK_YYYY_MM);
            var obj_01 = calendar.getObject("DE0000_01_form_01", "SALES_TO_DATE_CAL");
            
            // 달력타입을 월로 지정
            calendar.init.setDateType("month");
            calendar.init.setInitDate(calendar.util.getFirstDay2String(yyyymm, "-"));
            
            calendar.create(obj_01);
        }
    }, 
    // 콤보박스 생성
    combobox : {},
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	header1 : [
    		["CHECK",           "CHECK",                100, "left",       "center",      true,     false, null, true, null, 0, 0, true],
        	["COLUMN_NAME"     ,"TXT_COLUMN_NAME"      ,230  ,"left"      ,"center"      ,false    ,false    ,null    ,null    ,null    ,0    ,0],
            ["USER_SORT_TYPE"  ,"SORT"                 ,80   ,"center"    ,"center"      ,false    ,false    ,{type:'combobox', options:"gridCombo_1"}, null, null    ,0    ,0]
        ],
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("DE0000_01_dg_01");
            
            grid.init.setPage(true);
            grid.init.setEmptyMessage(true);
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_1"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1_1"}}, {params:{HEADER_ID:"header1_2"}}); // DB에서 그리드 해더 정보 획득
            //Theme.defaultGrid(dg_1, this.header1); // 스크립트에 지정된 변수값을 해더정보로 획득
        },
        initGrid_2 : function() {
    		var dg_2 = grid.getObject("DE0000_01_dg_02");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_2"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            
            Theme.defaultGrid(dg_2, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
    	onDblClick_DE0000_01_dg_01 : function(rowData) {
    		alert("Duble click...")
    	}
    },
    // 업무구현
    control : {
    	selectMainList : function() {
     		if(!form.handle.isValidate("DE0000_01_form_01")) return;
     		
            var dg_1 = grid.getObject("DE0000_01_dg_01");
        	var url = "/fm/od/od4201_01/selectMainList";
    		var params = form.handle.getElementsParam("DE0000_01_form_01");
    		
     		grid.handle.sendRedirect(dg_1, url, params);
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
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("DE0000_01_dg_01");
            var url = "/fm/od/od4201_01/selectMainList.fle";
            var fobj = form.getObject("DE0000_01_form_01");
            
  	        form.init.setURL(url);
  	        
  	        form.excelSubmit(dg_1, fobj, "Grid_Demo", resource.getMessage("LIST"));
  	    }
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
};
