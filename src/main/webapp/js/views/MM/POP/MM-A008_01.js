/******************************************************************************************
 * 작성자 : 
 * Program Id : MM-A008_01
 * 작업일자 : 2016.03.28
 * 
 * * <Header 작성법>
 * Array[0] = Field ID : string
 * Array[1] = Field Name : string
 * Array[2] = Width Size : number
 * Array[3] = Align : string
 * Array[4] = halign : string
 * Array[5] = Sort : boolean
 * Array[6] = hidden : boolean
 * Array[7] = editor type : json
 * Array[8] = checkbox : boolean
 * Array[9] = format : json
 * Array[10] = rowspan : number
 * Array[11] = colspan : number
 * Array[12] = frozen : boolean
 * Array[13] = styler: string
 ******************************************************************************************/

function onLoadPage() {
	MMA008_01.datagrid.initGrid_1();
}

var MMA008_01 = {
	
    init : {}, // 초기값 설정
	calendar : {}, // 달력 그리드 생성
	combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
			["CODE"        ,resource.getMessage("TXT_NATION_CODE")          ,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["CODE_NAME"   ,resource.getMessage("TXT_NATION_NAME")          ,200    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
			["CODE_NAME_ENG"   ,resource.getMessage("TXT_NATION_NAME,(,ENGLS,)")          ,200    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0]
        ],
		initGrid_1 : function() {
			var dg_1 = grid.getObject("MMA008_01_grid_01");
			
			grid.init.setPage(true);
 			grid.init.setFitColumns(true);
 			var params = form.handle.getElementsParam("MMA008_01_form_01");
 			
 			if (!oUtil.isNull(params.CODE) || !oUtil.isNull(params.CODE_NAME) || !oUtil.isNull(params.FTA_CD)) {
 			    grid.init.setURL("/mm/pop/mmA008_01/selectMainList");
            } else {
                grid.init.setURL("#");
            }
 			
 			grid.init.setQueryParams(params);
 			
 		    // 이벤트 설정
 			grid.event.setOnDblClickRow(dg_1);
 			
 			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
 		onClick_MMA008_01_grid_01 : function(rowData) {},
 		onDblClick_MMA008_01_grid_01 : function(rowData) {
 			var pid = form.handle.getValue("MMA008_01_form_01", "TARGET_PID");
 			
             if (!oUtil.isNull(pid)) {
             	var pro_id = eval("window." + pid + ".dialog");
             	
                 if (typeof(pro_id["callByMMA008_01"]) == "function") {
                     pro_id["callByMMA008_01"](rowData);
                 }
             }
 		}
     },
     control : {// 업무구현
     	selectMainList : function() {
     		// 검색창의 validation 체크결과를 비교한다.
     		if(!form.handle.isValidate("MMA008_01_form_01")) return;
     		
     		var dg_1 = grid.getObject("MMA008_01_grid_01");
     		var params = form.handle.getElementsParam("MMA008_01_form_01");
     		
     		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA008_01/selectMainList", params);
     	},
     	selectRowValue : function() {
     		var dg_1 = grid.getObject("MMA008_01_grid_01");
     		var rowData = grid.handle.getSelectedRowData(dg_1);
     		
     		if(!grid.handle.isSelected(dg_1)) {
     			alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
     			return;
     		}
     		
     		MMA008_01.event.onDblClick_MMA008_01_grid_01(rowData);
     	}
     },
     dialog : {
     	// 다이얼로그 구현
     },
     file : {
     	// 파일 input 엘리먼트 구현
     },
     excel : {
     	// 엑셀다운로드 구현
     }
 	
 };
