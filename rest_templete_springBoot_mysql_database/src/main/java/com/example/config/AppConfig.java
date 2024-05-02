package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

	@Bean
	public SecurityFilterChain EmployeeMethods(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.DELETE, "/employees/{id}").permitAll()
				.requestMatchers(HttpMethod.PUT, "/employees/update").permitAll()
				.requestMatchers(HttpMethod.GET, "/employees/getAll").permitAll()
				.requestMatchers(HttpMethod.DELETE, "/employees/deleteById/{id}").permitAll()
				.requestMatchers(HttpMethod.DELETE, "/employees/deleteByEmail/{email}").permitAll()
				.requestMatchers(HttpMethod.GET, "/employees/getById/{id}").permitAll()
				.requestMatchers(HttpMethod.POST, "/employees/post").permitAll()

				.anyRequest().authenticated()).csrf(c -> c.disable()).httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
