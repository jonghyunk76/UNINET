/******************************************************************************************
 * 작성자 : 이재욱
 * Program Id : MM-A103_01
 * 작업일자 : 2016.05.09
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA103_01.init.initComponent();
}

var MMA103_01 = {
    
    // 초기값 설정
    init : {
    	flag : form.handle.getValue("MMA103_01_form_01", "flag"),
        initComponent : function() {
        	MMA103_01.ui.changeBtnCheck();
        	
        	if(MMA103_01.init.flag != "insert") {
        		MMA103_01.ui.chageUpdateUI();
        	} else {
        		MMA103_01.ui.changeAutoCheck();
            	
            	MMA103_01.calendar.initCalendar_1();
            	MMA103_01.calendar.initCalendar_2();
            	MMA103_01.calendar.initCalendar_3();
        	}
        	
        	MMA103_01.combobox.initCombo_1();
        	MMA103_01.datagrid.initGrid_1();
        	
            // 초기화면에 보여줄 데이터 조회
            MMA103_01.control.selectCoMasterInfo();
            
            // 보고서 발급 툴팁
            MMA103_01.tooltip.initTooltip_1();
        }
    }, 
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
        	var objDate = calendar.getObject("MMA103_01_form_01", "ISSUE_DATE");
        	
        	calendar.init.setRequired(true);
        	
            calendar.create(objDate);
        },
        initCalendar_2 : function() {
        	var objDate = calendar.getObject("MMA103_01_form_01", "APPLY_DATE");
        	
        	if(MMA103_01.init.flag == "insert") {
        		var date = calendar.util.getFirstDay2String(form.handle.getValue("MMA103_01_form_01", "INVOICE_DATE"));
        		
        		calendar.init.setInitDate(date);
        		calendar.init.setCallFunction("exchageToDate");
        	} else {
        		calendar.init.setCallFunction("");
        	}
        	
        	calendar.create(objDate);
        },
        initCalendar_3 : function() {
        	var objDate = calendar.getObject("MMA103_01_form_01", "END_DATE");
            
            if(MMA103_01.init.flag == "insert") {
        		var fdate = calendar.util.getFirstDay2String(form.handle.getValue("MMA103_01_form_01", "INVOICE_DATE"));
            	var ldate = calendar.util.addDate2String("d", 364, fdate);
        		
        		calendar.init.setInitDate(ldate);
        		calendar.init.setCallFunction("compareToDate");
        	} else {
        		calendar.init.setCallFunction("");
        	}

            calendar.create(objDate);
        }
    },
    // 콤보박스 그리드 생성
    combobox : {
        initCombo_1 : function() {
            var obj = combo.getObject("MMA103_01_form_01", "SIGNATURE_SEQ");
            
            var vCompanyCd = form.handle.getValue("MMA103_01_form_01", "COMPANY_CD");
            var vDivisionCd = form.handle.getValue("MMA103_01_form_01", "DIVISION_CD");
            
            combo.init.setQueryParams({COMPANY_CD:vCompanyCd, DIVISION_CD:vDivisionCd, CATEGORY_CD:"SIGNATURE", MESSAGE_CODE : "SELET"});
            combo.init.setURL("/mm/cbox/selectSignature");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            combo.init.setCallFunction("onChangeSignature");
            
            combo.create(obj);
        },
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [// Field ID | Field Name | Width |  Align  | hAlign | Sort Y/N | hidden | edit type | chbox | style | rowspan | colspan
                   ["CHECK",         			"CHECK"                                                      , 100, "center", "center", true, false, null, true, null, 0, 0, true],
                   ["PRODUCT_ITEM_CD",       	resource.getMessage("LTIT, CODE")  /*품목코드*/        , 100, "center", "center", true, false, null, null, null, 0, 0],
                   ["PRODUCT_ITEM_NAME",     	resource.getMessage("LTIT, NAME")     /*품목명*/          , 100, "left"  , "center", true, false, null, null, null, 0, 0],
                   ["HS_CODE",       			resource.getMessage("HS,CODE")           /*HS코드*/          , 100, "center", "center", true, false, null, null, null, 0, 0],
                   ["FTA_CD",        			resource.getMessage("AGRET,CODE")        /*FTA코드*/         , 100, "center", "center", true, false, null, null, null, 0, 0],
                   ["FTA_NAME",      			resource.getMessage("AGRET,NAME")      /*FTA명*/           , 100, "center", "center", true, false, null, null, null, 0, 0],
                   ["RULE_CONTENTS", 			resource.getMessage("TXT_RULE_CODE_NAME")/*결정기준*/        , 100, "center", "center", true, false, null, null, null, 0, 0],
                   ["RVC_RATE",      			resource.getMessage("RVC,RATE,(%)")  /*부가가치비율(%)*/     , 100, "right" , "center", true, false, null, null, null, 0, 0],
                   ["ORIGIN_YN_NAME",           resource.getMessage("TXT_MEET_YN")       /*충족여부*/        , 100, "center", "center", true, false, null, null, null, 0, 0],
                   ["STATUS_NAME",              resource.getMessage("DTSTA, STAUS")                          , 100, "center", "center", true, false, null, null, null, 0, 0],
				   ["COMPANY_CD", 				"COMPANY_CD"        										 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["DIVISION_CD", 				"DIVISION_CD"        										 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["CO_REQ_NO", 				"CO_REQ_NO"        											 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["SALES_MGMT_NO", 			"SALES_MGMT_NO"        										 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["ORIGIN_YN", 			    "ORIGIN_YN"        										     , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["FTA_GROUP_CD", 			"FTA_GROUP_CD"        										 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["CUSTOMER_ITEM_CD", 		"CUSTOMER_ITEM_CD"        									 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["SALES_UNIT_PRICE", 		"SALES_UNIT_PRICE"        									 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["NALADISA_CODE", 			"NALADISA_CODE"        										 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["CURRENCY", 				"CURRENCY"        										     , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["TRACE_VALUE", 				"TRACE_VALUE"        										 , 100, "center", "center", true, true,  null, null, null, 0, 0],
                   ["STATUS", 					"STATUS"        										 	 , 100, "center", "center", true, true,  null, null, null, 0, 0]
                  ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA103_01_form_01");
        	var dg_2 = grid.getObject(params.TARGET_PID + "_grid_01");
            var dg_1 = grid.getObject("MMA103_01_grid_01");
            
            if(MMA103_01.init.flag == "insert") {
            	// 내수인 경우에는 부모창에서 체크된 발급대상정보를 gridData에 담아 요청함
            	if(params.EXPORT_FLAG == "D") {
            		params.gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2));
            	} else {
            		params.gridData = "";
            	}
            	
            	grid.init.setQueryParams(params);
            	grid.init.setURL("/mm/pop/mmA103_01/selectDeterminateList");
            } else {
            	grid.init.setQueryParams(params);
            	grid.init.setURL("/mm/pop/mmA103_01/selectCOOriginList");
            	this.header1[0][6] = true;
            }
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setCallBackFunction("loadAtferGrid");
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(true);
            grid.init.setMergeData(["PRODUCT_ITEM_CD" ,"PRODUCT_ITEM_NAME","HS_CODE"],["PRODUCT_ITEM_CD"]);
            
        	Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
    	exchageToDate : function(date) {
    		var ldate = calendar.util.addDate2String("d", 364, calendar.util.getString2Date(date));
    		calendar.handle.setDate("MMA103_01_form_01", "END_DATE", ldate);
    	},
    	// 포괄시작일과 종료일이 1년을 넘을 경우 경고를 띄운다.
    	compareToDate : function(date) {
    		var coFrom = calendar.handle.getDate("MMA103_01_form_01", "APPLY_DATE");
    		var coTo = calendar.handle.getDate("MMA103_01_form_01", "END_DATE");
    		
    		var betNum = calendar.util.between2Month(coFrom, coTo);
    		
    		if(betNum > 12) {
    			alert(resource.getMessage("MSG_COVER_CHECK_DATE"));
    			
    			var ldate = calendar.util.addDate2String("d", 364, coFrom);
        		calendar.handle.setDate("MMA103_01_form_01", "END_DATE", ldate);
    		}
    	},
    	loadAtferGrid : function() {
    		var dg_1 = grid.getObject("MMA103_01_grid_01");
    		
    		grid.handle.checkAll(dg_1, true);
    	},
    	setCoIssueInfo : function(data) {
    		form.handle.loadData("MMA103_01_form_01", data);
    		
    		if(MMA103_01.init.flag != "insert") {
	    		MMA103_01.control.selectSignatureFile(data.SIGNATURE_SEQ);
    		}
    	},
    	// 서명파일 조회
    	onChangeSignature : function(data) {
    		if(oUtil.isNull(data)) {
    			form.handle.setValue("MMA103_01_form_01", "SIGN_PHONE_NO", "");
    			form.handle.setValue("MMA103_01_form_01", "SIGN_EMAIL", "");
    			form.handle.setValue("MMA103_01_form_01", "SIGN_POSITION", "");
    			form.handle.setValue("MMA103_01_form_01", "SIGN_END_DATE", "");
    			
    			var obj = form.getObject("MMA103_01_form_01", "MMA103_01_form_01_SIGN_CARD");
    			obj.removeAttr("src");
    			
    			return;
    		} else {
    			MMA103_01.control.selectSignatureFile(data);
	    		MMA103_01.control.selectSignatureInfo();
    		}
    	},
    	setSignatureValue : function(data) {
    		form.handle.loadData("MMA103_01_form_01", data);
        },
        chageUpdateUI : function(data) {
        	if(!oUtil.isNull(data.CO_DOC_NO)) {
        		MMA103_01.init.flag = "update";
        		
        		form.handle.setValue("MMA103_01_form_01", "flag", "update");
        		form.handle.setValue("MMA103_01_form_01", "CO_DOC_NO", data.CO_DOC_NO);
        		
        		MMA103_01.ui.chageUpdateUI();
        		MMA103_01.control.selectMainList();
        		
        		var pid = form.handle.getValue("MMA103_01_form_01", "TARGET_PID");
                
                if (!oUtil.isNull(pid)) {
                    var pro_id = eval("window." + pid + ".control");
                    
                    if (typeof(pro_id["selectMainList"]) == "function") {
                        pro_id["selectMainList"]();
                    }
                }
        	}
        }
    },
    // 업무구현
    control : {
    	selectMainList : function() {
     		var dg_1 = grid.getObject("MMA103_01_grid_01");
     		var params = form.handle.getElementsParam("MMA103_01_form_01");
     		
            grid.handle.setHideColunm(dg_1, "CHECK");
            
     		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA103_01/selectCOOriginList", params);
     	},
    	selectCoMasterInfo : function() {
    		var obj = form.getObject("MMA103_01_form_01");
    		
    		if(MMA103_01.init.flag == "insert") {
    			form.init.setURL("/mm/pop/mmA103_01/selectExportNProducerInfo");
    		} else {
    			form.init.setURL("/mm/pop/mmA103_01/selectCOIssueInfo");
    		}
            form.init.setCallBackFunction("setCoIssueInfo");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
    	// 서명파일 조회
    	selectSignatureInfo : function() {
    		var obj = form.getObject("MMA103_01_form_01");
    		
    		form.init.setURL("/mm/pop/mmA103_01/selectSignatureInfo");
            form.init.setCallBackFunction("setSignatureValue");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectSignatureFile : function(data) {
        	var obj = form.getObject("MMA103_01_form_01", "MMA103_01_form_01_SIGN_CARD");
        	var src = "/mm/pop/mmA103_01/selectSignatureFile/"+data;
        	
        	obj.removeAttr("src").attr("src", src); 
        },
        issueReport : function() {
        	if(form.handle.getValue("MMA103_01_form_01", "chkAuto") == "N"
    			&& oUtil.isNull(form.handle.getValue("MMA103_01_form_01", "CO_DOC_NO"))) {
        		// 증명서 발급번호 필수 체크
    			alert(resource.getMessage("TXT_DO_NO_IS_REQUIRED_FILED"));
    			return;
    		} else if(oUtil.isNull(combo.handle.getValue("MMA103_01_form_01", "SIGNATURE_SEQ"))) {
    			// 서명권자 지정 필수 체크
        		alert(resource.getMessage("MSG_NOT_SELECT_SIGN_USER"));
        		return;
        	} else {
        		// 원산지 정보 수집
        		var dg_1 = grid.getObject("MMA103_01_grid_01");
        		
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
        			} else if(form.handle.getValue("MMA103_01_form_01", "CO_DOC_TYPE") == "N" && rows[i].ORIGIN_YN == "N") {
        				alert(resource.getMessage("MSG_CO_ISSUE_NOORGIN"));
        				return;
        			}
        		}
        		
        		// 필드별 유효성 체크
        		if(!form.handle.isValidate("MMA103_01_form_01")) return;
        		
        		$.messager.confirm(CNFIR, resource.getMessage("MSG_ISSUE_NOT_MODIFY"), function(flag) {
                	if(flag) {
                		form.handle.setValue("MMA103_01_form_01", "gridRows", grid.util.convertJson2Rows(rows));
        	        	
                		var obj = form.getObject("MMA103_01_form_01");
                		
        	        	if(form.handle.getValue("MMA103_01_form_01", "EXPORT_FLAG") == "D") {
        	        		form.init.setURL("/fm/or/or5102_01/insertCoIssueInfo");
        	        	} else if(form.handle.getValue("MMA103_01_form_01", "EXPORT_FLAG") == "E") {
        	        		form.init.setURL("/fm/or/or5101_01/insertCoIssueInfo");
        	        	}
        	        	form.init.setValidationFlag(true);
        	        	form.init.setProgressFlag(true);
        	        	form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
        	        	form.init.setCallBackFunction("chageUpdateUI");
        	        	
        	        	form.submit(obj);
                	}
                });
        	}
        }
    },
    // 다이얼로그 구현
    dialog : {
    	openDialog_1 : function() {
      		var dg_1 = dialog.getObject("MMA103_02_dailog_01");
      		
      		dialog.init.setWidth(800);
      		dialog.init.setHeight(220);
      		dialog.init.setURL("/mm/pop/mmA004_01");
      		dialog.init.setQueryParams({TARGET_PID:"MMA103_01", 
      			COMPANY_CD:form.handle.getValue("MMA103_01_form_01", "COMPANY_CD"), 
      			INVOICE_NO:form.handle.getValue("MMA103_01_form_01", "INVOICE_NO"),
      			INVOICE_DATE:form.handle.getValue("MMA103_01_form_01", "INVOICE_DATE")
      			});
      		dialog.init.setTitle(resource.getMessage("MMA004_01"));
      		dialog.open(dg_1);
      	},
      	openDialog_2 : function() {
      		var dg_2 = dialog.getObject("MMA103_03_dailog_01");
      		var params = form.handle.getElementsParam("MMA103_01_form_01");
      		
      		dialog.init.setWidth(960);
      		dialog.init.setHeight(600);
      		dialog.init.setURL("/mm/pop/mmA004_02");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_02"));
      		dialog.open(dg_2);
      	}
    },
    // 화면 UI 변경
    ui : {
    	changeBtnCheck : function() {
    		if(form.handle.getValue("MMA103_01_form_01", "EXPORT_FLAG") == "E") {  // 수출
    			form.util.setVisible("MMA103_01_btn_11", true);
    			form.util.setVisible("MMA103_01_form_01_export_view", true);
    		} else {  // 내수
    			form.util.setVisible("MMA103_01_btn_11", false);
    			form.util.setVisible("MMA103_01_form_01_export_view", false);
    		}
    		
    		if(form.handle.getValue("MMA103_01_form_01", "CO_DOC_TYPE") == "N") { // 개별발급
    			form.util.setVisible("MMA103_01_form_01_period_date", false);
    			form.util.setVisible("MMA103_01_form_01_period_text", false);
    		}
    	},
    	changeAutoCheck : function() {
    		if(form.handle.isChecked("MMA103_01_form_01", "chkAuto")) {
    			form.handle.setValue("MMA103_01_form_01", "chkAuto", "Y");
    			form.handle.setValue("MMA103_01_form_01", "CO_DOC_NO", "");
    			form.handle.disabled("MMA103_01_form_01", "CO_DOC_NO", true);
    		} else {
    			form.handle.setValue("MMA103_01_form_01", "chkAuto", "N");
    			form.handle.disabled("MMA103_01_form_01", "CO_DOC_NO", false);
    		}
    	},
    	chageUpdateUI : function() {
    		if(form.handle.getValue("MMA103_01_form_01", "CO_ISSUE_TYPE") == "M") { // 자율발급
    			form.util.setVisible("MMA103_01_btn_13", true);
    		} else { // 기관발급
    			form.util.setVisible("MMA103_01_btn_13", true);
    		}
    		
    		form.util.setVisible("MMA103_01_btn_12", false);
    		form.util.setVisible("MMA103_01_form_01_chkAuto", false);
    		form.handle.disabled("MMA103_01_form_01", "CO_DOC_NO", false);
    		
    		form.handle.closeAll("MMA103_01_form_01");
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현
    excel : {}
    
};
