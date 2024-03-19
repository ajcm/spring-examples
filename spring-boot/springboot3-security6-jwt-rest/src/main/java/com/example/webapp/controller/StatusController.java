package com.example.webapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class StatusController {

    @GetMapping("/")
    public String getStatus() {
        return "Ok";
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String doEcho(@RequestBody String echo) {
        return echo;
    }

}
