package com.visfull.vo;

import java.util.List;

import com.google.gson.annotations.Expose;

/*
 * 运营商
 */
public class Operator {
	
	@Expose
	private Long id;
	@Expose
	private String code;
	@Expose
	private String name;
	@Expose
	private String linkMan;
	@Expose
	private String phoneNo;
	@Expose
	private String serviceIntroduce;
	private List<AreaInfo> areaInfos;
	private List<ServiceProvider> serviceProviders;
	
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
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getServiceIntroduce() {
		return serviceIntroduce;
	}
	public void setServiceIntroduce(String serviceIntroduce) {
		this.serviceIntroduce = serviceIntroduce;
	}
	public List<AreaInfo> getAreaInfos() {
		return areaInfos;
	}
	public void setAreaInfos(List<AreaInfo> areaInfos) {
		this.areaInfos = areaInfos;
	}
	public List<ServiceProvider> getServiceProviders() {
		return serviceProviders;
	}
	public void setServiceProviders(List<ServiceProvider> serviceProviders) {
		this.serviceProviders = serviceProviders;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
