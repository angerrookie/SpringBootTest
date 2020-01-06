package com.springboot.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @description
 * @date 2020/1/3
 */
@Repository
public class UserRedis  {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 功能描述: 
     * @Param: [key, time, user]
     * @Return: void
     * @Author: Administrator
     * @Date: 2020/1/3 15:54
     */
    public void add(String key, Long time, User user){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(user),time, TimeUnit.MINUTES);
    }
    /**
     * 功能描述: 
     * @Param: [key, time, users]
     * @Return: void
     * @Author: Administrator
     * @Date: 2020/1/3 15:54
     */
    public void add(String key, Long time, List<User> users){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(users),time, TimeUnit.MINUTES);
    }
    /**
     * 功能描述: 
     * @Param: [key]
     * @Return: com.springboot.pojo.User
     * @Author: Administrator
     * @Date: 2020/1/3 15:54
     */
    public User get(String key){
        Gson gson = new Gson();
        User user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(userJson)){
            user = gson.fromJson(userJson,User.class);
        }
        return user;
    }
    /**
     * 功能描述: 
     * @Param: [key]
     * @Return: java.util.List<com.springboot.pojo.User>
     * @Author: Administrator
     * @Date: 2020/1/3 15:54
     */
    public List<User> getList(String key){
        Gson gson = new Gson();
        List<User> list = null;
        String listJson = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(listJson)){
            list = gson.fromJson(listJson,new TypeToken<List<User>>(){}.getType());
        }
        return list;
    }
    /**
     * 功能描述: 
     * @Param: [key]
     * @Return: void
     * @Author: Administrator
     * @Date: 2020/1/3 16:43
     */
    public void delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
