/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : ST-R001_01
 * 작업일자 : 2022.06.17
 * 
 ******************************************************************************************/

function onLoadPage() {
	STR001_01.config.applyFtaNation();
    STR001_01.init.initComponent();
}

var STR001_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	STR001_01.combobox.initCombo_1();
            STR001_01.datagrid.initGrid_1();
            STR001_01.dialog.openDialog_0();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	initCombo_1 : function() {
            var obj_1 = combo.getObject("STR001_01_form_01", "schKeyLike");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj_1);
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
            var dg_1 = grid.getObject("STR001_01_grid_01");
            
            grid.init.setPage(true);
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_1"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)            
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}}); // DB에서 그리드 해더 정보 획득            
        }
    },
    // 이벤트 처리
    event : {
    	onDblClick_STR001_01_grid_01 : function(rowData) {
    		STR001_01.dialog.openDialog_0('update');
    	}
    },
    // 업무구현
    control : {
    	selectMainList : function() {
    		var dg_1 = grid.getObject("STR001_01_grid_01");
    		var params = form.handle.getElementsParam("STR001_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/rs/st/stR001_01/selectServerList", params);
		}
    },
    // 다이얼로그 구현
    dialog : {
    	openDialog_0 : function(flag) {
    		var dl_1 = dialog.getObject("STR001_02_dailog_01");
    		var dg_1 = grid.getObject("STR001_01_grid_01");
    		
    		if(flag == "update" && !grid.handle.isSelected(dg_1)){ //선택안됐다면
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
    		
            if(flag == "insert") {
                params = form.handle.getElementsParam("STR001_01_form_01");
            } else {
                    params = grid.handle.getSelectedRowData(dg_1);
            } 
            
            params.flag = flag;
            
            dialog.init.setURL("/rs/st/stR001_02");
            dialog.init.setWidth(700);
            dialog.init.setHeight(306);
            dialog.init.setQueryParams(params);

            dialog.open(dl_1);
    	}
    },
    // 화면 UI 변경
    ui : {
        drawConnectStatus : function(value, row, index) {
            if(oUtil.isNull(value)) {
                return "";
            } else {
                if(row.CURRENT_STAT == "R") {
                    return "<label style=\"color:green;font-weight:bold;\">"+value+"</label>";
                } else if(row.CURRENT_STAT == "S") {
                    return "<label style=\"color:#c9c9c9\">"+value+"</label>";
                } else if(row.CURRENT_STAT == "E") {
                    return "<label style=\"color:red\">"+value+"</label>";
                } else {
                    return value;
                }
            }
        }
    }, 
    // 검색박스 구현
    searchbox : {},
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("STR001_01_grid_01");
            var fobj = form.getObject("STR001_01_form_01");
            
            form.init.setURL("/rs/st/stR001_01/selectServerList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("SCHE, TXT_LIST"), resource.getMessage("SCHE, TXT_LIST"));
        }
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
