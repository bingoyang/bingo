package com.visfull.system.service.impl;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.visfull.system.service.CacheService;

/**
 * 
 * CacheServiceIml
 * 
 * Administrator
 * 2012-7-31 下午02:11:52
 * 
 * @version 1.0.0
 * 
 */
@Service
public class CacheServiceImpl implements CacheService {
    
    @Cacheable(cacheName="appCache")
    public String cacheMethod1(String arg1) {
        System.out.println("nocache....");
        return "fuck|"+arg1;
    }

}
