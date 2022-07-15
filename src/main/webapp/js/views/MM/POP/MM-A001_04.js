/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A001_04
 * 작업일자 : 2018.02.16
******************************************************************************************/

function onLoadPage() {
	MMA001_04.config.applyFtaNation();
    MMA001_04.init.initComponent();
}

var MMA001_04 = {

    init : {
    	aryCnt : -12,
        initComponent : function() {
        	MMA001_04.calendar.initCalendar_1();
        	MMA001_04.calendar.initCalendar_2();
        	
        	MMA001_04.combobox.initCombo_1();
        	MMA001_04.combobox.initCombo_2();
        	
        	MMA001_04.datagrid.initGrid_4();
        	setTimeout(function(){ MMA001_04.datagrid.initGrid_1(); }, 100);
        	setTimeout(function(){ MMA001_04.datagrid.initGrid_3(); }, 200);
        	setTimeout(function(){ MMA001_04.datagrid.initGrid_2(); }, 300);
        	setTimeout(function(){ MMA001_04.datagrid.initGrid_5(); }, 400);
        }
    }, // 초기값 설정
    calendar : {
    	initCalendar_1 : function() {
    		var yyyymm = (oUtil.isNull(SESSION.WORK_YYYY_MM))?calendar.util.toMonth2String():(SESSION.WORK_YYYY_MM);
            var date = calendar.util.getFirstDay2String(yyyymm);
            var obj_01 = calendar.getObject("MMA001_04_form_01", "YYYYMM_CAL");              
            
            calendar.init.setDateType("month");
            calendar.init.setInitDate(date);
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	var yyyymm = form.handle.getValue("MMA001_04_form_01", "START_YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
        	
    		var obj_01 = calendar.getObject("MMA001_04_form_01", "START_YYYYMM_CAL");

            calendar.init.setDateType("month");
            calendar.init.setInitDate(yyyymm);
            
            calendar.create(obj_01);
        }
    }, // 달력 그리드 생성
    combobox : {
		initCombo_1 : function() {
            var obj = combo.getObject("MMA001_04_form_01", "FTA_CD");
            
            combo.init.setURL("/mm/cbox/selectFta");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMA001_04_form_01", "DIVISION_CD");

            combo.init.setURL("/mm/cbox/selectDivision");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        }
    },
    chart : {
    	chart1 : null,
    	chart2 : null,
    	chart3 : null,
    	chart4 : null,
    	initChart_1 : function(datas){
    		if(this.chart1) {
    			this.chart1.destroy();
            }
    		
    		var arrayData = new Array();
    		// 현재월 구하기
            var yyyymm = form.handle.getValue("MMA001_04_form_01", "YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
        	
    		// 12개월 정보로 데이터를 가공함
    		for(var m = MMA001_04.init.aryCnt; m < 0; m++) {
    			var data = new Object();
    			
    			var comDate = calendar.util.addDate2String('m', m, yyyymm, "", NATION_DATE_DB);
				comDate = calendar.util.getSepInDate2String(comDate, "", NATION_DATE_VIEW);
				comDate = comDate.substring(0, 6);
				
				var curDate = calendar.util.addDate2String('m', m, yyyymm, "/", NATION_DATE_DB);
				if(SESSION.FTA_NATION == "MX") {
					curDate = curDate.substring(3, 10);
				} else if(SESSION.FTA_NATION == "KR") {
					curDate = curDate.substring(0,7);
				}
	            
	            if(!oUtil.isNull(datas)) {
	            	var chk = true;
	            	data.YYYYMM = curDate;
	            	
    				for(var i = 0; i < datas.length; i++) {
    					var compData = datas[i];
    					
    					if(compData.YYYYMM == comDate) {
    						data.TOT_FTA_TARIFF_AMOUNT = compData.TOT_FTA_TARIFF_AMOUNT;
    						data.TOT_TARIFF_AMOUNT = compData.TOT_TARIFF_AMOUNT;
    	    				
    	    				chk = false;
    	    				break;
    					}
    				}
    				if(chk) {
    					data.TOT_FTA_TARIFF_AMOUNT = 0;
    					data.TOT_TARIFF_AMOUNT = 0;
    				}
    			} else {
    				data.YYYYMM = curDate;
    				data.TOT_FTA_TARIFF_AMOUNT = 0;
    				data.TOT_TARIFF_AMOUNT = 0;
    			}
	            
	            arrayData.push(data);
    		}
    		
    		var cht = chart.getChart("MMA001_04_chart_01");
    		
    		// 줌, tooltip 기능 설정(원형 그래프에서는 적용되는지 않는기능임)
            var cursor = chart.getCursor(cht);
            chart.setZoom(cursor, true);
            chart.setTooltip(cursor, false);
            
    		var series = chart.getSeries(cht, 2, "int");
    		chart.setRendererType(series, "bar", 0);
    		chart.setRendererType(series, "line", 1);
    		chart.setSeriesColors(series, ["#c3c3c3", "#0080FF"]);
    		chart.setSeriesLabel(series, resource.getMessage("TXT_BASE_AMOUNT"), 0);
    		chart.setSeriesLabel(series, resource.getMessage("CSTDU, SVAMT"), 1);
    		
    		chart.setAnimate(false);
    		chart.setShowLegend(true, "ne");
    		chart.setChartShadow(true);
    		chart.setChartLineWidth(0.5);
    		chart.setBottomXDrawLine(false);
    		chart.setLeftYFormat("int");
    		
    		var jsonHeader = {TICKS: "YYYYMM", SERIES: ["TOT_TARIFF_AMOUNT","TOT_FTA_TARIFF_AMOUNT"]}; // TICKS:X축 라벨이 될 컬럼명, SERIES:Y축 values)
    		
    		this.chart1 = chart.createChart(cht, arrayData, null, jsonHeader);
    	},
    	initChart_2 : function(){
    		if(this.chart2) {
    			this.chart2.destroy();
            }
    		
    		var cht = chart.getChart("MMA001_04_chart_02");
    		
    		// 줌, tooltip 기능 설정(원형 그래프에서는 적용되는지 않는기능임)
            var cursor = chart.getCursor(cht);
            chart.setZoom(cursor, true);
            chart.setTooltip(cursor, false);
            
    		var series = chart.getSeries(cht, 2, "int");
    		chart.setRendererType(series, "bar", 0);
    		chart.setRendererType(series, "bar", 1);
    		chart.setSeriesColors(series, ["#00749F", "#DF3A01"]);
    		chart.setSeriesLabel(series, resource.getMessage("TXT_BASE_AMOUNT"), 0);
    		chart.setSeriesLabel(series, resource.getMessage("CSTDU, SVAMT"), 1);
    		
    		chart.setAnimate(true);
    		chart.setShowLegend(true, "ne");
    		chart.setChartShadow(true);
    		chart.setChartLineWidth(0.5);
    		chart.setBottomXTitle("[ Date : "+form.handle.getValue("MMA001_04_form_01", "START_YYYYMM")+" ~ "+form.handle.getValue("MMA001_04_form_01", "YYYYMM")+" ]")
    		chart.setBottomXAngle(-15);
    		chart.setBottomXDrawLine(false);
    		chart.setLeftYFormat("int");
    		
    		var jsonHeader = {TICKS: "NATION_NAME", SERIES: ["TOT_TARIFF_AMOUNT", "TOT_FTA_TARIFF_AMOUNT"]};
    		var params = form.handle.getElementsParam("MMA001_04_form_01");
    		params.TARIFF_STATUS_TYPE = "NC";
    		
    		this.chart2 = chart.createChart(cht, "/mm/pop/selectExportTariffInfo", params, jsonHeader);
    	},
    	initChart_3 : function(datas){
    		if(this.chart3) {
    			this.chart3.destroy();
            }
    		
    		var arrayDatas = new Array(); // 여러 항목의 데이터를 담는 배열
    		var arraySeries = new Array(); // 각 항목의 시리즈별 값을 구분하는 명칭을 담은 배열
    		var arrayCondition = new Array(); // 항목 그룹을 담은 배열
    		// 현재월 구하기
            var yyyymm = form.handle.getValue("MMA001_04_form_01", "YYYYMM");
        	yyyymm = calendar.util.getSepInDate2String(yyyymm, "", NATION_DATE_VIEW);
        	yyyymm = yyyymm.substring(0, 6);
        	
        	var divisionCd = null;
        	for(var d = 0; d < datas.length; d++) {
        		var divisionTemp = datas[d].DIVISION_NAME;
        		
        		if(divisionCd == null) {
        			divisionCd = divisionTemp;
        			arrayCondition.push(divisionTemp);
        		} else {
        			if(!oUtil.isNull(divisionTemp) && divisionCd != divisionTemp) {
        				divisionCd = divisionTemp;
            			arrayCondition.push(divisionTemp);
        			}
        		}
        	}
        	
        	for(var d = 0; d < arrayCondition.length; d++) {
        		var arrayData = new Array();
        		
        		// 12개월 정보로 데이터를 가공함
	    		for(var m = MMA001_04.init.aryCnt; m < 0; m++) {
	    			var data = new Object();
	    			
	    			var comDate = calendar.util.addDate2String('m', m, yyyymm, "", NATION_DATE_DB);
					comDate = calendar.util.getSepInDate2String(comDate, "", NATION_DATE_VIEW);
					comDate = comDate.substring(0, 6);
					
					var curDate = calendar.util.addDate2String('m', m, yyyymm, "/", NATION_DATE_DB);
					if(SESSION.FTA_NATION == "MX") {
						curDate = curDate.substring(3, 10);
					} else if(SESSION.FTA_NATION == "KR") {
						curDate = curDate.substring(0,7);
					}
		            
		            if(!oUtil.isNull(datas)) {
		            	var chk = true;
		            	data.YYYYMM = curDate;
		            	
	    				for(var i = 0; i < datas.length; i++) {
	    					var compData = datas[i];
	    					
	    					if(compData.YYYYMM == comDate && arrayCondition[d] == datas[i].DIVISION_NAME) {
	    						data.TOT_FTA_TARIFF_AMOUNT = compData.TOT_FTA_TARIFF_AMOUNT;
	    	    				
	    	    				chk = false;
	    	    				break;
	    					}
	    				}
	    				if(chk) {
	    					data.TOT_FTA_TARIFF_AMOUNT = 0;
	    				}
	    			} else {
	    				data.YYYYMM = curDate;
	    				data.TOT_FTA_TARIFF_AMOUNT = 0;
	    			}
		            
		            arrayData.push(data);
	    		}
	    		
	    		arraySeries.push("TOT_FTA_TARIFF_AMOUNT");
	    		arrayDatas.push(arrayData);
        	}
        	
    		var cht = chart.getChart("MMA001_04_chart_03");
    		
    		// 줌, tooltip 기능 설정(원형 그래프에서는 적용되는지 않는기능임)
            var cursor = chart.getCursor(cht);
            chart.setZoom(cursor, true);
            chart.setTooltip(cursor, false);
            
    		var jsonHeader = {TICKS: "YYYYMM", SERIES: arraySeries}; // TICKS:X축 라벨이 될 컬럼명, SERIES:Y축 values)
    		
    		var series = chart.getSeries(cht, arrayCondition.length);
    		
    		for(var d = 0; d < arrayCondition.length; d++) {
    			chart.setSeriesLabel(series, arrayCondition[d], d);
    			chart.setRendererType(series, "line", d);
		        chart.setBarMargin(series, 0, d);          // 막대 그래프간 공백 설정(default=0px)
		        chart.setBarWidth(series, 15, d);          // 막대 그래프의 넓이 설정(default=15px)
		        chart.setBarPadding(series, d);            // 막대 그래프간 여백 설정(default=0px)
		        chart.setAnimationTime(series, 1000, d);   // 애니메이션 표시 속도
		        chart.setShadow(series, true, d);         // 그림자 표시여부(default=false)
    		}
    		
    		chart.setAnimate(false);
    		chart.setChartShadow(true);
    		chart.setChartLineWidth(1);
    		chart.setBottomXDrawLine(false);
    		chart.setLeftYFormat("int");
    		
    		this.chart3 = chart.createChart(cht, arrayDatas, null, jsonHeader);
    	},
    	initChart_4 : function(){
    		if(this.chart4) {
    			this.chart4.destroy();
            }
    		
    		var cht = chart.getChart("MMA001_04_chart_05");
    		
    		// 줌, tooltip 기능 설정(원형 그래프에서는 적용되는지 않는기능임)
            var cursor = chart.getCursor(cht);
            chart.setZoom(cursor, true);
            chart.setTooltip(cursor, false);
            
    		var series = chart.getSeries(cht, 2, "int");
    		chart.setRendererType(series, "bar", 0);
    		chart.setRendererType(series, "bar", 1);
    		chart.setSeriesColors(series, ["#00749F", "#DF3A01"]);
    		chart.setSeriesLabel(series, resource.getMessage("TXT_BASE_AMOUNT"), 0);
    		chart.setSeriesLabel(series, resource.getMessage("CSTDU, SVAMT"), 1);
    		
    		chart.setAnimate(true);
    		chart.setShowLegend(true, "ne");
    		chart.setChartShadow(true);
    		chart.setChartLineWidth(0.5);
    		chart.setBottomXTitle("[ Date : "+form.handle.getValue("MMA001_04_form_01", "START_YYYYMM")+" ~ "+form.handle.getValue("MMA001_04_form_01", "YYYYMM")+" ]")
    		chart.setBottomXAngle(-15);
    		chart.setBottomXDrawLine(false);
    		chart.setLeftYFormat("int");
    		
    		var jsonHeader = {TICKS: "CUSTOMER_NAME", SERIES: ["TOT_TARIFF_AMOUNT", "TOT_FTA_TARIFF_AMOUNT"]};
    		var params = form.handle.getElementsParam("MMA001_04_form_01");
    		params.TARIFF_STATUS_TYPE = "E";
    		
    		this.chart4 = chart.createChart(cht, "/mm/pop/selectExportTariffInfo", params, jsonHeader);
    	}
    },
    tooltip : {},
    datagrid : {
        header_1 : [
        	["COMPANY_NAME"     	,resource.getMessage("CONPY")                ,130    ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["YYYYMM"            	,resource.getMessage("TXT_CLOS_MON")         ,100    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,{format:"month"}],
			["TOT_AMOUNT"           ,resource.getMessage("EXPS")                 ,130    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["COMPANY_CO_RATE"      ,"A."+resource.getMessage("ORGGD,RATE")      ,120    ,"right"       ,"center"    ,true       ,false    ,null         ,null     ,{format:"percent", precision:2}],
        	["TOT_TARIFF_AMOUNT"    ,"B."+resource.getMessage("TXT_BASE_AMOUNT,AMUNT") ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}, 0, 0, null, "background-color:#c3c3c3;font-weight:bold;"],
			["TOT_FTA_TARIFF"       ,"C."+resource.getMessage("TXT_AGREEMENT_AMOUNT,AMUNT") ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_ORGIN_TARIFF_AMOUNT"  ,"D."+resource.getMessage("PAY,CSTDU,AMUNT")+"<br>(A*C)+((1-A)*B)"       ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_FTA_TARIFF_AMOUNT","E."+resource.getMessage("CSTDU,REDCN,AMUNT")+"<br>(B-D)"    ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}, 0, 0, null, "background-color:#0080FF;color:#fff;font-weight:bold;"]
        ],
        header_2 : [
        	["YYYYMM"            	,resource.getMessage("TXT_CLOS_MON")    ,100    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,{format:"month"}],
			["TOT_AMOUNT"           ,resource.getMessage("EXPS")            ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["NATION_NAME"          ,resource.getMessage("COTRY,NAME")      ,110    ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["FTA_CD"               ,resource.getMessage("AGRET,CODE")      ,90     ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["FTA_NAME"             ,resource.getMessage("AGRET,NAME")      ,100    ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["COMPANY_CO_RATE"      ,"A."+resource.getMessage("ORGGD,RATE")      ,120    ,"right"       ,"center"    ,true       ,false    ,null         ,null     ,{format:"percent", precision:2}],
        	["TOT_TARIFF_AMOUNT"    ,"B."+resource.getMessage("TXT_BASE_AMOUNT,AMUNT") ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}, 0, 0, null, "background-color:#00749F;color:#fff;font-weight:bold;"],
			["TOT_FTA_TARIFF"       ,"C."+resource.getMessage("TXT_AGREEMENT_AMOUNT,AMUNT") ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_ORGIN_TARIFF_AMOUNT"  ,"D."+resource.getMessage("PAY,CSTDU,AMUNT")+"<br>(A*C)+((1-A)*B)"       ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_FTA_TARIFF_AMOUNT","E."+resource.getMessage("CSTDU,REDCN,AMUNT")+"<br>(B-D)"    ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}, 0, 0, null, "background-color:#DF3A01;color:#fff;font-weight:bold;"]
        ],
        header_3 : [
        	["DIVISION_NAME"     	,resource.getMessage("DIVIS")           ,130    ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["YYYYMM"            	,resource.getMessage("TXT_CLOS_MON")    ,100    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,{format:"month"}],
			["TOT_AMOUNT"           ,resource.getMessage("EXPS")            ,150    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["COMPANY_CO_RATE"      ,"A."+resource.getMessage("ORGGD,RATE")      ,120    ,"right"       ,"center"    ,true       ,false    ,null         ,null     ,{format:"percent", precision:2}],
        	["TOT_TARIFF_AMOUNT"    ,"B."+resource.getMessage("TXT_BASE_AMOUNT,AMUNT") ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}, 0, 0, null],
			["TOT_FTA_TARIFF"       ,"C."+resource.getMessage("TXT_AGREEMENT_AMOUNT,AMUNT") ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_ORGIN_TARIFF_AMOUNT" ,"D."+resource.getMessage("PAY,CSTDU,AMUNT")+"<br>(A*C)+((1-A)*B)"       ,140    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_FTA_TARIFF_AMOUNT","E."+resource.getMessage("CSTDU,REDCN,AMUNT")+"<br>(B-D)"    ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}, 0, 0, null, "background-color:#0080FF;color:#fff;font-weight:bold;"]
        ],
        header_4 : [
        	["DIVISION_NAME"     	,resource.getMessage("DIVIS")           ,130    ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["PRODUCT_ITEM_CD"      ,resource.getMessage("PRDT, CODE")      ,100    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null],
			["PRODUCT_ITEM_NAME"    ,resource.getMessage("PRDT,NAME")       ,120    ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
			["HS_CODE"              ,resource.getMessage("HS,CODE")         ,100    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null],
			["EXPORT_HS_CODE"       ,resource.getMessage("AGRET,COTRY, HS,CODE")    ,100    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null],
			["NATION_NAME"          ,resource.getMessage("COTRY,NAME")      ,110    ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["FTA_CD"               ,resource.getMessage("AGRET,CODE")      ,90     ,"center"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["FTA_NAME"             ,resource.getMessage("AGRET,NAME")      ,100    ,"left"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["EC_RATE"              ,resource.getMessage("CTRAT")           ,110    ,"right"       ,"center"    ,true       ,false    ,null         ,null     ,{format:"percent", precision:2}],
        	["COMPANY_CO_RATE"      ,"A."+resource.getMessage("ORGGD,RATE")      ,120    ,"right"       ,"center"    ,true       ,false    ,null         ,null     ,{format:"percent", precision:2}],
        	["TOT_AMOUNT"           ,"B."+resource.getMessage("EXPS")            ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
        	["TARIFF_RATE"          ,"C."+resource.getMessage("TXT_BASE_AMOUNT,RATE")      ,100    ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["TOT_TARIFF_AMOUNT"    ,"D."+resource.getMessage("TXT_BASE_AMOUNT,AMUNT")+"<br>B*(C/100)" ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
        	["FTA_TARIFF_RATE"      ,"E."+resource.getMessage("TXT_AGREEMENT_AMOUNT,RATE")      ,100    ,"right"     ,"center"    ,true       ,false    ,null         ,null     ,null],
			["TOT_FTA_TARIFF"       ,"F."+resource.getMessage("TXT_AGREEMENT_AMOUNT,AMUNT")+"<br>B*(E/100)" ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_ORGIN_TARIFF_AMOUNT"  ,"G."+resource.getMessage("PAY,CSTDU,AMUNT")+"<br>(A*F)+((1-A)*D)"       ,140    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_FTA_TARIFF_AMOUNT","H."+resource.getMessage("CSTDU,REDCN,AMUNT")+"<br>(D-G)"    ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_TARGET_TARIFF_AMOUNT" ,"I."+resource.getMessage("GOAL,CSTDU,AMUNT")+"<br>(G-F)"  ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TARIFF_TYPE_NAME"     ,resource.getMessage("TRAFF,TYPE")      ,110    ,"center"     ,"center"    ,true       ,false    ,null         ,null     ,null]
        ],
        header_5 : [
        	["CUSTOMER_CD"     	    ,resource.getMessage("TXT_OVERSEA_CORP, CODE")           ,100    ,"center"       ,"center"    ,true       ,false    ,null         ,null     ,null],
        	["CUSTOMER_NAME"        ,resource.getMessage("TXT_OVERSEA_CORP, NAME")    ,160    ,"left"     ,"center"    ,true       ,false    ,null         ,null     ,null],
			["TOT_AMOUNT"           ,resource.getMessage("EXPS")            ,150    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["COMPANY_CO_RATE"      ,"A."+resource.getMessage("ORGGD,RATE")      ,120    ,"right"       ,"center"    ,true       ,false    ,null         ,null     ,{format:"percent", precision:2}],
        	["TOT_TARIFF_AMOUNT"    ,"B."+resource.getMessage("TXT_BASE_AMOUNT,AMUNT") ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}, 0, 0, null],
			["TOT_FTA_TARIFF"       ,"C."+resource.getMessage("TXT_AGREEMENT_AMOUNT,AMUNT") ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_ORGIN_TARIFF_AMOUNT" ,"D."+resource.getMessage("PAY,CSTDU,AMUNT")+"<br>(A*C)+((1-A)*B)"       ,140    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}],
			["TOT_FTA_TARIFF_AMOUNT","E."+resource.getMessage("CSTDU,REDCN,AMUNT")+"<br>(B-D)"    ,120    ,"right"      ,"center"    ,true       ,false    ,null         ,null     ,{format:"amount"}, 0, 0, null, "background-color:#DF3A01;color:#fff;font-weight:bold;"]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA001_04_form_01");
        	
        	params.TARIFF_STATUS_TYPE = "C";
        	
            var dg_1 = grid.getObject("MMA001_04_grid_01");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/selectExportTariffInfo");
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setCallBackFunction("selectTariff4CompnayOfGrid");
            grid.init.setCallBackFunctionForNoData("selectTariff4CompnayOfGrid");
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
        	var params = form.handle.getElementsParam("MMA001_04_form_01");
        	
        	params.TARIFF_STATUS_TYPE = "N";
        	
            var dg_1 = grid.getObject("MMA001_04_grid_02");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/selectExportTariffInfo");
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(true);
            grid.init.setMergeData(["YYYYMM", "TOT_AMOUNT", "NATION_NAME"],["NATION_NAME"]);
            grid.init.setCallBackFunction("selectTariff4NationOfGrid");
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_2);
        },
        initGrid_3 : function() {
        	var params = form.handle.getElementsParam("MMA001_04_form_01");
        	
        	params.TARIFF_STATUS_TYPE = "D";
        	
            var dg_1 = grid.getObject("MMA001_04_grid_03");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/selectExportTariffInfo");
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setCallBackFunction("selectTariff4DivisionOfGrid");
            grid.init.setCallBackFunctionForNoData("selectTariff4DivisionOfGrid");
            
            Theme.defaultGrid(dg_1, this.header_3);
        },
        initGrid_4 : function() {
        	var params = form.handle.getElementsParam("MMA001_04_form_01");
        	
        	params.TARIFF_STATUS_TYPE = "I";
        	
            var dg_1 = grid.getObject("MMA001_04_grid_04");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/selectExportTariffInfo");
            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(true);
            grid.init.setMergeData(["DIVISION_NAME", "PRODUCT_ITEM_CD", "PRODUCT_ITEM_NAME", "HS_CODE", "EXPORT_HS_CODE", "TOT_AMOUNT", "NATION_NAME"],["PRODUCT_ITEM_CD", "NATION_NAME"]);
            
            Theme.defaultGrid(dg_1, this.header_4);
        },
        initGrid_5 : function() {
        	var params = form.handle.getElementsParam("MMA001_04_form_01");
        	
        	params.TARIFF_STATUS_TYPE = "E";
        	
            var dg_1 = grid.getObject("MMA001_04_grid_05");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/selectExportTariffInfo");
            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setMergeMode(true);
            grid.init.setMergeData(["CUSTOMER_CD", "CUSTOMER_NAME"],"CUSTOMER_CD");
            grid.init.setCallBackFunction("selectTariff5CustomerOfGrid");
            grid.init.setCallBackFunctionForNoData("selectTariff5CustomerOfGrid");
            
            Theme.defaultGrid(dg_1, this.header_5);
        }
    },
    event : { // 이벤트 처리
    	selectTariff4CompnayOfGrid : function(datas) {
    		MMA001_04.chart.initChart_1(datas.rows);
    	},
    	selectTariff4NationOfGrid : function(datas) {
    		MMA001_04.chart.initChart_2();
    	},
    	selectTariff4DivisionOfGrid : function(datas) {
    		MMA001_04.chart.initChart_3(datas.rows);
    	},
    	selectTariff5CustomerOfGrid : function(datas) {
    		MMA001_04.chart.initChart_4();
    	}
    },
    control : {// 업무구현
    	selectExportTariffInfo : function() {
            if (!form.handle.isValidate("MMA001_04_form_01")) return;
            
            var params = form.handle.getElementsParam("MMA001_04_form_01");
            
            var yyyymm = params.YYYYMM;
            
            var coFrom = calendar.handle.getDate("MMA001_04_form_01", "START_YYYYMM_CAL");
    		var coTo = calendar.handle.getDate("MMA001_04_form_01", "YYYYMM_CAL");
    		
    		MMA001_04.init.aryCnt = calendar.util.betweenMonth2Number(coTo, coFrom)-1;
    		
    		if(MMA001_04.chart.chart1) MMA001_04.chart.chart1.destroy();
    		if(MMA001_04.chart.chart2) MMA001_04.chart.chart2.destroy();
    		if(MMA001_04.chart.chart3) MMA001_04.chart.chart3.destroy();
    		if(MMA001_04.chart.chart4) MMA001_04.chart.chart4.destroy();
            
        	this.selectExportTariffType(params, "I", "MMA001_04_grid_04");
        	setTimeout(function(){ MMA001_04.control.selectExportTariffType(params, "C", "MMA001_04_grid_01"); }, 100);
            setTimeout(function(){ MMA001_04.control.selectExportTariffType(params, "D", "MMA001_04_grid_03"); }, 200);
            setTimeout(function(){ MMA001_04.control.selectExportTariffType(params, "N", "MMA001_04_grid_02"); }, 300);
            setTimeout(function(){ MMA001_04.control.selectExportTariffType(params, "E", "MMA001_04_grid_05"); }, 400);
        },
        selectExportTariffType : function(params, type, gridId) {
        	var dg_1 = grid.getObject(gridId);
            
            params.TARIFF_STATUS_TYPE = type;
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/selectExportTariffInfo", params);
        }
    },
    dialog : {},
    ui : {
    	changeTableNChart : function(num) {
    		if(num == "1") {
    			form.util.setVisible("MMA001_04_view1_01", false);
    			form.util.setVisible("MMA001_04_view1_02", true);
    		}
    	}
    },
    file : {},
    excel : {
    	excelDownload_1 : function() {
    		var jsonData = form.handle.getElementsParam("MMA001_04_form_01");
    		
    		form.handle.loadData("MMA001_04_form_02", jsonData);
    		
            var dg_1 = grid.getObject("MMA001_04_grid_01");
            var fobj = form.getObject("MMA001_04_form_02");
            
            form.init.setURL("/mm/pop/selectExportTariffInfo.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("CONPY, SVAMT, LIST")+"_"+"(Date:"+form.handle.getValue("MMA001_04_form_01", "START_YYYYMM")+"-"+form.handle.getValue("MMA001_04_form_01", "YYYYMM")+")", resource.getMessage("LIST"));
        },
        excelDownload_2 : function() {
    		var jsonData = form.handle.getElementsParam("MMA001_04_form_01");
    		
    		form.handle.loadData("MMA001_04_form_03", jsonData);
    		
            var dg_1 = grid.getObject("MMA001_04_grid_02");
            var fobj = form.getObject("MMA001_04_form_03");
            
            form.init.setURL("/mm/pop/selectExportTariffInfo.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("CNTES, SVAMT, LIST")+"_"+"(Date:"+form.handle.getValue("MMA001_04_form_01", "START_YYYYMM")+"-"+form.handle.getValue("MMA001_04_form_01", "YYYYMM")+")", resource.getMessage("LIST"));
        },
        excelDownload_3 : function() {
    		var jsonData = form.handle.getElementsParam("MMA001_04_form_01");
    		
    		form.handle.loadData("MMA001_04_form_04", jsonData);
    		
            var dg_1 = grid.getObject("MMA001_04_grid_03");
            var fobj = form.getObject("MMA001_04_form_04");
            
            form.init.setURL("/mm/pop/selectExportTariffInfo.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("BYDIV, SVAMT, LIST")+"_"+"(Date:"+form.handle.getValue("MMA001_04_form_01", "START_YYYYMM")+"-"+form.handle.getValue("MMA001_04_form_01", "YYYYMM")+")", resource.getMessage("LIST"));
        },
        excelDownload_4 : function() {
    		var jsonData = form.handle.getElementsParam("MMA001_04_form_01");
    		
    		form.handle.loadData("MMA001_04_form_05", jsonData);
    		
            var dg_1 = grid.getObject("MMA001_04_grid_04");
            var fobj = form.getObject("MMA001_04_form_05");
            
            form.init.setURL("/mm/pop/selectExportTariffInfo.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("BYPRD, SVAMT, LIST")+"_"+"(Date:"+form.handle.getValue("MMA001_04_form_01", "START_YYYYMM")+"-"+form.handle.getValue("MMA001_04_form_01", "YYYYMM")+")", resource.getMessage("LIST"));
        },
        excelDownload_5 : function() {
    		var jsonData = form.handle.getElementsParam("MMA001_04_form_01");
    		
    		form.handle.loadData("MMA001_04_form_06", jsonData);
    		
            var dg_1 = grid.getObject("MMA001_04_grid_05");
            var fobj = form.getObject("MMA001_04_form_06");
            
            form.init.setURL("/mm/pop/selectExportTariffInfo.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("TXT_OVERSEA_CORP, SVAMT, LIST")+"_"+"(Date:"+form.handle.getValue("MMA001_04_form_01", "START_YYYYMM")+"-"+form.handle.getValue("MMA001_04_form_01", "YYYYMM")+")", resource.getMessage("LIST"));
        }
    },
    config : {
    	applyFtaNation : function() {}
    }

};
