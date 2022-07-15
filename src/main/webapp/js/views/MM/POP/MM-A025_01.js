/******************************************************************************************
 * 작성자 : 이재욱
 * Program Id : OR-5002_05
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA025_01.init.initComponent();
}

var MMA025_01 = {
	    
		// 초기값 설정
	    init : {
	    	initComponent : function() {
	    		var flag = form.handle.getValue("MMA025_01_form_01", "flag");
	    		
	    		MMA025_01.datagrid.initGrid_1();
	    		
	    		// 초기화면에 보여줄 데이터 조회
	    		if(flag != "insert") {
	    			MMA025_01.control.selectMainDetail5();
	    		}
	    		
	    	}
	    },
	    // 달력 그리드 생성
	    calendar : {
	    	
	    }, 
	    // 콤보박스 그리드 생성
	    combobox : {
	    },
	    datagrid : { // 데이터 그리드 생성
			header1 : [
	                    ["CHKECK"		,resource.getMessage("SELET")		 	,100    ,"center"    ,"center"    ,true       ,false    ,null         ,true     ,null    ,0        ,0],
						["FTA_CD"		,resource.getMessage("AGRET,CODE")	,100    ,"center"    ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
						["FTA_NAME"		,resource.getMessage("AGRET,NAME")	,200    ,"left"      ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
	                 ],
	         initGrid_1 : function() {
	 			var dg_1 = grid.getObject("MMA025_01_grid_01");

	     		// 팝업으로 넘겨줄 파라메터 생성
	     		var params1 = {};
	     		
	     		//params1.flag = flag;
	    		params1.COMPANY_CD = form.handle.getValue("MMA025_01_form_01", "COMPANY_CD");
	    		params1.DIVISION_CD = form.handle.getValue("MMA025_01_form_01", "DIVISION_CD");
	    		params1.INVOICE_NO = form.handle.getValue("MMA025_01_form_01", "INVOICE_NO");
	    		
	            grid.init.setQueryParams(params1);
	            grid.init.setURL("/mm/pop/mmA025_01/selectMainList");
	            grid.init.setEmptyMessage(false);

	            grid.init.setFitColumns(true);

	 			// 이벤트 설정
	 			grid.event.setOnDblClickRow(dg_1);

	 			Theme.defaultGrid(dg_1, this.header1);	            
	 		},
	     },
	    // 이벤트 처리
	    event : {
	    	// 상세 조회가 성공적으로 완료될 경우 호출되는 함수
	    	selectFTAAfterUI : function(data) {
	    		// selectMainDetail으로 부터 조회된 데이터를 form에 자동 입력함(input id와 name비교)
	    		form.handle.loadData("OR5102_02_form_01", data);
	    		
	    	},
	    	selectItemListAfter : function(data) {
	    		// selectMainDetail으로 부터 조회된 데이터를 form에 자동 입력함(input id와 name비교)
    		
	    	},
			onDblClick_MMA025_01_grid_01 : function(rowData) {
			},	    	
	    },
	    control : {// 업무구현
	    	selectFTA : function() {
	    		
	        	var dg_1 = grid.getObject("MMA025_01_grid_01");
	    		form.handle.setValue("MMA025_01_form_01", "gridData", grid.util.convertJson2Rows(grid.handle.getCheckedRowsData(dg_1, null, null)));	    		
	    		
	        	var dg_2 = grid.getObject("OR5102_02_grid_01");
	        	var params = form.handle.getElementsParam("MMA025_01_form_01");
	        	
        		grid.handle.sendRedirect(dg_2, "/fm/or/or5102_02/selectMainDetail2", params);

	    	},

	    	selectRowValue : function() {
	    		var dg_1 = grid.getObject("MMA025_01_grid_01");
	    		var rowData = grid.handle.getCheckedRowsData(dg_1);
	    		
	    		if(!grid.handle.isChecked(dg_1)) {
	    			alert("그리드에서 열을 체크해 주세요.");
	    		}
	    		
				var pid = form.handle.getValue("MMA025_01_form_01", "TARGET_PID");
				//alert(pid);
	            if (!oUtil.isNull(pid)) {
	            	var pro_id = eval("window." + pid + ".dialog");
	            	
	                if (typeof(pro_id["callByMMA025_01"]) == "function") {
	                    pro_id["callByMMA025_01"](rowData);
	                }
	            }
	    	}	    	
	    },
	    dialog : {
	    },
	    // 화면 UI 변경
	    ui : {
	    	viewChangeUI : function() {
	    	}
	    },
	    // 파일 input 엘리먼트 구현
	    file : { },
	    // 엑셀다운로드 구현
	    excel : { }
	    
	};