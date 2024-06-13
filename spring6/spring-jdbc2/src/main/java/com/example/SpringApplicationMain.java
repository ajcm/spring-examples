package com.example;

import com.example.dao.UserDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(JpaConfiguration.class);

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);

        UserDao userDao = applicationContext.getBean(UserDao.class);
        userDao.save(user1);
        userDao.save(user2);

        var users = userDao.list();

        for (User user : users) {
            System.out.println(user);
        }

        applicationContext.close();
    }


}
