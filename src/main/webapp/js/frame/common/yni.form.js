var formMethod =  {
	id : null,
    init : {
		/**
	     * form 생성을 위한 속성값
	     */
	    config : {},
	    
	    getConfig : function() {
        	if(dataset.isArrayObject(form.id + ".init")) {
        		return dataset.arrayFilter(form.id +".init");
        	} else {
        		return this.config;
        	}
        },
        
	    /**
	     * form URL 설정
	     * 
	     * @param string = action url
	     */
	    setURL : function(string) {
	    	this.getConfig().url = string;
	    },
	    
	    /**
	     * submit 종료 후 호출될 함수 설정.
	     * 
	     * @param string = 함수명(program ID + "." + 함수명)
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
	     * @param string = json, list, map, string, xml, none-default
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
    
    event : {
    	
    	/**
	     * form 생성을 위한 속성값
	     */
	    config : {},
	    
	    getConfig : function() {
        	if(dataset.isArrayObject(form.id + ".event")) {
        		return dataset.arrayFilter(form.id +".event");
        	} else {
        		return this.config;
        	}
        },
        
        setOnChange : function(obj) {
        	this.getConfig().onChange = function(target) {
        		;
        	};
        },
        
        /**
         * form에 데이터 로드가 완료되었을 때 실행
         * 
         * @param obj = form object
         */
        setOnLoadSuccess : function(obj) {
        	this.getConfig().onLoadSuccess = function(data) {
        		;
        	};
        },
        
	    /**
	     * from submit 실행
	     * 
	     * @param obj = form object
	     */
	    setOnSubmit : function(obj) {
	        this.getConfig().onSubmit = function(param) {
	        	var fid = (typeof obj == "object") ? obj.attr("id") : obj;
	        	var conf = form.getInitConfig(fid);
	            
	    	    var fm = document.getElementById(fid);
    	        
    	        // 날짜인 경우 value를 변경함.
    	        for (var i = 0; i < fm.length; i++) {
    	        	var noTag = fm.elements[i];
    	            var key = noTag.id;
    	            var type = noTag.type;
    	            
    	            if (oUtil.isNull(key) || key == "headers" || key == "filename" || key == "sheetname") {
    	                continue;
    	            } else { 
    	            	var fobj = form.getObject(fid, key);
    	            	
    	            	if(form.handle.isDateBox(fid, key)) {
    	            		var cDate = calendar.handle.getDate(fid, key);
    	            		fobj.combo("setValue", cDate);
    	            	}
    	            }
    	            
    	            // css에 uppercase클래스가 지정된 경우 대문자로 변경
    	            if(form.handle.hasClass(fid, key, "uppercase")) {
    	            	var fobj = form.getObject(fid, key);
    	            	var fdata = form.handle.getValue(fid, key);
    	            	
    	            	fobj.val(fdata.toUpperCase());
    	            }
                }
                
	        	var validFlag = conf.validationFlag;
	        	var excelFlag = conf.excelFlag;
	        	var progressFlag = conf.progressFlag;
	        	
	        	// 엑셀 데이터 처리
	            var hobj = form.getObject(fid, "headers");
            	var fobj = form.getObject(fid, "filename");
            	var sobj = form.getObject(fid, "sheetname");
            	var wsbj = form.getObject(fid, "websocket");
            	
	            if(excelFlag) {
//	            	console.log("websocket transaction id = " + SESSION.TRANSACTION_ID);
	            	
	            	if(hobj.length > 0) form.handle.setValue(fid, "headers", conf.headers);
	            	if(fobj.length > 0) form.handle.setValue(fid, "filename", conf.filename);
	            	if(sobj.length > 0) form.handle.setValue(fid, "sheetname", conf.sheetname);
	            	if(wsbj.length > 0) form.handle.setValue(fid, "websocket", SESSION.TRANSACTION_ID);
	            } else {
	            	if(hobj.length > 0) form.handle.setValue(fid, "headers", "");
	            	if(fobj.length > 0) form.handle.setValue(fid, "filename", "");
	            	if(sobj.length > 0) form.handle.setValue(fid, "sheetname", "");
	            	if(wsbj.length > 0) form.handle.setValue(fid, "websocket", "");
	            	
		            if(validFlag) {
		                var isValid = $(this).form('validate');
		                if (!isValid) {
		                    return false;
		                }
		            }
		            
		            var pid = form.handle.getProgramID(fid);
		            
		            if(progressFlag) {
		                $.messager.progress({
		                    text: "Data loading...",
		                    msg: "<div id='"+pid+"_ws_00' style='height:40px;'>"+resource.getMessage("MSG_LOADING_WAIT")+"</div>"
		                });
		            }
	            }
	        };
	    },
	    
	    /**
	     * from submit이 성공했을 경우 호출되는 이벤트<br>
	     * 1.form.init.setCallBackFunction함수의 파라메터로 지정한 function명이 있으면 처리완료 시 지정된 함수를 호출함<br>
	     * (지정된 함수의 구현위치는 Program ID.event.함수명)<br>
	     * 2.form.init.setLoadData를 true로 설정하면 "loadData"이면 form에 포함된 element로 자동 값이 입력됨.
	     * 
	     * @param obj = form object
	     */
	    setSuccess : function(obj) {
	        this.getConfig().success = function(datas) {
	        	var fid = (typeof obj == "object") ? obj.attr("id") : obj;
	        	var conf = form.getInitConfig(fid);
	        	
	        	var fm = document.getElementById(fid);
    	        // 날짜인 경우 value를 변경함.
    	        for (var i = 0; i < fm.length; i++) {
    	        	var noTag = fm.elements[i];
    	            var key = noTag.id;
    	            var type = noTag.type;
    	            
    	            if (oUtil.isNull(key) || key == "headers" || key == "filename" || key == "sheetname") {
    	                continue;
    	            } else { 
    	            	var fobj = form.getObject(fid, key);
    	            	
    	            	if(form.handle.isDateBox(fid, key)) {
    	            		if(!oUtil.isNull(fobj.combo("getValue"))) {
	    	            		var cDate = calendar.util.getDate2String(fobj.combo("getValue"), "", NATION_DATE_DB);
	    	            		fobj.combo("setValue", cDate);
    	            		}
    	            	}
    	            }
                }
    	        
	            var progressFlag = conf.progressFlag;
	            var succesemsg = conf.succeseMessage;
	            var failmsg = conf.failMessage;
	            var callFunc = conf.callFunc;
	            var loadData = conf.loadData;
	            var jsonReFlag = conf.jsonReturnFlag;
	            var debugFlag = conf.debugFlag;
	            var procFalg = true;
	            var returnType = conf.returnType;
	            
	            try {
	                var data;
	                
	                if(returnType != "xml") {
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
	                } else {
	                	data = datas;
	                }
	                
	                if (progressFlag) {
	                    $.messager.progress('close');
	                }
	                
	                if (!oUtil.isNull(data.message)) {
	                    alert(data.message, "window");
	                    
                        procFalg = false;
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

	                            procFalg = false;
	                        }
	                    }
	                }
	                
	                if(procFalg) {
		                if (loadData) {
		                	form.handle.loadData(obj, data);
		                    // combo와 date자동 입력 함수 호출
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
		                			conf.callFunc = ""; // 다른 요청에 적용되지 않도록 null처리함.
		                		}
	                        }
		                }
	                }
	            } catch (e) {
	                if (progressFlag) {
	                    $.messager.progress('close');
	                }
	                
	                if(debugFlag) console.debug(e);
	            } finally {
	            	// 기본설정 내용을 초기화 시킨다.
	            	conf.url = "";
	            	conf.succeseMessage = "";
                    conf.failMessage = "";
                    conf.validationFlag = true;
                	conf.progressFlag = true;
                	conf.jsonReturnFlag = true;
            	    conf.debugFlag = false;
            	    conf.returnType = "none";
            	    conf.loadData = false;
                    
                    return procFalg;
	            }
	        };
	    }
    },
    
    handle : {
	   
    	/**
	     * form안에 엘리먼트의 값을 지정한다.
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param val = id에 입력할 값
	     */
	    setValue : function(formId, eId, val) {
	        var frm = form.getObject(formId, eId);
	        
	        try {
		        if(frm.hasClass("easyui-numberbox")) { // 넘버박스인 경우 처리(2020.07.10)
		        	frm.numberbox("setValue", val);
		        } else {
		        	frm.val(val);
		        }
	        } catch(e) {
	        	frm.val(val);
	        }
	        
	        if(frm.hasClass("validatebox-text")) {
        		frm.validatebox("validate");
        	}
	    },
	    
	    /**
	     * form안에 엘리먼트의 값을 구한다.
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param dval = 빈값일 경우, 리턴할 값
	     * @return form.eId.value
	     */
	    getValue : function(formId, eId, dval) {
	    	var rst;
	        var frm = form.getObject(formId, eId);
	        
	        if(oUtil.isNull(frm.val()) && !oUtil.isNull(dval)) {
	        	return dval;
	        }
	        
	        try {
		        if(frm.hasClass("easyui-numberbox")) { // 넘버박스인 경우 처리(2020.07.10)
		        	rst = oUtil.getFilterFloat(frm.val());
		        } else {
		        	rst = frm.val();
		        }
	        } catch(e) {
	        	rst = frm.val();
	        }
	        
	        return rst;
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
	        
	        for(var i = 0; i < obj.length; i++) {
	        	if($('input[id='+eId+']:checked', '#'+formId)) {
	                return $('input[id='+eId+']:checked', '#'+formId).val();
	            }
	        }
	    },
	    
	    /**
	     * form안에 radio 타입의 값을 구한다.
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 엘리먼트 id
	     * @return form.eId.value
	     */
	    setRadioValue : function(formId, eId, value) {
	    	$('input:radio[id='+eId+']:radio[value='+value+']', '#'+formId).prop('checked', true);
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
	        
	        if(oUtil.isNull(dataFlag)) {
	            if(!oUtil.isNull(url)) {
	                dataFlag = false;
	            } else {
	                dataFlag = true;
	            }
	        }
	        
	        if (dataFlag) {
	        	var sobj = Object.keys(jsonData);
	        	
	        	// 등록할 데이터에 null값이 있으면 공백으로 치환한다.(2021-11-12)
	        	for(var i = 0 ; i < sobj.length; i++) {
	        		var id = sobj[i];
	        		var jval = jsonData[id];
	        		
	        		if(typeof jval == "string") {	        			
		        		if(jval.toUpperCase() == "NULL") {
		        			alert(jval);
		        			jsonData[id] = "";
		        		}
	        		}
		        }
		        
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
	    readonly : function(formId, eId, flag) {
	        var obj = form.getObject(formId, eId);
	        
	        obj.attr("readonly", flag);
	    },
	    
	    /**
	     * 지정된 <form - element>의 활성화 여부 설정
	     *  
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param flag = 활성화 여부(ture:비활성화, false:활성화)
	     */
	    disabled : function(formId, eId, flag, real) {
	        var obj = form.getObject(formId, eId);
	        
            obj.attr("disabled", flag);
	    },
	    
	    /**
	     * 지정된 <form - element>의 클래스 적용
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param cName = 적용할 클래스명
	     */
	    addClass : function(formId, eId, cName) {
	    	if(!this.hasClass(formId, eId, cName)) {
		    	var obj = form.getObject(formId, eId);
		        
		        obj.addClass(cName);
	    	}
	    },
	    
	    /**
	     * 지정된 <form - element>의 클래스 적용 취소
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param cName = 적용할 클래스명
	     */
	    removeClass : function(formId, eId, cName) {
	    	if(this.hasClass(formId, eId, cName)) {
		    	var obj = form.getObject(formId, eId);
		        
		        obj.removeClass(cName);
	    	}
	    },
	    
	    /**
	     * 지정된 <form - element>에 적용되어 있는 클래스인지 체크
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param cName = 적용할 클래스명
	     * @return 클래스 존재여부(true|false)
	     */
	    hasClass : function(formId, eId, cName) {
	        var obj = form.getObject(formId, eId);
	        
	        if(obj.hasClass(cName)) {
	        	return true;
	        } else {
	        	return false;
	        }
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
	     * 지정된 <form - element>의 Validate 적용여부 설정
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @param flag = 체크여부(true:체크함, false:체크안함)
	     */
	    setValidate : function(formId, eId, flag) {
	        var obj = form.getObject(formId, eId);
	        
	        if(flag) {
	        	var requAtt = obj.attr("required");
	        	var required = (oUtil.isNull(requAtt))?false:true;
	        	
	        	if (obj.hasClass("easyui-validatebox")) {
	        		obj.validatebox({
	        			required : required
	        		});
	        	}
	        } else {
	        	if(obj.hasClass("easyui-validatebox")) {
	        		var requAtt = obj.attr("required");
		        	var required = (oUtil.isNull(requAtt))?false:false;
		        	
	        		obj.validatebox({
	        			required : required
	        		});
	        	}
	        }
	    },
	    
	    /**
	     * form안에 모든 필수항목여부를 지정하거나 해지한다.
	     * 
	     * @param formId = form id:String
	     * @param flag = true(validation 지정), false(validation 해지)
	     * @param exps = 예외 리스트(form내 엘리먼트를 Array로 저장한 값)
	     */
	    setValidateAll : function(formId, flag, exps) {
	        var fm = document.getElementById(formId);
	        var fmlen = fm.length;
	        
	        if(oUtil.isNull(flag)) flag = true;
	        
	        for(var i = 0 ; i < fmlen; i++) {
	            var noTag = fm.elements[i];
	            var id;
	            try {
	            	id= noTag.id;
	            } catch(e) {
	            	continue;
	            }
	            
	            var exist = false;
	            
	            if(!oUtil.isNull(exps)) {
		            for(var e = 0; e < exps.length; e++) {
		            	if(id == exps[e]) {
		            		exist = true;
		            		break;
		            	}
		            }
	            }
	            
	            if(exist == true) continue;
	            	
	            var type = noTag.type;
	            var eobj = form.getObject(formId, id);
	            
	            if (oUtil.isNull(id)) {
	                continue;
	            } else {
	            	if(eobj.hasClass("easyui-validatebox")) {
	            		this.setValidate(formId, id, flag);
	            	}
	            }
	        }
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
	     * @param flag = 보이기 여부(true:보임, false 또는 "none" :숨김)
	     */
	    setVisible : function(formId, eId, flag) {
	        var obj = form.getObject(formId, eId);
	        
	        if(!is_bottom_auth(obj)) {
	        	if(typeof flag == "string") {
        			if(flag != "none") obj.show();
    	    		else obj.hide();
        		} else {        			
        			if(flag) obj.show();
        			else obj.hide();
        		}
        	}
	    },
	    
	    /**
	     * form의 입력란을 모두 닫는다.
	     * 
	     * @param formId = form id:String
	     * @param flag = true(입력 막기), false(입력 풀기)
	     * @param exps = 예외 리스트(form내 엘리먼트를 Array로 저장한 값)
	     */
	    closeAll : function(formId, flag, exps) {
	        var fm = document.getElementById(formId);
	        var fmlen = fm.length;
	        
	        if(oUtil.isNull(flag)) flag = true;
	        
	        for(var i = 0 ; i < fmlen; i++) {
	            var noTag = fm.elements[i];
	            var id;
	            try {
	            	id= noTag.id;
	            } catch(e) {
	            	continue;
	            }
	            
	            var exist = false;
	            
	            if(!oUtil.isNull(exps)) {
		            for(var e = 0; e < exps.length; e++) {
		            	if(id == exps[e]) {
		            		exist = true;
		            		break;
		            	}
		            }
	            }
	            
	            if(exist == true) continue;
	            	
	            var type = noTag.type;
	            var eobj = form.getObject(formId, id);
	            
	            if (oUtil.isNull(id)) {
	                continue;
	            } else { 
		            var boo1 = this.isComboBox(formId, id);
	                var boo2 = this.isDateBox(formId, id);
	                var boo3 = this.isDatetimespinner(formId, id);
	                
	                if(boo1) {
	                	combo.handle.readonly(formId, id, flag);
	                	continue;
	                } 
	                if(boo2 || boo3) {
	                	calendar.handle.readonly(formId, id, flag);
	                	continue;
	                } else {
	                    if(type == "file") {
	                    	if(flag) form.handle.disabled(formId, id, true);
	                    	else form.handle.disabled(formId, id, false);
	                    } else if(type == "checkbox") { // radio button
		                	;
		                } else if(type == "radio") { // radio button
		                	;
		                } else if(type == "text" || type == "textarea") {
		                	if(flag) {
		                		this.setValidate(formId, id, false);
			                	
			                	if(!eobj.hasClass("input_readOnly")) {
				                	eobj.addClass("input_readOnly");
			                	}
			                	
			                	eobj.attr("readonly", true);
		                	} else {
		                		this.setValidate(formId, id, true);
		                		
		                		if(eobj.hasClass("input_readOnly")) {
				                	eobj.removeClass("input_readOnly");
		                		}
		                		
		                		eobj.attr("readonly", false);
		                	}
			            }
	                }
	            }
	        }
	        
	        if(!flag) form.handle.isValidate(formId);
	    },
	    
	    /**
         * 파라메터로 지정한 타입으로 해당 component를 변경한다.
         * 
         * @param formId = form id
	     * @param eId = form 구성 id
         * @param type = element type(text)
         */
        changeUI : function(formId, eId, type, value) {
        	var id = formId+"_"+eId;
        	var obj = $("#"+id);
        	
        	if(type == "label") {
        		if(obj.length > 0) {
        			obj.html("");
        			obj.append("<label>"+value+"</label>");
        		}
        	}
        },
	    
	    /**
	     * 입력 폼이 Combo Box인지 여부를 리턴
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @return true:combo box, false:no combo box
	     */
	    isComboBox : function(formId, key) {
	    	var obj = form.getObject(formId, key);
	    	
	    	var result = true;
	    	try {
	    		if(obj.hasClass("combo-f") || obj.hasClass("combobox-f")) {
	    			result = true;
	    		} else {
	    			result = false;
	    		}
	    		//obj.combobox("options");
            } catch(e) {
            	result = false;
            }
            
            return result;
	    },
	    
	    /**
	     * 입력 폼이 Date Box인지 여부를 리턴
	     * 
	     * @param formId = form id
	     * @param eId = form 구성 id
	     * @return true:deta box, false:no date box
	     */
	    isDateBox : function(formId, key) {
	    	var obj = form.getObject(formId, key);
	    	
	    	var result = true;
	    	try {
                obj.datebox("options");
            } catch(e) {
            	result = false;
            }
            
            return result;
	    },
	    
	    /**
         * 객체가 월달력인지 체크
         * 
        * @param formId = form id
	     * @param eId = form 구성 id
	     * @return true:deta box, false:no date box
         */
        isDatetimespinner : function(formId, key) {
            var obj = form.getObject(formId, key);
	    	
	    	var result = true;
	    	try {
                obj.datetimespinner("options");
            } catch(e) {
            	result = false;
            }
            
            return result;
        },
        
	    /**
	     * form Element에 저장된 모든 값을 구한다.
	     * 
	     * @param name = form id:String
	     * @param up = 상위 엘리먼트에서 값을 찾을지 여부(true/false)
	     * @return form element의 id와 값를 Json타입으로 리턴
	     */
	    getElementsParam : function(formId, up) {
	        var fm = new Object();
	        
	        if(up) {	        	
	        	fm = window.parent.document.getElementById(formId);
	        } else {
	        	fm = document.getElementById(formId);
	        }
	        
	        if(oUtil.isNull(fm)) alert("not found fromID.");
	        
	        var fmlen = fm.length;
	        var jsonVal = {};
	        
	        for (var i = 0; i < fmlen; i++) {
	        	var noTag = fm.elements[i];
	            var key = noTag.name;
	            if(oUtil.isNull(key)) key = noTag.id;
	            var type = noTag.type;
	            
	            if (oUtil.isNull(key) || key == "headers" || key == "filename" || key == "sheetname") {
	                continue;
	            } else { 
	            	var obj = form.getObject(formId, key);
	            	var value = "";
	            	
	                if(type == "checkbox") { // radio button
	                	value = this.getCheckboxValue(formId, key);
	                	if(oUtil.isNull(value)) value = "";
	                } else if(type == "radio") { // radio button
	                	value = this.getRadioValue(formId, key);
	                	if(oUtil.isNull(value)) value = "";
	                } else {
	                	var boo1 = this.isComboBox(formId, key);
		                var boo2 = this.isDateBox(formId, key);
		                var value = "";
		                
		                // combobox
		                if(boo1) {
		                	value = combo.handle.getValue(formId, key);
		                }
		                // calendar
		                if(boo2) {
		    	            value = calendar.handle.getDate(formId, key);
		                }
		                
		                if(oUtil.isNull(value)) {
		                	if(up) {
		                		if(type == "textarea") {
			                		value = $("#" + formId, parent.document).find("textarea[name=\""+key+"\"]").val();
			                	} else {
		                			value = $("#" + formId, parent.document).find("input[name=\""+key+"\"]").val();
		                		}
		                	} else {
			                	if(type == "textarea") {
			                		value = $("#" + formId).find("textarea[name=\""+key+"\"]").val();
			                	} else {
		                			value = $("#" + formId).find("input[name=\""+key+"\"]").val();
		                		}
		                	}
		                }
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
    util : {
    	/**
         * 지정된 <div>의 쓰기 여부 설정 
         * 
         * @param divId = DIV ID
         * @param flag = 쓰기 금지 여부(ture:읽기 전용, false:쓰기 가능)
         */
    	readonly : function(divId, flag) {
       		$("#"+divId).attr("readonly", flag);
        },
        
        /**
         * 지정된 <div>의 클래스 적용 
         * 
         * @param divId = DIV ID
         * @param cName = 적용할 클래스명
         */
        addClass : function(divId, cName) {
    		if(!this.hasClass(divId, cName)) $("#"+divId).addClass(cName);
        },
        
        /**
         * 지정된 <div>의 클래스 적용 취소
         * 
         * @param divId = DIV ID
         * @param cName = 적용할 클래스명
         */
        removeClass : function(divId, cName) {
        	if(this.hasClass(divId, cName)) $("#"+divId).removeClass(cName);
        },
        
        /**
	     * 지정된 <div>에 적용되어 있는 클래스인지 체크
	     * 
	     * @param divId = DIV ID
         * @param cName = 적용할 클래스명
	     * @return 클래스 존재여부(true|false)
	     */
	    hasClass : function(divId, cName) {
	        if($("#"+divId).hasClass(cName)) {
	        	return true;
	        } else {
	        	return false;
	        }
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
         * @param flag = 보이기 여부(true:보임, false 또는 "none" :숨김)
         */
        setVisible : function(divId, flag) {
        	var obj = $("#"+divId);
        	
        	// 버튼권한이 숨김으로 되어 있으면 하위 로직은 처리되지 않도록 함(2021-10-20)
        	if(!is_bottom_auth(obj)) {
        		if(typeof flag == "string") {
        			if(flag != "none") obj.show();
    	    		else obj.hide();
        		} else {        			
        			if(flag) obj.show();
        			else obj.hide();
        		}
        	}
        },
        
        /**
         * input type이 file인 경우 선택한 파일 목록이 표시되는 tooltip 생성
         * @param tagId - tooltip객채명
         * @param gridId - tooltip내 파일 목록을 표시할 데이터 그리드
         * @param callback - tooltip이 열리기 전에 수행될 function 명
         * @param width - tooltip 넓이
         * @param height - tooltip 높이
         */
        setFileTooltip : function(tagId, gridId, callback, width, height) {
        	if(oUtil.isNull(tagId)) alert("undefined element ID.");
        	if(oUtil.isNull(gridId)) alert("undefined dagagrid ID.");
        	
        	if(oUtil.isNull(width)) width = 750;
        	if(oUtil.isNull(height)) height = 200;
        	
        	try {
        		$("#"+gridId).remove();
        	} catch(e) {}
        	
    	    $('#'+tagId).tooltip({
    	        showEvent: "none"
    	       ,hideEvent: "none"
    	       ,position: "bottom"
    	       ,content: $("<div></div>")
    	       ,onUpdate: function(content){
    	           content.panel({
    	                width: width
    	               ,height: height
    	               ,border: false
    	               ,content:
    	            	   "<div class=\"easyui-layout\" data-options=\"fit:true\">" +
                           "<div data-options=\"region:'center',border:false,fit:true\" style=\"padding-right:-6px;\">" +
                           "<table id='"+gridId+"'></table>" +   
                           "</div></div>"
    	               ,onBeforeOpen : function() {
    	            	   if (!oUtil.isNull(callback)) {
    	                        var pro_id = eval("window." + grid.handle.getProgramID(gridId) + ".tool");
    	                        if (!oUtil.isNull(pro_id)) {
    	                        	// 그리드 ID에 해당하는 <code>function</code>이 있으면 호출된다.
    	                            if (typeof(pro_id[callback]) == "function") {
    	                                pro_id[callback]();
    	                            }
    	                        }
    	                    }
    	               }
    	               ,onOpen : function() {
    	                   var tdg = grid.getObject(gridId);
    	                   tdg.datagrid("reload");
    	               }
    	           });
    	        }
    	       ,onPosition: function() {
    	           var t = $(this);
    	           var curtLeft = 0;
    	           var curLef = 0;
    	           
    	           if(t.length > 0){
    	        	   if(t[0].offsetParent) {
    	        	       curtLeft = t[0].offsetLeft;
    	           	   }
    	           }
    	           
    	           t.tooltip('tip').css('left', (t.offset().left - curtLeft));
    	           t.tooltip('tip').css('margin-top', -12);
    	           t.tooltip('arrow').css('left', 20);
    	        }
    	       ,onShow: function() {
    	           var t = $(this);
    	           
    	           t.tooltip('tip').focus().unbind().bind('blur', function() {
    	        	   t.tooltip('hide');
    	           });
    	       }
    	   });
        },

        /**
         * input type이 file인 경우 선택한 파일 목록이 표시되는 tooltip 생성
         * @param tagId - tooltip객채명
         * @param gridId - tooltip내 파일 목록을 표시할 데이터 그리드
         * @param fieldId - 검색어 입력 textarea
         * @param callback - tooltip이 열리기 전에 수행될 function 명
         * @param width - tooltip 넓이
         * @param height - tooltip 높이
         * @param num - object 번호
         */
        setSearchTooltip : function(tagId, gridId, fieldId, callback, width, height, num) {
        	if(oUtil.isNull(tagId)) alert("undefined element ID.");
        	if(oUtil.isNull(gridId)) alert("undefined dagagrid ID.");
        	
        	form.util.setVisible(tagId, true);
        	
        	if(oUtil.isNull(width)) width = 750;
        	if(oUtil.isNull(height)) height = 200;
        	
        	try {
        		$("#"+gridId).remove();
        	} catch(e) {
            }
        	
        	var maxCnt;
			
			try{
				maxCnt = MAX_MULTI_COUNT;
			} catch(e) {
				maxCnt = 100;
			}
			
        	var pid = grid.handle.getProgramID(gridId)
        	var contents =  "<div class=\"easyui-layout\" data-options=\"fit:true\" style=\"background-color:#fff;\">" +
	            "<div data-options=\"region:'north',border:false\" style=\"background-color:#fff;height:30px;overflow:hidden;\">"+
	            "<a href=\"javascript:"+pid+".tool.selectMultiTool('"+num+"');\" class=\"easyui-linkbutton\" style=\"width:248px;font-weight:bold;\">"+resource.getMessage("TXT_MULTI_SELECT")+"</a>"+
	            "</div>" +
	            "<div data-options=\"region:'center',border:false\">" +
	            "<div class=\"easyui-layout\" data-options=\"fit:true\" style=\"background-color:#fff;\">" +
	            "<div data-options=\"region:'north',border:false\" style=\"background-color:#fff;height:120px;\">"+
	            "<div class=\"h2_etc\" style=\"padding-bottom:2px;\">" +
	            "<p class=\"h2_btn\">" +
	            "<a href=\"javascript:tooltip.util.clearAll('"+fieldId+"', '"+gridId+"');\" class=\"btn\">"+resource.getMessage("CLEAR")+"</a> " +
	            "<a href=\"javascript:form.util.hideTooltip('"+tagId+"');\" class=\"btn\">"+resource.getMessage("CLOSE")+"</a>" +
	            "</p></div>"+
	            "<textarea id=\""+fieldId+"\" placeholder=\""+resource.getMessage("TXT_COPY_PASTE")+"\n" +"("+resource.getMessage("MAX")+":" +maxCnt+ resource.getMessage("COUNT")+")" + "\" contenteditable=\"true\" onpaste=\"javascript:tooltip.util.generateTable('"+fieldId+"', '"+gridId+"');\" oninput=\"javascript:tooltip.util.generateTable('"+fieldId+"', '"+gridId+"');\" style=\"width:97%;height:77%;background-color:#efefef\"></textarea>"+
	            "</div>"+
	    	    "<div data-options=\"region:'west',border:false\" style=\"background-color:#fff;width:248px;\">" +
	            "<table id=\""+gridId+"\"></table>" +
	            "</div></div></div>";
    	    $('#'+tagId).tooltip({
    	        showEvent: "none"
    	       ,hideEvent: "none"
    	       ,position: "bottom"
    	       ,content: $("<div></div>")
    	       ,onUpdate: function(content){
    	           content.panel({
    	                width: width
    	               ,height: height
    	               ,border: false
    	               ,content: contents
    	               ,onBeforeOpen : function() {
    	            	   if (!oUtil.isNull(callback)) {
    	                        var pro_id = eval("window." + pid + ".tool");
    	                        if (!oUtil.isNull(pro_id)) {
    	                            if (typeof(pro_id[callback]) == "function") {
    	                                pro_id[callback](num);
    	                            }
    	                        }
    	                    }
    	               }
    	               ,onOpen : function() {
    	                   var tdg = grid.getObject(gridId);
    	                   tdg.datagrid("reload");
    	               }
    	           });
    	        }
    	       ,onPosition: function() {
    	           var t = $(this);
    	           var curtLeft = 0;
    	           var curLef = 0;
    	           
    	           if(t.length > 0){
    	        	   if(t[0].offsetParent) {
    	        	       curtLeft = t[0].offsetLeft;
    	           	   }
    	           }
    	           
    	           t.tooltip('tip').css('left', t.offset().left+21);
    	           t.tooltip('tip').css('margin-top', -22);
    	           t.tooltip('arrow').css('left', 20);
    	        }
    	       ,onShow: function() {
    	    	   return;
    	    	   var t = $(this);
    	    	   
    	           t.tooltip('tip').unbind().bind('blur', function() {
    	               t.tooltip('hide');
    	           });
    	       }
    	   });
        },
        
        /**
         * 지정된 <div>의 툴팁 보임
         * @param tagId - tooltip객채명
         */
        showTooltip : function(tagId) {
            var t = $('#'+tagId);
            t.tooltip('show');
        },
        
        /**
         * 지정된 <div>의 툴팁 숨김
         * @param tagId - tooltip객채명
         */
        hideTooltip : function(tagId) {
            var t = $('#'+tagId);
            t.tooltip('hide');
        },
        
        disabledImage : function(tagId, flag) {
        	var clsname = "list_icon_disabled";
        	
        	if(flag) {
        		if(!this.hasClass(tagId, clsname))$("#"+tagId).addClass(clsname);
        	} else {
        		if(this.hasClass(tagId, clsname))$("#"+tagId).removeClass(clsname);
        	}
        },
        
        disabled : function(tagId, flag, nocls) {
        	var encls = "order_icon_enabled";
        	var discls = "order_icon_disabled";
        	
            if(nocls == true) encls = discls;
            
        	if(flag) {
        		if(!this.hasClass(tagId, discls))$("#"+tagId).addClass(discls);
        		if(this.hasClass(tagId, encls))$("#"+tagId).removeClass(encls);
        	} else {
        		if(!this.hasClass(tagId, encls))$("#"+tagId).addClass(encls);
        		if(this.hasClass(tagId, discls))$("#"+tagId).removeClass(discls);
        	}
        }
        
    }
    
};