아래 디렉토리 구성의 설명에 따라 프로젝트에 맞게 지정합니다.
적용을 위해선 app_resource.properties파일의 infc.map.path의 경로값을 수정해야 합니다.

1. mssql : MS-SQL DB를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : DB Link
2. oracle : Oracle DB를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : DB Link
3. sap1 : SAP를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : RFC 통신 + oracle, 바츠/TOMS HUB
   > 화신/화신정공에 적용함
4. sap2 : SAP를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : RFC 통신 + ms-sql, 바츠/TOMS HUB
   > 인지컨트롤스에 적용함
5. sap3 : SAP를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : RFC 통신 + ms-sql, 바츠/TOMS HUB
   > 엠에스오토텍에 적용함
6. sap4 : SAP를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : RFC 통신 + oracle, 바츠/TOMS HUB
   > 평화정공에 적용함
7. oracle : Oracle DB를 사용하는 회사의 인터페이스 맵핑정보
   > 변경사항 : ITEM_CD, PRODUCT_CD.. 관련하여 사이즈를 100자리로 늘림
   > 인터페이스 방식 : DB Link
   > 대성사에 적용 함.
8. sap5 : SAP를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : RFC 통신 + oracle, 바츠/TOMS HUB
   > 디에스시에 적용함
9. sap6 : SAP를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : RFC 통신 + postgresql, 바츠/TOMS HUB
   > 세정에 적용함   
9. sap7 : SAP를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : SAP PO + oracle, 바츠/TOMS HUB
   > 서연에 적용함
10. sap8 : SAP를 사용하는 회사의 인터페이스 맵핑정보
   > 인터페이스 방식 : RFC 통신 + ms-sql, 바츠/TOMS HUB
   > 위너콤에 적용함
   
   
** demo 폴더는 중계서버용 테스용 폴더입니다.