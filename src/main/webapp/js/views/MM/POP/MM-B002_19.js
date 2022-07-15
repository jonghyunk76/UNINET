/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_19
 * 작업일자 : 2020.12.09
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_19.config.applyFtaNation();
    MMB002_19.init.initComponent();
}

var MMB002_19 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_19.combobox.initCombo_1();
        	MMB002_19.combobox.initCombo_2();
        	
        	MMB002_19.datagrid.initGrid_1();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	data_1 : [
    		{CODE:"AGEN_CD", NAME:resource.getMessage("CC_대행사코드")},
    		{CODE:"AGEN_MTLTY", NAME:resource.getMessage("CC_대행사상호")}
        ],    	
    	initCombo_1 : function() {
            var obj = combo.getObject("MMB002_19_form_01", "schKeyField");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMB002_19_form_01", "schKeyLike");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        } 	    	
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB002_19_grid_01");
            
            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_MMB002_19_grid_01 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_19_form_01", "TARGET_PID");
        	var field = form.handle.getValue("MMB002_19_form_01", "FIELD_ID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_19"]) == "function") {
                	rowData.FIELD_ID = field;
                    pro_id["callByMMB002_19"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {
    	selectAgencyList : function() {
    		var dg_1 = grid.getObject("MMB002_19_grid_01");
    		var params = form.handle.getElementsParam("MMB002_19_form_01");
    		
    		params.ORDER_BY_TYPE = form.handle.getRadioValue("MMB002_19_form_02", "ORDER_BY_TYPE");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_19/selectAgencyList", params);
    	},
    	selectRowValue : function(type) {
            var dg_1 = grid.getObject("MMB002_19_grid_01");
            var rowData = {};
            
            
            if (type == "Y") {
            	// 그리드 선택
            	rowData = grid.handle.getSelectedRowData(dg_1);
            	
	            if(!grid.handle.isSelected(dg_1)) {
	                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
	                return;
	            }            	
            } else {
            	rowData.AGEN_CD = "";
            	rowData.AGEN_MTLTY = "";
            }            
            
            MMB002_19.event.onDblClick_MMB002_19_grid_01(rowData);
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
