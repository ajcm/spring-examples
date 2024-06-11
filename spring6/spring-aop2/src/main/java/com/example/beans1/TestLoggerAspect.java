package com.example.beans1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestLoggerAspect {


    @Before("within(com.example.beans1.*)")
    public void logger(JoinPoint joinPoint) {
        System.out.println("before: " + joinPoint.toLongString());
    }

    @After("execution(String com.example.beans1.TestBean.get*(..))")
    public void loggerAfter(JoinPoint joinPoint) {
        System.out.println("after exec: " + joinPoint.toLongString());
    }

    @After("execution(String com.example.beans1..*get*(..))")
    public void loggerAfter2(JoinPoint joinPoint) {
        System.out.println("after getters: " + joinPoint.toLongString());
    }


}
