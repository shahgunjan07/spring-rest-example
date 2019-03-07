package com.spring.rest.example.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public Resource<User> findOneUser(@PathVariable Integer id) {
		User user = service.findOne(id);
		
		/** Return HATEOAS Resource **/
		/** Hyper Media As The Engine Of Application State **/
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findAllUsers());
		resource.add(linkBuilder.withRel("All-Users"));
		
		return resource;
	}
	
	@DeleteMapping(value="/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PostMapping(value="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)  {
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
