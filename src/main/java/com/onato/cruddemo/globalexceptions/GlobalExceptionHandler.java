package com.onato.cruddemo.globalexceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	//send a error message without big stack trace to the client incase of any errors
	@ExceptionHandler(Exception.class)
    public static ResponseEntity<Object> handleExceptions( Exception exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Error Occured : " + exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        exception.printStackTrace();
        return entity;
    }
	
}
