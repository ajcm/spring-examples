package com.example.webapp.controller.admin;

import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class UserAdminController {
    private static Logger LOG = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    AuthUserRepository authUserRepository;


    @GetMapping("/users")
    public Page<AuthUserDetails> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return authUserRepository.findAll(pageable);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<AuthUserDetails> getUserByEmail(@PathVariable String email) {

        var optionalAuthUserDetails = authUserRepository.findByEmail(email);

        if (optionalAuthUserDetails.isEmpty()) {
            throw new UsernameNotFoundException("No user found with email:  " + email);
        }

        return ResponseEntity.ok(optionalAuthUserDetails.get());


    }


}
