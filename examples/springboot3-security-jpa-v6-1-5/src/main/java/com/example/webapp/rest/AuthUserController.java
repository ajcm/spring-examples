package com.example.webapp.rest;


import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthUserDetailsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/auth")
public class AuthUserController {

    private AuthUserDetailsRepository authUserDetailsRepository;

    @GetMapping("users")
    public List<AuthUserDetails> getAllUser() {
        return authUserDetailsRepository.findAll();
    }


    //getters setters
    public AuthUserController(AuthUserDetailsRepository authUserDetailsRepository) {
        this.authUserDetailsRepository = authUserDetailsRepository;
    }
}
