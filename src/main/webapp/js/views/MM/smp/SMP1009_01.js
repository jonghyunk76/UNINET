/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM1009_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM1009_01.init.initComponent();
}

var SM1009_01 = {
    
	// 초기값 설정
    init : {
    	initComponent : function() {
    		SM1009_01.calendar.initCalendar_1();
    		SM1009_01.calendar.initCalendar_2();
    		SM1009_01.combobox.initCombo_1();
    		SM1009_01.combobox.initCombo_2();
    		SM1009_01.combobox.initCombo_3();
    		
    		SM1009_01.datagrid.initGrid_1();
    	}
    },
    // 달력 그리드 생성
    calendar : {
    	initCalendar_1 : function() {
			var obj_01 = calendar.getObject("SM1009_01_form_01", "SCH_FROM_DATE");
			
			calendar.init.setInitDate(calendar.util.getFirstDay2String("201601", "-"));
			
			calendar.create(obj_01);
		},
		initCalendar_2 : function() {
			var obj_01 = calendar.getObject("SM1009_01_form_01", "SCH_TO_DATE");
			calendar.create(obj_01);
		}
    }, 
    // 콤보박스 그리드 생성
    combobox : {
    	data_1 : [{CODE:"MESSAGE", NAME:resource.getMessage("MSMGT, NAME")}, {CODE:"MESSAGE_CODE", NAME:resource.getMessage("MSMGT, CODE")}],
    	initCombo_1 : function() {
			var obj = combo.getObject("SM1009_01_form_01", "schKeyField");
			
			combo.init.setData(this.data_1);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_2 : function() {
			var obj = combo.getObject("SM1009_01_form_01", "schKeyLike");
			
			combo.init.setURL("/mm/cbox/selectStandardCode");
			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_3 : function() {
			var obj = combo.getObject("SM1009_01_form_01", "NATION_CODE");
			
			combo.init.setURL("/mm/cbox/selectStandardCode");
			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LENG_CD", ALL:"Y"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		}
    },
    datagrid : { // 데이터 그리드 생성
        header : [
                  ["MESSAGE_CODE", resource.getMessage("MSMGT, CODE"), 120, "center", "center", true, false, null, null, null, 0, 0],
                  ["NATION_CODE", resource.getMessage("LANG"), 90, "center", "center", true, false, null, null, null, 0, 0],
                  ["MESSAGE", resource.getMessage("MSMGT, NAME"), 450, "left", "center", true, false, null, null, null, 0, 0],
                  ["CREATE_DATE", resource.getMessage("REGER, DATE"), 130, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
                  ["CREATE_BY", resource.getMessage("CRTBY"), 100, "center", "center", true, false, null, null, null, 0, 0]
                 ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("SM1009_01_grid_01");
            
            grid.init.setURL("/mm/smp/smp1009/selectMessgeList");
            grid.init.setPage(true);
            grid.init.setFitColumns(true);
            grid.init.setAutoRowHeight(true);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header);
        }
    },
    event : { // 이벤트 처리
        onDblClick_SM1009_01_grid_01 : function(rowData) {
        	SM1009_01.dialog.openDialog_2('update');
        }
    },
    control : {// 업무구현
    	selectMainList : function() {
    		if(!form.handle.isValidate("SM1009_01_form_01")) return;
    		
    		var dg_1 = grid.getObject("SM1009_01_grid_01");
    		var params = form.handle.getElementsParam("SM1009_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/smp/smp1009/selectMessgeList", params);
    	},
    	saveMessageSystem : function() {
    		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_APPLY_MESSAGE_QUT"), function(flag) {
                if(flag) {
                	var obj = form.getObject("SM1009_01_form_01");
    	    		
    	    		form.init.setURL("/mm/smp/smp1009/saveMessageSystem");
    	    		form.init.setSucceseMessage(resource.getMessage("MSG_APPLY_MESSAGE_RST"));
    	    		form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
    	    		
    	    		form.submit(obj);
                }
            });
    	}
    },
    dialog : {
    	openDialog_1 : function() {
    		var dl_1 = dialog.getObject("MMA008_01_dailog_01");
    		
    		dialog.init.setWidth(600);
    		dialog.init.setHeight(400);
    		dialog.init.setURL("/mm/pop/mmA008_01");
    		dialog.init.setQueryParams({TARGET_PID:"SM1009_01", COMPANY_CD:SESSION.COMPANY_CD, CODE:form.handle.getValue("SM1009_01_form_01", "NATION_CODE")});
    		dialog.init.setTitle(resource.getMessage("MMA008_01"));
    		
    		dialog.open(dl_1);
    	},
    	openDialog_2 : function(flag) {
    		var dg_1 = grid.getObject("SM1009_01_grid_01");
    		var dl_1 = dialog.getObject("SM1009_02_dailog_01");
    		
    		if(flag != "insert" && !grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_NO_SELECT"));
    			return;
    		}
    		
    		// 팝업으로 넘겨줄 파라메터 생성
    		var params = {};
    		var params1 = {};
    		var params2 = {};
    		
    		params1.flag = flag;
    		if(flag != "insert") params2 = grid.handle.getSelectedRowData(dg_1);
    		
    		$.extend(params, params1, params2);

    		// 팝업 셋팅
    		dialog.init.setWidth(800);
    		dialog.init.setHeight(340);
    		dialog.init.setURL("/mm/smp/smp1009_02");
    		dialog.init.setQueryParams(params);
    		dialog.init.setTitle(resource.getMessage("SMP1009_02"));
    		
    		dialog.open(dl_1);
    	},
    	callByMMA008_01 : function(datas) {
    		var dl_1 = dialog.getObject("MMA008_01_dailog_01");
    		
    		form.handle.setValue("SM1009_01_form_01", "NATION_CODE", datas.CODE);
    		form.handle.setValue("SM1009_01_form_01", "NATION_NAME", datas.CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dl_1);
    	}
    },
    file : {
        // 파일 input 엘리먼트 구현
    },
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
    		var dg_1 = grid.getObject("SM1009_01_grid_01");
    		var fobj = form.getObject("SM1009_01_form_01");
	        
	        form.init.setURL("/mm/smp/smp1009/selectMessgeList.fle");
	        
	        // parameter description : grid객체, form객체, 파일명, 시트명
	        form.excelSubmit(dg_1, fobj, resource.getMessage("MSMGT, LIST"), resource.getMessage("MSMGT, LIST"));
	    }
    }
    
};
