package com.ajcm.springmvc001.demo.controller;

import com.ajcm.springmvc001.demo.model.Login;
import com.ajcm.springmvc001.demo.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController
{
	@Autowired
	EmployeeManager manager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model)
	{
		return "home";
	}



	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login( @ModelAttribute("login") Login login,
						 BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "error";
		}
		System.out.println("Login  .. "+login.getUsername());
		model.addAttribute("username", login.getUsername());

		return "home";
	}
}