package com.example.tools;

import org.springframework.stereotype.Component;

@Component
public class Wrench implements ITool {


    @Override
    public String getMessage() {
        return "Wrench";
    }
}
