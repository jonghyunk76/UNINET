/******************************************************************************************
 * 작성자 : 
 * Program Id : SM-1001_01
 * 작업일자 : 2016.03.25
 * 
 * <Header 작성법>
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
	SM1001_01.datagrid.initGrid_1();
	SM1001_01.combobox.initCombo_1();
	SM1001_01.combobox.initCombo_2();
	SM1001_01.combobox.initCombo_3();
	SM1001_01.calendar.initCalendar();
}

var SM1001_01 = {
	
	// 초기값 설정
    init : {},
    // 달력 그리드 생성
	calendar : { 
		initCalendar : function() {
			var obj_01 = calendar.getObject("SM1001_01_form_01", "calen");
			
			calendar.init.setRequired(true);
			
			calendar.create(obj_01);
		}
	}, 
	// 콤보박스 그리드 생성
	combobox : {
		data_3 : [{CODE : "ITEM_CODE", NAME : resource.getMessage("LTIT, CODE")}, {CODE : "ITEM_NAME", NAME : resource.getMessage("LTIT, NAME")}],
		// case1) URL 호출에 의한 ComboBox 데이터 생성 방법
		initCombo_1 : function() {
			var obj = combo.getObject("SM1001_01_form_01", "sch2");
			
			// 데이터를 가져올 요청 URL
			combo.init.setURL("/mm/cbox/selectCompany");
			// 요청시 적용할 파라메터(json타입)
			combo.init.setQueryParams({CODE:"KR"});
			// 생성 완료 시 자동으로 셋팅될 값
			combo.init.setValue("100000");
			// 콤보박스의 code로 지정될 필드명
			combo.init.setValueField("CODE");
			// 콤보박스의 code로 지정될 필드명
			combo.init.setNameField("NAME");
			// 콤보박스를 펼쳤을 때 바의 높이 지정
			//combo.init.setHeight(200);
			// 콤보박스 상태가 변경될 때 호출될 함수(window.SM1001_01.combobox.함수명 호출)
			combo.init.setCallFunction("onChangeCompany");
			
			combo.create(obj);
		},
		// case2) case1에서 지정한 콜백함수를 이용한 ComboBox 데이터 생성 방법(this.onChangeCompany() 함수에서 생성)
		initCombo_2 : function() {
			var obj = combo.getObject("SM1001_01_form_01", "sch3");
			
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		},
		// case3) 사용자 지정에 의한 ComboBox 데이터 생성 방법
		initCombo_3 : function() {
			var obj = combo.getObject("SM1001_01_form_01", "sch4");
			
			combo.init.setData(this.data_3);
			combo.init.setValueField("CODE");
			combo.init.setNameField("NAME");
			
			combo.create(obj);
		}
	},
	// 데이터 그리드 생성
    datagrid : { 
		data1 : [{VENDOR_CODE:"VENDOR1", VENDOR_NAME:"NAME1"},{VENDOR_CODE:"1234", VENDOR_NAME:"TNI Co,."}],
		data2 : [{VENDOR_CODE:"V10001", VENDOR_NAME:"TNI"}],
		header : [
		          //Field ID		,Field Name								,Width Size	,Align		,halign		,Sort	,hidden, 	,editor type	,checkbox	,format		,rowspan	,colspan	,frozen, styler
		          ["CHECK"        	,"CHECK"      							,100		,"center"	,"center"	,true	,false		,null			,true		,null		,0			,0, true],
		          ["CODE"      	  	,resource.getMessage("CUNTR, CODE") 	,200		,"left"		,"center"	,true	,false		,null			,null		,null		,0			,0],
		          ["CODE_NAME"    	,resource.getMessage("CUNTR, NAME")		,200		,"left"		,"center"	,true	,false		,null			,null		,null		,0			,0]
		         ],
		initGrid_1 : function() {
			// 그리드 객체 획득
			var dg_1 = grid.getObject("SM1001_01_grid_01");
			
			// 그리드 상단에 표시할 제목
			//grid.init.setTitle("TEST Page..."); 
			// URL 호출에 의해 그리드의 row에 표시할 경우에 적용할 것
			//grid.init.setURL("/mm/smp/smp1001/selectMainList"); 
			// Json타입의 데이터를 그리드의 row에 표시할 경우에 적용할 것
			// grid.init.setData(this.data1);
			// 그리드가 조회된 후 호출될 함수
			grid.init.setCallBackFunction("callBackNoData");
			// 페이지 설정
			grid.init.setPage(true);
			
			// row 클릭 이벤트 적용. ["onClick_" + grid id](row) 함수가 호출된다. 
			grid.event.setOnClickRow(dg_1);
			// row 더블클릭 이벤트 적용. ["onDblClick_" + grid id](row) 함수가 호출된다. 
			grid.event.setOnDblClickRow(dg_1);
			
			// 기본 데마를 적용한 그리드를 생성한다.
			Theme.defaultGrid(dg_1, this.header);
		}
    },
    // 이벤트 구현
    event : {
    	// 그리드의 row 클릭 이벤트 처리
		onClick_SM1001_01_grid_01 : function(rowData) {
			;//alert("onClickEvent = " + rowData.VENDOR_CODE);
		},
		// 그리드의 row 더블클릭 이벤트 처리
		onDblClick_SM1001_01_grid_01 : function(rowData) { 
			;//alert("onDblClickEvent = " + rowData.VENDOR_CODE);
		},
		// 그리드 처리 후 setCallBackFunction()에서 지정한 함수가 호출된다.
		callBackNoData : function(data) {
			;//alert(data);
		},
		// 콤보박스 변경 이벤트 처리
		onChangeCompany : function(data) {
			var param = {COMPANY_CD:data};
			
			// 콤보박스 데이터 reload
			combo.handle.reload("SM1001_01_form_01", "sch3", "/mm/cbox/selectDivision", param);
		}
    },
    //
    control : {
    	selectMainList : function() {
    		// 검색창의 validation 체크결과를 비교한다.
    		if(!form.handle.isValidate("SM1001_01_form_01")) return;
    		
    		// 그리드 객체 획득
    		var dg_1 = grid.getObject("SM1001_01_grid_01");
    		// form 내에 있는 모든 element 중 text에 해당하는 모든 정보를 Json타입으로 변환시킨다.
    		var params = form.handle.getElementsParam("SM1001_01_form_01");
    		
    		// 그리드 조회 실행
    		grid.handle.sendRedirect(dg_1, "/mm/smp/smp1001/selectMainList", params);
    	},
    	formTest : function() { 
    		// from 객체 획득
    		var obj = form.getObject("SM1001_01_form_01");
    		
    		// 호출할 URL 지정
    		form.init.setUrl("/mm/smp/smp1001/selectMainList");
    		// form.submit이 정상적으로 완료된 후 호출될 함수 지정
    		form.init.setCallFunc("");
    		// form.submit 시 정상적으로 완료되면 표시될 메시지
	        form.init.setSucceseMessage(MSG_SUCCESS_BODY);
	        // form.submit 시 실패되면 표시될 메시지
	        form.init.setFailMessage(MSG_SAVE_FAILURE);
	        
	        // 서버 요청
    		form.submit(obj);
    	}
    },
    dialog : { /******** dialog 구현 *********/
    	openDialog : function() {
    		// dialog 객체 획득(dialog ID는 띄울 창의 PID로 적용할 것)
    		var dg_1 = dialog.getObject("SM1005_01_dailog_01");
    		
    		// dialog 상단에 표시할 제목
    		dialog.init.setTitle(resource.getMessage("SMP1005"));
    		// dialog 창 넓이
    		dialog.init.setWidth(600);
    		// dialog 창 높이
    		dialog.init.setHeight(400);
    		// dialog에 표시할 페이지의 URL정보
    		dialog.init.setURL("/mm/smp/smp1005");
    		// dialog 서버 요청 시 포함할 파라메터 적용
    		dialog.init.setQueryParams({TARGET_PID:"SM1001_01", COMPANY_CD:SESSION.COMPANY_CD});
    		
    		// dialog창 열기
    		dialog.open(dg_1);
    	},
    	// dialog창에서 지정된 값을 메인에 입력하는 함수 구현(callBy+호출할 창의 PID)
    	callBySM1005_01 : function(datas) {
    		var dg_1 = dialog.getObject("SM1005_01_dailog_01");
    		
    		// dialog에서 넘겨받은 데이터를 form의 element에 set한다.
    		form.handle.setValue("SM1001_01_form_01", "sch5", datas.CODE);
    		form.handle.setValue("SM1001_01_form_01", "sch6", datas.CODE_NAME);
    		
    		// 창을 닫는다.
    		dialog.handle.close(dg_1);
    	}
    },
    file : {
    	// 파일 input 엘리먼트 구현
    },
    excel : {
    	// 엑셀다운로드 구현
    }
	
};