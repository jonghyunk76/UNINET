/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_04
 * 작업일자 : 2020.05.13
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_04.config.applyFtaNation();
    MMB002_04.init.initComponent();
}

var MMB002_04 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_04.datagrid.initGrid_1();

			setTimeout(function () {
				form.getObject("MMB002_04_form_01", "CODE").focus();
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
    		["CODE"              ,resource.getMessage("CODE_ID")     ,60       ,"center"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
    		["CODE_NAME"         ,resource.getMessage("CODE_NAME")    ,180      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
			["CODE_NAME_ENG"     ,resource.getMessage("CODE_NAME__40_ENGLS__41")    ,180      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0]
    	],
    	initGrid_1 : function() {
    		var params = form.handle.getElementsParam("MMB002_04_form_01");
    		var dg_1 = grid.getObject("MMB002_04_grid_01");
            
    		grid.init.setURL("/mm/pop/MMB002_04/selectStandardCode");
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
        onDblClick_MMB002_04_grid_01 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_04_form_01", "TARGET_PID");
        	var field = form.handle.getValue("MMB002_04_form_01", "FIELD_ID");
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_04"]) == "function") {
                	rowData.FIELD_ID = field;
                    pro_id["callByMMB002_04"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
    	selectStandardCode : function() {
    		var dg_1 = grid.getObject("MMB002_04_grid_01");
    		var params = form.handle.getElementsParam("MMB002_04_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_04/selectStandardCode", params);
    	},
    	selectRowValue : function(type) {
            var dg_1 = grid.getObject("MMB002_04_grid_01");
            var rowData = {};
            
            if (type == "Y") {
            	// 그리드 선택
            	rowData = grid.handle.getSelectedRowData(dg_1);
            	
	            if(!grid.handle.isSelected(dg_1)) {
	                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
	                return;
	            }            	
            } else {
            	rowData.CODE = "";
            	rowData.CODE_NAME = "";
            }
            
            MMB002_04.event.onDblClick_MMB002_04_grid_01(rowData);
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
