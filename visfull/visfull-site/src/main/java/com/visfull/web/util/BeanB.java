package com.visfull.web.util;

import java.util.List;

public class BeanB {
	
	public List<String> dataList;
	
	public BeanA beanA;

	public BeanB(List<String> dataList) {
		super();
		this.dataList = dataList;
		beanA = new BeanA(dataList);
	}
	

}
