function onLoadPage() {
	if($('#MM0001_01_form_1').length > 0) {
		try {
			$.messager.confirm("Info", resource.getMessage("TXT_LOGINOUT_RE_LOGIN"), function(flag) {
				if(flag) {
					window.location.href = SESSION.CONTEXTPATH + "/mm/login.htm";
				} else {
					window.close();
				}
			});
		} catch(e) {
			console.debug("Session timeout.....");
		}
	} else {
	    MM0000_01.config.applyFtaNation();
	    MM0000_01.init.initComponent();
	}
}

var MM0000_01 = {
    init : {
        initComponent : function() {
            MM0000_01.combobox.initCombo_1();
            MM0000_01.combobox.initCombo_2();
        }
    },
    combobox : {
        initCombo_1 : function() {
        	var certType = form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE");
            
            if(oUtil.isNull(certType)) certType = "internal";
            
            var companyCd = getCookie("toms.fta."+certType+".company.code");
            var serviceType = form.handle.getValue("MM0000_01_form_01", "APPLICATION_SERVICE_TYPE");
            var companyCbox = combo.getObject("MM0000_01_form_01", "REQUEST_COMPANY_CD");
            var url = "#";
            
            if(serviceType == "CL") {
                url = "";
            } else {
                url = "/mm/cbox/selectCompany";
            }
            
            combo.init.setURL(url);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setCallFunction("onChangeCompany");
            if(!oUtil.isNull(companyCd)) combo.init.setValue(companyCd);
            else combo.init.setValue("");
            
            combo.create(companyCbox);
        },
        initCombo_2 : function() {
        	var certType = form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE");
            
            if(oUtil.isNull(certType)) certType = "internal";
            
            var languageCd = getCookie("toms.fta."+certType+".language.code");
            var langCbox = combo.getObject("MM0000_01_form_01", "selectedLanguage");
            
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            if(!oUtil.isNull(languageCd)) combo.init.setValue(languageCd);
            else combo.init.setValue("");
            
            combo.create(langCbox);
        }
    },
    event : {
    	selectAfterIdenStatus : function(data) {
    		if(data.REG_STATUS == "D") {
    			alert(resource.getMessage("USER_DEL_STATUS_NOTICE"));
    		} else if(data.REG_STATUS == "B") {
    			alert(resource.getMessage("USER_EXPIRE_STATUS_NOTICE"));
    		} else if(data.REG_STATUS == "N") {
    			alert(resource.getMessage("NOT_FOUND_USER"));
    		} else {
    			MM0000_01.control.idenCheck();
    		}
    	},
    	selectAfterIden : function(data) {
            if(oUtil.isNull(data) || data.LOGIN_STATUS != 1) {
                alert(resource.getMessage("MSG_LOGIN_FAILED"));
            } else {
            	var certType = form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE");
                
                if(oUtil.isNull(certType)) {
                    certType = "internal";
                }
                
                var userId = form.handle.getValue("MM0000_01_form_01", "REQUEST_USER_ID");
                var userPw = form.handle.getValue("MM0000_01_form_01", "REQUEST_PASSWORD");
                var companyCd = combo.handle.getValue("MM0000_01_form_01", "REQUEST_COMPANY_CD");
                var languageCd = combo.handle.getValue("MM0000_01_form_01", "selectedLanguage");
                
            	// 로그인 정보 쿠기에 저장
            	if(form.handle.isChecked("MM0000_01_form_01", "chk_info")) {
          	        setCookie("toms.fta."+certType+".user.id", userId, "/");
          	        setCookie("toms.fta."+certType+".user.password", userPw, "/");
          	        setCookie("toms.fta."+certType+".company.code", companyCd, "/");
          	        setCookie("toms.fta."+certType+".language.code", languageCd, "/");
          	    } else {
          	        setCookie("toms.fta."+certType+".user.id", "", "/", null);
          	        setCookie("toms.fta."+certType+".user.password", "", "/");
          	        setCookie("toms.fta."+certType+".company.code", "", "/");
          	        setCookie("toms.fta."+certType+".language.code", "", "/");
          	    }
          	    
          	    // 로그인 실행
                if(form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE") == "external") {
                    if(form.handle.getValue("MM0000_01_form_01", "SUPPLIER_TYPE") == "ST"){
                    	MM0000_01_form_01.action = "/mm/authority/logSupProcess";
                    } else {
                    	MM0000_01_form_01.action = "/mm/authority/logExtProcess";
                    }
                } else {
                    MM0000_01_form_01.action = "/mm/authority/logonProcess";
                }
                
                MM0000_01_form_01.submit();
            }
        },
        onChangeCompany : function(val) {
            // 지원되는 언어 조회
            var params = new Object();
            
            params.COMPANY_CD = val;
            params.CATEGORY_CD = "LENG_CD";
            
            var certType = form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE");
            if(oUtil.isNull(certType)) certType = "internal";
            var languageCd = getCookie("toms.fta."+certType+".language.code");
            
            combo.handle.reload("MM0000_01_form_01", "selectedLanguage", "/mm/cbox/selectStandardCode", params, languageCd);
            
            // 담당자 연락처 조회
            form.handle.setValue("MM0000_01_form_02", "REQ_TYPE", "INFO");
            form.handle.setValue("MM0000_01_form_02", "COMPANY_CD", val);
            if(form.handle.getValue("MM0000_01_form_01", "APPLICATION_SERVICE_TYPE") != "CL") {
                form.handle.setValue("MM0000_01_form_02", "BUSINESS_NO", val);
            }
            
            MM0000_01.control.selectCompanyUserInfo();
        },
        selectCloudLoginCheck : function(datas) {
            // 로그인 체크
            if(datas.EXISTS == "true") {
            	MM0000_01.ui.initLoginCookie();
            	
                MM0000_01.control.selectCompanyComboList(); // 회사정보 조회
                MM0000_01.ui.userLoginMove(); // 로그인 화면으로 이동
                
                var fobj = form.getObject("MM0000_01_form_01", "REQUEST_USER_ID");
                fobj.focus();
            } else {
            	alert("등록되지 않은 사업자 입니다.");
            	return;
            }
        },
        selectCompanyUserInfoAfter : function(datas) {
        	$("#MM0000_01_span_04").html(datas.OFFICER_NAME);
            $("#MM0000_01_span_02").html(datas.OFFICER_EMAIL);
            $("#MM0000_01_span_03").html(datas.OFFICER_PHONE_NO);
        }
    },
    control : {
    	login : function() {
    		if(!form.handle.isValidate("MM0000_01_form_01")) return;
    		
    		if(form.handle.getValue("MM0000_01_form_01", "REQUEST_USER_ID") == "fta") {
    			form.handle.setValue("MM0000_01_form_01", "CERTIFY_TYPE", "internal");
    		}
    		
    		if(form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE") == "external") {
                this.idenCheck();
            } else {
            	form.handle.loadData("MM0000_01_form_03", form.handle.getElementsParam("MM0000_01_form_01"));
            	
            	var obj = form.getObject("MM0000_01_form_03");
        		
        		form.init.setURL("/mm/cbox/selectUserStatus");
        		form.init.setCallBackFunction("selectAfterIdenStatus");
                form.init.setProgressFlag(false);
                
                form.submit(obj);
            }
    	},
    	idenCheck : function() {
            var obj = form.getObject("MM0000_01_form_01");
            
            if(form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE") == "external") {
                form.init.setURL("/mm/cbox/selectSupplierIdenCheck");
            } else {
                form.init.setURL("/mm/cbox/selectUserIdenCheck");
            }
            form.init.setCallBackFunction("selectAfterIden");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectFTAMgmtSysCompany : function() { // 클라우드 로그시에만 적용
            form.handle.setValue("MM0000_01_form_01", "CERTIFY_TYPE", "internal");
            form.handle.setValue("MM0000_01_form_02", "REQ_TYPE", "EXISTS");
            form.handle.setValue("MM0000_01_form_02", "COMPANY_CD", "");
            
            MM0000_01.control.selectCloudCompanyInfo();
        },
        selectSupplierSysCompany : function() { // 클라우드 로그시에만 적용
            form.handle.setValue("MM0000_01_form_01", "CERTIFY_TYPE", "external");
            form.handle.setValue("MM0000_01_form_02", "REQ_TYPE", "EXISTS");
            form.handle.setValue("MM0000_01_form_02", "COMPANY_CD", "");
            
            MM0000_01.control.selectCloudCompanyInfo();
        },
        selectCloudCompanyInfo : function() {
        	var certType = form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE");
            var obj;
            
            if(TOP_SYS_ID == "CC") {
            	obj = form.getObject("MM0000_01_form_04");
            } else {
            	obj = form.getObject("MM0000_01_form_02");
            }
            
            if(certType == "external") {
                form.init.setURL("/mm/cbox/selectSupplierSysCompany");
            } else {
                form.init.setURL("/mm/cbox/selectFTAMgmtSysCompany");
            }
            form.init.setCallBackFunction("selectCloudLoginCheck");
            form.init.setProgressFlag(false);
            form.init.setValidationFlag(false);
            
            form.submit(obj);
        },
        selectCompanyUserInfo : function() {
            var obj = form.getObject("MM0000_01_form_02");
            
            form.init.setURL("/mm/cbox/selectFTAMgmtSysCompany");
            form.init.setCallBackFunction("selectCompanyUserInfoAfter");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectCompanyComboList : function() { // 클라우드 로그시에만 적용(FTA 적용 회사인 경우에는 운영타입을 'R'로 설정해야 보인다. 협력사 로그인인 경우에는 협력사 삭제여부가 'N'이여야 함)
            var certType = form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE");
            var serviceType = form.handle.getValue("MM0000_01_form_01", "APPLICATION_SERVICE_TYPE");
            var url = "#";
            
            if(serviceType == "CL") {
                if(certType == "external") {
                    url = "/mm/cbox/selectSupplierSysCompany";
                } else {
                    url = "/mm/cbox/selectFTAMgmtSysCompany";
                }
            } else {
                url = "/mm/cbox/selectCompany";
            }
            
            var params = new Object();
            
            params.REQ_TYPE = "LIST";
            if(TOP_SYS_ID == "CC") {
            	params.BUSINESS_NO = form.handle.getValue("MM0000_01_form_04", "BUSINESS_NO");
            } else {            	
            	params.BUSINESS_NO = form.handle.getValue("MM0000_01_form_02", "BUSINESS_NO");
            }
            
            combo.handle.reload("MM0000_01_form_01", "REQUEST_COMPANY_CD", url, params);
        },
        popupClose : function(){
        	setCookie("toms.fta.cloud.2019", calendar.util.toDate2String(), "/");
        	$('#login_win').window('close');
        }
    },
    dialog : {
    	openDialog_1 : function() {
            var dg_1 = dialog.getObject("MMA030_01_dailog_01");

            dialog.init.setURL("/mm/noses/mmA030_01");
            dialog.init.setWidth(1000);
    		dialog.init.setHeight(670);
    		
            dialog.open(dg_1);
        },
        openDialog_2 : function() {
            var dg_1 = dialog.getObject("MMA031_01_dailog_01");

            dialog.init.setURL("/mm/noses/mmA031_01");
            dialog.init.setWidth(850);
    		dialog.init.setHeight(665);
    		
            dialog.open(dg_1);
        }
    },
    ui : {
        userLoginMove : function() {
            var certType = form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE");
            
            if(oUtil.isNull(certType)) {
                certType = "internal";
            }
            
            if(certType == "external") {
            	$("#MM0000_01_div_01").html("<p class=\"tit\">"+ resource.getMessage("FTA_ORIGIN_SUBMIT_SYSTEM") +"</p>");
            	
            	form.util.setVisible("MM0000_01_div_05", false);
                form.util.setVisible("MM0000_01_div_06", true);
                form.util.setVisible("MM0000_01_div_07", false);
                form.util.setVisible("MM0000_01_div_08", true);
                
                $("#MM0000_01_div_02").html("<label id=\"company\">"+ resource.getMessage("CSTMR") +"</label>");
            } else if(certType == "internal") {
            	if(TOP_SYS_ID == "CC") {
            		$("#MM0000_01_div_01").html("<p class=\"tit\">"+ resource.getMessage("CC_수출입통관고도화프로그램 Demo Ver. 2021") +"</p>");
            	} else if(TOP_SYS_ID == "RS") {
                    $("#MM0000_01_div_01").html("<p class=\"tit\">"+ resource.getMessage("CC_중계서버 With Salesforce") +"</p>");
                } else {
            		$("#MM0000_01_div_01").html("<p class=\"tit\">"+ resource.getMessage("FTA_ORIGIN_MAN_SYSTEM") +"</p>");
            	}
            	
            	form.util.setVisible("MM0000_01_div_05", true);
                form.util.setVisible("MM0000_01_div_06", false);
                form.util.setVisible("MM0000_01_div_07", true);
                form.util.setVisible("MM0000_01_div_08", false);
                
                $("#MM0000_01_div_02").html("<label id=\"company\">"+ resource.getMessage("CONPY") +"</label>");
            }
            
            form.util.setVisible("MM0000_01_div_03", true);
            form.util.setVisible("MM0000_01_div_04", false);
            form.util.setVisible("MM0000_01_div_09", false);
        },
        prevPageMove : function() {
        	form.util.setVisible("MM0000_01_div_03", false);
        	
        	if(TOP_SYS_ID == "CC") {
        		$("#MM0000_01_div_01").html("<p class=\"tit\">"+resource.getMessage("CC_통관 시스템 방문을 환영합니다.")+"</p>");
        		
        		form.util.setVisible("MM0000_01_div_09", true);
        		form.getObject("MM0000_01_form_04", "BUSINESS_NO").focus();
        	} else {
	        	$("#MM0000_01_div_01").html("<p class=\"tit\">"+ resource.getMessage("TOMS_FTA_WELCOME") +"</p>");
	        	
	        	form.util.setVisible("MM0000_01_div_04", true);
        	}
        	
        },
        initLoginCookie : function() {
        	var certType = form.handle.getValue("MM0000_01_form_01", "CERTIFY_TYPE");
            
            if(oUtil.isNull(certType)) {
                certType = "internal";
            }
            
            var userId = getCookie("toms.fta."+certType+".user.id");
            var userPw = getCookie("toms.fta."+certType+".user.password");
            
            if(!oUtil.isNull(userId)) {
            	form.handle.setValue("MM0000_01_form_01", "REQUEST_USER_ID", userId);
            	form.handle.setValue("MM0000_01_form_01", "REQUEST_PASSWORD", userPw);
            	
				form.handle.setChecked("MM0000_01_form_01", "chk_info", true);
            } else {
            	form.handle.setValue("MM0000_01_form_01", "REQUEST_USER_ID", "");
            	form.handle.setValue("MM0000_01_form_01", "REQUEST_PASSWORD", "");
            	
            	form.handle.setChecked("MM0000_01_form_01", "chk_info", false);
            }
            
            form.handle.isValidate("MM0000_01_form_01");
        }
    },
    config : {
        applyFtaNation : function() {
            var ieVer = get_version_of_IE();
            
            if(ieVer != "N/A") {
            	if(parseInt(get_version_of_IE()) == 10) {
            		alert(resource.getMessage("TXT_SORRY_EXPLORER_10"));
            	}
            }
            
            if(form.handle.getValue("MM0000_01_form_01", "APPLICATION_SERVICE_TYPE") == "CL") {
            	if(TOP_SYS_ID == "CC") {
            		$("#MM0000_01_div_01").html("<p class=\"tit\">"+resource.getMessage("CC_통관 시스템 방문을 환영합니다.")+"</p>");
            		
            		form.util.setVisible("MM0000_01_div_09", true);
            		form.util.setVisible("MM0000_01_span_01", true);
            	} else if(TOP_SYS_ID == "RS") {
                    $("#MM0000_01_div_01").html("<p class=\"tit\">"+resource.getMessage("CC_중계서버 방문을 환영합니다.")+"</p>");
                    
                    form.util.setVisible("MM0000_01_div_09", true);
                    form.util.setVisible("MM0000_01_span_01", true);
                } else {
            		$("#MM0000_01_div_01").html("<p class=\"tit\">"+resource.getMessage("TOMS_FTA_WELCOME")+"</p>");
            		
            		form.util.setVisible("MM0000_01_div_04", true);
            		form.util.setVisible("MM0000_01_span_01", true);
            		
            		// 공지사항 페이지 오픈
                    if(calendar.util.toDate2String() <= "20190131") { // 공지사항 종료 기간 체크
    	                if(calendar.util.toDate2String() != getCookie("toms.fta.cloud.2019")) {
    	                	$('#login_win').window('open');  // 이벤트 종료(2019.01.31)
    	                }
                    }
            	}
                
            	form.handle.setValidate("MM0000_01_form_02", "BUSINESS_NO", true);
                
            	var busObj = form.getObject("MM0000_01_form_02", "BUSINESS_NO");
            	busObj.focus();
            } else {
            	$("#MM0000_01_div_01").html("<p class=\"tit\"></p>");
                
                form.util.setVisible("MM0000_01_div_03", true);
                MM0000_01.ui.userLoginMove();
                MM0000_01.ui.initLoginCookie();
                
                form.handle.setValidate("MM0000_01_form_02", "BUSINESS_NO", false);
                
                var busObj = form.getObject("MM0000_01_form_01", "REQUEST_USER_ID");
            	busObj.focus();
            }
        }
    }
};