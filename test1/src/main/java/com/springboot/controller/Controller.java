package com.springboot.controller;

import com.springboot.pojo.User;
import com.springboot.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @date 2019/12/26
 */
@RestController
public class Controller {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String find(Integer userId) {
        System.out.println("findAll........");
        List<User> list = service.findAll();
        for (User user : list) {
            System.out.println(user.getUserId() + "---" + user.getUserName());
        }
        return "findAll";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(User user) {
        service.add(user);
        return "save";
    }
}
