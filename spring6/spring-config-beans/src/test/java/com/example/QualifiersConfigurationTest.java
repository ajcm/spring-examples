package com.example;

import com.example.qualifier.MyBean;
import com.example.qualifier.QualifiersConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = QualifiersConfiguration.class)
public class QualifiersConfigurationTest {

    //Test Qualifiers with bean
    @Autowired
    @Qualifier("firstBean")
    MyBean myBean;

    @Autowired
    @Qualifier("secondBean")
    MyBean mySecondBean;


    @Test
    public void testMyBean() {
        Assertions.assertEquals("FirstMyBean", myBean.getMessage());
        Assertions.assertEquals("SecondMyBean", mySecondBean.getMessage());
    }

}
