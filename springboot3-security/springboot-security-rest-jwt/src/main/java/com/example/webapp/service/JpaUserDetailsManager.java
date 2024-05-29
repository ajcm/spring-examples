package com.example.webapp.service;

import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class JpaUserDetailsManager implements UserDetailsManager {

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthUserRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found with email:  " + email));
    }

    @Override
    public void createUser(UserDetails user) {
        userDetailsRepository.save((AuthUserDetails) user);
    }

    @Override
    public void updateUser(UserDetails user) {
        userDetailsRepository.save((AuthUserDetails) user);
    }

    @Override
    public void deleteUser(String email) {
        AuthUserDetails userDetails = userDetailsRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No User found for email: " + email));
        userDetailsRepository.delete(userDetails);
    }

    /**
     * This method assumes that both oldPassword and the newPassword params
     * are encoded with configured passwordEncoder
     *
     * @param rawOldPassword the old password of the user
     * @param rawNewPassword the new password of the user
     */
    @Override
    @Transactional
    public void changePassword(String rawOldPassword, String rawNewPassword) {
        SecurityContext securityContext = securityContextHolderStrategy.getContext();

        var auth = securityContext.getAuthentication();
        var email = (String) auth.getPrincipal();
        var user = userDetailsRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));

        var match = passwordEncoder.matches(rawOldPassword, user.getPassword());
        if (!match) {
            throw new IllegalArgumentException("Old Password is incorrect.");
        }

        user.setPassword(passwordEncoder.encode(rawNewPassword));
        userDetailsRepository.save(user);
    }


    @Transactional
    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean userExists(String email) {
        return userDetailsRepository.findByEmail(email).isPresent();
    }


}
