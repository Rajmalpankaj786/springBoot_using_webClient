package com.example.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	public Optional<Employee> findByEmail(String email);
}
