# 1. Spring AOP

## 1.1 AOP的几个专业术语

AOP的基本概念

1、连接点：可以被增强的方法

2、切入点：实际被增强的方法

3、通知（增强）：

　　3.1.实际增强的逻辑部分叫做通知

　　3.2.通知类型包括

1. 前置通知（执行方法前执行，通常用作参数日志输出、权限校验等）
2. 后置通知（逻辑代码执行完，准备执行return的代码时通知，通常用作执行结果日志输出、结果加密等）
3. 环绕通知（是前置通知和后置通知的综合，方法执行前和方法执行后都要执行，通常用作方法性能统计、接口耗时、统一加密、解密等）
4. 异常通知（相当于try{}catch ()中catch执行的部分,程序抛出异常时执行，通常用作告警处理、事务回滚等）
5. 最终通知（相当于try{}catch (Exception e){}finally { }中的finally执行的部分，通常用在关闭资源、清理缓存等业务逻辑中）

4、切面：把通知(增强)应用到切入点的过程

## 1.2 AOP的实现技术

1、Spring 框架一般都是基于 AspectJ 实现 AOP 操作

（1）AspectJ 不是 Spring 组成部分，独立 AOP 框架，一般把 AspectJ 和 Spirng 框架一起使 用，进行 AOP 操作

2、基于 AspectJ 实现 AOP 操作

（1）基于 xml 配置文件实现

（2）基于注解方式实现（使用）

3、在项目工程里面引入 AOP 相关依赖

```
<!-- https://mvnrepository.com/artifact/org.springframework/spring-aop -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>5.3.23</version>
</dependency>
```

```
<!-- https://mvnrepository.com/artifact/org.springframework/spring-aspects -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>5.3.23</version>
</dependency>
```

```
<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.23</version>
</dependency>
```

 4、切入点表达式

（1）切入点表达式作用：知道对哪个类里面的哪个方法进行增强

（2）语法结构： execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]) )

说明：* 号表示通配符，.. 符号表示一个或多个参数

举例 1：对 com.ep.controller.ProductController 类里面的 queryProduct 进行增强 execution(* com.ep.controller.ProductController.queryProduct (..))

举例 2：对 com.ep.controller.ProductController 类里面的所有的方法进行增强 execution(* com.ep.controller.ProductController .* (..))

举例 3：对 com.ep.controller 包里面所有以controller结尾的类里面所有方法进行增强 execution(* com.ep.controller.*Controller .* (..))

## 1.3 AOP的案例

这里使用spring中的aop实现对controller里日志输出来加深对aop的理解

实现步骤:

准备工作：

步骤一：创建一个controller类并定义方法

```
public class UserController {
    /**
     * 查询用户
     */
    public Object queryUser(String name, Integer age) {
        System.out.println("数据查询中模拟...........");
        return name + "，"+ age;
    }
}
```

步骤二：创建一个增强类，编写增强逻辑，也就是说建立一个切面

```
public class LogAop {
    public void method01() {
        System.out.println("method01-前置通知-增强逻辑-------------");
    }

    public void method02() {
        System.out.println("method02-后置通知-增强逻辑-------------");
    }

    public void method03() {
        System.out.println("method03-环绕通知-增强逻辑-------------");
    }

    public void method04() {
        System.out.println("method04-异常通知-增强逻辑-------------");
    }

    public void method05() {
        System.out.println("method05-最终通知-增强逻辑-------------");
    }
}
```

AOP配置

步骤一：在 spring 配置文件中，开启注解扫描

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
                              http://www.springframework.org/schema/beans/spring-beans.xsd 
                              http://www.springframework.org/schema/context 
                              https://www.springframework.org/schema/context/spring-context.xsd  
                              http://www.springframework.org/schema/aop 
                              http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!-- 开启注解扫描 -->
    <context:component-scan base-package="com.ep.aop"></context:component-scan>

    <!--开启注解aop 自动产生代理-->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

    <bean id="userController" class="com.ep.controller.UserController"></bean>
</beans>
```

步骤二：增强类上加上注解，以及在方法加不同类型的通知

类上加注解

![](https://img2020.cnblogs.com/blog/858186/202101/858186-20210114100655454-1286991910.png)

方法上加不同类型的通知

```
package com.ep.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Copyright (C) 
 * @Author: 
 * @Date: 
 * @Description: <p>
 * 前置通知（执行方法前执行，通常用作参数日志输出、权限校验等）
 * 后置通知（逻辑代码执行完，准备执行return的代码时通知，通常用作执行结果日志输出、结果加密等）
 * 环绕通知（是前置通知和后置通知的综合，方法执行前和方法执行后都要执行，通常用作方法性能统计、接口耗时、统一加密、解密等）
 * 异常通知（相当于try{}catch ()中catch执行的部分,程序抛出异常时执行，通常用作告警处理、事务回滚等）
 * 最终通知（相当于try{}catch (Exception e){}finally { }中的finally执行的部分，通常用在关闭资源、清理缓存等业务逻辑中）
 * </p>
 * 语法结构： execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]) )
 */
@Component
@Aspect
public class LogAop {
    /**
     * 前置通知
     */
    @Before(value = "execution(* com.ep.controller.*Controller.*(..))")
    public void method01() {
        System.out.println("method01-前置通知-增强逻辑-------------");
    }

    /**
     * 后置通知
     */
    @AfterReturning(value = "execution(* com.ep.controller.*Controller.*(..))")
    public void method02() {
        System.out.println("method02-后置通知-增强逻辑-------------");
    }

    /**
     * 环绕通知
     */
    //@Around(value = "execution(* com.ep.controller.*Controller.*(..))")
    public void method03() {
        System.out.println("method03-环绕通知-增强逻辑-------------");
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "execution(* com.ep.controller.*Controller.*(..))")
    public void method04() {
        System.out.println("method04-异常通知-增强逻辑-------------");
    }

    /**
     * 最终通知
     */
    @After(value = "execution(* com.ep.controller.*Controller.*(..))")
    public void method05() {
        System.out.println("method05-最终通知-增强逻辑-------------");
    }
}
```

步骤三：controller类上加上注解

![](https://img2020.cnblogs.com/blog/858186/202101/858186-20210114100900182-483477173.png)

步骤四：测试

```
package com.ep.test;

import com.ep.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
```

注意：当前测试需要关闭环绕通知

当没有异常的时候，执行结果

```
method01- @Before-前置通知-增强逻辑-------------
数据查询中模拟...........
method02- @AfterReturning-后置通知-增强逻辑-------------
method05- @After-最终通知-增强逻辑-------------
queryUser=张三,18岁
```

当有异常的时候

```
method01- @Before-前置通知-增强逻辑-------------
数据查询中模拟...........
method04- @AfterThrowing-异常通知-增强逻辑-------------
method05- @After-最终通知-增强逻辑-------------
```
