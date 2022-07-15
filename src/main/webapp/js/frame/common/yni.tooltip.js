var tooltipMethod = {
	id : null,
	init : {
    	
		config : {},
    	
    	getConfig : function() {
        	if(dataset.isArrayObject(tooltip.id + ".init")) {
        		return dataset.arrayFilter(tooltip.id +".init");
        	} else {
        		return this.config;
        	}
        },
        
        /**
		 * Tool Tip 표시할 이벤트 설정
		 * 
		 * @param string = 이벤트명(click... 등)
		 */
        setShowEvent : function(string) {
	        this.getConfig().showEvent = string;
	    },
	    
	    /**
		 * Tool Tip 숨길때 이벤트 설정
		 * 
		 * @param string = 이벤트명(click..., none 등)
		 */
        setHideEvent : function(string) {
	        this.getConfig().hideEvent = string;
	    },
	    
	    /**
		 * Tool Tip의 넓이 설정
		 * 
		 * @param integer = 넓이
		 */
	    setWidth : function(integer) {
	        this.getConfig().width = integer;
	    },
        
	    /**
		 * Tool Tip의 여백라인 표시여부 설정
		 * 
		 * @param boolean = 여백라인 표시여부
		 */
	    setBorder : function(boolean) {
	        this.getConfig().border = boolean;
	    },
	    
	    /**
		 * Tool Tip의 제목 설정
		 * 
		 * @param string = 제목
		 */
	    setTitle : function(string) {
	        this.getConfig().title = string;
	    },
	    
	    /**
		 * Tool Tip에 표시할 페이지 경로 설정
		 * 
		 * @param string = URL
		 */
	    setURL : function(string) {
	        this.getConfig().url = string;
	    },
	    
	    /**
	     * 화면의 프로그램 ID
	     * 
	     * @param string = 프로그램 ID
	     */
	    setPID : function(string) {
	    	this.getConfig().pid = string;
	    },
	    
	    /**
	     * div 내용을 채울 경우 지정된 id 
	     * 
	     * @param string = div ID
	     */
	    setContentID : function(string) {
	    	this.getConfig().contentID = string;
	    },
	    
	    /**
		 * Tool Tip이 보일때 호출할 이벤트 설정 
		 * 
		 * @param showCallbackFunction = 호출할 함수명
		 */
        setShowCallbackFunction : function(string) {
	        this.getConfig().showCallbackFunction = string;
	    }
	},
	event : {
    	
		config : {},
		
		getConfig : function() {
        	if(dataset.isArrayObject(tooltip.id + ".event")) {
        		return dataset.arrayFilter(tooltip.id +".event");
        	} else {
        		return this.config;
        	}
        },
        onUpdate : function(conf){
        	this.getConfig().onUpdate = function(content) {
        		var width = conf.width;
        		var border = conf.border;
        		var title = conf.title;
        		var url = conf.url;
        		
        		content.panel({
	        		width: width
	        	   ,border: border
	        	   ,title: title
	        	   ,href: url
	        	});	
        	};
        },
        onContent: function(conf){
        	this.getConfig().content = function() {
        		var id = conf.contentID;
        		
        		return $('#'+id);
        	}
        },
        onShow : function(obj, pid){
        	this.getConfig().onShow = function() {
        		var tid = (typeof obj == "object") ? obj.attr("id") : obj;
            	var initConf = tooltip.getInitConfig(tid);
            	var pid = grid.handle.getProgramID(tid);
            	
            	if(oUtil.isNull(tid) || tid == "MMA001_01_tooltip_1") {
	        		obj.tooltip('tip').unbind().bind('mouseenter', function(){obj.tooltip('show');});
	        		$("#"+initConf.pid+"_cls_btn").click(function () {obj.tooltip('hide');});
	        		$("#"+initConf.pid+"_cfm_btn").click(function () {obj.tooltip('hide');});
            	} else {
            		obj.tooltip('tip').focus().unbind().bind('blur',function(){
            			obj.tooltip('hide');
                    });
            		//obj.tooltip('tip').unbind().bind('mouseenter', function(){obj.tooltip('show');}).bind('mouseleave', function(){obj.tooltip('hide');});
            	}
            	
            	// 조회된 데이터가 없어도 callbackFuntion을 호출한다.
                var callback = initConf.showCallbackFunction;
                
                if (!oUtil.isNull(callback)) {
                    var pro_id = eval("window." + pid + ".event");
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id[callback]) == "function") {
                            pro_id[callback]();
                        }
                    }
                }
        	}
        }

	},
	handle : {
		close : function(tid) {
			$('#'+tid).tooltip('hide');
		}
	},
	util : {
		removeExtraTabs : function(s) {
        	if(oUtil.isNull(s)) return "";
            return s.replace(new RegExp("\t\t", 'g'), "\t");
        },
        
        generateTable : function(fieldId, gridID, formId, mcnt) {
			var data = null;
			if(oUtil.isNull(formId)) {
				data = this.removeExtraTabs($('#'+fieldId).val());
			} else {
				data = form.handle.getValue(formId, fieldId);
			}
			var rows = data.split("\n");
			var aryData = new Array();
			var dg_1 = grid.getObject(gridID);
			var gridYn = true;
			var rowDt = null;
			
			if(!dg_1.datagrid("getPanel")) {
				gridYn = false;
			} else {
				grid.handle.clearAll(dg_1);
			}
			
			var nm = 1;
			var maxCnt;
			
			if(oUtil.isNull(mcnt)) {
				try{
					maxCnt = MAX_MULTI_COUNT;
				} catch(e) {
					maxCnt = 100;
				}
			} else {
				maxCnt = mcnt;
			}
			
			for (var y in rows) {
			    rows[y] = this.removeExtraTabs(rows[y]);
			    
			    var cells = rows[y].split("\t");
				var data = new Object();
				
			    if(!oUtil.isNull(cells[0])) {
			    	data.KEY_WORD = cells[0];
			    	data.KEY_NUMBER = nm;
			    	
			    	if(gridYn) {
			    		grid.handle.appendRow(dg_1, data);
				    } else {
				    	aryData.push(data);
				    }
			    	
			    	nm++;
			    	
			    	if(nm > maxCnt) break;
			    }
			}
			
			if(!gridYn) {
				grid.util.drawSearchGrid(gridID, aryData);
        	}
        },
        
        clearAll : function(fieldId, gridID, formId) {
        	if(oUtil.isNull) $('#'+fieldId).val('');
        	if(oUtil.isNull) form.handle.setValue(formId, fieldId, '');
        	
        	var dg_1 = grid.getObject(gridID);
        	grid.handle.clearAll(dg_1);
        }
        
	},
};