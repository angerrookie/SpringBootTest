package com.redis.utils;

import redis.clients.jedis.Jedis;

/**
 * @author Administrator
 * @description
 * @date 2020/1/6
 */
public class RedisConfig {

    private static Jedis jedis;

    public static Jedis getJedis() {
        //连接到本地redis服务器
        jedis = new Jedis("127.0.0.1",6379);
//        验证用户密码
        jedis.auth("12345");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        return jedis;
    }
}
