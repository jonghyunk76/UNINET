/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A019_02
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA019_01.init.initComponent();
}

var MMA019_01 = {
    // 초기값 설정
    init : {
    	coModifyChecker : false,
        initComponent : function() {
        	// flag = insert(신규), renew(갱신), update(수정), modify(원산지 수정)
        	var flag = form.handle.getValue("MMA019_01_form_01", "flag");
        	
        	if(flag == "insert" || flag == "renew") {
        		MMA019_01.ui.onChangeChkAuto();
        	}
        	
            MMA019_01.calendar.initCalendar_1();
            MMA019_01.calendar.initCalendar_2();
            MMA019_01.calendar.initCalendar_3();
            MMA019_01.calendar.initCalendar_4();
            MMA019_01.calendar.initCalendar_5();
            
            MMA019_01.datagrid.initGrid_1();
            
            MMA019_01.control.selectBasicInfo(); // 초기 데이터 조회
            
            if(flag == "view" || flag == "modify") {
        		MMA019_01.ui.onChangeIssueUI();
        		
        		if(flag == "view") {
        			form.util.setVisible("MMA019_01_btn_04", true);
        		}
        	}
            
        	if(flag == "update") {
        		MMA019_01.ui.onChangeUpdateUI();
        	}
        	
        	if(flag != "insert") {
        		form.util.setVisible("MMA019_01_btn_02", true);
        	}
        	
        	var docType = form.handle.getValue("MMA019_01_form_01", "CO_DOC_TYPE");
            if(docType != "N") {
            	form.util.setVisible("MMA019_01_div_01", true);
    		}
        }
    }, 
    // 달력 그리드 생성
    calendar : {
    	//증명서 발행일
        initCalendar_1 : function() {
        	var flag = form.handle.getValue("MMA019_01_form_01", "flag");
            var objDate = calendar.getObject("MMA019_01_form_01", "ISSUE_DATE");
            
            if(flag == "view" || flag == "update" || flag == "modify") {
            	var date = calendar.util.getDate2String(form.handle.getValue("MMA019_01_form_01", "ISSUE_DATE"));
            	
            	calendar.init.setInitDate(date);
            } else {
            	calendar.init.setInitDate(calendar.util.toDate2String());
            }
            calendar.init.setRequired(true);
            
            calendar.create(objDate);
        },
        //포괄시작일자
        initCalendar_2 : function() {
        	var flag = form.handle.getValue("MMA019_01_form_01", "flag");
        	var objDate = calendar.getObject("MMA019_01_form_01", "APPLY_DATE");
        	
        	if(flag == "insert" || flag == "renew") {
        		var fdate = "";
            	var gp = form.handle.getValue("MMA019_01_form_01", "FTA_GROUP_CD");
            	var docType = form.handle.getValue("MMA019_01_form_01", "CO_DOC_TYPE");
            	
        		if(gp == "MN" && docType != "N") { // NAFTA이면서 포괄인 경우에는 한달을 기준으로 함
        			fdate = calendar.util.getFirstDay2String(form.handle.getValue("MMA019_01_form_01", "LAST_PURCHASE_DATE"));
        		} else {
        			if(flag == "insert") {
        				// 거래년도의 1월1일이 시작일되도록 변경(2017.07.27 YNI-Maker)
            			var poDate = form.handle.getValue("MMA019_01_form_01", "LAST_PURCHASE_DATE");
            			var apDate = poDate.substring(0, 4)+"0101";
            			fdate = calendar.util.getFirstDay2String(apDate);
        			} else {
        				fdate = calendar.util.getDate2String(form.handle.getValue("MMA019_01_form_01", "LAST_PURCHASE_DATE"));
        			}
        		}
        		
        		calendar.init.setInitDate(fdate);
        		calendar.init.setCallFunction("exchageToDate");
        	} else {
        		var date = calendar.util.getDate2String(form.handle.getValue("MMA019_01_form_01", "APPLY_DATE"));
        		
        		calendar.init.setInitDate(date);
        		calendar.init.setCallFunction("");
        	}
        	calendar.init.setRequired(true);
        	
        	calendar.create(objDate);
        },
        //포괄만료일자
        initCalendar_3 : function() {
        	var flag = form.handle.getValue("MMA019_01_form_01", "flag");
            var objDate = calendar.getObject("MMA019_01_form_01", "END_DATE");
            
            if(flag == "insert" || flag == "renew") {
            	var fdate = "";
            	var ldate = "";
            	var gp = form.handle.getValue("MMA019_01_form_01", "FTA_GROUP_CD");
            	var docType = form.handle.getValue("MMA019_01_form_01", "CO_DOC_TYPE");
            	
        		if(gp == "MN" && docType != "N") { // NAFTA이면서 포괄인 경우에는 한달을 기준으로 함
        			ldate = calendar.util.getLastDay2String(form.handle.getValue("MMA019_01_form_01", "LAST_PURCHASE_DATE"));
        		} else {
        			if(flag == "insert") {
        				// 거래년도의 1월1일이 시작일되도록 변경(2017.07.27 YNI-Maker)
            			var poDate = form.handle.getValue("MMA019_01_form_01", "LAST_PURCHASE_DATE");
            			var apDate = poDate.substring(0, 4)+"0101";
            			fdate = calendar.util.getFirstDay2String(apDate);
        				//fdate = calendar.util.getFirstDay2String(form.handle.getValue("MMA019_01_form_01", "LAST_PURCHASE_DATE"));
        			} else {
        				fdate = calendar.util.getDate2String(form.handle.getValue("MMA019_01_form_01", "LAST_PURCHASE_DATE"));
        			}
            		
	            	ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, fdate));
        		}
        		
        		calendar.init.setInitDate(ldate);
        		calendar.init.setCallFunction("compareToDate");
        	} else {
        		var date = calendar.util.getDate2String(form.handle.getValue("MMA019_01_form_01", "END_DATE"));
        		
        		calendar.init.setInitDate(date);
        		
        		if(flag == "modify") {
        			calendar.init.setCallFunction("compareToDate");
        		} else {
        			calendar.init.setCallFunction("");
        		}
        	}
            calendar.init.setRequired(true);
            
            calendar.create(objDate);
        },
        initCalendar_4 : function() {
        	var objDate = calendar.getObject("MMA019_01_form_01", "MOD_APPLY_DATE");
            
            calendar.init.setRequired(false);
            
            calendar.create(objDate);
        },
        initCalendar_5 : function() {
        	var objDate = calendar.getObject("MMA019_01_form_01", "MOD_END_DATE");
            
            calendar.init.setRequired(false);
            
            calendar.create(objDate);
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {},
    // 데이터 그리드 생성
    datagrid : {
        header1 : [// Field ID | Field Name | Width |  Align  | hAlign | Sort Y/N | hidden | edit type | chbox | style | rowspan | colspan
                   ["ITEM_CD",          resource.getMessage("LTIT, CODE")/*품목코드*/,          130, "center", "center", true, false, null, null, null, 0, 0],
                   ["ITEM_NAME",        resource.getMessage("LTIT, NAME")/*품목명*/,               180,   "left", "center", true, false, null, null, null, 0, 0],
                   ["HS_CODE",          resource.getMessage("TXT_HS_CODE")/*HS코드*/,                 100, "center", "center", true, false, null, null, null, 0, 0],
                   ["NALADISA_CODE",    resource.getMessage("TXT_NALADISA_CODE")/*NALADISA_CODE*/,    100, "center", "center", true, false, null, null, null, 0, 0],
                   ["FTA_CD",           resource.getMessage("AGRET,CODE")/*FTA코드*/,                 100, "center", "center", true, false, null, null, null, 0, 0],
                   ["FTA_NAME",         resource.getMessage("AGRET,NAME")/*FTA명*/,                 100,   "left", "center", true, false, null, null, null, 0, 0],
                   ["RULE_CONTENTS",    resource.getMessage("TXT_RULE_CODE_NAME")/*결정기준*/,        100, "center", "center", true, false, null, null, null, 0, 0],
                   ["FTA_CO_YN_Y",      resource.getMessage("ORGT")/*역내산*/,                       100, "center", "center", true, false, null, null, null, 0, 0],
                   ["FTA_CO_YN_N",      resource.getMessage("NOORD")/*역외산*/,                       100, "center", "center", true, false, null, null, null, 0, 0],
                   ["RVC_RATE",         resource.getMessage("ORG, RATE,<BR>(,%,)")/*부가가치비율(%)*/,200,  "right", "center", true, false, null, null, null, 0, 0],
                   ["TRACE_VALUE",      resource.getMessage("TXT_TRACE_VALUE")/*TRACE VALUE*/,        100,  "right", "center", true, false, null, null, null, 0, 0],
                   ["CURRENCY", 		resource.getMessage("CURCY"),          					      100, "center", "center", true, false, null, null, null, 0, 0],
                   ["SALES_UNIT_PRICE", resource.getMessage("TXT_SALES_UNIT_PRICE")/*판가*/,          100,  "right", "center", true, false, null, null, null, 0, 0],
                   ["SALES_CURRENCY", 	resource.getMessage("CURCY"),          						  100, "center", "center", true, false, null, null, null, 0, 0],
                   ["FTA_GROUP_CD",     resource.getMessage("FTA,GROUP")/*충족여부*/,                 100, "center", "center", true,  true, null, null, null, 0, 0],
                   ["CO_DOC_NO",        resource.getMessage("DOC,NO")/*충족여부*/,                    150, "center", "center", true,  true, null, null, null, 0, 0],
                   ["UPDATE_YN",        resource.getMessage("")/*수정여부*/,                           80, "center", "center", true,  true, null, null, null, 0, 0]
                  ],
        initGrid_1 : function(data) {
        	var params = form.handle.getElementsParam("MMA019_01_form_01");
        	var vFlag = form.handle.getValue("MMA019_01_form_01", "flag");
        	var url;
        	
        	var dg_1 = grid.getObject("MMA019_01_grid_01");
            
        	if(vFlag == "insert") {
        		url = "/mm/pop/mmA019_01/selectItemListCaseInsert";
        	} else if(vFlag == "update" || vFlag == "view" || vFlag == "modify") {
        		url = "/mm/pop/mmA019_01/selectItemListCaseUpdate";
	        } else if(vFlag == "renew") {
	        	url = "/mm/pop/mmA019_01/selectItemListCaseRenew";
	        }
        	grid.init.setQueryParams(params);
        	grid.init.setURL(url);
    		grid.init.setPage(true);
            grid.init.setShowConfigPage(true);
            grid.init.setPageLayout(["resizeColumn"]);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(false);
            grid.init.setMergeData(["ITEM_CD", "ITEM_NAME"], "ITEM_CD");
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            grid.event.setOnDblClickRow(dg_1);
            
            // 컬럼view 결정
            var headerNaladisaCd   = this.header1[3]; // NALADISA CODE
            var headerTraceValue   = this.header1[10]; // Trace Value
            var headerTVCurrency   = this.header1[11]; // Trace Value currency
            if(form.handle.getValue("MMA019_01_form_01", "FTA_GROUP_CD") == "MN") {
            	headerNaladisaCd[6]   = true;
            } else if(form.handle.getValue("MMA019_01_form_01", "FTA_GROUP_CD") == "ML") {
            	headerTraceValue[6] = true;
            	headerTVCurrency[6] = true;
            }
            
            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
    	exchageToDate : function(date) {
    		var gp = form.handle.getValue("MMA019_01_form_01", "FTA_GROUP_CD");
    		var docType = form.handle.getValue("MMA019_01_form_01", "CO_DOC_TYPE");
    		var ldate;
    		
    		if(gp == "MN" && docType != "N") {
    			var toDate = calendar.util.getString2Date(date);
    			
    			ldate = calendar.util.getLastDay2String(toDate, "", calendar.util.getLocale(toDate));
			} else {
				ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, calendar.util.getString2Date(date)));
			}
    		
    		calendar.handle.setDate("MMA019_01_form_01", "END_DATE", ldate);
    	},
    	// 포괄시작일과 종료일이 1년을 넘을 경우 경고를 띄운다.
    	compareToDate : function(date) {
    		var docType = form.handle.getValue("MMA019_01_form_01", "CO_DOC_TYPE");
    		var coFrom = calendar.handle.getDate("MMA019_01_form_01", "APPLY_DATE");
    		var coTo = calendar.handle.getDate("MMA019_01_form_01", "END_DATE");
    		var start = calendar.util.getMonth2String(calendar.handle.getDate("MMA019_01_form_01", "APPLY_DATE"), "");
            var end = calendar.util.getMonth2String(calendar.handle.getDate("MMA019_01_form_01", "END_DATE"), "");
            var cmpEndDate = "";
        	var inDate = "";
        	
            if(form.handle.getValue("MMA019_01_form_01", "flag") == "modify") {
            	var modApplyDate = calendar.handle.getDate("MMA019_01_form_01", "MOD_APPLY_DATE");
	        	var modEndDate = calendar.handle.getDate("MMA019_01_form_01", "MOD_END_DATE");
	        	
	        	// 지정한 만료일자가 수정기간을 벗어나는지 체크
	        	if(coTo < modApplyDate || coTo > modEndDate) {
	        		alert(resource.getMessage("MSG_MODIFY_OVER_PROPER"));  // 포괄만료기간이 수정기간을 벗어났습니다.
	        		
	        		calendar.handle.setDate("MMA019_01_form_01", "END_DATE", calendar.handle.getDate("MMA019_01_form_01", "MOD_END_DATE"), false);
	        		return;
	        	}
    		}
            
            if(oUtil.isNull(date)) {
        		inDate = calendar.util.getDate2String(coTo);
        		cmpEndDate = calendar.util.getLastDay2String(coTo);
        	} else {
        		inDate = calendar.util.getString2Date(date);
        		cmpEndDate = calendar.util.getLastDay2String(inDate, null, NATION_DATE_VIEW);
        	}
        	
    		var betNum = calendar.util.between2Month(coFrom, coTo);
    		
    		var max_period = 12;
    		var max_msg = "MSG_COVER_CHECK_DATE";
    		var gp = form.handle.getValue("MMA019_01_form_01", "FTA_GROUP_CD");
    		
    		if(gp == "MN" && docType != "N") {
    			max_period = 1;
    			max_msg = "MSG_COVER_MONTH_OVER";
    		}
    		
    		var diffYn = true;
    		
    		// 포괄기간 체크
    		if(betNum > max_period) {
    			alert(resource.getMessage(max_msg));
    			diffYn = false;
    		}
    		
    		// 시작일과 종료일 체크
    		if(diffYn && !calendar.util.compear2Date(coFrom, coTo)) {
    			alert(resource.getMessage(MSG_APPLY_SE_DATE));
    			diffYn = false;
    		}
    		
    		// 포괄기간이 1개월인 경우에는 시작과 종료월이 동일월인지 체크함 
    		if(diffYn && max_period == 1 && start != end) {
            	alert(resource.getMessage("TXT_VMD001_003"));
            	diffYn = false;
            }
    		
    		if(form.handle.getValue("MMA019_01_form_01", "flag") != "update") {
	    		if(!diffYn) {
	    			var ldate = "";
	    			
					if(gp == "MN" && docType != "N") {
						ldate = calendar.util.getLastDay2String(coFrom, "", NATION_DATE_DB);
					} else {
						ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, coFrom, "", NATION_DATE_DB));
					}
					
		    		calendar.handle.setDate("MMA019_01_form_01", "END_DATE", ldate);
	    		}
	    		
	    		// 지정한 만료일자가 매월 말일인지 체크
	    		if(diffYn && inDate != cmpEndDate) {
	        		alert(resource.getMessage("MSG_CHECK_COVER_END_DATE"));
	        		calendar.handle.setDate("MMA019_01_form_01", "END_DATE", cmpEndDate, true);
	        		diffYn = false;
	        	}
    		}
    		
    		return diffYn;
    	},
    	onDblClick_MMA019_01_grid_01 : function(rowData) {
        	MMA019_01.dialog.openDialog_1('update');
        },
        callBack_selectBasicInfo : function(data) {
            form.handle.loadData("MMA019_01_form_01", data);
            
            var flag = form.handle.getValue("MMA019_01_form_01", "flag");
            var docType = form.handle.getValue("MMA019_01_form_01", "CO_DOC_TYPE");
            
            if(docType == "N") {
            	calendar.handle.readonly("MMA019_01_form_01", "APPLY_DATE", true);
            	form.util.setVisible("MMA019_01_div_01", true);
    		}
            
            if(flag == "modify" && !oUtil.isNull(data.MOD_APPLY_DATE)) {
            	if(data.MOD_APPLY_DATE >= data.MOD_END_DATE) {
            		calendar.handle.readonly("MMA019_01_form_01", "END_DATE", true);
            		form.util.setVisible("MMA019_01_btn_03", false);
            		form.util.setVisible("MMA019_01_btn_04", false);
	    			form.util.setVisible("MMA019_01_span_01", false);
	    			form.util.setVisible("MMA019_01_p_01", false);
	    			
            		alert(resource.getMessage("MSG_REJECT_HANDLE"));
            	} else {
	            	calendar.handle.readonly("MMA019_01_form_01", "END_DATE", false);
	            	
	    			form.util.setVisible("MMA019_01_span_01", true);
	    			form.util.setVisible("MMA019_01_btn_03", true);
	    			form.util.setVisible("MMA019_01_btn_04", true);
	    			form.util.setVisible("MMA019_01_p_01", true);
            	}
            }
            
            if(!oUtil.isNull(data.PRF_FILE_NAME)) {
    			form.util.setVisible("MMA019_01_form_01_PRF_FILE_DOWN", true);
    		} else {
    			form.util.setVisible("MMA019_01_form_01_PRF_FILE_DOWN", false);
    		}
            
            $("#MMA019_01_form_01_PRF_FILE_NAME_SPAN").text(data.PRF_FILE_NAME);
        },
        //확인서기본정보 저장이 성공적으로 완료된 후
        callBack_saveBasicInfo : function(data) {
        	MMA019_01.init.coModifyChecker = false;
        	
        	MMA019_01.ui.onChangeIssueUI(data);
        	
        	form.handle.setValue("MMA019_01_form_01", "PRF_FILE_SEQ", "1");
        	
        	var pid = form.handle.getValue("MMA019_01_form_01", "TARGET_PID");
        	if(!oUtil.isNull(pid)) {
        		var pro_id = eval("window." + pid + ".dialog");
        		
        		if(typeof(pro_id["callByMMA019_01"]) == "function") {
        			pro_id["callByMMA019_01"](data);
        		}
        	}
        	
        	$("#MMA019_01_form_01_PRF_FILE_NAME_SPAN").text(data.PRF_FILE_NAME);
        },
        uploadProgressBar : function(idx, datas) {
     		if(typeof datas == "object") {
	     		var total = datas.length;
	     		var rate = (idx/total)*100;
	     		
	            $('#MMA019_01_de_progress').progressbar('setValue', Math.floor(rate));
	            
	            MMA019_01.control.appendCoData(idx+1, datas);
     		}
        },
        callBack_saveOriginModifyInfo : function(data) {
        	var dg_1 = grid.getObject("MMA019_01_grid_01");
        	
        	MMA019_01.init.coModifyChecker = true;
        	form.handle.setValue("MMA019_01_form_01", "flag", "renew");
			
        	var endFormDate = calendar.handle.getDate("MMA019_01_form_01", "END_DATE");
			var inApplyDate = calendar.util.addDate2String("d", 1, endFormDate, "", NATION_DATE_DB);
			
			// 제출일(오늘날짜), 포괄기간 수정(시작일:(수정전 종료일자+1일), 종료일:수정전 종료일자)
			form.handle.setValue("MMA019_01_form_01", "BF_CO_DOC_NO", form.handle.getValue("MMA019_01_form_01", "CO_DOC_NO"));
			calendar.handle.setDate("MMA019_01_form_01", "ISSUE_DATE", calendar.util.toDate2String());
			calendar.handle.setDate("MMA019_01_form_01", "APPLY_DATE", inApplyDate);
			calendar.handle.setDate("MMA019_01_form_01", "END_DATE", calendar.handle.getDate("MMA019_01_form_01", "MOD_END_DATE"), false);
			form.handle.setValue("MMA019_01_form_01", "gridData", grid.util.convertJson2Rows(grid.handle.getAllRows(dg_1)));
			
			MMA019_01.ui.onModifyIssueUI();
			
			var pid = form.handle.getValue("MMA019_01_form_01", "TARGET_PID");
        	if(!oUtil.isNull(pid)) {
        		var pro_id = eval("window." + pid + ".dialog");
        		
        		if(typeof(pro_id["callByMMA019_01"]) == "function") {
        			pro_id["callByMMA019_01"](data);
        		}
        	}
        }
    },
    // 업무구현
    control : {
    	//확인서기본정보 조회
    	selectBasicInfo : function() {
    		var obj = form.getObject("MMA019_01_form_01");
    		
    		var vFlag = nullToString(form.handle.getValue("MMA019_01_form_01", "flag"));
    		
    		if(vFlag == "insert" || vFlag == "renew" || vFlag == "pending") {
    			form.init.setURL("/mm/pop/mmA019_01/selectBasicInfoCaseInsert");
    		} else if(vFlag == "update" || vFlag == "view" || vFlag == "modify") {
    			form.init.setURL("/mm/pop/mmA019_01/selectBasicInfoCaseUpdate");
	    	}
    		
            form.init.setProgressFlag(true);
            form.init.setCallBackFunction("callBack_selectBasicInfo");
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectItemListQuest : function() {
        	var questMsg = resource.getMessage("REWRT")+" "+resource.getMessage("MSG_CONFIRM_EXECUTE");
        	questMsg += "<br><br>1." + resource.getMessage("CSTMR, HS,CODE, APYDT");
        	questMsg += ", 2." + resource.getMessage("ORG, INFMT, RESTO");
        	
        	$.messager.confirm(CNFIR, questMsg, function(flag) {
        		if(flag) {
        			MMA019_01.control.selectItemList("CH");
        		}
        	});
        },
        //품목별원산지정보 조회
        selectItemList : function(state) {
        	var dg_1 = grid.getObject("MMA019_01_grid_01");
        	var params = form.handle.getElementsParam("MMA019_01_form_01");
        	
        	if(state == "CH") {
        		params.CUSTOMER_HS_CODE_YN = "Y";
        	} else {
        		params.CUSTOMER_HS_CODE_YN = "N";
        	}
        	
        	form.handle.setValue("MMA019_01_form_01", "CUSTOMER_HS_CODE_YN", params.CUSTOMER_HS_CODE_YN);
        	
        	var vFlag = form.handle.getValue("MMA019_01_form_01", "flag");
        	if(vFlag == "insert") {
        		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA019_01/selectItemListCaseInsert", params);
        	} else if(vFlag == "update" || vFlag == "view" || vFlag == "modify") {
        		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA019_01/selectItemListCaseUpdate", params);
	        } else if(vFlag == "renew") {
	        	grid.handle.sendRedirect(dg_1, "/mm/pop/mmA019_01/selectItemListCaseRenew", params);
	        }
        },
        //버튼액션(제출) : 확인서 정보 저장
        saveMasterDetailInfo : function() {
        	var vFlag = form.handle.getValue("MMA019_01_form_01", "flag");
        	var hubVendor = form.handle.getValue("MMA019_01_form_01", "HUB_VENDOR_YN");
        	var issueMsg = "당사는 TOMS 서비스를 이용하는 업체로 TOMS HUB를 통해 원산지 확인서를 제출하길 권합니다."+
        	     "본 시스템에서 직접 원산지 확인서를 제출하는 경우, 소명에 대한 모든 책임이 당사에게 있음을 알려드립니다.<br><br>"+
        		 "TOMS HUB에 대한 문의가 있으시면 콜센터(1588-1037) 또는 silverstone@daeucna.com으로 연락바랍니다.<br><br>"+
        		 "확인서 제출을 계속 진행하시겠습니까?";
        	
        	if(hubVendor == "Y" && !(vFlag == "update" || vFlag == "view" || vFlag == "pending")) { // 수정요청, 상세보기, 미결요청이 아닌 경우
	        	// HUB등록 고객사인 경우 포탈에서 등록할 것인지 확인 후 등록을 진행함.(나중에 다국어 처리가 필요함)
	        	$.messager.confirm(resource.getMessage("CNFIR"), issueMsg, function(flag) {
	        		if(flag) {
	        			MMA019_01.control.saveCoCertifyInfo();
	        		}
	        	});
        	} else {
        		MMA019_01.control.saveCoCertifyInfo();
        	}
        },
        saveCoCertifyInfo : function() {
        	var obj = form.getObject("MMA019_01_form_01");
        	var dg_1 = grid.getObject("MMA019_01_grid_01");
        	
        	if(!form.handle.isValidate("MMA019_01_form_01")) return;
        	
        	var vFlag = nullToString(form.handle.getValue("MMA019_01_form_01", "flag"));
        	var signReg = form.handle.getValue("MMA019_01_form_01", "SING_REG_YN");
        	var fileReg = form.handle.getValue("MMA019_01_form_01", "PRF_FILE");
        	
        	if(oUtil.isNull(fileReg) && signReg != "Y") {
        		alert(resource.getMessage("MSG_NO_REGISTE_VENDOR_SIGN"));
        		return;
        	}
        	
        	//날짜 검증하기
        	if(!MMA019_01.event.compareToDate()) {
        		return;
        	}
        	
        	//필수값 누락 여부 체크
        	var gridRowCnt = grid.handle.getRowCount(dg_1);
        	var rowData = grid.handle.getAllRows(dg_1);
        	
        	if(gridRowCnt <= 0) {
        		alert(resource.getMessage("MSG_NO_INSERT")); /*저장할 데이터가 없습니다.*/
        		return;
        	}
        	
        	for(var i = 0; i < gridRowCnt; i++) {
        		if(oUtil.isNull(rowData[i]["HS_CODE"]) || (oUtil.isNull(rowData[i]["FTA_CO_YN_Y"]) && oUtil.isNull(rowData[i]["FTA_CO_YN_N"]))) {
        			alert(resource.getMessage("ERROR_REQUIRED_YN") + "<br/>" 
        					+ resource.getMessage("LTIT, NAME") + ":" + rowData[i]["ITEM_CD"] + ", "
        					+ resource.getMessage("AGRET,CODE") + ":" + rowData[i]["FTA_CD"]); /*필수입력오류*/
        			
        			grid.handle.selectRowIndex(dg_1, i);
        			
        			return;
        		}
        	}
        	
        	var confMsg = resource.getMessage("MSG_CONFIRM_SAVE"); /*저장하시겠습니까?*/
        	$.messager.confirm(CNFIR, confMsg, function(flag) {
        		if(flag) {
        			form.handle.setValue("MMA019_01_form_01", "gridData", grid.util.convertJson2Rows(grid.handle.getAllRows(dg_1)));
        			
        			form.init.setURL("/mm/pop/mmA019_01/insertRcvCoInfo");
        			form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY")); /*요청하신 작업이 성공적으로 처리되었습니다.*/
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
        			form.init.setCallBackFunction("callBack_saveBasicInfo");
        			
        			form.submit(obj);
        		}
        	});
        },
        //버튼액션(삭제) : 품목별원산지정보에서 자재 삭제
        deleteItemList : function() {
        	var dg_1 = grid.getObject("MMA019_01_grid_01");

        	if(!grid.handle.isChecked(dg_1)) {
        		alert(resource.getMessage("MSG_NO_SELECT")); /*선택된 데이터가 없습니다.*/
        		return;
        	}
        	
			var chkDG = grid.handle.getCheckedRowsIndex(dg_1); //체크된 그리드 인덱스 배열
			var chkDGLen = chkDG.length;
			
			for(var i = 0; i < chkDGLen; i++) {
				grid.handle.removeRow(dg_1, chkDG[i]);
			}
        },
        //버튼액션(Print) : 
        print : function() {
        	alert("추후 개발");
        },
        //증빙파일 다운받기
        selectProofFile : function() {
        	var obj = form.getObject("MMA019_01_form_01");
        	
            form.init.setURL("/mm/pop/mmA019_01/selectProofFile");
            form.init.setProgressFlag(false);
			
			form.submit(obj);
        },
        appendCoData : function(index, datas) {
        	var dg_1 = grid.getObject("MMA019_01_grid_01");
        	var idx = 0;
        	var total = datas.length;
        	
        	if(!oUtil.isNull(index)) {
            	idx = index;
            } else {
            	MMA019_01.event.uploadProgressBar(0);
            }
        	
        	try {
            	if(idx == total) {
            		MMA019_01.event.uploadProgressBar(100);
            		
                	setTimeout(function() {
            			$('#MMA019_01_main_win').window('close');
            		}, 1000);
                	
                	return;
                } else {
                	if(idx == 0) $('#MMA019_01_main_win').window('open');
                	
                	// 중복판정인지 체크해서 중복되지 않은 경우에만 판정을 요청함
                    setTimeout(function() {
                    	grid.handle.appendRow(dg_1, datas[idx]);

                    	MMA019_01.event.uploadProgressBar(idx, datas);
                    }, 10);
                }
            } catch(ex) {
            	$('#MMA019_01_main_win').window('close');
                alert(resource.getMessage("MSG_UNSPECIFIED_ERROR")+"<br>"+ex);
            }
        },
        saveOriginModifyInfo : function() {
        	var obj = form.getObject("MMA019_01_form_01");
        	var endDate = calendar.handle.getDate("MMA019_01_form_01", "END_DATE");
        	var modEndDate = calendar.handle.getDate("MMA019_01_form_01", "MOD_END_DATE");
        	
        	//날짜 검증하기
        	if(!MMA019_01.event.compareToDate()) {
        		return;
        	} else if(endDate == modEndDate) { // 지정한 만료일자가와 수정가능한 일자가 동일한지 체크
        		alert(resource.getMessage("MSG_MODIFY_NOT_FOUND"));
        		return;
        	} else {
        		var confMsg = resource.getMessage("MSG_REGISTER") + "<br>" + resource.getMessage("MSG_CONFIRM_UPDATE");
        		
            	$.messager.confirm(resource.getMessage("CNFIR"), confMsg, function(flag) {
            		if(flag) {
            			form.init.setURL("/mm/pop/mmA019_01/updateModifyEndDate");
            			form.init.setProgressFlag(true);
            			form.init.setSucceseMessage(resource.getMessage("MSG_MODIFIED_NEXT_ISSUE")); /*포괄만료일이 정상적으로 수정되었습니다. 원산지 정보를 수정하신 후 제출하시기 바랍니다.*/
            			form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
            			form.init.setCallBackFunction("callBack_saveOriginModifyInfo");
            			
            			form.submit(obj);
            		}
            	});
        	}
        },
        ftaApplyOfOrigin : function(org) {
        	var dg_1 = grid.getObject("MMA019_01_grid_01");
        	var rowCnt = grid.handle.getRowCount(dg_1);
        	
        	if(rowCnt == 0) {
        		alert(resource.getMessage("MSG_NOT_EXIST_DATA"));
        		return;
        	}
        	
        	var rows = grid.handle.getAllRows(dg_1);
        	var ftaCd = combo.handle.getValue("MMA019_01_form_03", "FTA_CD");
        	
        	try {
    			$.messager.progress({
                    text: "Data loading..."
                });
        		
        		setTimeout(function() {
		        	for(var i = 0; i < rowCnt; i++) {
		        		if(oUtil.isNull(ftaCd) || ftaCd == rows[i].FTA_CD) {
			        		rows[i].FTA_CO_YN = org;
			        		
			        		grid.handle.setChangeRowValue(dg_1, i, rows[i]);
		        		}
		        		
		        		if((i+1) == rowCnt) {
	        				$.messager.progress('close');
		        		}
		        	}
        		}, 1000);
        	} catch(e) {
        		if(SESSION.USER_ID == "fta") {
        			console.log(e);
        		}
        	}
        }
    },
    // 다이얼로그 구현
    dialog : {
    	//버튼액션(추가, 수정) : 외부원산지품목 정보
        openDialog_1 : function(flag) {
            var dg_1 = grid.getObject("MMA019_01_grid_01");
            var dl_1 = dialog.getObject("MMA020_01_dailog_01");
            
        	if(flag == "update" && !grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_NO_SELECT")); /*선택된 데이터가 없습니다.*/
    			return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("MMA019_01_form_01");
            var params2 = {};
            
            if(flag == "update") {
            	params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);
            
            //그리드의 idx값 조회
            if(flag == "update") {
	        	params.GRID_IDX = grid.handle.getCurrentRowIndex(dg_1);
            }
            params.TARGET_PID = "MMA019_01";
            params.flag       = flag;
            params.FTA_CO_YN  = (params2.FTA_CO_YN_Y == "Y")?"Y":"N";
            
            // 팝업 셋팅
            dialog.init.setHeight(200);
            dialog.init.setTitle(resource.getMessage("MMA020_01"));
            dialog.init.setURL("/mm/pop/mmA020_01");
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        },
        // 버튼액션(엑셀 업로드) : 품목별원산지정보 저장 기능
        openDialog_2 : function() {
    		var dg_2 = grid.getObject("MMA019_01_grid_01");
    		var dl_2 = dialog.getObject("MMA023_01_dailog_01");
    		
    		// 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("MMA019_01_form_01");
            var params2 = {};
            
            $.extend(params, params1, params2);

            //추가 파라매터
            params.TARGET_PID = "MMA019_01";
    		
    		// 팝업 셋팅
    		dialog.init.setTitle(resource.getMessage("MMA023_01"));
    		dialog.init.setURL("/mm/pop/mmA023_01");
    		dialog.init.setQueryParams(params);
    		
    		dialog.open(dl_2);
        },
        // 이미지 섬네일(원본 이미지로 보기)
        openDialog_3 : function() {
        	var dg_3 = grid.getObject("MMA019_01_grid_01");
        	var dl_3 = dialog.getObject("MMA026_01_dailog_01");
        	
        	var flag       = form.handle.getValue("MMA019_01_form_01", "flag");
        	var coDocNo    = form.handle.getValue("MMA019_01_form_01", "CO_DOC_NO");
            var vendorCd   = form.handle.getValue("MMA019_01_form_01", "VENDOR_CD");
            var divisionCd = form.handle.getValue("MMA019_01_form_01", "DIVISION_CD");
            var companyCd  = form.handle.getValue("MMA019_01_form_01", "COMPANY_CD");

            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
        	params.TARGET_PID  = "MMA019_01";  
        	params.nowDateTime = new Date().getTime();
        	if(flag == "insert") {
        		params.reqType  = "VENDOR";  
        		params.imageKey = companyCd + "," + vendorCd
            } else {
            	params.reqType  = "CERTIFICATE";  
            	params.imageKey = coDocNo + "," + vendorCd + "," + divisionCd + "," + companyCd
            }
        	
        	// 팝업 셋팅
        	dialog.init.setURL("/mm/pop/mmA026_01");
        	dialog.init.setQueryParams(params);
        	dialog.init.setTitle(resource.getMessage("MMA026_01"));
        	dialog.open(dl_3);
        },
        callByMMA020_01 : function(datas) {
        	var dg_1 = grid.getObject("MMA019_01_grid_01");
        	var dl_1 = dialog.getObject("MMA020_01_dailog_01");
        	
        	if(!oUtil.isNull(datas)) {
        		if(datas.flag == "insert") {
        			var dg_1 = grid.getObject("MMA019_01_grid_01");
        			var gridRows = grid.handle.getAllRows(dg_1);
    	            var gridRowCnt = grid.handle.getRowCount(dg_1);
    	            
    	            for(var i = 0; i < gridRowCnt; i++) {
        				grid.handle.selectRowIndex(dg_1, i);
        				
        				if(datas.ITEM_CD == gridRows[i].ITEM_CD && datas.FTA_CD == gridRows[i].FTA_CD) {
        					alert(resource.getMessage("MSG_ARD_ADD_ITEM_FTA")); /*이미 추가된 품목과 FTA협정입니다.*/
        					return;
        				}
        			}
    	            
    	            grid.handle.appendRow(dg_1, datas);
        		} else {
        			var selRow = grid.handle.getCurrentRowIndex(dg_1);
        			
        			if(datas.FTA_CO_YN == "Y") {
        				datas.FTA_CO_YN_Y = "Y";
        				datas.FTA_CO_YN_N = "";
        			} else {
        				datas.FTA_CO_YN_Y = "";
        				datas.FTA_CO_YN_N = "Y";
        			}
        			datas.STATUS = "";
        			
        			grid.handle.setChangeRowValue(dg_1, selRow, datas);
        			grid.handle.selectRowIndex(dg_1, selRow); // 수정된 row를 선택함
        			
        			dialog.handle.close(dl_1);
        		}
        	}
        },
        callByMMA023_01 : function(datas) {
        	var dg_1 = grid.getObject("MMA019_01_grid_01");
        	var rows = grid.handle.getAllRows(dg_1);
        	var rowCnt = grid.handle.getRowCount(dg_1);
        	var vFlag = nullToString(form.handle.getValue("MMA019_01_form_01", "flag"));
        	
        	// 확인서 번호는 유지해야 함
        	if(vFlag != "insert") {
	        	for(var i = 0; i < rowCnt; i++) {
	        		var itemCd = rows[i].ITEM_CD;
	        		var docNo = rows[i].CO_DOC_NO;
	        		
	        		for(var k = 0; k < datas.length; k++) {
	        			if(itemCd == datas[k].ITEM_CD) {
        	        		datas[k].CO_DOC_NO = docNo;
	        			}
	        		}
	        	}
    		}
        	
        	// 엑셀로 업로드 시 모든 row를 삭제하고, 엑셀 정보를 등록한다.
        	grid.handle.clearAll(dg_1);
        	
        	MMA019_01.control.appendCoData(null, datas);
        	
        	var dl_1 = dialog.getObject("MMA023_01_dailog_01");
        	dialog.handle.close(dl_1);
        },
        closeDialog_top : function() {
        	if(MMA019_01.init.coModifyChecker) {
        		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_NOT_FINISHED_AND_CLOSE"), function(flag) {
        			if(flag) {
        				var dl_1 = dialog.getObject("MMA019_01_dailog_01");
        	        	dialog.handle.close(dl_1);
        			}
        		});
        	} else {
        		var vFlag = nullToString(form.handle.getValue("MMA019_01_form_01", "flag"));
        		
        		if(vFlag == "modify") { // 원산지 수정중에는 창을 닫을지 확인한다. 
	        		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_NOT_MODIFY_CLOSE"), function(flag) {
	        			if(flag) {
	        				var dl_1 = dialog.getObject("MMA019_01_dailog_01");
	        	        	dialog.handle.close(dl_1);
	        			}
	        		});
        		} else {  // 수정이 완료되면 바로창을 닫는다.
        			var dl_1 = dialog.getObject("MMA019_01_dailog_01");
    	        	dialog.handle.close(dl_1);
        		}
        	}
        }
    },
    // 화면 UI 변경
    ui : {
    	onChangeChkAuto : function() { // 신규 및 갱신
    		if(form.handle.isChecked("MMA019_01_form_01", "chkAuto")) {
    			form.handle.setValue("MMA019_01_form_01", "chkAuto", "Y");
    			form.handle.setValue("MMA019_01_form_01", "CO_DOC_NO", "");
    			form.handle.addClass("MMA019_01_form_01", "CO_DOC_NO", "search_readOnly");
        		form.handle.readonly("MMA019_01_form_01", "CO_DOC_NO", true);
    			form.handle.setValidate("MMA019_01_form_01", "CO_DOC_NO", false);
    		} else {
    			form.handle.setValue("MMA019_01_form_01", "chkAuto", "N");
    			form.handle.removeClass("MMA019_01_form_01", "CO_DOC_NO", "search_readOnly");
        		form.handle.readonly("MMA019_01_form_01", "CO_DOC_NO", false);
    			form.handle.setValidate("MMA019_01_form_01", "CO_DOC_NO", true);
    		}
    	},
    	onChangeIssueUI : function(data) { // 상세조회
    		form.handle.closeAll("MMA019_01_form_01");
    		
    		if(!oUtil.isNull(data)) {
	    		form.handle.setValue("MMA019_01_form_01", "CO_DOC_NO", data.CO_DOC_NO);
	    		form.handle.setValue("MMA019_01_form_01", "PRF_FILE_NAME", data.PRF_FILE_NAME);
	    		$("#MMA019_01_form_01_PRF_FILE_NAME_SPAN").text(data.PRF_FILE_NAME); 
    		}
    		
    		form.util.setVisible("MMA019_01_form_02_chkAuto", false);
    		form.util.setVisible("MMA019_01_form_01_PRF_FILE_DW", true);
    		form.util.setVisible("MMA019_01_form_01_PRF_FILE", false);
    		form.util.setVisible("MMA019_01_form_01_chkAuto", false);
    		
    		form.util.setVisible("MMA019_01_btn_01", false);
    		form.util.setVisible("MMA019_01_btn_32", false);
    		form.util.setVisible("MMA019_01_btn_36", false);
    		form.util.setVisible("MMA019_01_btn_34", false);
    		form.util.setVisible("MMA019_01_btn_35", false);
    		form.util.setVisible("MMA019_01_btn_40", true);
    		form.util.setVisible("MMA019_01_tr_02", false);
    	},
    	onChangeUpdateUI : function() {  // 수정
    		form.util.setVisible("MMA019_01_form_01_chkAuto", false);
    		
    		form.handle.readonly("MMA019_01_form_01", "CO_DOC_NO", true);
    		form.handle.addClass("MMA019_01_form_01", "CO_DOC_NO", "input_readOnly");
    		calendar.handle.readonly("MMA019_01_form_01", "ISSUE_DATE", true);
    		calendar.handle.readonly("MMA019_01_form_01", "END_DATE", true);
    	},
    	onModifyIssueUI : function() {
    		form.handle.closeAll("MMA019_01_form_01", false);
    		
    		// 확인서 번호 자동 채번 체크 
			form.util.setVisible("MMA019_01_form_01_chkAuto", true);
			form.handle.setValue("MMA019_01_form_01", "CO_DOC_NO", "");
			
			MMA019_01.ui.onChangeChkAuto();
			
			// 첨부파일 초기화
			form.util.setVisible("MMA019_01_form_01_PRF_FILE_DW", false);
    		form.util.setVisible("MMA019_01_form_01_PRF_FILE", true);
    		if(form.handle.getValue("MMA019_01_form_01", "REGISTED_BY") != "2") {
    			form.util.setVisible("MMA019_01_form_02_chkAuto", true);
    		}
    		
    		// 버튼 초기화
    		form.util.setVisible("MMA019_01_btn_01", true);
    		form.util.setVisible("MMA019_01_btn_32", true);
    		form.util.setVisible("MMA019_01_btn_36", true);
    		form.util.setVisible("MMA019_01_btn_34", true);
    		form.util.setVisible("MMA019_01_btn_35", true);
    		form.util.setVisible("MMA019_01_btn_40", false);
    		form.util.setVisible("MMA019_01_btn_03", false);
    		
    		// UI 입력란 초기화
    		form.util.setVisible("MMA019_01_span_01", false);
    		form.handle.addClass("MMA019_01_form_01", "VENDOR_NAME", "input_readOnly");
    		form.handle.readonly("MMA019_01_form_01", "VENDOR_NAME", true);
    		form.handle.addClass("MMA019_01_form_01", "FTA_GROUP_NM", "input_readOnly");
    		form.handle.readonly("MMA019_01_form_01", "FTA_GROUP_NM", true);
    		calendar.handle.readonly("MMA019_01_form_01", "ISSUE_DATE", false);
    		calendar.handle.readonly("MMA019_01_form_01", "APPLY_DATE", true);
    		calendar.handle.readonly("MMA019_01_form_01", "END_DATE", true);
    	},
    	onChangeFilePassChk : function() {
    		if(form.handle.isChecked("MMA019_01_form_01", "chkFile")) {
    			form.handle.setValue("MMA019_01_form_01", "PRF_FILE", "");
    			form.handle.setValidate("MMA019_01_form_01", "PRF_FILE", false);
    			form.util.setVisible("MMA019_01_form_01_PRF_FILE", false);
    		} else {
    			form.util.setVisible("MMA019_01_form_01_PRF_FILE", true);
    			form.handle.setValidate("MMA019_01_form_01", "PRF_FILE", true);
    		}
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {
        setFiles : function(pfile) {
    		var fileForm = form.getObject("MMA019_01_form_01", "PRF_FILE");
    		
    		// 파일 확장자
    		if(fileForm.val() != ""){
    			var ext = fileForm.val().split('.').pop().toLowerCase();
    			var echk = false;
    			
			    if($.inArray(ext, ['pdf','xls','xlsx','doc','docx','zip']) == -1) {
			    	alert(resource.getMessage("MSG_EXT_PDF_XLS_DOC"));
			    	echk = true;
			    } else {   // 파일 크기 체크
		    		var file = pfile.files;
		            var tts = file[0].size;
		            
		            if(tts > parseInt(5000000)) {
		                alert(resource.getMessage("MSG_FILE_SIZE_OVER") + "(" + resource.getMessage("MAX, SIZE") + " : 5MB)");
		                echk = true;
		            }
			    }
	            
	            if(echk) {
	            	form.handle.setValue("MMA019_01_form_01", "PRF_FILE", "");
	            	return;
	            }
			}
    	}
    },
    // 엑셀다운로드 구현
    excel : {
    	// 협력사 원산지 확인서 등록 양식
    	excelDownload_1 : function() {
      		var dg_1 = grid.getObject("MMA019_01_grid_01");
      		var rows = grid.handle.getAllRows(dg_1);
        	var rowCnt = grid.handle.getRowCount(dg_1);
        	var vFlag = nullToString(form.handle.getValue("MMA019_01_form_01", "flag"));
        	
        	if(rows.length == 0) {
        		alert(resource.getMessage("MSG_NOT_EXIST_DATA"));
        		return;
        	}
        	
        	if(!(vFlag == "insert" || vFlag == "pending" || vFlag == "renew")) {
	      		for(var i = 0; i < rowCnt; i++) {
	        		var docNo = rows[i].CO_DOC_NO;
	        		
	        		if(oUtil.isNull(docNo)) {
	        			alert(resource.getMessage("MSG_FAILED_BODY")+"("+resource.getMessage("TXT_COO_CERTIFY_NO,MSG_NOT_FOUND_01")+")");
	        			return;
	        		}
	        	}
        	}
      		
      		var fobj = form.getObject("MMA019_01_form_01");
      		var inRows = new Array();
      		
      		for(var i = 0; i < rowCnt; i++) {
        		var docNo = rows[i].CO_DOC_NO;
        		var itemCd = rows[i].ITEM_CD;
        		var ftaCd = rows[i].FTA_CD;
        		
        		if(i == 0) {
        			inRows.push(rows[i]);
        		} else {
        			var chk = true;
        			
        			if(reqType == "NEWFTA") {
        				for(var k = 0; k < inRows.length; k++) {
	    		    		if(itemCd == inRows[k].ITEM_CD && ftaCd == inRows[k].FTA_CD) {
	    		    			chk = false;
	    		    			break;
	    		    		}
        				}
        			} else {
	        		    if(vFlag == "insert") {
	        		    	for(var k = 0; k < inRows.length; k++) {
	        		    		if(itemCd == inRows[k].ITEM_CD) {
	        		    			chk = false;
	        		    			break;
	        		    		}
	        		    	}
	        		    } else {
	        		    	for(var k = 0; k < inRows.length; k++) {
	        		    		if(itemCd == inRows[k].ITEM_CD && docNo == inRows[k].CO_DOC_NO) {
	        		    			chk = false;
	        		    			break;
	        		    		}
	        		    	}
	    		    	}
        			}
        			
        		    if(chk) inRows.push(rows[i]);
    		    }
        	}
      		
      		form.handle.setValue("MMA019_01_form_01", "gridData", grid.util.convertJson2Rows(inRows));
      		
      		if(vFlag == "insert") {
      			if(form.handle.getValue("MMA019_01_form_01", "REQ_TYPE") != "NEWFTA") {
      				form.handle.setValue("MMA019_01_form_01", "excel", "N");
      			}
      			
				form.init.setURL("/mm/pop/mmA019_01/selectItemListCaseInsert.fle");
			} else {
				form.handle.setValue("MMA019_01_form_01", "excel", "Y");
				
				form.init.setURL("/mm/pop/mmA019_01/selectItemListCaseRenew.fle");
			}
  	        
  	        // parameter description : grid객체, form객체, 파일명, 시트명
  	        form.excelSubmit(dg_1, fobj, resource.getMessage("TXT_ITEMBY_ORG_INFO"), resource.getMessage("TXT_ITEMBY_ORG_INFO")); /*품목별 원산지 정보*/
  	    },
  	    excelDownload_2 : function() {
	        var dg_1 = grid.getObject("MMA019_01_grid_01");
	        var fobj = form.getObject("MMA019_01_form_01");
	        
	        form.init.setURL("/mm/pop/mmA019_01/selectItemListCaseUpdate.fle");
	        
	        // parameter description : grid객체, form객체, 파일명, 시트명
	        form.excelSubmit(dg_1, fobj, resource.getMessage("TXT_ITEMBY_ORG_INFO"), resource.getMessage("TXT_ITEMBY_ORG_INFO"));
	    }
    },
    config : {
    	applyFtaNation : function() {
			if(form.handle.getValue("MMA019_01_form_01", "REGISTED_BY") == "2" && SESSION.USER_ID != "fta") {
    			form.util.setVisible("MMA019_01_form_02_chkAuto", false);
    		}
			
			if(form.handle.getValue("MMA019_01_form_01", "flag") == "update") {
				form.util.setVisible("MMA019_01_tr_01", true);
			}
		}
    }
    
};
