package com.example.cars;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CarLoggerAspect {


    //Private
    private static String createJoinPointTraceName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String sb = signature.getDeclaringType().getSimpleName() +
                '.' + signature.getName();
        return sb;
    }

    @Before("within(com.example.cars.*)")
    public void logCars(JoinPoint joinPoint) {
        var info = createJoinPointTraceName(joinPoint);
        System.out.println("before: " + info + ", " + joinPoint.getThis().getClass().getSimpleName());
    }

    @Before("within(com..*.*)")
    public void logCars2(JoinPoint joinPoint) {
        var info = createJoinPointTraceName(joinPoint);
        System.out.println("before2: " + info + ", " + joinPoint.getThis().getClass().getSimpleName());
    }

}
