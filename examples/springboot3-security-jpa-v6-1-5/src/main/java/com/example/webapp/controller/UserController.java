package com.example.webapp.controller;

import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthUserDetailsRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/all")
    public ModelAndView list() {
        List<AuthUserDetails> users = userRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);

        modelAndView.setViewName("user/users");
        return modelAndView;
    }

    @PostMapping("post")
    public String doPost(AuthUserDetails user, BindingResult bindingResult) {

        var password = user.getPassword();
        var encPassword = passwordEncoder.encode(password);
        user.setPassword(encPassword);

        userRepository.save(user);
        return "redirect:/users/all";
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


}
