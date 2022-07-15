package com.yni.fta.common;

import kr.yni.frame.util.FileUtil;


public class VaatzMoveTest {
	public static void main(String[] args) throws Exception {
		String inPath = "D:/TOMS/vaatz/xtrus/data/inbound/"; // 처리전 수신폴더 경로
		String fileName = "HUBSBC3-HUBR053-F103-20210502R0530010-0-20210502091217888.xml";
		String movePath = "D:/TOMS/vaatz/xtrus/data/finish/inbound/pending";
		
		if(movePath != null) {
			System.out.println("Start");
			FileUtil.fileMove(inPath, fileName, movePath);
			System.out.println("End");
		}
	}
}