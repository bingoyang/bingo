package com.visfull.system.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页器封装
 * 
 * @author LI.T
 * 
 */
public class Pageable<T> implements Serializable {

    private static final long serialVersionUID = 6217047497903290688L;

    /**
     * 每页大小
     */
    private int pageSize = 10;

    /**
     * 记录总条数
     */
    private int total;

    /**
     * 当前页号
     */
    private int pageNo = 1;

    /**
     * 本页数据
     */
    private List<T> data;

    public Pageable() {
        super();
    }

    public Pageable(int pageSize, int total, int pageNo, List<T> data) {
        super();
        this.pageSize = pageSize;
        this.total = total;
        this.pageNo = pageNo;
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
