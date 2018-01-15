package com.mitrais.guide.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mitrais.guide.models.Employee;
import com.mitrais.guide.repositories.EmployeeRepository;

@Controller
public class NaturalApiController {
	private final EmployeeRepository employeeRepository;

	@Autowired
	public NaturalApiController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/api/employee/{id}")
	@ResponseBody
	public Employee getEmployee(@PathVariable("id") Integer id) {
		Employee employee = employeeRepository.findOne(id);
		return employee;
	}

}
