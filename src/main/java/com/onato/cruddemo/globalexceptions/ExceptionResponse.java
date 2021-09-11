package com.onato.cruddemo.globalexceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {

	private LocalDateTime dateTime;
	private String message;

	public ExceptionResponse() {
	}
	
	public ExceptionResponse(LocalDateTime dateTime, String message) {
		super();
		this.dateTime = dateTime;
		this.message = message;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
