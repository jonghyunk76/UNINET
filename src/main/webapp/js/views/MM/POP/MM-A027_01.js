/******************************************************************************************
 * 작성자 : 이재욱
 * Program Id : OR-5002_05
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA027_01.init.initComponent();
}

var MMA027_01 = {
        
    // 초기값 설정
    init : {
        initComponent : function() {
            MMA027_01.datagrid.initGrid_1();
        }
    },
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {},
    datagrid : { // 데이터 그리드 생성
        initGrid_1 : function() {
        	var param = form.handle.getElementsParam("MMA027_01_form_01");
            var dg_1 = grid.getObject("MMA027_01_grid_01");
            
            grid.init.setURL("/mm/dpop/mmA027_01/selectInterfaceHistoryList");
            grid.init.setQueryParams(param);
            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            
            if(param.TARGET_PID == "STR003_01") {
                Theme.defaultGrid(dg_1, {url:"/mm/dpop/mmA027_01/selectServiceHeaderInfo", params:param});
            } else {                
                Theme.defaultGrid(dg_1, {url:"/mm/dpop/mmA027_01/selectHeaderInfo", params:param});
            }
        }
    },
    // 이벤트 처리
    event : {},
    // 업무 구현
    control : {},
    // Dialog 창 구현
    dialog : {},
    // 화면 UI 변경
    ui : {},
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
      		var dg_1 = grid.getObject("MMA027_01_grid_01");
      		var fobj = form.getObject("MMA027_01_form_01");
  	        
  	        form.init.setURL("/mm/dpop/mmA027_01/selectInterfaceHistoryList.fle");
  	        
  	        // parameter description : grid객체, form객체, 파일명, 시트명
  	        form.excelSubmit(dg_1, fobj, resource.getMessage("DTA, DETIL"), resource.getMessage("LIST"));
  	    }
    }
    
};