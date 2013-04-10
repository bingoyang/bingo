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
@Table(name="bz_community")
public class BzCommunity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expose
	private Integer id;
	@Expose
	private String communityName;
	@Expose
	private String communityCode;
	@Expose
	private Integer areaId;
	@Expose
	private Date createDate;
	
	public BzCommunity() {
		super();
	}
	public BzCommunity(Integer id, String communityName, String communityCode,
			Integer areaId, Date createDate) {
		super();
		this.id = id;
		this.communityName = communityName;
		this.communityCode = communityCode;
		this.areaId = areaId;
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
	
	@Column(name = "community_name", nullable = false)
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	@Column(name = "community_code", nullable = false)
	public String getCommunityCode() {
		return communityCode;
	}
	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}
	
	@Column(name = "area_id", nullable = false)
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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
