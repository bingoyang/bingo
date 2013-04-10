package com.visfull.bz.domain;

// Generated 2012-11-9 15:20:16 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.google.gson.annotations.Expose;

/**
 * BzServiceprovider generated by hbm2java
 */
@SuppressWarnings("serial")
@DynamicUpdate(true)
@Entity
@Table(name = "bz_serviceprovider")
public class BzServiceprovider implements java.io.Serializable {
	
	@Expose
	private long id;
	@Expose
	private String serviceCode;
	@Expose
	private String serviceName;
	private String servicePwd;
	@Expose
	private String linkMan;
	@Expose
	private String poster;
	@Expose
	private String homePage;
	@Expose
	private String serviceHours;
	@Expose
	private String phoneNo;
	@Expose
	private String consumeStandard;
	@Expose
	private String serviceIntroduce;
	@Expose
	private String serviceMotto;
	@Expose
	private long opId;
	@Expose
	private String opName;
	@Expose
	private SpStatus status;
	@Expose
	private Date initDate;
	@Expose
	private Date joinDate;
	@Expose
	private Date modifyTime;
	@Expose
	private Date createDate;
	@Expose
	private Long catalogId;
	@Expose
	private String catalogName;
	@Expose
	private Integer communityId;
	@Expose
	private String communityName;

	public BzServiceprovider() {
	}

	public BzServiceprovider(long id, Date modifyTime) {
		this.id = id;
		this.modifyTime = modifyTime;
	}

	public BzServiceprovider(long id, String serviceCode, String serviceName,
			String linkMan, String phoneNo, String serviceIntroduce,
			SpStatus status, Date modifyTime, Date createDate) {
		this.id = id;
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.linkMan = linkMan;
		this.phoneNo = phoneNo;
		this.serviceIntroduce = serviceIntroduce;
		this.status = status;
		this.modifyTime = modifyTime;
		this.createDate = createDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "service_code", length = 32)
	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@Column(name = "service_name")
	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Column(name = "link_man", length = 32)
	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "phone_no", length = 1024)
	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "service_introduce", length = 65535)
	public String getServiceIntroduce() {
		return this.serviceIntroduce;
	}

	public void setServiceIntroduce(String serviceIntroduce) {
		this.serviceIntroduce = serviceIntroduce;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10)
	public SpStatus getStatus() {
		return this.status;
	}

	public void setStatus(SpStatus status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time", nullable = false, length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "op_id")
	public long getOpId() {
		return opId;
	}

	public void setOpId(long opId) {
		this.opId = opId;
	}
	
	
	@Column(name = "service_pwd")
	public String getServicePwd() {
		return servicePwd;
	}

	public void setServicePwd(String servicePwd) {
		this.servicePwd = servicePwd;
	}
	
	@Column(name = "poster")
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	@Column(name = "home_page")
	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	@Column(name = "service_hours")
	public String getServiceHours() {
		return serviceHours;
	}

	public void setServiceHours(String serviceHours) {
		this.serviceHours = serviceHours;
	}
	
	@Column(name = "consume_standard")
	public String getConsumeStandard() {
		return consumeStandard;
	}

	public void setConsumeStandard(String consumeStandard) {
		this.consumeStandard = consumeStandard;
	}
	
	@Column(name = "service_motto")
	public String getServiceMotto() {
		return serviceMotto;
	}

	public void setServiceMotto(String serviceMotto) {
		this.serviceMotto = serviceMotto;
	}
	
	@Column(name = "init_date")
	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	
	@Column(name = "join_date")
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Column(name = "catalog_id")
	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	@Column(name = "catalog_name")
	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	
	@Column(name = "community_id")
	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	@Column(name = "community_name")
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	@Column(name = "op_name")
	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public enum SpStatus implements IEnumDisplay {
		NORMAL("正常"),STOP("停用");
		private String msg;
		private SpStatus(String msg){
			this.msg = msg;
		}
		
		public String getDisplayName() {
			return msg;
		}
	}
}
