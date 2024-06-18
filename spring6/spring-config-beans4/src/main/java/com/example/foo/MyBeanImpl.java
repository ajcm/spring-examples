package com.example.foo;

public class MyBeanImpl implements MyBean {

    private String name;

    public MyBeanImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
