package com.example.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<MyError> notFoundException(NotFoundException NPE , WebRequest req){
		MyError err = new MyError();
		err.setTimestamp(LocalDateTime.now());
		err.setDetails(req.getDescription(false));
		err.setMsg(NPE.getMessage());
		
		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<MyError> employeeNotFoundException(EmployeeNotFoundException UNPE , WebRequest req){
		MyError err = new MyError();
		err.setTimestamp(LocalDateTime.now());
		err.setDetails(req.getDescription(false));
		err.setMsg(UNPE.getMessage());
		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);
	}
		
	
}
