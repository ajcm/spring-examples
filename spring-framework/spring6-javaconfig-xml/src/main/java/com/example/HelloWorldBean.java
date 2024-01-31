package com.example;


public class HelloWorldBean implements MessageBean {

    private String message = "";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void destroy() {
        System.out.println("Bean is destroyed");
    }
}
