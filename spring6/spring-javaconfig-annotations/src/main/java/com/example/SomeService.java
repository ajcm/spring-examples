package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SomeService {

    @Value("${service.message}")
    private String message = "";


    /**
     * autowired beans are always called after bean creation
     */
    @Autowired
    public void whatever() {
        System.out.println("whatever called *****");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
