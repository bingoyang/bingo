package com.visfull.vo;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.visfull.bz.domain.BzCustomerBinder.TargetType;

public class CustomerBinder {
	
	@Expose
	private Long customerId;
	@Expose
	private String customerName;
	@Expose
	private String customerPhone;
	@Expose
	private Long targetId;
	@Expose
	private String targetCode;
	@Expose
	private String targetName;
	@Expose
	private TargetType targetType;
	@Expose
	private Date createDate;
	
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
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	public String getTargetCode() {
		return targetCode;
	}
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public TargetType getTargetType() {
		return targetType;
	}
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
