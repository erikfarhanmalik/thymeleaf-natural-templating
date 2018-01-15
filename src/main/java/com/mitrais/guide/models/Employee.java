package com.mitrais.guide.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
	private Integer id;
	private String username;
	private String email;
	private String name;
	private String role;
}
