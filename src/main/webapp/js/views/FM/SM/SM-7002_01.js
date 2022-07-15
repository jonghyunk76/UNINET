/******************************************************************************************
 * 작성자 : carlos
 * Program Id : SM-7002_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	SM7002_01.config.applyFtaNation();
	SM7002_01.init.initComponent();
}

var SM7002_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	SM7002_01.combobox.initCombo_1();
            SM7002_01.combobox.initCombo_2();
            
            SM7002_01.datagrid.initGrid_1();
        }
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {
        dataSearchType : [
        	{CODE : "DIVISION_CD", NAME : resource.getMessage("DIVIS,CODE")}, 
        	{CODE : "DIVISION_NAME", NAME : resource.getMessage("DIVIS,NAME")}
        ],
        initCombo_1 : function() {
            var objSearchType = combo.getObject("SM7002_01_form_01", "schKeyField");
            
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchType);
        },
        initCombo_2 : function() {
            var objSearchKeyLike = combo.getObject("SM7002_01_form_01", "schKeyLike");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchKeyLike);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [
        	["DIVISION_CD"       , resource.getMessage("DIVIS,CODE")          /*사업부 코드*/    , 100, "center", "center", true, false, null, null, null, 2, 0],
        	["DIVISION_NAME"     , resource.getMessage("DIVIS,NAME")          /*사업부 명*/      , 120, "left"  , "center", true, false, null, null, null, 2, 0],
        	["DIVISION_NAME_ENG" , resource.getMessage("DIVIS,NAME,(,ENGLS,)") /*사업부명(영문)*/ , 150, "left"  , "center", true, false, null, null, null, 2, 0],
        	["BUSINESS_NO"     , resource.getMessage("TXT_BUSINESSNO")           /*사업부형태*/       , 120, "center", "center", true, false , null, null, null, 2, 0],
        	["ADDRESS1"       , resource.getMessage("ADDRS")           /*사업부형태*/          , 200, "left", "center", true, false , null, null, null, 2, 0],
        	["DIVISION_TYPE"     , resource.getMessage("DIVIS,TYP")           /*사업부형태*/     , 100, "center", "center", true, true , null, null, null, 2, 0],
        	["DIVISION_TYPE_NAME", resource.getMessage("DIVIS,TYP")           /*사업부형태*/     , 100, "center", "center", true, false, null, null, null, 2, 0],
        	["OFFICER"           , resource.getMessage("THPIC,NAME")          /*담당자명*/      , 130, "left"  , "center", true, false, null, null, null, 2, 0],
        	["OFFICER_PHONE_NO"  , resource.getMessage("THPIC,TELNM")         /*담당자 전화번호*/  , 130, "center", "center", true, false, null, null, null, 2, 0],
        	["OFFICER_EMAIL"     , resource.getMessage("THPIC,EMAIL")         /*담당자이메일*/    , 180, "left"  , "center", true, false, null, null, null, 2, 0],
        	["ORGAN_DOC_NO"      , resource.getMessage("ISORG")              /*기관발급*/       , 220, "left"  , "center", true, true, null, null, null, 0, 4]
        ],
        header1_1 : [
        	["CUSTOMS_CD"     , resource.getMessage("TXT_CUSTOMS_CODE")         /*세관코드*/   , 100, "center"  , "center", true, true, null, null, null],
        	["CUSTOMS_NAME"     , resource.getMessage("CSTHU,NAME")         /*세관명*/   , 130, "left"  , "center", true, true, null, null, null],
        	["ORGAN_VENDOR_CD"     , resource.getMessage("DCRAN, ASSGN,CODE")         /*신고인 부여코드*/   , 130, "center"  , "center", true, true, null, null, null],
        	["ORGAN_DOC_NO"     , resource.getMessage("INBOX,MSSNM")         /*문서함번호*/   , 100, "center"  , "center", true, true, null, null, null]
        ],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("SM7002_01_grid_01");
            
            grid.init.setPage(true);
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header1, this.header1_1);
        }
    },
    // 이벤트 처리
    event : {
        onDblClick_SM7002_01_grid_01 : function(rowData) {
            SM7002_01.dialog.openDialog('update');
        }
    },
    // 업무구현
    control : {
    	selectDivisionList : function() {
            if (!form.handle.isValidate("SM7002_01_form_01")) {
                return;
            }
    	    
    		var dg_1 = grid.getObject("SM7002_01_grid_01");
    		var params = form.handle.getElementsParam("SM7002_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7002_01/selectDivisionList", params);
    	}
    },
    // 다이얼로그 구현
    dialog : {
    	
        openDialog : function(flag) {
        	var orgYn = form.handle.getValue("SM7002_01_form_01", "ORGAN_ISSUE_YN");
            var dg_1 = grid.getObject("SM7002_01_grid_01");
            var dl_1 = dialog.getObject("SM7002_02_dailog_01");
            
            if (flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = form.handle.getElementsParam("SM7002_01_form_01");
            var params2 = {};
            
            params1.flag = params1.flag || flag;
            
            if (flag != "insert") {
                params2 = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.extend(params, params1, params2);
            
            // 팝업 셋팅
            // title:사업부정보
            dialog.init.setTitle(resource.getMessage("SM7002_02"));
            dialog.init.setWidth(1024);
            if(orgYn == "Y") {
            	dialog.init.setHeight(338);
            } else {
            	dialog.init.setHeight(254);
            }
            dialog.init.setURL("/fm/sm/sm7002_02");
            dialog.init.setResizable(false);
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
            
        }
    },
    // 파일 input 엘리먼트 구현
    file : {},
    // 엑셀다운로드 구현
    excel : {
        excelDownload_1 : function() {
            var dg_1 = grid.getObject("SM7002_01_grid_01");
            var fobj = form.getObject("SM7002_01_form_01");
            
            form.init.setURL("/fm/sm/sm7002_01/selectDivisionList.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("DIVIS, INFMT,SRCH, MGT"), resource.getMessage("LIST"));
        }
    },
    config : {
    	applyFtaNation : function() {
    		var columns1 = SM7002_01.datagrid.header1;
    		var columns1_1 = SM7002_01.datagrid.header1_1;
    		var orgYn = form.handle.getValue("SM7002_01_form_01", "ORGAN_ISSUE_YN");
    		
			if(orgYn == "Y") {
				grid.util.changeHeaderHidden(columns1 , "ORGAN_DOC_NO", false);
				grid.util.changeHeaderHidden(columns1_1 , "CUSTOMS_CD", false);
				grid.util.changeHeaderHidden(columns1_1 , "CUSTOMS_NAME", false);
				grid.util.changeHeaderHidden(columns1_1 , "ORGAN_VENDOR_CD", false);
				grid.util.changeHeaderHidden(columns1_1 , "ORGAN_DOC_NO", false);
			}
			
			form.handle.setChecked("SM7002_01_form_01", "USE_YN", true);
		}
    }
	
};
