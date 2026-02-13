package com.emi.User_service.exception;

public class ReviewNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ReviewNotFoundException(String msg) {
		super(msg);
	}

}
