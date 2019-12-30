package com.springboot.controller;

import com.springboot.pojo.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @date 2019/12/30
 */
@RestController
public class UserController {
    @Autowired
    @Qualifier("service")
    private UserService userService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public String findAll(){
        List<User> list = userService.findAll();
        for (User user : list) {
            System.out.println(user.getUserId()+"---"+user.getUserName());
        }
        return "findAll";
    }
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public String findById(Integer userId){
        User user = userService.findById(userId);
        System.out.println(user.getUserId()+"---"+user.getUserName());
        return "findById";
    }
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public String insert(User user1){
       System.out.println( userService.insert(user1));
        List<User> list = userService.findAll();
        for (User user : list) {
            System.out.println(user.getUserId()+"---"+user.getUserName());
        }
        return "insert";
    }
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateById(User user){
        System.out.print(user.getUserId()+"---"+user.getUserName()+"--->");
        System.out.println(userService.updateById(user));
        User user1 = userService.findById(user.getUserId());
        System.out.println(user1.getUserId()+"---"+user1.getUserName());
        return "update";
    }
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteById(Integer userId){
        System.out.println(userService.deleteById(userId));
        List<User> list = userService.findAll();
        for (User user : list) {
            System.out.println(user.getUserId()+"---"+user.getUserName());
        }
        return "delate";
    }
}
