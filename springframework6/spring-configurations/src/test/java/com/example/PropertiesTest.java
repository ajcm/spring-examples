package com.example;

import com.example.properties.AppProperties;
import com.example.properties.AppPropertiesOther;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class PropertiesTest {


    @Autowired
    AppProperties appProperties;

    @Autowired
    AppPropertiesOther appPropertiesOther;


    @Test
    public void appProperties(){
        Assertions.assertNotNull(appProperties);
        Assertions.assertEquals("app name example app v1", appProperties.getMessage());
    }


    @Test
    public void appPropertiesOther(){
        Assertions.assertNotNull(appPropertiesOther);
        Assertions.assertEquals("app name example app v1", appPropertiesOther.getMessage());
    }

}
