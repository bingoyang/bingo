package com.visfull.bz.domain;

// Generated 2013-4-8 16:07:58 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Province generated by hbm2java
 */
@Entity
@Table(name = "province")
public class Province implements java.io.Serializable {

	private Integer id;
	private String name;
	private String code;
	private String countryId;

	public Province() {
	}

	public Province(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public Province(String name, String code, String countryId) {
		this.name = name;
		this.code = code;
		this.countryId = countryId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Code", nullable = false, length = 3)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CountryID", length = 3)
	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

}
