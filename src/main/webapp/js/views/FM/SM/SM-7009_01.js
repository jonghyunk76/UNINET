/******************************************************************************************
 * 작성자 : sbj1000
 * Program Id : SM-7009_01
 * 작업일자 : 2016.05.16
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7009_01.init.initComponent();
}

var SM7009_01 = {
    
    init : {
        initComponent : function() {  
            SM7009_01.combobox.initCombo_1();
            SM7009_01.combobox.initCombo_2();
            SM7009_01.combobox.initCombo_3();
            SM7009_01.combobox.initCombo_4();
            SM7009_01.combobox.initCombo_5();
            
            SM7009_01.datagrid.initGrid_1();
            SM7009_01.datagrid.initGrid_2();
        }
    }, // 초기값 설정
    calendar : {
    }, // 달력 그리드 생성
    combobox : {
    	data_1 : [
    		{CODE:"MENU_NM", NAME:resource.getMessage("TXT_MENU_NAME")}, 
    		{CODE:"MENU_ID", NAME:resource.getMessage("MENU,ID")}
    	],
    	data_2 : [
    		{CODE:" ", NAME:resource.getMessage("ALL")}, 
    		{CODE:"Y", NAME:resource.getMessage("TXT_YES")}, 
    		{CODE:"N", NAME:resource.getMessage("TXT_NO")}
    	],
        initCombo_1 : function() {
            var vCompanyCd = form.handle.getValue("SM7009_01_form_01", "COMPANY_CD") 
            
            var obj_01 = combo.getObject("SM7009_01_form_01", "SYS_ID");
            
            combo.init.setURL("/mm/cbox/selectSystemType");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.create(obj_01); 
         
        },
        initCombo_2 : function() {
            var vCompanyCd = form.handle.getValue("SM7009_01_form_01", "COMPANY_CD") 
            
            var obj_01 = combo.getObject("SM7009_01_form_01", "HGRNK_MENU_ID");
            
            combo.init.setURL("/mm/cbox/selectHighMenu");
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.create(obj_01); 
         
        },
    	initCombo_3 : function() {
			var obj = combo.getObject("SM7009_01_form_01", "schKeyField");
			
			combo.init.setData(this.data_1);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_4 : function() {
			var obj = combo.getObject("SM7009_01_form_01", "schKeyLike");
			
			combo.init.setURL("/mm/cbox/selectStandardCode");
			combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"}); // COMPANY_CD는 추후에 세션에서 찾도록 변경할 것
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_5 : function() {
			var obj = combo.getObject("SM7009_01_form_01", "USE_YN");
			
			combo.init.setData(this.data_2);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		}
    }, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
        header_1 : [
            ["SYS_ID"        ,resource.getMessage("SYS,ID")                /*시스템 ID*/    ,100  ,"left"    ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["HGRNK_MENU_ID" ,resource.getMessage("TXT_PARENT_MENU_ID")    /*상위메뉴 ID*/  ,100  ,"left"    ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["HGRNK_MENU_NM" ,resource.getMessage("TOP,MENU,NAME")         /*메뉴명*/       ,250  ,"left"    ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["MENU_ID"       ,resource.getMessage("MENU,ID")               /*메뉴 ID*/      ,100  ,"center"  ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["MENU_NM"       ,resource.getMessage("TXT_MENU_NAME")         /*메뉴명*/       ,200  ,"left"    ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["MENU_LVL_NO"   ,resource.getMessage("TXT_MENU_LEVEL")        /*메뉴레벨*/     ,100  ,"left"    ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["MENU_SORT_SER" ,resource.getMessage("TXT_SORT_SEQ")          /*정렬순번*/     ,100  ,"left"    ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["DIRECT_URL"    ,resource.getMessage("TXT_URL")               /*정렬순번*/     ,200  ,"left"    ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["PRGM_ID"       ,resource.getMessage("INF,ID")                /*정렬순번*/     ,100  ,"center"  ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["USE_YN_NAME"   ,resource.getMessage("TXT_USE_YN")            /*사용유무*/     ,100  ,"center"  ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0]
        ],
        header_2 : [
            ["MENU_ID"        ,resource.getMessage("MENU,ID")            /*메뉴 ID*/         ,100,"center" ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["LANGUAGE_CAT"   ,resource.getMessage("LANG")               /*언어*/            ,80,"center"   ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0],
            ["WORD"           ,resource.getMessage("TXT_MENU_NAME")      /*메뉴명*/          ,250,"left"   ,"center"  ,true  ,false  ,null  ,null  ,null  ,0  ,0]
        ],

        initGrid_1 : function() {
            
            var dg_1 = grid.getObject("SM7009_01_grid_01");
            
            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            grid.init.setMergeMode(true);
            grid.init.setMergeData(["SYS_ID", "HGRNK_MENU_ID", "HGRNK_MENU_NM"], "HGRNK_MENU_ID");
            
            grid.event.setOnDblClickRow(dg_1);
            grid.event.setOnClickRow(dg_1);
            
            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
            var dg_1 = grid.getObject("SM7009_01_grid_02");
            
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_2);
        }
    },
    event : { // 이벤트 처리
        onClick_SM7009_01_grid_01 : function(rowData) {
            SM7009_01.control.selectMenuLanguageList(rowData);
        },
        onDblClick_SM7009_01_grid_01 : function(rowData) {
            SM7009_01.dialog.openDialog_1('update');
        },
        onDblClick_SM7009_01_grid_02 : function(rowData) {         
            SM7009_01.dialog.openDialog_2('update');
        }
    },
    control : {// 업무구현
        selectMenuList : function() {
            // 검색창의 validation 체크결과를 비교한다.
            if (!form.handle.isValidate("SM7009_01_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("SM7009_01_grid_01");
            var params = form.handle.getElementsParam("SM7009_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7009_01/selectMenuList", params);
        },
        selectMenuLanguageList : function(pData) {
            if (!form.handle.isValidate("SM7009_01_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("SM7009_01_grid_02");
            var params = pData || form.handle.getElementsParam("SM7009_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7009_01/selectMenuLanguageList", params);
        },
    },
    dialog : {
        openDialog_1 : function(flag) {
            var dg_1 = grid.getObject("SM7009_01_grid_01");
            var dl_1 = dialog.getObject("SM7009_02_dailog_01");
                        
            if(flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = {};
            var params2 = {};
            params1.flag = flag;
                        
            if(flag != "insert") params2 = grid.handle.getSelectedRowData(dg_1);
            else params1.COMPANY_CD = SESSION.COMPANY_CD;
                
            $.extend(params, params1, params2);
            // 팝업 셋팅
            dialog.init.setTitle(resource.getMessage("SM7009_02"));
            dialog.init.setWidth(800);
            dialog.init.setHeight(330);
            dialog.init.setResizable(false);
            dialog.init.setURL("/fm/sm/sm7009_02");
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        },
        openDialog_2 : function(flag) {
            var dg_1 = grid.getObject("SM7009_01_grid_01");
            var dg_2 = grid.getObject("SM7009_01_grid_02");
            var dl_2 = dialog.getObject("SM7009_03_dailog_01");
                                    
            if(flag != "insert" && !grid.handle.isSelected(dg_2)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            if(flag == "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = {};
            var params2 = {};
            params1.flag = flag;
            
            var menu_id = grid.handle.getSelectedRowData(dg_1, "MENU_ID");
            
            if(flag != "insert") {
                params2 = grid.handle.getSelectedRowData(dg_2);
            }
            else {
                params1.COMPANY_CD = SESSION.COMPANY_CD;
                params1.MENU_ID = menu_id;
            }            
                
            $.extend(params, params1, params2);
            // 팝업 셋팅
            dialog.init.setTitle(resource.getMessage("SM7009_03"));
            dialog.init.setWidth(400);
            dialog.init.setHeight(270);
            dialog.init.setResizable(false);
            dialog.init.setURL("/fm/sm/sm7009_03");
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_2);
        }
    },
    file : { },
    excel : { }
    
};
