package com.example.webapp.controller;

import com.example.webapp.model.AuthUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class MvcController {

    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();


    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }


    @RequestMapping(path = "/")
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
//        SecurityContext securityContext = securityContextHolderStrategy.getContext();
//
//        if (securityContext.getAuthentication() != null && securityContext.getAuthentication().getPrincipal() != null) {
//
//            principal2.toString();
//            var principal = securityContext.getAuthentication().getPrincipal();
//            modelAndView.addObject("user", principal);
//        }

        if (principal != null) {
            modelAndView.addObject("username", principal.getName());
        }

        modelAndView.setViewName("home");
        return modelAndView;
    }


    @RequestMapping(path = "/userinfo")
    public ModelAndView getUserInfo() {
        ModelAndView modelAndView = new ModelAndView();

        SecurityContext securityContext = securityContextHolderStrategy.getContext();

        if (securityContext.getAuthentication() != null) {

            var authentication = securityContext.getAuthentication();

            modelAndView.addObject("token", authentication.getName());
            var authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());


            var authUserDetails = authentication.getPrincipal();


            modelAndView.addObject("authentication", authentication);
            modelAndView.addObject("authorities", authorities);



            if (authUserDetails instanceof AuthUserDetails) {
                modelAndView.addObject("authUserDetails", authUserDetails);
            }


        }


        modelAndView.setViewName("user/userinfo");
        return modelAndView;

    }
}
