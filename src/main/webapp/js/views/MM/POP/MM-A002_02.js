function onLoadPage() {
	MMA002_02.datagrid.initGrid_1();
}

var MMA002_02 = {
	
    init : {}, // 초기값 설정
	calendar : {}, // 달력 그리드 생성
	combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
		    ["COMPANY_CD",	     resource.getMessage("CONPY, CODE"), 100, "center", "center", true, false, null, null, null, 0, 0],
		    ["COMPANY_NAME",	 resource.getMessage("CONPY,NAME"), 120, "left",   "center", true, false, null, null, null, 0, 0],
		    ["ADDRESS1",	     resource.getMessage("ADDRS"),      200, "left",   "center", true, false, null, null, null, 0, 0],
		    ["OFFICER_PHONE_NO", resource.getMessage("CNTACT"),     110, "center", "center", true, false, null, null, null, 0, 0]
        ],
		initGrid_1 : function() {
			var dg_1 = grid.getObject("MMA002_02_grid_01");
    		
			grid.init.setPage(true);
			grid.init.setFitColumns(true);
			// 이벤트 설정
			grid.event.setOnDblClickRow(dg_1);

			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : {
		onDblClick_MMA002_02_grid_01 : function(rowData) {
			var pid = form.handle.getValue("MMA002_02_form_01", "TARGET_PID");
			
            if (!oUtil.isNull(pid)) {
            	var pro_id = eval("window." + pid + ".dialog");
            	
                if (typeof(pro_id["callByMMA002_02"]) == "function") {
                    pro_id["callByMMA002_02"](rowData);
                }
            }
		}
    },
    control : {
    	selectMainList : function() {
            if (!form.handle.isValidate("MMA002_02_form_01")) {
                return;
            }
            
    		var dg_1 = grid.getObject("MMA002_02_grid_01");
    		var params = form.handle.getElementsParam("MMA002_02_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA002_02/selectMainCompanyList", params);
    	},
    	selectRowValue : function() {
    		var dg_1 = grid.getObject("MMA002_02_grid_01");
    		var rowData = grid.handle.getSelectedRowData(dg_1);
    		
    		if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
    		}
    		
    		MMA002_02.event.onDblClick_MMA002_02_grid_01(rowData);
    	}
    },
    dialog : {
    	// 다이얼로그 구현
    },
    file : {
    	// 파일 input 엘리먼트 구현
    },
    excel : {
    	// 엑셀다운로드 구현
    }
	
};
