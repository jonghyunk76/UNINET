<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A030_01.jsp
  * @Description : 가입신청서 작성
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA030_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'north',border:false" style="overflow:hidden;font-family:'Arial',sans-serif;">
            <ul class="step_area">
               <li id="MMA030_01_divMove01" class="active"><span href="#">1.가입신청여부 확인</span></li>
               <li id="MMA030_01_divMove02"><span href="#">2.가입신청정보 입력</span></li>
               <li id="MMA030_01_divMove03"><span href="#">3.신청완료</span></li>
           </ul>
        </div>
        <div data-options="region:'center',border:false" style="padding:0 3px;">
            <div id="MMA030_01_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true" style="height:auto;">
	            <div id="MMA030_01_div_01" class="easyui-layout" data-options="fit:true">
	                <div data-options="region:'north',border:false" class="h2_etc">
	                    <p class="h2">이용약관</p>
	                    <p style="padding-left:10px;"><textarea readonly style="height:350px;width:99%;">
제1장 총 칙

  제1조 (목적)
    이 ‘FTA원산지관리 업무대행 TOMS 클라우드 서비스’ 이용 약관은 ㈜대유씨엔에이 (이하 ‘회사’)가 사용기업 회원사 (이하 ‘회원’)에게 서비스 하는 ‘FTA원산지관리 
    업무대행 TOMS 클라우드 서비스’의  이용조건, 절차 및 기타 필요한 사항을 규정함을 목적으로 합니다. 
  
  제2조 (용어의 정의) 
    이 약관에서 사용하는 용어의 정의는 다음과 같습니다. 
    1. 회원 : 회사와 서비스 이용에 관한 계약을 체결한 자 
    2. 아이디(ID) : 회원 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 회사가 승인하는 문자와 숫자의 조합 
    3. 비밀번호 : 회원이 통신상의 자신의 비밀을 보호하기 위해 선정한 문자, 숫자, 및 특수 기호의 조합 
    4. 전자우편(E-Mail) : 인터넷을 통한 우편 
    5. 해지 : 회사 또는 회원이 서비스 이용 이후 그 이용계약을 종료시키는 의사표시 
    6. 실명 : 금융실명거래 및 비밀보장에 관한 법률(약칭 : 금융실명법) 상의 개인의 이름 및 회사의 실제 이름
  
  제3조 (약관의 효력 및 변경) 
    1. 이 약관은 서비스를 통하여 이를 공지하거나 전자우편, 또는 기타의 방법으로 회원에게 통지함으로써 효력을 발생합니다.
    2. 회사는 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다.
    3. 회원은 신설 또는 변경된 약관에 동의하지 않을 경우 회원탈퇴를 요청할 수 있으며, 신설 또는 변경된 약관의 효력발생일 이후에도 서비스를 계속 사용할 경우 약관의
       변경사항에 동의한 것으로 간주됩니다. 
  
  제4조 (약관 이외의 준칙) 
    이 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법, 기타 관련법령에 규정되어 있을 경우 그 규정에 따릅니다. 


제2장 서비스 이용계약

  제5조 (이용신청) 
    1. 서비스 이용신청자는 회사에게 ‘서비스 이용 동의서’를 제출함으로써 이용신청을 할 수 있습니다. 
    2. ‘서비스 이용 동의서’ 신청 양식에 기재하는 모든 회원 정보는 실제 데이터인 것으로 간주됩니다. 실명이나 실제 정보를 기재하지 않은 이용자는 법적인 보호를 받을 수 
       없으며, 서비스사용의 제한을 받으실 수 있습니다. 
    3. 회사는 회원의 실명확인을 위하여 전문기관을 통하여 실명인증을 할 수 있습니다. 
  
  제6조 (이용신청의 승낙) 
    1. 회사는 제5조에 따른 이용신청에 대하여 특별한 사정이 없는 한 접수순서에 따라서 이용신청을 승낙합니다.
    2. 회사는 다음 각 호에 해당하는 경우 이용신청에 대한 승낙을 제한할 수 있고, 그 사유가 해소될 때까지 승낙을 유보할 수 있습니다.
      ① 서비스 관련 설비의 용량이 부족한 경우
      ② 기술상 장애사유가 있는 경우 
      ③ 가입신청자가 본 약관 제9조 제3항에 의거하여 회원 자격을 상실한 적이 있었던 경우 
      ④ 등록내용에 허위, 기재 누락, 오기가 있는 경우 
      ⑤ 기타 회사 또는 회사가 필요하다고 인정되는 경우 
  
  제7조 (이용계약의 성립) 
    1. 회원은 서비스 가입 신청시 본 약관을 읽고 "동의합니다"에 서명을 함으로써 본 약관에 동의하는 것으로 간주됩니다. 
    2. 이용계약은 서비스이용신청자의 이용신청에 대하여 회사가 승낙함으로써 성립합니다. 
  
  제8조 (회원 아이디(ID)의 지정 및 변경) 
    1. 회사는 회원의 중복 등록의 방지 등 구분을 위하여 회사 직권 또는 회원의 신청에 의하여 회원의 아이디(ID)를 지정할 수 있습니다. 
    2. 다음 각 호에 해당하는 경우 회사는 직권 또는 회원의 신청에 의하여 회원 아이디(ID)를 변경할 수 있습니다.
      ① 회원 아이디(ID)가 회원의 전화번호, 주민등록번호 등으로 등록되어 있어서 회원의 사생활을 침해할 우려가 있는 경우 
      ② 기타 회사의 합리적인 사유가 있는 경우 
  
  제9조 (서비스의 해지 및 이용 제한) 
    1. 회원이 서비스 이용계약을 해지하고자 하는 경우에는 본인이 TOMS 클라우드 서비스 화면 또는 전자우편을 통하여 회사에 회원탈퇴신청을 하여야 하며 회원의 
       탈퇴신청에 대해 회사는 24시간 내로 탈퇴 처리를 할 의무가 있습니다. 
    2. 회원이 파산한 때는 회원자격이 정지 또는 상실됩니다. 
    3. 회사는 회원이 제21조 회원의 의무에 위배되는 행위를 한 경우 사전통지 없이 서비스 이용계약을 해지하거나 회원자격을 적절한 방법으로 제한 및 정지할 수
       있습니다. 
  
  제10조 (회원 자격 및 회원자격의 해지, 제한, 정지의 절차) 
    1. 회사는 제9조 제3항의 규정에 의하여 회원자격을 해지, 제한 또는 정지하고자 하는 경우에는 그 사유, 일시 및 기간을 정하여 전자우편, 서면 또는 전화 등의 방법에 의
       하여 정지 후 즉시, 해당 회원 또는 대리인에게 통지합니다. 다만, 회사가 긴급하게 회원의 서비스 이용을 정지할 필요가 있다고 인정하는 경우에는 그러하지
       아니합니다. 
    2. 제1항에 의하여 이용제한의 통지를 받은 회원 또는 그 대리인이 이용제한 통지에 대하여 이의가 있는 경우에는 이의신청을 할 수 있습니다. 
    3. 회사는 제2항의 규정에 의한 이의신청에 대하여 그 확인을 위한 기간까지 이용제한을 일시 연기할 수 있으며, 그 결과를 이용자 또는 그 대리인에게 통지합니다. 
    4. 회사는 이용제한기간 중에 그 사유가 해소된 것이 확인된 경우에는 이용제한 조치를 즉시 해제합니다. 
  
  제11조 (회원에 대한 통지) 
    1. 회사가 회원에 대해 계약의 변경, 서비스 변경 등 기타 업무상 필요에 의한 통지를 하는 경우, 회원이 회사에 제출한 전자우편 주소 또는 회원 ID인 전자우편 주소로
       할 수 있습니다. 
  
  제12조 (회원 정보의 보호 및 비밀유지) 
    1. 회사는 관련법령이 정하는 바에 따라서 서비스와 관련하여 기득한 회원의 기업정보 및 개인정보를 보호해야 합니다. 회원의 기업정보, 회원이 입력한 FTA 업무와 
       관련된 정보 및 개인정보 보호에 관해서는 관련법령 및 회사가 정하는 "개인정보 보호정책"에 정한 바에 의합니다. 
    2. 회사는 회원의 정보에 대한 접근 권한을 서비스 업무 전담인원으로 엄격히 제한한다.
    3. 회사는 서비스와 관련하여 기득한 회원 정보는 제3자에게 비밀을 유지하여야 한다. 단, 회원이 서면상 동의한 고객사에 제출하는 원산지확인서 정보는 예외로 한다.
    4. 본조 제 1항의 비밀 유지와 관련하여 회사는 법률상의 모든 책임을 진다.


제 3 장 서비스 제공 및 이용

  제13조 (서비스의 내용 및 이용) 
    1. 회사가 회원에게 제공하는 ‘FTA원산지관리 업무대행 TOMS 클라우드 서비스’ 기본서비스의 내용은 다음과 같습니다. 
      ① FTA사전컨설팅 (FTA관리현황 진단 및 원산지관리 교육)
      ② FTA데이터 접수 (기초 데이터 점검 및 작성 교육)
      ③ 원산지 판정 수행
      ④ 원산지 판정 결과 오류원인 분석
      ⑤ 원산지 판정 후 점검 레포트 제공 
      ⑥ 실사대응 서류관리 
      ⑦ FTA업무 상담 (Help Desk, 원격지원)
    2. 회사가 회원에게 제공하는 부가서비스(옵션사항)는 다음과 같으며 이에 소요되는 비용은 모두 실비를 기준으로 별도로 청구합니다.
      ① 품목 분류 
      ② 인증수출자 획득
      ③ 국내 고객사 현장실사 지원
      ④ 협력사 외주화 서비스 (협력사 원산지확인서 검증, 협력사 평가 및 사후관리)
      ⑤ 원산지판정 정합성 진단 및 역내산 전환 전략 수립
      ⑥ 기타 회원의 요청에 의한 추가 서비스
    3. 회사는 필요한 경우 서비스의 내용을 추가 또는 변경할 수 있습니다. 이 경우 회사는 본 약관 제11조에 따라 추가 또는 변경내용을 회원에게 통지합니다. 
  
  제14조 (서비스 사용요금) 
    1. 서비스 사용요금은 ‘서비스 이용 계약서’ 또는 ‘견적서’에서 협약한 내용에 따릅니다. 
    2. 서비스 구성은 ‘기본서비스’와 ‘부가서비스(옵션사항)’로 분류됩니다.
  
  제15조 (서비스 개시) 
    서비스는 회사가 제7조에 따라서 이용신청을 승낙에 따라 서비스 이용 시작일로부터 개시됩니다. 다만, 회사의 업무상 또는 기술상의 장애로 인하여 서비스를 즉시
    개시하지 못하는 경우 회사는 회원에게 이를 통지하며, 이용 시작일은 서비스 개시 날까지 연기된 것으로 합니다. 
  
  제16조 (서비스 이용시간) 
    1. 서비스는 회사의 업무상 또는 기술상 장애, 기타 특별한 사유가 없는 한 연중무휴, 1일 24시간 이용할 수 있습니다. 다만 설비의 점검 등 회사가 필요한 경우 또는 설비
       의 장애, 서비스 이용의 폭주 등 불가항력 사항으로 인하여 서비스 이용에 지장이 있는 경우 예외적으로 서비스 이용의 전부 또는 일부에 대하여 제한할 수 있습니다. 
    2. 회사는 제공하는 서비스 중 일부에 대한 서비스 이용시간을 별도로 정할 수 있으며, 이 경우 그 이용시간을 사전에 회원에게 신속히 공지 또는 통지합니다. 
    3. 이러한 경우 이에 대한 후속조치를 즉시 회원에게 공유해서 회원에게 손해가 발생하지 않도록 노력하여야 한다. 
  
  제17조 (서비스 운영 정보의 제공 및 안내) 
    1. 회사는 서비스를 운용함에 있어서 각종 정보를 서비스에 게재하는 방법 등으로 회원에게 제공할 수 있습니다. 
    2. 회사는 서비스의 운용과 관련하여 서비스 화면, 판정 후 점검 레포트 등을 전자우편 등으로 회원에게 제공할 수 있습니다. 
  
  제18조 (서비스 제공의 중지) 
    1. 회사는 다음 각 호에 해당하는 경우 서비스의 제공을 완전히 중지할 수 있습니다. 
      ① 설비의 보수 등을 위하여 부득이한 경우 
      ② 전기통신사업법에 규정된 기간통신사업자가 전기통신서비스를 중지하는 경우 
      ③ 회사는 국가비상사태, 정전, 서비스 설비의 장애 또는 서비스 이용의 폭주 등으로 정상적인 서비스 이용에 지장이 있는 경우 서비스의 전부 또는 일부를 제한하거나
        중지할 수 있습니다. 
    2. 회사가 통제할 수 없는 사유로 인한 서비스의 중단(시스템 관리자의 고의 및 과실이 없는 디스크 장애, 시스템 다운 등)으로 인하여 사전 통지가 불가능한 경우에는
       사전통지 의무에서 이를 제외 할 수 있습니다.
    3. 이러한 경우 회사는 즉시 사후 통지를 해야하며, 회원에게 발생 가능한 손해에 대하여 회원의 승인 하에 이를 방지 또는 최소화하기 위한 조치를 취해야 합니다.
  
  제19조 (보안상 긴급상황) 
    1. 보안상 심각하고 시급을 요하는 프로그램 결함이나 장애 혹은 그에 준하는 사건발생 시, 회사에서 고객의 해당 부분을 일괄적으로 패치를 할 수 있습니다. 
    2. 보안상 심각하고 시급을 요하는 경우 회사에서 고객의 인증관련 정보를 응급 변경할 수 있습니다. 
    3. 본조 제1, 2항의 긴급상황 대처 전 회사는 공지나 이메일을 통하여 고객에게 이를 알려야 합니다. 만약 상황이 긴급하여 이를 알리기에 어려움이 있다면, 회사는
       대처 후라도 이를 공지나 이메일을 통하여 알려야 합니다. 


제4장 서비스와 관련한 권리 및 의무 관계

  제20조 (회사의 의무) 
    1. 회사는 제15조 및 제17조에서 정한 경우를 제외하고 이 약관에서 정한 바에 따라 계속적, 안정적으로 서비스를 제공해야합니다.
    2. 회사는 서비스에 관련된 설비를 항상 운용할 수 있는 상태로 유지 보수하고, 장애가 발생하는 경우 지체 없이 이를 수리, 복구해야 합니다. 
    3. 회사는 서비스와 관련한 회원의 불만사항이 접수되는 경우 이를 즉시 처리하여야 하며, 즉시 처리가 곤란한 경우 그 사유와 처리 일정을 서비스 또는 전자우편을
       통하여 동 회원에게 사후 통지하여야 합니다. 
  
  제21조 (회원의 의무)
    1. 회원은 관계법령, 이 약관의 규정, 이용안내 및 주의사항 등 회사와 회사가 통지하는 사항을 준수하여야 하며, 기타 회사의 업무에 방해되는 행위를 하여서는 아니
       됩니다. 
    2. 회원은 서비스를 이용하여 얻은 정보를 회사의 사전 승낙 없이 복사, 복제, 변경, 번역, 출판, 방송, 기타의 방법으로 사용하거나 이를 타인에게 제공할 수 없습니다. 
    3. 회원은 이용신청서의 기재내용 중 변경된 내용이 있는 경우 서비스를 통하여 그 내용을 회사에 통지하여야 합니다. 
    4. 회사가 관계법령 및 "개인정보보호정책"에 의하여 그 책임을 지는 경우를 제외하고, 회원에게 부여된 ID의 비밀번호의 관리 소홀, 부정 사용에 의하여 발생하는 모든
       결과에 대한 책임은 회원에게 있습니다. 
    5. 회원은 자신의 ID나 비밀번호가 부정하게 사용되었다는 사실을 발견한 경우에는 즉시 회사에 신고하여야 하며, 회사의 안내가 있는 경우에는 그에 따라야 합니다. 이를
       따르지 않는 경우 이에 따른 모든 결과의 책임은 회원에게 있습니다. 
  
  제22조 (회원의 아이디(ID) 및 비밀번호에 대한 의무)
    1. 회원은 서비스를 이용하는 경우 아이디(ID) 및 비밀번호를 사용해야 합니다. 
    2. 아이디(ID) 및 비밀번호에 대한 모든 관리의 책임은 회원에게 있습니다. 
    3. 회원은 아이디(ID) 및 비밀번호를 제3자에게 이용하게 해서는 안됩니다. 
    4. 아이디(ID) 및 비밀번호의 관리상 불충분, 사용상의 과실, 제3자의 사용 등에 의한 손해의 책임은 회원이 집니다. 
    5. 회원은 회사측의 실수가 아님이 명백하고, 회원의 실수로 인하여 발생한 아이디(ID) 및 비밀번호를 도난당하거나 제3자에게 사용되고 있음을 인지한 경우에는 바로
       회사에 통지하고 회사의 지시가 있는 경우에는 그에 따라야 합니다. 
  
제 5 장 기 타

  제23조 (양도금지)
    회원이 서비스의 이용권한, 기타 이용계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다. 
  
  제24조 (손해배상)
    1. 회사는 회사의 책임 있는 사유로 장애가 발생하여 월 가용률 구간별 고객이 손해를 입은 경우 회원의 청구에 의해서 손해를 배상합니다. 회원이 손해배상을 청구하고
       자 하는 경우 이에 대한 사유, 청구액 및 산출근거, 장애에 대한 상세 내용을 기재하여 서면으로 신청하여야 합니다. 
       * 月 가용률(%) = 100×[1-{서비스를 이용한 한달 동안 회사의 귀책사유로 인한 장애로 서비스를 이용하지 못하는 장애시간(분)의 합/서비스를 이용한 한 달(분)
       * 장애시간 : 서비스를 이용하지 못한 사실을 회원이 회사에 통지한 때 (회원의 통지 전에 회사가 그러한 사실을 알게 된 경우, 그러한 사유가 발생한 때)로 측정됨 
    2. 서비스가 제1항에서 제시된 월 가용률을 만족하지 못할 때 해당월의 손해액은 가용률에 따라 할인율을 고객과 협의하여 결정합니다.
              -----------------------------------------------------------
                  월 가용률(%)                           할인율(%)
              -----------------------------------------------------------
                  99.5% 이상 ~ 99.95%                   ( 10 )% 
                  99.5% 미만                              ( 20 )%
              -----------------------------------------------------------
  
  제25조 (면책)
    1. 회사는 회원이 입력하는 정보 및 Data의 내용을 감시하지 않으며, 회원이 정보 및 Data의 내용에 대한 책임은 각 회원에게 있습니다. 본 서비스를 이용하여 타인에게
       피해를 주거나 미풍양속을 해치는 행위를 하여 발생하는 모든 법적인 책임은 회원에게 있으며, 그 해당 아이디(ID)와 관련 데이터는 보호 받을 수 없습니다. 
    2. 회사는 회원이 서비스에 게재한 정보, Data, 자료에 대하여, 사실의 정확성, 신뢰성 등 그 내용에 관하여는 어떠한 책임을 부담하지 아니하고, 회원은 자기의 책임아래
       서비스를 이용하며, 서비스를 이용하여 게시 또는 전송한 자료 등에 관하여 손해가 발생하거나 자료의 취사선택, 기타 서비스 이용과 관련하여 어떠한 불이익이
       발생하더라도 이에 대한 모든 책임은 회원에게 있습니다. 
    3. 회사가 제공하는 정보와 자료는 회원의 거래의 목적으로 이용될 수 없습니다. 따라서 본 서비스의 정보와 자료 등에 근거한 거래는 전적으로 회원 자신의 책임과
       판단아래 수행되는 것이며, 회사는 회원이 서비스의 이용과 관련하여 기대하는 이익에 관하여 책임을 부담하지 않습니다. 
    4. 회원 아이디(ID)와 비밀번호의 관리 및 이용상의 부주의로 인하여 발생되는 손해 또는 제3자에 의한 부정사용 등에 대한 책임은 모두 회원에게 있습니다. 단, 회사의
       부주의 관리가 확인된 경우에는 그러하지 아니한다.
  
  제26조 (분쟁의 해결) 
    1. 회사와 회원은 서비스와 관련하여 발생한 분쟁을 원만하게 해결하기 위하여 필요한 모든 노력을 하여야 합니다. 
    2. 제1항의 규정에도 불구하고, 동 분쟁으로 인하여 소송이 제기될 경우, 동 소송은 회사의 본사소재지를 관할하는 법원으로 합니다. 


부칙 (시행일)
  본 약관은 2017년 10월 1일부터 적용합니다.
	                    </textarea></p>
	                    <p style="padding:0 10px 0 5px;"><input id="MMA030_01_input_01" type="checkbox" name="chk_info" id="chk_info" value="0"> 이용약관에 동의합니다.</p>
	                </div>
	                <div data-options="region:'center',border:false" style="padding-top:10px;">
	                    <div class="easyui-layout" data-options="fit:true">
	                        <div data-options="region:'west',border:false" class="width50" style="height:80px;">
	                            <div class="h2_etc"><p class="h2">신규 가입신청</p></div>
	                            <div style="padding:0 10px 0 5px;">
		                            <form id="MMA030_01_form_01" name="MMA030_01_form_01" method="post" target="_top">
		                               <table class="dataT">
		                                   <colgroup>
		                                       <col width="200px;" />
		                                       <col width=";"/>
		                                   </colgroup>
		                                   <tbody>
		                                       <tr style="height:40px;">
		                                           <th>사업자등록번호</th>
		                                           <td>
		                                               <input name="BUSINESS_NO" id="BUSINESS_NO" class="easyui-validatebox" required="true" validType="['businessId']" style="width:210px;height:24px;margin-left:5px;"/>
		                                           </td>
		                                       </tr>
		                                   </tbody>
		                               </table>
		                           </form>
	                           </div>
	                           <div style="text-align:center;"><a href="javascript:MMA030_01.control.selectNewJoinCompanyNo();" class="btnLoginBlue">확 인</a></div>
	                        </div>
	                        <div data-options="region:'center',border:false" class="width50" style="height:80px;">
	                            <div class="h2_etc"><p class="h2">가입신청 확인</p></div>
	                            <div style="padding:0 10px 0 5px;">
		                            <form id="MMA030_01_form_02" name="MMA030_01_form_02" method="post" target="_top">
		                               <table class="dataT">
		                                   <colgroup>
		                                       <col width="200px;" />
		                                       <col width=";"/>
		                                   </colgroup>
		                                   <tbody>
		                                       <tr style="height:40px;">
		                                           <th>사업자등록번호</th>
		                                           <td>
		                                               <input name="BUSINESS_NO" id="BUSINESS_NO" class="easyui-validatebox" required="true" validType="['businessId']" style="width:210px;height:24px;margin-left:5px;"/>
		                                           </td>
		                                       </tr>
		                                       <tr style="height:40px;">
		                                           <th>신청자 ID 또는 이메일</th>
		                                           <td>
		                                               <input name="USER_ID" id="USER_ID" class="easyui-validatebox" required="true" style="width:210px;height:24px;margin-left:5px;"/>
		                                           </td>
		                                       </tr>
		                                   </tbody>
		                               </table>
		                           </form>
		                       </div>
	                           <div style="text-align:center;"><a href="javascript:MMA030_01.control.selectOldJoinCompanyNo();" class="btnLoginBlue">확 인</a></div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div id="MMA030_01_div_02" class="easyui-layout" data-options="fit:true">
	                <div class="easyui-layout" data-options="fit:true">
	                     <form id="MMA030_01_form_03" name="MMA030_01_form_03" method="post" target="_top">
	                        <div data-options="region:'north',border:false" style="overflow:hidden;">
	                            <div class="h2_etc"><p class="h2">회사정보</p></div>
	                            <div style="padding:0 10px 0 5px;">
	                               <input type="hidden" id="COMPANY_JOIN_NO" name="COMPANY_JOIN_NO"/>
                                   <table class="dataT">
                                       <colgroup>
                                           <col width="200px;" />
                                           <col width=";"/>  
                                       </colgroup>
                                       <tbody>
                                           <tr>
                                               <th class="point2">회사명</th>
                                               <td>
                                                   <input name="COMPANY_NAME" id="COMPANY_NAME" class="easyui-validatebox" required="true" style="width:300px;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                               <th class="point2">사업자등록번호</th>
                                               <td>
                                                   <input name="BUSINESS_NO" id="BUSINESS_NO" readonly class="input_readOnly" style="width:210px;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                               <th class="point2">대표자명</th>
                                               <td>
                                                   <input name="PRESIDENT_NAME" id="PRESIDENT_NAME" class="easyui-validatebox" required="true" style="width:210px;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                               <th class="point2">전화번호</th>
                                               <td>
                                                   <input name="COM_PHONE_NO" id="COM_PHONE_NO" class="easyui-validatebox" required="true" style="width:210px;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                               <th class="point2">펙스번호</th>
                                               <td>
                                                   <input name="COM_FAX_NO" id="COM_FAX_NO" class="easyui-validatebox" required="true" style="width:210px;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                               <th class="point2">E-Mail</th>
                                               <td>
                                                   <input name="COM_EMAIL" id="COM_EMAIL" class="easyui-validatebox" required="true" validType="['email']" style="width:210px;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                               <th class="point2">주소</th>
                                               <td>
                                                   <input name="ADDRESS1" id="ADDRESS1" class="easyui-validatebox" required="true" style="width:98%;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                               <th>회사 및 제품에 관한 설명</th>
                                               <td>
                                                   <textarea name="COMMENTS" id="COMMENTS" style="height:80px;width:98%;margin-left:5px"></textarea>
                                               </td>
                                           </tr>
                                       </tbody>
                                   </table>
	                           </div>
	                        </div>
	                        <div data-options="region:'center',border:false" style="overflow:hidden;">
	                            <div class="h2_etc"><p class="h2">신청자 정보</p></div>
	                            <div style="padding:0 10px 0 5px;">
                                   <table class="dataT">
                                       <colgroup>
                                           <col width="200px;" />
                                           <col width=";"/>
                                           <col width="200px;" />
                                           <col width=";"/>
                                       </colgroup>
                                       <tbody>
                                           <tr>
                                               <th class="point2">신청자 ID</th>
                                               <td>
                                                   <input name="USER_ID" id="USER_ID" class="easyui-validatebox" required="true" placeholder="가입시 적용할 ID를 입력하세요." style="width:96%;height:20px;margin-left:5px;"/>
                                               </td>
                                               <th class="point2">신청자명</th>
                                               <td>
                                                   <input name="OFFICER_NAME" id="OFFICER_NAME" class="easyui-validatebox" required="true" style="width:96%;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                               <th class="point2">직급</th>
                                               <td>
                                                   <input name="OFFICER_POSITION" id="OFFICER_POSITION" class="easyui-validatebox" required="true" style="width:96%;height:20px;margin-left:5px;"/>
                                               </td>
                                               <th>부서</th>
                                               <td>
                                                   <input name="OFFICER_DEPT" id="OFFICER_DEPT" style="width:96%;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                           <tr>
                                                  <th class="point2">전화번호</th>
                                                  <td>
                                                      <input name="OFFICER_PHONE_NO" id="OFFICER_PHONE_NO" class="easyui-validatebox" required="true" style="width:96%;height:20px;margin-left:5px;"/>
                                                  </td>
                                                  <th>핸드폰 번호</th>
                                                  <td>
                                                      <input name="OFFICER_CELL_NO" id="OFFICER_CELL_NO" style="width:96%;height:20px;margin-left:5px;"/>
                                                  </td>
                                              </tr>
                                           <tr>
                                               <th class="point2">팩스번호</th>
                                               <td>
                                                   <input name="OFFICER_FAX_NO" id="OFFICER_FAX_NO" class="easyui-validatebox" required="true" style="width:96%;height:20px;margin-left:5px;"/>
                                               </td>
                                               <th class="point2">E-Mail</th>
                                               <td>
                                                   <input name="OFFICER_EMAIL" id="OFFICER_EMAIL" class="easyui-validatebox" required="true" validType="['email']" style="width:96%;height:20px;margin-left:5px;"/>
                                               </td>
                                           </tr>
                                       </tbody>
                                   </table>
	                            </div>
	                            <div id="MMA030_01_div_04" style="text-align:center;"><a href="javascript:MMA030_01.control.insertJoinCompanyInfo();" class="btnLoginBlue">신청</a></div>
	                            <div id="MMA030_01_div_05" style="text-align:center;display:none;"><a href="javascript:MMA030_01.control.updateJoinCompanyInfo();" class="btnLoginGrey">가입정보수정</a></div>
                            </div>
                        </form>
                    </div>
	            </div>
	            <div id="MMA030_01_div_03" class="easyui-layout" data-options="fit:true">
	                <table style="height:100%;width:100%;">
	                    <tr height="300px">
	                        <td style="text-align:center;vertical-align:bottom;color:#3e3e3e;">
	                            <span><img src="/images/icon/clu.png"/></span>
	                            <span id="MMA030_01_span_01" style="display:none;">
				                    <h1>입력하신 정보로 가입신청이 완료되었습니다.</h1>
				                    <div style="padding-top:10px;">신청결과는 담당자가 신청정보 확인 후 개별적으로 연락을 드리겠습니다.</div>
			                    </span>
			                    <span id="MMA030_01_span_02" style="display:none;">
                                    <h2>입력하신 정보로 가입신청 정보가 수정되었습니다.</h2>
                                    <p>신청결과는 담당자가 신청정보 확인 후 개별적으로 연락을 드리겠습니다.</p>
                                </span>
			                </td>
			            </tr>
			            <tr>
			                <td style="text-align:center;vertical-align:top;padding-top:16px;">
                                <span><a href="javascript:MMA030_01.ui.firstMove();" class="btnLoginGrey"><spring:message code="TO_FIRST"/></a></span>
                                <span><a href="javascript:MMA030_01.dialog.close();" class="btnLoginBlue"><spring:message code="APPLICATION_COMPLETE"/></a></span>
			                </td>
			            </tr>
	                </table>
                </div>
	        </div>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/MM/POP/MM-A030_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>