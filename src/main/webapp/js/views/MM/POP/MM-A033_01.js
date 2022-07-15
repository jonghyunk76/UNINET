/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A033_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA033_01.config.applyFtaNation();
    MMA033_01.init.initComponent();
}

var MMA033_01 = {
    init : {
        initComponent : function() {
            MMA033_01.combobox.initCombo_1();
            MMA033_01.combobox.initCombo_2();
            MMA033_01.combobox.initCombo_3();
            
            MMA033_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {
        initCalendar_1 : function() {
        }
    }, // 달력 그리드 생성
    combobox : {
    	data_1 : [
    		{CODE:"ITEM_CD", NAME:resource.getMessage("LTIT,CODE")},
    		{CODE:"ITEM_NAME", NAME:resource.getMessage("LTIT,NAME")},
	        {CODE:"CUSTOMER_NAME", NAME:resource.getMessage("CSTMR, NAME")},
	        {CODE:"CUSTOMER_CD", NAME:resource.getMessage("CSTMR, CODE")},
	        {CODE:"BUSINESS_NO", NAME:resource.getMessage("BZRGN")}
	    ],
	    initCombo_1 : function() {
            var obj = combo.getObject("MMA033_01_form_01", "schKeyField");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMA033_01_form_01", "schKeyLike");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_3 : function() {
            var obj = combo.getObject("MMA033_01_form_01", "CUSTOMER_SALES_TYPE");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CUSTOMER_SALES_TYPE", ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        }
    },
    datagrid : {
		header_1 : [
			["CUSTOMER_CD"			,resource.getMessage("CSTMR, CODE")			,100    ,"left"     ,"center"    ,true       ,false    	,null   ,null     ,null],
			["CUSTOMER_NAME"		,resource.getMessage("CSTMR,NAME")			,100    ,"left"     ,"center"    ,true       ,false    	,null   ,null     ,null],
			["ITEM_CD"				,resource.getMessage("CONPY, LTIT,CODE")			,100    ,"center"   ,"center"    ,true       ,false  	,null   ,null     ,null],
			["ITEM_NAME"			,resource.getMessage("CONPY, LTIT,NAME")			,150    ,"left"     ,"center"    ,true       ,false		,null   ,null     ,null],
			["HS_CODE"				,resource.getMessage("TXT_STAWN")			,100    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["CUSTOMER_ITEM_CD"		,resource.getMessage("CUSTM,LTIT, CODE")	,100    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["CUSTOMER_HS_CODE"		,resource.getMessage("CUSTM, TXT_STAWN")	,100    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["CUSTOMER_NALADISA_CODE" ,resource.getMessage("CUSTM, TXT_NALADISA_CODE"),140 ,"center" ,"center"    ,true       ,false    	,null   ,null     ,null],
			["CUSTOMER_SALES_TYPE_NAME"	,resource.getMessage("SALE, TYPE")		,100    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["FTA_NAME"	   		    ,resource.getMessage("APYDT,AGRET")			,100    ,"left"     ,"center"    ,true       ,false    	,null   ,null     ,null],
			["START_DATE"			,resource.getMessage("START,DATE")			,80    	,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["END_DATE"				,resource.getMessage("TXT_END,DATE")		,80    	,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null]
	    ],
        initGrid_1 : function() {
        	var dg_1 = grid.getObject("MMA033_01_grid_01");
            
            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA033_01_grid_01 : function(rowData) {
            var pid = form.handle.getValue("MMA033_01_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA033_01"]) == "function") {
                    pro_id["callByMMA033_01"](rowData);
                }
            }
        },
        callBackNoData : function(data) {}
    },
    control : {// 업무구현
        selectMainList : function() {
            var dg_1 = grid.getObject("MMA033_01_grid_01");
            var params = form.handle.getElementsParam("MMA033_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA033_01/selectCustomerItemList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA033_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
            }
            
            MMA033_01.event.onDblClick_MMA033_01_grid_01(rowData);
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
     		var columns2 = MMA033_01.datagrid.header_1;
     		
 			if(SESSION.FTA_NATION != "MX") {
 				grid.util.changeHeaderHidden(columns2 , "CUSTOMER_NALADISA_CODE", true);
 			}
      	}
     }
    
};
