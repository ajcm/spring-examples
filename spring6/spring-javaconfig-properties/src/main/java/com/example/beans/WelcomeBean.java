package com.example.beans;


public class WelcomeBean implements MessageBean {

    private String message = "Welcome";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
