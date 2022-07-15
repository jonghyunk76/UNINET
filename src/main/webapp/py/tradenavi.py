#-*- coding: utf-8 -*-
import requests
from bs4 import BeautifulSoup

####################################################
# File Name : tradenavi_overseas_tariff.py
# Desc : http://www.tradenavi.or.kr/ 해외 관세율 조회
# Prarmeters
# 
####################################################
def tradenavi_overseas_tariff(conn, nationCd, hscode4Unit):
    resultList = list() # Return List
    colList = list()
    colTup = tuple()

    url = "http://www.tradenavi.or.kr/CmsWeb/txrt/tHsCodeSearch.req?inttFtaNatnCd={0}&sKeyword={1}&sKeyword_nm=".format(nationCd, hscode4Unit);

    res = requests.get(url)
    res.raise_for_status()
    soup = BeautifulSoup(res.text, "lxml")

    # tr list
    tr_rows = soup.find("table", attrs={"frame":"void"}).find("tbody").find_all("tr")
    
    ##################################################################
    # Database 접속
    ##################################################################    
    cur = conn.cursor()
    # if len(tr_rows) > 0:
    cur.execute("SET search_path TO CC") # Postgresql에서 DB 스키마 지정
    selectNationTitle = "SELECT NATION_CD, TARRATE_SE, COL_ORDR FROM CC_TARSCHD_OVSEA_SETUP CCTOS WHERE NATION_CD = '%s' ORDER BY COL_ORDR;" % nationCd;
    cur.execute(selectNationTitle)
    rowsNationTitle = cur.fetchall() # 데이터 가져오기

    for td_row in tr_rows:
        cols = td_row.find_all("td")
        # print('a : ', columns)
        if len(cols) <= 1: # 의미없는 데이터는 SKIP
            continue   
        # print('a :', columns[2].get_text().strip(), 'B')
        if cols[2].get_text().strip() == '':
            continue

        for row in rowsNationTitle:
            colList = [col.get_text().strip() for index, col in enumerate(cols) if index == 0 or index == row[2]-1]
            colList.insert(0, nationCd)
            colList.insert(1, row[1])
            colTup = tuple(colList)
            
            print(rowsNationTitle)
            
            resultList.append(colTup)
    cur.close()
              
    return resultList