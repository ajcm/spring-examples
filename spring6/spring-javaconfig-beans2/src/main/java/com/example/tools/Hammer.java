package com.example.tools;

import org.springframework.stereotype.Component;

@Component
public class Hammer implements ITool {

    @Override
    public String getMessage() {
        return "Hammer";
    }
}

