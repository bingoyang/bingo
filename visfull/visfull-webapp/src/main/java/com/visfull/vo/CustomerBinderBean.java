package com.visfull.vo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.visfull.bz.domain.BzCustomerBinder.TargetType;

public class CustomerBinderBean {
	
	@Expose
	private Long targetId;
	@Expose
	private String targetName;
	@Expose
	private String targetCode;
	@Expose
	private TargetType targetType;
	@Expose
	private List<CustomerServiceBean> customerServiceInfo;

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
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

	public List<CustomerServiceBean> getCustomerServiceInfo() {
		return customerServiceInfo;
	}

	public void setCustomerServiceInfo(
			List<CustomerServiceBean> customerServiceInfo) {
		this.customerServiceInfo = customerServiceInfo;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	
}
