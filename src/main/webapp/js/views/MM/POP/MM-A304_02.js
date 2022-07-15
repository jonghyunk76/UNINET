/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A304_02
 * 작업일자 : 2021.09.10
 ******************************************************************************************/
function onLoadPage() {
    MMA304_02.config.applyFtaNation();
    MMA304_02.init.initComponent();
}

var MMA304_02 = {
    init : {
    	flag : form.handle.getValue("MMA304_02_form_01", "flag"),
    	initComponent : function() {
    		if(MMA304_02.init.flag != "insert") {
    			MMA304_02.ui.changeUpdateUI();
        	} else {
        		MMA304_02.ui.changeAutoCheck();
        	}
    		
    		MMA304_02.calendar.initCalendar_1();
    		MMA304_02.datagrid.initGrid_1();
        	
        	// 초기화면에 보여줄 데이터 조회
        	MMA304_02.control.selectCoMasterInfo();
        }
    },
    // 달력 생성
    calendar : {
    	initCalendar_1 : function() {
        	var objDate = calendar.getObject("MMA304_02_form_01", "ISSUE_DATE");
        	
        	calendar.init.setRequired(true);
        	if(MMA304_02.init.flag == "insert") {
        		calendar.init.setReadonly(false);
        	} else {
        		calendar.init.setReadonly(true);
        	}
        	
            calendar.create(objDate);
        }
    },
    // 콤보박스 생성
    combobox : {},
    // 데이터 그리드 생성
    datagrid : {
    	header1 : [
    		["CHECK",         		    "CHECK"                                     , 100, "center", "center", true, false, null, true, null, 0, 0, true],
        	["INVOICE_NO",              "VAT No."                                   , 120, "center" ,"center" ,true ,false ,null ,null ,null ,0 ,0],
        	["INVOICE_DATE",            resource.getMessage("SALES, DATE")          , 100, "center", "center", true, false, null, null, {format:'date'}, 0, 0],
        	["PRODUCT_ITEM_CD"	,       resource.getMessage("LTIT, CODE")			, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["CUSTOMER_ITEM_NAME",     	resource.getMessage("LTIT,NAME")    		, 180, "left"  , "center", true, false, null, null, null, 0, 0],
            ["CUSTOMER_ITEM_CD",       	resource.getMessage("CSTMR,LTIT, CODE") 	, 130, "center", "center", true, false, null, null, null, 0, 0],
            ["HS_CODE",       			resource.getMessage("HS,CODE")          	, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["FTA_CD",        			resource.getMessage("AGRET,CODE")       	, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["FTA_NAME",      			resource.getMessage("AGRET,NAME")       	, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["RULE_CONTENTS", 			resource.getMessage("TXT_RULE_CODE_NAME")   , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["RVC_RATE",      			resource.getMessage("RVC,RATE,(%)") 	    , 100, "right" , "center", true, false, null, null, null, 0, 0],
            ["ORIGIN_YN_NAME",          resource.getMessage("TXT_MEET_YN")      	, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["ORIGIN_NATION_CD"     ,resource.getMessage("ORG_COTRY")         ,120    ,"center"    ,"center"    ,true       ,false    ,{type:"text"}         ,null     ,null    ,0        ,0],
            ["FM_SALES_QTY",            resource.getMessage("QTY"),                   120, "right",  "center", true, false, null, null, {format:'quantity'}, 0, 0],
         	["FM_QTY_UNIT",             resource.getMessage("UNIT"),             	  100, "center", "center", true, false, null, null, null, 0, 0],
        	["FM_FOB_DECLARE_AMOUNT",   resource.getMessage("AMUNT")+"(FOB)",         100, "right",  "center", true, false, null, null, {format:'amount'}, 0, 0],
         	["FM_CURRENCY",             resource.getMessage("CURCY")		        , 100 ,"center", "center", true, false, null, null, null, 0, 0],
    		["STATUS_NAME",             resource.getMessage("DTSTA, STAUS")         , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["ORIGIN_NATION_SRC_NAME", resource.getMessage("TXT_ORIGIN_RULE_DESC"),        250, "left", "center", false, false, null, null, null, 0, 0],
            ["ERROR_REASON_NAME",       resource.getMessage("COMENT")               , 300, "left", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA304_02_form_01");
        	var dg_2 = grid.getObject("OR5202_02_grid_01");
        	var dg_1 = grid.getObject("MMA304_02_grid_01");
        	
            if(MMA304_02.init.flag == "insert") {
            	if(form.handle.isChecked("MMA304_02_form_02", "cmCheckbox")) {
   					params.cmCheckbox = "Y";
   	      		} else {
   	      			params.cmCheckbox = "N";
   	      		}
            	
        		params.gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2));
            	
        		form.handle.setValue("MMA304_02_form_01", "gridData", params.gridData);
        		
        		grid.init.setURL("/mm/pop/mmA204_01/selectDeterminateList");
                grid.init.setEditMode(true, "cell");
            } else {
            	grid.init.setURL("/mm/pop/mmA204_01/selectCOOriginList");
                grid.init.setEditMode(false, "cell");
            }
            grid.init.setPage(true);
            grid.init.setShowConfigPage(true);
            grid.init.setPageLayout(["resizeColumn"]);
            grid.init.setShowEditorFilm(false);
            grid.init.setQueryParams(params);
            grid.init.setSingleSelect(true);
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(true);
            grid.init.setCheckOnBox(true);
            grid.init.setMergeData(["INVOICE_NO", "INVOICE_DATE", "CUSTOMER_ITEM_CD" ,"CUSTOMER_ITEM_NAME", "PRODUCT_ITEM_CD", "HS_CODE"],["INVOICE_NO", "INVOICE_DATE", "CUSTOMER_ITEM_CD", "PRODUCT_ITEM_CD", "HS_CODE"]);
            grid.init.setCallBackFunction("loadAtferGrid");
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
        	Theme.defaultGrid(dg_1, this.header1,this.header1_2);
        }
    },
    // 이벤트처리
    event : {
    	loadAtferGrid : function(data) {
    		var dg_1 = grid.getObject("MMA304_02_grid_01");
    		var rows = data.rows;
    		
    		for(var i=0; i < rows.length; i++) {
    			if(rows[i]["ORIGIN_YN"] == "Y") {    				
    				grid.handle.checkRow(dg_1, true, i);
    			} else {
    				grid.handle.checkRow(dg_1, false, i);
    			}
    		}
//    		grid.handle.checkAll(dg_1, true);
    	},
    	setCoIssueInfo : function(data) {
    		form.handle.loadData("MMA304_02_form_01", data);
    		
    		MMA304_02.control.selectStempCIImg("2");
    	},
    	changeUpdateUI : function(data) {
        	if(!oUtil.isNull(data.CO_DOC_NO)) {
        		MMA304_02.init.flag = "update";
        		
        		form.handle.setValue("MMA304_02_form_01", "flag", "update");
        		form.handle.setValue("MMA304_02_form_01", "CO_DOC_NO", data.CO_DOC_NO);
        		
        		var dg_1 = grid.getObject("MMA304_02_grid_01");
        		
        		grid.handle.setHideColunm(dg_1, "CHECK");
       			grid.handle.setHideColunm(dg_1, "STATUS_NAME");
        		
       			MMA304_02.ui.changeUpdateUI();
       			MMA304_02.control.selectMainList();
        		
        		var pid = form.handle.getValue("MMA304_02_form_01", "TARGET_PID");
                
                if (!oUtil.isNull(pid)) {
                    var pro_id = eval("window." + pid + ".control");
                    var rstParam = new Object();
                    
                    if (typeof(pro_id["selectMainList"]) == "function") {
                        pro_id["selectMainList"](null, "m");
                    }
                }
        	}
        }
    },
    // 업무구현
    control : {
    	selectCoMasterInfo : function() {
    		var obj = form.getObject("MMA304_02_form_01");
    		
    		if(MMA304_02.init.flag == "insert") {
    			form.init.setURL("/mm/pop/mmA204_01/selectExportNProducerInfo");
    		} else {
    			form.init.setURL("/mm/pop/mmA204_01/selectCOIssueInfo");
    		}
            form.init.setCallBackFunction("setCoIssueInfo");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectMainList : function() {
     		var params = form.handle.getElementsParam("MMA304_02_form_01");
     		var dg_2 = grid.getObject("OR5202_02_grid_01");
        	var dg_1 = grid.getObject("MMA304_02_grid_01");
     		
   			if(MMA304_02.init.flag == "insert") {
   				if(form.handle.isChecked("MMA304_02_form_02", "cmCheckbox")) {
   					params.cmCheckbox = "Y";
   	      		} else {
   	      			params.cmCheckbox = "N";
   	      		}
   				
   				params.gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2));
   				
   				grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectDeterminateList", params);
   			} else {
   				grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectCOOriginList", params);
   			}
     	},
     	issueReport : function() {
     		if(form.handle.getValue("MMA304_02_form_01", "chkAuto") == "N"
    			&& oUtil.isNull(form.handle.getValue("MMA304_02_form_01", "CO_DOC_NO"))) {
        		// 증명서 발급번호 필수 체크
    			alert(resource.getMessage("TXT_DO_NO_IS_REQUIRED_FILED"));
    			return;
    		} else {
        		// 원산지 정보 수집
        		var dg_1 = grid.getObject("MMA304_02_grid_01");
        		
        		if(!grid.handle.isChecked(dg_1)) {
        			alert(resource.getMessage("MSG_NO_CHECK") + " ("+resource.getMessage("TXT_ITEMBY_ORG_INFO")+")");
        			return;
        		}
        		
        		var rows = grid.handle.getCheckedRowsData(dg_1);
        		var errorMsg;
        		var errorIdx;
        		
        		// 원산지 판정내역에 오류가 있거나, 불충족인 경우에는 발급할 수 없도록 한다.
        		for(var i=0; i < rows.length; i++) {
        			if(rows[i].STATUS == "9") {
        				alert(resource.getMessage("MSG_CO_ISUUE_ERROR"));
        				grid.handle.selectRowIndex(dg_1, i);
        				return;
        			}
        			
        			if(rows[i].ORIGIN_YN != "Y") {
        				alert(resource.getMessage("MSG_CO_ISSUE_NOORGIN"));
        				grid.handle.selectRowIndex(dg_1, i);
        				return;
        			}
        			
        			if(oUtil.isNull(errorMsg) && rows[i].ERROR_REASON.startsWith("W00")) {
        				errorMsg = rows[i].ERROR_REASON_NAME;
        				errorIdx = i;
        			}
        		}
        		
        		// 필드별 유효성 체크
        		if(!form.handle.isValidate("MMA304_02_form_01")) return;
        		
        		// 비고란에 경고문구가 있는 경우 계속진행할지 확인하도록 수정
        		if(oUtil.isNull(errorMsg)) {
        			MMA304_02.dialog.openDialog_1("insert");
        		} else {
	        		$.messager.confirm(CNFIR, resource.getMessage("TXT_DETERMINE_RESULT")+": "+errorMsg+"<br><br>"+resource.getMessage("TXT_CONTINUE_QUESTION"), function(flag) {
		                if(flag) {
		                	MMA304_02.dialog.openDialog_1("insert");
		                } else {
		                	grid.handle.selectRowIndex(dg_1, errorIdx);
		                	grid.handle.gotoCell(dg_1, "ERROR_REASON_NAME", errorIdx);
		                }
		            });
        		}
        	}
        },
        selectStempCIImg : function(data) {
        	var obj = form.getObject("MMA304_02_form_01", "MMA304_02_form_01_SIGN_CARD");
        	var src = "/fm/sm/SM7001_01/selectStempCIImg/"+data; // data = 1:Logo, 2:Stamp
        	
        	if(!oUtil.isNull(obj)) obj.removeAttr("src");
        	
        	obj.removeAttr("src").attr("src", src); 
        }
    },
    // 팝업 생성
    dialog : {
    	openDialog_1 : function(flag) {
      		var dg_3 = dialog.getObject("MMA004_03_dailog_01");
      		var params = form.handle.getElementsParam("MMA304_02_form_01");
      		
      		params.TARGET_PID = "MMA304_02";
      		params.flag = flag;
      		
      		dialog.init.setWidth(700);
      		dialog.init.setHeight(500);
      		dialog.init.setURL("/mm/pop/mmA004_03");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_03"));
      		
      		dialog.open(dg_3);
      	},
      	callByMMA004_03 : function(data) {
      		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_ISSUE_NOT_MODIFY"), function(flag) {
            	if(flag) {
            		var dl_3 = dialog.getObject("MMA004_03_dailog_01");
            		var dg_1 = grid.getObject("MMA304_02_grid_01");
            		var rows = grid.handle.getCheckedRowsData(dg_1);
            		
            		dialog.handle.close(dl_3);
            		
            		form.handle.setValue("MMA304_02_form_01", "gridRows", grid.util.convertJson2Rows(rows)); 
    	        	
            		if(!oUtil.isNull(data)) {
    	        		form.handle.setValue("MMA304_02_form_01", "REASON_TITLE", data.REASON_TITLE);
    	        		form.handle.setValue("MMA304_02_form_01", "REQUESTER", data.REQUESTER);
    	        		form.handle.setValue("MMA304_02_form_01", "REQUEST_DATE", data.REQUEST_DATE);
    	        		form.handle.setValue("MMA304_02_form_01", "REASON_CONTENTS", data.REASON_CONTENTS);
    	        	}
            		
            		var obj = form.getObject("MMA304_02_form_01");
            		
            		form.init.setURL("/fm/or/or5201_01/insertCoIssueInfo");
    	        	form.init.setValidationFlag(true);
    	        	form.init.setProgressFlag(true);
    	        	form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
    	        	form.init.setCallBackFunction("changeUpdateUI");
    	        	
    	        	form.submit(obj);
            	}
            });
      	},
      	openDialog_2 : function() {
      		var dg_2 = dialog.getObject("MMA004_02_dailog_01");
      		var params = form.handle.getElementsParam("MMA304_02_form_01");
      		
      		dialog.init.setWidth(960);
      		dialog.init.setHeight(700);
      		dialog.init.setURL("/mm/pop/mmA004_02");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_02"));
      		
      		dialog.open(dg_2);
      	},
      	openDialog_3 : function(flag) {
      		var dg_3 = dialog.getObject("MMA004_03_dailog_01");
      		var params = form.handle.getElementsParam("MMA304_02_form_01");
      		
      		params.TARGET_PID = "MMA304_02";
      		params.flag = flag;
      		
      		dialog.init.setWidth(700);
      		dialog.init.setHeight(500);
      		dialog.init.setURL("/mm/pop/mmA004_03");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_03"));
      		
      		dialog.open(dg_3);
      	}
    },
    // 화면 처리
    ui : {
    	changeAutoCheck : function() {
    		if(form.handle.isChecked("MMA304_02_form_01", "chkAuto")) {
    			form.handle.setValue("MMA304_02_form_01", "chkAuto", "Y");
    			form.handle.setValue("MMA304_02_form_01", "CO_DOC_NO", "");
    			form.handle.disabled("MMA304_02_form_01", "CO_DOC_NO", true);
    		} else {
    			form.handle.setValue("MMA304_02_form_01", "chkAuto", "N");
    			form.handle.disabled("MMA304_02_form_01", "CO_DOC_NO", false);
    		}
    	},
    	changeUpdateUI : function() {
    		form.util.setVisible("MMA304_02_btn_13", true);
    		form.util.setVisible("MMA304_02_btn_15", true);
    		form.util.setVisible("MMA304_02_span_01", true);
    		form.util.setVisible("MMA304_02_span_02", true);
    		form.util.setVisible("MMA304_02_btn_12", false);
    		form.util.setVisible("MMA304_02_form_01_chkAuto", false);
    		form.handle.disabled("MMA304_02_form_01", "CO_DOC_NO", false);
    		
    		form.handle.closeAll("MMA304_02_form_01");
    	}
    },
    // 파일처리
    file : {},
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA304_02_grid_01");
            var fobj = form.getObject("MMA304_02_form_01");
            
            if(MMA304_02.init.flag == "insert") {
            	form.init.setURL("/mm/pop/mmA204_01/selectDeterminateList");
            } else {
            	form.init.setURL("/mm/pop/mmA204_01/selectCOOriginList");
            }
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("TXT_ITEMBY_ORG_INFO"), resource.getMessage("LIST"));
        }
    },
    // 초기 설정값 조정
    config : {
        applyFtaNation : function() {
        	if(MMA304_02.init.flag != "insert") {
        		var columns1 = MMA304_02.datagrid.header1;
		
		        grid.util.changeHeaderHidden(columns1 , "CHECK", true);
		        grid.util.changeHeaderHidden(columns1 , "STATUS_NAME", true);
        	}
        }
    }
};