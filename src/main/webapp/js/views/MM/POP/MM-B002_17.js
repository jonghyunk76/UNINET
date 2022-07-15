/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_17
 * 작업일자 : 2020.06.30
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_17.config.applyFtaNation();
    MMB002_17.init.initComponent();
}

var MMB002_17 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_17.datagrid.initGrid_1();
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
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB002_17_grid_01");

    		grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(true);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_MMB002_17_grid_01 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_17_form_01", "TARGET_PID");
        	
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_17"]) == "function") {
                    pro_id["callByMMB002_17"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
    	applySelectData : function() {
    		var dg_1 = grid.getObject("MMB002_17_grid_01");
            var selData = grid.handle.getSelectedRowData(dg_1);
            
            MMB002_17.event.onDblClick_MMB002_17_grid_01(selData);
    	},
    	selectDatagridColumnList : function() {
    		var dg_1 = grid.getObject("MMB002_17_grid_01");
    		var params = form.handle.getElementsParam("MMB002_17_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/cbox/selectDatagridColumnList", params);
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
