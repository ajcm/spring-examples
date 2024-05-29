package com.example.webapp.controller;

import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthGrantedAuthorityRepository;
import com.example.webapp.repository.AuthUserDetailsRepository;
import com.example.webapp.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthUserDetailsRepository userRepository;

    @Autowired
    private AuthGrantedAuthorityRepository grantedAuthorityRepository;

    @Autowired
    private SecurityService securityService;


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/all")
    public ModelAndView list() {
        List<AuthUserDetails> users = userRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);

        modelAndView.setViewName("user/users");
        return modelAndView;
    }


    @GetMapping("details/{username}")
    public ModelAndView getDetails(@PathVariable String username) {

        Optional<AuthUserDetails> optionalUser = userRepository.findByUsername(username);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", optionalUser.get());

        modelAndView.setViewName("user/user");
        return modelAndView;
    }

    @GetMapping("delete/{username}")
    @Transactional
    public String deleteDetails(@PathVariable String username) {
        userRepository.deleteByUsername(username);
        return "redirect:/users/all";
    }


    @GetMapping("changePassword")
    public ModelAndView changePassword() {
        ModelAndView modelAndView = new ModelAndView();

        var optionalAuthUserDetails = securityService.getAuthUserDetails();

        if (optionalAuthUserDetails.isPresent()) {
            modelAndView.addObject("authUserDetails", optionalAuthUserDetails.get());
        }

        modelAndView.setViewName("user/changePassword");
        return modelAndView;
    }


    @PostMapping("changePassword")
    @Transactional
    public String doChangePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        securityService.changePassword(oldPassword, newPassword);
        return "redirect:user/userInfo";
    }


}
