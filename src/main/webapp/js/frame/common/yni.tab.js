var tabMethod =  {
    id : "",
    init : {
    	config : {},
    	
        getConfig : function() {
        	if(dataset.isArrayObject(tab.id + ".init")) {
        		return dataset.arrayFilter(tab.id +".init");
        	} else {
        		return this.config;
        	}
        },
        
        // tab container properties
        
        /**
         * tab container 넓이 설정
         * 
         * @param number = 숫자(default=auto)
         */
        setWidth : function(number) {
        	this.getConfig().width = number;
        },
        
        /**
         * tab container 높이 설정
         * 
         * @param number = 숫자(default=auto)
         */
        setHeight : function(number) {
        	this.getConfig().height = number;
        },
        
        /**
         * tab container 채우기 여부
         * 
         * @param boolean = true|false(default=true)
         */
        setFit : function(boolean) {
        	this.getConfig().fit = boolean;
        },
        
        /**
         * tab container 외각선 표시여부
         * 
         * @param boolean = true|false(default=false)
         */
        setBorder : function(boolean) {
        	this.getConfig().border = boolean;
        },
        
        /**
         * tab container내 Tool bar 설정
         * 
         * @param json = json타입의 툴바정보 또는 Object ID
         */
        setTools : function(json) {
        	this.getConfig().tools = json;
        },
        
        /**
         * tab container내의 Tool bar 위치 설정
         * 
         * @param string = left|right(default=right)
         */
        setToolPosition : function(string) {
        	this.getConfig().toolPosition = string;
        },
        
        /**
         * tab container 위치 설정
         * 
         * @param string = top|bottom|left|right(default=top)
         */
        setTabPosition : function(string) {
        	this.getConfig().tabPosition = string;
        },
        
        /**
         * tab container 해더의 넓이 설정(tab 위치가 left 또는 right일 경우에만 설정가능)
         * 
         * @param number = 숫자(default=150)
         */
        setHeaderWidth : function(number) {
        	this.getConfig().headerWidth = number;
        },
        
        /**
         * 초기에 선택할 tab container 설정
         * 
         * @param number = 숫자(default=0)
         */
        setSelectedIndex : function(number) {
        	this.getConfig().selected = number;
        },
        
        /**
         * tab container 해더 표시여부 설정
         * 
         * @param boolean = true|false(default=true)
         */
        setShowHeader : function(boolean) {
        	this.getConfig().showHeader = boolean;
        },
        
        /**
         * tab container의 배경을 지울지 여부 설정
         * 
         * @param boolean = true|false(default=true)
         */
        setPlain : function(boolean) {
        	this.getConfig().plain = boolean;
        }
        
    },
    event : {
        config : {},
        
        getConfig : function() {
        	if(dataset.isArrayObject(tab.id + ".event")) {
        		return dataset.arrayFilter(tab.id +".event");
        	} else {
        		return this.config;
        	}
        },
        
        /**
         * tab내 데이터가 읽은 후에 호출되는 함수
         * 
         * @param obj = tab object
         */
        setOnLoad : function(obj) {
        	this.getConfig().onLoad = function(panel) {
        	};
        },
        
        /**
         * tab panel이 선택될 때 호출되는 함수
         * 
         * @param obj = tab object
         */
        setOnSelect : function(obj) {
        	this.getConfig().onSelect = function(title,index) {
//        		console.log("selected tab > title = " + title + ", index = " + index);
        	};
        },
        
        /**
         * tab panel이 선택되지 않았을 때 호출되는 함수
         * 
         * @param obj = tab object
         */
        setOnUnselect : function(obj) {
        	this.getConfig().onUnselect = function(title,index) {
        	};
        },
        
        /**
         * tab panel을 닫히기 전에 호출되는 함수
         * 
         * @param obj = tab object
         */
        setOnBeforeClose : function(obj) {
        	this.getConfig().onBeforeClose = function(title,index) {
        	};
        },
        
        /**
         * tab panel을 닫은 후에 호출되는 함수
         * 
         * @param obj = tab object
         */
        setOnClose : function(obj) {
        	this.getConfig().onClose = function(title,index) {
        	};
        },
        
        /**
         * tab panel이 추가될 때 호출되는 함수
         * 
         * @param obj = tab object
         */
        setOnAdd : function(obj) {
        	this.getConfig().onAdd = function(title,index) {
        		//alert("add tab > title = " + title + ", index = " + index);
        	};
        },
        
        /**
         * tab panel이 수정될 때 호출되는 함수
         * 
         * @param obj = tab object
         */
        setOnUpdate : function(obj) {
        	this.getConfig().onUpdate = function(title,index) {
        		//alert("update tab > title = " + title + ", index = " + index);
        	};
        },
        
        /**
         * tab panel에서 오른쪽를 클릭할 때 호출되는 함수
         * 
         * @param obj = tab object
         */
        setOnContextMenu : function(obj) {
        	this.getConfig().onContextMenu = function(e, title, index) {
        		//alert("right click....."  + title);
        	};
        }
        
    },
    handle : {
    	
    	/**
         * 모든 tab을 리턴함
         * 
         * @param obj = tab Object Id
         */
    	getTabs : function(obj) {
    		return obj.tabs("tabs");
    	},
    	
    	/**
         * 모든 tab을 리턴함
         * 
         * @param obj = tab Object Id
         * @param index = 상세tab의 index 또는 명칭
         */
    	getTab : function(obj, index) {
    		return obj.tabs("getTab", index);
    	},
    	
    	/**
         * tab 컨테이너 크기를 Layout에 맞게 조정함
         * 
         * @param obj = tab Object Id
         */
    	resize : function(obj) {
    		obj.each(function() {
    			if($(this).is(':visible')) {
    				$(this).tabs('resize',{})
    			}
    		});
    	},
    	
    	/**
         * tab panel을 추가함<br>
         * tab panel properties 내용<br>
         * prop = {
			 id : string(tab panel의 ID)
			,title : string(tab panel의 명칭)	
			,content : string(tab panel의 내용).	
			,href : string(tab panel에 표시할 화면의 URL)
			,queryParams : json(URL호출 시 적용할 파라메터)
			,cache : boolean(URL 설정이 있는 경우 해당 페이지를 캐시하지 여부, default=true)
			,iconCls : string(명칭을 표시할 때 적용할 CSS의 클래스명)
			,width : number(tab panel의 넓이, default=auto)
			,height : number(tab panel의 높이, default=auto)
			,collapsible : boolean(tab panel을 접히게 할 지 여부 설정, default=false)
			,closable : boolean(닫기 버튼을 보일지 여부, default=false)
			,selected : boolean(tab panel의 선택여부, default=false)
			,disabled : boolean(tab panel의 비활성화, default=false)
			}
         * @param obj = tab Object Id
         * @param prop = tab panel 속성(json 타입)
         */
    	addTab : function(obj, prop, index) {
    		if(oUtil.isNull(prop) || typeof obj != "object") {
    			//console.debug("추가할 tab 생성정보를 찾을 수 없습니다.");
    			return;
    		}
    		
    		if(oUtil.isNull(index)) {
    			//console.debug("tab index에는 null을 적용할 수 없습니다.");
    			return;
    		}
    		
    		if(this.exists(obj, index)) {
    			obj.tabs("select", index);
    		} else {
	    		obj.tabs('add', prop);
    		}
    		
    		var maxNum = 15; // 기본값
    		try {
    			maxNum = TAB_MAX_SIZE;
    		} catch(e) {
    			;
    		}
    		
    		if(this.getTabIndex(obj) > maxNum) {
    			this.close(obj, 1);
    		}
    		
    		return;
    	},
    	
    	/**
         * tab panel을 닫음
         * 
         * @param obj = tab Object Id
         * @param index = 상세tab의 index 또는 명칭
         */
    	close : function(obj, index) {
    		if(this.exists(obj, index)) {
    			obj.tabs("close", index);
    		} else {
    			return;
    		}
    	},
    	
    	/**
         * tab panel의 인덱스를 구함
         * 
         * @param obj = tab Object Id
         * @param index = 상세tab 객체
         */
    	getTabIndex : function(obj, tab) {
    		if(oUtil.isNull(tab)) {
    			tab = this.getSelected(obj);
    		}
    		return obj.tabs("getTabIndex", tab);
    	},
    	
    	/**
         * 선택된 tab panel 객체를 구합니다.
         * 
         * @param obj = tab Object Id
         */
    	getSelected : function(obj) {
    		return obj.tabs("getSelected");
    	},
    	
    	/**
         * 선택된 tab panel 객체의 index를 구합니다.
         * 
         * @param obj = tab Object Id
         */
    	getSelectedIndex : function(obj) {
    		var tabObj = obj.tabs("getSelected");
    		
    		return this.getTabIndex(obj, tabObj);
    	},
    	
    	/**
         * tab panel을 선택함
         * 
         * @param obj = tab Object Id
         * @param index = 상세tab의 index 또는 명칭
         */
    	select : function(obj, index) {
    		if(this.exists(obj, index)) {
    			obj.tabs("select", index);
    		} else {
    			return;
    		}
    	},
    	
    	/**
         * tab panel을 선택을 해제함
         * 
         * @param obj = tab Object Id
         * @param index = 상세tab의 index 또는 명칭
         */
    	unselect : function(obj, index) {
    		obj.tabs("unselect", index);
    	},
    	
    	/**
         * tab 해더를 숨기거나 보이도록 함
         * 
         * @param obj = tab Object Id
         * @param flag = true:보임, false:숨김
         */
    	setShowHeader : function(obj, flag) {
    		if(flag) {
    			obj.tabs("showHeader");
    		} else {
    			obj.tabs("hideHeader");
    		}
    	},
    	
    	/**
         * tab에 tool바가 설정되어 있는 경우 숨기거나 보이도록 함
         * 
         * @param obj = tab Object Id
         * @param flag = true:보임, false:숨김
         */
    	setShowTool : function(obj, flag) {
    		if(flag) {
    			obj.tabs("showTool");
    		} else {
    			obj.tabs("hideTool");
    		}
    	},
    	
    	/**
         * tab panel의 인스턴스가 있는지 여부를 리턴함
         * 
         * @param obj = tab Object Id
         * @param index = 상세tab의 index 또는 명칭
         */
    	exists : function(obj, index) {
    		try {    			
    			return obj.tabs("exists", index);
    		} catch(e) {
    			console.log("exists() : " + e);
    			return false;
    		}
    	},
    	
        /**
         * tab panel의 인스턴스가 있는지 여부를 리턴함
         * 
         * @param obj = tab Object Id
         * @param index = 상세tab의 index 또는 명칭
         */
        existsForId : function(obj, menuId) {
            var size = tab.handle.getLength(obj);
            var exist = false;
            
            for(var i = 0; i < size; i++) {
                var tid = this.getTab(obj, i).panel('options').id;
                
                if(tid == menuId) {
                    exist = true;
                    break;
                }
            }
            
            return exist;
        },
        
    	/**
         * tab panel 페이지를 갱신함
         * 
         * @param obj = tab Object Id
         * @param url = 이동할 페이지 경로
         */
    	reload : function(obj, url) {
    		var tab = obj.tabs('getSelected');
    		tab.panel('refresh', url);
    	},
    	
    	/**
         * tab panel을 활성하거나 비활성화 시킴
         * 
         * @param obj = tab Object Id
         * @param flag = true:보임, false:숨김
         */
    	setEnableTab : function(obj, flag) {
    		if(flag) {
    			obj.tabs("enableTab");
    		} else {
    			obj.tabs("disableTab");
    		}
    	},
    	
    	/**
         * 지정된 숫자만큼 스크롤 함
         * 
         * @param obj = tab Object Id
         * @param number = 음수이면 오른쪽으로 스크롤, 양수인 경우 왼쪽으로 스크롤됨
         */
    	setScrollBy : function(obj, number) {
    		obj.tabs("scrollBy", number);
    	},
    	
    	/**
         * 생성된 tab갯수를 리턴함
         * 
         * @param obj = tab Object Id
         * @param index = 상세tab의 index 또는 명칭
         */
    	getLength : function(obj) {
    		return obj.tabs("tabs").length;
    	}
    	
    }
    
};
