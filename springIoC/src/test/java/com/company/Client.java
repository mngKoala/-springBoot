package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.dao.IAccountDao;
import com.company.service.IAccountService;

public class Client {
    /**
     * 获取spring的Ioc容器，并根据id获取对象
     * @param args
     */
    public static void main(String[] args) {
        // 1.使用 ApplicationContext 接口加载配置文件，获取 spring 容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        // 2.利用 getBean 根据 bean 的 id 获取对象
        IAccountService aService = (IAccountService) ac.getBean("accountServiceImpl");
        System.out.println(aService);
        IAccountDao aDao = (IAccountDao) ac.getBean("accountDaoImpl");
        System.out.println(aDao);
    }
}
