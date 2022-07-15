/******************************************************************************************
 * 작성자 : 
 * Program Id : MM-A005_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA005_01.config.applyFtaNation();
	MMA005_01.init.initComponent();
}

var MMA005_01 = {
	init : {
        initComponent : function() {
        	MMA005_01.combobox.initCombo_2();
        	MMA005_01.combobox.initCombo_3();
        	MMA005_01.combobox.initCombo_4();
        	
        	MMA005_01.datagrid.initGrid_1();
        }
    },
	calendar : {}, // 달력 그리드 생성
	// 콤보박스 그리드 생성
	combobox : {
		data_1 : [{CODE : "M.ITEM_CD", NAME : resource.getMessage("LTIT, CODE")}, 
			{CODE : "M.ITEM_NAME", NAME : resource.getMessage("LTIT, NAME")},
			{CODE : "M.HS_CODE", NAME : resource.getMessage("HS, CODE")}
		],
		data_1_v : [{CODE : "M.ITEM_CD", NAME : resource.getMessage("LTIT, CODE")},
			{CODE : "M.TEMP_COLUMN3", NAME:resource.getMessage("Part,MSSNM")},
            {CODE : "M.TEMP_COLUMN4", NAME:resource.getMessage("ECN,CODE")},
			{CODE : "M.ITEM_NAME", NAME : resource.getMessage("LTIT, NAME")},
			{CODE : "M.HS_CODE", NAME : resource.getMessage("HS, CODE")}
		],
		initCombo_1 : function() {
			var obj = combo.getObject("MMA005_01_form_01", "DIVISION_CD");
			
			combo.init.setURL("/mm/cbox/selectDivision");
			combo.init.setQueryParams({COMPANY_CD:form.handle.getValue("MMA005_01_form_01", "COMPANY_CD"), ALL:"Y"});
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_2 : function() {
			var obj = combo.getObject("MMA005_01_form_01", "ITEM_TYPE");
			
			combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"ITEM_TYPE", ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_3 : function() {
	        var obj = combo.getObject("MMA005_01_form_01", "schKeyField");
	
	        if(PART_NUM_VIEW_YN == "Y") {
	        	combo.init.setData(this.data_1_v);
	        } else {
	        	combo.init.setData(this.data_1);
	        }
	        combo.init.setValueField("CODE");
	        combo.init.setNameField("NAME");
	
	        combo.create(obj);
	    },
	    initCombo_4 : function() {
	        var obj = combo.getObject("MMA005_01_form_01", "schKeyLike");
	
	        combo.init.setURL("/mm/cbox/selectStandardCode");
	        combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
	        combo.init.setValueField("CODE");
	        combo.init.setNameField("NAME");
	
	        combo.create(obj);
	    }
	},
    datagrid : { // 데이터 그리드 생성
		header : [
					["ITEM_CD"			,resource.getMessage("LTIT,CODE")			,100    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
					["TEMP_COLUMN3"     ,resource.getMessage("Part,MSSNM")          ,120    ,"center"    ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
		            ["TEMP_COLUMN4"     ,resource.getMessage("ECN,CODE")            ,120    ,"center"    ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
					["ITEM_NAME"		,resource.getMessage("LTIT,NAME")			,150    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
					["ITEM_NAME_ENG"	,resource.getMessage("LTIT,NAME,(,ENGLS,)") ,150    ,"left"      ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
					["HS_CODE"			,resource.getMessage("HS,CODE")			    ,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
					["ITEM_TYPE_NAME"	,resource.getMessage("TXT_ASSETS_TYPE")		,80     ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
	             ],
				  
		initGrid_1 : function() {
			var dg_1 = grid.getObject("MMA005_01_grid_01");
			
			grid.init.setPage(true);
			grid.init.setFitColumns(true);
			// 이벤트 설정
			grid.event.setOnDblClickRow(dg_1);
			
			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
		onClick_MMA005_01_grid_01 : function(rowData) {},
		onDblClick_MMA005_01_grid_01 : function(rowData) {
			var pid = form.handle.getValue("MMA005_01_form_01", "TARGET_PID");
            if (!oUtil.isNull(pid)) {
            	var pro_id = eval("window." + pid + ".dialog");
            	
                if (typeof(pro_id["callByMMA005_01"]) == "function") {
                    pro_id["callByMMA005_01"](rowData);
                }
            }
		},
		callBackNoData : function(data) {},
    },
    control : {// 업무구현
    	selectMainList : function() {
    		var dg_1 = grid.getObject("MMA005_01_grid_01");
    		var params = form.handle.getElementsParam("MMA005_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA005_01/selectMainList", params);
    	},
    	selectRowValue : function() {
    		var dg_1 = grid.getObject("MMA005_01_grid_01");
    		var rowData = grid.handle.getSelectedRowData(dg_1);
    		
    		if(!grid.handle.isSelected(dg_1)) {
                // 그리드에서 열을 선택해 주세요.
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
    		}
    		
    		MMA005_01.event.onDblClick_MMA005_01_grid_01(rowData);
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
    },
    config : {
     	applyFtaNation : function() {
     		var columns1 = MMA005_01.datagrid.header;
     		
 			if(SESSION.FTA_NATION == "KR") {	
 				grid.util.changeHeaderHidden(columns1 , "ITEM_NAME", false);
 				grid.util.changeHeaderHidden(columns1 , "ITEM_NAME_ENG", true);
 			} else {
 				grid.util.changeHeaderHidden(columns1 , "ITEM_NAME", true);
 				grid.util.changeHeaderHidden(columns1 , "ITEM_NAME_ENG", false);
 			}
 			
 			if(PART_NUM_VIEW_YN == "Y") {
           	 var columns = MMA005_01.datagrid.header;

                
                grid.util.changeHeaderHidden(columns , "TEMP_COLUMN3", false); // Part No.
                grid.util.changeHeaderHidden(columns , "TEMP_COLUMN4", false); // ECN Code

            }
 		}
     }
	
};
