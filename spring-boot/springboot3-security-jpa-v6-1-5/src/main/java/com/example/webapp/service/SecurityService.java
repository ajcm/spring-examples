package com.example.webapp.service;

import com.example.webapp.model.AuthGrantedAuthority;
import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthGrantedAuthorityRepository;
import com.example.webapp.repository.AuthUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SecurityService {

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();
    @Autowired
    private AuthUserDetailsRepository userRepository;
    @Autowired
    private AuthGrantedAuthorityRepository grantedAuthorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JpaUserDetailsManager userDetailsManager;

    public Authentication getAuthentication() {
        SecurityContext securityContext = securityContextHolderStrategy.getContext();
        return securityContext.getAuthentication();
    }

    public List<String> getAuthorities() {
        SecurityContext securityContext = securityContextHolderStrategy.getContext();

        if (securityContext.getAuthentication() != null) {
            var authentication = securityContext.getAuthentication();
            return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        }
        return List.of();
    }


    public Optional<AuthUserDetails> getAuthUserDetails() {
        var authentication = getAuthentication();

        if (authentication == null) {
            return Optional.empty();
        }

        var authUserDetails = authentication.getPrincipal();

        if (authUserDetails == null) {
            return Optional.empty();
        }

        if (authUserDetails instanceof String username) {
            if (!userDetailsManager.userExists(username)) {
                return Optional.empty();
            } else {
                return Optional.ofNullable((AuthUserDetails) userDetailsManager.loadUserByUsername(username));
            }
        }

        if (authUserDetails instanceof AuthUserDetails) {
            return Optional.of((AuthUserDetails) authUserDetails);
        }
        return Optional.empty();
    }

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
