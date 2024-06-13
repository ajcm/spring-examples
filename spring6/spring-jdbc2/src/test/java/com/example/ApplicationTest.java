package com.example;

import com.example.dao.User;
import com.example.dao.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaConfiguration.class)
public class ApplicationTest {

    @Test
    public void test(@Autowired UserDao userDao) {

        User user = new User(UUID.randomUUID().toString(),"John doe","fake@email.com");

        userDao.save(user);

        var user2 = userDao.findByEmail(user.getEmail());
        Assertions.assertEquals(user.getEmail(), user2.getEmail());

        var list = userDao.list();

        Assertions.assertEquals(list.size(), 1);
    }


}
