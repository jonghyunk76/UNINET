var oUtil = {
    doc: document || window.document || document.all,
    isArray: function (o) {
        if (Object.prototype.toString.call(o) == "[object Array]") {
            return true;
        } else {
            return false;
        }
    },
    isBoolean: function (o) {
        return typeof o === 'boolean';
    },
    isFunction: function (o) {
        return typeof o === 'function';
    },
    isHTMLElement: function (o) {
        if (this.isObject(o) || this.isFunction(o)) {
            if (o.nodeName) {
                return true;
            }
        }
        return false;
    },
    isNull: function (o) {
        return o === null || this.trim(o) === "" || typeof o === 'undefined';
    },
    isNumber: function (o) {
    	var sExpStr = "^[ \\t]*(?:\\d+)[ \\t]*$";
        var sExpOpt = "m";
        
        return (new RegExp(sExpStr, sExpOpt)).test(o);
    },
    isDate: function(value, param) {
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
    isObject: function (o) {
        return (o && (typeof o === 'object' || L.isFunction(o))) || false;
    },
    isString: function (o) {
        return typeof o === 'string';
    },
    isUndefined: function (o) {
        return typeof o === 'undefined';
    },
    isValue: function (o) {
        var L = this;
        return (L.isObject(o) || L.isString(o) || L.isNumber(o) || L.isBoolean(o));
    },
    ltrim: function (s) {
        if (!this.isString(s)) {
            return null;
        }
        return s.replace(/\s*((\S+\s*)*)/, "$1");
    },
    rtrim: function (s) {
        if (!this.isString(s)) {
            return null;
        }
        return s.replace(/((\s*\S+)*)\s*/, "$1");
    },
    trim: function (s) {
        if (!this.isString(s)) {
            return null;
        }
        return this.ltrim(this.rtrim(s));
    },
    isEmpty: function (o) {
        if ((typeof o != "undefiend") && (o.length > 0)) {
            return false;
        } else {
            return true;
        }
    },
    getUID : function() {
        function s4() {
            return ((1 + Math.random()) * 0x10000 | 0).toString(16).substring(1);
        }
        
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
    }
};

var Base64 = {

	// private property
	_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

	// public method for encoding
	encode : function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;

		while (i < input.length) {

		  chr1 = input.charCodeAt(i++);
		  chr2 = input.charCodeAt(i++);
		  chr3 = input.charCodeAt(i++);

		  enc1 = chr1 >> 2;
		  enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
		  enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
		  enc4 = chr3 & 63;

		  if (isNaN(chr2)) {
			  enc3 = enc4 = 64;
		  } else if (isNaN(chr3)) {
			  enc4 = 64;
		  }

		  output = output +
			  this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
			  this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

		}

		return output;
	},

	// public method for decoding
	decode : function (input)
	{
	    var output = "";
	    var chr1, chr2, chr3;
	    var enc1, enc2, enc3, enc4;
	    var i = 0;

	    input = input.replace(/[^A-Za-z0-9+/=]/g, "");

	    while (i < input.length)
	    {
	        enc1 = this._keyStr.indexOf(input.charAt(i++));
	        enc2 = this._keyStr.indexOf(input.charAt(i++));
	        enc3 = this._keyStr.indexOf(input.charAt(i++));
	        enc4 = this._keyStr.indexOf(input.charAt(i++));

	        chr1 = (enc1 << 2) | (enc2 >> 4);
	        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
	        chr3 = ((enc3 & 3) << 6) | enc4;

	        output = output + String.fromCharCode(chr1);

	        if (enc3 != 64) {
	            output = output + String.fromCharCode(chr2);
	        }
	        if (enc4 != 64) {
	            output = output + String.fromCharCode(chr3);
	        }
	    }

	    return output;
	}
};

var StringHelper = {
    null2void : function (string, format) {
    	var rstVal;
    	
        if(oUtil.isNull(string)) {
        	if(format == "int") rstVal = "0";
        	else if(format == "percent") rstVal = "0.00";
        	else rstVal = "";
        } else {
        	if(format == "int") rstVal = formatInteger(string);
        	else if(format == "percent") rstVal = formatFloat(string, 2);
        	else rstVal = string;
        }
        
        return rstVal;
    },
}

function replaceAll(str, exStr, chStr) {
	if(oUtil.isNull(str) || oUtil.isNull(exStr) || typeof str != "string") return str;
	
	var retStr = str.split(exStr);
	retStr = retStr.join(chStr);
	
    return retStr;
}

function clone(obj) {
    if (null == obj || "object" != typeof obj) return obj;
    var copy = obj.constructor();
    
    for (var attr in obj) {
        if (obj.hasOwnProperty(attr)) copy[attr] = obj[attr];
    }
    
    return copy;
}

var resource = {
		
	/**
	 * 메시지내 배열로 지정된 문자열을 특정 문자로 치환 후 반환
	 * @param msg = 원본 메시지
	 * @param args = 치환을 위한 문자열 배열
	 * @return 치환이 완료된 메시지
	 */
	getMessage : function(msg, args) {
		var rstMsg = "";
		var arrParam = msg.split(",");
        
        for(var cnt = 0; cnt < arrParam.length; cnt++) {
        	var str = arrParam[cnt];
            
            if(DEFAULT_LANGUAGE == "ES" || DEFAULT_LANGUAGE == "EN") {
            	// 영문인 경우에는 무조건 공백을 추가한다.
                if(cnt != 0) {
                	rstMsg += " ";
                }
            } else {
            	if(cnt != 0) {
            		if(str.substring(0,1) == " ") rstMsg += " ";
            	}
            }
            
            try {
            	rstMsg += eval(str.trim());
            } catch(e) {
            	rstMsg += str;
            }
        }

		if(!oUtil.isNull(args)) {
			for(var i = 0; i < args.length; i++) {
				if(i == 0 && !oUtil.isNull(args[0])) rstMsg = rstMsg.replace(/\{0}/g, args[0]);
				else if(i == 1 && !oUtil.isNull(args[1])) rstMsg = rstMsg.replace(/\{1}/g, args[1]);
				else if(i == 2 && !oUtil.isNull(args[2])) rstMsg = rstMsg.replace(/\{2}/g, args[2]);
				else if(i == 3 && !oUtil.isNull(args[3])) rstMsg = rstMsg.replace(/\{3}/g, args[3]);
				else if(i == 4 && !oUtil.isNull(args[4])) rstMsg = rstMsg.replace(/\{4}/g, args[4]);
				else if(i == 5 && !oUtil.isNull(args[5])) rstMsg = rstMsg.replace(/\{5}/g, args[5]);
				else if(i == 6 && !oUtil.isNull(args[6])) rstMsg = rstMsg.replace(/\{6}/g, args[6]);
			}
		}
		
		rstMsg = rstMsg.replace(/\"/g, "＂");
		
		return rstMsg;
	}

};

function makeStringParameter(sObject, bEncoding) {
    var sParamDelimeter = "&";
    var sValueDelimeter = "=";
    if (bEncoding == null) {
        bEncoding = false;
    }
    var arrParam = new Array();
    var sKey;
    var svalue;
    for (var key in sObject) {
        if (key in sObject.constructor.prototype) {
            continue;
        }
        var value = sObject[key];
        if (bEncoding) {
            sKey = encodeURIComponent(key);
            svalue = encodeURIComponent(value);
        } else {
            sKey = key;
            svalue = value;
        }
        arrParam.push(sKey + sValueDelimeter + svalue);
    }
    var returnValue = arrParam.join(sParamDelimeter);
    
    return returnValue;
};

function findPosX(obj) {
    var curleft = 0;
    if (obj.offsetParent)
        while (1) {
            curleft += obj.offsetLeft;
            if (!obj.offsetParent) break;
            obj = obj.offsetParent;
        } else if (obj.x) curleft += obj.x;
    return curleft;
}

function findPosY(obj) {
    var curtop = 0;
    if (obj.offsetParent)
        while (1) {
            curtop += obj.offsetTop;
            if (!obj.offsetParent) break;
            obj = obj.offsetParent;
        } else if (obj.y) curtop += obj.y;
    return curtop;
}

function unescape(ostr) {
	if(!oUtil.isNull(ostr) && typeof ostr == "string") {
		var str = ostr.toString();
		var sold = new Array(/&amp;/g, /&quot;/g, /&lt;/g, /&gt;/g, /&apos;/g);
		var snew = new Array("&", "″", "<", ">", "'");
		
		for(var i = 0; i < sold.length; i++) {
			str =  str.replace(sold[i], snew[i]);
		}
		
		return str;
	}
	
	return ostr;
}

function IsPositiveInt(string) {
    if (string == "") return false;
    else
        for (var i = 0; i < string.length; i++)
            if (isNaN(parseInt(string.charAt(i)))) return false; 
    return true;
}

function CheckLeapYear(intYear) {
    if (((intYear % 4 == 0) && !(intYear % 100 == 0)) || (intYear % 400 == 0)) return true;
    else return false;
}

function CheckDateValidation(string) {
    var errCode = -1;
    string = replaceAll(string, SEPERATOR_OF_DATE, "");
    
    if (string.length != 8 || !IsPositiveInt(string)) {
    	errCode = -1;
    } else {
        errCode = 1;
        
        var yyyy, mm, dd;
        
        if(NATION_DATE_VIEW == "US") {
        	mm = parseInt(string.substring(0, 2), 10);
	        dd = parseInt(string.substring(2, 4), 10);
	        yyyy = parseInt(string.substring(4, 8), 10);
        } else if(NATION_DATE_VIEW == "MX") {
        	dd = parseInt(string.substring(0, 2), 10);
	        mm = parseInt(string.substring(2, 4), 10);
	        yyyy = parseInt(string.substring(4, 8), 10);
        } else {
	        yyyy = parseInt(string.substring(0, 4), 10);
	        mm = parseInt(string.substring(4, 6), 10);
	        dd = parseInt(string.substring(6, 8), 10);
        }
        
        if (yyyy == 0 || mm == 0 || dd == 0 || mm > 12 || dd > 31) errCode = 0;
        else if (mm == 2) {
            if (CheckLeapYear(yyyy)) {
                if (dd > 29) errCode = 0;
            } else {
                if (dd > 28) errCode = 0;
            }
        } else if ((mm == 4 || mm == 6 || mm == 9 || mm == 11) && dd > 30) errCode = 0;
    }
    
    if (errCode == 1) {
        return true;
    } else if (errCode == -1) {
        return false;
    } else {
    	return false;
    }
}

function CheckDateValidationYYYYMM(string) {
    var errCode = -1;
    string = replaceAll(string, SEPERATOR_OF_DATE, "");
    
    if (string.length != 6 || !IsPositiveInt(string)) {
    	errCode = -1;
    } else {
        errCode = 1;
        
        var yyyy;
        var mm;
        if(NATION_DATE_VIEW == "MX" || NATION_DATE_VIEW == "US") {
        	mm = parseInt(string.substring(0, 2), 10);
            yyyy = parseInt(string.substring(2, 6), 10);
        } else {
        	yyyy = parseInt(string.substring(0, 4), 10);
	        mm = parseInt(string.substring(4, 6), 10);
        }
        
        if (yyyy == 0 || mm == 0 || mm > 12) errCode = 0;
    }
    
    if (errCode == 1) {
        return true;
    } else if (errCode == -1) {
        return false;
    }
}

function charByteSize(ch) {
    if (ch == null || ch.length == 0) {
        return 0;
    }
    var charCode = ch.charCodeAt(0);
    if (charCode <= 0x00007F) {
        return 1;
    } else if (charCode <= 0x0007FF) {
        return 2;
    } else if (charCode <= 0x00FFFF) {
        return 3;
    } else {
        return 4;
    }
}

function isHangul(value) {
    var rgEx = /^[\uac00-\ud7a3]*$/g;
    var checker = rgEx.exec(value);
    if (checker) {
        return true;
    } else {
        return false;
    }
}

function stringByteSize(str) {
    if (str == null || str.length == 0) {
        return 0;
    }
    var size = 0;
    for (var i = 0; i < str.length; i++) {
        size += charByteSize(str.charAt(i));
    }
    return size;
}

function formatInteger(number) {
    if (oUtil.isNull(number)) return "";
    var num = number.toString().split(".");
    num[0] = num[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return num[0];
}

function formatNumber(number) {
    if (oUtil.isNull(number)) return "";
    var num = number.toString().split(".");
    num[0] = num[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return num.join(".");
}

function formatFloat(number, precision) {
    if (oUtil.isNull(number)) return "";
    var num = number.toString().split(".");
    var str1 = formatNumber(num[0]);
    var str2 = "";
    
    for (var i = 0; i < precision; i++) {
        if (oUtil.isNull(num[1])) {
            str2 += "0";
        } else {
            var p = num[1].charAt(i);
            if (!oUtil.isNull(p)) {
                str2 += p;
            } else {
                str2 += "0";
            }
        }
    }
    if (oUtil.isNull(str2)) {
        return str1;
    }
    
    if(parseInt(str2) == 0) {
    	return str1;
    } else {
        return str1 + "." + str2;
    }
}

/**
 * 날짜의 길이가 6보다 크면 년월일, 6이면 년월로 처리함.<br>
 * 국가별 날짜로 변경하기 위해 국가코드를 입력할 수 있음
 * 
 * @param date = 년월일 날짜
 * @param seperator = 구분자
 * @param vw_nation = UI에 표현되는 날짜타입의 국가
 * @param db_nation = Database에 표현되는 날짜타입의 국가
 * @returns
 */
function formatDate(date, seperator, vw_nation, db_nation, date_type) {
    var y, m, d;
	if (oUtil.isNull(date)) return "";
    
    if(oUtil.isNull(vw_nation)) vw_nation = NATION_DATE_VIEW;
    if(oUtil.isNull(db_nation)) db_nation = NATION_DATE_DB;
    
    if(vw_nation == "US") {
    	if(date.length == 6) {
    		m = parseInt(date.substring(0, 2));
    		y = parseInt(date.substring(2, 6));
    		d = 1;
    	} else if(date.length > 6) {
	    	m = parseInt(date.substring(0, 2));
	        d = parseInt(date.substring(2, 4));
	        y = "";
	        if(date.length > 6) {
	        	y = date.substring(4, 8);
	        }
    	}
    } if(vw_nation == "MX") {
    	if(date.length == 6) {
    		m = parseInt(date.substring(0, 2));
    		y = parseInt(date.substring(2, 6));
    		d = 1;
    	} else if(date.length > 6) {
	    	d = parseInt(date.substring(0, 2));
	    	m = parseInt(date.substring(2, 4));
	        y = "";
	        if(date.length > 6) {
	        	y = date.substring(4, 8);
	        }
    	}
    } else {
    	if(date.length == 6) {
    		y = parseInt(date.substring(0, 4));
    		m = parseInt(date.substring(4, 6));
    		d = 1;
    	} else if(date.length > 6) {
    		y = parseInt(date.substring(0, 4));
	        m = parseInt(date.substring(4, 6));
	        d = "";
	        if(date.length > 6) {
	        	d = parseInt(date.substring(6, 8));
	        }
    	}
    }
    
    if(date_type == "month") {
    	d = "";
    }
    
    return calendar.util.chageDate2Nation(y, m, d, seperator, db_nation);
}

function nullToString(value) {
    if (oUtil.isNull(value)) return "";
    else return value;
}

function changImage(obj, path) {
    obj.src = path;
}

function getMaxDataLength(data, textId) {
	try {
		if(oUtil.isNull(eval("data[0]." + textId))) return;
		
		var maxData = eval("data[0]." + textId + ".length");
	    for (var i = 0; i < data.length; i++) {
	        var char = eval("data[i]." + textId);
	        var size = getStringPixelWidth(char);
	        if (maxData < size) {
	            maxData = size;
	        }
	    }
	} catch(e) {
		console.log(e);
	}
    
    return maxData;
}

function getStringPixelWidth(string_value) {
	if(oUtil.isNull(string_value)) return;
	
    var ascii_code;
    var string_value_length = string_value.length;
    var character;
    var character_width = 0;
    var total_width = 0;
    var special_char_size = 9;
    var multibyte_char_size = 14;
    var base_char_start = 32;
    var base_char_end = 127;
    var ascii_char_size = Array(5, 5, 5, 7, 7, 10, 9, 5, 6, 6, 7, 7, 5, 7, 5, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 5, 5, 9, 7, 9, 7, 12, 9, 9, 10, 9, 9, 8, 10, 9, 3, 7, 9, 8, 11, 10, 10, 9, 10, 9, 9, 9, 9, 9, 10, 9, 9, 9, 7, 11, 7, 7, 7, 5, 8, 8, 8, 8, 8, 3, 8, 8, 3, 3, 7, 3, 11, 8, 8, 8, 8, 5, 8, 3, 8, 7, 10, 8, 8, 8, 7, 7, 7, 10, 7);
    for (var i = 0; i < string_value_length; i++) {
        character = string_value.substring(i, (i + 1));
        ascii_code = character.charCodeAt(0);
        if (ascii_code < base_char_start) {
            character_width = special_char_size;
        } else if (ascii_code <= base_char_end) {
            idx = ascii_code - base_char_start;
            character_width = ascii_char_size[idx];
        } else if (ascii_code > base_char_end) {
            character_width = multibyte_char_size;
        }
        total_width += character_width;
    }
    return total_width;
}

function setCookie(name, value, path, domain, secure, expires) {
    if (typeof (expires) == "undefined" || expires == null) {
        expires = new Date();
        expires.setMonth(expires.getMonth() + 1);
    }
    
    document.cookie = name + "=" + Base64.encode(value) + ((expires) ? "; expires=" + expires.toGMTString() : "") + ((path) ? "; path=" + path : "") + ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

function getCookie(name) {
    var arg = name + "=";
    var alen = arg.length;
    var clen = document.cookie.length;
    var i = 0;
    while (i < clen) {
        var j = i + alen;
        if (document.cookie.substring(i, j) == arg) return getCookieVal(j);
        i = document.cookie.indexOf(" ", i) + 1;
        if (i == 0) break;
    }
    return null;
}

function getCookieVal(offset) {
    var endstr = document.cookie.indexOf(";", offset);
    
    if (endstr == -1) endstr = document.cookie.length;
    
    return Base64.decode(document.cookie.substring(offset, endstr));
}

// textarea <br>, &nbsp 변환
// atom
function setTextareaConvert(str) {
  str = str.replace(/\n/gi, "<br>"); 
  str = str.replace(/ /gi,"&nbsp;");

  return str;
}

function getTextareaConvert(str) {
  str = str.replace(/<br>/gi, "\n"); 
  str = str.replace(/&nbsp;/gi," ");
  return str;
}

/**
 * window alert 함수 재정의
 * @param arg = 화면에 보여출 메세지
 */
window.alert = function(arg, type) {
    if(type == "window") {
    	$("#main_win_message").html(arg);
        $("#main_win").window('open');
        /*
        $('#main_win').keydown(function (e) {
        	alert(event.keyCode);
        	if(event.keyCode == 27) {
            	alert("esc");
            }
        });
        
        var _7bd = _7bc.find(".messager-button");
        _7bd.bind("keydown", function (e) {
            if (e.keyCode == 27) {
                alert("esc");
            }
        }
        */
    } else {
        $.messager.alert(resource.getMessage("INFMT"), arg, "info");
    }
};

/** 
 * input 숫자와 콤마만 입력되게 함
 */
$(this).delegate("input:text", "keyup", function() {
	if($(this).hasClass('easyui-numberbox') || $(this).hasClass('numberComma')) {
		var keycd = event.keyCode;
		//console.log("keyup = " + keycd);
		if(keycd != 9 && keycd != 13 && keycd != 37 && keycd != 38 && keycd != 39 && keycd != 40 && keycd != 46 && keycd != 8) {
			$(this).inputmask({ alias: 'decimal', autoGroup: true, groupSeparator: ","});
		}
	}
});

$(this).delegate("input:text", "focus", function() {
	if($(this).hasClass('easyui-numberbox') || $(this).hasClass('numberComma')) {
		var keycd = event.keyCode;
		//console.log("keyup = " + keycd);
		if(keycd != 9 && keycd != 13 && keycd != 37 && keycd != 38 && keycd != 39 && keycd != 40 && keycd != 46 && keycd != 8) {
			$(this).inputmask({ alias: 'decimal', autoGroup: true, groupSeparator: ","});
		}
	}
});

/**
 * input에서 엔터를 치면 지정된 function이 호출됨
 */
$(this).delegate("input", "keypress", function(e) {
	if (e.which == 13) {
		var callFunc = $(this).attr('search')
		if(callFunc) {
			var func = eval(callFunc);
			
    		if (typeof(func) == "function") {
    			func();
    		}
		}
	}
});

/**
 * 사용자 권한에 따라 버튼을 생성한다. 
 * <a href="javascript:CM12010.searchList()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"><spring:message code='BTN_SEARCH' /></a>
 *  
 * @param msg 버튼에 보여줄 Text
 * @param path 호출될 URL 및 javascript 함수명
 * @param auth 권한(Y,N)
 * @param type 아이콘 형태
 *            - 아이콘 형태는 다음과 같음
 *                  (blank, add, edit, remove, save, cut, ok, no, cancel, reload, search, print, help, undo, redo, back, sum, tip, mini-add, mini-edit, mini-refresh, logo)
 */
function btnAuth(msg, path, auth, type) {
    var tag = "";

    if(auth == "Y") { 
        if(path.indexOf("http") > 0) { // http로 시작하는 경로는 URL로 인식함
            tag = '<a href="'+path+'" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-'+type+'\'"><span>'+msg+'</span></a>';
        } else {
            tag = '<a href="javascript:'+path+';" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-'+type+'\'"><span>'+msg+'</span></a>';
        }
    }
    document.write(tag);
}

/**
 * IE 버전을 체크하여 린턴하는 함수
 * @returns
 */
function get_version_of_IE () { 
	 var word; 
	 var version = "N/A"; 
	 var agent = navigator.userAgent.toLowerCase(); 

	 // IE old version ( IE 10 or Lower ) 
	 if(navigator.appName == "Microsoft Internet Explorer") word = "msie "; 

	 else { 
		 // IE 11 
		 if(agent.search( "trident" ) > -1) word = "trident/.*rv:"; 
		 // Microsoft Edge  
		 else if(agent.search( "edge/" ) > -1) word = "edge/"; 
		 // 그외, IE가 아니라면 ( If it's not IE or Edge )  
		 else return version; 
	 } 

	 var reg = new RegExp( word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})" ); 

	 if(reg.exec( agent ) != null) version = RegExp.$1 + RegExp.$2; 

	 return version; 
} 


var windowControlClass = function() {
    this.defaultModalStyle = "center:yes;help:no;resizable:no;status:no;scroll:no;";
    this.defaultSimpleStyle = "status=no,resizable=no,scrollbars=no,status=no,toolbar=no,titlebar=no";
    
    this.Modal = function (sUrl,oParams,arrRect,sFeatures) {
        var sLeft = arrRect[0].toString().trim();
        var sTop = arrRect[1].toString().trim();
        var sWidth = arrRect[2].toString().trim();
        var sHeight = arrRect[3].toString().trim();

        var sRect = "";
        sRect += (sWidth.isEmpty()) ? "" : "dialogWidth:" + sWidth + ";";
        sRect += (sHeight.isEmpty()) ? "" : "dialogHeight:" + sHeight + ";";
        sRect += (sLeft.isEmpty()) ? "" : "dialogLeft:" + sLeft + ";";
        sRect += (sTop.isEmpty()) ? "" : "dialogTop:" + sTop + ";";

        sFeatures = sRect + sFeatures;
        
        var urlAndData = sUrl.split("?");
        var url = urlAndData[0];
        var dataList;
        var inDataSetYn = "N";
        var blankUrl = "/origin/inDataSetBlankPopup.do";
        var param = "";
        if(urlAndData.length > 1) {
            dataList = urlAndData[1].split("&");
            for(var i=0; i < dataList.length; i++) {
                var data = dataList[i].split("=");
                var key;
                var value;
                if(data.length > 1) {
                    key = data[0];
                    value = data[1];
                }
                if(key == "inDataSet") {
                    inDataSetYn = "Y";
                } else {
                    param += "&" + key + "=" + value;
                }
            }
            param = param.substring(1, param.length);
            param += "&"+ "realUrl="+url;
        }

        if(inDataSetYn == "Y") {
            var oReturnValue = window.showModalDialog(blankUrl + "?" + param,oParams,"" + sFeatures);
        } else {
            var oReturnValue = window.showModalDialog(sUrl,oParams,"" + sFeatures);
        }
        
        return oReturnValue;
    };
    
    this.Modaless = function (sUrl,oParams,arrRect,sFeatures) {
        var sLeft = arrRect[0].toString().trim();
        var sTop = arrRect[1].toString().trim();
        var sWidth = arrRect[2].toString().trim();
        var sHeight = arrRect[3].toString().trim();
        
        var sRect = "";
        sRect += (sWidth.isEmpty()) ? "" : "dialogWidth:" + sWidth + ";";
        sRect += (sHeight.isEmpty()) ? "" : "dialogHeight:" + sHeight + ";";
        sRect += (sLeft.isEmpty()) ? "" : "dialogLeft:" + sLeft + ";";
        sRect += (sTop.isEmpty()) ? "" : "dialogTop:" + sTop + ";";

        sFeatures = sRect + sFeatures;
        
        var oReturnValue = window.showModelessDialog(sUrl,oParams,"" + sFeatures);
        
        return oReturnValue;
    };
    
    this.DownloadFile = function (sUrl,sFileName) {
        sUrl = sUrl.nullValueEx("");
        sFileName = sFileName.nullValueEx("");
        
        if (sUrl.isEmpty()) {
            return false;
        }
        
        var sAction = "/origin/framework/controller/downloadFile.do";
        var sMethod = "post";
        var sTarget = "_self";
        var sFormId = "temporaryForm";
        
        var sFileNameId = "fileName";
        var sOriginalFileNameId = "originalFileName";
        
        oForm = document.createElement("<form></form>");
        oForm.id = sFormId;
        oForm.method = sMethod;
        oForm.action = sAction;
        oForm.target = sTarget;
        
        var oParam1 = document.createElement("<input type='hidden' name='" + sFileNameId + "'>");
        oParam1.value = sUrl;
        oForm.appendChild(oParam1);
        
        var oParam2 = document.createElement("<input type='hidden' name='" + sOriginalFileNameId + "'>");
        oParam2.value = sFileName;
        oForm.appendChild(oParam2);
        
        document.body.appendChild(oForm);
        
        oForm.submit();
        
        oForm.parentNode.removeChild(oForm);
        oForm = null;
        
        return true;
    };
    
    this.DownloadDBFile = function (sCooCertifyNo,sVendorCode,sDivisionCode,sFileName) {
        sFileName = sFileName.nullValueEx("");
        
        sDivisionCode = sDivisionCode.nullValueEx("");
        sVendorCode = sVendorCode.nullValueEx("");
        sCooCertifyNo = sCooCertifyNo.nullValueEx("");
        
        var sAction = "/origin/compliance/coomgt/selectExtCooCertifyFile.do";
        var sMethod = "post";
        var sTarget = "_self";
        var sFormId = "temporaryForm";
        
        var sFileNameId = "fileName";
        var sOriginalFileNameId = "originalFileName";
        
        oForm = document.createElement("<form></form>");
        oForm.id = sFormId;
        oForm.method = sMethod;
        oForm.action = sAction;
        oForm.target = sTarget;
        
        var oParam1 = document.createElement("<input type='hidden' name='COO_CERTIFY_NO'>");
        oParam1.value = sCooCertifyNo;
        oForm.appendChild(oParam1);

        var oParam3 = document.createElement("<input type='hidden' name='VENDOR_CODE>");
        oParam3.value = sVendorCode;
        oForm.appendChild(oParam3);
        
        var oParam4 = document.createElement("<input type='hidden' name='DIVISION_CODE'>");
        oParam4.value = sDivisionCode;
        oForm.appendChild(oParam4);
        
        var oParam5 = document.createElement("<input type='hidden' name='FILE_NAME'>");
        oParam5.value = sFileName;
        oForm.appendChild(oParam5);
        /*
        var oParam2 = document.createElement("<input type='hidden' name='" + sOriginalFileNameId + "'>");
        oParam2.value = sFileName;
        oForm.appendChild(oParam2);
        */
        document.body.appendChild(oForm);
        
        oForm.submit();
        
        oForm.parentNode.removeChild(oForm);
        oForm = null;
        
        return true;
    };
    
    this.DownloadEdocDBFile = function (sReferenceId,sVendorCode,sDivisionCode,sFileName, sDataUrl) {
        sFileName = sFileName.nullValueEx("");
        sReferenceId = sReferenceId.nullValueEx("");
        
        var sAction = sDataUrl;
        var sMethod = "post";
        var sTarget = "_self";
        var sFormId = "temporaryForm";
        
        var sFileNameId = "fileName";
        var sOriginalFileNameId = "originalFileName";
        
        oForm = document.createElement("<form></form>");
        oForm.id = sFormId;
        oForm.method = sMethod;
        oForm.action = sAction;
        oForm.target = sTarget;
        var oParam1 = document.createElement("<input type='hidden' name='REFERENCE_ID'>");
        oParam1.value = sReferenceId;
        oForm.appendChild(oParam1);

        var oParam2 = document.createElement("<input type='hidden' name='COPY_FILE_NAME'>");
        oParam2.value = sFileName;
        oForm.appendChild(oParam2);

        document.body.appendChild(oForm);
        
        oForm.submit();
        
        oForm.parentNode.removeChild(oForm);
        oForm = null;
        
        return true;
    };
    
    this.Post = function (sUrl,sParams,sTarget,bEncoded) {
        sUrl = sUrl.nullValueEx("");
        sParams = sParams.nullValueEx("");
        //sTarget = sTarget.nullValueEx("").emptyValue("_self");
        bEncoded = bEncoded.nullValueEx(true);
        
        if (sUrl.isEmpty()) {
            return false;
        }
        
        var sAction = sUrl;
        var sMethod = "post";
        
        var oForm;
        oForm = document.createElement("form");
        oForm.method = sMethod;
        oForm.action = sAction;
        oForm.target = sTarget;
        
        var arrParam = sParams.split("&");
        
        for(var i = 0;i < arrParam.length;i++) {
            var arrKeyValue = arrParam[i].split("=");
            var oHidden = document.createElement("input");
            oHidden.type = "hidden";
            if (bEncoded) {
                oHidden.name = decodeURIComponent(arrKeyValue[0]);
                oHidden.value = decodeURIComponent(arrKeyValue[1]);
            }
            else {
                oHidden.name = arrKeyValue[0];
                oHidden.value = arrKeyValue[1];
            }
            
            oForm.appendChild(oHidden);
        }
        
        document.body.appendChild(oForm);
        
        oForm.submit();
        
        return true;
    };
    
    this.ShowMessage = function (sMessage) {
        alert(sMessage);
    };
    
    this.ShowInfomation = function (sMessage) {
        alert(sMessage);
    };
    
    this.ShowError = function (sMessage) {
        alert("ERROR:" + CHARS.CR + sMessage);
    };
    
    this.Wait = function (bWait) {
        bWait = bWait.nullValueEx(false);

        var sDivId = "divWait";
        var oDiv = document.getElementById(sDivId);

        if (true == bWait) {
            if (isNullOrUndefined(oDiv)) {
                oDiv = document.createElement("div");
                oDiv.id = sDivId;
                document.body.appendChild(oDiv);
            }
            
            document.body.style.cursor = "wait"
        }
        else if (!isNullOrUndefined(oDiv)) {
            oDiv.parentNode.removeChild(oDiv);
            document.body.style.cursor = "default";
        }
        
        return;
    };
    
    this.DelayedCall = function (iMiliSec,func,arrParam) {
        iMiliSec = iMiliSec.nullValueEx(100);
        
        if (isNullOrUndefined(arrParam) || !isArray(arrParam)) {
            arrParam = [];
        }
        
        var funcBinded = Function.prototype.bind.apply(func,[null].concat(arrParam));
        window.setTimeout(funcBinded,iMiliSec);
    };
    
    this.DoWait = function (func) {
        WINDOW.Wait(true);
        var funcWait = function () { func.apply(this,arguments.toArray()); WINDOW.Wait(false); };
        WINDOW.DelayedCall(100,funcWait,arguments.toArray().slice(1));
    };
    
};

var WINDOW = new windowControlClass();