package com.example.extend.beans;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BaseBean {
    private final List<String> methods = new LinkedList<>();

    protected void addMethod(String name) {
        methods.add(name);
    }

    public String getMethods() {
        return Arrays.toString(methods.toArray());
    }
}
