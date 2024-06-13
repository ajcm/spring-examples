package com.example.banana;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BananaConfiguration.class)
public class BananaTest {

    @Test
    public void test1(@Autowired Banana bb) {
        Assertions.assertNotNull(bb);
    }

    @Test
    public void test2(@Autowired Milkshake milkshake) {
        Assertions.assertNotNull(milkshake);
        Assertions.assertNotNull(milkshake.getBanana());

        // getStrawberry - returns a proxy
        Assertions.assertNotNull(milkshake.getStrawberry());
    }

    @Test
    public void test3(@Autowired Milkshake milkshake) {
        Assertions.assertNotNull(milkshake);
        Assertions.assertNotNull(milkshake.getApple());
        Assertions.assertNotNull(milkshake.getApple().getName());
    }

    @Test
    public void test4(@Autowired Whatever whatever) {
        Assertions.assertNotNull(whatever);
        Assertions.assertNotNull(whatever.getBanana());
        Assertions.assertNotNull(whatever.getBanana().getHello());
    }




}
