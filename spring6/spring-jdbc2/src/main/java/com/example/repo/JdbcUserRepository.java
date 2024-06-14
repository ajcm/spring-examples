package com.example.repo;

import com.example.model.User;

import java.util.List;

public interface JdbcUserRepository {

    int save(User user);

    String findNameByEmail(String email);

    User findByEmail(String email);

    List<User> list();
}
