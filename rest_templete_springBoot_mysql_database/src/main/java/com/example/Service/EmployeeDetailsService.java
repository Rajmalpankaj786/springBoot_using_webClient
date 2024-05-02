package com.example.Service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.Entity.Employee;
import com.example.Exception.EmployeeNotFoundException;
import com.example.Repository.EmployeeRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeDetailsService implements UserDetailsService {

	private EmployeeRepo employeeRepo;
	
	
	public EmployeeDetailsService(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws EmployeeNotFoundException {
		log.info("inside loadUserByUsername method of EmployeeDetailsService");
	    Optional<Employee> emp = employeeRepo.findByEmail(username);
//	    if(emp.isPresent()) {
//	    	Employee employee = emp.get();
//	    	List<GrantedAuthority> auth = new ArrayList<>();
//	    	SimpleGrantedAuthority SGA = new Simpleg
//	    }
	    
	    return emp.orElseThrow(() -> new EmployeeNotFoundException("No Employee found this userName : "+username));
	    
	
	}

}
