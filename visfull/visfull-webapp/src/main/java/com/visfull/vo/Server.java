package com.visfull.vo;

import com.google.gson.annotations.Expose;

/*
 * 服务人员
 */
public class Server {
	
	@Expose
	private Long id;
	@Expose
	private String name;
	@Expose
	private String phoneNo;
	@Expose
	private long spId;
	@Expose
	private String skillDescribe;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getSkillDescribe() {
		return skillDescribe;
	}
	public void setSkillDescribe(String skillDescribe) {
		this.skillDescribe = skillDescribe;
	}
	public long getSpId() {
		return spId;
	}
	public void setSpId(long spId) {
		this.spId = spId;
	}
	
	
	
}
