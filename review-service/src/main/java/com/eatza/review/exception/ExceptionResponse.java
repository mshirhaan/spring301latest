package com.eatza.review.exception;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExceptionResponse {

	private LocalDate date;
	private String message;
	private String details;
	
	public ExceptionResponse(LocalDate date, String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}
}
