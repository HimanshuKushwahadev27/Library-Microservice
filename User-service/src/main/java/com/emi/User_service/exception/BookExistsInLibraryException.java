package com.emi.User_service.exception;

public class BookExistsInLibraryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public BookExistsInLibraryException(String msg) {
		super(msg);
	}
}
