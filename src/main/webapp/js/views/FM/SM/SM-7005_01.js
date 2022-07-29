/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7005_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7005_01.init.initComponent();
}

var SM7005_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            SM7005_01.combobox.initCombo_1();
            SM7005_01.datagrid.initGrid_1();
        }
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {
        dataSearchType : [{CODE : "IF_CD", NAME : resource.getMessage("INF, CODE")}, {CODE : "IF_NAME", NAME : resource.getMessage("INF, NAME")}],
        initCombo_1 : function() {
            var objSearchType = combo.getObject("SM7005_01_form_01", "schKeyField");
            
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchType);
            
            var objSearchKeyLike = combo.getObject("SM7005_01_form_01", "schKeyLike");
            
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
        	["IF_CD"          , resource.getMessage("INF, CODE")             /*인터페이스 코드*/   , 120, "center"  , "center", true, false, null, null, null, 0, 0],
        	["IF_NAME"        , resource.getMessage("INF, NAME")             /*인터페이스 명*/    , 180, "left"  , "center", true, false, null, null, null, 0, 0],
        	["ITEM_TYPE_NAME" , resource.getMessage("TXT_ITM,TYP")           /*아이템타입*/     , 70, "center", "center", true, false, null, null, null, 0, 0],
        	["USING_YN_NAME"  , resource.getMessage("USE,ORNOT")             /*사용여부*/       , 80, "center", "center", true, false, null, null, null, 0, 0],
        	["REMARK"         , resource.getMessage("DSCPT")              /*설명*/     , 300, "left", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("SM7005_01_grid_01");
            
            grid.init.setPage(true);
            grid.init.setFitColumns(true);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_SM7005_01_grid_01 : function(rowData) {
            SM7005_01.dialog.openDialog('update');
        }
    },
    // 업무구현
    control : {
        selectInterfaceItemMstList : function() {
            if (!form.handle.isValidate("SM7005_01_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("SM7005_01_grid_01");
            var params = form.handle.getElementsParam("SM7005_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7005_01/selectInterfaceItemMstList", params);
        }
    },
    // 다이얼로그 구현
    dialog : {
        openDialog : function(flag, type) {
            var dg_1 = grid.getObject("SM7005_01_grid_01");
            var dl_1 = dialog.getObject("SM7005_02_dailog_01");
            
            if (flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var itemType = "";
            var params = new Object();
            var params1 = form.handle.getElementsParam("SM7005_01_form_01");
            var params2 = new Object();
            
            params1.flag = flag;
            if (flag != "insert") {
                params2 = grid.handle.getSelectedRowData(dg_1);
                itemType = params2.ITEM_TYPE;
            } else {
            	itemType = type;
            	params2.ITEM_TYPE = type;
            }
            
            $.extend(params, params1, params2);
            
            if(itemType == "P") {
                dl_1 = dialog.getObject("SM7005_04_dailog_01");
                
                dialog.init.setWidth(600);
                dialog.init.setHeight(255);
                dialog.init.setURL("/fm/sm/sm7005_04");
            } else if(itemType == "J") {
                dl_1 = dialog.getObject("SM7005_02_dailog_07");
                
                dialog.init.setWidth(600);
                dialog.init.setHeight(255);
                dialog.init.setURL("/fm/sm/sm7005_07");
            }  else if(itemType == "T") {
                dl_1 = dialog.getObject("SM7005_02_dailog_01");
                
                dialog.init.setWidth(1024);
                dialog.init.setHeight(768);
                dialog.init.setURL("/fm/sm/sm7005_02");
            } else if(itemType == "C") {
                dl_1 = dialog.getObject("SM7005_02_dailog_01");
                
                dialog.init.setWidth(1024);
                dialog.init.setHeight(768);
                dialog.init.setURL("/fm/sm/sm7005_02");
            } else if(itemType == "E") {
                dl_1 = dialog.getObject("SM7005_05_dailog_01");
                
                dialog.init.setWidth(900);
                dialog.init.setHeight(700);
                dialog.init.setURL("/fm/sm/sm7005_05");
            } else {
                alert(resource.getMessage("MSG_REQ_TR_TYPE"));
                return;
            }
            dialog.init.setResizable(false);
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        }
    },
    file : {
        
    },
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("SM7005_01_grid_01");
            var fobj = form.getObject("SM7005_01_form_01");
            
            form.init.setURL("/fm/sm/sm7005_01/selectInterfaceItemMstList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("INF,ITEMS, TXT_LIST"), resource.getMessage("INF,ITEMS, TXT_LIST"));
        }
    }
    
};
