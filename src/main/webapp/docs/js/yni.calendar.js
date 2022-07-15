var calendarMethod = function() {
    init = {
        
		/**
	     * edit 활성화 여부 설정(default=false)
	     * 
	     * @param boolean = edit가능:true, readonly:false 
	     */
	    setEditable : function(boolean) {
	        this.getConfig().editable = boolean;
	    },
        
	    /**
	     * 필수입력여부 지정
	     * 
	     * @param boolean = 필수:true, 필수아님:false 
	     */
	    setRequired : function(boolean) {
	        this.getConfig().required = boolean;
	    },
	    
        /**
	     * 날짜 형식의 구분 문자 설정(default="-")
	     * 
	     * @param string = 구분문자
	     */
	    setSeperator : function(string) {
	        this.getConfig().seperator = string;
	    },
	    
	    /**
	     * 날짜을 표현할 국가를 지정함(default=KR)
	     * 
	     * @param string = 국가코드(DB의 국가코드 참고)
	     */
	    setNation : function(string) {
	        this.getConfig().nation = string;
	    },
	    
	    /**
	     * 달력이 선택되었을 때 호출될 함수 설정
	     * 
	     * @param string = 함구명
	     */
	    setCallFunction : function(string) {
	        this.getConfig().callLoadFunc = string;
	    },
	    
	    /**
	     * 달력생성 시 입력될 날짜 설정
	     * 
	     * @param date = new Date()
	     */
	    setInitDate : function(date) {
	        this.getConfig().initDate = date;
	    },
	    
	    /**
         * 날짜 구분을 분석하여 기본 함수 분석을 재정의하는 함수
         * 
         * @param formId = form ID
         * @param tagId = calendar ID
         */
        setParser : function(formId, tagId) {
	        this.getConfig().parser = function(s) {
	        	if (!s) return new Date();
	        	
	        	var ss = (s.split('-'));
	        	var y = parseInt(ss[0],10);
	        	var m = parseInt(ss[1],10);
	        	var d = parseInt(ss[2],10);
	            
	            if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
	            	return new Date(y,m-1,d);
	            } else {
	                return new Date();
	            }
	        }
        },
        
        /**
         * 날짜 형태를 함수에서 지정된 형태로 변환해서 반환함(default는 KR 기준임)
         * 
         * @param nation = 국가
         */
        setFormatter : function(formId, tagId) {
	        this.getConfig().formatter = function(date) {
	        	var conf = calendar.getInitConfig(formId, tagId);
	        	var seperator = conf.seperator;
	        	var nation = conf.nation;
	        	
	            var y = date.getFullYear();
	            var m = date.getMonth() + 1;
	            var d = date.getDate();
	            
	            var format = calendar.util.chageDate2Nation(y, m, d, seperator, nation);
	            
	            return format;
	        }
        }
	    
    },
    event = {
    	
        /**
         * 날짜를 선택했을 때 호출되는 함수로 콜백함수가 지정된 경우 호출함<br>
         * (window.[progrma id].calendar.[init.setCallFunction 명])
         * 
         * @param formId = form ID
         * @param tagId = calendar ID 
         */
        setOnSelect : function(formId, tagId) {
	        this.getConfig().onSelect = function(date) {
	        	var conf = calendar.getInitConfig(formId, tagId);
	            /*
	            var year = date.getFullYear();
	            var month = (date.getMonth() + 1);
	            var day = date.getDate();
	            
	            if (month < 10) month = "0" + month;
	            if (day < 10) day = "0" + day;
	            
	            //form.handle.setValue(formId, tagId.replace("_CALENDAR", ""), year + "" + month + "" + day);
	            */
	            var callFunc = conf.callLoadFunc;
	            
	            if (!oUtil.isNull(callFunc)) {
	        		var funcPath = eval("window." + form.handle.getProgramID(formId) + ".event");
	        		
            		if (typeof(funcPath[callFunc]) == "function") {
            			funcPath[callFunc](date);
            		}
                }
	        }
        }
        
    },
    handle = {
    	
    	/** 날짜 정보 획득
    	 * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param beSep =현재 구분자
         * @param afSep = 변환할 구분자
         */
        getDate : function(formId, tagId) {
            var obj = form.getObject(formId, tagId);
            var conf = calendar.getInitConfig(formId, tagId);
        	var seperator = conf.seperator;
        	var nation = conf.nation;
        	
        	var value = obj.datebox('getValue');
        	var date = (value.split(seperator));
        	
        	var y = "";
            var m = "";
            var d = "";
        	if(nation == "US") {
	            var m = parseInt(date[0], 10);
	            var d = parseInt(date[1], 10);
	            var y = parseInt(date[2], 10);
        	} else {
        		var y = parseInt(date[0], 10);
	            var m = parseInt(date[1], 10);
	            var d = parseInt(date[2], 10);
        	}
            
            return calendar.util.chageDate2Nation(y, m, d, "", NATION_DATE_DB);
        },
        
        /** 날짜 입력
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param date = 넣어줄 날짜(20020101 형태)
         */
        setDate : function(formId, tagId, date) {
            var obj = form.getObject(formId, tagId);
            var conf = calendar.getInitConfig(formId, tagId);
        	var seperator = conf.seperator;
        	var nation = conf.nation;
        	
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            
            obj.datebox('setValue', calendar.util.chageDate2Nation(y, m, d, seperator, nation));
        },
        
        disabled : function(formId, tagId, flag) {
        	var obj = form.getObject(formId, tagId);
        	
        	if(oUtil.isNull(flag)) flag = false;
        	
        	obj.datebox({
        		disabled : flag
        	});
        },
        
        readonly : function(formId, tagId, flag) {
        	var obj = form.getObject(formId, tagId);
        	
        	if(oUtil.isNull(flag)) flag = false;
        	
        	obj.datebox({
        		readonly : flag
        	});
        }
        
    },
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 죄송합니다.... 유틸은 정비중이므로 사용하지 마세요.~~~~~~~~~~~~~~~~~~~~~~~~
    ////////////////////////////////////////////////////////////////////////////////////////////////
    util = {   
    	
    	/**
    	 * 문자열 타입의 날짜를 Date타입으로 변환한 후 리턴
    	 * 
    	 * @param date = 날짜:string
    	 */
    	parseDate : function(date, seperator, nation) {
    		var date = date.split(seperator);
    		
    		var y = "";
            var m = "";
            var d = "";
    		if(nation == "US") {
	            m = parseInt(date[0], 10);
	            d = parseInt(date[1], 10);
	            y = parseInt(date[2], 10);
        	} else {
        		y = parseInt(date[0], 10);
	            m = parseInt(date[1], 10);
	            d = parseInt(date[2], 10);
        	}
    		
    		return new Date(y,m-1,d);
    	},
    	
    	/**
         * 현재 년도를 문자열 형태로 반환
         * 
         * @returns yyyy 또는 함수 입력시 지정된 구분자를 가지는 현재일자의 yyyy 값
         */
        toYear2String : function() {
            var today = new Date();
            var yyyy;
            
            yyyy = today.getFullYear();
            
            return yyyy;
        },
        
        /**
         * 현재 년,월를 문자열 형태로 반환
         * 
         * @param pDelimiter : pYyyymm 값에 사용된 구분자를 설정 (없으면 "" 입력)
         * @returns yyyymm 또는 함수 입력시 지정된 구분자를 가지는 현재일자의 yyyy?mm 값
         */
        toMonth2String : function(seperator) {
            var today = new Date();
            var yyyy, mm;
            
            yyyy = today.getFullYear();
            mm = today.getMonth() + 1;
            
            if(oUtil.isNull(seperator)) seperator = "";
            
            return this.chageDate2Nation(yyyy, mm, null, seperator, NATION_DATE_VIEW);
        },
        
        /**
    	 * 현재 년,월,일를 문자열 형태로 반환
    	 * 
    	 * @param pDelimiter : pYyyymmdd 값에 사용된 구분자를 설정 (없으면 "" 입력)
    	 * @returns yyyymmdd 또는 함수 입력시 지정된 구분자를 가지는 현재일자의 yyyy?mm?dd 값
    	 */
        toDate2String : function(seperator) {
            var today = new Date();
            var yyyy, mm, dd;
            
            yyyy = today.getFullYear();
            mm = today.getMonth() + 1;
            dd = today.getDate();
            
            if(oUtil.isNull(seperator)) seperator = "";
            
            return this.chageDate2Nation(yyyy, mm, dd, seperator, NATION_DATE_VIEW);
        },
        
        /**
         * 특정 날짜에 대해 지정한 값만큼 가감(+-)한 날짜를 반환<br>
         * 예시)<br>
         * 2008-01-01 에 3 일 더하기 ==> addDate("d", 3, "2008-08-01", "-");<br>
         * 20080301 에 8 개월 더하기 ==> addDate("m", 8, "20080301", "");<br>
         * 
         * @param pInterval : "yyyy" 는 연도 가감, "m" 은 월 가감, "d" 는 일 가감
         * @param pAddVal : 가감 하고자 하는 값 (정수형)
         * @param pYyyymmdd : 가감의 기준이 되는 날짜
         * @param pDelimiter : pYyyymmdd 값에 사용된 구분자를 설정 (없으면 "" 입력)
         * @returns yyyymmdd 또는 함수 입력시 지정된 구분자를 가지는 yyyy?mm?dd 값
         */
        addDate2String : function(pInterval, pAddVal, pYyyymmdd, pDelimiter) {
            var cDate;
            var yyyy, mm, dd;
            var cYear, cMonth, cDay;
            var rtnValue;
            
            if (pDelimiter != "") {
                pYyyymmdd = pYyyymmdd.replace(eval("/\\" + pDelimiter + "/g"), "");
            }
            yyyy = pYyyymmdd.substr(0, 4);
            mm = pYyyymmdd.substr(4, 2);
            dd = pYyyymmdd.substr(6, 2);
            if (pInterval == "yyyy") {
                yyyy = (yyyy * 1) + (pAddVal * 1);
            } else if (pInterval == "m") {
                mm = (mm * 1) + (pAddVal * 1);
            } else if (pInterval == "d") {
                dd = (dd * 1) + (pAddVal * 1);
            }
            cDate = new Date(yyyy, mm - 1, dd); // 12월, 31일을 초과하는 입력값에 대해 자동으로 계산된 날짜가 만들어짐.
            cYear = cDate.getFullYear();
            cMonth = cDate.getMonth() + 1;
            cDay = cDate.getDate();
            cMonth = cMonth < 10 ? "0" + cMonth : cMonth;
            cDay = cDay < 10 ? "0" + cDay : cDay;
            
            if (pDelimiter != "") {
                rtnValue = cYear.toString() + pDelimiter.toString() + cMonth.toString() + pDelimiter.toString() + cDay.toString();
            } else {
                rtnValue = cYear.toString() + cMonth.toString() + cDay.toString();
            }
            
            return rtnValue;
        },
        
        /**
         * GET 월의 마지막 날짜
         * @param pYyyymm 년월
         * @returns
         */
        lastDay2String : function(pYyyymm) {
            var yyyy, mm, cDate, lastDay;
            
            yyyy = pYyyymm.substr(0, 4);
            mm = pYyyymm.substr(4, 2);
            lastDay = new Date((new Date(yyyy, mm, 1)) - 1).getDate();
            
            return lastDay;
        },
        
        /**
         * 날짜 차이를 반환
         * 
         * @param pStartYyyymmdd 시작일자
         * @param pEndYyyymmdd 종료일자
         * @param pDelimiter 구분자
         * @returns
         */
        between2Day : function(pStartYyyymmdd, pEndYyyymmdd, pDelimiter){
            var yyyyS, mmS, ddS, yyyyE, mmE, ddE;
            if (pDelimiter != "") {
            	pStartYyyymmdd = pStartYyyymmdd.replace(eval("/\\" + pDelimiter + "/g"), "");
            	pEndYyyymmdd = pEndYyyymmdd.replace(eval("/\\" + pDelimiter + "/g"), "");
            }
            yyyyS = pStartYyyymmdd.substr(0, 4);
            mmS = pStartYyyymmdd.substr(4, 2);
            ddS = pStartYyyymmdd.substr(6, 2);
            
            yyyyE = pEndYyyymmdd.substr(0, 4);
            mmE = pEndYyyymmdd.substr(4, 2);
            ddE = pEndYyyymmdd.substr(6, 2);        
            
            var startDay = new Date(parseInt(yyyyS),parseInt(mmS)-1,parseInt(ddS));       
            var endDay = new Date(parseInt(yyyyE),parseInt(mmE)-1,parseInt(ddE));
            
            var diffTime = endDay.getTime() - startDay.getTime();
            
            return Math.floor(diffTime / (1000 * 60 * 60 * 24));     
        }, // kmi 날짜 차이 
        
        /**
         * 월의 차이를 반환
         * 
         * @param pStartYyyymmdd 시작일자
         * @param pEndYyyymmdd 종료일자
         * @param pDelimiter 구분자
         * @returns
         */
        between2Month : function(pStartYyyymmdd, pEndYyyymmdd, pDelimiter){
            var yyyyS, mmS, ddS, yyyyE, mmE, ddE;
            if (pDelimiter != "") {
            	pStartYyyymmdd = pStartYyyymmdd.replace(eval("/\\" + pDelimiter + "/g"), "");
            	pEndYyyymmdd = pEndYyyymmdd.replace(eval("/\\" + pDelimiter + "/g"), "");
            }
            yyyyS = pStartYyyymmdd.substr(0, 4);
            mmS = pStartYyyymmdd.substr(4, 2);
            ddS = pStartYyyymmdd.substr(6, 2);
            
            yyyyE = pEndYyyymmdd.substr(0, 4);
            mmE = pEndYyyymmdd.substr(4, 2);
            ddE = pEndYyyymmdd.substr(6, 2); 

            var startDay = new Date(parseInt(yyyyS),parseInt(mmS)-1,parseInt(ddS));       
            var endDay = new Date(parseInt(yyyyE),parseInt(mmE)-1,parseInt(ddE));

            var diffTime = endDay.getTime() - startDay.getTime();

            return diffTime / (1000 * 60 * 60 * 24 * 30);
        },
        
        /**
         * 특정 월에 대해 지정한 값만큼 가감(+-)한 월를 반환<br>
         * 예시)<br>
         * 2008-01-01 에 3 일 더하기 ==> addDate("d", 3, "2008-08-01", "-");<br>
         * 20080301 에 8 개월 더하기 ==> addDate("m", 8, "20080301", "");<br>
         * 
         * @param pInterval : "yyyy" 는 연도 가감, "m" 은 월 가감, "d" 는 일 가감
         * @param pAddVal : 가감 하고자 하는 값 (정수형)
         * @param pYyyymmdd : 가감의 기준이 되는 날짜
         * @param pDelimiter : pYyyymmdd 값에 사용된 구분자를 설정 (없으면 "" 입력)
         * @returns yyyymmdd 또는 함수 입력시 지정된 구분자를 가지는 yyyy?mm?dd 값
         */
        addMonth2String : function(pdate, num){
        	var add_m;
        	var lastDay; // 마지막 날(30,31..)
        	var pyear, pmonth, pday;

        	pdate = this.makeDateFormat(pdate); // javascript 날짜형변수로 변환
        	if (pdate == "") return "";

        	pyear = pdate.getYear();
        	pmonth= pdate.getMonth() + 1;
        	pday = pdate.getDate();

        	add_m = new Date(pyear, pmonth + num, 1); // 더해진 달의 첫날로 셋팅

        	lastDay = new Date(pyear, pmonth, 0).getDate(); // 현재월의 마지막 날짜를 가져온다.
        	if (lastDay == pday) { // 현재월의 마지막 일자라면 더해진 월도 마지막 일자로 
        	    pday = new Date(add_m.getYear(), add_m.getMonth(), 0).getDate();
        	}

        	add_m = new Date(add_m.getYear(), add_m.getMonth()-1, pday);

        	return add_m;
        },
        
        /**
         * 두날짜 비교(앞 날짜 보다 작은 경우 false)
         * @param pStartYyyymmdd 시작일자
         * @param pEndYyyymmdd 종료일자
         * @param pDelimiter 구분자
         * @returns
         */
        checkDateBefore : function (pStartYyyymmdd, pEndYyyymmdd, pDelimiter){
        	var diffDay = dateHelper.getDiffDay(pStartYyyymmdd, pEndYyyymmdd, pDelimiter);
        	if (diffDay >= 0) {
        		return true;
        	} else {
        		return false;
        	}
        },
        
        /**
         * 국가별 날짜 타입과 적합한 일자를 생성하는 함수
         * 
         * @param y = 년도
         * @param y = 월
         * @param y = 일
         * @param seperator = 구분문자
         * @param nation = 국가코드
         */
        chageDate2Nation : function(y, m, d, seperator, nation) {
        	var date = "";
        	
        	if(nation == "US") {
        		if(!oUtil.isNull(y) && !oUtil.isNull(m) && !oUtil.isNull(d)) {
        			date = (m < 10 ? ('0' + m) : m) + seperator + (d < 10 ? ('0' + d) : d) + seperator + y;
        		} else if(!oUtil.isNull(y) && !oUtil.isNull(m)) {
        			date = (m < 10 ? ('0' + m) : m) + seperator + y;
        		} else {
        			date = y;
        		}
            } else {
            	if(!oUtil.isNull(y) && !oUtil.isNull(m) && !oUtil.isNull(d)) {
            		date = y+seperator+(m<10?('0'+m):m)+seperator+(d<10?('0'+d):d);
            	} else if(!oUtil.isNull(y) && !oUtil.isNull(m)) {
        			date = y + seperator + (m < 10 ? ('0' + m) : m);
        		} else {
        			date = y;
        		}
            }
        	
        	return date;
        }
        
    }
};
