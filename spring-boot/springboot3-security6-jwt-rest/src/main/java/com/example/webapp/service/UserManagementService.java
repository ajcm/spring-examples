package com.example.webapp.service;

import com.example.webapp.model.AuthGrantedAuthority;
import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthGrantedAuthorityRepository;
import com.example.webapp.repository.AuthUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserManagementService {
    @Autowired
    private AuthUserDetailsRepository userRepository;

    @Autowired
    private AuthGrantedAuthorityRepository grantedAuthorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JpaUserDetailsManager userDetailsManager;


    public void changePassword(String oldPassword, String newPassword) {

        userDetailsManager.changePassword(oldPassword, newPassword);
    }

    public void addUser(AuthUserDetails user) {
        var password = user.getPassword();
        var encPassword = passwordEncoder.encode(password);
        user.setPassword(encPassword);

        AuthGrantedAuthority userGrant = new AuthGrantedAuthority("ROLE_USER", user);
        user.setAuthorities(Set.of(userGrant));

        userRepository.save(user);
        grantedAuthorityRepository.save(userGrant);

    }


    public boolean userExists(String user) {
        return userDetailsManager.userExists(user);
    }


}
