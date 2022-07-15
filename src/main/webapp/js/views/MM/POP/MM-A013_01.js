/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A013_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA013_01.init.initComponent();
}

var MMA013_01 = {
    init : {
        initComponent : function() {
            MMA013_01.calendar.initCalendar_1();
            MMA013_01.combobox.initCombo_1();
            MMA013_01.datagrid.initGrid_1();
        }
    },
    calendar : {
        initCalendar_1 : function() {
        },
    },
    combobox : {
        dataSearchType : [{CODE : "FTA_CD", NAME : resource.getMessage("AGRET,CODE")}, {CODE : "FTA_NAME", NAME : resource.getMessage("AGRET,NAME")}],
        initCombo_1 : function() {
            
            var vCompanyCd = form.handle.getValue("MMA013_01_form_01", "COMPANY_CD");
            
            var objSearchType = combo.getObject("MMA013_01_form_01", "schKeyField");
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.create(objSearchType);
            
            var objSearchKeyLike = combo.getObject("MMA013_01_form_01", "schKeyLike");
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.create(objSearchKeyLike);
        }
    },
    datagrid : {
        header : [
          // Field ID | Field Name | Width |  Align  | hAlign | Sort Y/N | hidden | edit type | chbox | style | rowspan | colspan
          ["FTA_CD"  , resource.getMessage("AGRET,CODE")/*사업부 코드*/ , 120, "center", "center", true, false, null, null, null, 0, 0],
          ["FTA_NAME", resource.getMessage("TXT_FTA_CD_NM")/*사업부 명*/, 250, "left"  , "center", true, false, null, null, null, 0, 0],
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("MMA013_01_grid_01");
            
            grid.init.setPage(true);
            grid.init.setFitColumns(true);
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA013_01_grid_01 : function(rowData) {
            var pid = form.handle.getValue("MMA013_01_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA013_01"]) == "function") {
                    pro_id["callByMMA013_01"](rowData);
                }
            }
        }
    },
    control : {// 업무구현
        selectMainList : function() {
            // 검색창의 validation 체크결과를 비교한다.
            if (!form.handle.isValidate("MMA013_01_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("MMA013_01_grid_01");
            var params = form.handle.getElementsParam("MMA013_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA013_01/selectMainList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA013_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            //for (key in rowData) {
            //    console.log("key:"+key+",value:"+rowData[key]);
            //}
            
            if (!grid.handle.isSelected(dg_1)) {
                // 그리드에서 열을 선택해 주세요.
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
            }
            
            MMA013_01.event.onDblClick_MMA013_01_grid_01(rowData);
        }
    },
    dialog : {
        // 다이얼로그 구현
    },
    file : {
        // 파일 input 엘리먼트 구현
    },
    excel : {
        // 엑셀다운로드 구현
    }
    
};
