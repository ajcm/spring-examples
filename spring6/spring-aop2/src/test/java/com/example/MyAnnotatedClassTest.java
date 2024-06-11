package com.example;


import com.example.annotation.MyAnnotatedClass;
import com.example.annotation.MyAnnotatedMethodClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class MyAnnotatedClassTest {

    @Autowired
    MyAnnotatedClass myobject;

    @Autowired
    MyAnnotatedMethodClass myAnnotatedMethodClass;

    @Test
    public void test() {
        Assertions.assertNotNull(myobject);
        myobject.setMessage("mmm");

    }


    @Test
    public void test2() {
        Assertions.assertNotNull(myAnnotatedMethodClass);
        myAnnotatedMethodClass.setMessage("mmm");

    }

}
