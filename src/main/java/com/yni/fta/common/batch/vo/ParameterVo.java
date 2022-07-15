package com.yni.fta.common.batch.vo;

import java.util.Map;

import com.yni.fta.common.Consistent;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.util.StringHelper;

/**
 * 외부 기관계 시스템과의 인터페이스를 위한 파라메터를 저장하는 클래스
 * 
 * @author jonghyun3.kim
 *
 */
public class ParameterVo {

    private DataMap paramMap = new DataMap();

    // 전송ID
    public String getTransId() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_TRANS_ID));
    }

    public void setTransId(String transid) {
        this.paramMap.put(Consistent.IF_PARAMETER_TRANS_ID, transid);
    }
    
    public String getCompanyCode() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_COMPANY_CD));
    }

    public void setCompanyCode(String ifParameterCompanyCode) {
        this.paramMap.put(Consistent.IF_PARAMETER_COMPANY_CD, ifParameterCompanyCode);
    }
    
    public String getFunctionName() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_FUNCTION_NAME));
    }

    public void setFunctionName(String functionName) {
        this.paramMap.put(Consistent.IF_PARAMETER_FUNCTION_NAME, functionName);
    }
    
    public String getFromDate() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_FROM_DATE));
    }

    public void setFromDate(String ifParameterFromDate) {
        this.paramMap.put(Consistent.IF_PARAMETER_FROM_DATE, ifParameterFromDate);
    }

    public String getToDate() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_TO_DATE));
    }

    public void setToDate(String ifParameterToDate) {
        this.paramMap.put(Consistent.IF_PARAMETER_TO_DATE, ifParameterToDate);
    }

    public String getDivisionCode() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_DIVISION_CD));
    }

    public void setDivisionCode(String ifParameterDivisionCode) {
        this.paramMap.put(Consistent.IF_PARAMETER_DIVISION_CD, ifParameterDivisionCode);
    }

    public String getIfCode() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_IF_CD));
    }

    public void setIfCode(String ifParameterIfCode) {
        this.paramMap.put(Consistent.IF_PARAMETER_IF_CD, ifParameterIfCode);
    }

    public String getItemCode() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_ITEM_CD));
    }

    public void setItemCode(String ifParameterItemCode) {
        this.paramMap.put(Consistent.IF_PARAMETER_ITEM_CD, ifParameterItemCode);
    }

    public String getBatchFlag() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_BATCH_FLAG));
    }

    public void setBatchFlag(String ifParameterBatchFlag) {
        this.paramMap.put(Consistent.IF_PARAMETER_BATCH_FLAG, ifParameterBatchFlag);
    }

    public String getVendorCode() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_VENDOR_CD));
    }

    public void setVendorCode(String ifParameterVendorCode) {
        this.paramMap.put(Consistent.IF_PARAMETER_VENDOR_CD, ifParameterVendorCode);
    }
    
    public String getClVendorCode() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_CL_VENDOR_CD));
    }

    public void setClVendorCode(String ifParameterVendorCode) {
        this.paramMap.put(Consistent.IF_PARAMETER_CL_VENDOR_CD, ifParameterVendorCode);
    }
    
    public String getUserId() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_USER_ID));
    }

    public void setUserId(String ifParameterVendorCode) {
        this.paramMap.put(Consistent.IF_PARAMETER_USER_ID, ifParameterVendorCode);
    }
    
    public String getBusinessNo() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_BUSINESS_NO));
    }

    public void setBusinessNo(String lang) {
        this.paramMap.put(Consistent.IF_PARAMETER_BUSINESS_NO, lang);
    }
    
    public String getUrl() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_URL));
    }

    public void setUrl(String lang) {
        this.paramMap.put(Consistent.IF_PARAMETER_URL, lang);
    }
    
    public String getRequestType() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_REQUEST_TYPE));
    }

    public void setRequestType(String type) {
        this.paramMap.put(Consistent.IF_PARAMETER_REQUEST_TYPE, type);
    }
    
    public String getTomsFtaCertKey() {
        return StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_TOMS_FTA_CERT_KEY));
    }

    public void setTomsFtaCertKey(String lang) {
        this.paramMap.put(Consistent.IF_PARAMETER_TOMS_FTA_CERT_KEY, lang);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setPutAll(Map map) {
        this.paramMap.putAll(map);
    }

    @SuppressWarnings("rawtypes")
    public Map getMap() {
        return ((DataMap) this.paramMap).getMap();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();

        if (!StringHelper.isNull(this.getTransId())) buf.append(" Transaction Number=" + this.getTransId());
        if (!StringHelper.isNull(this.getIfCode())) buf.append(" Batch ID=" + this.getIfCode());
        if (!StringHelper.isNull(this.getCompanyCode())) buf.append(" Company Code=" + this.getCompanyCode());
        if (!StringHelper.isNull(this.getDivisionCode())) buf.append(" Division Code=" + this.getDivisionCode());
        if (!StringHelper.isNull(this.getItemCode())) buf.append(" Product Code=" + this.getItemCode());
        if (!StringHelper.isNull(this.getFromDate())) buf.append(" From Date=" + this.getFromDate());
        if (!StringHelper.isNull(this.getToDate())) buf.append(" To Date=" + this.getToDate());
        if (!StringHelper.isNull(this.getUserId())) buf.append(" User Id=" + this.getUserId());
        if (!StringHelper.isNull(this.getUserId())) buf.append(" URL=" + this.getUrl());
        if (!StringHelper.isNull(this.getUserId())) buf.append(" License key=" + this.getTomsFtaCertKey());

        return buf.toString();
    }

    public void clear() {
        this.paramMap.clear();
    }
}
