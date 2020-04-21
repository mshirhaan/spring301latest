package com.eatza.review.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ReviewNotFoundException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundExceptions(ReviewNotFoundException ex, WebRequest request) throws Exception {
	ExceptionResponse exceptionRepsonse = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false));
	
	return new ResponseEntity(exceptionRepsonse, HttpStatus.NOT_FOUND);
	}

}
