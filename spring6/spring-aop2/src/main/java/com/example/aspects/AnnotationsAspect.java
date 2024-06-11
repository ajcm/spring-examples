package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationsAspect {

    //Private
    private static String createJoinPointTraceName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String sb = signature.getDeclaringType().getSimpleName() +
                '.' + signature.getName();
        return sb;
    }

    @AfterReturning("@target(com.example.annotation.AnnotationFlag)")
    public void logannotated(JoinPoint joinPoint) {
        var info = createJoinPointTraceName(joinPoint);
        System.out.println("FlagClass : " + info + ", " + joinPoint.getThis().getClass().getSimpleName());
    }

    @AfterReturning("@annotation(com.example.annotation.AnnotationFlag)")
    public void methodAnnotated(JoinPoint joinPoint) {
        var info = createJoinPointTraceName(joinPoint);
        System.out.println("MethodClass : " + info + ", " + joinPoint.getThis().getClass().getSimpleName());
    }


//    @Before("execution(public * com.example.beans.*.getMessage(..))")
//    public void logMessage(JoinPoint joinPoint) {
//        var info = createJoinPointTraceName(joinPoint);
//        System.out.println("before: " + info + ", " + joinPoint.getThis().getClass().getSimpleName());
//    }
//
//    @Before("execution(* set(..))")
//    public void logSetter(JoinPoint jp){
//        var info = createJoinPointTraceName(jp);
//        System.out.println("setter: " + info + ", " + jp.getThis().getClass().getSimpleName());
//
//    }


}
