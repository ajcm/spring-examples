package com.example.salad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("fruitSaladSetters")
public class FruitSaladSetters implements FruitSalad {

    Ingredient lettuce;
    Ingredient tomato;
    Ingredient dressing;

    public FruitSaladSetters() {
    }

    @Override
    public String getMessage() {
        return "fruitSaladSetters";
    }

    @Autowired
    public void whateverThisCanBeCalled(Ingredient dressing) {
        this.dressing = dressing;
    }

    public Ingredient getLettuce() {
        return lettuce;
    }

    @Autowired
    public void setLettuce(Ingredient lettuce) {
        this.lettuce = lettuce;
    }

    public Ingredient getTomato() {
        return tomato;
    }

    @Autowired
    public void setTomato(Ingredient tomato) {
        this.tomato = tomato;
    }

    public Ingredient getDressing() {
        return dressing;
    }
}
