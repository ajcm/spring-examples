package com.example.webapp.repository;

import com.example.webapp.model.AuthGrantedAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthAuthorityRepository extends JpaRepository<AuthGrantedAuthority, Long> {
}
