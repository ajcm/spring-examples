package com.example.salad;

import org.springframework.stereotype.Component;

@Component("fruitSaladConstructor")
public class FruitSaladConstructor implements FruitSalad {

    Ingredient lettuce;
    Ingredient tomato;
    Ingredient dressing;

    // @Autowired not required
    public FruitSaladConstructor(Ingredient lettuce, Ingredient tomato, Ingredient dressing) {
        this.lettuce = lettuce;
        this.tomato = tomato;
        this.dressing = dressing;
    }

    @Override
    public String getMessage() {
        return "fruitSaladConstructor";
    }


    public Ingredient getLettuce() {
        return lettuce;
    }

    public Ingredient getTomato() {
        return tomato;
    }

    public Ingredient getDressing() {
        return dressing;
    }
}
