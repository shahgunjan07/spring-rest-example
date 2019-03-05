package com.spring.rest.example.domain;

import java.util.Date;

public class ExceptionResponse {

	private Date date;

	private String errorMessage;

	private String errorDetails;
	
	

	public ExceptionResponse(Date date, String errorMessage, String errorDetails) {
		super();
		this.date = date;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

}
