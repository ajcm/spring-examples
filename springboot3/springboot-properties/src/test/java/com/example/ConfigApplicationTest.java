package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest
public class ConfigApplicationTest {

    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void test() {
        Assertions.assertNotNull(applicationContext);
    }


    @Test
    public void test1(@Autowired DbConfig dbConfig) {
        Assertions.assertNotNull(dbConfig);
        Assertions.assertEquals(dbConfig.getName(), "mydatabase");
        Assertions.assertEquals(dbConfig.getUser(), "admin");

    }


}
