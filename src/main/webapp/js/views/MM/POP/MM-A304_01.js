/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A304_01
 * 작업일자 : 2021.09.10
 ******************************************************************************************/
function onLoadPage() {
    MMA304_01.config.applyFtaNation();
    MMA304_01.init.initComponent();
}

var MMA304_01 = {
    init : {
    	flag : form.handle.getValue("MMA304_01_form_01", "flag"),
    	initComponent : function() {
        	if(MMA304_01.init.flag != "insert") {
        		MMA304_01.ui.changeUpdateUI();
        	} else {
        		MMA304_01.ui.changeAutoCheck();
        	}
        	
        	MMA304_01.calendar.initCalendar_1();
        	MMA304_01.calendar.initCalendar_2();
        	MMA304_01.calendar.initCalendar_3();
        	
        	MMA304_01.datagrid.initGrid_1();
        	
        	// 초기화면에 보여줄 데이터 조회
        	MMA304_01.control.selectCoMasterInfo();
        }
    },
    // 달력 생성
    calendar : {
    	initCalendar_1 : function() {
        	var objDate = calendar.getObject("MMA304_01_form_01", "ISSUE_DATE");
        	
        	calendar.init.setRequired(true);
        	if(MMA304_01.init.flag == "insert") {
        		calendar.init.setReadonly(false);
        	} else {
        		calendar.init.setReadonly(true);
        	}
        	
            calendar.create(objDate);
        },
        initCalendar_2 : function() {
        	var objDate = calendar.getObject("MMA304_01_form_01", "DECLARE_DATE");
        	
        	calendar.init.setRequired(true);
        	calendar.init.setInitDate("none");
        	if(MMA304_01.init.flag == "insert") {
        		calendar.init.setReadonly(false);
        	} else {
        		calendar.init.setReadonly(true);
        	}
        	
            calendar.create(objDate);
        },
        initCalendar_3 : function() {
        	var objDate = calendar.getObject("MMA304_01_form_01", "SHIPPING_DATE");
        	
        	calendar.init.setRequired(true);
        	calendar.init.setInitDate("none");
        	calendar.init.setReadonly(true);
        	
            calendar.create(objDate);
        }
        
    },
    // 콤보박스 생성
    combobox : {},
    // 데이터 그리드 생성
    datagrid : {
    	header1 : [
            ["CHECK",         		"CHECK"                               , 100, "center", "center", true, false, null, true, null, 2, 0, true],
            ["ORG_INFMT",           resource.getMessage("ORG_INFMT"),           100, "center", "center", true, false, null, null, null, 0, 12],
            ["EXP_DLRAT_INFMT",     resource.getMessage("EXP_DLRAT_INFMT"),		160, "center", "center", true, false, null, null, null, 0, 10],
            ["STATUS_NAME",         resource.getMessage("DTSTA_RSULT")        , 100, "center", "center", true, false, null, null, null, 0, 2]
        ],
        header1_2 : [
        	["INVOICE_NO",          resource.getMessage("INVIC, MSSNM")       , 120    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["PRODUCT_ITEM_CD"	,   resource.getMessage("LTIT, CODE")		  , 100, "center", "center", true, false,  null, null, null, 0, 0],
            ["CUSTOMER_ITEM_NAME",  resource.getMessage("LTIT,NAME")    	  , 180, "left"  , "center", true, false, null, null, null, 0, 0],
            ["CUSTOMER_ITEM_CD",    resource.getMessage("CSTMR,LTIT, CODE")   , 130, "center", "center", true, false, null, null, null, 0, 0],
            ["HS_CODE",       		resource.getMessage("HS,CODE")            , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["FTA_CD",        		resource.getMessage("AGRET,CODE")         , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["FTA_NAME",      		resource.getMessage("AGRET,NAME")         , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["RULE_CONTENTS", 		resource.getMessage("TXT_RULE_CODE_NAME") , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["RVC_RATE",      		resource.getMessage("RVC,RATE,(%)") 	  , 100, "right" , "center", true, false, null, null, null, 0, 0],
            ["ORIGIN_YN_NAME",      resource.getMessage("TXT_MEET_YN")        , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["ORIGIN_NATION_CD"     ,resource.getMessage("ORG_COTRY")         ,120    ,"center"    ,"center"    ,true       ,false    ,{type:"text"}         ,null     ,null    ,0        ,0],
            ["ORIGIN_NATION_SRC_NAME", resource.getMessage("TXT_ORIGIN_RULE_DESC"),        250, "left", "center", false, false, null, null, null, 0, 0],
            ["INVOICE_ISSUE_DATE",  resource.getMessage("ISSUE_DATE"),          100, "center", "center", true, false, null, null, {format:'date'}, 0, 0],
            ["DEAL_ITEM_NAME",      resource.getMessage("SALE_GOODS_NAME"),		160, "center", "center", true, false, null, null, null, 0, 0],
            ["INVOICE_QTY",         resource.getMessage("SALE,QTY"),         	120, "right",  "center", true, false, null, null, {format:'quantity'}, 0, 0],
         	["INVOICE_UNIT",        resource.getMessage("UNIT"),             	100, "center", "center", true, false, null, null, null, 0, 0],
        	["INVOICE_AMOUNT",      resource.getMessage("SALE,AMUNT"),          100, "right",  "center", true, false, null, null, {format:'amount'}, 0, 0],
         	["INVOICE_CURRENCY",    resource.getMessage("CURCY")		      , 100 ,"center", "center", true, false, null, null, null, 0, 0],
         	["GROSS_WEIGHT",        resource.getMessage("TXT_TOTAL_WEIGHT"),  	100, "right", "center", true, false, null, null, {format:'quantity'}, 0, 0],
         	["WEIGHT_UNIT",         resource.getMessage("TXT_WEIGHT_UNIT"),   	100, "center", "center", true, false, null, null, null],
         	["PACKING_QTY",         resource.getMessage("PACKG,NUMB"),        	100, 'right', 'center', true, false, null, null, {format:'quantity'}, 0, 0],
            ["PACKING_UNIT",        resource.getMessage("PACKG,UNIT"),        	100, 'center', 'center', true, false, null, null, null, 0, 0],
            ["STATUS_NAME",         resource.getMessage("DTSTA, STAUS")       , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["ERROR_REASON_NAME",   resource.getMessage("COMENT")             , 300, "left", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA304_01_form_01");
        	var dg_2 = grid.getObject(params.TARGET_PID + "_grid_03");
        	var dg_1 = grid.getObject("MMA304_01_grid_01");
        	
        	if(form.handle.isChecked("MMA304_01_form_02", "cmCheckbox")) {
				params.cmCheckbox = "Y";
      		} else {
      			params.cmCheckbox = "N";
      		}
        	
            if(MMA304_01.init.flag == "insert") {
        		if(params.SEARCH_TYPE == "ITEM") { // 고객사별 발급(부모창에서 구해진 값을 바로 입력한다.)
        			if(params.TARGET_PID == "OR5011_01") params.gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2));
        			else params.gridData = grid.util.convertJson2Rows(grid.handle.getAllRows(dg_2));
        		} else { // 인보이스별 발급
        			params.gridData = null;
        		}
            	
        		form.handle.setValue("MMA304_01_form_01", "gridData", params.gridData);
        		
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
            grid.init.setMergeData(["INVOICE_NO", "CUSTOMER_ITEM_CD" ,"CUSTOMER_ITEM_NAME", "PRODUCT_ITEM_CD", "HS_CODE"],["INVOICE_NO", "CUSTOMER_ITEM_CD", "PRODUCT_ITEM_CD", "HS_CODE"]);
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
    		var dg_1 = grid.getObject("MMA304_01_grid_01");
    		var rows = data.rows;
    		
    		// 역내산만 자동으로 체크함
//    		for(var i=0; i < rows.length; i++) {
//    			if(rows[i]["ORIGIN_YN"] == "Y") {
//    				grid.handle.checkRow(dg_1, true, i);
//    			} else {
//    				grid.handle.checkRow(dg_1, false, i);
//    			}
//    		}
    		grid.handle.checkAll(dg_1, true);
    		
    		var datas = grid.handle.getAllRows(dg_1);
    		if(datas.length > 0 && MMA304_01.init.flag != "insert") {
    			form.handle.setValue("MMA304_01_form_01", "FTA_GROUP_CD", datas[0].FTA_GROUP_CD);
    			form.handle.setValue("MMA304_01_form_01", "FTA_GROUP_CD_NAME", datas[0].FTA_GROUP_CD_NAME);
    			
    			form.handle.setValue("MMA304_01_form_01", "FTA_CD", datas[0].FTA_CD);
    			form.handle.setValue("MMA304_01_form_01", "FTA_NAME", datas[0].FTA_NAME);
    		}
    	},
    	setCoIssueInfo : function(data) {
    		form.handle.loadData("MMA304_01_form_01", data);
    		
    		if(data.ORIGIN_RESULT_1 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_1", true);
    		if(data.ORIGIN_RESULT_2 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_2", true);
    		if(data.ORIGIN_RESULT_3 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_3", true);
    		if(data.ORIGIN_RESULT_4 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_4", true);
    		if(data.ORIGIN_RESULT_5 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_5", true);
    		if(data.ORIGIN_RESULT_6 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_6", true);
    		if(data.ORIGIN_RESULT_7 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_7", true);
    		if(data.ORIGIN_RESULT_8 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_8", true);
    		if(data.ORIGIN_RESULT_9 == "Y") form.handle.setChecked("MMA304_01_form_03", "ORIGIN_RESULT_9", true);
    		
    		if(SESSION.FTA_NATION == "KR" && form.handle.getValue("MMA304_01_form_01", "CO_DOC_TYPE") == "N") {
	    		calendar.handle.readonly("MMA304_01_form_01", "APPLY_DATE", true);
				calendar.handle.readonly("MMA304_01_form_01", "END_DATE", true);
				
				form.util.setVisible("MMA304_01_div_05", true);
    		}
    		
    		MMA304_01.control.selectStempCIImg("2");
    	},
    	changeUpdateUI : function(data) {
        	if(!oUtil.isNull(data.CO_DOC_NO)) {
        		MMA304_01.init.flag = "update";
        		
        		form.handle.setValue("MMA304_01_form_01", "flag", "update");
        		form.handle.setValue("MMA304_01_form_01", "CO_DOC_NO", data.CO_DOC_NO);
        		
        		var dg_1 = grid.getObject("MMA304_01_grid_01");
        		
        		grid.handle.setHideColunm(dg_1, "CHECK");
       			grid.handle.setHideColunm(dg_1, "STATUS_NAME");
        		
        		MMA304_01.ui.changeUpdateUI();
        		MMA304_01.control.selectMainList();
        		
        		var pid = form.handle.getValue("MMA304_01_form_01", "TARGET_PID");
                
                if (!oUtil.isNull(pid)) {
                    var pro_id = eval("window." + pid + ".control");
                    var rstParam = new Object();
                    
                    if (typeof(pro_id["selectMainList"]) == "function") {
                        pro_id["selectMainList"](data.CO_DOC_NO, "m");
                    }
                }
        	}
        },
        updateOriginResultInfo : function() {
        	MMA304_01.dialog.openDialog_2();
        }
    },
    // 업무구현
    control : {
    	selectCoMasterInfo : function() {
    		var obj = form.getObject("MMA304_01_form_01");
    		
    		if(MMA304_01.init.flag == "insert") {
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
     		var params = form.handle.getElementsParam("MMA304_01_form_01");
     		var dg_2 = grid.getObject(params.TARGET_PID + "_grid_03");
     		var dg_1 = grid.getObject("MMA304_01_grid_01");
     		
   			if(MMA304_01.init.flag == "insert") {
   				if(form.handle.isChecked("MMA304_01_form_02", "cmCheckbox")) {
   					params.cmCheckbox = "Y";
   	      		} else {
   	      			params.cmCheckbox = "N";
   	      		}
   				
   				if(params.SEARCH_TYPE == "ITEM") { // 고객사별 발급(부모창에서 구해진 값을 바로 입력한다.)
   					if(params.TARGET_PID == "OR5011_01") params.gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2));
        			else params.gridData = grid.util.convertJson2Rows(grid.handle.getAllRows(dg_2));
        		}
   				
   				grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectDeterminateList", params);
   			} else {
   				grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectCOOriginList", params);
   			}
     	},
     	issueReport : function() {
            var ftaCd = form.handle.getValue("MMA304_01_form_01", "FTA_CD");
            var certNo = form.handle.getValue("MMA304_01_form_01", "CERTIFICATION_NO");
            
        	if(form.handle.getValue("MMA304_01_form_01", "chkAuto") == "N"
    			&& oUtil.isNull(form.handle.getValue("MMA304_01_form_01", "CO_DOC_NO"))) {
        		// 증명서 발급번호 필수 체크
    			alert(resource.getMessage("TXT_DO_NO_IS_REQUIRED_FILED"));
    			return;
    		}  else if(ftaCd == "PVNRC" && oUtil.isNull(certNo)) {
                // RCEP원산지 자율발급 시 인증수출자번호 필수
                alert(resource.getMessage("MSG_RCEP_NEED_CERTNO"));
                return;
            } else {
        		// 원산지 정보 수집
        		var dg_1 = grid.getObject("MMA304_01_grid_01");
        		
        		if(!grid.handle.isChecked(dg_1)) {
        			alert(resource.getMessage("MSG_NO_CHECK") + " (PPL:Parts price list)");
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
        		if(!form.handle.isValidate("MMA304_01_form_01")) return;
        		
        		// 비고란에 경고문구가 있는 경우 계속진행할지 확인하도록 수정
        		if(oUtil.isNull(errorMsg)) {
        			MMA304_01.dialog.openDialog_3("insert");
        		} else {
	        		$.messager.confirm(CNFIR, resource.getMessage("TXT_DETERMINE_RESULT")+": "+errorMsg+"<br><br>"+resource.getMessage("TXT_CONTINUE_QUESTION"), function(flag) {
		                if(flag) {
		                	MMA304_01.dialog.openDialog_3("insert");
		                } else {
		                	grid.handle.selectRowIndex(dg_1, errorIdx);
		                	grid.handle.gotoCell(dg_1, "ERROR_REASON_NAME", errorIdx);
		                }
		            });
        		}
        	}
        },
        updateOriginResultInfo : function() {
        	MMA304_01.util.setCheckOriginResult(); // 원산지 결정기준 적용
        	
    		var obj = form.getObject("MMA304_01_form_01");
    		
    		form.init.setURL("/fm/or/or5201_01/updateOriginResultInfo");
            form.init.setCallBackFunction("updateOriginResultInfo");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectStempCIImg : function(data) {
        	var obj = form.getObject("MMA304_01_form_01", "MMA304_01_form_01_SIGN_CARD");
        	var src = "/fm/sm/SM7001_01/selectStempCIImg/"+data; // data = 1:Logo, 2:Stamp
        	
        	if(!oUtil.isNull(obj)) obj.removeAttr("src");
        	
        	obj.removeAttr("src").attr("src", src); 
        }
    },
    // 팝업 생성
    dialog : {
    	openDialog_1 : function() {
    		var dg_1 = grid.getObject("MMA304_01_grid_01");
    		var rows = grid.handle.getCheckedRowsData(dg_1);
    		var cury = "";
    		
    		if(rows.length > 0) {
    			cury = rows[0].INVOICE_CURRENCY;
    		} else {
    			rows = grid.handle.getAllRows(dg_1);
    			cury = rows[0].INVOICE_CURRENCY;
    		}
    		
    		var params = {TARGET_PID:"MMA304_01", 
    				COMPANY_CD:form.handle.getValue("MMA304_01_form_01", "COMPANY_CD"),
    				INVOICE_NO:form.handle.getValue("MMA304_01_form_01", "INVOICE_NO"),
    				INVOICE_DATE:form.handle.getValue("MMA304_01_form_01", "INVOICE_DATE"),
    				DIVISION_CD:form.handle.getValue("MMA304_01_form_01", "DIVISION_CD"),
    				CURRENCY:cury,
    				FTA_CD:form.handle.getValue("MMA304_01_form_01", "FTA_CD")
    		};
    		
      		var dg_1 = dialog.getObject("MMA004_01_dailog_d_01");
      		
      		dialog.init.setWidth(930);
      		dialog.init.setHeight(620);
      		dialog.init.setURL("/mm/pop/mmA004_01");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_01"));
      		
      		dialog.open(dg_1);
      	},
      	openDialog_2 : function() {
      		var dg_2 = dialog.getObject("MMA004_02_dailog_01");
      		var params = form.handle.getElementsParam("MMA304_01_form_01");
      		
      		dialog.init.setWidth(960);
      		dialog.init.setHeight(700);
      		dialog.init.setURL("/mm/pop/mmA004_02");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_02"));
      		
      		dialog.open(dg_2);
      	},
      	openDialog_3 : function(flag) {
      		var dg_3 = dialog.getObject("MMA004_03_dailog_01");
      		var params = form.handle.getElementsParam("MMA304_01_form_01");
      		
      		params.TARGET_PID = "MMA304_01";
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
            		var dg_1 = grid.getObject("MMA304_01_grid_01");
            		var rows = grid.handle.getCheckedRowsData(dg_1);
            		
            		dialog.handle.close(dl_3);
            		
            		form.handle.setValue("MMA304_01_form_01", "gridRows", grid.util.convertJson2Rows(rows)); 
    	        	
            		if(!oUtil.isNull(data)) {
    	        		form.handle.setValue("MMA304_01_form_01", "REASON_TITLE", data.REASON_TITLE);
    	        		form.handle.setValue("MMA304_01_form_01", "REQUESTER", data.REQUESTER);
    	        		form.handle.setValue("MMA304_01_form_01", "REQUEST_DATE", data.REQUEST_DATE);
    	        		form.handle.setValue("MMA304_01_form_01", "REASON_CONTENTS", data.REASON_CONTENTS);
    	        	}
            		var deDate = calendar.handle.getDate("MMA304_01_form_01", "DECLARE_DATE"); // 신고일자
            		
            		form.handle.setValue("MMA304_01_form_01", "APPLY_DATE", deDate);
            		form.handle.setValue("MMA304_01_form_01", "END_DATE", deDate);
            		
            		MMA304_01.util.setCheckOriginResult(); // 원산지 결정기준 적용
            		
            		var obj = form.getObject("MMA304_01_form_01");
            		
            		form.init.setURL("/fm/or/or5201_01/insertCoIssueInfo");
    	        	form.init.setValidationFlag(true);
    	        	form.init.setProgressFlag(true);
    	        	form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
    	        	form.init.setCallBackFunction("changeUpdateUI");
    	        	
    	        	form.submit(obj);
            	}
            });
        },
        openDialog_4 : function() {
    		var dg_1 = grid.getObject("MMA304_01_grid_01");
      		var dl_1 = dialog.getObject("OD4205_02_dailog_01");
      		
      		if(!grid.handle.isSelected(dg_1)) {
      			alert(resource.getMessage("MSG_NO_SELECT"));
      			return;
      		}
      		
      		// 팝업으로 넘겨줄 파라메터 생성
      		var params = grid.handle.getSelectedRowData(dg_1);
      		params.ITEM_NAME = params.PRODUCT_ITEM_NAME;
      		params.PRODUCT_DIVISION_NAME = params.DIVISION_NAME;
      			
  		    dialog.init.setTitle(resource.getMessage("OD4205_02"));
      		dialog.init.setWidth(1562);
            dialog.init.setHeight(850);
      		dialog.init.setURL("/fm/od/od4205_02");
      		dialog.init.setQueryParams(params);
      		
      		dialog.open(dl_1);
    	},
    	openDialog_5 : function(num) {
    		var params = form.handle.getElementsParam("MMA304_01_form_01");
    		var dg_1 = grid.getObject("MMA304_01_grid_01");
    		
    		if(grid.handle.isSelected(dg_1)) {
    			var rowData = grid.handle.getSelectedRowData(dg_1);
    			params.INVOICE_NO = rowData.INVOICE_NO;
      		}
            params.USER_ID = SESSION.USER_ID;
            params.TARGET_PID = "MMA304_01";
            
            var dl_3 = dialog.getObject("OR5201_02_dailog_01");
            
            dialog.init.setWidth(1000);
    		dialog.init.setHeight(590);
            dialog.init.setURL("/fm/or/or5201_02");
            dialog.init.setQueryParams(params);

            dialog.open(dl_3);
        }
    },
    // 화면 처리
    ui : {
    	changeAutoCheck : function() {
    		if(form.handle.isChecked("MMA304_01_form_01", "chkAuto")) {
    			form.handle.setValue("MMA304_01_form_01", "chkAuto", "Y");
    			form.handle.setValue("MMA304_01_form_01", "CO_DOC_NO", "");
    			form.handle.disabled("MMA304_01_form_01", "CO_DOC_NO", true);
    		} else {
    			form.handle.setValue("MMA304_01_form_01", "chkAuto", "N");
    			form.handle.disabled("MMA304_01_form_01", "CO_DOC_NO", false);
    		}
    	},
    	changeUpdateUI : function() {
    		if(form.handle.getValue("MMA304_01_form_01", "CO_ISSUE_TYPE") == "M") { // 자율발급
    			form.util.setVisible("MMA304_01_btn_13", true);
    		} else { // 기관발급
    			var orgYn = form.handle.getValue("MMA304_01_form_01", "ORGAN_ISSUE_YN");
    			
    			if(orgYn == "Y") {
    				form.util.setVisible("MMA304_01_btn_13", false);
    			} else {
    				form.util.setVisible("MMA304_01_btn_13", true);
    			}
    		}
    		
    		form.util.setVisible("MMA304_01_btn_15", true);
    		form.util.setVisible("MMA304_01_span_01", true);
    		form.util.setVisible("MMA304_01_span_02", true);
    		form.util.setVisible("MMA304_01_div_01", false);
    		form.util.setVisible("MMA304_01_btn_12", false);
    		form.util.setVisible("MMA304_01_form_01_chkAuto", false);
    		form.handle.disabled("MMA304_01_form_01", "CO_DOC_NO", false);
    		
    		form.handle.closeAll("MMA304_01_form_01");
    	}
    },
    // 파일처리
    file : {},
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA304_01_grid_01");
            var fobj = form.getObject("MMA304_01_form_01");
            
            if(MMA304_01.init.flag == "insert") {
            	form.init.setURL("/mm/pop/mmA204_01/selectDeterminateList");
            } else {
            	form.init.setURL("/mm/pop/mmA204_01/selectCOOriginList");
            }
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("EXRPT_LTIT"), resource.getMessage("LIST"));
        }
    },
    // 공통내용 처리
    util : {
    	 setCheckOriginResult : function() {
    		 var ftaCd = form.handle.getValue("MMA304_01_form_01", "FTA_CD");
    		 
     		 // 원산결정기준 적용
     		 if(ftaCd != "PVNEE") {
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_1")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_1", "Y");
     			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_1", "N");
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_2")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_2", "Y");
     			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_2", "N");
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_3")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_3", "Y");
     			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_3", "N");
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_4")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_4", "Y");
     			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_4", "N");
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_5")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_5", "Y");
     			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_5", "N");
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_6")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_6", "Y");
     			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_6", "N");
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_7")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_7", "Y");
     			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_7", "N");
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_8")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_8", "Y");
     			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_8", "N");
     			 if(form.handle.isChecked("MMA304_01_form_03", "ORIGIN_RESULT_9")) form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_9", "Y");
    			 else form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_9", "N");
     		 } else {
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_1", "N");
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_2", "N");
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_3", "N");
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_4", "N");
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_5", "N");
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_6", "N");
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_7", "N");
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_8", "N");
     			 form.handle.setValue("MMA304_01_form_01", "ORIGIN_RESULT_9", "N");
     		 }
    	 },
    	 /*
    	  * dtype = json, sql
    	  */
    	 getInvoiceList : function(dtype) {
    		 var dg_1 = grid.getObject("MMA304_01_grid_01");
    		 var rows = grid.handle.getFieldData(dg_1, "INVOICE_NO");
     		 var fdata = new Array();
     		 var fstr = "";
     		 var returnVal = "";
     		 
     		 if(dtype == "json") {
     			 returnVal = "[";
     			
	    		 $.each(rows, function(i, value){
	    		     if(fdata.indexOf(value) == -1 ) {
	    		    	 fdata.push(value);
	    		    	 fstr += "{\"CODE\":\""+value+"\", \"NAME\":\""+value+"\"},";
	    		     }
	    	     });
	    		 
	    		 returnVal += fstr.substring(0, fstr.length - 1);
	    		 returnVal += "]";
     		 } else if(dtype == "sql") {
     			$.each(rows, function(i, value){
	    		     if(fdata.indexOf(value) == -1 ) {
	    		    	 fdata.push(value);
	    		    	 fstr += "'"+value+"',";
	    		     }
	    	     });
	    		 
	    		 returnVal += fstr.substring(0, fstr.length - 1);
     		 }
     		 
    		 return returnVal;
    	 }
    },
    // 초기 설정값 조정
    config : {
        applyFtaNation : function() {
        	if(MMA304_01.init.flag != "insert") {
        		var columns1 = MMA304_01.datagrid.header1;
		
		        grid.util.changeHeaderHidden(columns1 , "CHECK", true);
		        grid.util.changeHeaderHidden(columns1 , "STATUS_NAME", true);
        	}
        	
        	var ftaCd = form.handle.getValue("MMA304_01_form_01", "FTA_CD");
        	if(ftaCd == "PVNEU") { // 베트남-EU
        		form.util.setVisible("MMA304_01_label_04", true);
        	} else if(ftaCd == "PVNAG") { // 아세안무역협정
        		form.util.setVisible("MMA304_01_label_01", true);
        		form.util.setVisible("MMA304_01_label_02", true);
        		form.util.setVisible("MMA304_01_label_03", true);
        		form.util.setVisible("MMA304_01_label_04", true);
        		form.util.setVisible("MMA304_01_label_05", true);
        		form.util.setVisible("MMA304_01_label_06", true);
        		form.util.setVisible("MMA304_01_label_07", true);
        	} else if(ftaCd == "PVNAI") { // 베트남-인도
        		form.util.setVisible("MMA304_01_label_01", true);
        		form.util.setVisible("MMA304_01_label_02", true);
        		form.util.setVisible("MMA304_01_label_03", true);
        		form.util.setVisible("MMA304_01_label_09", true);
        	} else if(ftaCd == "PVNAC") { // 베트남-중국
        		form.util.setVisible("MMA304_01_label_01", true);
        		form.util.setVisible("MMA304_01_label_03", true);
        		form.util.setVisible("MMA304_01_label_04", true);
        		form.util.setVisible("MMA304_01_label_08", true);
        	} else if(ftaCd == "PVNAK") { // 아세안-한국
        		form.util.setVisible("MMA304_01_label_01", true);
        		form.util.setVisible("MMA304_01_label_02", true);
        		form.util.setVisible("MMA304_01_label_03", true);
        	} else if(ftaCd == "PVNVK") { // 베트남-한국
        		form.util.setVisible("MMA304_01_label_04", true);
        	}
        }
    }
};