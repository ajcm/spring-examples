package com.example;

import com.example.items.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import({ApplicationConfiguration.class})
public class ItemTest {

    @Mock
    Item testItem;

    @Autowired
    Item autowired;

    @Test
    public void test() {
        Mockito.when(testItem.getDescription()).thenReturn("banana");
        Assertions.assertEquals(testItem.getDescription(), "banana");
    }


    @Test
    public void test2() {
        Assertions.assertEquals(autowired.getDescription(), "description");
    }

}
