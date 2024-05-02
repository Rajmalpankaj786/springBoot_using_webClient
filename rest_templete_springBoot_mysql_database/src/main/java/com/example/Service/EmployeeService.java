package com.example.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Employee;
import com.example.Exception.NotFoundException;
import com.example.Repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Employee addEmployee(Employee employee) {
		Optional<Employee> emp = employeeRepo.findByEmail(employee.getEmail());
		if(emp.isEmpty()) {
			return employeeRepo.save(employee);
		}
		
		throw new NotFoundException("given email id : "+employee.getEmail()+" already exist in system" );
		
	}
	
	public List<Employee> viewAllEmployee(){
		return employeeRepo.findAll();
	}
	
	public Employee viewEmployeeByID(Integer id) {
	Employee emp = employeeRepo.findById(id).orElseThrow(()-> new NotFoundException("Employee id : "+ id +" not exist"));
	return emp;
	}
	
	public Employee updateEmployee(Employee updateEmployee) {
		Optional<Employee> emp = employeeRepo.findByEmail(updateEmployee.getEmail());
		if(emp.isPresent()) {
			Employee existingEmployee = emp.get();
			existingEmployee.setFirstName(updateEmployee.getFirstName());
			existingEmployee.setLastName(updateEmployee.getLastName());
			existingEmployee.setPassword(updateEmployee.getPassword());
			existingEmployee.setContact(updateEmployee.getContact());
			existingEmployee.setDepartment(updateEmployee.getDepartment());
			existingEmployee.setLocation(updateEmployee.getLocation());
			existingEmployee.setFullName(updateEmployee.getFirstName()+" "+updateEmployee.getLastName());
			return employeeRepo.save(existingEmployee);
		}
		
		throw new NotFoundException("give employee email id : "+updateEmployee.getEmail()+" does't exist");
		
	}
	
	public Employee deleteEmployeeByID(Integer id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		if(emp.isPresent()) {
			Employee employee = emp.get(); 
			employeeRepo.deleteById(id);
				return employee;
			}
			throw new NotFoundException("employee does not exist given id : "+id);
		}

	public Employee deleteEmployeeByEmail(String email) {
		Optional<Employee> emp = employeeRepo.findByEmail(email);
		if(emp.isPresent()) {
			Employee employee = emp.get();
			employeeRepo.deleteById(employee.getId());
			return employee;
			}
		throw new NotFoundException("employee does not exist given email id : "+email);
	}
	
	
	

}
