package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.fm.sm.SM7001;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Service("sm7001")
public class SM7001Impl extends YniAbstractService implements SM7001 {
	
    @Resource(name = "sm7001Dao")
    private SM7001Dao sm7001Dao;
    
    @Resource(name = "sm7002Dao")
    private SM7002Dao sm7002Dao;
    
    /**
     * 회사정보 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectCompanyList(Map map) throws Exception {
        return sm7001Dao.selectCompanyList(map);
    }
    
    /**
     * 회사정보 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectCompanyDupCheck(Map map) throws Exception {
        return sm7001Dao.selectCompanyDupCheck(map);
    }
    
    /**
     * 회사 CI 및 직인 파일 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectStempCIImg(Map map) throws Exception {
        return sm7001Dao.selectStempCIImg(map);
    }
    
    /**
     * 회사정보 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectCompanyInfo(Map map) throws Exception {
        return sm7001Dao.selectCompanyInfo(map);
    }
    
    /**
     * 회사 등록
     * @param map
     * @return
     * @throws Exception
     */
    public int insertCompanyInfo(Map map) throws Exception {
        
        Map dupInfo = sm7001Dao.selectCompanyDupCheck(map);
        
        if (dupInfo != null) {
            int nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            if (nDupCnt > 0) {
                // 이미 등록된 회사정보입니다.
                throw new RuntimeException(getMessage("MSG_EXISTS_COMPANY_INFO"));
            }
        }

        return sm7001Dao.insertCompanyInfo(map);
    }

    /**
     * 회사정보 수정
     * @param map
     * @return
     * @throws Exception
     */
    public int updateCompanyInfo(Map map) throws Exception {
        List<FormFile> formFileList = DataMapHelper.getFormFile(map);
        FormFile formFile = null;
        
        if(formFileList.size() > 0) {
            for(int i = 0 ; i < formFileList.size(); i++) {
            	formFile = formFileList.get(i);
            	
            	this.checkFormFile(formFile);
            	
            	if(formFile.getFieldName().equals("CI_IMAGE")) {
            		map.put("CI_IMAGE", formFile.getFileData());
            		map.put("CI_IMAGE_NAME", formFile.getOriginalFilename());
            	} else if(formFile.getFieldName().equals("STAMP_IMAGE")) {
            		map.put("STAMP_IMAGE", formFile.getFileData());
            		map.put("STAMP_IMAGE_NAME", formFile.getOriginalFilename());
            	}
            }
        }
        
        int cnt = sm7001Dao.updateCompanyInfo(map);
        
        map.remove("CI_IMAGE");
        map.remove("STAMP_IMAGE");
        
        return cnt;
    }
    
    private void checkFormFile(FormFile formFile) {
        if (formFile == null) {
            // 파일이 존재하지 않습니다.
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND"));
        }
        
        // contentType = "image/jpeg";
        String contentType = StringHelper.null2void(formFile.getContentType());
        String originalExtension = StringHelper.null2void(formFile.getOriginalExtension());
        int fileSize = formFile.getFileSize();

        if (!(contentType.startsWith("image/"))) {
            // 컨텐츠유형은 jpeg,gif 가능합니다.
            throw new RuntimeException(this.getMessage("MSG_EXTENSION_ONLY"));
        }
        
        if (fileSize > Constants.FILE_MAX_UPLOAD_SIZE) {
            // 파일 용량이 초과하였습니다."
            throw new RuntimeException(this.getMessage("MSG_FILE_SIZE_OVER"));
        }
    }
    
    /**
     * 회사정보 삭제
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteCompanyInfo(Map map) throws Exception {
        return sm7001Dao.deleteCompanyInfo(map);
    }
    
    /**
     * 회사정보 그룹정보 수정
     * @param map
     * @return
     * @throws Exception
     */
    public int updateCompnayGroupCd(Map map) throws Exception {
        return sm7001Dao.updateCompnayGroupCd(map);
    }
    
    /**
     * 회사정보 그룹 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectCompnayGroupList(Map map) throws Exception {
        return sm7001Dao.selectCompnayGroupList(map);
    }
    
    /**
	 * FTA 시스템 옵션 조회
	 * 
	 * @param map
     * @return
     * @throws Exception
	 */
	public Map selectSysconfigOptionInfo(Map map) throws Exception {
		return sm7001Dao.selectSysconfigOptionInfo(map);
	}
	
	/**
     * FTA 시스템 옵션 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int updateSysconfigOptionInfo(Map map) throws Exception {
		return sm7001Dao.updateSysconfigOptionInfo(map);
	}
	
	/**
     * FTA 인증 ID 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int updateCompanyCertIDInfo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
    	
		// 사업부 바츠인증ID 업데이트
        if (list != null && list.size() > 0) {
        	for(int i = 0; i < list.size(); i++) {
        		Map gmap = (Map) list.get(i);
        		
        		sm7002Dao.updateDivisionVaatzIDInfo(gmap);
        	}
        }
		
		return sm7001Dao.updateCompanyCertIDInfo(map);
	}
	
	/**
	 * HUB 매출고객사 리스트 조회
	 * 
	 * @param map
     * @return
     * @throws Exception
	 */
	public List selectHubCertCustomerList(Map map) throws Exception {
		return sm7001Dao.selectHubCertCustomerList(map);
	}
	
	/**
	 * HUB 매출 고객사 리스트 조회
	 * 
	 * @param map
     * @return
     * @throws Exception
	 */
	public List selectHubSalesCustomerList(Map map) throws Exception {
		return sm7001Dao.selectHubSalesCustomerList(map);
	}
	
	/**
	 * HUB 서명권자 리스트 조회
	 * 
	 * @param map
     * @return
     * @throws Exception
	 */
	public List selectHubSignatureList(Map map) throws Exception {
		return sm7001Dao.selectHubSignatureList(map);
	}
    
	/**
	 * 인증 ID 체크
	 * 
	 * @author YNI-maker
	 *
	 */
	public Map selectHubCertID(Map map) throws Exception {
		return sm7001Dao.selectHubCertID(map);
	}
	/**
	 * C/O 발급 고객사 관리
	 * 
	 * @author YNI-maker
	 *
	 */
    public int updateHubCertCustomerInfo(Map map) throws Exception {
        return sm7001Dao.updateHubCertCustomerInfo(map);
    }
    
	/**
	 * C/O 발급 고객사 삭제
	 * 
	 * @author YNI-maker
	 *
	 */
	public int deleteHubCertCustomerInfo(Map map) throws Exception {
		return sm7001Dao.deleteHubCertCustomerInfo(map);
	}
	
	/**
	 * 거래 고객사 관리
	 * 
	 * @author YNI-maker
	 *
	 */
	public int updateHubSalesCustomerInfo(Map map) throws Exception {
		return sm7001Dao.updateHubSalesCustomerInfo(map);
	}

	/**
	 * 거래 고객사 삭제
	 * 
	 * @author YNI-maker
	 *
	 */
	public int deleteHubSalesCustomerInfo(Map map) throws Exception {
		return sm7001Dao.deleteHubSalesCustomerInfo(map);
	}

	/**
	 * 서명권자 관리
	 * 
	 * @author YNI-maker
	 *
	 */
	public int updateHubSignatureInfo(Map map) throws Exception {
		return sm7001Dao.updateHubSignatureInfo(map);
	}
	
	/**
	 * 서명권자 삭제
	 * 
	 * @author YNI-maker
	 *
	 */
	public int deleteHubSignatureInfo(Map map) throws Exception {
		return sm7001Dao.deleteHubSignatureInfo(map);
	}
    
}
