/******************************************************************************************
 * 작성자 : 
 * Program Id : MM-A014_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA014.datagrid.initGrid_1();
}

var MMA014 = {
	
    init : {}, // 초기값 설정
	calendar : {}, // 달력 그리드 생성
	combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [],
		initGrid_1 : function() {
			var dg_1 = grid.getObject("grid_id");
			
			grid.init.setURL("URL");
			
			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
		onClick_grid_id : function(rowData) {},
		onDblClick_grid_id : function(rowData) {},
		callBackNoData : function(data) {}
    },
    control : {// 업무구현
    	selectMainList : function() {
    		var dg_1 = grid.getObject("grid_id");
    		var params = form.handle.getElementsParam("MMA014_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "URL", params);
    	},
    	formTest : function() { // fom
    		var obj = form.getObject("MMA014_form_01");
    		
    		form.init.setUrl("/mm/smp/smp1001/selectMainList");
    		form.init.setCallFunc("");
	        form.init.setSucceseMessage(MSG_SUCCESS_BODY);
	        form.init.setFailMessage(MSG_SAVE_FAILURE);
	        
    		form.submit(obj);
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
