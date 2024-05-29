package com.example.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class HomeController {
    private final AtomicLong counter = new AtomicLong();
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String hello() {
        logger.warn("test..");

        return "Hello this is the root path  (" + counter.incrementAndGet() + ")";
    }
}
