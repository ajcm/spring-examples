package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspects {


    private static String createJoinPointTraceName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String sb = signature.getDeclaringType().getSimpleName() +
                '.' + signature.getName();
        return sb;
    }

    @Before("execution(public * com.example.message.*.getMessage(..))")
    public void getMessage(JoinPoint joinPoint) {
        var str = createJoinPointTraceName(joinPoint);
        System.out.println("***** Before getMessage1: " + str);
    }

    @After("execution(public * *..*.getMessage(..))")
    public void getMessage2(JoinPoint joinPoint) {
        var str = createJoinPointTraceName(joinPoint);
        System.out.println("***** After getMessage2: " + str);
    }

    @Around("execution(public * *..*.getMessage(..))")
    public Object getMessage3(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        var str = createJoinPointTraceName(proceedingJoinPoint);

        System.out.println("ProceedingJoinPoint: " + str);
        System.out.println("***** Before ******");

        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();

        } catch (Exception exception) {
            System.out.println(":: " + exception.getMessage());
        }

        System.out.println("***** After ******");
        return value;
    }

}
