package com.mitrais.guide.repositories;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mitrais.guide.models.Employee;

@Service
public class EmployeeRepository {

	public Employee getLoggedInUser() {
		Employee user = Employee.builder()
		.id(1)
		.username("efmalik")
		.email("efmalik@mitrais.com")
		.name("Erik F Malik")
		.role("Admin")
		.build();
		return user;
	}
	
	public String getUsername(String name) {
		return name.replaceAll(" ", "").toLowerCase();
	}
	
	public List<Employee> getEmployees() {
		List<Employee> employees = Lists.newArrayList();
		List<String> names = Lists.newArrayList(
				"Anita Watkins",
				"Wade Carlson",
				"Stewart Howell",
				"Kenny Quinn",
				"Frederick Day");
		
		IntStream.range(0, names.size())
		  .forEach(idx -> employees.add(Employee.builder()
					.id(idx + 1)
					.username(getUsername(names.get(idx)))
					.email(getUsername(names.get(idx)) + "@mitrais.com")
					.name(names.get(idx))
					.role("User")
					.build())
		  );
		return employees;
	}
	
	public Employee findOne(Integer id) {
		return getEmployees().stream().filter(employee -> id.equals(employee.getId())).findFirst().orElse(null);
	}
}
