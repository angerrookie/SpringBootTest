package com.springboot.redis;

import com.springboot.pojo.User;
import com.springboot.service.UserRedis;
import com.springboot.test.RedisTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    UserRedis userRedis;
    @BeforeEach
    public void setup(){
        User user = new User();
        user.setUserName("lll");
        user.setPassWord("123");
        user.setAddress("222");

        userRedis.add(this.getClass().getName()+"userName:user",10L,user);
    }
    @Test
    void contextLoads() {
        System.out.println("------");
        User user = userRedis.get(this.getClass().getName()+"userName:user");
        System.out.println(user);
    }

}
