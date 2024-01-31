package com.example.extend.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
    JSR-250 -  @PostConstruct and @PreDestroy
 */

public class BeanLifecycleAnnotations extends BaseBean {

    @PostConstruct
    private void init() {
        System.out.println("BeanLifecycle: PostConstruct");
        addMethod("init");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("BeanLifecycle: PreDestroy");
        addMethod("destroy");
    }


}
