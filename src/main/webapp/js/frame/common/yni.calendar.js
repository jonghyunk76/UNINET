var calendarMethod =  {
    id : "",
    init : {
        
        config : {},
        
        getConfig : function() {
        	if(dataset.isArrayObject(calendar.id + ".init")) {
        		return dataset.arrayFilter(calendar.id +".init");
        	} else {
        		return this.config;
        	}
        },
        
        /**
	     * 달력의 타입을 설정한다.
	     * 
	     * @param string = 달력타입(date, month, time, datetime)
	     */
	    setDateType : function(string) {
	        this.getConfig().dateType = string;
	    },
	    
		/**
	     * edit 활성화 여부 설정(default=false)
	     * 
	     * @param boolean = edit가능:true, edite불가능:false 
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
	     * 만약, 설정값이 none라면 날짜가 빈상태로 표시됨(단, 월달력은 적용하지 않음) 
	     * 
	     * @param string = 날짜
	     */
	    setInitDate : function(string) {
	        this.getConfig().initDate = string;
	        this.getConfig().value = string;
	    },
	    
	    /**
	     * 달력에서 상/하위 스피너 버튼을 클릭할 경우 최초 변경될 위치 설정
	     * (월달력에 적용)
	     * 
	     * @param number = 배열의 위치(0~2)
	     */
	    setHighlight : function(number) {
	        this.getConfig().highlight = number;
	    },
	    
	    /**
         * 날짜 구분을 분석하여 기본 함수 분석을 재정의하는 함수
         * 
         * @param formId = form ID
         * @param tagId = calendar ID
         */
        setParser : function(formId, tagId) {
	        this.getConfig().parser = function(s) {
	        	if (oUtil.isNull(s)) return new Date();
	        	
	        	var ss = (s.split(SEPERATOR_OF_DATE));
	        	var nation = NATION_DATE_VIEW;
	        	
	        	if(nation == "US") {
		            m = parseInt(ss[0], 10);
		            d = parseInt(ss[1], 10);
		            y = parseInt(ss[2], 10);
	        	} else if(nation == "MX") {
		            d = parseInt(ss[0], 10);
		            m = parseInt(ss[1], 10);
		            y = parseInt(ss[2], 10);
	        	} else {
	        		y = parseInt(ss[0], 10);
		            m = parseInt(ss[1], 10);
		            d = parseInt(ss[2], 10);
	        	}
	        	
	        	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
	        		if(NATION_DATE_DB == "US") {
	            		return new Date(m-1,d,y);
	            	} else if(NATION_DATE_DB == "MX") {
	            		return new Date(d,m-1,y);
	            	} else {
	            		return new Date(y,m-1,d);
	            	}
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
	            
	            var format = calendar.util.chageDate2Nation(y, m, d, seperator, conf.nation);
	            
	            return format; 
	        }
        },
        
        /**
         * 읽기전용 여부 설정
         * 
         * @param boolean = true:읽기, false:쓰기
         */
        setReadonly : function(boolean) {
	        this.getConfig().readonly = boolean;
	    }
    },
    event : {
    	
        config : {},
        
        getConfig : function() {
        	if(dataset.isArrayObject(calendar.id + ".event")) {
        		return dataset.arrayFilter(calendar.id +".event");
        	} else {
        		return this.config;
        	}
        },
        
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
    handle : {
    	
    	/** 
    	 * 날짜 정보 획득<br>
    	 * 지정될 국가의 날자를 DB에 맞게 변환시킨 후 리턴
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param sep = 변환할 구분자
         * @return db에 저장된 날짜타입이 반영된 날짜
         */
        getDate : function(formId, tagId, changeSep) {
            var obj = form.getObject(formId, tagId);
            var conf = calendar.getInitConfig(formId, tagId);
        	var seperator = conf.seperator;
        	var nation = conf.nation;
        	
        	var date;
        	if(this.isDateBox(formId, tagId)) {
        	    date = obj.datebox('getValue');
        	} else {
        		date = obj.val();
        	}
        	
        	if(oUtil.isNull(date)) return;
        	else { // 사용자가 지정한 국가와 실제 날짜의 국가가 맞는지 체크한다.
        		nation = calendar.util.getLocale(date);
        	}
        	
        	try {
        		date.getFullYear();
        	} catch(e) {
        		date = calendar.util.parseDate(date, seperator, nation);
        		
        		if(oUtil.isNull(date)) return null;
        	}
        	
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            
            if(oUtil.isNull(changeSep)) changeSep = "";
            if(conf.dateType == "month") d = "";
            
            return calendar.util.chageDate2Nation(y, m, d, changeSep, NATION_DATE_DB);
        },
        
        /** 
         * 날짜 입력<br>
         * 사용자가 지정한 날짜 타입이 적용된 날짜 리턴
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param date = 넣어줄 날짜(20020101 형태)
         * @param flag = 날짜 국가별로 변환여부
         */
        setDate : function(formId, tagId, date, flag) {
        	var obj = form.getObject(formId, tagId);
        	var conf = calendar.getInitConfig(formId, tagId);
        	var nation = conf.nation;
        	var seperator = conf.seperator;
        	
        	if(date == "none" || oUtil.isNull(date)) {
        		obj.datebox("setValue", "");
        		return;
        	}
        	
        	if(oUtil.isNull(flag)) flag = true;
        	
        	try {
        		if(flag) {
        			date.getFullYear();
                } else {
                	date = calendar.util.parseDate(date, seperator, NATION_DATE_DB);
                }
        	} catch(e) {
        		date = calendar.util.parseDate(date, seperator, nation);
        	}
        	
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            
            if(this.isDateBox(formId, tagId)) {
            	obj.datebox('setValue', calendar.util.chageDate2Nation(y, m, d, seperator, NATION_DATE_VIEW));
            } else if(this.isDatetimespinner(formId, tagId)) {
            	form.handle.setValue(formId, tagId, calendar.util.chageDate2Nation(y, m, null, seperator, NATION_DATE_VIEW));
            	form.handle.setValue(formId, replaceAll(tagId, "_CAL", ""), calendar.util.chageDate2Nation(y, m, null, "", NATION_DATE_DB));
            }
        },
        
        disabled : function(formId, tagId, flag) {
        	var obj = form.getObject(formId, tagId);
        	
        	if(oUtil.isNull(flag)) flag = false;
        	
        	if(this.isDateBox(formId, tagId)) {
        		obj.datebox({
	        		disabled : flag
	        	});
        	} else if(this.isDatetimespinner(formId, tagId)) {
        		obj.datetimespinner({
	        		disabled : flag
	        	});
        	}
        },
        
        readonly : function(formId, tagId, flag) {
        	var obj = form.getObject(formId, tagId);
        	var conf = calendar.getInitConfig(formId, tagId);
        	
        	if(this.isDateBox(formId, tagId)) {
        		obj.datebox("readonly", flag);
                
            	return true;
            } else if(this.isDatetimespinner(formId, tagId)) {
            	obj.datetimespinner("readonly", flag);
            	
            	return true;
            } else {
                return false;
            }
        },
        
        /**
         * 객체가 달력인지 체크
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         */
        isDateBox : function(formId, tagId) {
            var obj = form.getObject(formId, tagId);
            
            try {
            	if(!oUtil.isNull(obj.datebox("options"))) {
    	            return true;
    	        } else {
    	            return false;
    	        }
            } catch(e) {
            	return false;
            }
        },
        
        /**
         * 객체가 월달력인지 체크
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         */
        isDatetimespinner : function(formId, tagId) {
            var obj = form.getObject(formId, tagId);
            
            try {
            	if(!oUtil.isNull(obj.datetimespinner("options"))) {
            		return true;
    	        } else {
    	            return false;
    	        }
            } catch(e) {
            	return false;
            }
        },
        
        /**
         * 날짜를 쿼리에 맞게 변경한 후 지정된 엘리먼트에 저장한다.
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param tgtId = copy할 element ID
         */
        setDateSync : function(formId, tagId, tgtId) {
        	var formData = calendar.handle.getDate(formId, tagId); // 화면에 입력된 날짜를 DB에 적용할 타입으로 변경함
        	
            form.handle.setValue(formId, tgtId, formData); // 변경한 날짜를 복사함
        }
    },
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 죄송합니다.... 유틸은 정비중이므로 사용하지 마세요.~~~~~~~~~~~~~~~~~~~~~~~~
    ////////////////////////////////////////////////////////////////////////////////////////////////
    util : {   
    	
    	/**
    	 * 문자열 타입의 날짜를 Date타입으로 변환한 후 리턴
    	 * 
    	 * @param date = 날짜:string
         * @param seperator = 날짜의 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @return date 타입의 Object
    	 */
    	parseDate : function(date, seperator, nation) {
    		if(oUtil.isNull(seperator)) seperator = SEPERATOR_OF_DATE;
    		if(oUtil.isNull(nation)) nation = NATION_DATE_VIEW;
        	if(oUtil.isNull(date)) return;
        	
    		var value = replaceAll(date, seperator, "");
    		var dateAry = null;
    		
    		try {
    			value = formatDate(value, seperator, nation);
    			dateAry = value.split(seperator);
    		} catch(e) {
    			return null;
    		}
    		
    		var y = "";
            var m = "";
            var d = "";
            
    		if(nation == "US") {
	            m = parseInt(dateAry[0], 10);
	            d = parseInt(dateAry[1], 10);
	            y = parseInt(dateAry[2], 10);
        	} else if(nation == "MX") {
	            d = parseInt(dateAry[0], 10);
	            m = parseInt(dateAry[1], 10);
	            y = parseInt(dateAry[2], 10);
        	} else {
        		y = parseInt(dateAry[0], 10);
	            m = parseInt(dateAry[1], 10);
	            d = parseInt(dateAry[2], 10);
        	}
    		
    		//alert(date + " > " + value + " : " + y + "-" + m + "-" + d + " > " + nation);
    		
    		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
            	if(nation == "US") {
            		return new Date(m-1,d,y);
            	} else if(nation == "MX") {
            		return new Date(d,m-1,y);
            	} else {
            		return new Date(y,m-1,d);
            	}
            } else {
                return new Date();
            }
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
         * @param seperator : pYyyymm 값에 사용된 구분자를 설정 (없으면 "" 입력)
         * @returns @returns UI에 표현되는 날짜타입의 국가에 맞는 값 
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
         * 현재 월를 문자열 형태로 반환
         */
        toMonth2Date : function() {
            var today = new Date();
            var yyyy, mm;
            
            yyyy = today.getFullYear();
            mm = today.getMonth() + 1;
            
            return mm;
        },
        
        /**
    	 * 현재 년,월,일를 문자열 형태로 반환
    	 * 
    	 * @param seperator : pYyyymmdd 값에 사용된 구분자를 설정 (없으면 "" 입력)
    	 * @returns UI에 표현되는 날짜타입의 국가에 맞는 값 
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
         * @param interval : "yyyy" 는 연도 가감, "m" 은 월 가감, "d" 는 일 가감
         * @param addVal : 가감 하고자 하는 값 (정수형)
         * @param pdate : 가감의 기준이 되는 날짜
         * @param seperator : pYyyymmdd 값에 사용된 구분자를 설정 (없으면 "" 입력)
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns UI에 표현되는 날짜타입의 국가에 맞는 값 
         */
        addDate2String : function(interval, addVal, pdate, seperator, nation) {
        	var date = this.parseDate(pdate, seperator, nation);
            
        	if(oUtil.isNull(seperator)) seperator = "";
        	
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            var dd = date.getDate();
            
            if(interval == "yyyy") {
            	date.setYear(yyyy + addVal);
            } else if(interval == "m") {
            	// FTA 솔루션인에는 기존 적용된 내용이 있어 그대로 적용함
            	if(oUtil.isNull(TOP_SYS_ID) || TOP_SYS_ID == "IF" || TOP_SYS_ID == "FM") {
            		date.setMonth(mm + addVal);
            	} else { // FTA 업무가 아닌 경우(2017.04.04)
            		date.setMonth(mm + (addVal - 1));
            	}
            	
            } else if(interval == "d") {
            	date.setDate(date.getDate() + addVal);
            }
            
            var new_yyyy = date.getFullYear();
            var new_mm = date.getMonth() + 1;
            var new_dd = date.getDate();
            
            return this.chageDate2Nation(new_yyyy, new_mm , new_dd, seperator, NATION_DATE_VIEW);
        },
        
        /**
         * 입력된 날짜의 년도,월,일 구하기
         * 
         * @param date = date object
         * @param seperator = 구분자
         * @returns UI에 표현되는 날짜타입의 국가에 맞는 값
         */
        getString2Date : function(date, seperator) {
        	if(oUtil.isNull(seperator)) seperator = "";
        	
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            var dd = date.getDate();
            
            return this.chageDate2Nation(yyyy, mm , dd, seperator, NATION_DATE_VIEW);
        },
        
        /**
         * 입력된 날짜의 년도,월,일 구하기
         * @param date = 년월일 날짜
         * @param seperator = 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns UI에 표현되는 날짜타입의 국가에 맞는 값
         */
        getDate2String : function(string, seperator, nation) {
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var date = this.parseDate(string, seperator, nation);
            
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            var dd = date.getDate();
            
            return this.chageDate2Nation(yyyy, mm , dd, seperator, NATION_DATE_VIEW);
        },
        
        /**
         * 입력된 날짜의 년도,월,일 구하기
         * @param date = 년월일 날짜
         * @param seperator = 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns UI에 표현되는 날짜타입의 국가에 맞는 값
         */
        getSepInDate2String : function(string, seperator, nation, nation2) {
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var date = this.parseDate(string, seperator, nation);
            
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            var dd = date.getDate();
            
            return this.chageDate2Nation(yyyy, mm , dd, seperator, NATION_DATE_DB);
        },
        
        /**
         * 입력된 날짜의 년도,월 구하기
         * @param date = 년월일 날짜
         * @param seperator = 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns UI에 표현되는 날짜타입의 국가에 맞는 값 
         */
        getMonth2String : function(string, seperator, nation) {
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var date = this.parseDate(string, seperator, nation);
        	
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            
            return this.chageDate2Nation(yyyy, mm , null, seperator, NATION_DATE_VIEW);
        },
        
        /**
         * 입력된 날짜의 년도,월의 첫일자를 리턴
         * @param date = 년월일 날짜
         * @param seperator = 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns UI에 표현되는 날짜타입의 국가에 맞는 값 
         */
        getFirstDay2String : function(string, seperator, nation) {
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var date = this.parseDate(string, seperator, nation);
        	
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            
            return this.chageDate2Nation(yyyy, mm , 1, seperator, NATION_DATE_VIEW);
        },
        
        /**
         * 입력된 날짜의 년도,월의 첫일자를 리턴
         * @param date = 년월일 날짜
         * @param seperator = 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns UI에 표현되는 날짜타입의 국가에 맞는 값 
         */
        getLastDay2String : function(string, seperator, nation) {
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var date = this.parseDate(string, seperator, nation);
        	
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            
            return this.chageDate2Nation(yyyy, mm , this.lastDay2String(string, seperator, nation), seperator, NATION_DATE_VIEW);
        },
        
        /**
         * GET 월의 마지막 날짜
         * @param string = 년월
         * @param seperator = 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns 
         */
        lastDay2String : function(string, seperator, nation) {
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var date = this.parseDate(string, seperator, nation);
            
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            var day = 1;
            
            var lastDay = new Date((new Date(yyyy, mm, day)) - 1).getDate();
            
            return lastDay;
        },
        
        /**
         * 날짜 차이를 반환
         * 
         * @param sdate 시작일자
         * @param edate 종료일자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns
         */
        between2Day : function(sdate, edate, seperator, nation){
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
            var startDay = this.parseDate(sdate, seperator, nation);
            var endDay = this.parseDate(edate, seperator, nation);
            
            var diffTime = endDay.getTime() - startDay.getTime();
            
            return Math.floor(diffTime / (1000 * 60 * 60 * 24));     
        },
        
        /**
         * 월의 차이를 반환
         * 
         * @param sdate 시작일자
         * @param edate 종료일자
         * @param seperator 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns
         */
        between2Month : function(sdate, edate, seperator, nation){
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var startDay = this.parseDate(sdate, seperator, nation);
            var endDay = this.parseDate(edate, seperator, nation);
            
            var diffTime = endDay.getTime() - startDay.getTime();
            var diffMon = diffTime / (1000 * 60 * 60 * 24 * 30);
            var diffDay = diffTime / (1000 * 60 * 60 * 24);
            
            if(diffMon > 12 && diffDay <= 365) {
            	return 12;
            } else {
            	return diffMon;
            }
        },
        
        /**
         * 월의 차이를 반환
         * 
         * @param sdate 시작일자(yyyymm)
         * @param edate 종료일자(yyyymm)
         * @param seperator 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns
         */
        betweenMonth2Number : function(sdate, edate, seperator, nation){
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var startDay = this.parseDate(sdate, seperator, nation);
            var endDay = this.parseDate(edate, seperator, nation);
            
            var syear = startDay.getYear();
        	var smonth= startDay.getMonth() + 1;
        	var eyear = endDay.getYear();
        	var emonth= endDay.getMonth() + 1;
            
        	return (eyear - syear)* 12 + (emonth - smonth);
        },
        
        /**
         * 특정 월에 대해 지정한 값만큼 가감(+-)한 월를 반환<br>
         * 
         * @param pdate : 입력일자(data type)
         * @param num : 가감 하고자 하는 값 (정수형)
         * @param seperator 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns date타입의 Object
         */
        addMonth2String : function(pdate, num, seperator, nation){
        	var add_m;
        	var lastDay; // 마지막 날(30,31..)
        	var pyear, pmonth, pday;
        	
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	pdate = this.parseDate(pdate, seperator, nation); // javascript 날짜형변수로 변환
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
         * @param sdate 시작일자
         * @param edate 종료일자
         * @param seperator 구분자
         * @param nation = 날짜 파라메터의 가지고 있는 날짜의 국가
         * @returns
         */
        compear2Date : function (sdate, edate, seperator, nation){
        	if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
        	var diffDay = this.between2Day(sdate, edate, seperator, nation);
        	
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
            } else if(nation == "MX") {
        		if(!oUtil.isNull(y) && !oUtil.isNull(m) && !oUtil.isNull(d)) {
        			date = (d < 10 ? ('0' + d) : d) + seperator + (m < 10 ? ('0' + m) : m) + seperator + y;
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
        },
        
        /**
         * 일자의 형식을 통해 적용해야 할 국가를 리턴
         * @param date
         * @return locale(KR or Locale)
         */
        getLocale : function(date) {
        	if(oUtil.isNull(date)) return "";
        	
        	var locale = NATION_DATE_VIEW;
        	
        	if(date.length == 8 && NATION_DATE_DB == "KR") {
    			if(parseInt(date.substring(4,8)) <= 1231) locale = NATION_DATE_DB;
    		}

        	return locale;
        },
        
        /**
         * 지정된 주(요일)의 시작일를 리턴
         * 
         * @param date
         * @return locale(KR or Locale)
         */
        getWeekStartDate : function(date, seperator, nation){
            var nowDayOfWeek = date.getDay();
            var nowDay = date.getDate();
            var nowMonth = date.getMonth();
            var nowYear = date.getYear();
            
            nowYear += (nowYear < 2000) ? 1900 : 0;
            
        	var wdate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);
        	var mymonth = wdate.getMonth()+1;
            var myweekday = wdate.getDate();
            
            if(oUtil.isNull(seperator)) seperator = "";
        	if(oUtil.isNull(nation)) nation = NATION_DATE_DB;
        	
            return this.chageDate2Nation(nowYear, mymonth , myweekday, seperator, NATION_DATE_VIEW);
        },
        
    }
};
