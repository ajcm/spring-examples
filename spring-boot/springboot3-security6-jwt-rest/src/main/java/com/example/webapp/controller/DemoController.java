package com.example.webapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/debug")
public class DemoController {

    @GetMapping("hello")
    public String getHello(){
        return "hello";
    }


    @PostMapping(value = "echo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String doEcho(@RequestBody  String echo){
        return echo;
    }

}
