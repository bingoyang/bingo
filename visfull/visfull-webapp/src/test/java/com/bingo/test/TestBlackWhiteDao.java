package com.bingo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.google.gson.reflect.TypeToken;
import com.visfull.bz.domain.BzCallRecord;
import com.visfull.bz.domain.BzCustomer;
import com.visfull.bz.domain.BzCustomerBinder;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.emnu.CallType;
import com.visfull.bz.emnu.Gender;
import com.visfull.facade.service.FacadeService;
import com.visfull.utils.JsonUtils;
import com.visfull.vo.CustomerBinderBean;
import com.visfull.vo.CustomerServiceBean;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class TestBlackWhiteDao extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private FacadeService facadeService;
	
	@Test
	public void testFacadeService(){
		
		BzCustomer customer = new BzCustomer();
		customer.setId(10L);
		customer.setName("刘德华");
		customer.setAge("11");
		customer.setGender(Gender.MALE);
		customer.setBindPhone("123123123214");
		customer.setPwd("123456");
		customer.setResidence("111111");
		customer.setCreateDate(new Date());
		
		String jsonString = JsonUtils.toJson(customer);
		
		System.out.println(jsonString);
	}
	
	@Test
	public void testGsonToBean(){
		CustomerBinderBean customerBinderBean = new CustomerBinderBean();
		customerBinderBean.setTargetId(10L);
		customerBinderBean.setTargetName("服务人员名称");
		customerBinderBean.setTargetType(BzCustomerBinder.TargetType.SERVER);
		List<CustomerServiceBean> customerServiceInfo = new ArrayList<CustomerServiceBean>();
		for (int i = 0; i < 3; i++) {
			CustomerServiceBean serviceBean = new CustomerServiceBean();
			serviceBean.setCustomerId(new Long(i+10));
			serviceBean.setCustomerName("客户"+(i+1));
			serviceBean.setCustomerPhone("客户电话号码");
			serviceBean.setServiceInfo("客户服务相关描述");
			serviceBean.setServiceDate(new Date());
			customerServiceInfo.add(serviceBean);
		}
		customerBinderBean.setCustomerServiceInfo(customerServiceInfo);
		String jsonString = JsonUtils.toJson(customerBinderBean);
		CustomerBinderBean binderBean = JsonUtils.fromJson(jsonString, CustomerBinderBean.class);
		System.out.println(binderBean.toString());
		System.out.println("ss");
	}
	
	@Test
	public void testRecord(){
		List<BzCallRecord> records = new ArrayList<BzCallRecord>();
		for (int i = 0; i < 6; i++) {
			BzCallRecord record = new BzCallRecord();
				record.setCallingNum("callingnum"+i);
				record.setCalledNum("callednum"+i);
				record.setCallingTime(System.currentTimeMillis());
				record.setCallType(CallType.CALLIN);
				record.setCreateDate(new Date());
			records.add(record);
		}
		String jsonsString = JsonUtils.toJson(records);
		
		List<BzCallRecord> dataBzCallRecords = JsonUtils.fromJson(jsonsString, new TypeToken<List<BzCallRecord>>(){}.getType());
		
		System.out.println(dataBzCallRecords.size());
	}
}
