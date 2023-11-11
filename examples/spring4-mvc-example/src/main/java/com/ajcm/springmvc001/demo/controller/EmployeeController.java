package com.ajcm.springmvc001.demo.controller;

import com.ajcm.springmvc001.demo.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employee")
public class EmployeeController 
{
	@Autowired
	EmployeeManager manager;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllEmployees(Model model)
	{
		model.addAttribute("employees", manager.getAllEmployees());
		return "employeesListDisplay";
	}
}