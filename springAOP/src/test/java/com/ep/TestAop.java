package com.ep;

import com.ep.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Copyright (C) 
 * @Author: 
 * @Date: 
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean-aop.xml")
public class TestAop {
	
    @Autowired
    @Qualifier("userController")
    private UserController userController;

    /**
     * 测试
     */
    @Test
    public void test01() {
        Object queryUser = userController.queryUser("张三", 18);
        System.out.println("queryUser=" + queryUser);
    }
}
