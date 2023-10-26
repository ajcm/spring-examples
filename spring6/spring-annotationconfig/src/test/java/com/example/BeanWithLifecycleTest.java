package com.example;

import com.example.beanprocessors.BeanWithLifecycleAnnotations;
import com.example.beanprocessors.BeanWithLifecycleExtended;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class BeanWithLifecycleTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    BeanWithLifecycleAnnotations beanWithLifecycleAnnotations;

    @Autowired
    BeanWithLifecycleExtended beanWithLifecycleExtended;

    @Test
    public void load() {
        Assertions.assertNotNull(applicationContext);
        Assertions.assertNotNull(beanWithLifecycleAnnotations);
        Assertions.assertNotNull(beanWithLifecycleExtended);
    }

}
