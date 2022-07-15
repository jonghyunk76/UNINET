/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM7005_06
 * 작업일자 : 2022.06.23
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7005_06.config.applyFtaNation();
	SM7005_06.init.initComponent();
}

var SM7005_06 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            SM7005_06.datagrid.initGrid_1();

            setTimeout(function () {
                form.getObject("SM7005_06_form_01", "CODE").focus();
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
        header1 : [
            ["IF_CD"         ,resource.getMessage("CC_인터페이스ID")   ,100       ,"center"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["IF_NAME"         ,resource.getMessage("CC_인터페이스명")    ,150      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0]
        ],
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("SM7005_06_form_01");
            var dg_1 = grid.getObject("SM7005_06_grid_01");
            
            grid.init.setURL("/fm/sm/sm7005_01/selectInterfaceItemMstList");
            grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            grid.init.setAutoColumWidth(false);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_SM7005_06_grid_01 : function(rowData) {
            var pid = form.handle.getValue("SM7005_06_form_01", "TARGET_PID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callBySM7005_06"]) == "function") {
                    rowData.FIELD_ID = form.handle.getValue("SM7005_06_form_01", "FIELD_ID");
                    pro_id["callBySM7005_06"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
        selectStandardCode : function() {
            var dg_1 = grid.getObject("SM7005_06_grid_01");
            var params = form.handle.getElementsParam("SM7005_06_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7005_01/selectInterfaceItemMstList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("SM7005_06_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }
            
            SM7005_06.event.onDblClick_SM7005_06_grid_01(rowData);
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
