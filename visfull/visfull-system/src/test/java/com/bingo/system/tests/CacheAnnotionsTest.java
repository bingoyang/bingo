package com.bingo.system.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.visfull.system.service.CacheService;
/**
 * 
 * CacheAnnotionsTest
 * 
 * Administrator
 * 2012-7-31 下午01:55:18
 * 
 * @version 1.0.0
 * 
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class CacheAnnotionsTest extends AbstractJUnit4SpringContextTests {
    
    @Autowired
    private CacheService cacheService;
    
    @Test
    public void testCacheAnnotions(){
        cacheService.cacheMethod1("aaaaaaaaaa");
        
        cacheService.cacheMethod1("aaaaaaaaaa");
    }

}
