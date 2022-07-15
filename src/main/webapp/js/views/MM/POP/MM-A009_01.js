/******************************************************************************************
 * 작성자 : csy
 * Program Id : MM-A009_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA009_01.init.initComponent();
}

var MMA009_01 = {
	
    init : {
    	initComponent : function() {
    		MMA009_01.datagrid.initGrid_1();
    	}
    }, // 초기값 설정
	calendar : {}, // 달력 그리드 생성
	combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
			["USER_ID"         ,resource.getMessage("USER, ID")             ,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["NAME"            ,resource.getMessage("USER, NAME")           ,160    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["NAME_ENG"        ,resource.getMessage("TXT_USER_NAME_ENG")    ,160    ,"left"      ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
			["EMP_NO"          ,resource.getMessage("EMPNO")                ,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["POSITION"        ,resource.getMessage("TXT_DUTY_POS")         ,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["EMAIL"           ,resource.getMessage("TXT_EMAIL")            ,150    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["CELL_PHONE_NO"   ,resource.getMessage("CELPH")                ,120    ,"center"    ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
			["OFFICE_PHONE_NO" ,resource.getMessage("TELNM")                ,120    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["FAX_NO"          ,resource.getMessage("FAX")                  ,120    ,"center"    ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0]
        ],
        initGrid_1 : function() {
 			var dg_1 = grid.getObject("MMA009_01_grid_01");
 			
 			var params = form.handle.getElementsParam("MMA009_01_form_01");
 			if(params.USER_ID != "" || params.NAME != "") {
            	grid.init.setURL("/mm/pop/mmA009_01/selectMainList");
            } else {
                grid.init.setURL("#");
            }
 			grid.init.setQueryParams(params);
 			grid.init.setPage(true);
 			grid.init.setFitColumns(true);
 			// 이벤트 설정
 			grid.event.setOnDblClickRow(dg_1);
 			
 			Theme.defaultGrid(dg_1, this.header);
 		}
    },
    event : {
 		onDblClick_MMA009_01_grid_01 : function(rowData) {
 			var pid = form.handle.getValue("MMA009_01_form_01", "TARGET_PID");
 			
             if (!oUtil.isNull(pid)) {
             	var pro_id = eval("window." + pid + ".dialog");
             	
                 if (typeof(pro_id["callByMMA009_01"]) == "function") {
                     pro_id["callByMMA009_01"](rowData);
                 }
             }
 		}
    },
    control : {// 업무구현
     	selectMainList : function() {
     		if(!form.handle.isValidate("MMA009_01_form_01")) return;
     		
     		var dg_1 = grid.getObject("MMA009_01_grid_01");
     		var params = form.handle.getElementsParam("MMA009_01_form_01");
     		
     		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA009_01/selectMainList", params);
     	},
     	selectRowValue : function() {
     		var dg_1 = grid.getObject("MMA009_01_grid_01");
     		var rowData = grid.handle.getSelectedRowData(dg_1);
     		
     		if(!grid.handle.isSelected(dg_1)) {
     			alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
     		}
     		
     		MMA009_01.event.onDblClick_MMA009_01_grid_01(rowData);
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
    }
 	
 };
