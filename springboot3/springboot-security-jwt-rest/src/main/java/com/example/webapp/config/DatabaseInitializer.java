package com.example.webapp.config;


import com.example.webapp.model.AuthGrantedAuthority;
import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthAuthorityRepository;
import com.example.webapp.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthAuthorityRepository authAuthorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // initialize the user in DB
    @Bean
    public CommandLineRunner initialize() {
        return (args) -> {

            AuthUserDetails adminUser = new AuthUserDetails();
            adminUser.setName("Administrator");
            adminUser.setEmail("admin");
            adminUser.setPassword(passwordEncoder.encode("password"));
            adminUser.setEnabled(true);
            adminUser.setCredentialsNonExpired(true);
            adminUser.setAccountNonExpired(true);
            adminUser.setAccountNonLocked(true);

            AuthGrantedAuthority userGrant = new AuthGrantedAuthority("ROLE_USER", adminUser);
            AuthGrantedAuthority adminGrant = new AuthGrantedAuthority("ROLE_ADMIN", adminUser);

            adminUser.setAuthorities(Set.of(userGrant, adminGrant));

            authUserRepository.save(adminUser);
            authAuthorityRepository.saveAll(Set.of(userGrant, adminGrant));

            AuthUserDetails user = new AuthUserDetails();
            user.setName("User");
            user.setEmail("user");
            user.setPassword(passwordEncoder.encode("password"));
            user.setEnabled(true);
            user.setCredentialsNonExpired(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);

            AuthGrantedAuthority userGrant2 = new AuthGrantedAuthority("ROLE_USER", user);


            user.setAuthorities(Set.of(userGrant2));

            authUserRepository.save(user);
            authAuthorityRepository.saveAll(Set.of(userGrant2));

        };

    }
}