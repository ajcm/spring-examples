package com.example.dao;

import com.example.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (id,name,email ) VALUES (?, ?,?)", user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from users where email like ?",
                (rs, rowNum) -> {
                    String id = rs.getString("ID");
                    String name = rs.getString("NAME");
                    String xemail = rs.getString("EMAIL");


                    User user = new User();
                    user.setId(id);
                    user.setName(name);
                    user.setEmail(xemail);

                    return user;
                },
                email);
    }

    @Override
    public List<User> list() {
        return jdbcTemplate.query("select * from users", (RowMapper<User>) (rs, row) -> {
            String id = rs.getString("ID");
            String name = rs.getString("NAME");
            String email = rs.getString("EMAIL");

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setEmail(email);

            return user;
        });
    }
}
