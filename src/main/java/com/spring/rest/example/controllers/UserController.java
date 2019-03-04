package com.spring.rest.example.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.rest.example.domain.User;
import com.spring.rest.example.service.UserDaoService;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;
	
	@GetMapping(value="/users")
	public List<User> findAllUsers() {
		return service.findAll();
	}
	
	@GetMapping(value="/users/{id}")
	public ResponseEntity<Object> findOneUser(@PathVariable Integer id) {
		User user = service.findOne(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping(value="/users")
	public ResponseEntity<Object> createUser(@RequestBody User user)  {
		User newUser = service.add(user);
		
		/**
		 * Create URI of the new resource created 
		 */
		URI resourceLocation = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(newUser.getId())
			.toUri();
		
		
		/**
		 * Send proper HTTP status : CREATED : 201
		 * 
		 */
		return ResponseEntity.created(resourceLocation).build();
	}
	
}
