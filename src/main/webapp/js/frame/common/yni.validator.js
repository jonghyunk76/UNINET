/**
 * YNI Framework 1.0
 * 
 * Copyright (c) 2013-2014 maker All rights reserved.
 * 
 * addition validate
 * 
 */
$.extend($.fn.validatebox.defaults.rules, {
	// 사용법 : validType="['empty']"
	// 메시지 : 필드에 값을 입력할 수 없습니다.
    empty: { 
        validator: function (value) {
            var sExpStr = "^$";
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_EMPTY")
    },
    
    notEmpty: { 
        validator: function (value, param) {
        	var sourceVal = null;
        	
        	if(!oUtil.isNull(param[0])) {
        		if(form.handle.isComboBox(param[0], param[1])) {
        			sourceVal = combo.handle.getValue(param[0], param[1]);
        		} else if(form.handle.isDateBox(param[0], param[1])) {
        			sourceVal = calendar.handle.getDate(param[0], param[1]);
        		} else {
	                sourceVal = form.handle.getValue(param[0], param[1]);
        		}
            } else {
                sourceVal = value;
            }
        	
        	if(oUtil.isNull(sourceVal)) return false;
            else return true;
        },
        message: resource.getMessage("MSG_REQUIRED_COLUMN")
    },
    
    // 사용법 : validType="['maxlength[arg0]']"
    //          - arg0 = 최대입력 문자 갯수
    // 메시지 : 최대 {0}자 이상의 문자는 입력할 수 없습니다.
    maxlength: {
        validator: function (value, param) {
        	var len = value.length;
        	
        	if(len > param[0]) {
        		return false;
        	} else {
        		return true;
        	}
            //return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_MAXLENG")
    },
    
    // 사용법 : validType="['minlength[arg0]']"
    //  - arg0 = 최조입력 문자 갯수
    // 메시지 : 최소 {0}자 미만의 문자는 입력할 수 없습니다.
    minlength: {
        validator: function (value, param) {
            var sExpStr = "^.{{0},}$".format(param[0]);
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_MINLENG")
    },
    
    // 사용법 : validType="['betlength[arg0, arg1]']"
    //  - arg0 = 최조입력 문자 갯수
    //  - arg1 = 최대입력 문자 갯수
    // 메시지 : 최소 {0}자에서 최대 {1}자까지만 입력가능합니다.
    betlength: {
        validator: function (value, param) {
            var sExpStr = "^.{{0},{1}}$".format(param[0], param[1]);
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_BETLENG")
    },
    
    // 사용법 : validType="['equals[arg0, arg1, arg2]']" --> 현재 적용안됨
    //          - arg0 : form id 또는 value
    //  		- arg1 : field name
    //			- arg2 : element id
    // 메시지 : {1} 필드에 입력한 값과 일치하지 않습니다.
    equals: {
        validator: function (value, param) {
            var sourceVal = "";
            
            if(oUtil.isNull(param[2])) {
            	var obj = form.getObject(param[0], param[2]);
                sourceVal = obj.val();
            } else {
                sourceVal = param[0];
            }
            return value == sourceVal;
        },
        message: resource.getMessage("MSG_VALIDATION_EQUALS")
    },
    
    // 사용법 : validType="['notequals[arg0, arg1, arg2]']" --> 현재 적용안됨
    //          - arg0 : form id 또는 value
    //  		- arg1 : field name
    //			- arg2 : element id
    // 메시지 : {1} 필드에 입력된 값과 일치하는 값을 등록할 수 없습니다.
    notequals: {
        validator: function (value, param) {
            var sourceVal = "";
            if (oUtil.isNull(param[2])) {
                var obj = form.getObject(param[0], param[2]);
                sourceVal = obj.val();
            } else {
                sourceVal = param[0];
            }
            return value != sourceVal;
        },
        message: resource.getMessage("MSG_VALIDATION_NEQUALS")
    },
    
	// 사용법 : validType="['integer']"
	// 메시지 : 
    integer: {
        validator: function (value) {
            var sExpStr = "^[ \\t]*(?:\\d+)[ \\t]*$";
            var sExpOpt = "m";
            
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_INTEGER")
    },
    
	// 사용법 : validType="['float']"
	// 메시지 : 
    float: {
        validator: function (value) {
            if (value.toString().trim() == "") {
                return true;
            }
            var sExpStr = "^[ \\t]*(?:\\d+|\\d{1,3})(?:\\.\\d{1,}) {0,1}[ \\t]*$";
            var sExpOpt = "m";
            
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_FLOAT")
    },
    
	// 사용법 : validType="['floatformat[arg0, arg1]']"
    //          - arg0 : 실수 앞자리 문자수
    //  		- arg1 : 실수 뒷자리 문자수 
	// 메시지 : 
    floatformat: {
        validator: function (_val, param) {
            if (oUtil.isNull(_val)) {
                return true;
            }
            
            var _value = oUtil.getFilterFloat(_val).toString();
            var splitStr = _value.split(".");
            
            if(splitStr[0].length > parseInt(param[0])) {
            	return false;
            }
            
            if(splitStr.length > 1) {
            	if(splitStr[1].length > parseInt(param[1])) {
                	return false;
                }
            }
            
            return true;
        },
        message: resource.getMessage("MSG_VALIDATION_FLOAT_FORM")
    },
    
    // 사용법 : validType="['integerformat[arg0]']"
    //          - arg0 : 숫자자리수
    // 메시지 : 
    integerformat: {
        validator: function (_val, param) {
            if (oUtil.isNull(_val)) {
                return true;
            }
            
            var _value = oUtil.getFilterFloat(_val).toString();
            
            if(_value.length > parseInt(param[0])) {
            	return false;
            }
            
            return true;
        },
        message: resource.getMessage("MSG_VALIDATION_INT_FORM")
    },
    
	// 사용법 : validType="['percent[arg0]']"
    //          - arg0 : 실수 뒷자리 문자수
	// 메시지 : 
    percent: {
        validator: function (value, param) {
            var sExpStr = "";
            if (value.toString().trim() == "") {
                return true;
            }
            sExpStr = "^[ \\t]*(?:\\d{1,3})(?:\\.\\d{1,{1}})[ \\t]*$".format('3', param[0]);
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_FLOAT_FORM")
    },
    
    // 사용법 : validType="['money']" 
	// 메시지 : 
    money: {
        validator: function (value) {
            var sExpStr = "^[ \\t]*(?:\\$|￦)?(?:\\+|-)?(?:\\d+|\\d{1,3}(?:,\\d{3})*)?(?:\\.\\d{1,}) {0,1}[ \\t]*$";
            var sExpOpt = "m";
            
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_MONEY")
    },
    
    // 사용법 : validType="['number']" 
	// 메시지 : 
    number: {
        validator: function (value) {
            var sExpStr = "^[ \\t]*(?:\\+|-)?(?:\\d+|\\d{1,3}(?:,\\d{3})*)?(?:|\\.\\d{1,}) {0,1}[ \\t]*$";
            var sExpOpt = "m";
            
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_NUMBER")
    },
    
    // 사용법 : validType="['telnum']" 
	// 메시지 : 
    telnum: {
        validator: function (value) {
            var sExpStr = "";
            if (value != "") {
                //sExpStr = "^([0-9]{2,3})-?([0-9]{3,4})-?([0-9]{4})*$";
            	//sExpStr = "^\+[0-9](?:[- ]?[0-9]){,14}$";
                // 20160607::carlos::수정함
                sExpStr = "^[0-9\(\)\-\.\+\/ ]{3,20}$";
            }
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_TELNUM")
    },
    
    // 사용법 : validType="['celnum']" 
	// 메시지 : 
    celnum: {
        validator: function (value) {
            var sExpStr = "";
            if (value != "") {
                //sExpStr = "^([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})*$";
            	//sExpStr = "^\+[0-9](?:[- ]?[0-9]){,14}$";
                // 20160607::carlos::수정함                
            	sExpStr = "^[0-9\(\)\-\.\+\/ ]{3,20}$";
            }
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_CELNUM")
    },
    
    // 사용법 : validType="['date']" 
	// 메시지 : 
    date: {
        validator: function (value, param) {
            var sSeperator = "-";
            if (param != null && param != "undefined") {
                sSeperator = param[0];
            } else {
            	sSeperator = SEPERATOR_OF_DATE;
            }
            var sExpStr = "";
            if (value != "") {
            	if(NATION_DATE_VIEW == "MX" || NATION_DATE_VIEW == "US") {
            		sExpStr = "^([0-9]{2})" + sSeperator + "?([0-9]{2})" + sSeperator + "?([0-9]{4})*$";
            	} else {
            		sExpStr = "^([0-9]{4})" + sSeperator + "?([0-9]{2})" + sSeperator + "?([0-9]{2})*$";
            	}
            }
            var sExpStr1 = "";
            if(NATION_DATE_VIEW == "MX" || NATION_DATE_VIEW == "US") {
            	sExpStr1 = "^([0-9]{2})?([0-9]{2})?([0-9]{4})*$";
            } else {
            	sExpStr1 = "^([0-9]{4})?([0-9]{2})?([0-9]{2})*$";
            }
            var sExpOpt = "m";
            if ((new RegExp(sExpStr, sExpOpt)).test(value) || (new RegExp(sExpStr1, sExpOpt)).test(value)) {
                if (CheckDateValidation(replaceAll(value, sSeperator, ""))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        },
        message: resource.getMessage("MSG_VALIDATION_DATE")
    },
    
    // 사용법 : validType="['email']" 
	// 메시지 : 
    email: {
        validator: function (value) {
        	var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        	return re.test(value);
        },
        message: resource.getMessage("EMAIL, MSG_VAR_TEL_TYPE")
    },
    
    // 사용법 : validType="['month']" 
	// 메시지 : 
    month: {
        validator: function (value) {
            var sExpStr = "";
            if (value != "") {
            	if(NATION_DATE_VIEW == "MX" || NATION_DATE_VIEW == "US") {
            		sExpStr = "^([0-9]{2})"+SEPERATOR_OF_DATE+"?([0-9]{4})*$";
            	} else {
            		sExpStr = "^([0-9]{4})"+SEPERATOR_OF_DATE+"?([0-9]{2})*$";
            	}
            }
            var sExpOpt = "m";
            if ((new RegExp(sExpStr, sExpOpt)).test(value)) {
                if (CheckDateValidationYYYYMM(value)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        },
        message: resource.getMessage("MSG_VALIDATION_MONTH")
    },
    
    // 사용법 : validType="['year']" 
	// 메시지 : 
    year: {
        validator: function (value) {
            var sExpStr = "";
            if (value != "") {
            	sExpStr = "^([0-9]{4})*$"
            }
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_YEAR")
    },
    
    // 사용법 : validType="['comparedate[arg0, arg1, arg2]']" --> 현재 적용안됨
    //          - arg0 : form id 또는 value
    //  		- arg1 : field name
    //			- arg2 : element id
    // 메시지 : 
    comparedate : {
        validator: function (value, param) {
            var sourceVal = "";
            
            if (oUtil.isNull(param[2])) {
            	sourceVal = calendar.handle.getDate(param[0], param[2]);
            } else {
                sourceVal = param[0];
            }

            return calendar.util.compear2Date(value, sourceVal, SEPERATOR_OF_DATE);
        },
        message: resource.getMessage("MSG_START_DATE_CHECK")
    },
    
    existsValue : {
    	validator: function(value, param) {
            if(oUtil.isNull(value)) return true;
            
            // 콤보박스에 값이 있는지 체크해서 유효성 결과를 리턴합니다.
            //console.log("existsValue > value = " + value + ", param = " + param + ", type = " + (typeof param));
            return false;
        },
        message: resource.getMessage("MSG_NO_SEARCH_RESULT")
    },
    
    // 사용법 : validType="['businessId']"
    // 메시지 : 
    businessId : {
    	validator: function(value) {
    		var sExpStr = /([0-9]{3})-?([0-9]{2})-?([0-9]{5})/;
            var sExpOpt = "m";
            
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("BZRGN, MSG_VAR_TEL_TYPE")
    },
    
    // 사용법 : validType="['nokorea']"
    // 메시지 : 한글은 사용할 수 없습니다.
    nokorea : {
    	validator: function(value) {
    		const korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
    		
            return !korean.test(value);
        },
        message: resource.getMessage("MSG_CANT_WRITE_KOR")
    }
        
});