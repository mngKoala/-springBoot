# 1. SpringIoC

## 1.1 什么是IoC

**IoC**（Inverse of Control:控制反转）是一种**设计思想**，就是将原本在程序中手动创建对象的控制权，交由Spring框架来管理；**DI（Dependency Injection，依赖注入）** 是实现**IoC**的一种方法。

## 1.2 IOC的技术实现方式有以下三种

- 构造方法注入

- setter方法注入

- 接口注入（处于“退役状态”）

## 1.3 SpringIoC的核心API

- BeanFactory
         获取bean工厂的一种方式（使用较少，支持类相对较少，支持懒加载形式）

- ApplicationContext
  
     获取bean工厂的一种方式，是BeanFactory的子接口，功能更强大（推荐使用）

- ClassPathXmlApplicationContext
  
    获取bean文件地址生成bean工厂的对象，读取classes目录下的文件

- FileSystemXmlApplicationContext
  
    获取bean文件地址生成bean工厂的对象，读取任意盘符下的文件（使用较少）

## 1.4 SpringIoC代码例子

第一步：建立`Maven`工程并导入`springframework`依赖

```
<dependencies>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.2.RELEASE</version>
  </dependency>
</dependencies>
```

第二步：在 resources 目录下创建 Spring 的配置文件 `spring.xml`

```
接口 IAccountService, IAccountDao 及其实现类 AccountServiceImpl,
 AccountDaoImpl
```

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://www.springframework.org/schema/beans
      http://www.springframework.org/schema/beans/spring-beans.xsd">

  <!--把对象的创建交给spring来管理-->
  <bean id = "accountServiceImpl" class = "com.company.service.
        impl.AccountServiceImpl"></bean>
  <bean id = "accountDaoImpl" class = "com.smallbeef.dao.impl.
        AccountDaoImpl"></bean>
</beans>
```

 第三步：获取 Bean 对象

- **使用 `ApplicationContext` 接口加载配置文件，获取 Spring 容器**
  
  对于配置文件的加载方式，除了使用 `ClassPathXmlApplicationContext`(去classpath 路径下查找配置文件)，另外也可以使用`FileSystemXmlApplicationContext`（加载磁盘**任意路径下**的配置文件） 和 `AnnotationConfigApplicationContext`（读取注解创建容器）

- **利用 `getBean` 根据 bean 的 id 从容器中获取对象**

```
public class Client {
    /**
     * 获取spring的Ioc容器，并根据id获取对象
     * @param args
     */
    public static void main(String[] args) {
        // 1.使用 ApplicationContext 接口加载配置文件，获取 spring 容器
        ApplicationContext ac
             = new ClassPathXmlApplicationContext("spring.xml");
        // 2.利用 getBean 根据 bean 的 id 获取对象
        IAccountService aService
             = (IAccountService) ac.getBean("accountServiceImpl");
        System.out.println(aService);
        IAccountDao aDao = (IAccountDao) ac.getBean("accountDaoImpl");
        System.out.println(aDao);
    }
}
```
