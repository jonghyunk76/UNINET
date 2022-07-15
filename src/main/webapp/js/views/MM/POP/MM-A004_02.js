/******************************************************************************************
 * 작성자 : 이재욱
 * Program Id : MM-A004_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA004_02.config.applyFtaNation();
    MMA004_02.init.initComponent();
}

var MMA004_02 = {
        
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA004_02.calendar.initCalendar_1();
        	
            MMA004_02.datagrid.initGrid_1();
            MMA004_02.datagrid.initGrid_2();
            
            var fta_cd = form.handle.getValue("MMA004_02_form_01", "FTA_CD");
            if(fta_cd == "OPT1"){ // 보고서 전용 ( 서명카드 )
            	$("#MMA004_02_out_list").hide();
            	$("#MMA004_02_out_list").css("width","0");
            }
        }
    },
    // 달력 그리드 생성
    calendar : {
    	initCalendar_1 : function() {
			var obj_01 = calendar.getObject("MMA004_02_form_01", "ISSUE_DATE");
			
			calendar.init.setInitDate("none");
			if(SESSION.FTA_NATION == "VN") {
				calendar.init.setReadonly(false);
			} else {
				calendar.init.setReadonly(true);
			}
			
			calendar.create(obj_01);
		}
    },
    // 콤보박스 그리드 생성
    combobox : {},
    datagrid : { // 데이터 그리드 생성
        header1 : [
            ["CHKECK"        ,resource.getMessage("CHK")            ,100    ,"center"     ,"center"    ,true       ,false    ,null         ,true     ,null    ,0        ,0],
            ["DOC_NAME"      ,resource.getMessage("TXT_DOC_NAME")   ,200    ,"left"       ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
            ["DOC_NAME_ENG"  ,resource.getMessage("TXT_DOC_NAME")   ,200    ,"left"       ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
            ["DOC_FILE_NAME" ,resource.getMessage("TXT_DOC_FILE")   ,200    ,"center"     ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0]
        ],
        header2 : [
            ["DOC_NAME"      ,resource.getMessage("TXT_DOC_NAME")   ,200    ,"left"       ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
            ["DOC_NAME_ENG"  ,resource.getMessage("TXT_DOC_NAME")   ,200    ,"left"       ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
            ["DOC_FILE_NAME" ,resource.getMessage("TXT_DOC_FILE")   ,200    ,"center"     ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0]
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("MMA004_02_grid_01");
            var params = form.handle.getElementsParam("MMA004_02_form_01");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/mmA004_01/selectCoDocList");
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            grid.init.setCallBackFunction("loadAtferGrid");
            grid.init.setRownumbers(false);
            grid.init.setShowHeader(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            Theme.defaultGrid(dg_1, this.header1);
        },
        initGrid_2 : function() {
            var dg_1 = grid.getObject("MMA004_02_grid_02");
            var params = form.handle.getElementsParam("MMA004_02_form_01");
            
            params.FTA_CD = "KROP";
            params.FTA_GROUP_CD = "";
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/mmA004_01/selectCoDocList");
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            Theme.defaultGrid(dg_1, this.header2);
        }
    },
    // 이벤트 처리
    event : {
        loadAtferGrid : function() {
            var dg_1 = grid.getObject("MMA004_02_grid_01");
            
            if(grid.handle.getRowCount(dg_1) > 0) {
                grid.handle.checkRow(dg_1, true, 0);
                
                MMA004_02.control.executeIssueCoDoc();
            }
        },
        updateCoIssueDate : function(data) {
        	MMA004_02.control.executeIssueCoDoc();
        }
    },
    control : {
        executeIssueCoDoc : function() {
            var dg_1 = grid.getObject("MMA004_02_grid_01");
            
            if(grid.handle.getRowCount(dg_1) <= 0) {
                return;
            } else {
                var rows = grid.handle.getCheckedRowsData(dg_1);
                var params = form.handle.getElementsParam("MMA004_02_form_01");
                
                params.GRID_ID = "MMA004_02_grid_01";
                
                $('#MMA004_02_panel_01').panel({href:rows[0].URL, queryParams:params});
            }
        },
        executeIssueEvidenceDoc : function() {
    		var dg_1 = grid.getObject("MMA004_02_grid_02");
            
            if(grid.handle.getRowCount(dg_1) <= 0) {
                return;
            } else {
            	var rows = grid.handle.getSelectedRowData(dg_1);
            	var params = form.handle.getElementsParam("MMA004_02_form_01");
                
            	params.GRID_ID = "MMA004_02_grid_02";
            	
            	$('#MMA004_02_panel_01').panel({href:rows.URL, queryParams:params});
            }
        },
        updateCoIssueDate : function() {
        	if(!form.handle.isValidate("MMA004_02_form_01")) return;
    		
    		$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_UPDATE"), function(flag) {
                if(flag) {
                	var obj = form.getObject("MMA004_02_form_01");
    	    		
	    			form.init.setURL("/mm/pop/mmA004_02/updateCoIssueDate");
	    			
	    			form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
    	    		form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
    	    		form.init.setCallBackFunction("updateCoIssueDate");
    	    		
    	    		form.submit(obj);
                }
            });
        }
    },
    dialog : {},
    // 화면 UI 변경
    ui : {},
    // 파일 input 엘리먼트 구현
    file : { },
    // 엑셀다운로드 구현
    excel : { },
    config : {
    	applyFtaNation : function() {
    		var columns1 = MMA004_02.datagrid.header1;
    		var columns2 = MMA004_02.datagrid.header2;
    		
			if(SESSION.DEFAULT_LANGUAGE == "KR") {
				grid.util.changeHeaderHidden(columns1 , "DOC_NAME", false);
				grid.util.changeHeaderHidden(columns1 , "DOC_NAME_ENG", true);
				
				grid.util.changeHeaderHidden(columns2 , "DOC_NAME", false);
				grid.util.changeHeaderHidden(columns2 , "DOC_NAME_ENG", true);
			} else {
				grid.util.changeHeaderHidden(columns1 , "DOC_NAME", true);
				grid.util.changeHeaderHidden(columns1 , "DOC_NAME_ENG", false);
				
				grid.util.changeHeaderHidden(columns2 , "DOC_NAME", true);
				grid.util.changeHeaderHidden(columns2 , "DOC_NAME_ENG", false);
			}
			
			if(SESSION.FTA_NATION == "VN") {
				form.util.setVisible("MMA004_02_span_01", true);
			}
		}
    }
    
};