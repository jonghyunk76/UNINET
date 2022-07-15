package com.yni.fta.common;

public class Consistent {

    // 인터페이스 스케쥴(JOB) 변수
    public final static String IF_JOB_COMPANY_CD = "COMPANY_CD";                    // 회사(법인)코드
    public final static String IF_JOB_SCHEDULE_CD = "SCHEDULE_CD";                  // 스케쥴 코드
    public final static String IF_JOB_CREATE_BY = "CREATE_BY";                      // 등록자
    public final static String IF_JOB_UPDATE_BY = "UPDATE_BY";                      // 수정자
    public final static String IF_JOB_YYYYMM = "P_YYYYMM";                          // 배치수행 년월(월마감 시 사용)
    public final static String IF_JOB_ALIAS_SCHEDULE_CD = "ALIAS_SCHEDULE_CD";      // 스케쥴의 임의명칭
    public final static String IF_JOB_STATUS = "STATUS";                            // 스케쥴 상태(0:대기,1:수행중,2:완료,3:오류)
    public final static String IF_JOB_SCHEDULE_TYPE = "SCHEDULE_TYPE";              // 스케줄 타입 (A or null:자동, M:수동)

    // 인터페이스 배치 변수
    public final static String IF_BATCH_INTERFACE_CD = "IF_CD";                     // 실행할 인터페이스 코드(JCO코드)
    public final static String IF_BATCH_PARENT_CD = "IF_PARENT_CD";                 // 상위 인터페이스 코드(JCO코드)
    public final static String IF_BATCH_REQUIRED = "COLUMN_REQUIRED_YN";            // 인터페이스 필수 항목 여부
    public final static String IF_BATCH_OFFSET = "IF_OFFSET";                       // 시작 포인트
    public final static String IF_BATCH_ITEM_TYPE = "ITEM_TYPE";                    // 실행할 인터페이스 코드(JCO코드)
    public final static String IF_BATCH_MAX_ROWS = "IF_MAX_ROWS";                   // 한번에 읽어들일 ROW수
    public final static String IF_BATCH_TRANS_TYPE = "COLUMN_TRANS_TYPE";           // 컬럼 전송유형(I:Input , O:Output)

    // 외부 통신을 위한 변수
    public final static String IF_BATCH_INTERFACE_METHOD = "IF_METHOD";             // 인터페이스 방식
    public final static String IF_BATCH_JCO_ID = "JCOID";                           // 표준XML 코드(표준 XML을 로딩하기 위한 식별ID)
    public final static String IF_BATCH_FUNCTION_NAME = "SOURCE_PROGRAM_NAME";      // 호출할 원격서버의 프로시져 또는 함수
    public final static String IF_BATCH_INTERFACE_PARAM = "IF_PARAM";               // 배치 파라메터(인터페이스 배치 파라메터 변수를 가지는 명칭) -> Map타입으로 저장됨
    public final static String IF_BATCH_SOURCE_TABLE = "SOURCE_TABLE";              // 소스가 저장된 테이블
    public final static String IF_BATCH_TARGET_TABLE = "TARGET_TABLE";              // 소스를 이관시킬 테이블
    public final static String IF_BATCH_HISTORY_TABLE = "HISTORY_TABLE";            // 임시 테이블 ex) INTERFACE_HISTORY_DATA
    public final static String IF_BATCH_TRANS_PROGRAM_NAME = "TRANS_PROGRAM_NAME";  // 이관프로그램명
    public final static String IF_BATCH_PROCEDURE_ID = "PROCEDURE_ID";              // 실행 Procedure 명
    public final static String IF_BATCH_RETURN_DATA = "RETURN_DATA";                // 배치 실행 후 직접 데이터 리턴
    public final static String IF_BATCH_VALID_CHECK_YN = "VALID_CHECK_YN";          // 배치 시 유효성 체크할지 여부
    public final static String IF_BATCH_FILE_PATH = "FILE_PATH";                    // 실행 프로그램 및 URL 정보
    
    // 배치 처리상태 별수
    public final static String IF_BATCH_TRANS_ID = "INTERFACE_HISTORY_ID";          // 전송이력 번호
    public final static String IF_BATCH_TRANS_DATA_ID = "INTERFACE_HISTORY_DATA_ID";// 전송이력 데이타 번호(IF_CD 적용)
    public final static String IF_BATCH_INTERFACE_ID = "INTERFACE_ID";              // 자동배치 시 하나의 transaction을 식별하기 위한 ID
    public final static String IF_BATCH_TRANS_SEQ = "ROW_SEQ";                      // 전송이력 row 순번
    public final static String IF_PARENT_HISTORY_ID = "PARENT_HISTORY_ID";          // 상위 전송이력 번호
    
    public final static String IF_BATCH_SUBMIT_STATUS = "SUBMIT_STATUS";            // 수신신유형(R:등록/수신, C:취소, S:제출/송신)
    public final static String IF_BATCH_BATCH_STATUS = "BATCH_STATUS";              // 배치수행 결과(성공:S, 오류:E, 미처리(데이터 없음):N)
    public final static String IF_BATCH_TRANS_STATUS = "TRANS_STATUS";              // 이관상태(0:진행중, 1:성공, 2: 에러)
    public final static String IF_BATCH_TOTAL_ROWS = "TOTAL_ROWS";                  // 인터페이스한 ROW 수
    public final static String IF_BATCH_ERROR_MESSAGE = "ERROR_MESSAGE";            // 처리 결과 메시지(결과코드가 'S'인 경우 => 추출된 데이터 건수, 'E'인 경우 => 오류메시지, 'N'인 경우 => 없음)

    // 인터페이스 배치 파라메터 변수(타 시스템간 이관 및 프로시져 호출 시 적용)
    public final static String IF_PARAMETER_TRANS_ID = "INTERFACE_HISTORY_ID";      // 전송이력 번호
    public final static String IF_PARAMETER_COMPANY_CD = "COMPANY_CD";              // 회사(법인)코드
    public final static String IF_PARAMETER_CL_VENDOR_CD = "CL_VENDOR_CD";          // 협력사 코드
    public final static String IF_PARAMETER_BUSINESS_NO = "BUSINESS_NO";            // 회사의 사업자 등록번호(식별번호)
    public final static String IF_PARAMETER_FUNCTION_NAME = "FUNCTION_NAME";        // 수행할 프로시져 명
    public final static String IF_PARAMETER_DIVISION_CD = "DIVISION_CD";            // 사업부 코드
    public final static String IF_PARAMETER_FROM_DATE = "FROM_DATE";                // 자료 수집 기간(FROM)
    public final static String IF_PARAMETER_TO_DATE = "TO_DATE";                    // 자료 수집 기간(TO)
    public final static String IF_PARAMETER_IF_CD = "IF_CD";                        // 인터페이스 코드
    public final static String IF_PARAMETER_ITEM_CD = "ITEM_CD";                    // 품목(또는 제품) 코드
    public final static String IF_PARAMETER_BATCH_FLAG = "BATCH_FLAG";              // 배치구분(D:일배치, M:월배치, W:주배치, U:수동배치)
    public final static String IF_PARAMETER_USER_ID = "USER_ID";                    // 작업을 요청한 사용자ID(엑셀업로드시 적용되는 파라메터 정보)
    public final static String IF_PARAMETER_REQUEST_TYPE = "REQUEST_TYPE";          // 작업을 요청 타입(AS:매출조정, DS:일배치 매출, MS:월배치 매출 등)-다양한 구분으로 활용가능
    public final static String IF_PARAMETER_URL = "URL";                            // URL
    public final static String IF_PARAMETER_TOMS_FTA_CERT_KEY = "TOMS_FTA_CERT_KEY";// License key
    
    // 암호화 패스워드
    public static final String ENCRYPTOR_PASSWORD = "@xhatamadmin!1";

    // TODO SCHEDULE_WORKS
    public final static String IF_BATCH_LANGUAGE = "KR";                            // 처리될 언어명(EN, KR, ES)-20150804 추가
    public final static String IF_BATCH_PARAMETER_NAME = "PARAMETER_NAME";          // 추출될 데이터의 파라메터 명(jco>function>table>parameter)-20150804 추가
    public final static String IF_PARAMETER_VENDOR_CD = "VENDOR_CD";                // 추출될 데이터의 파라메터 명(jco>function>table>parameter)-20150804 추가
    public final static String[] SYNC_INTERFACE_KIND = {""};                        // ERP데이터와 싱크를 맞추기 위한 대상-I/F할 JCO ID

    // CMRT 에러 메시지
    public static final String MESSAGE_NUMBER_E1 = "MSG_NOT_MATCHED_SCOPE";
    public static final String MESSAGE_NUMBER_E2 = "MSG_NOT_MATCHED_VER";
    public static final String MESSAGE_NUMBER_E3 = "MSG_CMRT_NOT_SUPT";
    public static final String MESSAGE_NUMBER_E4 = "MSG_CMRT_ENCRYPT_ER";
    public static final String MESSAGE_NUMBER_E5 = "MSG_CMRT_RECODE_ER";
    public static final String MESSAGE_NUMBER_E6 = "MSG_CMRT_NOT_MATCH";
    public static final String MESSAGE_NUMBER_E7 = "MSG_NOT_FOUND_SHEET";
    public static final String MESSAGE_NUMBER_E8 = "MSG_FILE_TYPE_ERROR";
    public static final String MESSAGE_NUMBER_E9 = "MSG_EXCEL_TYPE_ERROR";
    public static final String MESSAGE_NUMBER_E10 = "MSG_NOT_EXIST_DATA";
    public static final String MESSAGE_NUMBER_E11 = "MST_CMRT_VER_MISS";
    public static final String MESSAGE_NUMBER_E12 = "MSG_FILE_NOT_FOUND";
    public static final String MESSAGE_NUMBER_E13 = "MSG_EXCEL_PARSING_FAIL";
    
}
