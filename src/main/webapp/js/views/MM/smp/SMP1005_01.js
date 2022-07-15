/******************************************************************************************
 * 작성자 : 
 * Program Id : SM1005_01
 * _01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM1005_01.datagrid.initGrid_1();
}

var SM1005_01 = {
	
    init : {}, // 초기값 설정
	calendar : {}, // 달력 그리드 생성
	combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
				["CODE"        ,resource.getMessage("CUNTRY, CODE")        ,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
				["CODE_NAME"   ,resource.getMessage("CUNTRY, NAME")        ,200    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
                 ],
		initGrid_1 : function() {
			var dg_1 = grid.getObject("SM1005_01_grid_01");
			
			grid.init.setPage(true);
			grid.init.setFitColumns(true);
			// 이벤트 설정
			grid.event.setOnDblClickRow(dg_1);
			
			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
		onClick_SM1005_01_grid_01 : function(rowData) {},
		onDblClick_SM1005_01_grid_01 : function(rowData) {
			var pid = form.handle.getValue("SM1005_01_form_01", "TARGET_PID");
			
            if (!oUtil.isNull(pid)) {
            	var pro_id = eval("window." + pid + ".dialog");
            	
                if (typeof(pro_id["callBySM1005_01"]) == "function") {
                    pro_id["callBySM1005_01"](rowData);
                }
            }
		}
    },
    control : {// 업무구현
    	selectMainList : function() {
    		// 검색창의 validation 체크결과를 비교한다.
    		if(!form.handle.isValidate("SM1005_01_form_01")) return;
    		
    		var dg_1 = grid.getObject("SM1005_01_grid_01");
    		var params = form.handle.getElementsParam("SM1005_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA008_02/selectMainList", params);
    	},
    	selectRowValue : function() {
    		var dg_1 = grid.getObject("SM1005_01_grid_01");
    		var rowData = grid.handle.getSelectedRowData(dg_1);
    		
    		if(!grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_NO_SELECT"));
    			return;
    		}
    		
    		SM1005_01.event.onDblClick_SM1005_01_grid_01(rowData);
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
