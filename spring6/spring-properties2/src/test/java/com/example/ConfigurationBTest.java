package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SwapConfiguration.class, SwapConfiguration2.class})
@ActiveProfiles("profileb")
public class ConfigurationBTest {

    @Autowired
    String configMessage;


    @Test
    public void test1() {
        Assertions.assertEquals("this is profile B", configMessage);
    }


}
