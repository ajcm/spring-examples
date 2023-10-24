package com.example;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

public class HelloWorldBean implements MessageBean{

    @Value("${message}")
    private String message = "";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
