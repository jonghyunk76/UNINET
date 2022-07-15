/******************************************************************************************
 * 작성자 : YNI-Maker 
 * Program Id : MM-A001_02
 * 작업일자 : 2016.05.08
******************************************************************************************/

function onLoadPage() {
	MMA001_02.init.initComponent();
}

var MMA001_02 = {
	
	// 초기값 설정
    init : {
    	initComponent : function() {
    		MMA001_02.combobox.initCombo_1();
    		MMA001_02.combobox.initCombo_2();
    	}
    },
    // 달력 생성
	calendar : {}, 
	// 콤보박스 생성
	combobox : {
		initCombo_1 : function() {
			var obj = combo.getObject("MMA001_02_form_02", "YYYY");
			
			var stYear = parseInt(calendar.util.toYear2String()) - 10;
			combo.init.setType("year");
			combo.init.setHeight(160);
			combo.init.setStartYear(stYear);
			combo.init.setYearCount(11);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		initCombo_2 : function() {
			var obj = combo.getObject("MMA001_02_form_02", "MM");
			
			combo.init.setType("month");
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		}
	},
	// 데이터 그리드 생성
    datagrid : {},
    // 각종 이벤트 처리 구현
    event : {},
    // 업무 구현
    control : {
    	setCallFuntion : function() {
    		var pid = form.handle.getValue("MMA001_02_form_02", "TARGET_PID");
    		var yyyy = combo.handle.getValue("MMA001_02_form_02", "YYYY");
    		var mm = combo.handle.getValue("MMA001_02_form_02", "MM");
        	mm = (mm <= 9) ? "0"+mm : mm;
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".tooltip");
                
                if (typeof(pro_id["callByMMA001_02"]) == "function") {
                    pro_id["callByMMA001_02"](yyyy+""+mm);
                }
            }
    	}
    },
    // UI 변경
    ui : {},
    // 챠트 생성
    chart : {},
    // dailog(popup) 생성
    dialog : {},
    // 파일 처리 구현
    file : {},
    // 엑셀 업로드/다운로드 구현
    excel : {}
	
};
