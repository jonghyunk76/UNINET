(function($){
	var emptyField = resource.getMessage("NOFVL");
	
	function getPluginName(target){
		if ($(target).data('treegrid')){
			return 'treegrid';
		} else {
			return 'datagrid';
		}
	}
	
	var autoSizeColumn1 = $.fn.datagrid.methods.autoSizeColumn;
	var loadDataMethod1 = $.fn.datagrid.methods.loadData;
	var appendMethod1 = $.fn.datagrid.methods.appendRow;
	var deleteMethod1 = $.fn.datagrid.methods.deleteRow;
	
	$.extend($.fn.datagrid.methods, {
		autoSizeColumn: function(jq, field){
			return jq.each(function(){
				var fc = $(this).datagrid('getPanel').find('.datagrid-header .datagrid-filter-c');
				// fc.hide();
				fc.css({
					width:'1px',
					height:0
				});
				autoSizeColumn1.call($.fn.datagrid.methods, $(this), field);
				// fc.show();
				fc.css({
					width:'',
					height:''
				});
				resizeFilter(this, field);
			});
		},
		loadData: function(jq, data){
			// console.log("loadData(1) >> data = " + data.rows.length);
			jq.each(function(){
				$.data(this, 'datagrid').filterSource = null;
			});
			// console.log("loadData(2) >> data = " + data.rows.length);
			return loadDataMethod1.call($.fn.datagrid.methods, jq, data);
		},
		appendRow: function(jq, row){
			var result = appendMethod1.call($.fn.datagrid.methods, jq, row);
			jq.each(function(){
				var state = $(this).data('datagrid');
				// console.log("appendRow >> state = " + state.filterSource);
				if (state.filterSource){
					state.filterSource.total++;
					if (state.filterSource.rows != state.data.rows){
						state.filterSource.rows.push(row);
					}
				}
			});
			return result;
		},
		deleteRow: function(jq, index){
			// console.log("deleteRow >> index = " + index);
			jq.each(function(){
				var state = $(this).data('datagrid');
				var opts = state.options;
				if (state.filterSource && opts.idField){
					if (state.filterSource.rows == state.data.rows){
						state.filterSource.total--;
					} else {
						for(var i=0; i<state.filterSource.rows.length; i++){
							var row = state.filterSource.rows[i];
							if (row[opts.idField] == state.data.rows[index][opts.idField]){
								state.filterSource.rows.splice(i,1);
								state.filterSource.total--;
								break;
							}
						}
					}
				}
			});
			return deleteMethod1.call($.fn.datagrid.methods, jq, index);		
		}
	});

	var loadDataMethod2 = $.fn.treegrid.methods.loadData;
	var appendMethod2 = $.fn.treegrid.methods.append;
	var insertMethod2 = $.fn.treegrid.methods.insert;
	var removeMethod2 = $.fn.treegrid.methods.remove;
	
	$.extend($.fn.treegrid.methods, {
		loadData: function(jq, data){
			jq.each(function(){
				$.data(this, 'treegrid').filterSource = null;
			});
			return loadDataMethod2.call($.fn.treegrid.methods, jq, data);
		},
		append: function(jq, param){
			return jq.each(function(){
				var state = $(this).data('treegrid');
				var opts = state.options;
				if (opts.oldLoadFilter){
					var rows = translateTreeData(this, param.data, param.parent);
					state.filterSource.total += rows.length;
					state.filterSource.rows = state.filterSource.rows.concat(rows);
					$(this).treegrid('loadData', state.filterSource)
				} else {
					appendMethod2($(this), param);
				}
			});
		},
		insert: function(jq, param){
			return jq.each(function(){
				var state = $(this).data('treegrid');
				var opts = state.options;
				if (opts.oldLoadFilter){
					var ref = param.before || param.after;
					var index = getNodeIndex(param.before || param.after);
					var pid = index>=0 ? state.filterSource.rows[index]._parentId : null;
					var rows = translateTreeData(this, [param.data], pid);
					var newRows = state.filterSource.rows.splice(0, index>=0 ? (param.before ? index : index+1) : (state.filterSource.rows.length));
					newRows = newRows.concat(rows);
					newRows = newRows.concat(state.filterSource.rows);
					state.filterSource.total += rows.length;
					state.filterSource.rows = newRows;
					$(this).treegrid('loadData', state.filterSource);

					function getNodeIndex(id){
						var rows = state.filterSource.rows;
						for(var i=0; i<rows.length; i++){
							if (rows[i][opts.idField] == id){
								return i;
							}
						}
						return -1;
					}
				} else {
					insertMethod2($(this), param);
				}
			});
		},
		remove: function(jq, id){
			jq.each(function(){
				var state = $(this).data('treegrid');
				if (state.filterSource){
					var opts = state.options;
					var rows = state.filterSource.rows;
					for(var i=0; i<rows.length; i++){
						if (rows[i][opts.idField] == id){
							rows.splice(i, 1);
							state.filterSource.total--;
							break;
						}
					}
				}
			});
			return removeMethod2(jq, id);
		}
	});

	var extendedOptions = {
		filterMenuIconCls: 'icon-ok',
		filterBtnIconCls: 'icon-filter',
		filterBtnPosition: 'right',
		filterPosition: 'bottom',
		remoteFilter: false,
		showFilterBar: true,
		muitlBar: true,
		filterDelay: 400,
		filterRules: [],
		// specify whether the filtered records need to match ALL or ANY of the applied filters
		filterMatchingType: 'all',	// possible values: 'all','any'
		filterIncludingChild: false,
		// filterCache: {},
        getComboboxFilter : function(formId, eId, field, sid){ // 콤보박스 체크 후 확인버튼을 클릭하면 데이터그리드 내용을 필터해서 보여준다.(2020.06.05)
        	var name = getPluginName(this); // datagrid
			var state = $.data(this, name); // datagrid object
			var opts = state.options;
			var rules = opts.filterRules;
        	var obj = form.getObject(formId, eId);
	        var ary = new Array();
	        
	        var totcnt = $('input[id='+eId+']', '#'+formId+'').length;
	        var chkcnt = $('input[id='+eId+']:checked', '#'+formId+'').length;
	        
	        if(totcnt == 0) {
	        	$(this)[name]('removeFilterRule', field);
	        } else {
	        	if(totcnt != chkcnt) {
		        	$("#"+sid).addClass("multi_filter_on");
		        } else {
		        	$("#"+sid).removeClass("multi_filter_on");
		        }
		        
		        ($('input[id='+eId+']:checked', '#'+formId+'')).each(function() {
		        	if(this.checked) ary.push(this.value);
	        	});
		        
		        $(this)[name]('removeFilterRule', field);
		        rules.push({
					field: field,
					op: "equal",
					value: ary.join("^")
				});
	        }
	        
			$(this)[name]('doFilter');
			
			return state.filterSource;
        },
		filterMatcher: function(data){
			var name = getPluginName(this); // datagrid
			var dg = $(this);
			var state = $.data(this, name); // datagrid object
			var opts = state.options;
			
			// console.log("myLoadFilter() >>> rows3-1 data = " + data.rows.length + ", filterRules = " + opts.filterRules.toString() + ", name = " + name);
			
			if (opts.filterRules.length){
				var rows = [];
				
				if (name == 'treegrid'){
					var rr = {};
					$.map(data.rows, function(row){
						if (isMatch(row, row[opts.idField])){
							rr[row[opts.idField]] = row;
							var prow = getRow(data.rows, row._parentId);
							while(prow){
								rr[prow[opts.idField]] = prow;
								prow = getRow(data.rows, prow._parentId);
							}
							if (opts.filterIncludingChild){
								var cc = getAllChildRows(data.rows, row[opts.idField]);
								$.map(cc, function(row){
									rr[row[opts.idField]] = row;
								});
							}
						}
					});
					for(var id in rr){
						rows.push(rr[id]);
					}
				} else {
					for(var i=0; i<data.rows.length; i++){
						var row = data.rows[i];
						
						if (isMatch(row, i)){ // 필터 적용
							rows.push(row);
						}
					}
				}
				
				// console.log("myLoadFilter() >>> rows3-2 data = " + rows.length);
				data = {
					total: data.total - (data.rows.length - rows.length),
					rows: rows
				};
			}
			
			// console.log("myLoadFilter() >>> rows3-3 data = " + data.rows.length);
			
			return data;
			
			function isMatch(row, index){
				if (opts.val == $.fn.combogrid.defaults.val){
					opts.val = extendedOptions.val;
				}
				
				var rules = opts.filterRules;
				if (!rules.length){return true;}
				
				for(var i = 0; i < rules.length; i++){
					var rule = rules[i];
					
					// var source = row[rule.field];
					// var col = dg.datagrid('getColumnOption', rule.field);
					// if (col && col.formatter){
					// 	source = col.formatter(row[rule.field], row, index);
					// }
					
					var col = dg.datagrid('getColumnOption', rule.field); // 셀컬럼의 설정정보 획득
					var formattedValue = (col && col.formatter) ? col.formatter(row[rule.field], row, index) : undefined;
					var source = opts.val.call(dg[0], row, rule.field, formattedValue); // 그리드내 셀 입력값
					
					if (source == undefined){
						source = '';
					}
					
					var op = opts.operators[rule.op];
					var matched = op.isMatch(source, rule.value); // 소스와 값에 일치하는게 있는지 확인
					
//					console.log("isMatch()3-1-1 >>> filterRules = " + rule.field + ", formatted Value =" + formattedValue + ", source/value = " + source + "/" + rule.value + ", poerators = " + rule.op +", matched = " + matched + ", filterMatchingType = " + opts.filterMatchingType);
					
					if (opts.filterMatchingType == 'any'){
						if (matched){return true;}
					} else {
						if (!matched){return false;}
					}
				}
				
				// console.log("isMatch()3-1-2 >>> final matched = " + (opts.filterMatchingType == 'all'));
				
				return opts.filterMatchingType == 'all';
			}
			function getRow(rows, id){
				for(var i=0; i<rows.length; i++){
					var row = rows[i];
					if (row[opts.idField] == id){
						return row;
					}
				}
				return null;
			}
			function getAllChildRows(rows, id){
				var cc = getChildRows(rows, id);
				var stack = $.extend(true, [], cc);
				while(stack.length){
					var row = stack.shift();
					var c2 = getChildRows(rows, row[opts.idField]);
					cc = cc.concat(c2);
					stack = stack.concat(c2);
				}
				return cc;
			}
			function getChildRows(rows, id){
				var cc = [];
				for(var i=0; i<rows.length; i++){
					var row = rows[i];
					if (row._parentId == id){
						cc.push(row);
					}
				}
				return cc;
			}
		},
		defaultFilterType: 'text',
		defaultFilterOperator: 'contains',
		defaultFilterOptions: {
			onInit: function(target){
				var name = getPluginName(target);
				var opts = $(target)[name]('options');
				var field = $(this).attr('name');
				var input = $(this);
				if (input.data('textbox')){
					input = input.textbox('textbox');
				}
				input.unbind('.filter').bind('keydown.filter', function(e){
					var t = $(this);
					if (this.timer){
						clearTimeout(this.timer);
					}
					if (e.keyCode == 13){
						_doFilter();
					}
					if (e.keyCode == 27){
						input.val("");
						_doFilter();
					}
//					else { // key입력때마다 리로드할 경우, 부하가 많이 걸려서 주석처리함.즉, 엔터입력시에만 검색하도록 수정(2020.06.30)
//						this.timer = setTimeout(function(){
//							_doFilter();
//						}, opts.filterDelay);
//					}
				});
				function _doFilter(){
					var rule = $(target)[name]('getFilterRule', field);
					var value = input.val();
					
					// console.log("1.start filter(name = " + name +", field = " + field + ", value = "+value+")");
					
					if (value != ''){
						if ((rule && rule.value!=value) || !rule){
							$(target)[name]('addFilterRule', {
								field: field,
								op: opts.defaultFilterOperator,
								value: value
							});
							$(target)[name]('doFilter');
						}
					} else {
						if (rule){
							$(target)[name]('removeFilterRule', field);
							$(target)[name]('doFilter');
						}
					}
				}
			}
		},
		filterStringify: function(data){
			return JSON.stringify(data);
		},
		// the function to retrieve the field value of a row to match the filter rule
		val: function(row, field, formattedValue){
			return formattedValue || row[field];
		},
		onClickMenu: function(item,button){}
	};

	$.extend($.fn.datagrid.defaults, extendedOptions);
	$.extend($.fn.treegrid.defaults, extendedOptions);
	
	// filter types
	$.fn.datagrid.defaults.filters = $.extend({}, $.fn.datagrid.defaults.editors, {
		label: {
			init: function(container, options){
				return $('<span></span>').appendTo(container);
			},
			getValue: function(target){
				return $(target).html();
			},
			setValue: function(target, value){
				$(target).html(value);
			},
			resize: function(target, width){
				$(target)._outerWidth(width-8)._outerHeight(22);
			}
		}
	});
	$.fn.treegrid.defaults.filters = $.fn.datagrid.defaults.filters;
	
	// filter operators
	$.fn.datagrid.defaults.operators = {
		nofilter: {
			text: 'No Filter'
		},
		contains: {
			text: 'Contains',
			isMatch: function(source, value){ // 띄어쓰기로 검색어를 입력할 경우, 모두 검색될 수 있도록 기능 추가(2020.06.03) 
				var sev = value.split(" ");
				var chk = false;
				
				// console.log("isMatch3-1-1-1 >>> " + sev.length);
				
				for(var i = 0; i < sev.length; i++) {
					source = String(source);
					var val = String(sev[i]);
					
					if(source.toLowerCase().indexOf(val.toLowerCase()) >= 0) {					
						chk = true;
						break;
					}
				}
				
				return chk;
			}
		},
		equal: {
			text: 'Equal',
			isMatch: function(source, value){
				var sev = value.split("^");
				var chk = false;
				
				if(oUtil.isNull(source)) source = emptyField;
				else source = new String(source);
				
				for(var i = 0; i < sev.length; i++) {
					var val = String(sev[i]);
					
					if(source.startsWith("<div title=")) {
						var sobj = source.split("\"");
						source = String(sobj[1]);
					}
					
					if(source == String(val)) {					
						chk = true;
						break;
					}
					
				}
				
				return chk;
			}
		},
		notequal: {
			text: 'Not Equal',
			isMatch: function(source, value){
				return source != value;
			}
		},
		beginwith: {
			text: 'Begin With',
			isMatch: function(source, value){
				source = String(source);
				value = String(value);
				return source.toLowerCase().indexOf(value.toLowerCase()) == 0;
			}
		},
		endwith: {
			text: 'End With',
			isMatch: function(source, value){
				source = String(source);
				value = String(value);
				return source.toLowerCase().indexOf(value.toLowerCase(), source.length - value.length) !== -1;
			}
		},
		less: {
			text: 'Less',
			isMatch: function(source, value){
				return source < value;
			}
		},
		lessorequal: {
			text: 'Less Or Equal',
			isMatch: function(source, value){
				return source <= value;
			}
		},
		greater: {
			text: 'Greater',
			isMatch: function(source, value){
				return source > value;
			}
		},
		greaterorequal: {
			text: 'Greater Or Equal',
			isMatch: function(source, value){
				return source >= value;
			}
		}
	};
	$.fn.treegrid.defaults.operators = $.fn.datagrid.defaults.operators;
	
	function resizeFilter(target, field){
		var toFixColumnSize = false;
		var dg = $(target);
		var header = dg.datagrid('getPanel').find('div.datagrid-header');
		var tr = header.find('.datagrid-header-row:not(.datagrid-filter-row)');
		var ff = field ? header.find('.datagrid-filter[name="'+field+'"]') : header.find('.datagrid-filter');
		ff.each(function(){
			var name = $(this).attr('name');
			var col = dg.datagrid('getColumnOption', name);
			var cc = $(this).closest('div.datagrid-filter-c');
			var btn = cc.find('a.datagrid-filter-btn');
			var cell = tr.find('td[field="'+name+'"] .datagrid-cell');
			var cellWidth = cell._outerWidth();
			if (cellWidth != _getContentWidth(cc)){
				this.filter.resize(this, cellWidth - btn._outerWidth());
			}
			if (cc.width() > col.boxWidth+col.deltaWidth-1){
				col.boxWidth = cc.width() - col.deltaWidth + 1;
				col.width = col.boxWidth + col.deltaWidth;
				toFixColumnSize = true;
			}
		});
		
		if (toFixColumnSize){
			$(target).datagrid('fixColumnSize');			
		}

		function _getContentWidth(cc){
			var w = 0;
			$(cc).children(':visible').each(function(){
				w += $(this)._outerWidth();
			});
			return w;
		}
	}
	
	function getFilterComponent(target, field){
		var header = $(target).datagrid('getPanel').find('div.datagrid-header');
		return header.find('tr.datagrid-filter-row td[field="'+field+'"] .datagrid-filter');
	}
	
	/**
	 * get filter rule index, return -1 if not found.
	 */
	function getRuleIndex(target, field){
		var name = getPluginName(target);
		var rules = $(target)[name]('options').filterRules;
		for(var i=0; i<rules.length; i++){
			if (rules[i].field == field){
				return i;
			}
		}
		return -1;
	}

	function getFilterRule(target, field){
		var name = getPluginName(target);
		var rules = $(target)[name]('options').filterRules;
		var index = getRuleIndex(target, field);
		if (index >= 0){
			return rules[index];
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param target
	 * @param param : field: field, op: item.name,value: value
	 * @returns
	 */
	function addFilterRule(target, param){
		// console.log("2.start addFilterRule....");
		
		var name = getPluginName(target);
		var opts = $(target)[name]('options');
		var rules = opts.filterRules;

		if (param.op == 'nofilter'){
			removeFilterRule(target, param.field);
		} else {
			var index = getRuleIndex(target, param.field);
			if (index >= 0){
				$.extend(rules[index], param);
			} else {
				rules.push(param);
			}
		}

		var input = getFilterComponent(target, param.field);
		
		if (input.length){
			if (param.op != 'nofilter'){
				var value = input.val();
				
				if (input.data('textbox')){
					value = input.textbox('getText');
				}
				
				// console.log("filter menu="+input[0].menu+", id="+param.field+", value=" + value + " / param value = " + param.value);
				
				if (value != param.value){
					input[0].filter.setValue(input, param.value);					
				}
			}
			var menu = input[0].menu;
			if (menu){
				menu.find('.'+opts.filterMenuIconCls).removeClass(opts.filterMenuIconCls);
				var item = menu.menu('findItem', opts.operators[param.op]['text']);
				menu.menu('setIcon', {
					target: item.target,
					iconCls: opts.filterMenuIconCls
				});
			}
		}
	}
	
	function removeFilterRule(target, field){
		var name = getPluginName(target);
		var dg = $(target);
		var gid = grid.handle.getGridID(dg);
		var opts = dg[name]('options');
		if (field){
			var index = getRuleIndex(target, field);
			if (index >= 0){
				opts.filterRules.splice(index, 1);
			}
			_clear([field]);
		} else {
			opts.filterRules = [];
			var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields'));
			
			for(var i=0; i<fields.length; i++){
				var sid = gid + "_show_"+fields[i];
				
				$("#"+sid).removeClass("multi_filter_on");
			}
			
			_clear(fields);
		}
		
		function _clear(fields){
			for(var i=0; i<fields.length; i++){
				var input = getFilterComponent(target, fields[i]);
				
				if (input.length){
					input[0].filter.setValue(input, '');
					var menu = input[0].menu;
					if (menu){
						menu.find('.'+opts.filterMenuIconCls).removeClass(opts.filterMenuIconCls);
					}
				}
			}
		}
	}
	
	function doFilter(target){
		var name = getPluginName(target); // datagrid
		var state = $.data(target, name);
		var opts = state.options;
		var gid = grid.handle.getGridID($(target));
		
		if (opts.remoteFilter){
			$(target)[name]('load');
		} else {
			if (opts.view.type == 'scrollview' && state.data.firstRows && state.data.firstRows.length){
				state.data.rows = state.data.firstRows;
			}
			
//			 console.log("name="+name + ", doFilter >> " + state.filterSource.rows.length + " / " + state.data.rows.length + " = " + state.filterSource || state.data);
			
//			$(target)[name]('getPager').pagination('refresh', {pageNumber:1});
//			$(target)[name]('options').pageNumber = 1;
			$(target)[name]('loadData', state.filterSource || state.data);
		}
		
		$(".filter_btn").each(function(){
			$(this).css("display", "block");
        });
		$(".filtered_btn").each(function(){
			$(this).css("display", "none");
        });
	}
	
	function translateTreeData(target, children, pid){
		var opts = $(target).treegrid('options');
		if (!children || !children.length){return []}
		var rows = [];
		$.map(children, function(item){
			item._parentId = pid;
			rows.push(item);
			rows = rows.concat(translateTreeData(target, item.children, item[opts.idField]));
		});
		$.map(rows, function(row){
			row.children = undefined;
		});
		return rows;
	}

	function myLoadFilter(data, parentId){
		// console.log("start myLoadFilter......");
		
		var target = this;
		var name = getPluginName(target); // datagrid
		var state = $.data(target, name);
		var opts = state.options;
		
		if (name == 'datagrid' && $.isArray(data)){
			data = {
				total: data.length,
				rows: data
			};
		} else if (name == 'treegrid' && $.isArray(data)){
			var rows = translateTreeData(target, data, parentId);
			data = {
				total: rows.length,
				rows: rows
			}
		}
		
		// console.log("myLoadFilter() >>> remoteFilter = " + opts.remoteFilter + ", filterSource = " + state.filterSource + ", pagination = "+opts.pagination);
		
		if (!opts.remoteFilter){
			if (!state.filterSource){
				state.filterSource = data;
			} else {
				if (!opts.isSorting) {
					if (name == 'datagrid'){
						state.filterSource = data;
					} else {
						state.filterSource.total += data.length;
						state.filterSource.rows = state.filterSource.rows.concat(data.rows);
						
						if (parentId){
							return opts.filterMatcher.call(target, data);
						}
					}
				} else {
					opts.isSorting = undefined;
				}				
			}
			// console.log("myLoadFilter() >>> rows1 filterSource = " + state.filterSource.rows.length + ", data = " + data.rows.length);
			if (!opts.remoteSort && opts.sortName){
				var names = opts.sortName.split(',');
				var orders = opts.sortOrder.split(',');
				var dg = $(target);
				state.filterSource.rows.sort(function(r1,r2){
					var r = 0;
					for(var i=0; i<names.length; i++){
						var sn = names[i];
						var so = orders[i];
						var col = dg.datagrid('getColumnOption', sn);
						var sortFunc = col.sorter || function(a,b){
							return a==b ? 0 : (a>b?1:-1);
						};
						r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
						if (r != 0){
							return r;
						}
					}
					return r;
				});
			}
			// console.log("myLoadFilter() >>> rows2 filterSource = " + state.filterSource.rows.length + ", data = " + data.rows.length);
			data = opts.filterMatcher.call(target, {
				total: state.filterSource.total,
				rows: state.filterSource.rows
			});
//			 console.log("myLoadFilter() >>> rows3 filterSource = " + state.filterSource.rows.length + ", data = " + data.rows.length);
			
			if (opts.pagination){
				var dg = $(target);
				var pager = dg[name]('getPager');
				
				pager.pagination({
					onSelectPage:function(pageNum, pageSize){
						$(target)[name]('removeFilterRule'); // 필터를 초기화 시킴
						
						// 그리드내 모든 멀티 필터창을 닫는다.(2020.06.05)
			    		$(".icon-multi_filter").each(function(){
			    			$(this).tooltip('hide');
			            });
			    		
	                    opts.pageNumber = pageNum;
	                    opts.pageSize = pageSize;
//	                    pager.pagination('refresh',{
//	                        pageNumber:pageNum,
//	                        pageSize:pageSize
//	                    });
	                    //dg.datagrid('loadData', state.filterSource);
//	                    dg[name]('loadData', state.filterSource);
	                    if(pager.pagination("options").viewNumber == 0) {
		                    if(name == "datagrid") {
		                    	dg.datagrid("reload");
		                    } else if(name == "treegrid") {
		                    	dg.treegrid("reload");
		                    }
	                    }
					},
					onBeforeRefresh:function(){
						dg[name]('reload');
						return false;
					}
				});
				
				// console.log("myLoadFilter() >>> rows4 filterSource = " + state.filterSource.rows.length + ", data = " + data.rows.length);
				/*
				if (name == 'datagrid'){
					var pd = getPageData(data.rows);
					opts.pageNumber = pd.pageNumber;
					data.rows = pd.rows;
				} else {
			        var topRows = [];
			        var childRows = [];
			        $.map(data.rows, function(row){
			        	row._parentId ? childRows.push(row) : topRows.push(row);
			        });
			        data.total = topRows.length;
			        var pd = getPageData(topRows);
			        opts.pageNumber = pd.pageNumber;
			        data.rows = pd.rows.concat(childRows);
				}
				*/
			}
			
			$.map(data.rows, function(row){
				row.children = undefined;
			});
		}
		
		// console.log("myLoadFilter() >>> rows5 filterSource = " + state.filterSource.rows.length + ", data = " + data.rows.length);
		
		return data;

		function getPageData(dataRows){
			var rows = [];
			var page = opts.pageNumber;
			while(page > 0){
				var start = (page-1)*parseInt(opts.pageSize);
				var end = start + parseInt(opts.pageSize);
				rows = dataRows.slice(start, end);
				if (rows.length){
					break;
				}
				page--;
			}
			return {
				pageNumber: page>0?page:1,
				rows: rows
			};
		}
	}
	
	function init(target, filters){
		filters = filters || [];
		var name = getPluginName(target);
		var state = $.data(target, name);
		var opts = state.options;
		if (!opts.filterRules.length){
			opts.filterRules = [];
		}
		opts.filterCache = opts.filterCache || {};
		var dgOpts = $.data(target, 'datagrid').options;
		
		var onResize = dgOpts.onResize;
		dgOpts.onResize = function(width,height){
			resizeFilter(target);
			onResize.call(this, width, height);
		}
		var onBeforeSortColumn = dgOpts.onBeforeSortColumn;
		dgOpts.onBeforeSortColumn = function(sort, order){
			var result = onBeforeSortColumn.call(this, sort, order);
			if (result != false){
				opts.isSorting = true;				
			}
			return result;
		};

		var onResizeColumn = opts.onResizeColumn;
		opts.onResizeColumn = function(field,width){
			var fc = $(this).datagrid('getPanel').find('.datagrid-header .datagrid-filter-c');
			var focusOne = fc.find('.datagrid-filter:focus');
			fc.hide();
			$(target).datagrid('fitColumns');
			if (opts.fitColumns){
				resizeFilter(target);
			} else {
				resizeFilter(target, field);
			}
			fc.show();
			focusOne.blur().focus();
			onResizeColumn.call(target, field, width);
		};
		var onBeforeLoad = opts.onBeforeLoad;
		opts.onBeforeLoad = function(param1, param2){
			if (param1){
				param1.filterRules = opts.filterStringify(opts.filterRules);
			}
			if (param2){
				param2.filterRules = opts.filterStringify(opts.filterRules);
			}
			
			// console.log("onBeforeLoad event >> " +  param1 + " / " + param2);
			
			var result = onBeforeLoad.call(this, param1, param2);
			if (result != false && opts.url){
				if (name == 'datagrid'){
					state.filterSource = null;
				} else if (name == 'treegrid' && state.filterSource){
					if (param1){
						var id = param1[opts.idField];	// the id of the expanding row
						var rows = state.filterSource.rows || [];
						for(var i=0; i<rows.length; i++){
							if (id == rows[i]._parentId){	// the expanding row has children
								return false;
							}
						}
					} else {
						state.filterSource = null;
					}
				}
			}
			return result;
		};

		// opts.loadFilter = myLoadFilter;
		opts.loadFilter = function(data, parentId){
			var d = opts.oldLoadFilter.call(this, data, parentId);
			// console.log("oldLoadFilter call >> data = " + d.rows.length + ", parentId = " + parentId);
			return myLoadFilter.call(this, d, parentId);
		};
		
		initCss();
//		createFilter(true);
		createFilter();
		
		if (opts.fitColumns){
			setTimeout(function(){
				resizeFilter(target);
			}, 0);
		}

		$.map(opts.filterRules, function(rule){
			addFilterRule(target, rule);
		});
		
		function initCss(){
			if (!$('#datagrid-filter-style').length){
				$('head').append(
					'<style id="datagrid-filter-style">' +
					'a.datagrid-filter-btn{display:inline-block;width:20px;height:20px;margin:0px;vertical-align:middle;cursor:pointer;opacity:0.6;filter:alpha(opacity=60);}' +
					'a:hover.datagrid-filter-btn{opacity:1;filter:alpha(opacity=100);}' +
					'.datagrid-filter-row .textbox,.datagrid-filter-row .textbox .textbox-text{-moz-border-radius:0;-webkit-border-radius:0;border-radius:0;}' +
					'.datagrid-filter-row input{height:22px;margin:0px;border:0px}' +
					'.datagrid-filter-c{overflow:hidden}' +
					'.datagrid-filter-cache{position:absolute;width:10px;height:10px;left:-99999px;}' +
					'</style>'
				);
			}
		}
		
		/**
		 * create filter component
		 */
		function createFilter(frozen){
			var dc = state.dc;
			var fields = $(target).datagrid('getColumnFields', frozen);
//			if (frozen && opts.rownumbers){
//				fields.unshift('_'); // 우측이 선이 두개로 보여는 문제가 있어 주석처리함(2020.06.12)
//			}
			var table = (frozen?dc.header1:dc.header2).find('table.datagrid-htable');
			
			// clear the old filter component
			table.find('.datagrid-filter').each(function(){
				if (this.filter.destroy){
					this.filter.destroy(this);
				}
				if (this.menu){
					$(this.menu).menu('destroy');
				}
			});
			table.find('tr.datagrid-filter-row').remove();
			
			var gid = grid.handle.getGridID($(target));
			var tr = $('<tr class="datagrid-header-row datagrid-filter-row"></tr>');
			if (opts.filterPosition == 'bottom'){
				tr.appendTo(table.find('tbody'));
			} else {
				tr.prependTo(table.find('tbody'));
			}
			if (!opts.showFilterBar){
				tr.hide();
			}
			
			for(var i=0; i < fields.length; i++){
				var field = fields[i];
				if(field == "CHECK") { // 체크박스 해더란에 필터가 설정된 경우, 필드를 표시하지 않음(2020.06.23)
					continue;
				}
				var col = $(target).datagrid('getColumnOption', field);
				var td = $('<td></td>').attr('field', field).appendTo(tr);
				if (col && col.hidden){
					td.hide();
				}
				if (field == '_'){
					continue;
				}
				if (col && (col.checkbox || col.expander)){
					continue;
				}

				var fopts = getFilter(field);
				if (fopts){
					$(target)[name]('destroyFilter', field);	// destroy the old filter component
				} else {
					fopts = $.extend({}, {
						field: field,
						type: opts.defaultFilterType,
						options: opts.defaultFilterOptions
					});
				}

				var div = opts.filterCache[field];
				
				if (!div){
					div = $('<div class="datagrid-filter-c" style="text-align:center;background-color:#fff;"></div>').appendTo(td);
					
					var filter = opts.filters[fopts.type];
					var input = filter.init(div, $.extend({height:24},fopts.options||{}));
					input.addClass('datagrid-filter').attr('name', field);
					input.attr('placeholder', resource.getMessage("SERCH"));
					input.attr('onclick', "javascript:grid.edit.endCellEdit('"+gid+"');"); // input 클릭시 에디팅모드를 종료하고, 값을 입력할 수 있도록 함
					input.attr("tabindex", "-1")
					input[0].filter = filter;
					var opval = fopts.op;
					
					if(fopts.muitlBar == false) {
						input[0].menu = createFilterButton(div, opval, $(target), field);
					} else {
						if(fopts.type == "text") {
							input[0].menu = createFilterButton(div, "multi", $(target), field);
						} else {
							input[0].menu = createFilterButton(div, opval, $(target), field);
						}
					}
					
					if (fopts.options){
						if (fopts.options.onInit){
							fopts.options.onInit.call(input[0], target);
						}
					} else {
						opts.defaultFilterOptions.onInit.call(input[0], target);
					}
					opts.filterCache[field] = div;
					resizeFilter(target, field);
				} else {
					div.appendTo(td);
				}
			}
		}
		
		/**
		 * 필터 및 멀티검색 버튼 생성
		 * @param container: 필터 source
		 * @param operators: 필터버튼 구분
		 * @param dg: 데이터그리드 Object
		 * @param fieldName: 데이터그리드 필드ID
		 */
		function createFilterButton(container, operators, dg, fieldName){
			if (!operators){return null;}
			
			if(operators == "multi") { // 엑셀과 같은 그리드내 데이터를 선택할 수 있는 기능 추가(2020-05-30)
				var pid = grid.handle.getProgramID(dg);
				var gid = grid.handle.getGridID(dg);
				var oid = gid + "_"+fieldName;
				var fid = gid + "_filter_"+fieldName;
				var iid = gid + "_input_"+fieldName;
				var bid = gid + "_btn_"+fieldName;
				var sid = gid + "_show_"+fieldName;
				var btn = $('<a id="'+sid+'" class="datagrid-filter-btn easyui-tooltip">&nbsp;</a>').addClass("icon-multi_filter");
				
				if (opts.filterBtnPosition == 'right'){
					btn.appendTo(container);
				} else {
					btn.prependTo(container);
				}
				
				var bt = btn.tooltip({
					content: "<div style=\"width:200px;height:250px;overflow-x:auto;\">",
	                showEvent: 'click',
	                position: "bottom,left",
	                deltaX: 26,
	                onUpdate: function(content){
	                	$('.filter_check_over').hover(function() {
	                		$(this).css("background-color","#d5eafa");
	                	}, function() {
	                		$(this).css("background-color","#fff");
	                	});
	                	
	                	var t = $(this);
	                	
	                	// 필터검색 버튼 이벤트 처리
	                	$("#"+bid+"_1").unbind().bind('click', function(){
	                		$(".filter_btn").each(function(){
	                			$(this).css("display", "none");
	                        });
	                    	$(".filtered_btn").each(function(){
	                			$(this).css("display", "block");
	                        });
	                    	
	                    	setTimeout(function () {
	                    		opts.getComboboxFilter.call(target, fid, oid, fieldName, sid);
	                    	}, 500);
	                    });
	                	
	                	// 필터검색창 닫기 버튼 이번트 저리
	                	$("#"+bid+"_2").unbind().bind('click', function(){
	                		t.tooltip('hide');
	                    });
	                	
	                	// 전체 필터 지우기 버튼 이번트 저리
	                	$("#"+bid+"_3").unbind().bind('click', function(){
	            			$(target)[name]('removeFilterRule'); // 필터조건 초기화
	            			$(target)[name]("doFilter"); // 그리드 필터정보 해제.
	                    });
	                	
	                	// 필드 필터 지우기 버튼 이번트 저리
	                	$("#"+bid+"_4").unbind().bind('click', function(){
	                		$(target)[name]('removeFilterRule', fieldName); // 필터조건 초기화
	                		$(target)[name]("doFilter"); // 그리드 필터정보 해제.
	                		
	                		$('input[id='+oid+']', '#'+fid+'').each(function() {
	                			$(this).prop('checked', true);
                				$(this).attr("checked", true);
	                    	});
	                		$("#"+oid+"_all").prop('checked', true);
	                		$("#"+oid+"_all").attr('checked', true);
	                		$("#"+sid).removeClass("multi_filter_on");
	                    });
	                	
	                	// 체크박스 전체선택 또는 전체선택 해제 이벤트 처리
	                	$("#"+oid+"_all").unbind().bind('click', function(){
	                		var chk = $(this);
	                		var val = $("#"+iid).val();
	                		
	                		// 필터가 걸린 경우, 필터된 데이터는 체크박스를 모두 푼다.
	                		$('input[id='+oid+']', '#'+fid+'').each(function() {
	                			if($(this).val().toLowerCase().indexOf(val.toLowerCase()) >= 0) {
	                				$(this).prop('checked', chk.is(":checked"));
		                			$(this).attr('checked', chk.is(":checked"));
	                			} else {
	                				$(this).prop('checked', false);
	                				$(this).attr("checked", false);
	                			}
	                    	});
	                    });
	                	
	                	// 필터검색창 검색 이벤트 처리
	                	$("#"+iid).unbind().bind('keyup', function(){
	                		var val = $(this).val();
	                		
	                		$('input[id='+oid+']', '#'+fid+'').filter(function () {
	                			if($(this).val().toLowerCase().indexOf(val.toLowerCase()) >= 0) {
	                				$(this).parent().show();
	                				$(this).prop('checked', true);
	                				$(this).attr("checked", true);
	                			} else {
	                				$(this).parent().hide();
	                				$(this).prop('checked', false);
	                				$(this).attr("checked", false);
	                			}
	                		});
	                    });
	                	
	                	// 필터검색 내용 체크박스 변경 이벤트 처리
	                	$('input[id='+oid+']', '#'+fid+'').unbind().bind('change', function(){
	                		var totcnt = $('input[id='+oid+']', '#'+fid+'').length;
		                	var chkcnt = $('input[id='+oid+']:checked', '#'+fid+'').length;
		                	var val = $("#"+iid).val();
	                		
		                	// 필터여부에 따라 전체 표시되는 데이터 수를 조회
		                	if(oUtil.isNull(val)) {
		                		totcnt = $('input[id='+oid+']', '#'+fid+'').length;
		                	} else {
		                		var ocnt = 0;
		                		$('input[id='+oid+']', '#'+fid+'').filter(function () {
		                			if($(this).val().toLowerCase().indexOf(val.toLowerCase()) >= 0) {
		                				ocnt++;
		                			}
		                		});
		                		
		                		totcnt = ocnt;
		                	}
		                	
		                	if(totcnt > 0 && totcnt == chkcnt) {
		                		$("#"+oid+"_all").attr("checked", true);
		                	} else {
		                		$("#"+oid+"_all").attr("checked", false);
		                	}
	                    });
	                },
	                onShow: function(e){
	                    var t = $(this);
	                    var totcnt = 0;
	                	var chkcnt = $('input[id='+oid+']:checked', '#'+fid+'').length;
	                	var val = $("#"+iid).val();
                		
	                	// 필터여부에 따라 전체 표시되는 데이터 수를 조회
	                	if(oUtil.isNull(val)) {
	                		totcnt = $('input[id='+oid+']', '#'+fid+'').length;
	                	} else {
	                		var ocnt = 0;
	                		$('input[id='+oid+']', '#'+fid+'').filter(function () {
	                			if($(this).val().toLowerCase().indexOf(val.toLowerCase()) >= 0) {
	                				ocnt++;
	                			}
	                		});
	                		
	                		totcnt = ocnt;
	                	}
                		
	                	if(totcnt > 0 && totcnt == chkcnt) {
	                		$("#"+oid+"_all").attr("checked", true);
	                	} else {
	                		$("#"+oid+"_all").attr("checked", false);
	                	}
	                	
	                    t.tooltip('tip').unbind().bind('mouseenter', function(){
	                    	t.tooltip('show');
	                    });
	                }
	            });
				
				// 마우스 클릭시 행의 데이터 정보를 출력한다.(2020-05.30)
				btn.bind('click.menubutton', function(e){
					// 그리드내 모든 멀티 필터창을 닫는다.(2020.07.08)
					$(".icon-multi_filter").each(function(){
						$(this).tooltip('hide');
			        });
					
					var gdata_1 = grid.handle.getFieldData(dg, fieldName); // 그리드 전체 데이터 조회
					var fsource = state.filterSource; // 필터 데이터
					var fdata_1 = new Array();
					var sdata_1 = new Array();
					 
					if(oUtil.isNull(fsource)) {
						sdata_1 = gdata_1;
					} else {
						for(var i = 0; i < fsource.rows.length; i++) {
							sdata_1.push(fsource.rows[i][fieldName]);
						}
					}
					
					// 그리드데이터는 중복값 제거
                    $.each(sdata_1,function(i, value){
                        if(fdata_1.indexOf(value) == -1 ) fdata_1.push(value);
                    });
					
                    fdata_1 = fdata_1.sort();
                    for(var i = 0; i < fdata_1.length; i++) {
                    	if(oUtil.isNull(fdata_1[i])) {
                    		fdata_1.splice(i, 1);
                    		fdata_1.push(emptyField);
                    		break;
                    	}
                    }
                    
                    // tree 데이터 생성
                    var hsource = new Array();
                    hsource.push("<div style=\"width:200px;height:294px;\">");
                    hsource.push("<div><a href=\"#\" id=\""+bid+"_4\" class=\"btnFilterCancel\">"+resource.getMessage("CLEAR")+"</a><div>");
                    hsource.push("<div style=\"padding-top: 4px;\"><a href=\"#\" id=\""+bid+"_3\" class=\"btnFilterCancel\">"+resource.getMessage("ALL")+" "+resource.getMessage("CLEAR")+"</a><div>");
                    hsource.push("<div style='display: block;height: 1px;border: 0;border-bottom: 1px solid #ccc;margin: 1px 0 3px 0;padding: 0px;'></div>");
                    hsource.push("<input id=\""+iid+"\" placeholder=\""+resource.getMessage("SERCH")+"\" style=\"width:196px;\">");
                    hsource.push("<div style=\"padding:3px 2px 3px 0px;\">");
                    hsource.push("<div style=\"width:100%;height:200px;overflow-x:auto;border:1px solid #c7cbce;\"><form id=\""+fid+"\" method=\"post\">");
                    hsource.push("<label><div class=\"filter_check_over\">");
                    hsource.push("<input type=\"checkbox\" id=\""+oid+"_all\"> ("+resource.getMessage("SEALL")+")");
                    hsource.push("</div></label>");
                    for(var i = 0; i < fdata_1.length; i++) {
                    	// 필드에 값이 있는지 확인한다.
                    	var gchk = false;
                    	var gval = "";
                    	if(fdata_1[i] == emptyField) {
                    		gval = "";
                    	} else {
                    		gval = fdata_1[i];
                    	}
                    	
                    	for(var k = 0; k < gdata_1.length; k++) {
                    		if(gval == gdata_1[k]) {
                    			gchk = true;
                    			break;
                    		}
                    	}
                    	
                    	hsource.push("<label><div class=\"filter_check_over\">");
                    	if(gchk) {
                    		hsource.push("<input type=\"checkbox\" id=\""+oid+"\" value=\""+fdata_1[i]+"\" checked> " + fdata_1[i]);
                    	} else {
                    		hsource.push("<input type=\"checkbox\" id=\""+oid+"\" value=\""+fdata_1[i]+"\"> " + fdata_1[i]);
                    	}
                    	hsource.push("</div></label>");
                    }
                    hsource.push("</form></div>");
                    hsource.push("</div><div class=\"filter_btn\">"); // hsource.push("<div style=\"padding:3px 2px 3px 0px;\">");
                    hsource.push("<a href=\"#\" id=\""+bid+"_1\" class=\"btn_gray2\" style=\"width:93px;\">"+resource.getMessage("CNFIR")+"</a>");
                    hsource.push("<span style=\"float:right\"><a href=\"#\" id=\""+bid+"_2\" class=\"btn_gray2\" style=\"width:93px;\">"+resource.getMessage("CLOSE")+"</a></span>");
                    hsource.push("</div><div class=\"filtered_btn\" style=\"display:none;text-align:center;padding-top:4px;\">");
                    hsource.push("<span>Loading....</span>");
                    hsource.push("</div></div>"); // hsource.push("<div style=\"width:200px;height:250px;\">");
                    
                    
                    bt.tooltip("update", hsource.join(''));
                    
					return false;
				});
				
				return false;
			} else {
				var btn = $('<a class="datagrid-filter-btn">&nbsp;</a>').addClass(opts.filterBtnIconCls);
				if (opts.filterBtnPosition == 'right'){
					btn.appendTo(container);
				} else {
					btn.prependTo(container);
				}
				
				var menu = $('<div></div>').appendTo('body');
				$.map(['nofilter'].concat(operators), function(item){
					var op = opts.operators[item];
					if (op){
						$('<div></div>').attr('name', item).html(op.text).appendTo(menu);
					}
				});
			
				menu.menu({
					alignTo:btn,
					onClick:function(item){
						var btn = $(this).menu('options').alignTo;
						var td = btn.closest('td[field]');
						var field = td.attr('field');
						var input = td.find('.datagrid-filter');
						var value = input[0].filter.getValue(input);
						
						if (opts.onClickMenu.call(target, item, btn, field) == false){
							return;
						}
						
						addFilterRule(target, {
							field: field,
							op: item.name,
							value: value
						});
						
						doFilter(target);
					}
				});
				
				btn[0].menu = menu;
				btn.bind('click.menubutton', {menu:menu}, function(e){
					$(this.menu).menu('show',{left:e.clientX, top:e.clientY});
					return false;
				});
				
				return menu;
			}
		}
		
		function getFilter(field){
			for(var i=0; i<filters.length; i++){
				var filter = filters[i];
				if (filter.field == field){
					return filter;
				}
			}
			return null;
		}
	}
	
	$.extend($.fn.datagrid.methods, {
		enableFilter: function(jq, filters){
			return jq.each(function(){
				var name = getPluginName(this);
				var opts = $.data(this, name).options;
				if (opts.oldLoadFilter){
					if (filters){
						$(this)[name]('disableFilter');
					} else {
						return;
					}
				}
				
				opts.oldLoadFilter = opts.loadFilter;
				init(this, filters);
				$(this)[name]('resize');
				
				if (opts.filterRules.length){
					if (opts.remoteFilter){
						doFilter(this);
					} else if (opts.data){
						doFilter(this);
					}
				}
			});
		},
		disableFilter: function(jq){
			return jq.each(function(){
				var name = getPluginName(this);
				var state = $.data(this, name);
				var opts = state.options;
				if (!opts.oldLoadFilter){
					return;
				}
				var dc = $(this).data('datagrid').dc;
				var div = dc.view.children('.datagrid-filter-cache');
				if (!div.length){
					div = $('<div class="datagrid-filter-cache"></div>').appendTo(dc.view);
				}
				for(var field in opts.filterCache){
					$(opts.filterCache[field]).appendTo(div);
				}
				var data = state.data;
				if (state.filterSource){
					data = state.filterSource;
					$.map(data.rows, function(row){
						row.children = undefined;
					});
				}
				dc.header1.add(dc.header2).find('tr.datagrid-filter-row').remove();
				opts.loadFilter = opts.oldLoadFilter || undefined;
				opts.oldLoadFilter = null;
				$(this)[name]('resize');
				$(this)[name]('loadData', data);

				// $(this)[name]({
				// 	data: data,
				// 	loadFilter: (opts.oldLoadFilter||undefined),
				// 	oldLoadFilter: null
				// });
			});
		},
		destroyFilter: function(jq, field){
			return jq.each(function(){
				var name = getPluginName(this);
				var state = $.data(this, name);
				var opts = state.options;
				if (field){
					_destroy(field);
				} else {
					for(var f in opts.filterCache){
						_destroy(f);
					}
					$(this).datagrid('getPanel').find('.datagrid-header .datagrid-filter-row').remove();
					$(this).data('datagrid').dc.view.children('.datagrid-filter-cache').remove();
					opts.filterCache = {};
					$(this)[name]('resize');
					$(this)[name]('disableFilter');
				}

				function _destroy(field){
					var c = $(opts.filterCache[field]);
					var input = c.find('.datagrid-filter');
					if (input.length){
						var filter = input[0].filter;
						if (filter.destroy){
							filter.destroy(input[0]);
						}
					}
					c.find('.datagrid-filter-btn').each(function(){
						$(this.menu).menu('destroy');
					});
					c.remove();
					opts.filterCache[field] = undefined;
				}
			});
		},
		getFilterRule: function(jq, field){
			return getFilterRule(jq[0], field);
		},
		addFilterRule: function(jq, param){
			return jq.each(function(){
				addFilterRule(this, param);
			});
		},
		removeFilterRule: function(jq, field){
			return jq.each(function(){
				removeFilterRule(this, field);
			});
		},
		doFilter: function(jq){
			return jq.each(function(){
				doFilter(this);
			});
		},
		getFilterComponent: function(jq, field){
			return getFilterComponent(jq[0], field);
		},
		resizeFilter: function(jq, field){
			return jq.each(function(){
				resizeFilter(this, field);
			});
		},
		getFilterData: function(jq, field){
			var source = null;
			
			jq.each(function(){
				var name = getPluginName(this);
				var state = $.data(this, name);
				
				if(state.filterSource) {
					source = state.filterSource.rows;
				}
			});
			
			return source;
		}
	});
})(jQuery);