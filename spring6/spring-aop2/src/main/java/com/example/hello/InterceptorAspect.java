package com.example.hello;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class InterceptorAspect {

    @Around("execution(* com.example.hello..*.set*(*))")
    public Object aroundHello(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("execution: " + pjp.getThis().getClass().getSimpleName());

        System.out.println("setter object: " + pjp.getArgs()[0]);

        return pjp.proceed();
    }

}
