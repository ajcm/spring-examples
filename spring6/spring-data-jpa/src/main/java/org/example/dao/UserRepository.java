package org.example.dao;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);
    User findXXXXXByEmail(String email);
    User searchXXXXXByEmail(String email);
    User searchStarting3ByEmail(String email);
}
