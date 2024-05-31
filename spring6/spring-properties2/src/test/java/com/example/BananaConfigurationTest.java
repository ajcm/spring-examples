package com.example;

import com.example.beans.MessageBean;
import com.example.config.BananaConfiguration;
import com.example.config.OtherBananaConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MessageConfiguration.class)
@ActiveProfiles("dev")
public class BananaConfigurationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    BananaConfiguration bananaConfiguration;


    @Test
    public void test() {
        Assertions.assertNotNull(applicationContext);
    }


    @Test
    public void test1() {
        MessageBean messageBean = applicationContext.getBean(MessageBean.class);
        Assertions.assertEquals("hello banana", bananaConfiguration.getMessage());
    }


    @Test
    public void test2(@Autowired OtherBananaConfiguration fakeBananaConfiguration) {
        MessageBean messageBean = applicationContext.getBean(MessageBean.class);
        Assertions.assertEquals("hello banana", fakeBananaConfiguration.getMessage());
    }


}
