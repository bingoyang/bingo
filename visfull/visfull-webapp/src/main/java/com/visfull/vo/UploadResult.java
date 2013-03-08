package com.visfull.vo;

import com.google.gson.annotations.Expose;

public class UploadResult {
	
	@Expose
	private String statusCode;
	@Expose
	private String message;
	@Expose
	private String fileUrl;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	
}
