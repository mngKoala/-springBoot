package com.ep.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
