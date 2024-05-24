package com.example.webapp.config;


import com.example.webapp.model.AuthGrantedAuthority;
import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthGrantedAuthorityRepository;
import com.example.webapp.repository.AuthUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private AuthUserDetailsRepository authUserDetailsRepository;

    @Autowired
    private AuthGrantedAuthorityRepository authGrantedAuthorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // initialize the user in DB
    @Bean
    public CommandLineRunner initialize() {
        return (args) -> {

            AuthUserDetails adminUser = new AuthUserDetails();
            adminUser.setUsername("admin");
            adminUser.setName("Administrator");
            adminUser.setEmail("admin@email.com");
            adminUser.setPassword(passwordEncoder.encode("password"));
            adminUser.setEnabled(true);
            adminUser.setCredentialsNonExpired(true);
            adminUser.setAccountNonExpired(true);
            adminUser.setAccountNonLocked(true);

            AuthGrantedAuthority userGrant = new AuthGrantedAuthority("ROLE_USER", adminUser);
            AuthGrantedAuthority adminGrant = new AuthGrantedAuthority("ROLE_ADMIN", adminUser);
            AuthGrantedAuthority viewMessages = new AuthGrantedAuthority("VIEW_MESSAGES", adminUser);

            adminUser.setAuthorities(Set.of(userGrant, adminGrant, viewMessages));

            authUserDetailsRepository.save(adminUser);
            authGrantedAuthorityRepository.saveAll(Set.of(userGrant, adminGrant, viewMessages));

            AuthUserDetails user2 = new AuthUserDetails();
            user2.setUsername("user");
            user2.setName("John Doe");
            user2.setEmail("user@email.com");
            user2.setPassword(passwordEncoder.encode("password"));
            user2.setEnabled(true);
            user2.setCredentialsNonExpired(true);
            user2.setAccountNonExpired(true);
            user2.setAccountNonLocked(true);

            AuthGrantedAuthority userGrant2 = new AuthGrantedAuthority("ROLE_USER", user2);
            AuthGrantedAuthority userGrant3 = new AuthGrantedAuthority("VIEW_MESSAGES", user2);
            user2.setAuthorities(Set.of(userGrant2, userGrant3));

            authUserDetailsRepository.save(user2);
            authGrantedAuthorityRepository.saveAll(List.of(userGrant2, userGrant3));

            AuthUserDetails user3 = new AuthUserDetails();
            user3.setUsername("guest");
            user3.setName("Guest User");
            user3.setEmail("guest@email.com");
            user3.setPassword(passwordEncoder.encode("password"));
            user3.setEnabled(true);
            user3.setCredentialsNonExpired(true);
            user3.setAccountNonExpired(true);
            user3.setAccountNonLocked(true);

            authUserDetailsRepository.save(user3);
        };

    }
}