package com.yni.fta.mm.pop;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 이미지 보기
 * 
 * @author carlos
 *
 */
@Controller
public class MMA026Ctrl extends YniAbstractController {

    private static final Logger logger = LoggerFactory.getLogger(MMA026Ctrl.class);

    @Resource(name = "mmA017")
    private MMA017 mmA017;

    /**
     * 이미지정보 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA026_01")
    public ModelAndView or5006_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A026_01", dataMap);
    }

    /**
     * 공통 이미지정보 조회
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/mm/pop/mmA026_01/selectImageInfo")
    public byte[] selectImageInfo(HttpServletRequest req, HttpServletResponse response, DataMap dataMap) throws Exception  {
        byte[] bImage = null;

        return bImage;
    }

    /**
     * 
     * MSSQL-Image, Oracle-Blob 필드 byte[] 배열 반환
     * 
     * 
     * @author carlos
     * 
     * @param imageMap
     * @param imageKey
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private byte[] getImageByte(Map imageMap, String imageKey) throws Exception {
        if (imageMap == null || imageMap.size() == 0 || imageKey == null || imageKey.isEmpty()) {
            return null;
        }
        
        byte[] bImage = null;

        if (imageMap != null) {
            try {
                bImage = (byte[]) imageMap.get(imageKey);
            } catch (Exception e) {
                logger.debug("getImageByte() Exception(byte)");
            }
            
            if (bImage == null) {
                try {
                    String db = StringHelper.null2void(this.properties.get("application.db.type"));
                    
                    if (db.equals("MSSQL") || db.equals("POSTGRESQL")) {
                    	bImage = (byte[]) imageMap.get(imageKey);
                    } else {
                        Blob blob = (Blob) imageMap.get(imageKey); // 오라클에서 적용
                        bImage = OracleTypeHelper.getBytes(blob);
                    }
                } catch (Exception e) {
                    logger.debug("getImageByte() Exception(blob)");
                }
            }
        }

        return bImage;
    }
}
