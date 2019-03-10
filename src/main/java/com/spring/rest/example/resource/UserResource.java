package com.spring.rest.example.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.spring.rest.example.exceptions.UserNotFoundException;
import com.spring.rest.example.repository.UserRepository;

@RestController
public class UserResource {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/jpa/users")
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(value="jpa/users/{id}")
	public Resource<User> findOneUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException();
		}
		
		/** Return HATEOAS Resource **/
		/** Hyper Media As The Engine Of Application State **/
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findAllUsers());
		resource.add(linkBuilder.withRel("All-Users"));
		
		return resource;
	}
	
	@DeleteMapping(value="/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping(value="/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)  {
		User newUser = userRepository.save(user);
		
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
