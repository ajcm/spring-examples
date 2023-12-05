package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MvcController {


    @RequestMapping(path = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/nonauth")
    public ModelAndView doNonAuthenticated(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();


        if (principal != null) {
            modelAndView.addObject("username", principal.getName());

        }
        modelAndView.setViewName("nonauth");
        return modelAndView;

    }

}
