package com.example.beans;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component("welcomeBean")
public class DevWelcomeBean implements MessageBean {
    private String message = "this is dev";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
