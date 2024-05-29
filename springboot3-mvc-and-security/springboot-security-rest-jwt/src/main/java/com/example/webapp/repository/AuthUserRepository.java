package com.example.webapp.repository;

import com.example.webapp.model.AuthUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUserDetails, Long> {
    Optional<AuthUserDetails> findByEmail(String email);

    int deleteByEmail(String email);

    // Page<AuthUserDetails> findAll(Pageable pageable);

}
