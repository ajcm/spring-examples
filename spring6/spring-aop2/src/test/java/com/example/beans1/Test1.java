package com.example.beans1;


import com.example.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@ComponentScan
public class Test1 {

    @Autowired
    TestBean bean;

    @Test
    public void test() {
        Assertions.assertNotNull(bean);
        bean.setField1("field1");
        bean.setField2("field2");
        bean.setField3("field3");
    }

    @Test
    public void test2() {
        Assertions.assertNotNull(bean);
        bean.getField1();

    }

}
