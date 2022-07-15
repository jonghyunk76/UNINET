/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_15
 * 작업일자 : 2020.09.10
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_15.config.applyFtaNation();
    MMB002_15.init.initComponent();
}

var MMB002_15 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_15.combobox.initCombo_1();
        	MMB002_15.datagrid.initGrid_1();
        	MMB002_15.tool.makeTooltip();        	
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
        dataSearchType : 
        	[
        		{CODE : "OVSEA_BCNC_MTLTY", NAME : resource.getMessage("CC_해외거래처상호")},
            	{CODE : "OVSEA_BCNC_CD", NAME : resource.getMessage("CC_해외거래처코드")}
        	],
        initCombo_1 : function() {
            var objSearchType = combo.getObject("MMB002_15_form_01", "schKeyField");
            
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchType);
            
            var objSearchKeyLike = combo.getObject("MMB002_15_form_01", "schKeyLike");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchKeyLike);
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("MMB002_15_grid_01");
            
            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_MMB002_15_grid_01 : function(rowData) {
        	var pid = form.handle.getValue("MMB002_15_form_01", "TARGET_PID");
        	var field = form.handle.getValue("MMB002_15_form_01", "FIELD_ID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_15"]) == "function") {
                	rowData.FIELD_ID = field;
                    pro_id["callByMMB002_15"](rowData);
                }
            }
        },
        executeCustomerToUinpass : function() { 
            MMB002_15.control.selectOverBcncList();
        }
    },
    // 업무구현
    control : {
    	// 해외거래처 리스트 조회
    	selectOverBcncList : function() {
    		var dg_1 = grid.getObject("MMB002_15_grid_01");
    		var params = form.handle.getElementsParam("MMB002_15_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB002_15/selectOverBcncList", params);
    	},
    	selectRowValue : function(type) {
            var dg_1 = grid.getObject("MMB002_15_grid_01");
            var rowData = {};
            
            if (type == "Y") {
            	// 그리드 선택
            	rowData = grid.handle.getSelectedRowData(dg_1);
            	
	            if(!grid.handle.isSelected(dg_1)) {
	                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
	                return;
	            }            	
            } else {
            	rowData.OVSEA_BCNC_CD = "";
            	rowData.OVSEA_BCNC_MTLTY = "";
            }

            MMB002_15.event.onDblClick_MMB002_15_grid_01(rowData);
        },
        executeCustomerToUinpass : function() {
            var key = form.handle.getValue("MMB002_15_form_02", "P_PARA_VAL_03");
            
            if(oUtil.isNull(key)) {
                alert(resource.getMessage("CC_관세청 인터페이스를 위한 인증키를 찾을 수 없습니다.<br>(환경설정 > OpenAPI 항목메뉴에서 인증을 등록한 후 다시 시도해 주시기 바랍니다.)"));
                return;
            }
            
            var obj = form.getObject("MMB002_15_form_02");
            
            form.init.setURL("/fm/sm/sm7024_01/selectOpenAPICallSelectList");
            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("executeCustomerToUinpass");
            
            form.submit(obj);
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {}, 
    // 파일 input 엘리먼트 구현
    file : {
    },
    tool : {
		tagID : "MMB002_01_tool_01",
		gridID : "MMB002_01_mtgrid_01",
		fieldID : "MMB002_01_field_01",
		makeTooltip : function() {
			form.util.setSearchTooltip(this.tagID, this.gridID, this.fieldID, "initGrid", 250, 450);
		},
		showTooltip : function() {
			form.util.showTooltip(this.tagID);
		},
		initGrid : function() {
			var aryData = new Array();
			var datas = new Object();

			datas.data = "";
			aryData.push(datas);

			grid.util.drawSearchGrid(this.gridID, aryData);
		},
		selectMultiTool : function() {
			var mt_1 = grid.getObject(this.gridID);
			var fname = "MMB002_01_form_01";

			if (!mt_1.datagrid("getPanel")) {
				return;
			}

			var rows = grid.handle.getAllRows(mt_1);
			var bindVal = null;

			if (rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					if (i == 0) {
						bindVal = "'" + replaceAll(rows[i].KEY_WORD.toUpperCase(), "'", "")
								+ "',";
					} else {
						bindVal = bindVal + "'" + replaceAll(rows[i].KEY_WORD.toUpperCase(), "'", "") + "',";
					}
				}

				bindVal = bindVal.substring(0, bindVal.length - 1);
				form.handle.setValue(fname, "multiData", bindVal);

				var params = form.handle.getElementsParam(fname);

				MMB002_01.control.selectOverBcncList(params, "m");
			}
		}
	}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
