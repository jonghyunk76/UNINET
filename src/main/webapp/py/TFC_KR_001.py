# -*- encoding: utf-8 -*-
from typing import Dict
import tariff_util as tarif
import requests
import sys
import io
import json
from bs4 import BeautifulSoup
import statistics

####################################################
# File Name : tradenavi_overseas_tariff.py
# Desc : http://www.tradenavi.or.kr/ 해외 관세율 조회
# Prarmeters
# 
####################################################
sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding='utf-8')

def tradenavi_overseas_tariff(cl_url, nationCd, hscode4Unit):
    resultList = list() # Return List
    titleDic = {}
    colDic = {}
    
    # url = "http://www.tradenavi.or.kr/CmsWeb/txrt/tHsCodeSearch.req?inttFtaNatnCd={0}&sKeyword={1}&sKeyword_nm=".format(nationCd, hscode4Unit)
    url = tarif.TariffUtil.changeCode4URL(cl_url).format(nationCd, hscode4Unit)
    res = requests.get(url)
    res.raise_for_status()
    soup = BeautifulSoup(res.text, "lxml")

    # title
    title_row = soup.find("table", attrs={"frame":"void"}).find("thead").find_all("th")
    # tr list
    tr_rows = soup.find("table", attrs={"frame":"void"}).find("tbody").find_all("tr")
    
    for i, title_col in enumerate(title_row):
        # 더보기 컬럼 제거
        if title_col.get_text() in ('더보기'):
            continue

        # 타이틀 Dict 등록
        titleDic[i] = title_col.get_text()

    # print(titleDic)
    # print(url)
    
    for td_row in tr_rows:
        cols = td_row.find_all("td")
        # print('a : ', cols)
        if len(cols) <= 1: # 의미없는 데이터는 SKIP
            continue   
        # print('a :', cols[2].get_text().strip(), 'B')
        if cols[2].get_text().strip() == '':
            continue
        
        # 데이터 초기화
        colDic = {}
        rate_desc = ""
        rate_se_desc = ""
        hs_desc = ""
        
        # print(cols)
        for key, value in titleDic.items():
            for i, col in enumerate(cols):
                if key == i:
                    rate_se = tarif.TariffUtil.getRateType(value)
                    
                    if rate_se == "":
                        continue
                    
                    rate_se_desc = value
                    cte = tarif.TariffUtil.clearStr(col.get_text().strip());
                    
                    if i > 1: # 세율
                        rte = tarif.TariffUtil.getRate(col.get_text().strip());
                        
                        if (rate_se == "DCS") | (rate_se == "DCT"):
                            colDic[rate_se] = rte
                        elif colDic.get("BASIC") :
                            # print("basic = ", colDic["BASIC"] == "0");
                            if((colDic["BASIC"] == "0") & (rte == "")):
                                colDic[rate_se] = "0"
                            else:
                                colDic[rate_se] = rte
                        else:
                            colDic[rate_se] = rte
                            
                        colDic[rate_se+"_RATE_SE_DESC"] = value
                        colDic[rate_se+"_RATE_DESC"] = cte
                        # print(colDic[rate_se]," > " , col.get_text(), " : " , value, " > " + str(i), " > " , str(key))
                    else:
                        colDic[rate_se] = cte
                        hs_desc = cte # index 두번째 값 = 품명
                    
            if len(colDic) > 0:
                colDic['NATION_CD'] = nationCd
                colDic["HS_DESC"] = hs_desc
                
        resultList.append(colDic);
        
    return resultList

try:
    # EU국가는 국가코드가 아닌 EU파라메터로 지정할 것
    # rst = tradenavi_overseas_tariff('TH','8708')
    # print(rst)
    rst = tradenavi_overseas_tariff(sys.argv[1], sys.argv[2].upper(), sys.argv[3])
    print(json.dumps(rst, ensure_ascii=False, indent="\t"))
except Exception as error:
    print("ERROR: ",error)