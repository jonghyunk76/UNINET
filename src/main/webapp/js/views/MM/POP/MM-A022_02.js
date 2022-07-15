/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MMA022_02
 * 작업일자 : 2016.03.28
 ******************************************************************************************/

function onLoadPage() {
	MMA022_02.config.applyFtaNation();
    MMA022_02.init.initComponent();
}

var MMA022_02 = {
    init : {
        initComponent : function() {
        	MMA022_02.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {}, // 달력 그리드 생성
    combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
    	header1 : [
    		["ERR_MSG",          resource.getMessage("TXT_ERR_MSG")   ,                      300, "left",   "center", true, false, null, null, null, 0, 0],
    		["SPID",             "Row"                                ,                      50 , "right",  "center", true, false, null, null, null, 0, 0],
    		["DIVISION_CD",      resource.getMessage("DIVIS_CODE")/*사업부*/,                   150, "left", "center", true, false, null, null, null, 0, 0],
            ["VENDOR_CD",        resource.getMessage("PATNE, CODE")/*협력사코드*/,          120, "center", "center", true, false, null, null, null, 0, 0],
            ["VENDOR_NAME",      resource.getMessage("PATNE, NAME")/*협력사명*/,                 150, "left",   "center", true, false, null, null, null, 0, 0],
            ["RCV_CO_DOC_NO",    resource.getMessage("TXT_COO_CERTIFY_NO")/*증명번호*/,160,"center", "center", false, false, null, null, null, 0, 0],
            ["RCV_ISSUE_DATE",   resource.getMessage("TXT_CERTF_RPTIN_DAY")/*증명서 발행일*/,          130, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
            ["INVOICE_NO",       resource.getMessage("INVIC_MSSNM")/*인보이스번호*/,          150, "center", "center", true, false, null, null, null, 0, 0],
            ["INVOICE_DATE",     resource.getMessage("INVIC_TXT_ISSUE_DATE")/*발급일자*/,         120, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
            ["ITEM_CD",          resource.getMessage("LTIT, CODE")/*품목코드*/,            120, "center", "center", true, false, null, null, null, 0, 0],
            ["ITEM_NAME",        resource.getMessage("LTIT,NAME")/*품목명*/,              180,   "left", "center", true, false, null, null, null, 0, 0],
            ["FTA_CD",           resource.getMessage("AGRET,CODE")/*FTA코드*/,           100, "center", "center", true, false, null, null, null],
            ["FTA_NAME",         resource.getMessage("AGRET,NAME")/*FTA명*/,            120,   "left", "center", true, false, null, null, null],
            ["RCV_HS_CODE",      resource.getMessage("HS,CODE")/*HS코드*/,               100,   "center", "center", true, false, null, null, null, 0, 0],
            ["RCV_RULE_CONTENTS",resource.getMessage("TXT_RULE_CODE")/*결정기준*/,      100, "center", "center", false, false, null, null, null, 0, 0],
            ["RVC_RATE",         resource.getMessage("RVC, RATE")+"(%)"/*RVC 비율(%)*/,       200,  "right", "center", false, false, null, null, null, 0, 0],
            ["RCV_FTA_CO_YN",    resource.getMessage("TXT_MEET_YN")/*충족여부*/,        130, "center", "center", false, false, null, null, null, 0, 0],
            ["ORIGIN_NATION_CD", resource.getMessage("TXT_COO_REGION_NATION")/*원산지 국가*/,    130, "center", "center", true, false, null, null, null, 0, 0]
    	],
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("MMA022_02_form_01");
            var dg_1 = grid.getObject("MMA022_02_grid_01");
            
            // 페이지 설정
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/pop/mmA022_02/selectVientnamExcelSampleList");
            grid.init.setPage(false);
            grid.init.setFitColumns(false);
            grid.init.setFilter(true); // 그리드  해더에 필터 적용
            grid.init.setSelectCurrentRow(true);
            grid.init.setEmptyMessage(false);
            
            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : { 
        callbacksaveVientnamExcelSample: function(data) {
            MMA022_02.control.selectExcelSampleList();
        }, 
        callBackSaveItemList : function(datas) {
        	var pid = form.handle.getValue("MMA022_02_form_01", "beforeTP");
        	
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA022_02"]) == "function") {
                    pro_id["callByMMA022_02"](datas);
                }
            }
        },
        insertVientnamExcelList : function(data) {
            var pid = form.handle.getValue("MMA022_02_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA022_02"]) == "function") {
                    pro_id["callByMMA022_02"](data);
                }
            }
        },
        deleteVientnamExcelSampleRow : function(data) {
            MMA022_02.control.selectExcelSampleList();
        }
    },
    // 업무구현
    control : {
    	//버튼액션(엑셀업로드) : 엑셀 임시테이블에 저장
    	saveExcelSample : function() {
            // from 객체 획득
            var obj = form.getObject("MMA022_02_form_01");
            
            var vUploadExcelFile = form.handle.getValue("MMA022_02_form_01", "uploadExcelFile");
            
            if(oUtil.isNull(vUploadExcelFile) || vUploadExcelFile.length <= 0) {
                alert(resource.getMessage("MSG_REQUIRED_ATTACH_EXCEL_FILE")); /*엑셀파일을 첨부해 주십시오.*/
                return;
            }
            
            if(!confirm(resource.getMessage("MSG_CONFIRM_REG_EXCEL_FILE"))) { /*엑셀파일 등록하시겠습니까?*/
                return;
            }
            
            form.init.setProgressFlag(true);
            form.init.setURL("/mm/pop/mmA022_02/saveVientnamExcelSample");
            form.init.setCallBackFunction("callbacksaveVientnamExcelSample");
            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY")); /*요청하신 작업이 성공적으로 처리되었습니다.*/
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
            
            form.submit(obj);
        },
    	//파일적용 후 조회
        selectExcelSampleList : function() {
            if(!form.handle.isValidate("MMA022_02_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("MMA022_02_grid_01");
            var params = form.handle.getElementsParam("MMA022_02_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA022_02/selectVientnamExcelSampleList", params);
        },
        // 원산지 증명서 등록 실행
        insertExcelList : function() {
        	var dg_1 = grid.getObject("MMA022_02_grid_01");
        	var data = grid.handle.getAllRows(dg_1);
        	
        	if(data.length <= 0) {
        		alert(resource.getMessage("MSG_NOT_EXIST_EXCEL"));
        		return;
        	}
            
            // 유효성 체크
            for(var i = 0; i < data.length; i++) {
                if(!oUtil.isNull(data[i]["ERR_MSG"])) {
                    alert(resource.getMessage("TXT_FAIL_RETRY"));
                    return;
                }
            }
            
            $.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_INSERT"), function(flag) {                
                if(flag) {
                    form.handle.setValue("MMA022_02_form_01", "gridData", grid.util.convertJson2Rows(data));
                    
                    var fobj = form.getObject("MMA022_02_form_01");
                    
                    form.init.setURL("/mm/pop/mmA022_02/insertVientnamExcelList");
                    form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY")); /*요청하신 작업이 성공적으로 처리되었습니다.*/
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
                    form.init.setCallBackFunction("insertVientnamExcelList");
                    
                    form.submit(fobj);
                }
            });
        },
        // 원산지 증명서 등록 실행
        deleteExcelSampleRow : function(all) {
            var dg_1 = grid.getObject("MMA022_02_grid_01");
            
            if(all == "N" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_DELETE_SELECT"));
                return;
            }
            
            if(all == "Y" && grid.handle.getRowCount(dg_1) == 0) {
                alert(resource.getMessage("MSG_NOT_EXIST_DATA"));
                return;
            }
            
            var msg; 
            var data;
            if(all == "Y") {
                msg = resource.getMessage("ALL_MSG_CONFIRM_DELETE");
                
                var rows = grid.handle.getAllRows(dg_1);
                data = rows[0];
            } else {
                msg = resource.getMessage("MSG_CONFIRM_DELETE");
                
                data = grid.handle.getSelectedRowData(dg_1);
            }
            
            $.messager.confirm(CNFIR, msg, function(flag) {                
                if(flag) {        
                    form.handle.loadData("MMA022_02_form_02", data);
                    
                    var fobj = form.getObject("MMA022_02_form_02");
                    
                    if(all == "Y") form.init.setURL("/mm/pop/mmA022_02/deleteVientnamExcelSample");
                    else form.init.setURL("/mm/pop/mmA022_02/deleteVientnamExcelSampleRow");
                    form.init.setProgressFlag(true);
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY")); /*요청하신 작업이 성공적으로 처리되었습니다.*/
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE")); /*데이터 저장에 실패했습니다.*/
                    form.init.setCallBackFunction("deleteVientnamExcelSampleRow");
                    
                    form.submit(fobj);
                }
            });
        }
    },
    dialog : {}, // 다이얼로그 구현
    file : {}, // 파일 input 엘리먼트 구현
    excel : {
        excelDownload_1 : function() {
            form.handle.setValue("MMA022_02_form_01", "gridData", "");
            
            var dg_1 = grid.getObject("MMA022_02_grid_01");
            var fobj = form.getObject("MMA022_02_form_01");
            
            form.init.setURL("/mm/pop/mmA022_02/selectVientnamExcelSampleList.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("CNFOR_INFMT")+"-"+resource.getMessage("TXT_UPLOAD_LIST"), resource.getMessage("LIST"));
        }
    }, // 엑셀다운로드 구현
    config : {
    	applyFtaNation : function() {
    		// 역외산 사유를 입력이 지정된 경우라면 해당 항목을 표시한다.
			if(form.handle.getValue("MMA022_02_form_01", "NON_ORGIN_RESN_YN") == "N") {
				var columns1 = MMA022_02.datagrid.header1;
				
				grid.util.changeHeaderHidden(columns1 , "NONORIGIN_REASON_CD", true);
				grid.util.changeHeaderHidden(columns1 , "NONORIGIN_REASON_DESC", true);
			}
		}
    }
};
