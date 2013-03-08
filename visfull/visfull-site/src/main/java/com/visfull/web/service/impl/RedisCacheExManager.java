package com.visfull.web.service.impl;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 *@author Administrator
 *2012-6-25 下午04:42:47
 *
 */
public class RedisCacheExManager extends AbstractCacheManager {

    private RedisCacheManager redisCacheManager;
    
    public RedisCacheExManager() {
        super();
    }
    
    public RedisCacheExManager(RedisCacheManager redisCacheManager) {
        super();
        this.redisCacheManager = redisCacheManager;
    }

    public RedisCacheManager getRedisCacheManager() {
        return redisCacheManager;
    }

    public void setRedisCacheManager(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        
        redisCacheManager.getCache("default");
        Collection<String> names = redisCacheManager.getCacheNames();
        Collection<Cache> caches = new LinkedHashSet<Cache>(names.size());
        for (String  name : names) {
            caches.add(redisCacheManager.getCache(name));
        }
        return caches;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        if (cache == null) {
            Cache redisCache = this.redisCacheManager.getCache(name);
                addCache(redisCache);
        }
        return cache;
    }
}
