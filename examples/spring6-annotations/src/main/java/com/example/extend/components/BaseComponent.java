package com.example.extend.components;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BaseComponent {
    private final List<String> methods = new LinkedList<>();

    protected void addMethod(String name) {
        methods.add(name);
    }

    public String getMethods() {
        return Arrays.toString(methods.toArray());
    }
}
