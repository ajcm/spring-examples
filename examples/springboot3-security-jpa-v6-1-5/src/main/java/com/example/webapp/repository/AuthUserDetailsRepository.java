package com.example.webapp.repository;

import com.example.webapp.model.AuthUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserDetailsRepository extends JpaRepository<AuthUserDetails,Long> {
    Optional<AuthUserDetails> findByUsername(String username);
}
