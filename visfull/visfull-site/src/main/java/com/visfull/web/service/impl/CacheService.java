package com.visfull.web.service.impl;

import org.springframework.cache.annotation.Cacheable;

/**
 *@author Administrator
 *2012-6-25 下午04:14:38
 *
 */
public interface CacheService {
    
    @Cacheable(value="default")
    String findThingsByCache(String name);

}
