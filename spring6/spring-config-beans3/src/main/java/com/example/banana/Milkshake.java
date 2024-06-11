package com.example.banana;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Milkshake {

    Banana banana;
    Strawberry strawberry;
    Apple apple;

    public Milkshake(Banana banana) {
        this.banana = banana;
    }

    public Banana getBanana() {
        return banana;
    }

    public void setBanana(Banana banana) {
        this.banana = banana;
    }

    public Strawberry getStrawberry() {
        return strawberry;
    }

    @Autowired
    @Lazy
    public void setStrawberry(Strawberry strawberry) {
        this.strawberry = strawberry;
    }

    public Apple getApple() {
        return apple;
    }

    @Autowired
    @Lazy
    private void setApple(Apple apple) {
        this.apple = apple;
    }
}
