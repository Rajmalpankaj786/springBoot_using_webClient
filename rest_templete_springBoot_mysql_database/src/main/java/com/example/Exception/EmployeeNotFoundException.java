package com.example.Exception;

public class EmployeeNotFoundException extends RuntimeException{

	public EmployeeNotFoundException() {
		super();
	}
	
	public EmployeeNotFoundException(String msg) {
		super(msg);
	}
}
