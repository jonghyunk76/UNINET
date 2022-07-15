/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A017_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA017_01.config.applyFtaNation(); // FTA 협정별 UI변경
    MMA017_01.init.initComponent();
}

var MMA017_01 = {
    // 초기값 설정
    init : {
    	coModifyChecker : false,
        initComponent : function() {
        	var flag = form.handle.getValue("MMA017_01_form_01", "flag");
        	var createBy = form.handle.getValue("MMA017_01_form_01", "REGISTED_BY");
        	var docType = form.handle.getValue("MMA017_01_form_01", "CO_DOC_TYPE");
        	var ftaFix = form.handle.getValue("MMA017_01_form_01", "VENODR_FTA_FIX_YN");
        	var docType = form.handle.getValue("MMA017_01_form_01", "CO_DOC_TYPE");
        	
        	if(docType != "N") {
            	form.util.setVisible("MMA017_01_div_01", true);
            	form.util.setVisible("MMA017_01_div_02", true);
    		}
            
        	if(flag == "view") {
    			form.util.setVisible("MMA017_01_btn_04", true);
    			
    			if(SESSION.CERTIFY_TYPE == "external" || (SESSION.CERTIFY_TYPE == "internal" && createBy != "1")) {
        			form.util.setVisible("MMA017_01_btn_02", true);
        			form.util.setVisible("MMA017_01_span_04", true);
        		}
    			
        		form.util.setVisible("MMA017_01_span_03", true);
                if(createBy == "2" || createBy == "7") form.util.setVisible("MMA017_01_span_06", true);
        	}
        	
        	if(docType == "E") {
        		form.handle.readonly("MMA017_01_form_02", "HS_CODE", true);
        		form.handle.addClass("MMA017_01_form_02", "HS_CODE", "input_readOnly");
        		form.util.setVisible("MMA017_01_btn_34", false);
        		form.util.setVisible("MMA017_01_btn_35", false);
        		
        		calendar.handle.readonly("MMA017_01_form_01", "APPLY_DATE", true);
        	}
        	
            MMA017_01.calendar.initCalendar_1();
            MMA017_01.calendar.initCalendar_2();
            MMA017_01.calendar.initCalendar_3();
            MMA017_01.calendar.initCalendar_4();
            MMA017_01.calendar.initCalendar_5();
            
            MMA017_01.combobox.initCombo_1();
            MMA017_01.combobox.initCombo_2();
            MMA017_01.combobox.initCombo_3();
            MMA017_01.combobox.initCombo_4();
            MMA017_01.combobox.initCombo_6();
            if(form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_YN") == "Y") {
            	MMA017_01.combobox.initCombo_5();
            }
            
            if(createBy != "4" && ftaFix == "N" && (flag == "insert" || flag == "update" || flag == "renew" || flag == "")) {
            	form.util.setVisible("MMA017_01_tr_03", true);
            }
            
            if(ftaFix != "N" && flag == "renew") {
            	MMA017_01.control.CUSTOMER_HS_APPLY_FLAG = "CH";
            }
            
            MMA017_01.datagrid.initGrid_1();
            MMA017_01.datagrid.initGrid_2();
            MMA017_01.datagrid.initGrid_3();
            MMA017_01.datagrid.initGrid_4();
            
            MMA017_01.control.selectBasicInfo(); // 초기 데이터 조회
            
            if(flag == "insert" || flag == "renew" || flag == "pending") {
        		MMA017_01.ui.onChangeChkAuto();
        	}
        	
            if(flag == "update") {
        		MMA017_01.ui.onChangeUpdateUI();
        	}
            
            if(flag == "view" || flag == "modify") {
        		MMA017_01.ui.onChangeIssueUI();
        	}
        }
    }, 
    // 달력 그리드 생성
    calendar : {
    	//증명서 발행일
        initCalendar_1 : function() {
        	var flag = form.handle.getValue("MMA017_01_form_01", "flag");
            var objDate = calendar.getObject("MMA017_01_form_01", "ISSUE_DATE");
            
            if(flag == "view" || flag == "update" || flag == "modify") {
            	var date = calendar.util.getDate2String(form.handle.getValue("MMA017_01_form_01", "ISSUE_DATE"));
            	
            	calendar.init.setInitDate(date);
            } else {
            	calendar.init.setInitDate(calendar.util.toDate2String());
            }
            calendar.init.setRequired(true);
            
            calendar.create(objDate);
        },
        //포괄시작일자
        initCalendar_2 : function() {
        	var flag = form.handle.getValue("MMA017_01_form_01", "flag");
        	var objDate = calendar.getObject("MMA017_01_form_01", "APPLY_DATE");
        	var docType = form.handle.getValue("MMA017_01_form_01", "CO_DOC_TYPE");
        	
        	if(flag == "insert" || flag == "renew" || flag == "pending") {
        		var date = "";
        		
        		if(docType != "N") {
        			if(flag == "insert") {
            			// 거래년도의 1월1일이 시작일되도록 변경(2017.07.27 YNI-Maker)
            			var poDate = form.handle.getValue("MMA017_01_form_01", "LAST_PURCHASE_DATE");
            			var apDate = poDate.substring(0, 4)+"0101";
            			date = calendar.util.getFirstDay2String(apDate);
            			// 과거에는 구매마지막 일자를 적용했음
            			//date = calendar.util.getFirstDay2String(form.handle.getValue("MMA017_01_form_01", "LAST_PURCHASE_DATE"));
            		} else {
            			date = date = calendar.util.getDate2String(form.handle.getValue("MMA017_01_form_01", "LAST_PURCHASE_DATE"));
            		}
	        		
	        		date = (oUtil.isNull(date)) ? calendar.util.toDate2String() : date;
        		} else {
        			date = calendar.util.getDate2String(form.handle.getValue("MMA017_01_form_01", "LAST_PURCHASE_DATE"));
        		}
        		
        		calendar.init.setInitDate(date);
        		calendar.init.setCallFunction("exchageToDate");
        	} else {
        		var date = calendar.util.getDate2String(form.handle.getValue("MMA017_01_form_01", "APPLY_DATE"));
        		
        		calendar.init.setInitDate(date);
        		calendar.init.setCallFunction("");
        	}
            calendar.init.setEditable(false);
        	calendar.init.setRequired(true);
        	
        	calendar.create(objDate);
        },
        //포괄만료일자
        initCalendar_3 : function() {
        	var flag = form.handle.getValue("MMA017_01_form_01", "flag");
            var objDate = calendar.getObject("MMA017_01_form_01", "END_DATE");
            var docType = form.handle.getValue("MMA017_01_form_01", "CO_DOC_TYPE");
            
            if(flag == "insert" || flag == "renew" || flag == "pending") {
            	var fdate = "";
            	var ldate = "";
            	
            	if(docType != "N") {
            		if(flag == "pending") {
            			ldate = calendar.util.getDate2String(form.handle.getValue("MMA017_01_form_01", "END_FIX_DATE"));
            		} else {
		    			if(flag == "insert") {
		    				// 거래년도의 1월1일이 시작일되도록 변경(2017.07.27 YNI-Maker)
	            			var poDate = form.handle.getValue("MMA017_01_form_01", "LAST_PURCHASE_DATE");
	            			var apDate = poDate.substring(0, 4)+"0101";
	            			fdate = calendar.util.getFirstDay2String(apDate);
	            			// 과거에는 구매마지막 일자를 적용했음
		    				//fdate = calendar.util.getFirstDay2String(form.handle.getValue("MMA017_01_form_01", "LAST_PURCHASE_DATE"));
		    			} else {
		    				fdate = calendar.util.getDate2String(form.handle.getValue("MMA017_01_form_01", "LAST_PURCHASE_DATE"));
		    			}
		        		
		            	ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, fdate));
		            	
		            	// 고객사에 편한 관리를 위해 포괄시작년도와 만료년도가 다를 경우 시작년도의 말일을 포괄 만료일자로 지정할 것(2017.11.25, YNI-Maker)
		            	// 단, 나중에 멕시코버전에서 제대로 동작하는 확인할 것(2017.11.25)
		            	if(fdate.substring(0, 4) != ldate.substring(0, 4)) {
		            		fdate = ldate.substring(0, 4)+"0101";
		            		ldate = calendar.util.addDate2String("d", -1, fdate);
		            	}
            		}
            	} else {
            		ldate = calendar.util.getDate2String(form.handle.getValue("MMA017_01_form_01", "LAST_PURCHASE_DATE"));
            	}
            	
        		calendar.init.setInitDate(ldate);
        		calendar.init.setCallFunction("compareToDate");
        	} else {
        		var date = calendar.util.getDate2String(form.handle.getValue("MMA017_01_form_01", "END_DATE"));
        		
        		calendar.init.setInitDate(date);
        		
        		if(flag == "modify") {
        			calendar.init.setCallFunction("compareToDate");
        		} else {
        			calendar.init.setCallFunction("");
        		}
        	}
            calendar.init.setEditable(false);
            calendar.init.setRequired(true);
            
            calendar.create(objDate);
        },
        initCalendar_4 : function() {
        	var objDate = calendar.getObject("MMA017_01_form_01", "MOD_APPLY_DATE");
            
            calendar.init.setRequired(false);
            
            calendar.create(objDate);
        },
        initCalendar_5 : function() {
        	var objDate = calendar.getObject("MMA017_01_form_01", "MOD_END_DATE");
            
            calendar.init.setRequired(false);
            
            calendar.create(objDate);
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {
    	initView : false,
    	initCombo_1 : function() {
            var obj_1 = combo.getObject("MMA017_01_form_02", "FTA_CO_YN"); 

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN_TYPE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            combo.init.setCallFunction("onChangeOrigin");
            
            combo.create(obj_1);
        },
        initCombo_2 : function() {
        	var hscode = form.handle.getValue("MMA017_01_form_02", "HS_CODE");
        	var ftacode = form.handle.getValue("MMA017_01_form_02", "FTA_CD");
        	var obj_1 = combo.getObject("MMA017_01_form_02", "RULE_CONTENTS");
        	
        	combo.init.setQueryParams({HS_CODE:hscode, FTA_CODE:ftacode});
        	combo.init.setURL("/mm/pop/MMA018_01/selectRuleContents");
			combo.init.setValueField("RULE_CONTENTS");
			combo.init.setNameField("RULE_CONTENTS");
			combo.init.setRequired(true);
			combo.init.setCallFunction("onChangeRuleContents");
    		
        	combo.create(obj_1);
        },
        initCombo_3 : function() {
            var obj = combo.getObject("MMA017_01_form_03", "FTA_CD");
            
            combo.init.setURL("/mm/cbox/selectFta");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj);          
		},
		initCombo_4 : function() {
			var obj = combo.getObject("MMA017_01_form_03", "APPLY_FTA_CD");
			var params = new Object();
			
			params.COMPANY_CD = SESSION.COMPANY_CD;
			params.VENDOR_CD = form.handle.getValue("MMA017_01_form_01", "VENDOR_CD");
			params.CO_DOC_NO = form.handle.getValue("MMA017_01_form_01", "CO_DOC_NO");
			
			obj.combogrid({
                panelWidth:400,
                panelHeight:432,
                url: '/fm/fr/fr1201_01/selectMainList',
                idField:'FTA_CD',
                queryParams:params,
                textField:'FTA_NAME',
                mode:'remote',
                fitColumns:true,
                showHeader:true,
                singleSelect:false,
                checkOnSelect:true,
                selectOnCheck:true,
                multiple:true,
                editable:false,
                columns:[[
                	{field:'CHECK',title:'CHECK',width:60,halign:"center",align:"center",checkbox:true},
                    {field:'FTA_CD',title:resource.getMessage("AGRET,CODE"),width:60,halign:"center",align:"center"},
                    {field:'FTA_NAME',title:resource.getMessage("AGRET,NAME"),width:120,halign:"center",align:"left"}
                ]],
                onLoadSuccess:function(data) {
                	var ftaAry = new Array();
                	var sflag = form.handle.getValue("MMA017_01_form_01", "flag");
                    
                	for(var i = 0; i < data.rows.length; i++) {
                		if(data.rows[i].CO_DOC_CHK_YN == "Y") {
                			ftaAry.push(data.rows[i].FTA_CD);
                		}
                	}
                	
                	$(this).combogrid('setValues', ftaAry);
                	
                    if(data.rows.length > 0) {
                        if(sflag == "renew") {
                            /*
                            $.messager.confirm(resource.getMessage("INFMT"), resource.getMessage("CC_고객사의 최신 추천HS코드와 협정를 적용하시겠습니까?<br>(Cancel 선택 시 이전에 등록한 확인서 정보만 표시됩니다.)"), function(flag) {
                                if (flag) {
                                    form.handle.setValue("MMA017_01_form_01", "HEADER_RES", "");
                            
                                    MMA017_01.control.CUSTOMER_HS_APPLY_FLAG = "CH";
                                    MMA017_01.control.selectItemList();
                                } else {
                                    MMA017_01.control.CUSTOMER_HS_APPLY_FLAG = "";
                                    MMA017_01.control.selectItemList(null, ftaAry);
                                }
                            });
                            */
                            form.handle.setValue("MMA017_01_form_01", "HEADER_RES", "");
                            
                            MMA017_01.control.CUSTOMER_HS_APPLY_FLAG = "CH";
                            MMA017_01.control.selectItemList();
                        } else {
                            MMA017_01.control.selectItemList(null, ftaAry);
                        }
                        
                        MMA017_01.combobox.initView = true;
                	}
                },
                onChange:function(newValue, oldValue) {
                	var ftaFix = form.handle.getValue("MMA017_01_form_01", "VENODR_FTA_FIX_YN");
                	if(ftaFix == "N" && MMA017_01.combobox.initView == true) MMA017_01.control.selectItemList(null);
                }
            });
		},
		initCombo_5 : function() {
            var obj = combo.getObject("MMA017_01_form_02", "NONORIGIN_REASON_CD");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"NONORIGIN_REASON", MESSAGE_CODE:"NOSEL"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj);          
		},
        initCombo_6 : function() {
            var obj = combo.getObject("MMA017_01_form_03", "NATION_CD");
            var ftaCd = "P"+SESSION.FTA_NATION+"RC";
            
            combo.init.setURL("/fm/fr/fr1012_01/selectRcepNation");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, FTA_CD:ftaCd, MESSAGE_CODE:"UNDTM"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [
        	["ITEM_CD",          "* "+resource.getMessage("LTIT,CODE")/*품목코드*/,          130, "center", "center", false, false, null, null, null, 0, 0],
        	["TEMP_COLUMN3",     resource.getMessage("Part,MSSNM"),               	        120, "center", "center", false, true, null, null, null, 0, 0],
        	["TEMP_COLUMN4",     resource.getMessage("ECN,CODE"),               	        120, "center", "center", false, true, null, null, null, 0, 0],
        	["ITEM_NAME",        resource.getMessage("LTIT,NAME")/*품목명*/,               	180,   "left", "center", false, false, null, null, null, 0, 0],
        	["FTA_CD",           "* "+resource.getMessage("AGRET,CODE")/*FTA코드*/,           100, "center", "center", true, false, null, null, null, 0, 0],
        	["FTA_NAME",         resource.getMessage("AGRET,NAME")/*FTA명*/,                 	100,   "left", "center", true, false, null, null, null, 0, 0],
        	["HS_CODE",          "* "+resource.getMessage("HS,CODE")/*HS코드*/,              80,  "center", "center", false, false, null, null, null, 0, 0],
        	["RULE_CONTENTS",    "* "+resource.getMessage("TXT_RULE_CODE")/*결정기준*/,      100, "center", "center", false, false, null, null, null, 0, 0],
        	["RVC_RATE",         resource.getMessage("RVC, RATE")+"(%)"/*RVC 비율(%)*/,	    200,  "right", "center", false, false, null, null, null, 0, 0],
        	["FTA_CO_YN",        "* "+resource.getMessage("TXT_MEET_NOMEET_DESC_YN")/*충족여부*/, 180, "center", "center", false, false, null, null, null, 0, 0],
            ["ORIGIN_NATION_CD", resource.getMessage("TXT_NATION_INPUT_CD") /*원산지 국가*/,    160, "center", "center", false, false, null, null, null, 0, 0],
        	["NONORIGIN_REASON_CD", resource.getMessage("NOMEET, REASN,CODE"),               120, "center", "center", false, false, null, null, null, 0, 0],
        	["NONORIGIN_REASON_NAME", resource.getMessage("NOMEET, REASN,NAME"),               200, "left", "center", false, false, null, null, null, 0, 0],
        	["NONORIGIN_REASON_DESC", resource.getMessage("NOMEET, REASN,DSCPT"),        250, "left", "center", false, false, null, null, null, 0, 0]
       	],
       	header2 : [
       		["EV_DOC_TYPE_NAME",  resource.getMessage("TYPE"),          120, "left", "center", false, false, null, null, null, 0, 0],
            ["ORIGIN_FILE_NAME",  resource.getMessage("FILE,NAME"),          250, "left",   "center", false, false, null, null, null, 0, 0],
            ["ITEM_CD",           resource.getMessage("LTIT,CODE")/*품목코드*/,          130, "center", "center", false, false, null, null, null, 0, 0],
        	["ITEM_NAME",         resource.getMessage("LTIT,NAME")/*품목명*/,               	180,   "left", "center", false, false, null, null, null, 0, 0],
        	["FILE_DESC",         resource.getMessage("DSCPT")/*품목코드*/,            250, "left", "center", false, false, null, null, null, 0, 0]
        ],
        header3 : [
        	["ITEM_CD",          "* "+resource.getMessage("LTIT,CODE")/*품목코드*/,          130, "center", "center", false, false, null, null, null, 0, 0],
        	["ITEM_NAME",        resource.getMessage("LTIT,NAME")/*품목명*/,               	180,   "left", "center", false, false, null, null, null, 0, 0],
        	["FTA_CD",           "* "+resource.getMessage("FTA, CODE")/*FTA코드*/,           100, "center", "center", false, false, null, null, null, 0, 0],
        	["FTA_NAME",         resource.getMessage("FTA,NAME")/*FTA명*/,                 	100,   "left", "center", false, false, null, null, null, 0, 0],
        	["HS_CODE",          "* "+resource.getMessage("HS,CODE")/*HS코드*/,              80,  "center", "center", false, false, null, null, null, 0, 0],
        	["RULE_CONTENTS",    "* "+resource.getMessage("TXT_RULE_CODE")/*결정기준*/,      100, "center", "center", false, false, null, null, null, 0, 0],
        	["RVC_RATE",         resource.getMessage("RVC, RATE")+"(%)"/*RVC 비율(%)*/,	    200,  "right", "center", false, false, null, null, null, 0, 0],
        	["FTA_CO_YN",        "* "+resource.getMessage("TXT_MEET_NOMEET_DESC_YN"), /*충족여부*/,  180, "center", "center", false, false, null, null, null, 0, 0],
        	["ORIGIN_NATION_CD", resource.getMessage("TXT_NATION_INPUT_CD")/*원산지 국가*/,    160, "center", "center", true, false, null, null, null, 0, 0],
            ["NONORIGIN_REASON_CD", resource.getMessage("NOMEET, REASN,CODE"),               120, "center", "center", false, false, null, null, null, 0, 0],
        	["NONORIGIN_REASON_DESC", resource.getMessage("NOMEET, REASN,DSCPT"),        300, "left", "center", false, false, null, null, null, 0, 0],
        	["RULE_CODES",       resource.getMessage("REDER,:,TXT_RULE,CODE"),    150, "center", "center", false, false, null, null, null, 0, 0]
       	],
       	header4 : [
       		["CODE",           resource.getMessage("REASN,CODE"),             150, "center", "center", false, false, null, null, null, 0, 0],
        	["NAME",           resource.getMessage("REASN,NAME"),             250, "left",   "center", false, false, null, null, null, 0, 0],
        	["CODE_DESC",      resource.getMessage("NOMEET, REASN,DSCPT"),    400, "left",   "center", false, false, null, null, null, 0, 0]
       	],
        initGrid_1 : function() {
        	var flag = form.handle.getValue("MMA017_01_form_01", "flag");
            var dg_1 = grid.getObject("MMA017_01_grid_01");
            
    		grid.init.setPage(true);
            grid.init.setShowConfigPage(true);
            grid.init.setPageLayout(["resizeColumn"]);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(false);
            if(flag == "view") grid.init.setFilter(true); // 그리드  해더에 필터 적용
            else grid.init.setFilter(false); // 그리드  해더에 필터 적용
            grid.init.setCallBackFunction("oneSelectRow");
            if(PART_NUM_VIEW_YN == "Y") {
            	grid.init.setMergeData(["ITEM_CD", "TEMP_COLUMN3", "TEMP_COLUMN3", "ITEM_NAME"], "ITEM_CD");
            } else {
            	grid.init.setMergeData(["ITEM_CD", "ITEM_NAME"], "ITEM_CD");
            }
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header1);
        },
        initGrid_2 : function() {
        	var params = form.handle.getElementsParam("MMA017_01_form_01");
        	var dg_3 = grid.getObject("MMA017_01_grid_03");
        	
        	grid.init.setQueryParams(params);
        	grid.init.setURL("/mm/pop/mmA021_02/selectEvdcFileList");
        	grid.init.setPage(false);
        	grid.init.setEmptyMessage(false);
        	grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
        	Theme.defaultGrid(dg_3, this.header2);
        },
        initGrid_3 : function() {
        	var dg_4 = grid.getObject("MMA017_01_grid_04");
        	
        	grid.init.setPage(false);
        	grid.init.setEmptyMessage(false);
            
        	Theme.defaultGrid(dg_4, this.header3);
        },
        initGrid_4 : function() {
        	var dg_5 = grid.getObject("MMA017_01_grid_05");
        	
        	grid.init.setPage(false);
        	grid.init.setEmptyMessage(false);
            
        	Theme.defaultGrid(dg_5, this.header4);
        }
    },
    // 이벤트 처리
    event : {
    	oneSelectRow : function(data) {
    		var flag = form.handle.getValue("MMA017_01_form_01", "flag");
    		
    		if(flag == "view") {
    			var dg_1 = grid.getObject("MMA017_01_grid_01");
    			grid.handle.selectRowIndex(dg_1, 0);
    			
    			MMA017_01.event.onClick_MMA017_01_grid_01(data.rows[0]);
    		}
    	},
    	exchageToDate : function(date) {
    		var docType = form.handle.getValue("MMA017_01_form_01", "CO_DOC_TYPE");
    		
    		if(docType != "N") {
                var apydate = calendar.handle.getDate("MMA017_01_form_01", "APPLY_DATE");
                var enddate = calendar.handle.getDate("MMA017_01_form_01", "END_DATE");
    			var ldate;
                
    			//var ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, calendar.util.getString2Date(date)));
    			// 시작년도의 마지막 일자로 자동 셋팅(2019.09.07)
    		    if(SESSION.FTA_NATION == "MX") { // 멕시코는 포괄기간이 1달로 매달 제출
                    ldate = calendar.util.getLastDay2String(apydate, null, NATION_DATE_VIEW);
                    
                    if(apydate.substring(0, 6) != enddate.substring(0, 6)) {
                        MMA017_01.control.selectItemListQuest("1");
                    }
                } else { // 한국은 포괄기간이 최대 1년
                    ldate = calendar.util.getLastDay2String(apydate.substring(0, 4)+"1231", null, NATION_DATE_VIEW);
                    
                    if(apydate.substring(0, 4) != enddate.substring(0, 4)) {
                        MMA017_01.control.selectItemListQuest("1");
                    }                    
                }
                
                calendar.handle.setDate("MMA017_01_form_01", "END_DATE", ldate);
    		}
    	},
    	// 포괄시작일과 종료일이 1년을 넘을 경우 경고를 띄운다.
    	compareToDate : function(date) {
    		var docType = form.handle.getValue("MMA017_01_form_01", "CO_DOC_TYPE");
    		var coFrom = calendar.handle.getDate("MMA017_01_form_01", "APPLY_DATE");
    		var coTo = calendar.handle.getDate("MMA017_01_form_01", "END_DATE");
    		var f_flag = form.handle.getValue("MMA017_01_form_01", "flag");
    		var coverDataChk = form.handle.getValue("MMA017_01_form_01", "CO_COVER_YN");
    		var cmpEndDate = "";
        	var inDate = "";
        	
        	if(oUtil.isNull(coverDataChk)) coverDataChk = "Y";
        	
    		if(f_flag == "modify") {
            	var modApplyDate = calendar.handle.getDate("MMA017_01_form_01", "MOD_APPLY_DATE");
	        	var modEndDate = calendar.handle.getDate("MMA017_01_form_01", "MOD_END_DATE");
	        	
	        	// 지정한 만료일자가 수정기간을 벗어나는지 체크
	        	if(coTo < modApplyDate || coTo > modEndDate) {
	        		alert(resource.getMessage("MSG_MODIFY_OVER_PROPER"));
	        		
	        		calendar.handle.setDate("MMA017_01_form_01", "END_DATE", calendar.handle.getDate("MMA017_01_form_01", "MOD_END_DATE"), false);
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
    		var gp = form.handle.getValue("MMA017_01_form_01", "FTA_GROUP_CD");
    		
    		var diffYn = true;
    		
    		// 포괄기간 체크
    		if(betNum > max_period) {
    			alert(resource.getMessage(max_msg));
    			diffYn = false;
    		}
    		
    		// 시작일과 종료일 체크
    		if(diffYn && !calendar.util.compear2Date(coFrom, coTo)) {
    			alert(resource.getMessage("MSG_APPLY_SE_DATE"));
    			diffYn = false;
    		}
    		
    		// 종료일은 무조건 12월 말이이여 함(20171129, 김종현)
    		if(docType != "N" && f_flag != "modify" && f_flag != "update" && coverDataChk == "Y") { // 포괄기간을 체크할지 여부를 조건으로 추가(20210625)
	    		if(diffYn && coTo.substring(4, 8) != "1231") {
	    			alert(resource.getMessage("MSG_COVER_YEAR_END_DATE"));
	    			diffYn = false;
	    		}
    		}
    		
    		if(f_flag != "update") {
	    		if(!diffYn && docType != "N") {
	    			var ldate = "";
					ldate = calendar.util.addDate2String("d", -1, calendar.util.addDate2String("yyyy", 1, coFrom, "", NATION_DATE_DB));
					
		    		calendar.handle.setDate("MMA017_01_form_01", "END_DATE", ldate);
	    		}
	    		
	    		// 지정한 만료일자가 매월 말일인지 체크
	    		if(docType != "N" && diffYn && inDate != cmpEndDate) {
	        		alert(resource.getMessage("MSG_CHECK_COVER_END_DATE"));
	        		calendar.handle.setDate("MMA017_01_form_01", "END_DATE", cmpEndDate, true);
	        		diffYn = false;
	        	}
    		}
    		
    		return diffYn;
    	},
    	onClick_MMA017_01_grid_01 : function(rowData) {
    		var dg_1 = grid.getObject("MMA017_01_grid_01");
    		var rowGrid = grid.handle.getSelectedRowData(dg_1);
    		var row = $.extend({}, rowGrid);
    		
    		if(oUtil.isNull(row.FTA_CO_YN)) {
    			var org = combo.handle.getValue("MMA017_01_form_02", "FTA_CO_YN");
    			if(oUtil.isNull(org)) {
    				row.FTA_CO_YN = 'Y';
    			} else {
    				row.FTA_CO_YN = org;
    			}
    		}
    		
    		if(oUtil.isNull(row.HS_CODE)) {
    			delete row.HS_CODE;
    		}
    		
    		if(form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_YN") == "Y") {
    			if(oUtil.isNull(row.NONORIGIN_REASON_CD)) {
    				row.NONORIGIN_REASON_CD = " ";
    			}
    		}
    		
    		form.handle.loadData("MMA017_01_form_02", row);
    		
    		MMA017_01.control.selectRuleContents(rowData);
        },
        onChangeRuleContents : function(val) {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	var row = grid.handle.getSelectedRowData(dg_1);
    		
    		if(!grid.handle.isSelected(dg_1)) return;
    		
    		if(oUtil.isNull(row.RVC_RATE) && !oUtil.isNull(val)) {
	   			var org = combo.handle.getValue("MMA017_01_form_02", "FTA_CO_YN");
	    		
	   			MMA017_01.ui.setRvcRate(val, org);
    		}
        },
        onChangeOrigin : function(val) {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
    		var row = grid.handle.getSelectedRowData(dg_1);
    		
    		if(!grid.handle.isSelected(dg_1)) return;
    		
    		if(oUtil.isNull(row.RVC_RATE) && !oUtil.isNull(val)) {
    			var rule = combo.handle.getValue("MMA017_01_form_02", "RULE_CONTENTS");
    			
    			MMA017_01.ui.setRvcRate(rule, val);
    		}
        },
        callBack_selectBasicInfo : function(data) {
            form.handle.loadData("MMA017_01_form_01", data);
            
            var flag = form.handle.getValue("MMA017_01_form_01", "flag");
            var docType = form.handle.getValue("MMA017_01_form_01", "CO_DOC_TYPE");
            var createBy = form.handle.getValue("MMA017_01_form_01", "REGISTED_BY");
            
            if(docType == "N") {
            	calendar.handle.readonly("MMA017_01_form_01", "APPLY_DATE", true);
            	form.util.setVisible("MMA017_01_div_01", true);
    		}
            
            if(flag == "modify" && !oUtil.isNull(data.MOD_APPLY_DATE)) {
            	if(data.MOD_APPLY_DATE >= data.MOD_END_DATE) {
            		calendar.handle.readonly("MMA017_01_form_01", "END_DATE", true);
	    			form.util.setVisible("MMA017_01_span_02", false);
	    			form.util.setVisible("MMA017_01_btn_03", false);
	    			form.util.setVisible("MMA017_01_btn_04", false);
	    			form.util.setVisible("MMA017_01_p_01", false);
	    			
            		alert(resource.getMessage("MSG_REJECT_HANDLE"));
            	} else {
	            	calendar.handle.readonly("MMA017_01_form_01", "END_DATE", false);
	            	form.util.setVisible("MMA017_01_span_02", true);
	    			form.util.setVisible("MMA017_01_btn_03", true);
	    			form.util.setVisible("MMA017_01_btn_04", true);
	    			form.util.setVisible("MMA017_01_p_01", true);
            	}
            }
            
            if(!oUtil.isNull(data.PRF_FILE_NAME)) {
    			form.util.setVisible("MMA017_01_form_01_PRF_FILE_DOWN", true);
    		} else {
    			form.util.setVisible("MMA017_01_form_01_PRF_FILE_DOWN", false);
    		}
            
            $("#MMA017_01_form_01_PRF_FILE_NAME_SPAN").text(data.PRF_FILE_NAME);
        },
        //확인서기본정보 저장이 성공적으로 완료된 후
        callBack_saveBasicInfo : function(data) {
            if(data.RESULT_FLAG == "-1") {
                $.messager.confirm(CNFIR, resource.getMessage("MSG_CREATE_DOC_FAIL")+"<br>("+resource.getMessage("MSG_RETRY_CONTINUE")+")<br><br>"+
                                          resource.getMessage("TXT_CONTINUE_QUESTION"), function(flag) {
                    if(flag) {
                        var dg_1 = grid.getObject("MMA017_01_grid_01");
                        var rowData = grid.handle.getAllRows(dg_1, "1");
                        
                        MMA017_01.control.insertRcvCoInfo(rowData, "re");
                    }
                });
            } else {
                alert(resource.getMessage("MSG_SUCCESS_BODY")); /*요청하신 작업이 성공적으로 처리되었습니다.*/
                
                MMA017_01.event.insertRcvCoInfo(data);
            }
        },
        insertRcvCoInfo : function(data) {
        	MMA017_01.init.coModifyChecker = false;
        	
        	if(!oUtil.isNull(data.PRF_FILE_NAME)) {
    			form.util.setVisible("MMA017_01_form_01_PRF_FILE_DOWN", true);
    			form.util.setVisible("MMA017_01_btn_02", true);
    			form.util.setVisible("MMA017_01_btn_04", true);
    			form.util.setVisible("MMA017_01_span_03", true);
    			form.util.setVisible("MMA017_01_span_04", true);
    		} else {
    			form.util.setVisible("MMA017_01_form_01_PRF_FILE_DOWN", false);
    		}
        	
        	MMA017_01.ui.onChangeIssueUI(data);
        	
        	form.handle.setValue("MMA017_01_form_01", "PRF_FILE_SEQ", "1");
        	
        	var pid = form.handle.getValue("MMA017_01_form_01", "TARGET_PID");
        	if(!oUtil.isNull(pid)) {
        		var pro_id = eval("window." + pid + ".dialog");
        		
        		if(typeof(pro_id["callByMMA017_01"]) == "function") {
        			pro_id["callByMMA017_01"](data);
        		}
        	}
        },
        uploadProgressBar : function(idx, datas) {
     		if(typeof datas == "object") {
	     		var total = datas.length;
	     		var rate = (idx/total)*100;
	     		
	            $('#MMA017_01_de_progress').progressbar('setValue', Math.floor(rate));
	            
	            MMA017_01.control.appendCoData(idx+1, datas);
     		}
        },
        callBack_saveOriginModifyInfo : function(data) {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	
        	MMA017_01.init.coModifyChecker = true;
        	form.handle.setValue("MMA017_01_form_01", "flag", "renew");
			
			var endFormDate = calendar.handle.getDate("MMA017_01_form_01", "END_DATE");
			var inApplyDate = calendar.util.addDate2String("d", 1, endFormDate, "", NATION_DATE_DB);
			
			// 제출일(오늘날짜), 포괄기간 수정(시작일:(수정전 종료일자+1일), 종료일:수정전 종료일자)
			form.handle.setValue("MMA017_01_form_01", "BF_CO_DOC_NO", form.handle.getValue("MMA017_01_form_01", "CO_DOC_NO"));
			calendar.handle.setDate("MMA017_01_form_01", "ISSUE_DATE", calendar.util.toDate2String());
			calendar.handle.setDate("MMA017_01_form_01", "APPLY_DATE", inApplyDate);
			calendar.handle.setDate("MMA017_01_form_01", "END_DATE", calendar.handle.getDate("MMA017_01_form_01", "MOD_END_DATE"), false);
			form.handle.setValue("MMA017_01_form_01", "gridData", grid.util.convertJson2Rows(grid.handle.getAllRows(dg_1, "1")));
			
			MMA017_01.ui.onModifyIssueUI();
			
			var pid = form.handle.getValue("MMA017_01_form_01", "TARGET_PID");
        	if(!oUtil.isNull(pid)) {
        		var pro_id = eval("window." + pid + ".dialog");
        		
        		if(typeof(pro_id["callByMMA017_01"]) == "function") {
        			pro_id["callByMMA017_01"](data);
        		}
        	}
        },
        deleteEvcdFile : function() {
        	MMA017_01.control.selectEvdcFileList();
        },
        updateMgmtEvenFile : function(data) {
            form.handle.setValue("MMA017_01_form_01", "PRF_FILE_SEQ", "");
            $("#MMA017_01_form_01_PRF_FILE_NAME_SPAN").text(data.PRF_FILE_NAME);
            
            form.util.setVisible("MMA017_01_span_07", false);
            form.util.setVisible("MMA017_01_form_01_PRF_FILE", false);
            form.util.setVisible("MMA017_01_form_01_PRF_FILE_DW", true);
            form.util.setVisible("MMA017_01_form_01_PRF_FILE_DOWN", true);
            
            form.handle.setValue("MMA017_01_form_01", "PRF_FILE", "");
            form.handle.disabled("MMA017_01_form_01", "PRF_FILE", true);
            form.handle.setValidate("MMA017_01_form_01", "PRF_FILE", false);
        }
    },
    // 업무구현
    control : {
    	CUSTOMER_HS_APPLY_FLAG : "",
    	selectBasicInfo : function() {
    		var obj = form.getObject("MMA017_01_form_01");
    		
    		var vFlag = nullToString(form.handle.getValue("MMA017_01_form_01", "flag"));
    		
    		form.handle.setValue("MMA017_01_form_01", "HEADER_RES", "");
    		
    		if(vFlag == "insert" || vFlag == "renew" || vFlag == "pending") {
    			form.init.setURL("/mm/pop/mmA017_01/selectBasicInfoCaseInsert");
    		} else if(vFlag == "update" || vFlag == "view" || vFlag == "modify") {
    			form.init.setURL("/mm/pop/mmA017_01/selectBasicInfoCaseUpdate");
	    	}    		
    		
            form.init.setProgressFlag(false);
            form.init.setCallBackFunction("callBack_selectBasicInfo");
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectItemListQuest : function(eflg) {
        	var flag = form.handle.getValue("MMA017_01_form_01", "flag");
        	var ftaFix = form.handle.getValue("MMA017_01_form_01", "VENODR_FTA_FIX_YN");
        	var questMsg
            
            if(eflg == "1") {
                questMsg = resource.getMessage("MSG_CHG_COVER_DATE_HS"); // 포괄기간 변경으로 인해 등록할 협정 또는 HS코드가 변경될 수 있습니다.<br><br>품목별 원산지 정보가 갱신하겠습니까?;
            } else {
                questMsg = resource.getMessage("TXT_INIT_HSCODE_REFER");
            	
            	if(flag == "update" || (ftaFix == "N" && flag == "renew")) {
            		questMsg = questMsg + "<p>"+resource.getMessage("TXT_4_BY_ITEM_MARK")+"</p>";
            	}
            	
            	questMsg = questMsg + "<br><p>"+resource.getMessage("TXT_INIT_PROCESS")+"</p>";
        	}
            
        	$.messager.confirm(CNFIR, questMsg, function(flag) {
        		if(flag) {
        			form.handle.setValue("MMA017_01_form_01", "HEADER_RES", "");
        			
        			MMA017_01.control.CUSTOMER_HS_APPLY_FLAG = "CH";
        			MMA017_01.control.selectItemList();
        		}
        	});
        },
        //품목별원산지정보 조회
        selectItemList : function(state, ftaAry) {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	var createBy = form.handle.getValue("MMA017_01_form_01", "REGISTED_BY");
        	var params = form.handle.getElementsParam("MMA017_01_form_01");
        	var vFlag = form.handle.getValue("MMA017_01_form_01", "flag");
        	var ftaFix = form.handle.getValue("MMA017_01_form_01", "VENODR_FTA_FIX_YN");
        	var ftas = "";
        	
        	if(createBy != "4" && ftaFix == "N" && (vFlag == "insert" || vFlag == "update" || vFlag == "renew" || vFlag == "")) {
	        	if(oUtil.isNull(ftaAry)) {
	        		var comobj = combo.getObject("MMA017_01_form_03", "APPLY_FTA_CD");
	        		ftaAry = comobj.combogrid("getValues");
	        	}
	        	
	        	if(!oUtil.isNull(ftaAry)) {
	            	for(var i = 0; i < ftaAry.length; i++) {
	            		if(!oUtil.isNull(ftaAry[i])) ftas += "'"+ftaAry[i]+"',";
	            	}
	            	
	        	}
	        	
	        	if(oUtil.isNull(ftas)) {
	        		alert(resource.getMessage("FTA,CODE,MSG_CHOICE_BOMBO"));
	        		return;
	        	} else {
	        		ftas = ftas.substring(0, ftas.length-1);
	        		params.APPLY_FTA_CD = ftas;
	        	}
        	} else {
        		params.APPLY_FTA_CD = "";
        	}
        	
        	if(this.CUSTOMER_HS_APPLY_FLAG == "CH") {
        		params.CUSTOMER_HS_CODE_YN = "Y";
        	} else {
        		params.CUSTOMER_HS_CODE_YN = "N";
        	}
        	
        	form.handle.setValue("MMA017_01_form_01", "APPLY_FTA_CD", params.APPLY_FTA_CD);
        	form.handle.setValue("MMA017_01_form_01", "CUSTOMER_HS_CODE_YN", params.CUSTOMER_HS_CODE_YN);
        	
        	if(vFlag == "insert") { // 신규
        		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA017_01/selectItemListCaseInsert", params);
        	} else if(vFlag == "update" || vFlag == "view" || vFlag == "modify") { // 수정요청, 상세보기, 수정신고
        		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA017_01/selectItemListCaseUpdate", params);
	        } else if(vFlag == "renew") { // 갱신
	        	grid.handle.sendRedirect(dg_1, "/mm/pop/mmA017_01/selectItemListCaseRenew", params);
	        } else if(vFlag == "pending") { // 미결
	        	grid.handle.sendRedirect(dg_1, "/mm/pop/mmA017_01/selectItemListCasePeding", params);
	        }
        },
        //버튼액션(제출) : 확인서 정보 저장
        saveMasterDetailInfo : function() {
        	var vFlag = form.handle.getValue("MMA017_01_form_01", "flag");
        	var hubVendor = form.handle.getValue("MMA017_01_form_01", "HUB_VENDOR_YN");
        	var issueMsg = resource.getMessage("TXT_TOMS_HUB_KEEP");
        	
        	if(hubVendor == "Y" && !(vFlag == "update" || vFlag == "view" || vFlag == "pending")) { // 수정요청, 상세보기, 미결요청이 아닌 경우
	        	// HUB등록 고객사인 경우 포탈에서 등록할 것인지 확인 후 등록을 진행함.(나중에 다국어 처리가 필요함)
	        	$.messager.confirm(resource.getMessage("CNFIR"), issueMsg, function(flag) {
	        		if(flag) {
	        			MMA017_01.control.saveCoCertifyInfo();
	        		}
	        	});
        	} else {
        		MMA017_01.control.saveCoCertifyInfo();
        	}
        },
        saveCoCertifyInfo : function() {
        	var obj = form.getObject("MMA017_01_form_01");
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	
        	if(!form.handle.isValidate("MMA017_01_form_01")) return;
        	
        	var vFlag = nullToString(form.handle.getValue("MMA017_01_form_01", "flag"));
        	var signReg = form.handle.getValue("MMA017_01_form_01", "SING_REG_YN");
        	var fileReg = form.handle.getValue("MMA017_01_form_01", "PRF_FILE");
    		var norsYn = form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_YN");
    		var targetId = form.handle.getValue("MMA017_01_form_01", "TARGET_PID");
    		var rvcReqYn = form.handle.getValue("MMA017_01_form_01", "RVC_REQUIRED_YN");
    		
    		// 철판 미확인 품목 확인서 등록 시에는 역외사유와 비율을 체크하지 않도록 함(2019.10.18)
    		if(targetId == "DI3011_01") {
    			norsYn = "N";
    		}
    		
    		if(oUtil.isNull(fileReg) && signReg != "Y") {
        		alert(resource.getMessage("MSG_NO_REGISTE_VENDOR_SIGN"));
        		return;
        	}
        	
        	//날짜 검증하기
        	if(!MMA017_01.event.compareToDate()) {
        		return;
        	}
        	
        	//필수값 누락 여부 체크
        	var gridRowCnt = grid.handle.getRowCount(dg_1, "1");
        	var rowData = grid.handle.getAllRows(dg_1, "1");
        	var rcepYn = false;
            var rcepIdx;
            
        	if(gridRowCnt <= 0) {
        		alert(resource.getMessage("MSG_NO_INSERT")); /*저장할 데이터가 없습니다.*/
        		return;
        	}
            
        	for(var i = 0; i < gridRowCnt; i++) {
        		// 필수값 입력여부 체크
        		if(oUtil.isNull(rowData[i]["ITEM_CD"]) || oUtil.isNull(rowData[i]["FTA_CD"])||oUtil.isNull(rowData[i]["HS_CODE"]) || 
        				oUtil.isNull(rowData[i]["RULE_CONTENTS"])||oUtil.isNull(rowData[i]["FTA_CO_YN"])) {
        			alert(resource.getMessage("ERROR_REQUIRED_YN") + "<br/>" 
        					+ resource.getMessage("LTIT, NAME") + ":" + rowData[i]["ITEM_CD"] + ", "
        					+ resource.getMessage("AGRET,CODE") + ":" + rowData[i]["FTA_CD"]);
        		    
        			grid.handle.selectRowIndex(dg_1, i);
        			form.handle.clear("MMA017_01_form_02");
        			return;
        		}
        		
                // 역외산인 경우, KR원산지 입력불가 체크
                if(rowData[i]["FTA_CO_YN"] == 'N' && rowData[i]["ORIGIN_NATION_CD"] == 'KR' && rowData[i]["FTA_CD"] != 'PKRRC') {
                    alert(resource.getMessage("CC_충족여부가 N인 경우에는 원산지 국가를 KR로 입력할 수 없습니다."));
                    grid.handle.selectRowIndex(dg_1, i);
                    return;
                }
                
        		// RVC 비율 체크 여부 추가(2021-04-30)
        		if(rvcReqYn == "Y" && targetId != "DI3011_01") {
	        		if(!oUtil.isNull(rowData[i]["RULE_CONTENTS"])) {
	        			var stdRate = MMA017_01.util.getRuleRvcRate(rowData[i]["RULE_CONTENTS"]);
	        			
	        			if(stdRate != null && oUtil.isNull(rowData[i]["RVC_RATE"])) {
	        				alert(resource.getMessage("TXT_RVC_RATE_OMISS") + "<br/>"
	            					+ resource.getMessage("LTIT_NAME") + ":" + rowData[i]["ITEM_CD"] + ", "
	            					+ resource.getMessage("AGRET_CODE") + ":" + rowData[i]["FTA_CD"]);
	        				
	        				grid.handle.selectRowIndex(dg_1, i);
	        				form.handle.clear("MMA017_01_form_02");
	            			return;
	        			}
	        			
	        			var compareRst = MMA017_01.util.compareRuleToRvcRate(rowData[i]["RULE_CONTENTS"], rowData[i]["RVC_RATE"], rowData[i]["FTA_CO_YN"]);
	        			
	        			if(stdRate != null && !compareRst) {
	        				alert(resource.getMessage("TXT_RVC_RATE_ERROR") + "<br/>"
	            					+ resource.getMessage("LTIT_NAME") + ":" + rowData[i]["ITEM_CD"] + ", "
	            					+ resource.getMessage("AGRET_CODE") + ":" + rowData[i]["FTA_CD"]);
	        				
	        				grid.handle.selectRowIndex(dg_1, i);
	        				form.handle.clear("MMA017_01_form_02");
	            			return;
	        			}
	        		}
        		}
        		
        		// 역외사유 등록체크
            	if(norsYn == "Y") {
        			var nocd = rowData[i]["NONORIGIN_REASON_CD"];
        			var nomsg = rowData[i]["NONORIGIN_REASON_DESC"];
        			var coyn = rowData[i]["FTA_CO_YN"];
        			
        			// 적합성 체크
        			if(!MMA017_01.control.nonOriginValidation(nocd, nomsg, coyn)) {
        				grid.handle.selectRowIndex(dg_1, i);
        				form.handle.clear("MMA017_01_form_02");
        				
        				return;
            	    }
            	}
                
                // RCEP 원산지 국가 등록여부 체크
                if(rowData[i]["FTA_NAME"] == "RCEP" && oUtil.isNull(rowData[i]["ORIGIN_NATION_CD"])) {
                    rcepYn = true;
                    rcepIdx = i;
                }
        	}
        	
            // RCEP 원산지 국가가 누락된 경우, 원산지 국가를 등록할지 확인한다.
            if(rcepYn == true) {
                /*"RCEP 협정의 원산지 국가 정보를 입력해 주세요.<br>"+
                "원산지 국가 결정방법은 아래와 같습니다.<br><br>"+
                "1) 직접 생산하는 제품이고 원산지 판정결과가 충족인 경우, 생산국가코드를 입력합니다.<br>"+
                "2) 상품(최소공정 포함)이고 RCEP 원산지 국가가 확인된 경우, 해당 원산지 국가를 입력합니다.<br>"+
                "3) 원산지 불충족하거나 RCEP 원산지 국가를 확인할 수 없는 경우에는 미입력합니다.<br><br>"+
                "해당 사항이 없는 경우에는 [Cancel]을 클릭해 주세요.";*/
                var msg_1 = resource.getMessage("MSG_WAR_RCEP_ORGNAT"); 
                          
                $.messager.confirm(CNFIR, msg_1, function(flag) {
                    if(flag) {
                        grid.handle.selectRowIndex(dg_1, rcepIdx);
                    } else {
                        MMA017_01.control.insertRcvCoInfo(rowData);
                    }
                });
            } else {
                MMA017_01.control.insertRcvCoInfo(rowData);
            }
        },
        insertRcvCoInfo : function(rowData, ret) {
            var obj = form.getObject("MMA017_01_form_01");
            var confMsg = resource.getMessage("HSCODE_CHARGE_CONFIRM")+ " " + resource.getMessage("MSG_CONFIRM_SAVE"); /*저장하시겠습니까?*/
            
            if(ret == "re") {
                form.handle.setValue("MMA017_01_form_01", "gridData", grid.util.convertJson2Rows(rowData));
                    
                form.init.setURL("/mm/pop/mmA017_01/insertRcvCoInfo");
                form.init.setProgressFlag(true);
                form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
                form.init.setCallBackFunction("callBack_saveBasicInfo");
                
                form.submit(obj);
            } else {
                $.messager.confirm(CNFIR, confMsg, function(flag) {
                    if(flag) {
                        form.handle.setValue("MMA017_01_form_01", "gridData", grid.util.convertJson2Rows(rowData));
                        
                        form.init.setURL("/mm/pop/mmA017_01/insertRcvCoInfo");
                        form.init.setProgressFlag(true);
                        form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
                        form.init.setCallBackFunction("callBack_saveBasicInfo");
                        
                        form.submit(obj);
                    }
                });
            }
        },
        //버튼액션(삭제) : 품목별원산지정보에서 자재 삭제
        deleteItemList : function() {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");

        	if(!grid.handle.isChecked(dg_1)) {
        		alert(resource.getMessage("MSG_NO_CHECK")); /*선택된 데이터가 없습니다.*/
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
        	var obj = form.getObject("MMA017_01_form_01");
        	
            form.init.setURL("/mm/pop/mmA017_01/selectProofFile");
            form.init.setProgressFlag(false);
			
			form.submit(obj);
        },
        appendCoData : function(index, datas) {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	var idx = 0;
        	var total = datas.length;
        	
        	if(!oUtil.isNull(index)) {
            	idx = index;
            } else {
            	MMA017_01.event.uploadProgressBar(0);
            }
        	
        	try {
            	if(idx == total) {
            		MMA017_01.event.uploadProgressBar(100);
            		
                	setTimeout(function() {
            			$('#MMA017_01_main_win').window('close');
            		}, 1000);
                	
                	return;
                } else {
                	if(idx == 0) $('#MMA017_01_main_win').window('open');
                	
                	// 중복판정인지 체크해서 중복되지 않은 경우에만 판정을 요청함
                    setTimeout(function() {
                    	if(form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_YN") != "Y") {
                    		datas[idx].NONORIGIN_REASON_CD = "";
                    		datas[idx].NONORIGIN_REASON_DESC = "";
                        }
                    	
                    	grid.handle.appendRow(dg_1, datas[idx]);

                    	MMA017_01.event.uploadProgressBar(idx, datas);
                    }, 10);
                }
            } catch(ex) {
            	$('#MMA017_01_main_win').window('close');
                alert(resource.getMessage("MSG_UNSPECIFIED_ERROR")+"<br>"+ex);
            }
        },
        applyRowData : function() {
        	setTimeout(function() {
        		$("#MMA017_01_btn_05").attr("href", "javascript:MMA017_01.control.applyRowData();");
        	}, 300);
        	
        	$("#MMA017_01_btn_05").attr("href", "#");
        	
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	var data = form.handle.getElementsParam("MMA017_01_form_02");
        	
        	if(oUtil.isNull(data.FTA_CD)) {
        		alert(resource.getMessage("TXT_ITEMBY_ORG_INFO,MSG_CHOICE_BOMBO"));
        		return;
        	}
        	
        	var norsYn = form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_YN");
        	var targetId = form.handle.getValue("MMA017_01_form_01", "TARGET_PID");
    		var coyn = combo.handle.getValue("MMA017_01_form_02", "FTA_CO_YN");
            var natcd = form.handle.getValue("MMA017_01_form_02", "ORIGIN_NATION_CD");
            var ftacd = form.handle.getValue("MMA017_01_form_02", "FTA_CD");
            
            // 역외산인 경우, KR원산지 입력불가 체크
            if(coyn == 'N' && natcd.toUpperCase() == 'KR' && ftacd != 'PKRRC') {
                alert(resource.getMessage("CC_충족여부가 N인 경우에는 원산지 국가를 KR로 입력할 수 없습니다."));
                form.handle.setValue("MMA017_01_form_02", "ORIGIN_NATION_CD", "");
                form.handle.setValue("MMA017_01_form_02", "ORIGIN_NATION_NAME", "");
                return;
            }
            
    		// 철판 미확인 품목 확인서 등록 시에는 역외사유와 비율을 체크하지 않도록 함(2019.10.18)
    		if(targetId == "DI3011_01") {
    			norsYn = "N";
    		}
    		
        	if(norsYn == "Y") {
    			var nocd = combo.handle.getValue("MMA017_01_form_02", "NONORIGIN_REASON_CD");
    			var nomsg = form.handle.getValue("MMA017_01_form_02", "NONORIGIN_REASON_DESC");
    			
    			// 적합성 체크
    			if(!MMA017_01.control.nonOriginValidation(nocd, nomsg, coyn)) {
    				return;
        	    } else if(coyn == "Y" && !oUtil.isNull(nocd)) {
    				$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("NOT_MEET_NO_REASON_KEEP"), function(flag) {
    	        		if(flag) {
    	        			MMA017_01.control.applyNextStep(data, dg_1);
    	        		}
    	        	});
    			} else {
    				MMA017_01.control.applyNextStep(data, dg_1);
    			}
    		} else {
    			MMA017_01.control.applyNextStep(data, dg_1);
    		}
        },
        applyNextStep : function(data, dg_1) {
        	MMA017_01.dialog.callByMMA018_01(data);
        	
        	// 처리한 row의 다음 row로 자동 이동시킴
        	var idx = grid.handle.getCurrentRowIndex(dg_1);
        	var cnt = grid.handle.getRowCount(dg_1, "1");
        	
        	if((idx+1) < cnt) {
        		grid.handle.selectRowIndex(dg_1, idx+1);        			
        		
        		MMA017_01.event.onClick_MMA017_01_grid_01();
        	}
        },
        nonOriginValidation : function(nocd, nomsg, coyn) {
        	var norsReq = form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_REQ_YN");
        	
        	// 역외사유 적합성 체크
			if(norsReq == "Y" && coyn == "N" && oUtil.isNull(nocd)) {
				alert(resource.getMessage("NOT_MEET_NO_REASON_INPUT"));
				
				return false;
			} else if((norsReq == "Y" && coyn == "N" && oUtil.isNull(nomsg)) || (!oUtil.isNull(nocd) && oUtil.isNull(nomsg))) {
				var noral = null;
				
				if(nocd == "R01") noral = resource.getMessage("NOT_MEET_REASON_CODE1");
				if(nocd == "R02") noral = resource.getMessage("NOT_MEET_REASON_CODE2");
				if(nocd == "R03") noral = resource.getMessage("NOT_MEET_REASON_CODE3");
				if(nocd == "R04") noral = resource.getMessage("NOT_MEET_REASON_CODE4");
				if(nocd == "R05") noral = resource.getMessage("NOT_MEET_REASON_CODE5")
				if(nocd == "R06") noral = resource.getMessage("NOT_MEET_REASON_CODE6");
				if(nocd == "R07") noral = resource.getMessage("NOT_MEET_REASON_CODE7");
				if(nocd == "R08") noral = resource.getMessage("NOT_MEET_REASON_CODE8");
				if(nocd == "R00") noral = resource.getMessage("NOT_MEET_REASON_CODE9");
				
				alert(noral);
				
				return false;
    	    }
			
			return true;
        },
        selectRuleContents : function(rowData) {
        	if(oUtil.isNull(rowData)) {
        		rowData = form.handle.getElementsParam("MMA017_01_form_02");
        	}
        	
        	if(!oUtil.isNull(rowData.RULE_CONTENTS)) {
        		combo.init.setValue("MMA017_01_form_02", "RULE_CONTENTS", rowData.RULE_CONTENTS);
        	}
        	
        	if(!oUtil.isNull(rowData.HS_CODE)) {
        		var lang = rowData.HS_CODE.length;
        		
        		if(lang == 6 || lang == 8 || lang == 10) {
		        	var param = {HS_CODE:rowData.HS_CODE, FTA_CODE:rowData.FTA_CD, FTA_RULE_CODE:rowData.RULE_CONTENTS};
					combo.handle.reload("MMA017_01_form_02", "RULE_CONTENTS", "/mm/pop/MMA018_01/selectRuleContents", param);
        		} else if(lang < 6){
        			combo.handle.loadData("MMA017_01_form_02", "RULE_CONTENTS", {});
        		}
        	} else {
        		combo.handle.loadData("MMA017_01_form_02", "RULE_CONTENTS", {});
        	}
        },
        saveOriginModifyInfo : function() {
        	var obj = form.getObject("MMA017_01_form_01");
        	var endDate = calendar.handle.getDate("MMA017_01_form_01", "END_DATE");
        	var modEndDate = calendar.handle.getDate("MMA017_01_form_01", "MOD_END_DATE");

        	//날짜 검증하기
        	if(!MMA017_01.event.compareToDate()) {
        		return;
        	} else if(endDate == modEndDate) { // 지정한 만료일자가와 수정가능한 일자가 동일한지 체크
        		alert(resource.getMessage("MSG_MODIFY_NOT_FOUND"));
        		return;
        	} else {
        		var confMsg = resource.getMessage("MSG_REGISTER") + "<br>" + resource.getMessage("MSG_CONFIRM_UPDATE");
        		
            	$.messager.confirm(resource.getMessage("CNFIR"), confMsg, function(flag) {
            		if(flag) {
            			form.init.setURL("/mm/pop/mmA017_01/updateModifyEndDate");
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
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	var rowCnt = grid.handle.getRowCount(dg_1, "1");
        	
        	if(rowCnt == 0) {
        		alert(resource.getMessage("MSG_NOT_EXIST_DATA"));
        		return;
        	}
        	
        	var rows = grid.handle.getAllRows(dg_1, "1");
        	var ftaCd = combo.handle.getValue("MMA017_01_form_03", "FTA_CD");
        	var total = rows.length;
            
        	try {
    			//$.messager.progress({text: "Data loading..."});
                $('#MMA017_01_de_progress').progressbar('setValue', 0);
                $('#MMA017_01_main_win').window('open');
        		
                var i = 0;
                var playAlert = setInterval(function() {
                    if(oUtil.isNull(ftaCd) || ftaCd == rows[i].FTA_CD) {
                        rows[i].FTA_CO_YN = org;
                        
                        if(org == "N") rows[i].ORIGIN_NATION_CD = "";
                        else {
                            if(rows[i].FTA_CNCS_MTH == 'B') {
                               rows[i].ORIGIN_NATION_CD = rows[i].FTA_GROUP_CD;
                            }
                        }
                        
                        grid.handle.setChangeRowValue(dg_1, i, rows[i]);
                    }
                    
                    if((i+1) == rowCnt) {
                        // $.messager.progress('close');
                        // MMA017_01.event.uploadProgressBar(100);
                        $('#MMA017_01_de_progress').progressbar('setValue', 100);
                        $('#MMA017_01_main_win').window('close');
                        
                        clearInterval(playAlert);
                    } else {
                        var rate = ((i+1)/total)*100;
                        
                        $('#MMA017_01_de_progress').progressbar('setValue', Math.floor(rate));
                    }
                    
                    i++;
                }, 20);
                
                /* setTimeout(function() {
		        	for(var i = 0; i < rowCnt; i++) {
		        	}
        		}, 3000);*/
        	} catch(e) {
        		if(SESSION.USER_ID == "fta") {
        			console.log(e);
        		}
        	}
        },
        ftaApplyNationOfRcep : function() {
            var dg_1 = grid.getObject("MMA017_01_grid_01");
            var rowCnt = grid.handle.getRowCount(dg_1, "1");
            
            if(rowCnt == 0) {
                alert(resource.getMessage("MSG_NOT_EXIST_DATA"));
                return;
            }
            
            var rows = grid.handle.getAllRows(dg_1, "1");
            var ftaCd = "P"+SESSION.FTA_NATION+"RC";
            var nation = combo.handle.getValue("MMA017_01_form_03", "NATION_CD");
            var total = rows.length;
            
            if(nation == "AA") nation = "";
            
            try {
                //$.messager.progress({text: "Data loading..."});
                $('#MMA017_01_de_progress').progressbar('setValue', 0);
                $('#MMA017_01_main_win').window('open');
                
                var i = 0;
                var playAlert = setInterval(function() {
                    if(oUtil.isNull(ftaCd) || ftaCd == rows[i].FTA_CD) {
                        rows[i].ORIGIN_NATION_CD = nation;
                        
                        grid.handle.setChangeRowValue(dg_1, i, rows[i]);
                    }
                    
                    if((i+1) == rowCnt) {
                        // $.messager.progress('close');
                        // MMA017_01.event.uploadProgressBar(100);
                        $('#MMA017_01_de_progress').progressbar('setValue', 100);
                        $('#MMA017_01_main_win').window('close');
                        
                        clearInterval(playAlert);
                    } else {
                        var rate = ((i+1)/total)*100;
                        
                        $('#MMA017_01_de_progress').progressbar('setValue', Math.floor(rate));
                    }
                    
                    i++;
                }, 20);
                
                /* setTimeout(function() {
                    for(var i = 0; i < rowCnt; i++) {
                    }
                }, 3000);*/
            } catch(e) {
                if(SESSION.USER_ID == "fta") {
                    console.log(e);
                }
            }
        },
        selectEvdcFileList : function(data) {
     		var dg_1 = grid.getObject("MMA017_01_grid_03");
     		var params = form.handle.getElementsParam("MMA017_01_form_01");
     		
     		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA021_02/selectEvdcFileList", params);
        },
        deleteEvcdFile : function() {
        	var dg_1 = grid.getObject("MMA017_01_grid_03");
        	
        	if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
    		}
        	
        	$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
        		if(flag) {
		        	var row = grid.handle.getSelectedRowData(dg_1);
		        	
		        	form.handle.loadData("MMA017_01_form_04", row);
		        	
		        	var obj = form.getObject("MMA017_01_form_04");
		        	
		        	form.init.setURL("/mm/pop/mmA017_01/deleteProofFileInfo");
					form.init.setProgressFlag(false);
					form.init.setCallBackFunction("deleteEvcdFile");
					
					form.submit(obj);
        		}
        	});
        },
        updateMgmtEvenFile : function() {
            if(!form.handle.isValidate("MMA017_01_form_01")) return;
            
            $.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("TXT_ALREADY_DELETE_RE")+"<br><br>"+resource.getMessage("TXT_CONTINUE_QUESTION"), function(flag) {
                if(flag) {
                    var obj = form.getObject("MMA017_01_form_01");
                    
                    form.init.setURL("/mm/pop/mmA017_01/updateMgmtEvenFile");
                    form.init.setProgressFlag(false);
                    form.init.setCallBackFunction("updateMgmtEvenFile");
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {
    	//버튼액션(추가, 수정) : 외부원산지품목 정보 - 사용안함(등록화면으로 통합시킴)
        openDialog_1 : function(flag) {
            var dg_1 = grid.getObject("MMA017_01_grid_01");
            var dl_1;
            
        	if(flag == "update" && !grid.handle.isSelected(dg_1)) {
    			alert(resource.getMessage("MSG_NO_SELECT")); /*선택된 데이터가 없습니다.*/
    			return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("MMA017_01_form_01");
            var params2 = {};
            
            if(flag == "update") {
            	params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);
            
            //그리드의 idx값 조회
            if(flag == "update") {
	        	params.GRID_IDX = grid.handle.getCurrentRowIndex(dg_1);
            }
            params.TARGET_PID = "MMA017_01";
            params.flag       = flag;
            
            dl_1 = dialog.getObject("MMA018_01_dailog_01");
            
            // 팝업 셋팅
            dialog.init.setHeight(300);
            dialog.init.setTitle(resource.getMessage("MMA018_01"));
            dialog.init.setURL("/mm/pop/mmA018_01");
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        },
        // 버튼액션(엑셀 업로드) : 품목별원산지정보 저장 기능
        openDialog_2 : function() {
    		var dg_2 = grid.getObject("MMA017_01_grid_01");
    		var dl_2 = dialog.getObject("MMA022_01_dailog_01");
    		
    		// 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("MMA017_01_form_01");
            var params2 = {};
            
            $.extend(params, params1, params2);

            //추가 파라매터
            params.TARGET_PID = "MMA017_01";
    		
    		// 팝업 셋팅
    		dialog.init.setTitle(resource.getMessage("MMA022_01"));
    		dialog.init.setURL("/mm/pop/mmA022_01");
    		dialog.init.setQueryParams(params);
    		
    		dialog.open(dl_2);
        },
        // 이미지 섬네일(원본 이미지로 보기) - 사용안함(기능 제공에서 제외시킴)
        openDialog_3 : function() {
        	var dg_3 = grid.getObject("MMA017_01_grid_01");
        	var dl_3 = dialog.getObject("MMA026_01_dailog_01");
        	
        	var flag       = form.handle.getValue("MMA017_01_form_01", "flag");
        	var coDocNo    = form.handle.getValue("MMA017_01_form_01", "CO_DOC_NO");
            var vendorCd   = form.handle.getValue("MMA017_01_form_01", "VENDOR_CD");
            var companyCd  = form.handle.getValue("MMA017_01_form_01", "COMPANY_CD");

            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
        	params.TARGET_PID  = "MMA017_01";  
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
        callByMMA018_01 : function(datas) {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	
        	if(!oUtil.isNull(datas)) {
        		// 불충족 사유가 적용되는 경우에만 적용함.
        		if(form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_YN") == "Y") {
        			var nocd = combo.handle.getValue("MMA017_01_form_02", "NONORIGIN_REASON_CD");
        			
	        		if(!oUtil.isNull(nocd)) {
		        		datas.NONORIGIN_REASON_CD = nocd;
		        		datas.NONORIGIN_REASON_NAME = combo.handle.getText("MMA017_01_form_02", "NONORIGIN_REASON_CD");
	        		}
        		}
        		
                datas.ORIGIN_NATION_CD = datas.ORIGIN_NATION_CD.toUpperCase();
        		var selRow = grid.handle.getCurrentRowIndex(dg_1);
        		
        		grid.handle.setChangeRowValue(dg_1, selRow, datas);
    			grid.handle.selectRowIndex(dg_1, selRow); // 수정된 row를 선택함
        	}
        },
        callByMMA022_01 : function(datas) {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	var rows = grid.handle.getAllRows(dg_1, "1");
        	var rowCnt = grid.handle.getRowCount(dg_1, "1");
        	var vFlag = nullToString(form.handle.getValue("MMA017_01_form_01", "flag"));
        	
        	// 확인서 번호와 FTA그룹 코드는 유지해야 함
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
        	
        	MMA017_01.control.appendCoData(null, datas);
        	
        	var dl_1 = dialog.getObject("MMA022_01_dailog_01");
        	dialog.handle.close(dl_1);
        },
        closeDialog_top : function() {
        	if(MMA017_01.init.coModifyChecker) {
        		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_NOT_FINISHED_AND_CLOSE"), function(flag) {
        			if(flag) {
        				var dl_1 = dialog.getObject("MMA017_01_dailog_01");
        	        	dialog.handle.close(dl_1);
        			}
        		});
        	} else {
        		var vFlag = nullToString(form.handle.getValue("MMA017_01_form_01", "flag"));
        		
        		if(vFlag == "modify") { // 원산지 수정중에는 창을 닫을지 확인한다. 
	        		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_NOT_MODIFY_CLOSE"), function(flag) {
	        			if(flag) {
	        				var dl_1 = dialog.getObject("MMA017_01_dailog_01");
	        	        	dialog.handle.close(dl_1);
	        			}
	        		});
        		} else {  // 수정이 완료되면 바로창을 닫는다.
        			var dl_1 = dialog.getObject("MMA017_01_dailog_01");
    	        	dialog.handle.close(dl_1);
        		}
        	}
        },
        openDialog_4 : function() {
        	var dg_1 = grid.getObject("MMA017_01_grid_01");
        	var selrow = grid.handle.getSelectedRowData(dg_1);
        	var params = form.handle.getElementsParam("MMA017_01_form_01");
        	
        	params.ITEM_CD = selrow.ITEM_CD;
        	
        	if(SESSION.FTA_NATION == "VN") {
        		var dl_4 = dialog.getObject("MMA021_04_dailog_01");
        		
        		dialog.init.setURL("/mm/pop/mmA021_04");
        		dialog.init.setQueryParams(params);
        		dialog.init.setHeight(210);
        		dialog.init.setWidth(550);
        		
        		dialog.open(dl_4);
        	} else {        		
        		var dl_3 = dialog.getObject("MMA021_03_dailog_01");
        		
        		dialog.init.setURL("/mm/pop/mmA021_03");
        		dialog.init.setQueryParams(params);
        		dialog.init.setHeight(530);
        		dialog.init.setWidth(800);
        		
        		dialog.open(dl_3);
        	}
        },
        openDialog_5 : function() {
            var dg_1 = dialog.getObject("MMA008_01_dailog_01");

            dialog.init.setTitle(resource.getMessage("MMA008_01"));
            dialog.init.setWidth(600);
            dialog.init.setHeight(400);
            dialog.init.setURL("/mm/pop/mmA008_01");
            dialog.init.setQueryParams({TARGET_PID:"MMA017_01", COMPANY_CD:SESSION.COMPANY_CD});
            
            dialog.open(dg_1);
        },
        callByMMA008_01 : function(datas) {
            var dg_1 = dialog.getObject("MMA008_01_dailog_01");
            
            form.handle.setValue("MMA017_01_form_02", "ORIGIN_NATION_CD", datas.CODE);
            form.handle.setValue("MMA017_01_form_02", "ORIGIN_NATION_NAME", datas.CODE_NAME);
            
            dialog.handle.close(dg_1);
        }
    },
    // 화면 UI 변경
    ui : {
    	onChangeChkAuto : function() {
    		if(form.handle.isChecked("MMA017_01_form_01", "chkAuto")) {
    			form.handle.setValue("MMA017_01_form_01", "chkAuto", "Y");
    			form.handle.setValue("MMA017_01_form_01", "CO_DOC_NO", "");
    			form.handle.addClass("MMA017_01_form_01", "CO_DOC_NO", "search_readOnly");
        		form.handle.readonly("MMA017_01_form_01", "CO_DOC_NO", true);
    			form.handle.setValidate("MMA017_01_form_01", "CO_DOC_NO", false);
    		} else {
    			form.handle.setValue("MMA017_01_form_01", "chkAuto", "N");
    			form.handle.removeClass("MMA017_01_form_01", "CO_DOC_NO", "search_readOnly");
        		form.handle.readonly("MMA017_01_form_01", "CO_DOC_NO", false);
        		form.handle.setValidate("MMA017_01_form_01", "CO_DOC_NO", true);
    		}
    		
    		if(form.handle.getValue("MMA017_01_form_01", "REQ_TYPE") == "NEWFTA") {
    			calendar.handle.readonly("MMA017_01_form_01", "APPLY_DATE", true);
        		calendar.handle.readonly("MMA017_01_form_01", "END_DATE", true);
  			}
    	},
    	onChangeIssueUI : function(data) {
    		form.handle.closeAll("MMA017_01_form_01");
    		form.handle.closeAll("MMA017_01_form_02");
    		
    		if(!oUtil.isNull(data)) {
	    		form.handle.setValue("MMA017_01_form_01", "CO_DOC_NO", data.CO_DOC_NO);
	    		form.handle.setValue("MMA017_01_form_01", "PRF_FILE_NAME", data.PRF_FILE_NAME);
	    		$("#MMA017_01_form_01_PRF_FILE_NAME_SPAN").text(data.PRF_FILE_NAME); 
    		}
    		
    		form.util.setVisible("MMA017_01_form_02_chkAuto", false);
    		form.util.setVisible("MMA017_01_form_01_PRF_FILE_DW", true);
    		form.util.setVisible("MMA017_01_form_01_PRF_FILE", false);
    		form.util.setVisible("MMA017_01_form_01_chkAuto", false);
    		
    		form.util.setVisible("MMA017_01_btn_01", false);
    		form.util.setVisible("MMA017_01_btn_32", false);
    		form.util.setVisible("MMA017_01_btn_34", false);
    		form.util.setVisible("MMA017_01_btn_35", false);
    		form.util.setVisible("MMA017_01_btn_36", false);
    		form.util.setVisible("MMA017_01_btn_40", true);
    		form.util.setVisible("MMA017_01_span_01", false);
            form.util.setVisible("MMA017_01_span_05", false);
    		form.util.setVisible("MMA017_01_tr_02", false);
    		form.util.setVisible("MMA017_01_tr_03", false);
    		
    		$("#MMA017_01_layout_01").panel('minimize');
    		$("#MMA017_01_layout_02").panel('maximize');
    	},
    	onChangeUpdateUI : function() {
    		form.util.setVisible("MMA017_01_form_01_chkAuto", false);
    		
    		form.handle.readonly("MMA017_01_form_01", "CO_DOC_NO", true);
    		form.handle.addClass("MMA017_01_form_01", "CO_DOC_NO", "input_readOnly");
    		calendar.handle.readonly("MMA017_01_form_01", "ISSUE_DATE", true);
    		calendar.handle.readonly("MMA017_01_form_01", "END_DATE", true);
    	},
    	onModifyIssueUI : function() {
    		form.handle.closeAll("MMA017_01_form_01", false);
    		form.handle.closeAll("MMA017_01_form_02", false);
    		
    		// 확인서 번호 자동 채번 체크 
			form.util.setVisible("MMA017_01_form_01_chkAuto", true);
			form.handle.setValue("MMA017_01_form_01", "CO_DOC_NO", "");
			
			MMA017_01.ui.onChangeChkAuto();
			
			// 첨부파일 초기화
			form.util.setVisible("MMA017_01_form_01_PRF_FILE_DW", false);
    		form.util.setVisible("MMA017_01_form_01_PRF_FILE", true);
    		if(form.handle.getValue("MMA017_01_form_01", "REGISTED_BY") != "2") {
    			form.util.setVisible("MMA017_01_form_02_chkAuto", true);
    		}
    		
    		// 증빙파일 초기화
			form.util.setVisible("MMA017_01_btn_02", false);
			form.util.setVisible("MMA017_01_span_04", false);
    		form.util.setVisible("MMA017_01_span_03", false);
    		
    		var dg_3 = grid.getObject("MMA017_01_grid_03");
    		grid.handle.clearAll(dg_3);
    		
    		// 버튼 초기화
    		form.util.setVisible("MMA017_01_btn_01", true);
    		form.util.setVisible("MMA017_01_btn_32", true);
    		form.util.setVisible("MMA017_01_btn_34", true);
    		form.util.setVisible("MMA017_01_btn_35", true);
    		form.util.setVisible("MMA017_01_btn_36", true);
    		form.util.setVisible("MMA017_01_btn_40", false);
    		form.util.setVisible("MMA017_01_btn_03", false);
    		form.util.setVisible("MMA017_01_span_01", true);
            form.util.setVisible("MMA017_01_span_05", true);
    		
    		// UI 입력란 초기화
    		form.util.setVisible("MMA017_01_span_02", false);
    		form.handle.addClass("MMA017_01_form_01", "VENDOR_NAME", "input_readOnly");
    		form.handle.readonly("MMA017_01_form_01", "VENDOR_NAME", true);
    		form.handle.addClass("MMA017_01_form_01", "FTA_GROUP_NM", "input_readOnly");
    		form.handle.readonly("MMA017_01_form_01", "FTA_GROUP_NM", true);
    		calendar.handle.readonly("MMA017_01_form_01", "ISSUE_DATE", false);
    		calendar.handle.readonly("MMA017_01_form_01", "APPLY_DATE", true);
    		calendar.handle.readonly("MMA017_01_form_01", "END_DATE", false);
    		
    		form.handle.addClass("MMA017_01_form_02", "ITEM_CD", "input_readOnly");
    		form.handle.readonly("MMA017_01_form_02", "ITEM_CD", true);
    		form.handle.addClass("MMA017_01_form_02", "ITEM_NAME", "input_readOnly");
    		form.handle.readonly("MMA017_01_form_02", "ITEM_NAME", true);
    		form.handle.addClass("MMA017_01_form_02", "FTA_CD", "input_readOnly");
    		form.handle.readonly("MMA017_01_form_02", "FTA_CD", true);
    		form.handle.addClass("MMA017_01_form_02", "FTA_NAME", "input_readOnly");
    		form.handle.readonly("MMA017_01_form_02", "FTA_NAME", true);
    	},
    	onChangeFilePassChk : function() {
    		if(form.handle.isChecked("MMA017_01_form_01", "chkFile")) {
    			form.handle.setValue("MMA017_01_form_01", "PRF_FILE", "");
    			form.handle.setValidate("MMA017_01_form_01", "PRF_FILE", false);
    			form.util.setVisible("MMA017_01_form_01_PRF_FILE", false);
    		} else {
    			form.util.setVisible("MMA017_01_form_01_PRF_FILE", true);
    			form.handle.setValidate("MMA017_01_form_01", "PRF_FILE", true);
    		}
    	},
    	setRvcRate : function(rule, org) {
    		var stdRate = MMA017_01.util.getRuleRvcRate(rule);
    		
    		if(rule.indexOf("B55") > -1 || rule.indexOf("MC") > -1) {
				if(org == "Y") form.handle.setValue("MMA017_01_form_02", "RVC_RATE", "0");
				else form.handle.setValue("MMA017_01_form_02", "RVC_RATE", stdRate);
			} else if(rule.indexOf("BD") > -1 || rule.indexOf("BU") > -1 || rule.indexOf("NC") > -1) {
				if(org == "Y") form.handle.setValue("MMA017_01_form_02", "RVC_RATE", stdRate);
				else form.handle.setValue("MMA017_01_form_02", "RVC_RATE", "0");
			} else {
				form.handle.setValue("MMA017_01_form_02", "RVC_RATE", "");
			}
        },
        updateEvenFileUI : function() {
            var chk = $("#MMA017_01_span_07").css("display");
            
            if(chk == "none") {
                form.util.setVisible("MMA017_01_span_07", true);
                form.util.setVisible("MMA017_01_form_01_PRF_FILE", true);
                form.util.setVisible("MMA017_01_form_01_PRF_FILE_DW", false);
                
                form.handle.setValue("MMA017_01_form_01", "PRF_FILE", "");
                form.handle.disabled("MMA017_01_form_01", "PRF_FILE", false);
                form.handle.setValidate("MMA017_01_form_01", "PRF_FILE", true);
            } else {
                form.util.setVisible("MMA017_01_span_07", false);
                form.util.setVisible("MMA017_01_form_01_PRF_FILE", false);
                form.util.setVisible("MMA017_01_form_01_PRF_FILE_DW", true);
                
                form.handle.setValue("MMA017_01_form_01", "PRF_FILE", "");
                form.handle.disabled("MMA017_01_form_01", "PRF_FILE", true);
                form.handle.setValidate("MMA017_01_form_01", "PRF_FILE", false);
            }
        }
    },
    util : {
    	getRuleRvcRate : function(rule) {
    		var strInx = -1;
    		var endInx = 0;
    		
    		if(rule.indexOf("MC") > -1){
				strInx = rule.indexOf("MC");
    		} else if(rule.indexOf("BD") > -1){
    			strInx = rule.indexOf("BD");
    		} else if(rule.indexOf("BU") > -1){
    			strInx = rule.indexOf("BU");
    		} else if(rule.indexOf("NC") > -1){
    			strInx = rule.indexOf("NC");
    		} else if(rule.indexOf("B55") > -1) {
    			strInx = -1;
    		} else {
    			return null;
    		}
    		
    		if(rule.indexOf("+") > 0) {
    			if(rule.indexOf("MC") > -1 && rule.indexOf("MC") < rule.indexOf("+")){
    				endInx =  rule.indexOf("+");
        		} else if(rule.indexOf("BD") > -1 && rule.indexOf("BD") < rule.indexOf("+")){
        			endInx =  rule.indexOf("+");
        		} else if(rule.indexOf("BU") > -1 && rule.indexOf("BU") < rule.indexOf("+")){
        			endInx =  rule.indexOf("+");
        		} else if(rule.indexOf("NC") > -1 && rule.indexOf("NC") < rule.indexOf("+")){
        			endInx =  rule.indexOf("+");
        		} else {
        			endInx =  rule.length;
        		}
    		} else {
    			endInx =  rule.length;
    		}
    		
    		if(rule.indexOf("B55") > -1) {
    			return 55;
    		} else {
    			return parseInt(rule.substring((strInx+2), endInx));
    		}
    	},
    	compareRuleToRvcRate : function(rule, inRate, org) {
    		if(oUtil.isNull(inRate)) return true;
    		
    		var stdRate = this.getRuleRvcRate(rule);
    		
    		if(rule.indexOf("MC") > -1 || rule.indexOf("B55") > -1){
    			if(org == "Y" && stdRate < inRate) return false;
    			else if(org == "N" && rule.indexOf("+") < 0 && stdRate >= inRate)  return false; // 역외인데 PSR이 AND 조건이면 체크하지 않음
    			else return true;
    		} else if(rule.indexOf("BD") > -1 || rule.indexOf("BU") > -1 || rule.indexOf("NC") > -1){
    			if(org == "Y" && stdRate > inRate) return false;
    			else if(org == "N" && rule.indexOf("+") < 0 && stdRate <= inRate)  return false; // 역외인데 PSR이 AND 조건이면 체크하지 않음
    			else return true;
    		} else {
    			return true;
    		}
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {
    	evcdFileDownload : function(flag) {
        	var dg_1 = grid.getObject("MMA017_01_grid_03");
        	
        	if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
    		}
        	
        	var row = grid.handle.getSelectedRowData(dg_1);
    		
        	form.handle.loadData("MMA017_01_form_04", row);
			
        	var obj = form.getObject("MMA017_01_form_04");
        	
            form.init.setURL("/mm/pop/mmA021_02/selectEvdcDownloadFile");
            form.init.setProgressFlag(false);
			
			form.submit(obj);
        },
        setFiles : function(pfile) {
    		var fileForm = form.getObject("MMA017_01_form_01", "PRF_FILE");
    		
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
	            	form.handle.setValue("MMA017_01_form_01", "PRF_FILE", "");
	            	return;
	            }
			}
    	}
    },
    // 엑셀다운로드 구현
    excel : {
    	// 협력사 원산지 확인서 등록 양식
    	excelDownload_1 : function() {
      		var dg_1 = grid.getObject("MMA017_01_grid_01");
      		var rows = grid.handle.getAllRows(dg_1, "1");
        	var rowCnt = grid.handle.getRowCount(dg_1, "1");
        	var vFlag = nullToString(form.handle.getValue("MMA017_01_form_01", "flag"));
        	var reqType = nullToString(form.handle.getValue("MMA017_01_form_01", "REQ_TYPE"));
        	
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
      		
      		var fobj = form.getObject("MMA017_01_form_01");
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
      		
      		// 역외사유
      		var dg_5 = grid.getObject("MMA017_01_grid_05");
      		var prop = grid.getInitConfig(dg_5);
            var header_res = grid.util.convertJson2Header(prop.columns, prop.frozenColumns);
            
      		form.handle.setValue("MMA017_01_form_01", "HEADER_RES", header_res);
      		form.handle.setValue("MMA017_01_form_01", "gridData", grid.util.convertJson2Rows(inRows));
      		
      		if(vFlag == "insert") {
      			if(reqType != "NEWFTA") {
      				form.handle.setValue("MMA017_01_form_01", "excel", "N");
      			}
      			
				form.init.setURL("/mm/pop/mmA017_01/selectItemListCaseInsert.fle");
			} else {
				form.handle.setValue("MMA017_01_form_01", "excel", "Y");
				
				form.init.setURL("/mm/pop/mmA017_01/selectItemListCaseRenew.fle");
			}
  	        
      		var dg_4 = grid.getObject("MMA017_01_grid_04");
      		
  	        form.excelSubmit(dg_4, fobj, "CO_Upload_List", "List"); /*품목별 원산지 정보*/
  	    },
	  	excelDownload_2 : function() {
	        var dg_1 = grid.getObject("MMA017_01_grid_01");
	        var fobj = form.getObject("MMA017_01_form_01");
	        
	        form.init.setURL("/mm/pop/mmA017_01/selectItemListCaseUpdate.fle");
	        
	        form.excelSubmit(dg_1, fobj, resource.getMessage("TXT_ITEMBY_ORG_INFO"), resource.getMessage("TXT_ITEMBY_ORG_INFO"));
	    }
    },
    config : {
    	applyFtaNation : function() {
    		var docType = form.handle.getValue("MMA017_01_form_01", "CO_DOC_TYPE");
    		var vFlag = form.handle.getValue("MMA017_01_form_01", "flag");
    		var createBy = form.handle.getValue("MMA017_01_form_01", "REGISTED_BY");
    		
			if(docType == "N") {
				if(SESSION.FTA_NATION == "VN") {
					$("#MMA017_01_div_03").html(resource.getMessage("DECLA_DATE"));
				} else {					
					$("#MMA017_01_div_03").html(resource.getMessage("TXT_DEAL_DATE"));
				}
			}
			
			if(createBy == "2" && SESSION.USER_ID != "fta") {
    			form.util.setVisible("MMA017_01_form_02_chkAuto", false);
    		}
			
			if(vFlag == "update") {
				form.util.setVisible("MMA017_01_tr_01", true);
			}
			
			if(createBy == "4") { // 담당자가 코일을 등록할 때
				var coNo = form.handle.getValue("MMA017_01_form_01", "V_CO_DOC_NO");
    			form.handle.setValue("MMA017_01_form_01", "CO_DOC_NO", coNo);
    			
    			form.util.setVisible("MMA017_01_btn_34", false);
        		form.util.setVisible("MMA017_01_btn_35", false);
        		form.util.setVisible("MMA017_01_btn_36", false);
        		form.util.setVisible("MMA017_01_btn_40", false);
        		form.util.setVisible("MMA017_01_tr_02", false);
        		form.util.setVisible("MMA017_01_tr_03", false);
			} else {
				if(vFlag == "insert" || vFlag == "renew" || vFlag == "pending") {
	    			form.handle.setValue("MMA017_01_form_01", "CO_DOC_NO", "");
	    		} else if(vFlag == "update" || vFlag == "view" || vFlag == "modify") {
	    			var coNo = form.handle.getValue("MMA017_01_form_01", "V_CO_DOC_NO");
	    			
	    			form.handle.setValue("MMA017_01_form_01", "CO_DOC_NO", coNo);
		    	}
			}
			
			// 역외산 사유를 입력이 지정된 경우라면 해당 항목을 표시한다.
			if(form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_YN") == "N") {
				var columns1 = MMA017_01.datagrid.header1;
				
				grid.util.changeHeaderHidden(columns1 , "NONORIGIN_REASON_CD", true);
				grid.util.changeHeaderHidden(columns1 , "NONORIGIN_REASON_NAME", true);
				grid.util.changeHeaderHidden(columns1 , "NONORIGIN_REASON_DESC", true);
				
				var columns3 = MMA017_01.datagrid.header3;
				
				grid.util.changeHeaderHidden(columns3 , "NONORIGIN_REASON_CD", true);
				grid.util.changeHeaderHidden(columns3 , "NONORIGIN_REASON_DESC", true);
				grid.util.changeHeaderHidden(columns3 , "WRITET_CODE", true);
				grid.util.changeHeaderHidden(columns3 , "WRITET_DATA", true);
				grid.util.changeHeaderHidden(columns3 , "WRITET_DESC", true);
			} else {
				if(form.handle.getValue("MMA017_01_form_01", "NON_ORGIN_RESN_REQ_YN") == "Y") {
					var columns1 = MMA017_01.datagrid.header1;
					
					grid.util.changeHeaderTitle(columns1 , "NONORIGIN_REASON_CD", "*" + resource.getMessage("NOMEET, REASN,CODE"));
					grid.util.changeHeaderTitle(columns1 , "NONORIGIN_REASON_DESC", "*" + resource.getMessage("NOMEET, REASN,DSCPT"));
					
					var columns3 = MMA017_01.datagrid.header3;
					
					grid.util.changeHeaderTitle(columns3 , "NONORIGIN_REASON_CD", "*" + resource.getMessage("NOMEET, REASN,CODE"));
					grid.util.changeHeaderTitle(columns3 , "NONORIGIN_REASON_DESC", "*" + resource.getMessage("NOMEET, REASN,DSCPT"));
				}
			}

			if(SESSION.FTA_NATION == "VN") {
				form.util.setVisible("MMA017_01_form_02_chkAuto", false);
				form.util.setVisible("MMA017_01_btn_34", false);
				form.util.setVisible("MMA017_01_btn_35", false);
				form.util.setVisible("MMA017_01_btn_36", false);
				
				// 필수입력항목 제외
				form.handle.setValidate("MMA017_01_form_01", "VENDOR_CHARGE_NAME", false);
				form.handle.setValidate("MMA017_01_form_01", "VENDOR_CHARGE_POSITION", false);
				form.handle.setValidate("MMA017_01_form_01", "VENDOR_CHARGE_PHONE_NO", false);
				form.util.removeClass("MMA017_01_th_02", "point2");
				form.util.removeClass("MMA017_01_th_03", "point2");
				
				// 베트남 내수인 경우에는 RVC비율(RVC, RATE) 대신 역내산 비율(ORGGD_RATE)를 입력받음
				if(form.handle.getValue("MMA017_01_form_01", "TXN_TYPE") == "D") {
					var columns1 = MMA017_01.datagrid.header1;
					
					grid.util.changeHeaderTitle(columns1 , "RVC_RATE", "*" + resource.getMessage("ORGGD_RATE"));
					$("#MMA017_01_label_01").html(resource.getMessage("ORGGD_RATE"));
					
					form.util.addClass("MMA017_01_th_01", "point2");
					form.handle.setValidate("MMA017_01_form_01", "RVC_RATE", true);
				} else {
					form.handle.setValidate("MMA017_01_form_01", "RVC_RATE", false);
				}
			} else {
				form.handle.setValidate("MMA017_01_form_01", "RVC_RATE", false);
			}
			
//			if(PART_NUM_VIEW_YN == "Y") {
//				var columns = MMA017_01.datagrid.header1;
//                 
//                grid.util.changeHeaderHidden(columns , "TEMP_COLUMN3", false); // Part No.
//                grid.util.changeHeaderHidden(columns , "TEMP_COLUMN4", false); // ECN Code
//            }
		}
    }
    
};
