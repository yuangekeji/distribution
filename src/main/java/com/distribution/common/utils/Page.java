package com.distribution.common.utils;

import java.util.List;
import java.util.Map;

public class Page {
    public Page() {}
    protected Integer pageNo;
    protected Integer pageSize;
    protected Map parameterMap;
    protected List result = null;
    protected Integer totalCount = 0;
    protected Integer offset;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map parameterMap) {
        this.parameterMap = parameterMap;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置, 序号从0开始.
     */
    public Integer getOffset() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public Page(Integer pageNo, Integer pageSize, Map parameterMap){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.parameterMap = parameterMap;
    }

}