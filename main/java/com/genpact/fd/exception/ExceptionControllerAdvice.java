package com.genpact.fd.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(Exception exception) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setErrorMessage(exception.getMessage());
		errorDetails.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(FoodDeliveryException.class)
	public ResponseEntity<ErrorDetails> infyBankexceptionHandler(FoodDeliveryException exception) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setErrorMessage(exception.getMessage());
		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
}