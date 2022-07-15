package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 한국 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
@Repository("mmA004Dao")
public class MMA004Dao extends YniAbstractDAO {

    /**
     * 기발급 원산지 증명서 정보 조회(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectCOIssueInfo(Map map) throws Exception {
        return (Map) this.selectByPk("MMA004.selectCOIssueInfo", map);
    }
    
    /**
     * 증명서 발급 사유 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectIssueReasonInfo(Map map) throws Exception {
        return (Map) this.selectByPk("MMA004.selectIssueReasonInfo", map);
    }
    
    /**
     * 수출자 및 생산자 조회(신규등록)
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public Map selectExportNProducerInfo(Map map) throws Exception {
    	String eflag = StringHelper.null2void(map.get("EXPORT_FLAG"));
        String flag = StringHelper.null2string(map.get("VN_EXPORT_FLAG"), eflag);
        String pid = null;

        if (flag.equals("D")) {
            pid = "MMA003";
        } else if (flag.equals("E")) {
            pid = "MMA004";
        }

        if (pid == null) {
            return null;
        }
        if (log.isDebugEnabled())
            log.debug("SQL ID = " + pid + ".selectExportNProducerInfo");

        return (Map) this.selectByPk(pid + ".selectExportNProducerInfo", map);
    }

    /**
     * 기발급 증명서 원산지 리스트(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public List selectCOOriginList(Map map) throws Exception {
        return this.list("MMA004.selectCOOriginList", map);
    }

    /**
     * 원산지 판정내역 조회(신규등록)
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectDeterminateList(Map map) throws Exception {
    	String eflag = StringHelper.null2void(map.get("EXPORT_FLAG"));
        String flag = StringHelper.null2string(map.get("VN_EXPORT_FLAG"), eflag);
        String pid = null;

        if (flag.equals("D")) {
            pid = "MMA203";
        } else if (flag.equals("E")) {
            pid = "MMA103";
        }

        if (pid == null) {
            return null;
        }
        if (log.isDebugEnabled())
            log.debug("SQL ID = " + pid + ".selectDeterminateList");

        return this.list(pid + ".selectDeterminateList", map);
    }

    /**
     * 서명권자 정보 조회(신규등록)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectSignatureInfo(Map map) throws Exception {
        return (Map) this.selectByPk("MMA004.selectSignatureInfo", map);
    }

    /**
     * 수출 원산지 학인서 인보이스 정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectInvoiceInfo(Map map) throws Exception {
        return (Map) this.selectByPk("MMA004.selectInvoiceInfo", map);
    }

    /**
     * 수출 원산지 학인서 인보이스 정보 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateInvoiceInfo(Map map) throws Exception {
        return this.update("MMA004.updateInvoiceInfo", map);
    }

    /**
     * 발급할 보고서 리스트 조회
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public List selectCoDocList(Map map) throws Exception {
        return this.list("MMA004.selectCoDocList", map);
    }
    
    /**
     * 발급일자 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateCoIssueDate(Map map) throws Exception {
        return this.update("MMA004.updateCoIssueDate", map);
    }
    
}
