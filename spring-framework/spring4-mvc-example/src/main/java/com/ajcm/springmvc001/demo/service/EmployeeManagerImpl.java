package com.ajcm.springmvc001.demo.service;

import com.ajcm.springmvc001.demo.dao.EmployeeDAO;
import com.ajcm.springmvc001.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManagerImpl implements EmployeeManager {

	@Autowired
    EmployeeDAO dao;
	
	public List<Employee> getAllEmployees()
	{
		return dao.getAllEmployees();
	}
}
