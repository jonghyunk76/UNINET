var APPLICATION_CHANGE_MSGCODE = true; // 메시지코드 변경여부(true:default, false) - 2020-03-03
// 메뉴별 권한 정보
var APPLICATION_MUNE_AUTH = new Array();
// 클래스별 버튼 권한 설정(2021-10-19)
var APPLICATION_BOTTOM_AUTH = [{NAME:"btnPopTextCopy",AUTH:"REG_AUTH"}
,{NAME:"btnSave",AUTH:"REG_AUTH"}
,{NAME:"btnDelete",AUTH:"DEL_AUTH"}
,{NAME:"btnPopIssue",AUTH:"REG_AUTH"}
,{NAME:"btnPopPrint",AUTH:"SEL_AUTH"}
,{NAME:"btnPopInvoice",AUTH:"SEL_AUTH"}
,{NAME:"btnPopOrgreq",AUTH:"EXC_AUTH"}
,{NAME:"btnPopRefresh",AUTH:"SEL_AUTH"}
,{NAME:"btnPopSendData",AUTH:"EXC_AUTH"}
,{NAME:"btnPopFileImport",AUTH:"FLE_AUTH"}
,{NAME:"btnPopApply",AUTH:"REG_AUTH"}
,{NAME:"btnPopPreview",AUTH:"SEL_AUTH"}
,{NAME:"btnPopExlDown",AUTH:"SEL_AUTH"}
,{NAME:"btnPopNewWindow",AUTH:"SEL_AUTH"}
,{NAME:"btnPopTransfer",AUTH:"EXC_AUTH"}
,{NAME:"btnPopSearch",AUTH:"SEL_AUTH"}
,{NAME:"btnTreeView",AUTH:"SEL_AUTH"}
,{NAME:"btnWarning",AUTH:"SEL_AUTH"}
,{NAME:"btnFile",AUTH:"FLE_AUTH"}
,{NAME:"btnInvoice",AUTH:"SEL_AUTH"}
,{NAME:"btnPrint",AUTH:"SEL_AUTH"}
,{NAME:"btnIssue",AUTH:"REG_AUTH"}
,{NAME:"btnSearch",AUTH:"SEL_AUTH"}
,{NAME:"btnRefresh",AUTH:"SEL_AUTH"}
,{NAME:"btnUserAdd",AUTH:"REG_AUTH"}
,{NAME:"btnServer",AUTH:"REG_AUTH"}
,{NAME:"btnStop",AUTH:"EXC_AUTH"}
,{NAME:"btnStopwatch",AUTH:"EXC_AUTH"}
,{NAME:"btnList",AUTH:"SEL_AUTH"}
,{NAME:"btnMoreInfo",AUTH:"REG_AUTH"}
,{NAME:"btnMinusInfo",AUTH:"REG_AUTH"}
,{NAME:"btnMark",AUTH:"UPD_AUTH"}
,{NAME:"btnPreview",AUTH:"SEL_AUTH"}
,{NAME:"btnApply",AUTH:"REG_AUTH"}
,{NAME:"btnReject",AUTH:"UPD_AUTH"}
,{NAME:"btnOrigin",AUTH:"EXC_AUTH"}
,{NAME:"btnFileImport",AUTH:"FLE_AUTH"}
,{NAME:"btnExport",AUTH:"SEL_AUTH"}
,{NAME:"btnImport",AUTH:"REG_AUTH"}
,{NAME:"btnSynchroniz",AUTH:"REG_AUTH"}
,{NAME:"btnMainDelete",AUTH:"DEL_AUTH"}
,{NAME:"btnMainSave",AUTH:"REG_AUTH"}
,{NAME:"btnNewRegiste",AUTH:"REG_AUTH"}
,{NAME:"btnDeleteRegiste",AUTH:"DEL_AUTH"}
,{NAME:"btnEdit",AUTH:"UPD_AUTH"}
,{NAME:"btnCheckEdit",AUTH:"UPD_AUTH"}
,{NAME:"btnAddress",AUTH:"UPD_AUTH"}
,{NAME:"btnSendEmail",AUTH:"REG_AUTH"}
,{NAME:"btnNewEmail",AUTH:"REG_AUTH"}
,{NAME:"btnRequestClick",AUTH:"EXC_AUTH"}
,{NAME:"btnRegisteCheck",AUTH:"REG_AUTH"}
,{NAME:"btnStateGraph",AUTH:"SEL_AUTH"}
,{NAME:"btnExlDown",AUTH:"SEL_AUTH"}
,{NAME:"btnSelectClick",AUTH:"SEL_AUTH"}
,{NAME:"btnCheckClick",AUTH:"REG_AUTH"}
,{NAME:"btnSendData",AUTH:"EXC_AUTH"}
,{NAME:"btnCleaning",AUTH:"DEL_AUTH"}
,{NAME:"btnSampler",AUTH:"EXC_AUTH"}
,{NAME:"btnDirections",AUTH:"EXC_AUTH"}
,{NAME:"btnLoginBtn",AUTH:"EXC_AUTH"}
,{NAME:"btnLogoutBtn",AUTH:"EXC_AUTH"}
,{NAME:"btnArrowDown",AUTH:"REG_AUTH"}
,{NAME:"btnArrowUp",AUTH:"REG_AUTH"}
,{NAME:"btnArrowTop",AUTH:"REG_AUTH"}
,{NAME:"btnArrowBottom",AUTH:"REG_AUTH"}
,{NAME:"btnTextCopy",AUTH:"REG_AUTH"}
,{NAME:"btnTextPaste",AUTH:"REG_AUTH"}
,{NAME:"btnNewWindow",AUTH:"SEL_AUTH"}
,{NAME:"btnMovePage",AUTH:"SEL_AUTH"}
,{NAME:"btnMainCalculator",AUTH:"REG_AUTH"}
,{NAME:"btnToolCalculator",AUTH:"REG_AUTH"}
,{NAME:"btnMainRestart",AUTH:"EXC_AUTH"}
,{NAME:"btnMainTransfer",AUTH:"EXC_AUTH"}
,{NAME:"btnMainSplitPart",AUTH:"REG_AUTH"}
,{NAME:"btnMainSplitCancel",AUTH:"DEL_AUTH"}
,{NAME:"btnTextSync",AUTH:"REG_AUTH"}
,{NAME:"btnBasicSave",AUTH:"REG_AUTH"}
,{NAME:"btnBasicUpdate",AUTH:"UPD_AUTH"}
,{NAME:"btnBasicDelete",AUTH:"DEL_AUTH"}
,{NAME:"chatFileDownload",AUTH:"FLE_AUTH"}
,{NAME:"icon-btnEdit",AUTH:"UPD_AUTH"}
,{NAME:"icon-btnList",AUTH:"SEL_AUTH"}
,{NAME:"icon-single_mapping",AUTH:"REG_AUTH"}
,{NAME:"icon-batch_mapping",AUTH:"REG_AUTH"}
,{NAME:"icon-refresh",AUTH:"DEL_AUTH"}
,{NAME:"icon-excel",AUTH:"SEL_AUTH"}
,{NAME:"btnHeaderTransfer",AUTH:"EXC_AUTH"}
,{NAME:"btnTextSync",AUTH:"UPD_AUTH"}
];

/**
 * 크롬 브라우저 사용 시 input의 크기가 변경되는 패치로 인해 이를 처리하기 위해 일괄적 넓이를 조정함(2020-06-15)
 * 
 * @param obj = tabs 또는 dialog
 * @returns
 */
function inputResize(obj) {
	obj.find("input[type=text],input[type=password]").each(function(idx){
		var agent = navigator.userAgent.toLowerCase();
	
		if (agent.indexOf("chrome") != -1) {
			var wd = this.style.width;
		    
			if(wd.charAt(wd.length-1) == "%") {
		    	var wdint = parseInt(wd.replace("%", ""));
		    	
		    	if(wdint < 100) {
		    		$(this).width((wdint-2)+"%");
		    	}
		    } 
	//		else {
	//	    	$(this).width($(this).width()-4);
	//	    }
		}
	});
}

/**
 * 메뉴별 버튼권한 설정에 보이기/숨기기(2021-10-16)
 * 
 * @param menuId = 메뉴ID
 * @return 메뉴에 지정된 권한정보
 *  APPLICATION_MUNE_AUTH.MENU_ID;
	APPLICATION_MUNE_AUTH.SEL_AUTH;
	APPLICATION_MUNE_AUTH.REG_AUTH;
	APPLICATION_MUNE_AUTH.UPD_AUTH;
	APPLICATION_MUNE_AUTH.DEL_AUTH;
	APPLICATION_MUNE_AUTH.EXC_AUTH;
	APPLICATION_MUNE_AUTH.FLE_AUTH;
 */
function getMenuAuth(menuId) {
	for(var k = 0; k < APPLICATION_MUNE_AUTH.length; k++) {
		if(menuId == APPLICATION_MUNE_AUTH[k]["MENU_ID"]) {
			return APPLICATION_MUNE_AUTH[k];
		}
	}
}

/**
 * 메뉴별 버튼권한 설정에 보이기/숨기기(2021-10-16)
 * 
 * @param obj = tabs 또는 dialog
 * @param otype = Object type명
 */
function bottom_auth(obj, otype) {
	var targetId;
	var pid = (typeof obj == "object") ? obj.attr("id") : obj; // 현재 프로그램 ID
	
	try {
		var tabObj = tab.getObject("MM0001_01_tabs_01");
		var tabAtr = tab.handle.getSelected(tabObj);
		
		targetId = (typeof tabAtr == "object") ? tabAtr.attr("id") : tabAtr;
	} catch(e) { }
	
	obj.find("a").each(function(idx){
		var att_auth;
		
		try {
			att_auth = $(this).data("auth");
		}catch(e) {
			att_auth = true;
		}
		
		// auth 속성값이 false이면 무시하도록 함 
		if(oUtil.isNull(att_auth) || att_auth == true) {
			// class명이 등록된 권한에 대해서만 처리함
			if(!oUtil.isNull(this.className)) {
				var dsp = null;
				
				for(var i = 0; i < APPLICATION_BOTTOM_AUTH.length; i++) {
					if(this.className.indexOf(APPLICATION_BOTTOM_AUTH[i]["NAME"]) > -1) {
//						console.log("auth = " + att_auth +", class = " + APPLICATION_BOTTOM_AUTH[i]["NAME"] + " >> program id = "+ pid + " / menu id = " + targetId +" / auth = "+APPLICATION_BOTTOM_AUTH[i]["AUTH"]);
						
						// 조건: 메뉴 권한 설정과 클래스명에 따라 버튼을 숨기거나 보이게 함.(메뉴 권한은 초기 메뉴 생성 시 결정, 클래스명별 권한은 css 속성의 auth로 결정)
						// 1. 메뉴생성 시 권한 정보를 별도로 저정해 놓을 것
						// 2. 클래스명별로 권한속성이 있는지 확인 할 것
						for(var k = 0; k < APPLICATION_MUNE_AUTH.length; k++) {
							if(APPLICATION_MUNE_AUTH[k]["MENU_ID"] == targetId) {
								var dsp = APPLICATION_MUNE_AUTH[k][APPLICATION_BOTTOM_AUTH[i]["AUTH"]];
//								console.log("style' display = " + dsp);
								break;
							}
						}
						
						break;
					}
				}
				
				if(!oUtil.isNull(dsp) && this.style.display != "none") {
					this.style.display = dsp;
				}
	        }
		}
	});
}

/**
 * 버튼 권한이 설정된 객체인지 여부를 리턴
 * 
 * @param obj = 버튼객체
 * @return 숨김권한부여 여부(true:숨김, false:보임 또는 미설정)
 */
function is_bottom_auth(obj) {
	var targetId;
	var att_auth;
	var class_name = obj.attr("class");
	var pid = (typeof obj == "object") ? obj.attr("id") : obj; // 현재 프로그램 ID
	
	try {
		var tabObj = tab.getObject("MM0001_01_tabs_01");
		var tabAtr = tab.handle.getSelected(tabObj);
		
		targetId = (typeof tabAtr == "object") ? tabAtr.attr("id") : tabAtr;
	} catch(e) {}
	
	try {
		att_auth = obj.data("auth");
	}catch(e) {
		att_auth = true;
	}
	
//	console.log("att_auth = " + att_auth + " / className = " + obj.attr("class")+ " >> program id = "+ pid + " / menu id = " + targetId);
	
	// auth 속성값이 false이면 무시하도록 함 
	if(oUtil.isNull(att_auth) || att_auth == true) {
		// class명이 등록된 권한에 대해서만 처리함
		if(!oUtil.isNull(class_name)) {
			var dsp = "";
			
			for(var i = 0; i < APPLICATION_BOTTOM_AUTH.length; i++) {
				if(class_name.indexOf(APPLICATION_BOTTOM_AUTH[i]["NAME"]) > -1) {
					for(var k = 0; k < APPLICATION_MUNE_AUTH.length; k++) {
						if(APPLICATION_MUNE_AUTH[k]["MENU_ID"] == targetId) {
							var dsp = APPLICATION_MUNE_AUTH[k][APPLICATION_BOTTOM_AUTH[i]["AUTH"]];
							
							if(dsp == "none") return true;
							else return false;
						}
					}
				}
			}
        }
	}
	
	return false;
}

String.prototype.startsWith = function(str) {
	if (this.length < str.length) { return false; }
	return this.indexOf(str) == 0;
}

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
        return o === null|| this.trim(o) === "NaN" || this.trim(o) === "" || typeof o === 'undefined';
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
    },
    roundNumber : function(num, scale) {
    	if(!("" + num).includes("e")) {
    	    return +(Math.round(num + "e+" + scale)  + "e-" + scale);
    	} else {
    	    var arr = ("" + num).split("e");
    	    var sig = "";
    	    
    	    if(+arr[1] + scale > 0) {
    	      sig = "+";
    	    }
    	    
    	    return +(Math.round(+arr[0] + "e" + sig + (+arr[1] + scale)) + "e-" + scale);
    	}
    },
    getFilterNumber : function(str) {
    	if(str.startsWith("-")) {      		
    		return parseInt(str.replace(/^[+-][^0-9]/g,''));
    	} else {
    		return parseInt(str.replace(/[^0-9]/g,''));    		
    	}
    },
    getFilterChar : function(str) {
    	str = str.toUpperCase();
    	return str.replace(/[^A-Z]/g,'');
    },
    /**
     * 문자열에서 구분자(쉼표)를 찾아 배열을 리턴한다.
     */
    getListFilterNumber : function(str, gubun) {
    	str = str.toUpperCase();
    	
    	var rstList = new Array();
    	var spstr = str.split(gubun);
    	
    	for(var cnt = 0; cnt < spstr.length; cnt++) {
    		if(str.startsWith("-")) {    			
    			rstList.push(spstr[cnt].replace(/^[+-][^0-9]/g,''));
    		} else {
    			rstList.push(spstr[cnt].replace(/[^0-9]/g,''));
    		}
    	}
    	
    	return rstList;
    },
    getListFilterAlphabet : function(str, gubun) {
    	str = str.toUpperCase();
    	
    	var rstList = new Array();
    	var gubuns = gubun.split(",");
    	
    	for(var i = 0; i < gubuns.length; i++) {
    		var spstr = str.split(gubuns[i]);
        	
        	for(var cnt = 0; cnt < spstr.length; cnt++) {
        		rstList.push(spstr[cnt].replace(/[^A-Z]/g,''));
        	}
    	}
    	
    	return rstList;
    },
    getFilterFloat : function(str, ftype, perc) {
    	var rst = null;
    	str = replaceAll(str, ",", "");
    	
    	if(oUtil.isNull(str)) {
    		rst = str;
    	} else {
    		if(str.startsWith("-")) {
    			rst = str.match(/^-?\d+([\/.]\d+)?/g);
    		} else {
    			rst = str.match(/\d+([\/.]\d+)?/g);
    		}
    	}
    	
    	if(!oUtil.isNull(perc) && perc >= 0) {
    		if(ftype == "C") rst = oUtil.getCeil(rst, perc); // 올림
    		else if(ftype == "F") rst = oUtil.getFloor(rst, perc); // 내림
    		else if(ftype == "T") rst = oUtil.getTruncate(rst, perc); // 버림
    		else rst = oUtil.getRound(rst, perc); // 반올림    		
    	}
    	
    	if(oUtil.isNull(rst)) rst = str;
    	
    	return (oUtil.isNull(rst))?null:rst;
    },
    getFilterChar : function(str) {
    	var rst = null;
    	
    	if(oUtil.isNull(str)) {
    		rst = str;
    	} else {
    		if(str.startsWith("-")) {    			
    			rst = str.replace(/^-?\d+([\/.]\d+)?/g, '');
    		} else {
    			rst = str.replace(/\d+([\/.]\d+)?/g, '');
    		}
    		
    	}
    	return (oUtil.isNull(rst))?null:rst;
    },
    getFilterIncoterms : function(str) {
    	var rst = null;
    	var source = ['CFR'
    		,'CPT'
    		,'CIF'
    		,'DAP'
    		,'FOB'
    		,'CNI'
    		,'DES'
    		,'DEQ'
    		,'DDP'
    		,'DDU'
    		,'EXW'
    		,'FAS'
    		,'FCA'
    		,'DAF'
    		,'DAT'
    		,'CIP'];
    	
    	if(oUtil.isNull(str)) {
    		rst = str;
    	} else {
	    	for(var i = 0; i < source.length; i++) {
	    		if(str.indexOf(source[i]) >= 0) {
	    			rst = source[i];
	    			break;
	    		}
	    	}
    	}
    	
    	return (oUtil.isNull(rst))?null:rst;
    },
    getFilterCurrency : function(str) {
    	var rst = null;
    	var source = ['BTN','NOK','PEN','OMR','PKR','PHP','PLZ','PTE','QAR','GTQ','ZAR','MVR','IDR','RUB','RUR','SAR','ATS','SGD','SKK','ESP','LKR',
    		'SEK','CHF','BDT','SIT','TTD','TRY','TND','TRL','GBP','UYU','KRW','JPY','CNY','ZWD','PLN','LTL','AFA','DZD','ARS','AMD','AWG',
    		'AUD','AZM','BSD','THB','PAB','BBD','BEF','VEB','BOB','BRL','BND','BGN','CAD','GHC','XOF','CLP','COP','CRC','HRK','CYP','CZK',
    		'DKK','DEM','DOP','VND','GRD','EGP','SVC','AED','EUR','FJD','HUF','FRF','PYG','HKD','ISK','INR','IEP','ITL','JMD','JOD','BHD',
    		'KES','EEK','KWD','AOA','KZT','LVL','LBP','ALL','ROL','MYR','MTL','FIM','MUR','MXN','MNT','MAD','NGN','NAD','NPR','ANG','NLG',
    		'ILS','TWD','NZD','USD'];
    	
    	if(oUtil.isNull(str)) {
    		rst = str;
    	} else {
	    	for(var i = 0; i < source.length; i++) {
	    		if(str.indexOf(source[i]) >= 0) {
	    			rst = source[i];
	    			break;
	    		}
	    	}
    	}
    	
    	return (oUtil.isNull(rst))?null:rst;
    },
    getFilterNation : function(str) {
    	var rst = null;
    	var source = [{CODE:"MV",NAME:"MALDIVES"},
    		{CODE:"ML",NAME:"MALI"},
    		{CODE:"MT",NAME:"MALTA"},
    		{CODE:"MH",NAME:"MARSHALL ISLANDS"},
    		{CODE:"MQ",NAME:"MARTINIQUE"},
    		{CODE:"MR",NAME:"MAURITANIA"},
    		{CODE:"YT",NAME:"MAYOTTE"},
    		{CODE:"FM",NAME:"MICRONESIA, FEDERATED STATES OF"},
    		{CODE:"MZ",NAME:"MOZAMBIQUE"},
    		{CODE:"MD",NAME:"MOLDOVA, REPUBLIC OF"},
    		{CODE:"MN",NAME:"MONGOLIA"},
    		{CODE:"ME",NAME:"MONTENEGRO"},
    		{CODE:"MS",NAME:"MONTSERRAT"},
    		{CODE:"MM",NAME:"MYANMAR"},
    		{CODE:"NA",NAME:"NAMIBIA"},
    		{CODE:"NR",NAME:"NAURU"},
    		{CODE:"NP",NAME:"NEPAL"},
    		{CODE:"NL",NAME:"NETHERLANDS"},
    		{CODE:"AN",NAME:"NETHERLANDS ANTILLES"},
    		{CODE:"NC",NAME:"NEW CALEDONIA"},
    		{CODE:"NZ",NAME:"NEW ZEALAND"},
    		{CODE:"NI",NAME:"NICARAGUA"},
    		{CODE:"NE",NAME:"NIGER"},
    		{CODE:"NG",NAME:"NIGERIA"},
    		{CODE:"NU",NAME:"NIUE"},
    		{CODE:"ZZ",NAME:"Non Origin"},
    		{CODE:"NF",NAME:"NORFOLK ISLAND"},
    		{CODE:"MP",NAME:"NORTHERN MARIANA ISLANDS"},
    		{CODE:"NO",NAME:"NORWAY"},
    		{CODE:"OM",NAME:"OMAN"},
    		{CODE:"PK",NAME:"PAKISTAN"},
    		{CODE:"PA",NAME:"PANAMA"},
    		{CODE:"PG",NAME:"PAPUA NEW GUINEA"},
    		{CODE:"PY",NAME:"PARAGUAY"},
    		{CODE:"PH",NAME:"PHILIPPINES"},
    		{CODE:"PN",NAME:"PITCAIRN"},
    		{CODE:"PL",NAME:"POLAND"},
    		{CODE:"PR",NAME:"PUERTO RICO"},
    		{CODE:"QA",NAME:"QATAR"},
    		{CODE:"RE",NAME:"REUNION"},
    		{CODE:"RO",NAME:"ROMANIA"},
    		{CODE:"RU",NAME:"RUSSIAN FEDERATION"},
    		{CODE:"RW",NAME:"RWANDA"},
    		{CODE:"BL",NAME:"SAINT BARTHELEMY"},
    		{CODE:"SH",NAME:"SAINT HELENA"},
    		{CODE:"KN",NAME:"SAINT KITTS AND NEVIS"},
    		{CODE:"LC",NAME:"SAINT LUCIA"},
    		{CODE:"MF",NAME:"SAINT MARTIN"},
    		{CODE:"PM",NAME:"SAINT PIERRE AND MIQUELON"},
    		{CODE:"VC",NAME:"SAINT VINCENT AND THE GRENADINES"},
    		{CODE:"WS",NAME:"SAMOA"},
    		{CODE:"SM",NAME:"SAN MARINO"},
    		{CODE:"ST",NAME:"SAO TOME AND PRINCIPE"},
    		{CODE:"SA",NAME:"SAUDI ARABIA"},
    		{CODE:"SN",NAME:"SENEGAL"},
    		{CODE:"RS",NAME:"SERBIA"},
    		{CODE:"SC",NAME:"SEYCHELLES"},
    		{CODE:"SL",NAME:"SIERRA LEONE"},
    		{CODE:"SG",NAME:"SINGAPORE"},
    		{CODE:"SK",NAME:"SLOVAKIA"},
    		{CODE:"SI",NAME:"SLOVENIA"},
    		{CODE:"SB",NAME:"SOLOMON ISLANDS"},
    		{CODE:"SO",NAME:"SOMALIA"},
    		{CODE:"ZA",NAME:"SOUTH AFRICA"},
    		{CODE:"GS",NAME:"SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS"},
    		{CODE:"ES",NAME:"SPAIN"},
    		{CODE:"LK",NAME:"SRI LANKA"},
    		{CODE:"SD",NAME:"SUDAN"},
    		{CODE:"DK",NAME:"DENMARK"},
    		{CODE:"DJ",NAME:"DJIBOUTI"},
    		{CODE:"DM",NAME:"DOMINICA"},
    		{CODE:"GI",NAME:"GIBRALTAR"},
    		{CODE:"GR",NAME:"GREECE"},
    		{CODE:"GL",NAME:"GREENLAND"},
    		{CODE:"GD",NAME:"GRENADA"},
    		{CODE:"GP",NAME:"GUADELOUPE"},
    		{CODE:"GU",NAME:"GUAM"},
    		{CODE:"GT",NAME:"GUATEMALA"},
    		{CODE:"GG",NAME:"GUERNSEY"},
    		{CODE:"GN",NAME:"GUINEA"},
    		{CODE:"GW",NAME:"GUINEA-BISSAU"},
    		{CODE:"GY",NAME:"GUYANA"},
    		{CODE:"HT",NAME:"HAITI"},
    		{CODE:"HM",NAME:"HEARD ISLAND AND MCDONALD ISLANDS"},
    		{CODE:"VA",NAME:"HOLY SEE (VATICAN CITY STATE)"},
    		{CODE:"HN",NAME:"HONDURAS"},
    		{CODE:"HK",NAME:"HONG KONG"},
    		{CODE:"HU",NAME:"HUNGARY"},
    		{CODE:"IS",NAME:"ICELAND"},
    		{CODE:"IN",NAME:"INDIA"},
    		{CODE:"ID",NAME:"INDONESIA"},
    		{CODE:"IR",NAME:"IRAN, ISLAMIC REPUBLIC OF"},
    		{CODE:"IQ",NAME:"IRAQ"},
    		{CODE:"IE",NAME:"IRELAND"},
    		{CODE:"IM",NAME:"ISLE OF MAN"},
    		{CODE:"IL",NAME:"ISRAEL"},
    		{CODE:"IT",NAME:"ITALY"},
    		{CODE:"JM",NAME:"JAMAICA"},
    		{CODE:"JP",NAME:"JAPAN"},
    		{CODE:"JE",NAME:"JERSEY"},
    		{CODE:"JO",NAME:"JORDAN"},
    		{CODE:"KZ",NAME:"KAZAKHSTAN"},
    		{CODE:"KE",NAME:"KENYA"},
    		{CODE:"KI",NAME:"KIRIBATI"},
    		{CODE:"CD",NAME:"Konggo"},
    		{CODE:"KP",NAME:"KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF"},
    		{CODE:"KR",NAME:"KOREA, REPUBLIC OF"},
    		{CODE:"KW",NAME:"KUWAIT"},
    		{CODE:"KG",NAME:"KYRGYZSTAN"},
    		{CODE:"LA",NAME:"LAO PEOPLE'S DEMOCRATIC REPUBLIC"},
    		{CODE:"LV",NAME:"LATVIA"},
    		{CODE:"LB",NAME:"LEBANON"},
    		{CODE:"LS",NAME:"LESOTHO"},
    		{CODE:"LR",NAME:"LIBERIA"},
    		{CODE:"LY",NAME:"LIBYAN ARAB JAMAHIRIYA"},
    		{CODE:"LI",NAME:"LIECHTENSTEIN"},
    		{CODE:"LT",NAME:"LITHUANIA"},
    		{CODE:"LU",NAME:"LUXEMBOURG"},
    		{CODE:"MO",NAME:"MACAO"},
    		{CODE:"MK",NAME:"MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF"},
    		{CODE:"MG",NAME:"MADAGASCAR"},
    		{CODE:"MW",NAME:"MALAWI"},
    		{CODE:"SR",NAME:"SURINAME"},
    		{CODE:"SJ",NAME:"SVALBARD AND JAN MAYEN"},
    		{CODE:"SZ",NAME:"SWAZILAND"},
    		{CODE:"SE",NAME:"SWEDEN"},
    		{CODE:"CH",NAME:"SWITZERLAND"},
    		{CODE:"SY",NAME:"SYRIAN ARAB REPUBLIC"},
    		{CODE:"TW",NAME:"TAIWAN, PROVINCE OF CHINA"},
    		{CODE:"TZ",NAME:"TANZANIA, UNITED REPUBLIC OF"},
    		{CODE:"TJ",NAME:"TAJIKISTAN"},
    		{CODE:"TH",NAME:"THAILAND"},
    		{CODE:"TL",NAME:"TIMOR-LESTE"},
    		{CODE:"TG",NAME:"TOGO"},
    		{CODE:"TK",NAME:"TOKELAU"},
    		{CODE:"TO",NAME:"TONGA"},
    		{CODE:"TT",NAME:"TRINIDAD AND TOBAGO"},
    		{CODE:"TN",NAME:"TUNISIA"},
    		{CODE:"TR",NAME:"TURKEY"},
    		{CODE:"TM",NAME:"TURKMENISTAN"},
    		{CODE:"TC",NAME:"TURKS AND CAICOS ISLANDS"},
    		{CODE:"TV",NAME:"TUVALU"},
    		{CODE:"UG",NAME:"UGANDA"},
    		{CODE:"MY",NAME:"MALAYSIA"},
    		{CODE:"UA",NAME:"UKRAINE"},
    		{CODE:"AE",NAME:"UNITED ARAB EMIRATES"},
    		{CODE:"GB",NAME:"UNITED KINGDOM"},
    		{CODE:"US",NAME:"UNITED STATES"},
    		{CODE:"UM",NAME:"UNITED STATES MINOR OUTLYING ISLANDS"},
    		{CODE:"UY",NAME:"URUGUAY"},
    		{CODE:"UZ",NAME:"UZBEKISTAN"},
    		{CODE:"VU",NAME:"VANUATU"},
    		{CODE:"VE",NAME:"VENEZUELA, BOLIVARIAN REPUBLIC OF"},
    		{CODE:"VN",NAME:"VIET NAM"},
    		{CODE:"VG",NAME:"VIRGIN ISLANDS, BRITISH"},
    		{CODE:"VI",NAME:"VIRGIN ISLANDS, U.S."},
    		{CODE:"WF",NAME:"WALLIS AND FUTUNA"},
    		{CODE:"EH",NAME:"WESTERN SAHARA"},
    		{CODE:"YE",NAME:"YEMEN"},
    		{CODE:"YU",NAME:"YUGOSLAVIA"},
    		{CODE:"ZR",NAME:"ZAIRE"},
    		{CODE:"ZM",NAME:"ZAMBIA"},
    		{CODE:"ZW",NAME:"ZIMBABWE"},
    		{CODE:"PE",NAME:"페루"},
    		{CODE:"MX",NAME:"Mexico"},
    		{CODE:"AF",NAME:"AFGHANISTAN"},
    		{CODE:"AX",NAME:"ALAND ISLANDS"},
    		{CODE:"AL",NAME:"ALBANIA"},
    		{CODE:"DZ",NAME:"ALGERIA"},
    		{CODE:"AS",NAME:"AMERICAN SAMOA"},
    		{CODE:"AD",NAME:"ANDORRA"},
    		{CODE:"AO",NAME:"ANGOLA"},
    		{CODE:"AI",NAME:"ANGUILLA"},
    		{CODE:"AQ",NAME:"ANTARCTICA"},
    		{CODE:"AG",NAME:"ANTIGUA AND BARBUDA"},
    		{CODE:"AR",NAME:"ARGENTINA"},
    		{CODE:"AM",NAME:"ARMENIA"},
    		{CODE:"AW",NAME:"ARUBA"},
    		{CODE:"AU",NAME:"AUSTRALIA"},
    		{CODE:"AT",NAME:"AUSTRIA"},
    		{CODE:"AZ",NAME:"AZERBAIJAN"},
    		{CODE:"BS",NAME:"BAHAMAS"},
    		{CODE:"BH",NAME:"BAHRAIN"},
    		{CODE:"BD",NAME:"BANGLADESH"},
    		{CODE:"BB",NAME:"BARBADOS"},
    		{CODE:"BY",NAME:"BELARUS"},
    		{CODE:"BE",NAME:"BELGIUM"},
    		{CODE:"BZ",NAME:"BELIZE"},
    		{CODE:"BJ",NAME:"BENIN"},
    		{CODE:"PT",NAME:"PORTUGAL"},
    		{CODE:"BM",NAME:"BERMUDA"},
    		{CODE:"BT",NAME:"BHUTAN"},
    		{CODE:"BO",NAME:"BOLIVIA, PLURINATIONAL STATE OF"},
    		{CODE:"BA",NAME:"BOSNIA AND HERZEGOVINA"},
    		{CODE:"BW",NAME:"BOTSWANA"},
    		{CODE:"BV",NAME:"BOUVET ISLAND"},
    		{CODE:"BR",NAME:"BRAZIL"},
    		{CODE:"IO",NAME:"BRITISH INDIAN OCEAN TERRITORY"},
    		{CODE:"BN",NAME:"BRUNEI DARUSSALAM"},
    		{CODE:"BG",NAME:"BULGARIA"},
    		{CODE:"BF",NAME:"BURKINA FASO"},
    		{CODE:"BI",NAME:"BURUNDI"},
    		{CODE:"KH",NAME:"CAMBODIA"},
    		{CODE:"CM",NAME:"CAMEROON"},
    		{CODE:"CA",NAME:"CANADA"},
    		{CODE:"CV",NAME:"CAPE VERDE"},
    		{CODE:"KY",NAME:"CAYMAN ISLANDS"},
    		{CODE:"CF",NAME:"CENTRAL AFRICAN REPUBLIC"},
    		{CODE:"TD",NAME:"CHAD"},
    		{CODE:"CL",NAME:"CHILE"},
    		{CODE:"CN",NAME:"CHINA"},
    		{CODE:"CX",NAME:"CHRISTMAS ISLAND"},
    		{CODE:"CC",NAME:"COCOS (KEELING) ISLANDS"},
    		{CODE:"CO",NAME:"COLOMBIA"},
    		{CODE:"KM",NAME:"COMOROS"},
    		{CODE:"CG",NAME:"CONGO"},
    		{CODE:"CK",NAME:"COOK ISLANDS"},
    		{CODE:"CR",NAME:"COSTA RICA"},
    		{CODE:"CI",NAME:"COTE D'IVOIRE"},
    		{CODE:"HR",NAME:"CROATIA (local name: Hrvatska)"},
    		{CODE:"CU",NAME:"CUBA"},
    		{CODE:"CY",NAME:"CYPRUS"},
    		{CODE:"CZ",NAME:"CZECH REPUBLIC"},
    		{CODE:"DO",NAME:"DOMINICAN REPUBLIC"},
    		{CODE:"EC",NAME:"ECUADOR"},
    		{CODE:"EG",NAME:"EGYPT"},
    		{CODE:"SV",NAME:"EL SALVADOR"},
    		{CODE:"GQ",NAME:"EQUATORIAL GUINEA"},
    		{CODE:"ER",NAME:"ERITREA"},
    		{CODE:"EE",NAME:"ESTONIA"},
    		{CODE:"ET",NAME:"ETHIOPIA"},
    		{CODE:"FO",NAME:"FAROE ISLANDS"},
    		{CODE:"FJ",NAME:"FIJI"},
    		{CODE:"FI",NAME:"FINLAND"},
    		{CODE:"FR",NAME:"FRANCE"},
    		{CODE:"FX",NAME:"FRANCE, METROPOLITAN"},
    		{CODE:"GF",NAME:"FRENCH GUIANA"},
    		{CODE:"PF",NAME:"FRENCH POLYNESIA"},
    		{CODE:"TF",NAME:"FRENCH SOUTHERN TERRITORIES"},
    		{CODE:"GA",NAME:"GABON"},
    		{CODE:"GM",NAME:"GAMBIA"},
    		{CODE:"GE",NAME:"GEORGIA"},
    		{CODE:"DE",NAME:"GERMANY"},
    		{CODE:"GH",NAME:"GHANA"},
    		{CODE:"KR",NAME:"SOUTH KOREA"}];
    	
    	if(oUtil.isNull(str)) {
    		rst = str;
    	} else {
	    	for(var i = 0; i < source.length; i++) {
	    		if(str.toUpperCase().indexOf(source[i].NAME) >= 0) {
	    			rst = source[i].CODE;
	    			break;
	    		}
	    	}
    	}
    	
    	return (oUtil.isNull(rst))?null:rst;
    },
    getFilterDate : function(str) {
    	var rst = null;
    	
    	// 데이트 타입 변환
    	
    	if(oUtil.isNull(str)) {
    		rst = str;
    	} else {
	    	try {
	    		let date = new Date(str);
	    		
	    		var yyyy = date.getFullYear();
	            var mm = date.getMonth() + 1;
	            var dd = date.getDate();
	            
	            rst = calendar.util.chageDate2Nation(yyyy, mm, dd, "", NATION_DATE_VIEW);
	    	} catch(e) {
	    		rst = str;
	    	}
    	}
    	
    	return (oUtil.isNull(rst))?str:rst;
    },
    /**
     * 문자열 자르기
     * val = 자를 문자열
     * st : 시작위치(1~999)
     * en : 시작위치에서부터 자를 문자수(1~999)
     */
    MID : function(val, st, en) {
    	// 종료위치(시작위치 + 글짜수)
    	en = en+st;
    	
    	// 시작위치(0~99)
    	if(st == 0) st = 0;
    	else st = st-1;
    	
    	if(st >= 0 && en >= 0) {
    	    return val.substring(st, en);
    	} else {
    		return "";
    	}
    },
    toFloat : function(number){
    	var tmp = number + "";

		if(tmp.indexOf(".") != -1){
			number = number.replace(/(0+$)/, "");
			
			if(number.charAt(number.length-1) == ".") {
				number = number.replace(".", "");
			}
		}
		
		return number;
	},
	/**
	 * 실수인 경우에 표시할 소수점 자릿수 뒷 숫자기준으로 반올림함
	 * 
	 * @param nm = 변환전 값
	 * @param prcs = 표시할 소수점 자릿수 
	 */
	getRound : function(nm, prcs) {
		if(oUtil.isNull(prcs)) return nm;
		
		var fnm = parseFloat(nm);
		var count = Math.pow(10, prcs); // 반올림을 할 자리수(ex. 소수점 둘째자리에서 반올림을 하고 싶다면 2이다.)
		var num = Math.round(fnm * count) / count;
				
		return num.toFixed(prcs); // 0을 채워주기 toFixed를 이용
	},
	/**
	 * 실수인 경우에 표시할 소수점 자릿수 뒷 숫자기준으로 올림함
	 * 
	 * @param nm = 변환전 값
	 * @param prcs = 표시할 소수점 자릿수 
	 */
	getCeil : function(nm, prcs) {
		if(oUtil.isNull(prcs)) return nm;
		
		var fnm = parseFloat(nm);
		var count = Math.pow(10, prcs);
		var num = Math.ceil(fnm * count) / count;
		
		return num.toFixed(prcs); // 0을 채워주기 toFixed를 이용
	},
	/**
	 * 실수인 경우에 표시할 소수점 자릿수 뒷 숫자기준으로 내림함
	 * 
	 * @param nm = 변환전 값
	 * @param prcs = 표시할 소수점 자릿수 
	 */
	getFloor : function(nm, prcs) {
		if(oUtil.isNull(prcs)) return nm;
		
		var fnm = parseFloat(nm);
		var count = Math.pow(10, prcs);
		var num = Math.floor(fnm * count) / count;

		return num.toFixed(prcs); // 0을 채워주기 toFixed를 이용
	},
	/**
	 * 소수점 자리 버림
	 * 
	 * @param nm = 변환전 값
	 * @param prcs = 표시할 소수점 자릿수 
	 */
	getTruncate : function(nm, prcs) {
		if(oUtil.isNull(prcs)) return nm;
		
		return formatFloat(nm, prcs);
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
	 * - 스페인어(ES)는 추후 번역작업 시 정상적으로 등록하기 위해 현재는 과거 메시지코드를 그대로 사용함(2020.05.06) 
	 * - 통관 시스템의 메시지는 CC_+메시지로 규정하고 실제 다국어 표시할 경우 CC_를 제거함(메시지 다국어 처리 시 CC_로 검색하면 됨)
	 * 
	 * @param msg = 원본 메시지
	 * @param args = 치환을 위한 문자열 배열
	 * @return 치환이 완료된 메시지
	 */
	getMessage : function(msg, args) {
		var rstMsg = "";
		
		if(msg.startsWith("CC_")) {
			msg = replaceAll(msg, "CC_", "");
		} else if(msg.startsWith("SS_")) {
			msg = replaceAll(msg, "SS_", "");
		} else if(DEFAULT_LANGUAGE != "ES") {
			// 기존에 사용한 메시지코드 조합을 하나로 메시코드로 만들어서 처리하도록 메시지 코드를 수정함
	    	// 쉼표는 under bar로 표시하고 공백은 제거한다.
			// 특수문자는 under bar + 아스키 코드로 변환한다.
			if(APPLICATION_CHANGE_MSGCODE) {
				msg = replaceAll(msg, ",", "_");
				msg = msg.replace(/(\s*)/g, "");
				msg = this.changeCode4Message(msg);
			}
		}
		
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
		
		// 특정 문자열을 변경(김종현, 2019-05-16)
		try {
        	var cwlist = null;
            
            if(DEFAULT_LANGUAGE == "KR" || DEFAULT_LANGUAGE == "KO") {
            	cwlist = CONVERSION_WORD_KR;
            } else if(DEFAULT_LANGUAGE == "EN") {
            	cwlist = CONVERSION_WORD_EN;
            } else if(DEFAULT_LANGUAGE == "ES") {
            	cwlist = CONVERSION_WORD_ES;
            }
            
            if(!oUtil.isNull(cwlist)) {
	            for(var i = 0; i < cwlist.length; i++) {
	            	var name = cwlist[i].name;
	            	var word = cwlist[i].word;
	            	
	            	rstMsg = replaceAll(rstMsg, name, word);
	           }
            }
        } catch(e) { }
        
		return rstMsg;
	},
	
	/**
     * 파일명에서 사용할 수 없는 특수문자에 대해 공백으로 치환시킨다.
     * 
     * @param code : 메시지 코드
     * @return 변환시킨 메시지 코드
     */
    changeCode4FileName : function(code) {
    	var reStr = code;
		var oStr = ["<", ">", "/", "|", ":"];
		
		for(var i = 0; i < oStr.length; i++) {
			reStr = reStr.replace(oStr[i], "");
		}
		
		return reStr;
    },
	
	/**
     * 메시지 코드에 포함된 특수문자를 아스키 코드로 변환시킨다.
     * 
     * @param code : 메시지 코드
     * @return 변환시킨 메시지 코드
     */
    changeCode4Message : function(code) {
    	var reStr = code;
		var oStr = ["(", ")", "<", ">", "/", "%", "-", "|", "+", "&", ":", "[", "]"];
		var nStr = ["_40", "_41", "_60", "_62", "_47", "_37", "_45", "_124", "_43", "_38", "_58", "_91", "_93"];
		
		for(var i = 0; i < oStr.length; i++) {
			reStr = reStr.replace(oStr[i], nStr[i]);
		}
		
		return reStr;
    },
    
    /**
     * 메시지 코드에 포함된 특수문자를 아스키 코드로 변환시킨다.
     * 
     * @param code : 메시지 코드
     * @return 변환시킨 메시지 코드
     */
    changeDecode4String: function(code) {
    	var reStr = code;
    	var oStr = ["_40", "_41", "_60", "_62", "_47", "_37", "_45", "_124", "_43", "_38", "_58", "_91", "_93"];
		var nStr = ["(", ")", "<", ">", "/", "%", "-", "|", "+", "&", ":", "[", "]"];
		
		for(var i = 0; i < oStr.length; i++) {
			reStr = reStr.replace(oStr[i], nStr[i]);
		}
		
		return reStr;
    },
	
    /**
     * 그리드 헤더정보의 title를 메시지코드로 변환해서 리턴함
     * 
     * @param code : 메시지 코드
     * @return 변환시킨 메시지 코드
     */
	getSplitMessage : function(code) {
		var rstMsg = "";
		var arrParam = code.split("+");
		
		for(var cnt = 0; cnt < arrParam.length; cnt++) {
        	var str = arrParam[cnt];
            
        	if(cnt != 0) {
        		if(str.substring(0,1) == " ") rstMsg += " ";
        	}
            
            try {
            	rstMsg += this.getMessage(str);
            } catch(e) {
            	rstMsg += str;
            }
        }
		
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

function formatFloat(number, precision, format) {
    if (oUtil.isNull(number)) return "";
    var num = number.toString().split(".");
    var str1 = formatNumber(num[0]); // 숫자
    var str2 = ""; // 소수점
    var sepoint = 0; // 세션 금액 소수점 자리수
    
    // 세션 금액 소수점 자리수가 0이상인 경우에는 소수점 자릿수를 fix해서 보여주도록 기능 추가(2020.04.27)
    try {
    	if(!oUtil.isNull(SESSION.AMOUNT_POINT_NM)) {
    		sepoint = parseInt(SESSION.AMOUNT_POINT_NM);
    	}
    } catch(e) {
    	sepoint = 0;
    }
    
    if(format == "amount" && sepoint > 0) {
    	precision = sepoint;
    }
    
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
    
    if(format == "amount" && sepoint > 0) {
    	return str1 + "." + str2;
    }
    
    if (oUtil.isNull(str2)) {
        return str1;
    }
    
    if(parseInt(str2) == 0) {
    	return str1;
    } else {
        return oUtil.toFloat(str1 + "." + str2);
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
 * 화면에서 키보드로 백스페이스 및 F5, Ctrl+r키 막기, ESC Key 누르면 Dailog창 닫기
 * @param e 키보드 이벤트 객체
 */
$(this).keydown(function (event) {
	var code = event.which ? event.which : event.keyCode;
	
    var components = ["text", "textarea", "combobox", "password", "calendar"];
    var breakYn = false;
    
    for(var i = 0; i < components.length; i++) {
        if(components[i] == event.srcElement.type) {
            breakYn = true;
        }
    }
    
    // F5(116), Esc(27), Alt(18), Ctrl+N(78), Ctrl+R(82), backspace(8), 
    if(!breakYn) {
//        if(code == 116) {
//            event.keyCode = 505;
//            event.cancelBubble = true;        
//            event.returnValue =false;
//        }
    	
    	if(code == 40) { // 화살표 아래로 이동
    		var did = dialogSet.arrayLast();
    		var initConf = dialog.getInitConfig(did);
    		var pid = form.handle.getProgramID(did); // dialog 프로그램 ID
    		
    		if(initConf.arrowEvent) {
	    		var callback = "callByArrowDown";
	    		var pro_id = eval("window." + pid + ".event");
	            if (!oUtil.isNull(pro_id)) {
	                if (typeof(pro_id[callback]) == "function") {
	                    pro_id[callback]();
	                }
	            }
    		}
    	}
    	if(code == 38) { // 화살표 위로 이동
    		var did = dialogSet.arrayLast();
    		var initConf = dialog.getInitConfig(did);
    		var pid = form.handle.getProgramID(did); // dialog 프로그램 ID
    		
    		if(initConf.arrowEvent) {
	    		var callback = "callByArrowUp";
	    		var pro_id = eval("window." + pid + ".event");
	            if (!oUtil.isNull(pro_id)) {
	                if (typeof(pro_id[callback]) == "function") {
	                    pro_id[callback]();
	                }
	            }
    		}
    	}
        if(event.ctrlKey && (code == 78 || code ==82)) {
            return false;
        }
        if(code == 8) {
            return false;
        }
        if(code == 122) {
            event.keyCode = 505;
            event.cancelBubble = true;        
            event.returnValue = false;
        }
        if(code == 27) {
        	var did = dialogSet.arrayPop();
        	
        	if(!oUtil.isNull(did)) {
	        	var dl = dialog.getObject(did);
	        	var initConf = dialog.getInitConfig(did);
	        	
	        	if(initConf.closable) {
	        		return dialog.handle.close(dl);
        		} else {
        			return;
        		}
	        } else {
	        	return false;
	        }
        }
        if (code == 505) {
            return false;
        }
    }
    
    if(code == 112) { // F1
    	MM0001_01.dialog.dialogOpen_1();
    	return false;
    }
});

/** 
 * input 숫자와 콤마만 입력되게 함
 * 좌/우/탭 키를 누를 경우에는 변환시키지 않음
 */
$(this).delegate("input:text", "keyup", function() {
	if($(this).hasClass('easyui-numberbox') || $(this).hasClass('numberComma')) {
		try {
			var keycd = event.keyCode;
			//console.log("keyup = " + keycd);
			if(keycd != 9 && keycd != 13 && keycd != 37 && keycd != 38 && keycd != 39 && keycd != 40 && keycd != 46 && keycd != 8) {
				$(this).inputmask({ alias: 'decimal', autoGroup: true, groupSeparator: ","});
			}
		} catch(e) { }
	}
});

$(this).delegate("input:text", "focus", function() {
	if($(this).hasClass('easyui-numberbox') || $(this).hasClass('numberComma')) {
		try {
			var keycd = event.keyCode;
			//console.log("focus = " + keycd);
			if(keycd != 9 && keycd != 13 && keycd != 37 && keycd != 38 && keycd != 39 && keycd != 40 && keycd != 46 && keycd != 8) {
				$(this).inputmask({alias: 'decimal', autoGroup: true, groupSeparator: ","});
			}
		} catch(e) { }
	}
});

/**
 * input에서 엔터를 치면 지정된 function이 호출됨
 */
$(this).delegate("input", "keypress", function(e) {
    try {
    	if (e.which == 13) {
    		var callFunc = $(this).attr('search');
            
    		if(!oUtil.isNull(callFunc)) {
    			var func = eval(callFunc);
    			
        		if (typeof(func) == "function") {
        			func();
                    
                    return;
        		}
    		}
            
            return;
    	}
    } catch(e) {}
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

function isIE(){
    var ua = window.navigator.userAgent;

    return ua.indexOf('MSIE ') > 0 || ua.indexOf('Trident/') > 0 || ua.indexOf('Edge/') > 0
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
    
    this.sleep = function(ms) {
        return new Promise(res => setTimeout(res, ms));
    }
};

var WINDOW = new windowControlClass();