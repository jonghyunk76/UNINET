package com.yni.fta.common;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDownTest {
	public static void main(String[] args) {
		excelWriter("D:/excel_demo.xlsx");
	}

	// 참고사이트
	// https://huskdoll.tistory.com/795
	// https://good4uprin.tistory.com/35

	/**
	 * 엑셀파일 쓰기 예제 메소드
	 * 
	 * @param filePath
	 */
	static void excelWriter(String filePath) {
		XSSFWorkbook xworkbook = null;
		XSSFSheet xSheet = null;
		XSSFRow xRow = null;
		XSSFRow xRow4 = null;
		XSSFRow xRow5 = null;
		XSSFRow xRow6 = null;
		XSSFCell xCell = null;

		/* 3행 3열 예시 */
		try {
			xworkbook = new XSSFWorkbook();
			xSheet = xworkbook.createSheet("차종별 원가 변동 현황");

			/*
			 * //3.셀 스타일 및 폰트 설정 CellStyle styleOfBoardFillFontBlack11 =
			 * xworkbook.createCellStyle(); //정렬
			 * styleOfBoardFillFontBlack11.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
			 * styleOfBoardFillFontBlack11.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			 * //높이 가운데 정렬 //배경색
			 * styleOfBoardFillFontBlack11.setFillForegroundColor(IndexedColors.LAVENDER.
			 * getIndex());
			 * styleOfBoardFillFontBlack11.setFillPattern(CellStyle.SOLID_FOREGROUND); //테두리
			 * 선 (우,좌,위,아래)
			 * styleOfBoardFillFontBlack11.setBorderRight(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardFillFontBlack11.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardFillFontBlack11.setBorderTop(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardFillFontBlack11.setBorderBottom(HSSFCellStyle.BORDER_THIN); //폰트
			 * 설정 Font Black11 = xworkbook.createFont(); Black11.setFontName("나눔고딕"); //글씨체
			 * Black11.setFontHeight((short)(11*20)); //사이즈
			 * styleOfBoardFillFontBlack11.setFont(Black11);
			 * 
			 * //4.셀 스타일 및 폰트 설정(일반 텍스트) CellStyle styleOfBoardFontBlack11 =
			 * xworkbook.createCellStyle(); //정렬
			 * styleOfBoardFontBlack11.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
			 * styleOfBoardFontBlack11.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이
			 * 가운데 정렬 //테두리 선 (우,좌,위,아래)
			 * styleOfBoardFontBlack11.setBorderRight(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardFontBlack11.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardFontBlack11.setBorderTop(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardFontBlack11.setBorderBottom(HSSFCellStyle.BORDER_THIN); //폰트 설정
			 * (위 폰트 사용) styleOfBoardFontBlack11.setFont(Black11);
			 * 
			 * //4.셀 스타일 및 폰트 설정(금액) CellStyle styleOfBoardMoneyFontBlack11 =
			 * xworkbook.createCellStyle(); //정렬
			 * styleOfBoardMoneyFontBlack11.setAlignment(CellStyle.ALIGN_RIGHT); //우측 정렬
			 * styleOfBoardMoneyFontBlack11.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			 * //높이 가운데 정렬 //테두리 선 (우,좌,위,아래)
			 * styleOfBoardMoneyFontBlack11.setBorderRight(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardMoneyFontBlack11.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardMoneyFontBlack11.setBorderTop(HSSFCellStyle.BORDER_THIN);
			 * styleOfBoardMoneyFontBlack11.setBorderBottom(HSSFCellStyle.BORDER_THIN); //폰트
			 * 설정 (위 폰트 사용) styleOfBoardMoneyFontBlack11.setFont(Black11); //천단위 쉼표, 금액
			 * styleOfBoardMoneyFontBlack11.setDataFormat(HSSFDataFormat.getBuiltinFormat(
			 * "#,##0"));
			 */

			/**
			 * ROW, CELL 숨김
			 */
			// 세로(A,B) 숨김
			xSheet.setColumnHidden(0, true);
			xSheet.setColumnHidden(1, true);

			// 가로(1) 숨김
			xRow = xSheet.createRow(0);
			xRow.setZeroHeight(true);

			/**
			 * WIDTH
			 */
			xSheet.setColumnWidth(3, (short) 1024); // No
			xSheet.setColumnWidth(4, (short) 2800); // 품번
			xSheet.setColumnWidth(5, (short) 7800); // 품명

			/**
			 * TITLE
			 */
			xRow = xSheet.createRow(1);
			xSheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 6));
			xCell = xRow.createCell(2);
			xCell.setCellStyle(setCellStyle(xworkbook, "TITLE_STYLE"));
			xCell.setCellValue("▣ 차종별 원가 변동 현황");

			xRow = xSheet.createRow(2);
			xSheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 30));
			xCell = xRow.createCell(2);
			xRow.setHeight((short) 80);
			xCell.setCellStyle(setCellStyle(xworkbook, "BLACK_LINE"));

			xRow = xSheet.createRow(3);
			xSheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 30));
			xCell = xRow.createCell(0);
			xRow.setHeight((short) 80);

			/**
			 * HEADER 1 생성
			 */

			xRow4 = xSheet.createRow(4);
			xCell = xRow4.createCell(2);
			xCell.setCellValue("부품 기준 정보");

			xCell = xRow4.createCell(9);
			xCell.setCellValue("1차");

			xCell = xRow4.createCell(15);
			xCell.setCellValue("2차");

			xCell = xRow4.createCell(21);
			xCell.setCellValue("3차");

			xCell = xRow4.createCell(27);
			xCell.setCellValue("초도가");

			xCell = xRow4.createCell(30);
			xCell.setCellValue("비고");

			/////////////////////////////////////////////////////////
			xRow5 = xSheet.createRow(5);

			xCell = xRow5.createCell(2);
			xCell.setCellValue("차종");

			xCell = xRow5.createCell(3);
			xCell.setCellValue("No");

			xCell = xRow5.createCell(4);
			xCell.setCellValue("품번");

			xCell = xRow5.createCell(5);
			xCell.setCellValue("품명");

			xCell = xRow5.createCell(6);
			xCell.setCellValue("사양");

			xCell = xRow5.createCell(7);
			xCell.setCellValue("HKMC 담당");

			xCell = xRow5.createCell(9);
			xCell.setCellValue("설계원가");

			xCell = xRow5.createCell(12);
			xCell.setCellValue("구매원가");

			xCell = xRow5.createCell(15);
			xCell.setCellValue("설계원가");

			xCell = xRow5.createCell(18);
			xCell.setCellValue("구매원가");

			xCell = xRow5.createCell(21);
			xCell.setCellValue("설계원가");

			xCell = xRow5.createCell(24);
			xCell.setCellValue("설계원가");

			xCell = xRow5.createCell(27);
			xCell.setCellValue("초도가");

			xCell = xRow5.createCell(30);
			xCell.setCellValue("구매원가");

			//////////////////////////////////////////////////////
			xRow6 = xSheet.createRow(6);
			
			xCell = xRow6.createCell(7);
			xCell.setCellValue("설계원가");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(8);
			xCell.setCellValue("구매원가");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(9);
			xCell.setCellValue("제출일");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(10);
			xCell.setCellValue("EO No");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(11);
			xCell.setCellValue("가격");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(12);
			xCell.setCellValue("제출일");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(13);
			xCell.setCellValue("EO No");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(14);
			xCell.setCellValue("가격");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(15);
			xCell.setCellValue("제출일");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(16);
			xCell.setCellValue("EO No");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(17);
			xCell.setCellValue("가격");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(18);
			xCell.setCellValue("제출일");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(19);
			xCell.setCellValue("EO No");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(20);
			xCell.setCellValue("가격");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(21);
			xCell.setCellValue("제출일");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(22);
			xCell.setCellValue("EO No");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(23);
			xCell.setCellValue("가격");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(24);
			xCell.setCellValue("제출일");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(25);
			xCell.setCellValue("EO No");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(26);
			xCell.setCellValue("가격");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(27);
			xCell.setCellValue("제출일");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(28);
			xCell.setCellValue("EO No");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			xCell = xRow6.createCell(29);
			xCell.setCellValue("가격");
			xCell.setCellStyle(setCellStyle(xworkbook, "HEADER_STYLE"));

			CellRangeAddress region = null;
			// xSheet.addMergedRegion(new CellRangeAddress(4,4,2,8));
			// xSheet.addMergedRegion(new CellRangeAddress(4,4,9,14));
			// xSheet.addMergedRegion(new CellRangeAddress(4,4,15,20));
			// xSheet.addMergedRegion(new CellRangeAddress(4,4,21,26));
			// xSheet.addMergedRegion(new CellRangeAddress(4,5,27,29));
			// xSheet.addMergedRegion(new CellRangeAddress(4,6,30,30));

			region = new CellRangeAddress(4, 4, 2, 8);
			cleanBeforeMergeOnValidCells(xRow5.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow5.getSheet().addMergedRegion(region);

			region = new CellRangeAddress(4, 4, 9, 14);
			cleanBeforeMergeOnValidCells(xRow5.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow5.getSheet().addMergedRegion(region);

			region = new CellRangeAddress(4, 4, 15, 20);
			cleanBeforeMergeOnValidCells(xRow5.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow5.getSheet().addMergedRegion(region);

			region = new CellRangeAddress(4, 4, 21, 26);
			cleanBeforeMergeOnValidCells(xRow5.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow5.getSheet().addMergedRegion(region);

			region = new CellRangeAddress(4, 5, 27, 29);
			cleanBeforeMergeOnValidCells(xRow5.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow5.getSheet().addMergedRegion(region);

			region = new CellRangeAddress(4, 6, 30, 30);
			cleanBeforeMergeOnValidCells(xRow5.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow5.getSheet().addMergedRegion(region);

			/**
			 * HEADER 2 생성
			 */
			/*
			 * xSheet.addMergedRegion(new CellRangeAddress(5,6,2,2));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,6,3,3));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,6,4,4));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,6,5,5));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,6,6,6));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,5,7,8));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,5,9,11));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,5,12,14));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,5,15,17));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,5,18,20));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,5,21,23));
			 * xSheet.addMergedRegion(new CellRangeAddress(5,5,24,26));
			 * //xSheet.addMergedRegion(new CellRangeAddress(5,5,27,29));
			 */
			region = new CellRangeAddress(5, 6, 3, 3);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 6, 2, 2);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 6, 4, 4);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 6, 5, 5);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 6, 6, 6);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 5, 7, 8);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 5, 9, 11);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 5, 12, 14);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 5, 15, 17);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 5, 18, 20);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 5, 21, 23);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);
			region = new CellRangeAddress(5, 5, 24, 26);
			cleanBeforeMergeOnValidCells(xRow6.getSheet(), region, setCellStyle(xworkbook, "HEADER_STYLE"));
			xRow6.getSheet().addMergedRegion(region);

			/**
			 * HEADER 3 생성
			 */

			/*
			 * xCell = xRow.createCell(12); xSheet.addMergedRegion(new
			 * CellRangeAddress(4,4,12,17)); xCell.setCellValue("2차"); xCell =
			 * xRow.createCell(18); xSheet.addMergedRegion(new CellRangeAddress(4,4,18,23));
			 * xCell.setCellValue("3차"); xCell = xRow.createCell(24);
			 * xSheet.addMergedRegion(new CellRangeAddress(4,4,24,25));
			 * xCell.setCellValue("초도가"); xCell = xRow.createCell(26);
			 * xSheet.addMergedRegion(new CellRangeAddress(4,4,26,26));
			 * xCell.setCellValue("비고");
			 */
			/*
			 * xRow = xSheet.createRow(5); xCell = xRow.createCell(0);
			 * xCell.setCellValue(1); xCell = xRow.createCell(1); xCell.setCellValue("DD");
			 * xCell = xRow.createCell(2); xCell.setCellValue("1993-11-30");
			 * 
			 * xRow = xSheet.createRow(6); xCell = xRow.createCell(0);
			 * xCell.setCellValue(2); xCell = xRow.createCell(1); xCell.setCellValue("ZZ");
			 * xCell = xRow.createCell(2); xCell.setCellValue("1991-01-03");
			 */
			File file = new File(filePath);
			FileOutputStream fos = new FileOutputStream(file);
			xworkbook.write(fos);

			if (fos != null)
				fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * EXCEL CELL MERGE Style
	 */
	private static void cleanBeforeMergeOnValidCells(XSSFSheet sheet, CellRangeAddress region, CellStyle cellStyle) {
		for (int rowNum = region.getFirstRow(); rowNum <= region.getLastRow(); rowNum++) {
			XSSFRow row = sheet.getRow(rowNum);
			if (row == null) {
				sheet.createRow(rowNum);
				// System.out.println("while check row "+rowNum+" was created");
			}
			for (int colNum = region.getFirstColumn(); colNum <= region.getLastColumn(); colNum++) {
				XSSFCell currentCell = row.getCell(colNum);
				if (currentCell == null) {
					currentCell = row.createCell(colNum);
					// System.out.println("while check cell "+rowNum+":"+colNum+" was created");
				}

				currentCell.setCellStyle(cellStyle);

			}
		}
	}

	private static CellStyle setCellStyle(XSSFWorkbook xworkbook, String styleType) {
		CellStyle returnStyle = xworkbook.createCellStyle();

		if (styleType.equals("TITLE_STYLE")) {
			// 1.셀 스타일 및 폰트 설정

			// 정렬
			returnStyle.setAlignment(CellStyle.ALIGN_LEFT); // 가운데 정렬
			returnStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
			// 배경색
			returnStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
			// styleOfBoardFillFontBlackBold24.setFillPattern(CellStyle.SOLID_FOREGROUND);
			// 테두리 선 (우,좌,위,아래)
			returnStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			returnStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			returnStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			returnStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// 폰트 설정
			Font fontOfGothicBlackBold24 = xworkbook.createFont();
			fontOfGothicBlackBold24.setFontName("HY헤드라인M"); // 글씨체
			fontOfGothicBlackBold24.setFontHeight((short) (24 * 20)); // 사이즈
			fontOfGothicBlackBold24.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드 (굵게)
			returnStyle.setFont(fontOfGothicBlackBold24);

			/* 검정색 라인 */
			CellStyle styleOfBoardFill = xworkbook.createCellStyle();
			// 배경색
			styleOfBoardFill.setFillPattern(CellStyle.SOLID_FOREGROUND);
		} else if (styleType.equals("BLACK_LINE")) {
			/* 검정색 라인 */
			// 배경색
			returnStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		} else if (styleType.equals("HEADER_STYLE")) {
			System.out.println("HEADER_STYLE");
			// 2.셀 스타일 및 폰트 설정
			returnStyle = xworkbook.createCellStyle();
			// 정렬
			returnStyle.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
			returnStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
			// 배경색
			returnStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			returnStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			// 테두리 선 (우,좌,위,아래)
			returnStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			returnStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			returnStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			returnStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// 폰트 설정
			Font RedBold9 = xworkbook.createFont();
			RedBold9.setFontName("맑은 고딕"); // 글씨체
			// RedBold9.setColor(HSSFColor.RED.index);
			RedBold9.setFontHeight((short) (9 * 20)); // 사이즈
			RedBold9.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드 (굵게)
			returnStyle.setFont(RedBold9);
			System.out.println(returnStyle);
		}

		return returnStyle;

	}

}
