package com.example.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String getHello() {
        return "hello";
    }


    @GetMapping("/user")
    public String getUser() {
        return "user";
    }


    @PostMapping("/echo")
    public String doPost(@RequestBody String body) {
        return body;
    }

}
