/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_05
 * 작업일자 : 2020.05.13
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_05.config.applyFtaNation();
    MMB002_05.init.initComponent();
}

var MMB002_05 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_05.datagrid.initGrid_1();
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
    		["PORT_AIR_TY"         ,resource.getMessage("CC_적재항구분")     	,100      ,"center"       ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
    		["PORT_AIR_TY_NM"      ,resource.getMessage("CC_적재항구분")     	,100      ,"center"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
    		["PORT_AIR_CD"         ,resource.getMessage("CC_코드 ID")     	,100      ,"center"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
    		["PORT_AIR_NM"         ,resource.getMessage("CC_코드명")     		,180      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
    		["NATION_CD"           ,resource.getMessage("CC_국가코드")  		,180      ,"left"         ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
    		["NATION_NM"           ,resource.getMessage("CC_국가")  			,180      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0]
    	],
    	header2 : [
    		["CSMH_CD"         ,resource.getMessage("CC_세관코드")     		,60       ,"center"       ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
    		["CSMH_NM"         ,resource.getMessage("CC_세관명")  			,180      ,"left"         ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0]
    	],    	
    	initGrid_1 : function() {
    		var params = form.handle.getElementsParam("MMB002_05_form_01");
    		var dg_1 = grid.getObject("MMB002_05_grid_01");
            
    		grid.init.setURL("/mm/pop/MMB002_02/selectKcsCodeList");
    		grid.init.setQueryParams(params);
            grid.init.setPage(true);
            grid.init.setEmptyMessage(true);
            grid.init.setFitColumns(true);
            
            grid.event.setOnDblClickRow(dg_1);
                        
            form.handle.getValue("BI3001_02_form_01", "CATEGORY_CD");
            if (params.CATEGORY_CD == 'OPOS') {
            	Theme.defaultGrid(dg_1, this.header1);
            } else if (params.CATEGORY_CD == 'CT') {
            	Theme.defaultGrid(dg_1, this.header2);
            }
            
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_MMB002_05_grid_01 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_05_form_01", "TARGET_PID");
        	var field = form.handle.getValue("MMB002_05_form_01", "FIELD_ID");
            debugger;
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_05"]) == "function") {
                	rowData.FIELD_ID = field;
                    pro_id["callByMMB002_05"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
    	selectKcsCodeList : function() {
    		var dg_1 = grid.getObject("MMB002_05_grid_01");
    		var params = form.handle.getElementsParam("MMB002_05_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_02/selectKcsCodeList", params);
    	},
    	selectRowValue : function() {
            var dg_1 = grid.getObject("MMB002_05_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }
            
            MMB002_05.event.onDblClick_MMB002_05_grid_01(rowData);
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
