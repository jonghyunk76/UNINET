/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-B002_01
 * 작업일자 : 2020.05.08
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_01.config.applyFtaNation();
    MMB002_01.init.initComponent();
}

var MMB002_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_01.datagrid.initGrid_1();
        	MMB002_01.datagrid.initGrid_2();
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
    	header1 : [
    		["CHECK"             ,"Check"                         ,20       ,"center"       ,"center"    ,false       ,false     ,null         ,true     ,null    ,0        ,0],
    		["CODE"              ,resource.getMessage("CODE")     ,60       ,"center"       ,"center"    ,false       ,false     ,null         ,null     ,null    ,0        ,0],
    		["CODE_NAME"         ,resource.getMessage("DSCPT")    ,180      ,"left"         ,"center"    ,false       ,false     ,null         ,null     ,null    ,0        ,0]
    	],
    	initGrid_1 : function() {
    		var params = new Object();
    		
    		params.COMPANY_CD = SESSION.COMPANY_CD;
    		params.CATEGORY_CD = "C_IMP_CSCL_PLAN_CD";
    		
    		var dg_1 = grid.getObject("MMB002_01_grid_01");
            
    		grid.init.setData([{"":""}]); // 화면 테스트를 위한 목적으로 실제 개발시에는 삭제할 것
    		grid.init.setURL("/mm/smp/smp1002/selectSysCodeList");
    		grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setFitColumns(true);
            
            Theme.defaultGrid(dg_1, this.header1);
        },
        initGrid_2 : function() {
    		var dg_1 = grid.getObject("MMB002_01_grid_02");
            
    		grid.init.setData([{"":""}]); // 화면 테스트를 위한 목적으로 실제 개발시에는 삭제할 것
            grid.init.setPage(false);
            grid.init.setEmptyMessage(true);
            grid.init.setFitColumns(true);
            
            Theme.defaultGrid(dg_1, this.header1);
        }
    },
    // 이벤트 처리
    event : {
    	applyEntrPlan : function() {
            var pid = form.handle.getValue("MMB002_01_form_01", "TARGET_PID");
            
            if(!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMB002_01"]) == "function") {
                	var plan = form.handle.getValue("MMB002_01_form_01", "ENTR_PLAN");
                	
                    pro_id["callByMMB002_01"](plan);
                }
            }
        }
    },
    // 업무구현
    control : {
    	setEntrPlan : function() {
    		var plan = "";
    		var dg_1 = grid.getObject("MMB002_01_grid_02");
        	var rows = grid.handle.getAllRows(dg_1);
        	
        	for(var k = 0; k < rows.length; k++) {
        		plan = plan + rows[k].CODE;
        	}
        	
        	form.handle.setValue("MMB002_01_form_01", "ENTR_PLAN", plan);
    	}
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
    	hiddenColunm : function() {
        	var dg_1 = grid.getObject("MMB002_01_grid_01");
        	var crows = grid.handle.getCheckedRowsData(dg_1);
        	var dg_2 = grid.getObject("MMB002_01_grid_02");
        	var arows = grid.handle.getAllRows(dg_2);
        	
    		for(var i = 0; i < crows.length; i++) {
    			var chk = false;
    			
    			for(var k = 0; k < arows.length; k++) {
    				if(arows[k].CODE == crows[i].CODE) {
    					chk = true;
    					break;
    				}
    			}
    			
    			if(!chk) grid.handle.appendRow(dg_2, crows[i]);
    		}
    		
    		MMB002_01.control.setEntrPlan();
        },
        showColunm : function() {
        	var dg_2 = grid.getObject("MMB002_01_grid_02");
        	var crows = grid.handle.getCheckedRowsData(dg_2);
        	var arows = grid.handle.getAllRows(dg_2);
        	
        	for(var i = (arows.length - 1); i >= 0; i--) {
    			var chk = false;
    			
    			for(var k = 0; k < crows.length; k++) {
    				if(arows[i].CODE == crows[k].CODE) {
    					chk = true;
    					break;
    				}
    			}
    			
    			if(chk) grid.handle.removeRow(dg_2, i);
    		}
        	
        	MMB002_01.control.setEntrPlan();
        },
        moveRow : function(step) {
        	var dg_1 = grid.getObject("MMB002_01_grid_02");
        	var rows = grid.handle.getAllRows(dg_1);
        	var row = grid.handle.getSelectedRowData(dg_1);
        	
        	if (row){
        		var index = grid.handle.getCurrentRowIndex(dg_1);
        		var chgIdx;
        		
        		if(step == 1 || step == -1) {
	        		if(index == 0 && step == -1) return;
	        		if(index == (rows.length-1) && step == 1) return;
	        		
	        		chgIdx = index+step;
        		} else if(step == 0) {
        			chgIdx = 0;
        		} else if(step == 9) {
        			chgIdx = rows.length-1;
        		}
        		
        		rows.splice(index, 1); // 현재열에서 삭제
        		rows.splice(chgIdx, 0, row); // 현재열에서 위아래있는 값을 저장
        		
        		dg_1.datagrid('loadData',rows);
				dg_1.datagrid('scrollTo',chgIdx);
                dg_1.datagrid('selectRow',chgIdx);
        	}
        	
        	MMB002_01.control.setEntrPlan();
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
    
};
