# -*- encoding: utf-8 -*-
import re

class TariffUtil:
    # Tab, 공백문자 한번에 제거하기
    def clearStr(s):
        s = re.sub(r"[\t\"]*", "", s)
        s = re.sub(r"[\n\"]*", "", s)
        s = s.strip()
        return s    
    
    # 세율구분명칭 변환하기
    def getRateType(s):
        temp = s.upper().replace(" ", "").replace("-", "");
        
        # 무시할 세율 지정
        if any(format in temp for format in ["잠정세율", "소비세", "증치세", "보상세", "사회보장세", "부가세", "특별세", "상품서비스세","WTO협정세율","법정세율", "용도세율", "주택기금", "농업요소가산세", "주택기금"]) : 
            return "";
        
        if temp.find("기본세율") > -1 : # DCT세율가 없는 경우 DCS세율을 쓰고, 이도 없을 경우에는 기본세율을 쓴다.
            s = "BASIC"
        elif temp.find("KRUSFTA") > -1 :
            s = "PKRUS"
        elif temp.find("KRCNFTA") > -1 :
            s = "PKRCN"
        elif temp.find("KRSGFTA") > -1 :
            s = "PKRSG"
        elif temp.find("KRAUFTA") > -1 :
            s = "PKRAU"
        elif temp.find("KRCAFTA") > -1 :
            s = "PKRCA"
        elif temp.find("KCAFTA") > -1 :
            s = "PKRCE"
        elif temp.find("KRCLFTA") > -1 :
            s = "PKRCL"
        elif temp.find("KRCOFTA") > -1 :
            s = "PKRCO"
        elif temp.find("KRPEFTA") > -1 :
            s = "PKRPE"
        elif temp.find("KREUFTA") > -1 :
            s = "PKREU"
        elif temp.find("KRNZFTA") > -1 :
            s = "PKRNZ"
        elif temp.find("KRTRFTA") > -1 :
            s = "PKRTR"
        elif temp.find("KRUKFTA") > -1 :
            s = "PKRUK"
        elif temp.find("APTA") > -1 :
            s = "PKRAP"
        elif temp.find("CEPA") > -1 :
            s = "PKRIN"
        elif temp.find("AKFTA") > -1 :
            s = "PKRAS"
        elif temp.find("VKFTA") > -1 :
            s = "PKRVN"
        elif temp.find("EFTA") > -1 :
            s = "PKREF"
        elif temp.find("RCEP") > -1 :
            s = "PKRRC"
        elif temp.find("MFN") > -1 :
            s = "BASIC"
        elif temp.find("품명") > -1 :
            s = "HS_DESC"
        elif temp.find("DCS") > -1 :
            s = "DCS"
        elif temp.find("DCT") > -1 :
            s = "DCT"
            
        s = s.strip()
        return s
    
    # 세율 숫자로 변환하기
    def getRate(s):
        temp = s.lower()
        
        if temp == "u" :
            s = ""
        elif temp.find("free") > -1 :
            s = "0"
        elif temp.find("exempted") > -1 : # 면제
            s = "0"
        elif temp.find("미양허") > -1 : # 양허 미대상
            s = ""
        elif temp.find("상세보기") > -1 : # 상세보기(+) 제외
            s = ""
        elif temp.find("2.5% on the value of the lead content") > -1 : # 특정 조건 세율 
            s = "" # ?*0.025"
        elif temp.find("fr.") > -1 : # 단위당 세율을 곱하는 기준
            s = s.lower().replace("fr.", "").strip()
            if s.strip() != "0":
                s = "" # s+"*?"
        else:
            s = s.replace("%", "").strip()
        
        return s
    
    def changeCode4URL(s):
        dicty = {"_40":"(", "_41":")", "_60":"<", "_62":">", "_47":"/", "_37":"%", "_45":"-", "_124":"|", "_43":"+", "_38":"&", "_58":":", "_91":"[", "_93":"]"}
        for x, y in dicty.items():
            s = s.replace(x, y)
        
        return s