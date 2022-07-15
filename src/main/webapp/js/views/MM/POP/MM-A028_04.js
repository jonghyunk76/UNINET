/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A028_04
 * 작업일자 : 2019.07.17
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA028_04.config.applyFtaNation();
    MMA028_04.init.initComponent();
}

var MMA028_04 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA028_04.control.selectSurveyTempletList();
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
    event : {
    	selectEvaluSurveyInfo : function(datas) {
    		if($("#MMA028_04_div_01").length > 0) {
    			$("#MMA028_04_div_01").empty();
    		}
    		
    		var flag = form.handle.getValue("MMA028_04_form_01", "flag");
    		var type = "";
    		
    		if(flag == "view") {
    			type = "C";
    		} else {
    			type = datas.survey[0].SURVEY_TYPE;
    		}
    		
    		var surveyStr = createSurvey("MMA028_04_div_01", datas.survey, datas.code, null, datas.answer, type);
    		
        	$("#MMA028_04_div_01").html(surveyStr);
        	
        	form.setFormValue("MMA028_04_form_01", "str", surveyStr);
    	},
    	insertEvaluSurveyInfo : function() {
    		var pid = form.handle.getValue("MMA028_04_form_01", "TARGET_PID");
    		
    		if(pid == "DI3013_01") {
    			DI3013_01.control.selectEvaluationVendorList("1");
    		} else if(pid == "VBE002_01") {
    			VBE002_01.control.selectEvaluSurveyList('1');
    		}
    			
    		return;
    	}
    },
    // 업무구현
    control : {
    	selectSurveyTempletList : function() {
    		var obj = form.getObject("MMA028_04_form_01");
        	
    		form.init.setURL("/sp/vb/vbE002_01/selectEvaluSurveyInfo"); // 공통으로 소스이관해서 구현할 것
    		form.init.setCallBackFunction("selectEvaluSurveyInfo");
    		form.init.setProgressFlag(true);
    		form.init.setValidationFlag(true);
    		
            form.submit(obj);
    	},
    	insertEvaluSurveyInfo : function(status) {
    		var msg;
    		
    		if(status == "2") {
    			msg = resource.getMessage("MSG_CONFIRM_SAVE");
    		} else if(status == "3") {
    			msg = resource.getMessage("MSG_SUBMIT_NOT_MODIFY");
    		}
    		
    		$.messager.confirm(CNFIR, msg, function(flag) {
                if (flag) {
                	form.handle.setValue("MMA028_04_form_01", "EVALU_STATUS", status);

                	var obj = form.getObject("MMA028_04_form_01");
		            
		            form.init.setURL("/sp/vb/vbE002_02/insertEvaluSurveyInfo");
		            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
		            form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
		            form.init.setCallBackFunction("insertEvaluSurveyInfo");
		            
		            form.submit(obj);
                }
        	});
    	},
    	htmlConvertPdf : function() {
    		// 현재 document.body의 html을 A4 크기에 맞춰 PDF로 변환
    		html2canvas($('#MMA028_04_div_01'), {
    	        onrendered: function(canvas) {
    	     
	    	        // 캔버스를 이미지로 변환
	    	        var imgData = canvas.toDataURL("image/jpeg", 1.0);
	    	        
	    	        var imgWidth = 210-4; // 이미지 가로 길이(mm) A4 기준
	    	        var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
	    	        var imgHeight = canvas.height * imgWidth / canvas.width;
	    	        var heightLeft = imgHeight;
	    	        
	    	        var doc = new jsPDF('p', 'mm', 'a4');  // a4, letter
	    	        var position = 2;
	    	        
	    	        // 첫 페이지 출력
	    	        doc.addImage(imgData, 'JPEG', 2, position, imgWidth, imgHeight);
	    	        heightLeft -= pageHeight;
	    	        
	    	        // 한 페이지 이상일 경우 루프 돌면서 출력
	    	        while (heightLeft >= 20) {
		    	        position = heightLeft - imgHeight;
		    	        
		    	        doc.addPage();
		    	        doc.addImage(imgData, 'JPEG', 2, position, imgWidth, imgHeight);
		    	        
		    	        heightLeft -= pageHeight;
	    	        }
	    	        
	    	        // 파일 저장
	    	        doc.save('Supplier_survey.pdf');
    	        },
    	        background: '#ffffff'
    	    });
    	},
    	htmlConvertExcel : function() {
    		var survayName = form.handle.getValue("MMA028_04_form_01", "SURVEY_NAME");
    		var vendorName = form.handle.getValue("MMA028_04_form_01", "VENDOR_NAME");
    		var $clonedTable = $("#MMA028_04_div_01");
    		
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
    	applyFtaNation : function() {
    		var flag = form.handle.getValue("MMA028_04_form_01", "flag");
    		
    		if(flag == "view") {
    			form.util.setVisible("MMA028_04_btn_03", true);
    			form.util.setVisible("MMA028_04_btn_04", true);
    		} else {
    			form.util.setVisible("MMA028_04_btn_01", true);
    			form.util.setVisible("MMA028_04_btn_02", true);
    		}
    	}
    }
};
