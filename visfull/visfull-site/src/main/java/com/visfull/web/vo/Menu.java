package com.visfull.web.vo;

import java.io.Serializable;

public class Menu implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private Object[] cell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object[] getCell() {
        return cell;
    }

    public void setCell(Object[] cell) {
        this.cell = cell;
    }

}
