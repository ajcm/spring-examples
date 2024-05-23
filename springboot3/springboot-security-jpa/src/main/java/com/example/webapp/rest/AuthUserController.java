package com.example.webapp.rest;


import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthUserDetailsRepository;
import com.example.webapp.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/auth")
public class AuthUserController {

    @Autowired
    private final AuthUserDetailsRepository authUserDetailsRepository;

    @Autowired
    private SecurityService securityService;

    //getters setters
    public AuthUserController(AuthUserDetailsRepository authUserDetailsRepository) {
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
