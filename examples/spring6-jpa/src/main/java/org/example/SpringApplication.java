package org.example;

import com.github.javafaker.Faker;
import org.example.dao.UserDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpringApplication {


    @Autowired
    private Faker faker;

    @Autowired
    private DataSource dataSource;


    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);

        UserDao userDao = applicationContext.getBean(UserDao.class);
        userDao.save(user1);
        userDao.save(user2);

        DataSource dataSource = applicationContext.getBean(DataSource.class);
        runQuery(dataSource);

        applicationContext.close();
    }

    private static void runQuery(DataSource dataSource) {

        System.out.println("List users:");

        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();) {

            ResultSet rs = stmt.executeQuery("select * from users");

            while (rs.next()) {

                String id = rs.getString("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println(id + ", " + name + ", " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}