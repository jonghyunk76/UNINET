/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A001_06
 * 작업일자 : 2016.09.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA001_06.config.applyFtaNation();
    MMA001_06.init.initComponent();
}

var MMA001_06 = {
    
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
    	header1 : {params : ""},
    	header2_1 : {params : ""},
    	header2_1 : {params : ""},
    	initGrid_1 : function() {
    		var param1 = {PID:"MMA001_06", GRID_ID:"MMA001_06_grid_01"};
            var dg_1 = grid.getObject("MMA001_06_grid_01");

            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            // 이벤트 설정
            grid.event.setOnDblClickRow(dg_1);

            Theme.defaultGrid(dg_1, {params:param1});
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
    	applyFtaNation : function() {
    		// { name: 'clipboard-read' } 읽기 권한
    		// { name: 'clipboard-write' } 쓰기 권한 (브라우저가 활성화되어 있으면 자동으로 권한 부여)
    		/*
    		navigator.permissions.query({ name: 'clipboard-read' }).then((permission) => {
    		    // 권한을 허용했을 때는 'granted'
    		    // 권한을 거절했을 때는 'denied'
    		    // 권한을 요청 중일 때는 'prompt'
    			if(permission.state == "denied") {
    				alert("클립보드 사용권한이 허용되지 않았습니다.<br>사이트 설정에서 클립보드를 허용으로 변경해 주시기 바랍니다.");
    			}
    		});
    		*/
    	}
    }
    
};
