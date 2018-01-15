package com.mitrais.guide.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mitrais.guide.repositories.EmployeeRepository;

@Controller
public class NaturalController {
	private final EmployeeRepository employeeRepository;

	@Autowired
	public NaturalController(com.mitrais.guide.repositories.EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/")
	public String index(Map<String, Object> model) {
		model.put("user", employeeRepository.getLoggedInUser());
		return "index";
	}

	@GetMapping("/list-page")
	public String secondPage(Map<String, Object> model) {
		model.put("user", employeeRepository.getLoggedInUser());
		model.put("employees", employeeRepository.getEmployees());

		return "list-page";
	}

}
