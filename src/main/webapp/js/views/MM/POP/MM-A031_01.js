/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A031_01_01
 * 작업일자 : 2017.09.05
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA031_01.config.applyFtaNation();
    MMA031_01.init.initComponent();
}

var MMA031_01 = {
    
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
    datagrid : {},
    // 이벤트 처리
    event : {
    	insertInquiryEmailRecord : function() {
    		form.handle.closeAll("MMA031_01_form_01", true);
    		form.util.setVisible("MMA031_01_span_01", false);
    	}
    },
    // 업무구현
    control : {
    	insertInquiryEmailRecord : function() {
    		var obj = form.getObject("MMA031_01_form_01");
    		
    		form.init.setURL("/mm/noses/mmA031_01/insertInquiryEmailRecord");
    		form.init.setProgressFlag(true);
    		form.init.setSucceseMessage("<h3>문의하신 내용이 정상적으로 전달되었습니다.</h3><br>답변은 접수 후 1일 이내에 이메일 또는 전화로 전달됩니다.");
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("insertInquiryEmailRecord");
            
            form.submit(obj);
    	}
    },
    // 다이얼로그 구현
    dialog : {
    	close : function() {
    		var obj = dialog.getObject("MMA031_01_dailog_01");
			
    		dialog.handle.close(obj);
    	}
    },
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
