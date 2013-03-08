package com.visfull.web.vo;

import java.io.Serializable;
import java.util.List;

public class MenuData implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int total;
    private int page;
    private int records;
    private List<Menu> rows;
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getRecords() {
        return records;
    }
    public void setRecords(int records) {
        this.records = records;
    }
    public List<Menu> getRows() {
        return rows;
    }
    public void setRows(List<Menu> rows) {
        this.rows = rows;
    }
    
}
