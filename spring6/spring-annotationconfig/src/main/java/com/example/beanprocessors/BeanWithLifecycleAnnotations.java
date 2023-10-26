package com.example.beanprocessors;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// default scope is singleton
@Component
public class BeanWithLifecycleAnnotations {

    @PostConstruct
    private void init() {
        System.out.println("BeanWithLifecycleAnnotations: PostConstruct");
    }

    @PreDestroy
    private void finish() {
        System.out.println("BeanWithLifecycleAnnotations: PreDestroy");
    }


}
