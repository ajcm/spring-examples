package com.example.banana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Whatever {

    @Autowired
    final private Banana banana = null;


    public Banana getBanana() {
        return banana;
    }

    public Whatever() {
    }
//    public void setBanana(Banana banana) {
//        this.banana = banana;
//    }
}
