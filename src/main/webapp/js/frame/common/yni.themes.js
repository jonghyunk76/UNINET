var Theme = {
	/**
	 * 기본 그리드 생성 함수
	 * 
	 * @param dgObject : datagrid object
	 * @param header1 : 첫번째 행 해더정보(배열 또는 json타입)
	 * @param header2 : 두번째 행 해더정보(배열 또는 json타입)
	 * @param gridType : 데이터 그리드 타입 구분(tree/combo 외 datagrid)
	 */
	defaultGrid : function(dgObject, header1, header2, gridType) {
		Theme.setConfig(dgObject, header1, header2, gridType);
	},
	setConfig : function(dgObject, header1, header2, gridType) {
		var flag = false;
		
		try {
			var gid = grid.handle.getGridID(dgObject);
		    var initConfig = grid.getInitConfig(gid);
		    
			// if(initConfig.REQUEST_STATE == "1") return; // DB를 통해 그려지는 그리드인 경우 상태값이 1인 경우, 화면에 표시되지 않은 문제가 있어 주석처리함(2022-01-10)
            initConfig.REQUEST_STATE = "0"; // 데이터 그리드가 초기화될 때 요청상태를 0으로 변경한다.(2022-01-10)
			
			if(oUtil.isNull(header1.params)) {
				flag = true;
			}
			
			if(oUtil.isNull(header1.gridType) && gridType != "tree") {
				gridType = "grid";
			}
			
		    if(!flag) {
		    	var pid = grid.handle.getProgramID(dgObject);
			    var headerId = grid.handle.getProgramID(dgObject);
			    
			    // 첫번째 헤더정보를 가져올 url은 공통으로 적용
			    if(oUtil.isNull(header1.url)) {
			    	header1.url = "/mm/cbox/selectDatagridInfo";
			    }
			    // 두번째 헤더 파라메터의 골통 정보를 생성한다.
			    if(!oUtil.isNull(header1.params)) {
			    	// 그리드 해더 정보를 그리드 속성 정보 등록함
			    	if(oUtil.isNull(header1.params.COMPANY_CD)) header1.params.COMPANY_CD = SESSION.COMPANY_CD;
			    	if(oUtil.isNull(header1.params.PID)) header1.params.PID = pid;
			    	if(oUtil.isNull(header1.params.USER_ID)) header1.params.USER_ID = SESSION.USER_ID;
			    	if(oUtil.isNull(header1.params.GRID_ID)) header1.params.GRID_ID = gid;
			    	
			    	initConfig.HEADER_ID_1 = header1.params.HEADER_ID;
			    	
			    	if(oUtil.isNull(header1.params.USER_ATTRIBUTE1)) {
			    		initConfig.USER_ATTRIBUTE1 = null; // 검색조건1(거래처별)
			    	} else {
			    		initConfig.USER_ATTRIBUTE1 = header1.params.USER_ATTRIBUTE1;
			    	}
			    	if(oUtil.isNull(header1.params.USER_ATTRIBUTE2)) {
			    		initConfig.USER_ATTRIBUTE2 = null; // 검색조건2
			    	} else {
			    		initConfig.USER_ATTRIBUTE2 = header1.params.USER_ATTRIBUTE2;
			    	}
			    	if(oUtil.isNull(header1.params.USER_ATTRIBUTE3)) {
			    		initConfig.USER_ATTRIBUTE3 = null; // 검색조건3
			    	} else {
			    		initConfig.USER_ATTRIBUTE3 = header1.params.USER_ATTRIBUTE3;
			    	}
			    	if(oUtil.isNull(header1.params.USER_ATTRIBUTE4)) {
			    		initConfig.USER_ATTRIBUTE4 = null; // 검색조건4
			    	} else {
			    		initConfig.USER_ATTRIBUTE4 = header1.params.USER_ATTRIBUTE4;
			    	}
			    	if(oUtil.isNull(header1.params.USER_ATTRIBUTE5)) {
			    		initConfig.USER_ATTRIBUTE5 = null; // 검색조건5
			    	} else {
			    		initConfig.USER_ATTRIBUTE5 = header1.params.USER_ATTRIBUTE5;
			    	}
			    }
			    
			    if(!oUtil.isNull(header2)) {
			    	// 첫번째 헤더정보를 가져올 url은 공통으로 적용
			    	if(oUtil.isNull(header2.url)) {
			    		header2.url = "/mm/cbox/selectDatagridInfo";
			    	}
			    	// 두번째 헤더 파라메터의 골통 정보를 생성한다.
			    	if(!oUtil.isNull(header2.params)) {
			    		initConfig.HEADER_ID_2 = header2.params.HEADER_ID;
				    	if(oUtil.isNull(header2.params.COMPANY_CD)) header2.params.COMPANY_CD = SESSION.COMPANY_CD;
				    	if(oUtil.isNull(header2.params.PID)) header2.params.PID = pid;
				    	if(oUtil.isNull(header2.params.USER_ID)) header2.params.USER_ID = SESSION.USER_ID;
				    	if(oUtil.isNull(header2.params.GRID_ID)) header2.params.GRID_ID = gid;
				    } else {
				    	initConfig.HEADER_ID_2 = null;
				    }
			    }
			    
				if(typeof header1.url == "string") {
					// jquery를 이용하여 직접 요청하는 함수를 구현한 후 결과를 받아서 그리드를 생성한다.
			    	$.ajax({type: 'POST',
							url: header1.url,
							data: header1.params,
							async: false,
							success: function(datas) {
								var data = $.parseJSON(datas);
				                
				                // 이스케이프된 문자를 복원시킨다.
				                if(!oUtil.isNull(data.rows)) {
				                	var datas = data.rows;
				                    
				                    for(var i in datas) {
				                        $.each(datas[i], function(key, value) {
				                            if(typeof value == "string") {
				                            	if(key == "title") {
				                            		data.rows[i][key] = resource.getSplitMessage(unescape(value));
				                            	} else if(key == "formatter") {
				                            		data.rows[i][key] = JSON.parse(unescape(value));
				                            	} else if(key == "editor") {
				                            		data.rows[i][key] = JSON.parse(unescape(value));
				                            	} else {
				                            		data.rows[i][key] = unescape(value);
				                            	}
				                            }
				                        });
				                    }
				                }
				                
				                // 멀티정렬 여부
				                try {
					                var sortObj = data.dataMap.map.sort;
					                
					                if(!oUtil.isNull(sortObj)) {
										if(sortObj.MULTI_SORT) {
											initConfig.multiSort = sortObj.MULTI_SORT;
										}
										// 초기 정렬할 명칭
										if(sortObj.SORT_NAME) {
											initConfig.sortName = sortObj.SORT_NAME;
										}
										// 초기 정렬할 방식(asc, desc)
										if(sortObj.SORT_ORDER) {
											initConfig.sortOrder = sortObj.SORT_ORDER;
										}
					                }
								} catch(e) {
									console.log("error = " + e);
								}
				                
				                // DB에 저장된 해더정보가 있다면, 해당 해더정보를 적용함(2020-03-23)
								if(!oUtil.isNull(data)) {
									// DB에 저장된 해더인 경우, 그리드 하단에 설정버튼을 추가해서 보이도록 함 
									// 1. 사용자별 그리드 표시 관리(컬럼순서, 정렬방식, 표시여부, 셀 폭 사이즈)
									// 2. 관리자용 그리드 생성 및 관리 기능
									
									var userAuth = false;
									if(SESSION.USER_ID) {
										if(SESSION.USER_ID == "fta") {
											userAuth = true;
										}
									}
									
									// 페이징이 사용자에 의해 설정된 경우에는 setting과 management를 추가함
									if(initConfig.showConfigPage) {
										if(oUtil.isNull(initConfig.pageLayout)) {
											if(initConfig.pagination) {
												if(userAuth) {
													if(initConfig.fitColumns == true) initConfig.pageLayout = ["list","first","prev","sep","manual","sep","next","last", "setting", "management"];
													else initConfig.pageLayout = ["list","first","prev","sep","manual","sep","next","last", "setting", "management", "resizeColumn"];
												} else {
													if(initConfig.fitColumns == true) initConfig.pageLayout = ["list","first","prev","sep","manual","sep","next","last", "setting"];
													else initConfig.pageLayout = ["list","first","prev","sep","manual","sep","next","last", "setting", "resizeColumn"];
												}
											} else {
												if(userAuth) {
													if(initConfig.fitColumns == true) initConfig.pageLayout = ["setting", "management"];
													else initConfig.pageLayout = ["setting", "management", "resizeColumn"];
												} else {
													if(initConfig.fitColumns == true) initConfig.pageLayout = ["setting"];
													else initConfig.pageLayout = ["setting", "resizeColumn"];
												}
												
												initConfig.pageSize = null;
											}
										}
										
										// 고정된 pageLayout인 경우에, fta사용자 이외에는 관리버튼이 보이지 않도록 함(2021-09-10)
										if(userAuth == false) {
											var pageConf = initConfig.pageLayout;
											
											for(var p = 0; p < pageConf.length; p++) {
												if(pageConf[p] == "management") {
													initConfig.pageLayout.splice(p, 1);
													break;
												}
											}
										}
										
										initConfig.pagination = true;
									}
									
									header1 = Theme.util.makeHeaderForDataMap(data.rows, initConfig);
									
									if(!oUtil.isNull(header2)) {
										if(typeof header2.url == "string") {
											$.ajax({type: 'POST',
												url: header2.url,
												data: header2.params,
												async: false,
												success: function(datas){
													var data = $.parseJSON(datas);
									                
									                // 이스케이프된 문자를 복원시킨다.
									                if(!oUtil.isNull(data.rows)) {
									                    var datas = data.rows;
									                    
									                    for(var i in datas) {
									                        $.each(datas[i], function(key, value) {
									                            if(typeof value == "string") {
									                            	
									                            	if(key == "title") {
									                            		data.rows[i][key] = resource.getSplitMessage(unescape(value));
									                            	} else if(key == "formatter") {
									                            		data.rows[i][key] = JSON.parse(unescape(value));
									                            	} else if(key == "editor") {
									                            		data.rows[i][key] = JSON.parse(unescape(value));
									                            	} else {
									                            		data.rows[i][key] = unescape(value);
									                            	}
									                            }
									                        });
									                    }
									                }
									                
									                if(data.rows.length > 0) {
														if(!oUtil.isNull(data)) {
															header2 = Theme.util.makeHeaderForDataMap(data.rows, initConfig);
														}
														
														Theme.defaultDraw(dgObject, header1, header2, gridType);
									                } else {
									                	Theme.defaultDraw(dgObject, header1, null, gridType);
									                }
												},
												dataType: "text"}
											);
										}
								    } else {
								    	Theme.defaultDraw(dgObject, header1, null, gridType);
								    }
								} else { // DB에 저장된 해더정보가 없다면, 스크립트로 작성된 해더정보를 적용함(2020-03-23)
									Theme.defaultDraw(dgObject, header1, header2, gridType);
								}
						    },
							dataType: "text"}
			        );
			    } else {
			    	this.defaultDraw(dgObject, header1, header2, gridType);
			    }
		    } else {
		    	this.defaultDraw(dgObject, header1, header2, gridType);
		    }
		} catch(e) {
			console.log("exception = " + e);
		}
	},
	defaultDraw : function(dgObject, header1, header2, gridType) {
		try {
			if(!oUtil.isNull(dgObject)) dgObject.empty();
			
			var gid = grid.handle.getGridID(dgObject);
		    var initConfig = grid.getInitConfig(gid);
		    
			// json타입의 해더정보를 구한다.
		    var arrHeader_first = "";
		    var arrHeader_second = "";
		    var arrHeader_array = new Array();
		    var arrHeader_frozen_array = new Array();
		    var checkbox1 = false;
		    var checkbox2 = false;
		    
		    if(!oUtil.isNull(initConfig.firstExcelHeader)) {
		        for(var i = 0; i < initConfig.firstExcelHeader.length; i++) {
		    		arrHeader_array.push(this.util.makeHeaderRow(gid, initConfig.firstExcelHeader[i], null, initConfig));
		    	}
		    }
		    
		    if (!oUtil.isNull(header1)) {
		        arrHeader_frozen_array.push(this.util.makeHeaderRow(gid, header1, "frozen", initConfig));
		        arrHeader_first = this.util.makeHeaderRow(gid, header1, null, initConfig);
		        
		        for (var i = 0; i < header1.length; i++) {
		        	if(header1[i][grid.HeaderColumnTypes.checkbox] == true || header1[i][grid.HeaderColumnTypes.checkbox] == "true") {
		        		checkbox1 = true;
		        		break;
		        	}
		        }
		        
		        arrHeader_array.push(arrHeader_first);
		    }
		    if (!oUtil.isNull(header2)) {
		        arrHeader_second = this.util.makeHeaderRow(gid, header2, null, initConfig);
		        
		        for (var i = 0; i < header2.length; i++) {
		        	if(header2[i][grid.HeaderColumnTypes.checkbox] == true || header2[i][grid.HeaderColumnTypes.checkbox] == "true") {
		        		checkbox2 = true;
		        		break;
		        	}
		        }
		        
		        arrHeader_array.push(arrHeader_second);
		    }
		    
		    initConfig.rowindex = null;
		    initConfig.rowdata = null;
	        
		    // default 속성 지정
		    if(oUtil.isNull(initConfig.loadMsg)) initConfig.loadMsg = resource.getMessage("MSG_LOADING_WAIT");
		    if(oUtil.isNull(initConfig.rownumbers)) initConfig.rownumbers = true;
		    if(oUtil.isNull(initConfig.autoRowHeight)) initConfig.autoRowHeight = false;
		    if(oUtil.isNull(initConfig.fit)) initConfig.fit = true;
		    if(oUtil.isNull(initConfig.remoteSort)) initConfig.remoteSort = false;
		    if(oUtil.isNull(initConfig.resizable)) initConfig.resizable = false;
		    if(oUtil.isNull(initConfig.showPageList)) initConfig.showPageList = false;
		    // if(oUtil.isNull(initConfig.pageList)) initConfig.pageList = [100, 200, 300];
		    if(oUtil.isNull(initConfig.pageLayout)) {
			    if(initConfig.pagination) { 
			    	if(initConfig.fitColumns == true) initConfig.pageLayout = ["list","first","prev","sep","manual","sep","next","last"];
			    	else initConfig.pageLayout = ["list","first","prev","sep","manual","sep","next","last","resizeColumn"];
			    }
		    }
		    try {
		    	if(TOP_SYS_ID == "CC") {
		    		initConfig.pageList = [100, 500, 1000, 5000, 10000]; // 통관 표시갯수 공통지정(2020.06.06)
		    	} else {
				    if(SESSION.CERTIFY_TYPE == "external") {
				    	initConfig.pageList = [50, 100]; // 최대 표시갯수를 변경(2018.05.18, YNI-Maker)
				    } else {
				    	if(oUtil.isNull(initConfig.pageList)) {
				    		initConfig.pageList = [100, 500, 1000]; // 최대 표시갯수를 변경(2017.12.04, YNI-Maker)
				    	} else {
				    		if(initConfig.pageList[0] > 50) {
				    			initConfig.pageList = [100, 500, 1000]; // 최대 표시갯수를 변경(2017.12.04, YNI-Maker)
				    		}
				    	}
				    	
				    }
		    	}
		    } catch(e) {
		    	if(oUtil.isNull(initConfig.pageList)) {
		    		initConfig.pageList = [100, 500, 1000]; // 최대 표시갯수를 변경(2017.12.04, YNI-Maker)
		    	} else {
		    		if(initConfig.pageList[0] > 50) {
		    			initConfig.pageList = [100, 500, 1000]; // 최대 표시갯수를 변경(2017.12.04, YNI-Maker)
		    		}
		    	}
		    }
		    if(oUtil.isNull(initConfig.singleSelect)) initConfig.singleSelect = true;
		    if(oUtil.isNull(initConfig.checkOnBox)) initConfig.checkOnBox = false;
		    if(oUtil.isNull(initConfig.selectCurrentRow)) initConfig.selectCurrentRow = false;
		    
		    initConfig.striped = true;
		    
		    // 고정셀값 설정
		    if(arrHeader_frozen_array.length > 0) {
		        if (!oUtil.isEmpty(arrHeader_frozen_array[0])) {
		        	initConfig.frozenColumns = arrHeader_frozen_array;
		        } else {
		        	initConfig.frozenColumns = new Array();
		        }
		    } else {
		    	initConfig.frozenColumns = new Array();
		    }
		    
		    initConfig.columns = arrHeader_array;
		    
		    if(checkbox1 || checkbox2) {
		    	// 체크박스 설정
		    	if(initConfig.checkOnBox) {
		    		initConfig.checkOnSelect = false;
			    	initConfig.selectOnCheck = false;
		    	} else {
				    if(initConfig.singleSelect) {
				    	if(oUtil.isNull(initConfig.checkOnSelect)) initConfig.checkOnSelect = false;
				    	if(oUtil.isNull(initConfig.selectOnCheck)) initConfig.selectOnCheck = false;
				    	
				        grid.event.setOnClickRow(dgObject);
				    } else {
				    	if(oUtil.isNull(initConfig.checkOnSelect)) initConfig.checkOnSelect = true;
				    	if(oUtil.isNull(initConfig.selectOnCheck)) initConfig.selectOnCheck = true;
				    	
				        grid.event.setOnClickRow(dgObject);
				    }
		    	}
		    }
	        
		    // default 이벤트 설정
		    grid.event.setOnBeforeLoad(dgObject);
		    grid.event.setOnLoadSuccess(dgObject);
		    grid.event.setOnLoadError(dgObject);
		    grid.event.setOnSelect(dgObject);
		    grid.event.setOnAfterEdit(dgObject);
		    
		    // FTA 클라우드에서는 필터를 쓸 수 있도록 함
		    //if(oUtil.isNull(initConfig.filter) && TOP_SYS_ID != 'CC' && (SESSION.FTA_NATION == 'VN' || SESSION.APPLICATION_SERVICE_TYPE == "CL")) {
            if(oUtil.isNull(initConfig.filter) && TOP_SYS_ID != 'CC') {
		    	initConfig.filter = true;
		    }
		    
		    // 데이터 그리드 생성
		    if(gridType == "tree") {
		    	if(oUtil.isNull(initConfig.animate)) initConfig.animate = false;
		    	if(oUtil.isNull(initConfig.collapsible)) initConfig.collapsible = true;
		    	
		    	dgObject.treegrid(grid.getConfig(gid));
		    } else if(gridType == "combo") {
		    	initConfig.mode = "remote";
		    	
		    	dgObject.combogrid(grid.getConfig(gid));
		    } else {
		    	var dg = dgObject.datagrid(grid.getConfig(gid));
		    	
		    	if(initConfig.filter == true) {
		    		if(!oUtil.isNull(initConfig.filterType)) {
		    			dg.datagrid('enableFilter', initConfig.filterType);
		    		} else {
		    			dg.datagrid('enableFilter');
		    		}
		    	}
		    }
		} catch(e) {
			console.log("[defaultDraw] exception = " + e);
		}
	},
	util : {
		/**
		 * 해덩 정보를 구한다.
		 * 
		 * @param gid = datagrid id
		 * @param arrayValue = json 타입의 해더 정보
		 * @param colType = frozen여부(frozen, null)
		 * @param initConfig = 데이터 그리드 설정 정보
		 */
		makeHeaderRow : function(gid, arrayValue, colType, initConfig) {
			var json_array = new Array();
			
			if (oUtil.isArray(arrayValue)) {
				for (var i = 0; i < arrayValue.length; i++) {
					var json_string = new Object();
					
					if (!oUtil.isEmpty(arrayValue[i])) {
						if(i != 0 && json_array.length == 0) { // 첫셀이 고정이 아니라면 이후 열도 모두 고정을 해제함
							var prefrozen = arrayValue[i-1][grid.HeaderColumnTypes.frozen];
							prefrozen = (prefrozen == "true" || prefrozen == true) ? true : false;
							
							if(prefrozen == false) arrayValue[i][grid.HeaderColumnTypes.frozen] = false;
						}
						
						var frozen = arrayValue[i][grid.HeaderColumnTypes.frozen];
						frozen = (frozen == "true" || frozen == true) ? true : false;
						
						if (colType == "frozen") {
							if (frozen == true) {
								json_string = this.getMakedHeaderRow(gid, arrayValue[i], initConfig);
								json_array.push(json_string);
							}
						} else {
							if (frozen == false) {
								json_string = this.getMakedHeaderRow(gid, arrayValue[i], initConfig);
								json_array.push(json_string);
							}
						}
						
						arrayValue[i][grid.HeaderColumnTypes.frozen] = frozen;
					}
				}
			} else {
				$.messager.alert('Error', "There is a format error in grid.");
			}
			return json_array;
		},
		/**
		 * 해더 정보를 생성한다.
		 * 
		 * @param gid = datagrid id
		 * @param arrayValue = json 타입의 해더 정보
		 * @param initConfig = 데이터 그리드 설정 정보
		 */
		getMakedHeaderRow : function(gid, arrayValue, initConfig) {
			var editorMode = false;
			var editFilm = true;
			
			if(!oUtil.isNull(initConfig)) editorMode = initConfig.editMode;
			if(!oUtil.isNull(initConfig)) {
				if(oUtil.isNull(initConfig.showEditorFilm)) editFilm = true;
				else editFilm = initConfig.showEditorFilm;
			}
			
			var json_string = new Object();
			
			var fieldId = arrayValue[grid.HeaderColumnTypes.field];
			var title = arrayValue[grid.HeaderColumnTypes.title];
			var width = arrayValue[grid.HeaderColumnTypes.width];
			var align = arrayValue[grid.HeaderColumnTypes.align];
			var halign = arrayValue[grid.HeaderColumnTypes.halign];
			var sortable = arrayValue[grid.HeaderColumnTypes.sortable];
			var hidden = arrayValue[grid.HeaderColumnTypes.hidden];
			var editor = arrayValue[grid.HeaderColumnTypes.editor];
			var checkbox = arrayValue[grid.HeaderColumnTypes.checkbox];
			var formatter = arrayValue[grid.HeaderColumnTypes.formatter];
			var rowspan = arrayValue[grid.HeaderColumnTypes.rowspan];
			var colspan = arrayValue[grid.HeaderColumnTypes.colspan];
			var styler = arrayValue[grid.HeaderColumnTypes.styler];
			if (oUtil.isNull(rowspan) || rowspan < 1) {
				rowspan = 0;
			}
			if (oUtil.isNull(colspan) || colspan < 1) {
				colspan = 0;
			}
			if (colspan == 0 && rowspan > 0)
				json_string.rowspan = rowspan;
			if (rowspan == 0 && colspan > 0) {
				json_string.colspan = colspan;
				fieldId = null;
				align = null;
				halign = null;
				sortable = null;
				editor = null;
				checkbox = null;
				formatter = null;
			}
			if (!oUtil.isNull(fieldId)) {
				json_string.field = fieldId;
				
				// 공통 : 셀 넓이 설정
				var defaultSize = fieldId.getFieldSize();
				var formatNm = (formatter)?formatter.format:"";
				if (defaultSize != null) {
					if(formatNm == "function" && fieldId == "CO_DOC_NO") {
						width = defaultSize + 20;
					} else {
					    width = defaultSize;
					}
				}
				// 공통: 셀 포맷 설정
				var defaultFormat = fieldId.getFormat();
				if(!oUtil.isNull(defaultFormat)) {
					formatter = defaultFormat;
				}
				// 공통 : 셀 좌우정렬 설정
				var defaultAlign = fieldId.getAlign();
				if(!oUtil.isNull(defaultAlign)) {
					align = defaultAlign;
				}
			}
			
			//if(fieldId == "END_DATE") alert(align);
			if (!oUtil.isNull(title))
				json_string.title = resource.changeDecode4String(title);
			if (!oUtil.isNull(width))
				json_string.width = width;
			if (!oUtil.isNull(align))
				json_string.align = align;
			if (!oUtil.isNull(halign))
				json_string.halign = halign;
			if (!oUtil.isNull(sortable))
				json_string.sortable = sortable;
			if (!oUtil.isNull(hidden))
				json_string.hidden = hidden;
			if (!oUtil.isNull(editor)) {
				if(!oUtil.isNull(formatter)) {
					if(!oUtil.isNull(formatter.precision)) {
						editor.options = {precision:formatter.precision};
					}
				}
				
				if(!oUtil.isNull(editor.options)) {
					var opt;
					
					editor.options = replaceAll(editor.options, "^", '"'); // json타입 변환을 위한 문제 지환(^는 예약된 문자임)
					
					try {
						opt = JSON.parse(unescape(editor.options));
					} catch(e) {
						opt = editor.options;
					}
					
					if(typeof opt == "string") {
						var pid = grid.handle.getProgramID(gid);
						var opts = new Object();
	                    var pro_id = eval("window." + pid + ".editor");
	                    
	                    if (!oUtil.isNull(pro_id)) {
	                        if (typeof(pro_id[opt]) == "function") {
	                        	opts = pro_id[opt](this);
	                        }
	                    }
	                    
	                    if(!oUtil.isNull(formatter)) {
	                    	opts.viewField = formatter.field;
	                    }
	                    
	                    if(!oUtil.isNull(opts)) {
	                    	opts.gridId = gid;
	                    	opts.fieldId = fieldId;
	                    	editor.options = opts;
	                    }
					} else if(typeof opt == "object") {
						opt.gridId = gid;
						opt.fieldId = fieldId;
						editor.options = opt;
					}
				}
				
				json_string.editor = editor;
			}
			if (!oUtil.isNull(checkbox))
				json_string.checkbox = checkbox;
			if (!oUtil.isNull(formatter))
				json_string.session = formatter;
			if (!oUtil.isNull(formatter))
				json_string.formatter = function(value, row, index) {
					return grid.handle.getFormatData(this.session, value, row, index);
				};
			if (!oUtil.isNull(styler)) {
				try {
					var obj = styler;
					
					if(typeof obj == "string") {
						obj = JSON.parse(unescape(styler.replace("`", "'")));
					} else {
						obj = styler;
					}
					if(obj.type == "hstyle") { // type:hstyle 해더명 우측에 새 스타일 적용 적용
						json_string.hstyle = obj.style;
					}
				} catch(e) {
					json_string.styler = function(value, row, index) {
						return styler;
					};
				}
			}
			// 에디터 모드일 경우 edit가 아닌 필드를 표시하기 위해 style을 변경함
			if (oUtil.isNull(styler)) {
				if(editorMode && oUtil.isNull(editor)) {
					json_string.styler = function(value, row, index) {
						if(editFilm) {
							return "background:#efefef;opacity:0.7;filter:alpha(opacity=70);height:25px;line-height:25px;vertical-align: middle;color:#000;margin-top:0px;";
						} else {
							return;
						}
					};
				}
			}
			
			return json_string;
		},
		/**
		 * URL 호출에 의해 서버에서 응답받은 정보로 해더 정보를 생성한다.
		 * 
		 * @param dataMap = 서버에서 응답받은 정보로 해더 정보
		 * @param initConfig = 데이터 그리드 설정 정보
		 */
		makeHeaderForDataMap : function(dataMap, initConfig) {
			var editorMode = false;
			if(!oUtil.isNull(initConfig)) editorMode = initConfig.editMode;
			
			var headerArray = new Array();
			var idx = 0;
			
			for(var i = 0; i < dataMap.length; i++) {
				var columnArray = new Array();
				
				if(!oUtil.isNull(dataMap[i].field)) columnArray[0] = dataMap[i].field;
				if(!oUtil.isNull(dataMap[i].title)) columnArray[1] = dataMap[i].title;
				if(!oUtil.isNull(dataMap[i].width)) columnArray[2] = parseInt(dataMap[i].width);
				if(!oUtil.isNull(dataMap[i].align)) columnArray[3] = dataMap[i].align;
				if(!oUtil.isNull(dataMap[i].halign)) columnArray[4] = dataMap[i].halign;
				if(!oUtil.isNull(dataMap[i].sortable)) columnArray[5] = (dataMap[i].sortable == "true")?true:false;
				if(!oUtil.isNull(dataMap[i].hidden)) columnArray[6] = (dataMap[i].hidden == "true")?true:false;
				if(!oUtil.isNull(dataMap[i].editor)) columnArray[7] = (dataMap[i].editor == "null")?null:dataMap[i].editor;
				if(!oUtil.isNull(dataMap[i].checkbox)) columnArray[8] = (dataMap[i].checkbox == "null")?null:dataMap[i].checkbox;
				if(!oUtil.isNull(dataMap[i].formatter)) columnArray[9] = (dataMap[i].formatter == "null")?null:dataMap[i].formatter;
				if(!oUtil.isNull(dataMap[i].rowspan)) columnArray[10] = parseInt(dataMap[i].rowspan);
				if(!oUtil.isNull(dataMap[i].colspan)) columnArray[11] = parseInt(dataMap[i].colspan);
				if(!oUtil.isNull(dataMap[i].frozen)) columnArray[12] = (dataMap[i].frozen == "true")?true:false;
				if(!oUtil.isNull(dataMap[i].styler)) columnArray[13] = (dataMap[i].styler == "null")?null:dataMap[i].styler;
				if(oUtil.isNull(dataMap[i].styler)) {
					if(editorMode && oUtil.isNull(editor)) {
						json_string.styler = function(value, row, index) {
							return "background:#efefef;opacity:0.8;filter:alpha(opacity=80);height:24px;line-height:24px;vertical-align: middle;color:#000";
						};
					}
				}
				
				headerArray[idx] = columnArray;
				idx++;
			}
			
			return headerArray;
		}
	},
	defaultBarChart : function(cht, index, position, l_fix, r_fix) {
		//chart.setShowLegend(true, "n");         // 챠트 설명 표시여부(parameter:표시여부, 위치(e/w/s/n))
        chart.setLegendPlacement((oUtil.isNull(position))?"in":position);        // 챠트 설명 위치(in/out)
        chart.setAnimate(true);                 // 애니메이션 효과
        
        // 챠트 디자인
        chart.setBackgroundColor("#fff");    // 배경색상(default = #ffffff)
        chart.setChartLineColor("#325e7b");     // 라인 색상(default = #897b74)
        chart.setChartLineWidth(0.5);           // 라인 두께(default = 1)
        chart.setChartBorder(false);             // border(default = false)
        chart.setChartShadow(true);            // 그림자 표시(default = true)
        chart.setTextColor("#325e7b");          // 좌표 텍스트 색상(default = #000000)
        
        // 줌, tooltip 기능 설정(원형 그래프에서는 적용되는지 않는기능임)
        var cursor = chart.getCursor(cht);
        chart.setZoom(cursor, true);
        chart.setTooltip(cursor, false);
        
        var series = chart.getSeries(cht, index);   // 챠트 시리즈 객체 획득(parameter : 챠트 객체, 표시될 그래프 수)
        
        if(index == 2) chart.setSeriesColors(series, ["#0080FF", "#FE9A2E"]);
        if(index == 1) chart.setSeriesColors(series, ["#0080FF"]);
        
        // 첫번째 그래프 조각 설정
        chart.setRendererType(series, "line", 0);   // 그래프 타입 설정
        chart.setBarMargin(series, 0, 0);          // 막대 그래프간 공백 설정(default=0px)
        chart.setBarWidth(series, 15, 0);          // 막대 그래프의 넓이 설정(default=15px)
        chart.setBarPadding(series, 0);            // 막대 그래프간 여백 설정(default=0px)
        chart.setPointLabels(series, true, 0);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
        chart.setAnimationTime(series, 2000, 0);   // 애니메이션 표시 속도
        chart.setShadow(series, true, 0);         // 그림자 표시여부(default=false)
        chart.setPointLabels(series, false, 0);   // 그래프 위에 라벨을 표시할지 여부
		
        // 두번째 그래프 조각 설정
        if(index > 1) {
	        chart.setRendererType(series, "line", 1);   // 그래프 타입 설정
	        chart.setBarMargin(series, 0, 1);          // 막대 그래프간 공백 설정(default=0px)
	        chart.setBarWidth(series, 15, 1);          // 막대 그래프의 넓이 설정(default=15px)
	        chart.setBarPadding(series, 1);            // 막대 그래프간 여백 설정(default=0px)
	        chart.setPointLabels(series, true, 1);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
	        chart.setAnimationTime(series, 2000, 1);   // 애니메이션 표시 속도
	        chart.setShadow(series, true, 1);         // 그림자 표시여부(default=false)
	        chart.setPointLabels(series, false, 1);   // 그래프 위에 라벨을 표시할지 여부
        }
        
        // X축 설정
        chart.setBottomXFormat("prefix", "");     // X좌표에 좌표상 표기될 문자형태
        chart.setBottomXStartPoint(0.5);          // X좌표에 표기가 시작될 위치 지정(default=0)
        chart.setBottomXTickInterval(0.5);        // X좌표 라인별 구간 크기(default = 1)
        chart.setBottomXDrawLine(false);         // X좌표 라인표시여부(default = true)
        chart.setBottomXAngle(0);                // X좌표 text 각도(default = 0)
        // Y축 좌측 설정
        if(l_fix == "int") {
        	chart.setLeftYFormat("int");
        } else {
        	chart.setLeftYFormat("suffix", l_fix);
        }
        chart.setLeftYDrawLine(true);            // 라인표시여부(default = true)
        chart.setLeftYAngle(0);                  // X좌표 text 각도(default = 0)
        // Y축 우측 설정
        if(r_fix == "int") {
        	chart.setRightYFormat("int");
        } else {
        	chart.setRightYFormat("suffix", r_fix);
        }
        chart.setRightYDrawLine(true);           // 라인표시여부(default = true)
        chart.setRightYAngle(0);                // X좌표 text 각도(default = 0)
        
        return series;
	},
	defaultBarNLineChart : function(cht, index, position) {
		//chart.setShowLegend(true, "n");         // 챠트 설명 표시여부(parameter:표시여부, 위치(e/w/s/n))
        chart.setLegendPlacement((oUtil.isNull(position))?"in":position);        // 챠트 설명 위치(in/out)
        chart.setAnimate(true);                 // 애니메이션 효과
        
        // 챠트 디자인
        chart.setBackgroundColor("#fff");    // 배경색상(default = #ffffff)
        chart.setChartLineColor("#325e7b");     // 라인 색상(default = #897b74)
        chart.setChartLineWidth(0.5);           // 라인 두께(default = 1)
        chart.setChartBorder(false);             // border(default = false)
        chart.setChartShadow(true);            // 그림자 표시(default = true)
        chart.setTextColor("#325e7b");          // 좌표 텍스트 색상(default = #000000)
        
        // 줌, tooltip 기능 설정(원형 그래프에서는 적용되는지 않는기능임)
        var cursor = chart.getCursor(cht);
        chart.setZoom(cursor, true);
        chart.setTooltip(cursor, false);
        
        var series = chart.getSeries(cht, index);   // 챠트 시리즈 객체 획득(parameter : 챠트 객체, 표시될 그래프 수)
        
        if(index == 3) chart.setSeriesColors(series, ["#04B431", "#FF8000", "#0080FF"]);
        if(index == 2) chart.setSeriesColors(series, ["#04B431", "#FF8000"]);
        if(index == 1) chart.setSeriesColors(series, ["#0080FF"]);
        
        // 첫번째 그래프 조각 설정
        chart.setRendererType(series, "bar", 0);   // 그래프 타입 설정
        chart.setBarMargin(series, 0, 0);          // 막대 그래프간 공백 설정(default=0px)
        chart.setBarWidth(series, 15, 0);          // 막대 그래프의 넓이 설정(default=15px)
        chart.setBarPadding(series, 0);            // 막대 그래프간 여백 설정(default=0px)
        chart.setPointLabels(series, true, 0);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
        chart.setAnimationTime(series, 2000, 0);   // 애니메이션 표시 속도
        chart.setShadow(series, true, 0);         // 그림자 표시여부(default=false)
        chart.setPointLabels(series, false, 0);   // 그래프 위에 라벨을 표시할지 여부
        
        // 두번째 그래프 조각 설정
        if(index > 1) {
	        chart.setRendererType(series, "line", 1);   // 그래프 타입 설정
	        chart.setBarMargin(series, 0, 1);          // 막대 그래프간 공백 설정(default=0px)
	        chart.setBarWidth(series, 15, 1);          // 막대 그래프의 넓이 설정(default=15px)
	        chart.setBarPadding(series, 1);            // 막대 그래프간 여백 설정(default=0px)
	        chart.setPointLabels(series, true, 1);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
	        chart.setAnimationTime(series, 2000, 1);   // 애니메이션 표시 속도
	        chart.setShadow(series, true, 1);         // 그림자 표시여부(default=false)
	        chart.setPointLabels(series, false, 1);   // 그래프 위에 라벨을 표시할지 여부
        }
        
        if(index > 2) {
	        chart.setRendererType(series, "line", 2);   // 그래프 타입 설정
	        chart.setBarMargin(series, 0, 2);          // 막대 그래프간 공백 설정(default=0px)
	        chart.setBarWidth(series, 15, 2);          // 막대 그래프의 넓이 설정(default=15px)
	        chart.setBarPadding(series, 2);            // 막대 그래프간 여백 설정(default=0px)
	        chart.setPointLabels(series, true, 2);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
	        chart.setAnimationTime(series, 2000, 2);   // 애니메이션 표시 속도
	        chart.setShadow(series, true, 2);         // 그림자 표시여부(default=false)
	        chart.setPointLabels(series, false, 2);   // 그래프 위에 라벨을 표시할지 여부
        }
        
        // X축 설정
        chart.setBottomXFormat("prefix", "");     // X좌표에 좌표상 표기될 문자형태
        chart.setBottomXStartPoint(0.5);          // X좌표에 표기가 시작될 위치 지정(default=0)
        chart.setBottomXTickInterval(0.5);        // X좌표 라인별 구간 크기(default = 1)
        chart.setBottomXDrawLine(false);         // X좌표 라인표시여부(default = true)
        chart.setBottomXAngle(0);                // X좌표 text 각도(default = 0)
        // Y축 좌측 설정
        chart.setLeftYFormat("int");
        chart.setLeftYDrawLine(true);            // 라인표시여부(default = true)
        chart.setLeftYAngle(0);                  // X좌표 text 각도(default = 0)
        // Y축 우측 설정
        chart.setRightYFormat("int");
        chart.setRightYDrawLine(true);           // 라인표시여부(default = true)
        chart.setRightYAngle(0);                // X좌표 text 각도(default = 0)
        
        return series;
	},
	defaultDblBarLineChart : function(cht, index, position, title) {
		//chart.setShowLegend(true, "n");         // 챠트 설명 표시여부(parameter:표시여부, 위치(e/w/s/n))
        chart.setLegendPlacement((oUtil.isNull(position))?"in":position);        // 챠트 설명 위치(in/out)
        chart.setAnimate(true);                 // 애니메이션 효과
        
        // 챠트 디자인
        chart.setBackgroundColor("#fff");    // 배경색상(default = #ffffff)
        chart.setChartLineColor("#325e7b");     // 라인 색상(default = #897b74)
        chart.setChartLineWidth(0.5);           // 라인 두께(default = 1)
        chart.setChartBorder(false);             // border(default = false)
        chart.setChartShadow(true);            // 그림자 표시(default = true)
        chart.setTextColor("#325e7b");          // 좌표 텍스트 색상(default = #000000)
        
        // 줌, tooltip 기능 설정(원형 그래프에서는 적용되는지 않는기능임)
        var cursor = chart.getCursor(cht);
        chart.setZoom(cursor, true);
        chart.setTooltip(cursor, false);
        
        var series = chart.getSeries(cht, index);   // 챠트 시리즈 객체 획득(parameter : 챠트 객체, 표시될 그래프 수)
        
        if(index == 2) chart.setSeriesColors(series, ["#00749F", "#DF3A01", "#0080FF"]);
        if(index == 2) chart.setSeriesColors(series, ["#00749F", "#DF3A01"]);
        if(index == 1) chart.setSeriesColors(series, ["#0080FF"]);
        
        // 첫번째 그래프 조각 설정
        chart.setRendererType(series, "bar", 0);   // 그래프 타입 설정
        chart.setBarMargin(series, 0, 0);          // 막대 그래프간 공백 설정(default=0px)
        chart.setBarWidth(series, 15, 0);          // 막대 그래프의 넓이 설정(default=15px)
        chart.setBarPadding(series, 0);            // 막대 그래프간 여백 설정(default=0px)
        chart.setPointLabels(series, true, 0);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
        chart.setAnimationTime(series, 2000, 0);   // 애니메이션 표시 속도
        chart.setShadow(series, true, 0);         // 그림자 표시여부(default=false)
        chart.setPointLabels(series, false, 0);   // 그래프 위에 라벨을 표시할지 여부
        
        // 두번째 그래프 조각 설정
        if(index > 1) {
	        chart.setRendererType(series, "bar", 1);   // 그래프 타입 설정
	        chart.setBarMargin(series, 0, 1);          // 막대 그래프간 공백 설정(default=0px)
	        chart.setBarWidth(series, 15, 1);          // 막대 그래프의 넓이 설정(default=15px)
	        chart.setBarPadding(series, 1);            // 막대 그래프간 여백 설정(default=0px)
	        chart.setPointLabels(series, true, 1);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
	        chart.setAnimationTime(series, 2000, 1);   // 애니메이션 표시 속도
	        chart.setShadow(series, true, 1);         // 그림자 표시여부(default=false)
	        chart.setPointLabels(series, false, 1);   // 그래프 위에 라벨을 표시할지 여부
        }
        
        if(index > 2) {
	        chart.setRendererType(series, "line", 2);   // 그래프 타입 설정
	        chart.setBarMargin(series, 0, 2);          // 막대 그래프간 공백 설정(default=0px)
	        chart.setBarWidth(series, 15, 2);          // 막대 그래프의 넓이 설정(default=15px)
	        chart.setBarPadding(series, 2);            // 막대 그래프간 여백 설정(default=0px)
	        chart.setPointLabels(series, true, 2);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
	        chart.setAnimationTime(series, 2000, 2);   // 애니메이션 표시 속도
	        chart.setShadow(series, true, 2);         // 그림자 표시여부(default=false)
	        chart.setPointLabels(series, false, 2);   // 그래프 위에 라벨을 표시할지 여부
        }
        
        // X축 설정
        chart.setBottomXFormat("prefix", "");     // X좌표에 좌표상 표기될 문자형태
        chart.setBottomXStartPoint(0.5);          // X좌표에 표기가 시작될 위치 지정(default=0)
        chart.setBottomXTickInterval(0.5);        // X좌표 라인별 구간 크기(default = 1)
        chart.setBottomXDrawLine(false);         // X좌표 라인표시여부(default = true)
        chart.setBottomXAngle(0);                // X좌표 text 각도(default = 0)
        // Y축 좌측 설정
        chart.setLeftYFormat("int");
        chart.setLeftYDrawLine(true);            // 라인표시여부(default = true)
        chart.setLeftYAngle(0);                  // X좌표 text 각도(default = 0)
        // Y축 우측 설정
        chart.setRightYFormat("int");
        chart.setRightYDrawLine(true);           // 라인표시여부(default = true)
        chart.setRightYAngle(0);                // X좌표 text 각도(default = 0)
        
        return series;
	},
	defaultPieChart : function(cht, index) {
		chart.setShowLegend(true, "e");         // 챠트 설명 표시여부(parameter:표시여부, 위치(e/w/s/n))
        chart.setLegendPlacement("out");        // 챠트 설명 위치(in/out)
        chart.setAnimate(true);                 // 애니메이션 효과
        
        // 챠트 디자인
        chart.setBackgroundColor("#f4f4f4");    // 배경색상(default = #ffffff)
        chart.setChartLineColor("#999999");     // 라인 색상(default = #897b74)
        chart.setChartLineWidth(0.5);           // 라인 두께(default = 1)
        chart.setChartBorder(true);             // border(default = false)
        chart.setChartShadow(false);            // 그림자 표시(default = true)
        chart.setTextColor("#999999");          // 좌표 텍스트 색상(default = #000000)

        var series = chart.getSeriesDefaults(cht);
        
        chart.setRendererType(series, "pie");  // 그래프 타입 설정
        chart.setShowDataLabels(series, true); // 원형 그래프 라벨 표시여부
        chart.setSliceMargin(series, 4);       // 조각이 분리되서 표시(default = 0)
        chart.setSineWidth(series, 0);         // 조각의 테두리를 선으로 표시(default = 0)
        chart.setStartAngle(series, 180);      // 그래프 각도 지정(default = 0), 만약 180으로 지정하면 반대로 표시됨
        chart.setShadow(series, true);         // 그림자 표시여부(default=false)
        chart.setPointLabels(series, true);    // 마우스를 올리면 지정된 값이 표시됨(default=true)
        chart.setPointLabels(series, false);   // 그래프 위에 라벨을 표시할지 여부
        
        return series;
	},
	normalPieChart : function(cht) {
		chart.setShowLegend(true, "e");         // 챠트 설명 표시여부(parameter:표시여부, 위치(e/w/s/n))
        chart.setLegendPlacement("in");        // 챠트 설명 위치(in/out)
        chart.setAnimate(true);                 // 애니메이션 효과
        
        // 챠트 디자인
        chart.setBackgroundColor("#fff");    // 배경색상(default = #ffffff)
        chart.setChartLineColor("#fff");     // 라인 색상(default = #897b74)
        chart.setChartLineWidth(0.5);           // 라인 두께(default = 1)
        chart.setChartBorder(false);             // border(default = false)
        chart.setChartShadow(false);            // 그림자 표시(default = true)
        chart.setTextColor("#999999");          // 좌표 텍스트 색상(default = #000000)

        var series = chart.getSeriesDefaults(cht);
        
        chart.setRendererType(series, "pie");  // 그래프 타입 설정
        chart.setShowDataLabels(series, true); // 원형 그래프 라벨 표시여부
        chart.setSliceMargin(series, 4);       // 조각이 분리되서 표시(default = 0)
        chart.setSineWidth(series, 0);         // 조각의 테두리를 선으로 표시(default = 0)
        chart.setStartAngle(series, 180);      // 그래프 각도 지정(default = 0), 만약 180으로 지정하면 반대로 표시됨
        chart.setShadow(series, true);         // 그림자 표시여부(default=false)
        chart.setPointLabels(series, false);   // 그래프 위에 라벨을 표시할지 여부
        
        return series;
	}
	
};