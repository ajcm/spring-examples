package com.example;


import com.example.items.domain.IItemService;
import com.example.items.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@SpringBootTest
@Import({ApplicationConfiguration.class})
public class ServiceTest {

    @Autowired
    Item autowired;

    @MockBean
    IItemService itemService;


    @Test
    public void test(@Autowired IItemService itemService) {

        Mockito.when(itemService.getItem(Mockito.anyLong())).thenReturn(Optional.of(autowired));
        var optItem = itemService.getItem(1L);

        Assertions.assertTrue(optItem.isPresent());
        Assertions.assertEquals(optItem.get().getDescription(), "description");
    }

}
