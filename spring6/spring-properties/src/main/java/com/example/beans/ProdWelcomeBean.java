package com.example.beans;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@Component("welcomeBean")
public class ProdWelcomeBean implements MessageBean {
    private String message = "this is prod";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
