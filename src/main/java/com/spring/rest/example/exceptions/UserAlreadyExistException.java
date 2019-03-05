package com.spring.rest.example.exceptions;

public class UserAlreadyExistException extends RuntimeException {

	
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7786387602732027984L;

	public UserAlreadyExistException() {
		super();
	}

	public UserAlreadyExistException(String message) {
		super(message);
	}

	
	
}
