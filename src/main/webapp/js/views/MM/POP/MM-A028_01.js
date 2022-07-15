/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A028_01
 * 작업일자 : 2017.03.03
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA028_01.config.applyFtaNation();
    MMA028_01.init.initComponent();
}

var MMA028_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA028_01.control.selectViewFile();
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
    datagrid : {},
    // 이벤트 처리
    event : {},
    // 업무구현
    control : {
    	selectViewFile : function() {
    		try {
    			var path = form.handle.getValue("MMA028_01_form_01", "FILE_PATH");
        		
        		$('#MMA028_01_frame_1').attr("src", "/mm/pop/mmA028_01/selectViewFile?FILE_PATH="+path);
    		} catch(e) {
    			console.debug(e);
    		}
    	}
    },
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
