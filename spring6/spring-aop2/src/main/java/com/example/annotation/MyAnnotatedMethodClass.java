package com.example.annotation;

import org.springframework.stereotype.Component;

@Component
public class MyAnnotatedMethodClass {

    private String message;

    @AnnotationFlag
    public String getMessage() {
        return message;
    }

    @AnnotationFlag
    public void setMessage(String message) {
        this.message = message;
    }
}
