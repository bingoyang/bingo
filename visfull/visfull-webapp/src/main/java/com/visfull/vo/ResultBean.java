package com.visfull.vo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.visfull.bz.emnu.TargetType;

public class ResultBean {
	@Expose
	private String resultCode;
	@Expose
	private String message;
	@Expose
	private String userCode;
	@Expose
	private TargetType userType;
	@Expose
	private List<Object> dataObjects;
	
	public ResultBean(){
		
	}
	
	
	
	public ResultBean(String resultCode, String message, String userCode,
			TargetType userType, List<Object> dataObjects) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.userCode = userCode;
		this.userType = userType;
		this.dataObjects = dataObjects;
	}



	public ResultBean(String resultCode, String message,
			List<Object> dataObjects) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.dataObjects = dataObjects;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getDataObjects() {
		return dataObjects;
	}
	public void setDataObjects(List<Object> dataObjects) {
		this.dataObjects = dataObjects;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public TargetType getUserType() {
		return userType;
	}

	public void setUserType(TargetType userType) {
		this.userType = userType;
	}
}
