package com.example;

import com.example.callbacks.UserDebugRowCallbackHandler;
import com.example.callbacks.UserMapResultSetExtractor;
import com.example.callbacks.UserRowMapper;
import com.example.model.User;
import com.example.model.UserMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        template.update("delete from users");
        template.update("insert into users values (?,?,?)", "007", "James Bond", "some@email");
        template.update("insert into users values (?,?,?)", UUID.randomUUID().toString(), "mark", "some@email");
        template.update("insert into users values (?,?,?)", UUID.randomUUID().toString(), "mark", "some@email");
        template.update("insert into users values (?,?,?)", UUID.randomUUID().toString(), "mark", "some@email");
        template.update("insert into users values (?,?,?)", UUID.randomUUID().toString(), "mark", "some@email");
    }

    @Test
    public void test(@Autowired JdbcTemplate template) {

        // get object
        Long count = template.queryForObject("select count(*) from users", Long.class);
        Assertions.assertEquals(count, 5);

        //retrieves a list
        List<Map<String, Object>> list = template.queryForList("select * from users");
        Assertions.assertEquals(list.size(), 5);

        //only gets one row
        Map<String, Object> map = template.queryForMap("select * from users where id like '007'");
        Assertions.assertEquals(map.get("Name"), "James Bond");

    }


    @Test
    public void test2(@Autowired JdbcTemplate template) {

        //retrieves a user
        User uu = template.queryForObject("select * from users where id like '007'", (RowMapper<User>) (rs, rowNum) -> {
            var id = rs.getString("ID");
            var name = rs.getString("NAME");
            var email = rs.getString("EMAIL");

            return new User(id, name, email);
        });
        Assertions.assertEquals("James Bond", uu.getName());


        List<User> users = template.query("select * from users ", new UserRowMapper());
        Assertions.assertEquals(users.size(), 5);

    }


    @Test
    public void test3(@Autowired JdbcTemplate template) {

        UserMap userMap = template.query("select * from users", new UserMapResultSetExtractor());
        Assertions.assertEquals(userMap.getUserMap().size(), 5);

        template.query("select * from users", new UserDebugRowCallbackHandler());

    }
}
