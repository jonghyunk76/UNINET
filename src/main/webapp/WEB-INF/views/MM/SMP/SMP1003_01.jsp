<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>		
	<meta charset=utf-8>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="apple-mobile-web-app-title" content="">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes">
	<title></title>
	<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="/css/sample/import.css">
	<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="../css/ie7.css">
	<![endif]-->
</head>
<body>
<div id="wrap">
	<!-- 검색조건 -->
	<div class="sch_box2">
	<div class="sch_boxIn2">		
		<table>
			<caption>데이타 검색조건 설정</caption>
			<colgroup>
				<col width="70px" />
				<col width="230px" />
				<col width="90px" />
				<col width="160px" />
				<col width="100px" />
				<col width="250px" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row" class="point">기준일자</th>
					<td>
						<input type="text" name="" value="" size="5" id="calen" /><label for="calen"><a href="#"><img src="/images/sample/calen.gif" alt="달력" /></a></label> ~ 
						<input type="text" name="" value="" size="5" id="calen2" /><label for="calen2"><a href="#"><img src="/images/sample/calen.gif" alt="달력" /></a></label>
					</td>
					<th scope="row">READONLY</th>
					<td><input type="text" name="" value="" size="15" id="" title="데이타" class="readOnly" /></td>
					<th scope="row">일반콤보요건</th>
					<td>
						<input type="text" name="" value="" size="7" id="" title="콤보요건" /><a href="#"><img src="/images/sample/btn_sch.gif" alt="검색" /></a>
						<input type="text" name="" value="" size="10" id="" title="콤보요건" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</div>
	<!-- //검색조건 -->

	<div class="h2_etc">
		<p class="h2">입출력형태</p>
		<p class="h2_btn">
			<a href="#" class="btn">신규</a>
			<a href="#" class="btn">삭제</a>
			<a href="#" class="btn btn_red">저장</a>				
		</p>
	</div>
	<table class="dataT">
		<caption>데이타 테이블</caption>
		<colgroup>
			<col width="140px" />
			<col width="" />
			<col width="100px" />
			<col width="" />
			<col width="100px" />
			<col width="" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="point2">필수항목</th>
				<td>
					<input type="text" name="" value="" id="" size="5" class="input_red" title="검색어입력" /> <a href="#"><img src="/images/sample/btn_sch.gif" alt="검색" /></a>
					<input type="text" name="" value="" id="" size="5" class="readOnly" title="검색어" />
				</td>
				<th scope="row">일반항목</th>
				<td>
					<input type="text" name="" value="" id="" size="10" title="일반항목입력" /> <a href="#"><img src="/images/sample/btn_sch.gif" alt="검색" /></a>
				</td>
				<th scope="row">라디오버튼</th>
				<td>
					<input type="checkbox" name="" id="money" /><label for="money">천억</label>
					<input type="checkbox" name="" id="money2" /><label for="money2">억원</label>
				</td>
			</tr>
			<tr>
				<th scope="row">달력항목</th>
				<td></td>
				<th scope="row">일반항목</th>
				<td>
					<input type="checkbox" name="" id="check" /><label for="check">활성</label>
					<input type="checkbox" name="" id="check2" /><label for="check2">비활성</label>
				</td>
				<th scope="row">필수항목</th>
				<td>
					<select name="" id="" title="필수항목">
						<option>선택하세요</option>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">일반항목</th>
				<td>
					<select name="" id="" title="필수항목">
						<option>선택하세요</option>
					</select>
				</td>
				<th scope="row">에제1-확인-</th>
				<td>
					<input type="text" name="" value="" id="" size="5" class="input_red" title="검색어입력" /> <a href="#"><img src="/images/sample/btn_sch.gif" alt="검색" /></a>
					<input type="text" name="" value="" id="" size="5" class="readOnly" title="검색어" />
				</td>
				<th scope="row" class="point2">필수항목</th>
				<td>
					<input type="text" name="" value="" id="" size="3" class="input_red" title="필수항목선택" />
					<input type="text" name="" value="" id="" size="7" class="input_red" title="필수항목입력" />
				</td>
			</tr>
			<tr>
				<th scope="row" class="point2">sta_WF_OutputLabel</th>
				<td colspan="5"><textarea rows="3" cols="30" class="input_red" style="width:99%;" ></textarea></td>
			</tr>
			<tr>
				<th scope="row">버튼과 도움말</th>
				<td colspan="5" class="posi">
					<input type="text" name="" value="" id="" size="10" title="내부버튼" />
					<a href="#" class="btn btn_blue">내부버튼</a>	
					<span class="info">MIS 본사 네트워크 백본라우터#1</span>
					<p class="btn_rT">
						<a href="#" class="btn btn_point">내부강조버튼</a>	
					</p>
				</td>
			</tr>
		</tbody>
	</table>

	<ul class="tab_area">
		<li class="on"><a href="#">tabpage11</a></li>
		<li><a href="#">tabpage11</a></li>
		<li><a href="#">tabpage11</a></li>
		<li><a href="#">tabpage11</a></li>
		<li><a href="#">tabpage11</a></li>
		<li><a href="#">tabpage11</a></li>
		<li><a href="#">tabpage11</a></li>
		<li><a href="#">tabpage11</a></li>
		<li><a href="#">tabpage11</a></li>
	</ul>
	<div class="list_area">
		<table class="listT">
			<caption>테이타</caption>
			<colgroup>
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="first">항목</th>
					<th scope="col">순번</th>
					<th scope="col">항목</th>
					<th scope="col">항목코드</th>
					<th scope="col">항목번호</th>
				</tr>
			</thead>
			<tbody>
				<tr class="sel"><!-- 클릭했을 시 추가되는 class -->
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr class="odd">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr class="odd">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>


</div><!--//wrap -->
</body>	
</html>
