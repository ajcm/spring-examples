package com.ajcm.springmvc001.demo.dao;

import com.ajcm.springmvc001.demo.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	public List<Employee> getAllEmployees()
	{
		List<Employee> employees = new ArrayList<>();
		
		Employee vo1 = new Employee();
		vo1.setId(1);
		vo1.setFirstName("Lokesh");
		vo1.setLastName("Gupta");
		employees.add(vo1);

		Employee vo2 = new Employee();
		vo2.setId(2);
		vo2.setFirstName("Raj");
		vo2.setLastName("Kishore");
		employees.add(vo2);
		
		return employees;
	}
}