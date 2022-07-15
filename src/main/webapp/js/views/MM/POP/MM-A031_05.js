/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A031_05
 * 작업일자 : 2020.02.01
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA031_05.config.applyFtaNation();
    MMA031_05.init.initComponent();
}

var MMA031_05 = {
    
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
    event : {},
    // 업무구현
    control : {
    	htmlConvertExcel : function() {
    		var survayName = form.handle.getValue("MMA031_05_form_01", "SURVEY_NAME");
    		var vendorName = form.handle.getValue("MMA031_05_form_01", "VENDOR_NAME");
    		var $clonedTable = $("#MMA031_05_div_01");
    		
    		$clonedTable.find('[style="display:none;"]').remove();
    		
    		$clonedTable.table2excel({
    			exclude: ".noExl", 
    			name: "Excel Document Name", 
    			filename: survayName+"_"+vendorName+"_"+'.xls', //확장자를 여기서 붙여줘야한다. 
    			fileext: ".xls", 
    			exclude_img: true, 
    			exclude_links: true, 
    			exclude_inputs: true,
    			preserveColors: true
    		});
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
