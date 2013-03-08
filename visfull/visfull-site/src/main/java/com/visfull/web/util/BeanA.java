package com.visfull.web.util;

import java.util.List;


public class BeanA {
	
	private Long id;
	
	public List<String> dataList;
	
	

	public BeanA() {
		super();
	}

	public BeanA(List<String> dataList) {
		super();
		this.dataList = dataList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getDataList() {
		return dataList;
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}

}
