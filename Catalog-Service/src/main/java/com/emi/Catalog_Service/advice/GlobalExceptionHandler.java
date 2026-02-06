package com.emi.Catalog_Service.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emi.Catalog_Service.exception.BookDeletedException;
import com.emi.Catalog_Service.exception.BookNotFoundException;
import com.emi.Catalog_Service.exception.ContentDeletedException;
import com.emi.Catalog_Service.exception.ContentNotFoundException;
import com.emi.Catalog_Service.exception.GenreNotFoundException;
import com.emi.Catalog_Service.exception.NotAuthorizedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<?> handleBookNotFound(BookNotFoundException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(BookDeletedException.class)
	public ResponseEntity<?> handleBookDeleted(BookDeletedException ex){
		return ResponseEntity
				.status(410)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(ContentNotFoundException.class)
	public ResponseEntity<?> handleBookContentNotFound(ContentNotFoundException ex){
		return ResponseEntity
				.status(410)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(ContentDeletedException.class)
	public ResponseEntity<?> handleBookContentDeleted(ContentDeletedException ex){
		return ResponseEntity
				.status(410)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(GenreNotFoundException.class)
	public ResponseEntity<?> handleGenreNotFound(GenreNotFoundException ex){
		return ResponseEntity
				.status(410)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(NotAuthorizedException.class)
	public ResponseEntity<?> handleNotAuthorized(NotAuthorizedException ex){
		return ResponseEntity
				.status(410)
				.body(ex.getMessage());
	}
	
}
