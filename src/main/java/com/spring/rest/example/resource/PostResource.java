package com.spring.rest.example.resource;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.example.domain.Post;
import com.spring.rest.example.domain.User;
import com.spring.rest.example.exceptions.PostNotFoundException;
import com.spring.rest.example.exceptions.UserNotFoundException;
import com.spring.rest.example.repository.PostRepository;
import com.spring.rest.example.repository.UserRepository;

@RestController
public class PostResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(value="/jpa/posts")
	public List<Post> findAllPosts() {
		
		List<User> users = userRepository.findAll();
		List<Post> posts = new ArrayList<>();
		users.stream().forEach(x -> {
										posts.addAll(x.getPosts());
									});
		return posts;
	}
	
	@DeleteMapping(value="/jpa/user/{userid}/posts/{postid}")
	public ResponseEntity<Object> deletePostForAUser(@PathVariable Integer userid, @PathVariable Integer postid) {
		
		Optional<User> user = userRepository.findById(userid);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException();
		}
		
		List<Post> posts = user.get().getPosts();
		
		Optional<Post> post = posts.stream().filter( p -> p.getId().equals(postid)).findFirst();
		
		if (!post.isPresent()) {
			throw  new PostNotFoundException();
		}
		
		postRepository.delete(post.get());
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	
}
