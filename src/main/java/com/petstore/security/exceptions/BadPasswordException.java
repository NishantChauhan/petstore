package com.petstore.security.exceptions;

public class BadPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BadPasswordException(String message) {
		super(message);
	}

}
