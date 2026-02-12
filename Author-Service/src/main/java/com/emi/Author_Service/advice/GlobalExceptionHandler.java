package com.emi.Author_Service.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emi.Author_Service.exception.DeletedException;
import com.emi.Author_Service.exception.RegisteredAuthorException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RegisteredAuthorException.class)
	public ResponseEntity<?> handleAuthorExistsDuplication(RegisteredAuthorException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(DeletedException.class)
	public ResponseEntity<?> handleAuthorDeletedDuplication(DeletedException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}

	
}
