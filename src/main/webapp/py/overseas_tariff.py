#-*- coding: utf-8 -*-
import psycopg2
import tradenavi

conn = None

def tradenavi_overseas_tariff(nation, hs_cd):
    rst = "success"
    
    try:
        conn = psycopg2.connect(host='daeuserver.iptime.org', dbname='fta', user='toms', password='toms1219', port='50002') # db에 접속
    
        resultList = list()
    
        # Get 해외 세율 List
        rst = tradenavi.tradenavi_overseas_tariff(conn, nation, hs_cd)
        
        # print(resultList)
        
        # cur = conn.cursor()
        # cur.execute("SET search_path TO CC") # Postgresql에서 DB 스키마 지정
        #
        # for d in resultList:
        #     cur.execute("INSERT INTO CC.CC_TARSCHD_OVSEA (TARSCHD_OVSEA_SN, NATION_CD, TARRATE_SE_CD, HS_CD, TARRATE_SE_NM, TARRATE, APC_BEGIN_DE, APC_END_DE, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) \
        #                 VALUES(NEXTVAL('CC_TARSCHD_OVSEA_S'), %s, %s, %s, '', %s, '20210101', '20210101', now()::timestamp without time zone, 'fta', now()::timestamp without time zone, 'fta')", d)
        #
        # conn.commit()
        # cur.close()
    
    except Exception as error:
        rst = error
    finally:
        conn.close()
        
    return rst