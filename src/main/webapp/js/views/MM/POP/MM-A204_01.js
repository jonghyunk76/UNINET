/******************************************************************************************
 * 작성자 : 이재욱
 * Program Id : MM-A204_01
 * 작업일자 : 2016.05.09
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA204_01.config.applyFtaNation(); // FTA 협정별 UI변경
    MMA204_01.init.initComponent();
}

var MMA204_01 = {
    
    // 초기값 설정
    init : {
    	flag : form.handle.getValue("MMA204_01_form_01", "flag"),
        initComponent : function() {
        	MMA204_01.ui.changeBtnCheck();
        	
        	if(MMA204_01.init.flag != "insert") {
        		MMA204_01.ui.changeUpdateUI();
        	} else {
        		MMA204_01.ui.changeAutoCheck();
        	}
        	
        	if(form.handle.getValue("MMA204_01_form_01", "CO_ISSUE_TYPE") == "M") { // 자율발급
    			form.util.setVisible("MMA204_01_btn_11", true);
    		} else {
    			form.util.setVisible("MMA204_01_btn_11", false);
    		}
        	
        	MMA204_01.calendar.initCalendar_1();
        	MMA204_01.calendar.initCalendar_2();
        	MMA204_01.calendar.initCalendar_3();
        	if(!oUtil.isNull(form.handle.getValue("MMA204_01_form_01", "SHIPPING_DATE"))) {
        		MMA204_01.calendar.initCalendar_4();
        	} else {
        		form.handle.setValue("MMA204_01_form_01", "SHIPPING_DATE", " ");
        	}
        	
        	MMA204_01.combobox.initCombo_1();
        	
        	MMA204_01.datagrid.initGrid_1();
        	MMA204_01.datagrid.initGrid_2();
        	
            // 초기화면에 보여줄 데이터 조회
        	MMA204_01.control.selectCoMasterInfo();
        }
    }, 
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
        	var objDate = calendar.getObject("MMA204_01_form_01", "ISSUE_DATE");
        	
        	calendar.init.setRequired(true);
        	if(MMA204_01.init.flag == "insert") {
        		calendar.init.setReadonly(false);
        	} else {
        		calendar.init.setReadonly(true);
        	}
        	
            calendar.create(objDate);
        },
        initCalendar_2 : function() {
        	var objDate = calendar.getObject("MMA204_01_form_01", "APPLY_DATE");
        	
        	if(MMA204_01.init.flag == "insert") {
        		var date;
        		var gp = form.handle.getValue("MMA204_01_form_01", "FTA_GROUP_CD");
        		
        		if(gp == "KR") {
        			date = calendar.util.getDate2String(form.handle.getValue("MMA204_01_form_01", "INVOICE_DATE"), null, NATION_DATE_VIEW);
        		} else {
        			date = calendar.util.getFirstDay2String(form.handle.getValue("MMA204_01_form_01", "INVOICE_DATE"));
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
        	var objDate = calendar.getObject("MMA204_01_form_01", "END_DATE");
            
            if(MMA204_01.init.flag == "insert") {
            	var fdate = "";
            	var ldate = "";
            	var gp = form.handle.getValue("MMA204_01_form_01", "FTA_GROUP_CD");
            	
            	if(gp == "KR" && form.handle.getValue("MMA204_01_form_01", "CO_DOC_TYPE") == "N") {
        			ldate = calendar.util.getDate2String(form.handle.getValue("MMA204_01_form_01", "INVOICE_DATE"), null, NATION_DATE_VIEW);
        		} else {
        			fdate = calendar.util.getFirstDay2String(form.handle.getValue("MMA204_01_form_01", "INVOICE_DATE"));
        			ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, fdate));
        		}
        		
        		calendar.init.setInitDate(ldate);
        		calendar.init.setCallFunction("compareToDate");
        		calendar.init.setReadonly(false);
        	} else {
        		calendar.init.setCallFunction("");
        		calendar.init.setReadonly(true);
        	}

            calendar.create(objDate);
        },
        initCalendar_4 : function() {
        	var objDate = calendar.getObject("MMA204_01_form_01", "SHIPPING_DATE");
        	var ldate = calendar.util.getDate2String(form.handle.getValue("MMA204_01_form_01", "SHIPPING_DATE"), null, NATION_DATE_DB);
        	
        	calendar.init.setRequired(false);
        	calendar.init.setInitDate(ldate);
        	calendar.init.setReadonly(true);
        	
            calendar.create(objDate);
        }
    },
    // 콤보박스 그리드 생성
    combobox : {
    	initCombo_1 : function() {
            var vCompanyCd = form.handle.getValue("MMA204_01_form_01", "COMPANY_CD");
            var vDivisionCd = form.handle.getValue("MMA204_01_form_01", "DIVISION_CD");
            var valid = "N";
            
            if(MMA204_01.init.flag == "insert") {
            	valid = "Y";
            }
            
            var obj = combo.getObject("MMA204_01_form_01", "SIGNATURE_SEQ");
            
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
            ["CHECK",         			"CHECK"                                 	, 100, "center", "center", true, false, null, true, null, 0, 0, true],
            ["PRODUCT_ITEM_CD"	,       resource.getMessage("LTIT, CODE")			, 100, "center", "center", true, false,  null, null, null, 0, 0],
            ["CUSTOMER_ITEM_NAME",     	resource.getMessage("LTIT,NAME")    		, 180, "left"  , "center", true, false, null, null, null, 0, 0],
            ["CUSTOMER_ITEM_CD",       	resource.getMessage("CSTMR,LTIT, CODE") 			, 130, "center", "center", true, false, null, null, null, 0, 0],
            ["HS_CODE",       			resource.getMessage("HS,CODE")          	, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["INVOICE_NO"        ,resource.getMessage("INVIC, MSSNM")                ,120    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
            ["NALADISA_CODE",			resource.getMessage("NALADISA, CODE")		, 100, "center", "center", true, true, null, null, null, 0, 0],
            ["FTA_CD",        			resource.getMessage("AGRET,CODE")       	, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["FTA_NAME",      			resource.getMessage("AGRET,NAME")       	, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["RULE_CONTENTS", 			resource.getMessage("TXT_RULE_CODE_NAME")   , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["RVC_RATE",      			resource.getMessage("RVC,RATE,(%)") 	    , 100, "right" , "center", true, false, null, null, null, 0, 0],
            ["ORIGIN_YN_NAME",           resource.getMessage("TXT_MEET_YN")      	, 100, "center", "center", true, false, null, null, null, 0, 0],
            ["ORIGIN_NATION_CD"     ,resource.getMessage("ORG_COTRY")         ,120    ,"center"    ,"center"    ,true       ,false    ,{type:"text"}         ,null     ,null    ,0        ,0],
            ["TRACE_VALUE"		 	   ,"Trace Value" 		                    	, 100 ,"right",  "center", true, false, null, null, {format:"amount"}, 0, 0],
            ["CURRENCY"		     	   ,resource.getMessage("CURCY")		        , 100 ,"center", "center", true, true, null, null, null, 0, 0],
            ["STATUS_NAME",              resource.getMessage("DTSTA, STAUS")        , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["ORIGIN_NATION_SRC_NAME", resource.getMessage("TXT_ORIGIN_RULE_DESC"),        250, "left", "center", false, false, null, null, null, 0, 0]
        ],
        header2 : [
            ["PRODUCER_CD",         	resource.getMessage("PRDER,CODE")       , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["PRODUCER_NAME",       	resource.getMessage("PRDER,NAME")       , 180, "left", "center", true, false, null, null, null, 0, 0],
            ["CHARGE_NAME",       	resource.getMessage("TXT_PRESIDENT_NAME"),150, "left", "center", true, false, null, null, null, 0, 0],
            ["ADDRESS",       	    resource.getMessage("ADDRS")            , 200, "left", "center", true, false, null, null, null, 0, 0],
            ["PHONE_NO",       	    resource.getMessage("TELNM")            , 120, "center", "center", true, false, null, null, null, 0, 0],
            ["FAX_NO",       	    resource.getMessage("FAX")              , 120, "center", "center", true, false, null, null, null, 0, 0],
            ["CHARGE_EMAIL",       	resource.getMessage("EMAIL")            , 150, "center", "center", true, false, null, null, null, 0, 0],
            ["BUSINESS_NO",       	resource.getMessage("PRDER, IDEN,ID")   , 130, "center", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA204_01_form_01");
        	var dg_2 = grid.getObject(params.TARGET_PID + "_grid_03");
        	var dg_1 = grid.getObject("MMA204_01_grid_01");
        	
        	if(form.handle.isChecked("MMA204_01_form_02", "cmCheckbox")) {
				params.cmCheckbox = "Y";
      		} else {
      			params.cmCheckbox = "N";
      		}
        	
            if(MMA204_01.init.flag == "insert") {
        		if(params.SEARCH_TYPE == "ITEM") { // 고객사별 발급(부모창에서 구해진 값을 바로 입력한다.)
        			if(params.TARGET_PID == "OR5011_01") params.gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2)); // OR5011_01:해외법인 증명서 전송
        			else params.gridData = grid.util.convertJson2Rows(grid.handle.getAllRows(dg_2));
        		} else { // 인보이스별 발급
        			params.gridData = null;
        		}
            	
                form.handle.setValue("MMA204_01_form_01", "gridRows", "");
                form.handle.setValue("MMA204_01_form_01", "gridPrds", "");
        		form.handle.setValue("MMA204_01_form_01", "gridData", params.gridData);
        		
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
            grid.init.setMergeData(["CUSTOMER_ITEM_CD" ,"CUSTOMER_ITEM_NAME", "PRODUCT_ITEM_CD", "HS_CODE"],["CUSTOMER_ITEM_CD", "PRODUCT_ITEM_CD", "HS_CODE"]);
            grid.init.setCallBackFunction("loadAtferGrid");
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            grid.event.setOnCheckBox(dg_1); // 체크박스 이벤트 추가(2021.10.06)
            
        	Theme.defaultGrid(dg_1, this.header1);
        },
        initGrid_2 : function() {
        	var dg_1 = grid.getObject("MMA204_01_grid_02");
        	
        	grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
        	Theme.defaultGrid(dg_1, this.header2);
        }
    },
    // 이벤트 처리
    event : {
    	onCheckBox_MMA204_01_grid_01 : function(rowData, act) {
    		if(act == "uncheckall") {
    			var dg_2 = grid.getObject("MMA204_01_grid_02");
    			grid.handle.clearAll(dg_2);
    		} else {
    			MMA204_01.control.selectProceduerList();
    		}
    	},
    	exchageToDate : function(date) {
    		var gp = form.handle.getValue("MMA204_01_form_01", "FTA_GROUP_CD");
    		
    		var ldate = calendar.util.addDate2String("d", 364, calendar.util.getString2Date(date));
    		
    		calendar.handle.setDate("MMA204_01_form_01", "END_DATE", ldate);
    	},
    	// 포괄시작일과 종료일이 1년을 넘을 경우 경고를 띄운다.
    	compareToDate : function(date) {
    		var coFrom = calendar.handle.getDate("MMA204_01_form_01", "APPLY_DATE");
    		var coTo = calendar.handle.getDate("MMA204_01_form_01", "END_DATE");
    		
    		var betNum = calendar.util.between2Month(coFrom, coTo);
    		
    		var max_period = 12;
    		var max_msg = "MSG_COVER_CHECK_DATE";
    		var gp = form.handle.getValue("MMA204_01_form_01", "FTA_GROUP_CD");
    		
    		if(betNum > max_period) {
    			alert(resource.getMessage(max_msg));
    			
    			var ldate = calendar.util.addDate2String("d", 364, coFrom, "", NATION_DATE_DB);
    			
        		calendar.handle.setDate("MMA204_01_form_01", "END_DATE", ldate);
        		
        		return false;
    		}
    		
    		return true;
    	},
    	loadAtferGrid : function() {
    		var dg_1 = grid.getObject("MMA204_01_grid_01");
    		var datas = grid.handle.getAllRows(dg_1);
    		
    		if(datas.length > 0 && MMA204_01.init.flag != "insert") {
    			form.handle.setValue("MMA204_01_form_01", "FTA_GROUP_CD", datas[0].FTA_GROUP_CD);
    			form.handle.setValue("MMA204_01_form_01", "FTA_GROUP_CD_NAME", datas[0].FTA_GROUP_CD_NAME);
    			
    			form.handle.setValue("MMA204_01_form_01", "FTA_CD", datas[0].FTA_CD);
    			form.handle.setValue("MMA204_01_form_01", "FTA_NAME", datas[0].FTA_NAME);
    		}
    		
    		grid.handle.checkAll(dg_1, true);
    		
    		MMA204_01.ui.changeMexicoFtaUi();
    	},
    	setCoIssueInfo : function(data) {
    		if(oUtil.isNull(data.SHIPPING_DATE)) delete data.SHIPPING_DATE;
    		if(oUtil.isNull(data.VEHICLE_NAME))delete data.VEHICLE_NAME;
    		
    		form.handle.loadData("MMA204_01_form_01", data);
    		
    		if(MMA204_01.init.flag != "insert") {
	    		MMA204_01.control.selectSignatureFile(data.SIGNATURE_SEQ);
    		}
    		
    		if(SESSION.FTA_NATION == "KR" && form.handle.getValue("MMA204_01_form_01", "CO_DOC_TYPE") == "N") {
	    		calendar.handle.readonly("MMA204_01_form_01", "APPLY_DATE", true);
				calendar.handle.readonly("MMA204_01_form_01", "END_DATE", true);
				
				form.util.setVisible("MMA204_01_div_05", true);
    		}
    	},
    	// 서명파일 조회
    	onChangeSignature : function(data) {
    		if(MMA204_01.init.flag == "insert") {
	    		if(oUtil.isNull(data)) {
	    			form.handle.setValue("MMA204_01_form_01", "SIGN_PHONE_NO", "");
	    			form.handle.setValue("MMA204_01_form_01", "SIGN_EMAIL", "");
	    			form.handle.setValue("MMA204_01_form_01", "SIGN_POSITION", "");
	    			form.handle.setValue("MMA204_01_form_01", "SIGN_END_DATE", "");
	    			
	    			var obj = form.getObject("MMA204_01_form_01", "MMA204_01_form_01_SIGN_CARD");
	    			obj.removeAttr("src");
	    			
	    			return;
	    		} else {
	    			MMA204_01.control.selectSignatureFile(data);
		    		MMA204_01.control.selectSignatureInfo();
	    		}
    		} else {
    			return;
    		}
    	},
    	setSignatureValue : function(data) {
    		if(MMA204_01.init.flag != "insert") {
    			MMA204_01.control.selectSignatureFile(data.SIGNATURE_SEQ);
    		}
    		
    		form.handle.loadData("MMA204_01_form_01", data);
        },
        onCheckedUpdateUI : function(data) {
            form.handle.setValue("MMA204_01_form_01", "gridRows", "");
            form.handle.setValue("MMA204_01_form_01", "gridPrds", "");
            
            var params = form.handle.getElementsParam("MMA204_01_form_01");
            var dg_2 = grid.getObject(params.TARGET_PID + "_grid_03");
            var gridData;
            
            if(params.SEARCH_TYPE == "ITEM") { // 고객사별 발급(부모창에서 구해진 값을 바로 입력한다.)
                if(params.TARGET_PID == "OR5011_01") gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2));
                else gridData = grid.util.convertJson2Rows(grid.handle.getAllRows(dg_2));
            }
            
            form.handle.setValue("MMA204_01_form_01", "gridData", gridData);
            
            MMA204_01.control.selectMainList();
            //MMA204_01.control.selectProceduerList();
        },
        changeUpdateUI : function(data) {
            form.handle.setValue("MMA204_01_form_01", "gridRows", "");
            form.handle.setValue("MMA204_01_form_01", "gridPrds", "");
            
            var params = form.handle.getElementsParam("MMA204_01_form_01");
            var dg_2 = grid.getObject(params.TARGET_PID + "_grid_03");
            var gridData;
            
            if(params.SEARCH_TYPE == "ITEM") { // 고객사별 발급(부모창에서 구해진 값을 바로 입력한다.)
                if(params.TARGET_PID == "OR5011_01") gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2));
                else gridData = grid.util.convertJson2Rows(grid.handle.getAllRows(dg_2));
            }
            
            form.handle.setValue("MMA204_01_form_01", "gridData", gridData);
            
        	if(!oUtil.isNull(data.CO_DOC_NO)) {
        		MMA204_01.init.flag = "update";
        		
        		form.handle.setValue("MMA204_01_form_01", "flag", "update");
        		form.handle.setValue("MMA204_01_form_01", "CO_DOC_NO", data.CO_DOC_NO);
        		
        		var dg_1 = grid.getObject("MMA204_01_grid_01");
        		
        		grid.handle.setHideColunm(dg_1, "CHECK");
       			grid.handle.setHideColunm(dg_1, "STATUS_NAME");
        		
        		MMA204_01.ui.changeUpdateUI();
        		MMA204_01.control.selectMainList();
        		
        		var pid = form.handle.getValue("MMA204_01_form_01", "TARGET_PID");
                
                if (!oUtil.isNull(pid)) {
                    var pro_id = eval("window." + pid + ".control");
                    var rstParam = new Object();
                    
                    if (typeof(pro_id["selectMainList"]) == "function") {
                        pro_id["selectMainList"](data.CO_DOC_NO);
                    }
                }
        	}
        }
    },
    // 업무구현
    control : {
    	selectMainList : function() {
     		var params = form.handle.getElementsParam("MMA204_01_form_01");
     		var dg_2 = grid.getObject(params.TARGET_PID + "_grid_03");
     		var dg_1 = grid.getObject("MMA204_01_grid_01");
     		
   			if(MMA204_01.init.flag == "insert") {
   				if(form.handle.isChecked("MMA204_01_form_02", "cmCheckbox")) {
   					params.cmCheckbox = "Y";
   	      		} else {
   	      			params.cmCheckbox = "N";
   	      		}
   				
   				grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectDeterminateList", params);
   			} else {
   				grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectCOOriginList", params);
   			}
     	},
     	selectProceduerList : function() {
     		var params = clone(form.handle.getElementsParam("MMA204_01_form_01"));
     		var dg_2 = grid.getObject(params.TARGET_PID + "_grid_03");
     		var dg_1 = grid.getObject("MMA204_01_grid_02");
     		
     		params.PRODUCER_NAME = "";
     		
   			if(MMA204_01.init.flag == "insert") {
   				if(form.handle.isChecked("MMA204_01_form_02", "cmCheckbox")) {
   					params.cmCheckbox = "Y";
   	      		} else {
   	      			params.cmCheckbox = "N";
   	      		}
   				
   				var dg_0 = grid.getObject("MMA204_01_grid_01");
   				var checkRow = grid.handle.getCheckedRowsData(dg_0);
   				
   				// if(checkRow.length == 0) checkRow = [{CUSTOMER_CD:"",CUSTOMER_ITEM_CD:"", PRODUCT_ITEM_CD:"", DIVISION_CD:"", INVOICE_NO:""}];
   				
   				params.SEARCH_TYPE = "ITEM";
   				params.gridData = grid.util.convertJson2Rows(checkRow);
   				
   				grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectProducerList", params);
   			} else {
   				grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectCOProducerList", params);
   			}
     	},
    	selectCoMasterInfo : function() {
    		var obj = form.getObject("MMA204_01_form_01");
    		
    		if(MMA204_01.init.flag == "insert") {
    			form.init.setURL("/mm/pop/mmA204_01/selectExportNProducerInfo");
    		} else {
    			form.init.setURL("/mm/pop/mmA204_01/selectCOIssueInfo");
    		}
            form.init.setCallBackFunction("setCoIssueInfo");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
    	// 서명파일 조회
    	selectSignatureInfo : function() {
            form.handle.setValue("MMA204_01_form_01", "gridData", "");
            form.handle.setValue("MMA204_01_form_01", "gridRows", "");
            form.handle.setValue("MMA204_01_form_01", "gridPrds", "");
            
    		var obj = form.getObject("MMA204_01_form_01");
    		
    		form.init.setURL("/mm/pop/mmA204_01/selectSignatureInfo");
            form.init.setCallBackFunction("setSignatureValue");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectSignatureFile : function(data) {
        	var obj = form.getObject("MMA204_01_form_01", "MMA204_01_form_01_SIGN_CARD");
        	var src = "/mm/pop/mmA204_01/selectSignatureFile/"+data;
        	
        	if(!oUtil.isNull(obj)) obj.removeAttr("src");
        	
        	obj.removeAttr("src").attr("src", src); 
        },
        issueReport : function() {
            var ftaCd = form.handle.getValue("MMA204_01_form_01", "FTA_CD");
            var certNo = form.handle.getValue("MMA204_01_form_01", "CERTIFICATION_NO");
            
        	if(form.handle.getValue("MMA204_01_form_01", "chkAuto") == "N"
    			&& oUtil.isNull(form.handle.getValue("MMA204_01_form_01", "CO_DOC_NO"))) {
        		// 증명서 발급번호 필수 체크
    			alert(resource.getMessage("TXT_DO_NO_IS_REQUIRED_FILED"));
    			return;
    		} else if(!MMA204_01.event.compareToDate()) {
    			return;
    		} else if(oUtil.isNull(combo.handle.getValue("MMA204_01_form_01", "SIGNATURE_SEQ"))) {
    			// 서명권자 지정 필수 체크
        		alert(resource.getMessage("MSG_NOT_SELECT_SIGN_USER"));
        		return;
        	} else if(ftaCd == "P"+SESSION.FTA_NATION+"RC" && oUtil.isNull(certNo)) {
                // RCEP원산지 자율발급 시 인증수출자번호 필수
                alert(resource.getMessage("MSG_RCEP_NEED_CERTNO"));
                return;
            } else {
        		// 원산지 정보 수집
        		var dg_1 = grid.getObject("MMA204_01_grid_01");
        		var dg_2 = grid.getObject("MMA204_01_grid_02");
        		
        		if(!grid.handle.isChecked(dg_1)) {
        			alert(resource.getMessage("MSG_NO_CHECK") + " (PPL:Parts price list)");
        			return;
        		}
        		
        		var rows = grid.handle.getCheckedRowsData(dg_1);
        		
        		// 원산지 판정내역에 오류가 있거나, 한국 FTA인 경우 불충족인 경우에는 발급할 수 없도록 한다.
        		for(var i=0; i < rows.length; i++) {
        			if(rows[i].STATUS == "9") {
        				alert(resource.getMessage("MSG_CO_ISUUE_ERROR"));
        				return;
        			}
                    // 기관발급시에는 충족인 건만 발급할 수 있도록 함
        			if(form.handle.getValue("MMA204_01_form_01", "CO_ISSUE_TYPE") == "A" && rows[i].ORIGIN_YN == "N") {
        				alert(resource.getMessage("MSG_CO_ISSUE_NOORGIN"));
        				return;
        			}
        		}
        		
        		// 필드별 유효성 체크
        		if(!form.handle.isValidate("MMA204_01_form_01")) return;
        		else MMA204_01.dialog.openDialog_3("insert");
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
      		dialog.init.setQueryParams({TARGET_PID:"MMA204_01", 
      			COMPANY_CD:form.handle.getValue("MMA204_01_form_01", "COMPANY_CD"), 
      			INVOICE_NO:form.handle.getValue("MMA204_01_form_01", "INVOICE_NO"),
      			INVOICE_DATE:form.handle.getValue("MMA204_01_form_01", "INVOICE_DATE"),
      			DIVISION_CD:form.handle.getValue("MMA204_01_form_01", "DIVISION_CD"),
      			CURRENCY:form.handle.getValue("MMA204_01_form_01", "CURRENCY"),
      			FTA_CD:form.handle.getValue("MMA204_01_form_01", "FTA_CD")
      			});
      		dialog.init.setTitle(resource.getMessage("MMA004_01"));
      		
      		dialog.open(dg_1);
      	},
      	openDialog_2 : function() {
            form.handle.setValue("MMA204_01_form_01", "gridData", "");
      		form.handle.setValue("MMA204_01_form_01", "gridRows", "");
            form.handle.setValue("MMA204_01_form_01", "gridPrds", "");
            
            var dg_2 = dialog.getObject("MMA004_02_dailog_01");
      		var params = form.handle.getElementsParam("MMA204_01_form_01");
      		
      		dialog.init.setWidth(960);
      		dialog.init.setHeight(700);
      		dialog.init.setURL("/mm/pop/mmA004_02");
      		dialog.init.setQueryParams(params);
      		dialog.init.setTitle(resource.getMessage("MMA004_02"));
      		
      		dialog.open(dg_2);
      	},
      	openDialog_3 : function(flag) {
            form.handle.setValue("MMA204_01_form_01", "gridData", "");
            form.handle.setValue("MMA204_01_form_01", "gridRows", "");
            form.handle.setValue("MMA204_01_form_01", "gridPrds", "");
            
      		var dg_3 = dialog.getObject("MMA004_03_dailog_01");
      		var params = form.handle.getElementsParam("MMA204_01_form_01");
      		
      		params.TARGET_PID = "MMA204_01";
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
            		var dg_1 = grid.getObject("MMA204_01_grid_01");
            		var dg_2 = grid.getObject("MMA204_01_grid_02");
            		var rows = grid.handle.getCheckedRowsData(dg_1);
            		
            		dialog.handle.close(dl_3);
            		
            		form.handle.setValue("MMA204_01_form_01", "gridRows", grid.util.convertJson2Rows(rows));
            		form.handle.setValue("MMA204_01_form_01", "gridPrds", grid.util.convertJson2Rows(grid.handle.getAllRows(dg_2))); 
    	        	
            		if(!oUtil.isNull(data)) {
    	        		form.handle.setValue("MMA204_01_form_01", "REASON_TITLE", data.REASON_TITLE);
    	        		form.handle.setValue("MMA204_01_form_01", "REQUESTER", data.REQUESTER);
    	        		form.handle.setValue("MMA204_01_form_01", "REQUEST_DATE", data.REQUEST_DATE);
    	        		form.handle.setValue("MMA204_01_form_01", "REASON_CONTENTS", data.REASON_CONTENTS);
    	        	}
            		
            		var obj = form.getObject("MMA204_01_form_01");
            		
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
    		var dg_1 = grid.getObject("MMA204_01_grid_01");
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
    		var params = new Object();
    		
    		params.COMPANY_CD = form.handle.getValue("MMA204_01_form_01", "COMPANY_CD");
    		params.DIVISION_CD = form.handle.getValue("MMA204_01_form_01", "DIVISION_CD");
    		params.CO_DOC_NO = form.handle.getValue("MMA204_01_form_01", "CO_DOC_NO");
    		params.CO_DOC_SEQ = form.handle.getValue("MMA204_01_form_01", "CO_DOC_SEQ"); // 기관발급시 최조신고의 번호를 가져온다.
    		
            var dg_1 = dialog.getObject("OR5008_02_dailog_01");
    		
 	   		dialog.init.setWidth(1200);
 	   		dialog.init.setHeight(850);
 	   		dialog.init.setURL("/fm/or/or5008_02");
 	   		dialog.init.setQueryParams(params);
 	   		
 	   		dialog.open(dg_1);
    	},
    	openDialog_6 : function() {
            form.handle.setValue("MMA204_01_form_01", "gridData", "");
            form.handle.setValue("MMA204_01_form_01", "gridRows", "");
            
      		var dg_3 = dialog.getObject("OR5201_04_dialog_01");
      		var params = form.handle.getElementsParam("MMA204_01_form_01");
      		
      		params.TARGET_PID = "OR5201_04";
      		params.flag = MMA204_01.init.flag;
      		
      		dialog.init.setWidth(1100);
      		dialog.init.setHeight(600);
      		dialog.init.setURL("/fm/or/or5201_04");
      		dialog.init.setQueryParams(params);
      		
      		dialog.open(dg_3);
      	}
    },
    // 화면 UI 변경
    ui : {
    	changeBtnCheck : function() {
    		if(form.handle.getValue("MMA204_01_form_01", "CO_DOC_TYPE") == "N") { // 개별발급
    			form.util.setVisible("MMA204_01_form_01_period_date", false);
    			form.util.setVisible("MMA204_01_form_01_period_text", false);
    		}
    	},
    	changeAutoCheck : function() {
    		if(form.handle.isChecked("MMA204_01_form_01", "chkAuto")) {
    			form.handle.setValue("MMA204_01_form_01", "chkAuto", "Y");
    			form.handle.setValue("MMA204_01_form_01", "CO_DOC_NO", "");
    			form.handle.disabled("MMA204_01_form_01", "CO_DOC_NO", true);
    		} else {
    			form.handle.setValue("MMA204_01_form_01", "chkAuto", "N");
    			form.handle.disabled("MMA204_01_form_01", "CO_DOC_NO", false);
    		}
    	},
    	changeUpdateUI : function() {
    		if(form.handle.getValue("MMA204_01_form_01", "CO_ISSUE_TYPE") == "M") { // 자율발급
    			form.util.setVisible("MMA204_01_btn_13", true);
    		} else { // 기관발급
    			var orgYn = form.handle.getValue("MMA204_01_form_01", "ORGAN_ISSUE_YN");
    			
    			if(orgYn == "Y") {
    				form.util.setVisible("MMA204_01_btn_13", false);
    			} else {
    				form.util.setVisible("MMA204_01_btn_13", true);
    			}
    		}
    		
    		form.util.setVisible("MMA204_01_btn_15", true);
    		form.util.setVisible("MMA204_01_span_01", true);
    		form.util.setVisible("MMA204_01_span_02", true);
    		form.util.setVisible("MMA204_01_div_01", false);
    		form.util.setVisible("MMA204_01_btn_12", false);
    		form.util.setVisible("MMA204_01_form_01_chkAuto", false);
    		form.handle.disabled("MMA204_01_form_01", "CO_DOC_NO", false);
    		
    		form.handle.closeAll("MMA204_01_form_01");
    	},
    	changeMexicoFtaUi : function() {
    		var gp = form.handle.getValue("MMA204_01_form_01", "FTA_GROUP_CD");
    		var dg_1 = grid.getObject("MMA204_01_grid_01");
     		
    		if(gp == "MN") {
    			grid.handle.setHideColunm(dg_1, "NALADISA_CODE");
    		}
    		if(gp == "ML") {
    			grid.handle.setHideColunm(dg_1, "TRACE_VALUE");
    			grid.handle.setHideColunm(dg_1, "CURRENCY");
    		}
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var params = form.handle.getElementsParam("MMA204_01_form_01");
            var dg_2 = grid.getObject(params.TARGET_PID + "_grid_03");
            var gridData;
            
            if(params.SEARCH_TYPE == "ITEM") { // 고객사별 발급(부모창에서 구해진 값을 바로 입력한다.)
                if(params.TARGET_PID == "OR5011_01") gridData = grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_2));
                else gridData = grid.util.convertJson2Rows(grid.handle.getAllRows(dg_2));
            }
            
            form.handle.setValue("MMA204_01_form_01", "gridData", gridData);
            form.handle.setValue("MMA204_01_form_01", "gridRows", "");
            form.handle.setValue("MMA204_01_form_01", "gridPrds", ""); 
            
            var dg_1 = grid.getObject("MMA204_01_grid_01");
            var fobj = form.getObject("MMA204_01_form_01");
            
            if(MMA204_01.init.flag == "insert") {
            	form.init.setURL("/mm/pop/mmA204_01/selectDeterminateList");
            } else {
            	form.init.setURL("/mm/pop/mmA204_01/selectCOOriginList");
            }
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, resource.getMessage("TXT_ITEMBY_ORG_INFO"), resource.getMessage("LIST"));
        }
    },
    config : {
    	applyFtaNation : function() {
    		var columns1 = MMA204_01.datagrid.header1;
    		
			// 아래 내용은 항상 실행을 최우선으로 하기 위해 최상위 둘 것
			if(SESSION.FTA_NATION == "KR") {
				grid.util.changeHeaderHidden(columns1 , "NALADISA_CODE", true);
				grid.util.changeHeaderHidden(columns1 , "TRACE_VALUE", true);
				grid.util.changeHeaderHidden(columns1 , "CURRENCY", true);
				
				if(form.handle.getValue("MMA204_01_form_01", "CO_DOC_TYPE") == "N") {
					$("#MMA204_01_div_03").html(resource.getMessage("TXT_DEAL_DATE"));
					form.util.setVisible("MMA204_01_div_04", false);
					form.util.setVisible("MMA204_01_div_05", false);
				}
			}
			
			if(MMA204_01.init.flag != "insert") {
				grid.util.changeHeaderHidden(columns1 , "CHECK", true);
				grid.util.changeHeaderHidden(columns1 , "STATUS_NAME", true);
			}
		}
    }
    
};
