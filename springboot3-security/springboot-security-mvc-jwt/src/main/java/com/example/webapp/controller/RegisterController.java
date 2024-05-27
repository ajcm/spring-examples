package com.example.webapp.controller;

import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthGrantedAuthorityRepository;
import com.example.webapp.repository.AuthUserDetailsRepository;
import com.example.webapp.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private AuthUserDetailsRepository userRepository;

    @Autowired
    private AuthGrantedAuthorityRepository grantedAuthorityRepository;

    @Autowired
    private SecurityService securityService;


    @GetMapping("/register")
    @Transactional
    public String addUser() {
        return "register";
    }


    @PostMapping("/register")
    public String doPost(AuthUserDetails user, BindingResult bindingResult) {

        if (securityService.userExists(user.getUsername())) {
            return "user/userExists";
        }

        securityService.addUser(user);

        return "redirect:/login";
    }


}
