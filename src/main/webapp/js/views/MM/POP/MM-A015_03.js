/******************************************************************************************
 * 작성자 : HanaRyu
 * Program Id : MM-A015_03
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA015_03.init.initComponent();
}

var MMA015_03 = {
    init : {
        initComponent : function() {
        	MMA015_03.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {}, // 달력 그리드 생성
    combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
        header : [
        	["TXT_VALUE1"    , resource.getMessage("CODE")        , 100, "center"  , "center", true, false, null, null, null, 0, 0],
        	["CODE_NAME_ENG" , resource.getMessage("COTRY,NAME")  , 150, "left"    , "center", true, false, null, null, null, 0, 0],
            ["TXT_VALUE2"    , resource.getMessage("AGRET_NAME")  , 100, "center"  , "center", true, false, null, null, null, 0, 0]
            
        ],
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("MMA015_03_form_01");
            var dg_1 = grid.getObject("MMA015_03_grid_01");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/fm/sm/sm7007_01/selectSysCodeList");
            grid.init.setPage(true);
            grid.init.setFitColumns(true); 
            grid.init.setEmptyMessage(false); 
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA015_03_grid_01 : function(rowData) {
            var pid = form.handle.getValue("MMA015_03_form_01", "TARGET_PID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA015_03"]) == "function") {
                    pro_id["callByMMA015_03"](rowData);
                }
            }
        }
    },
    control : {// 업무구현
        selectSysCodeList : function() {
            var dg_1 = grid.getObject("MMA015_03_grid_01");
            var params = form.handle.getElementsParam("MMA015_03_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7007_01/selectSysCodeList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA015_03_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }
            
            MMA015_03.event.onDblClick_MMA015_03_grid_01(rowData);
        }
    },
    dialog : {}, // 다이얼로그 구현
    file : {}, // 파일 input 엘리먼트 구현
    excel : {}
};
