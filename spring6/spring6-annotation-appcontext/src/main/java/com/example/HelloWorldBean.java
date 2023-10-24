package com.example;


public class HelloWorldBean implements MessageBean{

    @Override
    public String getMessage() {
        return "Hello World";
    }
}
