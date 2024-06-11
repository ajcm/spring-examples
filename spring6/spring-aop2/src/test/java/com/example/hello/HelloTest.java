package com.example.hello;


import com.example.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class HelloTest {

    @Autowired
    HelloBean myobject;

    @Test
    public void test() {
        Assertions.assertNotNull(myobject);
        myobject.setMessage("hello world");
        Assertions.assertEquals("hello world", myobject.getMessage());
    }


}
