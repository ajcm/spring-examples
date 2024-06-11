package com.example;

import com.example.foo.BeanConfiguration;
import com.example.foo.MyBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfiguration.class)
public class BeanTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private MyBean bean1;

    @Autowired
    private MyBean bean2;

    @Autowired
    private MyBean bean3;


    //   @Test org.junit.jupiter.api.extension.ParameterResolutionException
//    public void test(@Autowired MyBean bean) {
//
//    }

    @Test
    void test(@Autowired MyBean bean1) {
    }


    @Test
    void test1(@Qualifier("bean2") @Autowired MyBean bean1) {
        Assertions.assertEquals(bean1.getName(), "bean2");
    }


    @Test
    void test2(@Autowired MyBean setBean) {
        Assertions.assertEquals(setBean.getName(), "bean2");

    }


}
