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
    // 사용법 : validType="['maxlength[arg0]']"
    //          - arg0 = 최대입력 문자 갯수
    // 메시지 : 최대 {0}자 이상의 문자는 입력할 수 없습니다.
    maxlength: {
        validator: function (value, param) {
            var sExpStr = "^.{0,{0}}$".format(param[0]);
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
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
        message: MSG_VALIDATION_MINLENG
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
        message: resource.getMessage("MSG_VALIDATION_MAXLENG")
    },
    // 사용법 : validType="['equals[arg0, arg1, arg2]']" --> 현재 적용안됨
    //          - arg0 : form id 또는 value
    //  		- arg1 : field name
    //			- arg2 : element id
    // 메시지 : {1} 필드에 입력한 값과 일치하지 않습니다.
    equals: {
        validator: function (value, param) {
            var sourceVal = "";
            alert(param[0]);
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
	// 사용법 : floatformat="['floatformat[arg0, arg1]']"
    //          - arg0 : 실수 앞자리 문자수
    //  		- arg1 : 실수 뒷자리 문자수 
	// 메시지 : 
    floatformat: {
        validator: function (value, param) {
            var sExpStr = "";
            if (value.toString().trim() == "") {
                return true;
            }
            sExpStr = "^[ \\t]*(?:\\d{1,{0}})(?:\\.\\d{1,{1}})[ \\t]*$".format(param[0], param[1]);
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_FLOAT_FORM")
    },
    // 사용법 : floatformat="['money']" 
	// 메시지 : 
    money: {
        validator: function (value) {
            var sExpStr = "^[ \\t]*(?:\\$|￦)?(?:\\+|-)?(?:\\d+|\\d{1,3}(?:,\\d{3})*)?(?:\\.\\d{1,}) {0,1}[ \\t]*$";
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_MONEY")
    },
    // 사용법 : floatformat="['number']" 
	// 메시지 : 
    number: {
        validator: function (value) {
            var sExpStr = "^[ \\t]*(?:\\+|-)?(?:\\d+|\\d{1,3}(?:,\\d{3})*)?(?:|\\.\\d{1,}) {0,1}[ \\t]*$";
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_NUMBER")
    },
    // 사용법 : floatformat="['telnum']" 
	// 메시지 : 
    telnum: {
        validator: function (value) {
            var sExpStr = "";
            if (value != "") {
                sExpStr = "^([0-9]{2,3})-?([0-9]{3,4})-?([0-9]{4})*$";
            }
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_TELNUM")
    },
    // 사용법 : floatformat="['celnum']" 
	// 메시지 : 
    celnum: {
        validator: function (value) {
            var sExpStr = "";
            if (value != "") {
                sExpStr = "^([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})*$";
            }
            var sExpOpt = "m";
            return (new RegExp(sExpStr, sExpOpt)).test(value);
        },
        message: resource.getMessage("MSG_VALIDATION_CELNUM")
    },
    // 사용법 : floatformat="['email']" 
	// 메시지 : 
    email: {
        validator: function (value) {
            return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value)
        },
        message: "Enter the e-mail."
    },
    // 사용법 : floatformat="['date']" 
	// 메시지 : 
    date: {
        validator: function (value, param) {
            var sSeperator = "-";
            if (param != null && param != "undefined") {
                sSeperator = param[0];
            }
            var sExpStr = "";
            if (value != "") {
                sExpStr = "^([0-9]{4})" + sSeperator + "?([0-9]{2})" + sSeperator + "?([0-9]{2})*$";
            }
            var sExpStr1 = "^([0-9]{4})?([0-9]{2})?([0-9]{2})*$";
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
    // 사용법 : floatformat="['month']" 
	// 메시지 : 
    month: {
        validator: function (value) {
            var sExpStr = "";
            if (value != "") {
                sExpStr = "^([0-9]{4})?([0-9]{2})*$";
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
    }
    
});