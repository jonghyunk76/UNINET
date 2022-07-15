/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_03
 * 작업일자 : 2020.05.09
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_03.config.applyFtaNation();
    MMB002_03.init.initComponent();	
}

var MMB002_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_03.datagrid.initGrid_1();
			setTimeout(function () {
				form.getObject("MMB002_03_form_01", "BCNC_MTLTY").focus();
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
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB002_03_grid_01");
            
            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_MMB002_03_grid_01 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_03_form_01", "TARGET_PID");
        	var field = form.handle.getValue("MMB002_03_form_01", "FIELD_ID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_03"]) == "function") {
                	rowData.FIELD_ID = field;
                    pro_id["callByMMB002_03"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
    	selectClientList : function() {
    		var dg_1 = grid.getObject("MMB002_03_grid_01");
    		var params = form.handle.getElementsParam("MMB002_03_form_01");
    		
    		params.ORDER_BY_TYPE = form.handle.getRadioValue("MMB002_03_form_02", "ORDER_BY_TYPE");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_03/selectClientList", params);
    	},
    	selectRowValue : function() {
            var dg_1 = grid.getObject("MMB002_03_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }
            
            MMB002_03.event.onDblClick_MMB002_03_grid_01(rowData);
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
