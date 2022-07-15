/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_27
 * 작업일자 : 2022.06.21
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_27.config.applyFtaNation();
    MMB002_27.init.initComponent();	
}

var MMB002_27 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {	
			MMB002_27.combobox.initCombo_1();
			MMB002_27.combobox.initCombo_2();
			MMB002_27.combobox.initCombo_3();
			
        	MMB002_27.datagrid.initGrid_1();
        	
        	MMB002_27.tool.makeTooltip();
        	
			setTimeout(function () {
				form.getObject("MMB002_27_form_01", "schKeyWord").focus();
			}, 300);
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {        	
		data_1 : [
       		{CODE:"CB.BCNC_MTLTY", NAME:resource.getMessage("CC_거래처명")},
        	{CODE:"C.BCNC_CD", NAME:resource.getMessage("CC_거래처코드")}
       	],
        initCombo_1 : function() {
            var obj = combo.getObject("MMB002_27_form_01", "schKeyField");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMB002_27_form_01", "schKeyLike");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_3 : function() {
			var impExpType = form.handle.getValue("MMB002_27_form_01", "IMP_EXP_TYPE");
            var obj = combo.getObject("MMB002_27_form_02", "MOD_BATCH_ITEM");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_M_SETUP_BATCH_MODIFY_ITEM", MESSAGE_CODE:"SELET", TXT_VALUE1:impExpType});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        }        
	},
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
			var impExpType = form.handle.getValue("MMB002_27_form_01", "IMP_EXP_TYPE");
    		var dg_1 = grid.getObject("MMB002_27_grid_01");
            
            grid.init.setPage(true);            
            grid.init.setPageList([300, 500, 1000]);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");            
            
            if (impExpType == "1") {
				Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
			} else if (impExpType == "2") {
				Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header2"}});
			}            
        }
    },
    // 이벤트 처리
    event : {
    	updateBcncSetupBatchModify : function() {
           	var formName = "MMB002_27_form_01";
    		var params = form.handle.getElementsParam(formName);
    		MMB002_27.control.selectClientList(params, "m");
    		
    		// 호출 리스트 재 조회
        	var pid = form.handle.getValue("MMB002_27_form_01", "TARGET_PID");
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_27"]) == "function") { 
                    pro_id["callByMMB002_27"]();
                }
            }    		
    	}
    },
    // 업무구현
    control : {
    	selectClientList : function() {
    		var dg_1 = grid.getObject("MMB002_27_grid_01");
    		var params = form.handle.getElementsParam("MMB002_27_form_01");
    		var impExpType = form.handle.getValue("MMB002_27_form_01","IMP_EXP_TYPE");
    		var url;
    		
    		if (impExpType == "1") {
				// 기본값일괄수정 수출 리스트 조회
				url = "/mm/pop/MMB002_27/selectExpBcncSetupList";
			} else if (impExpType == "2") {
				// 기본값일괄수정 수입 리스트 조회
				url = "/mm/pop/MMB002_27/selectImpBcncSetupList";
			}	
    		params.ORDER_BY_TYPE = form.handle.getRadioValue("MMB002_27_form_02", "ORDER_BY_TYPE");
    		
    		grid.handle.sendRedirect(dg_1, url, params);
    	},
    	//	기본값일괄 수정
    	updateBcncSetupBatchModify : function() {
			var formName = "MMB002_27_form_02";
    		if(!form.handle.isValidate(formName)) return;
    		
    		var dg_1 = grid.getObject("MMB002_27_grid_01");
        	
        	// 체크된 데이터가 있는지 확인
            if(!grid.handle.isChecked(dg_1)) {
                alert(resource.getMessage("MSG_NO_CHECK"));
                return;
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("CC_체크된 란에 일괄 적용하시겠습니까?"), function(flag) {
                if (flag) {
                	var chks = grid.handle.getCheckedRowsData(dg_1); // 체크된 데이터
                	var rowParam = grid.util.convertJson2Rows(chks);
                	
                	form.handle.setValue(formName, "gridData", rowParam);
                	
                	var obj = form.getObject(formName);
                    
                	form.init.setURL("/mm/pop/MMB002_27/updateBcncSetupBatchModify");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateBcncSetupBatchModify");
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {}, 
    // 파일 input 엘리먼트 구현
    file : {},
    tool : {
		tagID : "MMB002_27_tool_01",
		gridID : "MMB002_27_mtgrid_01",
		fieldID : "MMB002_27_field_01",
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
			var fname = "MMB002_27_form_01";

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

				MMB002_27.control.selectClientList(params, "m");
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
