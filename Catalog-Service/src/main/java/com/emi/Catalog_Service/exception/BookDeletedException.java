package com.emi.Catalog_Service.exception;

public class BookDeletedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BookDeletedException(String message) {
		super(message);
	}

}
