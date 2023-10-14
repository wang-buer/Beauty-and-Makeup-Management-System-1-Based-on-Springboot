package com.mh.util;

import com.mh.product.mapper.ProMapper;
import org.apache.ibatis.session.RowBounds;

/**
 * @author ljl
 * 分页插件
 */
public class PageInfo extends RowBounds {
    //limit参数
    public PageInfo(int pageNo,int pageRows) {
        super(pageNo, pageRows);
    }
    public PageInfo(int offset,int limit, String orderStr) {
        super(offset, limit);
        this.orderStr=orderStr;
    }
    private Integer curr;
    private Integer pageSize;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurr() {
        return curr;
    }

    public void setCurr(Integer curr) {
        this.curr = curr;
    }

    private Long total = 0l;
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    private String orderStr = "";
    public String getOrderStr() {
        return orderStr;
    }
    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    private int totalpagecount;

    public int getTotalpagecount() {
        return totalpagecount;
    }

    public void setTotalpagecount(int p) {
        if (p%this.pageSize==0){
            this.totalpagecount=p/this.pageSize;
        }else if(p%this.pageSize>0){
            this.totalpagecount=p/this.pageSize+1;
        }else{
            this.totalpagecount=0;
        }
    }
}
