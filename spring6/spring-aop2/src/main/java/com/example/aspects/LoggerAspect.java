package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    //Private
    private static String createJoinPointTraceName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String sb = signature.getDeclaringType().getSimpleName() +
                '.' + signature.getName();
        return sb;
    }

    @Before("execution(* com.example..*.get*(..))")
    public void logCars(JoinPoint joinPoint) {
        var info = createJoinPointTraceName(joinPoint);
        System.out.println("all before get: " + info + ", " + joinPoint.getThis().getClass().getSimpleName());
    }

}
