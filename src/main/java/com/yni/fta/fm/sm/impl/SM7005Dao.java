package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 시스템관리 > 인터페이스 > 항목 관리
 * 
 * @author carlos
 *
 */
@Repository("sm7005Dao")
public class SM7005Dao extends YniAbstractDAO {

    /**
     * 인터페이스 항목 마스터 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectInterfaceItemMstList(Map map) throws Exception {
        return this.listWithRowPaging("SM7005.selectInterfaceItemMstList", map);
    }
    
    /**
     * 인터페이스 항목 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectcInterfaceDupCheck(Map map) throws Exception {
        return (Map) this.selectByPk("SM7005.selectcInterfaceDupCheck", map);
    }
    
    /**
     * INTERFACE_ITEM_MST_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceItemMstId(Map map) throws Exception {
        return (Map) this.selectByPk("SM7005.selectLastInterfaceItemMstId", map);
    }
    
    /**
     * 인터페이스 항목 마스터 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectInterfaceItemMstInfo(Map map) throws Exception {
        return (Map) this.selectByPk("SM7005.selectInterfaceItemMstInfo", map);
    }
    
    /**
     * 인터페이스 항목 마스터 콤보용 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectInterfaceItemMstCombo(Map map) throws Exception {
        return this.list("SM7005.selectInterfaceItemMstCombo", map);
    }
    
    /**
     * 인터페이스 항목 마스터 신규등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertInterfaceItemMstInfo(Map map) throws Exception {
        return this.update("SM7005.insertInterfaceItemMstInfo", map);
    }

    /**
     * 인터페이스 항목 마스터 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateInterfaceItemMstInfo(Map map) throws Exception {
        return this.update("SM7005.updateInterfaceItemMstInfo", map);
    }
    
    /**
     * 인터페이스 항목 상세등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertExcuteInterfaceItemDtlBatch(List list) throws Exception {
        return this.executeBatch("SM7005.insertExcuteInterfaceItemDtlBatch", list);
    }
    
    /**
     * 인터페이스 항목 마스터 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteInterfaceItemMstInfo(Map map) throws Exception {
        log.debug("deleteInterfaceItemMstInfo = " +  map.toString());
        return this.delete("SM7005.deleteInterfaceItemMstInfo", map);
    }
    
    /**
     * 인터페이스 항목 디테일 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectInterfaceItemDtlList(Map map) throws Exception {
        return this.listWithRowPaging("SM7005.selectInterfaceItemDtlList", map);
    }
    
    /**
     * INTERFACE_ITEM_DTL_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceItemDtlId(Map map) throws Exception {
        return (Map) this.selectByPk("SM7005.selectLastInterfaceItemDtlId", map);
    }
    
    /**
     * 인터페이스 항목 디테일 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectInterfaceItemDtlInfo(Map map) throws Exception {
        return (Map) this.selectByPk("SM7005.selectInterfaceItemDtlInfo", map);
    }
    
    /**
     * 인터페이스 항목 디테일 신규등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertInterfaceItemDtlInfo(Map map) throws Exception {
        Object rData = this.insert("SM7005.insertInterfaceItemDtlInfo", map);
        return 1;
    }

    /**
     * 인터페이스 항목 디테일 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateInterfaceItemDtlInfo(Map map) throws Exception {
        return this.update("SM7005.updateInterfaceItemDtlInfo", map);
    }
    
    /**
     * 인터페이스 항목 디테일 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteInterfaceItemDtlInfo(Map map) throws Exception {
        return this.delete("SM7005.deleteInterfaceItemDtlInfo", map);
    }
    
    /**
     * 인터페이스 항목 디테일 전체 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteInterfaceItemDtlAllInfo(Map map) throws Exception {
        return this.delete("SM7005.deleteInterfaceItemDtlAllInfo", map);
    }
    
}
