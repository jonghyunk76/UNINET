/**
 * YNIFramework 1.0
 * 
 * Copyright (c) 2016-2019 jonghyunkim@kr.kpmg.com All rights reserved.
 * 
 */

/**
 * 설문지를 동적으로 생성하는 함수
 * @param divId = 설문지를 생성할 div id
 * @param surveyData = 설문지 데이타
 * @param codeList = 모든 공통 코드 List(category 별)
 * @parma companyInfo = 회사기본 정보
 * @param answerInfo = 질문답변내역
 * @param surveyType = 설문지 타입(T:template등록, W:EICC작성, S:표준등록, C:수취내역 조회, O:협력사 직접입력, F:파일 업로드, M:입력+유효성 체크결과, O:자율점검, D:서면심사, R:현상심사)
 */
function createSurvey(divId, surveyData, codeList, companyInfo, answerInfo, surveyType) {
	var surveyStr = "<div id=\""+divId+"\"/>";
		surveyStr += "<table class=\"dataT\" style=\"margin-bottom:0px;\">"; //survey 전체 String
    var colgroup = "";
    var addScore = false;
    
    if(divId == "MMA028_04_div_01") {
    	addScore = true;
    }
    
    //설문지 항목수 만큼 반복하면서 row 생성
    for(var i=0; i < surveyData.length; i++) {
        surveyStr += "<tr>";
        var code = surveyData[i].ATTRIBUTE_CODE;
        var name = surveyData[i].ATTRIBUTE_NAME;
        var style = surveyData[i].ATTRIBUTE_STYLE;
        
        if(oUtil.isNull(style)) style = "";
        
        var messageText = "";
        var answerField = false;
        
        //하위 답변이 없는 title 
        if(surveyData[i].ANSWER_NUM == "0" && !oUtil.isNull(surveyData[i].ATTRIBUTE_NAME)) {
        	for(var j=1; j <= 4; j++ ) {
        		if(j == 1) anStyle = surveyData[i].ANSWER_STYLE1;
                else if(j == 2) anStyle = surveyData[i].ANSWER_STYLE2;
                else if(j == 3) anStyle = surveyData[i].ANSWER_STYLE3;
                else if(j == 4) anStyle = surveyData[i].ANSWER_STYLE4;
                else if(j == 5) anStyle = surveyData[i].ANSWER_STYLE5;
                else if(j == 6) anStyle = surveyData[i].ANSWER_STYLE6;
                
                if(!oUtil.isNull(anStyle)) {
                	if(colgroup == "") colgroup = "<colgroup>";
                	colgroup += "<col " + anStyle + " />"; 
                }
        	}
        	
        	if(colgroup != "") {
        		colgroup += "</colgroup>";
        		surveyStr += colgroup;
        	}
        	
        	surveyStr += "<td scope=\"row\" "+style+">";
            surveyStr += name;
            surveyStr += "</td>";
            
            colgroup = "";
            
            //console.log("1.1."+surveyStr);
        //하위 답변이 있는 title
        } else {
            surveyStr += "<th scope=\"row\" "+style+">";
            surveyStr += name;
            surveyStr += "</th>";
            //console.log("1.2."+surveyStr);
            // jsonValue의 전체 row에서 ATTRIBUTE_CODE에 해당하는 row를 찾아 값을 설정한다.  
            // surveyData[i].ATTRIBUTE_CODE;
            
            //하위 답변 수 만큼 답변 행 생성
            for(var j=1; j <= surveyData[i].ANSWER_NUM; j++ ) {
                var id = eval("surveyData[i].ANSWER_NAME"+j) == null ? "" : eval("surveyData[i].ANSWER_NAME"+j);
                var value = "";
                var message = "";
                var message_view = "[{}]";
                
                if(id.charAt(0) == "C" && companyInfo != null) {
                    if(companyInfo.length == 1) {
                        value = eval("companyInfo[0]."+id) == null ? "" : eval("companyInfo[0]."+id);
                    } else if(companyInfo.length > 1) {
                        for(var a = 0; a < companyInfo.length; a++) {
                            value = eval("companyInfo["+a+"]."+id) == null ? "" : eval("companyInfo["+a+"]."+id);
                            if(eval("companyInfo["+a+"]."+id) != null) {
                            	if(eval("companyInfo["+a+"].ERROR_YN") == "Y") {
	                                message = (oUtil.isNull(eval("companyInfo["+a+"].ERROR_MESSAGE"))) ? "" : eval("companyInfo["+a+"].ERROR_MESSAGE");
	                                message_view = (oUtil.isNull(eval("companyInfo["+a+"].MESSAGE_VIEW"))) ? "" :  eval("companyInfo["+a+"].MESSAGE_VIEW");
                            	}
                                
                                break;
                            }
                        }
                    }
                } else if(answerInfo != null) {
                    if(answerInfo.length > 0) { // 질의에 대한 답변
                        for(var a = 0; a < answerInfo.length; a++) {
                            var qid = answerInfo[a].QUESTION_CODE;
                            if(qid == code) {
                                value = (oUtil.isNull(eval("answerInfo["+a+"].ANSWER_VALUE"+j))) == null ? "" : eval("answerInfo["+a+"].ANSWER_VALUE"+j);
                                message = (oUtil.isNull(eval("answerInfo["+a+"].ERROR_MESSAGE"))) == null ? "" : eval("answerInfo["+a+"].ERROR_MESSAGE");
                                message_view = (oUtil.isNull(eval("answerInfo["+a+"].MESSAGE_VIEW"))) == null ? "" :  eval("answerInfo["+a+"].MESSAGE_VIEW");
                                
                                break;
                            }
                        }
                    }
                    
                    if(oUtil.isNull(value)) value = "";
                }
                
                var type = eval("surveyData[i].ANSWER_TYPE"+j) == null ? "" : eval("surveyData[i].ANSWER_TYPE"+j);
                var desc;
                var anStyle;
                var category;
                var scopeCode = "";
                
                if(j == 1) anStyle = surveyData[i].ANSWER_STYLE1;
                else if(j == 2) anStyle = surveyData[i].ANSWER_STYLE2;
                else if(j == 3) anStyle = surveyData[i].ANSWER_STYLE3;
                else if(j == 4) anStyle = surveyData[i].ANSWER_STYLE4;
                else if(j == 5) anStyle = surveyData[i].ANSWER_STYLE5;
                else if(j == 6) anStyle = surveyData[i].ANSWER_STYLE6;
                
                if(j == 1) category = surveyData[i].ANSWER_CATEGORY1;
                else if(j == 2) category = surveyData[i].ANSWER_CATEGORY2;
                else if(j == 3) category = surveyData[i].ANSWER_CATEGORY3;
                else if(j == 4) category = surveyData[i].ANSWER_CATEGORY4;
                else if(j == 5) category = surveyData[i].ANSWER_CATEGORY5;
                else if(j == 6) category = surveyData[i].ANSWER_CATEGORY6;
                
                if(surveyType != 'C') {
	                if(j == 1) desc = surveyData[i].ANSWER_DESC1;
	                else if(j == 2) desc = surveyData[i].ANSWER_DESC2;
	                else if(j == 3) desc = surveyData[i].ANSWER_DESC3;
	                else if(j == 4) desc = surveyData[i].ANSWER_DESC4;
	                else if(j == 5) desc = surveyData[i].ANSWER_DESC5;
	                else if(j == 6) desc = surveyData[i].ANSWER_DESC6;
                } else {
                	desc = "";
                }
                
                if(oUtil.isNull(anStyle)) anStyle = "";
                if(oUtil.isNull(category)) category = "none";
                if(oUtil.isNull(desc)) desc = "";
                	
                //console.log("element style["+j+"] = " + anStyle);
                
                if(!oUtil.isNull(companyInfo)) {
                    scopeCode = companyInfo[0].C2_1;
                }
                
                if(j == surveyData[i].ANSWER_NUM) anWidth = "";
                
                if(type == "label") {
                	surveyStr += "<th scope=\"row\" "+anStyle+">"; 
                } else {
                	surveyStr += "<td>";
                }
                
                // 엘리먼트 생성
                surveyStr += createElement(divId, id, value, type, desc, surveyData[i], category, codeList, surveyType, scopeCode, anStyle, code, message_view);
                
                if(type == "label") {
                	surveyStr += "</th>";
                } else {
                	surveyStr += "</td>";
                }
                
                if(!oUtil.isNull(message)) messageText = message;
                if(!oUtil.isNull(type) && type != "label") answerField = true;
            }
        }
        
        if(surveyType == "F" || surveyType == "M") {
            var divName = divId + "_" + code;
            if(surveyData[i].ANSWER_NUM > 0 && answerField) {
                if(!oUtil.isNull(messageText)) {
                    surveyStr += "<td class=\"search_table_cont\"><div id='"+divName+"'><a href=\"#\" title=\""+messageText+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a></div></td>";
                } else {
                    surveyStr += "<td class=\"search_table_cont\"><div id='"+divName+"'><img src=\"/images/icons/ok.png\" boder=\"0\"/></div></td>";
                }
            } else {
                surveyStr += "<td class=\"search_table_cont\"> <div id='"+divName+"'></div></td>";
            }
        }
        
        surveyStr += "</tr>";
    }
    surveyStr += "</table></div>";
    checkArray = new Array();
    //document.write(surveyStr);
    return surveyStr;
}

var checkArray = new Array();

/**
 * 답변에 해당하는 tag or element 생성
 * @param divId = div 고유ID
 * @param id = tag or element id
 * @param value = tag or element value
 * @param type = tag or element type
 * @param desc = 설명
 * @param category = combobox에서 사용하는 공통 코드 category
 * @param data = combobox에서 사용 하는 공통 코드 데이타
 * @param surveyType = 설문지 타입(T:template등록, W:EICC작성, S:표준등록, C:CO수취현황, O:협력사 직접입력, F:파일 업로드, M:입력+유효성 체크결과, O:자율점검, D:서면심사, R:현상심사) 
 * @param scope = A:Company level, B:Divsion Level, C:Product Category Level, D:Product Level
 * @param width = Element 넓이
 * @param code = row별 고유식별자
 * @param message_view = 메시지 정보(json타입)
 * @returns {String}
 */
function createElement(divId, id, value, type, desc, surveyData, category, data, surveyType, scope, style, code, message_view) {
	var elementStr = "";
    var pid = divId.substring(0, PROGRAM_ID_NUMBER);
    var funcName = "funcCall_" + id;
    var requiredYn = surveyData.FILE_ATTACH_YN;
    //text tag 생성
    var bgColor = "#ffffff";
    if(requiredYn == "Y" && (id.substring(id.length-2, id.length) == "_1" || id == "B3_2")) {
        bgColor = "#FFFF7E";
    } else {
        requiredYn = "N";
    }
    var readonly = (surveyType == 'C') ? " readonly=\"true\" " : "";
    var comDisabled = (surveyType == 'C') ? " disabled=\"true\" " : "";
    var inputClass = (surveyType == 'C') ? "input_readOnly" : "easyui-validatebox";
    
    //console.log("pid = "+pid +", type = " + type + ", category = " + category + ", surveyType = "+ surveyType + ", desc = " + desc);
    
    if(surveyType == 'C') {
        if(type == "combobox") {
            // 코드값을 화면에 표시할 네임값으로 변경
            var comboList = eval("data."+category);
            for(var i=0; i < comboList.length; i++) {
                if(!oUtil.isNull(value) && value == comboList[i].CODE) {
                    value = comboList[i].NAME;
                    break;
                }
            }
            
            type = "text";
        }
        
        for(var i=2; i < 6; i++) {
            if("D" +i + "_1" == id) {
                if(value.toUpperCase() == 'NO') {
                    for(var j=1; j < 6; j++) {
                        checkArray[checkArray.length++] = "D" + (i + j * 5) + "_1";
                    }
                    break;
                }
            } 
        }
        
        for(var i=0; i < checkArray.length; i++) {
            if(id == checkArray[i] ) {
                bgColor = "#A6A6A6";
                break;
            }
        }
    }
    
    if(type == "text") {
        var disableVal = "";
        var event = "";
        
        if(((id == "C3_1" || id == "C10_1") && surveyType == "T")||(id == "C3_1" && surveyType == "W" && (scope == "A" || scope == "D"))) {
            inputClass = "";//"input_readOnly";
            readonly = " readonly=\"true\" ";
            bgColor = "#DFDFDF";
            disableVal = "disabled=\"true\"";
        }
        if(surveyType == "F") {
            inputClass = "input_readOnly";
            readonly = " readonly=\"true\" ";
        }
        if(requiredYn == "Y") {  // 필수인 경우에만 이벤트가 발생하도록 함.
            event = " onkeyup=\"javascript:survey."+funcName+"('"+pid+"', '"+id+"', '"+divId+"', '"+code+"','"+surveyData.VERSION +"', eval("+message_view+"));\" "; 
        }
        
        elementStr = "<input type=\"text\" class=\""+inputClass+"\" id=\""+id+"\" name=\""+id+"\"  required=\"true\" "+event+" value=\""+value+"\" "+disableVal+" placeholder=\""+desc+"\" "+readonly+" style=\"background-color:"+bgColor+";"+style+"\"/>";
    } else if(type == "readonly") {
        var disableVal = "";
        var event = "";
        
        if(((id == "C3_1" || id == "C10_1") && surveyType == "T")||(id == "C3_1" && surveyType == "W" && (scope == "A" || scope == "D"))) {
            inputClass = "";//"input_readOnly";
            readonly = " readonly=\"true\" ";
            bgColor = "#DFDFDF";
            disableVal = "disabled=\"true\"";
        } else {
            inputClass = "input_readOnly";
            readonly = " readonly=\"true\" ";
        }
        
        if(requiredYn == "Y") {  // 필수인 경우에만 이벤트가 발생하도록 함.
            event = " onkeyup=\"javascript:survey."+funcName+"('"+pid+"', '"+id+"', '"+divId+"', '"+code+"','"+surveyData.VERSION +"', eval("+message_view+"));\" "; 
        }
        
        elementStr = "<input type=\"text\" class=\""+inputClass+"\" id=\""+id+"\" name=\""+id+"\"  required=\"true\" "+event+" value=\""+value+"\" "+disableVal+" placeholder=\""+desc+"\" "+readonly+" style=\"background-color:"+bgColor+";"+style+"\"/>";
    } else if(type == "textarea") {
        var disableVal = "";
        
        if(((id == "C3_1" || id == "C10_1") && surveyType == "T")||(id == "C3_1" && surveyType == "W" && (scope == "A" || scope == "D"))) {
            inputClass = "";//"input_readOnly";
            readonly = " readonly=\"true\" ";
            bgColor = "#DFDFDF";
            disableVal = "disabled=\"true\"";
        }
        
        if(surveyType == "F") {
            inputClass = "input_readOnly";
            readonly = " readonly=\"true\" ";
        }
        
        if(requiredYn == "Y") {  // 필수인 경우에만 이벤트가 발생하도록 함.
            event = " onkeyup=\"javascript:survey."+funcName+"('"+pid+"', '"+id+"', '"+divId+"', '"+code+"','"+surveyData.VERSION+"', eval("+message_view+"));\" "; 
        }
        
        elementStr = "<textarea class=\""+inputClass+"\" id=\""+id+"\" name=\""+id+"\" required=\"true\" "+event+" "+disableVal+" placeholder==\""+desc+"\" "+readonly+" style=\"background-color:"+bgColor+";"+style+"\">"+value+"</textarea>";
    //combobox tag 생성
    } else if(type == "combobox") {
        var comboList = eval("data."+category);
        
        // 선언 범위(C2_1)에 대한 콤보박스 설정
        if(id == "C2_1" && (surveyType == "T" || surveyType == "W" || surveyType == "O")) {
            elementStr += "<input type=\"hidden\" id=\""+id+"\" name=\""+id+"\" value=\""+value+"\"/>";
            comDisabled = " disabled=\"true\" ";
            id = id + "_1";
        } else {
            // 1번~6번 질의에 대한 콤보박스 설정
            if(id.charAt(0) == "D") {
                if(surveyType == "T") {
                    comDisabled = " disabled=\"true\" ";
                }
                if(surveyType == "W") {
                    elementStr += "<input type=\"hidden\" id=\""+id+"\" name=\""+id+"\" value=\""+value+"\"/>";
                    comDisabled = " disabled=\"true\" ";
                    id = id + "_1";
                }
            }
        }
        
        if(surveyType == "F") {
            elementStr += "<input type=\"hidden\" id=\""+id+"\" name=\""+id+"\" value=\""+value+"\"/>";
            comDisabled = " disabled=\"true\" ";
            id = id + "_1";
        }
        
        // 속도문제로 일반 콤보박스 사용함
        if(surveyType == "F" || surveyType == "M") {
            elementStr += "<select id=\""+id+"\" name=\""+id+"\" "+comDisabled+" onChange=\"javascript:survey."+funcName+"('"+pid+"', '"+id+"', '"+divId+"', '"+code+"','"+surveyData.VERSION+"', eval("+message_view+"));\" style=\"background-color:"+bgColor+";"+style+"\">";
        } else {
            elementStr += "<select id=\""+id+"\" name=\""+id+"\" "+comDisabled+" title=\""+desc+"\" style=\"background-color:"+bgColor+";"+style+"\">";
        }
        
        elementStr += "<option value=\"\"></option>";
        
        if(!oUtil.isNull(comboList)) {
            for(var i=0; i < comboList.length; i++) {
	            var optSel = "";
	            if(!oUtil.isNull(value) && value == comboList[i].CODE) {
	                optSel = "selected='selected'";
	            }
	            elementStr += "<option value=\""+comboList[i].CODE+"\" "+ optSel +">"+comboList[i].NAME+"</option>";
	        }
        }
        
        elementStr += "</select>";
    //label tag 생성
    } else if(type == "label") {
        elementStr = "<label>"+ id +"</label>";
    }
    
    return elementStr;
}

/**
 * 보고서 유형 체크하는 클래스
 */
var surveyClass = function() {
    
    this.question1 = ["D32_1", "D33_1", "D34_1", "D35_1"]; // 의도적으로 3TG를 사용했는가?
    this.question2 = ["D2_1", "D3_1", "D4_1", "D5_1"];     // 제품에 3TG가 포함되어 있는가?
    this.question3 = ["D22", "D23", "D24", "D25"];     	   // 조사된 제련소가 3TG사용 내용과 일치하는가?
    this.question4 = ["D17_1", "D18_1", "D19_1", "D20_1"]; // 모든 제련소가 파악되었는가?
    
///////////////////////////////////////////////////////// 회사정보(ver3.xx) /////////////////////////////////////////////////////////
    
    this.funcCall_C1_1 = function(pid, id, divId, code, version, message_view) { this.requiredMandatory(pid, id, divId, code, ""); };
    this.funcCall_C2_1 = function(pid, id, divId, code, version, message_view) {
    	var value = this.getCmrtType($("#"+pid+"_survey").find("#"+id).val()); // 보고서 타입 
    	var div_id = "#"+pid+"_survey_DIV_C3";
    	
    	if(value != "D" && value != "P") {
        	$(div_id).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
    	}
    	
        if(this.requiredMandatory(pid, id, divId, code, "")) {
        	this.productCountForScope(pid, id, divId, code, message_view);
        }
    };
    this.funcCall_C3_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C4_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C5_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C6_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C7_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C8_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C9_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C10_1 = function(pid, id, divId, code, version, message_view) {
    	if(this.requiredMandatory(pid, id, divId, code, "")) {
    		this.isCompareDays(pid, id, divId, code, message_view);
    	}
    };
    this.funcCall_C11_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C12_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C13_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C14_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    this.funcCall_C15_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_C1_1(pid, id, divId, code, version, message_view); };
    
///////////////////////////////////////////////////////// 1~7번(ver3.xx) /////////////////////////////////////////////////////////
    /**
     * <p>
     * Question1,2번 D32~D35,D2~D5까지의 답변 유효성을 체크한다.
     * 1) 필수 입력체크
     * 2) 사용유무에 따라 하위의 답변내용이 입력되었지 체크
     * 3) 3TG사용여부와 제련소에 등록된 물질코드가 동일한지 체크
     * </p>
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     */
    this.funcCall_D32_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D5_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D33_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D5_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D34_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D5_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D35_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D5_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D2_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D5_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D3_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D5_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D4_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D5_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D5_1 = function(pid, id, divId, code, version, message_view) {
    	this.requiredAnswerMandatory(pid, id, divId, code, message_view); // 변경된 필드가 필수인지 체크
    	
    	var metalCode = this.getAnswerInfo(pid, id, "METAL_CODE");
    	
    	// 하위 답변 체크
        for(var i = 7; i <= 40; i++) {
            var tid = "D"+i+"_1";
            var tcode = "D"+i;
            
            var subMetalCode = this.getAnswerInfo(pid, tid, "METAL_CODE");
            
            if(metalCode != subMetalCode) continue;
            	
            var tflag = true; // 답변 체크여부 결정(답변을 가지는 질문은 제외하고 체크함)
            for(var k = 0; k < this.question1.length; k++) {
                if(this.question1[k] == tid) {
                    tflag = false;
                    break;
                }
            }
            
            var sflag = false; // Smelter List의 물질 체크여부 결정(답변을 표시할 질문을 결정함)
            for(var k = 0; k < this.question3.length; k++) {
                if(this.question3[k]+"_1" == tid) {
                    sflag = true;
                    break;
                }
            }
            
            if(tflag) {
            	var message_view_other = eval(this.getAnswerInfo(pid, tid, "MESSAGE_VIEW"));
            	// 사용유무에 따라 하위 D7~D40까지의 데이터 입력여부 체크
            	
            	if(this.requiredCondition(pid, tid, divId, tcode, message_view_other)) {
            		if(this.isAllConfirmCmrt(pid, tid, divId, tcode, message_view_other)) {
	                	if(sflag) this.existsSmelterForMetal(pid, tid, divId, tcode, message_view_other);
	                }
            	}
            }
        }
	        
        this.cmSmelterForMetal(pid, id, version);    // 사용유무 체크후 "Smelter List" 수정
    };
    
    /**
     * <p>
     * Question3번 D7~D10까지의 답변 유효성을 체크한다.
     * 1) 1번과 2번의 답변에 하나라도 Yes가 있다면 필수로 입력되어야 함
     * </p>
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     */
    this.funcCall_D7_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D8_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D9_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D10_1 = function(pid, id, divId, code, version, message_view) {
    	this.requiredCondition(pid, id, divId, code, message_view);
    };
    
    this.funcCall_D12_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D13_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D14_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D15_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    
    this.funcCall_D17_1 = function(pid, id, divId, code, version, message_view) { 
    	if(this.requiredCondition(pid, id, divId, code, message_view)) {
    		var metalCode = this.getAnswerInfo(pid, id, "METAL_CODE");
    		
    		for(var i = 18; i <= 30; i++) {
                var tid = "D"+i+"_1";
                var tcode = "D"+i;
                
                var subMetalCode = this.getAnswerInfo(pid, tid, "METAL_CODE");
                
                if(metalCode != subMetalCode) continue;
                
                var sflag = false; // Smelter List의 물질 체크여부 결정(답변을 표시할 질문을 결정함)
                for(var k = 0; k < this.question3.length; k++) {
                    if(this.question3[k]+"_1" == tid) {
                        sflag = true;
                        break;
                    }
                }
                
                var message_view_other = eval(this.getAnswerInfo(pid, tid, "MESSAGE_VIEW"));
                
                if(this.isAllConfirmCmrt(pid, tid, divId, tcode, message_view_other)) {
                	if(sflag) this.existsSmelterForMetal(pid, tid, divId, tcode, message_view_other);
                }
            }
    	}
    };
    this.funcCall_D18_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D17_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D19_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D17_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D20_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D17_1(pid, id, divId, code, version, message_view); };
    
    this.funcCall_D22_1 = function(pid, id, divId, code, version, message_view) {
    	if(this.requiredCondition(pid, id, divId, code, message_view)) {
    		if(this.isAllConfirmCmrt(pid, id, divId, code, message_view)) {
    			this.existsSmelterForMetal(pid, id, divId, code, message_view);
    		}
    	}
    };
    this.funcCall_D23_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D22_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D24_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D22_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D25_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D22_1(pid, id, divId, code, version, message_view); };
    
    this.funcCall_D27_1 = function(pid, id, divId, code, version, message_view) {
    	if(this.requiredCondition(pid, id, divId, code, message_view)) {
    		this.isAllConfirmCmrt(pid, id, divId, code, message_view);
    	}
    };
    this.funcCall_D28_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D27_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D29_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D27_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D30_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D27_1(pid, id, divId, code, version, message_view); };
    
    this.funcCall_D37_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D38_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D39_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    this.funcCall_D40_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_D10_1(pid, id, divId, code, version, message_view); };
    
///////////////////////////////////////////////////////// A~J번(공통) /////////////////////////////////////////////////////////
    
    this.funcCall_B2_1 = function(pid, id, divId, code, version, message_view) { this.requiredAnswerMandatory(pid, id, divId, code, message_view); };
    this.funcCall_B3_1 = function(pid, id, divId, code, version, message_view) {
        var value1 = $("#"+pid+"_survey").find("#B3_1").val();
        var value2 = $("#"+pid+"_survey").find("#B3_2").val();
        var message = "";
        
        if(oUtil.isNull(value1)) message = "Answer : ";
        if(oUtil.isNull(value2)) message = (oUtil.isNull(value1)) ? "Answer, Comments : " : "Comments : ";
        
        if(oUtil.isNull(value1) && this.existsMessageForResource(message_view, "#IS_REQUIRED")) {
            $("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.makeMessageForResource(message_view, "#IS_REQUIRED")+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
        } else {
        	if(value1 == "Y" && oUtil.isNull(value2) && this.existsMessageForResource(message_view, "#IS_COMMENT_REQUIRED")) {
        		$("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.makeMessageForResource(message_view, "#IS_COMMENT_REQUIRED")+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
        	} else {
        		$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
        	}
        }
    };
    this.funcCall_B3_2 = function(pid, id, divId, code, version, message_view) { this.funcCall_B3_1(pid, id, divId, code, version, message_view); };  // QuestionB-Comments
    this.funcCall_B4_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_B2_1(pid, id, divId, code, version, message_view); };
    this.funcCall_B5_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_B2_1(pid, id, divId, code, version, message_view); };
    this.funcCall_B6_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_B2_1(pid, id, divId, code, version, message_view); };
    this.funcCall_B7_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_B2_1(pid, id, divId, code, version, message_view); };
    this.funcCall_B8_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_B2_1(pid, id, divId, code, version, message_view); };
    this.funcCall_B9_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_B2_1(pid, id, divId, code, version, message_view); };
    this.funcCall_B10_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_B2_1(pid, id, divId, code, version, message_view); };
    this.funcCall_B11_1 = function(pid, id, divId, code, version, message_view) { this.funcCall_B2_1(pid, id, divId, code, version, message_view); };
    
///////////////////////////////////////////////////////// util /////////////////////////////////////////////////////////
    
    /**
     * 입력폼이 필수인지 체크
     * - 선택된 line의 우측 체크란에 경고문구를 띄움
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     * @return 정상유무(true:정상, false:에러) 
     */
    this.requiredMandatory = function(pid, id, divId, code) {
        if($("#"+pid+"_survey").find("#"+id).length > 0) {
            var value = $("#"+pid+"_survey").find("#"+id).val();
            
            if(oUtil.isNull(value)) {
                $("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.messageChangeValue(ERROR_REQUIRED_YN, [FIELD])+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
                return false;
            } else {
                $("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                return true;
            }
        }
        
        return true;
    };
    
    /**
     * 입력폼이 필수인지 체크
     * - 선택된 line의 우측 체크란에 경고문구를 띄움
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     * @param message_view = 화면에 표시할 메시지 정보(json타입)
     * @return 정상유무(true:정상, false:에러) 
     */
    this.requiredAnswerMandatory = function(pid, id, divId, code, message_view) {
        if($("#"+pid+"_survey").find("#"+id).length > 0) {
            var value = $("#"+pid+"_survey").find("#"+id).val();
            
            if(oUtil.isNull(value) && this.existsMessageForResource(message_view, "#IS_REQUIRED")) {
                $("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.makeMessageForResource(message_view, "#IS_REQUIRED")+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
                return false;
            } else {
                $("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                return true;
            }
        }
        
        return true;
    };
    
    /**
     * 조건부(3TG사용유무에 따라 결정) 필수 인지 체크
     * - 선택된 line의 우측 체크란에 경고문구를 띄움
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     * @param message_view = 화면에 표시할 메시지 정보(json타입)
     * @return 정상유무(true:정상, false:에러)
     */
    this.requiredCondition = function(pid, id, divId, code, message_view) {
        if($("#"+pid+"_survey").find("#"+id).length > 0) {
            var value = $("#"+pid+"_survey").find("#"+id).val();
            var useYn = this.isUseConflictMinerals(pid, id);
            
            if(useYn == "Y") { // 3TG 사용중
                if(oUtil.isNull(value) && this.existsMessageForResource(message_view, "#IS_ANSWER_REQUIRED")) {  // 값이 없으면 오류
                    $("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.makeMessageForResource(message_view, "#IS_ANSWER_REQUIRED")+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
                    return false;
                } else {
                    $("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                    return true;
                }
            } else if(useYn == "N") {  // 3TG 미사용중
                if(!oUtil.isNull(value) && this.existsMessageForResource(message_view, "#IS_ANSWER_UNREQUIRED")) {  // 값이 있으면 오류
                    $("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.makeMessageForResource(message_view, "#IS_ANSWER_UNREQUIRED")+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
                    return false;
                } else {
                    $("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                    return true;
                }
            } else {
                return false;
            }
        }
        
        return true;
    };
    
    /**
     * 선택된 값이 Product Level인 경우 Product List에 제품이 등록되어 있는지 체크
     * - 선언범위의 우측 체크란에 경고문고를 띄움
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     * @param message_view = 화면에 표시할 메시지 정보(json타입)
     * @return 정상유무(true:정상, false:에러)
     */
    this.productCountForScope = function(pid, id, divId, code, message_view) {
        if($("#"+pid+"_survey").find("#"+id).length > 0) {
            var value = this.getCmrtType($("#"+pid+"_survey").find("#"+id).val()); // 보고서 타입:// I : Product Level, C : Company Level, D: Division Level, P:Product Cat. Level, U:unconfirmed
            var text = $("#"+pid+"_survey").find("#"+id+" option:selected").text();
            var div_id = "#"+pid+"_survey_DIV_C3";
            
            // 제품 등록수 구하기
            var productGrid = pid + "_product";
            var grid = datagrid.getDataGrid(productGrid);
            var rownum = datagrid.getRownum(grid);
            
            if(value == "I") {
                if(rownum > 0) {
                	$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                } else {
                	if(this.existsMessageForResource(message_view, "#IS_REQUIRED_PRODUCT")) {
                		var msgText = this.makeMessageForResource(message_view, "#IS_REQUIRED_PRODUCT").replace(/\D9/g, text);
                		$("#"+divId+"_"+code).html("<a href=\"#\" title=\"" +msgText+ "\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
                		return false;
                	} else {
                		$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                	}
                }
            } else {
            	if(rownum <= 0) {
            		$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
            	} else {
            		if(this.existsMessageForResource(message_view, "#IS_NOREQUIRED_PRODUCT")) {
            			var msgText = this.makeMessageForResource(message_view, "#IS_NOREQUIRED_PRODUCT").replace(/\D9/g, text);
                		$("#"+divId+"_"+code).html("<a href=\"#\" title=\"" +msgText+ "\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
                	} else {
                		$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                	}
            	}
            	
            	if(value == "D" || value == "P") {
                	var desc = $("#"+pid+"_survey").find("#C3_1").val(); // 선언범위 설명
                	
                	if(!oUtil.isNull()) {
                		$(div_id).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                	} else {
                		$(div_id).html("<a href=\"#\" title=\"" + this.messageChangeValue(MSG_SCOPE_DESC_ER, [text]) + "\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
                    	return false;
                	}
                }
            }
        }
        
        return true;
    };
    
    /**
     * 제련소 등록 질의에 답변이 있는 경우, 제련소 리스트에 물질이 있는지 체크
     * - 답변의 우측 체크란에 경고문고를 띄움
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     * @param message_view = 화면에 표시할 메시지 정보(json타입)
     * @return 정상유무(true:정상, false:에러)
     */
    this.existsSmelterForMetal = function(pid, id, divId, code, message_view) {
    	if($("#"+pid+"_survey").find("#"+id).length > 0) {
            var metals = this.summeryMetalForSmelter(pid); // 제련소 리스트에 보고된 물질 종류
            var metalCode = this.getAnswerInfo(pid, id, "METAL_CODE");

            var value = $("#"+pid+"_survey").find("#"+id).val();
            
			if(oUtil.isNull(value)) return true;
            
            var chk = false;
            for(var j = 0; j < metals.length; j++) {
            	if(metals[j].code == metalCode) {
            		chk = true;
            		break;
            	}
            }
            
            if(chk) {
            	$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
            	return false;
            } else {
            	if(this.existsMessageForResource(message_view, "#IS_SMELTER_METAL")) {
            		$("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.makeMessageForResource(message_view, "#IS_SMELTER_METAL")+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
            		return false;
            	} else {
            		$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                	return true;
            	}
            }
        }
    	
        return true;
    };
    
    /**
     * CMRT 수취를 100% 완료된 상태가 아닌데 하위 질문의 답변을 Yes로 답했는지 체크
     * - 답변의 우측 체크란에 경고문고를 띄움
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     * @param message_view = 화면에 표시할 메시지 정보(json타입)
     * @return 정상유무(true:정상, false:에러)
     */
    this.isAllConfirmCmrt = function(pid, id, divId, code, message_view) {
    	if($("#"+pid+"_survey").find("#"+id).length > 0) {
    		var surveyForm = pid + "_survey";
    		var rate = this.getCmrtConfirmRate(pid, id);
    		var answer = form.getFormValue(surveyForm, id);
    		
    		if(rate != "Y" && answer == "Y") {
	    		if(this.existsMessageForResource(message_view, "#IS_ALL_COMFIRM_SMELTER")) {
	    			$("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.makeMessageForResource(message_view, "#IS_ALL_COMFIRM_SMELTER")+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
	        		return false;
	        	} else {
	        		$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
	        		return true;
	        	}
    		} else {
        		$("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
        		return true;
        	}
    	}
    	
    	return true;
    };
    
    /**
     * 입력된 날짜가 현재일과 비교해서 이후인지 체크
     * - 답변의 우측 체크란에 경고문고를 띄움
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param divId = 설물지 DIV
     * @param code = element code(Column의 name과 매칭)
     * @param message_view = 화면에 표시할 메시지 정보(json타입)
     * @return 정상유무(true:정상, false:에러)
     */
    this.isCompareDays = function(pid, id, divId, code, message_view) {
    	if($("#"+pid+"_survey").find("#"+id).length > 0) {
    		var value = $("#"+pid+"_survey").find("#"+id).val();
    		
    		// 날짜형식체크
    		if(value.length != 8 || dateHelper.makeDateFormat(value) == false) {
    			$("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.messageChangeValue(MSG_VALIDATION_DATE, null)+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
    			return false;
    		}
    		var days = dateHelper.getDiffDay(dateHelper.getToday("-") , value, "-");
    		
            if(!oUtil.isNull(value) && this.existsMessageForResource(message_view, "#IS_TODATE_COMPARE") && days > 0) {
                $("#"+divId+"_"+code).html("<a href=\"#\" title=\""+this.makeMessageForResource(message_view, "#IS_TODATE_COMPARE")+"\" class=\"easyui-tooltip\"><img src=\"/images/icons/no.png\" boder=\"0\"/></a>");
                return false;
            } else {
                $("#"+divId+"_"+code).html("<img src=\"/images/icons/ok.png\" boder=\"0\"/>");
                return true;
            }
    	}
    };
    
    /**
     * CM사용유무에 따라 제련소 물질명의 적합여부가 결정됨 
     * - Smelter List의 Metal cell를 삭제라인으로 표시
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param version = CMRT Revision number
     */
    this.cmSmelterForMetal = function(pid, id, version) {
        if($("#"+pid+"_survey").find("#"+id).length > 0) {
            var surveyForm = pid + "_survey";
            var smelterGrid = pid + "_smelter";
            var useYn = this.isUseConflictMinerals(pid, id);
            var metalCode = this.getAnswerInfo(pid, id, "METAL_CODE");
            
            var grid = datagrid.getDataGrid(smelterGrid);
            var griddata = datagrid.getData(grid);
            var rownum = datagrid.getRownum(grid);
            
            var message_view = [{MESSAGE_TYPE:'#IS_SURVEY_VALID_2', MESSAGE_NAME:'MSG_SURVEY_VALID', MESSAGE_ARG1:'QUEST1', MESSAGE_ARG2:'#METAL_NAME'},
                                {MESSAGE_TYPE:'#IS_SURVEY_VALID_3', MESSAGE_NAME:'MSG_SURVEY_VALID', MESSAGE_ARG1:'QUEST1N2', MESSAGE_ARG2:'#METAL_NAME'}];
            // 제련소 등록 갯수에 맞게 process bar를 통해 진행율을 표시
            if(rownum > 0) {
            	var bar = null;
            	if(rownum > 10) {
		            $.messager.progress({ title:'Please wait' ,msg:"Updating Smelter List...", interval:0 });
		            bar = $.messager.progress("bar");
            	}
            	
	            var k = 0;
	            var itv = setInterval(function() {
	                if(griddata[k].METAL_CODE == metalCode) {
	                    var datas = griddata[k];
	                    var metal_code = datas.METAL_CODE;
	                    var metal_name = datas.METAL_NAME;
	                    
	                    if(useYn == "Y") {
	                        datas.SURVEY_VALID = "OK";
	                    } else if(useYn == "N" || useYn == "U") {
	                    	if(version.substring(0, 2) == "2.") {
	                    		datas.SURVEY_VALID = survey.makeMessageForResource(message_view, "#IS_SURVEY_VALID_2").replace(/\METAL_NAME/g, metal_name);
	                    	} else {
	                    		datas.SURVEY_VALID = survey.makeMessageForResource(message_view, "#IS_SURVEY_VALID_3").replace(/\METAL_NAME/g, metal_name);
	                    	}
	                    }
	                    
	                    if(!oUtil.isNull(metal_code)) {
	                    	datas.METAL_VALID = datas.SURVEY_VALID;
	                    }
	                    
	                    datagrid.setValue(grid, k, datas);
	                }
	                
	                if(rownum > 10) {
		                var percent = Math.floor((k/rownum) * 100);
		                bar.progressbar("setValue", percent);
	                }
	                
	                if((k+1) == rownum) { 
	                	clearInterval(itv);
	                	
	                	if(rownum > 10) {
	                		$.messager.progress('close');
	                	}
	                }
	                
	                k++;
	            }, 60);
            }
        }
    };
    
    /**
     * 3TG사용유무 결정
     * @param pid = 화면 ID
     * @param id = Component ID
     * @return ture:사용, false:미사용
     */
    this.isUseConflictMinerals = function(pid, id) {
        var rstValue = false;
        var surveyForm = pid + "_survey";
        var metalCode = this.getAnswerInfo(pid, id, "METAL_CODE");
        var runCount = 0;
        
        // 1번 답변 확인
        for(var i = 0; i < this.question1.length; i++) {
            var tmpCode = this.getAnswerInfo(pid, this.question1[i], "METAL_CODE");
            
            if(tmpCode == metalCode) {
                var value = form.getFormValue(surveyForm, this.question1[i]);
                if(value == "Y") rstValue = true;
                if(!oUtil.isNull(value)) runCount++;
            }
        }
        
        // 2번 답변 확인
        for(var i = 0; i < this.question2.length; i++) {
            var tmpCode = this.getAnswerInfo(pid, this.question2[i], "METAL_CODE");
            
            if(tmpCode == metalCode) {
                var value = form.getFormValue(surveyForm, this.question2[i]);
                if(value == "Y") rstValue = true;
                if(!oUtil.isNull(value)) runCount++;
            }
        }
        
        if(runCount == 0) {
        	return "U";
        } else {
        	if(rstValue) return "Y";
        	else return "N";
        }
    };
    
    /**
     * 답변 데이터에서 지정된 필드값을 구한다.
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param field = 필드명
     * @return 필드명에 해당하는 value
     */
    this.getAnswerInfo = function(pid, id, field) {
        var metalCode = "";
        var surveyForm = pid + "_survey";
        var surveyAnswer = eval(form.getFormValue(surveyForm, "survey02"));
        
        // ID에 해당하는 물질코드를 구한다.
        for(var i = 0; i < surveyAnswer.length; i++) {
            if(surveyAnswer[i].QUESTION_CODE+"_1" == id) {
                metalCode = eval("surveyAnswer[i]."+field);
            }
        }
        
        return metalCode;
    };

    /**
     * 회사정보에 등록된 값을 구한다.
     * @param pid = 화면 ID
     * @param id = Component ID
     * @param field = 필드명
     * @return 필드명에 해당하는 value
     */
    this.getCompanyInfo = function(pid, id, field) {
        var answer = "";
        var companyForm = pid + "_survey";
        var companyAnswer = eval(form.getFormValue(companyForm, "survey01"));
        
        // ID에 해당하는 물질코드를 구한다.
        for(var i = 0; i < companyAnswer.length; i++) {
            if(companyAnswer[i].NAME_CODE == id) {
                answer = eval("companyAnswer[i]."+field);
            }
        }
        
        return answer;
    };
    
    /**
     * 버전별 선언범위에 따라 CMRT보고서 타입 조회
     * @param scope 선언범위 - Revision 2.xx : C:회사=A, D:사업부=B, P:제품군=C, I:ITEM=D, U:미입력=null
     *                       - Revision 3.xx : C:회사=A, D:사업부=C, P:제품군=C, I:ITEM=B, U:미입력=null
     * @return 보고서 타입
     */
    this.getCmrtType = function(scope) {
        var type = "U";

        if(scope == "A") type = "C";
        else if(scope == "B") type = "D";
        else if(scope == "C") type = "P";
        else if(scope == "D") type = "I";
        
        return type;
    };
    
    /**
     * 버전별로 보고서 선언범위를 지정한다.
     * @param version CMRT 버전
     * @param scope   선언범위 코드
     */
    this.getScopeToRevision = function(version, scope) {
    	var type = "U";
    	
    	if(oUtil.isNull(version)) {
    		return "A";
    	}
    	
    	if(version.substring(0, 2) == "2.") {
	        if(scope == "A") type = "A";
	        else if(scope == "B") type = "B";
	        else if(scope == "C") type = "C";
	        else if(scope == "D") type = "D";
	        else type = "A";
    	} else {
    		if(scope == "A") type = "A";
	        else if(scope == "B") type = "C";
	        else if(scope == "C") type = "C";
	        else if(scope == "D") type = "D";
	        else type = "A";
    	}
    	
        return type;
	};
    
    /**
     * 제련소 리스트에 등록된 물질의 종류 조회
     * @param pid = 화면 ID
     */
    this.summeryMetalForSmelter = function(pid) {
    	var smelterGrid = pid + "_smelter";
    	var fdatas = new Array();
    	
        var grid = datagrid.getDataGrid(smelterGrid);
        var rowData = datagrid.getData(grid);
        var rownum = datagrid.getRownum(grid);
        var index = 0;
        
        for (var k = 0; k < rownum; k++) {
            var mCode = rowData[k].METAL_CODE;
            var mName = rowData[k].METAL_NAME;
            var chk = true;
            
            for(var j = 0; j < fdatas.length; j++) {
            	if(fdatas[j].code == mCode) {
            		chk = false;
            		break;
            	}
            }
            
            if(k == 0 || chk) {
            	fdatas[index] = {code:mCode, name:mName};
            	index++;
            }
        }
        
        return fdatas;
    }
    
    /**
     * 제련소 리스트에 등록된 물질의 종류 조회
     * @param pid = 화면 ID
     */
    this.getCmrtConfirmRate = function(pid, id) {
    	var rstValue = null;
    	var surveyForm = pid + "_survey";
        var metalCode = this.getAnswerInfo(pid, id, "METAL_CODE");
        
        // 1번 답변 확인
        for(var i = 0; i < this.question4.length; i++) {
            var tmpCode = this.getAnswerInfo(pid, this.question4[i], "METAL_CODE");
            
            if(tmpCode == metalCode) {
            	rstValue = form.getFormValue(surveyForm, this.question4[i]);
            }
        }
        
        return rstValue;
    };
    
    /**
	 * 물질코드를 물질명으로 치환
	 * @param code
	 * @return
	 */
	this.getNameToCode = function(code) {
		var name = "";
		
		if(code == "36989") name = "Tantalum";
    	else if(code == "36993") name = "Tin";
    	else if(code == "37013") name = "Gold";
    	else if(code == "36996") name = "Tungsten";
		
		return name;
	}
	
	this.getCellNameToCode = function(code) {
		var cell = "";
		
		if(code == "36989") cell = this.question3[0];
        else if(code == "36993") cell = this.question3[1];
        else if(code == "37013") cell = this.question3[2];
        else if(code == "36996") cell = this.question3[3];
		
		return cell;
	};
	
	/**
     * 메시지내 배열로 지정된 문자열을 특정 문자로 치환 후 반환
     * @param msg = 원본 메시지
     * @param args = 치환을 위한 문자열 배열
     * @return 치환이 완료된 메시지
     */
    this.messageChangeValue = function(msg, args) {
    	if(oUtil.isNull(args)) return msg;
    	
    	var rstMsg = msg;
    	
    	for(var i = 0; i < args.length; i++) {
    		if(i == 0 && !oUtil.isNull(args[0])) rstMsg = msg.replace(/\{0}/g, args[0]);
    		else if(i == 1 && !oUtil.isNull(args[1])) rstMsg = rstMsg.replace(/\{1}/g, args[1]);
    		else if(i == 2 && !oUtil.isNull(args[2])) rstMsg = rstMsg.replace(/\{2}/g, args[2]);
    		else if(i == 3 && !oUtil.isNull(args[3])) rstMsg = rstMsg.replace(/\{3}/g, args[3]);
    		else if(i == 4 && !oUtil.isNull(args[4])) rstMsg = rstMsg.replace(/\{4}/g, args[4]);
    	}
    	
    	rstMsg = rstMsg.replace(/\"/g, "＂");
    	
    	return rstMsg;
    };
	
    /**
     * 메시지 타입에 해당하는 메시지가 있는 여부 체크
     * @param resource = 메시지 정보(json타입)
     * @param type = 메시지 타입
     * @return 메시지 존재여부
     */
    this.existsMessageForResource = function(resource, type) {
    	if(oUtil.isNull(resource)) return false;
    	else {
    		for(var i = 0; i < resource.length; i++) {
        		var msgType = resource[i].MESSAGE_TYPE;
        		
        		if(msgType == type) {
        			return true;
        		}
    		}
    	}
    	
    	return false;
    };
    
    /**
     * 메시지 타입에 해당하는 메시지를 생성한 후 리턴
     * @param resource = 메시지 정보(json타입)
     * @param type = 메시지 타입
     * @return 경고 메시지
     */
    this.makeMessageForResource = function(resource, type) {
    	if(oUtil.isNull(resource)) return this.messageChangeValue(MSG_NEED_CONFIRM, [INPUT, INFMT]);
    	
    	var rstMsg = "";
    	
    	for(var i = 0; i < resource.length; i++) {
    		var msgType = resource[i].MESSAGE_TYPE;
    		
    		if(msgType == type) {
	    		var msgName = resource[i].MESSAGE_NAME;
	    		var msgArg1 = resource[i].MESSAGE_ARG1;
	    		var msgArg2 = resource[i].MESSAGE_ARG2;
	    		var msgArg3 = resource[i].MESSAGE_ARG3;
	    		var msgArg4 = resource[i].MESSAGE_ARG4;
	    		var msgArg5 = resource[i].MESSAGE_ARG5;
	    		
	    		if(!oUtil.isNull(msgName)) {
		    		if(msgName.substring(0, 1) == "#") msgName = msgName.replace("#", "");
		    		else msgName = eval(msgName);
	    		}
	    		if(!oUtil.isNull(msgArg1)) {
		    		if(msgArg1.substring(0, 1) == "#") msgArg1 = msgArg1.replace("#", "");
		    		else msgArg1 = eval(msgArg1);
	    		}
	    		if(!oUtil.isNull(msgArg2)) {
		    		if(msgArg2.substring(0, 1) == "#") msgArg2 = msgArg2.replace("#", "");
		    		else msgArg2 = eval(msgArg2);
	    		}
	    		if(!oUtil.isNull(msgArg3)) {
		    		if(msgArg3.substring(0, 1) == "#") msgArg3 = msgArg3.replace("#", "");
		    		else msgArg3 = eval(msgArg3);
	    		}
	    		if(!oUtil.isNull(msgArg4)) {
		    		if(msgArg4.substring(0, 1) == "#") msgArg4 = msgArg4.replace("#", "");
		    		else msgArg4 = eval(msgArg4);
	    		}
	    		if(!oUtil.isNull(msgArg5)) {
		    		if(msgArg5.substring(0, 1) == "#") msgArg5 = msgArg5.replace("#", "");
		    		else msgArg5 = eval(msgArg5);
	    		}
	    		
	    		rstMsg = this.messageChangeValue(msgName, [msgArg1, msgArg2, msgArg3, msgArg4, msgArg5]);
    		}
    	}
    	
    	return rstMsg;
    };
    
}

var survey = new surveyClass();

function surveyDown(elementStr, fileName) {
    var excelFrame=document.createElement("iframe"); 
    excelFrame.id="surveyExcelFrame";
    document.body.appendChild(excelFrame);
    var frmTarget = document.all.surveyExcelFrame.contentWindow.document;
    frmTarget.open("application/vnd.ms-excel","replace"); 
    frmTarget.write('<html>');
    frmTarget.write('<meta http-equiv=\"Content-Type\" content=\"application/vnd.ms-excel; charset=UTF-8\">\r\n');
    frmTarget.write('<body>');
    frmTarget.write(elementStr);
    frmTarget.write('</body>');
    frmTarget.write('</html>');
    frmTarget.close();      
    frmTarget.charset="UTF-8";
    frmTarget.focus();
    var saveFileName= fileName + '.xls';
    frmTarget.execCommand('SaveAs','false',saveFileName); // 저장을 호출합니다.
}

function surveyDown1(elementStr, fileName) {
    var method = "post"; 
    var target = "_self"; 
    var tmpForm = document.createElement("<form></form>"); 
    tmpForm.id = "SURVEY_DOWN";
    tmpForm.method = method; 
    tmpForm.target = target;
    document.body.appendChild(tmpForm);
    
    var oParam1 = document.createElement("<input type='hidden' name='eiccStr' id='eiccStr'>"); 
     oParam1.value = elementStr;
     tmpForm.appendChild(oParam1);
     document.body.appendChild(tmpForm);
    
    
    tmpForm.action = "/modl/CM-500J0_eicc_templete_down.do"; 
    tmpForm.submit();
    tmpForm.parentNode.removeChild(tmpForm); 
    tmpForm = null;
}