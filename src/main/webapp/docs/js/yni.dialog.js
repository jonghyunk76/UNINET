var dialogMethod = function() {
	init = {
		
		/**
		 * dialog창의 제목 설정
		 * 
		 * @param string = 제목
		 */
		setTitle : function(string) {
			this.getConfig().title = string;
		},
		
		/**
		 * dialog창의 넓이 설정
		 * 
		 * @param number = 넓이값
		 */
		setWidth : function(number) {
			this.getConfig().width = number;
		},
		
		/**
		 * dialog창의 높이 설정
		 * 
		 * @param number = 높이값
		 */
		setHeight : function(height) {
			this.getConfig().height = height;
		},
		
		/**
		 * dialog 호출 URL 설정
		 * 
		 * @param string = URL 경로
		 */
		setURL : function(string) {
			this.getConfig().href = string;
		},
		
		/**
		 * dialog창 호출 시 넘겨줄 파라메터 설정
		 * 
		 * @param params = 파라메터:json
		 */
		setQueryParams : function(string) {
			this.getConfig().queryParams = string;
		},
		
		/**
		 * dialog창 호출 방식 설정
		 * 
		 * @param params = post, get
		 */
		setMethod : function(string) {
			this.getConfig().method = string;
		},
		
		/**
		 * dialog창 조절가능여부 설정
		 * 
		 * @param boolean = 조절가능:true, 고정:false
		 */
		setResizable : function(boolean) {
			this.getConfig().resizable = boolean;
		},
		
		/**
		 * dialog창 닫기기능 설정(true:default)
		 * 
		 * @param boolean = 닫기가능:true, 닫기금지:false
		 */
		setClosable : function(boolean) {
			this.getConfig().closable = boolean;
		},
		
		/**
		 * dialog창 Modal 설정
		 * 
		 * @param boolean = modal:true, modalless:false
		 */
		setModal : function(boolean) {
			this.getConfig().modal = boolean;
		},
		
		/**
		 * dialog가 Load될 때 호출될 함수 설정
		 * 
		 * @param string = 함수명
		 */
		setCallLoadFunc : function(string) {
			this.getConfig().callLoadFunc = string;
		},
		
		/**
		 * dialog가 Load될 때 호출될 함수 설정
		 * 
		 * @param string = 함수명
		 */
		setCallCloseFunc : function(string) {
			this.getConfig().callCloseFunc = string;
		}
		
	},
	event = {
		
		setOnLoad : function(obj) {
	        this.getConfig().onLoad = function() {
	        	var did = (typeof obj == "object") ? obj.attr("id") : obj;
	        	var conf = dialog.getInitConfig(did);
	            
	        	var func = conf.callLoadFunc;
	        	
	        	if (!oUtil.isNull(func)) {
	        		var funcPath = eval("window." + did + ".event");
	        		
            		if (typeof(funcPath[func]) == "function") {
                        funcPath[func]();
            		}
                }
	        }
		},
		
		setOnClose : function(obj) {
	        this.getConfig().onClose = function() {
	        	var did = (typeof obj == "object") ? obj.attr("id") : obj;
	        	var conf = dialog.getInitConfig(did);
	            
	        	var func = conf.callCloseFunc;
	        	
	        	if (!oUtil.isNull(func)) {
	        		var funcPath = eval("window." + did + ".event");
	        		
            		if (typeof(funcPath[func]) == "function") {
                        funcPath[func]();
            		}
                }
	        }
		},
		
		setOnMove : function(obj, width, height) {
	        this.getConfig().onMove = function(left, top) {
	        	var lyH = $(window).height(); 
	        	var lyW = $(window).width();
	        	var ly = obj.attr("alt");
	        	var barW = $('.'+ly).width();
	        	var barH = $('.'+ly).height();
	        	
	        	if (height > (lyH - barH)) height = (lyH - barH) * 0.97;
		        if (width > (lyW - barW)) width = (lyW - barW) * 0.97;
		        
	        	var leftP = (lyW/2)- (barW/2) - (width/2); // 가로 중앙에 위치
	        	var topP = ($(window).scrollTop() + (lyH/2)) - (barH/2) - (height/2); // top 중앙에 위치
	    		
	        	if(left < 0 || top < 0 || (lyH-60) <= top || (lyW-60) <= left) {
	        		obj.dialog("move", {left:leftP, top:topP});
	        	}
	        }
		}
		
	},
	
	handle = {
		close : function(obj) {
		    obj.dialog('close');
		}
	}
};
