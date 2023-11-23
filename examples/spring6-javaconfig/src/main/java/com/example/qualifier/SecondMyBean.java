package com.example.qualifier;

public class SecondMyBean implements MyBean {
    @Override
    public String getMessage() {
        return "SecondMyBean";
    }
}
