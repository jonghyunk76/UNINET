package com.yni.fta.mm.pop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import com.yni.fta.fm.sm.SM7023;
import com.yni.fta.mm.smp.SMP1002;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;
import kr.yni.frame.web.action.WebAction;
import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipOutputStream;

/**
 * 공통 > 통관 공통팝업 컨트롤 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMB002Ctrl extends YniAbstractController {

	@Resource(name="MMB002")
	private MMB002 MMB002;
	
	@Resource(name="smp1002")
	private SMP1002 smp1002;
	
	@Resource(name="mmA015")
	private MMA015 mmA015;
	
//	@Resource(name = "sm7023")
//    private SM7023 sm7023;
	
	/**
	 * 통관계획 설정 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_01")
	public ModelAndView MMB002_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_01", dataMap);
	}
	
	/**
	 * 세관/과 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_02")
	public ModelAndView MMB002_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_02", dataMap);
	}
	
	/**
	 * 거래처 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_03")
	public ModelAndView MMB002_03Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_03", dataMap);
	}
	
	/**
	 * 표준코드 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_04")
	public ModelAndView MMB002_04Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_04", dataMap);
	}
	
	/**
	 * 항구코드 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_05")
	public ModelAndView MMB002_05Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_05", dataMap);
	}
	
	/**
	 * 운송주선인 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_06")
	public ModelAndView MMB002_06Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_06", dataMap);
	}
	
	/**
	 * 해외거래처 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_07")
	public ModelAndView MMB002_07Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_07", dataMap);
	}
	
	/**
	 * 장치장부호 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_08")
	public ModelAndView MMB002_08Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_08", dataMap);
	}
	
	/**
	 * 브랜드 관리 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_09")
	public ModelAndView MMB002_09Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_09", dataMap);
	}
	
	/**
	 * 표준품명 코드 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_10")
	public ModelAndView MMB002_10Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_10", dataMap);
	}
	
	/**
	 * OpenAPI 가져오기 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_11")
	public ModelAndView MMB002_11Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_11", dataMap);
	}
	
	/**
	 * 화물운송주선업자 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_13")
	public ModelAndView MMB002_13Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_13", dataMap);
	}
	
	/**
	 * 관세사 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_14")
	public ModelAndView MMB002_14Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_14", dataMap);
	}
	
	/**
	 * 해외거래처 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_15")
	public ModelAndView MMB002_15Move(DataMap dataMap) throws Exception {
		Map param = dataMap.getMap();
		
		param.put("OAPI_IEM_CD", "API011"); // 수입요건승인 내역
		param.put("PARA_NM", "crkyCn");
		
//		Map keyMap = sm7023.selectOpenAPIParaKey(param);
		
//		param.putAll(keyMap);
		
		return WebAction.forwarding("/POP/MM-B002_15", dataMap);
	}
	
	/**
	 * 통관 첨부파일 등록 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_16")
	public ModelAndView MMB002_16Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_16", dataMap);
	}	
	
	/**
	 * 그리드 필드 조회 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_17")
	public ModelAndView MMB002_17Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_17", dataMap);
	}
	
	/**
	 * 해외 원산지증명서 발행 기관 조회 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_18")
	public ModelAndView MMB002_18Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_18", dataMap);
	}
	
	/**
	 * 대행사 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_19")
	public ModelAndView MMB002_19Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_19", dataMap);
	}
	
	/**
	 * HS부호 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_20")
	public ModelAndView MMB002_20Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_20", dataMap);
	}	
	
	/**
	 * 운수기관 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_21")
	public ModelAndView MMB002_21Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_21", dataMap);
	}	
	
	/**
	 * 우편번호별관할세관 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_22")
	public ModelAndView MMB002_22Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_22", dataMap);
	}
	
	/**
	 * 검사검역/요건승인 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_24")
	public ModelAndView MMB002_24Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_24", dataMap);
	}
	
	/**
	 * 수입화물진행정보 리스트
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_25")
	public ModelAndView MMB002_25Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_25", dataMap);
	}
	
	/**
	 * 법령부호 서류명 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_26")
	public ModelAndView MMB002_26Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_26", dataMap);
	}	
	
	/**
	 * 기본값일괄수정 조회 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB002_27")
	public ModelAndView MMB002_27Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B002_27", dataMap);
	}
	
	/**
	 * 거래처 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_03/selectClientList")
	public ModelAndView selectClientList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectClientList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 거래처 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_04/selectStandardCode")
	public ModelAndView selectStandardCode(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = smp1002.selectSysCodeList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 항구, 공항, 세관  리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_05/selectPortCodeList")
	public ModelAndView selectPortCodeList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA015.selectKcsStandardCodeList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}	

	/**
	 * 표준 품명 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_10/selectStardProductNameList")
	public ModelAndView selectStardProductNameList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectClientList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 장치장 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_08/selectShedList")
	public ModelAndView selectShedList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectShedList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
    /**
     * 세관, 항구, 공항... 리스트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */   
    @RequestMapping("/mm/pop/MMB002_02/selectKcsCodeList")
    public ModelAndView selectKcsCodeList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = MMB002.selectKcsCodeList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 과 리스트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */   
    @RequestMapping("/mm/pop/MMB002_02/selectKwaList")
    public ModelAndView selectKwaList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = MMB002.selectKwaList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    } 
    
    /**
     * 
     * 세관 과 연계 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB002_02/insertCsmhKwaMapping")
    public ModelAndView insertCsmhKwaMapping(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = MMB002.insertCsmhKwaMapping(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 세관 과 연계 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB002_02/deleteCsmhKwaMapping")
    public ModelAndView deleteCsmhKwaMapping(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
            int resultCnt = MMB002.deleteCsmhKwaMapping(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 세관 과 제외 리스트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */   
    @RequestMapping("/mm/pop/MMB002_02/selectCsmhKwaExcludeList")
    public ModelAndView selectCsmhKwaExcludeList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = MMB002.selectCsmhKwaExcludeList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    } 	
	
	/**
	 * 브랜드 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_09/selectCommonBrandList")
	public ModelAndView selectCommonBrandList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectCommonBrandList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 브랜드 일괄 등록/수정/삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
	@RequestMapping("/mm/pop/MMB002_09/updateCommonBrandInfo")
    public ModelAndView updateCommonBrandInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
            resultCnt = MMB002.updateCommonBrandInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	/**
     * 브랜드 일괄 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
	@RequestMapping("/mm/pop/MMB002_09/deleteCommonBrandInfo")
    public ModelAndView deleteCommonBrandInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
            resultCnt = MMB002.deleteCommonBrandInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	/**
	 * 화물운송주선업자 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_13/selectFreightTransportAgencyList")
	public ModelAndView selectFreightTransportAgencyList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectFreightTransportAgencyList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}	
	
	/**
	 * 관세사 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_14/selectCustomsBrokerAgentList")
	public ModelAndView selectCustomsBrokerAgentList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectCustomsBrokerAgentList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}	
	
	/**
	 * 해외거래처 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_15/selectOverBcncList")
	public ModelAndView selectOverBcncList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectOverBcncList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}		
	
	/**
	 * 통관 첨부파일 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_16/selectEntryFileList")
	public ModelAndView selectEntryFileList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectEntryFileList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
    /**
     * 통관 첨부파일 조회
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/MMB002_16/selectEntryFileDownload")
    public ModelAndView selectEntryFileDownload(HttpServletRequest request, DataMap dataMap) throws Exception {
        Map fileMap = null;
        String message = null;
        byte[] data = null;
        
        Map map = DataMapHelper.getMap(dataMap);
        String field = StringHelper.null2void(map.get("FILE_FIELD_NAME"));
        String fname = StringHelper.null2void(map.get("FILE_NAME"));
        
        try {        	
            fileMap =  MMB002.selectEntryFileInfo(map);
            
            String db = StringHelper.null2void(this.properties.get("application.db.type"));
    		
            if (db.equals("MSSQL") || db.equals("POSTGRESQL")) {
            	data = (byte[]) fileMap.get(field);
            } else {
            	Blob blob = (Blob) fileMap.get(field);
            	data = OracleTypeHelper.getBytes(blob);
            }
        } catch(Exception e) {
            message = getExceptionMessage(request, e, "MSG_UNSPECIFIED_ERROR");
        }
        
        return WebAction.returnFileView(data, fname, message);
    }
    
    /**
     * 
     * 통관 첨부파일 마스터 등록/상세 등록,수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB002_16/mergeEntryFile")
    public ModelAndView mergeEntryFile(HttpServletRequest req, DataMap dataMap) throws Exception {
    	Map resultMap = new HashMap();
    	Map map = null;
        String message = null;

		try {
			map = DataMapHelper.getMap(dataMap);
			
			resultMap = MMB002.mergeEntryFile(map);
			
			if (!resultMap.isEmpty()) {
                resultMap.put("ENTR_ATCHF_MASTR_SN", StringHelper.null2void(resultMap.get("ENTR_ATCHF_MASTR_SN")));
            }
			
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
    
    /**
	 * 통관 첨부파일 상세 삭제(Info)
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_16/deleteEntryFileDetailInfo")
	public ModelAndView deleteEntryFileDetailInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean success = true;
		String message = null;


	    try {
	        int resultCnt = MMB002.deleteEntryFileDetailInfo(DataMapHelper.getMap(dataMap));
	
	        if (resultCnt < 0) {
	            success = false;
	        }
	    } catch (Exception e) {
	        message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
	    }

	    return WebAction.resultJsonMsg(success, message);
	}

    /**
     * 수입신고서 파일 다운로드(ZIP)
     * 
     * @param res HttpServletResponse
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @exception Exception
     * */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/mm/pop/MMB002_16/selectEntryFileZipDownload")
    public ModelAndView selectEntryFileZipDownload(HttpServletResponse res, HttpServletRequest req, DataMap dataMap) throws Exception  {
    	List resultList = null;
		String message = null;
		String fname = null;
		Locale locale = null;
		byte[] data = null;
		byte[] buf = new byte[1024];
		byte[] returnData = null;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		
		try {
			Map map = DataMapHelper.getMap(dataMap);	
			String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
			locale = SystemHelper.getLocale(StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE));
			List gridData = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));	// CheckBox 선택 시(멀티)			 
			StringBuffer entrAtchfMastrSnIn = new StringBuffer();
			String pEntrAtchfMastrSnIn;
			
			if (gridData != null || gridData.size() > 0) {
				for(int i = 0; i < gridData.size(); i++) {
					Map staMap = (Map) gridData.get(i);
					String entrAtchfMastrSn = StringHelper.null2void(staMap.get("ENTR_ATCHF_MASTR_SN"));
					
					if (!entrAtchfMastrSn.isEmpty()) {
						entrAtchfMastrSnIn.append(entrAtchfMastrSn);
						entrAtchfMastrSnIn.append(",");
					}
				}
				
				if(entrAtchfMastrSnIn.length() == 0) {
					throw new RuntimeException(this.getMessage("MSG_EXCEL_NO_DATA", null, lang));
				} else {
					pEntrAtchfMastrSnIn = entrAtchfMastrSnIn.substring(0, entrAtchfMastrSnIn.length()-1);
					map.put("ENTR_ATCHF_MASTR_SN_IN", pEntrAtchfMastrSnIn);
				}
			}			
			
			// 통관 첨부파일 리스트 조회(첨부 전체 다운로드)
			resultList = MMB002.selectEntryFileAllList(map);
			
			if(resultList != null && resultList.size() > 0) {
				fname = StringHelper.null2string(map.get("FILE_NM"), "Import_Evidence_File.zip");
				
				for(int i = 0; i < resultList.size(); i++) {
					Map result = (Map) resultList.get(i);
					
					data = null;
					
					String db = StringHelper.null2void(this.properties.get("application.db.type"));

	                if(db.equals("MSSQL") || db.equals("POSTGRESQL")) {
	                    data = (byte[]) result.get("ATCHF");
	                } else {
	                	Blob blob = (Blob) result.get("ATCHF");
	                    data = blob.getBytes(1, (int) blob.length());
	                }
					
					if(data == null) continue;
						
					ByteArrayInputStream bais = new ByteArrayInputStream(data);
					
					if(data != null && data.length > 0) {
			            String fileFullName = StringHelper.null2string(result.get("FILE_NM"), "FILE(" + (i+1) + ")");
						
			            zos.putNextEntry(new ZipEntry(fileFullName));
			            
						int bytes_read = 0;
						while((bytes_read = bais.read(buf)) != -1){
			                zos.write(buf, 0, bytes_read);
						}
						
						if(resultList.size() == 1) {
							fname = fileFullName;
						}
					} 
					
					if(bais != null) bais.close();
				}
				
				if(resultList.size() == 1) {
					returnData = data;
				}
			}
			
			if(baos.size() <= 0) {
				message = this.messageSource.getMessage("MSG_EXCEL_NO_DATA", null, locale);
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		} finally {
			if(zos != null) {
				try {
					zos.closeEntry();
					zos.close();
					if(baos != null) baos.close();
					return WebAction.returnFileView(baos.toByteArray(), fname, message);	
				} catch (Exception e) {
					zos.close();
					if(baos != null) baos.close();					
					return WebAction.returnFileView(baos.toByteArray(), fname, message);
				}
			}
		}
		
		if(resultList.size() == 1) {
			return WebAction.returnFileView(returnData, fname, message);
		} else {
			return WebAction.returnFileView(baos.toByteArray(), fname, message);			
		}
    }
    
    /**
	 * 표준품명 세번코드 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_10/selectStardHscodeList")
	public ModelAndView selectStardHscodeList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectStardHscodeList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 표준품명코드 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_10/selectStardProductcodeList")
	public ModelAndView selectStardProductcodeList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectStardProductcodeList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 해외 원산지 발행기관 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_18/selectOverseaIssueOrganList")
	public ModelAndView selectOverseaIssueOrganList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectOverseaIssueOrganList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 대행사 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_19/selectAgencyList")
	public ModelAndView selectAgencyList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectAgencyList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 운수기관 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_21/selectTratInsttList")
	public ModelAndView selectTratInsttList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectTratInsttList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 우편번호별관할세관 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_22/selectZipCmpcCsmhList")
	public ModelAndView selectZipCmpcCsmhList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectZipCmpcCsmhList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 검사검역/요건승인 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_24/selectInspRqiList")
	public ModelAndView selectInspRqiList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectZipCmpcCsmhList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 법령부호별 서류명 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_26/selectLwodPapeKndList")
	public ModelAndView selectLwodPapeKndList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectLwodPapeKndList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * selectExpBcncSetupList
	 * 기본값일괄수정 수입 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_27/selectImpBcncSetupList")
	public ModelAndView selectImpBcncSetupList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectImpBcncSetupList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * selectExpBcncSetupList
	 * 기본값일괄수정 수출 리스트 조회
	 * 
	 * @param req HttpServletRequest 객체
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_27/selectExpBcncSetupList")
	public ModelAndView selectExpBcncSetupList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB002.selectExpBcncSetupList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}	
	
	/**
	 * updateBcncSetupBatchModify
	 * 기본값일괄 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB002_27/updateBcncSetupBatchModify")
	public ModelAndView updateBcncSetupBatchModify(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean succes = true;
		String message = null;

		try {
			int cnt = MMB002.updateBcncSetupBatchModify(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				succes = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(succes, message);
	}	
}