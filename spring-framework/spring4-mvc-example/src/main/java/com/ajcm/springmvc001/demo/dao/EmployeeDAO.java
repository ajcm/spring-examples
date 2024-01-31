package com.ajcm.springmvc001.demo.dao;

import com.ajcm.springmvc001.demo.model.Employee;

import java.util.List;

public interface EmployeeDAO 
{
	List<Employee> getAllEmployees();
}