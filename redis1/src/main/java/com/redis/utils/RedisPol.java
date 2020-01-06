package com.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**Redis连接池
 * @author Administrator
 * @description
 * @date 2020/1/6
 */
public class RedisPol {

    private static String host = "127.0.0.1";//服务器ip地址
    private static String passWord = "12345";//redis服务器密码
    private static int port = 6379;//服务器端口号

//    maxActive：控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；如果赋值为-1，则表示不限制；
//    如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted（耗尽）。
    private static Integer MAX_TOTAL  = 8;
//    maxIdle：控制一个pool最多有多少个状态为idle(空闲)的jedis实例；
    private static Integer MAX_IDLE  = 8;
//    maxWait：表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
    private static Integer maxWait = 10000;
    private static Integer TIMEOUT = 10000;
    //在borrow(用)一个jedis实例时，是否提前进行validate(验证)操作；如果为true，则得到的jedis实例均是可用的
    private static Boolean TEST_ON_BORROW = true;

    private  static JedisPool jedisPool  = null;
    //初始化redis连接池
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //设置最大连接数
            config.setMaxTotal(MAX_TOTAL);
            //设置最大空闲连接个数
            config.setMaxIdle(MAX_IDLE);
            //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config,host,port,TIMEOUT,passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取jedis实例
    public synchronized static Jedis getJedis(){
        try {
            if(jedisPool != null){
                Jedis jedis = jedisPool.getResource();
                return jedis;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
