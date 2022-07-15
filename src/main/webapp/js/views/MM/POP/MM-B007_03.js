/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B007_03
 * 작업일자 : 2021.03.14
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMB007_03.config.applyFtaNation();
    MMB007_03.init.initComponent();
}

var MMB007_03_modify = false;
var MMB007_03_changes = false;

var MMB007_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            MMB007_03.datagrid.initGrid_1();
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
    // 에디트 컴포넌트 생성
    editor : {
        gridSearchbox_1 : function(obj) {
            MMB007_03.dialog.openDialog_1("I");
        },
        gridSearchbox_2 : function(obj) {
            MMB007_03.dialog.openDialog_1("D");
        },
        gridSearchbox_3 : function(obj) {
            MMB007_03.dialog.openDialog_2();
        },
        gridCombo_1 : function(obj) {
            var opts = new Object;
            
            opts.queryParams = {COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"C_ORCY_DTRM_BASE_CD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."};
            
            opts.data = combo.util.getCacheData(opts.queryParams);
            if(oUtil.isNull(opts.data)) opts.url = "/mm/cbox/selectStandardCode";
            else opts.url = "";
            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            opts.panelWidth = "210";
            combo.util.putCacheData(opts, opts.queryParams);
            
            return opts;
        },
        gridCombo_2 : function(obj) {
            var opts = new Object;
            
            opts.queryParams = {COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"C_ORCY_INDC_EON_TPCD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."};
            opts.data = combo.util.getCacheData(opts.queryParams);
            if(oUtil.isNull(opts.data)) opts.url = "/mm/cbox/selectStandardCode";
            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            opts.panelWidth = "220";
            combo.util.putCacheData(opts, opts.queryParams);
            
            return opts;
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("MMB007_03_form_01");
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            
            grid.init.setURL("/mm/pop/MMB007_03/selectExportLneInputList");
            grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            // 에디팅 모드 적용
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(true); // 에디팅 컬럼이 아닌 경우, 흐른 필름을 위에 표시할지 여부
            grid.init.setEmptyMessage(false);
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
        updateExportLneInputInfo : function() {
            MMB007_03_modify = true;
            MMB007_03_changes = false;
            
            form.handle.setValue("MMB007_03_form_01", "gridData", "");
            
            MMB007_03.control.selectExportLneInputList();
            
            dialog.handle.close(dialog.getObject('MMB007_03_dailog_01'));
        },
        updateExportLneDealNetwght : function() {
            MMB007_03_modify = true;
            MMB007_03_changes = false;
            
            form.handle.setValue("MMB007_03_form_01", "gridData", "");
            
            MMB007_03.control.selectExportLneInputList();
        },
        updateExportLneDealName : function() {
            MMB007_03_modify = true;
            MMB007_03_changes = false;
            
            form.handle.setValue("MMB007_03_form_01", "gridData", "");
            
            MMB007_03.control.selectExportLneInputList();
        }
    },
    // 업무구현
    control : {
        selectExportLneInputList : function() {
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            var params = form.handle.getElementsParam("MMB007_03_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/MMB007_03/selectExportLneInputList", params);
        },
        updateExportLneInputInfo : function() {
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
            
            var allRows = grid.handle.getAllRows(dg_1);
            var warr = "";
                    
            // 신규등록하는지 체크함
            for(var i = 0; i < allRows.length; i++) {
                warr += MMB007_03.util.lenChecker(allRows   [i], i+1);
            }
            
            if(!oUtil.isNull(warr)) {
                $.messager.confirm(resource.getMessage("CNFIR"), "데이터 유효성 오류가 발생했습니다. 무시하시고 진행할 경우에는 [OK]를 클릭하세요.<br><br><textarea style=\"width:100%;height:120px;\">"+warr+"</textarea>", function(flag) {
                    if(flag) {
                        MMB007_03.control.saveExportLneInputInfo(allRows);
                    }
                }); 
            } else {
                MMB007_03.control.saveExportLneInputInfo(allRows);
            }
        },
        saveExportLneInputInfo : function(allRows) {
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {
                if (flag) {
                    var rowParam = grid.util.convertJson2Rows(allRows);
                    form.handle.setValue("MMB007_03_form_01", "gridData", rowParam);
                    
                    var obj = form.getObject("MMB007_03_form_01");
                    
                    form.init.setURL("/mm/pop/MMB007_03/updateExportLneInputInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateExportLneInputInfo");
                    
                    form.submit(obj);
                }
            });
        },
        updateExportLneDealNetwght : function(flags) {
            var totNetwght = form.handle.getValue("MMB007_03_form_01", "TOT_NETWGHT");
            
            if(oUtil.isNull(totNetwght)) {
                alert(resource.getMessage("CC_신고할 순중량의 총량을 입력해 주세요."));
                return;
            }
            
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
            
            var rowCnt = grid.handle.getRowCount(dg_1);
            var allRows = grid.handle.getAllRows(dg_1);
            var rowParam = grid.util.convertJson2Rows(allRows);
            
            // 란기준 안분
            if(flags == "Q") {
                var totQy = 0;
                
                // 전체 수량 구하기
                for(var i = 0; i < rowCnt; i++) {
                    totQy += allRows[i]["TOT_QY"];
                }
                
                if(oUtil.isNull(totQy) || totQy <= 0) {
                    alert(resource.getMessage("CC_입력된 수량이 없습니다."));
                    return;
                }
                
                form.handle.setValue("MMB007_03_form_01", "DCLRT_QY", totQy);
            } else if(flags == "P") {
                var pkgCo = 0;
                
                // 전체 수량 구하기
                for(var i = 0; i < rowCnt; i++) {
                    pkgCo += allRows[i]["PKNG_CO"];
                }
                
                if(oUtil.isNull(pkgCo) || pkgCo <= 0) {
                    alert(resource.getMessage("CC_입력된 포장갯수가 없습니다."));
                    return;
                }
                
                form.handle.setValue("MMB007_03_form_01", "DCLRT_PKNG_CO", pkgCo);
            } else if(flags == "A") {
                var totAmt = 0;
                
                // 전체 수량 구하기
                for(var i = 0; i < rowCnt; i++) {
                    totAmt += allRows[i]["SANC_AMT"];
                }
                
                if(oUtil.isNull(totAmt) || totAmt <= 0) {
                    alert(resource.getMessage("CC_입력된 결재금액이 없습니다."));
                    return;
                }
                
                form.handle.setValue("MMB007_03_form_01", "DCLRT_AMT", totAmt);
            }
            
            var msg = resource.getMessage("CC_순중량 안분을 실행하시겠습니까?");
            
            for(var i = 0; i < rowCnt; i++) {               
                if(allRows[i]["NETWGHT"] > 0) {
                    msg = "기 입력된 순중량이 존해하며 실행 후에는 재 계산된 값으로 변경됩니다.<br><br>그래도 " + msg;
                    break;
                }
            }
            
            $.messager.confirm(CNFIR, msg, function(flag) {
                if (flag) {
                    
                    form.handle.setValue("MMB007_03_form_01", "gridData", rowParam);
                    form.handle.setValue("MMB007_03_form_01", "PRPDVS_STDR", flags);
                    
                    var obj = form.getObject("MMB007_03_form_01");
                    
                    form.init.setURL("/mm/pop/MMB007_03/updateExportLneDealNetwght");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateExportLneDealNetwght");
                    
                    form.submit(obj);
                }
            });
        },
        updateExportLneDealName : function() {
            $.messager.confirm(CNFIR, resource.getMessage("CC_거래품목을 변경하시겠습니까?"), function(flag) {
                if (flag) {
                    var dg_1 = grid.getObject("MMB007_03_grid_01");
                    dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
                    
                    var chgs = grid.handle.getAllRows(dg_1); // 에디팅으로 인한 변경된 데이터만 추출하는 것에서 전체로 변경했음(2021-04-18)
                    var rowParam = grid.util.convertJson2Rows(chgs);
                    
                    form.handle.setValue("MMB007_03_form_01", "gridData", rowParam);
                    
                    var obj = form.getObject("MMB007_03_form_01");
                    
                    form.init.setURL("/mm/pop/MMB007_03/updateExportLneDealName");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateExportLneDealName");
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {
        openDialog_1 : function(stype) {
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var params = new Object();
            
            params.TARGET_PID = "MMB007_03";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.HS_CD = rowData.HS_CD;
            params.BCNC_CD = rowData.BCNC_CD;
            params.CC_TYPE = form.handle.getValue("MMB007_03_form_01", "CC_TYPE");
            params.SEARCH_TYPE = stype;
            
            MMB007_01_changes = true;
            
            var dl_1 = dialog.getObject("MMB007_02_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("CC_[f]업체 신고이력 조회"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(550);
            dialog.init.setHeight(500);
            dialog.init.setURL("/mm/pop/MMB007_02");
            
            dialog.open(dl_1);
        },
        callByMMB007_01 : function(data) {
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            
            var stype = form.handle.getValue("MMB007_02_form_01", "SEARCH_TYPE");
            
            if(stype == "D") rowData.DEAL_PRDNM = data.PRDNM;
            else rowData.PRDNM = data.PRDNM;
            
            grid.handle.setChangeRowValue(dg_1, idx, rowData);
            
            dialog.handle.close(dialog.getObject('MMB007_02_dailog_01'));
        },
        closeThisDialog : function() {
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            var rows = grid.handle.getAllRows(dg_1);
            var warr = "";
                    
            // 신규등록하는지 체크함
            for(var i = 0; i < rows.length; i++) {
                warr += MMB007_03.util.lenChecker(rows[i], i+1);
            }
            
            if(!oUtil.isNull(warr)) {
                $.messager.confirm(resource.getMessage("CNFIR"), "데이터 유효성 오류가 발생했습니다. 무시하시고 진행할 경우에는 [OK]를 클릭하세요.<br><br><textarea style=\"width:100%;height:120px;\">"+warr+"</textarea>", function(flag) {
                    if(flag) {
                        MMB007_03.dialog.dialogClose();
                    }
                }); 
            } else {
                MMB007_03.dialog.dialogClose();
            }
        },
        dialogClose : function() {
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            dg_1.datagrid('endCellEdit'); // 에디팅 모드 해제
            
            var chgCnt = grid.handle.getChangesCount(dg_1);
            
            if(chgCnt > 0 || MMB007_03_changes) {
                $.messager.confirm(CNFIR, resource.getMessage("CC_란입력 목록에 변경된 데이터가 있습니다.<br>저장하지 않고 진행할 경우 OK버튼을 클릭하세요.<br><br>창을 닫으시겠습니다?"), function(flag) {
                    if (flag) {
                        if(MMB007_03_modify) EC2004_01.control.selectExportOnetapMstInfo();
                        
                        dialog.handle.close(dialog.getObject('MMB007_03_dailog_01'));
                    }
                });
            } else {
                if(MMB007_03_modify) EC2004_01.control.selectExportOnetapMstInfo();
                
                dialog.handle.close(dialog.getObject('MMB007_03_dailog_01'));
            }
        },
        openDialog_2 : function() {
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(oUtil.isNull(rowData.ORPL)) {
                alert(resource.getMessage("CC_원산지 입력 후 검색이 가능합니다."));
                return;
            }
            
            var params = new Object();
            
            params.TARGET_PID  = "MMB007_03";
            params.COMPANY_CD  = SESSION.COMPANY_CD
            params.CATEGORY_CD = "CC_M_NATION_MULT_FTA";
            params.schKeyField = "CODE_NAME";
            params.schKeyWord = rowData.ORPL;
            params.schKeyLike = "Q";
            
            var dl_1 = dialog.getObject("MMA015_03_01_dailog_01");   
            
            dialog.init.setTitle(resource.getMessage("MMA015_03"));
            dialog.init.setURL("/mm/pop/mmA015_03");
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(450);
            dialog.init.setHeight(500);
            
            dialog.open(dl_1);
        },
        callByMMA015_03 : function(data) {
            var dg_1 = grid.getObject("MMB007_03_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var idx = grid.handle.getCurrentRowIndex(dg_1);
            
            rowData.CC_NATION_AGM_CD = data.TXT_VALUE1;
            
            grid.handle.setChangeRowValue(dg_1, idx, rowData);
            
            dialog.handle.close(dialog.getObject('MMA015_03_01_dailog_01'));
        }
    },
    // 화면 UI 변경
    ui : {},
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    util : {
        lenChecker : function(data, rownum) {
            var warr = "";
            
            // 유효성 경고메시지 체크
            if(!oUtil.isNull(data.PRDNM)) if(data.PRDNM.length > 50) warr += rownum+"번째열의 품명이 "+data.PRDNM.length+"자로 최대 문자수(50자)를 초과했습니다.\n";
            if(!oUtil.isNull(data.DEAL_PRDNM)) if(data.DEAL_PRDNM.length > 50) warr += rownum+"번째열의 거래품명이 "+data.DEAL_PRDNM.length+"자로 최대 문자수(50자)를 초과했습니다.\n";
            
            return warr;
        } 
    },
    // 화면 초기 설정
    config : {
        applyFtaNation : function() {}
    }
    
};
