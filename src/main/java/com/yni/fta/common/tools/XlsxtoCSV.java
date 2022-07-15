package com.yni.fta.common.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxtoCSV {

	public static void xlsx(File inputFile, File outputFile) throws Exception {
		// For storing data into CSV files
		StringBuffer data = new StringBuffer();

		FileOutputStream fos = new FileOutputStream(outputFile);
		// Get the workbook object for XLSX file
		FileInputStream fis = new FileInputStream(inputFile);
		Workbook workbook = null;
		
		try {
			String ext = FilenameUtils.getExtension(inputFile.toString());

			if (ext.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (ext.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(fis);
			}

			// Get first sheet from the workbook
			int numberOfSheets = workbook.getNumberOfSheets();

			if (numberOfSheets > 1) {
				numberOfSheets = 1;
			}

			Row row;
			Cell cell;
			// Iterate through each rows from first sheet

			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();

				System.out.println("Sheet Name : " + workbook.getSheetName(i));
				System.out.println("Sheet Row Count : " + sheet.getPhysicalNumberOfRows());

				while (rowIterator.hasNext()) {
					row = rowIterator.next();
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {

						cell = cellIterator.next();

						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							data.append(cell.getBooleanCellValue() + ",");

							break;
						case Cell.CELL_TYPE_NUMERIC:
							data.append(cell.getNumericCellValue() + ",");

							break;
						case Cell.CELL_TYPE_STRING:
							data.append("\"" + cell.getStringCellValue() + "\",");
							break;

						case Cell.CELL_TYPE_BLANK:
							data.append("" + ",");
							break;
						default:
							data.append(cell + ",");

						}
					}

					data.deleteCharAt(data.lastIndexOf(",")); // 마지막 콤마 삭제
					data.append('\n'); // appending new line after each row
				}

			}

			fos.write(data.toString().getBytes());
			fos.close();

		} catch (Exception ioe) {
			ioe.printStackTrace();
		} finally {
			fos.close();
		}
	}

	// testing the application

	public static void main(String[] args) throws Exception {
		// int i=0;
		// reading file from desktop
		File inputFile = new File("d:/test/b.xlsx"); // provide your path
		// writing excel data to csv
		File outputFile = new File("d:/test/bb.csv"); // provide your path
		xlsx(inputFile, outputFile);
		System.out.println("Conversion of " + inputFile + " to flat file: " + outputFile + " is completed");
	}
}