package com.webClient.Entity;

import lombok.Data;

@Data
public class Customer {

	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
}
