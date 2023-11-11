package org.example;

import com.github.javafaker.Faker;
import org.example.model.User;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
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
        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }


}
