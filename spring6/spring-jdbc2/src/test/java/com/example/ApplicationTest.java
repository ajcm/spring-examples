package com.example;

import com.example.repo.JdbcUserRepositoryImpl;
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
    public void test(@Autowired JdbcUserRepositoryImpl repository) {
        User user = new User(UUID.randomUUID().toString(), "John doe", "fake@email.com");
        repository.save(user);

        var name = repository.findNameByEmail("fake@email.com");
        Assertions.assertEquals("John doe", name);

        var xuser = repository.findByEmail("fake@email.com");
        Assertions.assertNotNull(xuser);
        Assertions.assertEquals("John doe", xuser.getName());

        var l = repository.list();
        Assertions.assertEquals(l.size(), 1);
    }


}
