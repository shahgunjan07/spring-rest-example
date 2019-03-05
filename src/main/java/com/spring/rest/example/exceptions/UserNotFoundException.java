package com.spring.rest.example.exceptions;

public class UserNotFoundException  extends RuntimeException{


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3133805833049010476L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}

}
