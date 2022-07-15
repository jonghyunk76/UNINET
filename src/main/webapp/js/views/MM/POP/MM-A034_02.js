/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A034_02
 * 작업일자 : 2020-03-24
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA034_02.config.applyFtaNation();
    MMA034_02.init.initComponent();
}

var MMA034_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA034_02.datagrid.initGrid_1();
        	MMA034_02.datagrid.initGrid_2();
        	
        	MMA034_02.combobox.initCombo_1();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	data_1 : [
    		{CODE:"1", NAME:"1"}
    		,{CODE:"2", NAME:"2"}
    	],
    	initCombo_1 : function() {
            var obj = combo.getObject("MMA034_02_form_01", "HEADER_NO");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setCallFunction("changeHeaderNo");

            combo.create(obj);
        }
    },
    // 셀에디터 모드 설정
    editor : {
    	data_1 : [
    		 {CODE:" ", NAME:resource.getMessage("NOAPY")}
    		,{CODE:"ASC", NAME:"ASC"}
    		,{CODE:"DESC", NAME:"DESC"}
    	],
        gridCombo_1 : function(obj) {
            var opts = new Object;
            
            opts.data = this.data_1;
            opts.valueField = "CODE";
            opts.textField = "NAME";
            opts.required = false;
            opts.panelHeight = "auto";
            
            return opts;
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	header_1 : [
    		["CHECK"               ,resource.getMessage("CHECK")            ,50   ,"center"    ,"center"      ,false    ,false    ,null    ,true    ,null    ,0    ,0    ,true],
        	["CELL_TITLE"          ,resource.getMessage("TXT_COLUMN_NAME")  ,185  ,"left"      ,"center"      ,false    ,false    ,null    ,null    ,{format:"function", pid:"MMA034_02", name:"changeColumnName"}    ,0    ,0],
        	["USER_SORT_TYPE"      ,resource.getMessage("SORT")             ,80   ,"center"    ,"center"      ,false    ,false    ,{type:'combobox', options:"gridCombo_1"}, null, null    ,0    ,0]
        ],
        header_2 : [
        	["CHECK",           resource.getMessage("CHECK"),                100, "left",     "center", true, false, null, true, null, 0, 0, true],
        	["CELL_TITLE"      ,resource.getMessage("TXT_COLUMN_NAME")      ,230,"left"      ,"center"      ,false    ,false    ,null    ,null    ,{format:"function", pid:"MMA034_02", name:"changeColumnName"}    ,0    ,0]
        ],
    	initGrid_1 : function() {
    		var params = form.handle.getElementsParam("MMA034_02_form_01");
    		params.CELL_HIDDEN = false;
    		params.HEADER_NO = "1";
    		var focusObj = form.getObject("MMA034_02_form_01", "HEADER_NO");
    		var dg_1 = grid.getObject("MMA034_02_grid_01");
            
    		grid.init.setURL("/mm/cbox/selectGridColumnSetList");
			grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setEditMode(true, "cell");
            grid.init.autoAddRow(false);
            grid.init.setSingleSelect(true);
            grid.init.setShowEditorFilm(false); // 비에디팅 셀에 흐른 막을 보여줄지 여부를 설정
            grid.init.setEmptyMessage(false);
            grid.init.setNextFocus(focusObj); // 셀 에디팅을 빠져나간 후 이동할 객체 지정
            grid.init.setCallBackFunction("enableEditCell"); // 조회 시 데이터가 있으면 에디팅할 셀을 활성화 시킴
            
            // 이벤트 설정
            grid.event.setOnClickCell(dg_1);
            grid.event.setOnCellEdit(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1);
        },
        initGrid_2 : function() {
        	var params = form.handle.getElementsParam("MMA034_02_form_01");
        	params.CELL_HIDDEN = true;
        	params.HEADER_NO = "1";
        	var dg_1 = grid.getObject("MMA034_02_grid_02");
            
        	grid.init.setURL("/mm/cbox/selectGridColumnSetList");
			grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setFitColumns(true);
            grid.init.setRownumbers(false);
            grid.init.setEmptyMessage(false);

            Theme.defaultGrid(dg_1, this.header_2);
        }
    },
    // 이벤트 처리
    event : {
    	initCnt : 0,
    	changeHeaderNo : function(data) {
    		if(this.initCnt > 0) {
	    		MMA034_02.control.selectGridColumnSetList("false");
	    		MMA034_02.control.selectGridColumnSetList("true");
    		}
    		this.initCnt++;
    	},
    	insertUserDatagridInfo : function() {
    		var tdg = form.handle.getValue("MMA034_02_form_01", "GRID_ID");
    		var pid = form.handle.getValue("MMA034_02_form_01", "PID");
    		
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
    	deleteUserDatagridInfo : function() {
    		MMA034_02.control.selectGridColumnSetList("false");
    		MMA034_02.control.selectGridColumnSetList("true");
    	}
    },
    // 업무구현
    control : {
    	selectGridColumnSetList : function(ptype) {
     		var dg_1;
     		var params = form.handle.getElementsParam("MMA034_02_form_01");
     		
     		if(ptype == "false") {
     			dg_1 = grid.getObject("MMA034_02_grid_01");
     		} else {
     			dg_1 = grid.getObject("MMA034_02_grid_02");
     		}
     		params.CELL_HIDDEN = ptype;
     		
     		grid.handle.sendRedirect(dg_1, "/mm/cbox/selectGridColumnSetList", params);
     	},
     	insertUserDatagridInfo : function() {
     		if(!form.handle.isValidate("MMA034_02_form_01")) return;
     		
     		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {                
                if(flag) {
                	// 에디팅모드 삭제
                	
                	var dg_1 = grid.getObject("MMA034_02_grid_01");
             		var dg_data1 = grid.handle.getAllRows(dg_1);
             		var dg_2 = grid.getObject("MMA034_02_grid_02");
             		var dg_data2 = grid.handle.getAllRows(dg_2);
             		
             		dg_1.datagrid('endCellEdit');
             		dg_2.datagrid('endCellEdit');
             		
             		form.handle.setValue("MMA034_02_form_01", "gridData1", grid.util.convertJson2Rows(dg_data1));
             		form.handle.setValue("MMA034_02_form_01", "gridData2", grid.util.convertJson2Rows(dg_data2));
             		
             		var obj = form.getObject("MMA034_02_form_01");
            		
            		form.init.setURL("/mm/cbox/insertUserDatagridInfo");
            		form.init.setCallBackFunction("insertUserDatagridInfo");
            		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_FAILED_BODY"));
                    
            		form.submit(obj);
                }
            });
     	},
     	deleteUserDatagridInfo : function() {
     		$.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("TXT_INIT_PROCESS"), function(flag) {                
                if(flag) {
                	var obj = form.getObject("MMA034_02_form_02");
            		
            		form.init.setURL("/mm/cbox/deleteUserDatagridInfo");
            		form.init.setCallBackFunction("deleteUserDatagridInfo");
            		
            		form.submit(obj);
                }
            });
     	}
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	tabMove : function(idx) {
            var obj = tab.getObject("MMA034_02_tabs_01");
            var num = tab.handle.getTabIndex(obj);
            
            for(var i = 1; i < 3; i++) {
                if(idx == i) {
                    form.util.addClass("MMA034_02_li_0"+i, "on");
                } else {
                    form.util.removeClass("MMA034_02_li_0"+i, "on");
                }
            }
            
            tab.handle.select(obj, idx - 1);
        },
        hiddenColunm : function() {
        	var dg_1 = grid.getObject("MMA034_02_grid_01");
        	var crows = grid.handle.getCheckedRowsData(dg_1);
        	var arows = grid.handle.getAllRows(dg_1);
        	
        	// 우측 그리드에 데이터 추가
        	var dg_2 = grid.getObject("MMA034_02_grid_02");
        	for(var k = 0; k < crows.length; k++) {
        		if(crows[k].CELL_CHECKBOX != "true") {
        			grid.handle.appendRow(dg_2, crows[k]);
        		}
        	}
        	
        	// 좌측 그리드 데이터 삭제
    		for(var i = (arows.length - 1); i >= 0; i--) {
    			var chk = false;
    			
    			for(var k = 0; k < crows.length; k++) {
    				if(arows[i].CELL_FIELD == crows[k].CELL_FIELD && crows[k].CELL_CHECKBOX != "true") {
    					chk = true;
    					break;
    				}
    			}
    			
    			if(chk) grid.handle.removeRow(dg_1, i);
    		}
        },
        showColunm : function() {
        	var dg_2 = grid.getObject("MMA034_02_grid_02");
        	var crows = grid.handle.getCheckedRowsData(dg_2);
        	var arows = grid.handle.getAllRows(dg_2);
        	
        	// 좌측 그리드에 데이터 추가
        	var dg_1 = grid.getObject("MMA034_02_grid_01");
        	for(var k = 0; k < crows.length; k++) {
        		grid.handle.appendRow(dg_1, crows[k]);
        	}
        	
        	// 좌측 그리드 데이터 삭제
    		for(var i = (arows.length - 1); i >= 0; i--) {
    			var chk = false;
    			
    			for(var k = 0; k < crows.length; k++) {
    				if(arows[i].CELL_FIELD == crows[k].CELL_FIELD) {
    					chk = true;
    					break;
    				}
    			}
    			
    			if(chk) grid.handle.removeRow(dg_2, i);
    		}
        },
        moveRow : function(step) {
        	var dg_1 = grid.getObject("MMA034_02_grid_01");
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
        },
        changeColumnName : function(value, row, index) {
        	var num = combo.handle.getValue("MMA034_02_form_01", "HEADER_NO");
        	
        	if(num == "1") {
	        	if(row.SUB_MAPPING_YN == "Y") {
	        		return resource.getSplitMessage(value) + " ▶  <a href=\"javascript:MMA034_02.ui.movePage('2');\" style=\"color:#7b7b7b;\">"+resource.getMessage("COLMGMT")+"</a>";
	        	} else {
	        		return resource.getSplitMessage(value);        		
	        	}
        	} else {
        		var msg = resource.getSplitMessage(value);
        		return "<a href=\"javascript:MMA034_02.ui.movePage('1');\" style=\"color:#7b7b7b;\">" + resource.getSplitMessage(row.FIELD_MAPPING_NAME) + "</a> ▶  "+msg;
        	}
        },
	    movePage : function(num) {
        	combo.handle.setValue("MMA034_02_form_01", "HEADER_NO", num);
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		var tdg = form.handle.getValue("MMA034_02_form_01", "GRID_ID");
    		var tobj = grid.getObject(tdg);
    		var prop = grid.getInitConfig(tobj);
    		
    		if(!oUtil.isNull(prop.USER_ATTRIBUTE1)) {
    			form.handle.setValue("MMA034_02_form_01", "USER_ATTRIBUTE1", prop.USER_ATTRIBUTE1);
    			form.util.setVisible("MMA034_02_div_01", true);
    		}
    	}
    }
    
};
