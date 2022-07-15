/******************************************************************************************
 * 작성자 : sbj1000
 * Program Id : SM-7009_02
 * 작업일자 : 2016.05.17
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7009_02.init.initComponent();
}

var SM7009_02 = {
        
        // 초기값 설정
        init : {
            initComponent : function() {
                
                var flag = form.handle.getValue("SM7009_02_form_01", "flag");

                // 초기화면에 보여줄 데이터 조회
                if (flag != "insert") {
                    SM7009_02.control.selectMenuInfo();
                }
                
                SM7009_02.combobox.initCombo_1();
                SM7009_02.combobox.initCombo_2();
                SM7009_02.combobox.initCombo_3();
                SM7009_02.combobox.initCombo_4();
                SM7009_02.combobox.initCombo_5();
                SM7009_02.combobox.initCombo_6();
                
                if(flag == "update"){
                    SM7009_02.ui.updateChangeUI();
                }
            }
        },
        // 달력 그리드 생성
        calendar : { }, 
        // 콤보박스 그리드 생성
        combobox : {
            dataLevel : [{CODE:"1", NAME:resource.getMessage("1, LEVEL")}, {CODE:"2", NAME:resource.getMessage("2, LEVEL")}],
            topMenu : [{CODE:"IF", NAME:"FTA"}, {CODE:"CC", NAME:resource.getMessage("CC_통관")}, {CODE:"RS", NAME:"중계서버"}],
            initCombo_1 : function() {                
                var obj_01 = combo.getObject("SM7009_02_form_01", "USE_YN");
                
                combo.init.setURL("/mm/cbox/selectStandardCode");
                combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN"});
                combo.init.setValueField("CODE");
                combo.init.setNameField("NAME");
                
                combo.create(obj_01);
            },
            initCombo_2 : function() {
                var vCompanyCd = form.handle.getValue("SM7009_01_form_01", "COMPANY_CD") 
                var obj_01 = combo.getObject("SM7009_02_form_01", "SYS_ID");
                
                combo.init.setURL("/mm/cbox/selectSystemType");
                combo.init.setQueryParams({COMPANY_CD:vCompanyCd});
                combo.init.setValueField("CODE");
                combo.init.setNameField("NAME");
                
                combo.create(obj_01); 
            },
            initCombo_3 : function() {
                var obj_01 = combo.getObject("SM7009_02_form_01", "HGRNK_MENU_ID");
                
                combo.init.setURL("/mm/cbox/selectHighMenu");
                combo.init.setQueryParams({MESSAGE_CODE:"None"});
                combo.init.setValueField("CODE");
                combo.init.setNameField("NAME");
                
                combo.create(obj_01); 
            },
            initCombo_4 : function() {
                var obj_01 = combo.getObject("SM7009_02_form_01", "MENU_LVL_NO");
                
                combo.init.setData(this.dataLevel);
                combo.init.setValueField("CODE");
                combo.init.setNameField("NAME");
                
                combo.create(obj_01);
            },
            initCombo_5 : function() {
                var obj_01 = combo.getObject("SM7009_02_form_01", "HGRNK_SYS_ID");
                
                combo.init.setData(this.topMenu);
                combo.init.setValueField("CODE");
                combo.init.setNameField("NAME");
                
                combo.create(obj_01);
            }
        },
        // 데이터 그리드 생성
        datagrid : { },
        // 이벤트 처리
        event : {
            // 성공적으로 요청처리가 완료될 경우 호출되는 함수
            updateAfterUI : function(data) {
                SM7009_01.control.selectMenuList();
                form.handle.readonly("SM7009_02_form_01", "MENU_ID", true);
                form.handle.addClass("SM7009_02_form_01", "MENU_ID", "input_readOnly");
            },
            selectAfterUI : function(data) {
                form.handle.loadData("SM7009_02_form_01", data);
            }
        },
        control : {// 업무구현
            selectMenuInfo : function() {
                var obj = form.getObject("SM7009_02_form_01");
                
                form.init.setURL("/fm/sm/sm7009_02/selectMenuInfo");
                form.init.setCallBackFunction("selectAfterUI");
                form.init.setProgressFlag(false);
                
                form.submit(obj);
            },
            updateMenuInfo : function() {
                if (!form.handle.isValidate("SM7009_02_form_01")) return;
                
                var confMsg = "";
                if(form.handle.getValue("SM7009_02_form_01", "flag") == "insert") {
                    confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
                } else {
                    confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
                }
                
                $.messager.confirm(CNFIR, confMsg, function(flag) {
                    if(flag) {
                        if(combo.handle.getValue("SM7009_02_form_01", "MENU_LVL_NO") == "1") {
                        	var pid = form.handle.getValue("SM7009_02_form_01", "MENU_ID");
                        	
                        	combo.handle.setValue("SM7009_02_form_01", "HGRNK_MENU_ID", pid);
                        }
                        var sysId = combo.handle.getValue("SM7009_02_form_01", "SYS_ID");
                        
                        if(sysId == "SM") form.handle.setValue("SM7009_02_form_01", "MENU_TYPE", "C");
                        else if(sysId == "SP") form.handle.setValue("SM7009_02_form_01", "MENU_TYPE", "E");
                        else form.handle.setValue("SM7009_02_form_01", "MENU_TYPE", "I");
                    	
                    	var obj = form.getObject("SM7009_02_form_01");
                        
                        if(form.handle.getValue("SM7009_02_form_01", "flag") == "insert") {
                            form.init.setURL("/fm/sm/sm7009_02/insertMenuInfo");
                        }
                        if(form.handle.getValue("SM7009_02_form_01", "flag") == "update") {
                            form.init.setURL("/fm/sm/sm7009_02/updateMenuInfo");
                        }
                        form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                        form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                        form.init.setCallBackFunction("updateAfterUI");
                        form.submit(obj);
                    }
                });
            }
        },
        dialog : { },
        // 화면 UI 변경
        ui : {
            updateChangeUI : function(){
                form.handle.readonly("SM7009_02_form_01", "MENU_ID", true);
                form.handle.addClass("SM7009_02_form_01", "MENU_ID", "input_readOnly");
            }
        },
        // 파일 input 엘리먼트 구현
        file : { },
        // 엑셀다운로드 구현
        excel : { }
};