package com.webClient.Entity;

import lombok.Data;

@Data
public class Employee {

	private Integer id;
	private String firstName;
	private String lastName;
	private String fullName ;
	private String email;
	private String password;
	private String contact;
	private String department;
	private String location;
}
