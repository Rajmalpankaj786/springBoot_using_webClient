package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Employee;
import com.example.Service.EmployeeService;

import jakarta.websocket.server.PathParam;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private PasswordEncoder passwordEncoder;

	public EmployeeController(EmployeeService employeeService, PasswordEncoder passwordEncoder) {
		super();
		this.employeeService = employeeService;
		this.passwordEncoder = passwordEncoder;
	}

//	@GetMapping("/hi")
//	public  ResponseEntity<String> welcome(){
//	    User user = (User) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//		return new ResponseEntity<String>("hello, how are you?" , HttpStatus.OK);
//	}

	@PostMapping(value = "/employees/post")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		String hashPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(hashPassword);
		return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/employees/log-in-Employee")
	public ResponseEntity<Employee> logInEmployee() {
		  Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<Employee>(user, HttpStatus.CREATED);
	}

	@GetMapping("/employees/logout")
    public ResponseEntity<String> logOutEmployee() {
        // Clear the authentication context
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
	
	@GetMapping(value = "/employees/getAll")
	public ResponseEntity<List<Employee>> viewAllEmployee() {
		return new ResponseEntity<List<Employee>>(employeeService.viewAllEmployee(), HttpStatus.OK);
	}

	@GetMapping(value = "/employees/getById/{id}")
	public ResponseEntity<Employee> viewEmployeeByID(@PathVariable Integer id) {
		return new ResponseEntity<Employee>(employeeService.viewEmployeeByID(id), HttpStatus.OK);
	}

	@PutMapping(value = "/employees/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(emp), HttpStatus.OK);
	}

	@DeleteMapping(value = "/employees/deleteById/{id}") 
	public ResponseEntity<Employee> deleteEmployeeByID(@PathVariable Integer id) {
		return new ResponseEntity<Employee>(employeeService.deleteEmployeeByID(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/employees/deleteByEmail/{email}")
	public ResponseEntity<Employee> deleteEmployeeByEmail(@PathVariable String email) {
		return new ResponseEntity<Employee>(employeeService.deleteEmployeeByEmail(email), HttpStatus.OK);
	}

}
