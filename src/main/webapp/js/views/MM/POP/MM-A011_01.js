/******************************************************************************************
 * 작성자 : 이재욱
 * Program Id : MM-A011_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA011_01.datagrid.initGrid_1();
}

var MMA011_01 = {
	
    init : {}, // 초기값 설정
	calendar : {}, // 달력 그리드 생성
	combobox : {}, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
		header : [
					["HS_CODE"          ,resource.getMessage("TXT_HS_CODE")		,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
					["HS_CODE_DESC"     ,resource.getMessage("DSCPT")	        ,260    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0]
	                 ],

		initGrid_1 : function() {
			var dg_1 = grid.getObject("MMA011_01_grid_01");

			grid.init.setPage(true);
			grid.init.setFitColumns(true);
			// 이벤트 설정
			grid.event.setOnDblClickRow(dg_1);
			
			var params = form.handle.getElementsParam("MMA011_01_form_01");
			
			if(params.HS_CODE != "" || params.HS_CODE_NAME != "") {
            	grid.init.setURL("/mm/pop/mmA011_01/selectMainList");
            } else {
                grid.init.setURL("#");
            }
			
			grid.init.setQueryParams(params);
			Theme.defaultGrid(dg_1, this.header);
		}
    },
    event : { // 이벤트 처리
		onClick_MMA011_01_grid_01 : function(rowData) {},
		onDblClick_MMA011_01_grid_01 : function(rowData) {
			var pid = form.handle.getValue("MMA011_01_form_01", "TARGET_PID");
			//alert(pid);
            if (!oUtil.isNull(pid)) {
            	var pro_id = eval("window." + pid + ".dialog");
            	
                if (typeof(pro_id["callByMMA011_01"]) == "function") {
                    pro_id["callByMMA011_01"](rowData);
                }
            }
		}
    },
    control : {// 업무구현
    	selectMainList : function() {    		
    		// 검색창의 validation 체크결과를 비교한다.
    		if(!form.handle.isValidate("MMA011_01_form_01")) return;
    		
    		var dg_1 = grid.getObject("MMA011_01_grid_01");
    		var params = form.handle.getElementsParam("MMA011_01_form_01");
    		
    		grid.handle.sendRedirect(dg_1, "/mm/pop/mmA011_01/selectMainList", params);
    	},
    	selectRowValue : function() {
    		var dg_1 = grid.getObject("MMA011_01_grid_01");
    		var rowData = grid.handle.getSelectedRowData(dg_1);
    		
    		if(!grid.handle.isSelected(dg_1)) {
                // 그리드에서 열을 선택해 주세요.
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
    		}
    		
    		MMA011_01.event.onDblClick_MMA011_01_grid_01(rowData);
    	}
    },
    dialog : { /******** dialog 구현 *********/
    	openDialog : function() {
    	},
    	// dialog창에서 지정된 값을 메인에 입력하는 함수 구현(callBy+호출할 창의 PID)
    	callBySM1005 : function(datas) {
    		var dg_1 = dialog.getObject("SM1005_dailog");
    		
    		// dialog에서 넘겨받은 데이터를 form의 element에 set한다.
    		form.handle.setValue("SM1001_form_01", "sch5", datas.CODE);
    		form.handle.setValue("SM1001_form_01", "sch6", datas.CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dg_1);
    	}
    },    file : {
    	// 파일 input 엘리먼트 구현
    },
    excel : {
    	// 엑셀다운로드 구현
    }
	
};
