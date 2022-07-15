/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A010_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA010_01.init.initComponent();
}

var MMA010_01 = {
    init : {
        //grid_01_init_param : {NATION_CODE:SESSION.DEFAULT_LANGUAGE},
        initComponent : function() {
            MMA010_01.calendar.initCalendar_1();
            MMA010_01.combobox.initCombo_1();
            MMA010_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {
        initCalendar_1 : function() {
        }
    }, // 달력 그리드 생성
    combobox : {
        initCombo_1 : function() {
        }
    }, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
                  ["SIGNATURE_SEQ" , resource.getMessage("AUSIN, MSSNM")    /*서명권자 번호*/, 100, "center", "center", true, false, null, null, null, 0, 0],
                  ["SIGNATURE_NAME", resource.getMessage("AUSIN, NAME")     /*서명권자명*/   , 180, "left"  , "center", true, false, null, null, null, 0, 0],
                  ["PHONE_NO"      , resource.getMessage("TELNM")           /*전화번호*/     , 100, "center", "center", true, false, null, null, null, 0, 0],
                  ["EMAIL"         , resource.getMessage("TXT_EMAIL")       /*이메일*/       , 180, "center", "center", true, false, null, null, null, 0, 0]
		         ],
		initGrid_1 : function() {
			var dg_1 = grid.getObject("MMA010_01_grid_01");
			
            grid.init.setPage(true);
            grid.init.setFitColumns(true);
            //grid.init.setURL("/mm/pop/mmA010_01/selectSignatureList");
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);
            
            // 기본 데마를 적용한 그리드를 생성한다.
			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
		//onClick_MMA010_01_grid_01 : function(rowData) {},
        onDblClick_MMA010_01_grid_01 : function(rowData) {
            var pid = form.handle.getValue("MMA010_01_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA010_01"]) == "function") {
                    pro_id["callByMMA010_01"](rowData);
                }
            }
        },
		callBackNoData : function(data) {}
    },
    control : {// 업무구현
    	selectSignatureList : function() {
            // 검색창의 validation 체크결과를 비교한다.
            if (!form.handle.isValidate("MMA010_01_form_01")) {
                return;
            }
    	    
    		var dg_1 = grid.getObject("MMA010_01_grid_01");
    		var params = form.handle.getElementsParam("MMA010_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA010_01/selectSignatureList", params);
    	},
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA010_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            //for (key in rowData) {
            //    console.log("key:"+key+",value:"+rowData[key]);
            //}
            
            if (!grid.handle.isSelected(dg_1)) {
                // 그리드에서 열을 선택해 주세요.
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
            }
            
            MMA010_01.event.onDblClick_MMA010_01_grid_01(rowData);
        }
    },
    dialog : {
    	// 다이얼로그 구현
    },
    file : {
    	// 파일 input 엘리먼트 구현
    },
    excel : {
    	// 엑셀다운로드 구현
    }
	
};
