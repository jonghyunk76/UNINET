/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A004_02
 * 작업일자 : 2018.04.24
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA004_04.config.applyFtaNation();
    MMA004_04.init.initComponent();
}

var MMA004_04 = {
        
    // 초기값 설정
    init : {
        initComponent : function() {
            MMA004_04.datagrid.initGrid_1();
            
            var fta_cd = form.handle.getValue("MMA004_04_form_01", "FTA_CD");
            if(fta_cd == "OPT1"){ // 보고서 전용 ( 서명카드 )
            	$("#MMA004_04_out_list").hide();
            	$("#MMA004_04_out_list").css("width","0");
            }
        }
    },
    // 달력 그리드 생성
    calendar : {},
    // 콤보박스 그리드 생성
    combobox : {},
    datagrid : { // 데이터 그리드 생성
        header1 : [
            ["CO_DOC_NO"     ,resource.getMessage("TXT_COO_CERTIFY_NO")     ,140   ,"left"     ,"center"    ,true       ,false    ,null         ,null      ,null    ,0        ,0]
        ],
        initGrid_1 : function() {
        	var gridId = form.handle.getValue("MMA004_04_form_01", "gridID"); 
        	var dg_1 = grid.getObject(gridId);        	
        	var rows = grid.handle.getCheckedRowsData(dg_1);
        	var gridData = new Object();
        	
        	// 그리드 데이터 구하기(확인서 번호 중복제거)
        	var coDocNo = new Array();
        	for(var i = 0; i < rows.length; i++) {
        		if(i == 0) {
        			coDocNo.push(rows[i]);
        		} else {
        			var dupChk = false;
        			
        			for(var j = 0; j < coDocNo.length; j++) {
        				if(rows[i]["CO_DOC_NO"] == coDocNo[j]["CO_DOC_NO"]) {
        					dupChk = true;
        				}
        			}
        			
        			if(!dupChk) {
        				coDocNo.push(rows[i]);
        			}
        		}
        	}
        	
        	var dg_2 = grid.getObject("MMA004_04_grid_01");
            
        	grid.init.setData(coDocNo);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(true);
            grid.init.setCallBackFunction("loadAtferGrid");
            grid.init.setRownumbers(false);
            grid.init.setShowHeader(false);
            
            Theme.defaultGrid(dg_2, this.header1);
        }
    },
    // 이벤트 처리
    event : {
        loadAtferGrid : function() {
            var dg_1 = grid.getObject("MMA004_04_grid_01");
            
            if(grid.handle.getRowCount(dg_1) > 0) {
                grid.handle.checkRow(dg_1, true, 0);
                
                MMA004_04.control.executeIssueCoDoc();
            }
        }
    },
    control : {
        executeIssueCoDoc : function() {
            var dg_1 = grid.getObject("MMA004_04_grid_01");
            
            if(grid.handle.getRowCount(dg_1) <= 0) {
                return;
            } else {
                var params = form.handle.getElementsParam("MMA004_04_form_01");
                
                params.GRID_ID = "MMA004_04_grid_01";
                
                $('#MMA004_04_panel_01').panel({href:"/fm/or/or5203_06", queryParams:params});
            }
        }
    },
    dialog : {},
    // 화면 UI 변경
    ui : {},
    // 파일 input 엘리먼트 구현
    file : { },
    // 엑셀다운로드 구현
    excel : { },
    config : {
    	applyFtaNation : function() {
    		var columns1 = MMA004_04.datagrid.header1;
    		
			if(SESSION.DEFAULT_LANGUAGE == "KR") {
				grid.util.changeHeaderHidden(columns1 , "DOC_NAME", false);
				grid.util.changeHeaderHidden(columns1 , "DOC_NAME_ENG", true);
			} else {
				grid.util.changeHeaderHidden(columns1 , "DOC_NAME", true);
				grid.util.changeHeaderHidden(columns1 , "DOC_NAME_ENG", false);
			}
		}
    }
    
};