/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MMA022_01
 * 작업일자 : 2016.03.28
 ******************************************************************************************/

function onLoadPage() {
	MMA022_01.config.applyFtaNation();
    MMA022_01.init.initComponent();
}

var MMA022_01 = {
    init : {
        initComponent : function() {
        	MMA022_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {}, // 달력 그리드 생성
    combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
    	header1 : [
    		["ERR_MSG",          resource.getMessage("TXT_ERR_MSG")   ,                      300, "left",   "center", true, false, null, null, null, 0, 0],
    		["SPID",             "Row"                                ,                      50 , "right",  "center", true, false, null, null, null, 0, 0],
    		["ITEM_CD",          resource.getMessage("LTIT,CODE")/*품목코드*/,          		130, "center", "center", true, false, null, null, null, 0, 0],
    		["ITEM_NAME",        resource.getMessage("LTIT,NAME")/*품목명*/,               	180,   "left", "center", true, false, null, null, null, 0, 0],
    		["FTA_CD",           resource.getMessage("AGRET,CODE")/*FTA코드*/,                100, "center", "center", true, false, null, null, null, 0, 0],
    		["FTA_NAME",         resource.getMessage("AGRET,NAME")/*FTA명*/,                 	100,   "left", "center", true, false, null, null, null, 0, 0],
    		["HS_CODE",          resource.getMessage("HS,CODE")/*HS코드*/,                 	100, "center", "center", true, false, null, null, null, 0, 0],
    		["RULE_CONTENTS",    resource.getMessage("TXT_RULE_CODE")/*결정기준*/,           100, "center", "center", true, false, null, null, null, 0, 0],
    		["RVC_RATE",         resource.getMessage("RVC, RATE")+"(%)"/*RVC 비율(%)*/,	    200,  "right", "center", true, false, null, null, null, 0, 0],
    		["FTA_CO_YN",        resource.getMessage("TXT_MEET_YN")/*충족여부*/,             130, "center", "center", true, false, null, null, null, 0, 0],
        	["ORIGIN_NATION_CD", resource.getMessage("TXT_COO_REGION_NATION")/*원산지 국가*/,    130, "center", "center", true, false, null, null, null, 0, 0],
            ["NONORIGIN_REASON_CD", resource.getMessage("NOMEET, REASN,CODE"),               120, "left", "center", false, false, null, null, null, 0, 0],
        	["NONORIGIN_REASON_DESC", resource.getMessage("NOMEET, REASN,DSCPT"),        300, "left", "center", false, false, null, null, null, 0, 0]
    	],
        initGrid_1 : function() {
            var dg_1 = grid.getObject("MMA022_01_grid_01");

            // 페이지 설정
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : { 
        callbackSaveExcelSample: function(data) {
            MMA022_01.control.selectExcelSampleList();
        }, 
        callBackSaveItemList : function(datas) {
        	var pid = form.handle.getValue("MMA022_01_form_01", "beforeTP");
        	
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA022_01"]) == "function") {
                    pro_id["callByMMA022_01"](datas);
                }
            }
        }
    },
    // 업무구현
    control : {
    	//버튼액션(엑셀업로드) : 엑셀 임시테이블에 저장
    	saveExcelSample : function() {
            // from 객체 획득
            var obj = form.getObject("MMA022_01_form_01");
            
            var vUploadExcelFile = form.handle.getValue("MMA022_01_form_01", "uploadExcelFile");
            
            if(oUtil.isNull(vUploadExcelFile) || vUploadExcelFile.length <= 0) {
                alert(resource.getMessage("MSG_REQUIRED_ATTACH_EXCEL_FILE")); /*엑셀파일을 첨부해 주십시오.*/
                return;
            }
            
            if(!confirm(resource.getMessage("MSG_CONFIRM_REG_EXCEL_FILE"))) { /*엑셀파일 등록하시겠습니까?*/
                return;
            }
            
            form.init.setProgressFlag(true);
            form.init.setURL("/mm/pop/mmA022_01/saveExcelSample");
            form.init.setCallBackFunction("callbackSaveExcelSample");
            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY")); /*요청하신 작업이 성공적으로 처리되었습니다.*/
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
            
            form.submit(obj);
        },
    	//파일적용 후 조회
        selectExcelSampleList : function() {
            if(!form.handle.isValidate("MMA022_01_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("MMA022_01_grid_01");
            var params = form.handle.getElementsParam("MMA022_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA022_01/selectExcelSampleList", params);
        },
        //버튼액션(적용) : 아이템 목록을 부모창의 그리드로 복사
        saveItemList : function() {
        	var dg_1 = grid.getObject("MMA022_01_grid_01");
        	var data = grid.handle.getAllRows(dg_1);
        	var pid = form.handle.getValue("MMA022_01_form_01", "TARGET_PID");
            
        	if(data.length <= 0) {
        		alert(resource.getMessage("MSG_NOT_EXIST_EXCEL")); /*업로드를 먼저 해주시기 바랍니다.*/
        		return;
        	}
        	
            var vErrYn = "";
            var erflag = false;
            
            // 유효성 체크
            for(var i = 0; i < data.length; i++) {
				if(!oUtil.isNull(data[i].ERR_MSG)) {
					grid.handle.selectRowIndex(dg_1, i);
					erflag = true;
					
					break;
            	}
			}
        	
			if(erflag && pid != "OR5007_01") {
				alert(resource.getMessage("MSG_UPLOAD_DATA_ERR")); /*업로드 데이터에 오류가 있습니다.*/
				return;
			} else {
	            if(!oUtil.isNull(pid)) {
	                var pro_id = eval("window." + pid + ".dialog");
	                
	                if(typeof(pro_id["callByMMA022_01"]) == "function") {
	                	pro_id["callByMMA022_01"](data);
	                }
	            }
			}
        }
    },
    dialog : {}, // 다이얼로그 구현
    file : {}, // 파일 input 엘리먼트 구현
    excel : {}, // 엑셀다운로드 구현
    config : {
    	applyFtaNation : function() {
    		// 역외산 사유를 입력이 지정된 경우라면 해당 항목을 표시한다.
			if(form.handle.getValue("MMA022_01_form_01", "NON_ORGIN_RESN_YN") == "N") {
				var columns1 = MMA022_01.datagrid.header1;
				
				grid.util.changeHeaderHidden(columns1 , "NONORIGIN_REASON_CD", true);
				grid.util.changeHeaderHidden(columns1 , "NONORIGIN_REASON_DESC", true);
			}
		}
    }
};
