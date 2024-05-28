package com.example.tools;

import org.springframework.stereotype.Component;

@Component
public class Screwdriver implements ITool {

    @Override
    public String getMessage() {
        return "Screwdriver";
    }
}

