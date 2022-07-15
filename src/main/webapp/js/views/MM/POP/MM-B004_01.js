/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B004_01
 * 작업일자 : 2020.05.15
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB004_01.config.applyFtaNation();
    MMB004_01.init.initComponent();
}

var MMB004_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB004_01.calendar.initCalendar_1();
        	MMB004_01.calendar.initCalendar_2();
        	MMB004_01.calendar.initCalendar_3();
        	MMB004_01.calendar.initCalendar_4();
        	
        	MMB004_01.combobox.initCombo_1();
        	MMB004_01.combobox.initCombo_2();
        	MMB004_01.combobox.initCombo_3();
        	MMB004_01.combobox.initCombo_4();
        	MMB004_01.combobox.initCombo_5();
        	MMB004_01.combobox.initCombo_6();
        	MMB004_01.combobox.initCombo_7();
        	MMB004_01.combobox.initCombo_8();
        	MMB004_01.combobox.initCombo_9();
        	MMB004_01.combobox.initCombo_10();
        	MMB004_01.combobox.initCombo_11();
        	MMB004_01.combobox.initCombo_12();
        	MMB004_01.combobox.initCombo_13();
        	
        	MMB004_01.datagrid.initGrid_1();
        	MMB004_01.datagrid.initGrid_2();
        	
        	MMB004_01.control.selectImportCvtaApcPdoList();
        }
    }, 
    // 달력 생성
    calendar : {
    	initCalendar_1 : function() {
        	var obj_01 = calendar.getObject("MMB004_01_form_02", "REQST_DE");
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	var obj_01 = calendar.getObject("MMB004_01_form_03", "ISSU_DE");
            
        	calendar.init.setInitDate("none");
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        },
        initCalendar_3 : function() {
        	var obj_01 = calendar.getObject("MMB004_01_form_03", "TKOFF_DE");
            
        	calendar.init.setInitDate("none");
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        },
        initCalendar_4 : function() {
        	var obj_01 = calendar.getObject("MMB004_01_form_03", "TRSH_DE");
            
        	calendar.init.setInitDate("none");
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        }
    }, 
    // 콤보박스 생성
    combobox : {
       	data_2 : [
    		{CODE:"9", NAME:"9."+resource.getMessage("CC_최초전송")},
    		{CODE:"35", NAME:"35."+resource.getMessage("RESND")}
    	],
    	data_3 : [
    		{CODE:" ", NAME:resource.getMessage("NOSEL")},
    		{CODE:"A", NAME:resource.getMessage("CC_A.제품")},
    		{CODE:"B", NAME:resource.getMessage("CC_B.원료")}
    	],
    	data_4 : [
    		{CODE:" ", NAME:resource.getMessage("NOSEL")},
    		{CODE:"Y", NAME:"Y"},
    		{CODE:"N", NAME:"N"}
    	],
    	data_5 : [
    		{CODE:"INSTT_NM", NAME:resource.getMessage("CC_기관명")},
    		{CODE:"TKOFF_DE", NAME:resource.getMessage("CC_출항일(yyyymmdd)")},
    		{CODE:"TRSH_NATION_CD", NAME:resource.getMessage("CC_환적국(2자리)")},
    		{CODE:"TRSH_PRT_NM", NAME:resource.getMessage("CC_환적항")},
    		{CODE:"TRSH_DE", NAME:resource.getMessage("CC_환적일(yyyymmdd)")},
    		{CODE:"TRSH_AT", NAME:resource.getMessage("CC_환적여부(Y/N)")},
    		{CODE:"CNNC_CEOR_ISSU_NATION_CD", NAME:resource.getMessage("CC_연결원산지증명서 발급국가(2자리)")},
    		{CODE:"THCO_INVC_ISSU_NATION_CD", NAME:resource.getMessage("CC_제3국 송품장 발급국가(2자리)")},
    		{CODE:"THCO_INVC_ISSU_AT", NAME:resource.getMessage("CC_제3국 송품장 발급여부(Y/N)")},
    		{CODE:"PARN_IMP", NAME:resource.getMessage("CC_분할수입(Y/N)")},
    		{CODE:"PARN_ODR", NAME:resource.getMessage("CC_분할차수")},
            {CODE:"ISSU_NO", NAME:resource.getMessage("CC_발급번호")},
            {CODE:"ISSU_DE", NAME:resource.getMessage("CC_발급일자")},
            {CODE:"ORPL_ISSUER_SE", NAME:resource.getMessage("CC_원산지증명서 발급주체")},
            {CODE:"INSTT_NM", NAME:resource.getMessage("CC_기관명")},
            {CODE:"INSTT_SE_SL", NAME:resource.getMessage("CC_종류")},
            {CODE:"EXTRAC_NATION_CD", NAME:resource.getMessage("CC_적출국")},
            {CODE:"EXTRAC_PRT_NM", NAME:resource.getMessage("CC_적출항명")},
            {CODE:"CARE_NO", NAME:resource.getMessage("CC_인증수출자번호")},
            {CODE:"COTDOO", NAME:resource.getMessage("CC_원산지기준")}
    	],
       	initCombo_1 : function() {
            var obj = combo.getObject("MMB004_01_form_03", "COTDOO");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_M_CVTA_COTDOO_CD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMB004_01_form_02", "TRNS_SE");

            combo.init.setData(this.data_2);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        },
        initCombo_3 : function() {
            var obj = combo.getObject("MMB004_01_form_02", "IMPR_IDNU_SE_SL");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_M_RLNM_NO_SE_CD", MESSAGE_CODE:"SELET", SEPERATOR_STR:".", IGNORE_CODE:"'07'"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        },
        initCombo_4 : function() {
            var obj = combo.getObject("MMB004_01_form_07", "MOD_BATCH_ITEM");

            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);

            combo.create(obj);
        },
        initCombo_5 : function() {
            var obj = combo.getObject("MMB004_01_form_03", "PARN_IMP");

            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);

            combo.create(obj);
        },
        initCombo_6 : function() {
            var obj = combo.getObject("MMB004_01_form_03", "THCO_INVC_ISSU_AT");

            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);

            combo.create(obj);
        },
        initCombo_7 : function() {
            var obj = combo.getObject("MMB004_01_form_06", "ORPL_COTDOO");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"C_ORCY_DTRM_BASE_CD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            combo.init.setEditable(true);

            combo.create(obj);
        },
        initCombo_8 : function() {
            var obj = combo.getObject("MMB004_01_form_06", "ORPL_AT");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"C_ORCY_INDC_EON_TPCD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            combo.init.setEditable(true);

            combo.create(obj);
        },
        initCombo_9 : function() {
            var obj = combo.getObject("MMB004_01_form_06", "ORPL_MTH");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"C_ORCY_INDC_MCD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            combo.init.setEditable(true);

            combo.create(obj);
        },
        initCombo_10 : function() {
            var obj = combo.getObject("MMB004_01_form_06", "ORPL_EXEPT");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"C_ORCY_INDC_EXMP_RCD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            combo.init.setEditable(true);

            combo.create(obj);
        },
        initCombo_11 : function() {
            var obj = combo.getObject("MMB004_01_form_06", "PARN_IMP");

            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);
            
            combo.create(obj);
        },
        initCombo_12 : function() {
            var obj = combo.getObject("MMB004_01_form_06", "PRDUCT_MTRAL_SE");

            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(false);

            combo.create(obj);
        },
        initCombo_13 : function() {
            var obj = combo.getObject("MMB004_01_form_06", "COTDOO");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_M_CVTA_COTDOO_CD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."});
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
    		var params = form.handle.getElementsParam("MMB004_01_form_01");
    		var dg_1 = grid.getObject("MMB004_01_grid_01");
            
    		grid.init.setQueryParams(params);
    		grid.init.setURL("/mm/pop/MMB004_01/selectImportCvtaDclrtLneList");
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
            
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header1"}});
        },
        initGrid_2 : function() {
    		var dg_1 = grid.getObject("MMB004_01_grid_02");
            
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            grid.init.setShowConfigPage(true);
            grid.init.setSelectCurrentRow(true);
            grid.init.setRecallFunction("initGrid_2");
            
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, {params:{HEADER_ID:"header2"}});
        }
    },
    // 이벤트 처리
    event : {
    	onClick_MMB004_01_grid_01 : function(data) {
    		var oldNo = form.handle.getValue("MMB004_01_form_05", "IMP_CVTA_APC_RDO_LNE_SN");
        	var newNo = data.IMP_CVTA_APC_RDO_LNE_SN;
        	
        	if(!oUtil.isNull(oldNo) && oldNo != newNo) {
    			MMB004_01.control.updateImportCvtaApcPdoLneInfo("Y");
    		}
    		
    		setTimeout(function () {
    			form.handle.setValue("MMB004_01_form_05", "IMP_CVTA_APC_RDO_LNE_SN", data.IMP_CVTA_APC_RDO_LNE_SN);
        		form.handle.setValue("MMB004_01_form_05", "DOC_STLE_SE", data.DOC_STLE_SE);
        		
        		if(data.DOC_STLE_SE == "F") { // 협정관세 적용신청서
        			form.util.setVisible("MMB004_01_div_02", true);
        			form.util.setVisible("MMB004_01_div_03", false);
        		} else if(data.DOC_STLE_SE == "I") { // 수입신고서
        			form.util.setVisible("MMB004_01_div_02", false);
        			form.util.setVisible("MMB004_01_div_03", true);
        		}
        		
        		MMB004_01.control.selectImportCvtaApcPdoLneList();
            }, 500);
        },
        onClick_MMB004_01_grid_02 : function(data) {
        	var oldNo = form.handle.getValue("MMB004_01_form_04", "IMP_CVTA_APC_RDO_STARD_SN");
        	var newNo = data.IMP_CVTA_APC_RDO_STARD_SN;
        	
        	if(!oUtil.isNull(oldNo) && oldNo != newNo) {
        		MMB004_01.control.updateImpCvtaApcPdoStardInfo("Y");
            }
        	
        	setTimeout(function () {
        		form.handle.loadData("MMB004_01_form_04", data);
        	}, 500);
        },
    	selectImportCvtaApcPdoList : function(datas) {
    		form.handle.setValue("MMB004_01_form_01", "IMP_CVTA_APC_RDO_SN", datas.IMP_CVTA_APC_RDO_SN);
    		
    		form.handle.loadData("MMB004_01_form_02", datas);
    		
    		if(datas.EXPTER_NATION_CD == "CN") {
            	form.util.setVisible("MMB004_01_divMove03", true);
            }
    	},
    	selectImportCvtaApcPdoLneList : function(datas) {
    		if(datas.DOC_STLE_SE == "F") form.handle.loadData("MMB004_01_form_03", datas);
    		else if(datas.DOC_STLE_SE == "I") form.handle.loadData("MMB004_01_form_06", datas);
    	},
    	deleteImportCvtaApcPdoLneInfo : function() {
    		form.handle.reset("MMB004_01_form_03");
    		combo.handle.setValue("MMB004_01_form_03", "COTDOO", " ");
    		
    		MMB004_01.control.selectImportCvtaDclrtLneList();
    	},
    	updateImpCvtaApcPdoStardInfo : function() {
    		MMB004_01.control.selectImpCvtaApcPdoStardList();
    		MMB004_01.control.selectImpCvtaApcPdoStardTotInfo();
    	},
    	deleteImpCvtaApcPdoStardInfo : function() {
    		form.handle.reset("MMB004_01_form_04");
    		form.handle.setValue("MMB004_01_form_04", "IMP_CVTA_APC_RDO_STARD_SN", "");
    		
    		MMB004_01.control.selectImpCvtaApcPdoStardList();
    		MMB004_01.control.selectImpCvtaApcPdoStardTotInfo();
    	},
    	selectImpCvtaApcPdoStardTotInfo : function(data) {
    		form.handle.setValue("MMB004_01_form_04", "CEOR_TOT_QY", data.CEOR_TOT_QY);
    		form.handle.setValue("MMB004_01_form_04", "IMPDEC_TOT_QY", data.IMPDEC_TOT_QY);
    	},
    	updateBatchImportCvtaDclrtLne : function() {
    		var dg_1 = grid.getObject("MMB004_01_grid_01");
    		var rowData = grid.handle.getSelectedRowData(dg_1);
            
    		this.onClick_MMB004_01_grid_01(rowData);
    	}
    },
    // 업무구현
    control : {
    	selectImportCvtaApcPdoList : function() {
    		var obj = form.getObject("MMB004_01_form_01");
			
			form.init.setURL("/mm/pop/MMB004_01/selectImportCvtaApcPdoList");
			form.init.setProgressFlag(true);
			form.init.setCallBackFunction("selectImportCvtaApcPdoList");
			
			form.submit(obj);
    	},
    	selectImportCvtaDclrtLneList : function() {
    		var dg_1 = grid.getObject("MMB004_01_grid_01");					// 그리드 Object 가져오기
    		var params = form.handle.getElementsParam("MMB004_01_form_01");	// 파라미터 가져오기
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB004_01/selectImportCvtaDclrtLneList", params);	// 그리드에 데이터베이스 연계
    	},
    	selectImpCvtaApcPdoStardList : function() {
    		var dg_1 = grid.getObject("MMB004_01_grid_02");					// 그리드 Object 가져오기
    		var params = form.handle.getElementsParam("MMB004_01_form_01");	// 파라미터 가져오기
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/MMB004_01/selectImpCvtaApcPdoStardList", params);	// 그리드에 데이터베이스 연계
    	},
    	selectImpCvtaApcPdoStardTotInfo : function() {
    		var obj = form.getObject("MMB004_01_form_01");
			
			form.init.setURL("/mm/pop/MMB004_01/selectImpCvtaApcPdoStardTotInfo");
			form.init.setProgressFlag(true);
			form.init.setCallBackFunction("selectImpCvtaApcPdoStardTotInfo");
			
			form.submit(obj);
    	},
    	selectImportCvtaApcPdoLneList : function() {
    		var obj = form.getObject("MMB004_01_form_05");
			
			form.init.setURL("/mm/pop/MMB004_01/selectImportCvtaApcPdoLneList");
			form.init.setProgressFlag(true);
			form.init.setCallBackFunction("selectImportCvtaApcPdoLneList");
			
			form.submit(obj);
    	},
    	updateImportTotalCvtaApcPdoInfo : function() {
    		var obj = tab.getObject("MMB004_01_tabs_01");
            var num = tab.handle.getTabIndex(obj);
            
            if(num == 0) MMB004_01.control.updateImportCvtaApcPdoInfo(); // 거래처 정보
            else if(num == 1) MMB004_01.control.updateImportCvtaApcPdoLneInfo(); // 란사항
            else if(num == 2) MMB004_01.control.updateImpCvtaApcPdoStardInfo(); // 원산지 규격 목록
    	},
    	updateImportCvtaApcPdoInfo : function() {
        	if (!form.handle.isValidate("MMB004_01_form_02")) return;
            
            var confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("MMB004_01_form_02");
                    
                    form.init.setURL("/mm/pop/MMB004_01/updateImportCvtaApcPdoInfo");	
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    
                    form.submit(obj);
                }
            });
    	},
    	updateImportCvtaApcPdoLneInfo : function(dirSave) {
            var docStle = form.handle.getValue("MMB004_01_form_05", "DOC_STLE_SE");
            var obj = null;
            
    		if(oUtil.isNull(docStle)) {
    			alert(resource.getMessage("CC_란자료를 선택해 주세요."));
                return;
    		}
    		
            if(docStle == "F") {
            	if(!form.handle.isValidate("MMB004_01_form_03")) return;
            	else obj = form.getObject("MMB004_01_form_03");
            } else if(docStle == "I") {
            	if(!form.handle.isValidate("MMB004_01_form_06")) return;
            	else obj = form.getObject("MMB004_01_form_06");
            } 
            
            if(dirSave == "Y") {
            	form.init.setURL("/mm/pop/MMB004_01/updateImportCvtaApcPdoLneInfo");
            	form.init.setProgressFlag(false);
            	
                form.submit(obj);
            } else {
	            var confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
	            
	            $.messager.confirm(CNFIR, confMsg, function(flag) {
	                if (flag) {
	                    form.init.setURL("/mm/pop/MMB004_01/updateImportCvtaApcPdoLneInfo");	
	                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
	                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
	                    form.init.setProgressFlag(true);
	                    
	                    form.submit(obj);
	                }
	            });
            }
    	},
    	deleteImportCvtaApcPdoLneInfo : function() {
    		var dg_1 = grid.getObject("MMB004_01_grid_01");
            
            if(!grid.handle.isSelected(dg_1)) {
            	alert(resource.getMessage("CC_란자료를 선택해 주세요."));
                return;
            }
            
            var confMsg = resource.getMessage("MSG_CONFIRM_DELETE");
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("MMB004_01_form_05");
                    
                    form.init.setURL("/mm/pop/MMB004_01/deleteImportCvtaApcPdoLneInfo");	
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("deleteImportCvtaApcPdoLneInfo");
                    
                    form.submit(obj);
                }
            });
    	},
    	insertRenewImpCvtaApcPdoLneInfo : function() {
            var confMsg = resource.getMessage("CC_란사항을 초기화하고 다시 생성하시겠습니까?");
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("MMB004_01_form_01");
                    
                    form.init.setURL("/cc/ic/IC1004_01/insertRenewImpCvtaApcPdoLneInfo");	
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("deleteImportCvtaApcPdoLneInfo");
                    
                    form.submit(obj);
                }
            });
    	},
    	updateBatchImportCvtaDclrtLne : function(flags) {
    		if(!form.handle.isValidate("MMB004_01_form_07")) return;
    		
    		var dg_1 = grid.getObject("MMB004_01_grid_01");
        	
        	// 체크된 데이터가 있는지 확인
            if(!grid.handle.isChecked(dg_1)) {
                alert(resource.getMessage("MSG_NO_CHECK"));
                return;
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("CC_체크된 란에 일괄 적용하시겠습니까?"), function(flag) {
                if (flag) {
                	var chks = grid.handle.getCheckedRowsData(dg_1); // 체크된 데이터
                	var rowParam = grid.util.convertJson2Rows(chks);
                	
                	form.handle.setValue("MMB004_01_form_07", "gridData", rowParam);
                	
                	var obj = form.getObject("MMB004_01_form_07");
                    
                	form.init.setURL("/mm/pop/MMB004_01/updateBatchImportCvtaDclrtLne");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateBatchImportCvtaDclrtLne");
                    
                    form.submit(obj);
                }
            });
        },
    	updateImpCvtaApcPdoStardInfo : function(dirSave) {
    		if(oUtil.isNull(form.handle.getValue("MMB004_01_form_04", "IMP_CVTA_APC_RDO_STARD_SN"))) {
            	alert(resource.getMessage("CC_원산지 규격을 선택해 주세요."));
                return;
            }
            
    		var obj = form.getObject("MMB004_01_form_04");
    		
    		if(dirSave == "Y") {
    			form.init.setURL("/mm/pop/MMB004_01/updateImpCvtaApcPdoStardInfo");
    			form.init.setProgressFlag(false);
    			form.init.setCallBackFunction("updateImpCvtaApcPdoStardInfo");
    			
    			form.submit(obj);
    		} else {	
    			var confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
    			
    			$.messager.confirm(CNFIR, confMsg, function(flag) {
    				if (flag) {
    					form.init.setURL("/mm/pop/MMB004_01/updateImpCvtaApcPdoStardInfo");	
    					form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
    					form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
    					form.init.setProgressFlag(true);
    					form.init.setCallBackFunction("updateImpCvtaApcPdoStardInfo");
    					
    					form.submit(obj);
    				}
    			});
    		}
    	},
    	deleteImpCvtaApcPdoStardInfo : function() {
            if(oUtil.isNull(form.handle.getValue("MMB004_01_form_04", "IMP_CVTA_APC_RDO_STARD_SN"))) {
            	alert(resource.getMessage("CC_원산지 규격을 선택해 주세요."));
                return;
            }
            
            var confMsg = resource.getMessage("MSG_CONFIRM_DELETE");
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("MMB004_01_form_04");
                    
                    form.init.setURL("/mm/pop/MMB004_01/deleteImpCvtaApcPdoStardInfo");	
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("deleteImpCvtaApcPdoStardInfo");
                    
                    form.submit(obj);
                }
            });
    	},
    	insertRenewImpCvtaApcPdoStardInfo : function() {
            var confMsg = resource.getMessage("CC_원산지 규격 정보을 초기화하고 다시 생성하시겠습니까?");
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {
                if (flag) {
                    var obj = form.getObject("MMB004_01_form_01");
                    
                    form.init.setURL("/cc/ic/IC1004_01/insertRenewImpCvtaApcPdoStardInfo");	
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("deleteImpCvtaApcPdoStardInfo");
                    
                    form.submit(obj);
                }
            });
    	}
    },
    // 다이얼로그 구현
    dialog : {
    	openDialog_1 : function(field) {
            var params = new Object();
            
            params.TARGET_PID = "MMB004_01";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.FIELD_ID = field;
            
    		var dl_1 = dialog.getObject("MMB002_04_dailog_01");
            
    		if(field == "CVTA_SE_CD") { // 세율구분
    			params.CATEGORY_CD = "C_TRRT_TPCD";
    			dialog.init.setTitle(resource.getMessage("CC_[f]세율구분 조회"));
    		} else if(field == "EXPTER_NATION_CD" || field == "EXTRAC_NATION_CD2" || field == "TRSH_NATION_CD" ||
    			field == "CNNC_CEOR_ISSU_NATION_CD" || field == "THCO_INVC_ISSU_NATION_CD") { // 국가코드 
	            params.CATEGORY_CD = "C_CNTY_CD";
	            dialog.init.setTitle(resource.getMessage("CC_[f]국가 조회"));
    		}
    		
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(500);
            dialog.init.setHeight(600);
            dialog.init.setURL("/mm/pop/MMB002_04");
            
            dialog.open(dl_1);
        },
        callByMMB002_04 : function(rowData) {
        	if(rowData.FIELD_ID == "CVTA_SE_CD") { // 세율구분
	        	form.handle.setValue("MMB004_01_form_03", "CVTA_SE_CD", rowData.CODE);
        	} else if(rowData.FIELD_ID == "EXPTER_NATION_CD") { // 수출자 국가코드
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_NATION_CD", rowData.CODE);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_NATION_NM", rowData.CODE_NAME);
        	} else if(rowData.FIELD_ID == "EXTRAC_NATION_CD2") { // 적출국
	        	form.handle.setValue("MMB004_01_form_03", "EXTRAC_NATION_CD", rowData.CODE);
        	} else if(rowData.FIELD_ID == "TRSH_NATION_CD") { // 환적국
	        	form.handle.setValue("MMB004_01_form_03", "TRSH_NATION_CD", rowData.CODE);
        	} else if(rowData.FIELD_ID == "CNNC_CEOR_ISSU_NATION_CD") { // 연결원산지증명서 발급국가
	        	form.handle.setValue("MMB004_01_form_03", "CNNC_CEOR_ISSU_NATION_CD", rowData.CODE);
        	} else if(rowData.FIELD_ID == "THCO_INVC_ISSU_NATION_CD") { // 제3국 송품장 발급국가
	        	form.handle.setValue("MMB004_01_form_03", "THCO_INVC_ISSU_NATION_CD", rowData.CODE);
        	}
        	
        	var dl_1 = dialog.getObject("MMB002_04_dailog_01");
        	dialog.handle.close(dl_1);
        },
        openDialog_2 : function(field) {
            var params = new Object();
            
            params.TARGET_PID = "MMB004_01";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.FIELD_ID = field;
            
    		var dl_1 = dialog.getObject("MMB002_03_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("CC_[f]거래처 조회"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(850);
            dialog.init.setHeight(600);
            dialog.init.setURL("/mm/pop/MMB002_03");
            
            dialog.open(dl_1);
        },
        callByMMB002_03 : function(rowData) {
        	if(rowData.FIELD_ID == "I") { 			// 수입자
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_CD", rowData.BCNC_CD);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_MTLTY", rowData.BCNC_MTLTY);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_NM", rowData.NM);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_IDNU", rowData.BIZRNO);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_ECTMRK", rowData.ECTMRK);
				form.handle.setValue("MMB004_01_form_02", "IMPR_BIZRNO", rowData.BIZRNO);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_ZIP", rowData.ZIP);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_RN_CD", rowData.RN_CD);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_NABN", rowData.NABN);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_BASS_ADRES", rowData.ADRES);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_DETAIL_ADRES", rowData.DETAIL_ADRES);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_TELNO", rowData.TELNO);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_EMAIL", rowData.EMAIL);
	        	form.handle.setValue("MMB004_01_form_02", "IMPR_FAX", rowData.FXNUM);
        	} else if(rowData.FIELD_ID == "E") { 		// 수출자
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_CD", rowData.BCNC_CD);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_MTLTY", rowData.BCNC_MTLTY);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_ADRES", rowData.ADRES);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_NM", rowData.NM);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_TELNO", rowData.TELNO);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_FAX", rowData.FXNUM);
        	} else if(rowData.FIELD_ID == "P") { 	// 생산자
        		form.handle.setValue("MMB004_01_form_02", "MKER_CD", rowData.BCNC_CD);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_MTLTY", rowData.BCNC_MTLTY);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_ADRES", rowData.ADRES);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_NM", rowData.NM);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_TELNO", rowData.TELNO);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_FAX", rowData.FXNUM);
        	}
        	
        	var dl_1 = dialog.getObject("MMB002_03_dailog_01");
        	dialog.handle.close(dl_1);
        },
        openDialog_3 : function(field) {
            var params = new Object();
            
            params.TARGET_PID = "MMB004_01";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            params.FIELD_ID = field;
            
    		var dl_1 = dialog.getObject("MMB002_15_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("CC_[f]해외거래처 조회"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(850);
            dialog.init.setHeight(600);
            dialog.init.setURL("/mm/pop/MMB002_15");
            
            dialog.open(dl_1);
        },
        callByMMB002_15 : function(rowData) {
        	if(rowData.FIELD_ID == "E") { 		// 수출자
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_CD", rowData.OVSEA_BCNC_CD);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_MTLTY", rowData.OVSEA_BCNC_MTLTY);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_ADRES", rowData.ADRES);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_NM", rowData.RPRS);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_TELNO", rowData.TELNO);
	        	form.handle.setValue("MMB004_01_form_02", "EXPTER_FAX", rowData.FXNUM);
        	} else if(rowData.FIELD_ID == "P") { 	// 생산자
        		form.handle.setValue("MMB004_01_form_02", "MKER_CD", rowData.OVSEA_BCNC_CD);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_MTLTY", rowData.OVSEA_BCNC_MTLTY);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_ADRES", rowData.ADRES);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_NM", rowData.RPRS);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_TELNO", rowData.TELNO);
	        	form.handle.setValue("MMB004_01_form_02", "MKER_FAX", rowData.FXNUM);
        	}
        	
        	var dl_1 = dialog.getObject("MMB002_15_dailog_01");
        	dialog.handle.close(dl_1);
        },
        openDialog_4 : function() {
            var params = new Object();
            
            params.TARGET_PID = "MMB004_01";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            
    		var dl_1 = dialog.getObject("MMB002_18_dailog_01");
            
    		dialog.init.setTitle(resource.getMessage("CC_[f]해외 원산지 발행기관 조회"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(700);
            dialog.init.setHeight(600);
            dialog.init.setURL("/mm/pop/MMB002_18");
            
            dialog.open(dl_1);
        },
        callByMMB002_18 : function(rowData) {
        	form.handle.setValue("MMB004_01_form_03", "INSTT_NM", rowData.ISU_INSTT_NM);
        	
        	var dl_1 = dialog.getObject("MMB002_18_dailog_01");
        	dialog.handle.close(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
    	divMove : function(idx) {
            var obj = tab.getObject("MMB004_01_tabs_01");
            var num = tab.handle.getTabIndex(obj);
            
            if(num == 0 && idx != 1) {
            	if(!form.handle.isValidate("MMB004_01_form_02")) return;
            	
            	var form2 = form.getObject("MMB004_01_form_02");
                
                form.init.setURL("/mm/pop/MMB004_01/updateImportCvtaApcPdoInfo");
                form.init.setProgressFlag(false);
                
                form.submit(form2);
            } else if(num == 1 && idx != 2) {
            	var saveType = form.handle.getValue("MMB004_01_form_05", "DOC_STLE_SE");
        		
        		if(!oUtil.isNull(saveType)) {
        			MMB004_01.control.updateImportCvtaApcPdoLneInfo("Y");
        		}
            } else if(num == 2 && idx != 3) {
            	if(!oUtil.isNull(form.handle.getValue("MMB004_01_form_04", "IMP_CVTA_APC_RDO_STARD_SN"))) {
            		MMB004_01.control.updateImpCvtaApcPdoStardInfo("Y");
                }
            }
            
            for(var i = 1; i < 4; i++) {
                if(idx == i) {
                    form.util.addClass("MMB004_01_divMove0"+i, "on");
                } else {
                    form.util.removeClass("MMB004_01_divMove0"+i, "on");
                }
            }
            
            tab.handle.select(obj, idx - 1);
            
            if(idx == "3") {
            	var dg_2 = grid.getObject("MMB004_01_grid_02");
            	
            	if(grid.handle.getRowCount(dg_2) == 0) {
            		MMB004_01.control.selectImpCvtaApcPdoStardList();
            	}
            }
        },
        showFileUpload : function() {
//        	$("#MMB004_01_div_01").css("height", 100);
        	$("#MMB004_01_div_01").slideDown("fast");
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
