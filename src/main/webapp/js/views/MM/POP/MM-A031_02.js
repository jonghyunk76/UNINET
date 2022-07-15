/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A031_02
 * 작업일자 : 2019.04.23
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA031_02.config.applyFtaNation();
    MMA031_02.init.initComponent();
}

var MMA031_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA031_02.datagrid.initGrid_1();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {},
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	header1 : [
        	["MESSAGE_NAME",         resource.getMessage("MAIL,NAME"),   120, "left", "center", true, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA031_02_form_01");
            var dg_1 = grid.getObject("MMA031_02_grid_01");

            grid.init.setPage(false);
            grid.init.setQueryParams(params);
            grid.init.setURL("/fm/di/di3209_05/selectMailSendMsgList");
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
            grid.init.setCallBackFunction("selectFirstData");
            
            grid.event.setOnClickRow(dg_1);
            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
    	onClick_MMA031_02_grid_01 : function(rowData) {
    		MMA031_02.ui.reviewerApyMailContents(rowData.MESSAGE_CONTENTS, rowData.MESSAGE_TITLE);
    	},
    	selectFirstData : function() {
    		var dg_1 = grid.getObject("MMA031_02_grid_01");
    		var rowDatas = grid.handle.getAllRows(dg_1);
    		
    		if(rowDatas.length > 0) {
	    		grid.handle.selectRowIndex(dg_1, 0);
	    		
	    		MMA031_02.ui.reviewerApyMailContents(rowDatas[0]["MESSAGE_CONTENTS"], rowDatas[0]["MESSAGE_TITLE"]);
    		}
    	},
    	onDblClick_MMA031_02_grid_01 : function(rowData) {
    		MMA031_02.control.selectRowValue();
    	}
    },
    // 업무구현
    control : {
    	selectMailSendMsgList : function() {
    		var dg_1 = grid.getObject("MMA031_02_grid_01");
    		var params = form.handle.getElementsParam("MMA031_02_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/di/di3209_05/selectMailSendMsgList", params);
    	},
    	selectRowValue : function() {
    		var dg_1 = grid.getObject("MMA031_02_grid_01");
    		
        	if(!grid.handle.isSelected(dg_1)) {
        		alert(resource.getMessage("MSG_NO_SELECT"));
        		return;
        	}
        	
            var rowData = grid.handle.getSelectedRowData(dg_1);
            var pid = form.handle.getValue("MMA031_02_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA031_02"]) == "function") {
                    pro_id["callByMMA031_02"](rowData);
                }
            }
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	reviewerApyMailContents : function(contents, title) {
    		var rstValue = DI3209_01.util.makeMailContents(contents, title);
    		
    		$("#MMA031_02_html_01").html(rstValue);
    	}
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
};
