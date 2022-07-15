/******************************************************************************************
 * 작성자 : 
 * Program Id : MM-A005_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA005_02.config.applyFtaNation();
	MMA005_02.init.initComponent();
}

var MMA005_02 = {
	init : {
        initComponent : function() {
        	MMA005_02.calendar.initCalendar_1();
        	MMA005_02.calendar.initCalendar_2();
        	
        	MMA005_02.combobox.initCombo_1();
        	MMA005_02.combobox.initCombo_2();
        	
        	MMA005_02.datagrid.initGrid_1();
        }
    },
    // 달력 그리드 생성
	calendar : {
		initCalendar_1 : function() {
        	var yyyymm = (oUtil.isNull(SESSION.WORK_YYYY_MM))?calendarMethod.util.toMonth2String():(SESSION.WORK_YYYY_MM);
        	var toyymm = calendarMethod.util.toMonth2String();
        	var initDate;
        	
        	if(oUtil.isNull(yyyymm) || yyyymm == toyymm) {
        		initDate = toyymm.substring(0, 4)+"01";
        	} else {
        		initDate = yyyymm;
        	}
        	
            var obj_01 = calendar.getObject("MMA005_02_form_01", "SCH_FROM_DATE_CAL");
            // 달력타입을 월로 지정
            calendar.init.setDateType("month");
            calendar.init.setInitDate(calendar.util.getFirstDay2String(initDate, "-"));
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	var yyyymm = (oUtil.isNull(SESSION.WORK_YYYY_MM))?calendarMethod.util.toMonth2String():(SESSION.WORK_YYYY_MM);
            var obj_01 = calendar.getObject("MMA005_02_form_01", "SCH_TO_DATE_CAL");
            
            // 달력타입을 월로 지정
            calendar.init.setDateType("month");
            calendar.init.setInitDate(calendar.util.getFirstDay2String(yyyymm, "-"));
            
            calendar.create(obj_01);
        }
	},
	// 콤보박스 그리드 생성
	combobox : {
		data_1 : [
			{CODE : "FBD.ITEM_CD", NAME : resource.getMessage("MATAL_CODE")}, 
			{CODE : "I.ITEM_NAME", NAME : resource.getMessage("MATAL_NAME")}
		],
		initCombo_1 : function() {
	        var obj = combo.getObject("MMA005_02_form_01", "schKeyField");
	        
	        combo.init.setData(this.data_1);
	        combo.init.setValueField("CODE");
	        combo.init.setNameField("NAME");
	
	        combo.create(obj);
	    },
	    initCombo_2 : function() {
	        var obj = combo.getObject("MMA005_02_form_01", "schKeyLike");
	
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
		],	  
		initGrid_1 : function() {
			var dg_1 = grid.getObject("MMA005_02_grid_01");
			
			grid.init.setPage(false);
			grid.init.setFitColumns(true);
			grid.init.setEmptyMessage(false);
			// 이벤트 설정
			grid.event.setOnDblClickRow(dg_1);
			
			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
		onDblClick_MMA005_02_grid_01 : function(rowData) {
			var pid = form.handle.getValue("MMA005_02_form_01", "TARGET_PID");
            if (!oUtil.isNull(pid)) {
            	var pro_id = eval("window." + pid + ".dialog");
            	
                if (typeof(pro_id["callByMMA005_02"]) == "function") {
                    pro_id["callByMMA005_02"](rowData);
                }
            }
		}
    },
    control : {// 업무구현
    	selectMainList : function() {
    		if(!form.handle.isValidate("MMA005_02_form_01")) return;
    		
    		var dg_1 = grid.getObject("MMA005_02_grid_01");
    		var params = form.handle.getElementsParam("MMA005_02_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA005_02/selectBomItemList", params);
    	},
    	selectRowValue : function() {
    		var dg_1 = grid.getObject("MMA005_02_grid_01");
    		var rowData = grid.handle.getSelectedRowData(dg_1);
    		
    		if(!grid.handle.isSelected(dg_1)) {
                // 그리드에서 열을 선택해 주세요.
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
    		}
    		
    		MMA005_02.event.onDblClick_MMA005_02_grid_01(rowData);
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
     		var columns1 = MMA005_02.datagrid.header;
     		
 			if(SESSION.FTA_NATION == "KR") {	
 				grid.util.changeHeaderHidden(columns1 , "ITEM_NAME", false);
 				grid.util.changeHeaderHidden(columns1 , "ITEM_NAME_ENG", true);
 			} else {
 				grid.util.changeHeaderHidden(columns1 , "ITEM_NAME", true);
 				grid.util.changeHeaderHidden(columns1 , "ITEM_NAME_ENG", false);
 			}
 			
 			if(PART_NUM_VIEW_YN == "Y") {
           	 var columns = MMA005_02.datagrid.header;

                
                grid.util.changeHeaderHidden(columns , "TEMP_COLUMN3", false); // Part No.
                grid.util.changeHeaderHidden(columns , "TEMP_COLUMN4", false); // ECN Code

            }
 		}
     }
	
};
