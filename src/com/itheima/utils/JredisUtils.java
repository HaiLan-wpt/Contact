package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JredisUtils {
    private static JedisPool jedisPool;

    static {
        ResourceBundle rb=ResourceBundle.getBundle("jedis");
        String maxtotal = rb.getString("maxTotal");
        String maxWaitMillis = rb.getString("maxWaitMillis");
        String host = rb.getString("host");
        int port = Integer.parseInt(rb.getString("port"));


        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxWaitMillis(Long.parseLong(maxWaitMillis));
        config.setMaxTotal(Integer.parseInt(maxtotal));

        jedisPool=new JedisPool(config,host,port);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static JedisPool getJedisPool(){
        return jedisPool;
    }
}
