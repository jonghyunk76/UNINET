var comboMethod = function() {
    init = {

        /**
         * 콤보박스에 표시할 호출 URL 설정
         * 
         * @param string = URL 경로
         */
        setURL : function(string) {
        	this.getConfig().url = string;
        },
        
        /**
		 * 콤보박스의 호출 시 넘겨줄 파라메터 설정
		 * 
		 * @param object = 파라메터:json
		 */
		setQueryParams : function(object) {
			this.getConfig().queryParams = object;
		},
		
		/**
		 * 콤보박스에 표시할 데이터(name과 value을 쌍으로 지정)
		 * 
		 * @param object = combobox data:json
		 */
		setData : function(object) {
			this.getConfig().data = object;
		},
		
        /**
         * 콤보박스의 Name로 표시될 field(Column) ID/Name 설정
         * 
         * @param string = field(Column) ID/Name
         */
        setValueField : function(string) {
        	this.getConfig().valueField = string;
        },
        
        /**
         * 콤보박스의 Value로 표시될 field(Column) ID/Name 설정
         * 
         * @param string = field(Column) ID/Name
         */
        setNameField : function(string) {
        	this.getConfig().textField = string;
        },
        
        /**
         * 콤보박스의 높이 설정
         * 
         * @param number = 높이값
         */
        setHeight : function(number) {
        	this.getConfig().panelHeight = number;
        },
        
        /**
         * 콤보박스의 Edit 가능여부 설정(default:false)
         * 
         * @param boolean = true:입력가능,  false:읽기만 가능 
         */
        setEditable : function(boolean) {
        	this.getConfig().editable = boolean;
        },
        
        /**
	     * 콤보박스의 값이 변경될 경우 호출될 함수 설정.<br> 
	     * 
	     * @param string = 함수명(program ID + "." + 함수명)
	     */
	    setCallFunction : function(string) {
	    	this.getConfig().callFunc = string;
	    },
        
        /**
         * 콤보박스의 다중선택 가능여부 설정(default:false)
         * 
         * @param boolean = true:다중선택,  false:단일 선택 
         */
        setMultiple : function(boolean) {
        	this.getConfig().multiple = boolean;
        },
        
        /**
		 * 콤보박스의 호출 방식 설정(default:post)
		 * 
		 * @param params = post, get
		 */
		setMethod : function(string) {
			this.getConfig().method = string;
		},
		
		/**
	     * 콤보박스에 초기 설정할 값 설정<br> 
	     * 초기로딩 시 설정된 값으로 자동으로 셋팅됨
	     * 
	     * @param string = 함수명(program ID + "." + 함수명)
	     */
	    setValue : function(string) {
	    	this.getConfig().initValue = string;
	    },
	    
	    /**
	     * 필수입력여부 지정
	     * 
	     * @param boolean = 필수:true, 필수아님:false 
	     */
	    setRequired : function(boolean) {
	        this.getConfig().required = boolean;
	    }
        
    },
    event = {

        /**
         * 콤보박스가 성공적으로 생성되었을 경우 호출되는 함수 - 정상
         * 
         * @param obj = combobox object
         */
        setOnLoadSuccess : function(obj) {
        	this.getConfig().onLoadSuccess = function(data) {
        		var fid = (typeof obj == "object") ? obj.parents("#"+obj.attr("id") + ",form").attr("id") : fid;
	        	var eid = (typeof obj == "object") ? obj.attr("id") : obj;
	        	var conf = combo.getInitConfig(fid, eid);
	        	
        		if (!oUtil.isNull(data) && data.length > 0) {
	                var val = eval("data[0]." + conf.valueField);
	                var value = conf.initValue;
	                	
	                if (!oUtil.isNull(value)) {
	                    for (var j = 0; j < data.length; j++) {
	                        var cmpval = eval("data[" + j + "]." + conf.valueField);
	                        if (cmpval == value) {
	                            val = value;
	                            break;
	                        }
	                    }
	                }
	                
	                obj.combobox("select", val);
	                var maxLen = getMaxDataLength(data, conf.textField);
	                
	                if ($(this).width() < maxLen) {
	                    var pan = obj.combobox("panel");
	                    pan.panel('resize', {
	                        width: maxLen
	                    });
	                }
	            } else {
	            	obj.combobox("select", "");
	            }
        	};
        },
        
        /**
	     * 콤보박스가 선택내용이 변경될 경우 호출되는 이벤트<br> - 정상
	     * 1.combo.init.setCallBackFunction함수의 파라메터로 지정한 function명이 있으면 처리완료 시 지정된 함수를 호출함<br>
	     * (지정된 함수의 구현위치는 Program ID.combobox.함수명)
	     * 
	     * @param obj = combobox object
	     * @param fid = form id
	     */
        setOnChange : function(obj, fid) {
	        this.getConfig().onChange = function(data) {
	        	var fid = (typeof obj == "object") ? obj.parents("#"+obj.attr("id") + ",form").attr("id") : fid;
	        	var eid = (typeof obj == "object") ? obj.attr("id") : obj;
	        	
	        	var pid = form.handle.getProgramID(fid);
	        	var conf = combo.getInitConfig(fid, eid);
	        	var callFunc = conf.callFunc;
	        	
	        	if (!oUtil.isNull(callFunc)) {
	        		var funcPath = eval("window." + pid + ".event");
	        		
            		if (typeof(funcPath[callFunc]) == "function") {
            			funcPath[callFunc](data);
            		}
                }
	        };
        }
        
    },
    handle = {
    	
    	/**
         * combobox의 value 지정
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param val = 콤보박스에 지정할 값
         */
        setValue : function(formId, tagId, val) {
            var obj = form.getObject(formId, tagId);
            
            if (this.isCombobox(formId, tagId)) {
            	obj.combobox("select", val);
            } else {
            	obj.val(val);
            }
        },
        
        /**
         * combobox의 value(값)을 받아온다.
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         */
        getValue : function(formId, tagId) {
            var obj = form.getObject(formId, tagId);
            
            if (oUtil.isNull(obj)) {
                return "";
            } else { 
                var multiple = false;
                var exp = false;
                var value;
                
                try {
                    multiple = obj.combobox("options");
                } catch(e) {
                	exp = true;
                }
                
                if(!exp) {
    	            multiple = obj.combobox("options").multiple;
    	            
    	            if (multiple) {
    	                value = obj.combobox("getValues");
    	            } else {
    	                value = obj.combobox("getValue");
    	            }
                }
                
                if (oUtil.isNull(value)) {
                	value = obj.val();
                }
                
                return value;
            }
        },
        
        /**
         * combobox의 text를 받아온다.
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         */
        getText : function(formId, tagId) {
            var obj = form.getObject(formId, tagId);
            
            if (oUtil.isNull(obj)) {
                return "";
            } else {
                var value = obj.combobox("getText");
                
                if (oUtil.isNull(value)) {
               		value = obj.val();
                }
                return value;
            }
        },
        
        /**
         * combobox disable 상태로 변경
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param flag = disable 여부(true, false)
         */
        disabled : function(formId, tagId, flag) {
            var obj = form.getObject(formId, tagId);
            
            if (this.isCombobox(formId, tagId)) {
                if (flag) {
                	obj.combobox("disable");
                } else {
                	obj.combobox("enable");
                }
                
                return true;
            } else {
                return false;
            }
        },
        
        /**
         * combobox를 readonly 한다.
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param flag = readonly 여부(true, false)
         */
        readonly : function(formId, tagId, flag) {
            var obj = form.getObject(formId, tagId);
            
            if (this.isCombobox(formId, tagId)) {
            	obj.combobox("readonly", flag);
                return true;
            } else {
                return false;
            }
        },
        
        /**
         * combobox의 데이터를 지정된 URL결과 값으로 변경한다.
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param url = 호출할 URL
         * @param param = URL 파라메터
         */
        reload : function(formId, tagId, url, param) {
            var obj = form.getObject(formId, tagId);
            var initConf = combo.getInitConfig(formId, tagId);
            
            if (this.isCombobox(formId, tagId)) {
            	obj.combobox("reload", {url: url, queryParams: [param]});
            }
        },
        
        /**
         * 객체가 콤보박스인지 체크
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         */
        isCombobox : function(formId, tagId) {
            var obj = form.getObject(formId, tagId);
            
            try {
    	        if(!oUtil.isNull(obj.combobox("options"))) {
    	            return true;
    	        } else {
    	            return false;
    	        }
            } catch(e) {
            	return false;
            }
        }
    }
};
