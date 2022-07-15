/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A004_05
 * 작업일자 : 2018.07.31
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA004_05.config.applyFtaNation();
    MMA004_05.init.initComponent();
}

var MMA004_05 = {
	init : {
        initComponent : function() {
        	setTimeout(function() {
        		MMA004_05.control.executeIssueDoc("P");
        	}, 0);
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
    	executeIssueDoc : function(type) {
            var files = "";
            var names = "";
            var coDcos = "";
            var rows = new Object();
            
            if(!oUtil.isNull(files)) files = files.substring(0, files.length - 1);
            
            if(oUtil.isNull(type) || type == "P") {
                form.handle.setValue("MMA004_05_form_01", "REPORT_PRINT_TYPE", "P");
            } else {
                form.handle.setValue("MMA004_05_form_01", "REPORT_PRINT_TYPE", "F");
            }
            
            form.handle.setValue("MMA004_05_form_01", "REPORT_TYPE", (oUtil.isNull(type))?"pdf":type);
            form.handle.setValue("MMA004_05_form_01", "SOCKET_ID", SESSION.TRANSACTION_ID);
            
            $.messager.progress({
            	msg: "Report printing...",
            	text : "Loading data..."
            });
            
            $("#MMA004_05_form_01").attr("action", "/mm/pop/mmA004_01/executeIssueCoDoc");
            $("#MMA004_05_form_01").submit();
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
