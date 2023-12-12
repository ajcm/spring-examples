package com.example.webapp.service;

import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class JpaUserDetailsManager implements UserDetailsManager {

    @Autowired
    private AuthUserDetailsRepository userDetailsRepository;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("No user found with username = " + username));
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
    public void deleteUser(String username) {
        AuthUserDetails userDetails = userDetailsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No User found for username -> " + username));
        userDetailsRepository.delete(userDetails);
    }

    /**
     * This method assumes that both oldPassword and the newPassword params
     * are encoded with configured passwordEncoder
     *
     * @param oldPassword the old password of the user
     * @param newPassword the new password of the user
     */
    @Override
    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        throw new IllegalArgumentException("not implemented");
    }

    @Override
    public boolean userExists(String username) {
        return userDetailsRepository.findByUsername(username).isPresent();
    }


}
