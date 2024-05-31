package com.example;

import com.example.salad.SaladConfiguration;
import com.example.salad.service.IFruitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SaladConfiguration.class)
public class FruitServiceTest {

    @Autowired
    IFruitService fruitService;

    @Test
    public void test() {
        Assertions.assertEquals("FruitService", fruitService.getName());
    }

    @Test
    public void test1(@Autowired IFruitService fruitService) {
        Assertions.assertNotNull(fruitService);
        Assertions.assertEquals("FruitService", fruitService.getName());
    }


    //qualificaition by name
    @Test
    public void test2(@Autowired IFruitService improvedFruitService) {
        Assertions.assertNotNull(fruitService);
        Assertions.assertEquals("ImprovedFruitService", improvedFruitService.getName());
    }

    //implicit qualificaition
    @Test
    public void test3(@Qualifier("BeanFruitService") @Autowired IFruitService beanFruitService) {
        Assertions.assertNotNull(beanFruitService);
        Assertions.assertEquals("BeanFruitService", beanFruitService.getName());
    }


}
