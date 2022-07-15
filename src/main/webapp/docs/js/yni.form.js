var formMethod = function() {
    init = {

	    /**
	     * form URL 설정
	     * 
	     * @param string = action url
	     */
	    setUrl : function(string) {
	    	this.getConfig().url = string;
	    },
	    
	    /**
	     * submit 종료 후 호출될 함수 설정.
	     * 
	     * @param string = 함수명
	     */
	    setCallBackFunction : function(string) {
	        this.getConfig().callFunc = string;
	    },
	    
	    /**
	     * submit 성공 시 표시될 메시지
	     * 
	     * @param string = 메시지
	     */
	    setSucceseMessage : function(string) {
	        this.getConfig().succeseMessage = string;
	    },
	    
	    /**
	     * submit 실패 시 표시될 메시지
	     * @param string = 메시지
	     */
	    setFailMessage : function(string) {
	        this.getConfig().failMessage = string;
	    },
	    
	    /**
	     * callback 함수에 json data를 넘겨줌(default=false)
	     * 
	     * @param boolean = true:넘겨줌, false:안넘겨줌
	     */
	    setJsonReturnFlag : function(boolean) {
	        if (oUtil.isNull(boolean)) boolean = false;
	        
	        if (oUtil.isBoolean(boolean)) {
	            this.getConfig().jsonReturnFlag = boolean;
	        } else {
	            $.messager.alert("ERROR", "setJsonReturnFlag() parameter input error.(true or false)");
	        }
	    },
	    
	    /**
	     * validation check여부(default=true)
	     * 
	     * @param boolean = true:체크함, false:미체크
	     */
	    setValidationFlag : function(boolean) {
	        if (oUtil.isNull(boolean)) boolean = true;
	        if (oUtil.isBoolean(boolean)) {
	            this.getConfig().validationFlag = boolean;
	        } else {
	            $.messager.alert("ERROR", "setValidationFlag() parameter input error.(true or false)");
	        }
	    },
	    
	    /**
	     * progress bar 사용 여부(default=true)
	     * 
	     * @param boolean = true:사용, false: 미사용
	     */
	    setProgressFlag : function(boolean) {
	        if (oUtil.isNull(boolean)) boolean = true;
	        if (oUtil.isBoolean(boolean)) {
	            this.getConfig().progressFlag = boolean;
	        } else {
	            $.messager.alert("ERROR", "setProgressFlag() parameter input error.(true or false)");
	        }
	    },
	    
	    /**
	     * 디버그 모드 적용 여부(default=true)
	     * 
	     * @param boolean = true:사용, false: 미사용
	     */
	    setDebugFlag : function(boolean) {
	        if (oUtil.isNull(boolean)) boolean = false;
	        if (oUtil.isBoolean(boolean)) {
	            this.getConfig().debugFlag = boolean;
	        } else {
	            $.messager.alert("ERROR", "setDebugFlag() parameter input error.(true or false)");
	        }
	    },
	    
	    /**
	     * 응답되는 데이터 타입
	     * 
	     * @param string = json, list, map, string, none-default
	     */
	    setReturnType : function(string) {
	        this.getConfig().returnType = string;
	    },
	    
	    /**
	     * 응답 데이터를 자동으로 form 안에 input할지 여부 설정(default=false)
	     * 
	     * @param boolean = true:입력, false:미입력
	     */
	    setLoadData : function(boolean) {
	        if (oUtil.isBoolean(boolean)) {
	            this.getConfig().loadData = boolean;
	        } else {
	            $.messager.alert("ERROR", "setProgressFlag() parameter input error.(true or false)");
	        }
	    }
    },
    
    event = {
    	
	    /**
	     * from submit 실행
	     * 
	     * @param formId = form id
	     */
	    setOnSubmit : function(formId) {
	        this.getConfig().onSubmit = function() {
	        	var fid = (typeof formId == "object") ? formId.attr("id") : formId;
	        	var conf = form.getInitConfig(fid);
	            
	            var validFlag = conf.validationFlag;
	            var progressFlag = conf.progressFlag;
	            
	            if (validFlag) {
	                var isValid = $(this).form('validate');
	                if (!isValid) {
	                    return false;
	                }
	            }
	            if (progressFlag) {
	                $.messager.progress({
	                    text: "Data loading..."
	                });
	            }
	        };
	    },
	    
	    /**
	     * from submit이 성공했을 경우 호출되는 이벤트<br>
	     * 1.form.init.setCallBackFunction함수의 파라메터로 지정한 function명이 있으면 처리완료 시 지정된 함수를 호출함<br>
	     * (지정된 함수의 구현위치는 Program ID.event.함수명)<br>
	     * 2.form.init.setLoadData를 true로 설정하면 "loadData"이면 form에 포함된 element로 자동 값이 입력됨.
	     * 
	     * @param formId = form id
	     */
	    setSuccess : function(formId) {
	        this.getConfig().success = function(datas) {
	        	var fid = (typeof formId == "object") ? formId.attr("id") : formId;
	        	var conf = form.getInitConfig(fid);
	            
	            var progressFlag = conf.progressFlag;
	            var succesemsg = conf.succeseMessage;
	            var failmsg = conf.failMessage;
	            var callFunc = conf.callFunc;
	            var loadData = conf.loadData;
	            var jsonReFlag = conf.jsonReturnFlag;
	            var debugFlag = conf.debugFlag;
	            
	            try {
	                var data;
	                
	                data = $.parseJSON(datas);
	                
	                // 이스케이프된 문자를 복원시킨다.
	                if(!oUtil.isNull(data.rows)) {
	                    var datas = data.rows;
	                    
	                    for(var i in datas) {
	                        $.each(datas[i], function(key, value) {
	                            if(typeof value == "string") {
	                                data.rows[i][key] = unescape(value);
	                            }
	                        });
	                    }
	                }
	                
	                if (progressFlag) {
	                    $.messager.progress('close');
	                }
	                
	                if (!oUtil.isNull(data.message)) {
	                    alert(data.message, "window");
	                } else {
	                    if(!oUtil.isNull(data.success)) {
	                        if (data.success) {
	                            if (!oUtil.isNull(succesemsg)) {
	                                $.messager.alert("INFO", succesemsg);
	                            }
	                        } else {
	                            if (!oUtil.isNull(failmsg)) {
	                                $.messager.alert("ERROR", failmsg);
	                            }
	                            return false;
	                        }
	                    }
	                }
	                
	                if (loadData) {
	                    form.handle.loadData(formId, data);
	                } 
	                
	                if (!oUtil.isNull(callFunc)) {
	                	var pid = form.handle.getProgramID(fid);
	                	var funcPath = eval("window." + pid + ".event");
	                	
	                	if (!oUtil.isNull(funcPath)) {
	                		if (typeof(funcPath[callFunc]) == "function") {
	                			if (jsonReFlag) {
	                            	funcPath[callFunc](data);
	                            } else {
	                            	funcPath[callFunc]();
	                            }
	                		}
                        }
	                }
	            } catch (e) {
	                if (progressFlag) {
	                    $.messager.progress('close');
	                }
	                
	                if(debugFlag) alert(e);
	                
	                return false;
	            }
	        };
	    }
    },
    
    handle = {
	   
    	/**
	     * form안에 엘리먼트의 값을 지정한다.
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param val = id에 입력할 값
	     */
	    setValue : function(formId, eId, val) {
	        var frm = form.getObject(formId, eId);
	        
	        frm.val(val);  
	    },
	    
	    /**
	     * form안에 엘리먼트의 값을 구한다.
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @return form.eId.value
	     */
	    getValue : function(formId, eId) {
	        var frm = form.getObject(formId, eId);
	        
	        return frm.val();
	    },
	    
	    /**
	     * form안에 radio 타입의 값을 구한다.
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 엘리먼트 id
	     * @return form.eId.value
	     */
	    getRadioValue : function(formId, eId) {
	        var obj = form.getObject(formId, eId);
	        
	        for (var i = 0; i < obj.length; i++) {
	        	if($('input[id='+eId+']:checked', '#'+formId+'')) {
	                return $('input[id='+eId+']:checked', '#'+formId+'').val();
	            }
	        }
	    },
	    
	    /**
	     * form안에 checkbox타입에서 체크된 값을 구하기
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 엘리먼트 id
	     * @return form.eId.value
	     */
	    getCheckboxValue : function(formId, eId) {
	        var obj = form.getObject(formId, eId);
	        
	        for (var i = 0; i < obj.length; i++) {
	        	if($('input[id='+eId+']:checked', '#'+formId+'')) {
	                return $('input[id='+eId+']:checked', '#'+formId+'').val();
	            }
	        }
	    },
	    
	    /**
	     * form에 지정된 validation 체크결과를 리턴한다.
	     * 
	     * @param formId = form id
	     * @return 유효성 체크결과(true:일치, false:불일치)
	     */
	    isValidate : function(formId) {
	        return $('#' + formId).form('validate');
	    },
	    
	    /**
	     * 데이터를 json 형태로 받아와서 html element에 mapping
	     * 
	     * @param formId = form id
	     * @param jsonData = json data
	     * @param dataFlag = 만들어진 json data 사용 여부(사용 : true, 미사용 : false) - > 미사용일 경우에는 url을 통해 데이터 조회 후 mapping
	     * @param url = data 조회 url
	     */
	    loadData : function(formId, jsonData, dataFlag, url) {
	        var frm = form.getObject(formId);
	        
	        if (dataFlag == null || dataFlag == "undefined") {
	            if (url != null && url != "") {
	                dataFlag = false;
	            } else {
	                dataFlag = true;
	            }
	        }
	        
	        if (dataFlag) {
	            frm.form('load', jsonData);
	        } else {
	            frm.form('load', url);
	        }
	    },
	    
	    /**
	     * form data를 clear
	     * 
	     * @param formId = form id
	     */
	    clear : function(formId) {
	        var frm =  form.getObject(formId);
	        
	        frm.form('clear');
	    },
	    
	    /**
	     * form명에서 Program ID 찾기
	     * 
	     * @param formId = form object 또는 ID
	     */
	    getProgramID : function(formId) {
	    	var fid = (typeof formId == "object") ? formId.attr("id") : formId;
	        var pro_id = fid.substr(0, PROGRAM_ID_NUMBER);
	        
	        return pro_id;
	    },
	    
	    /**
	     * 지정된 <form - element>의 쓰기 여부 설정
	     *  
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param flag = 쓰기 금지 여부(ture:읽기 전용, false:쓰기 가능)
	     */
	    readOnly : function(formId, eId, flag) {
	        var obj = form.getObject(formId, eId);
	        
	        obj.attr("readonly", flag);
	    },
	    
	    /**
	     * 지정된 <form - element>의 클래스 적용
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param cName = 적용할 클래스명
	     */
	    addClass : function(formId, eId, cName) {
	        var obj = form.getObject(formId, eId);
	        
	        obj.addClass(cName);
	    },
	    
	    /**
	     * 지정된 <form - element>의 클래스 적용 취소
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param cName = 적용할 클래스명
	     */
	    removeClass : function(formId, eId, cName) {
	        var obj = form.getObject(formId, eId);
	        
	        obj.removeClass(cName);
	    },
	    
	    /**
	     * 지정된 <form - element>의 체크여부 리턴
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @return 체크여부
	     */
	    isChecked : function(formId, eId) {
	        var obj = form.getObject(formId, eId);
	        
	        return obj.is(':checked');
	    },
	    
	    /**
	     * 지정된 <form - element>의 체크여부 설정
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param flag = 체크여부(true:체크함, false:체크안함)
	     */
	    setChecked : function(formId, eId, flag) {
	        var obj = form.getObject(formId, eId);
	        
	        return obj.prop('checked', flag);
	    },
	    
	    /**
	     * 지정된 <form - element>를 보일지 여부 설정
	     *  
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param flag = 보이기 여부(true:보임, false:숨김)
	     */
	    setVisible : function(formId, eId, flag) {
	        var obj = form.getObject(formId, eId);
	        
	        if(flag) obj.show();
	        else obj.hide();
	    },
	    
	    /**
	     * form의 입력란을 모두 닫는다.
	     * 
	     * @param name = form id:String
	     */
	    closeElements : function(formId) {
	        var fm = document.getElementById(formId);
	        var fmlen = fm.length;
	        
	        for(var i = 0 ; i < fmlen; i++) {
	            var noTag = fm.elements[i];
	            var id = noTag.id;
	            var type = noTag.type;
	            
	            if(type == "text") {
	                noTag.style.border = "1px solid #ffffff";
	                noTag.readOnly = true;
	            }
	        }
	    },
	    
	    /**
	     * form의 입력란을 모두 닫는다.
	     * 
	     * @param name = form id:String
	     * @return form element의 id와 값를 Json타입으로 리턴
	     */
	    getElementsParam : function(formId) {
	        var fm = document.getElementById(formId);
	        var fmlen = fm.length;
	        var jsonVal = {};
	        
	        for (var i = 0; i < fmlen; i++) {
	        	var noTag = fm.elements[i];
	            var key = noTag.id;
	            var type = noTag.type;
	            
	            var obj = form.getObject(formId, key);
	            
	            if (oUtil.isNull(obj) || oUtil.isNull(key)) {
	                continue;
	            } else { 
	            	var value = "";
	            	
	                if(type == "checkbox") { // radio button
	                	value = this.getCheckboxValue(formId, key);
	                	if(oUtil.isNull(value)) value = "";
	                } else if(type == "radio") { // radio button
	                	value = this.getRadioValue(formId, key);
	                	if(oUtil.isNull(value)) value = "";
	                } else {
	                	var exp1 = false;
		                var exp2 = false;
		                var value = "";
		                
		                try {
		                    obj.combobox("options");
		                } catch(e) {
		                	exp1 = true;
		                }
		                
		                try {
		                    obj.datebox("options");
		                } catch(e) {
		                	exp2 = true;
		                }
		                
		                // combobox
		                if(!exp1) {
		                	value = combo.handle.getValue(formId, key);
		                }
		                
		                // calendar
		                if(!exp2) {
		    	            value = calendar.handle.getDate(formId, key);
		                }
		                
		                if(oUtil.isNull(value)) value = obj.val();
	                }
	                
	                jsonVal[key] = value;
	            }
            }
	        
            return jsonVal;
	    },
	    
	    /**
	     * 폼안의 입력값을 초기화 시킴
	     * 
	     * @param formId = form id
	     */
	    reset : function(formId) {
	    	var obj = form.getObject(formId);
	    	
	    	obj.form("reset");
        }
	    
    },
    // div 객체에 대한 기능지원
    util = {
    	/**
         * 지정된 <div>의 쓰기 여부 설정 
         * 
         * @param divId = DIV ID
         * @param flag = 쓰기 금지 여부(ture:읽기 전용, false:쓰기 가능)
         */
        readOnly : function(divId, flag) {
       		$("#"+divId).attr("readonly", flag);
        },
        
        /**
         * 지정된 <div>의 클래스 적용 
         * 
         * @param divId = DIV ID
         * @param cName = 적용할 클래스명
         */
        addClass : function(divId, cName) {
    		if(flag) $("#"+divId).addClass(cName);
    		else $("#"+divId).removeClass(cName);
        },
        
        /**
         * 지정된 <div>의 클래스 적용 취소
         * 
         * @param divId = DIV ID
         * @param cName = 적용할 클래스명
         */
        removeClass : function(divId, cName) {
        	$("#"+divId).removeClass(cName);
        },
        
        /**
         * 지정된 <div>의 체크여부 리턴
         * 
         * @param divId = DIV ID
    	 * @return 체크여부
         */
        isChecked : function(divId) {
        	return $("#"+divId).is(':checked');
        },
        
        /**
         * 지정된 <div>의 체크여부 설정
         * 
         * @param divId = DIV ID
    	 * @return 체크여부
         */
        setChecked : function(divId, flag) {
        	return $("#"+divId).prop('checked', flag);
        },
        
        /**
         * 지정된 <div>를 보일지 여부 설정 
         * 
         * @param divId = DIV ID
         * @param flag = 보이기 여부(true:보임, false:숨김)
         */
        setVisible : function(divId, flag) {
    		if(flag) $("#"+divId).show();
    		else $("#"+divId).hide();
        }
        
    }
    
};