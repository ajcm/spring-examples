package com.example.dao;

import com.example.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    User findByEmail(String email);

    List<User> list();
}
