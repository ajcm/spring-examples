package com.example.webapp.controller;

import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ModelAndView list() {
        List<User> users = userRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @PostMapping("post")
    public String doPost(User user, BindingResult bindingResult) {

        var password = user.getPassword();
        var encPassword = passwordEncoder.encode(password);
        user.setPassword(encPassword);

        userRepository.save(user);
        return "redirect:/users/all";
    }


    @GetMapping("details/{username}")
    public ModelAndView getDetails(@PathVariable String username) {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", optionalUser.get());
        modelAndView.setViewName("user");

        return modelAndView;
    }

//
//
//    @GetMapping("delete/{id}")
//    public String delete(@PathVariable String id) {
//        messageService.delete(id);
//        return "redirect:/messages/all";
//    }


}
