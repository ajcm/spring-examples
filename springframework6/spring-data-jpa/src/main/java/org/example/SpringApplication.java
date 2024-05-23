package org.example;

import org.example.dao.RoleRepository;
import org.example.dao.UserRepository;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SpringApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);

        Role role = new Role();
        role.setDescription("admin");

        Role role2 = new Role();
        role2.setDescription("user");

        role = roleRepository.save(role);
        role2 = roleRepository.save(role2);

        List<Role> roles = roleRepository.findAll();

        for (Role r : roles) {
            System.out.println(r);
        }

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);

        user1.getRoles().add(role);
        user1.getRoles().add(role2);

        user2.getRoles().add(role);

        userRepository.save(user1);
        userRepository.save(user2);


        User testUser1 = userRepository.findByEmail(user1.getEmail());
        System.out.println(testUser1);


//
//
//        user1.getRoles().add(role);
//        userRepository.save(user1);
//
//        DataSource dataSource = applicationContext.getBean(DataSource.class);
//        runQuery(dataSource);

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