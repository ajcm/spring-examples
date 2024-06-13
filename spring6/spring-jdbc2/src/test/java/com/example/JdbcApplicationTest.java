package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaConfiguration.class)
public class JdbcApplicationTest {

    @BeforeEach
    public void setUp(@Autowired JdbcTemplate template) {
        template.update("insert into users values (?,?,?)", "007", "James Bond", "some@email");
        template.update("insert into users values (?,?,?)", UUID.randomUUID().toString(), "mark", "some@email");
        template.update("insert into users values (?,?,?)", UUID.randomUUID().toString(), "mark", "some@email");
        template.update("insert into users values (?,?,?)", UUID.randomUUID().toString(), "mark", "some@email");
        template.update("insert into users values (?,?,?)", UUID.randomUUID().toString(), "mark", "some@email");
    }

    @Test
    public void test(@Autowired JdbcTemplate template) {

        //retrieves a list
        List<Map<String, Object>> list = template.queryForList("select * from users");
        Assertions.assertEquals(list.size(), 5);

        //only gets one row
        Map<String, Object> map = template.queryForMap("select * from users where id like '007'");
        Assertions.assertEquals(map.get("Name"), "James Bond");

        Long count = template.queryForObject("select count(*) from users", Long.class);
        Assertions.assertEquals(count, 5);
    }


    @Test
    public void test2(@Autowired JdbcTemplate template) {

        //retrieves a list
        List<Map<String, Object>> list = template.query()



    }

}
