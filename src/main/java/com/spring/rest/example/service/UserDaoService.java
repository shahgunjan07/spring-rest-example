package com.spring.rest.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.spring.rest.example.domain.User;
import com.spring.rest.example.exceptions.UserAlreadyExistException;
import com.spring.rest.example.exceptions.UserNotFoundException;

@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<>();
	
	private static int userCount = 3;
	
	static {
		userList.add(new User(1, "Gunjan", LocalDate.of(1989, 8, 18)));
		userList.add(new User(2, "Shreya", LocalDate.of(1992, 2, 21)));
		userList.add(new User(3, "Suresh", LocalDate.of(1955, 9, 7)));
	}
	
	
	public List<User> findAll() {
		return userList;
	}
	
	public void delete(Integer id) {
		
		Iterator<User> itr = userList.iterator();
		
		User user = null;
		boolean userDeleted = false;
		while (itr.hasNext()) {
			
			user = itr.next();
			if(user.getId().equals(id)) {
				itr.remove();
				userDeleted = true;
				break;
			}
		}
		
		if (!userDeleted) {
			throw new UserNotFoundException("User does not exist");
		} 
		
	}
	
	public User add(User user) {
		if (userList.contains(user)) {
			throw new UserAlreadyExistException("User already exist");
		} else {
			if (user.getId() == null) {
				user.setId(new Integer(++userCount));
			}
			userList.add(user);
		}
		return user;
	}
	
	public User findOne(Integer id) {
		
		Optional<User> user = userList
				.stream()
				.filter( u -> u.getId().equals(id))
				.findFirst();
		
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException("User does not exist"); 
		}
	}
}
