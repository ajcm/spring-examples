package com.example;

import com.example.foo.ComposeBean;
import com.example.foo.MyBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationTest {


    @Test
    void test(@Autowired MyBean beanA) {
        Assertions.assertNotNull(beanA);
    }

// qualifier cannot be user by superbean
//    @Test
//    void testA(@Autowired MyBean superbean) {
//        Assertions.assertNotNull(superbean);
//    }

    // Qualifier disambiguates
    @Test
    void test1(@Autowired @Qualifier("superbean") MyBean xxx) {
        Assertions.assertNotNull(xxx);
    }


    // Qualifier overrides bean name
    @Test
    void test2(@Autowired @Qualifier("superbean") MyBean beanA) {
        Assertions.assertNotNull(beanA);
        Assertions.assertEquals(beanA.getName(), "bean2");
    }


    //Qualifier overrides
    @Test
    void test3(@Autowired @Qualifier("beanA") MyBean bean1) {
        Assertions.assertNotNull(bean1);
        Assertions.assertEquals(bean1.getName(), "beanX");
    }


    // name of bean can be used as qualifier
    @Test
    void test4(@Autowired @Qualifier("beanA") MyBean nananananan) {
        Assertions.assertNotNull(nananananan);
        Assertions.assertEquals(nananananan.getName(), "beanX");
    }


    @Test
    void testCompose3(@Autowired ComposeBean composeBean) {
        Assertions.assertNotNull(composeBean);
        Assertions.assertNotNull(composeBean.getOne());
        Assertions.assertNotNull(composeBean.getTwo());
    }


}
