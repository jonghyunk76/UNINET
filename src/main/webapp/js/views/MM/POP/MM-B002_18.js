/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_18
 * 작업일자 : 2020.06.30
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_18.config.applyFtaNation();
    MMB002_18.init.initComponent();
}

var MMB002_18 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_18.combobox.initCombo_1();
        	MMB002_18.combobox.initCombo_2();
        	
        	MMB002_18.datagrid.initGrid_1();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	dataSearchType : [
    		{CODE : "ISU_INSTT_NM", NAME : resource.getMessage("CC_기관명")},
    		{CODE : "INSTT_CD", NAME : resource.getMessage("CC_코드")},
    		{CODE : "NATION_KOR", NAME : resource.getMessage("CC_국가명")}
        ],
        initCombo_1 : function() {
            var objSearchType = combo.getObject("MMB002_18_form_01", "schKeyField");
            
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchType);
        },
        initCombo_2 : function() {
            var objSearchKeyLike = combo.getObject("MMB002_18_form_01", "schKeyLike");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchKeyLike);
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB002_18_grid_01");

    		grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_MMB002_18_grid_01 : function(rowData) {
        	if(oUtil.isNull(rowData)) return;
        	
        	var pid = form.handle.getValue("MMB002_18_form_01", "TARGET_PID");
        	var field = form.handle.getValue("MMB002_18_form_01", "FIELD_ID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_18"]) == "function") {
                	rowData.FIELD_ID = field;
                    pro_id["callByMMB002_18"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
    	applySelectData : function() {
    		var dg_1 = grid.getObject("MMB002_18_grid_01");
            var selData = grid.handle.getSelectedRowData(dg_1);
            
            MMB002_18.event.onDblClick_MMB002_18_grid_01(selData);
    	},
    	selectOverseaIssueOrganList : function() {
    		var dg_1 = grid.getObject("MMB002_18_grid_01");
    		var params = form.handle.getElementsParam("MMB002_18_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_18/selectOverseaIssueOrganList", params);
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
