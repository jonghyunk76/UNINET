var gridMethod = {
    id : null,
    init : {
        
        config : {},
        
        getConfig : function() {
            if(dataset.isArrayObject(grid.id + ".init")) {
                return dataset.arrayFilter(grid.id +".init");
            } else {
                return this.config;
            }
        },
        
        /**
         * grid 타이틀 설정(지정시 그리드 상위에 표시됨)
         * 
         * @param string = grid 타이틀
         */
        setTitle : function(title) {
            if (!oUtil.isEmpty(title)) {
                this.getConfig().title = "<div style='color:#000;'>" + title + "</div>";
                this.getConfig().iconCls = "icon-sub-title";
            } else {
                $.messager.alert("ERROR", "setTitle() parameter input error.");
            }
        },
        
        /**
         * 해더 표시여부 설정(default = true)
         *  
         * @param boolean = 표시:true, 미표시:false
         */
        setShowHeader : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().showHeader = boolean;
            } else {
                $.messager.alert("ERROR", "setShowHeader() parameter input error.");
            }
        },
        
        /**
         * 조회된 컬럼의 열 넓이을 자동으로 조절(수평 스크룰 제거시 적용)(default = false)
         * 
         * @param boolean = 자동저절:true, 적용안함:false
         */
        setFitColumns : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().fitColumns = boolean;
                this.getConfig().fit = true;
            } else {
                $.messager.alert("ERROR", "setFitColumns() parameter input error.");
            }
        },
        
        /**
         * 자동으로 열 넓이에 맞게 cell 크기를 조정되며, 수평 스크룰 제거시 적용 사용(default = false)
         * 
         * @param boolean = 자동저절:true, 적용안함:false
         */
        setFit : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().fit = boolean;
            } else {
                $.messager.alert("ERROR", "setFitColumns() parameter input error.");
            }
        },
        
        /**
         * 자동으로 열 넓이에 맞게 cell 크기를 조정되며, 수평 스크룰 제거시 적용 사용(default = false)
         * 
         * @param boolean = 자동저절:true, 적용안함:false
         */
        setAutoColumWidth : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().autoColumWidth = boolean;
            } else {
                $.messager.alert("ERROR", "setAutoColumWidth() parameter input error.");
            }
        },
        
        /**
         * 그리드 첫컬럼에 번호를 붙일지 여부를 설정(default = false)
         * 
         * @param boolean = 표시:true, 미표시:false
         */
        setRownumbers : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().rownumbers = boolean;
            } else {
                $.messager.alert("ERROR", "setRownumbers() parameter input error.");
            }
        },
        /**
         * 단일 선택여부 설정(default = false)
         * 
         * @param boolean = 단일선택:true, 멀티선택:false
         */
        setSingleSelect : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().singleSelect = boolean;
            } else {
                $.messager.alert("ERROR", "setSingleSelect() parameter input error.");
            }
        },
        /**
         * 정렬방식 설정(default = asc)
         * 
         * @param flag = asc 또는 desc
         */
        setSortOrder : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().sortOrder = string;
            } else {
                $.messager.alert("ERROR", "setSortOrder() parameter input error.");
            }
        },
        /**
         * 정렬할 필드ID 설정
         * 
         * @param string : 필드ID
         */
        setSortName : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().sortName = string;
            } else {
                $.messager.alert("ERROR", "setSortName() parameter input error.");
            }
        },
        /**
         * 외부 정렬 수행여부 설정
         * 
         * @param boolean =
         *            정렬:true, 정렬안함:false
         */
        setRemoteSort : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().remoteSort = boolean;
            } else {
                $.messager.alert("ERROR", "setRemoteSort() parameter input error.(true or false)");
            }
        },
        /**
         * 요청 URL 경로 설정(#으로 입력하면 URL을 설정하지 않음)
         * 
         * @param string = 경로
         */
        setURL : function(string) {
            if (!oUtil.isEmpty(string)) {
                if(string == "#") {
                    this.getConfig().url = null;
                } else {
                    this.getConfig().url = string;
                }
            } else {
                $.messager.alert("ERROR", "setURL() parameter input error(# or url).");
            }
        },
        /**
         * 데이터 그리드 초기화시 화면에 보여줄 data 설정
         * 
         * @param json = josn타입의 string
         */
        setData : function(json) {
            if(!oUtil.isNull(json)) {
                var rowData = {"total":json.length, "rows":json};
            
                this.getConfig().data = rowData;
            }
        },
        /**
         * 서버요청 방식 설정(default = post)
         * 
         * @param flag = post or get 
         */
        setMethod : function(flag) {
            if (!oUtil.isEmpty(flag)) {
                this.getConfig().method = flag;
            } else {
                $.messager.alert("ERROR", "setMethod() parameter input error.");
            }
        },
        /**
         * 서버요청 쿼리 parameter 설정
         * 
         * @param string = 파라메터
         */
        setQueryParams : function(string) {
            if (oUtil.isObject(string)) {
                this.getConfig().queryParams = string;
            } else {
                $.messager.alert("ERROR", "setQueryParams() parameter input error.");
            }
        },
        /**
         * 한 라인에 데이터를 표시할지 여부 설정. 단, true로 지정해야 성능이 향상됨(default = true)
         * 
         * @param boolean = 표시:true, 미표시:false
         */
        setNowrap : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().nowrap = boolean;
            } else {
                $.messager.alert("ERROR", "setNowrap() parameter input error.");
            }
        },
        /**
         * 열 높이를 cell에 채워진 글짜 높이만큼 자동할지 여부를 설정(default = true)
         * 
         * @param boolean = 자동조절:true, 조절안함:false
         */
        setAutoRowHeight : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().autoRowHeight = boolean;
            } else {
                $.messager.alert("ERROR", "setAutoRowHeight() parameter input error.");
            }
        },
        
        /**
         * row의 높이를 fix
         * 
         * @param number = 높이
         */
        setFixRowHeight : function(number) {
            if (oUtil.isNumber(number)) {
                this.getConfig().fixRowHeight = number;
            } else {
                $.messager.alert("ERROR", "setFixRowHeight() parameter input error.");
            }
        },
        
        /**
         * 서버요청 시 표시될 메시지 설정
         * 
         * @param string = 메시지
         */
        setLoadMsg : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().loadMsg = string;
            } else {
                $.messager.alert("ERROR", "setLoadMsg() parameter input error.");
            }
        },
        /**
         * 페이징 도구 모음 표시여부 설정(default = false)
         * 
         * @param boolean = 표시:true, 숨김:false
         */
        setPage : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().pagination = boolean;
            } else {
                $.messager.alert("ERROR", "setPagination() parameter input error.");
            }
        },
        /**
         * 페이징 도구 모음의 표시 위치 설정(default = bottom)
         * 
         * @param position = top, bottom, both
         */
        setPagePosition : function(position) {
            if (!oUtil.isEmpty(position)) {
                this.getConfig().pagePosition = position;
            } else {
                $.messager.alert("ERROR", "setPagePosition() parameter input error.");
            }
        },
        /**
         * 컬럼 넓이 조정여부 설정
         * 
         * @param boolean = 조정:true, 고정:false 
         */
        setResizable : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().resizable = boolean;
            } else {
                $.messager.alert("ERROR", "setResizable() parameter input error.");
            }
        },
        /**
         * 페이징 지정시 초기 페이지 번호 설정(default = 1)
         * 
         * @param number = 초기 페이지에 보여질 페이지 번호
         */
        setPageNumber : function(number) {
            if (oUtil.isNumber(number)) {
                this.getConfig().pageNumber = number;
            } else {
                $.messager.alert("ERROR", "setPageNumber() parameter input error.");
            }
        },
        /**
         * 페이징 지정시 초기 페이지 크기 설정(default = 10)
         * 
         * @param number = page크기
         */
        setPageSize : function(number) {
            if (oUtil.isNumber(number)) {
                this.getConfig().pageSize = number;
            } else {
                $.messager.alert("ERROR", "setPageSize() parameter input error.");
            }
        },
        /**
         * 페이징 지정시 페이지에 표시할 row 갯수 설정(default = [100, 200, 300])
         * 
         * @param array = 배열로 지정
         */
        setPageList : function(array) {
            if (oUtil.isArray(array)) {
                this.getConfig().pageList = array;
            } else {
                $.messager.alert("ERROR", "setPageList() parameter input error.");
            }
        },
        /**
         * 페이징 지정시 페이지 표시여부 지정 설정
         * 
         * @param boolean = 표시:true, 숨김:false
         */
        setShowPageList : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().showPageList = boolean;
            } else {
                $.messager.alert("ERROR", "setShowPageList() parameter input error.(true or false)");
            }
        },
        /**
         * 페이징에 표시할 항목 지정
         * 
         * @param boolean = 표시:true, 숨김:false
         */
        setPageLayout : function(array) {
            if (oUtil.isArray(array)) {
                this.getConfig().pageLayout = array;
            } else {
                $.messager.alert("ERROR", "setShowPageList() parameter input error.");
            }
        },
        /**
         * 그리드 header 정보 셋팅 설정
         * 
         * @param json = json타입의 배열
         */
        setColumns : function(json) {
            if (!oUtil.isEmpty(json)) {
                this.getConfig().columns = json;
            } else {
                $.messager.alert("ERROR", "setColumns() parameter input error.");
            }
        },
        /**
         * 그리드 row의 style 설정
         * 
         * @param string = 열에 적용될 style
         */
        setRowStyler : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().rowStyler = function(index, row) {
                    return string;
                };
            } else {
                $.messager.alert("ERROR", "setRowStyler() parameter input error.");
            }
        },
        /**
         * 
         * 그리드 toolbar로 지정할 엘리먼트 설정 
         * 
         * @param element = toolbar로 지정할 element ID
         */
        setToolbar : function(element) {
            this.getConfig().toolbar = "#" + element;
        },
        
        /**
         * 그리드 row 선택시 체크여부 설정
         * 
         * @param boolean = row 클릭시 체크:true, check box 클릭 시 에만 체크:false
         */
        setCheckOnSelect : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().checkOnSelect = boolean;
            } else {
                $.messager.alert("ERROR", "setCheckOnSelect() parameter input error.(true or false)");
            }
        },
        
        /**
         * check 박스의 체크 모드 설정
         * 
         *  @param boolean = 직접 체크박스 클릭 시에만 체크:true, 직접 체크하는 방식 이외의 체크도 허용 : false
         */
        setCheckOnBox : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().checkOnBox = boolean;
            } else {
                $.messager.alert("ERROR", "selectOnCheck() parameter input error.(true or false)");
            }
        },
        
        /**
         * check 박스 체크시 열 선택여부 설정
         * 
         *  @param boolean = 체크 시 row 선택:true, 체크해도 row가 선택되지 않음 : false
         */
        setSelectOnCheck : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().selectOnCheck = boolean;
            } else {
                $.messager.alert("ERROR", "selectOnCheck() parameter input error.(true or false)");
            }
        },
        
        /**
         * 데이터 그리드를 에디터 모드로 사용할지 여부 설정<br>
         * (default = false)
         * 
         * @param boolean = true:표시, false:숨김
         * @param scope = 선택범위(row, cell) - default:row
         */
        setEditMode : function(boolean, scope) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().editMode = boolean;
                if(boolean) {
                    if(oUtil.isNull(scope)) scope = "row";
                    this.getConfig().scope = scope;
                }
            } else {
                $.messager.alert("ERROR", "setEditorMode() parameter input error.(true or false)");
            }
        },
        /**
         * 그리드 조회결과 데이터가 없는 경우 메시지를 표시할지 여부 설정<br>
         * (default = true)
         * 
         * @param boolean = true:표시, false:숨김
         */
        setEmptyMessage : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().onEmptyMessage = boolean;
            } else {
                $.messager.alert("ERROR", "onEmptyMessage() parameter input error.(true or false)");
            }
        },
        /**
         * 요청이 성공적으로 완료된 후 호출될 함수 설정.<br>
         * 참고로, 조회된 데이터가 없는 경우에는 호출되지 않으므로 setCallBackFunctionForNoData을 사용해야 함
         * 
         * @param string = 호출될 함수명
         */
        setCallBackFunction : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().callBackFunction = string;
            } else {
                $.messager.alert("ERROR", "setCallBackFunction() parameter input error.");
            }
        },
        
        /**
         * 조회된 데이터가 없는 경우 호출될 함수 설정
         * 
         * @param string = 호출될 함수명
         */
        setCallBackFunctionForNoData : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().callBackFunctionForNoData = string;
            } else {
                $.messager.alert("ERROR", "setCallBackFunctionForNoData() parameter input error.");
            }
        },
        
        /**
         * 그리드 설정을 변경한 후 화면에 변경된 Datagird를 출력하기 위한 재귀함수명
         * (호출 경로 : PID+".datagrid."+재귀함수명)
         *   - PID : Datagrid명칭에서 앞 10자리로 정해진 Program 아이디
         *   - 재귀함수명 : Datagrid를 그리기 위해 초기에 호출된 함수명 
         *    
         * @param string = 호출될 함수명
         */
        setRecallFunction : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().recallFunction = string;
            } else {
                $.messager.alert("ERROR", "setCallBackFunctionForNoData() parameter input error.");
            }
        },
        
        /**
         * 셀단위로 에디팅된 상태에서 값이 변경되었을 때 호출하는 함수 설정(2017.12.16 추가)
         * - 에디팅된 셀이 에디팅이 끝나는 시점에 호출됨을 주의할 것
         * @param string = 호출될 함수명
         */
        setCellEditCallBackFunction : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().cellEditCallBackFunction = string;
            } else {
                $.messager.alert("ERROR", "setCellEditCallBackFunction() parameter input error.");
            }
        },
        
        /**
         * 에디팅모드에서 셀지정 후 엔터를 입력하면 호출하는 함수 설정(2020.11.12 추가)
         * @param string = 호출될 함수명
         */
        setCellEnterCallBackFunction : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().cellEnterCallBackFunction = string;
            } else {
                $.messager.alert("ERROR", "setCallBackFunctionForNoData() parameter input error.");
            }
        },
        
        /**
         * 서버 요청전에 호출될 함수 설정
         * 
         * @param string =호출될 함수명
         */
        setBeforeLoadFunction : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().beforeLoadFunction = string;
            } else {
                $.messager.alert("ERROR", "setBeforeLoadFunction() parameter input error.");
            }
        },
        
        /**
         * 그리드 footer 표시여부 설정
         * 
         * @param boolean = true:표시, false:숨김
         */
        setShowFooter : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().showFooter = boolean;
            } else {
                $.messager.alert("ERROR", "setShowFooter() parameter input error.(true or false)");
            }
        },
        
        /**
         * 스크롤 상에 고정 컴럼 설정
         * 
         * @param array = 고정시킬 file 배열
         */
        setFrozenColumns : function(array) {
            if (!oUtil.isEmpty(array)) {
                this.getConfig().frozenColumns = array;
            } else {
                $.messager.alert("ERROR", "setFrozenColumns() parameter input error.");
            }
        },
        /**
         * 데이터 그리드의 특정 cell의 style 설정
         * 
         * @param string = style
         */
        setCellStyler : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().styler = string;
            } else {
                $.messager.alert("ERROR", "setCellStyler() parameter input error.");
            }
        },
        /**
         * 데이터 로딩시 호출할 함수 설정
         * 
         * @param string = 함수명
         */
        setLoadFilter : function(string) {
            this.getConfig().loadFilter = string;
        },
        
        /**
         * 그리드 row 표시방법을 버퍼에 담아 보여줄지 여부 설정(아직 적용이 안되고 오류남)
         * - 스크롤바를 최하위까지 내리면 다음 데이터를 읽어옴
         * 
         * @param boolean = true:버퍼에 담아 지정된 row만큼 보여줌, false:한번에 모두 보여줌
         * @param number = 한번에 표시할 row수(default=100)
         */
        setBufferView: function(boolean, number) {
            if (oUtil.isBoolean(boolean)) {
                if(boolean) {
                    if(oUtil.isNull(number)) number = 100;
                    
                    this.getConfig().view = "scrollview";
                    this.getConfig().pageSize = number;
                } if(boolean) {
                    this.getConfig().view = "";
                }
                
            } else {
                $.messager.alert("ERROR", "setBufferView() parameter input error.(true or false)");
            }
        },
        
        /**
         * Merge 사용여부 설정(default = false)
         * 
         * @param boolean = 적용:true, 미적용:false
         */
        setMergeMode : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().mergeMode = boolean;
            } else {
                $.messager.alert("ERROR", "setMergeMode() parameter input error.");
            }
        },
        
        /**
         * merge 사용시 merge 범위 및 merge key set 설정
         * 
         * @param mergeArray = merge 할 filed array
         * @param mergeKey = merge 체크 할 key filed
         */
        setMergeData : function(array, string) {
            if (oUtil.isArray(array) && !oUtil.isNull(string)) {
                this.getConfig().mergeList = array;
                this.getConfig().mergeKey = string;
            } else {
                $.messager.alert("ERROR", "setMergeData() parameter input error.");
            }
        },
        
        /**
         * 최근 선택열을 자동으로 선택할지 여부를 설정(default = false)
         * 
         * @param boolean = 선택:true, 선택안함:false
         */
        setSelectCurrentRow : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().selectCurrentRow = boolean;
            } else {
                $.messager.alert("ERROR", "setSelectCurrentRow() parameter input error.");
            }
        },
        
        /**
         * 필터 적용여부 설정(default = false)
         * 
         * @param array = 필터를 지정한 필드 리스트
         */
        setRemoteFilter : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().remoteFilter = boolean;
            } else {
                $.messager.alert("ERROR", "setRemoteFilter() parameter input error.");
            }
            
        },
        
        /**
         * 에디팅 시 마지막 cell에서 다음로 넘어갈 경우 자동으로 열을 추가할지 여부 설정(default = true)
         * 
         * @param array = 필터를 지정한 필드 리스트
         */
        autoAddRow : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().autoAddRow = boolean;
            } else {
                $.messager.alert("ERROR", "autoAddRow() parameter input error.");
            }
            
        },
        
        /**
         * 트리가 생성될 기준 fieldID(treegrid, combogrid 전용값)
         *  
         * @param string = fieldID
         */
        setIdField : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().idField = string;
            } else {
                $.messager.alert("ERROR", "setIdField() parameter input error.");
            }
        },
        
        /**
         * 트리생성 시 화면에 표시할 fieldID(treegrid 전용값)
         *  
         * @param string = fieldID
         */
        setTreeField : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().treeField = string;
            } else {
                $.messager.alert("ERROR", "setTreeField() parameter input error.");
            }
        },
        
        /**
         * 트리형태로 표시할지 여부(treegrid 전용값, default = true)
         * 
         * @param array = 필터를 지정한 필드 리스트
         */
        setCollapsible : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().collapsible = boolean;
            } else {
                $.messager.alert("ERROR", "setCollapsible() parameter input error.");
            }
            
        },
        
        /**
         * 트리형태일 경우 닫기 펼치기를 할 때 애니메이션 효과를 부여할지 여부(treegrid 전용값, default = false)
         * 
         * @param array = 필터를 지정한 필드 리스트
         */
        setAnimate : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().animate = boolean;
            } else {
                $.messager.alert("ERROR", "setAnimate() parameter input error.");
            }
            
        },
        
        /**
         * 콤보트리에 표시할 text필드(combogrid 전용값)
         * 
         * @param array = 필드ID
         */
        setTextField : function(String) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().textField = string;
            } else {
                $.messager.alert("ERROR", "setTextField() parameter input error.");
            }
        },
        
        /**
         * 그리드 에디팅 모드에서 빠져나갈 때 이동할 포커스를 지정(에디팅 모드일 경우에만 적용)
         * 
         * @param obj = 포커스될 오브젝트
         */
        setNextFocus : function(obj) {
            this.getConfig().nextFocus = obj;
        },
        
        /**
         * 그리드 하단에 Setting 및 Management 버튼 표시여부 지정 설정
         * 
         * @param boolean = 표시:true, 숨김:false
         */
        setShowConfigPage : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().showConfigPage = boolean;
            } else {
                $.messager.alert("ERROR", "setShowConfigPage() parameter input error.(true or false)");
            }
        },
        
        /**
         * 그리드에서 에데트 셀을 지정하지 않을 경우, 해당 영역을 흐리게 보일지 여부 설정
         * 
         * @param boolean = 표시:true, 숨김:false
         */
        setShowEditorFilm : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().showEditorFilm = boolean;
            } else {
                $.messager.alert("ERROR", "setShowEditorFilm() parameter input error.(true or false)");
            }
        },
        
        /**
         * 멀티정렬 여부 설정
         * 
         * @param boolean = 표시:true, 숨김:false
         */
        setMultiSort : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().multiSort = boolean;
            } else {
                $.messager.alert("ERROR", "setMultiSort() parameter input error.(true or false)");
            }
        },
        
        /**
         * 해더에 필터를 표시할지 여부(2020.05.22)
         * - 단, 해더가 2행 이상인 경우에는 사용할 수 없음
         * 
         * @param boolean = 표시:true, 숨김:false(default)
         */
        setFilter : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().filter = boolean;
            } else {
                $.messager.alert("ERROR", "setFilter() parameter input error.(true or false)");
            }
        },
        
        /**
         * <p>해더에 필터를 표시할지 여부(2020.05.22)</p>
         * <p>1.자체 추가된 설정</p>
         * <p>textbox와 데이터 멀티선택바 함게 표시할 경우 = {field:'field ID', type:'text', muitlBar:true}</p>
         * <p>2.기존 제공하는 설정방법은 API와 동일함</p>
         * 
         * @param boolean : datagrid 또는 treegrid에 적용할 컬럼별 필터의 타입
         */
        setFilterType : function(json) {
            this.getConfig().filterType = json;
        },
        
        /**
         * 버퍼 페이지뷰를 적용할지 여부(2020.06.06)
         * 
         * @param boolean = 버퍼 페이지뷰를 적용할지 여부
         * @param psize = 한번에 읽을 row수(default = 1000)
         */
        setBufferview : function(boolean, psize) {
            if (oUtil.isBoolean(boolean)) {
                if(boolean == true) {
                    if(oUtil.isNull(psize)) psize = 1000;
                    
                    this.getConfig().view = bufferview;
                    this.getConfig().pageSize = psize;
                }
                this.getConfig().buffergrid = boolean;
            } else {
                $.messager.alert("ERROR", "setBufferview() parameter input error.(true or false)");
            }
        },
        
        /**
         * 동일 데이터를 그룹핑해서 보여주기 위한 필드(그룹뷰)
         * 
         * @param array = 필드ID
         */
        setGroupField : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().groupField = string;
                this.getConfig().view = groupview;
            } else {
                $.messager.alert("ERROR", "setGroupField() parameter input error.");
            }
        },
        
        /**
         * 그룹뷰 행에 보여줄 명칭
         * 
         * @param array = 필드ID
         */
        setGroupFormat : function(func) {
            this.getConfig().groupFormatter = func;
        },
        
        /**
         * 그룹뷰를 초기에 접을지 펼칠지 여부 설정
         * 
         * @param string = E:펼치기, C:접기
         */
        setGroupViewType : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().groupViewType = string;
            } else {
                $.messager.alert("ERROR", "setGroupField() parameter input error.");
            }
        },
        
        /**
         * 해더의 첫 부분에 추가할 정보 설정
         * (엑셀 다운로드용)
         * @param json = [[{}],[{}]] 타입의 json 정보
         */
        setFirstExcelHeader : function(json) {
            if (!oUtil.isEmpty(json)) {
                this.getConfig().firstExcelHeader = json;
            } else {
                $.messager.alert("ERROR", "setFirstExcelHeader() parameter input error.");
            }
        },
        
        /**
         * 열의 라인색상을 설정
         * 
         * @param string = 색상코드
         */
        setRowColor : function(string) {
            if (!oUtil.isEmpty(string)) {
                this.getConfig().rowcolor = string;
            } else {
                $.messager.alert("ERROR", "setRowColor() parameter input error.");
            }
        },
        
        /**
         * 그리드에서 최근에 체크한 행에 대해 체크를 유지할지 여부를 설정
         * 
         * @param boolean = 유지:true, 유지안함:false(default)
         */
        setKeepCheck : function(boolean) {
            if (oUtil.isBoolean(boolean)) {
                this.getConfig().keepCheck = boolean;
            } else {
                $.messager.alert("ERROR", "setKeepCheck() parameter input error.(true or false)");
            }
        }
        
    },
    event : {
        
        config : {},
        
        getConfig : function() {
            if(dataset.isArrayObject(grid.id + ".event")) {
                return dataset.arrayFilter(grid.id +".event");
            } else {
                return this.config;
            }
        },
        
        /**
         * 요청 시 서버에서 오류가 발생한 경우 실행됨
         * 
         * @param obj = grid object
         */
        setOnLoadError : function(obj) {
            this.getConfig().onLoadError = function() {
                var pid = grid.handle.getProgramID(obj);
                var gridId = grid.handle.getGridID(obj);
                var conf = grid.getInitConfig(gridId);
                
                try {
                    $("#"+pid+"_searchBtn").each(function (index, item) {
                        $(item).linkbutton("enable");
                    });
                    
                    conf.REQUEST_STATE = "0"; // 요청상태(0:대기중, 1:요청중)
                } catch(e) {
                    ;
                }
                
                var pro_id = eval("window");
                if (!oUtil.isNull(pro_id)) {
                    if (typeof(pro_id["memberSessionChecker"]) == "function") {
                        pro_id["memberSessionChecker"]();
                    } else {
                        $.messager.alert("ERROR", "System error has occurred. Ask person in charge.", "error");
                    }
                }
            };
        },
        
        /**
         * 모든 이벤트에 대해 데이터(grid)가 성공적으로 로드될 경우 실행됨
         * 
         * @param obj = grid object
         * @param callback 완료 후 호출할 함수명
         */
        setOnLoadSuccess : function(obj) {
            if (!oUtil.isNull(obj)) {
                this.getConfig().onLoadSuccess = function(data) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var conf = grid.getInitConfig(gridId);
                    
                    try {
                        $("#"+pid+"_searchBtn").each(function (index, item) {
                            $(item).linkbutton("enable");
                        });
                    } catch(e) {
                        ;
                    }
                    
                    conf.REQUEST_STATE = "0"; // 요청상태(0:대기중, 1:요청중)
                    
                    if(oUtil.isNull(conf.checkData)) {                      
                        grid.handle.checkAll(obj, false); // 체크했을 때
                    }
                    if(!oUtil.isNull(data)) {
                        // 이스케이프된 문자를 복원시킨다.
                        if(!oUtil.isNull(data.rows)) {
                            var datas = data.rows;
                            var tcnt = 0;
                            
                            for(var i in datas) {
                                $.each(datas[i], function(key, value) {
                                    if(oUtil.isNull(value)) value = ""; // MS-SQL의 Null문자에 대해서 공백으로 처리함
                                    
                                    if(typeof value == "string") {
                                        data.rows[i][key] = unescape(value);
                                    }
                                });
                                
                                if(oUtil.isNull(datas[i].rownum)) {
                                    datas[i].rownum = tcnt;
                                }
                                
                                // 이전에 체크된 데이터가 있으면 체크한다.(2021.11.12)
                                if(!oUtil.isNull(conf.checkData)) {
                                    conf.checkData.filter(function(ckdata) {
                                        if(datas[i].rownum == ckdata.rownum) {
                                            obj.datagrid("checkRow", tcnt);
                                            return;
                                        }
                                    });
                                }
                                
                                
                                tcnt++;
                            }
                            
                            var pager = obj.datagrid('getPager');
                            
                            if(conf.showConfigPage) {
                                var layout = conf.pageLayout;
                                
                                if(layout[0] == "list") {
                                    if(conf.buffergrid == true) {
                                        pager.pagination({
                                            displayMsg:"{rows} of {total}"
                                        });
                                    } else {
                                        pager.pagination({
                                            displayMsg:"{from}~{to} of {total}"
                                        });
                                    }
                                } else {
                                    pager.pagination({
                                        displayMsg:"total: {total}"
                                    });
                                    data.total = tcnt;
                                }
                            } else {
                                if(conf.pagination == false) {
                                    pager.pagination({
                                        displayMsg:"total: {total}"
                                    });
                                    data.total = tcnt;
                                }
                            }
                        } else {
                            conf.checkData = null;
                        }
                        
                        if (!oUtil.isNull(data.message) && data.message.length > 0) {
                            alert(data.message, "window");
                        }
                        
                        var msgView = conf.onEmptyMessage;
                        
                        if (oUtil.isNull(data.rows[0])) {
                            return;
                        }
                        if (!oUtil.isNull(data.rows[0].empty)) {
                            try {
                                if (oUtil.isNull(msgView) || msgView) {
                                    $.messager.show({
                                        title: resource.getMessage("INFMT"),
                                        showSpeed: 100,
                                        msg: "<p style=\"text-align:center;\"><b>" + eval(data.rows[0].empty) + "</b><br>("+resource.getMessage("MSG_CLOSE_SENCODE_WIN")+")</p>",
                                        timeout: 1500,
                                        showType: 'fade',
                                        style: {
                                            right: '',
                                            bottom: ''
                                        }
                                    });
                                }
                            } finally {
                                grid.handle.removeRow(obj, 0); // 첫열을 삭제한다.
                                
                                // 조회된 데이터가 없어도 callbackFuntion을 호출한다.
                                var callback = conf.callBackFunctionForNoData;
                                
                                if (!oUtil.isNull(callback)) {
                                    var pro_id = eval("window." + pid + ".event");
                                    if (!oUtil.isNull(pro_id)) {
                                        // 그리드 ID에 해당하는 <code>function</code>이 있으면 호출된다.
                                        if (typeof(pro_id[callback]) == "function") {
                                            pro_id[callback](data);
                                        }
                                    }
                                }
                            }
                            
                            conf.rowindex = null;
                            conf.rowdata = null;
                        } else {
                            // 성공적으로 요청이 완료되면 지정한 callback함수를 호출한다.
                            var callback = conf.callBackFunction;
                            var mergeMode = conf.mergeMode;
                            var editorMode = conf.editMode;
                            var selectCurrentRow = conf.selectCurrentRow;
                            var scope = conf.scope;
                            
                            if(data.rows.length <= conf.rowindex) {
                                conf.rowindex = null;
                                conf.rowdata = null;
                            } else {
                                // 최근에 선택된 row의 열을 자동으로 선택한다.
                                if(!oUtil.isNull(conf.rowindex) && selectCurrentRow) {
                                    obj.datagrid("selectRow", conf.rowindex);
                                    conf.rowdata = data.rows[conf.rowindex];
                                }
                            }
                            
                            if (mergeMode) {
                                var mergeKey = conf.mergeKey;
                                var idxList = new Array();
                                var mergeFullRowList = grid.handle.getMergeList(data.rows, 0, mergeKey, idxList);
                                
                                grid.handle.mergeRows(obj, mergeFullRowList);
                            }
                            
                            if (!oUtil.isNull(callback)) {
                                var pro_id = eval("window." + pid + ".event");
                                if (!oUtil.isNull(pro_id)) {
                                    // 그리드 ID에 해당하는 <code>function</code>이 있으면 호출된다.
                                    if (typeof(pro_id[callback]) == "function") {
                                        pro_id[callback](data);
                                    }
                                }
                            }
                            
                            if(!selectCurrentRow && editorMode && scope == "cell") {
                                obj.datagrid("selectRow", 0);
                            }
                        }
                    }
                    
                    if(!oUtil.isNull($('#tt')) && $('#tt').length > 0) {
                        var tab = $('#tt').tabs('getSelected');
                        var index = $('#tt').tabs('getTabIndex', tab);
                        if (index > grid.MAX_TAB_NUM) {
                            $('#tt').tabs('close', 1);
                        }
                    }
                    
                    // 열넓이 자동 조절 기능 추가(2020-12-30)
                    if(conf.autoColumWidth == true && (oUtil.isNull(conf.fitColumns) || conf.fitColumns == false)) {
                        grid.handle.autoResizeColumn(obj);
                    }
                    
                    // 그룹뷰 설정 정보가 있는 경우에는 이전 설정정보에 의해 접거나 펴는게 유지되도록 기능을 추가함(2020-12-29)
                    var gfield = conf.groupField;
                    var glist = conf.groupView;
                    var gextend = conf.groupViewType;
                    
                    if(!oUtil.isNull(gfield)) {
                        if(!oUtil.isNull(glist)) {
                            for(var g = 0; g < glist.length; g++) {
                                var group = glist[g].group;
                                
                                if(gextend == "E") {
                                    $(this).datagrid('expandGroup', group.index);
                                } else {
                                    if(group.state == "C") {
                                        $(this).datagrid('collapseGroup', group.index);
                                    } else if(group.state == "E"){
                                        $(this).datagrid('expandGroup', group.index);
                                    }
                                }
    //                          console.log("group view size = " + group.index + ", " + group.state);
                            }
                        } else {
                            var groups = obj.datagrid('groups');
                            if(!oUtil.isNull(groups)) {
                                for(var g = 0; g < groups.length; g++) {
                                    var gdata = groups[g].rows[0];
                                    var status = new Object();
                                    
                                    status.index = g;
                                    if(gridId == "IC1004_01_grid_01" || gridId == "EC2004_01_grid_01") {
                                        if(oUtil.isNull(gdata.STT_ODR)) {
                                            status.state = "E";
                                        } else {
                                            status.state = "C";                                     
                                        }
                                    } else {
                                        if(gextend == "E") {
                                            status.state = "E";
                                        } else {
                                            status.state = "C";                                         
                                        }
                                    }
//                                  console.log("group view size = " + status.index + ", " + status.state);
                                    
                                    grid.setGroupConfig(gridId, status);
                                    
                                    if(status.state == "C") {
                                        $(this).datagrid('collapseGroup', status.index);
                                    } else {
                                        $(this).datagrid('expandGroup', status.index);
                                    }
                                }
                            }
                        }
                    }
                };
            }
        },
        
        /**
         * datagrid를 draw하거나 요청에 의해 재생성되는 경우 호출되는 함수
         * 
         * @return true:서버 요청을 진행한다.
         *         false:서버 요청을 취소시킨다. 
         */
        setOnBeforeLoad : function(obj) {
            this.getConfig().onBeforeLoad = function(param) {
                var pid = grid.handle.getProgramID(obj);
                var gridId = grid.handle.getGridID(obj);
                var conf = grid.getInitConfig(gridId);
                var callback = conf.beforeLoadFunction;
                var urlyn = conf.url;
                var pager = obj.datagrid('getPager');
                
                // datagrid를 load만 할 경우 setOnLoadSuccess함수가 호출되지 않기 때문에 url를 체크해서 disable하지 않도록 한다.
                try {
                    if(!oUtil.isNull(urlyn)) {
                        $("#"+pid+"_searchBtn").each(function (index, item) {
                            $(item).linkbutton("disable");
                        });
                        
                        conf.REQUEST_STATE = "1"; // 요청상태(0:대기중, 1:요청중)
                    }
                } catch(e) {
                    ;
                }
                
                if(conf.showConfigPage) {
                    var layout = conf.pageLayout;
                    
                    if(layout[0] == "list") {
                        if(conf.buffergrid == true) {
                            pager.pagination({
                                displayMsg:"0 of 0"
                            });
                        } else {
                            pager.pagination({
                                displayMsg:"0 to 0 of 0"
                            });
                        }
                    } else {
                        if(conf.pagination == false) {
                            pager.pagination({
                                displayMsg:"total: 0"
                            });
                        }
                    }
                }
                
                if (!oUtil.isNull(callback)) {
                    var pro_id = eval("window." + pid);
                    if (pro_id != null && pro_id != "undefined") {
                        if (typeof(pro_id[callback]) == "function") {
                            return pro_id[callback](param);
                        }
                    }
                }
                
                // 그리드내 모든 멀티 필터창을 닫는다.(2020.07.08)
                $(".icon-multi_filter").each(function(){
                    $(this).tooltip('hide');
                });
                
                return true;
            };
        },
        
        /**
         * 사용자가 row를 클릭했을 때 실행됨-매개변수를 포함하고 있음<br>
         * 이벤트 실행 후 row 값을 구하기 위해선 페이지에  window.[pid].event.onClick_[grid id]함수를 구현하셔야 합니다.
         * 
         * @param obj = grid Object
         */
        setOnClickRow : function(obj) {
            var editIndex = undefined;
            
            if (!oUtil.isNull(obj)) {
                this.getConfig().onClickRow = function(rowIndex, rowData) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var conf = grid.getInitConfig(gridId);
                    var editorMode = conf.editMode;
                    var scope = conf.scope;
                    
                    conf.rowindex = rowIndex;
                    conf.rowdata = rowData;
                    
                    if (editorMode) {
                        // Editor 모드로 변경
                        if (editIndex != rowIndex) {
                            if(scope == "row") {
                                if (grid.edit.endEditing(obj)) {
                                    obj.datagrid('selectRow', rowIndex).datagrid('beginEdit', rowIndex);
                                    grid.edit.setEditIndex(obj, rowIndex);
                                } else {
                                    obj.datagrid('selectRow', editIndex);
                                }
                            }
                        }
                    }
                    
                    var pro_id = eval("window." + pid + ".event");
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onClick_"+gridId]) == "function") {
                            pro_id["onClick_"+gridId](rowData);
                        }
                    }
                    
                    // 체크박스 체크여부 확인(조건문에 부합되는 경우만 동작함)
                    if(conf.singleSelect && !conf.checkOnSelect && 
                       !conf.selectOnCheck && !conf.checkOnBox) {
                        var checkRows = grid.handle.getCheckedRowsIndex(obj);
                        var checked = true;
                        
                        for(var i = 0; i < checkRows.length; i++) {
                            if(checkRows[i] == rowIndex) {
                                checked = false;
                            }
                        }
        
                        if(checked) grid.handle.checkRow(obj, true, rowIndex);
                        else grid.handle.checkRow(obj, false, rowIndex);
                    }
                };
            }
        },
        
        /**
         * 사용자가 row를 더블클릭했을 때 실행됨<br>
         * 이벤트 실행 후 row 값을 구하기 위해선 페이지에 window.[pid].event.onDblClick_[grid id]함수를 구현하셔야 합니다.
         * 
         * @param obj = grid Object
         */ 
        setOnDblClickRow : function(obj) {
            if (!oUtil.isNull(obj)) {
                this.getConfig().onDblClickRow = function(rowIndex, rowData) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var conf = grid.getInitConfig(gridId);
                    
                    conf.rowindex = rowIndex;
                    conf.rowdata = rowData;

                    var pro_id = eval("window." + pid + ".event");
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onDblClick_"+gridId]) == "function") {
                            pro_id["onDblClick_"+gridId](rowData);
                        }
                    }
                };
            }
        },
        
        /**
         * Editing 이후 Merge를 원복하기 위해 호출되는 함수
         * 
         * @param obj = grid object
         */
        setOnAfterEdit : function(obj) {
            /*
             * Fires when user finish editing, the parameters contains:
             * 
             * rowIndex: the editing row index, start with 0
             * rowData: the record corresponding to the editing row
             * changes: the changed field/value pairs
             */
            this.getConfig().onAfterEdit = function(rowIndex, rowData, changes) {
                var gridId = grid.handle.getGridID(obj);
                var conf = grid.getInitConfig(gridId);
                var editorMode = conf.editMode;
                var mergeMode = conf.mergeMode;
                
                if (editorMode) {
                    if (mergeMode) {
                        var mergeKey = conf.mergeKey;
                        var idxList = new Array();
                        var mergeFullRowList = grid.handle.getMergeList(grid.handle.getAllRows(obj), 0, mergeKey, idxList);
                        
                        grid.handle.mergeRows(obj, mergeFullRowList);
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            };
        },
        
        /**
         * 사용자가 cell을 클릭했을 때 실행하는 함수<br>
         * 이벤트 실행 후 cell 값을 구하기 위해선 페이지에 Program-code.[onClickGridCell]함수를 구현하셔야 합니다.
         * 
         * @param obj = grid object
         */
        setOnClickCell : function(obj) {
            if (!oUtil.isNull(obj)) {
                this.getConfig().onClickCell = function(rowIndex, field, value) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var conf = grid.getInitConfig(gridId);
                    var editorMode = conf.editMode;
                    
                    if(editorMode) {
                        obj.datagrid("enableCellEditing").datagrid('gotoCell', {
                            index: rowIndex,
                            field: field
                        });
                    }
                    
                    var pro_id = eval("window." + pid + ".event");
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onClickCell_"+gridId]) == "function") {
                            pro_id["onClickCell_"+gridId](rowIndex, field, value);
                        }
                    }
                    
                    return;
                };
            }
        },
        
        /**
         * 사용자가 cell을 더블클릭했을 때 실행하는 함수<br>
         * 이벤트 실행 후 cell 값을 구하기 위해선 페이지에 Program-code.[onDblClickGridCell]함수를 구현하셔야 합니다.
         * 
         * @param obj = grid object
         */
        setOnDblClickCell : function(obj) {
            if (!oUtil.isNull(obj)) {
                this.getConfig().onDblClickCell = function(rowIndex, field, value) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    
                    var pro_id = eval("window." + pid + ".event");
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onDblClickCell_"+gridId]) == "function") {
                            pro_id["onDblClickCell_"+gridId](rowIndex, field, value);
                        }
                    }
                    
                    return;
                };
            }
        },
        
        /**
         * 그리드를 선택했을 때 실행하는 함수
         * 
         * @param obj = grid object
         */
        setOnSelect : function(obj) {
            if (!oUtil.isNull(obj)) {
                this.getConfig().onSelect = function(rowIndex, rowData) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var conf = grid.getInitConfig(gridId);
                    
                    conf.rowindex = rowIndex;
                    conf.rowdata = rowData;
                    
                    // 에디트 모드일 경우에만 셀을 선택했을 때 수행한다.
                    if(conf.editMode) {
                        var pro_id = eval("window." + pid + ".event");
                        
                        if (!oUtil.isNull(pro_id)) {
                            if (typeof(pro_id["onSelect_"+gridId]) == "function") {
                                pro_id["onSelect_"+gridId](rowData);
                            }
                        }
                    }
                };
            }
        },
        
        /**
         * 그리드에서 cell을 선택했을 때 실행하는 함수
         * 
         * @param obj = grid object
         */
        setOnCellEdit : function(obj) {
            this.getConfig().onCellEdit = function(index, field, value) {
                try {
                    var ed = obj.datagrid('getEditor', {index:index, field:field});
                    
                    if(!oUtil.isNull(ed)) {
                        var tg = $(ed.target);
                        
                        if(tg.hasClass("combo-f")) {
                            tg.combo("showPanel");
                        }
                    }
                } catch(e) { console.log(e); }
            };
        },
        
        /**
         * 해더의 마우스 우측클릭 시 표시할 메뉴 지정
         * 
         * @param obj = grid object
         * @param id = 메뉴 ID
         */
        setOnHeaderContextMenu: function(obj, id){
            this.getConfig().onHeaderContextMenu = function(e, field){
                obj.datagrid('#'+id).menu('show', {
                    left:e.pageX,
                    top:e.pageY
                });
            }
        },
        
        /**
         * 그리드행을 마우스 우측클릭 시 표시할 메뉴 지정(2022-04-27)
         * 
         * @param obj = grid object
         * @param id = 메뉴 ID
         */
        setOnRowContextMenu: function(obj, id){
            if (!oUtil.isNull(obj)) {
                this.getConfig().onRowContextMenu = function(e, index, row){
                    e.preventDefault();
                    
                    grid.handle.selectRowIndex(obj, index);
                    
                    $('#'+id).menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            }
        },
        
        /**
         * 그리드의 체크박스가 변경될 때 실행되는 함수
         * 
         * @param obj = grid object
         */
        setOnCheckBox : function(obj) {
            if (!oUtil.isNull(obj)) {
                this.getConfig().onCheck = function(rowIndex, rowData) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var conf = grid.getInitConfig(gridId);
                    
                    conf.rowindex = rowIndex;
                    conf.rowdata = rowData;
                    
                    var pro_id = eval("window." + pid + ".event");
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onCheckBox_"+gridId]) == "function") {
                            pro_id["onCheckBox_"+gridId](rowData, "check");
                        }
                    }
                };
                
                this.getConfig().onUncheck = function(rowIndex, rowData) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var conf = grid.getInitConfig(gridId);
                    
                    conf.rowindex = rowIndex;
                    conf.rowdata = rowData;
                    
                    var pro_id = eval("window." + pid + ".event");
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onCheckBox_"+gridId]) == "function") {
                            pro_id["onCheckBox_"+gridId](rowData, "uncheck");
                        }
                    }
                };
                
                this.getConfig().onCheckAll = function(rows) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var pro_id = eval("window." + pid + ".event");
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onCheckBox_"+gridId]) == "function") {
                            pro_id["onCheckBox_"+gridId](rows, "checkall");
                        }
                    }
                };
                
                this.getConfig().onUncheckAll = function(rows) {
                    var pid = grid.handle.getProgramID(obj);
                    var gridId = grid.handle.getGridID(obj);
                    var pro_id = eval("window." + pid + ".event");
                    
                    if (!oUtil.isNull(pro_id)) {
                        if (typeof(pro_id["onCheckBox_"+gridId]) == "function") {
                            pro_id["onCheckBox_"+gridId](rows, "uncheckall");
                        }
                    }
                };
            }
        }
    },
    edit : {
        
        config : {},
        
        /**
         * 에디터 모드에서 지정된 row의 index를 저장
         * 
         * @param obj = grid object
         * @param index = 에디팅되고 있는 위치값
         */
        setEditIndex : function(obj, index) {
            var property = grid.getInitConfig(grid.handle.getGridID(obj));
            property.editIndex = index;
        },
        
        /**
         * 에디터 모드에서 지정된 row의 index를 반환
         * 
         * @param obj = grid object
         */
        getEditIndex : function(obj) {
            var property = grid.getInitConfig(grid.handle.getGridID(obj));
            return property.editIndex;
        },
        
        /**
         * rowIndex에 해당 하는 field의 editor 객체를 return
         * 
         * @param obj = grid object
         * @param rowIndex = editor 를 받을 row index
         * @param fieldID = field ID
         */
        getEditor : function(obj, rowIndex, fieldID) {
            obj.datagrid('beginEdit', rowIndex);
            
            return obj.datagrid('getEditor', {
                index: rowIndex,
                field: fieldID
            });
        },
        
        /**
         * rowIndex에 해당 하는 모든 editor를 return
         * 
         * @param obj = grid object
         * @param rowIndex = editor 를 받을 row index
         */
        getAllEditor : function(obj, rowIndex) {
            var edList = obj.datagrid('getEditors', rowIndex);
            
            return edList;
        },
        
        /**
         * rowIndex에 해당 하는 field 값을 return
         * 
         * @param obj = grid object
         * @param rowIndex = editor 를 받을 row index
         * @param field = field ID
         */
        getRowValue : function(obj, rowIndex, field) {
            var ed = this.getEditor(obj, rowIndex, field);
            var value = "";
            
            if (ed.type == "text" || ed.type == "textarea" || ed.type == "validatebox") {
                value = $(ed.target).val();
            } else if (ed.type == "numberbox") {
                value = $(ed.target).numberbox("getValue");
            } else if (ed.type == "datebox") {
                var seperator = "-";
                var date = $(ed.target).datebox('getValue');
                
                if(oUtil.isNull(date)) return;

                var nation = calendar.util.getLocale(date);
                    
                try {
                    date.getFullYear();
                } catch(e) {
                    date = calendar.util.parseDate(date, seperator, nation);
                }
                
                var y = date.getFullYear();
                var m = date.getMonth() + 1;
                var d = date.getDate();
                
                value = calendar.util.chageDate2Nation(y, m, d, "", NATION_DATE_DB);
            } else if (ed.type == "combobox") {
                value = $(ed.target).combobox("getValue");
            } else if (ed.type == "combotree") {
                value = $(ed.target).combotree("getValue");
            } else if (ed.type == "searchbox") {
                value = $(ed.target).searchbox("getValue");
            }
            
            return value;
        },
        
        /**
         * rowIndex에 해당 하는 field 값을 set<br>
         * 이 함수를 적용하기 위해서 datagrid생성 시 editer type을 지정해야 한다.
         * 
         * @param obj = grid object
         * @param rowIndex = editor 를 받을 row index
         * @param fieldID = field ID
         * @param value = set 할 값
         * @param fieldName = Comboxbox인 경우 화면에 표시할 필드명
         */
        setRowValue : function(obj, rowIndex, fieldID, value, fieldName) {
            try {
                var ed = this.getEditor(obj, rowIndex, fieldID);
                
                if (ed.type == "text" || ed.type == "textarea" || ed.type == "validatebox") {
                    if(!oUtil.isNull(value)) $(ed.target).val(value);
                } else if (ed.type == "numberbox") {
                    if(!oUtil.isNull(value)) $(ed.target).numberbox("setValue", value);
                } else if (ed.type == "datebox") {
                    if(!oUtil.isNull(value)) return;
                    
                    var seperator = "-";
                    var date;
                    var nation = calendar.util.getLocale(value);
                    
                    try {
                        date = calendar.util.parseDate(value, seperator, NATION_DATE_DB);
                        
                        var y = date.getFullYear(); 
                        var m = date.getMonth() + 1;
                        var d = date.getDate();
                        
                        $(ed.target).datebox('setValue', calendar.util.chageDate2Nation(y, m, d, seperator, NATION_DATE_VIEW));
                    } catch(e) {
                        return;
                    }
                } else if (ed.type == "combobox") {
                    if(!oUtil.isNull(value)) $(ed.target).combobox("setValue", value);
                    obj.datagrid('getRows')[rowIndex][fieldName] = $(ed.target).combobox("getText");
                } else if (ed.type == "combotree") {
                    if(!oUtil.isNull(value)) $(ed.target).combotree("setValue", value);
                    obj.datagrid('getRows')[rowIndex][fieldName] = $(ed.target).combotree("getText");
                } else if (ed.type == "searchbox") {
                    if(!oUtil.isNull(value)) $(ed.target).searchbox("setValue", value);
                }
            } catch (e) {
                obj.datagrid('rejectChanges');
            }
        },
        
        /**
         * rowIndex에 해당 하는 field 값을 set<br>
         * 이 함수를 적용하기 위해서 grid생성 시 editor type을 지정해야 한다.
         * 
         * @param obj = grid object
         * @param rowIndex = editor 를 받을 row index
         */
        setRowValueEditing : function(obj, rowIndex) {
            obj.datagrid('beginEdit', rowIndex);
            
            try {
                var prop = grid.getInitConfig(grid.handle.getGridID(obj));
                var columns = prop.columns;
                var frozenColumns = prop.frozenColumns;
                
                if (!oUtil.isNull(frozenColumns)) {
                    for (var i = 0; i < frozenColumns.length; i++) {
                        var colData = frozenColumns[i];
                        for (var j in colData) {
                            var field = colData[j].field;
                            var editor = colData[j].editor;
                            var formatter = colData[j].session;
                            
                            if (!oUtil.isNull(editor)) {
                                if (!oUtil.isNull(formatter) && formatter.format == "row") {
                                    var ed = this.getEditor(obj, rowIndex, field);
                                    
                                    if(editor.type == "combobox" || editor.type == "combotree") {
                                        obj.datagrid('getRows')[rowIndex][formatter.field] = $(ed.target).combobox("getText");
                                    }
                                }
                            }
                        }
                    }
                }
                
                if (!oUtil.isNull(columns)) {
                    for (var i = 0; i < columns.length; i++) {
                        var colData = columns[i];
                        for (var j in colData) {
                            var field = colData[j].field;
                            var editor = colData[j].editor;
                            var formatter = colData[j].session;
                            
                            if (!oUtil.isNull(editor)) {
                                if (!oUtil.isNull(formatter) && formatter.format == "row") {
                                    var ed = this.getEditor(obj, rowIndex, field);
                                    
                                    if (editor.type == "combobox" || editor.type == "combotree") {
                                        obj.datagrid('getRows')[rowIndex][formatter.field] = $(ed.target).combobox("getText");
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (e) {
                obj.datagrid('rejectChanges');
            }
        },
        
        /**
         * Editing을 종료하고 최종 입력된 값을 적용
         * 
         * @param obj = grid object
         */
        endEditing : function(grid) {
            var obj;
            
            if(typeof grid == "string") obj = grid.getObject(grid);
            else obj = grid;
            
            var editIndex = this.getEditIndex(obj);
            if (oUtil.isNull(editIndex)) {
                return true;
            }
            if (obj.datagrid('validateRow', editIndex)) {
                this.setRowValueEditing(obj, editIndex);
                
                if (obj.datagrid('validateRow', editIndex)) {
                    obj.datagrid('endEdit', editIndex);
                } else {
                    obj.datagrid('cancelEdit', editIndex);
                }
                
                editIndex = undefined;
                this.setEditIndex(obj, editIndex);
                return true;
            } else {
                return false;
            }
        },
        
        /**
         * 지정된 위치로 cell을 에디팅함(cell edit에서만 사용가능)
         * 
         * @param obj = grid object
         * @param index = row index
         * @param fieldId = 이동할 file ID
         */
        gotoCell : function(obj, index, fieldId) {
            obj.datagrid('editCell', {index:index, field:fieldId});
            //obj.datagrid('gotoCell', {index:index, field:fieldId});
        },
        
        /**
         * 셀에디팅 모드를 종료함
         * @param obj = grid명 또는 object
         */
        endCellEdit : function(grid, nextFocus) {
            var obj;
            
            if(typeof grid == "string") obj = $("#" + grid);
            else obj = grid;
            
            obj.datagrid('endCellEdit');
            
            var opts = obj.datagrid('options');
            var panel = obj.datagrid('getPanel');
            var cell = panel.find('td.datagrid-row-selected');
            
            cell.removeClass('datagrid-row-selected');
            if(opts.nextFocus) opts.nextFocus.focus();
            if(!oUtil.isNull(nextFocus)) nextFocus.focus();
        }
        
    },
    handle : {
        
        config : {},
        
        /**
         * grid Object에서 프로그램 코드를 검색하는 함수(ID의 앞7자리를 찾아 받환)
         * 
         * @parma obj = grid object
         * @return program ID
         */
        getProgramID : function(obj) {
            var pro_id = "";
            var grid_id;
            
            if (typeof obj == "string") {
                grid_id = obj;
            } else if (typeof obj == "object") {
                try {
                    grid_id = obj.datagrid("options").id;
                } catch(e) {
                    grid_id = obj.attr("id");
                }
            }
            
            pro_id = grid_id.substr(0, PROGRAM_ID_NUMBER);
            
            return pro_id;
        },
        
        /**
         * grid object에서 gird id를 검색하는 함수
         * 
         * @parma obj = grid object
         * @return grid ID
         */
        getGridID : function(obj) {
            var pro_id = "";
            var grid_id;
            
            if (typeof obj == "string") {
                grid_id = obj;
            } else if (typeof obj == "object") {
                try {
                    grid_id = obj.datagrid("options").id;
                } catch(e) {
                    grid_id = obj.attr("id");
                }
            }
            
            return grid_id;
        },
        
        /**
         * 서버 URL 요청
         * 
         * @praram obj datagrid
         * @param url = URL
         * @param params = 파라메터
         * @param callback = 응답 후 호출할 함수명 
         */
        sendRedirect : function(obj, url, params, callback) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var prop = grid.getInitConfig(grid.handle.getGridID(obj));
            var urlyn = prop.url;
            var pid = grid.handle.getProgramID(obj);
            var keepck = false;
//            console.log("sendRedirect... prop.REQUEST_STATE = " + prop.REQUEST_STATE);
            if(prop.REQUEST_STATE == "1") return; // 요청상태(0:대기중, 1:요청중)
            
            // 초기 Datagrid 생성 시 URL이 없는 경우 검색버튼 disable시킴
            try {
                if(oUtil.isNull(urlyn)) {
                    $("#"+pid+"_searchBtn").each(function (index, item) {
                        $(item).linkbutton("disable");
                    });
                }
                
                prop.REQUEST_STATE = "1"; // 요청상태(0:대기중, 1:요청중)
                
                keepck = (prop.keepCheck)?prop.keepCheck:false;
            } catch(e) {
                console.log(e);
            }
            
            // 체크박스를 유지하는 경우 체크된 row정보를 저장한다.(2021.11.12)
            var ckdata = obj.datagrid("getChecked");
            var ckflag = prop.checkFlag;
            
            if(oUtil.isNull(ckflag)) ckflag = true;
            
            if(keepck == true && ckdata.length > 0 && ckflag == true) {
                prop.checkData = ckdata;
            } else {
                prop.checkData = null;
            }
            
            var opts = obj.datagrid("options");
            
            opts.url = url;
            opts.queryParams = params;
            opts.pageNumber = 1;
            
            obj.datagrid("reload");
        },
        
        /**
         * 데이터가 그리드가 선택된 상태인지 여부 리턴 - 정상동작
         * 
         * @param obj = grid object obj
         * @return 선택안됨:true, 선택됨:false
         */
        isNotSelected : function(grid) { 
            var obj;
            if(typeof grid == "string") obj = grid.getObject(grid);
            else obj = grid;
            
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            if (!oUtil.isNull(this.getSelectedRowData(obj))) {
                return false;
            } else {
                return true;
            }
        },
        
        /**
         * 데이터가 그리드가 선택된 상태인지 여부 리턴 - 정상동작
         * 
         * @param obj = grid object obj
         * @return 선택:true, 선택안됨:false
         */
        isSelected : function(grid) { 
            var obj;
            if(typeof grid == "string") obj = grid.getObject(grid);
            else obj = grid;
            
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            if (!oUtil.isNull(this.getSelectedRowData(obj))) {
                return true;
            } else {
                return false;
            }
        },
        
        /**
         * 데이터가 그리드내 체크박스가 체크된 상태인지 여부 리턴 - 정상동작
         * 
         * @param obj = grid object obj
         * @return 체크안됨:true, 체크됨:false
         */
        isNotChecked : function(grid) {
            var obj;
            if(typeof grid == "string") obj = grid.getObject(grid);
            else obj = grid;
            
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var data = this.getCheckedRowsData(obj);
            var checkList = this.getRowIndex(obj, data);
            
            if (checkList.length > 0) {
                return false;
            } else {
                return true;
            }
        },
        
        /**
         * 데이터가 그리드내 체크박스가 체크된 상태인지 여부 리턴 - 정상동작
         * 
         * @param obj = grid object obj
         * @return 체크안됨:true, 체크됨:false
         */
        isChecked : function(grid) {
            var obj;
            if(typeof grid == "string") obj = grid.getObject(grid);
            else obj = grid;
            
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var data = this.getCheckedRowsData(obj);
            var checkList = this.getRowIndex(obj, data);
            
            if (checkList.length > 0) {
                return true;
            } else {
                return false;
            }
        },
        
        /**
         * 컬럼의 해더 index(위치)를 구하는 함수 - 정상동작
         * 
         * @param obj = grid object
         * @param fieldID = column Name
         * @returns row index
         */
        getColumnIndex : function(obj, fieldID) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var prop = grid.getInitConfig(grid.handle.getGridID(obj));
            var gridField = prop.columns[0];
            var gridLen = gridField.length;
            var colIndex = 0;
            
            for (var i = 0; i < gridLen; i++) {
                if (gridField[i].field == fieldID) {
                    colIndex = i + 1;
                }
            }
            
            return colIndex;
        },
        
        /**
         * 선택된 row 레코드의 index 번호 리턴<br>
         * (multiSelect mode에서는 마지막으로 선택된 row의 index번호를 리턴함) - 정상동작
         * 
         * @param obj = grid object
         * @returns 선택된 row index
         */
        getCurrentRowIndex : function(obj) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var gridId = grid.handle.getGridID(obj);
            var conf = grid.getInitConfig(gridId);
            
            return conf.rowindex;
        },
        
        /**
         * 선택된 row 레코드의 data 리턴<br>
         * (multiSelect mode에서는 마지막으로 선택된 row의 data를 리턴함) - 정상동작
         * 
         * @param obj = grid object
         * @returns 선택된 row data
         */
        getCurrentRowData : function(obj) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var gridId = grid.handle.getGridID(obj);
            var conf = grid.getInitConfig(gridId);
            
            return conf.rowdata;
        },
        
        /**
         * 현재 선택된 row 레코드의 row 값을 리턴<br> - 정상동작
         * file ID를 지정한 경우 해당하는 필드의 값을 리턴한다.
         * 
         * @param obj = grid object
         * @param fieldID = datagrid에서 정의한 필드ID 
         * @returns {String}
         */
        getSelectedRowData : function(obj, fieldID) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var row = obj.datagrid('getSelected');
            
            if (row == null) {
                return null;
            }
            if (fieldID == null) {
                return row;
            } else {
                var value = eval("row." + fieldID);
                
                if (value != null) {
                    return value.toString();
                } else {
                    return "";
                }
            }
        },
        
        /**
         * 체크된 row 레코드의 모든 데이터를 리턴 - 정상동작
         * 
         * @param obj = grid object
         * @return check된 row의 모든 값(json 타입)
         */
        getCheckedRowsData : function(obj) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            return this.getCheckedRowFieldData(obj, null, null);
        },
        
        /**
         * 체크된 row 레코드의 특정 field ID에서 해당되는 값을 리턴
         * 
         * @param obj = grid object
         * @param checkRow = 가져올 grid data의 row
         * @param fieldID = 가져올 grid data의 field id
         * @return check된 row에 대한 특정 file에 대한 배열
         */
        getCheckedRowFieldData : function(obj, checkRow, fieldID) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var checkRowData = obj.datagrid('getChecked');
            
            if (checkRowData == null) {
                return null;
            }
            
            if (fieldID == null && checkRow == null) {
                return checkRowData;
            } else if (checkRow != null && fieldID == null) {
                return checkRowData[checkRow];
            } else if (checkRow != null && fieldID != null) {
                return eval("checkRowData[" + checkRow + "]." + fieldID);
            }
        },
        
        /**
         * 체크된 row 레코드 인덱스 배열 리턴
         * 
         * @param obj = grid object
         * @return 체크된 index의 순번에 대한 배열
         */
        getCheckedRowsIndex : function(obj) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var checkRows = new Array();
            var index = 0;
            
            var data = this.getCheckedRowsData(obj);
            var checkList = this.getRowIndex(obj, data);
            var indexList = this.getAllRows(obj);
            
            for (var i = (indexList.length - 1); i >= 0; i--) {
                for (var k = 0; k < checkList.length; k++) {
                    if (data[k] == indexList[i]) {
                        checkRows[index] = i;
                        index++;
                        
                        break;
                    }
                }
            }
            
            return checkRows;
        },
        
        /**
         * 지정된 row를 파라메터의 data로 변경
         * 
         * @param obj = grid object
         * @param index = row index
         * @param data = 변경할 row data 
         */
        setChangeRowValue : function(obj, index, data) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            obj.datagrid('updateRow', {
                index: index,
                row: data
            });
        },
        
        /**
         * 그리드에서 추출된 데이터의 row index를 리턴
         * 
         * @param obj = grid object
         * @param rowData = select나 checked 된 rowData
         * @return row 번호
         */
        getRowIndex : function(obj, rowData) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var indexList = new Array();
            
            if (rowData.length == null || rowData.length == "undefined") {
                return obj.datagrid('getRowIndex', rowData);
            } else {
                for (var i = 0; i < rowData.length; i++) {
                    indexList[i] = obj.datagrid('getRowIndex', rowData[i]);
                }
                
                return indexList;
            }
        },
        
        /**
         * 지정된 index 열을 특정 필드를 수정한다.
         * 
         * @param obj = grid object
         * @param idx = 변경할 row index번호
         * @param fdata = 변경할 필드와 값(json타입)
         */
        updateRow : function(obj, idx, fdata) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            obj.datagrid('updateRow',{
                index: idx,
                row: fdata
            });
        },
        
        /**
         * 지정된 index 열을 지운다.
         * 
         * @param obj = grid object
         * @param index = 지울 row index번호
         */
        removeRow : function(obj, index) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            obj.datagrid('cancelEdit', index).datagrid('deleteRow', index);
        },
        
        /**
         * 지정된 index 열을 추가한다.
         * 
         * @param obj = grid object
         * @param params = 추가할 데이터(json)
         * @param index(0..) = 추가될 행번호(지정하지 않을 경우 마지막 행번호+1)
         */
        appendRow : function(obj, params, index) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            if(!oUtil.isNull(index)) {
                if(oUtil.isNull(params)) {
                    obj.datagrid('insertRow', {
                        index: index,
                        row: {FIELD_STATE: 'I'}
                    });
                } else {
                    obj.datagrid('insertRow', {
                        index: index,
                        row: params
                    });
                }
                
                this.selectRowIndex(obj, index);
            } else {
                if(oUtil.isNull(params)) {
                    obj.datagrid('appendRow', {
                        FIELD_STATE: 'I'
                    });
                } else {
                    obj.datagrid('appendRow', params);
                }
                
                this.selectRowIndex(obj, this.getRowCount(obj, "2")-1);
            }
        },
        
        /**
         * datagrid의 row수를 리턴한다.
         * @param grid = datagrid object
         * @param search_type = 데이터 조회 타입(1:원본 데이터(핕터와 무관한 모든 데이터), 2:필터된 데이터)
         * @returns row count
         */
        getRowCount : function(obj, search_type) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            return this.getAllRows(obj, search_type).length;
        },
        
        /**
         * datagrid의 모든 row데이터를 리턴
         * 
         * @param grid = datagrid object
         * @param search_type = 데이터 조회 타입(1:원본 데이터(핕터와 무관한 모든 데이터)-default, 2:필터된 데이터)
         * @returns json data
         */
        getAllRows : function(obj, search_type) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var rows = null;
            var prop = grid.getInitConfig(obj);
            
            if(oUtil.isNull(search_type)) search_type = "1"; // 원본 데이터를 가져가도록 수정(2022-04-01)
            
            if(search_type == "1" && prop.filter) {
                rows = obj.datagrid("getFilterData");
            }
            
            if(rows == null) {
                rows = obj.datagrid("getData").rows;
            }
            
            return rows;
        },
        
        /**
         * datagrid의 데이터에서 지정된 field의 값을 배열로 리턴
         * 
         * @param obj = grid object
         * @param fieldId = 필드 식별ID
         * @reutrns Array
         */
        getFieldData : function(obj, fieldId, index) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var fieldData = new Array();
            var rownum = this.getRowCount(obj);
            
            if (rownum > 0) {
                var rowData = this.getAllRows(obj);
                
                if(oUtil.isNull(index) || index < 0) {
                    for (var k = 0; k < rownum; k++) {
                        fieldData[k] = rowData[k][fieldId];
                    }
                } else {
                    return rowData[index][fieldId];
                }
            }
            
            return fieldData;
        },
        
        /**
         * 지정된 열의 필드로부터 span까지를 merge
         * 
         * @param grie = datagrid object
         * @param index = 열번호
         * @param span = merge시킨 column수
         * @param field = merge를 시작할 필드 id
         */
        mergeCols : function(obj, index, span, field) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var merges = [{
                index: index,
                colspan: span
            }];
            for (var i = 0; i < merges.length; i++) {
                obj.datagrid('mergeCells', {
                    index: merges[i].index,
                    field: field,
                    colspan: merges[i].colspan
                });
            }
        },
        
        /**
         * 지정된 행의 필드로부터 span까지를 merge
         * 
         * @param grid = datagrid object
         * @param mergeFullRowList = merge를 위한 index및 필드 정보를 포함하고 있는 List 객체
         */
        mergeRows : function(obj, mergeFullRowList) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var prop = grid.getInitConfig(grid.handle.getGridID(obj));
            var mergeList = prop.mergeList;
            var startRowidx = 0;
            var key = mergeFullRowList[startRowidx].key;
            var mergeidxList;
            
            if (mergeFullRowList.length < 1) {
                return;
            }
            for (var i = 0; i < mergeList.length; i++) {
                if (!oUtil.isNull(mergeFullRowList[startRowidx + 1])) {
                    key = mergeFullRowList[startRowidx + 1].key;
                    if (mergeList[i] == key) {
                        startRowidx++;
                    }
                }
                mergeidxList = mergeFullRowList[startRowidx].mergeidxList;
                for (var j = 0; j < mergeidxList.length; j++) {
                    obj.datagrid('mergeCells', {
                        index: mergeidxList[j].index,       // 시작행
                        field: mergeList[i],                // 병합할 필드ID
                        rowspan: mergeidxList[j].rowspan    // 종료행
                    });
                }
            }
        },
        
        /**
         * merge 대상 칼럼의 start 위치와 merge의 갯수 리턴
         * 
         * @param data = 조회 데이타
         * @param startIdx = 데이타를 시작할 위치 index
         * @param mergeKey = merge 할 key가 되는 칼럼 배열
         * @param mergeidxList = 하나의 merge key 에 대한 start index와 rowspan 갯수를 가지고 있는 json 형태의 리스트
         * @param mergeFullIdxList = 모든 merge key에 대한 mergeidxList를 가지고 있는 json 형태의 리스트
         * @param mergeKeyIdx = merge key index
         * 
         * @returns mergeFullIdxList[{key:value, mergeidxList:[mergeidxList.index, rowspan:mergeidxList.rowspan]}]
         */
        getMergeList : function(data, startIdx, mergeKey, mergeidxList, mergeFullIdxList, mergeKeyIdx) {
            var mergeKeyList = new Array();
            
            // 초기화 값 셋팅
            if (oUtil.isNull(startIdx)) {
                startIdx = 0;
            }
            if (oUtil.isNull(mergeKeyIdx)) {
                mergeKeyIdx = 0;
            }
            if (oUtil.isNull(mergeFullIdxList)) {
                mergeFullIdxList = new Array();
            }
            
            // merge할 key값을 구하기
            if (typeof mergeKey == "string") {  // merge할 Key의 컬럼명(1개만 지정)
                mergeKeyList[0] = mergeKey;
            } else {  // merge할 Key의 컬럼 배열(1개 이상 지정)
                mergeKeyList = mergeKey;
            }
            
            for (var j = mergeKeyIdx; j < mergeKeyList.length; j++) { 
                var mergeKeyString = mergeKeyList[j]; // merge할 key
                
                for (var i = startIdx; i < data.length; i++) { // 데이타 시작 index
                    if (i + 1 == data.length) { // 마지막 row머지 처리
                        var rowsnum = (i - startIdx) + 1;
                        mergeidxList.push({
                            index: startIdx,
                            rowspan: rowsnum
                        });
                        break;
                    } else {
                        // 비교할 key값 구하기
                        var startKey = "";
                        var idxKey = "";
                        for (var s = 0; s <= j; s++) {
                            startKey += eval("data[startIdx]." + mergeKeyList[s]);
                            idxKey += eval("data[i+1]." + mergeKeyList[s]);
                        }
                        if (startKey == idxKey) {
                            continue;
                        } else {
                            var rowsnum = (i - startIdx) + 1;
                            mergeidxList.push({
                                index: startIdx,
                                rowspan: rowsnum
                            });
                            return grid.handle.getMergeList(data, i + 1, mergeKeyList, mergeidxList, mergeFullIdxList, j);
                        }
                    }
                }
                mergeFullIdxList.push({
                    key: mergeKeyString,
                    mergeidxList: mergeidxList
                });
                
                // 데이터 초기화
                startIdx = 0;
                mergeidxList = new Array();
            }
            
            return mergeFullIdxList;
        },
        
        /**
         * grid의 colunm을 숨김
         * @param obj datagrid
         * @param field 숨길 컬럼ID
         * @param 
         */
        setHideColunm : function(obj, field, header) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            obj.datagrid("hideColumn", field);
            
            // 그리드 해더정보의 숨김여부에 대해 싱크를 맞춘다.
            var prop = grid.getInitConfig(obj);
            
            if(!oUtil.isNull(prop.columns)) {
                var csize = prop.columns.length;
                var columns = prop.columns;
                
                for(var s = 0; s < csize; s++) {
                    for(var i = 0; i < columns[s].length; i++) {
                        var cobj = columns[s][i];
                        var fid = cobj.field;
                        
                        if(fid == field) {
                            cobj.hidden = true;
                            break;
                        }
                    }
                }
            }
        },
        
        /**
         * grid의 colunm을 보여줌
         * @param obj datagrid
         * @param field 표시할 컬럼ID
         */
        setShowColunm : function(obj, field, header) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            obj.datagrid("showColumn", field);
            
            // 그리드 해더정보의 숨김여부에 대해 싱크를 맞춘다.
            var prop = grid.getInitConfig(obj);
            
            if(!oUtil.isNull(prop.columns)) {
                var csize = prop.columns.length;
                var columns = prop.columns;
                
                for(var s = 0; s < csize; s++) {
                    for(var i = 0; i < columns[s].length; i++) {
                        var cobj = columns[s][i];
                        var fid = cobj.field;
   
                        if(fid == field) {
                            cobj.hidden = false;
                            break;
                        }
                    }
                }
            }
        },
        
        /**
         * grid의 cell format타입 설정
         * @param format 설정정보
         * @param value cell 값
         * @param row data
         * @index 지정된 row번호
         */
        getFormatData : function(format, value, row, index) {
            if (oUtil.isNull(value)) return;
            var rst = "";
            
            if (!oUtil.isNull(format)) {
                var type = format.format;
                
                if(type == "int") { // 숫자 3자리마다 콤마(,)를 찍어 반환
                    rst = formatInteger(value);
                } else if(type == "float") { // 앞 정수 3자리마다 콤마(,)를 찍고, 실수는 지정된 값(format.precision)에 맞게 자릿수를 만들거나 제거해서 반환
                    var n = format.precision;
                    
                    if(oUtil.isNull(n)) n = 0;
                    
                    rst = formatFloat(value, n);
                } else if(type == "amount") { // 금액관리 : 앞 정수 3자리마다 콤마(,)를 찍고, 실수는 지정된 값(format.precision)에 맞게 자릿수를 만들거나 제거해서 반환
                    var n = format.precision;
                    
                    if(oUtil.isNull(n)) {
                        n = 4;
                    }
                    
                    rst = formatFloat(value, n, "amount");
                } else if(type == "quantity") { // 수량관리 : 앞 정수 3자리마다 콤마(,)를 찍고, 실수는 지정된 값(format.precision)에 맞게 자릿수를 만들거나 제거해서 반환
                    var n = format.precision;
                    
                    rst = formatFloat(value, 3);
                } else if(type == "rate") { // 비율관리 : 앞 정수 3자리마다 콤마(,)를 찍고, 실수는 지정된 값(format.precision)에 맞게 자릿수를 만들거나 제거해서 반환
                    var n = format.precision;
                    
                    rst = formatFloat(value, 2);
                } else if(type == "exchange") { // 환율관리 : 앞 정수 3자리마다 콤마(,)를 찍고, 실수는 지정된 값(format.precision)에 맞게 자릿수를 만들거나 제거해서 반환
                    var n = format.precision;
                    
                    if(oUtil.isNull(n)) {
                        n = 8;
                    }
                    
                    rst = formatFloat(value, 8);
                } else if(type == "date") { // 숫자형식인 경우 6자리와 8자리에 대해 xxxx-xx-xx형식으로 반환,(단, 숫자가 아닌경우 원래값을 반환시킴)
                    var n = format.delim;   // 날짜 사이의 구분자(format.delim)를 적용할 수 있음(default구분자는 "-"임) 
                    var nt = format.nation; // 화면에 표시할 국가(US, MX, KR 지원)
                    
                    if(n == null) n = SEPERATOR_OF_DATE;
                    if(oUtil.isNull(nt)) nt = NATION_DATE_DB;
                    
                    rst = formatDate(value, n, nt, NATION_DATE_VIEW, type);
                } else if(type == "month") {    // 숫자형식인 경우 6자리와 8자리에 대해 xxxx-xx-xx형식으로 반환,(단, 숫자가 아닌경우 원래값을 반환시킴)
                    var n = format.delim;   // 날짜 사이의 구분자(format.delim)를 적용할 수 있음(default구분자는 "-"임) 
                    var nt = format.nation; // 화면에 표시할 국가(US, MX, KR 지원)
                    
                    if(n == null) n = SEPERATOR_OF_DATE;
                    if(oUtil.isNull(nt)) nt = NATION_DATE_DB;
                    
                    rst = formatDate(value, n, nt, NATION_DATE_VIEW, type);
                } else if(type == "percent") { // 소수점처리 방식에서 마지막에 %를 붙여서 반환
                    var n = format.precision;
                    var rate = (oUtil.isNull(format.multiple))?100:format.multiple;
                    
                    if(oUtil.isNull(n)) n = 0;
                    var value1 = value*rate;
                    
                    rst = formatFloat(value1, n) + "%";
                } else if(type == "amount2string") { // 기본은 숫자처리하나, 화폐의 기본단위를 포함시켜 반환(예> 원화, 달라, 유로, 엔화를 지원함)
                    rst = formatNumber(value);
                } else if(type == "concat") { // 기본자열에 추가문자열을 붙여서 반환
                    var n = format.attach;
                    
                    if(oUtil.isNull(n)) n = "";
                    
                    rst = value+n;
                } else if(type == "row") {
                    if(!oUtil.isNull(format.field)) {
                        rst = row[format.field];
                    }
                    //console.log("name = " + format.field + ", value = " + value);
                } else if(type == "style") {
                    // 예약어를 통해 셀 텍스트의 색상을 변경하기 위한 기본기능 추가(2022-04-25)
                    // 해더 정보설정 시 {"format":"style"} 으로 지정
                    if(!oUtil.isNull(value)) {
                        var field = new String(format.field);
                        
                        if(value.toString().indexOf("::RED") != -1) {
                            value = value.replace("::RED", "");
                            rst = "<font color='red'>"+value.replace("::RED", "")+"</font>";
                            if(!oUtil.isNull(field)) row[field] = value;
                        } else if(value.toString().indexOf("::BLUE") != -1) {
                            value = value.replace("::BLUE", "");
                            rst = "<font color='blue'>"+value.replace("::BLUE", "")+"</font>";
                            if(!oUtil.isNull(field)) row[field] = value;
                        } else {
                            rst =  value;
                        }
                    }
                } else if(type == "function") {
                    var pid = new String(format.pid);
                    var fnc = new String(format.name);
                    
                    var pro_id = eval("window."+pid+".ui");
                    
                    if(!oUtil.isNull(pro_id)) {
                        // 그리드 ID에 해당하는 <code>function</code>이 있으면 호출된다.
                        if (typeof (pro_id[fnc]) == "function") {
                            rst = pro_id[fnc](value, row, index);
                        }
                    }
                } else {
                    rst = value;
                }
            }
            
            if(!oUtil.isNull(format.append)) rst = rst+format.append;
            
            return "<div title=\"" + value + "\">" + rst + "</div>"; // 에디팅 기능에서 문제가 있어 tooltip을 제거했음.
        },
        
        /** combobox 생성
         * @param formId = 화면 form Id
         * @param tagId = tag or element Id
         * @param url = combobox data를 셋하기 위한 요청 url
         * @param valueId = value mapping id
         * @param textId = text(화면에 표현) mapping id
         * @param data = json 형태의 mapping id
         * @param resizable = 콤보박스 크기
         * @param value = 초기 선택될 값
         * @param editable = editing 허용여부(true | fasle)
         * @param multiple = 다중선택 여부(true | fasle)
         */
        setGridCombobox : function(formId, tagId, url, valueId, textId, data, height, value, func, editable, multiple) {
            var panelHeight = null;
            var tagCurrObj = form.getFormObject(formId, tagId);
            
            // 자동 크기조정 여부 체크
            if (oUtil.isNull(height) || height <= 0) {
                panelHeight = "auto";
            } else {
                panelHeight = height;
            } if (oUtil.isNull(editable)) {
                editable = false;
            }
            if (oUtil.isNull(multiple)) {
                multiple = false;
            }
            if (!oUtil.isNull(url)) {
                $(tagCurrObj).combobox({
                    url: url,
                    valueField: valueId,
                    textField: textId,
                    panelHeight: panelHeight,
                    editable: editable,
                    multiple: multiple,
                    required: true,
                    onLoadSuccess: function(data) {
                        return;
                        if (!oUtil.isNull(data) && data.length > 0) {
                            var val = eval("data[0]." + valueId);
                            if (!oUtil.isNull(value)) {
                                // 조회된 데이터에 지정된 데이터가 있는지 확인한다.
                                for (var j = 0; j < data.length; j++) {
                                    var cmpval = eval("data[" + j + "]." + valueId);
                                    if (cmpval == value) {
                                        val = value;
                                        break;
                                    }
                                }
                            }
                            $(this).combobox("select", val);
                            var maxLen = getMaxDataLength(data, textId);
                            if ($(this).width() < maxLen) {
                                var pan = $(this).combobox("panel");
                                pan.panel('resize', {
                                    width: maxLen
                                });
                            }
                        } else {
                            $(this).combobox("select", "");
                        }
                    },
                    onChange: function(data) {
                        var pro_id = eval("window." + form.handle.getProgramID(formId));
                        if (typeof(pro_id[func]) == "function") {
                            pro_id[func](data);
                        }
                    }
                });
            } else {
                $(tagCurrObj).combobox({
                    valueField: valueId,
                    textField: textId,
                    data: data,
                    editable: editable,
                    multiple: multiple,
                    required: true,
                    panelHeight: panelHeight,
                    onLoadSuccess: function(data) {
                        if (!oUtil.isNull(data) && data.length > 0) {
                            return;
                            var val = eval("data[0]." + valueId);
                            if (!oUtil.isNull(value)) {
                                // 조회된 데이터에 지정된 데이터가 있는지 확인한다.
                                for (var j = 0; j < data.length; j++) {
                                    var cmpval = eval("data[" + j + "]." + valueId);
                                    if (cmpval == value) {
                                        val = value;
                                        break;
                                    }
                                }
                            }
                            $(this).combobox("select", val);
                            var maxLen = getMaxDataLength(data, textId);
                            if ($(this).width() < maxLen) {
                                var pan = $(this).combobox("panel");
                                pan.panel('resize', {
                                    width: maxLen
                                });
                            }
                        } else {
                            $(this).combobox("select", "");
                        }
                    },
                    onChange: function(data) {
                        return;
                        var pro_id = eval("window." + form.handle.getProgramID(formId));
                        if (typeof(pro_id[func]) == "function") {
                            pro_id[func](data);
                        }
                    }
                });
            }
        },
        
        /**
         * 그리드의 모든 checkbox 를 선택 또는 해제를 수행
         * 
         * @param obj = grid object
         * @param boolean = 체크 여부 flag(true: 모두 체크, false: 모두 해제)
         */
        checkAll : function(obj, boolean) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            if (boolean) {
                obj.datagrid("checkAll");
            } else {
                obj.datagrid("uncheckAll");
            }
        },
        
        /**
         * 그리드의 checkbox 를 선택 또는 해제한다.
         * 
         * @param obj = grid object object
         * @param flag = 체크 여부 flag(true: 체크, false: 해제)
         * @param index = 체크 또는 해제 row index
         */
        checkRow : function(obj, flag, index) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            if (flag) {
                obj.datagrid("checkRow", index);
            } else {
                obj.datagrid("uncheckRow", index);
            }
        },
        
        /**
         * datagrid의 모든 row데이터를 삭제한다.
         * @param obj = grid object
         */
        clearAll : function(obj) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var rows = [];
            var prop = grid.getInitConfig(obj);
            
            obj.datagrid("loadData", rows);
            if(prop.showFooter) obj.datagrid("reloadFooter", []);
        },
        
        /**
         * rowIndex에 해당하는 열 선택
         * @param obj = grid object
         * @param rowIndex = select할 row index
         */
        selectRowIndex : function(obj, rowIndex) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            obj.datagrid("selectRow", rowIndex);
        },
        
        /**
         * 에디팅할 cell를 임의로 지정
         * @param obj = grid object
         * @param index = 에디팅할 열번호
         * @param field = 에디팅할 항목
         * @param rowIndex = select할 row index
         */
        selectEditCell : function(obj, index, field) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var param = new Object();
            
            param.index = index;
            param.field = field;
            
            obj.datagrid('selectCell', param);
            obj.datagrid('enableCellEditing');
            
            grid.handle.selectRowIndex(obj, index);
            
            var cell = obj.datagrid('cell');
            
            obj.datagrid('editCell', cell);
        },
        
        /**
         * 에디팅모드에서 변경된 Row Data를 리턴
         * 
         * @param obj = grid object
         * @param mode = true:변경된 필드의 상태값(FIELD_STATE)을 포함해서 리턴, false:변경된 값을 리턴함
         *        - FIELD_STATE = I:insert, U:update, D:delete
         * @param delyn = y:delete  포함, n:미포함
         */
        getChangesData : function(obj, mode, delyn) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var rows = [];
            
            if(!oUtil.isNull(mode) && mode == false) {
                rows = obj.datagrid('getChanges');
            } else {
                if(oUtil.isNull(delyn)) delyn = "Y";
                
                // 전체 데이터에서 삭제할 데이터를 제외하고 리턴
                var arows = obj.datagrid('getChanges');
                var drows = obj.datagrid('getChanges', 'deleted');
                
                for(var i = 0; i < arows.length; i++) {
                    var chkrows = true;
                    
                    for(var k = 0; k < drows.length; k++) {
                        if(arows[i] == drows[k]){
                            chkrows = false;
                            break;
                        }
                    }
                    
                    if(chkrows) {
                        if(oUtil.isNull(arows[i].FIELD_STATE)){                         
                            arows[i].FIELD_STATE = "U";
                        }
                        
                        rows.push(arows[i]);
                    }
                }
                
                // 전체 데이터에서 삭제할 데이터를 찾아 리턴
                if(delyn == "Y") {
                    for(var k = 0; k < drows.length; k++) {
                        drows[k].FIELD_STATE = "D";
                        
                        rows.push(drows[k]);
                    }
                }
            }
            
            return rows;
        },
        
        /**
         * 에디팅모드에서 변경된 Row Data의 갯수를 리턴
         * @param obj = grid object
         */
        getChangesCount : function(obj) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            return obj.datagrid('getChanges').length;
        },
        
        /**
         * 에디팅모드에서 변경된 row의 상태값을 초기화 시킴
         * @param obj = grid object
         */
        initChangeData : function(obj) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var arows = obj.datagrid('getChanges');
            
            for(var i = 0; i < arows.length; i++) {
                arows[i].FIELD_STATE = "";
            }
            
            obj.datagrid('acceptChanges'); // [중요]데이터그리드내 에디팅으로 변경된 값의 상태를 초기화함
        },
        
        /**
         * 특정셀의 스타일을 다이렉트로 변경함
         * 
         * @param obj     grid Object
         * @param fieldId 변경할 필드ID
         * @param idx     열번호(0~9999)
         * @param style   스타일값
         * @param word    말풍선 도움말
         */
        setCellStyler : function(obj, fieldId, idx, style, word) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var dg = $(obj);
            var opts = $(dg).datagrid('options');
            var col = opts.finder.getTr(dg[0], idx, 'body').find('td[field="'+fieldId+'"]'); // 셀위치를 찾는다.
            var title = col.find('div[title]');
            
            // css 적용
            var gubuns = style.split(";");
            
            for(var i = 0; i < gubuns.length; i++) {
                var spstr = gubuns[i].split(":");
                
                if(spstr.length > 1) {
                    col.css(spstr[0], spstr[1]);
                }
            }
            
            if(!oUtil.isNull(word)) {
                title.attr("title", word); // 표시된 위치에 적용된 값을 표시하기 위해 title 변경
            }
        },
        
        /**
         * 특정열의 스타일을 다이렉트로 변경함
         * 
         * @param dg     grid Object
         * @param idx     열번호(0~9999)
         * @param style   스타일값
         */
        setRowStyler : function(dg, idx, style) {
            if(!dg.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields'));
            
            for(var j = 0; j < fields.length; j++) {
                var col = dg.datagrid('getColumnOption', fields[j]);
                
                col.styler = function(){
                    return style;
                };
            }
            
            dg.datagrid('refreshRow', idx);
        },
        /**
         * 데이터 그리드에 데이터를 출력함
         * 
         * @param obj     grid Object
         * @param data    json타입의 data
         */
        setLoadData : function(obj, data) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            obj.datagrid('loadData', data);
        },
        /**
         * 데이터 그리드 필터를 해지함(2020.06.23)
         * - field명을 지정하지 않을 경우, 모든 필터를 해제함
         * 
         * @param obj     grid Object
         * @param field   필터를 해지할 filed명
         */
        removeFilterRule : function(obj, field) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            if(oUtil.isNull(field)) {
                // 그리드내 모든 멀티 필터창을 닫는다.
                $(".icon-multi_filter").each(function(){
                    $(this).tooltip('hide');
                });
                
                obj.datagrid("removeFilterRule");               
            } else {
                obj.datagrid("removeFilterRule", field);
            }
        },
        /**
         * 데이터 그리드에 데이터를 출력함
         * 
         * @param obj     grid Object
         * @param column  이동할 필드명
         * @param idx     이동할 row
         */
        gotoCell : function(obj, column, idx) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            if(idx == null || idx == -1) {
                idx = this.getCurrentRowIndex(obj);
            }
            
            obj.datagrid("gotoCell", {index:idx, field:column});
        },
        
        /**
         * 현재 지정된 셀위치 리턴
         * 
         * @param obj     grid Object
         */
        getCellPoint : function(obj) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            return obj.datagrid("cell");
        },
        
        /**
         * 컬럼 사이즈를 데이터 크기에 맞게 자동조정
         * 
         * @param obj     grid Object
         */
        autoResizeColumn : function(obj) { // 2020-12-30
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            
            obj.datagrid("loading"); // 그리드 로딩바 표시
            
            var fields = obj.datagrid('getColumnFields'); 
            
            setTimeout( function() {
                for(var i=0; i<fields.length; i++){ 
                    var field = fields[i];
                    
                    obj.datagrid('autoSizeColumn', field);
                    obj.datagrid('getColumnOption', field).auto = false;
                }
                
                obj.datagrid("loaded"); // 그리드 로딩바 숨기기
            }, 500);
        },
        
        /**
         * 이전에 체크된 행의 체크여부 결정
         * 
         * @param obj datagrid
         * @param flag (true:유지, false:유지안함)
         */
        setKeepCheck : function(obj, flag) {
            if(!obj.data('datagrid')) return; // 그리드가 없다면 리턴함
            var prop = grid.getInitConfig(obj);
            
            if(flag == true) {
                prop.checkFlag = true;
            } else {
                prop.checkFlag = false;
            }
        },
        
        /**
         * 그리드 해더의 title 변경
         *
         * @param obj datagrid
         * @param field 변경할 field명
         * @param title 변경할 title값
         */
        onChangeHeaderTitle : function(obj, field, title) {
            // UI상의 Title을 변경한다.
            var gid = grid.handle.getGridID(obj);
            
            $("#"+gid+"_"+field).attr("title", title);
            $("#"+gid+"_"+field).find("span:first").html(title);
            
            // grid내 속성값을 변경한다.(엑셀 다운로드 시 적용을 위함)
            var prop = grid.getInitConfig(gid);
            var column1 = prop.columns;
            var column2 = prop.frozenColumns;
            
            if(!oUtil.isNull(column1)) {
                for (var i = 0; i < column1.length; i++) {
                    var colData = column1[i];
                    
                    for (var j in colData) {
                        if(colData[j].field == field) {
                            colData[j].title = title;
                            break;                            
                        }
                    }
                }
            }
            
            if(!oUtil.isNull(column2)) {
                for (var i = 0; i < column2.length; i++) {
                    var colData = column2[i];
                    
                    for (var j in colData) {
                        if(colData[j].field == field) {
                            colData[j].title = title;
                            break;                            
                        }
                    }
                }
            }
        }
    },
    
    util : {
        
        /**
         * 그리드 컬럼의 해더정보를 Json타입으로 변환 후 리턴
         * 
         * @param columns = head property
         * @param frozenColumns = frozen columns property
         */
        convertJson2Header : function(columns, frozenColumns) {
            var returnVal = [ "[" ];
            var toList = [];
            var frozenList = [];
            
            if (!oUtil.isNull(frozenColumns)) {
                for (var i = 0; i < frozenColumns.length; i++) {
                    var colData = frozenColumns[i];
                    for ( var j in colData) {
                        var toMap = [ "" ];
                        var field = colData[j].field;
                        var title = colData[j].title;
                        var width = colData[j].width;
                        var align = colData[j].align;
                        var halign = colData[j].halign;
                        var sortable = colData[j].sortable;
                        var hidden = colData[j].hidden;
                        var editor = colData[j].editor;
                        var checkbox = colData[j].checkbox;
                        var formatter = colData[j].session;
                        var rowspan = colData[j].rowspan;
                        var colspan = colData[j].colspan;
                        if (!oUtil.isNull(title))
                            toMap.push("{");
                        if (!oUtil.isNull(field))
                            toMap.push('"field":"' + field + '",');
                        if (!oUtil.isNull(title))
                            toMap.push('"title":"' + title.replace("<br>", "") + '",');
                        if (!oUtil.isNull(width))
                            toMap.push('"width":"' + (parseInt(width * 32)) + '",');
                        if (!oUtil.isNull(align))
                            toMap.push('"align":"' + align + '",');
                        if (!oUtil.isNull(halign))
                            toMap.push('"halign":"' + halign + '",');
                        if (!oUtil.isNull(sortable))
                            toMap.push('"sortable":"' + sortable + '",');
                        if (!oUtil.isNull(hidden)) {
                            if(colData[j].field == "CHECK") {
                                toMap.push('"hidden":"true",');
                            } else {
                                toMap.push('"hidden":"' + hidden + '",');
                            }
                        }
                        if (!oUtil.isNull(editor))
                            toMap.push('"editor":"' + editor + '",');
                        if (!oUtil.isNull(checkbox))
                            toMap.push('"checkbox":"' + checkbox + '",');
                        if (!oUtil.isNull(formatter))
                            toMap.push('"formatter":"' + formatter.format + '",');
                        if (!oUtil.isNull(rowspan))
                            toMap.push('"merge_row":"' + rowspan + '",');
                        if (!oUtil.isNull(colspan))
                            toMap.push('"merge_col":"' + colspan + '",');
                        var lastMapVal = toMap[toMap.length - 1];
                        toMap[toMap.length - 1] = lastMapVal.substring(0, lastMapVal.length - 1);
                        if (!oUtil.isNull(title)) 
                            toMap.push("},");
                        frozenList.push(toMap.join(""));
                    }
                }
            }
            for (var i = 0; i < columns.length; i++) {
                var colData = columns[i];
                
                toList.push("[");
                if (i == 0) {
                    if (!oUtil.isNull(frozenList) && frozenList.length > 0) {
                        toList.push(frozenList.join(""));
                    }
                }
                
                for ( var j in colData) {
                    var toMap = [ "" ];
                    var field = colData[j].field;
                    var title = colData[j].title;
                    var width = colData[j].width;
                    var align = colData[j].align;
                    var halign = colData[j].halign;
                    var sortable = colData[j].sortable;
                    var hidden = colData[j].hidden;
                    var editor = colData[j].editor;
                    var checkbox = colData[j].checkbox;
                    var formatter = colData[j].session;
                    var rowspan = colData[j].rowspan;
                    var colspan = colData[j].colspan;
                    if (!oUtil.isNull(title))
                        toMap.push("{");
                    if (!oUtil.isNull(field))
                        toMap.push('"field":"' + field + '",');
                    if (!oUtil.isNull(title))
                        toMap.push('"title":"' + title.replace("<br>", "") + '",');
                    if (!oUtil.isNull(width))
                        toMap.push('"width":"' + (parseInt(width * 32)) + '",');
                    if (!oUtil.isNull(align))
                        toMap.push('"align":"' + align + '",');
                    if (!oUtil.isNull(halign))
                        toMap.push('"halign":"' + halign + '",');
                    if (!oUtil.isNull(sortable))
                        toMap.push('"sortable":"' + sortable + '",');
                    if (!oUtil.isNull(hidden)) {
                        if(colData[j].field == "CHECK") {
                            toMap.push('"hidden":"true",');
                        } else {
                            toMap.push('"hidden":"' + hidden + '",');
                        }
                    }
                    if (!oUtil.isNull(editor))
                        toMap.push('"editor":"' + editor + '",');
                    if (!oUtil.isNull(checkbox))
                        toMap.push('"checkbox":"' + checkbox + '",');
                    if (!oUtil.isNull(formatter))
                        toMap.push('"formatter":"' + formatter.format + '",');
                    if (!oUtil.isNull(rowspan))
                        toMap.push('"merge_row":"' + rowspan + '",');
                    if (!oUtil.isNull(colspan))
                        toMap.push('"merge_col":"' + colspan + '",');
                    var lastMapVal = toMap[toMap.length - 1];
                    toMap[toMap.length - 1] = lastMapVal.substring(0,
                            lastMapVal.length - 1);
                    if (!oUtil.isNull(title)) {
                        if (j == (colData.length - 1)) {
                            toMap.push("}");
                        } else {
                            toMap.push("},");
                        }
                    }
                    
                    toList.push(toMap.join(""));
                }
                toList.push("],");
            }
            var toListStr = toList.join("");
            returnVal += toListStr.substring(0, toListStr.length - 1);
            returnVal += "]";
            
            return returnVal;
        },
    
        /**
         * grid data를 json타입으로 변경
         * 
         * @param gridData
         * @returns {String}
         */
        convertJson2Rows : function(gridData, chang) {
            var key;
            var value;
            var quotes = '\\"';
            var quote = "'";
            var toList = "[";
            var gridDataArray = new Array();
            
            if (oUtil.isNull(gridData)) {
                gridDataArray[0] = gridData;
            } else {
                gridDataArray = gridData;
            }
            
            for (var i = 0; i < gridDataArray.length; i++) {
                var rowData = gridDataArray[i];
                var toMap = "{";
                var rowCnt = 0;
                
                for (var j in rowData) {
                    rowCnt++;
                    key = j;
                    try {
                        value = eval("rowData." + key);
                    }catch(e) {
                        alert(value);
                        return;
                    }
                    var values = new String(value);
                    
                    // 더블쿼테이션 적용
                    values = values.replace(/\"/g, quotes);
                    values = values.replace(/\'/g, quote);
                    
                    // values = values.replace(/\"/g, "＂");
                    // values = values.replace(/\'/g, "`");
                    
                    if (oUtil.isNull(values) || values == 'null') {
                        toMap += '"' + key + '":"",';
                    } else {
                        toMap += '"' + key + '":"' + values + '",';
                    }
                }
                
                toMap = toMap.substring(0, toMap.length - 1);
                toMap += "},";
                toList += toMap;
            }
            if(toList.length > 1) {
                toList = toList.substring(0, toList.length - 1);
            }
            toList += "]";
            
            return toList;
        },
        
        /**
         * 그리드 생성전 해더 정보의 title을 변경
         * 
         * @param column = 헤더정보
         * @param fieldID = 필드ID
         * @param str = 등록명
         */
        changeHeaderTitle : function(column, fieldID, val) {
            if(!oUtil.isNull(column)) {
                for(var i = 0; i < column.length; i++) {
                    if(column[i][0] == fieldID) {
                        column[i][1] = val;
                        break;
                    }
                }
            }
        },
        
        /**
         * 그리드 생성전 해더 정보의 숨김여부를 변경
         * 
         * @param column = 헤더정보
         * @param fieldID = 필드ID
         * @param bool = true:숨김, false:보임
         * @param del = 해당 배열속성을 삭제할지 여부
         */
        changeHeaderHidden : function(column, fieldID, val, del) {
            if(!oUtil.isNull(column)) {
                for(var i = 0; i < column.length; i++) {
                    if(column[i][0] == fieldID) {
                        column[i][6] = val;
                        if(val && del) column.splice(i,1);
                        break;
                    }
                }
            }
        },
        
        /**
         * row 병합 갯수 설정 및 변경
         * 
         * @param column = 헤더정보
         * @param fieldID = 필드ID
         * @param val = 병합할 열수
         */
        changeHeaderRowSpan : function(column, fieldID, val) {
            if(!oUtil.isNull(column)) {
                for(var i = 0; i < column.length; i++) {
                    if(column[i][0] == fieldID) {
                        column[i][10] = val;
                        break;
                    }
                }
            }
        },
        
        /**
         * col 병합 갯수 설정 및 변경
         * 
         * @param column = 헤더정보
         * @param fieldID = 필드ID
         * @param val = 병합 행수
         */
        changeHeaderColSpan : function(column, fieldID, val) {
            if(!oUtil.isNull(column)) {
                for(var i = 0; i < column.length; i++) {
                    if(column[i][0] == fieldID) {
                        column[i][11] = val;
                        break;
                    }
                }
            }
        },
        
        /**
         * 멀티검색용 그리드 생성
         * 
         * @param 그리드ID
         * @param 초기 생성시 출력할 데이터셋(List)
         */
        drawSearchGrid : function(gridId, aryData) {
            var dg_1 = grid.getObject(gridId);
            
            grid.init.setShowHeader(false);
            grid.init.setRownumbers(false);
            grid.init.setPage(false);
            grid.init.setData(aryData);
            grid.init.setFitColumns(false);
            grid.init.setEmptyMessage(false);

            Theme.defaultGrid(dg_1, dataset.searchHeader);
        },
        
        /**
         * 클립보드를 통해 복사/붙여넣기한 경우, A~AZ까지의 데이터 리턴
         * 
         * @param datas 클립보드 데이터셋 
         */
        getExcelHeaderData : function(datas) {
            var hdata = new Array();
            var maplist = new Array();
            
            for(var i in datas) {
                var hcell = new Object();
                var existsData = false;
                
                for(var k in datas[i]) {
                    var indata = datas[i][k];
                    
                    if(oUtil.isNull(indata)) {
                        indata = "";
                    } else if(typeof indata === "object") {
                        indata = JSON.stringify(indata);
                        existsData = true;
                    } else {
                        indata = indata.toString();
                        existsData = true;
                    }
                    
                    if(k == 0)  hcell.A = indata; 
                    else if(k == 1)  hcell.B = indata;
                    else if(k == 2)  hcell.C = indata;
                    else if(k == 3)  hcell.D = indata;
                    else if(k == 4)  hcell.E = indata;
                    else if(k == 5)  hcell.F = indata;
                    else if(k == 6)  hcell.G = indata;
                    else if(k == 7)  hcell.H = indata;
                    else if(k == 8)  hcell.I = indata;
                    else if(k == 9)  hcell.J = indata;
                    else if(k == 10) hcell.K = indata;
                    else if(k == 11) hcell.L = indata;
                    else if(k == 12) hcell.M = indata;
                    else if(k == 13) hcell.N = indata;
                    else if(k == 14) hcell.O = indata;
                    else if(k == 15) hcell.P = indata;
                    else if(k == 16) hcell.Q = indata;
                    else if(k == 17) hcell.R = indata;
                    else if(k == 18) hcell.S = indata;
                    else if(k == 19) hcell.T = indata;
                    else if(k == 20) hcell.U = indata;
                    else if(k == 21) hcell.V = indata;
                    else if(k == 22) hcell.W = indata;
                    else if(k == 23) hcell.X = indata;
                    else if(k == 24) hcell.Y = indata;
                    else if(k == 25) hcell.Z = indata;
                    else if(k == 26) hcell.AA = indata;
                    else if(k == 27) hcell.AB = indata;
                    else if(k == 28) hcell.AC = indata;
                    else if(k == 29) hcell.AD = indata;
                    else if(k == 30) hcell.AE = indata;
                    else if(k == 31) hcell.AF = indata;
                    else if(k == 32) hcell.AG = indata;
                    else if(k == 33) hcell.AH = indata;
                    else if(k == 34) hcell.AI = indata;
                    else if(k == 35) hcell.AJ = indata;
                    else if(k == 36) hcell.AK = indata;
                    else if(k == 37) hcell.AL = indata;
                    else if(k == 38) hcell.AM = indata;
                    else if(k == 39) hcell.AN = indata;
                    else if(k == 40) hcell.AO = indata;
                    else if(k == 41) hcell.AP = indata;
                    else if(k == 42) hcell.AQ = indata;
                    else if(k == 43) hcell.AR = indata;
                    else if(k == 44) hcell.AS = indata;
                    else if(k == 45) hcell.AT = indata;
                    else if(k == 46) hcell.AU = indata;
                    else if(k == 47) hcell.AV = indata;
                    else if(k == 48) hcell.AW = indata;
                    else if(k == 49) hcell.AX = indata;
                    else if(k == 50) hcell.AY = indata;
                    else if(k == 51) hcell.AZ = indata;
                }
                
                if(existsData == true) hdata.push(hcell);
            }
            
            return hdata;
        }
        
    }

};