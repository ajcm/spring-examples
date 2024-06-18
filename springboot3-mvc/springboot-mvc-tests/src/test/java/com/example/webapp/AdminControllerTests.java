package com.example.webapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void test() throws Exception {
        Assertions.assertNotNull(mockMvc);

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.content().string("hello"));

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test1() throws Exception {
        Assertions.assertNotNull(mockMvc);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.content().string("this is admin"));

    }


}
