/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A021_02
 * 작업일자 : 2016.09.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA021_02.config.applyFtaNation();
    MMA021_02.init.initComponent();
}

var MMA021_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMA021_02.datagrid.initGrid_1();
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
    		["CO_DOC_NO",            	resource.getMessage("TXT_SEARCH_COO_CERTIFY_NO")/*증명번호*/,   180, "left", "center", true, false, null, null, null, 0, 0],
    		["DIVISION_NAME",           resource.getMessage("DIVIS")/*사업부*/,                       150, "left", "center", true, false, null, null, null, 0, 0],
        	["VENDOR_CD",               resource.getMessage("PATNE, CODE")/*협력사코드*/,          120, "center", "center", true, false, null, null, null, 0, 0],
            ["VENDOR_NAME",             resource.getMessage("PATNE, NAME")/*협력사명*/,            150, "left",   "center", true, false, null, null, null, 0, 0],
            ["APPLY_DATE",           	resource.getMessage("TXT_COVER_START_DATE")/*포괄시작일자*/,          100, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
            ["END_DATE",            	resource.getMessage("TXT_COVER_EXPR_DATE")/*포괄만료일자*/,           100, "center", "center", true, false, null, null, {format:"date"}, 0, 0],
            ["ITEM_CD",            	resource.getMessage("LTIT, CODE")/*품목코드*/,                        120, "center", "center", true, false, null, null, null, 0, 0],
            ["ITEM_NAME",           resource.getMessage("LTIT,NAME")/*품목명*/,                           150,   "left", "center", true, false, null, null, null, 0, 0],
            ["HS_CODE",         resource.getMessage("TXT_HS_CODE")/*HS코드*/,                         130, "center", "center", true, false, null, null, null, 0, 0],
            ["FTA_CD",             	resource.getMessage("AGRET,CODE")/*FTA코드*/,                       100, "center", "center", true, false, null, null, null, 0, 0],
            ["FTA_NAME",           	resource.getMessage("AGRET,NAME")/*FTA명*/,                         100,   "left", "center", true, false, null, null, null, 0, 0],
            ["RULE_CONTENTS",      	resource.getMessage("TXT_RULE_CODE_NAME")/*결정기준*/,                80,  "center", "center", true, false, null, null, null, 0, 0],
            ["RVC_RATE",			resource.getMessage("TXT_RVC_RATE")/*협정RVC비율*/,                   120,  "right", "center", true, false, null, null, null, 0, 0],
            ["FTA_CO_YN_NAME",          	resource.getMessage("TXT_MEET_YN")/*충족여부*/,                       100, "center", "center", true, false, null, null, null, 0, 0],
            ["SALES_UNIT_PRICE",    resource.getMessage("SALE,<br>,UNIPR")/*판매단가*/,                   100,  "right", "center", true, false, null, null, {format:"amount"}, 0, 0],
            ["TRACE_VALUE",         "Trace Value"/*Trace Value*/,                                          80,  "right", "center", true, false, null, null, {format:"amount"}, 0, 0],
            ["CURRENCY", 		    resource.getMessage("TXT_CURRENCY")/*원산지 국가*/,                   100, "center", "center", true, false, null, null, null, 0, 0],
            ["MODIFY_REQ", 		    resource.getMessage("Revision")/*원산지 국가*/,                   100, "right", "center", true, false, null, null, null, 0, 0],
            ["CREATE_DATE",           resource.getMessage("CDATE")/*협력사명*/    , 150, "center",   "center", false, false, null, null, null, 0, 0],
        	["CREATE_BY",           resource.getMessage("CRTBY")/*협력사명*/    , 100, "center",   "center", false, false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA021_02_form_01");
            var dg_1 = grid.getObject("MMA021_02_grid_01");
            
            grid.init.setURL("/mm/pop/mmA021_02/selectRecieveCoDetail");
            grid.init.setQueryParams(params);
            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);
            
            Theme.defaultGrid(dg_1, this.header_1);
        }
    },
    // 이벤트 처리
    event : {},
    // 업무구현
    control : {},
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {}, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA021_02_grid_01");
            var fobj = form.getObject("MMA021_02_form_01");
            var title = resource.getMessage("VGB000_01, RECOD");
            
            form.init.setURL("/mm/pop/mmA021_02/selectRecieveCoDetail.fle");
            
            form.excelSubmit(dg_1, fobj, title, title);
        }
    },
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {
    		if(SESSION.FTA_NATION == "KR") {
				var columns1 = MMA021_02.datagrid.header_1;
				
				grid.util.changeHeaderHidden(columns1 , "SALES_UNIT_PRICE", true);
				grid.util.changeHeaderHidden(columns1 , "TRACE_VALUE", true);
				grid.util.changeHeaderHidden(columns1 , "CURRENCY", true);
			}
    	}
    }
};
