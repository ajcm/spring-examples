package com.example.foo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;


public class ComposeBean {

    // need to be injected manually
    @Autowired
    @Qualifier("bean1")
    static private MyBean yyy = null;
    @Autowired
    @Qualifier("bean1")
    private final MyBean xxx = null;

    public ComposeBean() {
    }


    // executes after DI
    @PostConstruct
    private void postConstruct() {
        System.out.println("test");
        ComposeBean.yyy = xxx;
    }

    public MyBean getOne() {
        return xxx;
    }

    public MyBean getTwo() {
        return yyy;
    }
}
