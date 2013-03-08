package com.visfull.web.service.impl;


/**
 *@author Administrator
 *2012-6-25 下午04:26:00
 *
 */
public class CacheServiceImpl implements CacheService {

    public String findThingsByCache(String name) {
        System.out.println("no cache ......!");
        return "cache "+name;
    }

}
