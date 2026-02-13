package com.emi.User_service.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emi.User_service.exception.BookExistsInLibraryException;
import com.emi.User_service.exception.BookmarkException;
import com.emi.User_service.exception.DeletedException;
import com.emi.User_service.exception.ReviewNotFoundException;
import com.emi.User_service.exception.UserExistsException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookExistsInLibraryException.class)
	public ResponseEntity<?> handleBookExistsFound(BookExistsInLibraryException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(BookmarkException.class)
	public ResponseEntity<?> handleBookmarkFound(BookmarkException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(UserExistsException.class)
	public ResponseEntity<?> handleUserFound(UserExistsException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(DeletedException.class)
	public ResponseEntity<?> handleDeleteFound(DeletedException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<?> handleReviewFound(ReviewNotFoundException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}
}
