/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM-7004_04
 * 작업일자 : 2020-01-25
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7004_04.config.applyFtaNation();
    SM7004_04.init.initComponent();
}

var SM7004_04 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            SM7004_04.calendar.initCalendar_1();
            SM7004_04.calendar.initCalendar_2();
            
            SM7004_04.combobox.initCombo_1();
            
            SM7004_04.datagrid.initGrid_1();
            SM7004_04.datagrid.initGrid_2();
        }
    }, 
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
            var vToDate  = calendar.util.toDate2String(SEPERATOR_OF_DATE);
            var vFromDate = calendar.util.addDate2String("m", -2, vToDate, SEPERATOR_OF_DATE);
            var val_01 = form.handle.getValue("SM7004_04_form_01", "SCH_FROM_DATE");
            var obj_01 = calendar.getObject("SM7004_04_form_01", "SCH_FROM_DATE");
            
            if (!oUtil.isNull(vFromDate)) {
                calendar.init.setInitDate(vFromDate);    
            }
            calendar.init.setRequired(true);
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
            var vToDate  = calendar.util.toDate2String(SEPERATOR_OF_DATE);
            var val_02 = form.handle.getValue("SM7004_04_form_01", "SCH_TO_DATE");
            var obj_02 = calendar.getObject("SM7004_04_form_01", "SCH_TO_DATE");
            
            if (!oUtil.isNull(vToDate)) {
                calendar.init.setInitDate(vToDate);    
            }
            calendar.init.setRequired(true);
            
            calendar.create(obj_02);
        }
    },
    // 콤보박스 그리드 생성
    combobox : {
    	initCombo_1 : function() {
            var oBatchStatus = combo.getObject("SM7004_04_form_01", "BATCH_STATUS");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"BATCH_STATUS", ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(oBatchStatus);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [
        	["IF_NAME"             , resource.getMessage("TXT_BATCH,NAME")     /*배치명*/     , 180, "left"  , "center", true, false, null, null, null, 0, 0],
        	["START_DATE"          , resource.getMessage("DATE")               /*실행 시작시간*/ ,  90, "center", "center", true, false, null, null, null, 0, 0],
        	["START_DATE_TIME"     , resource.getMessage("START,HOUR")         /*실행 시작시간*/ , 90,  "center", "center", true, false, null, null, null, 0, 0],
        	["END_DATE_TIME"       , resource.getMessage("TXT_END,HOUR")       /*실행 완료시간*/ , 90,  "center", "center", true, false, null, null, null, 0, 0],
        	["TOTAL_ROWS"          , resource.getMessage("TXT_TOTAL_COUNT")    /*실행 시작시간*/ , 70,  "right",  "center", true, false, null, null, {format:"int"}, 0, 0],
        	["IF_PARAM"            , resource.getMessage("TXT_IF_PARAM")       /*실행 시작시간*/ , 220, "left",   "center", true, false, null, null, null, 0, 0],
        	["BATCH_STATUS_NAME"   , resource.getMessage("TXT_RESLT_STAT")     /*실행 시작시간*/ , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["RESULT_MESSAGE"      , resource.getMessage("TXT_BATCH_EXE_RESULT")/*처리결과*/    ,300,  "left"  , "center", true, false, null, null, null, 0, 0]
        ],
        header2 : [
        	["SCHEDULE_SEQ"    , "No."                              , 40, "center"  , "center", false, false, null, null, null, 0, 0],
        	["IF_NAME"         , resource.getMessage("INF, NAME")   , 240, "left"  , "center", false, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("SM7004_04_grid_01");
            
            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header1);
        },
        initGrid_2 : function() {
            var params = form.handle.getElementsParam("SM7004_04_form_01");
            var dg_2 = grid.getObject("SM7004_04_grid_02");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/fm/sm/sm7004_02/selectScheduleMappingList");
            grid.init.setPage(false);
            grid.init.setRownumbers(false);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false);
            
            grid.event.setOnClickRow(dg_2);
            
            Theme.defaultGrid(dg_2, this.header2);
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_SM7004_04_grid_01 : function(rowData) {
            SM7004_04.dialog.openDialog_1();
        },
        onClick_SM7004_04_grid_02 : function(rowData) {
        	SM7004_04.control.selectCloseList(rowData);
        }
    },
    // 업무구현
    control : {
        selectCloseList : function(data) {
        	if(!oUtil.isNull(data)) {
            	form.handle.setValue("SM7004_04_form_01", "IF_CD", data.IF_CD);
            } else {
            	form.handle.setValue("SM7004_04_form_01", "IF_CD", "");
            }
        	
            var dg_1 = grid.getObject("SM7004_04_grid_01");
            var params = form.handle.getElementsParam("SM7004_04_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/cs/cs6001_01/selectCloseList", params);
        }
    },
    // 다이얼로그 구현
    dialog : {
        openDialog_1 : function() {
            var dg_1 = grid.getObject("SM7004_04_grid_01");
            
            if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var params = grid.handle.getSelectedRowData(dg_1);
            var dl_1 = dialog.getObject("MMA027_01_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("MMA027_01"));
            dialog.init.setQueryParams(params);
            dialog.init.setURL("/mm/pop/mmA027_01");
            dialog.init.setWidth(1024);
            dialog.init.setHeight(768);
            dialog.init.setResizable(false);
            
            dialog.open(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {},
    // 파일 input 엘리먼트 구현
    file : {
        
    },
    // 엑셀다운로드 구현
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("SM7004_04_grid_01");
            var fobj = form.getObject("SM7004_04_form_01");
            
            form.init.setURL("/fm/cs/SM7004_04/selectCloseList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("FNISH, RECOD"), resource.getMessage("FNISH, RECOD"));
        }
    },
    // 화면 초기 설정
    config : {
        applyFtaNation : function() {}
    }
    
};
