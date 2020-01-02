package com.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @description
 * @date 2020/1/2
 */
@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @RequestMapping(value = "/test4",method = RequestMethod.GET)
    public String test(){
        LOGGER.trace("trace");
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
        System.out.println("----------");
        return "log4j";
    }
}
