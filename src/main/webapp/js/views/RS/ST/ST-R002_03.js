/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : ST-R002_03
 * 작업일자 : 2022.06.24
 * 
 ******************************************************************************************/

function onLoadPage() {
	STR002_03.config.applyFtaNation();
    STR002_03.init.initComponent();
}

var STR002_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            STR002_03.datagrid.initGrid_1();

            setTimeout(function () {
                form.getObject("STR002_03_form_01", "CODE").focus();
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
            ["SERVICE_ID"         ,resource.getMessage("CC_서비스ID")   ,100       ,"center"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["SERVICE_NAME"         ,resource.getMessage("CC_서비스명")    ,150      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["SERVICE_TYPE_NAME"          ,resource.getMessage("CC_서비스 타입")    ,100      ,"center"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0]
        ],
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("STR002_03_form_01");
            var dg_1 = grid.getObject("STR002_03_grid_01");
            
            grid.init.setURL("/rs/st/stR002_03/selectServiceMstList");
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
        onDblClick_STR002_03_grid_01 : function(rowData) {
            var pid = form.handle.getValue("STR002_03_form_01", "TARGET_PID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callBySTR002_03"]) == "function") {
                    pro_id["callBySTR002_03"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
        selectStandardCode : function() {
            var dg_1 = grid.getObject("STR002_03_grid_01");
            var params = form.handle.getElementsParam("STR002_03_form_01");
            
            grid.handle.sendRedirect(dg_1, "/rs/st/stR002_03/selectServiceMstList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("STR002_03_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }
            
            STR002_03.event.onDblClick_STR002_03_grid_01(rowData);
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
