/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A001_01
 * 작업일자 : 2016.05.08
******************************************************************************************/

function onLoadPage() {
	MMA001_01.config.applyFtaNation();
    MMA001_01.init.initComponent();
}

var MMA001_01 = {
	authTariff : form.handle.getValue("MMA001_01_form_01", "AUTHTARIFF"), // 관세절감액을 보일지 여부
    init : {
        initComponent : function() {
            MMA001_01.datagrid.initGrid_1();
            MMA001_01.datagrid.initGrid_2();
            MMA001_01.datagrid.initGrid_3();
            
            MMA001_01.combobox.initCombo_1();
            MMA001_01.combobox.initCombo_2();
            
            MMA001_01.control.initPageData("STEP0", true);
			MMA001_01.tooltip.initTooltip_1();
        }
    }, // 초기값 설정
    calendar : {}, // 달력 그리드 생성
    combobox : {
        initCombo_1 : function() {
            var obj = combo.getObject("MMA001_01_form_02", "YYYY");

            combo.init.setType("year");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMA001_01_form_02", "MM");

            combo.init.setType("month");
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        }
    },
    chart : {
    	chart1 : null,
    	chart2 : null,
    	chart3 : null,
    	initChart_1 : function(datas){
    		if(this.chart1) {
    			this.chart1.destroy();
            }
    		
    		var arrayData = new Array();
    		// 현재월 구하기
            var yyyymm = form.handle.getValue("MMA001_01_form_01", "YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
        	
    		// 12개월 정보로 데이터를 가공함
    		for(var m = -12; m < 0; m++) {
    			var data = new Object();
    			
    			var comDate = calendar.util.addDate2String('m', m, yyyymm, "", NATION_DATE_DB);
				comDate = calendar.util.getSepInDate2String(comDate, "", NATION_DATE_VIEW);
				comDate = comDate.substring(0, 6);
				
				var curDate = calendar.util.addDate2String('m', m, yyyymm, "/", NATION_DATE_DB);
				if(SESSION.FTA_NATION == "MX") {
					curDate = curDate.substring(3, 10);
				} else if(SESSION.FTA_NATION == "KR") {
					curDate = curDate.substring(0,7);
				} else if(SESSION.FTA_NATION == "VN") {
					curDate = curDate.substring(0,7);
				}
	            
	            if(!oUtil.isNull(datas)) {
	            	var chk = true;
	            	data.YYYYMM = curDate;
	            	
    				for(var i = 0; i < datas.length; i++) {
    					var compData = datas[i];
    					
    					if(compData.YYYYMM == comDate) {
    						data.DE_ORG_RATE = compData.DE_ORG_RATE;
    	    				data.DE_NOORG_RATE = compData.DE_NOORG_RATE;
    	    				
    	    				chk = false;
    	    				break;
    					}
    				}
    				if(chk) {
    					data.DE_ORG_RATE = 0;
        				data.DE_NOORG_RATE = 0;
    				}
    			} else {
    				data.YYYYMM = curDate;
    				data.DE_ORG_RATE = 0;
    				data.DE_NOORG_RATE = 0;
    			}
	            
	            arrayData.push(data);
    		}
    		
    		var cht = chart.getChart("MMA001_01_chart_01");
    		var jsonHeader = {TICKS: "YYYYMM", SERIES: ["DE_ORG_RATE", "DE_NOORG_RATE"]}; // TICKS:X축 라벨이 될 컬럼명, SERIES:Y축 values)
    		
    		var series = Theme.defaultBarChart(cht, 2, "in", "%");
    		
    		chart.setShowLegend(true, "w");
    		chart.setPointLabels(series, true, 0); // 라벨을 표시할지 여부
    		chart.setPointLabels(series, false, 1); // 라벨을 표시할지 여부
    		chart.setSeriesLabel(series, resource.getMessage("ORGGD, RATE"), 0);
    		chart.setSeriesLabel(series, resource.getMessage("NOORD, RATE"), 1);
    		chart.setAnimate(false);
    		chart.setLeftYMaxLabel(110);
    		chart.setLeftYMinLabel(0);
    		chart.setLeftYTickInterval(50);
    		chart.setChartBorder(false);             // border(default = false)
    		
    		this.chart1 = chart.createChart(cht, arrayData, null, jsonHeader);
    	},
    	initChart_2 : function(datas){
    		if(this.chart2) {
    			this.chart2.destroy();
            }
    		
    		var arrayData = new Array();
    		var arrayData2 = new Array();
    		// 현재월 구하기
            var yyyymm = form.handle.getValue("MMA001_01_form_01", "YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
        	
    		// 12개월 정보로 데이터를 가공함
    		for(var m = -12; m < 0; m++) {
    			var data = new Object();
    			
				var comDate = calendar.util.addDate2String('m', m, yyyymm, "", NATION_DATE_DB);
				comDate = calendar.util.getSepInDate2String(comDate, "", NATION_DATE_VIEW);
				comDate = comDate.substring(0, 6);
				
				var curDate = calendar.util.addDate2String('m', m, yyyymm, "/", NATION_DATE_DB);
				if(SESSION.FTA_NATION == "MX") {
					curDate = curDate.substring(3, 10);
				} else if(SESSION.FTA_NATION == "KR") {
					curDate = curDate.substring(0,7);
				} else if(SESSION.FTA_NATION == "VN") {
					curDate = curDate.substring(0,7);
				}
				
	            if(!oUtil.isNull(datas)) {
	            	var chk = true;
	            	data.YYYYMM = curDate;
	            	
    				for(var i = 0; i < datas.length; i++) {
    					var compData = datas[i];
    					
    					if(compData.YYYYMM == comDate) {
    						data.CO_RECEPT_TOT = compData.TCO_RECEPT_TOT;
    						if(SESSION.FTA_NATION == "VN") data.CO_RECEPT_CNT = compData.TCO_DECLARE_CNT;
    						else data.CO_RECEPT_CNT = compData.TCO_RECEPT_CNT;
    	    				data.CO_RECEPT_RATE = compData.TCO_RECEPT_RATE;
    	    				
    	    				chk = false;
    	    				break;
    					}
    				}
    				if(chk) {
    					data.CO_RECEPT_TOT = 0;
        				data.CO_RECEPT_CNT = 0;
        				data.CO_RECEPT_RATE = 0;
    				}
    			} else {
    				data.YYYYMM = curDate;
    				data.CO_RECEPT_TOT = 0;
    				data.CO_RECEPT_CNT = 0;
    				data.CO_RECEPT_RATE = 0;
    			}
	            
	            arrayData.push(data);
	            arrayData2.push(data);
    		}
    		
    		var cht = chart.getChart("MMA001_01_chart_02");
    		var jsonHeader = {TICKS: "YYYYMM", SERIES: ["CO_RECEPT_TOT", "CO_RECEPT_CNT", "CO_RECEPT_RATE"]}; // TICKS:X축 라벨이 될 컬럼명, SERIES:Y축 values) 
    		
    		var series = Theme.defaultBarChart(cht, 3, "in", "%", "int");
    		
    		chart.setAnimate(false);
    		chart.setShowLegend(true, "w");
    		chart.setSeriesColors(series, ["#a3c1d9", "#f0f000", "#4c78a8"]);
    		chart.setRendererType(series, "bar", 0);
    		chart.setRendererType(series, "bar", 1);
    		chart.setRendererType(series, "line", 2);
    		if(SESSION.FTA_NATION == "VN") {
    			chart.setSeriesLabel(series, resource.getMessage("PURSI_CNTNB")+"&nbsp;&nbsp;", 0);
    			chart.setSeriesLabel(series, resource.getMessage("DECLA_CNTNB"), 1);
    		} else {
    			chart.setSeriesLabel(series, resource.getMessage("TARGT, CNTNB")+"&nbsp;&nbsp;", 0);
    			chart.setSeriesLabel(series, resource.getMessage("RCEPT, CNTNB"), 1);
    		}
    		chart.setSeriesLabel(series, resource.getMessage("MSG_RCEPER"), 2);
    		chart.setPointLabels(series, true, 2); // 라벨을 표시할지 여부
    		chart.setRightYRender(series, 0); // 우측에 표시할 데이터 지정
    		chart.setRightYRender(series, 1); // 우측에 표시할 데이터 지정
    		chart.setRightYDrawLine(false);
    		chart.setLeftYMaxLabel(110);
    		chart.setLeftYMinLabel(0);
    		chart.setLeftYTickInterval(50);
    		chart.setChartBorder(false);             // border(default = false)
    		
    		this.chart2 = chart.createChart(cht, arrayData, null, jsonHeader);
    	},
    	initChart_3 : function(datas){
    		if(this.chart3) {
    			this.chart3.destroy();
            }
    		
    		var arrayData = new Array();
    		// 현재월 구하기
            var yyyymm = form.handle.getValue("MMA001_01_form_01", "YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
        	
    		// 12개월 정보로 데이터를 가공함
    		for(var m = -12; m < 0; m++) {
    			var data = new Object();
    			
    			var comDate = calendar.util.addDate2String('m', m, yyyymm, "", NATION_DATE_DB);
				comDate = calendar.util.getSepInDate2String(comDate, "", NATION_DATE_VIEW);
				comDate = comDate.substring(0, 6);
				
				var curDate = calendar.util.addDate2String('m', m, yyyymm, "/", NATION_DATE_DB);
				if(SESSION.FTA_NATION == "MX") {
					curDate = curDate.substring(3, 10);
				} else if(SESSION.FTA_NATION == "KR") {
					curDate = curDate.substring(0,7);
				} else if(SESSION.FTA_NATION == "VN") {
					curDate = curDate.substring(0,7);
				}
	            
	            if(!oUtil.isNull(datas)) {
	            	var chk = true;
	            	data.YYYYMM = curDate;
	            	
    				for(var i = 0; i < datas.length; i++) {
    					var compData = datas[i];
    					
    					if(compData.YYYYMM == comDate) {
    						data.TOT_TARIFF_AMOUNT = compData.TOT_TARIFF_AMOUNT;
    	    				data.TOT_FTA_TARIFF_AMOUNT = compData.TOT_FTA_TARIFF_AMOUNT;
    	    				
    	    				chk = false;
    	    				break;
    					}
    				}
    				if(chk) {
    					data.TOT_TARIFF_AMOUNT = 0;
        				data.TOT_FTA_TARIFF_AMOUNT = 0;
    				}
    			} else {
    				data.YYYYMM = curDate;
    				data.TOT_TARIFF_AMOUNT = 0;
    				data.TOT_FTA_TARIFF_AMOUNT = 0;
    			}
	            
	            arrayData.push(data);
    		}
    		
    		var cht = chart.getChart("MMA001_01_chart_03");
    		var jsonHeader = {TICKS: "YYYYMM", SERIES: ["TOT_TARIFF_AMOUNT", "TOT_FTA_TARIFF_AMOUNT"]}; // TICKS:X축 라벨이 될 컬럼명, SERIES:Y축 values)
    		
    		var series = Theme.defaultDblBarLineChart(cht, 2, "in");
    		
    		chart.setSeriesLabel(series, resource.getMessage("TXT_BASE_AMOUNT"), 0);
    		chart.setSeriesLabel(series, resource.getMessage("CSTDU, SVAMT")+"&nbsp;&nbsp;", 1);
    		chart.setAnimate(false);
    		
    		this.chart3 = chart.createChart(cht, arrayData, null, jsonHeader);
    	},
    	convertToImage : function(id) {
    		var imgData = $("#MMA001_01_chart_01").jqplotToImageStr({}); // given the div id of your plot, get the img data
    		var imgElem = $("<img/>").attr("src", imgData); // create an img and add the data to it
    		
    		//$("#MMA001_01_chart_img_01").append(imgElem);​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​
    	}
    },
    tooltip : {
        initTooltip_1 : function() {
            var obj = tooltip.getObject("MMA001_01_tooltip_1");
            
            tooltip.init.setURL("/mm/pop/mmA001_02?TARGET_PID=MMA001_01");
            
            tooltip.create(obj);
        },
        callByMMA001_02 : function(yyyymm) {
            form.handle.setValue("MMA001_01_form_01", "YYYYMM", yyyymm);
            SESSION.WORK_YYYY_MM = yyyymm;

            if($("#MMA001_01_CLOSING_YYYYMM").children().length > 0) {
                $("#MMA001_01_CLOSING_YYYYMM").html("");
            }
            $("#MMA001_01_CLOSING_YYYYMM").append("<b>• "+resource.getMessage("FNISH,MONTH")+"</b> : " + yyyymm);

            MMA001_01.control.initPageData("STEP1", true);
        }
    },
    datagrid : {
        header_1 : [
        	["POST_TYPE_NAME",   resource.getMessage("TXT_BBS_TYPE"), 60, "center", "center", true, true, null, null, null, 0, 0],
        	["SUBJECT",   resource.getMessage("TITLE"), 350, "left", "center", true, false, null, null, {format:"function", pid:"MMA001_01", name:"showNewIcon"}, 0, 0],
        	["CREATE_DATE",    resource.getMessage("TXT_CREATE_DAY"), 100, "center", "center", true, false, null, null, null, 0, 0]
        ],
        header_2 : [
        	["YYYYMM",        resource.getMessage("FNISH,MONTH"), 100, "center", "center", true, false, null, null, {format:"month"}, 2, 0],
        	["ITEM_CNT",      resource.getMessage("LTIT,CNTNB"),  110, "right", "center", true, false, null, null, {format:"int"}, 2, 0],
        	["FTA_CNT",       resource.getMessage("AGRET,CNTNB"), 110, "right", "center", true, false, null, null, {format:"int"}, 2, 0],
        	["DE_SUCC_CNT",   resource.getMessage("SUCES"), 	  110, "right", "center", true, false, null, null, {format:"int"}, 2, 0],
        	["DE_RESULT",     resource.getMessage("TXT_DETERMINE_RESULT"), 	200, "center", "center", true, false, null, null, {format:"int"}, 0, 2],
        	["DE_EXP_CNT",    resource.getMessage("DTSTA,EXPT"),  120, "right", "center", true, false, null, null, {format:"int"}, 2, 0],
        	["DE_FAIL_CNT",   resource.getMessage("FAIL"), 	      120, "right", "center", true, false, null, null, {format:"int"}, 2, 0],
        	["DE_NOVER_CNT",  resource.getMessage("NOVER"), 	  120, "right", "center", true, false, null, null, {format:"int"}, 2, 0]
        ],
        header_2_1 : [
        	["ORG_CNT",   resource.getMessage("ORGT"), 		  100, "right", "center", true, false, null, null, {format:"function", pid:"MMA001_01", name:"showOringinRate"}, 0, 0],
        	["NOORG_CNT", resource.getMessage("NOORG"), 	  100, "right", "center", true, false, null, null, {format:"function", pid:"MMA001_01", name:"showNonOringinRate"}, 0, 0]
        ],
        header_3_1 : [
        	["YYYYMM",    resource.getMessage("FNISH,MONTH"),     100, "center", "center", true, false, null, null, {format:"month"}, 2, 0],
        	["CO_RECEPT",   resource.getMessage("COVER"), 		  100, "center", "center", true, false, null, null, null, 0, 3],
        	["CN_RECEPT",   resource.getMessage("IDVDL"), 		  100, "center", "center", true, false, null, null, null, 0, 3],
        	["TCO_RECEPT",  resource.getMessage("SUM,(,COVER,+,IDVDL,)"), 	          100, "center", "center", true, false, null, null, null, 0, 4]
        ],
        header_3_2 : [
        	["CO_RECEPT_TOT",   resource.getMessage("TARGT, CNTNB"),  	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CO_RECEPT_CNT",   resource.getMessage("RCEPT, CNTNB"),    	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CO_RECEPT_RATE",  resource.getMessage("RCEPT,RATE"), 	  	100, "right", "center", true, false, null, null, null, 0, 0],
        	["CN_RECEPT_TOT",   resource.getMessage("TARGT, CNTNB"), 100, "center", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CN_RECEPT_CNT",   resource.getMessage("RCEPT, CNTNB"), 100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CN_RECEPT_RATE",  resource.getMessage("RCEPT,RATE"), 	100, "right", "center", true, false, null, null, null, 0, 0],
        	["TCO_RECEPT_TOT",   resource.getMessage("TARGT, CNTNB"), 100, "center", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["TCO_RECEPT_CNT",   resource.getMessage("RCEPT, CNTNB"), 100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["TCO_NORECEPT_CNT", resource.getMessage("NON,RCEPT, CNTNB"), 100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["TCO_RECEPT_RATE",  resource.getMessage("RCEPT,RATE"), 	100, "right", "center", true, false, null, null, null, 0, 0]
        ],
        header_3_v1 : [
        	["YYYYMM",    resource.getMessage("FNISH,MONTH"),     100, "center", "center", true, false, null, null, {format:"month"}, 2, 0],
        	["CO_RECEPT",   resource.getMessage("DOMDE"), 		  100, "center", "center", true, false, null, null, null, 0, 4],
        	["CN_RECEPT",   resource.getMessage("IMPT"), 		  100, "center", "center", true, false, null, null, null, 0, 4],
        	["TCO_RECEPT",  resource.getMessage("TTSUM"), 	          100, "center", "center", true, false, null, null, null, 0, 5]
        ],
        header_3_v2 : [
        	["CO_RECEPT_TOT",   resource.getMessage("PURSI_CNTNB"),  	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CO_DECLARE_CNT",  resource.getMessage("DECLA_CNTNB"),    	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CO_RECEPT_CNT",   resource.getMessage("RCEPT, CNTNB"),    	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CO_RECEPT_RATE",  resource.getMessage("RCEPT,RATE"), 	  	100, "right", "center", true, false, null, null, null, 0, 0],
        	["CN_RECEPT_TOT",   resource.getMessage("PURSI_CNTNB"),  	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CN_DECLARE_CNT",  resource.getMessage("DECLA_CNTNB"),    	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CN_RECEPT_CNT",   resource.getMessage("RCEPT, CNTNB"),    	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["CN_RECEPT_RATE",  resource.getMessage("RCEPT,RATE"), 	  	100, "right", "center", true, false, null, null, null, 0, 0],
        	["TCO_RECEPT_TOT",   resource.getMessage("PURSI_CNTNB"), 100, "center", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["TCO_DECLARE_CNT",  resource.getMessage("DECLA_CNTNB"),    	100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["TCO_RECEPT_CNT",   resource.getMessage("RCEPT, CNTNB"), 100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["TCO_NORECEPT_CNT", resource.getMessage("NON,RCEPT, CNTNB"), 100, "right", "center", true, false, null, null, {format:"int"}, 0, 0],
        	["TCO_RECEPT_RATE",  resource.getMessage("RCEPT,RATE"), 	100, "right", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("MMA001_01_grid_01");
            var params = new Object();
            
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.USE_YN = "Y";
            params.BBS_TYPE = "1";
            params.TARGET_PID = "MMA001_01";
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/fm/sm/sm7011_01/selectNoticeList");
            grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용

            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
        	var params = new Object();
        	var yyyymm = SESSION.WORK_YYYY_MM;
        	var startDate = calendar.util.addDate2String('m', -12, yyyymm, "", NATION_DATE_DB);
            startDate = calendar.util.getSepInDate2String(startDate, "", NATION_DATE_VIEW);
            startDate = startDate.substring(0, 6);
            
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.START_YYYYMM = startDate;
            params.YYYYMM = yyyymm;
            
            form.handle.setValue("MMA001_01_form_03", "COMPANY_CD", SESSION.COMPANY_CD);
        	form.handle.setValue("MMA001_01_form_03", "START_YYYYMM", startDate);
        	form.handle.setValue("MMA001_01_form_03", "YYYYMM", yyyymm);
        	
        	var dg_1 = grid.getObject("MMA001_01_grid_02");
        	
        	grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/selectDeterminateCount");
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            grid.init.setCallBackFunction("selectOrginRateDataOfChart");
            grid.init.setCallBackFunctionForNoData("selectOrginRateDataOfChart");
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            Theme.defaultGrid(dg_1, this.header_2, this.header_2_1);
        },
        initGrid_3 : function() {
        	var params = new Object();
        	var yyyymm = SESSION.WORK_YYYY_MM;
        	var startDate = calendar.util.addDate2String('m', -12, yyyymm, "", NATION_DATE_DB);
            startDate = calendar.util.getSepInDate2String(startDate, "", NATION_DATE_VIEW);
            startDate = startDate.substring(0, 6);
        	
        	params.COMPANY_CD = SESSION.COMPANY_CD;
            params.START_YYYYMM = startDate;
            params.YYYYMM = yyyymm;
            
            form.handle.setValue("MMA001_01_form_02", "COMPANY_CD", SESSION.COMPANY_CD);
        	form.handle.setValue("MMA001_01_form_02", "START_YYYYMM", startDate);
        	form.handle.setValue("MMA001_01_form_02", "YYYYMM", yyyymm);
        	
        	var dg_1 = grid.getObject("MMA001_01_grid_03");
        	
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/selectVendorCoCount");
            grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            grid.init.setCallBackFunction("selectVendorCoCountOfChart");
            grid.init.setCallBackFunctionForNoData("selectVendorCoCountOfChart");
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            if(SESSION.FTA_NATION == "VN") Theme.defaultGrid(dg_1, this.header_3_v1, this.header_3_v2);
            else Theme.defaultGrid(dg_1, this.header_3_1, this.header_3_2);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA001_01_grid_01 : function(rowData) {
            MMA001_01.dialog.openDialog_1('view');
        },
        eventPageView : function(data) {
        	var reqStatus = (oUtil.isNull(data.REQUEST_STATE)) ? null : data.REQUEST_STATE;
        	
        	if(oUtil.isNull(reqStatus)) {
        		MMA001_01.control.selectMainCompany();
        	}
            MMA001_01.ui.initPageVisible(data);
        },
        selectOrginRateDataOfChart : function(datas) {
        	MMA001_01.chart.initChart_1(datas.rows); // 챠트 생성
    	},
    	selectVendorCoCountOfChart : function(datas) {
        	MMA001_01.chart.initChart_2(datas.rows); // 챠트 생성
    	},
    	selectExportTariffOfChart: function(datas) {
        	MMA001_01.chart.initChart_3(datas.rows); // 챠트 생성
    	},
    	selectMainCompany : function(datas) {
    		try {
    			var bizNo = datas.rows[0].BUSINESS_NO;
    			bizNo = replaceAll(bizNo, "-", "");
    			
    			if(bizNo == "1338122303") {
    				form.util.setVisible("MMA001_01_span_03", true);
    			}
    			
    			if(bizNo == "5058108108" || bizNo == "3128162567" || bizNo == "5058138714" || 
    					bizNo == "3128620640" || bizNo == "2908100086") {
    				form.util.setVisible("MMA001_01_span_04", true);
    			}
    			
    			if(bizNo == "3128109223" || bizNo == "6068608181") {
    				form.util.setVisible("MMA001_01_span_05", true);
    			}
    			
    			if(bizNo == "5038106628") {
    				form.util.setVisible("MMA001_01_span_06", true);
    				form.util.setVisible("MMA001_01_span_07", true);
    			}
    			
//    			if(bizNo == "201883651") {
//    				form.util.setVisible("MMA001_01_span_07", true);
//    			}
    		} catch(e) { console.debug(e); }
    	},
        updateDwMonthFcrStatus : function() {
            MMA001_01.control.selectDeterminateCount();
        }
    },
    control : {// 업무구현
        selectMainList : function() {
            if (!form.handle.isValidate("MMA001_01_form_01")) return;
            
            var dg_1 = grid.getObject("MMA001_01_grid_01");
            var params = form.handle.getElementsParam("MMA001_01_form_01");

            grid.handle.sendRedirect(dg_1, "/mm/pop/MMA001_01/selectMainList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA001_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);

            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }

            MMA001_01.event.onDblClick_MMA001_01_grid_01(rowData);
        },
        initPageData : function(step, allFlag) {
        	if(step == "STEP1" || step == "STEP0") {
        		if(step != "STEP0") {
	        		MMA001_01.control.selectDeterminateCount();
	        		MMA001_01.control.selectVendorCoCount();
        		}
        		
        		setTimeout(function() {    	            
        			if(MMA001_01.authTariff == "Y") {
    	            	MMA001_01.control.selectExportTariffInfo();
    	            }
        		}, 500);
        	}
        	
        	if(allFlag) step = "";
        	
        	form.handle.setValue("MMA001_01_form_01", "REQUEST_STATE", step);
        	form.handle.setValue("MMA001_01_form_01", "START_YYYYMM", form.handle.getValue("MMA001_01_form_01", "YYYYMM"));
        	if(SESSION.FTA_NATION == "VN") {        		
        		form.handle.setValue("MMA001_01_form_01", "SCHEDULE_CD", "DAILY_BATCH");
        	}
        	
        	var obj = form.getObject("MMA001_01_form_01");

            form.init.setURL("/mm/pop/selectClosingStatus");
            form.init.setCallBackFunction("eventPageView");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectVendorCoCount : function() {
        	if(MMA001_01.chart.chart2) {
        		MMA001_01.chart.chart2.destroy();
            }
        	
        	var dg_1 = grid.getObject("MMA001_01_grid_03");
            var params = new Object();
            
        	// 현재월 구하기
            var yyyymm = form.handle.getValue("MMA001_01_form_01", "YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
            // 지정된 월의 1년전을 구하기
            var startDate = calendar.util.addDate2String('m', -12, yyyymm, "", NATION_DATE_DB);
            startDate = calendar.util.getSepInDate2String(startDate, "", NATION_DATE_VIEW);
            startDate = startDate.substring(0, 6);
            
            form.handle.setValue("MMA001_01_form_02", "COMPANY_CD", SESSION.COMPANY_CD);
        	form.handle.setValue("MMA001_01_form_02", "START_YYYYMM", startDate);
        	form.handle.setValue("MMA001_01_form_02", "YYYYMM", yyyymm);
        	
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.START_YYYYMM = startDate;
            params.YYYYMM = yyyymm;
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/selectVendorCoCount", params);
        },
        selectDeterminateCount : function() {
        	if(MMA001_01.chart.chart1) {
        		MMA001_01.chart.chart1.destroy();
            }
        	
        	var dg_1 = grid.getObject("MMA001_01_grid_02");
            var params = new Object();
            
            // 현재월 구하기
            var yyyymm = form.handle.getValue("MMA001_01_form_01", "YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
            // 지정된 월의 1년전을 구하기
            var startDate = calendar.util.addDate2String('m', -12, yyyymm, "", NATION_DATE_DB);
            startDate = calendar.util.getSepInDate2String(startDate, "", NATION_DATE_VIEW);
            startDate = startDate.substring(0, 6);
            
            form.handle.setValue("MMA001_01_form_03", "COMPANY_CD", SESSION.COMPANY_CD);
        	form.handle.setValue("MMA001_01_form_03", "START_YYYYMM", startDate);
        	form.handle.setValue("MMA001_01_form_03", "YYYYMM", yyyymm);
        	
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.START_YYYYMM = startDate;
            params.YYYYMM = yyyymm;
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/selectDeterminateCount", params);
        },
        selectExportTariffInfo : function() {
        	if(MMA001_01.chart.chart3) {
        		MMA001_01.chart.chart3.destroy();
            }
        	
        	// 현재월 구하기
            var yyyymm = form.handle.getValue("MMA001_01_form_01", "YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
            // 지정된 월의 1년전을 구하기
            var startDate = calendar.util.addDate2String('m', -12, yyyymm, "", NATION_DATE_DB);
            startDate = calendar.util.getSepInDate2String(startDate, "", NATION_DATE_VIEW);
            startDate = startDate.substring(0, 6);
            
        	form.handle.setValue("MMA001_01_form_03", "START_YYYYMM", startDate);
        	
        	var obj = form.getObject("MMA001_01_form_03");
        	
            form.init.setURL("/mm/pop/selectExportTariffInfo");
            form.init.setCallBackFunction("selectExportTariffOfChart");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectMainCompany : function() {
        	var obj = form.getObject("MMA001_01_form_02");
        	
            form.init.setURL("/mm/pop/mmA002_02/selectMainCompanyList");
            form.init.setCallBackFunction("selectMainCompany");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        updateDwMonthFcrStatus : function() {
            var dg_1 = grid.getObject("MMA001_01_grid_02");

            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var params = grid.handle.getSelectedRowData(dg_1);
            
            form.handle.setValue("MMA001_01_form_04", "YYYYMM", params.YYYYMM);
            
            var obj = form.getObject("MMA001_01_form_04");
            
            form.init.setURL("/mm/pop/updateDwMonthFcrStatus");
            form.init.setCallBackFunction("updateDwMonthFcrStatus");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        }
    },
    dialog : {
        // 다이얼로그 구현
        openDialog_1 : function(flag) {
            var dg_1 = grid.getObject("MMA001_01_grid_01");
            var dl_1 = dialog.getObject("SM7011_02_dailog_01");

            if(flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var params = grid.handle.getSelectedRowData(dg_1);
            
            if(params.POST_TYPE == "U") {
        		$("#main_win_message_1").html(params.CONTENT1);
                $("#main_win_1").window('open');
        	} else {
	            params.flag = flag;
	            params.COMPANY_CD = form.handle.getValue("MMA001_01_form_01", "COMPANY_CD");
	            params.TARGET_PID = "MMA001_01";
	
	            // 팝업 셋팅
	            dialog.init.setTitle(resource.getMessage("SM7011_02"));
	            dialog.init.setWidth(800);
	            dialog.init.setHeight(437);
	            dialog.init.setResizable(true);
	            dialog.init.setURL("/fm/sm/sm7011_02");
	            dialog.init.setQueryParams(params);
	
	            dialog.open(dl_1);
        	}
        },
        openDialog_2 : function() {
        	var params = form.handle.getElementsParam("MMA001_01_form_03");
        	var dl_1 = dialog.getObject("MMA001_04_dailog_01");
        	
        	dialog.init.setWidth(1600);
            dialog.init.setHeight(900);
            dialog.init.setResizable(true);
            dialog.init.setQueryParams(params);
            dialog.init.setURL("/mm/pop/mmA001_04");

            dialog.open(dl_1);
        }
    },
    ui : {
        initPageVisible : function(data) {
        	var flag = (oUtil.isNull(data.REQUEST_STATE)) ? null : data.REQUEST_STATE;
            
            // step-1
            if(oUtil.isNull(flag) || flag == "STEP1") {
                if($("#MMA001_01_CLOSING_YYYYMM").children().length > 0) {
                    $("#MMA001_01_CLOSING_YYYYMM").html("");
                    $("#MMA001_01_CLOSING_COMPANY").html("");
                    $("#MMA001_01_CLOSING_STATUS").html("");
                    $("#MMA001_01_CLOSING_DATE").html("");
                }

                var work_date = form.handle.getValue("MMA001_01_form_01", "YYYYMM");
                var yyyymm = calendar.util.getMonth2String(work_date, "-");

                $("#MMA001_01_CLOSING_YYYYMM").append("<b>• "+resource.getMessage("FNISH,MONTH")+"</b> : "+yyyymm);
                $("#MMA001_01_CLOSING_COMPANY").append("&nbsp;- "+resource.getMessage("CONPY")+" : "+SESSION.COMPANY_NAME);
                $("#MMA001_01_CLOSING_STATUS").append("&nbsp;- "+resource.getMessage("DPOST,STAUS")+" : " + StringHelper.null2void(data.CLOSING_STATUS));
                $("#MMA001_01_CLOSING_DATE").append("&nbsp;- "+resource.getMessage("DPOST,DATE")+" : " + StringHelper.null2void(data.CLOSING_DATE));

                form.handle.setValue("MMA001_01_form_01", "PARENT_HISTORY_ID", data.PARENT_HISTORY_ID);
            }
            // step-2
            if(oUtil.isNull(flag) || flag == "STEP2") {
                if($("#MMA001_01_IF_ITEM").children().length > 0) {
                    $("#MMA001_01_IF_ITEM").html("");
                    $("#MMA001_01_IF_ITEM_PO").html("");
                    $("#MMA001_01_IF_ITEM_INV").html("");
                    $("#MMA001_01_IF_ITEM_BOM").html("");
                    $("#MMA001_01_IF_ITEM_SALES").html("");
                }
                $("#MMA001_01_IF_ITEM").append("<b>• "+resource.getMessage("INF, STAUS")+"</b>");
                $("#MMA001_01_IF_ITEM_PO").append("&nbsp;- "+resource.getMessage("PURSI")+" : "+StringHelper.null2void(data.IF_ITEM_PO, "int"));
                if(SESSION.FTA_NATION == "VN") $("#MMA001_01_IF_ITEM_INV").append("&nbsp;- "+resource.getMessage("BKREP")+"("+resource.getMessage("PRODU")+") : "+StringHelper.null2void(data.IF_ITEM_INV, "int"));
                else $("#MMA001_01_IF_ITEM_INV").append("&nbsp;- "+resource.getMessage("BKREP")+" : "+StringHelper.null2void(data.IF_ITEM_INV, "int"));
                $("#MMA001_01_IF_ITEM_BOM").append("&nbsp;- "+resource.getMessage("BOM")+" : "+StringHelper.null2void(data.IF_ITEM_BOM, "int"));
                $("#MMA001_01_IF_ITEM_SALES").append("&nbsp;- "+resource.getMessage("SALES")+" : "+StringHelper.null2void(data.IF_ITEM_SALES, "int"));
            }
            // step-3
            if(oUtil.isNull(flag) || flag == "STEP3") {
                if($("#MMA001_01_CO_TARGET").children().length > 0) {
                    $("#MMA001_01_CO_TARGET").html("");
                    $("#MMA001_01_CO_RECEPT_TOT").html("");
                    $("#MMA001_01_CO_RECEPT_CNT").html("");
                    $("#MMA001_01_CO_RECEPT_RATE").html("");
                    $("#MMA001_01_CN_TARGET").html("");
                    $("#MMA001_01_CN_RECEPT_TOT").html("");
                    $("#MMA001_01_CN_RECEPT_CNT").html("");
                    $("#MMA001_01_CN_RECEPT_RATE").html("");
                }
                if(SESSION.FTA_NATION == "VN") $("#MMA001_01_CO_TARGET").append("<b>• "+resource.getMessage("DOMDE")+" "+resource.getMessage("LTIT")+"</b>");
                else $("#MMA001_01_CO_TARGET").append("<b>• "+resource.getMessage("TXT_RECEIVE_COVER")+"</b>");
                $("#MMA001_01_CO_RECEPT_TOT").append("&nbsp;- "+resource.getMessage("TARGT,CNTNB")+"</b> : "+StringHelper.null2void(data.CO_RECEPT_TOT, "int"));
                $("#MMA001_01_CO_RECEPT_CNT").append("&nbsp;- "+resource.getMessage("RCEPT,CNTNB")+"</b> : "+StringHelper.null2void(data.CO_RECEPT_CNT, "int"));
                $("#MMA001_01_CO_RECEPT_RATE").append("&nbsp;- "+resource.getMessage("RCEPT,RATE")+"</b> : "+StringHelper.null2void(data.CO_RECEPT_RATE, "percent")+"%");
                if(SESSION.FTA_NATION == "VN") $("#MMA001_01_CN_TARGET").append("<b>• "+resource.getMessage("IMP_LTIT")+"</b>");
                else $("#MMA001_01_CN_TARGET").append("<b>• "+resource.getMessage("TXT_RECEIVE_INDIVIDUAL")+"</b>");
                $("#MMA001_01_CN_RECEPT_TOT").append("&nbsp;- "+resource.getMessage("TARGT,CNTNB")+"</b> : "+StringHelper.null2void(data.CN_RECEPT_TOT, "int"));
                $("#MMA001_01_CN_RECEPT_CNT").append("&nbsp;- "+resource.getMessage("RCEPT,CNTNB")+"</b> : "+StringHelper.null2void(data.CN_RECEPT_CNT, "int"));
                $("#MMA001_01_CN_RECEPT_RATE").append("&nbsp;- "+resource.getMessage("RCEPT,RATE")+"</b> : "+StringHelper.null2void(data.CN_RECEPT_RATE, "percent")+"%");
                //MMA001_01.chart.initChart1();
            }
            // step-4
            if(oUtil.isNull(flag) || flag == "STEP4") {
                if($("#MMA001_01_DE_STATUS").children().length > 0) {
                    $("#MMA001_01_DE_STATUS").html("");
                    $("#MMA001_01_DE_TOT").html("");
                    $("#MMA001_01_DE_SUCC_CNT").html("");
                    $("#MMA001_01_DE_FAIL_CNT").html("");
                    $("#MMA001_01_DE_NOVER_CNT").html("");
                    $("#MMA001_01_DE_RST").html("");
                    $("#MMA001_01_DE_ORG_RATE").html("");
                    $("#MMA001_01_DE_NOORG_RATE").html("");
                }
                if(oUtil.isNull(data.DE_SUCC_CNT)) data.DE_SUCC_CNT = 0;
                if(oUtil.isNull(data.DE_FAIL_CNT)) data.DE_FAIL_CNT = 0;
                if(oUtil.isNull(data.DE_NOVER_CNT)) data.DE_NOVER_CNT = 0;
                $("#MMA001_01_DE_STATUS").append("<b>• "+resource.getMessage("DTSTA, STAUS")+"</b>");
                $("#MMA001_01_DE_TOT").append("&nbsp;- "+resource.getMessage("TARGT,CNTNB")+" : "+StringHelper.null2void((data.DE_SUCC_CNT+data.DE_FAIL_CNT+data.DE_NOVER_CNT), "int"));
                $("#MMA001_01_DE_SUCC_CNT").append("&nbsp;- "+resource.getMessage("SUCES")+" : "+StringHelper.null2void(data.DE_SUCC_CNT, "int"));
                $("#MMA001_01_DE_FAIL_CNT").append("&nbsp;- "+resource.getMessage("FAIL")+" : "+StringHelper.null2void(data.DE_FAIL_CNT, "int"));
                $("#MMA001_01_DE_NOVER_CNT").append("&nbsp;- "+resource.getMessage("NOVER")+" : "+StringHelper.null2void(data.DE_NOVER_CNT, "int"));
                $("#MMA001_01_DE_RST").append("<b>• "+resource.getMessage("DTSTA, RSULT")+"</b>");
                $("#MMA001_01_DE_ORG_RATE").append("&nbsp;- "+resource.getMessage("ORGT, RATE")+" : "+StringHelper.null2void(data.DE_ORG_RATE, "percent")+"%");
                $("#MMA001_01_DE_NOORG_RATE").append("&nbsp;- "+resource.getMessage("NOORG, RATE")+" : "+StringHelper.null2void(data.DE_NOORG_RATE, "percent")+"%");
            }
            // step-5
            if(oUtil.isNull(flag) || flag == "STEP5") {
                if($("#MMA001_01_REPORT_DMT").children().length > 0) {
                    $("#MMA001_01_REPORT_DMT").html("");
                    $("#MMA001_01_REPORT_DMT_TOT").html("");
                    $("#MMA001_01_REPORT_DMT_ISU").html("");
                    $("#MMA001_01_REPORT_DMT_RATE").html("");
                    $("#MMA001_01_REPORT_EXP").html("");
                    $("#MMA001_01_REPORT_EXP_TOT").html("");
                    $("#MMA001_01_REPORT_EXP_ISU").html("");
                    $("#MMA001_01_REPORT_EXP_RATE").html("");
                }

                if(SESSION.FTA_NATION == "VN") $("#MMA001_01_REPORT_DMT").append("<b>• "+resource.getMessage("DOMDE")+" "+resource.getMessage("LTIT")+"</b>");
                else $("#MMA001_01_REPORT_DMT").append("<b>• "+resource.getMessage("TXT_ISSUE_COVER")+"</b>");
                $("#MMA001_01_REPORT_DMT_TOT").append("&nbsp;- "+resource.getMessage("TARGT,CNTNB")+" : "+StringHelper.null2void(data.REPORT_DMT_TOT, "int"));
                $("#MMA001_01_REPORT_DMT_ISU").append("&nbsp;- "+resource.getMessage("CERIS,CNTNB")+" : "+StringHelper.null2void(data.REPORT_DMT_ISU, "int"));
                $("#MMA001_01_REPORT_DMT_RATE").append("&nbsp;- "+resource.getMessage("CERIS,RATE")+" : "+StringHelper.null2void(data.REPORT_DMT_RATE, "percent")+"%");
                if(SESSION.FTA_NATION == "VN") $("#MMA001_01_REPORT_EXP").append("<b>• "+resource.getMessage("EXP_LTIT")+"</b>");
                else $("#MMA001_01_REPORT_EXP").append("<b>• "+resource.getMessage("TXT_ISSUE_INDIVIDUAL")+"</b>");
                $("#MMA001_01_REPORT_EXP_TOT").append("&nbsp;- "+resource.getMessage("TARGT,CNTNB")+" : "+StringHelper.null2void(data.REPORT_EXP_TOT, "int"));
                $("#MMA001_01_REPORT_EXP_ISU").append("&nbsp;- "+resource.getMessage("CERIS,CNTNB")+" : "+StringHelper.null2void(data.REPORT_EXP_ISU, "int"));
                $("#MMA001_01_REPORT_EXP_RATE").append("&nbsp;- "+resource.getMessage("CERIS,RATE")+" : "+StringHelper.null2void(data.REPORT_EXP_RATE, "percent")+"%");
            }

            if(oUtil.isNull(investigateId)) {
                tags.setVisible("MMA001_01_invest_none", true);
                tags.setVisible("MMA001_01_invest_view", false);
            } else {
                tags.setVisible("MMA001_01_invest_none", false);
                tags.setVisible("MMA001_01_invest_view", true);
            }
        },
        showOringinRate : function(value, row, index) {
        	return formatFloat(row.DE_ORG_RATE, 2) + "%";
        },
        showNonOringinRate : function(value, row, index) {
        	return formatFloat(row.DE_NOORG_RATE, 2) + "%";
        },
        pageDeterMove : function() {
        	MM0001_01.control.go_page("/fm/od/od4201_01","","OD-4001", resource.getMessage("TXT_COO_DETERMIN, MGT"));
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
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA001_01_grid_02");
            var fm_1 = form.getObject("MMA001_01_form_03");
            
            form.init.setURL("/mm/pop/selectDeterminateCount.fle");
            
            form.excelSubmit(dg_1, fm_1, resource.getMessage("TXT_COO_DETERMIN, RSULT"), resource.getMessage("LIST"));
        },
        excelDownload_2 : function() {
            var dg_1 = grid.getObject("MMA001_01_grid_03");
            var fm_1 = form.getObject("MMA001_01_form_02");
            
            form.init.setURL("/mm/pop/selectVendorCoCount.fle");
            
            form.excelSubmit(dg_1, fm_1, resource.getMessage("SFSCO"), resource.getMessage("LIST"));
        }
    },
    config : {
    	applyFtaNation : function() {
            if(SESSION.FTA_NATION == "KR") {
    			form.util.setVisible("divKoreaSiteLink", true);
    			form.util.setVisible("MMA001_01_div_01", true);
    			
    			$("#MMA001_01_div_01").css("height", "46px");
    			$("#MMA001_01_div_01").css("padding-top", "2px");
    		} else if(SESSION.FTA_NATION == "VN") {
    			form.util.setVisible("divVietnamSiteLink", true);
    			form.util.setVisible("MMA001_01_div_01", true);
    			
    			$("#MMA001_01_div_01").css("height", "46px");
    			$("#MMA001_01_div_01").css("padding-top", "2px");
    		} else {
    			$("#MMA001_01_div_01").css("height", "0px");
    			$("#MMA001_01_div_01").css("padding-top", "0px");
    		}
    		
    		if(form.handle.getValue("MMA001_01_form_01", "CUSTOMER_CO_TARGET") == "1") {
    			form.util.setVisible("MMA001_01_span_01", true);
    			form.util.setVisible("MMA001_01_span_02", true);
    		}
    		
    		if(MMA001_01.authTariff == "Y") {
    			$("#MMA001_01_div_02").css("height", "210px");
    			$("#MMA001_01_div_02").css("padding-bottom", "10px");
    			$("#MMA001_01_div_02").css("display", "online");
    			$("#MMA001_01_div_03").css("height", "630px");
    		} else {
    			$("#MMA001_01_div_02").css("height", "0px");
    			$("#MMA001_01_div_02").css("padding-bottom", "0px");
    			$("#MMA001_01_div_02").css("display", "none");
    			$("#MMA001_01_div_03").css("height", "100%");
    		}
            
            // 원격지원서비스 표시여부(2022-05-02)
            if(form.handle.getValue("MMA001_01_form_01", "REMOTE_SERVICE_YN") == "Y") {
                if(SESSION.FTA_NATION == "KR") {
                    $("#MMA001_01_img_01").attr("src", "/images/main/remote_desktop.png");
                } else {
                    $("#MMA001_01_img_01").attr("src", "/images/main/remote_desktop_en_logo.png");
                }
                 
                form.util.setVisible("MMA001_01_span_08", true);
            }
    	}
    }

};
