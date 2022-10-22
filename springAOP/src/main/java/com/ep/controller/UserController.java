package com.ep.controller;

import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    /**
     * 查询用户
     */
    public Object queryUser(String name, Integer age) {
        System.out.println("数据查询中模拟...........");
        return name + "，"+ age;
    }	
}
