package com.example.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("hello")
@PropertySource("classpath:example.properties")
public class HelloBean implements MessageBean {
    private String message;

    @Autowired
    public String setMessage(@Value("${hello.bean.message}") String message) {
        return this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
