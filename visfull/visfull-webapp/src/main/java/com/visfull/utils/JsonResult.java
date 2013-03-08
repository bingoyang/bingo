package com.visfull.utils;

import com.google.gson.annotations.Expose;

public class JsonResult {
    public static final String STATUS_SUCCESS = "1";
    public static final String STATUS_FAILED = "0";
    @Expose
    private String status;
    @Expose
    private String message;
    
    public JsonResult(boolean status, String message){
        this.status = status ? STATUS_SUCCESS : STATUS_FAILED;
        this.message = message;
    }

    public JsonResult(String status, String message){
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
