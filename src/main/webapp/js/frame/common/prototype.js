/**
 * 지정된 명칭으로 jQuery 표준화 적용 여부할 업무인지 리턴
 * - 필요 시 단계적으로 웹 표준를 진행한다.
 * @param id = 적용ID
 * @returns {Boolean}
 */
function isStandardToBiz(id) {
	if(id.substring(0,2) == "SC" || id.substring(0, 2) == "CM500P0") {
		return true;
	}
	
	return false;
}

//////////////////////////////////////////////////////////////////////////////
//전역함수
//////////////////////////////////////////////////////////////////////////////

//지정된 obj 변수가 undefined 인지 확인
function isUndefined(obj) {
    if((typeof obj) == "undefined" || obj == null) {
        return true;
    }
    return false;
};

//지정된 obj 변수가 null 인지 확인
function isNull(obj) {
    return (null == obj);
};

//지정된 obj 변수가 null 이나 undefined 인지 확인
function isNullOrUndefined(obj) {
    return isUndefined(obj) || isNull(obj);
};

//지정된 obj 변수가 function 인지 확인
function isFunction(obj) {
    return (typeof obj) == "function" || Object.prototype.toString.call(obj) == "[object Function]";
};

//지정된 obj 변수가 boolean 인지 확인
function isBoolean(obj) {
    return (typeof obj) == "boolean" || Object.prototype.toString.call(obj) == "[object Boolean]";
};

//지정된 obj 변수가 string 인지 확인
function isString(obj) {
    return (typeof obj) == "string" || Object.prototype.toString.call(obj) == "[object String]";
};

//지정된 obj 변수가 number 인지 확인
function isNumber(obj) {
    return (typeof obj) == "number" || Object.prototype.toString.call(obj) == "[object Number]";
};

//지정된 obj 변수가 element 인지 확인
function isElement(obj) {
  return !!(obj && obj.nodeType == 1);
};

//지정된 obj 변수가 object 인지 확인
function isObject(obj) {
    return (typeof obj) == "object" || Object.prototype.toString.call(obj) == "[object Object]";
};

//지정된 obj 변수가 배열 인지 확인
function isArray(obj) {
    return (typeof obj) == "object" && Object.prototype.toString.call(obj) == "[object Array]";
};

//getCurrentFunctionName 함수를 호출한 함수의 이름을 반환
function getCurrentFunctionName() { 
    regex = "/^function\s+([^(]+)/";
    return regex.exec(arguments.caller.callee.toString())[1];                              
};

//////////////////////////////////////////////////////////////////////////////
//String prototype & function
//////////////////////////////////////////////////////////////////////////////

//string이 null 이거나 oNullValue 값과 타입이 일치하지 않을 때 일 때 oNullValue 반환
Object.nullValueEx = function (obj,oNullValue) {
    if (isNullOrUndefined(obj) || (typeof obj) != (typeof oNullValue)) {
        return oNullValue;
    }
    else {
        return obj;
    }
};

//string이 null일 때 oNullValue 반환
String.prototype.nullValueEx = function (obj) {
    return Object.nullValueEx(this,obj);
};

//boolean이 null일 때 oNullValue 반환
Boolean.prototype.nullValueEx = function (obj) {
    return Object.nullValueEx(this,obj);
};

//number가 null일 때 oNullValue 반환
Number.prototype.nullValueEx = function (obj) {
    if (this == null || !isNumber(this)) {
        return obj;
    }
    else {
        return this;
    }
};

String.prototype.leftPad = function (iLen, sFill) {
    alert(iLen);
    var sSource = isNullOrUndefined(this) ? "" : this.toString();
    
    iLen = Object.nullValueEx(iLen,0);
    sFill = Object.nullValueEx(sFill,"").emptyValue(" ");

    if (sFill.length > 1) {
        sFill = sFill.substr(0,1);
    }

    if (sSource.length > iLen) {
        return sSource;
    }

    var returnValue = "";
    var i;
    
    for (i = sSource.length; i < iLen; i++) {
        returnValue = returnValue + sFill;
    }

    returnValue = returnValue + sSource;

    return returnValue;
};

String.prototype.trim = function () {
    return this.replace(/^\s+|\s+$/g,"");
};

String.prototype.ltrim = function () {
    return this.replace(/^\s+/,"");
};

String.prototype.rtrim = function () {
    return this.replace(/\s+$/,"");
};

String.prototype.isEmpty = function () {
    if(this == "") return true;
    return false;
};

String.prototype.format = function () {     
    var s = this;         
    var i = arguments.length;
    
    while (i--) {         
        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);     
    }     
    
    return s; 
};

String.prototype.emptyValue = function (sEmptyValue) {
    if (this.isEmpty()) {
        return sEmptyValue;
    }
    else {
        return this;
    }
};

String.prototype.encodeURIComponent = function () {
    return encodeURIComponent(this);
};

String.prototype.decodeURIComponent = function () {
    return decodeURIComponent(this);
};

String.prototype.encodeURI = function () {
    return encodeURI(this);
};

String.prototype.decodeURI = function () {
    return decodeURI(this);
};

String.prototype.escape = function () {
    return escape(this);
};

String.prototype.unescape = function () {
    return unescape(this);
};

String.prototype.wrap = function (sStart,sEnd) {
    return sStart + this + sEnd;
};

String.prototype.bracket = function () {
    return this.wrap("(",")");
};

String.prototype.squareBracket = function () {
    return this.wrap("[","]");
};

String.prototype.brace = function () {
    return this.wrap("{","}");
};

String.prototype.toUTF8 = function () {
    var s = this;
    
    for(var c, i = -1, l = (s = s.split("")).length, o = String.fromCharCode;++i < l;s[i] = (c = s[i].charCodeAt(0)) >= 127 ? o(0xc0 | (c >>> 6)) + o(0x80 | (c & 0x3f)) : s[i]);
    
    return s.join("");
};

String.prototype.fromUTF8 = function () {
    var s = this;
    
    for(var a, b, i = -1, l = (s = s.split("")).length, o = String.fromCharCode, c = "charCodeAt";++i < l;((a = s[i][c](0)) & 0x80) && (s[i] = (a & 0xfc) == 0xc0 && ((b = s[i + 1][c](0)) & 0xc0) == 0x80 ? o(((a & 0x03) << 6) + (b & 0x3f)) : o(128), s[++i] = ""));

    return s.join("");
};

String.prototype.tryNumber = function () {
    var oRegExp = /^[ \t]*(?:\+|-)?(?:\d+|\d{1,3}(?:,\d{3})*)?(?:\.\d{1,}){0,1}[ \t]*$/m;
    return oRegExp.test(this);
};

String.prototype.toNumber = function () {
    if (this.tryNumber()) {
        return new Number(this.replace(/,/gm,""));
    }
    else {
        return NaN;
    }
};

String.prototype.equals = function (sCompare) {
	return (this == sCompare);
};

String.prototype.notEquals = function (sCompare) {
	return (this != sCompare);
};

String.prototype.equalsIn = function () {
	for (var i = 0;i < arguments.length;i++) {
		if (this.equals(arguments[i])) {
			return true;
		}
	}
	
	return false;
};

var StringBuffer = function() {
    this.buffer = new Array();
}

StringBuffer.prototype.append = function(str) {
    this.buffer[this.buffer.length] = str;
}

StringBuffer.prototype.toString = function() {
    return this.buffer.join("");
}

//////////////////////////////////////////////////////////////////////////////
//Function prototype & function
//////////////////////////////////////////////////////////////////////////////
//Function.prototype.bind = function(obj) {  
//    var slice = [].slice;
//    var args = slice.call(arguments, 1);  
//    var self = this;
//    var nop = function () {};
//    var bound = function () {
//        return self.apply(this instanceof nop ? this : (obj || {}),args.concat(slice.call(arguments)));      
//    };
//    
//    nop.prototype = self.prototype;  
//    bound.prototype = new nop();
//    
//    return bound;
//};

//////////////////////////////////////////////////////////////////////////////
//Number prototype & function
//////////////////////////////////////////////////////////////////////////////
Number.prototype.addComma = function () {
    sNumber = this.toString();
    
    x = sNumber.split('.');
    
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    
    var rgx = /(\d+)(\d{3})/;
    
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    
    return x1 + x2;
};

//16진수를 10진수 값으로 변환
Number.fromHex = function (sHex) {
    return parseInt(sHex,16);
};

//10진수를 16진수 값로 변환
Number.prototype.toHex = function () {
    return this.toString(16);
};

//입력값을 16진수 값으로 변환
//입력값은 0~255
Number.prototype.toHexByte = function () {
if (this == null)
    return "00";

    var iDec = parseInt(this);
    
    if (iDec == 0 || isNaN(iDec)) 
        return "00";
    
    iDec = Math.max(0,iDec); 
    iDec = Math.min(iDec,255); 
    iDec = Math.round(iDec);
    
    return "0123456789ABCDEF".charAt((iDec - iDec % 16) / 16) + "0123456789ABCDEF".charAt(iDec % 16);
};

//////////////////////////////////////////////////////////////////////////////
//Color prototype & function
//////////////////////////////////////////////////////////////////////////////
function colorClass() {
    //RGB값을 16진수 값으로 변환
    this.RGB2Hex = function (R,G,B) {
        return R.toHexByte() + G.toHexByte() + B.toHexByte();
    };
    
    //RGB값을 10진수 값으로 변환
    this.RGB2Dec = function (R,G,B) {
        return Number.fromHex(this.RGB2Hex(R,G,B));
    };
}

var Color = new colorClass();


//////////////////////////////////////////////////////////////////////////////
//Key 변환 함수
//////////////////////////////////////////////////////////////////////////////
var KeysControlClass = function () {
    
    this.Asc = function (sChar){
        return sChar.charCodeAt(0);
    };

    this.Chr = function (iChar){
        return String.fromCharCode(iChar);
    };
    
    this.TAB = 9;
    this.CR = 13;
    this.LF = 10;
};

var KEYS = new KeysControlClass();

var CharsControlClass = function () {
    
    this.Asc = function (sChar){
        return sChar.charCodeAt(0);
    };

    this.Chr = function (iChar){
        return String.fromCharCode(iChar);
    };
    
    this.TAB = this.Chr(KEYS.TAB);
    this.CR = this.Chr(KEYS.CR);
    this.LF = this.Chr(KEYS.LF);
    this.CRLF = this.CR + this.LF;
};

var CHARS = new CharsControlClass();

//////////////////////////////////////////////////////////////////////////////
// 그리드 속성 자동 지정
//////////////////////////////////////////////////////////////////////////////
String.prototype.getFieldSize = function() {
	var val = eval("F_H_20091115020000."+this);
	
	if(!oUtil.isNull(val)) {
		return val.width;
	} else {
		return;
	}
};
String.prototype.getFormat = function() {
	var val = eval("F_H_20091115020000."+this);
	
	if(!oUtil.isNull(val)) {
		return val.format;
	} else {
		return;
	}
};
String.prototype.getAlign = function() {
	var val = eval("F_H_20091115020000."+this);
	
	if(!oUtil.isNull(val)) {
		return val.align;
	} else {
		return;
	}
};

// dialog 창크기 지정
String.prototype.getWidth = function() {
	var val = eval("F_D_76080120160605."+this);
	
	if(!oUtil.isNull(val)) {
		return val.width;
	} else {
		return;
	}
};
String.prototype.getHeight = function() {
	var val = eval("F_D_76080120160605."+this);
	
	if(!oUtil.isNull(val)) {
		return val.height;
	} else {
		return;
	}
};
//dialog 창크기 지정
String.prototype.getReWidth = function() {
	var val = eval("F_D_76080120160605."+this);
	
	if(!oUtil.isNull(val)) {
		return val.rewidth;
	} else {
		return;
	}
};
String.prototype.getReHeight = function() {
	var val = eval("F_D_76080120160605."+this);
	
	if(!oUtil.isNull(val)) {
		return val.reheight;
	} else {
		return;
	}
};

/**
 * 프로그램 ID와 도움말 ID를 매칭시킨 문자열 리턴
 * 
 * @param pid 프로그램ID
 * @param type M:메인, P:팝업
 * @returns 도움말ID
 */
function changeMenual2Pid(tid, type) {
	var pid = tid;
	
	// 메인화면
	if(type == "M") {
		// 도움말과 메뉴ID에 대한 싱크를 맞추기 위해 ID를 변경함
		if(tid == "FR-1001") pid = "FR-1201"; // FTA 협정
		else if(tid == "SI-2003") pid = "SI-2203"; // 고객사 품목
		else if(tid == "OD-4004") pid = "OD-4204"; // 판매가치 환산 기준율
		else if(tid == "DI-3001") pid = "DI-3201"; // 구매원장
		else if(tid == "DI-3004") pid = "DI-3204"; // 수불부
		else if(tid == "DI-3002") pid = "DI-3202"; // 매출원장
		else if(tid == "DI-3009") pid = "DI-3209"; // 협력사 메일링
		else if(tid == "DI-3007") pid = "DI-3207"; // 사급 확인서
		else if(tid == "OD-4001") pid = "OD-4201"; // 원산지 판정 관리
		else if(tid == "OD-4003") pid = "OD-4203"; // FTA BOM
		else if(tid == "OR-5002") pid = "OR-5202"; // 원산지 포괄 확인서 발급
		else if(tid == "OR-5001") pid = "OR-5201"; // 수출용 원산지 증명서 발급
		else if(tid == "OR-5004") pid = "OR-5204"; // 원산지 확인서 수정발급
		else if(tid == "OR-5007") pid = "OR-5207"; // 미정산 확인서 발급
		else if(tid == "OR-5003") pid = "OR-5203"; // 원산지 확인서(증명서) 발급 현황
		else if(tid == "CS-6002") pid = "CS-6001"; // 일마감 실행(ID불일치)
		// 협력사 포탈
		else if(tid == "VG-B001") pid = "VG-B201"; //
		else if(tid == "VG-B002") pid = "VG-B202"; //
		else if(tid == "VG-B003") pid = "VG-B203"; //
		else if(tid == "VG-B004") pid = "VG-B204"; //
		else if(tid == "VG-B005") pid = "VG-B205"; //
		else if(tid == "VG-B006") pid = "VG-B206"; //
		else if(tid == "VG-B007") pid = "VG-B207"; //
		else if(tid == "VG-B008") pid = "VG-B208"; //
		else if(tid == "VM-D001") pid = "VM-D201"; //
	}
	
	// 팝업화면
	if(type == "P") {
		if(tid == "FR-1010_03") pid = "FR-1010_02"; // FTA Rule > FTA 세율 > 팝업(미등록 세율과 등록/수정 팝업을 나눠서 작성할 것)
		else if(tid == "MM-A001_04") pid = "FR-1010_10"; // 메인화면 > FTA 세율 보고서
		else if(tid == "SI-2005_05") pid = "SI-2005_03"; // 기준정보 > 생산공정 > 제조공정도 발행
		else if(tid == "DI-3005_01") pid = "DI-3005_00"; // 협력사 확인서 > 증명서 등록 > 수입품목 리스트 > 증명서 등록 화면
		else if(tid == "DI-3209_03") pid = "DI-3209_04"; // 협력사 확인서 > 협력사 메일링 > 메일미리보기
		else if(tid == "DI-3209_04") pid = "DI-3209_03"; // 협력사 확인서 > 협력사 메일링 > 메일발송이력
		// 원산지 판정 이력
		else if(tid == "OD4205_02") pid = "OD-4205_02";
		else if(tid == "OD4205_03") pid = "OD-4205_02";
		else if(tid == "OD4205_04") pid = "OD-4205_04";
		else if(tid == "OD4205_05") pid = "OD-4205_05";
		else if(tid == "OD4205_06") pid = "OD-4205_06";
		else if(tid == "OD4205_07") pid = "OD-4205_07";
		else if(tid == "OD4205_08") pid = "OD-4205_08";
		else if(tid == "OD4205_09") pid = "OD-4205_09";
		// 협력사 포탈
		
	}
	
	return pid;
}