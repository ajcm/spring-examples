package com.example.webapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerTests {

    @LocalServerPort
    private String localPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        System.out.println("local port: " + localPort);
    }


    @Test
    void test() {
        Assertions.assertNotNull(testRestTemplate);
        var response = testRestTemplate.getForEntity("/", String.class);
        Assertions.assertEquals(response.getBody(), "hello");

    }


//    @Test
//    void test2() {
//        Assertions.assertNotNull(testRestTemplate);
//        var response = testRestTemplate.getForEntity("/admin",String.class);
//        Assertions.assertEquals(response.getBody(),"this is admin");
//
//    }

}
