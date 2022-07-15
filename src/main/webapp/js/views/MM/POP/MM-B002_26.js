/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_26
 * 작업일자 : 2022.06.03
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_26.config.applyFtaNation();
    MMB002_26.init.initComponent();
}

var MMB002_26 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_26.datagrid.initGrid_1();

			setTimeout(function () {
				form.getObject("MMB002_26_form_01", "CODE").focus();
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
    		["TXT_VALUE1"         ,resource.getMessage("CC_법령부호")   ,80       ,"center"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
    		["TXT_VALUE2"         ,resource.getMessage("CC_법령명")    ,125      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
			["CODE_NAME"          ,resource.getMessage("CC_서류명")    ,230      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0]
    	],
    	initGrid_1 : function() {
    		var params = form.handle.getElementsParam("MMB002_26_form_01");
    		var dg_1 = grid.getObject("MMB002_26_grid_01");
            
    		grid.init.setURL("/mm/pop/MMB002_26/selectLwodPapeKndList");
    		grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
			grid.init.setAutoColumWidth(false);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_MMB002_26_grid_01 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_26_form_01", "TARGET_PID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_26"]) == "function") {
                    pro_id["callByMMB002_26"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
    	selectStandardCode : function() {
    		var dg_1 = grid.getObject("MMB002_26_grid_01");
    		var params = form.handle.getElementsParam("MMB002_26_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_26/selectLwodPapeKndList", params);
    	},
    	selectRowValue : function() {
            var dg_1 = grid.getObject("MMB002_26_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }
            
            MMB002_26.event.onDblClick_MMB002_26_grid_01(rowData);
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
