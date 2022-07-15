var comboMethod =  {
    id : "",
    init : {
    	config : {},
    	
        getConfig : function() {
        	if(dataset.isArrayObject(combo.id + ".init")) {
        		return dataset.arrayFilter(combo.id +".init");
        	} else {
        		return this.config;
        	}
        },
        
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
			try {
				if(!oUtil.isNull(object)) {
					if(object.length > 0) {
						if(!oUtil.isNull(DBMS_TYPE) && DBMS_TYPE == "oracle") {
							for(var i = 0; i < object.length; i++) {
								object[i].CODE = replaceAll(object[i].CODE, "DBO.", "");
							}
						}
					}
				}
			} catch(e) { console.log(e); }
			
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
         * 콤보박스의 넓이 설정
         * 
         * @param number = 넓이값
         */
        setWidth : function(number) {
        	this.getConfig().panelWidth = number;
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
	     * @param string = code value
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
	    },
	    
	    /**
	     * 콤보박스 타입이 "year"일 경우 시작될 년도 설정
	     * 
	     * @param integer = 년도:year, 월:month
	     */
	    setStartYear : function(integer) {
	        this.getConfig().startYear = integer;
	    },
	    
	    /**
	     * 콤보박스 타입이 "year"일 경우 시작월로부터 표시될 루프 수 지정
	     * 
	     * @param integer = 루프 카운트
	     */
	    setYearCount : function(integer) {
	        this.getConfig().yearCount = integer;
	    },
	    
	    /**
	     * 콤보박스 타입지정
	     * 
	     * @param string = 년도:year, 월:month
	     */
	    setType : function(string) {
	        this.getConfig().type = string;
	    },
	    
	    /**
         * 읽기전용 여부 설정
         * 
         * @param boolean = true:읽기, false:쓰기
         */
        setReadonly : function(boolean) {
	        this.getConfig().readonly = boolean;
	    },
	    
	    /**
         * combo 타입지정
         * 
         * @param comboType = combobox, combotree 등
         */
        setComboType : function(type) {
        	if(oUtil.isNull(type)) type = "combobox";
        	
	        this.getConfig().comboType = type;
	    },
        
        
        /**
         * combo 포맷지정
         * 
         * @param fnc = 포맷을 지정할 함수명
         */
        setFormatter : function(fnc) {
            this.getConfig().formatter = fnc;
        }
        
    },
    event : {
        config : {},
        
        getConfig : function() {
        	if(dataset.isArrayObject(combo.id + ".event")) {
        		return dataset.arrayFilter(combo.id +".event");
        	} else {
        		return this.config;
        	}
        },
        
        /**
         * 콤보박스가 성공적으로 생성되었을 경우 호출되는 함수 - 정상
         * 
         * @param obj = combobox object
         */
        setOnLoadSuccess : function(obj) {
        	this.getConfig().onLoadSuccess = function(data, treedata) {
        		var fid = (typeof obj == "object") ? obj.parents("#"+obj.attr("id") + ",form").attr("id") : fid;
	        	var eid = (typeof obj == "object") ? obj.attr("id") : obj;
	        	var conf = combo.getInitConfig(fid, eid);
	        	
	        	if(conf.comboType == "combotree") {
	        		data = treedata;
	        	}
	        	
        		if (!oUtil.isNull(data) && data.length > 0) {
	                var val = eval("data[0]." + conf.valueField);
	                var value = conf.initValue;
	                var formVal = form.handle.getValue(fid, eid);
	                
	                if(oUtil.isNull(value) && !oUtil.isNull(formVal)) {
	                	value = formVal;
	                }
	                if (!oUtil.isNull(value)) {
	                    for (var j = 0; j < data.length; j++) {
	                        var cmpval = eval("data[" + j + "]." + conf.valueField);
	                        if (cmpval == value) {
	                            val = value;
	                            break;
	                        }
	                    }
	                }
	                
	                if(conf.comboType == "combotree") {
	            		obj.combotree("setValue", val);
	            	} else {
	            		obj.combobox("select", val);
	            	}
	                
	                try {
		                var maxLen;
		                var pan = pan = obj.combo("panel");
		                
		                if(!oUtil.isNull(conf.panelWidth)) {
		                	maxLen = conf.panelWidth;
		                } else {
			                maxLen = getMaxDataLength(data, conf.textField);
		                }
		                
		                if(data.length > 15) {
		                	pan.panel('resize', {
		                        height:280
		                    });
		                }
		                
		                maxLen = maxLen + 20;
		                
		                if ($(this).width() < maxLen) {
		                    pan.panel('resize', {
		                        width: maxLen
		                    });
		                }
	                } catch(e) {
	                	if(SESSION.USER_ID == "fta") console.debug("create combobox exception = " + e);
	                }
	                // 정상적으로 로딩된 데이터의 캐시로 저장함(2020.06.25)
	                if(conf.url == "/mm/cbox/selectStandardCode") {
	                	var exsitsVo = comboVo.isArrayObject(conf.queryParams);
	                	
	                	if(!exsitsVo) {
	                		var vo = new Object();
	                		
	                		vo.id = conf.queryParams; // 식별 ID
	                		vo.data = data; // 캐시할 데이터
	                		
	                		comboVo.arrayPush(vo);
	                	}
	                }
	            } else {
	            	if(conf.comboType == "combotree") {
	            		obj.combotree("setValue", "");
	            	} else {
	            		obj.combobox("select", "");
	            	}
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
	        	
	        	// 수정 및 보기에서 입력되어 있는 form값을 초기화 시킴
	        	form.handle.setValue(fid, eid, "");
	        	
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
        },
        
        setOnShowPanel : function(obj, fid) {
        	this.getConfig().onShowPanel = function() {
        		if (obj.hasClass("validatebox-text")) {
        			;
	        	}
	        };
        },
        
        setOnHidePanel : function(obj, fid) {
        	this.getConfig().onHidePanel = function() {
        		if (obj.hasClass("validatebox-text")) {
        			;
	        	}
	        };
        }
        
    },
    handle : {
    	
    	/**
         * combobox의 value 지정
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param val = 콤보박스에 지정할 값
         */
        setValue : function(formId, tagId, val) {
        	var obj = form.getObject(formId, tagId);
        	var conf = combo.getInitConfig(formId, tagId);
        	
            if (this.isCombobox(formId, tagId)) {
            	if(conf.comboType == "combotree") {
            		obj.combotree("setValue", val);
            	} else {
            		obj.combobox("select", val);
            	}
            } else {
            	obj.val(val);
            }
        },
        
        /**
         * combobox의 value 지정
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param val = 콤보박스에 지정할 값
         */
        setValues : function(formId, tagId, val) {
        	var obj = form.getObject(formId, tagId);
        	var conf = combo.getInitConfig(formId, tagId);
        	
            if (this.isCombobox(formId, tagId)) {
        		var vals = val.toString().split(",");
	            
        		if(conf.comboType == "combotree") {
            		obj.combotree("setValues", vals);
            	} else {
            		obj.combobox("setValues", vals);
            	}
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
            var conf = combo.getInitConfig(formId, tagId);
            
            if (!this.isCombobox(formId, tagId)) {
                return obj.val();
            } else { 
                var multiple = false;
                var exp = false;
                var value;
                
                if(conf.comboType == "combotree") {
                	value = obj.combo("getValue");
                } else {
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
         * @param initVal = 초기 표시값
         */
        reload : function(formId, tagId, url, param, initVal) {
        	var obj = form.getObject(formId, tagId);
            var conf = combo.getInitConfig(formId, tagId);
        	
            if (this.isCombobox(formId, tagId)) {
            	this.clear(formId, tagId);
            	
            	if(conf.comboType == "combotree") {
            		obj.combotree("reload", {url: url, queryParams: [param]});
            	} else {
            		obj.combobox("reload", {url: url, queryParams: [param]});
            	}
            	
            	// 초기값 변경
            	if(!oUtil.isNull(initVal)) {
            		conf.initValue = initVal;
            	} else {
            		conf.initValue = null;
            	}
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
            	if(obj.hasClass("combo-f") || obj.hasClass("combobox-f") || obj.hasClass("combotree-f")) {
	    			return true;
	    		} else {
	    			return false;
	    		}
            } catch(e) {
            	return false;
            }
        },
        
        /**
         * 콤보박스에 선택된 값을 제거
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         */
        clear : function(formId, tagId) {
        	var obj = form.getObject(formId, tagId);
        	var conf = combo.getInitConfig(formId, tagId);
        	var initVal = combo.handle.getValue(formId, tagId);
        	
        	// 데이터 초기화 전에 초기값을 현재 지정된 값으로 변경함
        	conf.initValue = initVal;
        	
        	
            if (this.isCombobox(formId, tagId)) {
            	if(conf.comboType == "combotree") {
            		obj.combotree("clear");
            	} else {
            		obj.combobox("clear");
            	}
            }
        },
        
        /**
         * 콤보박스에 표시되는 리스트 갱신
         * 
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param data = 콤보박스에 표시할 Json타입의 데이터
         */
        loadData : function(formId, tagId, data) {
        	var obj = form.getObject(formId, tagId);
        	var conf = combo.getInitConfig(formId, tagId);
        	
            if (this.isCombobox(formId, tagId)) {
            	this.clear(formId, tagId);
            	
            	if(conf.comboType == "combotree") {
            		obj.combotree("loadData", data);
            	} else {
            		obj.combobox("loadData", data);
            	}
            }
        },
    },
    util : {
    	
    	/**
    	 * 캐시에 저장된 콤보 데이터를 가져오는 함수
    	 * 
    	 * @param opts 캐시를 구분하기 위한 식별자
    	 */
    	getCacheData : function(params) {
    		// 이미 호출된 정보인 경우에는 기존에 캐시된 데이터를 적용하도록 수정(2020.10.07)
            var exsitsVo = comboVo.isArrayObject(params);
            
        	if(exsitsVo) {
        		return comboVo.arrayFilter(params);
        	} else {
        		return null;
        	}
    	},
    	
    	/**
    	 * 공통 콤보함수를 적용하지 않고, 강제로 콤보로 조회된 데이터를 캐시에 저장하는 함수
    	 * 
    	 * @param opts 콤보설정옵션 정보
    	 * @param opts 캐시를 구분하기 위한 식별자
    	 */
    	putCacheData : function(opts, params) {
    		opts.onLoadSuccess = function(data, treedata) {
            	// 정상적으로 로딩된 데이터의 캐시로 저장함(2020.10.07)
            	var exsits = comboVo.isArrayObject(params);
            	
            	if(!exsits) {
            		var vo = new Object();
            		
            		vo.id = params; // 식별 ID
            		vo.data = data; // 캐시할 데이터
            		
            		comboVo.arrayPush(vo);
            	}
            }
    	}
    }
};
