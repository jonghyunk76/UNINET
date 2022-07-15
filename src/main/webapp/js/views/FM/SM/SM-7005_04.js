/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7005_04
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7005_04.init.initComponent();
}

var SM7005_04 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            
            var vFlag = nullToString(form.handle.getValue("SM7005_04_form_01", "flag"));

            // 초기화면에 보여줄 데이터 조회
            if (vFlag.indexOf("insert") == -1) {
                SM7005_04.control.selectInterfaceItemMstInfo();
            }
            
            SM7005_04.calendar.initCalendar_1();
            SM7005_04.combobox.initCombo_1();
            SM7005_04.datagrid.initGrid_1();
            
            // ui변경
            if (vFlag == "view") {
                SM7005_04.ui.viewChangeUI();
            } else if (vFlag == "update") {
                SM7005_04.ui.updateChangeUI();
            } else {
                SM7005_04.ui.insertChangeUI();
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
        // case1) URL 호출에 의한 ComboBox 데이터 생성 방법
        initCombo_1 : function() {
            var vCompanyCd = form.handle.getValue("SM7005_04_form_01", "COMPANY_CD") 
            
            var objUsingYn = combo.getObject("SM7005_04_form_01", "USING_YN");
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, COMPANY_CD:vCompanyCd, CATEGORY_CD:"USE_YN"});
            //combo.init.setValue("100000");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            //combo.init.setHeight(200);
            //combo.init.setCallFunction("onChangeIfMethod");
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
            form.handle.loadData("SM7005_04_form_01", data);
        },
        // 성공적으로 요청처리가 완료될 경우 호출되는 함수
        updateAfterUI : function(data) {
            // flag가 "update"인 경우, 초기화면 로딩 시 입력란을 컨트롤 함
            
            var oMap = null;
            try {
                oMap = data.dataMap.map;    
            } catch (e) {
                oMap = {};
            }
            
            var vFlag = nullToString(form.handle.getValue("SM7005_04_form_01", "flag"));
            
            if (vFlag.indexOf("insert") > -1) {
                // update 상태로 변경한다.
                form.handle.setValue("SM7005_04_form_01", "flag", "update");
                
                form.handle.setValue("SM7005_04_form_01", "INTERFACE_ITEM_MST_ID", oMap.INTERFACE_ITEM_MST_ID);
            }
            
            // 업데이트 상태로 UI를 변경한다.
            SM7005_04.ui.updateChangeUI();
            
            // 부모조회
            SM7005_01.control.selectInterfaceItemMstList();
            
            //var dl_1 = dialog.getObject("SM7005_04_dailog_01");
            //dialog.handle.close(dl_1);
            
        },
        deleteAfterUI : function(data) {
            // 부모조회
            SM7005_01.control.selectInterfaceItemMstList();
            
            var dl_1 = dialog.getObject("SM7005_04_dailog_01");
            dialog.handle.close(dl_1);
        }
    },
    // 업무구현
    control : {
        selectInterfaceItemMstInfo : function() {
            var obj = form.getObject("SM7005_04_form_01");
            
            form.init.setURL("/fm/sm/sm7005_02/selectInterfaceItemMstInfo");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            // 성공과 실패에 대한 메시지 안 뜨게 함(추후엔 공통에서 처리할 것임)
            form.init.setSucceseMessage("");
            form.init.setFailMessage("");
            
            form.submit(obj);
        },
        // insert/update 수행
        updateInterfaceItemMstInfo : function() {
            
            if (!form.handle.isValidate("SM7005_04_form_01")) return;
            var confMsg = "";
            
            var vFlag = nullToString(form.handle.getValue("SM7005_04_form_01", "flag"));
            
            if (vFlag.indexOf("insert") > -1) {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                
                if (flag) {
                    var obj = form.getObject("SM7005_04_form_01");
                    
                    var vFlag = nullToString(form.handle.getValue("SM7005_04_form_01", "flag"));
                    
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
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("SM7005_04_form_01");
                    
                    form.init.setURL("/fm/sm/sm7005_02/deleteInterfaceItemMstInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        validate : function(pType) {
            
            var vCompanyCd = form.handle.getValue("SM7005_04_form_01", "COMPANY_CD");
            var vIfCode = form.handle.getValue("SM7005_04_form_01", "IF_CD");
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
            }
            
            return true;
        }
    },
    // 다이얼로그 구현
    dialog : {

        openDialog : function(flag) {
            var dg_1 = grid.getObject("SM7005_04_grid_01");
            var dl_1 = dialog.getObject("SM7005_03_dailog_01");
            
            var vFlag = nullToString(flag);
            
            if (vFlag.indexOf("insert") == -1 && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("SM7005_04_form_01");
            var params2 = {};
            
            params1.flag = vFlag;
            if (vFlag.indexOf("insert") == -1) {
                params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);

            // 팝업 셋팅
            // title:인터페이스항목등록
            dialog.init.setTitle(resource.getMessage("SM7005_03"));
            dialog.init.setWidth(800);
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
            
            // 삭제버튼감춤
            form.util.setVisible("SM7005_04_btn_02", false);
        },
        updateChangeUI : function() {
            
            // 삭제버튼보임
            form.util.setVisible("SM7005_04_btn_02", true);
            
            // key가 되는 input은 모두 막는다.
            form.handle.readonly("SM7005_04_form_01", "IF_CD", true);
            form.handle.addClass("SM7005_04_form_01", "IF_CD", "input_readOnly");
        },
        viewChangeUI : function() {
            
            dialog.handle.resize("SM7005_04_dailog_01", 600, 225);
            
            // 버튼감싸는영역
            form.util.setVisible("SM7005_04_div_01", false);
            
            // form안 모든 element에 대해서 label과 같은 형식으로 표시한다.
            form.handle.closeAll("SM7005_04_form_01");
        }
    },
    // 파일 input 엘리먼트 구현
    file : {
        
    },
    // 엑셀다운로드 구현
    excel : {
        
    }
    
};
