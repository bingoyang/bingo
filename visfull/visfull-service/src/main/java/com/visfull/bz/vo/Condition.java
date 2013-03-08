package com.visfull.bz.vo;

import java.util.Date;

import com.visfull.bz.domain.BzBlackWhite.PhoneType;
import com.visfull.bz.domain.BzOperator.OpStatus;
import com.visfull.bz.emnu.Gender;
import com.visfull.bz.emnu.TargetType;

public class Condition {
	
	private long id;
    
    private Date startDate;
    
    private Date endDate;
    
    private PhoneType phoneType;
    
    private String phone;
    
    private String opName;
    
    private OpStatus status;
    
    private Gender gender;
    
    private TargetType targetType;
    
    private String targetCode;
    
    private Long catalogId;
    
    public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public OpStatus getStatus() {
		return status;
	}

	public void setStatus(OpStatus status) {
		this.status = status;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	
	
}
