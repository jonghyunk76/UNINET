var treeMethod = {
	id : null,
	init : {
		
		config : {},
    	
    	getConfig : function() {
        	if(dataset.isArrayObject(tree.id + ".init")) {
        		return dataset.arrayFilter(tree.id +".init");
        	} else {
        		return this.config;
        	}
        },
        
        /**
	     * tree를 구성할 json data
	     * 
	     * @param data = json data
	     */
		setData : function(data) {
			if (!oUtil.isNull(data)) {
	            this.getConfig().data = data;
	        } else {
	            $.messager.alert("ERROR", "setTreeData() parameter input error.");
	        }
	    },
	    
	    /**
	     * tree 라인표시여부 설정
	     * 
	     * @param boolean = true/false
	     */
		setLines : function(boolean) {
			if (oUtil.isBoolean(boolean)) {
	            this.getConfig().lines = boolean;
	        } else {
	            $.messager.alert("ERROR", "setLines() parameter input error.");
	        }
	    },
	    
	    /**
	     * 클릭시 호출되는 함수명 설정
	     * 
	     * @param string = 함수명
	     */
		setCallbackFuncion : function(string) {
			if (!oUtil.isNull(string)) {
	            this.getConfig().callBackFunction = string;
	        } else {
	            $.messager.alert("ERROR", "setcallBackFunction() parameter input error.");
	        }
	    },
	    
	    /**
	     * Accodian text 설정
	     * 
	     * @param string = Accodian text
	     */
		setAccodianText : function(string) {
			if (!oUtil.isNull(string)) {
	            this.getConfig().accodianText = string;
	        } else {
	            $.messager.alert("ERROR", "setAccodianText() parameter input error.");
	        }
	    },
	    
	    /**
	     * navigation 표시여부 설정
	     * 
	     * @param boolean = true/false
	     */
		setNavigation : function(boolean) {
			if (oUtil.isBoolean(boolean)) {
	            this.getConfig().navigation = boolean;
	        } else {
	            $.messager.alert("ERROR", "setNavigation() parameter input error.");
	        }
	    },
	    
	    /**
	     *  로딩 성공 시 open할 tree코드(단, accodianText가 null인 경우에만 적용됨)
	     * 
	     * @param string = tree코드
	     */
		setOpenCode : function(string) {
			if (!oUtil.isNull(string)) {
	            this.getConfig().openCode = string;
	        } else {
	            $.messager.alert("ERROR", "setOpenCode() parameter input error.");
	        }
	    }
	    
	},
	event : {
    	
		config : {},
		
		getConfig : function() {
        	if(dataset.isArrayObject(tree.id + ".event")) {
        		return dataset.arrayFilter(tree.id +".event");
        	} else {
        		return this.config;
        	}
        },
        
        /**
    	 * 트리 엘리먼트를 클릭 시 발생하는 이벤트 처리
    	 * 
    	 * @param obj = grid object
    	 */
        setOnClick : function(obj) {
            this.getConfig().onClick = function(node) {
            	var pid = tree.handle.getProgramID(obj);
            	var treeId = tree.handle.getGridID(obj);
            	var conf = tree.getInitConfig(treeId);
            	
            	var navigation = conf.navigation;
            	
            	if(!oUtil.isNull(node)) {
                    if(!oUtil.isNull(node.attributes)) {
                    	var funcPath = eval("window." + pid + ".event");
                    	
                        if(!oUtil.isNull(node.attributes.url)) {
                            if(navigation) {
                            	tree.util.getNavigation(treeId, node, accodianText);
                            }
                            
		                	if(!oUtil.isNull(funcPath)) {
		                		if(typeof(funcPath["onClick_"+treeId]) == "function") {
		                			funcPath["onClick_"+treeId](node.id, node.text, node.attributes, node.attributes);
		                		}
	                        }
                        } else {
		                	if(!oUtil.isNull(funcPath)) {
		                		if(typeof(funcPath["onClick_"+treeId]) == "function") {
		                			funcPath["onClick_"+treeId](node.id, node.text, node.attributes);
		                		}
	                        }
                        }
                    }
                }
            };
        },
        
        /**
    	 * 트리 엘리먼트를 클릭 시 발생하는 이벤트 처리
    	 * 
    	 * @param obj = grid object
    	 */
        setOnLoadSuccess : function(obj) {
        	this.getConfig().onLoadSuccess = function(node, data) {
        		var pid = tree.handle.getProgramID(obj);
            	var treeId = tree.handle.getGridID(obj);
            	var conf = tree.getInitConfig(treeId);
            	
            	var treeData = conf.data;
            	var callfunc = conf.callBackFunction;
            	var openCode = conf.openCode;
            	var accodianText = conf.accodianText;
            	
        		if(oUtil.isNull(accodianText)) {
                	var idx = 0;
                	
                    if (oUtil.isNull(openCode)) {
                        openCode = treeData[idx].id;
                    }
                    
                    tree.handle.setOpen(obj, openCode, null);
                    
                    if (!oUtil.isNull(callfunc)) {
                    	var funcPath = eval("window." + pid + ".event");
                    	
                    	if(!oUtil.isNull(funcPath)) {
	                		if(typeof(funcPath[callfunc]) == "function") {
	                			if(oUtil.isNull(openCode)) {
	                				funcPath[callfunc](treeData[idx].id, treeData[idx].text, treeData[idx].attributes);
	                        	} else {
	                        		funcPath[callfunc](openCode, "", treeData[idx].attributes);
	                        	}
	                		}
                        }
                    }
                }
        	};
        }
	},
	handle : {
    	
    	config : {},
    	
    	/**
         * grid Object에서 프로그램 코드를 검색하는 함수(ID의 앞7자리를 찾아 받환)
         * 
         * @parma obj = grid object
         * @return program ID
         */
        getProgramID : function(obj) {
            var pro_id = "";
            var tree_id;
            
            if (typeof obj == "string") {
                tree_id = obj;
            } else if (typeof obj == "object") {
            	try {
            		tree_id = obj.datagrid("options").id;
            	} catch(e) {
            		tree_id = obj.attr("id");
            	}
            }
            
            pro_id = tree_id.substr(0, PROGRAM_ID_NUMBER);
            
            return pro_id;
        },
        
        /**
         * grid object에서 gird id를 검색하는 함수
         * 
         * @parma obj = grid object
         * @return grid ID
         */
        getGridID : function(obj) {
            var pro_id = "";
            var tree_id;
            
            if (typeof obj == "string") {
                tree_id = obj;
            } else if (typeof obj == "object") {
            	try {
            		tree_id = obj.datagrid("options").id;
            	} catch(e) {
            		tree_id = obj.attr("id");
            	}
            }
            
            return tree_id;
        },
        
        /**
         * 특정 id에 해당하는 tree menu를 open 한다.
         * 
         * @param obj = tree object
         * @param targetId = 특정 field id
         * @param accodianText = accodian text
         */
        setOpen : function(obj, targetId, accodianText) {
            var node = obj.tree('find', targetId);
            
            if (node != null) {
            	obj.tree('expandTo', node.target).tree('select', node.target);
                
                if (accodianText != null && accodianText != "undefined") {
                	tree.util.getNavigation(treeId, node, accodianText);
                }
                
                return true;
            } else {
                return false;
            }
        },
        
        /**
         * 선택된 menu의 field id를 반환한다.
         * 
         * @param obj = tree object
         * @param field = 선택 field id
         */
        getFieldId : function(obj, field) {
            var node = obj.tree('getSelected');
            
            if (node) {
                var s = eval("node." + field);
                if (oUtil.isNull(s) && !oUtil.isNull(node.attributes)) {
                    s = eval("node.attributes." + field);
                }
                return s;
            }
        },
        
        /**
         * tree menu를 모두 닫는다.
         * 
         * @param obj = tree object
         */
        allClose : function(obj) {
        	obj.tree('collapseAll');
        },
        
        /**
         * tree menu를 모두 open 한다.
         * @param obj = tree object
         */
        allOpen : function(obj) {
        	obj.tree('expandAll');
        }
	},
	util : {
		
		/**
		 * 페이지 네비게이션 표시
		 * @param treeId = tree id
		 * @param node = tree node
		 * @param accodianText = accodian text
		 * @returns
		 */
		getNavigation : function(treeId, node, accodianText) {
		    var parent;
		    var home = "<a href=\"#\" onclick=\"$('#tt').tabs('select', 'Main')\">Main</a>";
		    var menuTitle;
		    var menuId;
		    var navi = "";
		    
		    if (!oUtil.isNull(treeId)) {
		        parent = $('#' + treeId).tree('getParent', node.target);
		    }
		    
		    if (accodianText != null && accodianText != "undefined") {
		        navi += " > " + accodianText;
		    }
		    
		    if (!oUtil.isNull(node)) {
		        menuTitle = node.text;
		        menuId = node.id;
		    }
		    
		    if (!oUtil.isNull(parent) && !oUtil.isNull(menuTitle)) {
		        navi += " > " + parent.text + " > " + menuTitle;
		    } else if (!oUtil.isNull(menuTitle)) {
		        navi += " > " + menuTitle;
		    }
		    
		    if (!oUtil.isNull(navi)) {
		        var child = $("#navi").contents().text(this);
		        if (child != ("main" + navi)) {
		            $("#navi").html(home + navi);
		        }
		    } else {
		        $("#navi").html(home + navi);
		    }
		}
	}
};