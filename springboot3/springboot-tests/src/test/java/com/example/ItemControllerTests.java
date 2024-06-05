package com.example;

import com.example.items.ItemController;
import com.example.items.domain.IItemService;
import com.example.items.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ItemController.class)
public class ItemControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IItemService service;

    @Test
    public void tests() throws Exception {

        Item item = new Item();
        item.setDescription("description");

        mockMvc.perform(MockMvcRequestBuilders.post("/item")
                        .content("{\"description \": \"apple\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                .andExpect(MockMvcResultMatchers.status().isOk());


        mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(""));

    }


}
