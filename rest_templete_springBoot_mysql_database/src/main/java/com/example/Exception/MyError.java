package com.example.Exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyError {

	private String msg;
	private LocalDateTime timestamp;
	private String details;
	
	
}
