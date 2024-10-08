package com.example.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
class SpringWebApplicationTests {


    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    void contextLoads(@Autowired DataSource dataSource) {

    }

    @Test
    void testJdbcTemplate() {
        jdbcTemplate.getDataSource();
    }

}
