package com.example;

import com.example.config.DataSourceConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SwapConfiguration.class, SwapConfiguration2.class})
@ActiveProfiles("profilea")
public class ConfigurationATest {

    @Autowired
    String configMessage;

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Autowired
    String databaseName;

    @Test
    public void test1() {
        Assertions.assertEquals("this is profile A", configMessage);
    }

    @Test
    public void test2() {
        Assertions.assertEquals("this is a database", databaseName);
    }


}
