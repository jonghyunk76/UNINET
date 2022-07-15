/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7005_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7005_02.config.applyFtaNation();
    SM7005_02.init.initComponent();
}

var SM7005_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            var vFlag = nullToString(form.handle.getValue("SM7005_02_form_01", "flag"));
            
            SM7005_02.calendar.initCalendar_1();
            SM7005_02.combobox.initCombo_1();
            SM7005_02.combobox.initCombo_2();
            
            SM7005_02.datagrid.initGrid_1();
            
            // ui변경
            if (vFlag == "view") {
                SM7005_02.ui.viewChangeUI();
            } else if (vFlag == "update") {
                SM7005_02.ui.updateChangeUI();
            } else {
                SM7005_02.ui.insertChangeUI();
            }
            
            if(vFlag != "insert") {
            	SM7005_02.control.selectInterfaceItemMstInfo();
            }
        }
    }, 
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {
        initCombo_1 : function() {
            var objUsingYn = combo.getObject("SM7005_02_form_01", "USING_YN");
            
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN"});
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(objUsingYn);
        },
        initCombo_2 : function() {
            var objUsingYn = combo.getObject("SM7005_02_form_01", "FILE_TYPE");
            
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"IF_CATEGORY"});
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(objUsingYn);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [
        	["FROM_COL_ID", resource.getMessage("ORIGIN,TB, TXT_COLUMN") /*원천테이블 컬럼*/, 180, "left"  , "center", true, false, null, null, null, 0, 0],
        	["TO_COL_ID"  , resource.getMessage("TARGT,TB, TXT_COLUMN")  /*대상테이블 컬럼*/, 180, "left"  , "center", true, false, null, null, null, 0, 0],
        	["COL_NAME"  ,  resource.getMessage("TXT_COLUMN, NAME")  /*대상테이블 컬럼*/,     150, "left"  , "center", true, false, null, null, null, 0, 0],
        	["REQUIRED_YN", resource.getMessage("REQUD,ORNOT")           /*필수여부*/       , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["DATA_TYPE"  , resource.getMessage("DTA, TYP")              /*데이터 타입*/    , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["DATA_LENGTH", resource.getMessage("DTA, LENG")             /*데이터 길이*/    , 100, "right" , "center", true, false, null, null, {format:'int'}, 0, 0],
        	["DATA_FORMAT", resource.getMessage("DTA, FOMAT")            /*데이터 포맷*/    , 120, "center", "center", true, false, null, null, null, 0, 0],
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("SM7005_02_form_01");
            var dg_1 = grid.getObject("SM7005_02_grid_01");
            
            grid.init.setPage(true);
            grid.init.setURL("/fm/sm/sm7005_02/selectInterfaceItemDtlList");
            grid.init.setQueryParams(params);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            
            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
        selectAfterUI : function(data) {
            form.handle.loadData("SM7005_02_form_01", data);
        },
        updateAfterUI : function(data) {
            var oMap = null;
            
            try {
                oMap = data.dataMap.map;    
            } catch (e) {
                oMap = {};
            }
            
            var vFlag = nullToString(form.handle.getValue("SM7005_02_form_01", "flag"));
            
            if (vFlag.indexOf("insert") > -1) {
                form.handle.setValue("SM7005_02_form_01", "flag", "update");
                form.handle.setValue("SM7005_02_form_01", "INTERFACE_ITEM_MST_ID", oMap.INTERFACE_ITEM_MST_ID);
            }
            
            SM7005_02.ui.updateChangeUI();
            SM7005_01.control.selectInterfaceItemMstList();
        },
        deleteAfterUI : function(data) {
            SM7005_01.control.selectInterfaceItemMstList();
            
            var dl_1 = dialog.getObject("SM7005_02_dailog_01");
            dialog.handle.close(dl_1);
        },
        onDblClick_SM7005_02_grid_01 : function(rowData) {
            SM7005_02.dialog.openDialog('update');
        },
    },
    // 업무구현
    control : {
        selectInterfaceItemMstInfo : function() {
            var obj = form.getObject("SM7005_02_form_01");
            
            form.init.setURL("/fm/sm/sm7005_02/selectInterfaceItemMstInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        // insert/update 수행
        updateInterfaceItemMstInfo : function() {
            if (!form.handle.isValidate("SM7005_02_form_01")) return;
            
            var confMsg = "";
            
            var vFlag = nullToString(form.handle.getValue("SM7005_02_form_01", "flag"));
            
            if (vFlag.indexOf("insert") > -1) {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                
                if (flag) {
                    var obj = form.getObject("SM7005_02_form_01");
                    
                    var vFlag = nullToString(form.handle.getValue("SM7005_02_form_01", "flag"));
                    
                    if (vFlag.indexOf("insert") > -1) {
                        form.init.setURL("/fm/sm/sm7005_02/insertInterfaceItemMstInfo");
                    }
                    if (vFlag == "update") {
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
            var vFlag = form.handle.getValue("SM7005_02_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7005_02_form_01");
                    
                    form.init.setURL("/fm/sm/sm7005_02/deleteInterfaceItemMstInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        insertInterfaceItemDtl : function() {
            if (!form.handle.isValidate("SM7005_02_form_01")) return;
            
            var dg_1 = grid.getObject("SM7005_02_grid_01");
            var rows = grid.handle.getAllRows(dg_1, "1"); // 조회된 모든 데이터(필터와 무관함)
            
            if(rows.length == 0) {
                alert(resource.getMessage("MSG_NOT_EXIST_DATA"));
                return;
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {
                if (flag) {
                    var rowParam = grid.util.convertJson2Rows(rows);
                    
                    form.handle.setValue("SM7005_02_form_01", "gridData", rowParam);
                    
                    var obj = form.getObject("SM7005_02_form_01");
                    
                    form.init.setURL("/fm/sm/sm7005_03/insertInterfaceItemDtlInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    
                    form.submit(obj);
                }
            });
        },
        selectInterfaceItemDtlList : function() {
            if (!form.handle.isValidate("SM7005_02_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("SM7005_02_grid_01");
            var params = form.handle.getElementsParam("SM7005_02_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7005_02/selectInterfaceItemDtlList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("SM7005_02_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if (!grid.handle.isSelected(dg_1)) {
                // 그리드에서 열을 선택해 주세요.
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
            }
            
            SM7005_02.event.onDblClick_SM7005_02_grid_01(rowData);
        },
        validate : function(pType) {
            
            var vCompanyCd = form.handle.getValue("SM7005_02_form_01", "COMPANY_CD");
            var vIfCode = form.handle.getValue("SM7005_02_form_01", "IF_CD");
            if (oUtil.isNull(vCompanyCd)) {
                // 회사코드를 찾을 수 없습니다.
                alert(resource.getMessage("MSG_NOT_FOUND_COMPANY_CD"));
                return false;
            }
            
            if (pType.indexOf("insert") <= -1) {
                if (oUtil.isNull(vIfCode)) {
                    // 인터페이스 코드를 찾을 수 없습니다.
                    alert(resource.getMessage("MSG_NOT_FOUND_IF_CODE"));
                    return false;
                }
            }
            
            return true;
        },
        pasteColumn : function() {
            var obj = form.getObject("SM7005_02_form_00", "PASTE_TEXT1");
            
            if(isIE()) {
                alert(resource.getMessage("TXT_SORRY_EXPLORER_10"));
            } else {
                obj.focus();
                obj.trigger("paste"); // 붙여넣기 이벤트 생성
            }
        }
    },
    // 다이얼로그 구현
    dialog : {
        openDialog : function(flag) {
            var dg_1 = grid.getObject("SM7005_02_grid_01");
            var dl_1 = dialog.getObject("SM7005_03_dailog_01");
            
            var vInterfaceItemMstId = form.handle.getValue("SM7005_02_form_01", "INTERFACE_ITEM_MST_ID");
            var vIfCode = form.handle.getValue("SM7005_02_form_01", "IF_CD");
            var vIfName = form.handle.getValue("SM7005_02_form_01", "IF_NAME");
            var vSourceTable = form.handle.getValue("SM7005_02_form_01", "SOURCE_TABLE");
            var vTargetTable = form.handle.getValue("SM7005_02_form_01", "TARGET_TABLE");
            
            if (oUtil.isNull(vInterfaceItemMstId)) {
                // 인터페이스 ID를 찾을 수 없습니다
                alert(resource.getMessage("MSG_NOT_FOUND_IF_ID"));
                return;
            }
            
            if (oUtil.isNull(vIfCode)) {
             // 인터페이스 코드를 찾을 수 없습니다
                alert(resource.getMessage("MSG_NOT_FOUND_IF_CODE"));
                return;
            }
            
            if (oUtil.isNull(vIfName)) {
                // 인터페이스 명을 찾을 수 없습니다
                alert(resource.getMessage("MSG_NOT_FOUND_IF_NAME"));
                return;
            }
            
            if (oUtil.isNull(vSourceTable)) {
                // 원천테이블 명을 찾을 수 없습니다
                alert(resource.getMessage("MSG_NOT_FOUND_SOURCE_TABLE_NAME"));
                return;
            }
            
            if (oUtil.isNull(vTargetTable)) {
                // 대상테이블 명을 찾을 수 없습니다
                alert(resource.getMessage("MSG_NOT_FOUND_TARGET_TABLE_NAME"));
                return;
            }
            
            var vFlag = nullToString(flag);
            
            if (vFlag.indexOf("insert") == -1 && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("SM7005_02_form_01");
            var params2 = {};
            
            params1.flag = vFlag;
            if (vFlag.indexOf("insert") == -1) {
                params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);
            
            // 추가파라미터 설정
            params.INTERFACE_ITEM_MST_ID = params.INTERFACE_ITEM_MST_ID || vInterfaceItemMstId;
            params.IF_CD = params.IF_CD || vIfCode;
            params.IF_NAME = params.IF_NAME || vIfName;   
            params.SOURCE_TABLE = params.SOURCE_TABLE || vSourceTable;
            params.TARGET_TABLE = params.TARGET_TABLE || vTargetTable;
            
            // 팝업 셋팅
            // title:인터페이스항목등록
            //dialog.init.setTitle(resource.getMessage("TXT_IF_ITEM_REGER"));
            dialog.init.setTitle(resource.getMessage("SM7005_03"));
            dialog.init.setWidth(800);
            dialog.init.setHeight(252);
            dialog.init.setURL("/fm/sm/sm7005_03");
            dialog.init.setResizable(true);
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
        insertChangeUI : function() {
            // 삭제버튼감춤
            form.util.setVisible("SM7005_02_btn_02", false);
            
            // 그리드관련 버튼 비활성화
            form.util.setVisible("SM7005_02_btn_03", false);
            form.util.setVisible("SM7005_02_btn_04", false);
            form.util.setVisible("SM7005_02_btn_07", false);
        },
        updateChangeUI : function() {
            // 삭제버튼보임
            form.util.setVisible("SM7005_02_btn_02", true);
            
            // key가 되는 input은 모두 막는다.
            form.handle.readonly("SM7005_02_form_01", "IF_CD", true);
            form.handle.addClass("SM7005_02_form_01", "IF_CD", "input_readOnly");
            
            // 그리드관련 버튼 활성화
            form.util.setVisible("SM7005_02_btn_03", true);
            form.util.setVisible("SM7005_02_btn_04", true);
            form.util.setVisible("SM7005_02_btn_07", true);
        },
        viewChangeUI : function() {
            
            //dialog.handle.resize("SM7005_02_dailog_01", 1024, 768);
            
            // 버튼감싸는영역
            form.util.setVisible("SM7005_02_div_01", false);
            
            // 그리드관련 버튼 비활성화
            form.util.setVisible("SM7005_02_btn_03", false);
            form.util.setVisible("SM7005_02_btn_04", false);
            form.util.setVisible("SM7005_02_btn_07", false);
            
            // form안 모든 element에 대해서 label과 같은 형식으로 표시한다.
            form.handle.closeAll("SM7005_02_form_01");
        },
        drowSetData : function() {
            var pval = form.handle.getValue("SM7005_02_form_00", "PASTE_TEXT1");

            try {
                var dg_1 = grid.getObject("SM7005_02_grid_01");
                var datas;
                
                if(pval.startsWith("[") && pval.endsWith["]"]) { // json타입으로 인식
                    datas = eval(pval);
                } else { // 스페이스로 구분된 텍스트로 인식
                    var csvarray = [];
                    var rows = pval.split('\n');
                    
                    for(var i = 0; i < rows.length; i++){
                        csvarray.push(rows[i].split('\t'));
                    }
                    
                    datas = csvarray;
                }
                
                grid.handle.clearAll(dg_1);
                
                // 이스케이프된 문자를 복원시킨다.
                if(datas.length > 0) {
                    var hdata = SM7005_02.util.getHeaderData(datas);
                    
                    dg_1.datagrid('loadData', hdata);
                    
                    form.util.setVisible("SM7005_02_btn_08", true);
                }
            } catch(e) {
                alert(resource.getMessage("NOT_READ_DATA") + " >> " + e);
            }
        }
    },
    // 파일 input 엘리먼트 구현
    file : {
        
    },
    // 엑셀다운로드 구현
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("SM7005_02_grid_01");
            var fobj = form.getObject("SM7005_02_form_01");
            
            form.init.setURL("/fm/sm/sm7005_02/selectInterfaceItemDtlList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("INF,ITEMS, TXT_LIST"), resource.getMessage("INF,ITEMS, TXT_LIST"));
        }
    },
    // 화면 초기 설정
    config : {
        applyFtaNation : function() {
            var obj1 = form.getObject("SM7005_02_form_00", "PASTE_TEXT1");
            
            obj1.bind('paste', function(e) {
                navigator.clipboard.readText().then(clipText => form.handle.setValue("SM7005_02_form_00", "PASTE_TEXT1", clipText));
            });
            
            obj1.bind('propertychange change keyup paste input', function() {
                setTimeout(function() {
                    SM7005_02.ui.drowSetData();
                }, 500);
            });
        }
    },
    util : {
        getHeaderData : function(datas) {
            var hdata = new Array();
            
            for(var i in datas) {
                var hcell = new Object();
                var existsData = false;
                
                for(var k in datas[i]) {
                    var indata = datas[i][k];
                    
                    if(oUtil.isNull(indata)) {
                        indata = "";
                    } else if(typeof indata === "object") {
                        indata = JSON.stringify(indata);
                        existsData = true;
                    } else {
                        indata = indata.toString();
                        existsData = true;
                    }
                    
                    if(k == 0) hcell.FROM_COL_ID     = indata; 
                    if(k == 1) hcell.TO_COL_ID     = indata;
                    if(k == 2) hcell.COL_NAME     = indata;
                    if(k == 3) hcell.REQUIRED_YN     = indata;
                    if(k == 4) hcell.DATA_TYPE    = indata;
                    if(k == 5) hcell.DATA_LENGTH  = indata;
                    if(k == 6) hcell.DATA_FORMAT    = indata;
                }
                
                if(existsData == true) hdata.push(hcell);
            }
            
            return hdata;
        }
    }
};
