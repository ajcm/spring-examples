package com.example.message;

import org.springframework.stereotype.Component;

@Component("welcome")
public class WelcomeBean implements MessageBean {
    @Override
    public String getMessage() {
        return "welcome";
    }
}
