package com.visfull.bz.tests;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import com.visfull.bz.dao.BlackWhiteDao;
import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.domain.BzBlackWhite.PhoneType;
import com.visfull.bz.service.BzInfoService;
import com.visfull.bz.vo.Pageable;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class TestBlackWhiteDao extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private BlackWhiteDao blackWhiteDao;
	
	@Autowired
	private BzInfoService bzInfoService;
	
	@Test
	public void testBlackWhiteSave(){
		BzBlackWhite blackWhite = new BzBlackWhite();
		blackWhite.setPhone("15801239457");
		blackWhite.setType(PhoneType.BLACK);
		blackWhite.setCreateDate(new Date());
		blackWhiteDao.save(blackWhite);
		
		List<BzBlackWhite> blackWhites = blackWhiteDao.findBlackWhiteAll();
		Assert.isTrue(blackWhites.size() > 0);
	}
	
	@Test
	public void testBlackWhiteService(){
	       BzBlackWhite blackWhite = new BzBlackWhite();
	        blackWhite.setPhone("15801239457");
	        blackWhite.setType(PhoneType.BLACK);
	        blackWhite.setCreateDate(new Date());
	        try {
                bzInfoService.addBlackWhite(blackWhite);
            } catch (Exception e) {
                e.printStackTrace();
            }
	}
	
	@Test
	public void testFindBlackWhitePage(){
	    
	    Pageable<BzBlackWhite> page = new Pageable<BzBlackWhite>();
	    page = bzInfoService.findBlackWhiteByPage(null, page);
	    System.out.println(page.getData().size());
	}
	

}
