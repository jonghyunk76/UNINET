/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B007_02
 * 작업일자 : 2020.05.15
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB007_02.config.applyFtaNation();
    MMB007_02.init.initComponent();
}

var MMB007_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB007_02.datagrid.initGrid_1();
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
    		var params = form.handle.getElementsParam("MMB007_02_form_01");
    		var dg_1 = grid.getObject("MMB007_02_grid_01");
            
    		if(params.TARGET_PID == "MMB007_01") grid.init.setURL("/mm/pop/MMB007_01/selectImportPrdnmHistList");
    		else if(params.TARGET_PID == "MMB007_03") grid.init.setURL("/mm/pop/MMB007_03/selectExportPrdnmHistList");
    		grid.init.setQueryParams(params);
    		grid.init.setPage(false);
    		grid.init.setEmptyMessage(false);
    		grid.init.setFitColumns(false);
    		grid.init.setFilter(true);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
    	onDblClick_MMB007_02_grid_01 : function(data) {
            var pid = form.handle.getValue("MMB007_02_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
            	var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB007_01"]) == "function") {
                    pro_id["callByMMB007_01"](data);
                }
            }
        }
    },
    // 업무구현
    control : {
    	applySelectData : function() {
    		var dg_1 = grid.getObject("MMB007_02_grid_01");
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            MMB007_02.event.onDblClick_MMB007_02_grid_01(rowData);
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
