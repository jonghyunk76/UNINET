/******************************************************************************************
 * 작성자 : Csy
 * Program Id : MM-A007_01
 * _01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA007_01.config.applyFtaNation();
	MMA007_01.init.initComponent();
}

var MMA007_01 = {
	init : {
        initComponent : function() {
        	MMA007_01.combobox.initCombo_1();
            MMA007_01.combobox.initCombo_2();
            
        	MMA007_01.datagrid.initGrid_1();
        }
    },
	calendar : {}, // 달력 그리드 생성
	combobox : {
		data_1 : [
	         {CODE:"CUSTOMER_NAME", NAME:resource.getMessage("CSTMR, NAME")}
	        ,{CODE:"CUSTOMER_CD", NAME:resource.getMessage("CSTMR, CODE")}
	        ,{CODE:"BUSINESS_NO", NAME:resource.getMessage("BZRGN")}
	    ],
	    initCombo_1 : function() {
	        var obj = combo.getObject("MMA007_01_form_01", "schKeyField");
	
	        combo.init.setData(this.data_1);
	        combo.init.setValueField("CODE");
	        combo.init.setNameField("NAME");
	
	        combo.create(obj);
	    },
	    initCombo_2 : function() {
	        var obj = combo.getObject("MMA007_01_form_01", "schKeyLike");
	
	        combo.init.setURL("/mm/cbox/selectStandardCode");
	        combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
	        combo.init.setValueField("CODE");
	        combo.init.setNameField("NAME");
	
	        combo.create(obj);
	    }
	}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
			["CUSTOMER_CD"        ,resource.getMessage("CSTMR, CODE")        ,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["CUSTOMER_NAME"      ,resource.getMessage("CSTMR, NAME")        ,200    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["BUSINESS_NO"        ,resource.getMessage("BZRGN")              ,100    ,"center"      ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
		    ["ADDRESS"            ,resource.getMessage("ADDRS")              ,200    ,"left"      ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
			["ADDRESS_ENG"        ,resource.getMessage("ADDRS")              ,200    ,"left"      ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0]
         ],
         initGrid_1 : function() {
 			var dg_1 = grid.getObject("MMA007_01_grid_01");
 			
 			grid.init.setPage(true);
 			grid.init.setFitColumns(true);
 			
 			grid.event.setOnDblClickRow(dg_1);
 			
 			Theme.defaultGrid(dg_1, this.header);
 		}
     },
     event : {
 		onDblClick_MMA007_01_grid_01 : function(rowData) {
 			var pid = form.handle.getValue("MMA007_01_form_01", "TARGET_PID");
 			
             if (!oUtil.isNull(pid)) {
             	var pro_id = eval("window." + pid + ".dialog");
             	
                 if (typeof(pro_id["callByMMA007_01"]) == "function") {
                     pro_id["callByMMA007_01"](rowData);
                 }
             }
 		}
     },
     control : {// 업무구현
     	selectMainList : function() {
     		// 검색창의 validation 체크결과를 비교한다.
     		if(!form.handle.isValidate("MMA007_01_form_01")) return;
     		
     		var dg_1 = grid.getObject("MMA007_01_grid_01");
     		var params = form.handle.getElementsParam("MMA007_01_form_01");
     		
     		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA007_01/selectMainList", params);
     	},
     	selectRowValue : function() {
     		var dg_1 = grid.getObject("MMA007_01_grid_01");
     		var rowData = grid.handle.getSelectedRowData(dg_1);
     		
     		if(!grid.handle.isSelected(dg_1)) {
     			alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
     		}
     		
     		MMA007_01.event.onDblClick_MMA007_01_grid_01(rowData);
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
      		var columns1 = MMA007_01.datagrid.header;
      		
  			if(SESSION.FTA_NATION == "MX") {
  				grid.util.changeHeaderHidden(columns1 , "ADDRESS", false);
  				grid.util.changeHeaderHidden(columns1 , "ADDRESS_ENG", true);
  			} else {
  				grid.util.changeHeaderHidden(columns1 , "ADDRESS", true);
  				grid.util.changeHeaderHidden(columns1 , "ADDRESS_ENG", false);
  			} 
  		}
     }
 	 
 };
