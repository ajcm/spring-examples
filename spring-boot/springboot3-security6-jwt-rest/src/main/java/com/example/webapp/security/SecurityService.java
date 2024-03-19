package com.example.webapp.security;

import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.service.JpaUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityService {

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();


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

        if (authUserDetails instanceof String email) {
            if (!userDetailsManager.userExists(email)) {
                return Optional.empty();
            } else {
                return Optional.ofNullable((AuthUserDetails) userDetailsManager.loadUserByUsername(email));
            }
        }

        if (authUserDetails instanceof AuthUserDetails) {
            return Optional.of((AuthUserDetails) authUserDetails);
        }
        return Optional.empty();
    }


}
