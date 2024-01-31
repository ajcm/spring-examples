package com.example.springdatajdbc;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("testRes")
public class HelloController {

    @GetMapping
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("hello");
    }
}
