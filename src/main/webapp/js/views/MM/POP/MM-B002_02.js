/******************************************************************************************
 * 작성자 : Atom
 * Program Id : MM-B002_02
 * 작업일자 : 2020.06.30
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMB002_02.init.initComponent();
}

var MMB002_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            MMB002_02.combobox.initCombo_1();
            MMB002_02.combobox.initCombo_2();
            MMB002_02.combobox.initCombo_3();
            
            MMB002_02.datagrid.initGrid_1();
            MMB002_02.datagrid.initGrid_2();
            MMB002_02.datagrid.initGrid_3();
            
        }
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {
    	data_1 : [
    		{CODE:"1", NAME:resource.getMessage("CC_수입")},
        	{CODE:"2", NAME:resource.getMessage("CC_수출")}
       	],   	
    	data_2 : [
    		{CODE:"", NAME:resource.getMessage("ALL")},
    		{CODE:"CODE", NAME:resource.getMessage("CC_세관코드")},
        	{CODE:"CODE_NAME", NAME:resource.getMessage("CC_세관명")}
       	],
       	initCombo_1 : function() {
            var obj = combo.getObject("MMB002_02_form_01", "IMP_EXP_SE_CD");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            //combo.init.setCallFunction("onChangeKeyField1");

            combo.create(obj);
        },       	
       	initCombo_2 : function() {
            var obj = combo.getObject("MMB002_02_form_01", "schKeyField");

            combo.init.setData(this.data_2);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_3 : function() {
            var obj = combo.getObject("MMB002_02_form_01", "schKeyLike");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        }       
    },
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB002_02_grid_01");
    		var params = form.handle.getElementsParam("MMB002_02_form_01");
            
    		grid.init.setURL("/mm/pop/MMB002_02/selectKcsCodeList");
    		grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_1"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            grid.init.setCallBackFunction("onClick_AutoLine");			// 그리드 자동 클릭
            grid.event.setOnClickRow(dg_1);	// 그리드 ONE 클릭 시 이벤트
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}}); // DB에서 그리드 해더 정보 획득						
        },
    	initGrid_2 : function() {
    		var dg_1 = grid.getObject("MMB002_02_grid_02");

            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_2"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header2"}}); // DB에서 그리드 해더 정보 획득						
        },
    	initGrid_3 : function() {
    		var dg_1 = grid.getObject("MMB002_02_grid_03");

            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setShowConfigPage(true); // 하단에 그리드 설정 및 생성 버튼을 표시할지 여부를 지정(매우중요)
            grid.init.setRecallFunction("initGrid_3"); // 그리드 설정 후 호출할 자신의 함수명 지정(중요)

            //grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header3"}}); // DB에서 그리드 해더 정보 획득						
        }        
    },
    // 이벤트 처리
    event : {
    	// 세관 자동 클릭  Call
    	onClick_AutoLine : function(rowData) {
            // 첫번째 그리드 값을 자동으로 선택할 것
            var dg_1 = grid.getObject("MMB002_02_grid_01");
            var currentIdx = grid.handle.getCurrentRowIndex(dg_1);
            
            if(oUtil.isNull(currentIdx)) {
                currentIdx = 0;
            }
            
            grid.handle.selectRowIndex(dg_1, currentIdx);
            
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            this.onClick_MMB002_02_grid_01(rowData);
    	},
    	// 과 리스트 Call
    	onClick_MMB002_02_grid_01 : function(rowData) {
    		MMB002_02.control.selectKwaList(rowData);
    		MMB002_02.control.selectCsmhKwaExcludeList(rowData);
    	},
    	afterCsmhKwaMappingUI : function() {
    		var dg_1 = grid.getObject("MMB002_02_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            MMB002_02.control.selectKwaList(rowData);
            MMB002_02.control.selectCsmhKwaExcludeList(rowData);
        },
        onChangeKeyField1 : function() {
        	MMB002_02.control.selectKcsCodeList();
        },
        onDblClick_MMB002_02_grid_02 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_02_form_01", "TARGET_PID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_02"]) == "function") {
                    pro_id["callByMMB002_02"](rowData);
                }
            }
        }    
    },
    // 업무구현
    control : {
    	// 세관
    	selectKcsCodeList : function() {
    		var dg_1 = grid.getObject("MMB002_02_grid_01");
    		var params = form.handle.getElementsParam("MMB002_02_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_02/selectKcsCodeList", params);
    	},
    	// 과
    	selectKwaList : function(rowData) {
            if (!form.handle.isValidate("MMB002_02_form_01")) return;		// FORM 개체 유효성 체크
            
    		var dg_1 = grid.getObject("MMB002_02_grid_02");					// 그리드 Object 가져오기  
    		var params = rowData;	// 파라미터 가져오기
    		params.COMPANY_CD = SESSION.COMPANY_CD;
    		params.IMP_EXP_SE_CD = combo.handle.getValue("MMB002_02_form_01", "IMP_EXP_SE_CD");
	
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_02/selectKwaList", params);	// 그리드에 데이터베이스 연계
    	},
    	// 세관 과 제외 리스트
    	selectCsmhKwaExcludeList : function(rowData) {
            if (!form.handle.isValidate("MMB002_02_form_01")) return;		// FORM 개체 유효성 체크
            
    		var dg_1 = grid.getObject("MMB002_02_grid_03");					// 그리드 Object 가져오기  
    		var params = rowData;	// 파라미터 가져오기
    		params.COMPANY_CD = SESSION.COMPANY_CD;
    		params.IMP_EXP_SE_CD = combo.handle.getValue("MMB002_02_form_01", "IMP_EXP_SE_CD");
	
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_02/selectCsmhKwaExcludeList", params);	// 그리드에 데이터베이스 연계
    	},    
    	// 세관 과 추가
    	insertCsmhKwaMapping : function() {
        	var formName = "MMB002_02_form_03";
        	var gridName = "MMB002_02_grid_03";
            var dg_3 = grid.getObject(gridName);
            
            if(!grid.handle.isChecked(dg_3)) {
                alert(resource.getMessage("MSG_NO_CHECK") + "<br>(Grid : " + resource.getMessage("CC_과 전체") + ")");
                return;
            }

            var row3 = grid.handle.getCheckedRowsData(dg_3);
            var dg_1 = grid.getObject("MMB002_02_grid_01");
            var row1 = grid.handle.getSelectedRowData(dg_1);
            												
            form.handle.setValue(formName, "IMP_EXP_SE_CD", combo.handle.getValue("MMB002_02_form_01", "IMP_EXP_SE_CD"));
            form.handle.setValue(formName, "CSMH_CD", row1.CSMH_CD);
            form.handle.setValue(formName, "gridData", grid.util.convertJson2Rows(row3));

            var obj = form.getObject(formName);
            
            form.init.setURL("/mm/pop/MMB002_02/insertCsmhKwaMapping");
            form.init.setCallBackFunction("afterCsmhKwaMappingUI");

            form.submit(obj);
        },
        // 세관 과 삭제
        deleteCsmhKwaMapping : function() {
        	var formName = "MMB002_02_form_02";
        	var gridName = "MMB002_02_grid_02";
            var dg_1 = grid.getObject(gridName);
            
            if(!grid.handle.isChecked(dg_1)) {
                alert(resource.getMessage("MSG_NO_CHECK") + "<br>(Grid : " + resource.getMessage("CC_과") + ")");
                return;
            }
            
            var row1 = grid.handle.getCheckedRowsData(dg_1);

            form.handle.setValue(formName, "gridData", grid.util.convertJson2Rows(row1));
            
            var obj = form.getObject(formName);
            
            form.init.setURL("/mm/pop/MMB002_02/deleteCsmhKwaMapping");
            form.init.setCallBackFunction("afterCsmhKwaMappingUI");
            
            form.submit(obj);
        },    	
    	selectRowValue : function() {
            var dg_1 = grid.getObject("MMB002_02_grid_02");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }
            
            MMB002_02.event.onDblClick_MMB002_02_grid_02(rowData);
        }    	    	
    },
    // 다이얼로그 구현
    dialog : {
    	
    },
    file : {
        
    },
    tool : {
    	
	},
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMB002_02_grid_01");
            var fobj = form.getObject("MMB002_02_form_01");
            
            form.init.setURL("/mm/pop/MMB002_02/selectShedList.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("CC_관세율기본"), resource.getMessage("CC_관세율기본"));
        }
    }
    
};
