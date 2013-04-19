package com.visfull.bz.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "bz_position_info")
public class BzPositionInfo implements Serializable {
	
	private Long id;
	private String positionData;
	private Date createDate;
	
	
	
	public BzPositionInfo() {
		super();
	}
	public BzPositionInfo(Long id, String positionData, Date createDate) {
		super();
		this.id = id;
		this.positionData = positionData;
		this.createDate = createDate;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "position_data")
	public String getPositionData() {
		return positionData;
	}
	public void setPositionData(String positionData) {
		this.positionData = positionData;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
