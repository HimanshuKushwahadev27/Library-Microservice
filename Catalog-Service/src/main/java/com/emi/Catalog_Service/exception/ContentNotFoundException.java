package com.emi.Catalog_Service.exception;

public class ContentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ContentNotFoundException(String message) {
		super(message);
	}
}
