package com.example;

import com.example.beans.MessageBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("dev")
public class PropsApplicationDevTest {

    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void test() {
        Assertions.assertNotNull(applicationContext);
    }


    @Test
    public void test1(@Autowired MessageBean message) {
        Assertions.assertNotNull(message);
          Assertions.assertEquals(message.getMessage(),"hello world from local path");

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }


}
