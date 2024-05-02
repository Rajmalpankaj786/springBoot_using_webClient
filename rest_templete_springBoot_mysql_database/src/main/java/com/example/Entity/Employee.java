package com.example.Entity;

import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Data;

@Data
@Entity
@Table(name = "emp_table")
public class Employee implements UserDetails, CredentialsContainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer id;
	
	@Column(name = "emp_firstName")
	private String firstName;
	
	@Column(name = "emp_lastName")
	private String lastName;
	
	
	@Column(name = "emp_fullName")
	private String fullName ;
	
	@Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", flags = Flag.CASE_INSENSITIVE, message = "Email must be in a valid format")
	@Column(name = "emp_email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "emp_password", nullable = false)
	@JsonProperty(access=Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "emp_contact")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone Number should be exactly ten digits")
	private String contact;
	
	@Column(name = "emp_department")
	private String department;
	
	@Column(name = "emp_location")
	private String location;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public void eraseCredentials() {
		this.password=null;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Employee(Integer id, String firstName, String lastName,
			@Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", flags = Flag.CASE_INSENSITIVE, message = "Email must be in a valid format") String email,
			String password,
			@Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone Number should be exactly ten digits") String contact,
			String department, String location) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = firstName+" "+lastName;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.department = department;
		this.location = location;
	}

	public Employee() {
		
	}
	
}
