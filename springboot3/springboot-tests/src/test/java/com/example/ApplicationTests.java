package com.example;

import com.example.items.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @LocalServerPort
    String localPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void tests() throws Exception {

        System.out.println("-> " + localPort);
        ResponseEntity<String> page = restTemplate.getForEntity("/items", String.class);
        Assertions.assertTrue(page.getStatusCode().is2xxSuccessful());

    }


    @Test
    public void tests2() throws Exception {

        Item item = new Item();
        item.setDescription("description");

        ResponseEntity<Item> response = restTemplate.postForEntity("/item", item, Item.class);

        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(response.getBody().getDescription(), "description");


        ResponseEntity<Item> response2 = restTemplate.getForEntity("/item/{id}",
                Item.class,
                response.getBody().getId().toString());

        Assertions.assertEquals(response2.getBody().getDescription(), "description");

    }


}
