/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A034_03
 * 작업일자 : 2020-04-16
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA034_03.config.applyFtaNation();
    MMA034_03.init.initComponent();
}

var MMA034_03 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA034_03.datagrid.initGrid_1();
        	MMA034_03.datagrid.initGrid_2();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {},
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
        header_1 : [
            ["CHECK"                ,"Check"        ,100    ,"center"     ,"center"  ,false       ,false    ,null   ,true     ,null    ,0    ,0    ,true],
            ["CELL_FIELD"           ,resource.getMessage("FIELD_ID")    ,160    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null    ,0    ,0    ,true],
            ["CELL_TITLE"           ,resource.getMessage("FIELD_NAME")  ,200    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,{format:"function", pid:"MMA034_03", name:"convertMessageCode"}    ,0    ,0    ,true],
            ["CELL_WIDTH"           ,resource.getMessage("WIDT")        ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_ALIGN"           ,resource.getMessage("TEXT_ALIGN")  ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_HALIGN"          ,resource.getMessage("TEXT_VALIGN") ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_SORTABLE"        ,resource.getMessage("SORT_YN")     ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_HIDDEN"          ,resource.getMessage("HIDDEN_YN")   ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_EDITOR"          ,resource.getMessage("EDIT_TYPE")   ,250    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_CHECKBOX"        ,resource.getMessage("CHECKBOX_YN") ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_FORMATTER"       ,resource.getMessage("CELL_FORMAT") ,250    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_ROWSPAN"         ,resource.getMessage("ROWSAPN")     ,80     ,"right"    ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_COLSPAN"         ,resource.getMessage("COLSPAN")     ,80     ,"right"    ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_FROZEN"          ,resource.getMessage("CELL_FIX_YN") ,80     ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_STYLER"          ,resource.getMessage("CELL_STYLE")  ,250    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_SPAN_CNT"        ,resource.getMessage("COLSPAN_CNT") ,80     ,"center"   ,"center"   ,false       ,false        ,null   ,null     ,null],
            ["REAL_TABLE"           ,resource.getMessage("TARGT_TB")    ,100    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["REAL_COLUMN"          ,resource.getMessage("TARGT_TXT_COLUMN")  ,100    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null]
        ],
        header_2 : [
            ["CHECK"                ,"Check"        ,100    ,"center"     ,"center"    ,false       ,false        ,null   ,true     ,null    ,0    ,0    ,true],
            ["FIELD_MAPPING_ID"     ,resource.getMessage("TOP_ITEMS")   ,160    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null    ,0    ,0    ,true],
            ["CELL_FIELD"           ,resource.getMessage("FIELD_ID")    ,160    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null    ,0    ,0    ,true],
            ["CELL_TITLE"           ,resource.getMessage("FIELD_NAME")  ,200    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,{format:"function", pid:"MMA034_03", name:"convertMessageCode"}    ,0    ,0    ,true],
            ["CELL_WIDTH"           ,resource.getMessage("WIDT")        ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_ALIGN"           ,resource.getMessage("TEXT_ALIGN")  ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_HALIGN"          ,resource.getMessage("TEXT_VALIGN") ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_SORTABLE"        ,resource.getMessage("SORT_YN")     ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_HIDDEN"          ,resource.getMessage("HIDDEN_YN")   ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_EDITOR"          ,resource.getMessage("EDIT_TYPE")   ,250    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_CHECKBOX"        ,resource.getMessage("CHECKBOX_YN") ,100    ,"center"   ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_FORMATTER"       ,resource.getMessage("CELL_FORMAT") ,250    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["CELL_ROWSPAN"         ,resource.getMessage("ROWSAPN")     ,80     ,"right"    ,"center"   ,false       ,true         ,null   ,null     ,null], // null 처리
            ["CELL_COLSPAN"         ,resource.getMessage("COLSPAN")     ,80     ,"right"    ,"center"   ,false       ,true         ,null   ,null     ,null], // null 처리
            ["CELL_FROZEN"          ,resource.getMessage("CELL_FIX_YN") ,80     ,"center"   ,"center"   ,false       ,true         ,null   ,null     ,null], // null 처리
            ["CELL_STYLER"          ,resource.getMessage("CELL_STYLE")  ,250    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["REAL_TABLE"           ,resource.getMessage("TARGT_TB")    ,100    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null],
            ["REAL_COLUMN"          ,resource.getMessage("TARGT_TXT_COLUMN")  ,100    ,"left"     ,"center"   ,false       ,false        ,{type:'text'}   ,null     ,null]
        ],
        initGrid_1 : function() {
        	var focusObj = form.getObject("MMA034_03_form_01", "GRID_LANG_CODE");
        	var params = form.handle.getElementsParam("MMA034_03_form_01");
    		params.HEADER_NO = "1";
        	var dg_1 = grid.getObject("MMA034_03_grid_01");
            
        	grid.init.setURL("/mm/cbox/selectGridColumnSetList");
        	grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.event.setOnClickRow(dg_1);
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(true);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            grid.init.setEmptyMessage(false);
            grid.init.setNextFocus(focusObj); // 셀 에디팅을 빠져나간 후 이동할 객체 지정
            grid.init.setCallBackFunction("displayGridName"); // 조회 시 데이터가 있으면 에디팅할 셀을 활성화 시킴
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
        	var focusObj = form.getObject("MMA034_03_form_01", "GRID_LANG_CODE");
        	var params = form.handle.getElementsParam("MMA034_03_form_01");
    		params.HEADER_NO = "2";
        	var dg_2 = grid.getObject("MMA034_03_grid_02");
            
        	if(!oUtil.isNull(params.HEADER_ID_2)) {
	        	grid.init.setURL("/mm/cbox/selectGridColumnSetList");
	        	grid.init.setQueryParams(params);
        	} else {
        		grid.init.setURL("#");
        	}
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(true);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false);
            grid.init.setEmptyMessage(false);
            grid.init.setNextFocus(focusObj); // 셀 에디팅을 빠져나간 후 이동할 객체 지정
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_2);
            grid.event.setOnCellEdit(dg_2);
            
            Theme.defaultGrid(dg_2, this.header_2);
        }
    },
    // 이벤트 처리
    event : {
    	onClick_MMA034_03_grid_01 : function(rowData) {
            ;
        },
        deleteDatagridMstInfo : function() {
        	MMA034_03.control.selectGridColumnSetList("1");
        	MMA034_03.control.selectGridColumnSetList("2");
        },
        displayGridName : function(data) {
        	var dg_1 = grid.getObject("MMA034_03_grid_01");
     		var dg_data1 = grid.handle.getAllRows(dg_1);
     		
     		if(oUtil.isNull(form.handle.getValue("MMA034_03_form_01", "GRID_LANG_CODE"))) {     			
     			form.handle.setValue("MMA034_03_form_01", "GRID_LANG_CODE", dg_data1[0].GRID_LANG_CODE);
     		}
        	form.handle.isValidate("MMA034_03_form_01");
        }
    },
    // 업무구현
    control : {
    	selectGridColumnSetList : function(num) {
     		var dg_1;
     		var params = form.handle.getElementsParam("MMA034_03_form_01");
     		
     		if(num == "1") {
     			dg_1 = grid.getObject("MMA034_03_grid_01");
     		} else {
     			dg_1 = grid.getObject("MMA034_03_grid_02");
     		}
     		params.HEADER_NO = num;
     		
     		grid.handle.sendRedirect(dg_1, "/mm/cbox/selectGridColumnSetList", params);
     	},
    	insertDatagridMstInfo : function() {
    		if(!form.handle.isValidate("MMA034_03_form_01")) return;
    		
    		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {                
                if(flag) {
                	var dg_1 = grid.getObject("MMA034_03_grid_01");
             		var dg_data1 = grid.handle.getAllRows(dg_1);
             		var dg_count1 = grid.handle.getRowCount(dg_1);
             		
             		dg_1.datagrid('endCellEdit');
             		
             		var dg_2 = grid.getObject("MMA034_03_grid_02");
             		var dg_data2 = grid.handle.getAllRows(dg_2);
             		
             		dg_2.datagrid('endCellEdit');
             		
             		if(dg_count1 <= 0) {
             			alert(resource.getMessage("MSG_NO_INSERT"));
             			return;
             		}
             		
             		form.handle.setValue("MMA034_03_form_01", "gridData1", grid.util.convertJson2Rows(dg_data1));
             		form.handle.setValue("MMA034_03_form_01", "gridData2", grid.util.convertJson2Rows(dg_data2));
             		form.handle.setValue("MMA034_03_form_01", "HEADER_ID_1", form.handle.getValue("MMA034_03_form_02", "HEADER_ID_1"));
            		form.handle.setValue("MMA034_03_form_01", "HEADER_ID_2", form.handle.getValue("MMA034_03_form_03", "HEADER_ID_2"));
            		
             		var obj = form.getObject("MMA034_03_form_01");
            		
            		form.init.setURL("/mm/pop/mmA034_03/insertDatagridMstInfo");
            		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            		
            		form.submit(obj);
                }
            });
    	},
    	deleteDatagridMstInfo : function() {
     		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("TXT_INIT_PROCESS"), function(flag) {                
                if(flag) {
                	var obj = form.getObject("MMA034_03_form_01");
            		
            		form.init.setURL("/mm/pop/mmA034_03/deleteDatagridMstInfo");
            		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteDatagridMstInfo");
            		
            		form.submit(obj);
                }
            });
     	},
     	pasteColumn_1 : function() {
    		var obj = form.getObject("MMA034_03_form_00", "PASTE_TEXT1");
    		
    		if(isIE()) {
				alert(resource.getMessage("TXT_SORRY_EXPLORER_10"));
			} else {
	    		obj.focus();
	    		obj.trigger("paste"); // 붙여넣기 이벤트 생성
			}
    	},
    	pasteColumn_2 : function() {
    		var obj = form.getObject("MMA034_03_form_00", "PASTE_TEXT2");
    		
    		if(isIE()) {
				alert(resource.getMessage("TXT_SORRY_EXPLORER_10"));
			} else {
	    		obj.focus();
	    		obj.trigger("paste"); // 붙여넣기 이벤트 생성
			}
    	},
     	copyColumn_1 : function() {
    		var pid = form.handle.getValue("MMA034_03_form_01", "PID");
    		var headId = form.handle.getValue("MMA034_03_form_01", "HEADER_ID_1");
    		var datas = eval(pid+".datagrid."+headId);
    		var dg_1 = grid.getObject("MMA034_03_grid_01");
			
			grid.handle.clearAll(dg_1);
			
			if(!oUtil.isNull(datas)) {
            	var hdata = MMA034_03.util.getHeaderData(datas, "1");
            	
            	dg_1.datagrid('loadData', hdata);
            } else {
            	alert(resource.getMessage("NO_PROG_SOURCE"));
            }
    	},
    	copyColumn_2 : function() {
    		var pid = form.handle.getValue("MMA034_03_form_01", "PID");
    		var headId = form.handle.getValue("MMA034_03_form_01", "HEADER_ID_2");
    		var datas = eval(pid+".datagrid."+headId);
    		var dg_1 = grid.getObject("MMA034_03_grid_02");
			
			grid.handle.clearAll(dg_1);
			
            if(!oUtil.isNull(datas)) {
            	var hdata = MMA034_03.util.getHeaderData(datas, "2");
            	
            	dg_1.datagrid('loadData', hdata);
            } else {
            	alert(resource.getMessage("NO_PROG_SOURCE"));
            }
    	}
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	drowSetData : function(num) {
    		var pval;
    		
    		if(num == "1") {
    			pval = form.handle.getValue("MMA034_03_form_00", "PASTE_TEXT1");
    		} else if(num == "2") {
    			pval = form.handle.getValue("MMA034_03_form_00", "PASTE_TEXT2");
			}
    		try {
    			var dg_1;
    			var datas;
    			
    			if(pval.startsWith("[") && pval.endsWith["]"]) { // json타입으로 인식
    				datas = eval(pval);
    			} else { // 스페이스로 구분된 텍스트로 인식
    				var csvarray = [];
    				var rows = pval.split('\n');
    				
    			    for(var i = 0; i < rows.length; i++){
    			        csvarray.push(rows[i].split('\t'));
    			    }
    			    
    			    datas = csvarray;
    			}
    			
    			if(num == "1") {
    			    dg_1 = grid.getObject("MMA034_03_grid_01");
    			} else if(num == "2") {
    			    dg_1 = grid.getObject("MMA034_03_grid_02");
    			}
    			
    			grid.handle.clearAll(dg_1);
    			
    			// 이스케이프된 문자를 복원시킨다.
                if(datas.length > 0) {
                	var hdata = MMA034_03.util.getHeaderData(datas, num);
                	
                	dg_1.datagrid('loadData', hdata);
                }
    		} catch(e) {
    			alert(resource.getMessage("NOT_READ_DATA"));
    		}
    	},
    	convertMessageCode : function(val, row, index) {
    		var title = resource.getSplitMessage(val); 
    		
    		if(oUtil.isNull(title)) title = " ";
    		
    		return val + " ▶ " + title;//"<span title=\""+val+"\" class=\"easyui-tooltip\">"+title+"<span>";
    	},
    	showFileName : function(num) {
    		
    	},
    	viewApplyDatagrid : function() {
        	var tdg = form.handle.getValue("MMA034_03_form_01", "GRID_ID");
    		var pid = form.handle.getValue("MMA034_03_form_01", "PID");
    		
    		var obj = grid.getObject(tdg);
    		var prop = grid.getInitConfig(obj);
    		
    		if(!oUtil.isNull(prop.recallFunction)) {
    			var pro_id = eval("window." + pid + ".datagrid");
    			
    			grid.handle.clearAll(obj);
    			
                if (!oUtil.isNull(pro_id)) {
                    if (typeof(pro_id[prop.recallFunction]) == "function") {
                        pro_id[prop.recallFunction]();
                    }
                }
    		}
        },
        insertRow : function(i) {
        	var dg_1 = grid.getObject("MMA034_03_grid_0"+i);
        	var rowCnt = grid.handle.getRowCount(dg_1);
        	var data = MMA034_03.util.getInitRowData(rowCnt+1);
        	
        	grid.handle.appendRow(dg_1, data);
        },
        deleteRow : function(i) {
        	var dg_1 = grid.getObject("MMA034_03_grid_0"+i);
        	
        	if(!grid.handle.isSelected(dg_1)) {
        		alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	var idx = grid.handle.getCurrentRowIndex(dg_1);
        	
        	grid.handle.removeRow(dg_1, idx);
        },
        moveRow : function(i, step) {
        	var dg_1 = grid.getObject("MMA034_03_grid_0"+i);
        	var rows = grid.handle.getAllRows(dg_1);
        	var row = grid.handle.getSelectedRowData(dg_1);
        	
        	if (row){
        		var index = grid.handle.getCurrentRowIndex(dg_1);
        		var chgIdx;
        		
        		if(step == 1 || step == -1) {
	        		if(index == 0 && step == -1) return;
	        		if(index == (rows.length-1) && step == 1) return;
	        		
	        		chgIdx = index+step;
        		} else if(step == 0) {
        			chgIdx = 0;
        		} else if(step == 9) {
        			chgIdx = rows.length-1;
        		}
        		
        		rows.splice(index, 1); // 현재열에서 삭제
        		rows.splice(chgIdx, 0, row); // 현재열에서 위아래있는 값을 저장
        		
        		dg_1.datagrid('loadData',rows);
				dg_1.datagrid('scrollTo',chgIdx);
                dg_1.datagrid('selectRow',chgIdx);
        	}
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA034_03_grid_01");
            var fobj = form.getObject("MMA034_03_form_01");
            
            form.handle.setValue("MMA034_03_form_01", "HEADER_NO", "1");
            form.init.setURL("/mm/cbox/selectGridColumnSetList.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("HEADER_COLNUM")+"_1", resource.getMessage("LIST"));
        },
        excelDownload_2 : function() {
            var dg_1 = grid.getObject("MMA034_03_grid_02");
            var fobj = form.getObject("MMA034_03_form_01");
            
            form.handle.setValue("MMA034_03_form_01", "HEADER_NO", "2");
            form.init.setURL("/mm/cbox/selectGridColumnSetList.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("HEADER_COLNUM")+"_2", resource.getMessage("LIST"));
        }
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		var obj1 = form.getObject("MMA034_03_form_00", "PASTE_TEXT1");
    		var obj2 = form.getObject("MMA034_03_form_00", "PASTE_TEXT2");
    		
    		obj1.bind('paste', function(e) {
    			navigator.clipboard.readText().then(clipText => form.handle.setValue("MMA034_03_form_00", "PASTE_TEXT1", clipText));
//    			navigator.clipboard.readText().then(text => {
//    				form.handle.setValue("MMA034_03_form_00", "PASTE_TEXT1", text);
//    		    });
			});
    		
    		obj1.bind('propertychange change keyup paste input', function() {
    			setTimeout(function() {
        			MMA034_03.ui.drowSetData("1");
        		}, 500);
			});
    		
    		obj2.bind('paste', function(e) {
    			navigator.clipboard.readText().then(clipText => form.handle.setValue("MMA034_03_form_00", "PASTE_TEXT2", clipText));
//    			navigator.clipboard.readText().then(text => {
//    				form.handle.setValue("MMA034_03_form_00", "PASTE_TEXT2", text);
//    		    });
			});
    		
    		obj2.bind('propertychange change keyup paste input', function() {
    			setTimeout(function() {
        			MMA034_03.ui.drowSetData("2");
        		}, 500);
			});
    		
    		var tdg = form.handle.getValue("MMA034_03_form_01", "GRID_ID");
    		var tobj = grid.getObject(tdg);
    		var prop = grid.getInitConfig(tobj);
    		
    		form.handle.setValue("MMA034_03_form_01", "HEADER_ID_1", prop.HEADER_ID_1);
    		form.handle.setValue("MMA034_03_form_02", "HEADER_ID_1", prop.HEADER_ID_1);
    		form.handle.setValue("MMA034_03_form_01", "HEADER_ID_2", prop.HEADER_ID_2);
    		form.handle.setValue("MMA034_03_form_03", "HEADER_ID_2", prop.HEADER_ID_2);
    		
    		if(!oUtil.isNull(prop.HEADER_ID_2)) {
    			form.util.setVisible("MMA034_03_p_01", true);
    			form.util.setVisible("MMA034_03_tr_01", true);
    		}
    	}
    },
    util : {
    	getHeaderData : function(datas, num) {
    		var hdata = new Array();
    		var maplist = new Array();
    		
    		// 해더 2행인 경우, 1행정보에서 연결할 정보를 찾는다.
    		if(num == "2") {
    			var dg_1 = grid.getObject("MMA034_03_grid_01");
         		var dg_data1 = grid.handle.getAllRows(dg_1);
         		var dg_count1 = grid.handle.getRowCount(dg_1);
         		
         		for(var i = 0; i < dg_count1; i++) {
         			var cspan = dg_data1[i].CELL_COLSPAN;
         			
         			if(oUtil.isNull(cspan)) cspan = "0";
         			
         			cspan = parseInt(cspan);
         				
         			if(cspan > 0) {
         				var field = dg_data1[i].CELL_FIELD;
         				
         				for(var c = 0; c < cspan; c++) {
         					maplist.push(field);
         				}
         			}
         		}
    		}
    		
    		for(var i in datas) {
        		var hcell = new Object();
        		var existsData = false;
        		
                for(var k in datas[i]) {
                	var indata = datas[i][k];
                	
                	if(oUtil.isNull(indata)) {
                		indata = "";
                	} else if(typeof indata === "object") {
                		indata = JSON.stringify(indata);
                		existsData = true;
                	} else {
                		indata = indata.toString();
                		existsData = true;
                	}
                	
                	if(k == 0) hcell.CELL_FIELD     = indata; 
                	if(k == 1) hcell.CELL_TITLE     = indata;
                	if(k == 2) hcell.CELL_WIDTH     = indata;
                	if(k == 3) hcell.CELL_ALIGN     = indata;
                	if(k == 4) hcell.CELL_HALIGN    = indata;
                	if(k == 5) hcell.CELL_SORTABLE  = indata;
                	if(k == 6) hcell.CELL_HIDDEN    = indata;
                	if(k == 7) hcell.CELL_EDITOR    = indata;
                	if(k == 8) hcell.CELL_CHECKBOX  = indata;
                	if(k == 9) hcell.CELL_FORMATTER = indata;
                	if(k == 10) hcell.CELL_ROWSPAN  = indata;
                	if(k == 11) hcell.CELL_COLSPAN  = indata;
                	if(k == 12) hcell.CELL_FROZEN   = indata;
                	if(k == 13) hcell.CELL_STYLER   = indata;
                	if(k == 14) hcell.CELL_SPAN_CNT = indata;
                }
                
                if(num == "2") {
            		hcell.FIELD_MAPPING_ID = maplist[i];
            	}
                
                if(existsData == true) hdata.push(hcell);
            }
    		
    		return hdata;
    	},
    	getInitRowData : function(idx) {
    		var hcell = new Object();
    		
    		hcell.CELL_FIELD     = "COLUMN_"+idx; 
        	hcell.CELL_TITLE     = "CC_";
        	hcell.CELL_WIDTH     = "100";
        	hcell.CELL_ALIGN     = "center";
        	hcell.CELL_HALIGN    = "center";
        	hcell.CELL_SORTABLE  = "true";
        	hcell.CELL_HIDDEN    = "false";
        	hcell.CELL_EDITOR    = null;
        	hcell.CELL_CHECKBOX  = null;
        	hcell.CELL_FORMATTER = null;
        	hcell.CELL_ROWSPAN   = "0";
        	hcell.CELL_COLSPAN   = "0";
        	hcell.CELL_FROZEN    = null;
        	hcell.CELL_STYLER    = null;
        	hcell.CELL_SPAN_CNT  = null;
        	
        	return hcell;
    	}
    }
    
};
