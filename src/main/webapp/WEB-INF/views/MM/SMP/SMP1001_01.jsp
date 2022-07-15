<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SMP1001
  * @Description : 다양한 기능예제
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SMP1001_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false" style="height:300px;">
        <div class="easyui-layout" data-options="fit:true">
			<!-- 검색조건 -->
			<div data-options="region:'north',border:false" class="sch_box">
				<div class="sch_boxIn">
				    <form id="SM1001_01_form_01" method="POST">
						<table>
							<caption>데이타 검색조건 설정</caption>
							<colgroup>
								<col width="70px" />
								<col width="155px" />
								<col width="70px" />
								<col width="230px" />
								<col width="105px" />
								<col width="135px" />
								<col width="55px" />
								<col width="135px"/>
								<col width=";"/>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row" class="point">필수항목</th>
									<td><input type="text" name="" value="" id="calen" size="13"/></td>
									<th scope="row">기본항목</th>
									<td>
									    <input type="checkbox" id="amount1" name="amount1" value="3"/><label for="amount1">천억</label>
		                                <input type="checkbox" id="amount2" name="amount2" value="4"/><label for="amount2">억원</label>
									</td>
									<th scope="row">회사</th>
									<td><input type="text" name="" value="" size="15" class="easyui-validatebox" search="SM1001_01.control.selectMainList" id="sch2" /></td>
									<th scope="row">사업부</th>
									<td><input type="text" name="" value="" size="15" class="easyui-validatebox" search="SM1001_01.control.selectMainList" id="sch3" /></td>
									<td rowspan="2" align="right">
			                           <a href="javascript:SM1001_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="SM1001_01_searchBtn">조회</a>
			                        </td>
								</tr>
								<tr>
									<th scope="row">기본항목</th>
									<td><input type="text" name="" value="" size="10" class="easyui-validatebox" search="SM1001_01.control.selectMainList" id="sch4" /></td>
									<th scope="row">기본항목</th>
									<td>
										<input type="text" name="" value="" size="7" class="easyui-validatebox" search="SM1001_01.control.selectMainList" id="sch5" /><label for="sch5"><a href="javascript:SM1001_01.dialog.openDialog();"><img src="/images/sample/btn_sch.gif" alt="검색" /></a></label>
										<input type="text" name="" value="" size="7" class="easyui-validatebox" search="SM1001_01.control.selectMainList" id="sch6" />
									</td>
									<th scope="row">항목라디오박스</th>
									<td colspan="4" class="lbl">
										<input type="radio" id="money" name="money" value="1" checked="true"/><label for="money">천억</label>
										<input type="radio" id="money" name="money" value="2"/><label for="money">억원</label>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<!-- //검색조건 -->
			<div data-options="region:'center',border:false" style="padding-top:12px;">
				<table id="SM1001_01_grid_01"></table>
			</div>
		</div>
    </div>
	<div data-options="region:'center',border:false">
        <div style="padding:5px;">
            아래 입력란에 값을 입력하면 자동으로 validation한 결과가 표시됩니다.
        </div>
        <div class="sch_box">
	        <div class="sch_boxIn">
	            <form id="SM1001_01_form_02" method="POST">
	                <table>
	                    <caption>validation check</caption>
	                    <colgroup>
	                        <col width="70px" />
	                        <col width="80px" />
	                        <col width="155px" />
	                        <col width="80px" />
	                        <col width="150px" />
	                        <col width="105px" />
	                        <col width="135px" />
	                        <col width="55px" />
	                        <col width="135px"/>
	                        <col width=";"/>
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <td rowspan="2">Validation Sample</td>
	                            <th scope="row" class="point">필수여부</th>
	                            <td>
	                                <input type="text" id="validation1" name="" class="easyui-validatebox" required="true" size="13"/>
	                            </td>
	                            <th scope="row">정수,길이</th>
	                            <td>
	                                <input type="text" id="validation2" name="" class="easyui-validatebox" validType="['integer','maxlength[3]']" size="13"/>
	                            </td>
	                            <th scope="row">년월</th>
	                            <td>
	                                <input type="text" id="validation3" name="" class="easyui-validatebox" validType="'month'" style="width:75px"/>
	                            </td>
	                            <th scope="row">실수</th>
	                            <td>
	                                <input type="text" id="validation4" name="" class="easyui-validatebox" validType="'float'" size="13"/>
	                            </td>
	                            <td rowspan="2" align="right">
	                               <a href="javascript:if(form.handle.isValidate('SM1001_01_form_02'))return;" class="easyui-linkbutton pop_sch" id="SM1001_01_searchBtn">조회</a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row">실수,형식</th>
	                            <td>
	                                <input type="text" id="validation5" name="" class="easyui-validatebox" validType="'floatformat[3,2]'" size="13"/>
	                            </td>
	                            <th scope="row">숫자</th>
	                            <td>
	                                <input type="text" id="validation6" name="re_pwd" class="easyui-validatebox" validType="'number'" size="13"/>
	                            </td>
	                            <th scope="row">min~max길이</th>
	                            <td>
	                                <input type="text" id="validation7" name="re_pwd" class="easyui-validatebox" validType="['minlength[2]','maxlength[5]']" size="13"/>
	                            </td>
	                            <th scope="row">이메일</th>
	                            <td colspan="3" class="lbl">
	                                <input type="text" id="validation8" name="re_pwd" class="easyui-validatebox" validType="'email'" size="13"/>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	            </form>
	        </div>
	    </div>
    </div>
</div><!--//wrap -->

<script type="text/javascript" src="/js/views/MM/smp/SMP1001_01.js"></script>
<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>	
</html>
