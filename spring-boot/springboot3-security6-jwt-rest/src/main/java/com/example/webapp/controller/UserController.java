package com.example.webapp.controller;


import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthUserDetailsRepository;
import com.example.webapp.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/auth")
public class UserController {

    @Autowired
    private final AuthUserDetailsRepository authUserDetailsRepository;

    @Autowired
    private SecurityService securityService;

    //getters setters
    public UserController(AuthUserDetailsRepository authUserDetailsRepository) {
        this.authUserDetailsRepository = authUserDetailsRepository;
    }

    @GetMapping("id")
    public String getId(Principal principal) {
        var optionalAuthUserDetails = securityService.getAuthUserDetails();

        if (optionalAuthUserDetails.isPresent()) {
            return optionalAuthUserDetails.get().getName();
        }

        var auth = securityService.getAuthentication();

        return auth.getName();

    }

    @GetMapping("users")
    public List<AuthUserDetails> getAllUser() {
        return authUserDetailsRepository.findAll();
    }
}
