/******************************************************************************************
 * 작성자 : HanaRyu
 * Program Id : MM-A015_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA015_01.init.initComponent();
}

var MMA015_01 = {
    init : {
        grid_01_init_param : {NATION_CODE:"KR"},
        initComponent : function() {
        	MMA015_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {}, // 달력 그리드 생성
    combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
        header : [
                  ["CODE"         , resource.getMessage("CODE")     /*코드*/        , 50,  "center", "center", true, false, null, null, null, 0, 0],
                  ["CODE_NAME"    , resource.getMessage("CODE,NAME")/*코드명(한글)*/, 150, "left"  , "center", true, false, null, null, null, 0, 0]
                 ],
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("MMA015_01_form_01");
            var dg_1 = grid.getObject("MMA015_01_grid_01");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/mmA015_01/selectMainList");
            grid.init.setPage(true);
            grid.init.setFitColumns(true); 
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA015_01_grid_01 : function(rowData) {
            var pid = form.handle.getValue("MMA015_01_form_01", "TARGET_PID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA015_01"]) == "function") {
                    pro_id["callByMMA015_01"](rowData);
                }
            }
        }
    },
    control : {// 업무구현
        selectMainList : function() {
            var dg_1 = grid.getObject("MMA015_01_grid_01");
            var params = form.handle.getElementsParam("MMA015_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA015_01/selectMainList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA015_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }
            
            MMA015_01.event.onDblClick_MMA015_01_grid_01(rowData);
        }
    },
    dialog : {}, // 다이얼로그 구현
    file : {}, // 파일 input 엘리먼트 구현
    excel : {}
};
