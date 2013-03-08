package com.bingo.system.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.visfull.system.dao.UserRoleDao;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class AuthRoleTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Test
	public void testUserRole(){
		
		
		
	}

}
