package com.visfull.vo;

import com.google.gson.annotations.Expose;

/*
 * 区域信息
 */
public class AreaInfo {
	@Expose
	private Long id;
	@Expose
	private String positionInfo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPositionInfo() {
		return positionInfo;
	}
	public void setPositionInfo(String positionInfo) {
		this.positionInfo = positionInfo;
	}
	
	
}
