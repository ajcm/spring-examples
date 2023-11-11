package org.example.dao;

import org.example.model.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {

    void save(User user);

    @Transactional
    User findByEmail(String email);
}
