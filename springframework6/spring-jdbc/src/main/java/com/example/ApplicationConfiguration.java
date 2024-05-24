package com.example;

import com.example.dao.UserDao;
import com.example.dao.UserDaoImpl;
import com.example.model.User;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Import(JpaConfiguration.class)
public class ApplicationConfiguration {

    @Bean
    public Faker fake() {
        return new Faker();
    }

    @Bean
    @Scope("prototype")
    public User user(Faker faker) {
        User user = new User();
        user.setId(faker.random().nextLong());
        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }

    @Bean
    public UserDao userDao(JdbcTemplate jdbcTemplate) {
        return new UserDaoImpl(jdbcTemplate);
    }


}
