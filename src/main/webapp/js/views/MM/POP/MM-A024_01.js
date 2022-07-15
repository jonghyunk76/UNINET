/******************************************************************************************
 * 작성자 : RHN 
 * Program Id : MMA024_01
 * 작업일자 : 2016.04.12
 ******************************************************************************************/

function onLoadPage() {
	MMA024_01.init.initComponent();
}

var MMA024_01 = {
    init : {
    	initComponent : function() {
        	MMA024_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
	calendar : {}, // 달력 그리드 생성
	combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
                  ["PRODUCT_LINE_CD", resource.getMessage("TXT_PRODUCT_GROUP_CODE")/*제품군 코드*/, 80,  "center", "center", true, false, null, null, null, 0, 0],
                  ["CONTENT1",	      resource.getMessage("TXT_PRODUCT_GROUP_NAME")/*제품군 명*/  , 150, "left",   "center", true, false, null, null, null, 0, 0]
                 ],
		initGrid_1 : function() {
			var dg_1 = grid.getObject("MMA024_01_grid_01");
			var params = form.handle.getElementsParam("MMA024_01_form_01");

			grid.init.setPage(true);
			grid.init.setFitColumns(true);
			grid.init.setURL("/mm/pop/mmA024_01/selectMainList");
            grid.init.setQueryParams(params);
            
			// 이벤트 설정
			grid.event.setOnDblClickRow(dg_1);
			
			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
		onClick_MMA024_01_grid_01 : function(rowData) {},
		onDblClick_MMA024_01_grid_01 : function(rowData) {
			var pid = form.handle.getValue("MMA024_01_form_01", "TARGET_PID");
			
            if(!oUtil.isNull(pid)) {
            	var pro_id = eval("window." + pid + ".dialog");
            	
                if(typeof(pro_id["callByMMA024_01"]) == "function") {
                    pro_id["callByMMA024_01"](rowData);
                }
            }
		}
    },
    control : {// 업무구현
    	selectRowValue : function() {
    		var dg_1 = grid.getObject("MMA024_01_grid_01");
    		var rowData = grid.handle.getSelectedRowData(dg_1);
    		
    		if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
    		}
    		
    		MMA024_01.event.onDblClick_MMA024_01_grid_01(rowData);
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
