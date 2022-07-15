/******************************************************************************************
 * 작성자 : Atom
 * Program Id : MM-B002_11
 * 작업일자 : 2020.07.08
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMB002_25.init.initComponent();
}

var MMB002_25 = {
    
    // 초기값 설정
    init : {    	 
        initComponent : function() {
        	
        	MMB002_25.datagrid.initGrid_1();
        	
        }
    }, 
    // 달력 그리드 생성
    calendar : {}, 
    // 콤보박스 그리드 생성
    combobox : {},
    // 데이터 그리드 생성
    datagrid : {
    	initGrid_1 : function() {
    		var dg_1 = grid.getObject("IC1001_05_grid_01"); // 수입화물진행정보 그리드
			var datas = grid.handle.getAllRows(dg_1);			
			var dg_2 = grid.getObject("MMB002_25_grid_01");

			grid.init.setData(datas); // 바닥의 그리드 데이터를 가져와서 보여줌.
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setShowConfigPage(true);
            grid.init.setRecallFunction("initGrid_1");
                        
            grid.event.setOnDblClickRow(dg_2);
            
            Theme.defaultGrid(dg_2, {params:{HEADER_ID:"header1"}});                                          
        }   
    },
    // 이벤트 처리
    event : {
    	onDblClick_MMB002_25_grid_01 : function(rowData) {
    		var formName = "MMB002_25_form_01";
        	var pid = form.handle.getValue(formName, "TARGET_PID");
        	//var field = form.handle.getValue("IC1001_05_form_01", "FIELD_ID");

        	/* if (oUtil.isNull(rowData)){
        		return;
        	} */
        	
        	console.log(rowData);

            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_25"]) == "function") {
                	//rowData.FIELD_ID = field;
                    pro_id["callByMMB002_25"](rowData);
                }
            }
        }
    },
    // 업무구현
    control : {    	
    	// 선택버튼 클릭
    	selectRowValue : function() {
    		var formName = "MMB002_25_form_01";
            var dg_1 = grid.getObject("MMB002_25_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);            

            if(!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST")); /*그리드에서 열을 선택해 주세요.*/
                return;
            }

            MMB002_25.event.onDblClick_MMB002_25_grid_01(rowData);            
        }
    },
    // 다이얼로그 구현
    dialog : {},
    file : {},
    excel : {},
    ui : {}    
};
