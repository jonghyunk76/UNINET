/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM-7529_01
 * 작업일자 : 2022.05.02
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7529_01.config.applyFtaNation();
    SM7529_01.init.initComponent();
}

var SM7529_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            SM7529_01.datagrid.initGrid_1();
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
    	header1_1 : [
            ["CHECK", "Check", 100, "center", "center", true, false, null, true, null, 0, 0],
            ["SESSION_ID", "Session No.", 100, "center", "center", true, false, null, null, null, 0, 0],
            ["SESSION_KEY", "Session key", 250, "center", "center", true, false, null, null, null, 0, 0],
            ["URL", "URL", 200, "left", "center", true, false, null, null, null, 0, 0],
            ["TIME_OUT", "Timeout", 100, "right", "center", true, false, null, null, null, 0, 0],
            ["BUFFER_SIZE", "Buffer size", 100, "right", "center", true, false, null, null, null, 0, 0]
        ],
    	initGrid_1 : function() {
            var dg_1 = grid.getObject("SM7529_01_grid_01");
            
            grid.init.setURL("/fm/sm/sm7529_01/selectWebSocketList");
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, this.header1_1);
        }
    },
    // 이벤트 처리
    event : {
        removeUserWebSocket : function(data) {
            SM7529_01.control.selectWebSocketList();
        },
        removeAllWebSocket : function(data) {
            SM7529_01.control.selectWebSocketList();
        }
    },
    // 업무구현
    control : {
        selectWebSocketList : function(p, m) {
            var dg_1 = grid.getObject("SM7529_01_grid_01");
            var params = form.handle.getElementsParam("SM7529_01_form_02");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7529_01/selectWebSocketList", params);
        },
        removeUserWebSocket : function() {
            var dg_1 = grid.getObject("SM7529_01_grid_01");
            
            if(!grid.handle.isChecked(dg_1)) {
                alert(resource.getMessage("MSG_NO_CHECK"));
                return;
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("CC_세션을 닫으시겠습니까?"), function(flag) {
                if (flag) {
                    var rows = grid.handle.getCheckedRowsData(dg_1);
                    
                    form.handle.setValue("SM7529_01_form_01", "gridData", grid.util.convertJson2Rows(rows));
                    
                    var obj = form.getObject("SM7529_01_form_01");
                    
                    form.init.setURL("/fm/sm/sm7529_01/removeUserWebSocket");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("removeUserWebSocket");
                    
                    form.submit(obj);
                }
            });
        },
        removeAllWebSocket : function() {
            $.messager.confirm(CNFIR, resource.getMessage("CC_모든 세션을 닫으시겠습니까?"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7529_01_form_02");
                    
                    form.init.setURL("/fm/sm/sm7529_01/removeAllWebSocket");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("removeAllWebSocket");
                    
                    form.submit(obj);
                }
            });
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
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
