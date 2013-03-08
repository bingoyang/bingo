package com.bingo.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 *@author Administrator
 *2012-5-21 上午11:09:17
 *
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext-redis.xml" })
public class RedisTests extends AbstractJUnit4SpringContextTests {

@Autowired
private ShardedJedisPool shardedJedisPool;
@SuppressWarnings("rawtypes")
@Autowired
private RedisTemplate redisTemplate;

@Test
public void testRedisConnection(){
    
    for (int i = 0; i < 1000; i++) {
        
        ShardedJedis shardedJedis =  shardedJedisPool.getResource();
        
        shardedJedis.set("3"+i, "5"+i);
        System.out.println(i);
        shardedJedisPool.returnResource(shardedJedis);
    }
    
}

@SuppressWarnings("unchecked")
@Test
public void testRedisTemplate(){
    for (int i = 0; i < 1000; i++) {
     redisTemplate.opsForValue().set("123"+i,"123"+i);
     System.out.println(i);
    }
}
}
