/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B001_01
 * 작업일자 : 2020.05.08
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB001_01.config.applyFtaNation();
    MMB001_01.init.initComponent();
}

var MMB001_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {}
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
    	initGrid_1 : function() {
    		var param1 = {PID:"MMB001_01", GRID_ID:"MMB001_01_grid_01"};
            var dg_1 = grid.getObject("MMB001_01_grid_01");

            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, {params:param1});
            //Theme.defaultGrid(dg_1, {params:param1}, {params:param2});
        }
    },
    // 이벤트 처리
    event : {},
    // 업무구현
    control : {},
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {}, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
