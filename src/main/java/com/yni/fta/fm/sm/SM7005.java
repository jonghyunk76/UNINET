package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 시스템관리 > 인터페이스 > 항목 관리
 * 
 * @author carlos
 *
 */
public interface SM7005 {

    /**
     * 인터페이스 항목 마스터 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectInterfaceItemMstList(Map map) throws Exception;
    
    /**
     * 인터페이스 항목 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectcInterfaceDupCheck(Map map) throws Exception;
    
    /**
     * INTERFACE_ITEM_MST_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceItemMstId(Map map) throws Exception;
    
    /**
     * 인터페이스 항목 마스터 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectInterfaceItemMstInfo(Map map) throws Exception;

    /**
     * 인터페이스 항목 마스터 콤보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectInterfaceItemMstCombo(Map map) throws Exception;
    
    public int insertInterfaceItemMstInfo(Map map) throws Exception;

    public int updateInterfaceItemMstInfo(Map map) throws Exception;
    
    public int deleteInterfaceItemMstInfo(Map map) throws Exception;
    
    /**
     * 인터페이스 항목 디테일 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectInterfaceItemDtlList(Map map) throws Exception;
    
    /**
     * INTERFACE_ITEM_DTL_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceItemDtlId(Map map) throws Exception;
    
    /**
     * 인터페이스 항목 디테일 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectInterfaceItemDtlInfo(Map map) throws Exception;

    public int insertInterfaceItemDtlInfo(Map map) throws Exception;

    public int updateInterfaceItemDtlInfo(Map map) throws Exception;
    
    public int deleteInterfaceItemDtlInfo(Map map) throws Exception;
    
    public int deleteInterfaceItemDtlAllInfo(Map map) throws Exception;
}
