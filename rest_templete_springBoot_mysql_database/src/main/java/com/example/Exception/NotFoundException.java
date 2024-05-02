package com.example.Exception;

public class NotFoundException extends RuntimeException {

	public NotFoundException() {
		super();
	}
	public NotFoundException(String msg) {
		super(msg);
	}
}
