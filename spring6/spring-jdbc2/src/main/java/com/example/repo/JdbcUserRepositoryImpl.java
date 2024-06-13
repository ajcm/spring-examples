package com.example.repo;

import com.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserRepositoryImpl extends JdbcAbstractRepository<User> implements JdbcUserRepository {

    @Override
    public int save(User user) {
        return getTemplate().update("insert into users values (?,?,?)", user.getId(), user.getName(), user.getEmail());

    }

    @Override
    public String findNameByEmail(String email) {
        return getTemplate().queryForObject("select name from users where email like ?", String.class, email);
    }

    @Override
    public User findByEmail(String email) {
        return getTemplate().queryForObject("select * from users where email like ?", new UserRowMapper(), email);
    }

    @Override
    public List<User> list() {

        return getTemplate().query("select * from users", (row, rowno) -> {
            var id = row.getString("id");
            var name = row.getString("name");
            var email = row.getString("email");

            return new User(id, name, email);
        });

    }

    @Autowired
    protected void setTemplate(JdbcTemplate template) {
        super.setTemplate(template);
    }

}
