package com.springboot.repository;

import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @date 2019/12/26
 */
@Service("service")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    //   @Transactional
    public void add(User user) {
        System.out.println("开始......");
        userRepository.save(user);
        System.out.println("...........");
        throw new RuntimeException("异常");
    }
}
