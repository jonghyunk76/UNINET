/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_09
 * 작업일자 : 2020.05.22
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_09.config.applyFtaNation();
    MMB002_09.init.initComponent();
}

var MMB002_09 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_09.combobox.initCombo_1();
        	MMB002_09.combobox.initCombo_2();
        	
        	MMB002_09.datagrid.initGrid_1();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	data_1 : [
    		{CODE:"BRA_NM", NAME:resource.getMessage("CC_브랜드명")},
    		{CODE:"BRA_CD", NAME:resource.getMessage("CC_코드")},
        	{CODE:"BRA_NO", NAME:resource.getMessage("CC_Dv")}
       	],
       	initCombo_1 : function() {
            var obj = combo.getObject("MMB002_09_form_01", "schKeyField");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMB002_09_form_01", "schKeyLike");

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
    		var dg_1 = grid.getObject("MMB002_09_grid_01");
            
            grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            // 에디팅 모드 적용
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(true);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            grid.init.setEmptyMessage(false);
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
    	updateCommonBrandInfo : function(data) {
    		var dg_1 = grid.getObject("MMB002_09_grid_01");
    		
    		grid.handle.initChangeData(dg_1); // [중요]데이터그리드내 에디팅으로 변경된 값의 상태를 초기화함
    	},
    	deleteCommonBrandInfo : function(data) {
    		MMB002_09.control.selectCommonBrandList();
    	}
    },
    // 업무구현
    control : {
        selectCommonBrandList : function() { 
			var dg_1 = grid.getObject("MMB002_09_grid_01");			// 그리드 Object 가져오기
			var params = form.handle.getElementsParam("MMB002_09_form_01");	// 파라미터 가져오기
			
			grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_09/selectCommonBrandList", params);	// 그리드에 데이터베이스 연계
		},
		updateCommonBrandInfo : function() {
            var dg_1 = grid.getObject("MMB002_09_grid_01");
            
            dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
            
            if(grid.handle.getChangesCount(dg_1) == 0) {
                alert(resource.getMessage("MSG_NOT_CHANGE"));
                return;
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_INSERT"), function(flag) {
                if (flag) {
                	var rows = grid.handle.getChangesData(dg_1, true); // 변경된 값 조회(FIELD_STATE(I/U/D)가 추가됨)
                    
                	console.log(grid.util.convertJson2Rows(rows));
                	
                	form.handle.setValue("MMB002_09_form_01", "gridData", grid.util.convertJson2Rows(rows));
                    
                    var obj = form.getObject("MMB002_09_form_01");
                    
                    form.init.setURL("/mm/pop/MMB002_09/updateCommonBrandInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateCommonBrandInfo");
                    
                    form.submit(obj);
                }
            });
        },
        deleteCommonBrandInfo : function() {
            var dg_1 = grid.getObject("MMB002_09_grid_01");
    		
    		if(!grid.handle.isChecked(dg_1)) {
    			alert(resource.getMessage("MSG_NO_CHECK"));
    			return;
    		}
    		
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
        		if(flag) {
        			var rows = grid.handle.getCheckedRowsData(dg_1);
		        	
		        	form.handle.setValue("MMB002_09_form_01", "gridData", grid.util.convertJson2Rows(rows));
		        	
        			var obj = form.getObject("MMB002_09_form_01");
        			
        			form.init.setURL("/mm/pop/MMB002_09/deleteCommonBrandInfo");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
        			form.init.setCallBackFunction("deleteCommonBrandInfo");
        			
        			form.submit(obj);
        		}
        	});
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	insertRow : function(i) {
        	var dg_1 = grid.getObject("MMB002_09_grid_01");
        	
        	grid.handle.appendRow(dg_1);
        },
        deleteRow : function(i) {
        	var dg_1 = grid.getObject("MMB002_09_grid_01");
        	
        	if(!grid.handle.isSelected(dg_1)) {
        		alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	var idx = grid.handle.getCurrentRowIndex(dg_1);
        	
        	grid.handle.removeRow(dg_1, idx);
        }
    },
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
