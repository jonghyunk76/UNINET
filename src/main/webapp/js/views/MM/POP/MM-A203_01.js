/******************************************************************************************
 * 작성자 : 이재욱
 * Program Id : MM-A203_01
 * 작업일자 : 2016.05.09
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA203_01.config.applyFtaNation(); // FTA 협정별 UI변경
    MMA203_01.init.initComponent();
}

var MMA203_01 = {
    
    // 초기값 설정
    init : {
    	flag : form.handle.getValue("MMA203_01_form_01", "flag"),
        initComponent : function() {
        	MMA203_01.ui.changeBtnCheck();
        	
        	if(MMA203_01.init.flag != "insert") {
        		MMA203_01.ui.changeUpdateUI();
        	} else {
        		MMA203_01.ui.changeAutoCheck();
        	}
        	
        	MMA203_01.calendar.initCalendar_1();
        	MMA203_01.calendar.initCalendar_2();
        	MMA203_01.calendar.initCalendar_3();
        	
        	MMA203_01.combobox.initCombo_1();
        	MMA203_01.datagrid.initGrid_1();
        	
            // 초기화면에 보여줄 데이터 조회
            MMA203_01.control.selectCoMasterInfo();
        }
    }, 
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
        	var objDate = calendar.getObject("MMA203_01_form_01", "ISSUE_DATE");
        	
        	calendar.init.setRequired(true);
        	if(MMA203_01.init.flag == "insert") {
        		calendar.init.setReadonly(false);
        	} else {
        		calendar.init.setReadonly(true);
        	}
        	
            calendar.create(objDate);
        },
        initCalendar_2 : function() {
        	var objDate = calendar.getObject("MMA203_01_form_01", "APPLY_DATE");
        	var pid = form.handle.getValue("MMA203_01_form_01", "TARGET_PID");
        	
        	if(MMA203_01.init.flag == "insert") {
        		var gp = form.handle.getValue("MMA203_01_form_01", "FTA_GROUP_CD");
        		var sdDate = form.handle.getValue("MMA203_01_form_01", "INVOICE_DATE");
        		var date = "";
        		
        		if(pid == "OR5204_01" || gp == "MN") {
        			date = calendar.util.getFirstDay2String(sdDate);
        		} else {
        			if(pid == "OR5202_01") { // 포괄 확인서 발급에서 현대차 HUB연계가 체크되어 있는 경우에는 매출일자 기준으로 포괄일자를 설정한다.(2020.02.04, 김종현)
        				var vaatz = form.handle.getCheckboxValue("OR5202_01_form_01", "VAATZ_ISSUE_YN");
        				
        				if(vaatz == "Y") {
        					date = calendar.util.getFirstDay2String(sdDate);
        				} else {
        					date = sdDate.substring(0, 4)+"0101";
        				}
        			} else {        				
        				date = sdDate.substring(0, 4)+"0101";
        			}
        		}
        		
        		calendar.init.setInitDate(date);
        		calendar.init.setCallFunction("exchageToDate");
        		calendar.init.setReadonly(false);
        	} else {
        		calendar.init.setCallFunction("");
        		calendar.init.setReadonly(true);
        	}
        	
        	calendar.create(objDate);
        },
        initCalendar_3 : function() {
        	var objDate = calendar.getObject("MMA203_01_form_01", "END_DATE");
        	var pid = form.handle.getValue("MMA203_01_form_01", "TARGET_PID");
        	
            if(MMA203_01.init.flag == "insert") {
            	var fdate = "";
            	var ldate = "";
            	var gp = form.handle.getValue("MMA203_01_form_01", "FTA_GROUP_CD");
            	var sdDate = form.handle.getValue("MMA203_01_form_01", "INVOICE_DATE");
            	
        		if(gp == "MN") {
        			ldate = calendar.util.getLastDay2String(sdDate);
        		} else {
        			if(pid == "OR5204_01") {
	        			fdate = calendar.util.getFirstDay2String(sdDate);
	        			ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, fdate));
        			} else {
        				if(pid == "OR5202_01") { // 포괄 확인서 발급에서 현대차 HUB연계가 체크되어 있는 경우에는 매출일자 기준으로 포괄일자를 설정한다.(2020.02.04, 김종현)
            				var vaatz = form.handle.getCheckboxValue("OR5202_01_form_01", "VAATZ_ISSUE_YN");
            				
            				if(vaatz == "Y") {
            					ldate = calendar.util.getLastDay2String(calendar.util.addDate2String("m", 10, sdDate));
            				} else {
            					ldate = sdDate.substring(0, 4)+"1231";
            				}
            			} else {        				
            				ldate = sdDate.substring(0, 4)+"1231";
            			}
        			}
        		}
        		
        		calendar.init.setInitDate(ldate);
        		calendar.init.setCallFunction("compareToDate");
        		calendar.init.setReadonly(false);
        	} else {
        		calendar.init.setCallFunction("");
        		calendar.init.setReadonly(true);
        	}

            calendar.create(objDate);
        }
    },
    // 콤보박스 그리드 생성
    combobox : {
    	initCombo_1 : function() {
            var vCompanyCd = form.handle.getValue("MMA203_01_form_01", "COMPANY_CD");
            var vDivisionCd = form.handle.getValue("MMA203_01_form_01", "DIVISION_CD");
            var valid = "N";
            
            if(MMA203_01.init.flag == "insert") {
            	valid = "Y";
            }
            
            var obj = combo.getObject("MMA203_01_form_01", "SIGNATURE_SEQ");
            
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, DIVISION_CD:vDivisionCd, CATEGORY_CD:"SIGNATURE", VALID:valid, MESSAGE_CODE : "SELET"});
            combo.init.setURL("/mm/cbox/selectSignature");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            combo.init.setCallFunction("onChangeSignature");
            
            combo.create(obj);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [
        	["CHECK",         			"CHECK"                                                      , 40, "center", "center", true, false, null, true, null, 0, 0, true],
        	["CUSTOMER_ITEM_CD",       	resource.getMessage("CERIS, LTIT, CODE")  /*품목코드*/            , 120, "center", "center", true, false, null, null, null, 0, 0],
        	["CUSTOMER_ITEM_NAME",     	resource.getMessage("CERIS, LTIT,NAME")     /*품목명*/          , 150, "left"  , "center", true, false, null, null, null, 0, 0],
        	["PRODUCT_ITEM_CD"	,       resource.getMessage("LTIT, CODE")					,100    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["HS_CODE",       			resource.getMessage("HS,CODE")           /*HS코드*/          , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["NALADISA_CODE",			resource.getMessage("NALADISA, CODE")						 , 100 ,"center", "center", true, false, null         ,null     ,null    ,0        ,0],
        	["FTA_CD",        			resource.getMessage("AGRET,CODE")        /*FTA코드*/         , 80, "center", "center", true, false, null, null, null, 0, 0],
        	["FTA_NAME",      			resource.getMessage("AGRET,NAME")      /*FTA명*/           , 110, "center", "center", true, false, null, null, null, 0, 0],
        	["RULE_CONTENTS", 			resource.getMessage("TXT_RULE_CODE_NAME")/*결정기준*/        , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["RVC_RATE",      			resource.getMessage("RVC,RATE,(%)")  /*부가가치비율(%)*/     , 100, "right" , "center", true, false, null, null, null, 0, 0],
        	["ORIGIN_YN_NAME",          resource.getMessage("TXT_MEET_YN")       /*충족여부*/        , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["ORIGIN_NATION_CD"     ,resource.getMessage("ORG_COTRY")         ,120    ,"center"    ,"center"    ,true       ,false    ,{type:"text"}         ,null     ,null    ,0        ,0],
            ["TRACE_VALUE"		 	   ,"Trace Value" 		                    					 , 100 ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}    ,0        ,0],
        	["CURRENCY"		     	   ,resource.getMessage("CURCY")		        				 , 100 ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
        	["STATUS_NAME",              resource.getMessage("DTSTA, STAUS")                          , 100, "center", "center", true, false, null, null, null, 0, 0],
        	["NONORIGIN_REASON_CD", resource.getMessage("NOMEET, REASN,CODE"),               120, "center", "center", false, false, null, null, null, 0, 0],
        	["NONORIGIN_REASON_NAME", resource.getMessage("NOMEET, REASN,NAME"),               200, "left", "center", false, false, null, null, null, 0, 0],
        	["NONORIGIN_REASON_DESC", resource.getMessage("NOMEET, REASN,DSCPT"),        300, "left", "center", false, false, null, null, null, 0, 0],
            ["ORIGIN_NATION_SRC_NAME", resource.getMessage("TXT_ORIGIN_RULE_DESC"),        250, "left", "center", false, false, null, null, null, 0, 0]
         ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA203_01_form_01");
        	var dg_2 = grid.getObject(params.TARGET_PID + "_grid_01");
            var dg_1 = grid.getObject("MMA203_01_grid_01");
            
            if(MMA203_01.init.flag == "insert") {
            	// 내수인 경우에는 부모창에서 체크된 발급대상정보를 gridData에 담아 요청함
            	if(params.EXPORT_FLAG == "D") {
            		var pval = grid.handle.getCheckedRowsData(dg_2);
            		
            		checkData = clone(pval);
            		
                    if(params.TARGET_PID == "OR5204_01") {
            			for(var i = 0; i < checkData.length; i++) {
            				if(oUtil.isNull(checkData[i].YYYYMM)) {
            					checkData[i].YYYYMM = params.YYYYMM;
            				}
            			}
            		}

            		if(params.TARGET_PID == "FH8003_02") {
            			for(var i = 0; i < checkData.length; i++) {
            				if(!oUtil.isNull(checkData[i].SALES_ITEM_CD)) {
            					checkData[i].PRODUCT_ITEM_CD = pval[i].SALES_ITEM_CD;
            				}
            			}
            		}
            		
            		params.gridData = grid.util.convertJson2Rows(checkData);
            	} else {
            		params.gridData = "";
            	}
            	
            	form.handle.setValue("MMA203_01_form_02", "gridData", params.gridData);
            	
            	grid.init.setQueryParams(params);
            	grid.init.setURL("/mm/pop/mmA203_01/selectDeterminateList");
                grid.init.setEditMode(true, "cell");
            } else {
            	grid.init.setQueryParams(params);
            	grid.init.setURL("/mm/pop/mmA203_01/selectCOOriginList");
                grid.init.setEditMode(false, "cell");
            }
            grid.init.setShowEditorFilm(false);
            grid.init.setPage(true);
            grid.init.setShowConfigPage(true);
            grid.init.setPageLayout(["resizeColumn"]);
            grid.init.setFitColumns(false);
            grid.init.setCallBackFunction("loadAtferGrid");
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(true);
            grid.init.setCheckOnBox(true);
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            grid.init.setMergeData(["CUSTOMER_ITEM_CD" ,"CUSTOMER_ITEM_NAME", "PRODUCT_ITEM_CD", "HS_CODE"],["CUSTOMER_ITEM_CD", "PRODUCT_ITEM_CD", "HS_CODE"]);
            
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
        	Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
    	exchageToDate : function(pdate) {
    		var gp = form.handle.getValue("MMA203_01_form_01", "FTA_GROUP_CD");
    		date = calendar.util.getString2Date(pdate);
    		var ldate;
    		
    		if(gp == "MN") {
    			var toDate = calendar.util.getString2Date(date);
    			
    			ldate = calendar.util.getLastDay2String(toDate, "", calendar.util.getLocale(toDate));
			} else {
				ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, date));
			}
    		
    		calendar.handle.setDate("MMA203_01_form_01", "END_DATE", ldate);
    	},
    	// 포괄시작일과 종료일이 1년을 넘을 경우 경고를 띄운다.
    	compareToDate : function(date) {
    		var coFrom = calendar.handle.getDate("MMA203_01_form_01", "APPLY_DATE");
    		var coTo = calendar.handle.getDate("MMA203_01_form_01", "END_DATE");
    		
    		var betNum = calendar.util.between2Month(coFrom, coTo);
    		
    		var max_period = 12;
    		var max_msg = "MSG_COVER_CHECK_DATE";
    		var gp = form.handle.getValue("MMA203_01_form_01", "FTA_GROUP_CD");
    		
    		if(gp == "MN") { 
    			max_period = 1;
    			max_msg = "MSG_COVER_MONTH_OVER";
    		}
    		
    		if(betNum > max_period) {
    			alert(resource.getMessage(max_msg));
    			
    			var ldate = "";
    			if(gp == "MN") {
    				ldate = calendar.util.getLastDay2String(coFrom, "", NATION_DATE_DB);
    			} else {
    				ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, coFrom, "", NATION_DATE_DB));
    			}
    			
        		calendar.handle.setDate("MMA203_01_form_01", "END_DATE", ldate);
        		
        		return false;
    		}
    		
    		return true;
    	},
    	loadAtferGrid : function() {
    		var dg_1 = grid.getObject("MMA203_01_grid_01");
    		
    		grid.handle.checkAll(dg_1, true);
    		
    		var datas = grid.handle.getAllRows(dg_1);
    		if(datas.length > 0 && MMA203_01.init.flag != "insert") {
    			form.handle.setValue("MMA203_01_form_01", "FTA_GROUP_CD", datas[0].FTA_GROUP_CD);
    			form.handle.setValue("MMA203_01_form_01", "FTA_GROUP_CD_NAME", datas[0].FTA_GROUP_CD_NAME);
    		}
    		
    		MMA203_01.ui.changeMexicoFtaUi();
    	},
    	setCoIssueInfo : function(data) {
    		form.handle.loadData("MMA203_01_form_01", data);
    		
    		try {
	    		if(data.CO_SHARE_YN == "Y") {
	    			form.handle.setChecked("MMA203_01_form_01", "CO_SHARE_YN", true);
	    		} else {
	    			form.handle.setChecked("MMA203_01_form_01", "CO_SHARE_YN", false);
	    		}
    		} catch(e) {}
    		
    		if(MMA203_01.init.flag != "insert") {
	    		MMA203_01.control.selectSignatureFile(data.SIGNATURE_SEQ);
    		}
    	},
    	// 서명파일 조회
    	onChangeSignature : function(data) {
    		if(MMA203_01.init.flag == "insert") {
	    		if(oUtil.isNull(data)) {
	    			form.handle.setValue("MMA203_01_form_01", "SIGN_PHONE_NO", "");
	    			form.handle.setValue("MMA203_01_form_01", "SIGN_EMAIL", "");
	    			form.handle.setValue("MMA203_01_form_01", "SIGN_POSITION", "");
	    			form.handle.setValue("MMA203_01_form_01", "SIGN_END_DATE", "");
	    			
	    			var obj = form.getObject("MMA203_01_form_01", "MMA203_01_form_01_SIGN_CARD");
	    			obj.removeAttr("src");
	    			
	    			return;
	    		} else {
	    			MMA203_01.control.selectSignatureFile(data);
		    		MMA203_01.control.selectSignatureInfo();
	    		}
    		}
    	},
    	setSignatureValue : function(data) {
    		if(MMA203_01.init.flag != "insert") {
    			MMA203_01.control.selectSignatureFile(data.SIGNATURE_SEQ);
    		}
    		
    		form.handle.loadData("MMA203_01_form_01", data);
        },
        changeUpdateUI : function(data) {
        	if(!oUtil.isNull(data.CO_DOC_NO)) {
        		MMA203_01.init.flag = "update";
        		
        		form.handle.setValue("MMA203_01_form_01", "flag", "update");
        		form.handle.setValue("MMA203_01_form_01", "CO_DOC_NO", data.CO_DOC_NO);
        		
        		MMA203_01.ui.changeUpdateUI();
        		MMA203_01.control.selectMainList();
        		
        		var pid = form.handle.getValue("MMA203_01_form_01", "TARGET_PID");
                
                if (!oUtil.isNull(pid)) {
                    var pro_id = eval("window." + pid + ".control");
                    
                    if (typeof(pro_id["selectMainList"]) == "function") {
                        pro_id["selectMainList"]();
                    }
                }
        	}
        },
        changeSendToCustomerUI : function(data) {
        	var pid = form.handle.getValue("MMA203_01_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".control");
                
                if (typeof(pro_id["selectMainList"]) == "function") {
                    pro_id["selectMainList"]();
                }
            }
        }
    },
    // 업무구현
    control : {
    	selectMainList : function() {
    		var dg_1 = grid.getObject("MMA203_01_grid_01");
     		var params = form.handle.getElementsParam("MMA203_01_form_01");

   			grid.handle.setHideColunm(dg_1, "CHECK");
   			grid.handle.setHideColunm(dg_1, "STATUS_NAME");
   			
     		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA203_01/selectCOOriginList", params);
     	},
    	selectCoMasterInfo : function() {
    		var obj = form.getObject("MMA203_01_form_01");
    		
    		if(MMA203_01.init.flag == "insert") {
    			form.init.setURL("/mm/pop/mmA203_01/selectExportNProducerInfo");
    		} else {
    			form.init.setURL("/mm/pop/mmA203_01/selectCOIssueInfo");
    		}
            form.init.setCallBackFunction("setCoIssueInfo");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
    	// 서명파일 조회
    	selectSignatureInfo : function() {
    		var obj = form.getObject("MMA203_01_form_01");
    		
    		form.init.setURL("/mm/pop/mmA203_01/selectSignatureInfo");
            form.init.setCallBackFunction("setSignatureValue");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectSignatureFile : function(data) {
        	var obj = form.getObject("MMA203_01_form_01", "MMA203_01_form_01_SIGN_CARD");
        	var src = "/mm/pop/mmA203_01/selectSignatureFile/"+data;
        	
        	obj.removeAttr("src").attr("src", src); 
        },
        issueReport : function() {
        	if(form.handle.getValue("MMA203_01_form_01", "chkAuto") == "N"
    			&& oUtil.isNull(form.handle.getValue("MMA203_01_form_01", "CO_DOC_NO"))) {
        		// 증명서 발급번호 필수 체크
    			alert(resource.getMessage("TXT_DO_NO_IS_REQUIRED_FILED"));
    			return;
    		} else if(!MMA203_01.event.compareToDate()) {
    			return;
    		} else if(oUtil.isNull(combo.handle.getValue("MMA203_01_form_01", "SIGNATURE_SEQ"))) {
    			// 서명권자 지정 필수 체크
        		alert(resource.getMessage("MSG_NOT_SELECT_SIGN_USER"));
        		return;
        	} else {
        		// 원산지 정보 수집
        		var dg_1 = grid.getObject("MMA203_01_grid_01");
        		
        		if(!grid.handle.isChecked(dg_1)) {
        			alert(resource.getMessage("MSG_NO_CHECK") + " (" + resource.getMessage("TXT_ITEMBY_ORG_INFO") + ")");
        			return;
        		}
        		
        		var rows = grid.handle.getCheckedRowsData(dg_1);
        		
        		// 원산지 판정내역에 오류가 있거나, 불충족인 경우에는 발급할 수 없도록 한다.
        		for(var i=0; i < rows.length; i++) {
        			if(rows[i].STATUS == "9") {
        				alert(resource.getMessage("MSG_CO_ISUUE_ERROR"));
        				return;
        			}
        			if(form.handle.getValue("MMA203_01_form_01", "CO_DOC_TYPE") == "N" && rows[i].ORIGIN_YN == "N") {
        				alert(resource.getMessage("MSG_CO_ISSUE_NOORGIN"));
        				return;
        			}
        		}
        		
        		// 필드별 유효성 체크
        		if(!form.handle.isValidate("MMA203_01_form_01")) return;
        		else {
        			MMA203_01.dialog.openDialog_3("insert");
        		}
        	}
        },
        updateSendToCustomer : function() {
        	var msgAply = "";
        	
        	if(form.handle.getValue("MMA203_01_form_01", "CLIENT_SEND_YN") == "Y") {
        		alert(resource.getMessage("MSG_NOT_CANCLE_SUBMIT_DATA"));
        		return;
        	} else {
	        	if(form.handle.isChecked("MMA203_01_form_01", "CO_SHARE_YN")) {
	        		msgAply = resource.getMessage("MSG_COO_ACCEPT_CONFIRM");
	        	} else {
	        		msgAply = resource.getMessage("MSG_CANCLE_SUBMIT");
	        	}
	        	
	        	$.messager.confirm(resource.getMessage("CNFIR"), msgAply, function(flag) {
	            	if(flag) {
	            		var obj = form.getObject("MMA203_01_form_01");
	            		
	    	        	form.init.setURL("/fm/or/or5202_01/updateSendToCustomer");
	    	        	form.init.setValidationFlag(true);
	    	        	form.init.setProgressFlag(true);
	    	        	form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
	    	        	form.init.setCallBackFunction("changeSendToCustomerUI");
	    	        	
	    	        	form.submit(obj);
	            	}
	            });
        	}
        }
    },
    // 다이얼로그 구현
    dialog : {
    	openDialog_1 : function() {
      		var dg_1 = dialog.getObject("MMA004_01_dailog_d_01");
      		
      		dialog.init.setWidth(930);
      		dialog.init.setHeight(620);
      		dialog.init.setURL("/mm/pop/mmA004_01");
      		dialog.init.setQueryParams({TARGET_PID:"MMA203_01", 
      			COMPANY_CD:form.handle.getValue("MMA203_01_form_01", "COMPANY_CD"), 
      			INVOICE_NO:form.handle.getValue("MMA203_01_form_01", "INVOICE_NO"),
      			INVOICE_DATE:form.handle.getValue("MMA203_01_form_01", "INVOICE_DATE"),
      			DIVISION_CD:form.handle.getValue("MMA204_01_form_01", "DIVISION_CD"),
      			CURRENCY:form.handle.getValue("MMA204_01_form_01", "CURRENCY"),
      			FTA_CD:form.handle.getValue("MMA204_01_form_01", "FTA_CD")
      			});
      		dialog.init.setTitle(resource.getMessage("MMA004_01"));
      		
      		dialog.open(dg_1);
      	},
      	openDialog_2 : function() {
      		var dg_2 = dialog.getObject("MMA004_02_dailog_01");
      		var params = form.handle.getElementsParam("MMA203_01_form_01");
      		
      		dialog.init.setWidth(960);
      		dialog.init.setHeight(700);
      		dialog.init.setURL("/mm/pop/mmA004_02");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_02"));
      		
      		dialog.open(dg_2);
      	},
      	openDialog_3 : function(flag) {
      		var dg_3 = dialog.getObject("MMA004_03_dailog_01");
      		var params = form.handle.getElementsParam("MMA203_01_form_01");
      		
      		params.TARGET_PID = "MMA203_01";
      		params.flag = flag;
      		
      		dialog.init.setWidth(700);
      		if(SESSION.APPLICATION_SERVICE_TYPE == "CL" && flag == "insert") {
      			dialog.init.setHeight(528);
      		} else {
      			dialog.init.setHeight(500);
      		}
      		dialog.init.setURL("/mm/pop/mmA004_03");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_03"));
      		
      		dialog.open(dg_3);
      	},
      	callByMMA004_03 : function(data) {
      		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_ISSUE_NOT_MODIFY"), function(flag) {
            	if(flag) {
            		if(!oUtil.isNull(data)) {
    	        		form.handle.setValue("MMA203_01_form_01", "REASON_TITLE", data.REASON_TITLE);
    	        		form.handle.setValue("MMA203_01_form_01", "REQUESTER", data.REQUESTER);
    	        		form.handle.setValue("MMA203_01_form_01", "REQUEST_DATE", data.REQUEST_DATE);
    	        		form.handle.setValue("MMA203_01_form_01", "REASON_CONTENTS", data.REASON_CONTENTS);
    	        	}
            		
            		var dl_1 = dialog.getObject("MMA004_03_dailog_01");
            		dialog.handle.close(dl_1);
            		
            		var dg_1 = grid.getObject("MMA203_01_grid_01");
            		var rows = grid.handle.getCheckedRowsData(dg_1);
            		
            		form.handle.setValue("MMA203_01_form_01", "gridRows", grid.util.convertJson2Rows(rows));
            		
            		var obj = form.getObject("MMA203_01_form_01");
            		
    	        	form.init.setURL("/fm/or/or5202_01/insertCoIssueInfo");
    	        	form.init.setValidationFlag(true);
    	        	form.init.setProgressFlag(true);
    	        	form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
    	        	form.init.setCallBackFunction("changeUpdateUI");
    	        	
    	        	form.submit(obj);
            	}
            });
        },
        openDialog_4 : function() {
    		var dg_1 = grid.getObject("MMA203_01_grid_01");
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
    	 openDialog_5 : function() {
     		var dg_1 = grid.getObject("MMA203_01_grid_01");
       		var dl_1 = dialog.getObject("OD4205_09_dailog_01");
       		
       		if(!grid.handle.isSelected(dg_1)) {
       			alert(resource.getMessage("MSG_NO_SELECT"));
       			return;
       		}
       		
       		
       		// 팝업으로 넘겨줄 파라메터 생성
       		var params = grid.handle.getSelectedRowData(dg_1);
       		var cparam = clone(params);
       		params.REQ_PID = "MMA203_01";
       		
       		if(params.ORIGIN_YN == "Y") {
       			alert(resource.getMessage("NOT_MEET_NO_REASON_INPUT"));
       			return;
       		}
       		
       		var aplyFta = form.handle.getValue("MMA203_01_form_01", "APPLY_FTA_CD");
       		if(oUtil.isNull(aplyFta)) {
       			params.TARGET_FTA_CD = cparam.FTA_CD;
       			params.FTA_CD = "";
       		} else {
       			params.FTA_CD = replaceAll(aplyFta, "'", "");
       		}
       		
       		dialog.init.setURL("/fm/od/od4205_09");
       		dialog.init.setQueryParams(params);
       		dialog.init.setWidth(1300);
       		dialog.init.setHeight(850);
       		
       		dialog.open(dl_1);
     	 }
    },
    // 화면 UI 변경
    ui : {
    	changeBtnCheck : function() {
    		if(form.handle.getValue("MMA203_01_form_01", "EXPORT_FLAG") == "E") {  // 수출
    			form.util.setVisible("MMA203_01_btn_11", true);
    			form.util.setVisible("MMA203_01_form_01_export_view", true);
    		} else {  // 내수
    			form.util.setVisible("MMA203_01_btn_11", false);
    			form.util.setVisible("MMA203_01_form_01_export_view", false);
    		}
    		
    		if(form.handle.getValue("MMA203_01_form_01", "CO_DOC_TYPE") == "N") { // 개별발급
    			form.util.setVisible("MMA203_01_form_01_period_date", false);
    			form.util.setVisible("MMA203_01_form_01_period_text", false);
    		}
    	},
    	changeAutoCheck : function() {
    		if(form.handle.isChecked("MMA203_01_form_01", "chkAuto")) {
    			form.handle.setValue("MMA203_01_form_01", "chkAuto", "Y");
    			form.handle.setValue("MMA203_01_form_01", "CO_DOC_NO", "");
    			form.handle.disabled("MMA203_01_form_01", "CO_DOC_NO", true);
    		} else {
    			form.handle.setValue("MMA203_01_form_01", "chkAuto", "N");
    			form.handle.disabled("MMA203_01_form_01", "CO_DOC_NO", false);
    		}
    	},
    	changeCoShareCheck : function() {
    		if(form.handle.isChecked("MMA203_01_form_01", "CO_SHARE_YN")) {
    			form.handle.setValue("MMA203_01_form_01", "CO_SHARE_YN", "Y");
    		} else {
    			form.handle.setValue("MMA203_01_form_01", "CO_SHARE_YN", "N");
    		}
    	},
    	changeUpdateUI : function() {
    		if(form.handle.getValue("MMA203_01_form_01", "CO_ISSUE_TYPE") == "M") { // 자율발급
    			form.util.setVisible("MMA203_01_btn_13", true);
    		} else { // 기관발급
    			form.util.setVisible("MMA203_01_btn_13", true);
    		}
    		
    		if(form.handle.getValue("MMA203_01_form_01", "CO_SHARE_YN") == "Y") {
    			form.handle.setChecked("MMA203_01_form_01", "CO_SHARE_YN", true);
    		} else {
    			form.handle.setChecked("MMA203_01_form_01", "CO_SHARE_YN", false);
    		}
    		
    		if(form.handle.getValue("MMA203_01_form_01", "CLIENT_SEND_YN") == "Y" || form.handle.getValue("MMA203_01_form_01", "CLIENT_SEND_YN") == "I") {
    			form.handle.disabled("MMA203_01_form_01", "CO_SHARE_YN", true);
    			form.util.setVisible("MMA203_01_btn_14", false);
    		} else {
    			form.util.setVisible("MMA203_01_btn_14", true);
    		}
    		
    		form.util.setVisible("MMA203_01_span_01", true);
    		form.util.setVisible("MMA203_01_span_02", true);
    		form.util.setVisible("MMA203_01_btn_12", false);
    		form.util.setVisible("MMA203_01_form_01_chkAuto", false);
    		form.handle.disabled("MMA203_01_form_01", "CO_DOC_NO", false);
    		
    		form.handle.closeAll("MMA203_01_form_01");
    	},
    	changeMexicoFtaUi : function() {
    		var gp = form.handle.getValue("MMA203_01_form_01", "FTA_GROUP_CD");
    		var dg_1 = grid.getObject("MMA203_01_grid_01");
     		
    		if(gp == "ML") {
    			grid.handle.setHideColunm(dg_1, "TRACE_VALUE");
    			grid.handle.setHideColunm(dg_1, "CURRENCY");
    		}else{
    			grid.handle.setHideColunm(dg_1, "NALADISA_CODE");
    		}
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA203_01_grid_01");
            var fobj;
            
            if(MMA203_01.init.flag == "insert") {
                fobj = form.getObject("MMA203_01_form_02");
                
            	form.init.setURL("/mm/pop/mmA203_01/selectDeterminateList");
            } else {
                fobj = form.getObject("MMA203_01_form_01");
                
            	form.init.setURL("/mm/pop/mmA203_01/selectCOOriginList");
            }
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("TXT_ITEMBY_ORG_INFO"), resource.getMessage("LIST"));
        }
    },
    config : {
    	applyFtaNation : function() {
    		var columns1 = MMA203_01.datagrid.header1;
    		
    		// 아래 내용은 항상 실행을 최우선으로 하기 위해 최상위 둘 것
			if(SESSION.FTA_NATION == "KR") {
				grid.util.changeHeaderHidden(columns1 , "NALADISA_CODE", true);
				grid.util.changeHeaderHidden(columns1 , "TRACE_VALUE", true);
				grid.util.changeHeaderHidden(columns1 , "CURRENCY", true);
			}
			
			if(MMA203_01.init.flag != "insert") {
				grid.util.changeHeaderHidden(columns1 , "CHECK", true);
				grid.util.changeHeaderHidden(columns1 , "STATUS_NAME", true);
			}
		}
    }
    
};
