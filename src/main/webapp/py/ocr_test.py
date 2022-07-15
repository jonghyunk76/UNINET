import sys
import os
import pyocr
import pyocr.builders
# import PyPDF2
import tabula
import ssl
import pandas
from PIL import Image
import csv
from lib2to3.fixer_util import Newline

if __name__ == '__main__':
    ocr_results = ""
    file = "C:\\Temp\\test_import.png"
    # file = "C:\\Temp\\2. 수입신고서.PDF"
    
    print(file)
    
    if file.lower().endswith("pdf") :
        # create a pdf file object
        # ocr_results = read_pdf(file, pages='all')
        ocr_results = tabula.read_pdf(file, pages='all')
        # tabula.convert_into(file, "C:\\Temp\\output.csv", output_format="csv", pages='all')
        #
        # # create a pdf reader object
        # pdfReader = PyPDF2.PdfFileReader(pdfFileObj, device)
        #
        # for i in range(pdfReader.numPages):
        #     pageObj = pdfReader.getPage(i)
        #     ocr_results += pageObj.extractText()+"\n"
        #
        # # closing the pdf file object
        # pdfFileObj.close()
    else:
        this_program_directory = os.path.dirname(os.path.abspath(__file__))
        os.chdir(this_program_directory)
        
        tesseract_home = "C:\\Program Files\\Tesseract-OCR"
        if tesseract_home not in os.environ["PATH"].split(os.pathsep):
            os.environ["PATH"] += os.pathsep + tesseract_home
            
        tools = pyocr.get_available_tools()
        if len(tools) == 0:
            print("OCR tool is not found in path(" + tesseract_home + ")")
            sys.exit(1)
    
        tool = tools[0]
        
        wk_builder = pyocr.builders.DigitBuilder()
        ocr_results = tool.image_to_string(
            Image.open(file),
            lang='eng',
            builder=wk_builder
        )
    # ls = list(ocr_results)
    # # print(ls)
    # for wt in ls:
    #     print(">>>>>> ", wt)
        # sc = wt.split('')
        # print(type(wt))
        # for i in sc:
        #     print('>>>>', i)
    # cwr = csv.writer(open('C:\\Temp\\convert_csv.csv', 'w', Newline='')
                     
    # for wt in 
    
    print(ocr_results)
    # input("Please Enter to Exit")