package com.example.workshop.tools;

import org.springframework.stereotype.Component;

@Component("hammer")
public class Hammer implements ITool {

    @Override
    public String getMessage() {
        return "Hammer";
    }
}

