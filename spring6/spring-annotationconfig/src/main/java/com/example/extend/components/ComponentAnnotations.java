package com.example.extend.components;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ComponentAnnotations extends BaseComponent {

    @PostConstruct
    private void init() {
        addMethod("init");
    }

    @PreDestroy
    private void destroy() {
        addMethod("destroy");
        System.out.println("ComponentAnnotations: destroy");
    }
}
