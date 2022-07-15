var chartClass = function() {
    this.chartPropertity = null;
    
    /**
     * 챠트 그리드의 타이틀 설정
     * @param string
     */
    this.setTitle = function(str) {
        if (!oUtil.isEmpty(str)) {
            this.chartPropertity.title = str;
        } else {
            $.messager.alert("ERROR", "setTitle() parameter input error.");
        }
    };
    
    /**
     * 애니메이션 효과 적용여부
     * @param boolean
     */
    this.setAnimate = function(boolean) {
        if (oUtil.isBoolean(boolean)) {
            this.chartPropertity.animate = boolean;
            this.chartPropertity.animateReplot = boolean;
        } else {
            $.messager.alert("ERROR", "setAnimate() parameter input error.(true or false)");
        }
    };
    
    /**
     * 챠트 그래프 설명 여부
     * (default, true)
     * @param boolean
     * @param location e:east, w:west, s:south, n:north, default:east-top
     */
    this.setShowLegend = function(boolean, location) {
        if (oUtil.isBoolean(boolean)) {
            this.chartPropertity.legend.show = boolean;
            if(!oUtil.isNull(location)) {
                this.chartPropertity.legend.location = location;
            }
        } else {
            $.messager.alert("ERROR", "setShowLegend() parameter input error.(true or false)");
        }
    };
    
    /**
     * 챠트 그래프 설명바 위치
     * (default, true)
     * @param boolean
     * @param location in, out
     */
    this.setLegendPlacement = function(location) {
        if(!oUtil.isNull(location)) {
        	if(location == "out") {
        		this.chartPropertity.legend.placement = "outside";
        	} else {
        		this.chartPropertity.legend.placement = "inside";
        	}
        } else {
            $.messager.alert("ERROR", "setLegendPlacement() parameter input error.(string only)");
        }
    };
    
    /**
     * series의 색상 지정
     * 
     * @param color 배열로 지정색 색상
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setSeriesColors = function(series, color) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setSeriesColors() not definded Series.");
        } else {
            if(!oUtil.isNull(color)) {
            	this.chartPropertity.seriesColors = color;
            } else {
                 $.messager.alert("ERROR", "setSeriesColors() parameter input error.");
            }
        }
    };
    
    /**
     * 배열상 같은 위치에 있는 값을 하나의 막대 그래프로 표시
     * @param boolean
     */
    this.setStackSeries = function(boolean) {
        if (oUtil.isBoolean(boolean)) {
            this.chartPropertity.stackSeries = boolean;
        } else {
            $.messager.alert("ERROR", "setStackSeries() parameter input error.(true or false)");
        }
    };
    
    /**
     * 차트의 커서 객체 리턴
     * @param chart 객체
     * @return 커서
     */
    this.getCursor = function(property) {
        return property.cursor = {show:true, looseZoom:true};
    };
    
    /**
     * 커서의 줌 효과 적용여부
     * @param cursor 커서
     * @param boolean
     */
    this.setZoom = function(cursor, boolean) {
        if(oUtil.isNull(cursor)) { 
            $.messager.alert("ERROR", "setZoom() not definded Cursor.");
        } else {
            if (oUtil.isBoolean(boolean)) {
                cursor.zoom = boolean;
            } else {
                $.messager.alert("ERROR", "setZoom() parameter input error.(true or false)");
            }
        }
    };
    
    /**
     * 커서의 툴팁 효과 적용여부
     * @param cursor 커서
     * @param boolean
     */
    this.setTooltip = function(cursor, boolean) {
        if(oUtil.isNull(cursor)) {
            $.messager.alert("ERROR", "setTooltip() not definded Cursor.");
        } else {
            if (oUtil.isBoolean(boolean)) {
                cursor.showTooltip = boolean;
            } else {
                $.messager.alert("ERROR", "setTooltip() parameter input error.(true or false)");
            }
        }
    };
    
    /**
     * 차트의 타입(시리즈) 객체 리턴
     * @param Object 챠트 객체(필수)
     * @param int 화면에 표시할 그래프 수
     * @return 시리즈
     */
    this.getSeries = function(property, size) {
        var type = new Array();
        
        if(!oUtil.isNull(size) && size > 0) {
            for(var i = 0; i < size; i++) {
                type[i] = {};
            }
        } else {
            type = [{}];
        }
        
        property.seriesType = "array";
        property.seriesCount = size;
        
        return property.series = type;
    };
    
    /**
     * 차트의 타입(시리즈) 객체 리턴
     * - 원형 그래프에서는 반드시 이 객체를 리턴받아야 함
     * @param Object 챠트 객체(필수)
     * @return 시리즈
     */
    this.getSeriesDefaults = function(property) {
        property.seriesType = "default";
        property.seriesCount = 1;
        
        return property.seriesDefaults = {};
    };
    
    /**
     * 챠트 타입 설정
     * @param series 커서
     * @param type bar(막대그래프),
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setRendererType = function(series, type, idx) {
        if(oUtil.isNull(series)) {
            $.messager.alert("ERROR", "setRendererType() not definded Series.");
        } else {
            if(!oUtil.isNull(type)) {
                if(oUtil.isNull(idx)) idx = 0;
                
                if(this.chartPropertity.seriesType == "array") {  // 막대 그래프
                	if(idx < this.chartPropertity.seriesCount) {
	                    if(type == "bar") {  // 막대 그래프
	                        series[idx].renderer = $.jqplot.BarRenderer;
	                    } else if(type == "pie") {  // 원형 그래프
	                        series[idx].renderer = $.jqplot.PieRenderer;
	                    }
	                    
	                    series[idx].rendererOptions = {barMargin:0, barWidth:15, barPadding:0};
	                    series[idx].pointLabels = {show:false};
	                    series[idx].shadow = false;
                	}
                } else if(this.chartPropertity.seriesType == "default") {
                    if(type == "bar") {  // 막대 그래프
                        series.renderer = $.jqplot.BarRenderer;
                    } else if(type == "pie") {  // 원형 그래프
                        series.renderer = $.jqplot.PieRenderer;
                    }
                    
                    series.pointLabels = {show:false};
                    series.rendererOptions = {};
                    series.rendererOptions.fill = true;
                    series.shadow = false;
                }
                
                if(type == "pie") {
                    this.chartPropertity.axesDefaults = {};
                    this.chartPropertity.axes = {};
                } else {
                    this.chartPropertity.highlighter= {show: true,  
                                                       showLabel: true,  
                                                       tooltipAxes: 'y', 
                                                       sizeAdjust: 7.5,
                                                       tooltipLocation : 'ne'
                                                       };
                }
                
                this.chartPropertity.chatType = type;
            } else {
                 $.messager.alert("ERROR", "setRendererType() parameter input error.");
            }
        }
    };
    
    /**
     * 그래프내에서 포인트에 걸린 위치에 라벨을 표시할 지 여부 설정
     * (default = true)
     * @param series 커서
     * @param boolean 
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setPointLabels = function(series, boolean, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setPointLabels() not definded Series.");
        } else {
            if (oUtil.isBoolean(boolean)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.pointLabels.show = boolean;
                    series.showHighlight = true;
                    series.rendererOptions.highlightMouseOver = (boolean)?false:true;
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) {
                        series[idx].pointLabels.show = boolean;
                        series[idx].showHighlight = true;
                        series[idx].rendererOptions.highlightMouseOver = (boolean)?false:true;
                    }
                }
            } else {
                $.messager.alert("ERROR", "setPointLabels() parameter input error.(true or false)");
            }
        }
    };
    
    /**
     * 그림자 명암 표시여부 설정
     * (default = false)
     * @param series 커서
     * @param boolean 
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setShadow = function(series, boolean, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setShadow() not definded Series.");
        } else {
            if (oUtil.isBoolean(boolean)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.shadow = boolean;
                    //if(boolean) series.shadowDepth(0.5);
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) series[idx].shadow = boolean;
                    //if(boolean) series[idx].shadowDepth(0.5);
                }
                
                
            } else {
                $.messager.alert("ERROR", "setShadow() parameter input error.(true or false)");
            }
        }
    };
    
    /**
     * 부드러운 곡선으로 변경할지 여부
     * (default = false)
     * @param series 커서
     * @param boolean 
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setSmooth = function(series, boolean, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setSmooth() not definded Series.");
        } else {
            if (oUtil.isBoolean(boolean)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.rendererOptions.smooth = boolean;
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.smooth = boolean;
                }
            } else {
                $.messager.alert("ERROR", "setSmooth() parameter input error.(true or false)");
            }
        }
    };
    
    /**
     * 시리즈 text 색성
     * (default = false)
     * @param series 커서
     * @param value 
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setTextColor = function(series, value, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setSmooth() not definded Series.");
        } else {
            if (!oUtil.isNull(value)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.rendererOptions.baselineColor = value;
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.baselineColor = value;
                }
            } else {
                $.messager.alert("ERROR", "setSmooth() parameter input error.(true or false)");
            }
        }
    };
    
    /**
     * 막대 그래프 설정시 막대간 공백 설정
     * (default = 0)
     * @param series 커서
     * @param size 간격(픽셀)
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setBarMargin = function(series, size, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setBarMargin() not definded Series.");
        } else {
            if(!oUtil.isNull(size)) {
                if(oUtil.isNull(idx)) idx = 0;
                if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.barMargin = size;
            } else {
                 $.messager.alert("ERROR", "setBarMargin() parameter input error.");
            }
        }
    };
    
    /**
     * 막대 그래프 설정시 막대 넓이 설정
     * (default = 15)
     * @param series 커서
     * @param size 넓이(픽셀)
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setBarWidth = function(series, size, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setBarWidth() not definded Series.");
        } else {
            if(!oUtil.isNull(size)) {
                if(oUtil.isNull(idx)) idx = 0;
                if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.barWidth = size;
            } else {
                 $.messager.alert("ERROR", "setBarWidth() parameter input error.");
            }
        }
    };
    
    /**
     * 막대 그래프 설정시 막대 여백 설정
     * (default = 0)
     * @param series 커서
     * @param size 넓이(픽셀)
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setBarPadding = function(series, size, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setBarPadding() not definded Series.");
        } else {
            if(!oUtil.isNull(size)) {
                if(oUtil.isNull(idx)) idx = 0;
                if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.barPadding = size;
            } else {
                 $.messager.alert("ERROR", "setBarPadding() parameter input error.");
            }
        }
    };
    
    /**
     * 원형 그래프 라벨 표시여부
     * @param series 커서
     * @param boolean
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setShowDataLabels = function(series, boolean, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setShowDataLabels() not definded Series.");
        } else {
            if (oUtil.isBoolean(boolean)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.rendererOptions.showDataLabels = boolean;
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.showDataLabels = boolean;
                }
            } else {
                $.messager.alert("ERROR", "setShowDataLabels() parameter input error.(true or false)");
            }
        }
    };
    
    /**
     * 원형 그래프의 조각을 분리 시 여백 크기 설정
     * (default = 0)
     * @param series 커서
     * @param int 여백 크기(픽셀)
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setSliceMargin = function(series, size, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setSliceMargin() not definded Series.");
        } else {
            if (!oUtil.isNull(size)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.rendererOptions.sliceMargin = size;
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.sliceMargin = size;
                }
            } else {
                $.messager.alert("ERROR", "setSliceMargin() parameter input error.(integer type only)");
            }
        }
    };
    
    /**
     * 원형 그래프의 조각 테두리을 선으로 표시할 때 선의 두께
     * (default = 0)
     * @param series 커서
     * @param int 선 두께(픽셀)
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setSineWidth = function(series, width, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setSineWidth() not definded Series.");
        } else {
            if (!oUtil.isNull(width)) {
                if(this.chartPropertity.seriesType == "default") {
                	if(width > 0) {
	                    series.rendererOptions.lineWidth = width;
	                    series.rendererOptions.fill = false;
                	} else {
                		series.rendererOptions.fill = true;
                	}
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) {
                        series[idx].rendererOptions.lineWidth = width;
                        series[idx].rendererOptions.fill = false;
                    }
                }
            } else {
                $.messager.alert("ERROR", "setSineWidth() parameter input error.(integer type only)");
            }
        }
    };
    
    /**
     * 원형 그래프를 선으로 표시할 때 선의 두께
     * (default = 0)
     * @param series 커서
     * @param int 선 두께(픽셀)
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setStartAngle = function(series, point, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setStartAngle() not definded Series.");
        } else {
            if (!oUtil.isNull(point)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.rendererOptions.startAngle = point;
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.startAngle = point;
                }
            } else {
                $.messager.alert("ERROR", "setStartAngle() parameter input error.(integer type only)");
            }
        }
    };
    
    /**
     * 애니메이션 그리기 속도 설정
     * (default = 0)
     * @param series 커서
     * @param time 속도
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setAnimationTime = function(series, time, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setAnimationTime() not definded Series.");
        } else {
            if(!oUtil.isNull(time)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.rendererOptions.animation = {speed: time};
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) series[idx].rendererOptions.animation = {speed: time};
                }
            } else {
                 $.messager.alert("ERROR", "setAnimationTime() parameter input error.");
            }
        }
    };
    
    /**
     * 
     * @param series 커서
     * @param label
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setSeriesLabel = function(series, label, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setSeriesLabel() not definded Series.");
        } else {
            if(!oUtil.isNull(label)) {
                if(this.chartPropertity.seriesType == "default") {
                    series.label = label;
                } else {
                    if(oUtil.isNull(idx)) idx = 0;
                    if(idx < this.chartPropertity.seriesCount) series[idx].label = label;
                }
            } else {
                 $.messager.alert("ERROR", "setSeriesLabel() parameter input error.");
            }
        }
    };
    
    /**
     * Y축의 좌측에 표기할 문자열 타입 설정
     * @param format 표시할 문자열 형태
     * @param delimiter 구분문자
     */
    this.setLeftYFormat = function(format, delimiter) {
        if(!oUtil.isNull(format)) {
            if(format == "year") {
                this.chartPropertity.axes.yaxis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.yaxis.tickInterval = "1 years";
            }
            if(format == "month") {
                this.chartPropertity.axes.yaxis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.yaxis.tickInterval = "1 months";
            }
            if(format == "date") {
                this.chartPropertity.axes.yaxis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.yaxis.tickInterval = "1 days";
            }
            this.chartPropertity.axes.yaxis.tickOptions.formatString = this.getFormatString(format, delimiter) + " ";
            //this.chartPropertity.axes.yaxis.pad = 100;
        } else {
            $.messager.alert("ERROR", "setLeftYFormat() parameter input error.");
       }
    };
    
    /**
     * X축의 하위에 표기할 문자열 타입 설정
     * @param format 표시할 문자열 형태
     */
    this.setBottomXFormat = function(format, delimiter) {
        if(!oUtil.isNull(format)) {
            if(format == "year") {
                this.chartPropertity.axes.xaxis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.xaxis.tickInterval = "1 years";
            }
            if(format == "month") {
                this.chartPropertity.axes.xaxis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.xaxis.tickInterval = "1 months";
            }
            if(format == "date") {
                this.chartPropertity.axes.xaxis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.xaxis.tickInterval = "1 days";
            }
            this.chartPropertity.axes.xaxis.tickOptions.formatString = this.getFormatString(format, delimiter);
        } else {
            $.messager.alert("ERROR", "setBottomXFormat() parameter input error.");
       }
    };
    
    /**
     * Y축의 우측에 표기할 문자열 타입 설정
     * @param format 표시할 문자열 형태
     */
    this.setRightYFormat = function(format, delimiter) {
        if(!oUtil.isNull(format)) {
            if(format == "year") {
                this.chartPropertity.axes.y2axis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.y2axis.tickInterval = "1 years";
            }
            if(format == "month") {
                this.chartPropertity.axes.y2axis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.y2axis.tickInterval = "1 months";
            }
            if(format == "date") {
                this.chartPropertity.axes.y2axis.renderer = $.jqplot.DateAxisRenderer;
                this.chartPropertity.axes.y2axis.tickInterval = "1 days";
            }
            this.chartPropertity.axes.y2axis.tickOptions.formatString = this.getFormatString(format, delimiter);
        } else {
            $.messager.alert("ERROR", "setRightYFormat() parameter input error.");
       }
    };
    
    /**
     * Y축의 우측 타이틀 설정
     * @param value
     */
    this.setLeftYTitle = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.yaxis.label = value;
        } else {
            $.messager.alert("ERROR", "setLeftYTitle() parameter input error.");
       }
    };
    
    /**
     * Y축의 좌측에 표기할 최소 라벨
     * @param value
     */
    this.setLeftYMinLabel = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.yaxis.min = value;
        } else {
            $.messager.alert("ERROR", "setLeftYMinLabel() parameter input error.");
       }
    };
    
    /**
     * Y축의 좌측에 표기할 최대 라벨
     * @param value
     */
    this.setLeftYMaxLabel = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.yaxis.max = value;
        } else {
            $.messager.alert("ERROR", "setLeftYMaxLabel() parameter input error.");
       }
    };
    
    /**
     * Y축의 좌측 라인 표시여부
     * @param boolean
     */
    this.setLeftYDrawLine = function(boolean) {
        if(!oUtil.isNull(boolean)) {
            if(!oUtil.isNull(this.chartPropertity.axes.yaxis)) this.chartPropertity.axes.yaxis.drawMajorGridlines = boolean;
        } else {
            $.messager.alert("ERROR", "setLeftYDrawLine() parameter input error.");
       }
    };
    
    /**
     * Y축의 좌측 라인 text 각도 설정
     * @param value
     */
    this.setLeftYAngle = function(value) {
        if(!oUtil.isNull(value)) {
            if(!oUtil.isNull(this.chartPropertity.axes.yaxis)) {
                this.chartPropertity.axes.yaxis.rendererOptions.tickOptions.angle = value;
            }
        } else {
            $.messager.alert("ERROR", "setLeftYAngle() parameter input error.");
       }
    };
    
    /**
     * Y축의 우측 타이틀 설정
     * @param value
     */
    this.setRightYTitle = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.y2axis.label = value;
        } else {
            $.messager.alert("ERROR", "setRightYTitle() parameter input error.");
       }
    };
    
    /**
     * Y축의 우측에 표기할 최소 라벨
     * @param value
     */
    this.setRightYMinLabel = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.y2axis.min = value;
        } else {
            $.messager.alert("ERROR", "setRightYMinLabel() parameter input error.");
       }
    };
    
    /**
     * Y축의 우측에 표기할 최대 라벨
     * @param value
     */
    this.setRightYMaxLabel = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.y2axis.max = value;
        } else {
            $.messager.alert("ERROR", "setRightYMaxLabel() parameter input error.");
       }
    };
    
    /**
     * Y축의 우측 라인 표시여부
     * @param boolean
     */
    this.setRightYDrawLine = function(boolean) {
        if(!oUtil.isNull(boolean)) {
            if(!oUtil.isNull(this.chartPropertity.axes.y2axis)) this.chartPropertity.axes.y2axis.drawMajorGridlines = boolean;
        } else {
            $.messager.alert("ERROR", "setRightYDrawLine() parameter input error.");
       }
    };
    
    /**
     * Y축의 우측 라인 text 각도 설정
     * @param value
     */
    this.setRightYAngle = function(value) {
        if(!oUtil.isNull(value)) {
            if(!oUtil.isNull(this.chartPropertity.axes.y2axis)) {
                this.chartPropertity.axes.y2axis.rendererOptions.tickOptions.angle = value;
            }
        } else {
            $.messager.alert("ERROR", "setRightYAngle() parameter input error.");
       }
    };
    
    /**
     * X축 타이틀 설정
     * @param value
     */
    this.setBottomXTitle = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.xaxis.label = value;
        } else {
            $.messager.alert("ERROR", "setBottomXTitle() parameter input error.");
       }
    };
    
    /**
     * X축의 아래에 표기할 최소 라벨
     * @param value
     */
    this.setBottomXMinLabel = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.xaxis.min = value;
        } else {
            $.messager.alert("ERROR", "setBottomXMinLabel() parameter input error.");
       }
    };
    
    /**
     * X축의 아래에 표기할 최대 라벨
     * @param value
     */
    this.setBottomXMaxLabel = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.xaxis.max = value;
        } else {
            $.messager.alert("ERROR", "setBottomXMaxLabel() parameter input error.");
       }
    };
    
    /**
     * X축의 아래에 그래프 시작 위치
     * @param value
     */
    this.setBottomXStartPoint = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.axes.xaxis.rendererOptions.tickInset = value;
        } else {
            $.messager.alert("ERROR", "setBottomXMinLabel() parameter input error.");
       }
    };
    
    /**
     * X축 라인 표시여부
     * @param boolean
     */
    this.setBottomXDrawLine = function(boolean) {
        if(!oUtil.isNull(boolean)) {
            if(!oUtil.isNull(this.chartPropertity.axes.xaxis)) this.chartPropertity.axes.xaxis.drawMajorGridlines = boolean;
        } else {
            $.messager.alert("ERROR", "setBottomXDrawLine() parameter input error.");
       }
    };
    
    /**
     * X축 라인 text 각도 설정
     * @param value
     */
    this.setBottomXAngle = function(value) {
        if(!oUtil.isNull(value)) {
            if(!oUtil.isNull(this.chartPropertity.axes.xaxis)) {
                this.chartPropertity.axes.xaxis.tickOptions.angle = value;
            }
        } else {
            $.messager.alert("ERROR", "setBottomXAngle() parameter input error.");
       }
    };
    
    /**
     * X축 라인 간격
     * @param value
     */
    this.setBottomXTickInterval = function(value) {
        if(!oUtil.isNull(value)) {
            if(!oUtil.isNull(this.chartPropertity.axes.xaxis)) {
                this.chartPropertity.axes.xaxis.tickInterval = value;
            }
        } else {
            $.messager.alert("ERROR", "setBottomXTickInterval() parameter input error.");
       }
    };
    
    /**
     * Y축 왼쪽 라인 간격
     * @param value
     */
    this.setLeftYTickInterval = function(value) {
        if(!oUtil.isNull(value)) {
            if(!oUtil.isNull(this.chartPropertity.axes.xaxis)) {
                this.chartPropertity.axes.yaxis.tickInterval = value;
            }
        } else {
            $.messager.alert("ERROR", "setBottomXTickInterval() parameter input error.");
       }
    };
    
    /**
     * Y축의 표기를 우측으로 변경
     * (default = 25)
     * @param series 시리즈
     * @param format 표시할 문자열 형태
     * @param int 화면에 표시될 그래프 index 위치
     */
    this.setRightYRender = function(series, idx) {
        if(oUtil.isNull(series)) { 
            $.messager.alert("ERROR", "setRightYRender() not definded Series.");
        } else {
            if(this.chartPropertity.seriesType == "default") {
                series.yaxis = "y2axis";
            } else {
                if(oUtil.isNull(idx)) idx = 0;
                series[idx].yaxis = "y2axis";
            }
        }
    };
    
    /**
     * 챠트 배경 색상 설정
     * @param value
     */
    this.setBackgroundColor = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.grid.background = value;
        } else {
            $.messager.alert("ERROR", "setBackgroundColor() parameter input error.");
       }
    };
    
    /**
     * 챠트 라인 색상 설정
     * @param value
     */
    this.setChartLineColor = function(value) {
        if(!oUtil.isNull(value)) {
            this.chartPropertity.grid.gridLineColor = value;
        } else {
            $.messager.alert("ERROR", "setChartLineColor() parameter input error.");
       }
    };
    
    /**
     * 챠트 라인 두께
     * @param int
     */
    this.setChartLineWidth = function(size) {
        if(!oUtil.isNull(size)) {
            this.chartPropertity.grid.gridLineWidth = size;
        } else {
            $.messager.alert("ERROR", "setChartLineWidth() parameter input error.");
       }
    };
    
    /**
     * 챠트 border 여부
     * @param int
     */
    this.setChartBorder = function(boolean) {
        if(!oUtil.isNull(boolean)) {
            this.chartPropertity.grid.drawBorder = boolean;
        } else {
            $.messager.alert("ERROR", "setChartBorder() parameter input error.");
       }
    };
    
    /**
     * 챠트 그림자 표시여부
     * @param int
     */
    this.setChartShadow = function(boolean) {
        if(!oUtil.isNull(boolean)) {
            this.chartPropertity.grid.shadow = boolean;
        } else {
            $.messager.alert("ERROR", "setChartShadow() parameter input error.");
       }
    };
    
    /**
     * X-Y축 text 색상 설정
     * @param value
     */
    this.setTextColor = function(value) {
        if(!oUtil.isNull(value)) {
            if(!oUtil.isNull(this.chartPropertity.axes.xaxis)) this.chartPropertity.axes.xaxis.tickOptions.textColor = value;
            if(!oUtil.isNull(this.chartPropertity.axes.yaxis)) this.chartPropertity.axes.yaxis.tickOptions.textColor = value;
            if(!oUtil.isNull(this.chartPropertity.axes.y2axis)) this.chartPropertity.axes.y2axis.tickOptions.textColor = value;
        } else {
            $.messager.alert("ERROR", "setTextColor() parameter input error.");
       }
    };
    
    // passing in the url string as the jqPlot data argument is a handy
    // shortcut for our renderer.  You could also have used the
    // "dataRendererOptions" option to pass in the url.
    this.createChart = function(property, jsonurl, params, jsonHeader) {
        var plot = null;
        
        if(oUtil.isNull(property)) {
            $.messager.alert("ERROR", "setChart() not definded Property.");
        }
        
        var type = property.chatType;
        
        if(typeof jsonurl == "object") {
        	var chartData = chart.getChartData(property, jsonurl, jsonHeader);
            
        	try {
	            if(type != "pie") {
	            	if(chartData.length > 1) {
	            		property.axes.xaxis.ticks = chartData[1];
	            	}
	            }
	            
	            plot = $.jqplot(property.id, chartData[0], property); 
        	} catch(e) {}
        } else if(typeof jsonurl == "string") {
            $.ajax({
                // have to use synchronous here, else the function
                // will return before the data is fetched
                async: false,
                url: jsonurl,
                dataType:"json",
                data:params,
                success: function(data) {
                    if(oUtil.isNull(jsonHeader)) {
                    	alert("please, Enter hearder information.(needs to TICKS and SERIES)");
                    	return;
                    }
                    
                    var chartData = chart.getChartData(property, data.rows, jsonHeader);
                    
                    if(type != "pie") {
                    	if(chartData.length > 1) {
                    		property.axes.xaxis.ticks = chartData[1];
                    	}
                    }
                    
                    plot = $.jqplot(property.id, chartData[0], property);
                }
            });
        }
        
        $('.jqplot-highlighter-tooltip').addClass('ui-corner-all');
        $('#'+property.id).bind('resize', function(event, ui) {
            plot1.replot( { resetAxes: true } );
        });
        
        return plot;
    };
    
    this.getChartData = function(property, data, jsonHeader) {
    	var retValue = new Array();
    	var serise = new Array();
        var ticks = new Array();
        
    	var sCnt = property.seriesCount;
        var type = property.chatType;

        if(!oUtil.isNull(jsonHeader)) {
	    	if(type == "pie") {
	        	if(!oUtil.isNull(jsonHeader.SERIES)) { // 시리즈에 해당하는 값 구하기
	                for(var j = 0; j < sCnt; j++) {
	                    var tmpArray = new Array();
	                    
	                    for(var i = 0; i < data.length; i++) {
	                    	var value = eval("data[i]." + jsonHeader.SERIES[j]);
	                    	
	                    	if(!oUtil.isNull(jsonHeader.TICKS)) {
	                    		var title = eval("data[i]." + jsonHeader.TICKS);
	                    		tmpArray[i] = [title, value];
	                    	} else {
	                    		tmpArray[i] = [value, value];
	                    	}
	                    }
	                    
	                    serise[j] = tmpArray;
	                }
	            }
	        } else {
	            if(!oUtil.isNull(jsonHeader.TICKS) && data.length > 0) {  // x축 라벨로 표시될 컬럼명
	            	var dataTicks;
	            	
	            	if(Array.isArray(data[0])) {
	            		dataTicks = data[0];
	            	} else {
	            		dataTicks = data;
	            	}
	                for(var i = 0; i < dataTicks.length; i++) {
	                    ticks[i] = eval("dataTicks[i]." + jsonHeader.TICKS);
	                }
	            }
	            
	            if(!oUtil.isNull(jsonHeader.SERIES) && data.length > 0) { // 시리즈에 해당하는 값 구하기
	                for(var j = 0; j < sCnt; j++) {
	                	var tmpArray = new Array();
	                	var dataTicks;
		            	
		            	if(Array.isArray(data[j])) {
		            		dataTicks = data[j];
		            	} else {
		            		dataTicks = data;
		            	}
		            	
		                for(var i = 0; i < dataTicks.length; i++) {
		                	tmpArray[i] = eval("dataTicks[i]." + jsonHeader.SERIES[j]);
		                }
	                    
	                    serise[j] = tmpArray;
	                }
	            }
	        }
        } else {
        	serise = data;
        }
        
    	retValue[0] = serise;
    	if(ticks.length > 0) retValue[1] = ticks;
    	
    	return retValue;
    };
    
    var chartArray = new Array();
    
    /**
     * chart객체 리턴
     * @param name chart 객체명
     * @return 설정값을 저장하고 있는 object
     */
    this.getChart = function(name) {
        var checkFlag = true;
        
        // 기존에 생성된 Dataname 속성인지 체크
        for (var i = 0; i < chartArray.length; i++) {
            if (chartArray[i] == name) {
                this.chartPropertity = chartArray[i];
                checkFlag = false;
                
                break;
            }
        }
        // 기등록된 속성이 없으면 속성을 갖는 객체를 생성한다.
        if (checkFlag) {
            this.chartPropertity = new Object(name);
            this.chartPropertity.id = name;
            this.chartPropertity.stackSeries = false;
            this.chartPropertity.grid = {
                            background: '#ffffff',
                            drawBorder: false,
                            shadow: true,
                            gridLineColor: '#897b74',
                            gridLineWidth: 0
                        };
            this.chartPropertity.axesDefaults = {pad: 0};
            this.chartPropertity.axes = {};
            this.chartPropertity.axes.xaxis = { tickInterval: 1, // 셀 구간별 간격(default)
                    autoscale: true,
                    renderer: $.jqplot.CategoryAxisRenderer,
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    tickOptions: {formatString: "",
                    	textColor: "#000000",
                    	fontSize:'11px', 
                        fontFamily:'굴림,  arial, 맑은 고딕, Dotum, Gulim, 굴림, malgun gothic, sans-serif'
                    }, // X축 text format
                    labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                    labelOptions: {
                    	fontSize:'12px', 
                        fontFamily:'Arial'
                    },
                    rendererOptions: { 
                         tickInset: 0, // x축 시작 index
                         minorTicks: 1 // x축 간격당 표시될 트랙수
                    },
                    drawMajorGridlines: true
                    };
            this.chartPropertity.axes.yaxis = {
            		autoscale: true,
                    tickOptions: {formatString: "",
                    	textColor: "#000000",
                    	fontSize:'11px',
                        fontFamily:'굴림,  arial, 맑은 고딕, Dotum, Gulim, 굴림, malgun gothic, sans-serif'
                    }, // Y축 text format
                    labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                    labelOptions: {
                    	fontSize:'12px'
                    },
                    rendererOptions: { tickRenderer:$.jqplot.CanvasAxisTickRenderer, forceTickAt0: true, tickOptions:{} },
                    drawMajorGridlines: true,
                    padMin: 0
                   };
            this.chartPropertity.axes.y2axis = {
            		autoscale: true,
                    tickOptions: {formatString: "",
                    	textColor: "#000000",
                    	fontSize:'11px', 
                        fontFamily:'굴림,  arial, 맑은 고딕, Dotum, Gulim, 굴림, malgun gothic, sans-serif'
                    },
                    labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                    labelOptions: {
                    	fontSize:'12px', 
                        fontFamily:'굴림,  arial, 맑은 고딕, Dotum, Gulim, 굴림, malgun gothic, sans-serif'
                    },
                    rendererOptions: { tickRenderer:$.jqplot.CanvasAxisTickRenderer, alignTicks: true, forceTickAt0: true, tickOptions:{} },
                    drawMajorGridlines: true
                    };
            this.chartPropertity.legend = { show:true}; // 동쪽에 그래프 설명 표시

            chartArray.push(this.chartPropertity);
            
            // 챠트의 바를 클릭하면 아래의 소스와 바인드 되도록 함
            // 챠트내 그래프 타입이 틀릴 경우 각각 호출됨
//            $('#'+name).bind('jqplotDataClick', 
//                function (ev, seriesIndex, pointIndex, data) {
//                    alert('series: '+seriesIndex+', point: '+pointIndex+', data: '+data);
//                }
//            );
        }
        
        return this.chartPropertity;
    };
    
    /**
     * chart 객체의 속성 리턴
     * 
     * @param name chart 객체명
     * @returns chart 객체 속성
     */
    this.getChartProperty = function(name) {
        var propertyObj = null;
        var name_id = null;
        
        if (typeof name == "string") {
            name_id = name;
        } else if (typeof name == "object") {
            $.messager.alert("ERROR", "getChartProperty() parameter input error.(no string)");
        }
        
        // 기존에 생성된 Dataname 속성인지 체크
        for (var i = 0; i < chartArray.length; i++) {
            if (chartArray[i] == name_id) {
                propertyObj = chartArray[i];
                break;
            }
        }
        
        return propertyObj;
    };
    
    this.getFormatString = function(format, delimiter) {
        if(format == "int") return "%'d";
        if(format == "dollar") return "$%'d";
        if(format == "won") return "₩%'d";
        if(format == "yen") return "￥%'d";
        if(format == "float") {
            return "%'."+((oUtil.isNull(delimiter))?"2":delimiter).toString()+"f";
        }
        if(format == "prefix") {
            delimiter = (oUtil.isNull(delimiter))?"":delimiter;
            return delimiter + " %'s";
        }
        if(format == "suffix") {
            delimiter = (oUtil.isNull(delimiter))?"":delimiter;
            return "%'s " + delimiter;
        }
        if(format == "year") {
            delimiter = (oUtil.isNull(delimiter))?"-":delimiter;
            return "%Y";
        }
        if(format == "month") {
        	delimiter = (oUtil.isNull(delimiter))?"-":delimiter;
            return "%Y"+delimiter+"%#m";
        }
        if(format == "date") {
            delimiter = (oUtil.isNull(delimiter))?"-":delimiter;
            return "%Y"+delimiter+"%#m"+delimiter+"%#d";
        }
        
        return "";
    };
};

var chart = new chartClass();