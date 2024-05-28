package com.example.fruits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FruitService {

    @Autowired
    IFruit fruit;

    public IFruit getFruit() {
        return fruit;
    }

    public void setFruit(IFruit fruit) {
        this.fruit = fruit;
    }

    //
//    public Banana getFruit() {
//        return fruit;
//    }
//
//    public void setFruit(Banana fruit) {
//        this.fruit = fruit;
//    }
}
