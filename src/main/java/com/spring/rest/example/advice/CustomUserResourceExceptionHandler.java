package com.spring.rest.example.advice;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.rest.example.domain.ExceptionResponse;
import com.spring.rest.example.exceptions.UserAlreadyExistException;
import com.spring.rest.example.exceptions.UserNotFoundException;

@Controller
@ControllerAdvice
public class CustomUserResourceExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public final  ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
	
		ExceptionResponse errorResponse  = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final  ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
	
		ExceptionResponse errorResponse  = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public final  ResponseEntity<Object> handleUserAlreaddyExistxception(Exception ex, WebRequest request) {
	
		ExceptionResponse errorResponse  = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse errorResponse  = new ExceptionResponse(new Date(), "Invalid inout parameters", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
}
