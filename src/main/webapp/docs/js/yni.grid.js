var gridMethod = function() {
		init = {
	        
		    /**
		     * grid 타이틀 설정(지정시 그리드 상위에 표시됨)
		     * 
		     * @param string = grid 타이틀
		     */
			setTitle : function(title) {
		        if (!oUtil.isEmpty(title)) {
		            this.getConfig().title = title;
		            this.getConfig().iconCls = "icon-sub-title";
		        } else {
		            $.messager.alert("ERROR", "setTitle() parameter input error.");
		        }
		    },
		    /**
		     * 해더 표시여부 설정(default = true)
		     *  
		     * @param boolean = 표시:true, 미표시:false
		     */
		    setShowHeader : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().showHeader = boolean;
		        } else {
		            $.messager.alert("ERROR", "setShowHeader() parameter input error.");
		        }
		    },
		    /**
		     * 조회된 컬럼의 열 넓이을 자동으로 조절(수평 스크룰 제거시 적용)(default = false)
		     * 
		     * @param boolean = 자동저절:true, 적용안함:false
		     */
		    setFitColumns : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().fitColumns = boolean;
		            this.getConfig().fit = true;
		        } else {
		            $.messager.alert("ERROR", "setFitColumns() parameter input error.");
		        }
		    },
		    /**
		     * 자동으로 열 넓이에 맞게 cell 크기를 조정되며, 수평 스크룰 제거시 적용 사용(default = false)
		     * 
		     * @param boolean = 자동저절:true, 적용안함:false
		     */
		    setFit : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().fit = boolean;
		        } else {
		            $.messager.alert("ERROR", "setFitColumns() parameter input error.");
		        }
		    },
		    /**
		     * 그리드 첫컬럼에 번호를 붙일지 여부를 설정(default = false)
		     * 
		     * @param boolean = 표시:true, 미표시:false
		     */
		    setRownumbers : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().rownumbers = boolean;
		        } else {
		            $.messager.alert("ERROR", "setRownumbers() parameter input error.");
		        }
		    },
		    /**
		     * 단일 선택여부 설정(default = false)
		     * 
		     * @param boolean = 단일선택:true, 멀티선택:false
		     */
		    setSingleSelect : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().singleSelect = boolean;
		        } else {
		            $.messager.alert("ERROR", "setSingleSelect() parameter input error.");
		        }
		    },
		    /**
		     * 정렬방식 설정(default = asc)
		     * 
		     * @param flag = asc 또는 desc
		     */
		    setSortOrder : function(flag) {
		        if (!oUtil.isEmpty(flag)) {
		            this.getConfig().singleSelect = flag;
		        } else {
		            $.messager.alert("ERROR", "setSortOrder() parameter input error.");
		        }
		    },
		    /**
		     * 정렬할 필드ID 설정
		     * 
		     * @param string : 필드ID
		     */
		    setSortName : function(string) {
		        if (!oUtil.isEmpty(string)) {
		            this.getConfig().sortName = string;
		        } else {
		            $.messager.alert("ERROR", "setSortName() parameter input error.");
		        }
		    },
		    /**
		     * 외부 정렬 수행여부 설정
		     * 
		     * @param boolean = 정렬:true, 정렬안함:false
		     */
		    setRemoteSort : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().remoteSort = boolean;
		        } else {
		            $.messager.alert("ERROR", "setRemoteSort() parameter input error.(true or false)");
		        }
		    },
		    /**
		     * 요청 URL 경로 설정(#으로 입력하면 URL을 설정하지 않음)
		     * 
		     * @param string = 경로
		     */
		    setURL : function(string) {
		        if (!oUtil.isEmpty(string)) {
		            if(string == "#") {
		                this.getConfig().url = null;
		            } else {
		                this.getConfig().url = string;
		            }
		        } else {
		            $.messager.alert("ERROR", "setURL() parameter input error(# or url).");
		        }
		    },
		    /**
		     * 데이터 그리드 초기화시 화면에 보여줄 data 설정
		     * 
		     * @param json = josn타입의 string
		     */
		    setData : function(json) {
		        var totalCnt = 0;
		        if(oUtil.isNull(json)) {
		        	json = [{"empty":MSG_NO_SEARCH_RESULT}];
		        } else {
		            totalCnt = json.length;
		        }
		        var rowData = {"total":totalCnt, "rows":json};
		        
		        this.getConfig().data = rowData;
		    },
		    /**
		     * 서버요청 방식 설정(default = post)
		     * 
		     * @param flag = post or get 
		     */
		    setMethod : function(flag) {
		        if (!oUtil.isEmpty(flag)) {
		            this.getConfig().method = flag;
		        } else {
		            $.messager.alert("ERROR", "setMethod() parameter input error.");
		        }
		    },
		    /**
		     * 서버요청 쿼리 parameter 설정
		     * 
		     * @param string = 파라메터
		     */
		    setQueryParams : function(string) {
		        if (oUtil.isObject(string)) {
		            this.getConfig().queryParams = string;
		        } else {
		            $.messager.alert("ERROR", "setQueryParams() parameter input error.");
		        }
		    },
		    /**
		     * 한 라인에 데이터를 표시할지 여부 설정. 단, true로 지정해야 성능이 향상됨(default = true)
		     * 
		     * @param boolean = 표시:true, 미표시:false
		     */
		    setNowrap : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().nowrap = boolean;
		        } else {
		            $.messager.alert("ERROR", "setNowrap() parameter input error.");
		        }
		    },
		    /**
		     * 열 높이를 cell에 채워진 글짜 높이만큼 자동할지 여부를 설정(default = true)
		     * 
		     * @param boolean = 자동조절:true, 조절안함:false
		     */
		    setAutoRowHeight : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().autoRowHeight = boolean;
		        } else {
		            $.messager.alert("ERROR", "setAutoRowHeight() parameter input error.");
		        }
		    },
		    /**
		     * 서버요청 시 표시될 메시지 설정
		     * 
		     * @param string = 메시지
		     */
		    setLoadMsg : function(string) {
		        if (!oUtil.isEmpty(string)) {
		            this.getConfig().loadMsg = string;
		        } else {
		            $.messager.alert("ERROR", "setLoadMsg() parameter input error.");
		        }
		    },
		    /**
		     * 페이징 도구 모음 표시여부 설정(default = false)
		     * 
		     * @param boolean = 표시:true, 숨김:false
		     */
		    setPage : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().pagination = boolean;
		        } else {
		            $.messager.alert("ERROR", "setPagination() parameter input error.");
		        }
		    },
		    /**
		     * 페이징 도구 모음의 표시 위치 설정(default = bottom)
		     * 
		     * @param position = top, bottom, both
		     */
		    setPagePosition : function(position) {
		        if (!oUtil.isEmpty(position)) {
		            this.getConfig().pagePosition = position;
		        } else {
		            $.messager.alert("ERROR", "setPagePosition() parameter input error.");
		        }
		    },
		    /**
		     * 컬럼 넓이 조정여부 설정
		     * 
		     * @param boolean = 조정:true, 고정:false 
		     */
		    setResizable : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().resizable = boolean;
		        } else {
		            $.messager.alert("ERROR", "setResizable() parameter input error.");
		        }
		    },
		    /**
		     * 페이징 지정시 초기 페이지 번호 설정(default = 1)
		     * 
		     * @param number = 초기 페이지에 보여질 페이지 번호
		     */
		    setPageNumber : function(number) {
		        if (oUtil.isNumber(number)) {
		            this.getConfig().pageNumber = number;
		        } else {
		            $.messager.alert("ERROR", "setPageNumber() parameter input error.");
		        }
		    },
		    /**
		     * 페이징 지정시 초기 페이지 크기 설정(default = 10)
		     * 
		     * @param number = page크기
		     */
		    setPageSize : function(number) {
		        if (oUtil.isNumber(number)) {
		            this.getConfig().ageSize = number;
		        } else {
		            $.messager.alert("ERROR", "setPageSize() parameter input error.");
		        }
		    },
		    /**
		     * 페이징 지정시 페이지에 표시할 row 갯수 설정(default = [100, 200, 300])
		     * 
		     * @param array = 배열로 지정
		     */
		    setPageList : function(array) {
		        if (oUtil.isArray(array)) {
		            this.getConfig().pageList = array;
		        } else {
		            $.messager.alert("ERROR", "setPageList() parameter input error.");
		        }
		    },
		    /**
		     * 페이징 지정시 페이지 표시여부 지정 설정
		     * 
		     * @param boolean = 표시:true, 숨김:false
		     */
		    setShowPageList : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().showPageList = boolean;
		        } else {
		            $.messager.alert("ERROR", "setShowPageList() parameter input error.(true or false)");
		        }
		    },
		    /**
		     * 그리드 header 정보 셋팅 설정
		     * 
		     * @param json = json타입의 배열
		     */
		    setColumns : function(json) {
		        if (!oUtil.isEmpty(json)) {
		            this.getConfig().columns = json;
		        } else {
		            $.messager.alert("ERROR", "setColumns() parameter input error.");
		        }
		    },
		    /**
		     * 그리드 row의 style 설정
		     * 
		     * @param string = 열에 적용될 style
		     */
		    setRowStyler : function(string) {
		        if (!oUtil.isEmpty(string)) {
		            this.getConfig().rowStyler = function(index, row) {
		                return string;
		            };
		        } else {
		            $.messager.alert("ERROR", "setRowStyler() parameter input error.");
		        }
		    },
		    /**
		     * 
		     * 그리드 toolbar로 지정할 엘리먼트 설정 
		     * 
		     * @param element = toolbar로 지정할 element ID
		     */
		    setToolbar : function(element) {
		        this.getConfig().toolbar = "#" + element;
		    },
		    
		    /**
		     * 그리드 row 선택시 체크여부 설정
		     * 
		     * @param boolean = row 클릭시 체크:true, check box 클릭 시 에만 체크:false
		     */
		    setCheckOnSelect : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().checkOnSelect = boolean;
		        } else {
		            $.messager.alert("ERROR", "setCheckOnSelect() parameter input error.(true or false)");
		        }
		    },
		    /**
		     * check 박스 체크시 열 선택여부 설정
		     * 
		     *  @param boolean = 체크 시 row 선택:true, 체크해도 row가 선택되지 않음 : false
		     */
		    setSelectOnCheck : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().selectOnCheck = boolean;
		        } else {
		            $.messager.alert("ERROR", "selectOnCheck() parameter input error.(true or false)");
		        }
		    },
		    /**
		     * 데이터 그리드를 에디터 모드로 사용할지 여부 설정<br>
		     * (default = false)
		     * 
		     * @param boolean = true:표시, false:숨김
		     */
		    setEditMode : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().setEditMode = boolean;
		        } else {
		            $.messager.alert("ERROR", "setEditorMode() parameter input error.(true or false)");
		        }
		    },
		    /**
		     * 그리드 조회결과 데이터가 없는 경우 메시지를 표시할지 여부 설정<br>
		     * (default = true)
		     * 
		     * @param boolean = true:표시, false:숨김
		     */
		    setEmptyMessage : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().onEmptyMessage = boolean;
		        } else {
		            $.messager.alert("ERROR", "onEmptyMessage() parameter input error.(true or false)");
		        }
		    },
		    /**
		     * 요청이 성공적으로 완료된 후 호출될 함수 설정.<br>
		     * 참고로, 조회된 데이터가 없는 경우에는 호출되지 않으므로 setCallBackFunctionForNoData을 사용해야 함
		     * 
		     * @param string = 호출될 함수명
		     */
		    setCallBackFunction : function(string) {
		        if (!oUtil.isEmpty(string)) {
		            this.getConfig().callBackFunction = string;
		        } else {
		            $.messager.alert("ERROR", "setCallBackFunction() parameter input error.");
		        }
		    },
		    
		    /**
		     * 조회된 데이터가 없는 경우 호출될 함수 설정
		     * 
		     * @param string = 호출될 함수명
		     */
		    setCallBackFunctionForNoData : function(string) {
		        if (!oUtil.isEmpty(string)) {
		            this.getConfig().callBackFunctionForNoData = string;
		        } else {
		            $.messager.alert("ERROR", "setCallBackFunctionForNoData() parameter input error.");
		        }
		    },
		    
		    
		    /**
		     * 서버 요청전에 호출될 함수 설정
		     * 
		     * @param string =호출될 함수명
		     */
		    setBeforeLoadFunction : function(string) {
		        if (!oUtil.isEmpty(string)) {
		            this.getConfig().beforeLoadFunction = string;
		        } else {
		            $.messager.alert("ERROR", "setBeforeLoadFunction() parameter input error.");
		        }
		    },
		    
		    /**
		     * 그리드 footer 표시여부 설정
		     * 
		     * @param boolean = true:표시, false:숨김
		     */
		    setShowFooter : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().showFooter = boolean;
		        } else {
		            $.messager.alert("ERROR", "setShowFooter() parameter input error.(true or false)");
		        }
		    },
		    
		    /**
		     * 스크롤 상에 고정 컴럼 설정
		     * 
		     * @param array = 고정시킬 file 배열
		     */
		    setFrozenColumns : function(array) {
		        if (!oUtil.isEmpty(array)) {
		            this.getConfig().frozenColumns = array;
		        } else {
		            $.messager.alert("ERROR", "setFrozenColumns() parameter input error.");
		        }
		    },
		    /**
		     * 데이터 그리드의 특정 cell의 style 설정
		     * 
		     * @param string = style
		     */
		    setCellStyler : function(string) {
		        if (!oUtil.isEmpty(string)) {
		            this.getConfig().styler = string;
		        } else {
		            $.messager.alert("ERROR", "setCellStyler() parameter input error.");
		        }
		    },
		    /**
		     * 데이터 로딩시 호출할 함수 설정
		     * 
		     * @param string = 함수명
		     */
		    setLoadFilter : function(string) {
		        this.getConfig().loadFilter = string;
		    },
		    
		    /**
		     * 그리드 row 표시방법을 버퍼에 담아 보여줄지 여부 설정(아직 적용이 안되고 오류남)
		     * - 스크롤바를 최하위까지 내리면 다음 데이터를 읽어옴
		     * 
		     * @param boolean = true:버퍼에 담아 지정된 row만큼 보여줌, false:한번에 모두 보여줌
		     * @param number = 한번에 표시할 row수(default=100)
		     */
		    setBufferView: function(boolean, number) {
		        if (oUtil.isBoolean(boolean)) {
		            if(boolean) {
		                if(oUtil.isNull(number)) number = 100;
		                
		                this.getConfig().view = "scrollview";
		                this.getConfig().pageSize = number;
		            } if(boolean) {
		                this.getConfig().view = "";
		            }
		            
		        } else {
		            $.messager.alert("ERROR", "setBufferView() parameter input error.(true or false)");
		        }
		    },
		    /**
			 * Merge 사용여부 설정(default = false)
			 * 
			 * @param boolean = 적용:true, 미적용:false
			 */
		    setMergeMode : function(boolean) {
		        if (oUtil.isBoolean(boolean)) {
		            this.getConfig().mergeMode = boolean;
		        } else {
		            $.messager.alert("ERROR", "setMergeMode() parameter input error.");
		        }
		    },
		    
		    /**
			 * merge 사용시 merge 범위 및 merge key set 설정
			 * 
			 * @param mergeArray = merge 할 filed array
			 * @param mergeKey = merge 체크 할 key filed
			 */
		    setMergeData : function(array, string) {
		        if (oUtil.isArray(array) && !oUtil.isNull(string)) {
		            this.getConfig().mergeList = array;
		            this.getConfig().mergeKey = string;
		        } else {
		            $.messager.alert("ERROR", "setMergeData() parameter input error.");
		        }
		    }
	    },
	event = {

		/**
    	 * 요청 시 서버에서 오류가 발생한 경우 실행됨
    	 * 
    	 * @param obj = grid object
    	 */
        setOnLoadError : function(obj) {
            this.getConfig().onLoadError = function() {
            	var pid = grid.handle.getProgramID(obj);
            	
            	if($("#"+pid+"_searchBtn").length > 0) {
                	$("#"+pid+"_searchBtn").linkbutton("enable");
                }
            	
                var pro_id = eval("window");
                if (!oUtil.isNull(pro_id)) {
                    if (typeof(pro_id["memberSessionChecker"]) == "function") {
                        pro_id["memberSessionChecker"]();
                    } else {
                    	$.messager.alert("ERROR", "System error has occurred. Ask person in charge.", "error");
                    }
                }
            };
        },
        
        /**
    	 * 모든 이벤트에 대해 데이터(grid)가 성공적으로 로드될 경우 실행됨
    	 * 
    	 * @param obj = grid object
    	 * @param callback 완료 후 호출할 함수명
    	 */
        setOnLoadSuccess : function(obj) {
            if (!oUtil.isNull(obj)) {
                this.getConfig().onLoadSuccess = function(data) {
                	var pid = grid.handle.getProgramID(obj);
                	var gridId = grid.handle.getGridID(obj);
                	var conf = grid.getInitConfig(gridId);
                	
                	if($("#"+pid+"_searchBtn").length > 0) {
                    	$("#"+pid+"_searchBtn").linkbutton("enable");
                    }
                	
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
                    	
                    	var pager = obj.datagrid('getPager');
                    	pager.pagination({
                    		displayMsg:"{from} to {to} of {total}"
        	            });
                    }
                    
                    if (!oUtil.isNull(data.message) && data.message.length > 0) {
                        alert(data.message, "window");
                    }
                    
                    var msgView = conf.onEmptyMessage;
                    if (oUtil.isNull(data.rows[0])) {
                        return;
                    }
                    if (!oUtil.isNull(data.rows[0].empty)) {
                    	try {
    	                    if (oUtil.isNull(msgView) || msgView) {
    	                        $.messager.show({
    	                            title: 'Info',
    	                            height: "70px",
    	                            width: "280px",
    	                            showSpeed: 100,
    	                            msg: "<b>" + eval(data.rows[0].empty) + "</b><br>(This window will be closed in 2 seconds.)",
    	                            timeout: 1500,
    	                            showType: 'fade',
    	                            style: {
    	                                right: '',
    	                                bottom: ''
    	                            }
    	                        });
    	                    }
                    	} finally {
    	                    grid.handle.removeRow(obj, 0); // 첫열을 삭제한다.
    	                    
    	                    // 조회된 데이터가 없어도 callbackFuntion을 호출한다.
    	                    var callback = conf.callBackFunctionForNoData;
    	                    
    	                    if (!oUtil.isNull(callback)) {
    	                        var pro_id = eval("window." + pid + ".event");
    	                        if (!oUtil.isNull(pro_id)) {
    	                        	// 그리드 ID에 해당하는 <code>function</code>이 있으면 호출된다.
    	                            if (typeof(pro_id[callback]) == "function") {
    	                                pro_id[callback](data);
    	                            }
    	                        }
    	                    }
                    	}
                    } else {
                    	// 성공적으로 요청이 완료되면 지정한 callback함수를 호출한다.
                        var callback = conf.callBackFunction;
                        var mergeMode = conf.mergeMode;
                        
                        if (mergeMode) {
                            var mergeKey = conf.mergeKey;
                            var idxList = new Array();
                            var mergeFullRowList = grid.handle.getMergeList(data.rows, 0, mergeKey, idxList);
                            grid.handle.mergeRows(obj, mergeFullRowList);
                        }
                        if (!oUtil.isNull(callback)) {
                            var pro_id = eval("window." + pid + ".event");
                            if (!oUtil.isNull(pro_id)) {
                            	// 그리드 ID에 해당하는 <code>function</code>이 있으면 호출된다.
                                if (typeof(pro_id[callback]) == "function") {
                                    pro_id[callback](data);
                                }
                            }
                        }
                    }
                    
                    if(!oUtil.isNull($('#tt')) && $('#tt').length > 0) {
    	                var tab = $('#tt').tabs('getSelected');
    	                var index = $('#tt').tabs('getTabIndex', tab);
    	                if (index > grid.MAX_TAB_NUM) {
    	                    $('#tt').tabs('close', 1);
    	                }
                    }
                };
            }
        },
        
        /**
    	 * datagrid를 draw하거나 요청에 의해 재생성되는 경우 호출되는 함수
    	 * 
    	 * @return true:서버 요청을 진행한다.
    	 * 		   false:서버 요청을 취소시킨다. 
    	 */
        setOnBeforeLoad : function(obj) {
            this.getConfig().onBeforeLoad = function(param) {
            	var pid = grid.handle.getProgramID(obj);
            	var gridId = grid.handle.getGridID(obj);
            	var conf = grid.getInitConfig(gridId);
            	var callback = conf.beforeLoadFunction;
                var urlyn = conf.url;
                var pager = obj.datagrid('getPager');
                
                
                // datagrid를 load만 할 경우 setOnLoadSuccess함수가 호출되지 않기 때문에 url를 체크해서 disable하지 않도록 한다.
                if(!oUtil.isNull(urlyn) && $("#"+pid+"_searchBtn").length > 0) {
                	$("#"+pid+"_searchBtn").linkbutton("disable");
                }
                
                pager.pagination({
                	displayMsg:"0 to 0 of 0"
                });
                
                if (!oUtil.isNull(callback)) {
                    var pro_id = eval("window." + pid);
                    if (pro_id != null && pro_id != "undefined") {
                        if (typeof(pro_id[callback]) == "function") {
                            return pro_id[callback](param);
                        }
                    }
                }
                
                return true;
            };
        },
        
        /**
    	 * 사용자가 row를 클릭했을 때 실행됨-매개변수를 포함하고 있음<br>
    	 * 이벤트 실행 후 row 값을 구하기 위해선 페이지에  window.[pid].event.onClick_[grid id]함수를 구현하셔야 합니다.
    	 * 
    	 * @param obj = grid Object
    	 */
        setOnClickRow : function(obj) {
            var editIndex = undefined;
            
            if (!oUtil.isNull(obj)) {
                this.getConfig().onClickRow = function(rowIndex, rowData) {
                	var pid = grid.handle.getProgramID(obj);
                	var gridId = grid.handle.getGridID(obj);
                	var conf = grid.getInitConfig(gridId);
                	
                    conf.rowindex = rowIndex;
                    conf.rowdata = rowData;
                    
                    var pro_id = eval("window." + pid + ".event");
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onClick_"+gridId]) == "function") {
                            pro_id["onClick_"+gridId](rowData);
                        }
                    }
                    
                    var editorMode = conf.setEditMode;
                    if (editorMode) {
                    	// Editor 모드로 변경
                        if (editIndex != rowIndex) {
                            if (grid.edit.endEditing(obj)) {
                                obj.datagrid('selectRow', rowIndex).datagrid('beginEdit', rowIndex);
                                grid.edit.setEditIndex(obj, rowIndex);
                            } else {
                                obj.datagrid('selectRow', editIndex);
                            }
                        }
                    }
                    
                    // 체크박스 체크여부 확인(조건문에 부합되는 경우만 동작함)
                    if(conf.singleSelect &&
                    		!conf.checkOnSelect &&
                    		!conf.selectOnCheck) {
    	                var checkRows = grid.handle.getCheckedRowsIndex(obj);
    	            	var checked = true;
    	            	
    	            	for(var i = 0; i < checkRows.length; i++) {
    	                	if(checkRows[i] == rowIndex) {
    	                    	checked = false;
    	                    }
    	                }
    	
    	                if(checked) grid.handle.checkRow(obj, true, rowIndex);
    	                else grid.handle.checkRow(obj, false, rowIndex);
                    }
                };
            }
        },
        
        /**
    	 * 사용자가 row를 더블클릭했을 때 실행됨<br>
    	 * 이벤트 실행 후 row 값을 구하기 위해선 페이지에 window.[pid].event.onDblClick_[grid id]함수를 구현하셔야 합니다.
    	 * 
    	 * @param obj = grid Object
    	 */ 
        setOnDblClickRow : function(obj) {
            if (!oUtil.isNull(obj)) {
                this.getConfig().onDblClickRow = function(rowIndex, rowData) {
                	var pid = grid.handle.getProgramID(obj);
                	var gridId = grid.handle.getGridID(obj);
                	var conf = grid.getInitConfig(gridId);
                	
                    conf.rowindex = rowIndex;
                    conf.rowdata = rowData;

                    var pro_id = eval("window." + pid + ".event");
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onDblClick_"+gridId]) == "function") {
                            pro_id["onDblClick_"+gridId](rowData);
                        }
                    }
                    
                   // alert(conf.setEditMode);
                };
            }
        },
        
        /**
    	 * Editing 이후 Merge를 원복하기 위해 호출되는 함수
    	 * 
    	 * @param obj = grid object
    	 */
        setOnAfterEdit : function(obj) {
        	/*
    		 * Fires when user finish editing, the parameters contains:
    		 * 
    		 * rowIndex: the editing row index, start with 0
    		 * rowData: the record corresponding to the editing row
    		 * changes: the changed field/value pairs
    		 */
            this.getConfig().onAfterEdit = function(rowIndex, rowData, changes) {
            	var gridId = grid.handle.getGridID(obj);
            	var conf = grid.getInitConfig(gridId);
            	var editorMode = this.getProperty(obj).setEditMode;
                var mergeMode = this.getProperty(obj).mergeMode;
                
                if (editorMode) {
                    if (mergeMode) {
                        var mergeKey = conf.mergeKey;
                        var idxList = new Array();
                        var mergeFullRowList = grid.handle.getMergeList(grid.handle.getAllRows(obj), 0, mergeKey, idxList);
                        
                        grid.handle.mergeRows(obj, mergeFullRowList);
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            };
        },
        
        /**
    	 * 사용자가 cell을 클릭했을 때 실행하는 함수<br>
    	 * 이벤트 실행 후 cell 값을 구하기 위해선 페이지에 Program-code.[onClickGridCell]함수를 구현하셔야 합니다.
    	 * 
    	 * @param obj = grid object
    	 */
        setOnClickCell : function(obj) {
            if (!oUtil.isNull(obj)) {
            	var pid = grid.handle.getProgramID(obj);
            	
                this.getConfig().onClickCell = function(rowIndex, field, value) {
                    var pro_id = eval("window." + pid);
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onClickGridCell"]) == "function") {
                            pro_id["onClickGridCell"](value);
                        }
                    }
                    
                    return;
                };
            }
        },
        
        /**
    	 * 사용자가 cell을 더블클릭했을 때 실행하는 함수<br>
    	 * 이벤트 실행 후 cell 값을 구하기 위해선 페이지에 Program-code.[onDblClickGridCell]함수를 구현하셔야 합니다.
    	 * 
    	 * @param obj = grid object
    	 */
        setOnDblClickCell : function(obj) {
            if (!oUtil.isNull(obj)) {
            	var pid = grid.handle.getProgramID(obj);
            	
                this.getConfig().onDblClickCell = function(rowIndex, field, value) {
                    var pro_id = eval("window." + pid);
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onDblClickGridCell"]) == "function") {
                            pro_id["onDblClickGridCell"](value);
                        }
                    }
                    
                    return;
                };
            }
        },
        
        /**
    	 * 그리드를 선택했을 때 실행하는 함수
    	 * 
    	 * @param obj = grid object
    	 */
        setOnSelect : function(obj) {
            this.getConfig().onSelect = function(rowIndex, rowData) {
            	var gridId = grid.handle.getGridID(obj);
            	var conf = grid.getInitConfig(gridId);
            	
                conf.rowindex = rowIndex;
                conf.rowdata = rowData;
            };
        }
        
    },
    edit = {
				
        /**
    	 * 에디터 모드에서 지정된 row의 index를 반환
    	 * 
    	 * @param obj = grid object
    	 */
        getEditIndex : function(obj) {
        	var property = grid.getInitConfig(grid.handle.getGridID(obj));
            return property.editIndex;
        },
        
        /**
    	 * rowIndex에 해당 하는 field의 editor 객체를 return
    	 * 
    	 * @param obj = grid object
    	 * @param rowIndex = editor 를 받을 row index
    	 * @param fieldID = field ID
    	 */
        getEditor : function(obj, rowIndex, fieldID) {
            grid.datagrid('beginEdit', rowIndex);
            
            return obj.datagrid('getEditor', {
                index: rowIndex,
                field: fieldID
            });
        },
        
        /**
    	 * rowIndex에 해당 하는 모든 editor를 return
    	 * 
    	 * @param obj = grid object
    	 * @param rowIndex = editor 를 받을 row index
    	 */
        getAllEditor : function(obj, rowIndex) {
            var edList = obj.datagrid('getEditors', rowIndex);
            
            return edList;
        },
        
        /**
    	 * rowIndex에 해당 하는 field 값을 return
    	 * 
    	 * @param obj = grid object
    	 * @param rowIndex = editor 를 받을 row index
    	 * @param field = field ID
    	 */
        getRowValue : function(obj, rowIndex, field) {
            var ed = this.getEditor(obj, rowIndex, field);
            var value = "";
            
            if (ed.type == "text" || ed.type == "textarea" || ed.type == "validatebox") {
                value = $(ed.target).val();
            } else if (ed.type == "numberbox") {
                value = $(ed.target).numberbox("getValue");
            } else if (ed.type == "datebox") {
                value = $(ed.target).datebox("getValue");
            } else if (ed.type == "combobox") {
                value = $(ed.target).combobox("getValue");
            } else if (ed.type == "combotree") {
                value = $(ed.target).combotree("getValue");
            }
            
            return value;
        },
        
        /**
    	 * rowIndex에 해당 하는 field 값을 set<br>
    	 * 이 함수를 적용하기 위해서 datagrid생성 시 editer type을 지정해야 한다.
    	 * 
    	 * @param obj = grid object
    	 * @param rowIndex = editor 를 받을 row index
    	 * @param fieldID = field ID
    	 * @param value = set 할 값
    	 * @param fieldName = Comboxbox인 경우 화면에 표시할 필드명
    	 */
        setRowValue : function(obj, rowIndex, fieldID, value, fieldName) {
            try {
                var ed = this.getEditor(obj, rowIndex, fieldID);
                
                if (ed.type == "text" || ed.type == "textarea" || ed.type == "validatebox") {
                	if(!oUtil.isNull(value)) $(ed.target).val(value);
                } else if (ed.type == "numberbox") {
                	if(!oUtil.isNull(value)) $(ed.target).numberbox("setValue", value);
                } else if (ed.type == "datebox") {
                	if(!oUtil.isNull(value)) $(ed.target).datebox("setValue", value);
                } else if (ed.type == "combobox") {
                	if(!oUtil.isNull(value)) $(ed.target).combobox("setValue", value);
            	    grid.datagrid('getRows')[rowIndex][fieldName] = $(ed.target).combobox("getText");
                } else if (ed.type == "combotree") {
                	if(!oUtil.isNull(value)) $(ed.target).combotree("setValue", value);
            	    grid.datagrid('getRows')[rowIndex][fieldName] = $(ed.target).combotree("getText");
                }
            } catch (e) {
                grid.datagrid('rejectChanges');
            }
        },
        
        /**
    	 * rowIndex에 해당 하는 field 값을 set<br>
    	 * 이 함수를 적용하기 위해서 grid생성 시 editor type을 지정해야 한다.
    	 * 
    	 * @param obj = grid object
    	 * @param rowIndex = editor 를 받을 row index
    	 */
        setRowValueEditing : function(obj, rowIndex) {
            grid.datagrid('beginEdit', rowIndex);
            
            try {
                var prop = grid.getInitConfig(grid.handle.getGridID(obj));
                var columns = prop.columns;
                var frozenColumns = prop.frozenColumns;
                
                if (!oUtil.isNull(frozenColumns)) {
                    for (var i = 0; i < frozenColumns.length; i++) {
                        var colData = frozenColumns[i];
                        for (var j in colData) {
                            var field = colData[j].field;
                            var editor = colData[j].editor;
                            var formatter = colData[j].session;
                            
                            if (!oUtil.isNull(editor)) {
                                if (!oUtil.isNull(formatter) && formatter.format == "row") {
                                	var ed = this.getEditor(obj, rowIndex, field);
                                	
                                	if(editor.type == "combobox" || editor.type == "combotree") {
                                		grid.datagrid('getRows')[rowIndex][formatter.field] = $(ed.target).combobox("getText");
                                    }
                                }
                            }
                        }
                    }
                }
                
                if (!oUtil.isNull(columns)) {
                    for (var i = 0; i < columns.length; i++) {
                        var colData = columns[i];
                        for (var j in colData) {
                            var field = colData[j].field;
                            var editor = colData[j].editor;
                            var formatter = colData[j].session;
                            
                            if (!oUtil.isNull(editor)) {
                            	if (!oUtil.isNull(formatter) && formatter.format == "row") {
                            		var ed = this.getEditor(obj, rowIndex, field);
                            		
                            		if (editor.type == "combobox" || editor.type == "combotree") {
                            			grid.datagrid('getRows')[rowIndex][formatter.field] = $(ed.target).combobox("getText");
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (e) {
                grid.datagrid('rejectChanges');
            }
        },
        
        /**
    	 * Editing을 종료하고 최종 입력된 값을 적용
    	 * 
    	 * @param obj = grid object
    	 */
        endEditing : function(obj) {
            var editIndex = this.getEditIndex(obj);
            if (oUtil.isNull(editIndex)) {
                return true;
            }
            if (grid.datagrid('validateRow', editIndex)) {
                this.setRowValueEditing(obj, editIndex);
                
                if (grid.datagrid('validateRow', editIndex)) {
                    grid.datagrid('endEdit', editIndex);
                } else {
                    grid.datagrid('cancelEdit', editIndex);
                }
                
                editIndex = undefined;
                this.setEditIndex(obj, editIndex);
                return true;
            } else {
                return false;
            }
        }
        
    },
    handle = {
    	
    	/**
         * grid Object에서 프로그램 코드를 검색하는 함수(ID의 앞7자리를 찾아 받환)
         * 
         * @parma obj = grid object
         * @return program ID
         */
        getProgramID : function(obj) {
            var pro_id = "";
            var grid_id;
            
            if (typeof obj == "string") {
                grid_id = obj;
            } else if (typeof obj == "object") {
                grid_id = obj.datagrid("options").id;
            }
            
            pro_id = grid_id.substr(0, PROGRAM_ID_NUMBER);
            
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
            var grid_id;
            
            if (typeof obj == "string") {
                grid_id = obj;
            } else if (typeof obj == "object") {
            	try {
            		grid_id = obj.datagrid("options").id;
            	} catch(e) {
            		grid_id = obj.attr("id");
            	}
            }
            
            return grid_id;
        },
        
        /**
    	 * 서버 URL 요청
    	 * 
    	 * @praram obj datagrid
    	 * @param url = URL
    	 * @param params = 파라메터
    	 * @param callback = 응답 후 호출할 함수명 
    	 */
        sendRedirect : function(obj, url, params, callback) {
        	var prop = grid.getInitConfig(grid.handle.getGridID(obj));
            var urlyn = prop.url;
            var pid = grid.handle.getProgramID(obj);
            
            // 초기 Datagrid 생성 시 URL이 없는 경우 검색버튼 disable시킴
            if(oUtil.isNull(urlyn) && $("#"+pid+"_searchBtn").length > 0) {
            	$("#"+pid+"_searchBtn").linkbutton("disable");
            }
            
            obj.datagrid({
                url: url,
                queryParams: params
            });
        },
        
        /**
	     * 데이터가 그리드가 선택된 상태인지 여부 리턴 - 정상동작
	     * 
	     * @param obj = grid object obj
	     * @return 선택안됨:true, 선택됨:false
	     */
	    isNotSelected : function(obj) { 
	    	if(typeof grid == "string") obj = grid.getObject(obj);
	    	
	        if (!oUtil.isNull(this.getSelectedRowData(obj))) {
	            return false;
	        } else {
	            return true;
	        }
	    },
	    
	    /**
	     * 데이터가 그리드가 선택된 상태인지 여부 리턴 - 정상동작
	     * 
	     * @param obj = grid object obj
	     * @return 선택안됨:true, 선택됨:false
	     */
	    isSelected : function(obj) { 
	    	if(typeof grid == "string") obj = grid.getObject(obj);
	    	
	        if (!oUtil.isNull(this.getSelectedRowData(obj))) {
	            return true;
	        } else {
	            return false;
	        }
	    },
	    
	    /**
	     * 데이터가 그리드내 체크박스가 체크된 상태인지 여부 리턴 - 정상동작
	     * 
	     * @param obj = grid object obj
	     * @return 체크안됨:true, 체크됨:false
	     */
	    isNotChecked : function(obj) {
	        if(typeof grid == "string") obj = grid.getObject(obj);
	        
	        var data = this.getCheckedRowsData(obj);
	        var checkList = this.getRowIndex(obj, data);
	        
	        if (checkList.length > 0) {
	            return false;
	        } else {
	            return true;
	        }
	    },
	    
	    /**
	     * 데이터가 그리드내 체크박스가 체크된 상태인지 여부 리턴 - 정상동작
	     * 
	     * @param obj = grid object obj
	     * @return 체크안됨:true, 체크됨:false
	     */
	    isChecked : function(obj) {
	        if(typeof grid == "string") obj = grid.getObject(obj);
	        
	        var data = this.getCheckedRowsData(obj);
	        var checkList = this.getRowIndex(obj, data);
	        
	        if (checkList.length > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    },
	    
        /**
    	 * 컬럼의 해더 index(위치)를 구하는 함수 - 정상동작
    	 * 
    	 * @param obj = grid object
    	 * @param fieldID = column Name
    	 * @returns row index
    	 */
        getColumnIndex : function(obj, fieldID) {
        	var prop = grid.getInitConfig(grid.handle.getGridID(obj));
            var gridField = prop.columns[0];
            var gridLen = gridField.length;
            var colIndex = 0;
            
            for (var i = 0; i < gridLen; i++) {
                if (gridField[i].field == fieldID) {
                    colIndex = i + 1;
                }
            }
            
            return colIndex;
        },
        
        /**
    	 * 선택된 row 레코드의 index 번호 리턴<br>
    	 * (multiSelect mode에서는 마지막으로 선택된 row의 index번호를 리턴함) - 정상동작
    	 * 
    	 * @param obj = grid object
    	 * @returns 선택된 row index
    	 */
        getCurrentRowIndex : function(obj) {
        	var gridId = grid.handle.getGridID(obj);
        	var conf = grid.getInitConfig(gridId);
        	
            return conf.rowindex;
        },
        
        /**
    	 * 선택된 row 레코드의 data 리턴<br>
    	 * (multiSelect mode에서는 마지막으로 선택된 row의 data를 리턴함) - 정상동작
    	 * 
    	 * @param obj = grid object
    	 * @returns 선택된 row data
    	 */
        getCurrentRowData : function(obj) {
        	var gridId = grid.handle.getGridID(obj);
        	var conf = grid.getInitConfig(gridId);
        	
            return conf.rowdata;
        },
        
        /**
    	 * 현재 선택된 row 레코드의 row 값을 리턴<br> - 정상동작
    	 * file ID를 지정한 경우 해당하는 필드의 값을 리턴한다.
    	 * 
    	 * @param obj = grid object
    	 * @param fieldID = datagrid에서 정의한 필드ID 
    	 * @returns {String}
    	 */
        getSelectedRowData : function(obj, fieldID) {
        	var row = obj.datagrid('getSelected');
            
            if (row == null) {
                return null;
            }
            if (fieldID == null) {
                return row;
            } else {
                var value = eval("row." + fieldID);
                
                if (value != null) {
                    return value.toString();
                } else {
                    return "";
                }
            }
        },
        
        /**
    	 * 체크된 row 레코드의 모든 데이터를 리턴 - 정상동작
    	 * 
    	 * @param obj = grid object
    	 * @return check된 row의 모든 값(json 타입)
    	 */
        getCheckedRowsData : function(obj) {
            return this.getCheckedRowFieldData(obj, null, null);
        },
        
        /**
    	 * 체크된 row 레코드의 특정 field ID에서 해당되는 값을 리턴
    	 * 
    	 * @param obj = grid object
    	 * @param checkRow = 가져올 grid data의 row
    	 * @param fieldID = 가져올 grid data의 field id
    	 * @return check된 row에 대한 특정 file에 대한 배열
    	 */
        getCheckedRowFieldData : function(obj, checkRow, fieldID) {
            var checkRowData = obj.datagrid('getChecked');
            
            if (checkRowData == null) {
                return null;
            }
            
            if (fieldID == null && checkRow == null) {
                return checkRowData;
            } else if (checkRow != null && fieldID == null) {
                return checkRowData[checkRow];
            } else if (checkRow != null && fieldID != null) {
                return eval("checkRowData[" + checkRow + "]." + fieldID);
            }
        },
        
        /**
    	 * 체크된 row 레코드 인덱스 배열 리턴
    	 * 
    	 * @param obj = grid object
    	 * @return 체크된 index의 순번에 대한 배열
    	 */
        getCheckedRowsIndex : function(obj) {
            var checkRows = new Array();
            var index = 0;
            
            var data = this.getCheckedRowsData(obj);
            var checkList = this.getRowIndex(obj, data);
            var indexList = this.getAllRows(obj);
            
            for (var i = (indexList.length - 1); i >= 0; i--) {
                for (var k = 0; k < checkList.length; k++) {
                    if (data[k] == indexList[i]) {
                        checkRows[index] = i;
                        index++;
                        
                        break;
                    }
                }
            }
            
            return checkRows;
        },
        
        /**
    	 * 지정된 row를 파라메터의 data로 변경
    	 * 
    	 * @param obj = grid object
    	 * @param index = row index
    	 * @param data = 변경할 row data 
    	 */
        setChangeRowValue : function(obj, index, data) {
        	obj.datagrid('updateRow', {
                index: index,
                row: data
            });
        },
        
        /**
    	 * 그리드에서 추출된 데이터의 row index를 리턴
    	 * 
    	 * @param obj = grid object
    	 * @param rowData = select나 checked 된 rowData
    	 * @return row 번호
    	 */
        getRowIndex : function(obj, rowData) {
            var indexList = new Array();
            
            if (rowData.length == null || rowData.length == "undefined") {
                return obj.datagrid('getRowIndex', rowData);
            } else {
                for (var i = 0; i < rowData.length; i++) {
                    indexList[i] = obj.datagrid('getRowIndex', rowData[i]);
                }
                
                return indexList;
            }
        },
        
        /**
    	 * 지정된 index 열을 지운다.
    	 * 
    	 * @param obj = grid object
    	 * @param index = 지울 row index번호
    	 */
        removeRow : function(obj, index) {
        	obj.datagrid('cancelEdit', index).datagrid('deleteRow', index);
        },
        
        /**
    	 * 지정된 index 열을 추가한다.
    	 * @param obj = grid object
    	 */
        appendRow : function(obj, params) {
            if (oUtil.isNull(params)) {
            	obj.datagrid('appendRow', {
                    status: 'P'
                });
            } else {
            	obj.datagrid('appendRow', params);
            }
        },
        
        /**
    	 * datagrid의 row수를 리턴한다.
    	 * @param grid = datagrid object
    	 * @returns row count
    	 */
        getRowCount : function(obj) {
            return obj.datagrid("getRows").length;
        },
        
        /**
    	 * datagrid의 모든 row데이터를 리턴
    	 * 
    	 * @param grid = datagrid object
    	 * @returns json data
    	 */
        getAllRows : function(obj) {
            return obj.datagrid("getData").rows;
        },
        
        /**
    	 * datagrid의 데이터에서 지정된 field의 값을 배열로 리턴
    	 * 
    	 * @param obj = grid object
    	 * @param fieldId = 필드 식별ID
    	 * @reutrns Array
    	 */
        getFieldData : function(obj, fieldId) {
            var fieldData = new Array();
            var rownum = this.getRowCount(obj);
            
            if (rownum > 0) {
                var rowData = this.getAllRows(obj);
                for (var k = 0; k < rownum; k++) {
                    fieldData[k] = eval(rowData[k] + "." + fieldId);
                }
            }
            return fieldData;
        },
        
        /**
    	 * 지정된 열의 필드로부터 span까지를 merge
    	 * 
    	 * @param grie = datagrid object
    	 * @param index = 열번호
    	 * @param span = merge시킨 column수
    	 * @param field = merge를 시작할 필드 id
    	 */
        mergeCols : function(obj, index, span, field) {
            var merges = [{
                index: index,
                colspan: span
            }];
            for (var i = 0; i < merges.length; i++) {
            	obj.datagrid('mergeCells', {
                    index: merges[i].index,
                    field: field,
                    colspan: merges[i].colspan
                });
            }
        },
        
        /**
    	 * 지정된 행의 필드로부터 span까지를 merge
    	 * 
    	 * @param grid = datagrid object
    	 * @param mergeFullRowList = merge를 위한 index및 필드 정보를 포함하고 있는 List 객체
    	 */
        mergeRows : function(obj, mergeFullRowList) {
        	var prop = grid.getInitConfig(grid.handle.getGridID(obj));
            var mergeList = prop.mergeList;
            var startRowidx = 0;
            var key = mergeFullRowList[startRowidx].key;
            var mergeidxList;
            
            if (mergeFullRowList.length < 1) {
                return;
            }
            for (var i = 0; i < mergeList.length; i++) {
                if (!oUtil.isNull(mergeFullRowList[startRowidx + 1])) {
                    key = mergeFullRowList[startRowidx + 1].key;
                    if (mergeList[i] == key) {
                        startRowidx++;
                    }
                }
                mergeidxList = mergeFullRowList[startRowidx].mergeidxList;
                for (var j = 0; j < mergeidxList.length; j++) {
                	obj.datagrid('mergeCells', {
                    	index: mergeidxList[j].index, 		// 시작행
    					field: mergeList[i], 				// 병합할 필드ID
    					rowspan: mergeidxList[j].rowspan 	// 종료행
                    });
                }
            }
        },
        
        /**
    	 * merge 대상 칼럼의 start 위치와 merge의 갯수 리턴
    	 * 
    	 * @param data = 조회 데이타
    	 * @param startIdx = 데이타를 시작할 위치 index
    	 * @param mergeKey = merge 할 key가 되는 칼럼 배열
    	 * @param mergeidxList = 하나의 merge key 에 대한 start index와 rowspan 갯수를 가지고 있는 json 형태의 리스트
    	 * @param mergeFullIdxList = 모든 merge key에 대한 mergeidxList를 가지고 있는 json 형태의 리스트
    	 * @param mergeKeyIdx = merge key index
    	 * 
    	 * @returns mergeFullIdxList[{key:value, mergeidxList:[mergeidxList.index, rowspan:mergeidxList.rowspan]}]
    	 */
        getMergeList : function(data, startIdx, mergeKey, mergeidxList, mergeFullIdxList, mergeKeyIdx) {
            var mergeKeyList = new Array();
            
            // 초기화 값 셋팅
            if (oUtil.isNull(startIdx)) {
                startIdx = 0;
            }
            if (oUtil.isNull(mergeKeyIdx)) {
                mergeKeyIdx = 0;
            }
            if (oUtil.isNull(mergeFullIdxList)) {
                mergeFullIdxList = new Array();
            }
            
            // merge할 key값을 구하기
            if (typeof mergeKey == "string") {  // merge할 Key의 컬럼명(1개만 지정)
                mergeKeyList[0] = mergeKey;
            } else {  // merge할 Key의 컬럼 배열(1개 이상 지정)
                mergeKeyList = mergeKey;
            }
            
            for (var j = mergeKeyIdx; j < mergeKeyList.length; j++) { 
                var mergeKeyString = mergeKeyList[j]; // merge할 key
                
                for (var i = startIdx; i < data.length; i++) { // 데이타 시작 index
                    if (i + 1 == data.length) { // 마지막 row머지 처리
                        var rowsnum = (i - startIdx) + 1;
                        mergeidxList.push({
                            index: startIdx,
                            rowspan: rowsnum
                        });
                        break;
                    } else {
                    	// 비교할 key값 구하기
                        var startKey = "";
                        var idxKey = "";
                        for (var s = 0; s <= j; s++) {
                            startKey += eval("data[startIdx]." + mergeKeyList[s]);
                            idxKey += eval("data[i+1]." + mergeKeyList[s]);
                        }
                        if (startKey == idxKey) {
                            continue;
                        } else {
                            var rowsnum = (i - startIdx) + 1;
                            mergeidxList.push({
                                index: startIdx,
                                rowspan: rowsnum
                            });
                            return grid.handle.getMergeList(data, i + 1, mergeKeyList, mergeidxList, mergeFullIdxList, j);
                        }
                    }
                }
                mergeFullIdxList.push({
                    key: mergeKeyString,
                    mergeidxList: mergeidxList
                });
                
                // 데이터 초기화
                startIdx = 0;
                mergeidxList = new Array();
            }
            
            return mergeFullIdxList;
        },
        
        /**
    	 * grid의 colunm을 숨김
    	 * @parma grid datagrid
    	 */
        setHideColunm : function(obj, field) {
        	obj.datagrid("hideColumn", field);
        },
        
        /**
    	 * grid의 colunm을 보여줌
    	 * @parma grid datagrid
    	 */
        setShowColunm : function(obj, field) {
        	obj.datagrid("showColumn", field);
        },
        
        /**
         * grid의 cell format타입 설정
         * @param format 설정정보
         * @param value cell 값
         * @param row data
         * @index 지정된 row번호
         */
        getFormatData : function(format, value, row, index) {
            if (oUtil.isNull(value)) return;
            var rst = "";
            
            if (!oUtil.isNull(format)) {
                var type = format.format;
                
                if(type == "int") { // 숫자 3자리마다 콤마(,)를 찍어 반환
    				rst = formatInteger(value);
    			} else if(type == "float") { // 앞 정수 3자리마다 콤마(,)를 찍고, 실수는 지정된 값(format.precision)에 맞게 자릿수를 만들거나 제거해서 반환
    				var n = format.precision;
    				
    				if(oUtil.isNull(n)) n = 0;
    				
    				rst = formatFloat(value, n);
    			} else if(type == "date") {	// 숫자형식인 경우 6자리와 8자리에 대해 xxxx-xx-xx형식으로 반환,(단, 숫자가 아닌경우 원래값을 반환시킴)
    				var n = format.delim;	// 날짜 사이의 구분자(format.delim)를 적용할 수 있음(default구분자는 "-"임) 
    				var nt = format.nation;	// 국가(US와 KR만 지원)
    				
    				if(n == null) n = SEPERATOR_OF_DATE;
    				if(oUtil.isNull(nt)) nt = NATION_DATE_VIEW;
    				
    				rst = formatDate(value, n, nt);
    			} else if(type == "percent") { // 소수점처리 방식에서 마지막에 %를 붙여서 반환
    				var n = format.precision;
    				var rate = (oUtil.isNull(format.multiple))?100:format.multiple;
    				
    				if(oUtil.isNull(n)) n = 0;
    				value = value*rate;
    				
    				rst = formatFloat(value, n) + "%";
    			} else if(type == "amount") { // 기본은 숫자처리하나, 화폐의 기본단위를 포함시켜 반환(예> 원화, 달라, 유로, 엔화를 지원함)
    				rst = formatNumber(value);
    			} else if(type == "concat") { // 기본자열에 추가문자열을 붙여서 반환
    				var n = format.attach;
    				
    				if(oUtil.isNull(n)) n = "";
    				
    				rst = value+n;
    			} else if(type == "row") {
    				var gridId = new String(format.datagrid);
    				var field = new String(format.field);
    				var obj = grid.getObject(gridId);
    				
    				rst = obj.datagrid('getRows')[index][field];
    			} else if(type == "function") {
    				var pid = new String(format.pid);
    				var fnc = new String(format.name);
    				
    				var pro_id = eval("window."+pid);
    				
    				if(!oUtil.isNull(pro_id)) {
    					// 그리드 ID에 해당하는 <code>function</code>이 있으면 호출된다.
    					if (typeof (pro_id[fnc]) == "function") {
    						rst = pro_id[fnc](value, row, index);
    					}
    				}
    			} else {
    				rst = value;
    			}
            }
            
            return "<div title=\"" + value + "\" class=\"easyui-tooltip\">" + rst + "</div>";
        },
        
        /** combobox 생성
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param url = combobox data를 셋하기 위한 요청 url
         * @param valueId = value mapping id
         * @param textId = text(화면에 표현) mapping id
         * @param data = json 형태의 mapping id
         * @param resizable = 콤보박스 크기
         * @param value = 초기 선택될 값
         * @param editable = editing 허용여부(true | fasle)
         * @param multiple = 다중선택 여부(true | fasle)
         */
        setGridCombobox : function(formId, tagId, url, valueId, textId, data, height, value, func, editable, multiple) {
            var panelHeight = null;
            var tagCurrObj = form.getFormObject(formId, tagId);
            
            // 자동 크기조정 여부 체크
            if (oUtil.isNull(height) || height <= 0) {
                panelHeight = "auto";
            } else {
                panelHeight = height;
            } if (oUtil.isNull(editable)) {
                editable = false;
            }
            if (oUtil.isNull(multiple)) {
                multiple = false;
            }
            if (!oUtil.isNull(url)) {
                $(tagCurrObj).combobox({
                    url: url,
                    valueField: valueId,
                    textField: textId,
                    panelHeight: panelHeight,
                    editable: editable,
                    multiple: multiple,
                    required: true,
                    onLoadSuccess: function(data) {
                        return;
                        if (!oUtil.isNull(data) && data.length > 0) {
                            var val = eval("data[0]." + valueId);
                            if (!oUtil.isNull(value)) {
                            	// 조회된 데이터에 지정된 데이터가 있는지 확인한다.
                                for (var j = 0; j < data.length; j++) {
                                    var cmpval = eval("data[" + j + "]." + valueId);
                                    if (cmpval == value) {
                                        val = value;
                                        break;
                                    }
                                }
                            }
                            $(this).combobox("select", val);
                            var maxLen = getMaxDataLength(data, textId);
                            if ($(this).width() < maxLen) {
                                var pan = $(this).combobox("panel");
                                pan.panel('resize', {
                                    width: maxLen
                                });
                            }
                        } else {
                            $(this).combobox("select", "");
                        }
                    },
                    onChange: function(data) {
                        var pro_id = eval("window." + form.handle.getProgramID(formId));
                        if (typeof(pro_id[func]) == "function") {
                            pro_id[func](data);
                        }
                    }
                });
            } else {
                $(tagCurrObj).combobox({
                    valueField: valueId,
                    textField: textId,
                    data: data,
                    editable: editable,
                    multiple: multiple,
                    required: true,
                    panelHeight: panelHeight,
                    onLoadSuccess: function(data) {
                        if (!oUtil.isNull(data) && data.length > 0) {
                            return;
                            var val = eval("data[0]." + valueId);
                            if (!oUtil.isNull(value)) {
                            	// 조회된 데이터에 지정된 데이터가 있는지 확인한다.
                                for (var j = 0; j < data.length; j++) {
                                    var cmpval = eval("data[" + j + "]." + valueId);
                                    if (cmpval == value) {
                                        val = value;
                                        break;
                                    }
                                }
                            }
                            $(this).combobox("select", val);
                            var maxLen = getMaxDataLength(data, textId);
                            if ($(this).width() < maxLen) {
                                var pan = $(this).combobox("panel");
                                pan.panel('resize', {
                                    width: maxLen
                                });
                            }
                        } else {
                            $(this).combobox("select", "");
                        }
                    },
                    onChange: function(data) {
                        return;
                        var pro_id = eval("window." + form.handle.getProgramID(formId));
                        if (typeof(pro_id[func]) == "function") {
                            pro_id[func](data);
                        }
                    }
                });
            }
        },
        
        /**
         * grid data를 json타입으로 변경
         * 
         * @param gridData
         * @returns {String}
         */
        changeJsonForData : function(gridData, chang) {
            var key;
            var value;
            var quotes = '\\"';
            var toList = "[";
            var gridDataArray = new Array();
            
            if (gridData != null && gridData != 'undefined' && (gridData.length == null || gridData.length == 'undefined')) {
                gridDataArray[0] = gridData;
            } else {
                gridDataArray = gridData;
            }
            
            for (var i = 0; i < gridDataArray.length; i++) {
                var rowData = gridDataArray[i];
                var toMap = "{";
                var rowCnt = 0;
                
                for (var j in rowData) {
                    rowCnt++;
                    key = j;
                    try {
                    	value = eval("rowData." + key);
                    }catch(e) {
                    	alert(value);
                    	return;
                    }
                    var values = new String(value);
                    
                    if(chang == "N") { // 미변경
    				    values = values.replace(/\"/g, quotes);
    				} else { // 변경
                        values = values.replace(/\"/g, "＂");   
                    }
                    
                    if (oUtil.isNull(values) || values == 'null') {
                        toMap += '"' + key + '":"",';
                    } else {
                        toMap += '"' + key + '":"' + values + '",';
                    }
                }
                toMap = toMap.substring(0, toMap.length - 1);
                toMap += "},";
                toList += toMap;
            }
            toList = toList.substring(0, toList.length - 1);
            toList += "]";
            
            return toList;
        },
        
        /**
    	 * 그리드의 모든 checkbox 를 선택 또는 해제를 수행
    	 * 
    	 * @param obj = grid object
    	 * @param boolean = 체크 여부 flag(true: 모두 체크, false: 모두 해제)
    	 */
        checkAll : function(obj, boolean) {
            if (boolean) {
            	obj.datagrid("checkAll");
            } else {
            	obj.datagrid("uncheckAll");
            }
        },
        
        /**
    	 * 그리드의 checkbox 를 선택 또는 해제한다.
    	 * 
    	 * @param obj = grid object object
    	 * @param flag = 체크 여부 flag(true: 체크, false: 해제)
    	 * @param index = 체크 또는 해제 row index
    	 */
        checkRow : function(obj, flag, index) {
            if (flag) {
            	obj.datagrid("checkRow", index);
            } else {
            	obj.datagrid("uncheckRow", index);
            }
        },
        
        /**
    	 * datagrid의 모든 row데이터를 삭제한다.
    	 * @param obj = grid object
    	 */
        clearAll : function(obj) {
        	var rows = [{}];
        	
        	obj.datagrid("loadData", rows);
        	obj.datagrid("deleteRow", 0);
        },
        
        /**
    	 * rowIndex에 해당하는 열 선택
    	 * @param obj = grid object
    	 * @param rowIndex = select할 row index
    	 */
        selectRowIndex : function(obj, rowIndex) {
        	obj.datagrid("selectRow", rowIndex);
        }
        
    }

};