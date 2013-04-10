package com.visfull.bz.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="bz_area")
public class BzArea implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private Integer id;
	@Expose
	private String areaName;
	@Expose
	private String areaCode;
	@Expose
	private Integer countyId;
	@Expose
	private String countyName;
	@Expose
	private Date createDate;
	
	public BzArea() {
		super();
	}
	public BzArea(Integer id, String areaName, String areaCode,
			Integer countyId, Date createDate) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.areaCode = areaCode;
		this.countyId = countyId;
		this.createDate = createDate;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "area_name", nullable = false)
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Column(name = "area_code", nullable = false)
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Column(name = "county_id", nullable = false)
	public Integer getCountyId() {
		return countyId;
	}
	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}
	@Column(name = "county_name", nullable = false)
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
