/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A001_07
 * 작업일자 : 2016.09.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA001_07.config.applyFtaNation();
    MMA001_07.init.initComponent();
}

var MMA001_07_sqlcount = 1;

var MMA001_07 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            MMA001_07.datagrid.initGrid_1();
            MMA001_07.datagrid.initGrid_2();
            
            MMA001_07.chart.initChart_1([]);
            MMA001_07.chart.initChart_2([]);
        }
    }, 
    // 달력 생성
    calendar : {},
    // 콤보박스 생성
    combobox : {},
    // 챠트 생성
    chart : {
        chart1 : null,
        chart2 : null,
        initChart_1 : function(datas){
            if(this.chart1) {
                this.chart1.destroy();
            }
            
            // 데이터 생성
            var arrayData = new Array();
            
            for(var i = 0; i < 24; i++) {
                var data = new Object();
                
                data.HOUR_TIME = i+':00';
                data.MSG_CNT = 0;
                
                arrayData.push(data);
            }
            
            //챠트생성
            var cht = chart.getChart("MMA001_07_chart_01");
            var jsonHeader = {TICKS: "HOUR_TIME", SERIES: ["MSG_CNT"]}; // TICKS:X축 라벨이 될 컬럼명, SERIES:Y축 values)
            
            var series = Theme.defaultBarChart(cht, 1, "in");
            
            chart.setAnimate(false);
            chart.setLeftYMinLabel(0);
            chart.setLeftYTickInterval(500);
            chart.setChartBorder(false);
            chart.setBackgroundColor("#393939");
            chart.setChartLineColor("#666666");
            chart.setSeriesColors(series, ["#d3eb3b"]);
            chart.setSmooth(series, true, 0);
            
            this.chart1 = chart.createChart(cht, arrayData, null, jsonHeader);
        },
        initChart_2 : function(datas){
            if(this.chart2) {
                this.chart2.destroy();
            }
            
            // 데이터 생성
            var arrayData = new Array();
            
            for(var i = 0; i < 24; i++) {
                var data = new Object();
                
                data.HOUR_TIME = i+':00';
                data.MSG_CNT = 0;
                //data.MSG_CNT = (Math.random())*1000;
                
                arrayData.push(data);
            }
            
            //챠트생성
            var cht = chart.getChart("MMA001_07_chart_02");
            var jsonHeader = {TICKS: "HOUR_TIME", SERIES: ["MSG_CNT"]}; // TICKS:X축 라벨이 될 컬럼명, SERIES:Y축 values)
            
            var series = Theme.defaultBarChart(cht, 1, "in");
            
            chart.setAnimate(false);
            chart.setLeftYMinLabel(0);
            chart.setLeftYTickInterval(500);
            chart.setChartBorder(false);
            chart.setBackgroundColor("#393939");
            chart.setChartLineColor("#666666");
            chart.setSeriesColors(series, ["#d4c35d"]);
            chart.setSmooth(series, true, 0);
            
            this.chart2 = chart.createChart(cht, arrayData, null, jsonHeader);
        }
    },
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
        header_1 : [
            ["SERVER_NAME",   resource.getMessage("CC_서버명"), 250, "left", "center", true, false, null, null, null, 0, 0],
            ["CURRENT_STAT_NAME", resource.getMessage("CC_연결상태"), 100, "center", "center", true, false, null, null, {format:"function",pid:"MMA001_07",name:"drawConnectStatus"}, 0, 0],
        ],
        header_2 : [
            ["SCHEDULE_CD"         , resource.getMessage("SCHE, CODE")  /*스케줄 코드*/, 120, "center", "center", true, false, null, null, null, 0, 0],
            ["SCHEDULE_NAME"       , resource.getMessage("SCHE, NAME")  /*스케줄 명*/  , 120, "center", "center", true, false, null, null, null, 0, 0],
            ["APPLY_FROM_DATE"     , resource.getMessage("APYDT,DATE")  /*적용일자*/   , 95, "center", "center", true, false, null, null, {format:'date'}, 0, 0],
            ["APPLY_TO_DATE"       , resource.getMessage("EXPIT,DATE")  /*만료일자*/   , 105, "center", "center", true, false, null, null, {format:'date'}, 0, 0],
            ["SYSTEM_BATCH_YN_NAME", resource.getMessage("EXCUS,ORNOT") /*실행여부*/   , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["STATUS_NAME"         , resource.getMessage("STAUS")       /*상태*/      , 100, "center", "center", true, false, null, null, null, 0, 0],
            ["NEXT_TIME"           , resource.getMessage("NEXT,TXT_BATCH,HOUR")       ,  160, "center", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("MMA001_07_form_01");
            var dg_1 = grid.getObject("MMA001_07_grid_01");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/rs/st/stR001_01/selectServerList");
            grid.init.setPage(false);
            grid.init.setShowHeader(false);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
            var params = form.handle.getElementsParam("MMA001_07_form_01");
            var dg_1 = grid.getObject("MMA001_07_grid_02");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/fm/sm/sm7004_01/selectScheduleList");
            grid.init.setPage(false);
            grid.init.setShowHeader(true);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false);
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, this.header_2);
        }
    },
    // 이벤트 처리
    event : {
        onCalledWebSoket : function(data) {
            var out_obj = "";
            
            if(data.length > PROGRAM_ID_NUMBER) {
                if(data.indexOf("[SYS]") > 0) {
                    out_obj = $("#MMA001_07_ta_02");
                } else if(data.indexOf("[REL]") > 0) {
                    out_obj = $("#MMA001_07_ta_01");
                }
                
                data = data.substring(PROGRAM_ID_NUMBER+5);
            }
            
            var vcnt = 1000;
            
            if(!oUtil.isNull(vcnt)) {
                if(MMA001_07_sqlcount > (vcnt*2)) {
                    MMA001_07.control.clearLog();
                }
            }
            
            if(data.length > 0) {
                var contents = out_obj.val();
                
                if(oUtil.isNull(contents)) {
                    out_obj.val(data + "\n");
                } else {
                    out_obj.val(contents + data + "\n");
                }
                
                var top = out_obj.prop("scrollHeight");
                out_obj.scrollTop(top);
                
                MMA001_07_sqlcount++;
                
                return;
            }
        }
    },
    // 업무구현
    control : {
        selectServerList : function() {
            var params = form.handle.getElementsParam("MMA001_07_form_01");
            var dg_1 = grid.getObject("MMA001_07_grid_01");
            
            grid.handle.sendRedirect(dg_1, "/rs/st/stR001_01/selectServerList", params);
        },
        clearLog : function(obj) {
            obj.val('');
            MMA001_07_sqlcount = 1;
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
        drawConnectStatus : function(value, row, index) {
            if(oUtil.isNull(value)) {
                return "";
            } else {
                if(row.CURRENT_STAT == "R") {
                    return "<label style=\"color:green;font-weight:bold;\">"+value+"</label>";
                } else if(row.CURRENT_STAT == "S") {
                    return "<label style=\"color:#c9c9c9\">"+value+"</label>";
                } else if(row.CURRENT_STAT == "E") {
                    return "<label style=\"color:red\">"+value+"</label>";
                } else {
                    return value;
                }
            }
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
        applyFtaNation : function() {
            // { name: 'clipboard-read' } 읽기 권한
            // { name: 'clipboard-write' } 쓰기 권한 (브라우저가 활성화되어 있으면 자동으로 권한 부여)
            navigator.permissions.query({ name: 'clipboard-read' }).then((permission) => {
                // 권한을 허용했을 때는 'granted'
                // 권한을 거절했을 때는 'denied'
                // 권한을 요청 중일 때는 'prompt'
                if(permission.state == "denied") {
                    alert("클립보드 사용권한이 허용되지 않았습니다.<br>사이트 설정에서 클립보드를 허용으로 변경해 주시기 바랍니다.");
                }
            });
        }
    }
    
};
