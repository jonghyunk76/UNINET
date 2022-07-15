/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7005_03
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7005_03.init.initComponent();
}

var SM7005_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            
            var flag = form.handle.getValue("SM7005_03_form_01", "flag");

            // 초기화면에 보여줄 데이터 조회
            if (flag != "insert") {
                SM7005_03.control.selectInterfaceItemDtlInfo();
            }
            
            SM7005_03.calendar.initCalendar_1();
            SM7005_03.combobox.initCombo_1();
            SM7005_03.datagrid.initGrid_1();
            
            // ui변경
            if (flag == "view") {
                SM7005_03.ui.viewChangeUI();
            } else if (flag == "update") {
                SM7005_03.ui.updateChangeUI();
            } else {
                SM7005_03.ui.insertChangeUI();
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
            var vCompanyCd = form.handle.getValue("SM7005_03_form_01", "COMPANY_CD") 
            
            var objUsingYn = combo.getObject("SM7005_03_form_01", "REQUIRED_YN");
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, COMPANY_CD:vCompanyCd, CATEGORY_CD:"YN"});
            //combo.init.setValue("100000");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            //combo.init.setHeight(200);
            combo.create(objUsingYn);
            
            var objUsingYn = combo.getObject("SM7005_03_form_01", "DATA_TYPE");
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, COMPANY_CD:vCompanyCd, CATEGORY_CD:"DATA_TYPE"});
            //combo.init.setValue("100000");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            //combo.init.setHeight(200);
            combo.init.setCallFunction("onChangeDataType");
            combo.create(objUsingYn);
            
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [],
        initGrid_1 : function() {
        }
    },
    // 이벤트 처리
    event : {
        // 상세 조회가 성공적으로 완료될 경우 호출되는 함수
        selectAfterUI : function(data) {
            // selectMessgeDetail으로 부터 조회된 데이터를 form에 자동 입력함(input id와 name비교)
            form.handle.loadData("SM7005_03_form_01", data);
        },
        // 성공적으로 요청처리가 완료될 경우 호출되는 함수
        updateAfterUI : function(data) {
            
            var oMap = null;
            try {
                oMap = data.dataMap.map;    
            } catch (e) {
                oMap = {};
            }
            
            // flag가 "update"인 경우, 초기화면 로딩 시 입력란을 컨트롤 함
            if (form.handle.getValue("SM7005_03_form_01", "flag") == "insert") {
                // update 상태로 변경한다.
                form.handle.setValue("SM7005_03_form_01", "flag", "update");
                
                // 등록시 생성된 KEY값 설정
                form.handle.setValue("SM7005_03_form_01", "INTERFACE_ITEM_DTL_ID", oMap.INTERFACE_ITEM_DTL_ID);
                form.handle.setValue("SM7005_03_form_01", "COL_SEQ", oMap.COL_SEQ);
            }
            
            // 업데이트 상태로 UI를 변경한다.
            SM7005_03.ui.updateChangeUI();
            
            // 부모조회
            SM7005_02.control.selectInterfaceItemDtlList();
            
            //var dl_1 = dialog.getObject("SM7005_03_dailog_01");
            //dialog.handle.close(dl_1);
        },
        deleteAfterUI : function(data) {
            // 부모조회
            SM7005_02.control.selectInterfaceItemDtlList();
            
            var dl_1 = dialog.getObject("SM7005_03_dailog_01");
            dialog.handle.close(dl_1);;
        },
        onChangeDataType : function(data) {
            //alert("11 : " + form.handle.getValue("SM7005_03_form_01", "DATA_TYPE"));
        }
    },
    // 업무구현
    control : {
        selectInterfaceItemDtlInfo : function() {
            var obj = form.getObject("SM7005_03_form_01");
            
            form.init.setURL("/fm/sm/sm7005_03/selectInterfaceItemDtlInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            // 성공과 실패에 대한 메시지 안 뜨게 함(추후엔 공통에서 처리할 것임)
            form.init.setSucceseMessage("");
            form.init.setFailMessage("");
            
            form.submit(obj);
        },
        // insert/update 수행
        updateInterfaceItemMstInfo : function() {
            
            if (!form.handle.isValidate("SM7005_03_form_01")) return;
            
            var vFlag = form.handle.getValue("SM7005_03_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            var confMsg = "";
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                
                if (flag) {
                    var obj = form.getObject("SM7005_03_form_01");
                    
                    if (form.handle.getValue("SM7005_03_form_01", "flag") == "insert") {
                        form.init.setURL("/fm/sm/sm7005_03/insertInterfaceItemDtlInfo");
                    }
                    if (form.handle.getValue("SM7005_03_form_01", "flag") == "update") {
                        form.init.setURL("/fm/sm/sm7005_03/updateInterfaceItemDtlInfo");
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
            
            var vFlag = form.handle.getValue("SM7005_03_form_01", "flag");
            
            if (!this.validate(vFlag)) {
                return
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7005_03_form_01");
                    
                    form.init.setURL("/fm/sm/sm7005_03/deleteInterfaceItemDtlInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        selectInterfaceItemDtlList : function() {
            // 검색창의 validation 체크결과를 비교한다.
            if (!form.handle.isValidate("SM7005_03_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("SM7005_03_grid_01");
            var params = form.handle.getElementsParam("SM7005_03_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7005_02/selectInterfaceItemDtlList", params);
        },
        validate : function(pType) {
            
            var vCompanyCd = form.handle.getValue("SM7005_03_form_01", "COMPANY_CD");
            var vIfCode = form.handle.getValue("SM7005_03_form_01", "IF_CD");
            var vColSeq = form.handle.getValue("SM7005_03_form_01", "COL_SEQ");
            
            if (oUtil.isNull(vCompanyCd)) {
                // 회사코드를 찾을 수 없습니다.
                alert(resource.getMessage("MSG_NOT_FOUND_COMPANY_CD"));
                return false;
            }
            
            if (pType != "insert") {
                if (oUtil.isNull(vIfCode)) {
                    // 인터페이스 코드를 찾을 수 없습니다.
                    alert(resource.getMessage("MSG_NOT_FOUND_IF_CODE"));
                    return false;
                }
                if (oUtil.isNull(vColSeq)) {
                    // 컬럼 순번을 찾을 수 없습니다.
                    alert(resource.getMessage("MSG_NOT_FOUND_COL_SEQ"));
                    return false;
                }
            }
            
            return true;
        }
    },
    // 다이얼로그 구현
    dialog : {
       
        openDialog : function(flag) {
            var dg_1 = grid.getObject("SM7005_03_grid_01");
            var dl_1 = dialog.getObject("SM7005_03_dailog_01");
            
            if (flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("SM7005_03_form_01");
            var params2 = {};
            
            params1.flag = flag;
            if (flag != "insert") {
                params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);

            // 팝업 셋팅
            // title:인터페이스항목등록
            dialog.init.setTitle(resource.getMessage("SM7005_03"));
            dialog.init.setWidth(600);
            dialog.init.setHeight(250);
            dialog.init.setURL("/fm/sm/sm7005_03");
            dialog.init.setResizable(true);
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
        insertChangeUI : function() {
            
            var vDefaultLanguage = form.handle.getValue("SM7005_03_form_01", "DEFAULT_LANGUAGE");
            
            if (vDefaultLanguage == "EN") {
                dialog.handle.resize("SM7005_03_dailog_01", 800, 275);
            }
            
            // 삭제버튼감춤
            form.util.setVisible("SM7005_03_btn_02", false);
            
            form.handle.readonly("SM7005_03_form_01", "IF_CD", true);
            form.handle.addClass("SM7005_03_form_01", "IF_CD", "input_readOnly");
            form.handle.readonly("SM7005_03_form_01", "IF_NAME", true);
            form.handle.addClass("SM7005_03_form_01", "IF_NAME", "input_readOnly");
            form.handle.readonly("SM7005_03_form_01", "SOURCE_TABLE", true);
            form.handle.addClass("SM7005_03_form_01", "SOURCE_TABLE", "input_readOnly");
            form.handle.readonly("SM7005_03_form_01", "TARGET_TABLE", true);
            form.handle.addClass("SM7005_03_form_01", "TARGET_TABLE", "input_readOnly");
        },
        updateChangeUI : function() {
            
            var vDefaultLanguage = form.handle.getValue("SM7005_03_form_01", "DEFAULT_LANGUAGE");
            
            if (vDefaultLanguage == "EN") {
                dialog.handle.resize("SM7005_03_dailog_01", 800, 275);
            }
            
            // 삭제버튼보임
            form.util.setVisible("SM7005_03_btn_02", true);
            
            // key가 되는 input은 모두 막는다.
            form.handle.readonly("SM7005_03_form_01", "IF_CD", true);
            form.handle.addClass("SM7005_03_form_01", "IF_CD", "input_readOnly");
            form.handle.readonly("SM7005_03_form_01", "IF_NAME", true);
            form.handle.addClass("SM7005_03_form_01", "IF_NAME", "input_readOnly");
            form.handle.readonly("SM7005_03_form_01", "SOURCE_TABLE", true);
            form.handle.addClass("SM7005_03_form_01", "SOURCE_TABLE", "input_readOnly");
            form.handle.readonly("SM7005_03_form_01", "TARGET_TABLE", true);
            form.handle.addClass("SM7005_03_form_01", "TARGET_TABLE", "input_readOnly");
        },
        viewChangeUI : function() {
            
            var vDefaultLanguage = form.handle.getValue("SM7005_03_form_01", "DEFAULT_LANGUAGE");
            
            if (vDefaultLanguage == "EN") {
                dialog.handle.resize("SM7005_03_dailog_01", 800, 275);
            } else {
                dialog.handle.resize("SM7005_03_dailog_01", 800, 220);
            }
            
            // 버튼감싸는영역
            form.util.setVisible("SM7005_03_div_01", false);

            // form안 모든 element에 대해서 label과 같은 형식으로 표시한다.
            form.handle.closeAll("SM7005_03_form_01");
            
        }
    },
    // 파일 input 엘리먼트 구현
    file : {
        
    },
    // 엑셀다운로드 구현
    excel : {
        
    }
    
};
