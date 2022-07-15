#-*- coding: utf-8 -*-
import sys
import tradenavi_map
import json
from lxml.etree import indent

# print("path = "+str(sys.argv[0])+", parameter = " + sys.argv[1])

rst = ""

try:
    # rst = tradenavi_map.tradenavi_overseas_tariff(sys.argv[1], sys.argv[2])
    rst = tradenavi_map.tradenavi_overseas_tariff('VN','8707')
    
    # print(json.dumps(rst, ensure_ascii=False, indent="\t"))
except Exception as error:
    print(error)