package com.spring.rest.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.rest.example.domain.User;

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
	
	public void delete(User user) {
		if (userList.contains(user)) {
			userList.remove(user);
		} else {
			// Exceception Handling
		}
		
	}
	
	public User add(User user) {
		if (userList.contains(user)) {
			// Exception Handling
		} else {
			
			if (user.getId() == null) {
				user.setId(new Integer(++userCount));
			}
			
			
			userList.add(user);
		}
		
		return user;
	}
	
	public User findOne(Integer id) {
		return userList
					.stream()
					.filter( u -> u.getId().equals(id))
					.findFirst().get();
	}
}
