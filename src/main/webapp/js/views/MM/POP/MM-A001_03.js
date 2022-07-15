/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A001_03
 * 작업일자 : 2017.06.22
******************************************************************************************/

function onLoadPage() {
	MMA001_03.config.applyFtaNation();
    MMA001_03.init.initComponent();
}

var MMA001_03 = {

    init : {
        initComponent : function() {
        	MMA001_03.control.selectMainCompany(); // 회사별 설정을 통한 화면 구성 조회
        	MMA001_03.control.selectOriginReqInfo(); // 일반원산지 등록 필수인지 체크
        	MMA001_03.control.selectOriginReqMenuInfo(); // 일반원산지를 적용하는지 체크
        	MMA001_03.control.selectSubcontractMenuInfo(); // 사급확인서를 적용하는지 체크
        	MMA001_03.control.selectVendorRequiredInfo(); // 협력사 정보 필수등록여부 체크
        	
        	MMA001_03.calendar.initCalendar_1();
        	MMA001_03.calendar.initCalendar_2();
        	
        	//MMA001_03.combobox.initCombo_1();
        	MMA001_03.combobox.initCombo_2();
        	MMA001_03.combobox.initCombo_3();
        	
        	MMA001_03.datagrid.initGrid_1();
            MMA001_03.datagrid.initGrid_2();
            MMA001_03.datagrid.initGrid_3();
            
            MMA001_03.control.selectVendorProcessInfo(); // 원산지 확인서 처리상태 조회
        }
    }, // 초기값 설정
    calendar : {
    	initCalendar_1 : function() {
        	var toDay = (calendar.util.toYear2String()-1) + "01";
        	var obj_01 = calendar.getObject("MMA001_03_form_06", "FROM_DATE");
            
        	calendar.init.setInitDate(calendar.util.getFirstDay2String(toDay, "-"));
        	
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	form.handle.setValue("MMA001_03_form_06", "TO_DATE", calendar.util.toDate2String());
        }
    }, // 달력 그리드 생성
    combobox : {
    	initCombo_1 : function() {
		    var yyyyAry = new Array();
    		var obj = combo.getObject("MMA001_03_form_02", "STATE_YYYY");
		    
		    for(var i = calendar.util.toYear2String(); i >= 2016; i--) {
		    	var yyyy = new Object();
		    	
		    	yyyy = {CODE:i,NAME:i+resource.getMessage("YEAR") + " ~ " + resource.getMessage("TODAY")};
		    	
		    	yyyyAry.push(yyyy);
		    }
		    
		    combo.init.setData(yyyyAry);
		    combo.init.setValueField("CODE");
		    combo.init.setNameField("NAME");
		    combo.init.setCallFunction("onChangeStateYear");
		    
		    combo.create(obj);
		},
		initCombo_2 : function() {
			var obj = combo.getObject("MMA001_03_form_06", "CO_COVER_YEAR");
			var stYear = parseInt(calendar.util.toYear2String()) - 8;

			combo.init.setType("year");
			combo.init.setHeight(120);
			combo.init.setStartYear(stYear);
			combo.init.setYearCount(10);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			combo.init.setCallFunction("changeSearchYear");
			
			combo.create(obj);
		},
		initCombo_3 : function() {
        	var obj = combo.getObject("MMA001_03_form_06", "FTA_GROUP_CD");
        	var vCompanyCd  = form.handle.getValue("MMA001_03_form_06", "COMPANY_CD"); 
        	
        	combo.init.setURL("/mm/cbox/selectStandardCode");
        	combo.init.setQueryParams({COMPANY_CD:vCompanyCd, CATEGORY_CD:"FTA_GROUP_CD", ALL:"Y"});
        	combo.init.setValueField("CODE");
        	combo.init.setNameField("NAME");
        	
        	combo.create(obj);
        }
    },
    chart : {
    	chart1 : null,
    	initChart_1 : function(datas){
    		if(this.chart1) {
    			this.chart1.destroy();
            }
    		
    		var arrayData = new Array();
    		
    		var data = new Object();
    		data.STATUS_NAME = resource.getMessage("RENEW,REGER, CNTNB, : " + datas.RECEP_CNT);
    		if(!oUtil.isNull(datas)) data.CNT = datas.RECEP_CNT;
    		arrayData.push(data);
    		
    		var data = new Object();
    		data.STATUS_NAME = resource.getMessage("NEW,REGER, CNTNB, : " + datas.NO_RECEP_CNT);
    		if(!oUtil.isNull(datas)) data.CNT = datas.NO_RECEP_CNT;
    		arrayData.push(data);
    		
    		var data = new Object();
    		data.STATUS_NAME = resource.getMessage("TXT_MOD_REQ, CNTNB, : ")+datas.MOD_REQ_CNT;
    		if(!oUtil.isNull(datas)) data.CNT = datas.MOD_REQ_CNT;
    		arrayData.push(data);
    		
    		var cht = chart.getChart("MMA001_03_chart_01");
    		var jsonHeader = {TICKS: "STATUS_NAME", SERIES: ["CNT"]}; // TICKS:X축 라벨이 될 컬럼명, SERIES:Y축 values)
    		
    		var series = Theme.normalPieChart(cht);
    		chart.setAnimate(false);
    		
    		this.chart1 = chart.createChart(cht, arrayData, null, jsonHeader);
    	}
    },
    tooltip : {},
    datagrid : {
        header_1 : [
        	["SUBJECT",   resource.getMessage("TITLE"), 550, "left", "center", true, false, null, null, {format:"function", pid:"MMA001_03", name:"showNewIcon"}, 0, 0],
        	["CREATE_DATE",    resource.getMessage("TXT_CREATE_DAY"), 130, "center", "center", true, false, null, null, null, 0, 0],
        ],
        header_2 : [
        	["CO_DOC_NO",   resource.getMessage("TXT_COO_CERTIFY_NO")/*원산지증명번호*/ , 230, "center", "center", true, false, null, null, {format: 'function', pid:'MMA001_03', name:'coReportView'}, 0, 0],
            ["ISSUE_DATE",  resource.getMessage("TXT_SUBMIT_DATE")/*제출일자*/       , 120, "center", "center", true, false, null, null, {format:'date'}, 0, 0],
            ["APPLY_DATE",  resource.getMessage("TXT_COVER_START_DATE")/*포괄시작일자*/, 120, "center", "center", true, false, null, null, {format:'date'}, 0, 0],
            ["END_DATE",    resource.getMessage("TXT_COVER_EXPR_DATE")/*포괄만료일자*/ , 120, "center", "center", true, false, null, null, {format:'date'}, 0, 0],
            ["CO_ISSUE_STATUS_NM",    resource.getMessage("TXT_RESLT_STAT")/*포괄만료일자*/ , 100, "center", "center", true, false, null, null, null, 0, 0]
        ],
        header_3 : [
        	["REQUEST_TYPE_NAME",     resource.getMessage("REQ,TYPE"),     120, "center", "center", true, false, null, null, null, 0, 0],
            ["REQUEST_DATE",          resource.getMessage("REQ,DATE"),     150, "center", "center", true, false, null, null, {format:'date'}, 0, 0],
            ["REQUEST_REASON",        resource.getMessage("REQ,REASN"),    450, "left",   "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("MMA001_03_grid_01");
            var params = new Object();
            
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.USE_YN = "Y";
            params.BBS_TYPE = "1";
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/sp/vb/vbE001_01/selectNoticeList");
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용

            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
        	var toDay = (calendar.util.toYear2String()) + "01";
        	
        	var dg_1 = grid.getObject("MMA001_03_grid_02");
            var params = new Object();
            
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.VENDOR_CD = SESSION.VENDOR_CD;
            params.GLOBAL_CD = SESSION.FTA_NATION;
            params.CO_DETAIL_YN = "N";
            params.FROM_DATE = (calendar.util.toYear2String()-1) + "0101";
            params.TO_DATE = (calendar.util.toYear2String()) + "1231";
            if(SESSION.FTA_NATION == "VN") params.REGISTED_BY = '1';
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/sp/vg/vgB207_01/selectMainList");
            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            Theme.defaultGrid(dg_1, this.header_2);
        },
        initGrid_3 : function() {
        	var dg_1 = grid.getObject("MMA001_03_grid_03");
            var params = new Object();
            
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.VENDOR_CD = SESSION.VENDOR_CD;
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/sp/vg/vgB207_01/selectClientRequestList");
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_3);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA001_03_grid_01 : function(rowData) {
            MMA001_03.dialog.openDialog_1('view');
        },
        onDblClick_MMA001_03_grid_03 : function(rowData) {
            MMA001_03.ui.pageMove();
        },
    	selectSupplierIssueStatusOfChart : function(datas) {
        	MMA001_03.chart.initChart_1(datas); // 챠트 생성
    	},
    	onChangeStateYear : function(val) {
    		if(MMA001_03.chart.chart1) {
    			MMA001_03.chart.chart1.destroy();
            }
    		
    		MMA001_03.control.selectSupplierIssueStatus();
    	},
    	selectMainCompany : function(datas) {
    		try {
    			var bizNo = datas.rows[0].BUSINESS_NO;
    			
    			if(replaceAll(bizNo, "-", "") == "1338122303") { // 인지컨트롤스의 배너를 보여준다.
    				form.util.setVisible("MMA001_03_span_03", true);
    			}
    		} catch(e) { console.debug(e); }
    	},
    	selectOriginReqInfo : function(data) {
    		form.handle.setValue("MMA001_03_form_05", "GEN_ORIGIN_REQ_YN", data.CONFIG_VALUE);
    	},
    	selectOriginReqMenuInfo : function(data) {
    		if(oUtil.isNull(data.MENU_ID)) {
    			form.handle.setValue("MMA001_03_form_05", "GEN_ORIGIN_MENU_YN", "N");
    			form.util.setVisible("MMA001_03_tr_01", false);
    		} else {
    			form.handle.setValue("MMA001_03_form_05", "GEN_ORIGIN_MENU_YN", "Y");
    			form.util.setVisible("MMA001_03_tr_01", true);
    		}
    	},
    	selectSubcontractMenuInfo : function(data) {
    		if(oUtil.isNull(data.MENU_ID)) {
    			form.util.setVisible("MMA001_03_tr_02", false);
    		} else {
    			form.util.setVisible("MMA001_03_tr_02", true);
    		}
    	},
    	selectVendorProcessInfo : function(datas) {
    		if(oUtil.isNull(datas)) {
    			return;
    		} else {
    			for(var i = 0; i < datas.rows.length; i++) {
    				var data = datas.rows[i];
    				
    				if(data.DATA_TYPE == "N") { // 신규
    					$("#MMA001_03_lebal_01").html(data.TOT_CNT);
    					$("#MMA001_03_lebal_02").html(data.NO_CNT);
    					if(data.NO_CNT > 0) {
    						$("#MMA001_03_lebal_02").parent('td').css("background-color", "#f9e2e2");
    					} else {
    						$("#MMA001_03_lebal_02").parent('td').css("background-color", "#fff");
    					}
    				} else if(data.DATA_TYPE == "R") { // 갱신
    					$("#MMA001_03_lebal_05").html(data.TOT_CNT);
    					$("#MMA001_03_lebal_06").html(data.NO_CNT);
    					$("#MMA001_03_lebal_07").html(data.CMP_CNT);
    					$("#MMA001_03_lebal_08").html(data.CMP_RATE+"%");
    					if(data.NO_CNT > 0) {
    						$("#MMA001_03_lebal_06").parent('td').css("background-color", "#f9e2e2");
    					} else {
    						$("#MMA001_03_lebal_06").parent('td').css("background-color", "#fff");
    					}
    				} else if(data.DATA_TYPE == "Q") { // 수정요청
    					$("#MMA001_03_lebal_09").html(data.TOT_CNT);
    					$("#MMA001_03_lebal_10").html(data.NO_CNT);
    					$("#MMA001_03_lebal_11").html(data.CMP_CNT);
    					$("#MMA001_03_lebal_12").html(data.CMP_RATE+"%");
    					if(data.NO_CNT > 0) {
    						$("#MMA001_03_lebal_10").parent('td').css("background-color", "#f9e2e2");
    					} else {
    						$("#MMA001_03_lebal_10").parent('td').css("background-color", "#fff");
    					}
    				} else if(data.DATA_TYPE == "D") { // 미결
    					$("#MMA001_03_lebal_13").html(data.TOT_CNT);
    					$("#MMA001_03_lebal_14").html(data.NO_CNT);
    					$("#MMA001_03_lebal_15").html(data.CMP_CNT);
    					$("#MMA001_03_lebal_16").html(data.CMP_RATE+"%");
    					if(data.NO_CNT > 0) {
    						$("#MMA001_03_lebal_14").parent('td').css("background-color", "#f9e2e2");
    					} else {
    						$("#MMA001_03_lebal_14").parent('td').css("background-color", "#fff");
    					}
    				} else if(data.DATA_TYPE == "O") { // 일반확인서
    					$("#MMA001_03_lebal_17").html(data.TOT_CNT);
    					$("#MMA001_03_lebal_18").html(data.NO_CNT);
    					$("#MMA001_03_lebal_19").html(data.CMP_CNT);
    					$("#MMA001_03_lebal_20").html(data.CMP_RATE+"%");
    					if(data.NO_CNT > 0) {
    						$("#MMA001_03_lebal_18").parent('td').css("background-color", "#f9e2e2");
    					} else {
    						$("#MMA001_03_lebal_18").parent('td').css("background-color", "#fff");
    					}
    				} else if(data.DATA_TYPE == "S") { // 사급확인서
    					$("#MMA001_03_lebal_21").html(data.TOT_CNT);
    					$("#MMA001_03_lebal_22").html(data.NO_CNT);
    					$("#MMA001_03_lebal_23").html(data.CMP_CNT);
    					$("#MMA001_03_lebal_24").html(data.CMP_RATE+"%");
    					if(data.NO_CNT > 0) {
    						$("#MMA001_03_lebal_22").parent('td').css("background-color", "#f9e2e2");
    					} else {
    						$("#MMA001_03_lebal_22").parent('td').css("background-color", "#fff");
    					}
    				} 
    			}
    		}
    	}
    },
    control : {// 업무구현
        selectMainList : function() {
            if (!form.handle.isValidate("MMA001_03_form_01")) return;
            
            var dg_1 = grid.getObject("MMA001_03_grid_01");
            var params = form.handle.getElementsParam("MMA001_03_form_01");

            grid.handle.sendRedirect(dg_1, "/mm/pop/MMA001_03/selectMainList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA001_03_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);

            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }

            MMA001_03.event.onDblClick_MMA001_03_grid_01(rowData);
        },
        selectSupplierIssueStatus : function() {
        	var obj = form.getObject("MMA001_03_form_02");
        	
            form.init.setURL("/mm/pop/selectSupplierIssueStatus");
            form.init.setCallBackFunction("selectSupplierIssueStatusOfChart");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectMainCompany : function() {
        	var obj = form.getObject("MMA001_03_form_01");
        	
            form.init.setURL("/mm/pop/mmA002_02/selectMainCompanyList");
            form.init.setCallBackFunction("selectMainCompany");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectOriginReqInfo : function() {
        	var obj = form.getObject("MMA001_03_form_03");
        	
        	form.handle.setValue("MMA001_03_form_03", "SYS_CODE", "GEN_ORIGIN_REQ_YN");
        	
            form.init.setURL("/mm/pop/selectSystemConfig");
            form.init.setCallBackFunction("selectOriginReqInfo");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectOriginReqMenuInfo : function() {
        	var obj = form.getObject("MMA001_03_form_04");
        	
        	form.handle.setValue("MMA001_03_form_04", "MENU_ID", "VG-B208");
        	
            form.init.setURL("/mm/pop/selectSystemMenuInfo");
            form.init.setCallBackFunction("selectOriginReqMenuInfo");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectSubcontractMenuInfo : function() {
        	var obj = form.getObject("MMA001_03_form_07");
        	
        	form.handle.setValue("MMA001_03_form_07", "MENU_ID", "VM-D001");
        	
            form.init.setURL("/mm/pop/selectSystemMenuInfo");
            form.init.setCallBackFunction("selectSubcontractMenuInfo");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectVendorRequiredInfo : function() {
        	if(form.handle.getValue("MMA001_03_form_05", "VENDOR_INFO_REQUIRED_YN") == "Y") {    			
    			if(form.handle.getValue("MMA001_03_form_05", "VENDOR_WRITE_YN") == "Y") {
    				MMA001_03.dialog.openDialog_3();
    			}
    		}
        },
        selectVendorProcessInfo : function() {
        	var obj = form.getObject("MMA001_03_form_06");
        	
        	if(SESSION.FTA_NATION == "VN") {
        		form.init.setURL("/sp/vs/vsC002_01/selectVietnamVendorProcessInfo");
        	} else {
        		form.init.setURL("/sp/vs/vsC002_01/selectVendorProcessInfo");
        	}
            form.init.setCallBackFunction("selectVendorProcessInfo");
            form.init.setProgressFlag(true);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        }
    },
    dialog : {
        openDialog_1 : function(flag) {
            var dg_1 = grid.getObject("MMA001_03_grid_01");
            var dl_1 = dialog.getObject("SM7011_02_dailog_01");

            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }

            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = {};
            var params2 = {};

            if(flag != "insert") params2 = grid.handle.getSelectedRowData(dg_1);

            $.extend(params, params1, params2);
            
            params.flag = "view";
            params.COMPANY_CD = form.handle.getValue("MMA001_03_form_01", "COMPANY_CD");
            params.TARGET_PID = "MMA001_03";

            // 팝업 셋팅
            dialog.init.setTitle(resource.getMessage("SM7011_02"));
            dialog.init.setWidth(800);
            dialog.init.setHeight(437);
            dialog.init.setResizable(true);
            dialog.init.setURL("/fm/sm/sm7011_02");
            dialog.init.setQueryParams(params);

            dialog.open(dl_1);
        },
        openDialog_2 : function() {
        	var dl_1 = null;
        	var dg_1 = grid.getObject("MMA001_03_grid_02");
        	
        	if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	var params = grid.handle.getSelectedRowData(dg_1);
        	
        	params.TARGET_PID = "MMA001_03";
            params.flag = "view";
            
    		// 팝업 셋팅
            dialog.init.setWidth(1000);
            dialog.init.setHeight(700);
            
            if(SESSION.FTA_NATION == "MX") {
	            dl_1 = dialog.getObject("MMA019_01_dailog_01");
	            
	            dialog.init.setTitle(resource.getMessage("MMA019_01"));
	    		dialog.init.setURL("/mm/pop/mmA019_01");
	    		dialog.init.setQueryParams(params);
            } else if(SESSION.FTA_NATION == "KR" || SESSION.FTA_NATION == "VN") {
	            dl_1 = dialog.getObject("MMA017_01_dailog_01");
	            
	    		dialog.init.setTitle(resource.getMessage("MMA017_01"));
	    		dialog.init.setURL("/mm/pop/mmA017_01");
	    		dialog.init.setQueryParams(params);
            }
            
    		dialog.open(dl_1);
        },
        openDialog_3 : function() {
        	if(SESSION.FTA_NATION == "VN") {
        		var dl_1 = dialog.getObject("VSC002_05_dailog_01");
        		
        		dialog.init.setURL("/sp/vs/vsC002_05");
        		dialog.init.setWidth(1300);
        		dialog.init.setHeight(400);
        		dialog.init.setClosable(false);
        	} else {        		
        		var dl_1 = dialog.getObject("VSC002_03_dailog_01");
        		
        		dialog.init.setURL("/sp/vs/vsC002_03");
        		dialog.init.setWidth(1300);
        		dialog.init.setHeight(500);
        		dialog.init.setClosable(false);
        	}
        	
        	dialog.open(dl_1);
        }
    },
    ui : {
    	coReportView : function(value, row, index) {
    		if(oUtil.isNull(value)) {
                return "";
            } else {
                return "<a href=\"javascript:MMA001_03.dialog.openDialog_2();\">" +
                       "<img src=\"/images/icon/external_link.png\" boder=\"0\"/> "+value+"</a>";
            }
    	},
    	pageMove : function() {
    		var dg_1 = grid.getObject("MMA001_03_grid_03");
        	
        	if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	var params = grid.handle.getSelectedRowData(dg_1);
        	
        	if(SESSION.CERTIFY_TYPE == "internal") {
	        	if(params.REQUEST_TYPE == "MOD") {
	        		SI2006_05.ui.go_page("/sp/vg/vgB203_01", "", "VG-B003", resource.getMessage("MOD, REQ"), "SI2006_05_form_02");
	        	} else {
	        		SI2006_05.ui.go_page("/sp/vg/vgB205_01", "", "VG-B005", resource.getMessage("DPEND, REQ"), "SI2006_05_form_02");
	        	}
        	} else {
        		if(params.REQUEST_TYPE == "MOD") {
	        		MM0001_01.control.go_page("/sp/vg/vgB203_01", "", "VG-B003", resource.getMessage("MOD, REQ"));
	        	} else {
	        		MM0001_01.control.go_page("/sp/vg/vgB205_01", "", "VG-B005", resource.getMessage("DPEND, REQ"));
	        	}
        	}
    	},
    	showNewIcon : function(val, row, idx) {
    		var src;
    		
    		if(oUtil.isNull(val)) {
    			src = "";
    		} else {
    			if(row.NEW_YN == "Y") {
    				src = "<span id=\"MM0001_01_span_03\" class=\"new_notice\">New</span> <span>"+val+"</span>";
    			} else {
    				src = val;
    			}
    		}
    		
    		return src;
    	}
    },
    file : {
        // 파일 input 엘리먼트 구현
    },
    excel : {
        // 엑셀다운로드 구현
    },
    config : {
    	applyFtaNation : function() {
    		if(SESSION.FTA_NATION == "KR") {
    			form.util.setVisible("MMA001_03_div_01", true);
                form.util.setVisible("supplierKoreaSiteLink", true);
                
    			$("#MMA001_03_div_01").css("height", "46px");
    			$("#MMA001_03_div_01").css("padding-top", "2px");
    		} else {
    			$("#MMA001_03_div_01").css("height", "0px");
    			$("#MMA001_03_div_01").css("padding-top", "0px");
    		}
            
            // 원격지원서비스 표시여부(2022-05-02)
            if(form.handle.getValue("MMA001_03_form_06", "REMOTE_SERVICE_YN") == "Y") {
                if(SESSION.FTA_NATION == "KR") {
                    $("#MMA001_03_img_01").attr("src", "/images/main/remote_desktop.png");
                } else {
                    $("#MMA001_03_img_01").attr("src", "/images/main/remote_desktop_en_logo.png");
                }
                
                form.util.setVisible("MMA001_03_div_01", true);
                form.util.setVisible("MMA001_03_span_04", true);
                $("#MMA001_03_div_01").css("height", "46px");
                $("#MMA001_03_div_01").css("padding-top", "2px");
            }
    	}
    }

};
