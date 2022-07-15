/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_10
 * 작업일자 : 2020.06.30
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_10.config.applyFtaNation();
    MMB002_10.init.initComponent();
}

var MMB002_10 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_10.datagrid.initGrid_1();
        	MMB002_10.datagrid.initGrid_2();
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
    	groupFormat_1 : function(value, rows){
            return "규격번호" + rows[0].STARD_NO;
        },
        initGrid_1 : function() {
    		var params = form.handle.getElementsParam("MMB002_10_form_01");
    		var dg_1 = grid.getObject("MMB002_10_grid_01");
            
    		grid.init.setURL("/mm/pop/MMB002_10/selectStardHscodeList");
    		grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(true);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        },
        initGrid_2 : function() {
    		var dg_2 = grid.getObject("MMB002_10_grid_02");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(true);
            grid.init.setGroupField("STARD_NO");
            grid.init.setGroupFormat(this.groupFormat_1);
            grid.init.setGroupViewType("E");
            grid.init.setShowConfigPage(true);
            grid.init.setRownumbers(false);
            grid.init.setSingleSelect(true);
            grid.init.setRecallFunction("initGrid_2");
            
            //grid.event.setOnDblClickRow(dg_2);
            
            Theme.defaultGrid(dg_2, {params:{HEADER_ID:"header2"}});
        }
    },
    // 이벤트 처리
    event : {
    	onClick_MMB002_10_grid_01 : function(rowData) {
    		MMB002_10.control.selectStardProductcodeList(rowData);
        },
        onDblClick_MMB002_10_grid_02 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_10_form_01", "TARGET_PID");
            
        	if(form.handle.isChecked("MMB002_10_form_02", "ONLY_STD_CODE_YN")) {
        		rowData.ONLY_STD_CODE_YN = "Y";
        	} else {
        		rowData.ONLY_STD_CODE_YN = "N";
        	}
        	
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_10"]) == "function") {
                    pro_id["callByMMB002_10"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
    	selectStardHscodeList : function() {
    		var dg_1 = grid.getObject("MMB002_10_grid_01");
    		var params = form.handle.getElementsParam("MMB002_10_form_01");    		
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_10/selectStardHscodeList", params);
    	},
    	selectStardProductcodeList : function(params) {
    		var dg_1 = grid.getObject("MMB002_10_grid_02");    		
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_10/selectStardProductcodeList", params);
    	},
    	selectStardProductcodeRow : function(params) {
            var rowData = new Object();
    		var dg_1 = grid.getObject("MMB002_10_grid_01");
    		var rowDatas_1 = grid.handle.getSelectedRowData(dg_1);
    		var stdPrdName = rowDatas_1.HS_CD + "-" + rowDatas_1.PRDNM_NO;
            
            rowData.STD_PRDNM_ENG = rowDatas_1.STD_PRDNM_ENG;
            
            var dg_2 = grid.getObject("MMB002_10_grid_02");
            var rowDatas_2 = grid.handle.getCheckedRowsData(dg_2);
            
            // 규칙 = 세번부호&-&품명번호&-&규격번호&규격값&-&규격번호&규격값....
            for(var i = 0; i < rowDatas_2.length; i++) {
                if(!oUtil.isNull(rowDatas_2[i]["STARD_NO"])) stdPrdName += "-" + rowDatas_2[i]["STARD_NO"] + rowDatas_2[i]["STARD_VAL"];
            }
            
            // 표준품명코드 생성
            rowData.STD_PRDNM_CD = stdPrdName;
            
    		MMB002_10.event.onDblClick_MMB002_10_grid_02(rowData);
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
