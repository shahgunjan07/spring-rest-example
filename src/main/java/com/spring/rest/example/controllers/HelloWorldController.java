package com.spring.rest.example.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	/**
	 * messageSource
	 */
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(value="/welcome")
	public String welcomeMessage(@RequestHeader(name="Accept-Language", required= false) Locale locale) {
		return messageSource.getMessage("welcome.message", null, locale);
		
	}
}
