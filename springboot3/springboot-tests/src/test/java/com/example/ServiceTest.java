package com.example;


import com.example.items.domain.IItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ServiceTest {

    @MockBean
    IItemService iItemService;


    @Test
    public void test(){

        System.out.println("test");
    }

}
