package com.emi.User_service.exception;

public class UserExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserExistsException(String msg) {
		super(msg);
	}

}
