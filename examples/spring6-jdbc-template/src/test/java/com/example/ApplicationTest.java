package com.example;

import com.example.dao.UserDao;
import com.example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationTest {

    @Test
    public void test(@Autowired UserDao userDao, @Autowired User user) {

        userDao.save(user);

        var user2 = userDao.findByEmail(user.getEmail());
        Assertions.assertEquals(user.getEmail(), user2.getEmail());

        var list = userDao.list();

        Assertions.assertEquals(list.size(), 1);
    }


}
