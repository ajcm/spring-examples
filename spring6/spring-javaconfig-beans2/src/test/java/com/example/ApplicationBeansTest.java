package com.example;

import com.example.salad.SaladConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SaladConfiguration.class)
@ComponentScan
public class ApplicationBeansTest {

    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void test() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            var b = applicationContext.getBean(beanName);
            System.out.println(beanName + " -> " + b.getClass().getSimpleName());
        }
    }


}
