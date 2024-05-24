package com.example.webapp.controller;

import com.example.webapp.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MvcController {

    @Autowired
    private SecurityService securityService;


    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }


    @RequestMapping(path = "/")
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        if (principal != null) {
            modelAndView.addObject("username", principal.getName());
        }

        modelAndView.setViewName("home");
        return modelAndView;
    }


    @RequestMapping(path = "/userinfo")
    public ModelAndView getUserInfo() {
        ModelAndView modelAndView = new ModelAndView();

        var authentication = securityService.getAuthentication();
        modelAndView.addObject("authentication", authentication);
        modelAndView.addObject("token", authentication.getName());

        var authorities = securityService.getAuthorities();
        modelAndView.addObject("authorities", authorities);

        var optionalAuthUserDetails = securityService.getAuthUserDetails();

        if (optionalAuthUserDetails.isPresent()) {
            modelAndView.addObject("authUserDetails", optionalAuthUserDetails.get());
        }

        modelAndView.setViewName("user/userInfo");
        return modelAndView;

    }
}
