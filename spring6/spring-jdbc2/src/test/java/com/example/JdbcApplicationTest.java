package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

     var list =   template.queryForList("select * from users");

     list.size();

    }


}
