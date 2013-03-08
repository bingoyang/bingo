package com.visfull.vo;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.visfull.bz.domain.BzPoster;

public class CustomerServiceBean {
	
	@Expose
	private Long customerId;
	@Expose
	private String customerName;
	@Expose
	private String customerPhone;
	@Expose
	private String serviceInfo;
	@Expose
	private List<BzPoster> posters; 
	@Expose
	private Long duration;
	@Expose
	private Date serviceDate;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getServiceInfo() {
		return serviceInfo;
	}
	public void setServiceInfo(String serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public List<BzPoster> getPosters() {
		return posters;
	}
	public void setPosters(List<BzPoster> posters) {
		this.posters = posters;
	}
}
