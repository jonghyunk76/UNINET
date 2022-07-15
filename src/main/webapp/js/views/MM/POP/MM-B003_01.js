/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B003_01
 * 작업일자 : 2020.05.15
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB003_01.config.applyFtaNation();
    MMB003_01.init.initComponent();
}

var MMB003_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
//        	MMB003_01.calendar.initCalendar_1();
        	MMB003_01.calendar.initCalendar_2();
        	MMB003_01.calendar.initCalendar_3();
        	MMB003_01.calendar.initCalendar_4();
        	MMB003_01.calendar.initCalendar_5();
        	MMB003_01.calendar.initCalendar_6();
        	MMB003_01.calendar.initCalendar_7();
        	MMB003_01.calendar.initCalendar_8();
        	
        	MMB003_01.combobox.initCombo_1();
        	MMB003_01.combobox.initCombo_3();
        	MMB003_01.combobox.initCombo_4();
        	MMB003_01.combobox.initCombo_5();
        	MMB003_01.combobox.initCombo_6();
        	MMB003_01.combobox.initCombo_7();
        	MMB003_01.combobox.initCombo_8();
        	MMB003_01.combobox.initCombo_9();
        	MMB003_01.combobox.initCombo_10();
        	MMB003_01.combobox.initCombo_11();
        	MMB003_01.combobox.initCombo_12();
        	MMB003_01.combobox.initCombo_13();
        	
        	// 기 가격신고된 정보가 있는지 조회함
        	MMB003_01.control.selectRegistImpPrnoInfo();
        }
    }, 
    // 달력 생성
    calendar : {
    	initCalendar_1 : function() {
        	var obj_01 = calendar.getObject("MMB003_01_form_01", "TRNS_RELT");
            
        	calendar.init.setRequired(true);
        	
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	var obj_01 = calendar.getObject("MMB003_01_form_01", "RCE_DE");
        	
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        },
        initCalendar_3 : function() {
        	var obj_01 = calendar.getObject("MMB003_01_form_01", "INVC_ISU_DE");
            
        	calendar.init.setInitDate("none");
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        },
        initCalendar_4 : function() {
        	var obj_01 = calendar.getObject("MMB003_01_form_01", "CNR_DE");
            
        	calendar.init.setInitDate("none");
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        },
        initCalendar_5 : function() {
        	var obj_01 = calendar.getObject("MMB003_01_form_01", "PURC_ORR_DE");
            
        	calendar.init.setInitDate("none");
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        },
        initCalendar_6 : function() {
        	var obj_01 = calendar.getObject("MMB003_01_form_01", "PC_DCSN_PREA_ERA_PARN_ERA"); // 가격_확정_예정_시기_분할_시기
            
        	calendar.init.setInitDate("none");
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        },
        initCalendar_7 : function() {
        	var obj_01 = calendar.getObject("MMB003_01_form_01", "RELA_IMP_DEAL_CNR_END_DE"); // 관련_수입_거래_계약_만료_일자
            
        	calendar.init.setInitDate("none");
        	calendar.init.setRequired(false);
        	
            calendar.create(obj_01);
        },
        initCalendar_8 : function() {
        	var obj_01 = calendar.getObject("MMB003_01_form_01", "WRI_DE"); // 작성일자
            
        	calendar.init.setRequired(true);
        	
            calendar.create(obj_01);
        }
    }, 
    // 콤보박스 생성
    combobox : {
    	data_1 : [
    		{CODE:"2", NAME:"2."+resource.getMessage("ADDTL")},
    		{CODE:"9", NAME:"9."+resource.getMessage("CC_원본")},
    		{CODE:"35", NAME:"35."+resource.getMessage("RESND")}
    	],
    	data_3 : [
    		{CODE:" ", NAME:resource.getMessage("TXT_NOT_MEET_NA1")},
    		{CODE:"Y", NAME:resource.getMessage("TXT_YES")+"(Y)"},
    		{CODE:"N", NAME:resource.getMessage("TXT_NO")+"(N)"}
    	],
    	data_4 : [
    		{CODE:" ", NAME:resource.getMessage("TXT_NOT_MEET_NA1")},
    		{CODE:"01", NAME:"1번"},
    		{CODE:"02", NAME:"2번"},
    		{CODE:"03", NAME:"3번"},
    		{CODE:"04", NAME:"4번"},
    		{CODE:"05", NAME:"5번"},
    		{CODE:"06", NAME:"6번"},
    		{CODE:"07", NAME:"7번"},
    		{CODE:"08", NAME:"8번"}
    	],
    	data_5 : [
    		{CODE:" ", NAME:resource.getMessage("TXT_NOT_MEET_NA1")},
    		{CODE:"01", NAME:"1번"},
    		{CODE:"02", NAME:"2번"},
    		{CODE:"03", NAME:"3번"},
    		{CODE:"04", NAME:"4번"},
    		{CODE:"05", NAME:"5번"},
    		{CODE:"06", NAME:"6번"},
    		{CODE:"07", NAME:"7번"},
    		{CODE:"99", NAME:"8번"}
    	],
    	initCombo_1 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "TRNS_SE"); // 전송구분

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);

            combo.create(obj);
        },
        initCombo_2 : function(type) {
            var obj = combo.getObject("MMB003_01_form_01", "PRNO_FOT_SE"); // 서식구분

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_M_PRNO_FOT_SE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setValue(type);
            combo.init.setRequired(true);
            combo.init.setCallFunction("changeImportReportType");
            
            combo.create(obj);
        },
    	initCombo_3 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "PRNO_FOT_MTH_CD"); // 과세가격 결정방법

            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            combo.init.setRequired(true);
            combo.init.setCallFunction("changeImportDefineType");
            
            combo.create(obj);
        },
        initCombo_4 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "PLFTP_IDNU_SE_SL"); // 납세의무자 식별번호 구분부호

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_M_RLNM_NO_SE_CD", MESSAGE_CODE:"SELET", SEPERATOR_STR:"."});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            combo.init.setRequired(true);
            
            combo.create(obj);
        },
        initCombo_5 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_07_A_NO");

            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        },
        initCombo_6 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_07_B_NO");

            combo.init.setData(this.data_4);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        },
        initCombo_7 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_07_C_AT");

            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        },
        initCombo_8 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_07_D_AT");

            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        },
        initCombo_9 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_07_E_NO");

            combo.init.setData(this.data_5);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        },
        initCombo_10 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_08_A_AT");

            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        },
        initCombo_11 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_08_B_AT");

            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        },
        initCombo_12 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_09_A_AT");

            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        },
        initCombo_13 : function() {
            var obj = combo.getObject("MMB003_01_form_01", "M1_AFO_09_B_AT");

            combo.init.setData(this.data_3);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setEditable(true);
            
            combo.create(obj);
        }
        
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {
    	changeImportReportType : function(data) {
    		var params = null;
    		
    		if(data == "A") {
    			params = {COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_M_CVODV_EVL_MTH_CD", IGNORE_CODE:"'2', '3', '4A', '4B', '5', '6'"};

    			form.util.setVisible("MMB003_01_typeA_01", true);
    			form.util.setVisible("MMB003_01_typeA_02", true);
    			form.util.setVisible("MMB003_01_typeB_01", false);
    			form.util.setVisible("MMB003_01_typeB_02", false);
    			form.util.setVisible("MMB003_01_typeB_03", false);
    			form.util.setVisible("MMB003_01_typeB_04", false);
    			form.util.setVisible("MMB003_01_typeB_05", false);
    			$("#MMB003_01_span_01").html("11.");
    			$("#MMB003_01_span_02").html("12.");
    			$("#MMB003_01_span_03").html("26.신고납부 과세가격(A+B-C)");
    		} else if(data == "B") {
    			params = {COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"CC_M_CVODV_EVL_MTH_CD", IGNORE_CODE:"'1'"};
    			
    			form.util.setVisible("MMB003_01_typeA_01", false);
    			form.util.setVisible("MMB003_01_typeA_02", false);
    			form.util.setVisible("MMB003_01_typeB_01", true);
    			form.util.setVisible("MMB003_01_typeB_02", true);
    			$("#MMB003_01_span_01").html("12.");
    			$("#MMB003_01_span_02").html("13.");
    		}
            
            var initVal = combo.handle.getValue("MMB003_01_form_01", "PRNO_FOT_MTH_CD");
    		if(!oUtil.isNull(params)) combo.handle.reload("MMB003_01_form_01", "PRNO_FOT_MTH_CD", "/mm/cbox/selectStandardCode", params, initVal);
    	},
    	changeImportDefineType : function(data) {
    		form.handle.setValue("MMB003_01_form_01", "M26_BFO_TATA_MTH", data);
    		
    		if(data == "2" || data == "3") { // 제2~3방법
    			form.util.setVisible("MMB003_01_typeB_03", true);
    			form.util.setVisible("MMB003_01_typeB_04", false);
    			form.util.setVisible("MMB003_01_typeB_05", false);
    			
    			$("#MMB003_01_span_03").html("19.신고 과세가격(A-B+C)");
    			MMB003_01.util.totalAmount("B2_T19");
    		} else if(data == "4A" || data == "4B") { // 제4방법
    			form.util.setVisible("MMB003_01_typeB_03", false);
    			form.util.setVisible("MMB003_01_typeB_04", true);
    			form.util.setVisible("MMB003_01_typeB_05", false);
    			
    			$("#MMB003_01_span_03").html("23.신고 과세가격(D-E)");
    			MMB003_01.util.totalAmount("B3_T23");
    		} else if(data == "5" || data == "6") { // 제5~6방법
    			form.util.setVisible("MMB003_01_typeB_03", false);
    			form.util.setVisible("MMB003_01_typeB_04", false);
    			form.util.setVisible("MMB003_01_typeB_05", true);
    			
    			$("#MMB003_01_span_03").html("19.신고 과세가격(F+G)");
    			MMB003_01.util.totalAmount("B4_T19");
    		} else {
    			MMB003_01.util.totalAmount("A_T26");
    		}
    	},
    	selectRegistImpPrnoInfo : function(data) {
    		var reportType = "";
    		
    		if(oUtil.isNull(data.IMP_PRNO_SN)) {
    			form.handle.setValue("MMB003_01_form_01", "flag", "insert");
    			
    			MMB003_01.control.selectEmptyImpPrnoInfo();
    		} else {
    			reportType= data.PRNO_FOT_SE;
    			delete data["PRNO_FOT_SE"];
    			
    			if(oUtil.isNull(data.TRNS_RELT)) delete data["TRNS_RELT"];
    			if(oUtil.isNull(data.RCE_DE)) delete data["RCE_DE"];
    			if(oUtil.isNull(data.INVC_ISU_DE)) delete data["INVC_ISU_DE"];
    			if(oUtil.isNull(data.CNR_DE)) delete data["CNR_DE"];
    			if(oUtil.isNull(data.PURC_ORR_DE)) delete data["PURC_ORR_DE"];
    			if(oUtil.isNull(data.PC_DCSN_PREA_ERA_PARN_ERA)) delete data["PC_DCSN_PREA_ERA_PARN_ERA"];
    			if(oUtil.isNull(data.RELA_IMP_DEAL_CNR_END_DE)) delete data["RELA_IMP_DEAL_CNR_END_DE"];
    			if(oUtil.isNull(data.WRI_DE)) delete data["WRI_DE"];
    			
    			form.handle.loadData("MMB003_01_form_01", data);
    			form.handle.setValue("MMB003_01_form_01", "flag", "update");
    			
    			if(!oUtil.isNull(data.PRVS_PC_STT_RESN_SL)) MMB003_01.util.checkedPrvsPcsttResn(data.PRVS_PC_STT_RESN_SL);
    			if(!oUtil.isNull(data.M26_BFO_PRPOS)) MMB003_01.util.checkedM26BfoPrpos(data.M26_BFO_PRPOS);
    			if(!oUtil.isNull(data.M26_BFO_PC_CALC_BASIS)) MMB003_01.util.checkedM26BfoPcCalcBasis(data.M26_BFO_PC_CALC_BASIS);
    		}
    		
    		if(oUtil.isNull(reportType)) reportType = "A";
    		
    		setTimeout(function () {
        		MMB003_01.combobox.initCombo_2(reportType);
            }, 500);
    	},
    	selectEmptyImpPrnoInfo : function(data) {
    		form.handle.loadData("MMB003_01_form_01", data);
    	},
    	updateImpPrnoInfo : function(data) {
    		if(!oUtil.isNull(data.IMP_PRNO_SN)) {
    			form.handle.setValue("MMB003_01_form_01", "IMP_PRNO_SN", data.IMP_PRNO_SN);
    		}
    		
    		dialog.handle.close(dialog.getObject('MMB003_01_dailog_01'));
    	},
    	deleteImpPrnoInfo : function() {
    		var dl_1 = dialog.getObject("MMB003_01_dailog_01");
        	dialog.handle.close(dl_1);
    	}
    },
    // 업무구현
    control : {
    	selectRegistImpPrnoInfo : function() {
    		var obj = form.getObject("MMB003_01_form_02");
			
			form.init.setURL("/mm/pop/MMB003_01/selectRegistImpPrnoInfo");
			form.init.setProgressFlag(true);
			form.init.setCallBackFunction("selectRegistImpPrnoInfo");
			
			form.submit(obj);
    	},
    	selectEmptyImpPrnoInfo : function() {
    		var obj = form.getObject("MMB003_01_form_03");
			
			form.init.setURL("/mm/pop/MMB003_01/selectEmptyImpPrnoInfo");
			form.init.setProgressFlag(true);
			form.init.setCallBackFunction("selectEmptyImpPrnoInfo");
			
			form.submit(obj);
    	},
    	// 가격신고서 작성
    	updateImpPrnoInfo : function() {
    		if (!form.handle.isValidate("MMB003_01_form_01")) return;		// input 유효성 체크
            
            var confMsg = "";
            var vFlag = form.handle.getValue("MMB003_01_form_01", "flag");	// Get Mode(insert, update, view)
            
            if (vFlag == "insert") {
                confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            } else {
                confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            }
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {							// 계속 실행 하시겠습니까? 물어보는 얼랏 창
                if (flag) {
                	// 수입신고번호 생성
                	var decNo1 = form.handle.getValue("MMB003_01_form_01", "APLC_CBOA_APPN");
                	var decNo2 = form.handle.getValue("MMB003_01_form_01", "SN_YY");
                	var decNo3 = form.handle.getValue("MMB003_01_form_01", "IMPDEC_SN");
                	var decNo4 = form.handle.getValue("MMB003_01_form_01", "IMPDEC_TYPE");
                	
                	form.handle.setValue("MMB003_01_form_01", "STT_NO", decNo1+decNo2+decNo3+decNo4);
                	
                	// 잠정신고 대상인 경우, 잠정신고번호 생성
                	var sttNo1 = form.handle.getValue("MMB003_01_form_01", "STT_CSMH"); // 세관
                	var sttNo2 = form.handle.getValue("MMB003_01_form_01", "STT_KWA"); // 과
                	var sttNo3 = form.handle.getValue("MMB003_01_form_01", "STT_YYYY"); // 년도
                	var prvs_sttNo1 = form.handle.getValue("MMB003_01_form_01", "PRVS_PC_STT_CSMH"); // 잠정세관코드
                    
                    if(!oUtil.isNull(prvs_sttNo1)) form.handle.setValue("MMB003_01_form_01", "PRVS_PC_STT_NO", sttNo1+sttNo2+sttNo3);
                    else  form.handle.setValue("MMB003_01_form_01", "PRVS_PC_STT_NO", "");
                	
                	// 잠정가격신고 사유 체크
                	form.handle.setValue("MMB003_01_form_01", "PRVS_PC_STT_RESN_SL", MMB003_01.util.getPrvsPcsttResnCodes());
                	// 외환거래가 수반되지 않는 경우 수입물품의 용도
                	form.handle.setValue("MMB003_01_form_01", "M26_BFO_PRPOS", MMB003_01.util.getM26BfoPrposCodes());
                	// 외환거래가 수반되지 않는 수입물품의 가격 산정 근거
                	form.handle.setValue("MMB003_01_form_01", "M26_BFO_PC_CALC_BASIS", MMB003_01.util.getM26BfoPcCalcBasisCodes());
                	
                    var obj = form.getObject("MMB003_01_form_01");
                    
                    form.init.setURL("/mm/pop/MMB003_01/updateImpPrnoInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateImpPrnoInfo");
                    
                    form.submit(obj);
                }
            });
    	},
    	deleteImpPrnoInfo : function() {
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
                    var obj = form.getObject("MMB003_01_form_01");
                    
                    form.init.setURL("/mm/pop/MMB003_01/deleteImpPrnoInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteImpPrnoInfo");
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {
    	openDialog_1 : function() {
            var params = new Object();
            
            params.TARGET_PID = "MMB003_01";
            params.COMPANY_CD = SESSION.COMPANY_CD;
            
    		var dl_1 = dialog.getObject("MMB002_03_dailog_01");
            
            dialog.init.setTitle(resource.getMessage("CC_[f]납세의무자 조회"));
            dialog.init.setQueryParams(params);
            dialog.init.setWidth(850);
            dialog.init.setHeight(600);
            dialog.init.setURL("/mm/pop/MMB002_03");
            
            dialog.open(dl_1);
        },
        callByMMB002_03 : function(rowData) {
        	form.handle.setValue("MMB003_01_form_01", "PLFTP_MTLTY", rowData.BCNC_MTLTY);
        	form.handle.setValue("MMB003_01_form_01", "PLFTP_IDNU", rowData.ECTMRK);
        	
        	var dl_1 = dialog.getObject("MMB002_03_dailog_01");
        	dialog.handle.close(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
    	toggleList : ["3","7b","7e","8a", "10"], // 접고 펴기가 지원되는 항목 리스트
    	allToggleOpen : function() {
    		for(var i = 0; i < this.toggleList.length; i++) {
    			$("#MMB003_01_div_0"+this.toggleList[i]).slideDown("fast");
    			$("#MMB003_01_label_0"+this.toggleList[i]).text("▲");
    		}
    	},
    	allToggleClose : function() {
    		for(var i = 0; i < this.toggleList.length; i++) {
    			$("#MMB003_01_div_0"+this.toggleList[i]).slideUp("fast");
    			$("#MMB003_01_label_0"+this.toggleList[i]).text("▼");
    		}
    	},
    	toggleMove : function(mn) {
    		if($("#MMB003_01_label_0"+mn).text() == "▼") {
    			$("#MMB003_01_div_0"+mn).slideDown("fast");
    			$("#MMB003_01_label_0"+mn).text("▲");
    		} else {
    			$("#MMB003_01_div_0"+mn).slideUp("fast");
    			$("#MMB003_01_label_0"+mn).text("▼");
    		}
    	}
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		var frm = form.getObject("MMB003_01_form_01");
    		
    		// TypeA
    		frm.find("input[surveyno=\"A_A14\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculateMultiply("A_A14");
    		});
            frm.find("input[surveyno=\"A_B14\"]").bind("keyup blur", function (e) {
                MMB003_01.util.calculatePlus("A_B14");
            });
    		frm.find("input[surveyno=\"A_B19\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculatePlus("A_B19");
    			MMB003_01.util.calculatePlus("A_B20");
    		});
    		frm.find("input[surveyno=\"A_B20\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculatePlus("A_B20");
    		});
    		frm.find("input[surveyno=\"A_C25\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculatePlus("A_C25");
    		});
    		
    		frm.find("input[surveyno=\"B_A14\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculateMultiply("B_A14");
    		});
    		frm.find("input[surveyno=\"B_B16\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculatePlus("B_B16");
    		});
    		frm.find("input[surveyno=\"B_C18\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculatePlus("B_C18");
    		});
    		frm.find("input[surveyno=\"B_D20\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculateMultiply("B_D20");
    		});
    		frm.find("input[surveyno=\"B_E22\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculatePlus("B_E22");
    		});
    		frm.find("input[surveyno=\"B_F24\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculatePlus("B_F24");
    		});
    		frm.find("input[surveyno=\"B_G26\"]").bind("keyup blur", function (e) {
    			MMB003_01.util.calculatePlus("B_G26");
    		});
    	}
    },
    util : {
    	calculateMultiply :function(no) {
    		var frm = form.getObject("MMB003_01_form_01");
    		var _val = "";
    		var _value = 0.00;
    		var cnt = 0;
    		
    		frm.find("input[surveyno=\""+no+"\"]").each(function () {
    			var _this = $(this).val();
    			if(oUtil.isNull(_this)) _this = "0";
    			else _this = oUtil.getFilterFloat(_this);
    			
    			if(oUtil.isNull(_val)) _val = _this;
    			else _val = _val + "*"+ _this;
    			
    			cnt++;
    		});
    		
            var val_1 = form.handle.getValue("MMB003_01_form_01", "M1_AFO_CALC_BASIS_INDRT_AMT", "0");
            if(no == "A_A14") {
                _val = _val + "+" + val_1;
            }
            
    		if(cnt > 1 && _val != "") {
    			_value = eval(_val);
    		}
    		// console.log("수식 = " + _val + " = " + _value + ", 개수 = " + cnt);
    		if(no == "A_A14") {
                // alert(_value);
                try {
        			form.handle.setValue("MMB003_01_form_01", "M1_AFO_CVER_TAMT", formatFloat(_value, 0)); // 결제금액 소수점 차리로 계산상 오류가 발생함 
                } catch(e) { }
    			this.totalAmount("A_T26");
    		} else if(no == "B_A14") {
                try {
    			    form.handle.setValue("MMB003_01_form_01", "M23_BFO_ALRN_WON_CVER_PC", formatFloat(_value, 4));
                } catch(e) { }
    			this.totalAmount("B2_T19");
    		} else if(no == "B_D20") {
                try {
    			    form.handle.setValue("MMB003_01_form_01", "M4_BFO_WON_CVER_PC", formatFloat(_value, 4));
                } catch(e) { }
    			this.totalAmount("B3_T23");
    		}
    	},
    	calculatePlus : function(no) {
    		var frm = form.getObject("MMB003_01_form_01");
    		var _val = "";
    		var _value = 0;
    		
    		frm.find("input[surveyno=\""+no+"\"]").each(function () {
    			var _this = $(this).val();
    			if(oUtil.isNull(_this)) _this = 0;
    			else _this = oUtil.getFilterFloat(_this);
    			
    			if(_val == "") _val = _this;
    			else _val = _val + "+"+ _this;
    		});
    		
            var val_1 = form.handle.getValue("MMB003_01_form_01", "M1_AFO_CALC_BASIS_PC", "0");
            if(no == "A_B14") {
                _val = _val + "+" + val_1;
            }
            
    		if(_val != "") {
    			_value = eval(_val);
    		}
    		
            if(no == "A_B14") {
                form.handle.setValue("MMB003_01_form_01", "M1_AFO_CVER_TAMT", _value);
    		} else if(no == "A_B19") {
    			form.handle.setValue("MMB003_01_form_01", "M1_AFO_ADDI_CTRN_SSUM", _value);
    		} else if(no == "A_B20") {
    			form.handle.setValue("MMB003_01_form_01", "M1_AFO_ADDI_TAMT", _value);
    			this.totalAmount("A_T26");
    		} else if(no == "A_C25") {
    			form.handle.setValue("MMB003_01_form_01", "M1_AFO_DDC_CT_TAMT", _value);
    			this.totalAmount("A_T26");
    		} else if(no == "B_B16") {
    			form.handle.setValue("MMB003_01_form_01", "M23_BFO_DDC_MDAT_SSUM", _value);
    			this.totalAmount("B2_T19");
    		} else if(no == "B_C18") {
    			form.handle.setValue("MMB003_01_form_01", "M23_BFO_ADDI_MDAT_SSUM", _value);
    			this.totalAmount("B2_T19");
    		} else if(no == "B_E22") {
     			form.handle.setValue("MMB003_01_form_01", "M4_BFO_DDC_CT_TAMT", _value);
     			this.totalAmount("B3_T23");
     		} else if(no == "B_F24") {
     			this.totalAmount("B4_T19");
     		} else if(no == "B_G26") {
     			form.handle.setValue("MMB003_01_form_01", "M56_BFO_ADDI_AMT_SSUM", _value);
     			this.totalAmount("B4_T19");
     		}
    	},
    	calculateA_B20 : function(no) {
    		
    	},
    	totalAmount : function(no) {
    		var totAmt = 0;
    		
    		if(no == "A_T26") {
    			var val_1 = form.handle.getValue("MMB003_01_form_01", "M1_AFO_CVER_TAMT", "0");
    			var val_2 = form.handle.getValue("MMB003_01_form_01", "M1_AFO_ADDI_TAMT", "0");
    			var val_3 = form.handle.getValue("MMB003_01_form_01", "M1_AFO_DDC_CT_TAMT", "0");
    			
    			totAmt = parseInt(val_1)+parseInt(val_2)-parseInt(val_3);
    		} else if(no == "B2_T19") {
    			var val_1 = form.handle.getValue("MMB003_01_form_01", "M23_BFO_ALRN_WON_CVER_PC", "0");
    			var val_2 = form.handle.getValue("MMB003_01_form_01", "M23_BFO_DDC_MDAT_SSUM", "0");
    			var val_3 = form.handle.getValue("MMB003_01_form_01", "M23_BFO_ADDI_MDAT_SSUM", "0");
    			
    			totAmt = parseInt(val_1)-parseInt(val_2)+parseInt(val_3);
    		} else if(no == "B3_T23") {
    			var val_1 = form.handle.getValue("MMB003_01_form_01", "M4_BFO_WON_CVER_PC", "0");
    			var val_2 = form.handle.getValue("MMB003_01_form_01", "M4_BFO_DDC_CT_TAMT", "0");
    			
    			totAmt = parseInt(val_1)-parseInt(val_2);
    		} else if(no == "B4_T19") {
    			var val_1 = form.handle.getValue("MMB003_01_form_01", "M56_BFO_CALC_BSIS", "0");
    			var val_2 = form.handle.getValue("MMB003_01_form_01", "M56_BFO_ADDI_AMT_SSUM", "0");
    			
    			totAmt = parseInt(val_1)+parseInt(val_2);
    		}
    		
    		form.handle.setValue("MMB003_01_form_01", "STT_PAY_TAXT_PC", totAmt);
    	},
    	getPrvsPcsttResnCodes : function() {
    		var resnSl = "";
    		
    		for(var i = 1; i <= 19; i++) {
    			if(form.handle.isChecked("MMB003_01_form_01", "PRVS_PC_STT_RESN_SL_"+i)) {
    				resnSl += form.handle.getValue("MMB003_01_form_01", "PRVS_PC_STT_RESN_SL_"+i) + "^"; 
    			}
            }
    		
    		return resnSl.substring(0, resnSl.length-1);
    	},
    	checkedPrvsPcsttResn : function(datas) {
    		var dataList = datas.split("^");
    		
    		for(var i=0; i < dataList.length; i++) {
    			form.handle.setChecked("MMB003_01_form_01", "PRVS_PC_STT_RESN_SL_"+dataList[i], true);
    		}
    	},
    	getM26BfoPrposCodes : function() {
    		var resnSl = "";
    		
    		for(var i = 1; i <= 7; i++) {
    			if(form.handle.isChecked("MMB003_01_form_01", "M26_BFO_PRPOS_"+i)) {
    				resnSl += form.handle.getValue("MMB003_01_form_01", "M26_BFO_PRPOS_"+i) + "^"; 
    			}
            }
    		
    		return resnSl.substring(0, resnSl.length-1);
    	},
    	checkedM26BfoPrpos : function(datas) {
    		var dataList = datas.split("^");
    		
    		for(var i=0; i < dataList.length; i++) {
    			form.handle.setChecked("MMB003_01_form_01", "M26_BFO_PRPOS_"+dataList[i], true);
    		}
    	},
    	getM26BfoPcCalcBasisCodes : function() {
    		var resnSl = "";
    		
    		for(var i = 1; i <= 5; i++) {
    			if(form.handle.isChecked("MMB003_01_form_01", "M26_BFO_PC_CALC_BASIS_"+i)) {
    				resnSl += form.handle.getValue("MMB003_01_form_01", "M26_BFO_PC_CALC_BASIS_"+i) + "^"; 
    			}
            }
    		
    		return resnSl.substring(0, resnSl.length-1);
    	},
    	checkedM26BfoPcCalcBasis : function(datas) {
    		var dataList = datas.split("^");
    		
    		for(var i=0; i < dataList.length; i++) {
    			form.handle.setChecked("MMB003_01_form_01", "M26_BFO_PC_CALC_BASIS_"+dataList[i], true);
    		}
    	}
    }
};
