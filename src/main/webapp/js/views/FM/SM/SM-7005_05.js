/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : SM7005_05
 * 작업일자 : 2021.03.25
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7005_05.config.applyFtaNation();
	SM7005_05.init.initComponent();
}

var SM7005_05 = {
    
    // 초기값 설정
    init : {
    	initComponent : function() {
        	var vFlag = form.handle.getValue("SM7005_05_form_01", "flag");
        	
        	SM7005_05.combobox.initCombo_1();
        	SM7005_05.combobox.initCombo_2();
        	SM7005_05.combobox.initCombo_3();
        	SM7005_05.combobox.initCombo_4();
        	
        	SM7005_05.datagrid.initGrid_1();
        	
            if (vFlag == "view") {
            	SM7005_05.ui.viewChangeUI();
            } else if (vFlag == "update") {
            	SM7005_05.ui.updateChangeUI();
            } else {
            	SM7005_05.ui.insertChangeUI();
            }
            
            if(vFlag != "insert") {
            	SM7005_05.control.selectInterfaceItemMstInfo();
            }
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
        initCombo_1 : function() {
            var obj = combo.getObject("SM7005_05_form_01", "FILE_TYPE");
            
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"IF_CATEGORY"});
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        },
        initCombo_2 : function() {
            var objIfCd = combo.getObject("SM7005_05_form_01", "TARGET_TABLE");

            combo.init.setURL("/fm/sm/sm7005_01/selectInterfaceItemMstCombo");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, FIRST_ROW:"UNCHOOSE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setHeight(250);
            combo.init.setRequired(false);
            
            combo.create(objIfCd);
        },
        initCombo_3 : function() {
            var obj = combo.getObject("SM7005_05_form_01", "USING_YN");
            
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN"});
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        },
        initCombo_4 : function() {
            var obj = combo.getObject("SM7005_05_form_01", "FILE_STATUS");
            
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN"});
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	header : [
        	["FROM_COL_ID"  , resource.getMessage("TXT_COLUMN_NAME")       /*대상테이블 컬럼*/, 180, "left"  , "center", false, false, {type:'text'}, null, null, 0, 0],
        	["TO_COL_ID"    , resource.getMessage("EXCEL_HEADER_NAME")      /*필드명*/,      150, "left"  , "center", false, false, {type:'text'}, null, null, 0, 0],
        	["COL_SEQ"      , resource.getMessage("INDEX_SEQNM")           /*표시순번*/,     100, "right"  , "center", false, false, {type:'text'}, null, null, 0, 0],
        	["REQUIRED_YN"  , resource.getMessage("REQUD,ORNOT")+"(Y/N)"   /*필수여부*/    , 100, "center", "center", false, false, {type:'text'}, null, null, 0, 0],
        	["DATA_TYPE"    , resource.getMessage("DTA, TYP")              /*데이터 타입*/    , 100, "center", "center", false, false, {type:'text'}, null, null, 0, 0],
        	["DATA_LENGTH"  , resource.getMessage("DTA, LENG")             /*데이터 길이*/    , 100, "right" , "center", false, false, {type:'text'}, null, null, 0, 0],
        	["DATA_FORMAT"  , resource.getMessage("DTA, FOMAT")            /*데이터 포맷*/    , 120, "center", "center", false, false, {type:'text'}, null, null, 0, 0]
        ],
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("SM7005_05_grid_01");
    		
    		grid.init.setPage(false);
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(true);
            grid.init.setSingleSelect(true);
            //grid.init.setNextFocus(focusObj); // 다음으로 이동할 커서를 지정한다.
            grid.init.setCallBackFunction("enableEditCell"); // 조회 시 데이터가 있으면 에디팅할 셀을 활성화 시킴
            
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, this.header);
        }
    },
    // 이벤트 처리
    event : {
    	selectAfterUI : function(data) {
            form.handle.loadData("SM7005_05_form_01", data);
            
            SM7005_05.control.selectInterfaceItemDtlList();
        },
        updateAfterUI : function(data) {
            var oMap = null;
            
            try {
                oMap = data.dataMap.map;    
            } catch (e) {
                oMap = {};
            }
            
            var vFlag = nullToString(form.handle.getValue("SM7005_05_form_01", "flag"));
            
            if (vFlag.indexOf("insert") > -1) {
                form.handle.setValue("SM7005_05_form_01", "flag", "update");
                form.handle.setValue("SM7005_05_form_01", "INTERFACE_ITEM_MST_ID", oMap.INTERFACE_ITEM_MST_ID);
            }
            
            SM7005_05.ui.updateChangeUI();
            SM7005_01.control.selectInterfaceItemMstList();
        },
        deleteAfterUI : function(data) {
            SM7005_01.control.selectInterfaceItemMstList();
            
            var dl_1 = dialog.getObject("SM7005_05_dailog_01");
            dialog.handle.close(dl_1);
        }
    },
    // 업무구현
    control : {
    	selectInterfaceItemMstInfo : function() {
            var obj = form.getObject("SM7005_05_form_01");
            
            form.init.setURL("/fm/sm/sm7005_02/selectInterfaceItemMstInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectInterfaceItemDtlList : function() {
            if (!form.handle.isValidate("SM7005_05_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("SM7005_05_grid_01");
            var params = form.handle.getElementsParam("SM7005_05_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7005_02/selectInterfaceItemDtlList", params);
        },
        updateInterfaceItemMstInfo : function() {
            
            if (!form.handle.isValidate("SM7005_05_form_01")) return;
            var confMsg = "";
            
            var vFlag = nullToString(form.handle.getValue("SM7005_05_form_01", "flag"));
            
            if (vFlag.indexOf("insert") > -1) {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            if(vFlag == "update" && form.handle.isChecked("SM7005_05_form_01", "CREATE_TBL_YN")) {
            	confMsg += "<br><br><br><font color='red'>"+resource.getMessage("TXT_SUBMIT_CONFIRM")+"</font>";
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                	var dg_1 = grid.getObject("SM7005_05_grid_01");
                	var rows = grid.handle.getAllRows(dg_1);
                	var rowParam = grid.util.convertJson2Rows(rows);
                	
                	form.handle.setValue("SM7005_05_form_01", "gridData", rowParam);
                	
                	var obj = form.getObject("SM7005_05_form_01");
                    var vFlag = nullToString(form.handle.getValue("SM7005_05_form_01", "flag"));
                    
                    if(vFlag.indexOf("insert") > -1) {
                        form.init.setURL("/fm/sm/sm7005_02/insertInterfaceItemMstInfo");
                    }
                    if(vFlag == "update") {
                        form.init.setURL("/fm/sm/sm7005_02/updateInterfaceItemMstInfo");
                    }
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        // delete 수행
        deleteInterfaceItemMstInfo : function() {
            var vFlag = form.handle.getValue("SM7005_05_form_01", "flag");
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7005_05_form_01");
                    
                    form.init.setURL("/fm/sm/sm7005_02/deleteInterfaceItemMstInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	insertChangeUI : function() {
            // 삭제버튼감춤
            form.util.setVisible("SM7005_05_btn_02", false);
            form.handle.setChecked("SM7005_05_form_01", "CREATE_TBL_YN", true);
        },
        updateChangeUI : function() {
            // 삭제버튼보임
            form.util.setVisible("SM7005_05_btn_02", true);
            
            // key가 되는 input은 모두 막는다.
            form.handle.readonly("SM7005_05_form_01", "IF_CD", true);
            form.handle.addClass("SM7005_05_form_01", "IF_CD", "input_readOnly");
            form.handle.setChecked("SM7005_05_form_01", "CREATE_TBL_YN", false);
        },
        viewChangeUI : function() {
            // 버튼감싸는영역
            form.util.setVisible("SM7005_05_div_01", false);
            
            // form안 모든 element에 대해서 label과 같은 형식으로 표시한다.
            form.handle.closeAll("SM7005_05_form_01");
        },
        insertRow : function() {
            var dg_1 = grid.getObject("SM7005_05_grid_01");
            var rowCnt = grid.handle.getRowCount(dg_1);
        	var data = SM7005_05.util.getInitRowData(rowCnt+1);
        	
        	grid.handle.appendRow(dg_1, data);
        },
        deleteRow : function() {
        	var dg_1 = grid.getObject("SM7005_05_grid_01");
        	
        	if(!grid.handle.isSelected(dg_1)) {
        		alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	var idx = grid.handle.getCurrentRowIndex(dg_1);
        	
        	grid.handle.removeRow(dg_1, idx);
        }
    }, 
    // 검색박스 구현
    searchbox : {},
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    util : {
    	getInitRowData : function(idx) {
    		var dataType = "";
    		
    		if(DBMS_TYPE == "oracle") {
    			dataType = "nvarchar2";
    		} else if(DBMS_TYPE == "mssql") {
    			dataType = "nvarchar";
    		} else if(DBMS_TYPE == "postgresql") {
    			dataType = "varchar";
    		}
    			
    		var hcell = new Object();
    		
    		hcell.FROM_COL_ID   = "COLUMN_"+idx; 
        	hcell.TO_COL_ID     = "NAME"+idx;
        	hcell.COL_SEQ       = idx;
        	hcell.REQUIRED_YN   = "N";
        	hcell.DATA_TYPE     = dataType;
        	hcell.CELL_SORTABLE = "true";
        	hcell.DATA_LENGTH   = "200";
        	hcell.DATA_FORMAT   = null;
        	
        	return hcell;
    	}
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
