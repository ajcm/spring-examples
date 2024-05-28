package com.example.beans;


import org.springframework.beans.factory.annotation.Value;

public class HelloWorldBean implements MessageBean {

    @Value("${bean.message}")
    private String message = "";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
