package com.visfull.bz.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.visfull.bz.dao.OperatorDao;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.util.BeanUtils;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class TestOperatorDao extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private OperatorDao operatorDao;
	
	
	@Test
	public void saveOperatorTest(){
		System.out.println("test ....");
		operatorDao.findByPK(1L);
	}
	
	@Test
	public void beanCopyTest(){
		BzServer bzServer = new BzServer();
		BzServer bzServer2 = new BzServer();
		bzServer.setId(10L);
		try {
			BeanUtils.copyProperties(bzServer, bzServer2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(bzServer.getId());
	}
}
