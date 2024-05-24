package com.example.webapp.controller;


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
@RequestMapping("/jwt/")
public class JwtController {

    @Autowired
    private final AuthUserDetailsRepository authUserDetailsRepository;

    @Autowired
    private SecurityService securityService;

    //getters setters
    public JwtController(AuthUserDetailsRepository authUserDetailsRepository) {
        this.authUserDetailsRepository = authUserDetailsRepository;
    }

    @GetMapping("token")
    public String getToken() {
        var optionalAuthUserDetails = securityService.getAuthUserDetails();


        return "xxx";

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


}
