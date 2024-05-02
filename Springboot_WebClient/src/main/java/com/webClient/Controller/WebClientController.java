package com.webClient.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.webClient.Entity.Employee;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/Employee-client")
public class WebClientController {

WebClient webClient;
	
	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8080/employees")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
	
	@PostMapping("/post")
	public Mono<Employee> postEmployee(@RequestBody Employee employee ){
		return webClient.post().uri("/post").syncBody(employee).retrieve().bodyToMono(Employee.class);
	}
	
	@GetMapping("/getAll")
	public Flux<Employee> getAllEmployee(){
		return webClient.get().uri("/getAll").retrieve().bodyToFlux(Employee.class);
	}
	
	@GetMapping("/getById/{id}")
	public Mono<Employee> getEmployeeById(@PathVariable Integer id){
		return webClient.get().uri("/getById/{id}",id).retrieve().bodyToMono(Employee.class);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Mono<Employee> deleteEmployeeById(@PathVariable Integer id){
		return webClient.delete().uri("/deleteById/{id}",id).retrieve().bodyToMono(Employee.class);
	}
	
	@PutMapping("/update")
	public Mono<Employee> updateEmployee(@RequestBody Employee employee){
		return webClient.put()
                .uri("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(employee))
                .retrieve()
                .bodyToMono(Employee.class);
	}
	
	
}
