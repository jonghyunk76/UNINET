/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B006_04
 * 작업일자 : 2020.05.14
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB006_04.config.applyFtaNation();
    MMB006_04.init.initComponent();
}

var MMB006_04 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            MMB006_04.datagrid.initGrid_1();
            MMB006_04.datagrid.initGrid_2();
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
    	initGrid_1 : function() {
            var dg_1 = grid.getObject("MMB006_04_grid_01");
            
            grid.init.setPage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setFitColumns(false);
            grid.init.setRecallFunction("initGrid_1");
            grid.init.setEmptyMessage(false);
            
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        },
        initGrid_2 : function() {
            var dg_1 = grid.getObject("MMB006_04_grid_02");
            
            grid.init.setPage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setFitColumns(false);
            grid.init.setRecallFunction("initGrid_2");
            grid.init.setEmptyMessage(false);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header2"}});
        }
    },
    // 이벤트 처리
    event : {
        onClick_MMB006_04_grid_01 : function(row) {
            MMB006_04.control.selectInvUpMasterInfo(row);
        }
    },
    // 업무구현
    control : {
        searchMainList : function() {
            var dg_1 = grid.getObject("MMB006_04_grid_01");
            var params = form.handle.getElementsParam("MMB006_04_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/MMB006_01/selectInvUpSetupList", params);
        },
        selectInvUpMasterInfo : function(rowData) {
            form.handle.setValue("MMB006_04_form_02", "FORM_SETUP_SN", rowData.FORM_SETUP_SN);
            form.handle.setValue("MMB006_04_form_02", "BCNC_CD", rowData.BCNC_CD);
            
            var dg_1 = grid.getObject("MMB006_04_grid_02");
            var params = form.handle.getElementsParam("MMB006_04_form_02");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/MMB006_01/selectInvUpMasterInfo", params);
        },
        applySettingInfo : function() {
            var pid = form.handle.getValue("MMB006_04_form_01", "TARGET_PID");
            var dg_1 = grid.getObject(pid + "_grid_01");
            var params = form.handle.getElementsParam("MMB006_04_form_02");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/MMB006_01/selectInvUpMasterInfo", params);
        }
    },
    // 다이얼로그 구현
    dialog : {
        openDialog_1 : function() {
            var params = new Object();
            
            params.TARGET_PID = "MMB006_04";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            
            var dl_1 = dialog.getObject("MMB002_03_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("CC_[f]거래처 조회"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(850);
            dialog.init.setHeight(600);
            dialog.init.setURL("/mm/pop/MMB002_03");
            
            dialog.open(dl_1);
        },
        callByMMB002_03 : function(rowData) {
            form.handle.setValue("MMB006_04_form_01", "BCNC_CD", rowData.BCNC_CD);
            
            var dl_1 = dialog.getObject("MMB002_03_dailog_01");
            dialog.handle.close(dl_1);
        },
    },
    // 화면 UI 변경
    ui : {}, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMB006_04_grid_01");
            var fobj = form.getObject("MMB006_04_form_01");
            
            form.init.setURL("/mm/pop/MMB006_01/selectInvUpSetupList.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("CC_설정업체 리스트"), resource.getMessage("LIST"));
        },
        excelDownload_2 : function() {
            var dg_1 = grid.getObject("MMB006_04_grid_02");
            var fobj = form.getObject("MMB006_04_form_02");
            
            form.init.setURL("/mm/pop/MMB006_01/selectInvUpMasterInfo.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("CC_업체 설정값"), resource.getMessage("LIST"));
        }
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
