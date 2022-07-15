/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7003_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7003_01.init.initComponent();
}

var SM7003_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            SM7003_01.combobox.initCombo_1();
            SM7003_01.combobox.initCombo_2();
            
            SM7003_01.datagrid.initGrid_1();
        }
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {
        dataSearchType : [
        	{CODE : "NAME", NAME : resource.getMessage("TXT_NAME_KOR")},
        	{CODE : "USER_ID", NAME : resource.getMessage("TXT_USER_ID")} 
        ],
        initCombo_1 : function() {
            var objSearchType = combo.getObject("SM7003_01_form_01", "schKeyField");
            
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchType);
        },
        initCombo_2 : function() {
            var objSearchKeyLike = combo.getObject("SM7003_01_form_01", "schKeyLike");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchKeyLike);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [
        	["USER_ID"      , resource.getMessage("TXT_USER_ID")         ,  90, "center", "center", true, false, null, null, null, 0, 0],
            ["NAME"         , resource.getMessage("TXT_NAME_KOR")        , 180, "left"  , "center", true, false, null, null, null, 0, 0],
            ["EMP_NO"       , resource.getMessage("TXT_EMP_NO")          , 130, "center", "center", true, false, null, null, null, 0, 0],
            ["POSITION"     , resource.getMessage("POSIT")               ,  90, "center", "center", true, false, null, null, null, 0, 0],
            ["EMAIL"        , resource.getMessage("TXT_EMAIL")           , 170, "center", "center", true, false, null, null, null, 0, 0],
            ["CELL_PHONE_NO", resource.getMessage("CELPH")               , 150, "center", "center", true, false, null, null, null, 0, 0],
            ["MANAGER_NAME" , resource.getMessage("IN_CHARGE_TYPE")      ,  130, "center", "center", true, false, null, null, null, 0, 0],
            ["START_DATE"   , resource.getMessage("TXT_APPLY_START_DATE"),  130, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
        	["END_DATE"     , resource.getMessage("TXT_APPLY_END_DATE")  ,  130, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
        	["STATUS_NAME"  , resource.getMessage("USE,ORNOT")           ,  90, "center", "center", true, false, null, null, null, 0, 0]
        ],
        header2 : [
        	["USER_ID"      , resource.getMessage("TXT_USER_ID")         ,  90, "center", "center", true, false, null, null, null, 0, 0],
            ["NAME"         , resource.getMessage("TXT_NAME_KOR")        , 180, "left"  , "center", true, false, null, null, null, 0, 0],
            ["EMP_NO"       , resource.getMessage("TXT_EMP_NO")          , 130, "center", "center", true, false, null, null, null, 0, 0],
            ["POSITION"     , resource.getMessage("POSIT")               ,  90, "center", "center", true, false, null, null, null, 0, 0],
            ["EMAIL"        , resource.getMessage("TXT_EMAIL")           , 170, "center", "center", true, false, null, null, null, 0, 0],
            ["CELL_PHONE_NO", resource.getMessage("CELPH")               , 150, "center", "center", true, false, null, null, null, 0, 0],
            ["MANAGER_NAME" , resource.getMessage("IN_CHARGE_TYPE")      ,  130, "center", "center", true, false, null, null, null, 0, 0],
            ["START_DATE"   , resource.getMessage("TXT_APPLY_START_DATE"),  130, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
        	["END_DATE"     , resource.getMessage("TXT_APPLY_END_DATE")  ,  130, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
        	["STATUS_NAME"  , resource.getMessage("USE,ORNOT")           ,  90, "center", "center", true, false, null, null, null, 0, 0],
        	["MAIL_SENDER_YN"  , resource.getMessage("AUTO,TXT_SEND_MAIL,APYDT") ,  130, "center", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("SM7003_01_grid_01");
            
            grid.init.setPage(true);
            grid.event.setOnDblClickRow(dg_1);
            
            if(SESSION.USER_ID == "fta") {
            	Theme.defaultGrid(dg_1, this.header2);
            } else {
            	Theme.defaultGrid(dg_1, this.header1);
            }
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_SM7003_01_grid_01 : function(rowData) {
            SM7003_01.dialog.openDialog('update');
        }
    },
    // 업무구현
    control : {
        selectUserList : function() {
            if (!form.handle.isValidate("SM7003_01_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("SM7003_01_grid_01");
            var params = form.handle.getElementsParam("SM7003_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7003_01/selectUserList", params);
        }
    },
    // 다이얼로그 구현
    dialog : {
        
        openDialog : function(flag) {
            var dg_1 = grid.getObject("SM7003_01_grid_01");
            var dl_1 = dialog.getObject("SM7003_02_dailog_01");
            
            if (flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("SM7003_01_form_01");
            var params2 = {};
            
            params1.flag = params1.flag || flag;
            params1.callPid = "SM7003_01";
            if (flag != "insert") {
                params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);
            
            dialog.init.setTitle(resource.getMessage("SM7003_02"));
            dialog.init.setWidth(1024);
            dialog.init.setHeight(312);
            dialog.init.setURL("/fm/sm/sm7003_02");
            dialog.init.setResizable(false);
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        }
    },
    // 파일 input 엘리먼트 구현
    file : {
        
    },
    // 엑셀다운로드 구현
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("SM7003_01_grid_01");
            var fobj = form.getObject("SM7003_01_form_01");
            
            form.init.setURL("/fm/sm/sm7003_01/selectUserList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("USER, TXT_LIST"), resource.getMessage("USER, TXT_LIST"));
        }
    }
    
};
