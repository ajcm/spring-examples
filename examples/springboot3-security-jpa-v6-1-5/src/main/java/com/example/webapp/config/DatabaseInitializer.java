package com.example.webapp.config;


import com.example.webapp.model.AuthGrantedAuthority;
import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthGrantedAuthorityRepository;
import com.example.webapp.repository.AuthUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

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
        return (args)->{
            System.out.println("application started");

            //uncomment if required

            AuthUserDetails user2 = new AuthUserDetails();
            user2.setUsername("user");
            user2.setPassword(passwordEncoder.encode("password"));
            user2.setEnabled(true);
            user2.setCredentialsNonExpired(true);
            user2.setAccountNonExpired(true);
            user2.setAccountNonLocked(true);

            AuthGrantedAuthority grantedAuthority = new AuthGrantedAuthority();
            grantedAuthority.setAuthority("USER");
            grantedAuthority.setAuthUserDetails(user2);
            authUserDetailsRepository.save(user2);
            authGrantedAuthorityRepository.save(grantedAuthority);
            user2.setAuthorities(Collections.singleton(grantedAuthority));
        };

    }
}