package com.example.annotation;

import org.springframework.stereotype.Component;

@Component
@AnnotationFlag
public class MyAnnotatedClass {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
